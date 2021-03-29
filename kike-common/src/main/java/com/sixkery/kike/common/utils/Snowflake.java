package com.common.utils;

/**
 * 雪花算法生成 ID
 *
 * @author sixkery
 * @date 2020/5/30
 */
public class Snowflake {
    /**
     * 起始时间： 2020-01-02 00:00:00
     */
    private static final long BASIC_TIMESTAMP = 1577894400000L;

    private static final int MAX_WORK_ID = 31;

    private static final int MAX_MACHINE_ID = 31;

    /**
     * 数据中心标示0-31
     */
    private static int workId;

    /**
     * 机器标示0-31
     */
    private static int machineId;

    private static int sequence = 0;

    private static long lastTimestamp;

    public Snowflake(int workId, int machineId) {
        if (workId < 0 || workId > MAX_WORK_ID) {
            throw new IllegalArgumentException("The workId must between 0 and 31");
        }
        if (machineId < 0 || machineId > MAX_MACHINE_ID) {
            throw new IllegalArgumentException("The machineId must between 0 and 31");
        }
        Snowflake.workId = workId;
        Snowflake.machineId = machineId;
    }

    // ==============================Methods==========================================

    /**
     * 返回String类型
     *
     * @return string的ID
     */
    public static String nextAsString() {
        return String.valueOf(next());
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public static synchronized long next() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            int sequenceMask = 4095;
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - BASIC_TIMESTAMP) << 22) | (workId << 17) | (machineId << 12) | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected static long timeGen() {
        return System.currentTimeMillis();
    }


}
