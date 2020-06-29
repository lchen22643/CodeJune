import java.util.*;

public class Solution {
    public static List<List<Integer>> solution(int[] arr, int n){
        
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        for(int i = 0; i < n; i++){
            List<Integer> list = new ArrayList<>();
            res.add(list);
            int[] sumAtI = {i, 0};
            pq.add(sumAtI);
        }
        
        for(int i = arr.length -1 ; i >= 0; i--){
            int[] minSum = pq.poll();
            res.get(minSum[0]).add(arr[i]);
            minSum[1] += arr[i];
            pq.add(minSum);

        }
        return res;
    }
    public static void test(int[] arr, int n){
        List<List<Integer>> res = solution(arr, n);
        for(List<Integer> list : res){
            System.out.print("[");
            for(int i : list){
                System.out.print(i + ",");
            }
            System.out.print("]");
            int sum = list.stream().reduce((a,b)->a+b).get();
            System.out.print(" sum is : " + sum + ";");
        }
        System.out.println();
    }
    public static void main(String[] args){
        // minSum + curr = maxSum // > maxSum // < maxSum
        test(new int[] {1,2,15, 20}, 2);
        test(new int[]{1,2,3,4,5,6,7,10,16,29}, 3);
        test(new int[]{1,4,5,5,5}, 4);
        test(new int[] {2,2,3,4,6, 10}, 3);
    }
}