package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * 速算游戏规则
 * 一 游戏分为五个难度：
 *    1：两个数的加减法
 *    2：连个数的乘除法（两个数之和不超过100）
 *    3：两个数的四则运算，且包含一定的三则运算
 *    4：一定比例的3-4个数值的混合运算
 *    5：一定比例的带括号运算
 *
 * 二： 用户自选难度，并且 要求输入用户名
 * 三： 1级答对一道得1分，两级答对一道得2分，以此类推。
 * 四：保存排行榜，可读取查看
 * 五：游戏规则：屏幕上方随机掉落各种公式，公式掉落的速度不一，
 *    用户输入某公式计算的结果并以回车结束，若计算正确公式消失，
 *    否则继续下落直至地面；若有5个公式落到地面，游戏结束。回到排行榜，并且用户的信息显示红色。
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GamePage/HomePage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("速算游戏");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        try {
            launch(args);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
