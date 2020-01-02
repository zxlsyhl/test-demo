package org.zxl.testdemo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

public final class ZookeeperTest {
    ZooKeeper zooKeeper = null;
    @Before
    public void before() throws Exception{
        zooKeeper = new ZooKeeper("localhost:2181,localhost:2182,localhost:2183", 5000, null);
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
}
