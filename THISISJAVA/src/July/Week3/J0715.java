package July.Week3;

/* // 오버로딩 283p~
// 오버로딩 : 한 클래스 내에 같은 이름의 메서드를 여러개 정의하는 것
// 예제 6-21
class OverloadingTest {
    public static void main(String[] args) {
        MyMath3 mm = new MyMath3();
        System.out.println("mm.add( 3,  3) 결과: "+mm.add(3,3));
        System.out.println("mm.add(3L,  3) 결과: "+mm.add(3L,3));
        System.out.println("mm.add( 3, 3L) 결과: "+mm.add(3,3L));
        System.out.println("mm.add(3L, 3L) 결과: "+mm.add(3L,3L));
        
        int[] a = {100, 200, 300};
        System.out.println("mm.add(a) 결과: "+mm.add(a));
    }
}
class MyMath3 {
    int add(int a, int b){
        System.out.print("int add(int a, int b) - ");
        return a+b;
    }
    long add(int a, long b){
        System.out.print("long add(int a, long b) - ");
        return a+b;
    }
    long add(long a, int b){
        System.out.print("long add(long a, long b) - ");
        return a+b;
    }
    long add(long a, long b){
        System.out.print("long add(long a, long b) - ");
        return a+b;
    }

    int add(int[] a){
        System.out.print("int add(int[] a) - ");
        int result = 0;
        for(int i=0; i<a.length; i++) result += a[i];
        return result;
    }
}

// 가변인자와 오버로딩 287p~
// 예제 6-22
class VarArgsEX{
    public static void main(String[] args) {
        String[] strArr = {"100","200","300"};

        System.out.println(concatenate("", "100","200","300"));
        System.out.println(concatenate("-", strArr));
        System.out.println(concatenate(",", new String[]{"1","2","3"}));
     // System.out.println(concatenate("-", {"100","200","300"})); // ERROR

    }
                                        // 가변인수는 항상 마지막에
    static String concatenate(String delim, String... args){
        String result = "";

        for(String str : args) {
            result += str + delim;
        }

        return result;
    }
}
*/

/* // 생성자 291p~
// 예제 6-23
class Data1 {
    int value;
}
class Data2 {
    int value;

    Data2 (int x){ // 매개변수가 있는 생성자
        value = x;
    }
}

class ConstructorTest {
    public static void main(String[] args) {
        Data1 d1 = new Data1();
     // Data2 d2 = new Data2(); // ERROR. 
        // Data2(int x)가 정의되어 있으므로 기본 생성자 추가 X
        Data2 d2 = new Data2(10);
    }
}

// 매개변수가 있는 생성자 294p~
// 예제 6-24
class Car {
    String color;
    String gearType;
    int door;

    Car() {} // 생성자
    Car(String c, String g, int d){ // 생성자
        color = c;
        gearType = g;
        door = d;
    }
}
class CarTest{
    public static void main(String[] args) {
        Car c1 = new Car();
        c1.color = "White";
        c1.gearType = "auto";
        c1.door = 4;

        Car c2 = new Car("Black", "auto", 2);

        System.out.printf("c1의 color=%s, gearType=%s, door=%d\n",c1.color,c1.gearType,c1.door);
        System.out.printf("c2의 color=%s, gearType=%s, door=%d\n",c2.color,c2.gearType,c2.door);
    }
}

// 생성자에서 다른 생성자 호출 295p~ 
// 예제 6-25
class Car2{
    String color; 
    String gearType;
    int door;

    Car2(){
        this("white","auto", 4); // Car2(String color, String gearType, int door)를 호출
    }

    // Car(String color){
    //     door = 5;
    //     Car(color,"auto",4); // ERROR1. 생성자에서 다른 생성자를 호출할때는 반드시 첫줄
    // }                        // ERROR2. 클래스 이름 대신 this를 사용

    Car2(String color){
        this(color,"auto",4);
    }

    Car2(String color, String gearType, int door){
        this.color = color;
        this.gearType = gearType;
        this.door = door;
    }
}

class CarTest2{
    public static void main(String[] args) {
        Car2 c1 = new Car2(); // 126 Line
        Car2 c2 = new Car2("blue"); // 135 Line

        System.out.printf("c1의 color=%s, gearType=%s, door=%d\n",c1.color,c1.gearType,c1.door);
        System.out.printf("c2의 color=%s, gearType=%s, door=%d\n",c2.color,c2.gearType,c2.door);
    }
}

// 생성자를 이용한 인스턴스의 복사 298p~
// 예제 6-26
class Car3{
    String color; 
    String gearType;
    int door;

    Car3(){
        this("white","auto", 4); 
    }
    
    Car3(Car3 c){ // 인스턴스 복사를 위한 생성자
        color = c.color;
        gearType = c.gearType;
        door = c.door;
    }

    Car3(String color, String gearType, int door){
        this.color = color;
        this.gearType = gearType;
        this.door = door;
    }
}
class CarTest3{
    public static void main(String[] args) {
        Car3 c1 = new Car3();
        Car3 c2 = new Car3(c1); // c1의 복사본 c2를 생성 167 Line

        System.out.printf("c1의 color=%s, gearType=%s, door=%d\n",c1.color,c1.gearType,c1.door);
        System.out.printf("c2의 color=%s, gearType=%s, door=%d\n",c2.color,c2.gearType,c2.door);
        
        c1.door = 100; // c1의 인스턴스변수 door의 값을 변경
        System.out.println("c1.door = 100; 수행 후");

        System.out.printf("c1의 color=%s, gearType=%s, door=%d\n",c1.color,c1.gearType,c1.door);
        System.out.printf("c2의 color=%s, gearType=%s, door=%d\n",c2.color,c2.gearType,c2.door);
        // c2는 c1을 복사하여 생성되었으나 독립적인 메모리 공간에 존재 -> c1을 변경해도 c2는 영향 X
    }
}
*/

// 변수의 초기화 300p~
class InitTest{
    int x; // 인스턴스 변수
    int y = x; // 인스턴스 변수

    void method1(){
        int i; // 지역변수
     // int j = i; // ERROR. 지역변수는 초기화 필수
    }
}

// 명시적 초기화 301p~
// 명시적 초기화 : 변수를 선언과 동시에 초기화 하는 것
class Car {
    int door = 4;            // 기본형 변수의 명시적 초기화
  //Engine e = new Engine(); // 참조형 변수의 명시적 초기화
}

// 초기화 블럭 302p~
// 초기화 블럭 -> 클래스 초기화 블럭 / 인스턴스 초기화 블럭
class Car2{
    String color; 
    String gearType;
    int serialNo;
    int count = 0;
    { // 인스턴스 초기화 블럭
        count++;
        serialNo = count;
    }
    Car2(){
        color = "White";
        gearType = "Auto";
    }
    Car2(String color, String gearType){
        this.color = color;
        this.gearType = gearType;
    }
}
// 예제 6-27
class BlockTest{
    static{
        System.out.println("static { } : 클래스 초기화 블럭"); // 1
    }

    {
        System.out.println("{ } : 인스턴스 초기화 블럭"); // 3.1 / 5.1
    }

    public BlockTest(){
        System.out.println("생성자"); // 3.2 / 5.2
    }

    public static void main(String[] args) {
        System.out.println("BlockTest bt = new BlockTest(); "); // 2
        BlockTest bt = new BlockTest(); // 3

        System.out.println("BlockTest bt2 = new BlockTest(); "); // 4
        BlockTest bt2 = new BlockTest(); // 5
    }
}
// 예제 6-28
class StaticBlockTest{
    static int[] arr = new int[10]; // 클래스 변수 배열의 명시적 초기화
    
    static{ // 복잡한 초기화 작업 -> 클래스 초기화 블럭 사용
        for(int i=0; i<arr.length; i++){
            // 1과 10 사이의 난수 저장
            arr[i] = (int)(Math.random()*10) + 1;
        }
    }

    public static void main(String[] args) {
        for(int i=0; i<arr.length; i++){
            System.out.printf("arr[%d] : %d\n",i,arr[i]);
        }
    }
} 