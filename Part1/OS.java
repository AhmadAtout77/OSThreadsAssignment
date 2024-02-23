

public class OS {
	public static int sharedVariable = 0; // shared variable to be incremented by threads
	
	public static void main(String[] args) { 
		long start = System.nanoTime(); // start time of executing the code
		int N = 841 + 500; // N is the total number of threads to be created (11822841 = 841 + 500)
		Thread[] threads = new Thread[N]; // create an array to hold N threads
		
		// create N threads and start them
		for (int i = 0; i < N; i++) {
            threads[i] = new Thread(new MyRunnable());
            threads[i].start();
        }
		
		try {
			// wait for all threads to finish
			for (int i = 0; i < N; i++) {
	            threads[i].join();
	        }
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		// print the expected and actual values of the shared variable
	    System.out.println("////////////////////////////////////////////");
	    System.out.println("Expected value is N^2 = "+ 1341*1341+" , counter value = "+ sharedVariable);
	    System.out.println("////////////////////////////////////////////");
	    long duration = (System.nanoTime() - start)/1000000; // current time - start time to measure performence 
	    System.out.println("Performence time in ms: "+ duration);
	}
	
	// this class implements the Runnable interface, so it can be used as a task to be executed by threads
	static class MyRunnable implements Runnable {
	    public void run() {
	    	
	    	for(int i= 0; i < 841+500; i++) {
	    		// sleep for TID%10 nano seconds
		        System.out.println("I'm thread "+Thread.currentThread().getId()+" about to go to sleep for "+Thread.currentThread().getId()%10
		        		+" nanoseconds");
		        try {
					Thread.sleep((Thread.currentThread().getId()%10)/1000000);
				} catch (Exception E) {
					System.out.println(E.toString());
				}
		        
		        // increment the shared variable
		        System.out.println("I'm thread "+ Thread.currentThread().getId()+"; about to increment the counter, old value was "
		        		+ sharedVariable );
		        sharedVariable++;
		        System.out.println("I'm thread "+ Thread.currentThread().getId()+"; finished incrementing the counter, new value is "
		        		+ sharedVariable );
		    }
	    }
	}
}
