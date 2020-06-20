public class Solution {
    public int solution(int N, String S){
        boolean[][] seats = new boolean[N][10];
        for(String str : S.split(" ")){
            int row = str.charAt(0) - '0';
            int col = str.charAt(1) - 'A';
            seats[row][col] = true;
        }
        int res = 0;
        for(int i = 0; i < N; i++){
            res += checkSeat(seats, i, 1);
            res += checkSeat(seats, i, 3);
            res += checkSeat(seats, i, 7);
        }
        return res;
    }

    public int checkSeat(boolean[][] seats, int row, int start){
        
        int curr = start;
        while(curr < seats[row].length){
            if(seats[row][curr] == seats[row][start]){
                curr ++;
            } else {
                break;
            }
        }
        if(curr - start > 4){
            return 1;
        } else {
            return 0;
        }
    }
}