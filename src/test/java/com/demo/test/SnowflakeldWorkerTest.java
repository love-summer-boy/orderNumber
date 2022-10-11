package com.demo.test;

import com.demo.SpringbootDemoApp;
import com.demo.base.SnowflakeIdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.awt.geom.AreaOp;

import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDemoApp.class)
public class SnowflakeldWorkerTest {

    public SnowflakeIdWorker SnowflakeIdWorker;

    @Test
    public static void main(String[] args) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1);

        long start = System.currentTimeMillis();
        int count = 0;
        /*for (int i = 0; System.currentTimeMillis()-start<1000; i++,count=i) {
            idWorker.nextId();
        }
        long end = System.currentTimeMillis()-start;
        System.out.println(end);
        System.out.println(count);*/

        Long id = idWorker.nextId();
        System.out.println("args = " + id);

    }
}
