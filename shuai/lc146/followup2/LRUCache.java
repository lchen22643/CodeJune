import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;


class ListNode {
    public int key;
	public volatile int val;
	public volatile long timestamp;
	public boolean expire;
    public ListNode next;
	public ListNode pre;
	
	public ReentrantLock nodeLock;

    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
		this.pre = null;
		this.timestamp = System.currentTimeMillis();
		nodeLock = new ReentrantLock();
	}
	public void updateNode(int val, long timestamp){
		this.nodeLock.lock();
		this.val = val;
		this.timestamp = timestamp;
		this.nodeLock.unlock();
	}

}

class DList {
    ListNode head = null;
    ListNode tail = null;
    
    public ListNode rmNode(ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode pre = node.pre;
		ListNode next = node.next;
		
        if (pre != null) {
            pre.next = next;
        } else {
            head = node.next;
        }
        if (next != null) {
            next.pre = pre;
        } else {
            tail = node.pre;
        }
        node.pre = null;
        node.next = null;
        return node;
    }

    public void addToTail(ListNode node) {
        if (this.tail == null) {
            this.tail = node;
            this.head = node;
        } else {
            this.tail.next = node;
            node.pre = this.tail;
            this.tail = node;
        }
    }

    public ListNode removeHead() {
        return rmNode(this.head);
    }
}

public class LRUCache {
    Map<Integer, ListNode> dataMap;
	DList list;
	ReentrantLock cacheLock;
	int capacity;

	long expireTime = 10000;
    public LRUCache(int capacity) {
        dataMap = new ConcurrentHashMap<>();
        list = new DList();
		this.capacity = capacity;
		cacheLock = new ReentrantLock();

    }

    public int get(int key) {
		int res = -1;
		
        if (dataMap.containsKey(key)) {
			ListNode node = dataMap.get(key);
			if(System.currentTimeMillis() - node.timestamp > expireTime){
				return res;
			} else {
				node.updateNode(node.val, System.currentTimeMillis());
				res = node.val;
			}

            cacheLock.lock();
            node = list.rmNode(node);
            list.addToTail(node);
			cacheLock.unlock();
            
        }
        return res;

    }

    public void set(int key, int value) {
		ListNode node = null;
		
        if (dataMap.containsKey(key)) {
			node = dataMap.get(key);
			node.updateNode(value, System.currentTimeMillis());
			cacheLock.lock();
			node = list.rmNode(node);
			cacheLock.unlock();

        } else {
			cacheLock.lock();
			node = new ListNode(key, value);
			dataMap.put(key, node);
			cacheLock.unlock();

        }
        cacheLock.lock();
        list.addToTail(node);
        
        if (dataMap.size() > this.capacity) {
            dataMap.remove(list.removeHead().key);
		}
		clearCache();
		cacheLock.unlock();

	}

	public void clearCache(){
		while(list.head != null){
			if(System.currentTimeMillis() - list.head.timestamp > expireTime){
               list.removeHead();
			} else {
				break;
			}
		}
		

	}

    public static void main(String[] args) {
		LRUCache obj = new LRUCache(10);
		obj.set(10, 13);
		obj.set(3, 17);
		obj.set(6, 11);
		obj.set(10, 5);
		obj.set(9, 10);
		System.out.println(obj.get(13));
		obj.set(2, 19);
		System.out.println(obj.get(2));
		System.out.println(obj.get(3));
		obj.set(5, 25);
		System.out.println(obj.get(8));
		obj.set(9, 22);
		obj.set(5, 5);
		obj.set(1, 30);
		System.out.println(obj.get(11));
		obj.set(9, 12);
		System.out.println(obj.get(7));
		System.out.println(obj.get(5));
		System.out.println(obj.get(8));
		System.out.println(obj.get(9));
		obj.set(4, 30);
		obj.set(9, 3);
		System.out.println(obj.get(9));
		System.out.println(obj.get(10));
		System.out.println(obj.get(10));
		obj.set(6, 14);
		obj.set(3, 1);
		System.out.println(obj.get(3));
		obj.set(10, 11);
		System.out.println(obj.get(8));
		obj.set(2, 14);
		System.out.println(obj.get(1));
		System.out.println(obj.get(5));
		System.out.println(obj.get(4));
		obj.set(11, 4);
		obj.set(12, 24);
		obj.set(5, 18);
		System.out.println(obj.get(13));
		obj.set(7, 23);
		System.out.println(obj.get(8));
		System.out.println(obj.get(12));
		obj.set(3, 27);
		obj.set(2, 12);
		System.out.println(obj.get(5));
		obj.set(2, 9);
		obj.set(13, 4);
		obj.set(8, 18);
		obj.set(1, 7);
		System.out.println(obj.get(6));
		obj.set(9, 29);
		obj.set(8, 21);
		System.out.println(obj.get(5));
		obj.set(6, 30);
		obj.set(1, 12);
		System.out.println(obj.get(10));
		obj.set(4, 15);
		obj.set(7, 22);
		obj.set(11, 26);
		obj.set(8, 17);
		obj.set(9, 29);
		System.out.println(obj.get(5));
		obj.set(3, 4);
		obj.set(11, 30);
		System.out.println(obj.get(12));
		obj.set(4, 29);
		System.out.println(obj.get(3));
		System.out.println(obj.get(9));
		System.out.println(obj.get(6));
		obj.set(3, 4);
		System.out.println(obj.get(1));
		System.out.println(obj.get(10));
		obj.set(3, 29);
		obj.set(10, 28);
		obj.set(1, 20);
		obj.set(11, 13);
		System.out.println(obj.get(3));
		obj.set(3, 12);
		obj.set(3, 8);
		obj.set(10, 9);
		obj.set(3, 26);
		System.out.println(obj.get(8));
		System.out.println(obj.get(7));
		System.out.println(obj.get(5));
		obj.set(13, 17);
		obj.set(2, 27);
		obj.set(11, 15);
		System.out.println(obj.get(12));
		obj.set(9, 19);
		obj.set(2, 15);
		obj.set(3, 16);
		System.out.println(obj.get(1));
		obj.set(12, 17);
		obj.set(9, 1);
		obj.set(6, 19);
		System.out.println(obj.get(4));
		System.out.println(obj.get(5));
		System.out.println(obj.get(5));
		obj.set(8, 1);
		obj.set(11, 7);
		obj.set(5, 2);
		obj.set(9, 28);
		System.out.println(obj.get(1));
		obj.set(2, 2);
		obj.set(7, 4);
		obj.set(4, 22);
		obj.set(7, 24);
		obj.set(9, 26);
		obj.set(13, 28);
		obj.set(11, 26);

	}


}