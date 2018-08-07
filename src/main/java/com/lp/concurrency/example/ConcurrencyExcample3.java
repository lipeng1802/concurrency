package com.lp.concurrency.example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 代码测试并发
 */
@Slf4j
public class ConcurrencyExcample3
{
    private static int clientTotal = 5000;
    private static int threadTotal = 200;

    private static AtomicBoolean isHappend = new AtomicBoolean(false);


    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService excutorService = Executors.newCachedThreadPool();
        final Semaphore semaphore =new Semaphore(threadTotal);
        final CountDownLatch countDownlatch = new CountDownLatch(clientTotal);

        for(int i=0 ; i<clientTotal ; i++){
            excutorService.execute(() -> {
                try
                {
                    semaphore.acquire();
                    ConcurrencyExcample3.add();
                    semaphore.release();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                countDownlatch.countDown();


            });
        }
        countDownlatch.await();
        excutorService.shutdown();
        log.info("isHappend: " + isHappend.get());

    }

    private static void add(){
        if(isHappend.compareAndSet(false,true)){
            log.info("execute");
        }
    }
}
