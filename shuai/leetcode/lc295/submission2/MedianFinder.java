package submission2;

import java.util.*;

public class MedianFinder {
    ArrayList<Integer> list;

    public MedianFinder() {
        list = new ArrayList<>();
    }

    private int findIndex(int target) {
        int lo = 0, hi = list.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (list.get(mid) < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public void addNum(int num) {
        int idx = findIndex(num);
        if (idx < list.size()) {
            list.add(idx, num);
        } else {
            list.add(num);
        }
    }

    public double findMedian() {
        double res = 0d;
        int len = list.size();
        if (len % 2 == 1) {
            res = (double) list.get(len / 2);
        } else {
            res = (list.get(len / 2 - 1) + list.get(len / 2)) / 2.0;
        }
        return res;
    }
}