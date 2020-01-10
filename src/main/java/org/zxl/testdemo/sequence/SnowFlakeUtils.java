package org.zxl.testdemo.sequence;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 雪花ID生成的是一个64位的二进制正整数，然后转换成10进制的数。64位二进制数由如下部分组成：
 *
 *
 * snowflake id生成规则
 * 1位标识符：始终是0，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0。
 * 41位时间戳：41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截 )得到的值，这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的。
 * 10位机器标识码：可以部署在1024个节点，如果机器分机房（IDC）部署，这10位可以由 5位机房ID + 5位机器ID 组成。
 * 12位序列：毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号
 *
 * 优点
 *
 * 简单高效，生成速度快。
 * 时间戳在高位，自增序列在低位，整个ID是趋势递增的，按照时间有序递增。
 * 灵活度高，可以根据业务需求，调整bit位的划分，满足不同的需求。
 *
 * 缺点
 *
 * 依赖机器的时钟，如果服务器时钟回拨，会导致重复ID生成。
 * 在分布式环境上，每个服务器的时钟不可能完全同步，有时会出现不是全局递增的情况。
 *
 */
public class SnowFlakeUtils {
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long twepoch = 1288834974657L;
    //Thu, 04 Nov 2010 01:42:54 GMT
    private long workerIdBits = 0L;
    //节点ID长度
    private long datacenterIdBits = 0L;
    //数据中心ID长度
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    //最大支持机器节点数0~31，一共32个
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    //最大支持数据中心节点数0~31，一共32个
    private long sequenceBits = 1L;
    //序列号12位
    private long workerIdShift = sequenceBits;
    //机器节点左移12位
    private long datacenterIdShift = sequenceBits + workerIdBits;
    //数据中心节点左移17位
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    //时间毫秒数左移22位
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    //最大为4095
    private long lastTimestamp = -1L;

    private static class SnowFlakeHolder {
        private static final SnowFlakeUtils instance = new SnowFlakeUtils();
    }

    public static SnowFlakeUtils get(){
        return SnowFlakeHolder.instance;
    }

    public SnowFlakeUtils() {
        this(0L, 0L);
    }

    public SnowFlakeUtils(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public String getContractNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
        Date now = new Date();
        String dateFormat = sdf.format(now);
        return dateFormat + nextId();
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        //获取当前毫秒数
        //如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & sequenceMask;
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
                //自旋等待到下一毫秒
            }
        } else {
            sequence = 0L;
            //如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
        }
        lastTimestamp = timestamp;
        // 最后按照规则拼出ID。
        // 000000000000000000000000000000000000000000  00000            00000       000000000000
        // time                                      datacenterId      workerId     sequence
        // return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
        //        | (workerId << workerIdShift) | sequence;

        long longStr= ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
        // System.out.println(longStr);
        return longStr;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        for(int i=0; i< 5; i++){
            System.out.println(SnowFlakeUtils.get().getContractNo());
        }
    }
}
