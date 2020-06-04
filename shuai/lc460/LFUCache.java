import java.util.*;

class ListNode {
    public int key;
    public int val;
	public int count;
    public ListNode next;
    public ListNode pre;
    
    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
        this.pre = null;
    }
    
}
class DList {
	ListNode head = null;
    ListNode tail = null;
    int size = 0;
	public ListNode rmNode(ListNode node){
        if(node == null){
            return null;
        }
        ListNode pre = node.pre;
        ListNode next = node.next;
		// 如果当前节点为 head, 更新 head
        if(pre != null){
            pre.next = next;
        } else {
            head = node.next;
        }
		// 如果当前节点为 tail, 更新 tail
        if(next != null){
            next.pre = pre;
        } else {
            tail = node.pre;
        }
        node.pre = null;
        node.next = null;
		this.size --;
        return node;
    }
    public void addToTail(ListNode node){
		    // 如果 tail 为空， 同时更新 tail 和 head
            if(this.tail == null){
                this.tail = node;
                this.head = node;
            } else {
                this.tail.next = node;
                node.pre = this.tail;
                this.tail = node;
            }
			this.size ++;
    }
    public ListNode removeHead(){
        return rmNode(this.head);
    }
	public int size(){
		return this.size;
	}
}
public class LFUCache {
    Map<Integer, ListNode> vals;
    Map<Integer, DList> lists;
    int cap;
    int min = -1;
    public LFUCache(int capacity) {
        cap = capacity;
        vals = new HashMap<>();
        lists = new HashMap<>();
		// make sure every count has a list
        lists.put(1, new DList());
    }
    
    public int get(int key) {
        if(!vals.containsKey(key))
            return -1;
        updateFreq(key);
        return vals.get(key).val;
    }

	private void updateFreq(int key){
		
		ListNode node = vals.get(key);
		// 更新对应 key 的 frequency
		int count = node.count;
        node.count ++;
        node = lists.get(count).rmNode(node);
		// 如果当前缓存为最小 freq, 且无重复值
	    // 更新最小 freq
        if(count==min && lists.get(count).size()==0)
            min ++;
		// 
        if(!lists.containsKey(count+1))
            lists.put(count+1, new DList());
        lists.get(count+1).addToTail(node);
	}
    
    public void put(int key, int value) {
        if(cap<=0)
            return;
        if(vals.containsKey(key)) {
            vals.get(key).val = value;
            updateFreq(key);
            return;
        } 
        if(vals.size() >= cap) {
            ListNode evit = lists.get(min).removeHead();
			if(vals.containsKey(evit.key)){
               vals.remove(evit.key);
			}
          
        }
		ListNode node = new ListNode(key, value);
		node.count = 1;
        vals.put(key, node);
        
        min = 1;
        lists.get(1).addToTail(node);
    }
	public static void main(String[] args){
		LFUCache obj = new LFUCache(10);
        obj.put(10,13);
		obj.put(3,17);
		obj.put(6,11);
		obj.put(10,5);
		obj.put(9,10);
		System.out.println(obj.get(13));
		obj.put(2,19);
		System.out.println(obj.get(2));
		System.out.println(obj.get(3));
		obj.put(5,25);
		System.out.println(obj.get(8));
		obj.put(9,22);
		obj.put(5,5);
		obj.put(1,30);
		System.out.println(obj.get(11));
		obj.put(9,12);
		System.out.println(obj.get(7));
		System.out.println(obj.get(5));
		System.out.println(obj.get(8));
		System.out.println(obj.get(9));
		obj.put(4,30);
		obj.put(9,3);
		System.out.println(obj.get(9));
		System.out.println(obj.get(10));
		System.out.println(obj.get(10));
		obj.put(6,14);
		obj.put(3,1);
		System.out.println(obj.get(3));
		obj.put(10,11);
		System.out.println(obj.get(8));
		obj.put(2,14);
		System.out.println(obj.get(1));
		System.out.println(obj.get(5));
		System.out.println(obj.get(4));
		obj.put(11,4);
		obj.put(12,24);
		obj.put(5,18);
		System.out.println(obj.get(13));
		obj.put(7,23);
		System.out.println(obj.get(8));
		System.out.println(obj.get(12));
		obj.put(3,27);
		obj.put(2,12);
		System.out.println(obj.get(5));
		obj.put(2,9);
		obj.put(13,4);
		obj.put(8,18);
		obj.put(1,7);
		System.out.println(obj.get(6));
		obj.put(9,29);
		obj.put(8,21);
		System.out.println(obj.get(5));
		obj.put(6,30);
		obj.put(1,12);
		System.out.println(obj.get(10));
		obj.put(4,15);
		obj.put(7,22);
		obj.put(11,26);
		obj.put(8,17);
		obj.put(9,29);
		System.out.println(obj.get(5));
		obj.put(3,4);
		obj.put(11,30);
		System.out.println(obj.get(12));
		obj.put(4,29);
		System.out.println(obj.get(3));
		System.out.println(obj.get(9));
		System.out.println(obj.get(6));
		obj.put(3,4);
		System.out.println(obj.get(1));
		System.out.println(obj.get(10));
		obj.put(3,29);
		obj.put(10,28);
		obj.put(1,20);
		obj.put(11,13);
		System.out.println(obj.get(3));
		obj.put(3,12);
		obj.put(3,8);
		obj.put(10,9);
		obj.put(3,26);
		System.out.println(obj.get(8));
		System.out.println(obj.get(7));
		System.out.println(obj.get(5));
		obj.put(13,17);
		obj.put(2,27);
		obj.put(11,15);
		System.out.println(obj.get(12));
		obj.put(9,19);
		obj.put(2,15);
		obj.put(3,16);
		System.out.println(obj.get(1));
		obj.put(12,17);
		obj.put(9,1);
		obj.put(6,19);
		System.out.println(obj.get(4));
		System.out.println(obj.get(5));
		System.out.println(obj.get(5));
		obj.put(8,1);
		obj.put(11,7);
		obj.put(5,2);
		obj.put(9,28);
		System.out.println(obj.get(1));
		obj.put(2,2);
		obj.put(7,4);
		obj.put(4,22);
		obj.put(7,24);
		obj.put(9,26);
		obj.put(13,28);
		obj.put(11,26);


	}
}

