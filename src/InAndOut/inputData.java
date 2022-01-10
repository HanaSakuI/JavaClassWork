package InAndOut;

import java.io.*;
import java.util.ArrayList;

/**
 * 读取文件
 */
public class inputData {
    private static ArrayList<String> rank = new ArrayList<>();
    public static ArrayList getLevellist(String level) throws IOException {
        rank.clear();
        String pathname = "";
        switch (level){
            case "level1":pathname = "src\\Resource\\level1.txt";break;
            case "level2":pathname = "src\\Resource\\level2.txt";break;
            case "level3":pathname = "src\\Resource\\level3.txt";break;
            case "level4":pathname = "src\\Resource\\level4.txt";break;
            case "level5":pathname = "src\\Resource\\level5.txt";break;
            default: break;
        }
        System.out.println(pathname);
        File filename = new File(pathname); // 要读取以上路径的input。txt文件
       try {
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line = "";
            line = br.readLine();
            rank.add(line);
            while(line != null) {
                line = br.readLine(); // 一次读入一行数据
                rank.add(line);//获取一行行的数据
            }
        }catch (Exception e){
            System.out.println("文件空");
        }
        return rank;
    }
}
