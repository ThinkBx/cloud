package com.cloud.util;

/**
 * @author fjj
 * @date 2020/6/25 22:46
 * 64 bit的long型的数字作为全局唯一 id
 * 1、第1位是符号位 始终为0-因为生成的id都是正数
 * 2、后面是41位的时间戳 精确到毫秒级-41位的长度可以表示2^41-1个毫秒值 也就是说可以使用69年
 * 3、之后的10位是机器标识 前5bit是机房id 后5bit是机器id-10位的长度表明该服务最多可以部署在2^10台机器(即1024台机器)上
 * 4、最后12位是计数序列号-支持同一节点同一毫秒生成多个id 12位的计数序列号支持每个节点每毫秒产生2^12-1(即4096)个ID序号
 */
public class ToolSnowFlake {

    // 起始时间戳
    private final static long START_STMP = 1480166465631L;

    // 每部分的位数
    private final static long SEQUENCE_BIT = 12; // 序列号占用位数
    private final static long MACHINE_BIT = 5; // 机器id占用位数
    private final static long DATACENTER_BIT = 5; // 机房id占用位数

    // 每部分最大值
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    // 每部分向左的位移
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId; // 机房id
    private long machineId; // 机器id
    private long sequence = 0L; // 序列号
    private long lastStmp = -1L; // 上次的时间戳

    public ToolSnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    // 产生下一个ID
    public synchronized long getNextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.Refusing to generate id");
        }
        if (currStmp == lastStmp) {
            // 若在相同毫秒内 序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 同一毫秒的序列数已达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            // 若在不同毫秒内 则序列号置为0
            sequence = 0L;
        }
        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT // 时间戳部分
                | datacenterId << DATACENTER_LEFT // 机房id部分
                | machineId << MACHINE_LEFT // 机器id部分
                | sequence; // 序列号部分
    }

    // 获取新的毫秒数
    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    // 获取当前的毫秒数
    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
//        ToolSnowFlake snowFlake = new ToolSnowFlake(1,1);
//        long nextId = snowFlake.getNextId();
//        System.out.println(nextId);
        String machineId = System.getenv("machineId");

//        String machineId = System.getenv("JAVA_HOME");
        System.out.println(machineId);
    }
}
