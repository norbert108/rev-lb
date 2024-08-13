package scr;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class LoadBalancer {
    private List<Worker> workers;
    private WorkerLoads workerLoads;
    private Scheduler scheduler;
    private ServerSocket lbSocket;

    public LoadBalancer(List<Worker> workers, WorkerLoads workerLoads, Scheduler scheduler){
        this.workers = workers;
        this.workerLoads = workerLoads;
        this.scheduler = scheduler;
    }

    public LoadBalancer() {
        this.workers = getWorkersFromFile();
        this.workerLoads = new WorkerLoads(workers.size());;
        this.scheduler =  new LowestLoad(workerLoads);;
    }

    public void runLoadBalancer(ServerSocket ssock) {
        try {
            lbSocket = ssock;

            while (!Thread.interrupted()) {
                Socket clientSocket = lbSocket.accept();
                int workerId = scheduler.schedule();
                new Thread(new WorkerTask(clientSocket, workerLoads, workerId)).start();
                System.out.println("TASK SCHEDULED: " + workerId);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Worker> getWorkersFromFile() {
        List<Worker> workerList = null;

        try {
            workerList = new BufferedReader(new FileReader("scr/workers.txt"))
                    .lines()
                    .map(Worker::new)
                    .toList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return workerList;
    }
}
