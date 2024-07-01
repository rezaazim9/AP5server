package Controller;

import Model.Account;
import Model.AccountFile;
import Model.Variables;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerDownload extends Thread {

    private AccountFile accountFile;
    private Account account;
    private DatagramSocket socket;
    private static final int MAX_RETRIES = 3;

    public ServerDownload(AccountFile accountFile, Account account) throws SocketException {
        this.accountFile = accountFile;
        this.account = account;
        this.socket = new DatagramSocket();
    }

    @Override
    public void run() {
        FileInputStream fileInputStream = null;
        try {
            File file = new File(Variables.dataBase + File.separator + accountFile.getAccount().getName() + File.separator + accountFile.getFileName());
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[1000];
            int bytesRead;
            int sequenceNumber = 0;
            InetAddress clientAddress = InetAddress.getByName("localhost");
            int clientPort = accountFile.getPort();
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                DatagramPacket packet = new DatagramPacket(buffer, bytesRead, clientAddress, clientPort);
                boolean sent = false;
                int retries = 0;
                while (!sent && retries < MAX_RETRIES) {
                    socket.send(packet);

                    socket.setSoTimeout(1000);
                    byte[] ackBuffer = new byte[10];
                    DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length);
                    try {
                        socket.receive(ackPacket);
                        String ack = new String(ackPacket.getData(), 0, ackPacket.getLength());
                        if (ack.equals("ACK" + sequenceNumber)) {
                            sent = true;
                        }
                    } catch (IOException e) {
                        retries++;
                    }
                }
                if (!sent) {
                    break;
                }
                sequenceNumber++;
            }
            DatagramPacket endPacket = new DatagramPacket(new byte[0], 0, clientAddress, clientPort);
            socket.send(endPacket);

        } catch (IOException e) {
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
