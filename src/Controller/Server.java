package Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1111);
            while (true) {
                Socket socket = serverSocket.accept();
                new ServerTCP(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}