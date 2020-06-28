import java.io.*;
import java.util.*;
public class Solution {
    public static boolean solution(String a, String b){
        if(a.length() != b.length()){
            return false;
        }
        int[] charCountOnA = new int[26];
        int[] charCountOnB = new int[26];

        // 检查两个 string 包含的char 是否一致
        for(int i = 0 ; i < charCountOnA.length; i++){
            if(charCountOnA[i] == 0 && charCountOnB[i] != 0){
                return false;
            }
            if(charCountOnB[i] == 0 && charCountOnA[i] != 0){
                return false;
            }
        }
        for(char ch : a.toCharArray()){
            charCountOnA[ch - 'a'] ++;
        }
        for(char ch: b.toCharArray()){
            charCountOnB[ch - 'a'] ++;
        }

         // 检查两个 string 包含的char 是否一致
        for(int i = 0 ; i < charCountOnA.length; i++){
            if(charCountOnA[i] == 0 && charCountOnB[i] != 0){
                return false;
            }
            if(charCountOnB[i] == 0 && charCountOnA[i] != 0){
                return false;
            }
        }
        // sort two char Array
        // compare two sorted char array
        Arrays.sort(charCountOnA);
        Arrays.sort(charCountOnB);
        for(int i = 0; i < 26; i++){
            if(charCountOnA[i] != charCountOnB[i]){
                return false;
            }
        }

        return true;


    }

    public static void main(String[] args) throws IOException{
        String fileName = "input.txt";
        File testFile = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(testFile));
        String line = null;
        while((line = reader.readLine()) != null){
            String[] inputStrs = line.split(" ");
            System.out.println(solution(inputStrs[0], inputStrs[1]));
        }
    }
}