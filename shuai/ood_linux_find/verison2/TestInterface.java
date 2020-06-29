import java.util.*;
public class TestInterface {
       public static void testInterface(List<Integer> list){
       }
       public static List<Integer> testReturn(){
              ArrayList<Integer> list = new ArrayList<>();
	      return list;
       }
       public static void main(String[] args) {
              ArrayList<Integer> list = new ArrayList<>();
	      testInterface(list);
	      testInterface(new ArrayList<>());
	      testReturn();
       }

}
