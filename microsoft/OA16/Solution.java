import java.util.*;
/*
Min Adj Swaps to Group Red Balls
*/
public class Solution {
    /**
     * 
     * @param s
     * @return minSwap
     * 
     *  greedy 思路, 找出最中间的　red ball, 然后每个它附近的球swap 向其靠拢
     *  计算的时候不需要一步步的操作，　只要计算该球和mi之间的　white ball 即可，　即　number of swaps for each R is the distance to mid, minus the number of R's between them
     */
    public static int solution(String s) {
        List<Integer> redIndices = getRedIndices(s);
        int max = 1000000000;
        int mid = redIndices.size() / 2;
        int minSwaps = 0;
        for (int i = 0; i < redIndices.size(); i++) {
            // 
            minSwaps += Math.abs(redIndices.get(mid) - redIndices.get(i)) - Math.abs(mid - i);
            if(minSwaps > max){
                return -1;
            }
        }
        return minSwaps;
    }
    
    private static List<Integer> getRedIndices(String s) {
        List<Integer> indices = new ArrayList<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                indices.add(i);
            }
        }
        return indices;
    }

    public static void main(String[] args){
        // return 2
        System.out.println(solution("WRRWWR"));
        // return 4
        System.out.println(solution("WWRWWWRWR"));
        // return 0
        System.out.println(solution("WWW"));
    }
    
}