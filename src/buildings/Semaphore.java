package buildings;

public class Semaphore {
    private int _permits;

    public Semaphore(int permits) {
        _permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {
        if (_permits > 0) {
            _permits--;
        }

        else {
            wait();
            _permits--;
        }
    }


    public synchronized void release() throws InterruptedException {
        _permits++;

        if (_permits > 0) {
            notify();
        }
    }

}
