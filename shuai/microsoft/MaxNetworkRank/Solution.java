import java.util.*;
public class Solution {
    public int solution(int[] A, int[] B, int N){
        Map<Integer, Integer> map = new HashMap<>();
        int res = 1;
        for(int i = 1; i <= N; i++){
            map.put(i, 0);
        }
        
        
        for(int i = 0; i < A.length; i++){
            int start = A[i];
            int end = B[i];
            map.put(start, map.get(start + 1));
            map.put(end, map.get(end + 1));
        }
        for(int i = 0; i < A.length; i++){
            int start = A[i];
            int end = B[i];
            res = Math.max(map.get(start) + map.get(end) - 1, res );
        }
        return res;
    }
}