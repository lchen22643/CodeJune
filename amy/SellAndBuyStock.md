start for lc 121 
这道题首先最常见的思路是用贪心法，设定一个最小买入价随着每一天的股价更新，设定一个最大卖出价随着每一天的股价更新，数组里面的值遍历一遍就会得出最大的价格差 

```java
Int bestTime( int[] arr){
Int[] slice = new int[2];
if(arr.length <= 1){ 
return 0;
}
//slice[0] = min arr[] before i
 // slice[1] = max[] - min[arr];
slice [0] = arr[0];
Slice[1] = Intger.Min_Num;

for(int i = 1; i < arr.length ; i++){
Slice[1] = Math.max(arr[i] - slice[0], slice[1]);
 Slice[0] = Math.min(arr[i], slice[0]);

}



return slice[1] <0 ? 0 : slice[1];
}

```
其实这道题一开始的价钱是设定在第0天之前买入的最小值 和第0天之前买出的最大值，所以一开始我们slice[0]是第一天的买入值而slice[1]是第一天预期的卖出最大值，我们设定为Integer.MIN_VALUE
那么如果你可以完成最多 两笔交易呢？
一个办法是从右边算一个卖出的值
```java
class Solution {
    public int maxProfit(int[] prices) {
            if(prices.length == 0) return 0;
            int[] leftProfix = new int[prices.length];
            leftProfix[0] = 0;
            int low = prices[0];
            for(int i = 1; i < prices.length; i++){
                leftProfix[i] = Math.max(prices[i] - low, leftProfix[i - 1]);
                low = Math.min(prices[i], low);
            }
            int[] rightProfix = new int[prices.length];
            int high = prices[prices.length -1];
            rightProfix[prices.length -1] = 0;
            for(int i = prices.length - 2; i >= 0; i--){
                rightProfix[i] = Math.max(rightProfix[i + 1], high - prices[i] );
                high = Math.max(prices[i], high);
            }
            int result = 0;
            for(int i = 0; i < prices.length; i++){
                result = Math.max(result, rightProfix[i]+leftProfix[i]);
            }
            return result;
    }
}
```
这里的rightprofix说的就是把右边当成开始，然后往前做贪心， 一开始设定的卖出价肯定是 prices[prices.length -1]，因为不知道买入多少钱所以我们设定rightProfix[prices.length -1] = 0;
rightprofix[i - 1] 找的是当日买入和不知道哪天卖出的 最大差  
high更新的是卖出的最高价（就是跟 low反过来，因为low是买入而high是卖出)  
这道题的概念主要在于理解了贪心法的初始设定  
因为我们leftprofix[0]代表的是比price[0]早（一天）的初始值，而rightprofix[n - 1] 代表的是比price[n - 1] 晚一天的初始值  
leftprofix说的是0 - 1~ n -2的日期差之间的数字 right profix 说的是1~n  
举个栗子  
[3,3,5,0,0,3,1,4]  
这里  
left为  
[0,0,2,2,2,3,3,4]    
right 为  
[4,4,4,4,4,3,3,0]  
左边是这样的  
[0,0,2,2,2,3,3,4]  
-[3,3,5,0,0,3,1,4]  
右边其实是这样一种差  
-[3,3,5,0,0,3,1,4]  
--[4,4,4,4,4,3,3,0]  
  不知道这样能不能说明差异性之间的问题 总之这个时间差导致了两边没有冲突 直接加起来就好了  
  
  
  

