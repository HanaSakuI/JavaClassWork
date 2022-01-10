    package GamePage;

    import GameCode.LevelSet;
    import GameCode.UserData;
    import Main.AlertBox;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.Button;
    import javafx.scene.control.ComboBox;
    import javafx.scene.control.TextField;
    import  Main.*;
    import java.io.IOException;
    import java.net.URL;
    import java.util.ResourceBundle;
    /**  主页： 登入页要求
     *  1：id  2：用户名 3：选择难度
     *  将数据存入数据库和文件中
     */
    public class HomePageView implements Initializable{
        @FXML private TextField userName; //用户名
        @FXML private ComboBox comboBox; //难度
        @FXML private Button star; //开始按钮
        @FXML private Button ranklist; // 排行榜
              private LevelSet gameLevel = new LevelSet();
              private AlertBox ab = new AlertBox(); //提示框
              private ScenePage scenePage = new ScenePage();
              private static String username;
              private static String level;
        @Override
        public void initialize(URL location, ResourceBundle resources) {
        comboBox.getItems().addAll("1级","2级","3级","4级","5级");
         star.setOnAction(e ->{
             if(userName.getText().isEmpty()){
                 ab.display("请输入用户名！");
             }else if(userName.getText().length() > 10){
                 ab.display("用户名不得超过10个字符！");
             }else if( comboBox.getSelectionModel().isEmpty()){
                 ab.display("请选择难度！");
             }else{
                     new UserData(userName.getText().replaceAll(" ","").toString(),comboBox.getValue().toString());//存入用户名和等级
                     try {
                         System.out.println("UserName:" + userName.getText()+"  " + "Level:" + comboBox.getValue());
                         System.out.println("开始游戏");
                         scenePage.showPage("/GamePage/GamePage.fxml");
                     } catch (IOException ioException) {
                         ioException.printStackTrace();
                         ab.display("无法打开游戏");
                     }
                 }
         });
         ranklist.setOnAction(event -> {
             try {
                 scenePage.showPage("/GamePage/rankView.fxml");
             } catch (IOException e) {
                 e.printStackTrace();
             }
         });
        }
    }
