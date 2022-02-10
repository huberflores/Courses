package ee.ut.cs.ds.sample.threads;

//1. Alternative 1, extend your class to "Thread"
public class MultithreadAlt1 extends Thread {
	
	//2. Override the run method
	@Override
	public void run() {
		//3. Now run your code here
		
		for (int i=1; i<=5; i++) {
			System.out.println(i);
			
			//sleep function has been around long - at some point it was meant to be removed.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
