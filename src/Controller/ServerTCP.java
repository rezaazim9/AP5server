package Controller;

import Model.Account;
import Model.Logic;
import Model.Packet;
import Model.Variables;


import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerTCP extends Thread {
    private Socket socket;

    public ServerTCP(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            boolean valid;
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            Object o = inputStream.readObject();
            Packet packet = (Packet) o;
            if (packet.getType().equals("login")) {
                valid = Logic.loginCheck((Account) packet.getObject());
                outputStream.writeObject(valid);
            } else if (packet.getType().equals("register")) {
                valid = Logic.registerCheck((Account) packet.getObject());
                if (valid) {
                    Variables.accounts.add((Account) packet.getObject());
                    File file = new File("C:\\Users\\ostad\\IdeaProjects\\AP5\\dataBase\\" + ((Account) packet.getObject()).getName());
                    if (!file.exists()) {
                        file.mkdir();
                    }
                }
                outputStream.writeObject(valid);
            } else if (packet.getType().equals("view")) {
                outputStream.writeObject(Logic.view(packet));
            } else if (packet.getType().equals("requestAccess")) {
                Logic.requestAccess(packet);
            } else if (packet.getType().equals("viewRequestAccess")) {
                outputStream.writeObject(Logic.viewRequestAccess(packet));
            } else if (packet.getType().equals("viewRequests")) {
                outputStream.writeObject(Logic.viewRequests(packet));
            } else if (packet.getType().equals("viewAccess")) {
                outputStream.writeObject(Logic.viewAccess(packet));
            } else if (packet.getType().equals("add")) {
                Logic.add(packet);
            } else if (packet.getType().equals("remove")) {
                Logic.remove(packet);
            } else if (packet.getType().equals("JWTDownload")) {
                Logic.download(packet);
            } else if (packet.getType().equals("JWTUpload")) {
                Logic.upload(packet);
            }
            socket.getOutputStream().flush();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
