import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP extends Thread{
    private DatagramSocket socket;
    public ServerUDP(DatagramSocket socket){
        this.socket=socket;
    }
    @Override
    public void run() {

    }
}
