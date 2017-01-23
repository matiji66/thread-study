package chapter01;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.State;

public class Thread01_Calculator implements Runnable {

	private int number;

	public Thread01_Calculator(int number) {
		super();
		this.number = number;
	}

	public void run() {

		for (int i = 0; i < 10; i++) {
			System.out.printf("%s: %d * %d = %d \n", Thread.currentThread()
					.getName(), number, i, i * number);
		}
	}

	public static void main(String[] args) {
		// both array is OK.
		// Thread [] threads = new Thread[10] ;
		Thread threads[] = new Thread[10];
		Thread.State states[] = new Thread.State[10];

		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Thread01_Calculator(i));
			if (i % 2 == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread" + i);
		}

		try (FileWriter fileWriter = new FileWriter("log.txt");
				PrintWriter pWriter = new PrintWriter(fileWriter);) {

			for (int i = 0; i < 10; i++) {
				pWriter.println("Main : status of thread " + i + " : "
						+ threads[i].getState());
				states[i] = threads[i].getState();
			}

			for (int i = 0; i < 10; i++) {
				threads[i].start();
			}
			boolean finish = false;
			while (!finish) {
				for (int i = 0; i < 10; i++) {
					if (threads[i].getState() != states[i]) {
						writeThreadInfo(pWriter, threads[i], states[i]);
						states[i] = threads[i].getState();
					}
				}
				finish = true;
				for (int i = 0; i < 10; i++) {
					finish = finish
							&& (threads[i].getState() == State.TERMINATED);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeThreadInfo(PrintWriter pWriter, Thread thread,
			State state) {
		pWriter.printf("Main : Id %d - %s \n", thread.getId(), thread.getName());
		pWriter.printf("Main : Priority : %d \n", thread.getPriority());
		pWriter.printf("Main : OldState : %s \n", state);
		pWriter.printf("Main : New State: %s \n", thread.getState());
		pWriter.printf("Main : ***************************");

	}

}
