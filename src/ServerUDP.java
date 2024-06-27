import java.net.Socket;

public class ServerUDP extends Thread{
    private Socket socket;
    public ServerUDP(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {

    }
}
