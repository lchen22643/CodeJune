import java.util.*;
// "static void main" must be defined in a public class.
public class Main {
    public static int calculateOccurance(int[] arr, int[][] query){
        if(arr.length == 0|| query.length == 0){
            return 0;
        }
        Map<Integer, List<Integer> > map = new HashMap<>();
        
        // O(N)
        for(int i = 0; i < arr.length; i++){
            if(map.containsKey(arr[i])){
                map.get(arr[i]).add(i);
            }else{
                map.put(arr[i], new ArrayList<>());
                map.get(arr[i]).add(i);
            }
        }
        int res = 0;
        for(int i = 0; i < query.length; i++){
            int[] part = query[i];
            // 
            res += searchList(part[0], part[1], map.get(part[2]));
               
            
        }
        return res;
    }
    /*
    return number of element in list between [start, end]
    idxStart: 如果 start 在 list 里，返回idx, or, 返回 insertion point
    idxEnd: 如果 end 在 list 里，返回idx, or, 返回 insertion point - 1
    返回区间　[idxStart, idxEnd] 的长度
    */
    public static int searchList(int start, int end, List<Integer> list){
        if(start > end){
            return 0;
        }
        
        /*
        index of the search key, if it is contained in the array; 
        otherwise, (-(insertion point) - 1). 
        The insertion point is defined as the point at which the key would be inserted into 
        the array: the index of the first element greater than the key, or a.length if all elements in the array are less than the specified key. 
        Note that this guarantees that the return value will be >= 0 if and only if the key is found.
        */
        int idxStart = Collections.binarySearch(list, start);
        if(idxStart < 0){
            idxStart = -idxStart - 1;
        }
        int idxEnd = Collections.binarySearch(list, end);
        if(idxEnd < 0){
            idxEnd = -idxEnd -2;
        }
        return idxEnd - idxStart + 1;
    }
    public static void main(String[] args) {
        int[] array = new int[]{1,1,2,3,2};
        int[][] matrix = new int[][]{{1,2,1}, {2,4,2}, {0,3,1}};

        System.out.println(calculateOccurance(array, matrix));
    }
}