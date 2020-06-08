import java.util.*;
public class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int left ,right;
        int idx = getIndex(arr, x);
        left = idx-1; right = idx;
        while(k > 0){
        
            if( left < 0 || right < arr.length && Math.abs(arr[left] - x) > Math.abs(arr[right] -x)){
                right ++;
            } else{
                left --;
            }
            k --;
        }
        for(int i = left + 1; i < right; i++){
            res.add(arr[i]);
        }
        return res;

    }
    private int getIndex(int[] arr, int x){
        int lo = 0, hi = arr.length -1;
        while(lo < hi){
            int mid = (lo + hi) / 2;
            if(arr[mid] < x){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}