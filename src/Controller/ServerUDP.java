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
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class ServerUDP extends Thread {

    private AccountFile accountFile;
    private Account account;
    private DatagramSocket socket;

    public ServerUDP(AccountFile accountFile, Account account) throws SocketException {
        this.accountFile = accountFile;
        this.account = account;
    }

    @Override
    public void run() {
        try {
            ArrayList<Account> accounts = new ArrayList<>();
            accounts.add(account);
            File file = new File(Variables.dataBase + "\\" + accountFile.getAccount().getName() + "\\" + accountFile.getFileName());
            OutputStream outputStream = new FileOutputStream(file);
            socket = new DatagramSocket(accountFile.getPort());
            byte[] buffer = new byte[1000];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), accountFile.getPort());
            System.out.println(packet.getAddress() + " " + packet.getPort() + " " + packet.getLength() + " " + packet.getData().length);
            System.out.println(socket.getPort());
            System.out.println("Upload Loop1");
            System.out.println(packet.getAddress() + " " + packet.getPort() + " " + packet.getLength() + " " + packet.getData().length);
            socket.receive(packet);
            System.out.println("Upload Loop2");
            outputStream.write(packet.getData());
            outputStream.flush();
            System.out.println("Upload Loop3");
            System.out.println("Upload Loop4");
            outputStream.close();
            System.out.println("Upload Done");
            socket.close();
            RFile rFile = new RFile(Variables.id++, file, accounts, new ArrayList<>());
            account.getFiles().add(rFile);
            Variables.files.add(rFile);
        } catch (IOException e) {

        }
    }
}
