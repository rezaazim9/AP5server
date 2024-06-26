

import java.io.File;
import java.util.ArrayList;

public class RFile {
    int id;
    public File file;
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
