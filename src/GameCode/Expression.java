package GameCode;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Expression {
    private String expo;
    private String exp;
    private Stack<String> stack1 = new Stack<String>();
    private Stack<Double> stack2 = new Stack<Double>();

    public Expression() {

    }
    public Expression(String e) {
        this.expo = e;
        this.exp = e.replaceAll(" ","").replace("=","");
    }

    public String getExpression() {
        return this.exp;
    }

    public int precdence(String o) {
        if(o.equals("+")) {
            return 1;
        }
        else if(o.equals("-")) {
            return 2;
        }
        else if(o.equals("*")) {
            return 3;
        }
        else if(o.equals("/")) {
            return 4;
        }
        else {
            return 0;
        }
    }

    public boolean validate() {
        int flag = 0;
        for(int i = 0;i < this.exp.length();i ++) {
            if(exp.charAt(i) == '(') {
                flag ++;
            }
            if(exp.charAt(i) == ')') {
                flag --;
            }

            if(flag < 0) {
                return false;
            }
        }
        if(flag != 0) {
            return false;
        }
        Matcher m = Pattern.compile("\\(\\)").matcher(exp);
        if(m.find()) {
            return false;
        }
        String temp = this.exp.replaceAll("\\(|\\)","") + "#";
        m = Pattern.compile("/0").matcher(temp);
        if(m.find()) {
            System.out.println(expo + " = " + "NaN");
        }

        return true;
    }

    public Double getResult() {
        int top = 0;int temp = 0;
        ArrayList<String> list1 = new ArrayList<String>();
        String figure = new String();
        for(int i = 0 ;i < exp.toCharArray().length;i ++) {
            char t = exp.charAt(i);
            if((t <= '9'&&t >= '0')||(t == '.')) {
                figure = figure.concat(String.valueOf(t));
            }
            else if(t == '-'&& !(exp.charAt(i - 1) == ')'||exp.charAt(i - 1) == '(') && !(exp.charAt(i - 1) <= '9'&&exp.charAt(i - 1) >= '0')) {
                figure = figure.concat(String.valueOf(t));
            }
            else {
                if(!figure.isEmpty()) {
                    list1.add(figure);
                    figure = "";
                }

                if(t == '(') {
                    this.stack1.add(String.valueOf(t));temp = top;
                    top = 0;
                }
                else if(t == ')') {
                    while(!stack1.peek().equals("(")) {
                        list1.add(stack1.pop());
                    }
                    stack1.pop();top = temp;
                }
                else if(this.precdence(String.valueOf(t)) >= top) {
                    top = this.precdence(String.valueOf(t));
                    this.stack1.add(String.valueOf(t));
                }
                else {
                    while(!stack1.isEmpty()) {
                        String operate = stack1.peek();
                        if(this.precdence(operate) <= this.precdence(String.valueOf(t))){
                            break;
                        }
                        else {
                            list1.add(operate);
                            stack1.pop();
                        }
                    }

                    top = this.precdence(String.valueOf(t));
                    this.stack1.add(String.valueOf(t));
                }
            }
        }

        if(!figure.isEmpty()) {
            list1.add(figure);
        }

        while(!stack1.isEmpty()) {
            list1.add(stack1.pop());
        }

        int i = 0;
        for(String t:list1) {
            Matcher m = Pattern.compile("-?\\d+").matcher(t);
            if(m.matches()) {
                this.stack2.add(Double.valueOf(t));
            }
            else {
                if(t.equals("-")&&i < list1.size() - 1&&list1.get(i+1).equals("-")) {
                    t = "+";
                }
                double fig2 = stack2.pop();
                double fig1 = stack2.pop();
                Calculate cal = new Calculate(fig1,fig2,t);
                if(t.equals("/")&&fig2 == 0) {
                    System.out.println(expo + " = " + "NaN");
                    return null;
                }
                this.stack2.add(cal.getResult());
            }
            i ++;
        }

        return this.stack2.pop();
    }
}


class Calculate {

    private Operator operator;

    public Calculate() {

    }
    public Calculate(double fig1,double fig2,String o) {
        if(o.equals("+")) {
            this.operator = new Addition(fig1,fig2);
        }
        else if(o.equals("-")) {
            this.operator = new Subtraction(fig1,fig2);
        }
        else if(o.equals("*")) {
            this.operator = new Multiply(fig1,fig2);
        }
        else if(o.equals("/")) {
            this.operator = new Division(fig1,fig2);
        }
    }
    public Calculate(String o) {
        if(o.equals("+")) {
            this.operator = new Addition();
        }
        else if(o.equals("-")) {
            this.operator = new Subtraction();
        }
        else if(o.equals("*")) {
            this.operator = new Multiply();
        }
        else if(o.equals("/")) {
            this.operator = new Division();
        }
    }

    public void setFigure(double fig1,double fig2) {
        this.operator.setAll(fig1, fig2);
    }

    public void setAll(double fig1,double fig2,String o) {
        if(o == "+") {
            this.operator = new Addition(fig1,fig2);
        }
        else if(o == "-") {
            this.operator = new Subtraction(fig1,fig2);
        }
        else if(o == "*") {
            this.operator = new Multiply(fig1,fig2);
        }
        else if(o == "/") {
            this.operator = new Division(fig1,fig2);
        }
    }

    public Operator getOperator() {
        return this.operator;
    }

    public double getResult() {
        return this.operator.operate();
    }
}

abstract class Operator {

    private double fig1;
    private double fig2;

    public Operator() {

    }
    public Operator(double fig1,double fig2) {
        this.fig1 = fig1;
        this.fig2 = fig2;
    }


    public void setfig1(double fig1) {
        this.fig1 = fig1;
    }

    public void setfig2(double fig2) {
        this.fig2 = fig2;
    }

    public void setAll(double fig1,double fig2) {
        this.fig1 = fig1;
        this.fig2 = fig2;
    }

    public double getfig1() {
        return this.fig1;
    }

    public double getfig2() {
        return this.fig2;
    }

    abstract double operate();
    abstract int getLever();
}

class Addition extends Operator{

    private String o = "+";
    public Addition() {

    }
    public Addition(double fig1,double fig2) {
        super(fig1,fig2);
    }

    public String toString() {
        return this.o;
    }

    public double operate(){
        return super.getfig1() + super.getfig2();
    }

    public int getLever() {
        return 1;
    }
}

class Subtraction extends Operator{
    private String o = "-";

    public Subtraction() {

    }
    public Subtraction(double fig1,double fig2) {
        super(fig1,fig2);
    }

    public String toString() {
        return this.o;
    }

    public double operate(){
        return super.getfig1() - super.getfig2();
    }

    public int getLever() {
        return 1;
    }
}

class Division extends Operator{

    private String o = "/";

    public Division() {

    }
    public Division(double fig1,double fig2) {
        super(fig1,fig2);
    }

    public String toString() {
        return this.o;
    }

    public double operate(){
        return super.getfig1() / super.getfig2();
    }

    public int getLever() {
        return 3;
    }
}

class Multiply extends Operator{
    private String o = "*";

    public Multiply() {

    }
    public Multiply(double fig1,double fig2) {
        super(fig1,fig2);
    }

    public String toString() {
        return this.o;
    }

    public double operate(){
        return super.getfig1() * super.getfig2();
    }

    public int getLever() {
        return 3;
    }
}