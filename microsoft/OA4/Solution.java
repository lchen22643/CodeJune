import java.util.*;

public class Solution {

    public static int minSwap(String str){
        char[] chs = str.toCharArray();
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : chs){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        char midCh = '0';
        int midCount = 0;
        int mid = chs.length / 2;
        for(char ch: map.keySet()){
            int freq = map.get(ch);
            if(freq % 2 == 1){
                midCh = ch;
                midCount ++;
            }
        }
        if(midCount > 1){
            return -1;
        }
        
        int lo = 0;
        int hi = chs.length - 1;
        while(lo < mid){
            char curr = chs[lo];
            int tmp = hi;
            while(tmp >= lo){
                //System.out.println(chs[tmp] + " " + tmp);
                if(chs[tmp] == curr){
                    break;
                }
                
                tmp --;
            }
            if(tmp == lo){
               swapAtoB(chs, lo, lo + 1);
               count ++;
            } else {
               swapAtoB(chs, tmp, hi);
               count += (hi - tmp);
               hi --;
               lo ++;
            }
           
        }
        return count;
    }
    public static void swapAtoB(char[] input, int a, int b){
        if(a < b){
            for(int i = a; i < b; i++){
                swap(input, i, i+1);
            }
            return;
        }
        if(a > b){
            for(int i = a; i > b; i--){
                swap(input, i, i-1);
            }
            return;
        }
        
        
    }
    public static void swap(char[] input, int i, int j){
        char tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }

    public static void main(String[] args){
        String[] input = {"mamad", "opisnd", "ntiin"};
        for(String s : input){
            System.out.println(minSwap(s));
        }
    }
    
}