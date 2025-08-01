package July.Week3;
/* // 클래스/인스턴스 변수 247p~
// 예제 6-5 
class Card {
    String kind;
    int number;
    static int width = 100;
    static int height = 250;
}

class CardTest {
    public static void main(String[] args) {
        System.out.println(Card.width); // 100
        System.out.println(Card.height); // 250
        // 클래스 변수(static 변수)는 객체생성 없이 '클래스이름.클래스변수'로 직접 사용 가능

        Card c1 = new Card(); // 인스턴스 생성
        c1.kind = "HEART"; // 객체 생성, 인스턴스 변수 값 변경
        c1.number = 7;

        Card c2 = new Card();
        c2.kind = "SPADE";
        c2.number = 4;

        System.out.printf("c1 : %s %d / 크기 : %d X %d\n",c1.kind,c1.number,c1.width,c1.height);
        // c1 : HEART 7 / 크기 : 100 X 250
        System.out.printf("c2 : %s %d / 크기 : %d X %d\n",c2.kind,c2.number,c2.width,c2.height);
        // c2 : SPADE 4 / 크기 : 100 X 250

        // 클래스 변수의 값 변경
        c1.width = 50;
        c2.height = 85;

        System.out.printf("c1 : %s %d / 크기 : %d X %d\n",c1.kind,c1.number,c1.width,c1.height);
        // c1 : HEART 7 / 크기 : 50 X 85
        System.out.printf("c2 : %s %d / 크기 : %d X %d\n",c2.kind,c2.number,c2.width,c2.height);
        // c2 : SPADE 4 / 크기 : 50 X 85
        // Card 인스턴스인 c1과 c2 는 width와 height의 값을 공유하기 때문에 c1에서 변경하더라도 c2도 바뀐 값을 공유한다.
        System.out.println();

    }
}
*/

/* // JVM 메모리 구조 263p~
// 예제 6-8
// Stack : LIFO Last In First Out
class CallstackTest2 {
    public static void main(String[] args) {
        System.out.println("main 시작");
        firstMethod();
        System.out.println("main 종료");
    }

    static void firstMethod(){
        System.out.println("FirstMethod 시작");
        SecondMethod();
        System.out.println("FirstMethod 종료");
    }

    static void SecondMethod(){
        System.out.println("SecondMethod 시작");
        System.out.println("SecondMethod 종료");
    }
}
*/

/* // 기본형/참조형 매개변수 264p~
// 예제 6-9 기본형 매개변수
class Data {int x;}
class PrimitiveParamEx {
    public static void main(String[] args) {
        Data d = new Data();
        d.x = 10;
        System.out.println("main() : x = "+d.x); // 10

        change(d.x);
        System.out.println("After change(d.x)");
        System.out.println("main() : x = "+d.x); // 10
        // d.x의 값이 변경된 것이 아니라 change 메서드의 매개변수 x의 값이 변경된것
        // (Read Only)
    }

    static void change(int x){
        x = 1000;
        System.out.println("change() : x = "+x); // 1000
    }       
}
// 예제 6-10 참조형 매개변수
class ReferenceParamEx {
    public static void main(String[] args) {
        Data d = new Data();
        d.x = 10;
        System.out.println("main() : x = "+d.x); // 10

        change(d);
        System.out.println("After change(d)");
        System.out.println("main() : x = "+d.x); // 1000
        // change 메서드의 매개변수가 값이 아닌 '값이 저장된 주소'를 넘겨주었기 때문에
        // 값을 읽어 오는 것 뿐 아니라 변경도 가능하다. (Read & Write)
    }

    static void change(Data d){
        d.x = 1000;
        System.out.println("change() : x = "+ d.x); // 1000
    }       
}
// 예제 6-11
class ReferenceParamEx2 {
    public static void main(String[] args) {
        int[] x = {10}; // 크기가 1인 배열 x[0] = 10;
        System.out.println("main() : x = "+x[0]); // 10

        change(x);
        System.out.println("After change(x)");
        System.out.println("main() : x = "+x[0]); // 1000
        // 배열도 객체와 같이 참조 변수를 통해 데이터가 저장된 공간에 접근
        // 따라서 변수 x도 int배열타입의 참조 변수 (Read & Write)
    }

    static void change(int[] x){
        x[0] = 1000;
        System.out.println("change() : x = "+ x[0]); // 1000
    }       
}
// 예제 6-13
class ReturnTest {
    public static void main(String[] args) {
        ReturnTest r = new ReturnTest();

        int result = r.add(3,5);
        System.out.println(result); // 8

        int[] result2 = {0};
        r.add(3,5,result2);
        System.out.println(result2[0]); // 8
    }
    
    int add(int a, int b)   {return a+b;}

    void add(int a, int b, int[] result)    {result[0] = a+b;}
    // 매개변수로 넘겨받은 배열에 연산결과를 저장 -> return(반환값) 없이 메서드의 실행결과를 얻음
}
*/

/* // 참조형 반환타입 268p~
// 예제 6-14
class Data {int x;}
class ReferenceReturnEX{
    public static void main(String[] args) {
        Data d = new Data();
        d.x = 10;

        Data d2 = copy(d); // 반환된 tmp를 d2에 저장
        System.out.println(d.x);  // 10
        System.out.println(d2.x); // 10
    }

    static Data copy(Data d){
        Data tmp = new Data(); // 새로운 객체 tmp 생성
        tmp.x = d.x; // tmp.x에 d.x 복사
        return tmp; // tmp 객체의 주소 반환
    }
}
*/

/* // 재귀호출 270p~
// 예제 6-15
class FactorialTest{
    public static void main(String[] args) {
        int result = factorial(4); // 4 * 3 * 2 * 1 
        System.out.println(result); // 24 
    }
    static int factorial(int n){
        int result = 0;
        if (n ==1) result = 1;
        else result = n * factorial(n-1);
        return result;
    }
}
*/

/* // 클래스/인스턴스 매서드 277p~
// 예제 6-19
class MyMath2 {
    long a, b;
    // static X -> 인스턴스 메서드
    long add() {return a+b;} // a,b 는 인스턴스 변수

    // 인스턴스 변수와 관계없이 매개변수 만으로 작업 가능
    static long sub(long a, long b) {return a-b;} // a, b는 지역변수
}
class MyMathTest2 {
    public static void main(String[] args) {
        // 클래스메서드 호출 (인스턴스 생성 없이 가능)
        System.out.println(MyMath2.sub(200L,100L));

        MyMath2 mm = new MyMath2(); // 인스턴스 생성
        mm.a = 200L;
        mm.b = 100L;
        // 인스턴스메서드는 객체 생성후에만 호출 가능
        System.out.println(mm.add()); 
    }
}
*/

/* // 클래스 멤버와 인스턴스 멤버간의 참조와 호출 280p~
// 예제 6-20
class MemberCall{
    int iv = 10;
    static int cv = 20;
    
    int iv2 = cv;
//  static int cv2 = iv; // ERROR. 클래스 변수는 인스턴스 변수 사용 불가
    // 클래스 변수 >> class 로딩 시 로딩됨
    // 인스턴스 변수 >> 인스턴스 생성 후 로딩됨
    static int cv2 = new MemberCall().iv; // 따라서 객체를 생성해야 사용 가능
    // MemberCall c = new MemberCall();
    // static int cv3 = c.iv; // ERROR

    static void staticMethod1(){
        System.out.println(cv);
  //    System.out.println(iv); // ERROR. 클래스 메서드에서 인스턴스 변수 사용불가
        MemberCall c = new MemberCall();
        System.out.println(c.iv); // 객체 생성후 인스턴스 변수 참조 가능
    }
    void instanceMethod1(){
        System.out.println(cv);
        System.out.println(iv); // 인스턴스메서드에서는 인스턴스변수 바로 사용가능
    }

    static void staticMethod2(){
        staticMethod1();
  //    instanceMethod1(); // ERROR. 클래스 메서드에서는 인스턴스 메서드 호출 불가
        MemberCall c = new MemberCall();
        c.instanceMethod1(); // 인스턴스 생성 후 호출 가능
    }
    void instanceMethod2(){
        staticMethod1();
        instanceMethod1();
    }

}

*/
