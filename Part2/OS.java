

public class OS {
    public static int sharedVariable = 0;

    // Synchronized method to increment the shared variable
    public static synchronized void incrementCounter() {
        sharedVariable++;
    }

    public static void main(String[] args) {
		long start = System.nanoTime(); // start time of executing the code
        int N = 841 + 500; // set the number of threads to create my student ID = 11822841
        Thread[] threads = new Thread[N];

        // Create and start all the threads
        for (int i = 0; i < N; i++) {
            threads[i] = new Thread(new MyRunnable());
            threads[i].start();
        }

        try {
            // Wait for all the threads to finish
            for (int i = 0; i < N; i++) {
                threads[i].join();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // Print the final counter value and expected value
        System.out.println("////////////////////////////////////////////");
        System.out.println("Expected value is N^2 = " + 1341 * 1341 + " , counter value = " + sharedVariable);
        System.out.println("////////////////////////////////////////////");
		 
	    long duration = (System.nanoTime() - start)/1000000; // current time - start time to measure performence 
	    System.out.println("Performence time in ms: "+ duration);
    }

    // Inner class implementing the Runnable interface
    static class MyRunnable implements Runnable {
        public void run() {
            // Loop 1341 times and increment the shared variable
            for (int i = 0; i < 841 + 500; i++) {
                // sleep for TID%10 nanoseconds
                System.out.println("I'm thread " + Thread.currentThread().getId() + " about to go to sleep for "
                        + Thread.currentThread().getId() % 10 + " nanoseconds");
                try {
                    Thread.sleep((Thread.currentThread().getId() % 10) / 1000000);
                } catch (Exception E) {
                    System.out.println(E.toString());
                }

                // Use the synchronized method to increment the shared variable
                System.out.println("I'm thread " + Thread.currentThread().getId()
                        + "; about to increment the counter, old value was " + sharedVariable);
                incrementCounter();
                System.out.println("I'm thread " + Thread.currentThread().getId()
                        + "; finished incrementing the counter, new value is " + sharedVariable);
            }
        }
    }

}
