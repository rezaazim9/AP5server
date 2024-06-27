package Model;

import java.io.Serial;
import java.io.Serializable;

public class RequestAccess implements Serializable {
    @Serial
    final private static  long serialVersionUID = -3273031418871145319L;
    private Account account;
    public String fileName;

    public RequestAccess(Account account, String fileName) {
        this.account = account;
        this.fileName = fileName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
