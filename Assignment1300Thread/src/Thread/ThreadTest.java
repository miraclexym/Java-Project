package Thread;

public class ThreadTest extends Thread{
	
    public static ThreadTest primeThread0 = new ThreadTest(0, 0, 1, 1000);
    public static ThreadTest primeThread1 = new ThreadTest(1, 0, 1001, 2000);
    public static ThreadTest primeThread2 = new ThreadTest(2, 0, 2001, 3000);
    
    private int threadId;
    private int threadCount;
    private int startRange;
    private int endRange;
    
    public ThreadTest(int threadId, int threadCount, int startRange,
			int endRange) {
		super();
		this.threadId = threadId;
		this.threadCount = threadCount;
		this.startRange = startRange;
		this.endRange = endRange;
	}

	@Override
    public void run() {
        System.out.println("线程-" + threadId + "开始查找素数:");

        for (int i = startRange; i <= endRange; i++, threadCount++) {
        	
        	boolean shouldBeRun = false;
        	if(threadCount <= primeThread0.threadCount && threadCount <= primeThread1.threadCount && threadCount <= primeThread2.threadCount)
        		shouldBeRun = true;
        	
            if (isPrime(i)) {
            	if(shouldBeRun)
                    System.out.println("线程-" + threadId + "：" + i);
            	else{
            		i--;
            		threadCount--;
            	}
            }
        }

        System.out.println("线程-" + threadId + "结束查找素数.");
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
    
	public static void main(String[] args) {
		primeThread0.start();
		primeThread1.start();
		primeThread2.start();
    }
}