想要用此来记录一下自己的取模问题，之前对取模一直都比较乱
```{java}
class Solution {
    public int numSub(String s) {
        final long mod = 1000000007L;//取模数字
        long res = 0;//被取模的数字要设置为long
        long j = 0;//这里的j如果不取模也会报错， j 有一个很长的testcase
        
        
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != '1'){
                res += j*(j + 1) / 2;
                res %= mod;//防止溢出
                j = 0;
                
            }else{
                j++;
            }
            
        }
        if(j != 0){
            res += (j*(j + 1) / 2)%mod;
                res %= mod;
        }
     
        return (int) res;//return的时候记得转换成int
    }
}
```
