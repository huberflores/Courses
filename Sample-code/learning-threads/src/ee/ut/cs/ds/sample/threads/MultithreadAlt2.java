package ee.ut.cs.ds.sample.threads;


//1. Alternative 1, extend your class to "Runnable"
public class MultithreadAlt2 implements Runnable {
	
	private int threadNumber = 0;
	public MultithreadAlt2(int id) {
		this.threadNumber = id;
	}
	
	
	//2. Override the run method
	@Override
	public void run() {
		//3. Now run your code here
		
		for (int i=1; i<=5; i++) {
			System.out.println(i + " from thread " + threadNumber);
			
			//4. Test a crash thread does not impact others
			/*if (threadNumber==3) {
				throw new RuntimeException();
			}*/
			
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

