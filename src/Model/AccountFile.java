package Model;

import java.io.Serial;
import java.io.Serializable;

public class AccountFile implements Serializable {
    @Serial
    final private static long serialVersionUID = 799875924863443431L;
    private String fileName;
    private Account account;
    private int port;

    public AccountFile(String fileAddress, Account account, int port) {
        this.fileName = fileAddress;
        this.account = account;
        this.port = port;
    }

    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
