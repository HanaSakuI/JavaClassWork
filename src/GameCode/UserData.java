package GameCode;

public class UserData  implements GameSet{
     private static String level;
     private static String userName;
     public UserData(){ }
     public UserData(String userName,String level){UserData.userName = userName;UserData.level = level;}
     public static void setLevel(String level){
        UserData.level = level;
    }
     public static String getLevel() {
        return level;
    }
     public static void setUserName(String userName) {
          UserData.userName = userName;
    }
     public static String getUserName() {
        return userName;
    }
}
