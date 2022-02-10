package ee.ut.cs.ds.sample.threads;

public class Multithreading {

	public static void main(String[] args) {

//Block-1
/*
		//Alternative-1 for Multithreading using Thread
		MultithreadAlt1 job1 = new MultithreadAlt1();
		MultithreadAlt1 job2 = new MultithreadAlt1();
		
		//This is incorrect in the context of multi-threads. Code will be executed, but sequentially
		//job1.run();
		//job2.run();
		
		job1.start();
		job2.start();
*/		

//Block-2
/*
		//Larger number
		for (int i=0; i<5; i++) {
			MultithreadAlt1b jobN = new MultithreadAlt1b(i);
			jobN.start();
			
		}
*/		
		
		//Alternative-3 for Multithreading using Runnable
		for (int i=0; i<5; i++) {
			MultithreadAlt2 jobN = new MultithreadAlt2(i);
			Thread myThread = new Thread(jobN);
			myThread.start();
			
			//other functions
			try {
				myThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
