package org.zxl.testdemo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.ZooKeeperServerListener;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
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

        byte[] sqlserver = zooKeeper.getData("/zxl/jdbc/sqlServer", false,null);
        if(sqlserver != null){
            System.out.println(new String(sqlserver.toString()));
        }
    }

    public void test4() throws Exception{
    }
}
