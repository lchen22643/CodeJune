import java.util.*;
public class Solution {
    public int solution(int[] A){
        int max = -1;
        Map<Integer, Pair> map = new HashMap<>();
        for(int number : A){
            int sum = sumDigit(number);
            Pair pair = map.getOrDefault(sum, new Pair());
            pair.addNumber(number);
            max = Math.max(max, pair.getSum());
        }
        
        return max;
    }
    public int sumDigit(int a){
        int res = 0;
        while(a > 0){
            res += (a % 10);
            a = a / 10;
        }
        return res;
    }
}
class Pair {
    int lo = -1;
    int hi = -1;

    public void addNumber(int input){
           if(input > hi){
               hi = input;
               return;
           }
           if(input > lo){
               lo = input;
               return;
           }
    }
    public int getSum(){
        if(lo == -1 || hi == -1){
            return -1;
        } else {
            return lo + hi;
        }
    }
}