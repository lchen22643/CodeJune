public class Solution {
    public int mctFromLeafValues(int[] A) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for(int i = 0; i < A.length; i++){
            int x = A[i];
            while (stack.peek() <= x) {
                int mid = stack.pop();
                res += mid * Math.min(stack.peek(), x);
            }
            stack.push(x);
        }
        
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }
}