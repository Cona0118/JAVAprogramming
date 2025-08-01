package July.Week4;

import java.util.Objects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import java.awt.Toolkit;

/* // 어노테이션 558p~
@Target({ElementType.METHOD}) // 적용대상 : METHOD
@Retention(RetentionPolicy.RUNTIME) // 유지정책 : RUNTIME
@interface PrintAnnotation{
    String value() default "-";
    int number() default 15;
}

class Service{
    @PrintAnnotation
    public void method1(){
        System.out.println("살행내용1");
    }

    @PrintAnnotation("*")
    public void method2(){
        System.out.println("실행내용2");
    }

    @PrintAnnotation(value = "#", number = 10)
    public void method3(){
        System.out.println("실행내용3");
    }
}

class PrintAnnotationEx{
    public static void main(String[] args) {
        Method[] declarMethods = Service.class.getDeclaredMethods(); // Service 클래스 메소드 읽기
        for(Method method: declarMethods) {
            // PrintAnnotation 얻기
            PrintAnnotation printAnnotation = method.getAnnotation(PrintAnnotation.class); // 지정한 어노테이션이 적용되어있으면 리턴, 아니면 null

            // 설정 정보를 이용해서 선을 출력함
            printLine(printAnnotation);
            // 메소드 호출
            // method.invoke(new Service());
            // 설정 정보를 이용해서 선을 출력
            printLine(printAnnotation);

        }
    }

    public static void printLine(PrintAnnotation printAnnotation){
        if(printAnnotation != null) {
            int number = printAnnotation.number();
            for(int i=0; i<number; i++){
                String value = printAnnotation.value();
                System.out.print(value);
            }
            System.out.println();
        }
    }
}
*/

/* // Chapter 13 제네릭 572p~ 
class Box <T> {
    public T content;
}

class genericTest {
    public static void main(String[] args) {
        Box<String> box = new Box<String>();
        box.content = "Hello world";
        String str = box.content;
        System.out.println(str);

        Box<Integer> box2 = new Box<>(); 
        box2.content = 123;
        int value = box2.content;
        System.out.println(value);
    }
}

class Product<K,M> {
    private K kind;
    private M model;

    public K getKind() { return this.kind; }
    public M getModel() { return this.model; }
    public void setKind(K kind) { this.kind = kind; }
    public void setModel(M model) { this.model = model; }
}
class Car {}
class Tv {}

class GenericEX{
    public static void main(String[] args) {
        // K = Tv , M = String
        Product<Tv,String> product1 = new Product();

        product1.setKind(new Tv()); // setKind(K kind) 
        product1.setModel("스마트 Tv"); // setModel(M model)

        // Getter 리턴값 -> Tv, String
        Tv tv = product1.getKind();
        String tvModel = product1.getModel();

    }
}

interface Rentable<P>{ // 타입 파라미터 P 정의
    P rent(); // 타입 파라미터 P를 리턴 타입으로 사용
}

class Home {
    public void turnOnLight(){
        System.out.println("전등 ON");
    }
}

// 제한된 타입 파라미터
class GenericExam{
    public static <T extends Number> boolean compare(T t1, T t2){
        System.out.println("compare("+t1.getClass().getSimpleName() + ", "+ t2.getClass().getSimpleName()+")");

        double v1 = t1.doubleValue();
        double v2 = t2.doubleValue();

        return (v1 == v2);
    }

    public static void main(String[] args) {
        boolean result = compare(10, 20);
        System.out.println(result);
        System.out.println();

        boolean result2 = compare(4.5, 4.5);
        System.out.println(result2);
    }
}

// 와일드카드 타입 파라미터
class Person{}
class Worker extends Person{}
class Student extends Person{}
class HighStudent extends Student{}
class MiddleStudent extends Student{}

class Applicant<T>{
    public T kind;

    public Applicant(T kind){
        this.kind = kind;
    }
}

class Course{
    public static void registerCourse1(Applicant<?> applicant){
        System.out.println(applicant.kind.getClass().getSimpleName() +" course1 등록");
    }

    public static void registerCourse2(Applicant<? extends Student> applicant){
        System.out.println(applicant.kind.getClass().getSimpleName() +" course2 등록");
    }

    public static void registerCourse3(Applicant<? super Worker> applicant){
        System.out.println(applicant.kind.getClass().getSimpleName() +" course3 등록");
    }
}

class GeEX{
    public static void main(String[] args) {
        Course.registerCourse1(new Applicant<Person>(new Person()));

        Course.registerCourse2(new Applicant<Student>(new Student()));

        Course.registerCourse3(new Applicant<Worker>(new Worker()));
    }
}

// 제네릭 메소드
class Box3<T>{
    private T t;
    
    public T get(){
        return t;
    }

    public void set(T t){
        this.t = t;
    }
}

class GenericMethod{
    public static <T> Box3<T> boxing(T t){
        Box3<T> box3 = new Box3<T>();
        box3.set(t);
        return box3;
    }

    public static void main(String[] args) {
        Box3<Integer> box1 = boxing(100);
        int intValue = box1.get();
        System.out.println(intValue);

        Box3<String> box2 = boxing("홍길동");
        String strValue = box2.get();
        System.out.println(strValue);
    }
}
*/

/* // 확인문제 589p
// 3번
class ContainerExample{
    public static void main(String[] args) {
        Container<String,String> container1 = new Container<>();
        container1.set("홍길동","도적");
        System.out.println(container1.getKey()+container1.getValue());

        Container<String,Integer> container2 = new Container<>();
        container2.set("홍길동",35);
        System.out.println(container2.getKey()+container2.getValue());
    }
}

class Container<K,V> {
    private K name;
    private V value;

    public K getKey() { return this.name; }
    public V getValue() { return this.value; }
    public void set(K name, V value){ 
        this.name = name;
        this.value = value; 
    }

}

// 4번
class UtilExample{
    public static void main(String[] args) {
        Pair<String,Integer> pair = new Pair<>("홍길동", 36);
        Integer age = Util.getValue(pair,"홍길동");
        System.out.println(age);

        ChildPair<String,Integer> childPair = new ChildPair<>("홍삼원", 20);
        Integer childAge = Util.getValue(childPair,"홍삼순");
        System.out.println(childAge);

        // OtherPair 은 Pair를 상속하지 않아 에러 발생
    }
}

class Pair<K,V>{
    private K key;
    private V value;

    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }
    public K getKey() {return key;}
    public V getValue() {return value;}
}

class ChildPair<K,V> extends Pair<K,V>{
    public ChildPair(K k, V v) { super(k, v); }
}

class OtherPair<K,V>{
    private K key;
    private V value;

    public OtherPair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {return key;}
    public V getValue() {return value;}
}

class Util{
    public static <K,V> V getValue(Pair<?extends K,V> p, K k){
        if (Objects.equals(p.getKey(), k)) return p.getValue();
        return null;
    }
}
*/

// 멀티스레드 594p~
class BeepPrintExample{
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit(); // Toolkit 객체
        for(int i=0;i<5;i++){
            toolkit.beep();
            try{Thread.sleep(500);} catch(Exception e){}
        }

    }
}

