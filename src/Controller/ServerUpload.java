package Controller;

import Model.Account;
import Model.AccountFile;
import Model.RFile;
import Model.Variables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.net.SocketException;
import java.util.ArrayList;

public class ServerUpload extends Thread {

    private AccountFile accountFile;
    private Account account;
    private DatagramSocket socket;

    public ServerUpload(AccountFile accountFile, Account account) throws SocketException {
        this.accountFile = accountFile;
        this.account = account;
        this.socket = new DatagramSocket(accountFile.getPort());
    }

    @Override
    public void run() {
        try {
            ArrayList<Account> accounts = new ArrayList<>();
            accounts.add(account);

            String filePath = Variables.dataBase + File.separator + accountFile.getAccount().getName() + File.separator + accountFile.getFileName();
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            try (OutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[1000];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                int expectedSequenceNumber = 0;

                while (true) {
                    socket.receive(packet);
                    if (packet.getLength() == 0) {
                        break;
                    }

                    outputStream.write(packet.getData(), 0, packet.getLength());
                    outputStream.flush();
                     String ack = "ACK" + expectedSequenceNumber;
                    DatagramPacket ackPacket = new DatagramPacket(ack.getBytes(), ack.length(), packet.getAddress(), packet.getPort());
                    socket.send(ackPacket);
                    expectedSequenceNumber++;
                }
            }

            socket.close();
            RFile rFile = new RFile(Variables.id++, file, accounts, new ArrayList<>());
            account.getFiles().add(rFile);
            Variables.files.add(rFile);
        } catch (IOException e) {
        }
    }
}
