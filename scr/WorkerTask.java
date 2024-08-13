package scr;

import java.net.Socket;

public class WorkerTask implements Runnable{
    private Socket clientSocket;
    private WorkerLoads workerLoads;
    private int serverId;

    public WorkerTask(Socket clientCocket, WorkerLoads workerLoads, int serverId) {
        this.clientSocket = clientCocket;
        this.workerLoads = workerLoads;
        this.serverId = serverId;
    }

    @Override
    public void run() {
        try {
            workerLoads.incrementLoad(serverId);
            System.out.println(clientSocket.getPort() + "  SLEEPING");
            Thread.sleep(2000);
            System.out.println(clientSocket.getPort() + "  AWAKE");
            workerLoads.decrementLoad(serverId);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
