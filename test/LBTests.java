package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import scr.LoadBalancer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class LBTests {

    ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Test
    public void tes1() throws Exception {
        LoadBalancer lb = new LoadBalancer();
        CountDownLatch latch = new CountDownLatch(5);

        ServerSocket spysocket = Mockito.spy(new ServerSocket(12345));

        executorService.submit(() -> lb.runLoadBalancer(spysocket));
        Thread.sleep(300);

        Socket clientScoket = new Socket("localhost", 12345);
        Socket clientScoket1 = new Socket("localhost", 12345);
        Socket clientScoket2 = new Socket("localhost", 12345);
        Socket clientScoket3 = new Socket("localhost", 12345);
        Socket clientScoket4 = new Socket("localhost", 12345);



        Mockito.verify(spysocket, Mockito.times(4)).accept();
    }

}
