public class Solution {
    public static String solution(String input){
        if(input.length() < 3){
            return input;
        }
        StringBuilder sb = new StringBuilder();
        char currCh = input.charAt(0);
        int len = input.length();
        char[] chs = input.toCharArray();
        int count = 1;
        sb.append(currCh);
        for(int i = 1; i <  len; i++){
            if(count == 2 && chs[i] == currCh){
                continue;
            } else {
                sb.append(chs[i]);
                if(chs[i] == currCh){
                    count ++;
                } else {
                    currCh = chs[i];
                    count = 1;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        String[] test = {"eedaaad", "xxxtxxx", "uuuuxaaaaxuuu"};
        for(String s : test){
            System.out.println(solution(s));
        }
    }
    
}