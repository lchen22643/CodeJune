import java.util.*;

class ListNode {
	int key, value;
	ListNode next;
	long timestamp;

	public ListNode(int key, int value) {
		this.key = key;
		this.value = value;
		this.next = null;
		timestamp = System.currentTimeMillis();
	}
}

public class LRUCache {
	/*
	 * @param capacity: An integer
	 * 
	 */

	ListNode dummy, tail;

	int capacity, size;
	Map<Integer, ListNode> keytoPrev;

	// expire time in millsencond
	long expireTime = 1000;
	int n = 2;

	public LRUCache(int capacity) {
		this.dummy = new ListNode(0, 0);
		this.tail = dummy;

		this.capacity = capacity;
		this.keytoPrev = new HashMap<>();
	}

	public void moveToTail(int key) {
		ListNode prev = keytoPrev.get(key);
		ListNode cur = prev.next;
		cur.timestamp = System.currentTimeMillis();
		if (tail == cur) {
			return;
		}
		prev.next = prev.next.next;
		if (prev.next != null) {
			keytoPrev.put(prev.next.key, prev);
		}
		tail.next = cur;

		keytoPrev.put(key, tail);

		tail = cur;
	}

	public int get(int key) {
		if (!keytoPrev.containsKey(key)) {
			return -1;
		}
		moveToTail(key);
		return tail.value;
	}

	/*
	 * @param key: An integer
	 * 
	 * @param value: An integer
	 * 
	 * @return: nothing
	 */
	public void set(int key, int value) {

		if (keytoPrev.containsKey(key)) {
			ListNode prev = keytoPrev.get(key);
			ListNode cur = prev.next;
			cur.value = value;

			moveToTail(key);
			return;
		}
		if (size < capacity) {

			ListNode cur = new ListNode(key, value);
			tail.next = cur;
			keytoPrev.put(key, tail);
			tail = cur;
			size = keytoPrev.size();
			return;
		}
		ListNode first = dummy.next;
		keytoPrev.remove(first.key);
		first.key = key;
		first.value = value;
		keytoPrev.put(key, dummy);
		moveToTail(key);
		clearCache();
	}

	public void clearCache() {
		// check time of head node
		long currentTime = System.currentTimeMillis();
		while (dummy.next != null) {
			ListNode node = dummy.next;
			if (currentTime - node.timestamp > expireTime) {
				keytoPrev.remove(node.key);
				dummy.next = dummy.next.next;
				if (dummy.next != null) {
					keytoPrev.put(dummy.next.key, dummy);
				}
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
