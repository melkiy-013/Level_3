package Lessen_6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public List zadanie_1(List<Integer> ar){
        List<Integer> res = new ArrayList<>();
        for(int i=ar.size()-1; i>0; i--){
            if(ar.get(i).equals(4)){
                break;
            }
            res.add(ar.get(i));
        }
        Collections.reverse(res);
        return res;
    }

    public boolean zadanie_2(List<Integer> ar){
        for(int i:ar){
            if(i == 4){
                return true;
            }
        }
        return false;
    }

}
