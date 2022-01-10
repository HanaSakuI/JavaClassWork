package InAndOut;

import GameCode.UserData;
import GamePage.GamePageView;
import GamePage.HomePageView;

import java.io.*;
import java.util.ArrayList;

/**
 * @author HanaSaku
 * 保存成绩
 * 1：判断选择的难度 分类储存
 * 2：判断用户名是否重复 若用户名重复，将最大值存入
 * 3：若用户名不重复，则直接存入！
 * 4:存入方式： 用户名 + “ ” + 成绩
 */
public class saveScore {
 private String level; //选择的等级
 private String userName;
 private String score; // 用户的成绩
    private static boolean flag; // 判断有没有用户名重复
    public saveScore(){}
    public saveScore(String level,String userName,String score){this.level = level;this.userName = userName;this.score = score;}
    public static void save(String level, String userName, String score) throws IOException {
        flag = false;
        String pathname = "";
        switch (level){
            case "1级":pathname = "src\\Resource\\level1.txt";break;
            case "2级":pathname = "src\\Resource\\level2.txt";break;
            case "3级":pathname = "src\\Resource\\level3.txt";break;
            case "4级":pathname = "src\\Resource\\level4.txt";break;
            case "5级":pathname = "src\\Resource\\level5.txt";break;
            default: break;
        }

        File filename = new File(pathname);

        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        StringBuffer bf = new StringBuffer();
        String line = "";
        while( ( line = br.readLine() ) != null) {
            String[] s = line.split(" ");
            if(s[0].equals(userName)){
                flag = true;
                if(Integer.parseInt(s[1]) < Integer.parseInt(score)){
                    line = line.replace(line,userName + " " + score);
                    bf.append(line +"\n");
                }else bf.append(line + "\n");
            }else bf.append(line+"\n");
        }
        br.close();
        FileOutputStream fos = new FileOutputStream(filename);
        PrintWriter pw = new PrintWriter(fos);
        pw.write(bf.toString().toCharArray());
        pw.flush();
        pw.close();

        // 如果不存在相同的用户名 则直接存入
      if(flag == false) {
         FileWriter fileWriter = new FileWriter(filename,true);
         String s = userName + " " + score ;
          PrintWriter printWriter = new PrintWriter(fileWriter);
          printWriter.println(s);
          printWriter.close();
          fileWriter.close();
      }
    }
}