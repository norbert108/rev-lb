import java.util.ArrayList;
import java.util.List;

public class WorkerLoads {
    private List<Integer> workerLoads = new ArrayList<>();

    public WorkerLoads(int numWorkers) {
        for (int i = 0; i < numWorkers; i++) {
            workerLoads.add(0);
        }
    }

    public int getLoad(int workerId) {
        return workerLoads.get(workerId);
    }

    public synchronized void incrementLoad(int workerId) {
        workerLoads.set(workerId, workerLoads.get(workerId) + 1);
    }

    public synchronized void decrementLoad(int workerId) {
        workerLoads.set(workerId, workerLoads.get(workerId) - 1);
    }
}
