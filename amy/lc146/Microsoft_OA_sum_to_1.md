```java
// "static void main" must be defined in a public class.
public class Main {
   public static int gcd(int x,int y){ return y==0?x:gcd(y,x%y); }

    public static int solve(int[] A,int[] B){
    
    int ans=0,M=1000000007;
    Map<Long,Integer>map = new HashMap<>();
    
    for(int i=0;i<A.length;i++){
        int p=A[i];
        int q=B[i];
        if(p>q)continue;
        
        int g=gcd(p,q);
        p/=g;
        q/=g;
        // fraction=p/q, we need (q-p)/q for the other term
        long h=(long) (q-p)*M+q;
    
        if(map.containsKey(h)){
            ans=(ans+map.get(h)%M);}
        long num = (long) p*M+q;
        map.put(num, map.getOrDefault(num,0)+ 1);
    }
    return ans;
}
     public static void main(String[] args) {
	int[] x1 = { 1, 1, 2 }, y1 = { 3, 2, 3 };
	int[] x2 = { 1, 1, 1 }, y2 = { 2, 2, 2 };
	int[] x3 = { 1, 2, 3, 1, 2, 12, 8, 4 }, y3 = { 5, 10, 15, 2, 4, 15, 10, 5 };
	System.out.println(solve(x1, y1));
	System.out.println(solve(x2, y2));
	System.out.println(solve(x3, y3));
}
}
```
