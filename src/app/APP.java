package app;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import abspak.SysoutCallable;
import tasks.TaskOne;

public class APP {
	
	public static void main(String[] args) {
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(3);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 2L,TimeUnit.SECONDS, queue, new ThreadPoolExecutor.CallerRunsPolicy());
		SysoutCallable callable = new SysoutCallable();
		
		for(int i = 0; i < 10; i  ++){
			try {
				pool.execute(new TaskOne(i, callable)
						.enableTimeLimit()
						.setTimeLimit(2000L)
						.setInterval(1000L));
				Thread.sleep(10L);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		pool.execute(new TaskOne(10, callable)
				.enableExeLimit()
				.setExeLimit(3)
				.setInterval(500L));
		
		while(true){
			try {
				System.out.println("poolSize : "+pool.getPoolSize() +" active : "+pool.getActiveCount()+" queueSize : " + pool.getQueue().size());
				Thread.sleep(200L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
