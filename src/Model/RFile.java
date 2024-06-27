package Model;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class RFile implements Serializable {
    @Serial
   final private static long serialVersionUID = -8701095066761201967L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    private int id;
    private File file;
    private ArrayList<Account> accounts;
    private ArrayList<Account> requests;

    public RFile(int id, File file, ArrayList<Account> accounts, ArrayList<Account> requests) {
        this.id = id;
        this.file = file;
        this.accounts = accounts;
        this.requests = requests;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<Account> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Account> requests) {
        this.requests = requests;
    }

    public void addRequest(Account account) {
        requests.add(account);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeRequest(Account account) {
        requests.remove(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }
}
