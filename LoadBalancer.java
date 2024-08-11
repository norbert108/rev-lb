import java.io.BufferedReader;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class LoadBalancer {
    private static void runLoadBalancer() {
        try {
            List<Worker> workers = new BufferedReader(new FileReader("workers.txt"))
                    .lines()
                    .map(Worker::new)
                    .toList();
            WorkerLoads workerLoads = new WorkerLoads(workers.size());
//            Scheduler scheduler = new RoundRobin(workers.size());
            Scheduler scheduler = new LowestLoad(workerLoads);

            ServerSocket lbSocket = new ServerSocket(12345);

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

    public static void main(String[] args) {
        runLoadBalancer();
    }
}
