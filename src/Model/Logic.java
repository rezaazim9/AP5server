package Model;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;

public class Logic {
    public static boolean loginCheck(Account account) throws IOException {
        return Variables.accounts.stream().anyMatch(account1 -> account1.getName().equals(account.getName()) && account1.getPassword().equals(account.getPassword()));
    }

    public static boolean registerCheck(Account account) throws IOException {
        return Variables.accounts.stream().noneMatch(account1 -> account1.getName().equals(account.getName()));
    }

    public static ArrayList<String> view(Packet packet) {
        Account account = (Account) packet.getObject();
        ArrayList<String> filesName = new ArrayList<>();
        for (Account account1 : Variables.accounts) {
            if (account1.getName().equals(account.getName())) {
                account = account1;
                break;
            }
        }
        for (RFile file : account.getFiles()) {
            filesName.add(file.getFile().getName());
        }
        return filesName;
    }

    public static void requestAccess(Packet packet) {
        RFile file = new RFile(-1, null, new ArrayList<>(), new ArrayList<>());
        RequestAccess requestAccess = (RequestAccess) packet.getObject();
        for (RFile rFile : Variables.files) {
            if (requestAccess.getId() == rFile.getId()) {
                file = rFile;
            }
        }
        for (Account account : Variables.accounts) {
            if (account.getName().equals(requestAccess.getAccount().getName())) {
                file.getRequests().add(account);
                requestAccess.getAccount().getRequestAccesses().add(requestAccess);
            }
        }
    }

    public static boolean viewRequestAccess(Packet packet) {
        Account account = new Account();
        RequestAccess requestAccess = (RequestAccess) packet.getObject();
        for (Account account1 : Variables.accounts) {
            if (account1.getName().equals(requestAccess.getAccount().getName())) {
                account = account1;
                break;
            }
        }
        for (RFile file : account.getFiles()) {
            if (file.getId()==requestAccess.getId()) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> viewRequests(Packet packet) {
        RFile file = new RFile(-1, null, null, null);
        for (RFile file1 : Variables.files) {
            if (file1.getId() == (int) packet.getObject()) {
                file = file1;
                break;
            }
        }
        ArrayList<String> requests = new ArrayList<>();
        for (Account account : file.getRequests()) {
            requests.add(account.getName());
        }
        return requests;
    }
    public static ArrayList<String> viewAccess(Packet packet){
        RFile file = new RFile(-1, null, null, null);
        for (RFile file1 : Variables.files) {
            if (file1.getId() == (int) packet.getObject()) {
                file = file1;
                break;
            }
        }
        ArrayList<String> access = new ArrayList<>();
        for (Account account : file.getAccounts()) {
            access.add(account.getName());
        }
        return access;
    }

    public static void add(Packet packet) {
        RequestAccess requestAccess = (RequestAccess) packet.getObject();
        Account account = requestAccess.getAccount();
        int id = requestAccess.getId();
        for (Account account1 : Variables.accounts) {
            if (account.getName().equals(account1.getName())) {
                account = account1;
                break;
            }
        }
        for (RFile file : Variables.files) {
            if (file.getId() == id) {
                file.getRequests().remove(account);
                file.getAccounts().add(account);
                account.getFiles().add(file);
                break;
            }
        }
    }

    public static void upload(Packet packet) {
        AccountFile accountFile = (AccountFile) packet.getObject();
        Account account =new Account();
        for (Account account1:Variables.accounts){
            if (account1.getName().equals(accountFile.getAccount().getName())){
                account=account1;
                break;
            }
        }
        File file = new File(accountFile.getFileAddress());
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(account);
        if (JOptionPane.showConfirmDialog(new Frame(), account.getJwt(), "Access", JOptionPane.YES_NO_OPTION) == 0) {
            RFile rFile = new RFile(Variables.id++, file, accounts, new ArrayList<>());
            account.getFiles().add(rFile);
            Variables.files.add(rFile);
            /////////////////////////////////////////////
        }
    }

    public static void download(Packet packet) {
        AccountFile accountFile = (AccountFile) packet.getObject();
        Account account = accountFile.getAccount();
        if (JOptionPane.showConfirmDialog(new Frame(), account.getJwt(), "Access", JOptionPane.YES_NO_OPTION) == 0) {
            /////////////////////////////////////////////////////////////
        }
    }

    public static void remove(Packet packet) {
        RequestAccess requestAccess = (RequestAccess) packet.getObject();
        Account account = requestAccess.getAccount();
        int id = requestAccess.getId();
        for (Account account1 : Variables.accounts) {
            if (account.getName().equals(account1.getName())) {
                account = account1;
                break;
            }
        }
        for (RFile file : Variables.files) {
            if (file.getId() == id) {
                file.getRequests().remove(account);
                break;
            }
        }
    }
}
