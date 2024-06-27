package Model;

import java.io.Serial;
import java.io.Serializable;

public class RequestAccess implements Serializable {
    @Serial
    final private static  long serialVersionUID = -3273031418871145319L;
    private Account account;
   private int id;

    public RequestAccess(Account account, int id) {
        this.account = account;
        this.id=id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
