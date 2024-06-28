package Model;

import java.io.Serial;
import java.io.Serializable;

public class AccountFile implements Serializable {
    @Serial
    final private static long serialVersionUID = 799875924863443431L;
    private String fileAddress;
    private Account account;

    public AccountFile(String fileAddress, Account account) {
        this.fileAddress = fileAddress;
        this.account = account;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
