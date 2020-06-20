public class Solution {
    public static int solution(int[] arr){
        int res = 1;
        int lo = 0, hi = arr.length - 1;
        while(true){
            int splitIdx = findSplit(arr, lo, hi);
            System.out.println(splitIdx);
            if(splitIdx == -1){
                break;
            } else {
                lo = splitIdx + 1;
                res ++;
            }
        }
        return res;
    }
    private static int findSplit(int[] arr, int lo, int hi){
        if(hi - lo < 1){
            return -1;
        }
        int[] maxArr = new int[arr.length];
        int[] minArr = new int[arr.length];
        maxArr[lo] = arr[lo];
        minArr[hi] = arr[hi];
        for(int i = lo + 1; i <= hi; i++){
             maxArr[i] = Math.max(maxArr[i-1], arr[i]);
        } 
        for(int i = hi - 1; i >= lo; i--){
             minArr[i] = Math.min(minArr[i+1], arr[i]);
        }
        for(int i = lo; i < hi; i++){
            if(maxArr[i] < minArr[i+1]){
                return i;
            }
        }
        return -1;

    }
    public static void main(String[] args){
        int[] input = {2,4,1,6,5,9,7};
        System.out.println(solution(new int[]{2,4,1,6,5,9,7}));
        System.out.println(solution(new int[]{4,3,2,6,1}));
        System.out.println(solution(new int[]{2,1,6,4,3,7}));
    }
}