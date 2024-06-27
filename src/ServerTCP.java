import Model.Account;
import Model.Packet;
import Model.RFile;
import Model.RequestAccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTCP extends Thread {
    private Socket socket;

    public ServerTCP(Socket socket) {
        this.socket = socket;
    }

    public boolean loginCheck(Account account) throws IOException {
        return Variables.accounts.stream().anyMatch(account1 -> account1.getName().equals(account.getName()) && account1.getPassword().equals(account.getPassword()));
    }

    public boolean registerCheck(Account account) throws IOException {
        return Variables.accounts.stream().noneMatch(account1 -> account1.getName().equals(account.getName()));
    }
    public ArrayList<String> view(Packet packet){
        Account account=(Account) packet.getObject();
        ArrayList<String> filesName=new ArrayList<>();
        for (Account account1:Variables.accounts){
            if (account1.getName().equals(account.getName())){
                account=account1;
                break;
            }
        }
        for (RFile file: account.getFiles()){
            filesName.add(file.file.getName());
        }
        return  filesName;
    }
    public void sendingRequest(Packet packet){
        RFile file=null;
        RequestAccess requestAccess=(RequestAccess) packet.getObject();
        for (RFile rFile:Variables.files){
            if (requestAccess.getFileName().equals(rFile.file.getName())){
                file=rFile;
            }
        }
        for (Account account:Variables.accounts){
            if (account.getFiles().contains(file)){
                account.getRequestAccesses().add(requestAccess);
            }
        }
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
                valid = loginCheck((Account) packet.getObject());
                outputStream.writeObject(valid);
            } else if (packet.getType().equals("register")){
                valid = registerCheck((Account) packet.getObject());
                if (valid) {
                    Variables.accounts.add((Account) packet.getObject());
                }
                outputStream.writeObject(valid);
            } else if (packet.getType().equals("view")) {
                outputStream.writeObject(view(packet));
            } else if (packet.getType().equals("requestAccess")) {
                sendingRequest(packet);
            }
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
