package GameCode;

import InAndOut.inputData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *  等级排序
 *
 *  将不同等级的成绩 按分数排序
 *
 */
public class RankSort {

    public static ArrayList CompareList(String level) throws IOException {
          ArrayList<String> rank = (ArrayList<String>) inputData.getLevellist(level);
          if(rank.isEmpty()){
              return rank;
          }else {
              Collections.sort(rank,new Comparator<String>() {
                  @Override
                  public int compare(String s1, String s2) {
                      if(String.valueOf(s1).equals("null") || String.valueOf(s2).equals("null")){
                          return 1;
                      }else{
                          String[] t1 = s1.split(" ");
                          String[] t2 = s2.split(" ");
                          return Integer.parseInt(t2[1]) - Integer.parseInt(t1[1]);
                      }
                  }
              });
          }
            return rank;
    }
}
