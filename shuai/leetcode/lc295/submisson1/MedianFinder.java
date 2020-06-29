import java.util.*;

public class MedianFinder {
    // put the median in the left queue
    // left Q 为最大堆
    PriorityQueue<Integer> leftQ;
    // rightQ 为最小堆
    PriorityQueue<Integer> rightQ;
    /** initialize your data structure here. */
    public MedianFinder() {
        leftQ = new PriorityQueue<>( (a, b) -> b- a);
        rightQ = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if(leftQ.size() == rightQ.size()){
            rightQ.add(num);
            leftQ.add(rightQ.poll());
        } else {
            leftQ.add(num);
            rightQ.add(leftQ.poll());
        }
    }
    
    public double findMedian() {
        double res = 0d;
        if(leftQ.size() > rightQ.size()){
            res = (double) leftQ.peek();
        } else {
            res = (leftQ.peek() + rightQ.peek()) / 2.0;
        }
        return res;
    }
}