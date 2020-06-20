import java.util.*;
public class Solution {

    public static boolean solution(int[] A){
        Set<Integer> set = new HashSet<>();
        for(int i : A){
            set.add(i);
        }
        for(int i : set){
            if(set.contains(i -1) || set.contains(i+1)){
                return true;
            }
        }
        return false;
    }

    
    
}