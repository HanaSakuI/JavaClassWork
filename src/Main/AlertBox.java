package Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 提示框类
 * 如果出现错误情况，调用此方法，从而提示用户 出了什么错误
 * 用法： AlertBox ab = new AlertBox();
 *       ab.dispaly("弹出的提示内容写在这里");
 */
public class AlertBox { // 弹出提示框
    public void display(String str){
        Stage stage = new Stage();
        stage.setTitle("提示");
        //modality要使用Modality.APPLICATION_MODEL
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(300);
        stage.setMinHeight(132);

        Button button = new Button("确定");
        button.setLayoutX(50);
        button.setLayoutY(104);
        button.setOnAction(e -> stage.close());

        Label label = new Label(str);
        label.setLayoutY(199);
        label.setLayoutY(24);
        label.setFont(Font.font(18));//字体大小

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label , button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
        stage.showAndWait();
    }
}
