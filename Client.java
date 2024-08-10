import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            while (!Thread.interrupted()) {
                Socket lbSocket = new Socket("localhost", 12345);
                lbSocket.getOutputStream().write("AaaaaAAaAAA".getBytes());
                lbSocket.getOutputStream().flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
