package GamePage;

import Main.ScenePage;
import GameCode.RankSort;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
/**
 * 排行榜
 * 排行榜要求： 1 分级查看
 *            2 将用户名 + 成绩 排序输出 展示
 * 先通过IOtxt将数据一行行保存在List里
 * 再将List进行切割成用户名和成绩 保存在map里
 * 再将map进行排序
 * 将排序完的map存入到Llabel中
 */
public class RankListView  implements Initializable {
    @FXML private Pane pane;
    @FXML private MenuItem level1; //显示五个不同等级
    @FXML private MenuItem level2;
    @FXML private MenuItem level3;
    @FXML private MenuItem level4;
    @FXML private MenuItem level5;
    @FXML private Label dis;
    @FXML private MenuItem back; //返回主页
          private ScenePage scenePage = new ScenePage();
          private static ArrayList<String> list  = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //初始化
        try {
            list.clear();
            list = RankSort.CompareList("level1");
        } catch (IOException e) {
            e.printStackTrace();
        }
          setRank();
        back.setOnAction(event -> {
         try {
             scenePage.showPage("/GamePage/HomePage.fxml");
         } catch (IOException e) {
             e.printStackTrace();
         }
     });
         level1.setOnAction(event -> {
             dis.setText("难度一");
             try {
                 list.clear();
                 list = RankSort.CompareList("level1");
             } catch (IOException e) {
                 e.printStackTrace();
             }
             setRank();
         });
        level2.setOnAction(event -> {
            dis.setText("难度二");
            try {
                list.clear();
                list = RankSort.CompareList("level2");
            } catch (IOException e) {
                e.printStackTrace();
            }
            setRank();
        });
        level3.setOnAction(event -> {
            dis.setText("难度三");
            try {
                list.clear();
                list = RankSort.CompareList("level3");
            } catch (IOException e) {
                e.printStackTrace();
            }
            setRank();
        });
        level4.setOnAction(event -> {
            dis.setText("难度四");
            try {
                list.clear();
                list = RankSort.CompareList("level4");
            } catch (IOException e) {
                e.printStackTrace();
            }
            setRank();
        });
        level5.setOnAction(event -> {
            dis.setText("难度五");
            try {
                list.clear();
                list = RankSort.CompareList("level5");
            } catch (IOException e) {
                e.printStackTrace();
            }
            setRank();
        });
    }
   public void setRank(){
           pane.getChildren().clear();
           if(list.isEmpty()){
               return;
           }else{
               for(int i = 0 ; i < list.size() ; i ++){
                   if(String.valueOf(list.get(i)).equals("null")){ break; }
                   Label label = new Label("  第" + ( i + 1 ) + "名：   " + String.valueOf( list.get(i) ) + "分");
                   label.setLayoutX(40);
                   label.setLayoutY(40 + i * 65);
                   label.setFont(Font.font(25));
                   if( i == 0 ){label.setTextFill(Paint.valueOf("#ff0000"));}
                   pane.getChildren().add(label);
                   if( i == 5) break;
               }
           }

   }
}
