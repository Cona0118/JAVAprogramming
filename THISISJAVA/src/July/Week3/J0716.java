package July.Week3;

// JAVA의 정석
/* // 연습문제 6-1,2
class SutdaCard {
    int num;
    boolean isKwang;

    SutdaCard(){
        this(1,true);
    }
    SutdaCard(int n, boolean k){
        num = n;
        isKwang = k;
    }

    String info(){
        if(isKwang) return String.valueOf(num)+"K";
        else return String.valueOf(num);
    }
}
class Exercise6_2{
    public static void main(String[] args) {
        SutdaCard card1 = new SutdaCard(3, false);
        SutdaCard card2 = new SutdaCard();

        System.out.println(card1.info());
        System.out.println(card2.info());
    }
}
*/

/* // 연습문제 6-3,4
class Student {
    String name;
    int ban;
    int no;
    int kor;
    int eng;
    int math;

    int getTotal() { return kor+eng+math; }
    float getAverage() { 
        // float Average = getTotal() / 3.0f;
        // return Math.round(Average * 10) / 10.0f;

        return (int)(getTotal() / 3.0f * 10 + 0.5f ) / 10f; // 반올림
    }
}
class Exercise6_4 {
    public static void main(String[] args) {
        Student s = new Student();
        s.name = "홍길동";
        s.ban = 1;
        s.no = 1;
        s.kor = 100;
        s.eng = 60;
        s.math = 76;

        System.out.println("이름: "+s.name);
        System.out.println("총점: "+s.getTotal());
        System.out.println("평균: "+s.getAverage());
    }
}
*/

/* // 연습문제 6-5
class Exercise6_5{
    public static void main(String[] args) {
        Student s = new Student("홍길동",1,1,100,60,76);
        System.out.println(s.info());
    }
}
class Student{
    String name;
    int ban;
    int no;
    int kor;
    int en;
    int math;

    Student(String n,int b,int o,int k,int e,int m){
        name = n;
        ban = b;
        no = o;
        kor = k;
        en = e;
        math = m;
    }

    String info(){
        int sum = kor + en + math;
        float average = Math.round(sum / 3.0f * 10) / 10.0f ;
        String result = name+","+ban+","+no+","+kor+","
                        +en+","+math+","+sum+","+average;
        return result;
    }
}
*/

/* // 연습문제 6-6
class Exercise6_6{
    // 두점 x,y 와 x1,y1 간의 거리를 구한다
    static double getDistance(int x, int y, int x1, int y1){
        double result = Math.sqrt(Math.pow(x1-x, 2)+ Math.pow(y1-y, 2));
        // double result = Math.sqrt((x1-x)*(x1-x)+(y1-y)*(y1-y));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getDistance(1, 1, 2, 2));
    }
}
*/

/* // 연습문제 6-7
class MyPoint{
    int x;
    int y;
    MyPoint(int x, int y){
        this.x = x;
        this.y = y;
    }
    double getDistance(int x1, int y1){
        double result = Math.sqrt(Math.pow(x1-x, 2)+ Math.pow(y1-y, 2));
        // double result = Math.sqrt((x1-x)*(x1-x)+(y1-y)*(y1-y));
        return result;
    }
}
class Exercise6_7{
    public static void main(String[] args) {
        MyPoint p = new MyPoint(1,1);

        // p와 (2,2)의 거리를 구한다.
        System.out.println(p.getDistance(2, 2));
    }
}
*/

/* // 연습문제 6-19
class Exercise6_19{
    public static void change(String str){
        str += "456";
    }
    public static void main(String[] args) {
        String str = "ABC123";
        System.out.println(str);
        change(str);
        System.out.println("After change : "+str);
    }
}
*/

/* // 연습문제 6-20
class Exercise6_20{
    static int[] shuffle(int[] arr){
        for(int i=0;i<arr.length;i++){
            int temp = arr[i];
            int rand = (int)(Math.random()*arr.length);
            arr[i] = arr[rand];
            arr[rand] = temp;
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] original = {1,2,3,4,5,6,7,8,9};
        System.out.println(java.util.Arrays.toString(original));

        int[] result = shuffle(original);
        System.out.println(java.util.Arrays.toString(result));
    }
}
*/

/* // 연습문제 6-21
class MyTv{
    boolean isPowerOn;
    int channel;
    int volume;

    final int MAX_VOLUME = 100;
    final int MIN_VOLUME = 0;
    final int MAX_CHANNEL = 100;
    final int MIN_CHANNEL = 1;

    void turnOnOff(){
        isPowerOn = !isPowerOn;
    }

    void volumeUp(){
        if(volume<MAX_VOLUME) volume++;
    }

    void volumeDown(){
        if(volume>MIN_VOLUME) volume--;
    }

    void channelUp(){
        channel++;
        if(channel>MAX_CHANNEL) channel = MIN_CHANNEL;
    }

    void channelDown(){
        channel--;
        if(channel<MIN_CHANNEL) channel = MAX_CHANNEL;
    }
}
class Exercise6_21{
    public static void main(String[] args) {
        MyTv t = new MyTv();

        t.channel = 100;
        t.volume = 0;
        System.out.println("CH:"+t.channel+", Vol:"+t.volume);

        t.channelDown();
        t.volumeDown();
        System.out.println("CH:"+t.channel+", Vol:"+t.volume);
            
        t.volume = 100;
        t.channelUp();
        t.volumeUp();
        System.out.println("CH:"+t.channel+", Vol:"+t.volume);
    }
}
*/

/* // 연습문제 6-22
class Exercise6_22{
    static boolean isNumber(String str){
        if(str==null|| str.equals("")) return false;
        else{
            for(int i=0;i<str.length();i++){
                if('0'>str.charAt(i) || '9'<str.charAt(i)) {
                    return false;
                }
            }
        }
        return true; 
    }
    public static void main(String[] args) {
        String str = "123";
        System.out.println(str+"는 숫자입니까? "+isNumber(str));

        str = "1234o";
        System.out.println(str+"는 숫자입니까? "+isNumber(str));
    }
}
*/

/* // 연습문제 6-23
class Exercise6_23{
    static int max(int[] arr){
        if (arr==null || arr.length == 0) return -999999;
        else {
            int maxV = arr[0];
            for(int i=1;i<arr.length;i++){
                if(maxV<arr[i]) maxV = arr[i];
            }
            return maxV;
        } 
    }

    public static void main(String[] args) {
        int[] data = {3,2,9,4,7};
        System.out.println(java.util.Arrays.toString(data));
        System.out.println("최댓값: "+max(data));
        System.out.println("최댓값: "+max(null));
        System.out.println("최댓값: "+max(new int[] {})); // 크기가 0인 배열
    }
}
*/

/* // 연습문제 6-24
class Exercise6_24{
    static int abs(int value){
        // if (value < 0) value *= -1;
        // return value;
        return value >=0 ? value : -value;
    }
    public static void main(String[] args) {
        int value = 5;
        System.out.printf("%d의 절대값: %d\n",value,abs(value));
        value = -10;
        System.out.printf("%d의 절대값: %d\n",value,abs(value));
    }
}
*/