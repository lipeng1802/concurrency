package com.lp.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExcample2
{
    //代码段
    public void test1(){
        synchronized (SynchronizedExcample2.class){
            for (int i =0;i<10;i++){
                log.info("test1 - {}", i);
            }
        }
    }

    public  synchronized void test2(){
        for (int i =0;i<10;i++){
            log.info("test2 - {}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExcample2 e1 = new SynchronizedExcample2();
        SynchronizedExcample2 e2 = new SynchronizedExcample2();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() ->{
            e1.test1();
        });
        executorService.execute(() ->{
            e2.test2();
        });
    }

}
