class Animal {}
class Cat extends Animal {}


public class Test {
public static void testChildToParent(Animal a){}
public static void testParentChild(Cat a){}
public static Animal TestReturn() {
       Cat cat = new Cat();
       return cat;
}

public static void main(String[] args) {
       Cat a = new Cat();
       Animal animal = new Animal();
       testChildToParent(a);
//       testParentChild(animal);



}

}
