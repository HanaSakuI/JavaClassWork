package Main;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import java.io.IOException;
/**
 * 切换页面类 如从 登入页面 跳转到 注册页面
 *  调用此类
 *  用法： DrawPage dp = new DrawPage();
 *  dp.showPage("对应页面的fxml文件名");
 */
public class ScenePage { // 调取不同的页面
    private String str;
    public ScenePage(){}
    public ScenePage(String str){
        this.str = str;
    }
    public void showPage(String str) throws IOException { //展示不同的UI
        ObservableList<Stage> stage = FXRobotHelper.getStages();
        javafx.scene.Scene scene = new javafx.scene.Scene(FXMLLoader.load(getClass().getResource(str)));
        stage.get(0).setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.get(0).setX((screenBounds.getWidth() - stage.get(0).getWidth()) / 2);
        stage.get(0).setY((screenBounds.getHeight() - stage.get(0).getHeight()) / 2);
    }
}
