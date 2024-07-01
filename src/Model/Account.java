package Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
    @Serial
    final private static long serialVersionUID = -3930272386469641391L;
    private String name;
    private String password;
    private JWT jwt;
    private ArrayList<RFile> files;
    private ArrayList<RequestAccess> requestAccesses;

    public Account() {
    }

    public ArrayList<RequestAccess> getRequestAccesses() {
        return requestAccesses;
    }

    public void setRequestAccesses(ArrayList<RequestAccess> requestAccesses) {
        this.requestAccesses = requestAccesses;
    }

    public Account(String name, String password, ArrayList<RFile> files, JWT jwt, ArrayList<RequestAccess> requestAccess) {
        this.name = name;
        this.files = files;
        this.password = password;
        this.jwt = jwt;
        this.requestAccesses = requestAccess;
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
