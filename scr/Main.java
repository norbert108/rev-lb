package scr;

import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        try {
            new LoadBalancer().runLoadBalancer(new ServerSocket(12345));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
