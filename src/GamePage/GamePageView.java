package GamePage;
import GameCode.EquatGenerate;
import InAndOut.saveScore;
import Main.ScenePage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import GameCode.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class GamePageView implements Initializable{
    @FXML private Label gameNumber; // 分数
    @FXML private ImageView heart1; // 生命值 1 -5
    @FXML private ImageView heart2;
    @FXML private ImageView heart3;
    @FXML private ImageView heart4;
    @FXML private ImageView heart5;
    @FXML private Pane pane;
    @FXML private Label result; //结果是否正确
    @FXML private Menu level;
    @FXML private TextField input; //输入的答案
    @FXML private MenuItem pause; //暂停
    @FXML private MenuItem play; //开始游戏
    @FXML private MenuItem reStar; // 重新开始
    @FXML private MenuItem back; // 主页按钮
    @FXML private MenuItem exit; // 退出游戏
    @FXML private MenuItem ranklist; // 排行榜
          private final String bingoFile ="src\\Resource\\true.mp3";
          private final String errorFile = "src\\Resource\\false.mp3";
          private final String bgm = "src\\Resource\\1.mp3";
          private Media bingo = new Media(new File(bingoFile).toURI().toString());
          private Media error = new Media(new File(errorFile).toURI().toString());
          private Media playBGM = new Media(new File(bgm).toURI().toString());
          private MediaPlayer mediaPlayer;
          private MediaPlayer mp = new MediaPlayer(playBGM);
          private static boolean flag = false; //代表是否答对
          private Image image = new Image("/Resource/heart.jpg");
          private int t = 5; //代表心的个数
          private int time = 0; //循环时间
          private Timeline timeline = new Timeline(); //时间线
          private ScenePage dp = new ScenePage();
          private static String number; //分数
          private static ArrayList<Double> arr = new ArrayList<>();
          private static Map<String,String> map = new TreeMap<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        map.clear();
        mp.setCycleCount(9999);
        mp.setVolume(0.7);
        mp.play();
        level.setText("Level ：" + UserData.getLevel());
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if( (time += 10) % 2200 == 0){
                    new LevelSet(UserData.getLevel());
                    arr.add(LevelSet.getDownSpeed());
                    Calculation.ExpEvalute(LevelSet.getExpression());
                    map = Calculation.getMap();
                    pane.getChildren().add(EquatGenerate.addLabel(LevelSet.getExpression())); //将Label显示出来
                }
                for(int i = 0; i < pane.getChildren().size() ; i++){
                    try {
                        pane.getChildren().get(i).setLayoutY(pane.getChildren().get(i).getLayoutY() + arr.get(i));
                    }catch (Exception e){
                         System.out.println("数组超限");
                    }
                    if(pane.getChildren().get(i).getLayoutY() > 640){
                         pane.getChildren().remove(i);
                         arr.remove(i);
                        try {
                            heartDown(t); //调用减少生命值方法
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        playMusic(error);
                    }
                }

            }
        });
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.play();
        input.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    for(Map.Entry m : map.entrySet()){
                        System.out.println(m.getKey()+" "+m.getValue());
                        if(input.getText().replaceAll(" ","").equals(m.getValue())){ //输入的答案等于map中的值
                         for( int i = 0; i < pane.getChildren().size(); i ++){
                             Label label = (Label) pane.getChildren().get(i);
                                 if(m.getKey().equals(label.getText())){ //况且这个计算式存在
                                     flag = true; // bool更换为true
                                     gameNumber.setText(String.valueOf(Integer.parseInt(gameNumber.getText()) + LevelSet.getScore()));  // 加分
                                     pane.getChildren().remove(i);//删除这个表达式
                                     map.remove(m.getKey()); //移除这个map
                                     playMusic(bingo);
                                     break;
                                 }
                             }
                        }
                        if(flag)break;
                    }
                    Flag(); //判断是否正确
                }
            }
        });

      pause.setOnAction(event -> {
          timeline.pause();
          mp.pause();
          input.setDisable(true);
      });
      play.setOnAction(event -> {
          timeline.play();
          mp.play();
          input.setDisable(false);
      });
     reStar.setOnAction(event -> {
         timeline.stop();
         mp.stop();
         try {
             dp.showPage("/GamePage/GamePage.fxml");
         } catch (IOException ioException) {
             ioException.printStackTrace();
         }
     });
     back.setOnAction(e->{
         timeline.stop();
         mp.stop();
         mp.dispose();
         try {
             dp.showPage("/GamePage/HomePage.fxml");
         } catch (IOException ioException) {
             ioException.printStackTrace();
         }
     });
     ranklist.setOnAction(event -> {
         timeline.stop();
         mp.stop();
         mp.dispose();
         try {
             dp.showPage("/GamePage/rankView.fxml");
         } catch (IOException e) {
             e.printStackTrace();
         }
     });
     exit.setOnAction(event ->{
         System.exit(0);
     });
    }
    public void heartDown(int heart) throws IOException {
        switch (heart){
            case 1:heart5.setImage(null);break;
            case 2:heart4.setImage(null);break;
            case 3:heart3.setImage(null);break;
            case 4:heart2.setImage(null);break;
            case 5:heart1.setImage(null);break;
            default:break;
        }

        if( (t -= 1 ) == 0){
            timeline.stop();
            mp.stop();
            mp.dispose();
            arr.clear();
            number = gameNumber.getText().toString();
            saveScore.save(UserData.getLevel(),UserData.getUserName(),number);
            try{
                dp.showPage("/GamePage/gameOver.fxml");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void playMusic(Media media){
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.seek(Duration.seconds(0.4));
        mediaPlayer.play();
    }
    public void Flag(){
        if(flag == true){
            result.setText("答对咯");
            result.setTextFill(Paint.valueOf("#FF0000"));
        }else{
            if(!input.getText().replaceAll(" ","").isEmpty()){
                result.setText("再试试吧");
                playMusic(error);
                result.setTextFill(Paint.valueOf("#0000CC"));
            }else{
                    result.setText("请输入答案");
                    result.setTextFill(Paint.valueOf("#0000CC"));
            }
        }
        flag = false;
        input.setText("");
    }
    public static String  getNumber(){
        return number;
    }
}
