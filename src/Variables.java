import Model.Account;
import Model.JWT;
import Model.RFile;

import java.io.File;
import java.util.ArrayList;

public class Variables {
    public static ArrayList<RFile> files = new ArrayList<>();
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static ArrayList<JWT> jwtList = new ArrayList<>();
    public static File request= new File("C:\\Users\\ostad\\IdeaProjects\\AP5\\Request.json");
   public  static  File response= new File("C:\\Users\\ostad\\IdeaProjects\\AP5\\Response.json");
}
