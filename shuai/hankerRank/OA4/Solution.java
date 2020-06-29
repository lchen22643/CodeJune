import java.util.*;

public class Solution {
    public static int phoneCalls(List<Integer> start, List<Integer> duration, List<Integer> volume){
        List<Node> list = new ArrayList<>();
        for(int i = 0; i < start.size(); i++){
            Node node = new Node(start.get(i), duration.get(i), volume.get(i));
        }
        list.sort(new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b){
                return a.start - b.start;
            }
        });
        int max = 0;
        int lo = 0;
        
        boolean[] mark = new boolean[start.size()];
        while(lo < list.size()){
            int count = 0;
            int curr = lo;
            while(curr < list.size()){
                  count += list
            }

        }
    }
}
class Node {
    int start;
    int duration;
    int volume;
    public Node(int start, int duration, int volume){
        this.start = start;
        this.duration = duration;
        this.volume = volume;
    }
}