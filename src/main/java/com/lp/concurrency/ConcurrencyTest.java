package com.lp.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 代码测试并发
 */
public class ConcurrencyTest
{
    private static int clientTotal = 5000;
    private static int threadTotal = 200;
    private static int count = 0;

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
                    ConcurrencyTest.add();
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
        System.out.println("count: " + count);

    }

    private static void add(){
        count++;
    }
}
