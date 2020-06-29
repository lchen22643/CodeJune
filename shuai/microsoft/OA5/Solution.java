import java.util.*;
/*
Longest Substring Without 3 Contiguous Occurrences of Letter
*/
public class Solution {
    public static String solution(String input){
        // index < 3 的字符串可以直接 return
        if(input.length() < 3){
            return input;
        }
        char[] chs = input.toCharArray();
        // 双指针；　count : 记录前一个字符出现的次数
        int start = 0, end = 0, count = 0;
        // 记录符合条件的最长 substring 对应的区间 // [0,1)
        int[] subStrRange = {0,1};

        // 记录前一个出现的字符， 初始化为 idx = 0 处的字符
        char prev = chs[0];
        count ++;
        end = 1;
        while(end < chs.length){
              char ch = chs[end];
              
              if(ch == prev){
                  count ++;
                  // 连续字符出现后：1 计算当前长度，决定是否更新 subStrRange 2 更新start 和count
                  if(count > 2){
                    int len = end - start; //[0,1) [0, 2)]
                    if(len > subStrRange[1] - subStrRange[0]){
                        subStrRange[0] = start;
                        subStrRange[1] = end;
                    }
                    // 下一个满足条件的 substring　是从当前 string　往前一位开始，　即 end - 1
                    // 此时的区间为[end -1, end] 因此重复元素为２
                    start = end - 1;
                    count = 2;
                    
  
                }
              } else {
                  prev = ch;
                  count = 1;
              }
              end ++;
        }
          // 循环结束后，　计算最后一个区间对应的长度
        if(end - start > subStrRange[1] - subStrRange[0]){
            subStrRange[0] = start;
            subStrRange[1] = end;
        }

        
        return input.substring(subStrRange[0], subStrRange[1]);
    }

    public static void main(String[] args){
        //
        System.out.println(solution("aabbaaabbaba"));
        //
        System.out.println(solution("aabbaabbaabbaa"));
    }
    
}