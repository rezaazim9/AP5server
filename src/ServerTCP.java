import Model.Account;
import Model.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
            } else {
                valid = registerCheck((Account) packet.getObject());
                if (valid) {
                    Variables.accounts.add((Account) packet.getObject());
                }
            }
            outputStream.writeObject(valid);
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
