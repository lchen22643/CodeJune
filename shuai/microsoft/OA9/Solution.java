import java.util.*;
/*
 Longest Semi-Alternating Substring
*/
public class Solution {
    public static int solution(String input){
        // index < 3 的字符串可以直接 return str.length()
        if(input.length() < 3){
            return input.length();
        }
        char[] chs = input.toCharArray();
        // 双指针；　count : 记录前一个字符出现的次数
        int start = 0, end = 0, count = 0;
        // 记录符合条件的最长 substring 对应的长度
        int maxSubStrLen = 0;

        // 记录前一个出现的字符， 初始化为 idx = 0 处的字符
        char prev = chs[0];
        count ++;
        end = 1;
        while(end < chs.length){
              char ch = chs[end];
              
              if(ch == prev){
                  count ++;
                  // 连续字符出现后：1 计算当前长度，决定是否更新 maxSubStrLen 2 更新start 和count
                  if(count > 2){
                    int len = end - start;
                    if(len > maxSubStrLen){
                        maxSubStrLen = len;
                    }
                    start = end -1;
                    count = 2;
                    
  
                }
              } else {
                  prev = ch;
                  count = 1;
              }
              end ++;
        }
          // 循环结束后，　计算最后一个区间对应的长度
        if(end - start > maxSubStrLen){
            maxSubStrLen = end - start;
        }

        
        return maxSubStrLen;
    }

    public static void main(String[] args){
        // return 7
        System.out.println(solution("baaabbabbb"));
        // return 5
        System.out.println(solution("babba"));
        // return 4
        System.out.println(solution("abaaaa"));

    }
    
}