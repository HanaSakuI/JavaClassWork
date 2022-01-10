package GameCode;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.*;

/**    计算表达式结果
 *  1： String expression;表达式输入
 *  2： String input； 表达式去除空格 等号之后的输入值
 *  3： double evaluation；  计算结果。
 *  3： 正则表达式，将input匹配 纳入到 exprEvaluation();获取计算结果
 *  4 ：将表达式和结果存入到 Map中。
 *      返回Map
 */
    public class Calculation {
    private static String expr ; // 输入的表达式
    private static String evaluation; //计算结果。最后转化为String
    private static String input; //表达式去除空格 等号 参与运算
    private static LinkedList<Integer> intStack = new LinkedList<>(); //数字栈
    private static LinkedList<Character> charStack = new LinkedList<Character>(); //符号栈
    private static Map<String,String> map = new TreeMap<>();
    public Calculation(){}
    public Calculation(String expression){this.expr  = expression;}
    public static void ExpEvalute(String expression){
         input = expression.replaceAll(" ","").replace("=",""); //移除所有的 空格等号
         evaluation =  String.format("%.2f", new Expression(input).getResult());
         if(Pattern.compile("\\.[1-9]{1}[0]").matcher(evaluation).find()){ //x.10
             evaluation = String.format("%.1f",Double.valueOf(evaluation));
         }else
         if(Pattern.compile("\\.[0][1-9]{1}").matcher(evaluation).find()){ //x.01
             evaluation = evaluation;
         }else
        if(Pattern.compile("\\.[0]{1,2}").matcher(evaluation).find()){ //x.0 x.00
            double d = Double.parseDouble(evaluation);
            int t = new Double(d).intValue();
              evaluation = String.valueOf(t);
         }
          System.out.println(evaluation);
          map.put(expression,evaluation);
    }

    public static void setMap(Map<String, String> map) {
        Calculation.map = map;
    }
    public static Map<String, String> getMap() {
        return map;
    }
    public static  String getEvaluation(){return evaluation;}

}
