public class Solution {
    
    public static int drawingEdge(int n) {
        int num = n * (n - 1) /2;
        return fastpower(2, num);
    }
    private static int fastpower(int x, int n ){
        
        long mod = (long)1e9 +7;
        long ans = 1, tmp =(long) x;
        while(n != 0){
            tmp %= mod;
            if(n%2 == 1){
                ans *= tmp;
                ans %= mod;
            }
            
            tmp*= tmp;
            n /=2;

        }
        int num = (int) (ans % mod);
        return num;
    }
    public static int sumOfAll(int num){
        int mod = (int) 1e9 +7;
        int res = 0;
        for(int i = 0; i <= num; i++){
            int curr = binomialCoeff(num, i);
            res += curr;
            res %= mod;
        }
        return res;
    }

    static int binomialCoeff(int n, int k) 
    { 
        int mod = (int) 1e9 +7;
        if (k > n) 
            return 0; 
  
        int res = 1; 
  
        // Since C(n, k) = C(n, n-k) 
        if (k > n - k) 
            k = n - k; 
  
        // Calculate the value of 
        // [n * (n - 1) *---* (n - k + 1)] / 
        // [k * (k - 1) * ... * 1] 
        for (int i = 0; i < k; ++i) 
        { 
            res *= (n - i); 
            res /= (i + 1); 
        } 
        return res % mod; 
    } 
    public static void main(String[] args){
        System.out.println(drawingEdge(991));
    }
}