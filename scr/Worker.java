package scr;

public class Worker {
    private String host;
    private Integer port;

    public Worker(String configLine) {
        String[] conf = configLine.split(";");
        this.host = conf[0];
        this.port = Integer.valueOf(conf[1]);
    }
}
