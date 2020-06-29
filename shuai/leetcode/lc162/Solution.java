public class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length == 1){
            return 0;
        }
        int lo = 0, hi = nums.length - 1;
        while(lo < hi){
            int mid = lo + (hi - lo) / 2;
           
            if(nums[mid] < nums[mid + 1]){
                hi= mid;
            } else {
                lo = mid + 1;
            }
        }
        if(lo == nums.length - 1 || nums[lo] > nums[lo + 1]){
            return lo;
        } else {
            return hi;
        }
        
    }
   
}