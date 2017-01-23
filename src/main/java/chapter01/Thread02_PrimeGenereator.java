package chapter01;

import com.pateo.thread.Log;

public class Thread02_PrimeGenereator extends Thread{

	@Override
	public void run() {
		super.run();
		long number = 1L ;
		while (true) {
			if (isPrime(number)) {
				System.out.printf("Number %d is Prime \n",number);
			}
			if (isInterrupted()) {
				System.out.printf("The Prime Generator has been interrupt \n");
				return ;
			}
			number ++ ;
		}
	}

	private boolean isPrime(long number) {
		 if (number <= 2) {
			return true;
		}
		 for (int i = 2; i < (number/2)+1; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Thread task = new Thread02_PrimeGenereator();
		Log.logInfo("task start");
		task.start();
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		task.interrupt();
		Log.logInfo("task end");
	}
}
