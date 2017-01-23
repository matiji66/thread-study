package chapter01;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Thread03_FileSearch implements Runnable {

	private String initPath = null;
	private String fileName = null;

	public Thread03_FileSearch(String initPath, String fileName) {
		super();
		this.initPath = initPath;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		File file = new File(initPath);
		if (file.isDirectory()) {
			try {
				directoryProcess(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				fileProcess(file);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void directoryProcess(File file) throws InterruptedException {
		File list[] = file.listFiles();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].isDirectory()) {
					directoryProcess(list[i]);
				} else {
					fileProcess(list[i]);
				}
			}
		}
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}

	private void fileProcess(File file) throws InterruptedException {
		if (file.getName().equals(fileName)) {
			System.out.println( Thread.currentThread().getName() +" : " + file.getAbsolutePath());
		}
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		Thread03_FileSearch searcher = new Thread03_FileSearch("D:\\workspace", "log.txt");
		Thread thread = new Thread(searcher);
		thread.start();
		
		try {
			TimeUnit.MILLISECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		thread.interrupted();
	}

}
