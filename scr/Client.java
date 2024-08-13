package scr;

import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            while (!Thread.interrupted()) {
                new Thread(() -> {
                    try {
                        Socket lbSocket = new Socket("localhost", 12345);
                        lbSocket.getOutputStream().write("AaaaaAAaAAA".getBytes());
                        lbSocket.getOutputStream().flush();
                        System.out.println("Written to socket");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();

                Thread.sleep(100);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
