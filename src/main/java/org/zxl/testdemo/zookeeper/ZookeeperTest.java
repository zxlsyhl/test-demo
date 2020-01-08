package org.zxl.testdemo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.ZooKeeperServerListener;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public final class ZookeeperTest {
    ZooKeeper zooKeeper = null;
    @Before
    public void before() throws Exception{
        Properties prop = new Properties();
        String zookeeperPropPath = ZookeeperTest.class.getResource("/zookeeper.properties").getPath();
        System.out.println("zookeeperPropPath:"+zookeeperPropPath);
        InputStream is = new FileInputStream(zookeeperPropPath);
        prop.load(is);
        String connectString = prop.getProperty("connectString");
        System.out.println("connectString:"+connectString);
        final CountDownLatch cdl = new CountDownLatch(1);
        zooKeeper = new ZooKeeper(connectString, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if(event.getState() == Event.KeeperState.SyncConnected){
                    System.out.println("连接成功");
                    cdl.countDown();
                }
                if(event.getType() == Event.EventType.NodeDeleted){
                    System.out.println("删除节点:"+event.getPath());
                }
                if(event.getType() == Event.EventType.NodeCreated){
                    System.out.println("创建节点："+event.getPath());
                }
            }
        });
        cdl.await();
        System.out.println("zooKeeper.getState():"+zooKeeper.getState());
        System.out.println("zooKeeper.getSessionId():"+zooKeeper.getSessionId());
    }

    @Test
    public void test1() throws Exception{
        zooKeeper.create("/zxl/jdbc/db2","1.0.9".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.create("/zxl/jdbc/sqlServer","6.0.9".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("test1 finished!!");
    }

    @Test
    public void test2() throws Exception{

        zooKeeper.exists("/zxl/jdbc/mongodb", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("test2 watcher begin");
                System.out.println(watchedEvent.getState());
                System.out.println("test2 watcher end");
            }
        });
        System.out.println("判断是否存在");
        zooKeeper.create("/zxl/jdbc/mongodb","4.1.2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("create /zxl/jdbc/mongodb  end");
    }

    @Test
    public void test3() throws Exception{
        Stat stat = new Stat();
        stat.setVersion(1);
        byte[] sqlserver = zooKeeper.getData("/zxl/jdbc/sqlServer", false,stat); //stat用于接收节点信息
        if(sqlserver != null){
            System.out.println(new String(sqlserver));
        }

        AsyncCallback.DataCallback dataCallback = new AsyncCallback.DataCallback(){
            @Override
            public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
                System.out.println("stat:"+stat);
            }
        };
        zooKeeper.getData("/zxl/jdbc/sqlServer",false, dataCallback,null);

    }

    @Test
    public void test4() throws Exception{
        System.out.println("判断节点是否存在，不存在创建、存在则删除");
        if(zooKeeper.exists("/zxl/jdbc/sqlServer",true)==null){
            String createReturn  = zooKeeper.create("/zxl/jdbc/sqlServer", "".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("创建完成"+createReturn);
        }else{
            zooKeeper.delete("/zxl/jdbc/sqlServer",0);
            System.out.println("删除节点，触发监听。");
        }
    }

    @Test
    public void test5() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists("/zxl/jdbc/sqlServer",true);
        System.out.println("stat:"+stat);
    }

    @Test
    public void test6() throws KeeperException, InterruptedException {
        zooKeeper.create("/zxl/jdbc/","".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        zooKeeper.create("/zxl/jdbc/","".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        List<String> children = zooKeeper.getChildren("/zxl/jdbc",false);
        for(String str:children){
            System.out.println(str);
        }
    }
}
