package GameCode;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Map;
import java.util.TreeMap;

/**游戏 主要代码类
 * 游戏分为五个等级，由开始界面选择调用。
 *
 */
public class LevelSet{
    private static double downSpeed = 0.01; //下落速度
    private static int score = 1; //答对加分的数值
    private static String expression; //表达式
    private static int k,l,m,n; // k l m n 代表四个数值
    private static String symbol = ""; //符号
    private static String evaluation; //表达式的结果
    private static Map<String,String> map = new TreeMap<>(); //表达式和 结果栈
    public LevelSet(){setDownSpeed(0.15 + Math.random() * 0.35 );}
    public LevelSet(String Level){
        setDownSpeed(0.15 + Math.random() * 0.35);
        switch (Integer.parseInt(Level.replace("级",""))){
            case 1: setScore(1);break;
            case 2: setScore(2);break;
            case 3: setScore(3);break;
            case 4: setScore(4);break;
            case 5: setScore(5);break;
            default: setScore(1);break;
        }
        SelectLevel(Level);

    }
    public static void Level1(){
       k = (int)(Math.random() * 11);
       l = (int)(Math.random() * 11);
       switch ((int)(Math.random() * 2)+1){
           case 1: symbol = "+"; break;
           case 2: symbol = "-"; break;
           default:break;
       }
        setExpression(k + " " + symbol + " " + l + " = ");
    }
    public static void Level2(){
        k = (int)(Math.random() * 100);
        l = (int)(Math.random() * 99) + 1;
        while(k + l > 99){
            k = (int)(Math.random() * 100);
            l = (int)(Math.random() * 99) + 1;
        }
        switch ((int)(Math.random() * 2)+1){
            case 1: symbol = "*"; break;
            case 2: symbol = "/"; break;
            default:break;
        }
        setExpression(k + " " + symbol + " " + l + " = ");
    }
    public static void Level3(){
        k = (int)(Math.random() * 100);
        l = (int)(Math.random() * 99) + 1;
        m = (int)(Math.random() * 99) + 1;

        switch ((int)(Math.random() * 4)+1){
            case 1: symbol = "+"; break;
            case 2: symbol = "-"; break;
            case 3: symbol = "*"; break;
            case 4: symbol = "/"; break;
            default:break;
        }

        double r = Math.random();
        if(r > 0.78){
            String symbol1 = "";
            while(true){
                switch ((int)(Math.random() * 4)+1){
                    case 1: symbol1 = "+"; break;
                    case 2: symbol1 = "-"; break;
                    case 3: symbol1 = "*"; break;
                    case 4: symbol1 = "/"; break;
                    default:break;
                }
                if(symbol.equals(symbol1)  && symbol.equals("/")){
                    continue;
                }else{ break;}
            }
            setExpression(k + " " + symbol + " " + l + " " + symbol1 + " " + m + " = ");
        }else{
            setExpression(k + " " + symbol + " " + l + " = ");
        }
    }
    public static void Level4(){
        k = (int)(Math.random() * 100);
        l = (int)(Math.random() * 99) + 1;
        m = (int)(Math.random() * 99) + 1;
        n = (int)(Math.random() * 99) + 1;
        switch ((int)(Math.random() * 4)+1){
            case 1: symbol = "+"; break;
            case 2: symbol = "-"; break;
            case 3: symbol = "*"; break;
            case 4: symbol = "/"; break;
            default:break;
        }
        double r = Math.random();
        if(r > 0.70 && r < 0.90){
            String symbol1 = "";
            while(true){
                switch ((int)(Math.random() * 4)+1){
                    case 1: symbol1 = "+"; break;
                    case 2: symbol1 = "-"; break;
                    case 3: symbol1 = "*"; break;
                    case 4: symbol1 = "/"; break;
                    default:break;
                }
                if(symbol.equals(symbol1)  && symbol.equals("/")){
                    continue;
                }else break;
            }
            setExpression(k + " " + symbol + " " + l + " " + symbol1 + " " + m + " = ");

        }else if( r >= 0.90){
            String symbol1 = "";
            String symbol2 = "";
            while(true){
                switch ((int)(Math.random() * 4)+1){
                    case 1: symbol1 = "+"; break;
                    case 2: symbol1 = "-"; break;
                    case 3: symbol1 = "*"; break;
                    case 4: symbol1 = "/"; break;
                    default:break;
                }
                if(symbol.equals(symbol1)  && symbol.equals("/")){
                    continue;
                }else{ break;}
            }
            while(true){
                switch ((int)(Math.random() * 4)+1){
                    case 1: symbol2 = "+"; break;
                    case 2: symbol2 = "-"; break;
                    case 3: symbol2 = "*"; break;
                    case 4: symbol2 = "/"; break;
                    default:break;
                }
                 break;
            }
            setExpression(k + " " + symbol + " " + l + " " + symbol1 + " " + m + " " + symbol2 + " " + n + " = ");
        }else{
            setExpression(k + " " + symbol + " " + l +   " = ");
        }

    }
    public static void Level5(){
        k = (int)(Math.random() * 100);
        l = (int)(Math.random() * 99) + 1;
        m = (int)(Math.random() * 99) + 1;
        n = (int)(Math.random() * 99) + 1;
        String symbol1 = "";
        switch ((int)(Math.random() * 4)+1){
            case 1: symbol = "+"; break;
            case 2: symbol = "-"; break;
            case 3: symbol = "*"; break;
            case 4: symbol = "/"; break;
            default:break;
        }
        while(true){
            switch ((int)(Math.random() * 4)+1){
                case 1: symbol1 = "+"; break;
                case 2: symbol1 = "-"; break;
                case 3: symbol1 = "*"; break;
                case 4: symbol1 = "/"; break;
                default:break;
            }
            if(symbol.equals(symbol1)  && symbol.equals("/")){
                continue;
            }else break;
        }
        double r = Math.random();
        if( r <= 0.5){
            setExpression(k + " " + symbol + " " + l +   " = ");
        }else if(r > 0.5 && r < 0.8){
            setExpression(k + " " + symbol + " " + l + " " + symbol1 + " " + m + " = ");
        }else if( r >= 0.8){
            String symbol2 = "";
            while(true){
                switch ((int)(Math.random() * 4)+1){
                    case 1: symbol2 = "+"; break;
                    case 2: symbol2 = "-"; break;
                    case 3: symbol2 = "*"; break;
                    case 4: symbol2 = "/"; break;
                    default:break;
                }
                break;
            }
            setExpression(k + " " + symbol + " ( " + l + " " + symbol1 + " " + m + " " + symbol2 + " " + n + " )" + " = " );
        }
    }
    public static void SelectLevel(String level){
        System.out.println("SelectLevel ："+ level);
        switch (Integer.parseInt(level.replace("级",""))){
            case 1: Level1(); System.out.println("表达式：" + getExpression());break;
            case 2: Level2(); System.out.println("表达式：" +getExpression());break;
            case 3: Level3(); System.out.println("表达式：" +getExpression());break;
            case 4: Level4(); System.out.println("表达式：" +getExpression());break;
            case 5: Level5(); System.out.println("表达式：" +getExpression());break;
            default:System.out.println("空等级");break;
        }
        System.out.println("下落速度： "+downSpeed);
    }
    public static void setExpression(String expression){ LevelSet.expression = expression;}
    public static String getExpression() { return expression; }
    public static Double getDownSpeed() { return downSpeed; }
    public static void setDownSpeed(double downSpeed) { LevelSet.downSpeed = downSpeed; }
    public static int getScore() {
        return score;
    }
    public static void setScore(int score) {
        LevelSet.score = score;
    }
}
