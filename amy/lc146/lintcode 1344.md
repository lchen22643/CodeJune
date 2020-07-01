思路来源于这道 https://leetcode.jp/leetcode-1329-sort-the-matrix-diagonally-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/ 
题目：
Given a matrix of n * m, you can loop through the counter-diagonal direction to get (n + m) - 1 lists, return sorted lists

Only 26 lowercase letters are included in the matrix
Traversal mode: traverse from the left or bottom boundary of the matrix to the upper right direction
Sort: A=["a", "b", "d"] B=["a", "b", "b"] C=["c", "c", "c"], B < A < C, you need to return [B, A, C]

Example:
Input:
grids = [[a, b, c], [e, d, f],[c, a, m]]
Output:[[a, a, a], [a, f, a], [c, c, c], [e, b, e], [m, m, m]]
Explanation: you can get 5 lists by the paradiagonal, there are [a, a, a], [e, b, e], [c, d, c], [a,f, a], [m, m, m], then you need sort them and return them
```java
public class Solution {
    /**
     * @param grids: a maxtrix with alphabet
     * @return: return sorted lists
     */
    public List<List<String>> CounterDiagonalSort(List<List<String>> grids) {
        int r = grid.size(), c = grids.get(0).size();
        List<List<String>> result = new ArrayList<>();
        for(int i = 0; i < c; i++){
            result.add(collect(i,0,grids));
        }
        for(int j = 1; j < r; j++){
            result.add(collect(0,j,grids);
        }
         Collections.sort(result, Comparator<List<String>>{
             public int compare(List<String> A, List<String> B){
                 int i = 0;
                 while(i < A.size()){
                     if(A.get(i) == B.get(i)){// A.get(i).compareTo(B.get(i)) == 0?
                         i++;
                     }else{
                         return A.get(i).compareTo( B.get(i));
                     }
                 }
                 
             }
             }); 
    }
    private List<String> collect(int row, int col, List<List<String>> grids){
        int r = row, c = col;
        List<String> result = new ArrayList<>();
        while(result.size() < grids.get(0).size()){
            
            while(row >= 0 && row < grids.size() && col >= 0 && col <grids.get(0).size()){
                if(result.size() == grids.get(0).size()){
                return result;
                
                }
            result.add(grids.get(row).get(col));
            col--;
            row++;
            }
            row = r; 
            col = c;
        }
        return result;
        
    }
}
```

a = 0,0
e, b e = 1,0 0,1 1,0
c d c = 2,0 1,1, 0,2
a f = 2,1 1,2 2,1

[[a, b, c], 
[e, d, f],
[c, a, m]]

```java
public class Solution {
    /**
     * @param grids: a maxtrix with alphabet
     * @return: return sorted lists
     */
    public List<List<String>> CounterDiagonalSort(List<List<String>> grids) {
        int r = grids.size(), c = grids.get(0).size();
        List<List<String>> result = new ArrayList<>();
        for(int i= 0; i < r; i++){//先从左上角到左下角为matrix[0 ~ matrix.length - 1]
            result.add(collect(i,0,grids));
        }
        for(int j = 1; j < c; j++){//再从左下角到右下角，为matrix[matrix.length - 1][i~(1,matrix[0].length - 1)]
            result.add(collect(r - 1,j,grids));
        }
        Collections.sort(result, new Comparator<List<String>>(){
             public int compare(List<String> A, List<String> B){
                StringBuilder sba = new StringBuilder();
                StringBuilder sbb = new StringBuilder();
              //  System.out.println("A's Size is " + A.size()+ "B's Size is " + B.size());
                int i = 0;
                while(i < A.size()){
                     sba.append(A.get(i));
                     sbb.append(B.get(i));
                     i++;
                    }
                    String a = sba.toString();
                    String b = sbb.toString();
                return a.compareTo(b );
                 
             }
             }); 
            return result; 
    }
    private List<String> collect(int row, int col, List<List<String>> grids){
        int r = row, c = col;
        List<String> result = new ArrayList<>();
        while(result.size() < Math.min(grids.size(),grids.get(0).size())){
            
            while(row >= 0 && row < grids.size() && col >= 0 && col <grids.get(0).size()
            && result.size() < Math.min(grids.size(),grids.get(0).size())){
            //增加一个判别条件，相关报错测试：[["v","f","a","k"],["n","x","e","j"],["p","m","e","b"]]
                if(result.size() == grids.get(0).size()){
                return result;
                
                }
            result.add(grids.get(row).get(col));
            col++;
            row--;
            }
            row = r; 
            col = c;
            
        }
        return result;
        
    }
}
```
