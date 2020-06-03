import java.util.*;
class ListNode {
    public int key;
    public int val;
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

	public ListNode rmNode(ListNode node){
        if(node == null){
            return null;
        }
        ListNode pre = node.pre;
        ListNode next = node.next;
        if(pre != null){
            pre.next = next;
        } else {
            head = node.next;
        }
        if(next != null){
            next.pre = pre;
        } else {
            tail = node.pre;
        }
        node.pre = null;
        node.next = null;
        return node;
    }
    public void addToTail(ListNode node){
            if(this.tail == null){
                this.tail = node;
                this.head = node;
            } else {
                this.tail.next = node;
                node.pre = this.tail;
                this.tail = node;
            }
    }
    public ListNode removeHead(){
        if(this.head == null){
            return null;
        } else {
            ListNode node = this.head;
            this.head = this.head.next;
            if(this.head != null){
                this.head.pre = null;
            }
            return node;
        }
    }
    
}
public class LRUCache {
     Map<Integer, ListNode> dataMap;
     DList list;
     int capacity;

    public LRUCache(int capacity) {
         dataMap = new HashMap<>();
         list = new DList();
         this.capacity = capacity;

    }
    
    public int get(int key) {
        int res = -1;
        if(dataMap.containsKey(key)){
            ListNode node = dataMap.get(key);
            node = list.rmNode(node);
            list.addToTail(node);
            dataMap.put(key, node);
            res = node.val;
        }
        
        return res;

    }
    
    public void put(int key, int value) {
          ListNode node = null;
          if(dataMap.containsKey(key)) {
                node = dataMap.get(key);
                node.val = value;
                node = list.rmNode(node);
                
          } else {
                node = new ListNode(key ,value);
                
          }

          list.addToTail(node);
          
          dataMap.put(key, node);
          if(dataMap.size() > this.capacity){
               dataMap.remove(list.removeHead().key);
          }
          
          
    }
    
}