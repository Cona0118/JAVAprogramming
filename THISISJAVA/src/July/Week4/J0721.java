package July.Week4;

import javax.security.sasl.Sasl;

// 자바의 정석
/* // 연습문제 7-1,2
class SutdaDeck {
    final int CARD_NUM = 20;
    SutdaCard[] cards = new SutdaCard[CARD_NUM];

    SutdaDeck(){
        for(int i=0; i<CARD_NUM; i++){
            int num = i%10+1;
            boolean isKwang = (i<10)&&(num==1||num==3||num==8);
            cards[i] = new SutdaCard(num,isKwang);
        }
    }

    void shuffle(){
        for(int i=0; i<cards.length; i++){
            int rand = (int)(Math.random()*cards.length);
            SutdaCard tmp = cards[i];
            cards[i] = cards[rand];
            cards[rand] = tmp;
        }
    }

    SutdaCard pick(int n){
        if(n<0||n>=CARD_NUM) //index의 유효성을 검사한다
            return null;
        return cards[n];
    }

    SutdaCard pick(){
        return cards[(int)(Math.random()*cards.length)];
    }

}

class SutdaCard{
    int num;
    boolean isKwang;

    SutdaCard(){
        this(1,true);
    }

    SutdaCard(int n,boolean K){
        num = n;
        isKwang = K;
    }

    public String toString(){
        return num + (isKwang ? "K":"");
    }
}

class Exercise7_1{
    public static void main(String[] args) {
        SutdaDeck deck = new SutdaDeck();

        for(int i=0; i<deck.cards.length; i++){
            System.out.print(deck.cards[i]+", ");
        }
    }
}

class Exercise7_2{
    public static void main(String[] args) {
        SutdaDeck deck = new SutdaDeck();

        System.out.println(deck.pick(0));
        System.out.println(deck.pick());
        deck.shuffle();

        for(int i=0; i<deck.cards.length; i++){
            System.out.print(deck.cards[i]+", ");
        }

        System.out.println();
        System.out.println(deck.pick(0));
    }
}
*/

/* // 연습문제 7-10,11
class MyTv2{
    private boolean isPowerOn;
    private int channel;
    private int volume;

    final int MAX_VOLUME = 100;
    final int MIN_VOLUME = 0;
    final int MAX_CHANNEL = 100;
    final int MIN_CHANNEL = 1;

    private int PrevChannel;

    public void setChannel(int c){
        if(c<=MAX_CHANNEL && c>=MIN_CHANNEL) {
            PrevChannel = this.channel;
            this.channel = c;
        }
    }
    public int getChannel(){
        return channel;
    }
    public void setVolume(int v){
        if(v<=MAX_VOLUME && v>=MIN_VOLUME) this.volume = v;
    }
    public int getVolume(){
        return volume;
    }

    public void gotoPrevChannel(){
        this.setChannel(PrevChannel);
    }
}
class Exercise7_10{
    public static void main(String[] args) {
        MyTv2 t = new MyTv2();

        t.setChannel(10);
        System.out.println("CH:"+t.getChannel());
        t.setVolume(20);
        System.out.println("VOL:"+t.getVolume());
    }
}
class Exercise7_11{
    public static void main(String[] args) {
        MyTv2 t = new MyTv2();
        
        t.setChannel(10);
        System.out.println("CH:"+t.getChannel());
        t.setChannel(20);
        System.out.println("CH:"+t.getChannel());
        t.gotoPrevChannel();
        System.out.println("CH:"+t.getChannel());
        t.gotoPrevChannel();
        System.out.println("CH:"+t.getChannel());
    }
}
*/

// // 연습문제 7-17
// abstract class Unit { // 클래스마다 이동하는 방법이 다르므로 추상메서드로 정의
//     int x,y; // 현재 위치
//     abstract void move(int x, int y);
//     void stop() { /* 현재 위치 정지 */ }
// }
//
// class Marine extends Unit {
//     void move(int x, int y) {/* 지정된 위치로 이동 */}
//     void stimPack() {/* 스팀팩 사용 */}
// }
//
// class Tank extends Unit {
//     void move(int x, int y) {/* 지정된 위치로 이동 */}
//     void changeMode() {/* 공격 모드 변환 */}
// }
//
// class Dropship extends Unit {
//     void move(int x, int y) {/* 지정된 위치로 이동 */}
//     void load() {/* 선택된 대상을 태운다 */}
//     void unload() {/* 선택된 대상을 내린다 */}
// }

/* // 연습문제 7-18
class Exercise7_18{
    public static void action(Robot r){
        if(r instanceof DanceRobot) ((DanceRobot)r).Dance();
        else if (r instanceof SingRobot) ((SingRobot)r).Sing();
        else if (r instanceof DrawRobot) ((DrawRobot)r).Draw();
    }

    public static void main(String[] args) {
        Robot[] arr = { new DanceRobot(), new SingRobot(), new DrawRobot() };

        for(int i=0; i<arr.length; i++) action(arr[i]);
    }
}
class Robot {}
class DanceRobot extends Robot{
    void Dance(){
        System.out.println("춤을 춥니다");
    }
}
class SingRobot extends Robot{
    void Sing(){
        System.out.println("노래를 부릅니다.");
    }
}
class DrawRobot extends Robot{
    void Draw(){
        System.out.println("그림을 그립립니다.");
    }
}
*/

/* // 연습문제 7-19
class Exercise7_19{
    public static void main(String[] args) {
        Buyer b = new Buyer();
        b.buy(new Tv());
        b.buy(new Computer());
        b.buy(new Tv());
        b.buy(new Audio());
        b.buy(new Computer());
        b.buy(new Computer());
        b.buy(new Computer());

        b.summary();
    }
}
class Buyer {
    int money = 1000;
    Product[] cart = new Product[3];
    int i = 0;

    void buy(Product p){
        if(money>=p.price){
            money -= p.price;
            add(p);
        }
        else{
            System.out.printf("잔액이 부족하여 %s을(를) 구매할 수 없습니다.\n",p);
            return;
        }
    }

    void add(Product p){
        if(i>=cart.length){
            Product[] cartTmp = new Product[cart.length*2];
            System.arraycopy(cart, 0, cartTmp, 0, i);
            cart = cartTmp;
        }
        cart[i++] = p;
    }

    void summary(){
        int sum = 0;
        String str = "";

        for(int j=0; j<cart.length; j++) {
            if(cart[j]==null) break;
            sum += cart[j].price;
            str += cart[j]+",";
        }
        System.out.println("구입한 물건:"+str);
        System.out.println("사용한 금액:"+sum);
        System.out.println("남은 금액:"+money);
    }
    
}

class Product{
    int price;
    Product(int price){
        this.price = price;
    }
}

class Tv extends Product{
    Tv() { super(100); }
    public String toString() {return "Tv";}
}
class Computer extends Product{
    Computer() { super(200); }
    public String toString() {return "Computer";}
}
class Audio extends Product{
    Audio() { super(50); }
    public String toString() {return "Audio";}
}
*/

/* // 연습문제 7-22,23
abstract class Shape {
    Point p;

    Shape(){
        this(new Point(0,0));
    }
    Shape(Point p){
        this.p = p;
    }

    abstract double calcArea(); // 도형의 면적을 계산해서 반환

    Point getPosition(){
        return p;
    }

    void SetPosition(Point p){
        this.p = p;
    }
}

class Point{
    int x;
    int y;

    Point(){
        this(0,0);
    }
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "["+x+", "+y+"]";
    }
}

class Circle extends Shape {
    double r;

    Circle(double r){
        this(new Point(0,0),r);
    }

    Circle(Point p, double r){
        super(p);
        this.r = r;
    }

    double calcArea(){
        return r*r*Math.PI;
    }

}

class Rectangle extends Shape{
    double width;
    double height;

    Rectangle(double w, double h){
        this(new Point(0,0),w,h);
    }

    Rectangle(Point p,double w, double h){
        super(p);
        this.width = w;
        this.height = h;
    }

    boolean isSquare(){
        return width*height!=0 && width==height;
    }

    double calcArea(){
        return width*height;
    }
}

class Exercise7_23{
    static double sumArea(Shape[] arr){
        double sum = 0.0;
        for(int i=0; i<arr.length; i++){
            sum = sum + arr[i].calcArea();
        }
        return sum;
    }

    public static void main(String[] args) {
        Shape[] arr = {new Circle(5.0), new Rectangle(3, 4), new Circle(1)};
        System.out.println("면적의 합: "+sumArea(arr));
    }
}
*/

// 이것이 자바다 Chapter6 278p~
/* // 확인문제 13,14,15
class Member {
    String name;
    String id;
    String password;
    int age;

    Member(String n, String i){
        this.name = n;
        this.id = i;
    }
}

class MemberService{
    boolean login(String id, String pass){
        if(id.equals("hong") && pass.equals("12345")){
            return true;
        }
        return false;
    }

    void logout(String id){
        System.out.println(id+"님이 로그아웃되었습니다.");
    }
}

class Q15{
    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        boolean result = memberService.login("hong","12345");
        if(result){
            System.out.println("로그인 되었습니다.");
            memberService.logout("hong");
        }
        else{
            System.out.println("ID 또는 PASSWORD가 올바르지 않습니다.");
        }
    }
}
*/

 // 확인문제 16,17
class Q16{
    void println(int value){
        System.out.println(value);
    }
    void println(boolean value){
        System.out.println(value);
    }
    void println(double value){
        System.out.println(value);
    }
    void println(String value){
        System.out.println(value);
    }
}
class Q17{
    static void println(int value){
        System.out.println(value);
    }
    static void println(boolean value){
        System.out.println(value);
    }
    static void println(double value){
        System.out.println(value);
    }
    static void println(String value){
        System.out.println(value);
    }
}

