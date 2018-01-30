package com.infotech.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.infotech.model.Counter;

public class ClientTest {

	public static void main(String[] args) {
		ExecutorService executorService = null;
		try {
			executorService = Executors.newFixedThreadPool(3);

			final Counter counter = new Counter();
			Runnable task1 = new Runnable() {

				public void run() {
					System.out.println(Thread.currentThread().getName()+" writting on count variable..");
					for (int i = 1; i <= 50000; i++) {
						counter.increment();
					}
				}
			};

			Runnable task2 = new Runnable() {

				public void run() {
					System.out.println(Thread.currentThread().getName()+" getiing value of count variable..");
					int finalCount = counter.getCount();
					System.out.println("Final Count:"+finalCount);
				}
			};
			
			
			Runnable task3 = new Runnable() {

				public void run() {
					System.out.println(Thread.currentThread().getName()+" getiing value of count variable..");
					int finalCount = counter.getCount();
					System.out.println("Final Count:"+finalCount);
				}
			};
			executorService.submit(task1);
			
			executorService.awaitTermination(3, TimeUnit.SECONDS);
			
			executorService.submit(task2);
			executorService.submit(task3);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (executorService != null)
				executorService.shutdown();
		}
	}

}
