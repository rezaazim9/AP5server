import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class ServerTCP extends Thread {
    private Socket socket;

    public ServerTCP(Socket socket) {
        this.socket = socket;
    }

    public boolean loginCheck() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Account account = objectMapper.readValue(Variables.request, Account.class);
        return Variables.accounts.stream().anyMatch(account1 -> account1.getName().equals(account.getName()) && account1.getPassword().equals(account.getPassword()));
    }

    public boolean registerCheck() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Account account = objectMapper.readValue(Variables.request, Account.class);
        return Variables.accounts.stream().noneMatch(account1 -> account1.getName().equals(account.getName()));
    }

    public void writerResponse(boolean valid) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(Variables.response, valid);
    }

    @Override
    public void run() {
        try {
            boolean valid;
            if (socket.getInputStream().read() == 0) {
                valid = loginCheck();
            } else {
                valid = registerCheck();
                if (valid) {
                    Variables.accounts.add(new ObjectMapper().readValue(Variables.request, Account.class));
                }
            }
            writerResponse(valid);
            String response = new ObjectMapper().writeValueAsString(valid);
            socket.getOutputStream().write(response.getBytes());
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
