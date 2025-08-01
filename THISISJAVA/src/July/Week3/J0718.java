package July.Week3;

/* // instanceof : 형변환이 가능한지 출력 362p~
// 예제 7-17
class InstanceofTest{
    public static void main(String[] args) {
        FireEngine fe = new FireEngine();
    
        if(fe instanceof FireEngine){
            System.out.println("this is a FireEngine instance");
        }

        if (fe instanceof Car){
            System.out.println("This is a Car instance");
        }

        if (fe instanceof Object){
            System.out.println("This is Object instance");
        }

        System.out.println(fe.getClass().getName()); // 클래스 이름 출력
    }
}

class Car{}
class FireEngine extends Car{}

*/

/* // 참조변수와 인스턴스의 연결 364p~
// 예제 7-18
class BindingTest{
    public static void main(String[] args) {
        Parent p = new Child();
        Child c = new Child();
        // 타입은 다르나 두 변수 모두 Chile 인스턴스를 참조

        System.out.println(p.x); // 100
        p.method(); // Child Method

        System.out.println(c.x); // 200
        c.method(); // Child Method 
        
    }
}
class Parent {
    int x = 100;
    void method(){
        System.out.println("Parent Method");
    }
}
class Child extends Parent {
    int x = 200;
    void method(){
        System.out.println("Child Method");
    }
}
// 예제 7-20
class BindingTest3{
    public static void main(String[] args) {
        Parent2 p = new Child2();
        Child2 c = new Child2();

        System.out.println("p.x = "+p.x);
        p.method();
        System.out.println();

        System.out.println("c.x = "+c.x);
        c.method();
    }
}
class Parent2{
    int x = 100;

    void method(){
        System.out.println("Parent Method");
    }
}
class Child2 extends Parent2{
    int x = 200;
    void method(){
        System.out.println("x="+x);
        System.out.println("super.x="+super.x);
        System.out.println("this.x="+this.x);
    }
}
*/

// 매개변수의 다형성 367p~
// 예제 7-21
class Product{
    int price;
    int bonusPoint;

    Product(int price){
        this.price = price;
        bonusPoint = (int)(price/10.0);
    }
}
class Tv extends Product{
    Tv(){
        // 조상 클래스의 Product(int price) 호출
        super(100);
    }
    public String toString() { return "Tv"; }
}
class Computer extends Product {
    Computer() { super(200); }
    public String toString() { return "Computer"; }
}

class Buyer {
    int money = 1000;
    int bonusPoint = 0;

    void buy(Product p){
        if(money < p.price){
            System.out.println("잔액이 부족합니다");
            return;
        }
        money -= p.price;
        bonusPoint += p.bonusPoint;
        System.out.println(p+"을(를) 구입하셨습니다.");
    }
}
class PolyArgumentTest{
    public static void main(String[] args) {
        Buyer b = new Buyer();

        b.buy(new Tv());
        b.buy(new Computer());

        System.out.println(b.money);
        System.out.println(b.bonusPoint);
    }
}

// 예제 7-22

class Product2{
    int price;
    int bonusPoint;
    Product2(int price){
        this.price = price;
        bonusPoint = (int)(price/10.0);
    }
    Product2(){} // 기본생성자
}
class Tv2 extends Product2{
    Tv2() { super(100); }
    public String toString() { return "Tv2"; }
}
class Computer2 extends Product2{
    Computer2() { super(200); }
    public String toString() { return "Computer2"; }
}
class Audio2 extends Product2{
    Audio2() { super(50); }
    public String toString() { return "Audio2"; }
}

class Buyer2 {
    int money = 1000;
    int bonusPoint = 0;
    Product2[] item = new Product2[10]; // 구매 제품 저장 배열
    int i = 0;

    void buy2(Product2 p){
        if(money < p.price){
            System.out.println("잔액이 부족합니다");
            return;
        }
        money -= p.price;
        bonusPoint += p.bonusPoint;
        item[i++] = p;
        System.out.println(p+"을(를) 구입하셨습니다.");
    }

    void sumnary(){
        int sum = 0;
        String itemList = "";
        for(int i=0; i<item.length;i++){
            if(item[i]==null) break;
            sum+=item[i].price;
            itemList += item[i] +", ";
        }
        System.out.println("총금액 : "+sum);
        System.out.println("제품 목록 : "+itemList);
    }
}
class PolyArgumentTest2{
    public static void main(String[] args) {
        Buyer2 b = new Buyer2();

        b.buy2(new Tv2());
        b.buy2(new Computer2());
        b.buy2(new Audio2());

        b.sumnary();
    }
}
