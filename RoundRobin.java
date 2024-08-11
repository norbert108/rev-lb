public class RoundRobin implements Scheduler {
    private int numOfWorkers;
    private int currentWorker = 0;

    public RoundRobin(int numOfWorkers) {
        this.numOfWorkers = numOfWorkers;
    }

    @Override
    public synchronized int schedule() {
        currentWorker = (currentWorker+1) % numOfWorkers;
        return currentWorker;
    }
}
