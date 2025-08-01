package July.Week3;

/* //  상속관계/포함관계 310p~
// 예제 7-2
class DrawShape {
    public static void main(String[] args) {
        Point[] p = {   new Point(100,100), new Point(140,50), new Point(200,100)   };
        Triangle t = new Triangle(p);

        Circle c = new Circle(new Point(150,150), 50);

        t.draw();
        c.draw();
    }
}
class Shape {
    String color = "black";
    void draw(){
        System.out.println(color);
    }
}
class Point {
    int x;
    int y;
    
    Point (int x, int y){
        this.x = x;
        this.y = y;
    }
    Point(){
        this(0,0);
    }
    String getXY(){
        return "("+x+","+y+")";
    }
}

class Circle extends Shape { // Shape의 자손 클래스 Circle
    Point center; 
    int r;
    
    Circle(){
        this(new Point(0,0),100); 
    }
    Circle(Point center, int r){
        this.center = center;
        this.r = r;
    }

    void draw(){ // 조상 클래스와 겹치면 자손 클래스 호출 -> 오버라이딩
        System.out.printf("[center = (%d,%d), r = %d, color = %s]\n",center.x,center.y,r,color);
    }
}

class Triangle extends Shape {
    Point[] p = new Point[3];

    Triangle (Point[] p){
        this.p = p;
    }

    void draw(){
        System.out.printf("[p1=%s, p2=%s, p3=%s, color=%s]\n",p[0].getXY(),p[1].getXY(),p[2].getXY(),color);
    }
}

// 예제 7-3
class DeckTest{
    public static void main(String[] args) {
        Deck d = new Deck(); 
        Card c = d.pick(0);
        System.out.println(c); // c = c.toString()

        d.shuffle();
        c=d.pick(0);
        System.out.println(c);
        
    }
}
class Deck {
    final int CARD_NUM = 52; // 카드의 총 갯수
    Card cardArr[] = new Card[CARD_NUM]; // Card 객체 배열

    Deck() { // Deck 카드 초기화
        int i = 0;
        for(int k=Card.KIND_MAX; k>0; k--){
            for(int n=0; n<Card.NUM_MAX; n++){
                cardArr[i++] = new Card(k, n+1);
            }
        }
    }

    Card pick(int index){
        return cardArr[index];
    }

    Card pick(){
        int index = (int)(Math.random()*CARD_NUM);
        return cardArr[index];
    }

    void shuffle(){
        for(int i=0; i<cardArr.length; i++){
            int r = (int)(Math.random()*CARD_NUM);
            Card temp = cardArr[i];
            cardArr[i] = cardArr[r];
            cardArr[r] = temp;
        }
    }
}

class Card {
    static final int KIND_MAX = 4; // 카드 무늬의 수
    static final int NUM_MAX = 13; // 무늬별 카드의 수

    static final int SPADE = 4; 
    static final int DIAMOND = 3; 
    static final int HEART = 2; 
    static final int CLOVER = 1; 
    int kind;
    int number;

    Card(){
        this(SPADE,1);
    }
    
    Card(int kind, int number){
        this.kind = kind;
        this.number = number;
    }

    public String toString(){
        String[] kinds = {"","CLOVER","HEART","DIAMOND","SPADE"};
        String numbers = "0123456789XJQK"; 

        return "kind : "+ kinds[this.kind]+ ", number : "+ numbers.charAt(this.number);
    }

}   
*/

/* // 오버라이딩 327p~
class Point {
    int x;
    int y;

    String getLocation(){
        return "x:"+x+",y:"+y;
    }
}
class Point3D extends Point{
    int z;
    String getLocation(){ // 오버라이딩 
        return "x:"+x+",y:"+y+",z:"+z; 
    }
}
*/

/* // super 330p~
// 예제 7-6
class SuperTest{
    public static void main(String[] args) {
        Child c = new Child();
        c.method();
    }
}
class Parent {
    int x = 10;
}
class Child extends Parent{
    int x = 20;
    void method(){
        System.out.println("x = "+ x); // 20
        System.out.println("this.x = "+this.x); // 20 : this >> 자기 자신의 멤버변수
        System.out.println("super.x = "+super.x); // 10 : super >> 조상 클래스에 선언된 멤버 변수 참조
    }
}
// 예제 7-8
class PointTest{
    public static void main(String[] args) {
        Point3D p3 = new Point3D();
        System.out.println(p3.x); // 100
        System.out.println(p3.y); // 200
        System.out.println(p3.z); // 300
    }
}
class Point {
    // 생성자 첫줄에서 다른 생성자를 호출하지 않았기 깨문에
    // 컴파일러가 super();를 삽입 -> Point의 조상인 Object 클래스의 기본 생성자 Object()

    int x = 10;
    int y = 20;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class Point3D extends Point{
    int z = 30;
    
    Point3D(){
        this(100,200,300); 
    }
    Point3D(int x, int y, int z){
        super(x, y);
        this.z = z;
    }
}
*/

// 다형성 354p~
// 예제 7-15
class CastingTest1 {
    public static void main(String[] args) {
        Car car = null;
        FireEngine fe = new FireEngine();
        FireEngine fe2 = null;
        
        fe.water(); // water 
        
        car = fe; // car <- fe 업 캐스팅
     // car.water(); // ERROR. Car 타입의 참조 변수임으로 water()를 호출 불가
        
        fe2 = (FireEngine) car; // car -> fe 다운 캐스팅
        fe2.water(); // water
    }
}
class Car {
    String color;
    int door;

    void drive(){
        System.out.println("드라이브");
    }

    void stop(){
        System.out.println("스탑");
    }    
}
class FireEngine extends Car{
    void water(){
        System.out.println("water");
    }
}
// 예제 7-16
class CastingTest2{
    public static void main(String[] args) {
     // Car car = new Car();
        Car car = new FireEngine();
        Car car2 = null;
        FireEngine fe = null;

        car.drive();

        fe = (FireEngine)car;
        fe.drive();

        car2 = fe;
        car2.drive();
    }
}
