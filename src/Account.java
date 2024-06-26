import java.util.ArrayList;

public class Account {

    private String name;
    private String password;
    private JWT jwt;
    private ArrayList<RFile> files;
    public Account() {
    }
    public Account(String name, String password, ArrayList<RFile> files, JWT jwt) {
        this.name = name;
        this.files = files;
        this.password = password;
        this.jwt = jwt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }

    public ArrayList<RFile> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<RFile> files) {
        this.files = files;
    }
}
