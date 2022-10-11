package com.demo.base;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MySnowFlake {
    private long lastTimestamp = -1L;
    private int sequence;
    private final int sequenceMask=999;
    public MySnowFlake(Integer sequence){
        if (sequence<0||sequence>sequenceMask){
            throw new IllegalArgumentException(
                    "sequence can't be greater than 999 or lesser than 0"
            );
        }
        this.sequence=sequence;

    }

    public synchronized String nextId() {
        String result="";
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            System.err.printf(
                    "clock is moving backwards. Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) % sequenceMask;

            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            //从0开始
            sequence = 0;
        }
        lastTimestamp=timestamp;
        Date date1 =new Date(lastTimestamp);
        SimpleDateFormat sd =new SimpleDateFormat("yyyyMMdd-HH-mm-ss-SSS");
        String format = sd.format(date1);
        result=format+"-"+sequence;
        return result;
    }

    private long timeGen(){
        return System.currentTimeMillis();
    }

    /**
     * 等待到下一毫秒
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {

        long timestamp = timeGen();

        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }



    public static void main(String[] args) {
        MySnowFlake mySnowFlake =new MySnowFlake(1);
        for (int i=0;i<40;i++){
            String s = mySnowFlake.nextId();
            System.out.println(s);
        }
    }

}
