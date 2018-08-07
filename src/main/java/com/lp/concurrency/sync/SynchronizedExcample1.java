package com.lp.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExcample1
{
    //代码段
    public void test1(){
        synchronized (this){
            for (int i =0;i<10;i++){
                log.info("test1 - {}", i);
            }
        }
    }

    public  void test2(){
        for (int i =0;i<10;i++){
            log.info("test2 - {}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExcample1 e1 =new SynchronizedExcample1();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() ->{
            e1.test1();
        });
        executorService.execute(() ->{
            e1.test2();
        });
    }

}
