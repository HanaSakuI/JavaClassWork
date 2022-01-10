package GameCode;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import java.util.Random;
import java.util.regex.Matcher;
/**
 * 随机生成方程式。
 * 不同的等级生成的难度不同，生成的速度不同。
 */
public class EquatGenerate {
    private static Label label;
    public EquatGenerate(){}
    public EquatGenerate(Label label){label = label;}
    public static Label addLabel(String equation){
        Label label = new Label(equation);
        label.setLayoutX( 50 +  ((Math.random() * 12 + 1) * 50));
        label.setLayoutY(80);
        StringBuffer result = new StringBuffer();
        for(int i = 0 ; i < 6 ; i ++) {//生成颜色
            result.append(Integer.toHexString(new Random().nextInt(16)));
        }
        label.setTextFill(Paint.valueOf(result.toString().toUpperCase()));
        label.setFont(new Font(30));
        return label;
    }
}
