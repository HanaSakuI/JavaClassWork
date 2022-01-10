package GamePage;

import GameCode.UserData;
import Main.ScenePage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 *  游戏结束页面
 *  内容
 *  1 用户名  2 分数
 *  按钮
 *  重新开始 返回主页 排行榜 退出游戏
 *  按钮要求：每个按钮都要把成绩和等级存入到库中
 */
public class gameOver implements Initializable {
    @FXML private Label userName; // 用户名
    @FXML private Label number; //分数
    @FXML private Button reStar; //重新开始
    @FXML private Button back; //返回主页
    @FXML private Button rankList; //排行榜
    @FXML private Button exit; //退出游戏
    @FXML private Pane pane;
          private ScenePage scenePage = new ScenePage();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new GamePageView();
        number.setText(GamePageView.getNumber());
        userName.setText(UserData.getUserName());
        reStar.setOnAction(event -> {
           LoadPage("/GamePage/GamePage.fxml");
        });
        back.setOnAction(event -> {
            LoadPage("/GamePage/HomePage.fxml");
        });
        rankList.setOnAction(event -> {
            LoadPage("/GamePage/rankView.fxml");
        });
        exit.setOnAction(event -> {
            System.exit(0);
        });
    }
 public void LoadPage(String page){
     try {
         scenePage.showPage(page);
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}
