import java.util.*;
public class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int lo = 0, hi = arr.length -1;
        while(lo < hi){
            int mid = (lo + hi) / 2;
            if(arr[mid] < x){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        int lowerBound = -1, upperBound = -1;
        if(lo < arr.length){
            lowerBound = lo;
        }
        if(lo > 0){
            upperBound = lo -1;
        }
        int idx = -1;
        int min = Integer.MAX_VALUE;
        if(lowerBound != -1){
            min = Math.min(arr[lowerBound] - x, min);
            idx = lowerBound;
        }
        if(upperBound != -1 && x - arr[upperBound] <= min ){
            idx = upperBound;
        }
        res.add(arr[idx]);
        lo = idx-1; hi = idx+1;
        while(lo >= 0 && hi < arr.length){
            if(res.size() == k){
                break;
            }
            if(Math.abs(x - arr[lo]) <= Math.abs(x - arr[hi])){
                res.add(arr[lo]);
                lo --;
            } else{
                res.add(arr[hi]);
                hi ++;
            }
        }
        while(res.size() < k ){
            if(lo >= 0){
                res.add(arr[lo --]);
            }
            if(hi < arr.length){
                res.add(arr[hi ++]);
            }
        }
        Collections.sort(res);
        return res;

    }
}