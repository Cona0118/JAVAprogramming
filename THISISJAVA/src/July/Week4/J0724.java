package July.Week4;

import java.util.*;
import java.util.Map.Entry;

// 컬렉션 프레임워크 642p~
/* // List 컬렉션 643p~
class Board{
    private String subject;
    private String content;
    private String writer;

    public Board(String subject, String content, String writer){
        this.subject = subject;
        this.content = content;
        this.writer = writer;
    }

    public String getSubject() {return subject;}
    public void setSubject(String subject) {this.subject = subject;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public String getWriter() {return writer;}
    public void getWriter(String writer) {this.writer = writer;}
}
class ArrayListExample{
    public static void main(String[] args) {
        // ArrayList 컬렉션 생성
        List<Board> list = new ArrayList<>();

        // list 객체 추가
        list.add(new Board("제목1", "내용1", "글쓴이1"));
        list.add(new Board("제목2", "내용2", "글쓴이2"));
        list.add(new Board("제목3", "내용3", "글쓴이3"));
        list.add(new Board("제목4", "내용4", "글쓴이4"));
        list.add(new Board("제목5", "내용5", "글쓴이5"));

        // 저장된 총 객체 수 얻기
        int size = list.size();
        System.out.println("총 객체 수: "+size);
        System.out.println();

        // 특정 인덱스의 객체 가져오시
        Board board = list.get(2);
        System.out.println(board.getSubject()+"\t"+board.getContent()+"\t"+board.getWriter());
        System.out.println();

        // 모든 객체 하나씩
        for(int i=0; i<list.size(); i++){
            Board b = list.get(i);
            System.out.println(b.getSubject()+"\t"+b.getContent()+"\t"+b.getWriter());
        }
        System.out.println();

        // 객체 삭제
        list.remove(2);
        list.remove(2);

        // 객체 하나씩 가져오기
        for(Board b:list){
            System.out.println(b.getSubject()+"\t"+b.getContent()+"\t"+b.getWriter());
        }

    }
}

// Vector
// Vector 는 동기화된 메소드로 구성 -> 멀티스레드가 동시에 Vector() 메솓를 실행 불가 -> 멀티스레드 환경에서 안전하게 객체 추가/삭제 불가
class VectorExample{
    public static void main(String[] args) {
        // Vector 컬렉션 생성
        List<Board> list = new Vector<>();
        // List<Board> list = new ArrayList<>(); // 값 다르게 나옴

        // 작업 스레드 객체 생성
        Thread threadA = new Thread() {
            @Override
            public void run(){
                // 객체 1000개 추가
                for(int i=1; i<=1000;i++){
                    list.add(new Board("제목"+i,"내용"+i,"글쓴이"+i));
                }
            }
        };

        // 작업 스레드 객체 생성
        Thread threadB = new Thread(){
            @Override
            public void run(){
                // 객체 1000개 추가
                for(int i=1; i<=1000;i++){
                    list.add(new Board("제목"+i,"내용"+i,"글쓴이"+i));
                }
            }
        };

        // 작업 스레드 실행
        threadA.start();
        threadB.start();

        // 작업스레드들이 모두 종료될 때 까지 메인스레드 대기
        try{
            threadA.join();
            threadB.join();
        } catch(Exception e){}

        // 저장된 총 객체수 얻기
        int size = list.size();
        System.out.println("총 객체 수 : "+size);
        System.out.println();
    }
}

// LinkedList
// LinkedList는 인접 객체를 체인처럼 연결하여 관리 -> 객체 삽입/삭제 하면 앞 뒤 링크 수정 -> ArrayList에 비해 더 좋은 성능
class LinkedListExample{
    public static void main(String[] args) {
        // ArrayList 컬렉션 객체 생성
        List<String> list1 = new ArrayList<String>();

        // LinkedList 컬렉션 객체 생성
        List<String> list2 = new LinkedList<String>();

        // 시작 시간과 끝 시간을 저장할 변수 선언
        long stratTime;
        long endTime;

        // ArrayList 컬렉션에 저장하는 시간 측정
        stratTime = System.nanoTime();
        for(int i=0; i<10000; i++){
            list1.add(0,String.valueOf(i));
        }
        endTime = System.nanoTime();
        System.out.printf("%-17s %8d ns \n","ArrayList 걸린시간: ",(endTime - stratTime));

        // LinkedList 컬렉션에 저장하는 시간 측정
        stratTime = System.nanoTime();
        for(int i=0; i<10000; i++){
            list2.add(0,String.valueOf(i));
        }
        endTime = System.nanoTime();
        System.out.printf("%-17s %8d ns \n","LinkedList 걸린시간: ",(endTime - stratTime));
    }
}
*/

/* // Set 컬렉션 651p~
// 중복 저장 불가,  저장순서 유지 X -> 인덱스 존재 X
class HashSetExample{
    public static void main(String[] args) {
        // HashSet 컬렉션 저장
        Set<String> set = new HashSet<String>();

        // 객체 저장
        set.add("THIS");
        set.add("JAVA");
        set.add("IS");
        set.add("JAVA"); // 중복 객체 (저장 X)
        set.add("SPRING");

        // 저장된 객체 수 출력
        int size = set.size();
        System.out.println("총 객체 수: "+size); // 4
    }
}
class Member{
    public String name;
    public int age;

    public Member(String name, int age){
        this.name = name;
        this.age = age;
    }

    // hashCode 재정의
    @Override
    public int hashCode(){
        return name.hashCode() + age; // name과 age 값이 같으면 동일한 hashCode 리턴
    }

    // euqals 재정의
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Member target){
            return target.name.equals(name) && (target.age == age); // name과 age가 같으면 true 리턴
        } else{
            return false;
        }
    }
}
class HashSetExample2{
    public static void main(String[] args) {
        // HashSet 컬렉션 생성
        Set<Member> set = new HashSet<>();

        // Memver 객체 저장
        set.add(new Member("홍길동", 30));
        set.add(new Member("홍길동", 30)); // 인스턴스는 다르지만 동등 객체이므로 저장 X

        // 저장된 객체 수 출력
        System.out.println("총 객체 수 : "+ set.size());
    }
}
class HashSetExample3{
    public static void main(String[] args) {
        // HashSet 컬렉션 생성
        Set<String> set = new HashSet<>();

        // 객체 추가
        set.add("THIS");
        set.add("JAVA");
        set.add("IS");
        set.add("SPRING");

        // 객체를 하나씩 가져와서 처리
        Iterator<String> iterator = set.iterator(); // 반복자
        while(iterator.hasNext()){ // 가져올 객체가 있는지 확인 true or false 출력
            // 객체를 하나 가져오기
            String element = iterator.next();
            System.out.println(element);
            if(element.equals("SPRING")) iterator.remove();
        }
        System.out.println();

        // 객체 제거
        set.remove("IS");

        // 객체를 하나씩 가져와서 처리
        for(String element : set) System.out.println(element);
    }
}
*/

/* // Map 컬렉션 658p~
// 키와 값으로 엔트리 객체를 저장 << 키, 값 모두 객체

// HashMap
// 키로 사용할 객체가 hahsCode() 메소드의 리턴값이 같고 equals() 메소드가 true를 리턴할 경우, 동일키로 판단하고 중복저장 허용X
class HashMapExample{
    public static void main(String[] args) {
        // Map 컬렉션 생성
        Map<String, Integer> map = new HashMap<>(); 

        // 객체 저장
        map.put("신용권",85);
        map.put("홍길동",90);
        map.put("동장군",80);
        map.put("홍길동",98); // 키 값이 같기 때문에 마지막 저장한 값만 저장
        System.out.println("총 Entry 수:"+map.size());
        System.out.println();

        // 키로 값 얻기
        String key = "홍길동";
        int value = map.get(key); // 키를 매개값으로 주면 값을 리턴
        System.out.println(key+": "+value);
        System.out.println();
        
        // 키 Set 컬렉션을 얻고, 반복해서 키와 값을 얻기
        Set<String> keySet = map.keySet();                // 키를 반복하기 위해
        Iterator<String> keyIterator = keySet.iterator(); // 반복자를 얻음   
        while (keyIterator.hasNext()) {
            String k = keyIterator.next();
            Integer v = map.get(k);
            System.out.println(k+" : "+v);
        }    
        System.out.println();

        // 엔트리 Set 컬렉션을 얻고, 반복해서 키와 값을 얻기
        Set<Entry<String,Integer>> entrySet = map.entrySet(); 
        Iterator<Entry<String,Integer>> entryIterator = entrySet.iterator();
        while (entryIterator.hasNext()) {
            Entry<String,Integer> entry = entryIterator.next();
            String k = entry.getKey();
            Integer v = entry.getValue();
            System.out.println(k+" : "+v);
        }
        System.out.println();

        // 키로 엔트리 삭제
        map.remove("홍길동");
        System.out.println("총 Entry 수: "+map.size());
    }
}

// Hashtable
// HashMap과 동일한 내부구조, 차이점은 동기화된 메소드로 구성되어 있어 멀티스레드가 동시에 실행 불가 -> 안전하게 멀티스레드 환경에서 객체를 추가,삭제 가능
class HashtableExample{
    public static void main(String[] args) {
        // Hashtable 객체 생성
        Map<String, Integer> map = new Hashtable<>();
        // Map<String, Integer> map = new HashMap<>(); // 다른 값 출력

        // 작업 스레드 객체 생성
        Thread threadA = new Thread() {
            @Override
            public void run(){
                // 엔트리 1000개 추가
                for(int i=1; i<=1000; i++){
                    map.put(String.valueOf(i), i);
                }
            }
        };

        // 작업 스레드 객체 생성
        Thread threadB = new Thread() {
            @Override
            public void run(){
                // 엔트리 1000개 추가
                for(int i=1001; i<=2000; i++){
                    map.put(String.valueOf(i), i);
                }
            }
        };

        // 작업 스레드 실행
        threadA.start();
        threadB.start();

        // 작업스레드들이 모두 종료될 때 까지 메인스레드 대기
        try{
            threadA.join();
            threadB.join();
        }catch(Exception e){}

        // 저장된 총 엔트리 수 얻기
        int size = map.size();
        System.out.println("총 엔트리 수: "+size); // 2000
        System.out.println();
    }
}

// Properties
// Hashtable의 자식 클래스, 키와 값을 String 타입으로 제한
// properties(키와 값이 = 으로 연결된 텍스트 파일) 읽을 때 주로 사용
class PropertiesExample{
    public static void main(String[] args) {
        // Properties 컬렉션 생성
        Properties properties = new Properties();

        // PropertiesExample.class 와 동일한 ClassPath에 있는 database.properties 파일 로드
        // properties.load(PropertiesExample.class.getResourceAsStream("database.properties"));

        // 주어진 키에 대한 값 읽기
        String driver = properties.getProperty("driver");
    }
}
*/

/* // 검색 기능 강화 컬렉션 666p~
// TreeSet
// 이진트리를 기반으로한 Set 컬렉션, 루트 노드라고 불리는 하나의 노드에서 시작해 각 노드에 최대 2개의 노드를 연결할 수 있는 구조
class TreeSetExample{
    public static void main(String[] args) {
        // TreeSet 컬렉션 생성
        TreeSet<Integer> scores = new TreeSet<>();

        // Integer 객체 생성
        scores.add(87);
        scores.add(95);
        scores.add(75);
        scores.add(98);
        scores.add(90);

        // 정렬된 Integer 객체를 하나씩 가져오기 
        for(Integer s : scores) System.out.print(s+" ");
        System.out.println("\n");

        // 특정 Integer 객체를 가져오기
        System.out.println("최소 "+scores.first());
        System.out.println("최대 "+scores.last());
        System.out.println("95점 아래 "+scores.lower(95));
        System.out.println("95점 위 "+scores.higher(95));
        System.out.println("95점이거나 바로 위 "+scores.floor(95));
        System.out.println("85점이거나 바로 위 "+scores.ceiling(85)+"\n");

        // 내림차순 정렬
        NavigableSet<Integer> descendingScores = scores.descendingSet();
        for(Integer s : descendingScores) System.out.print(s+" ");
        System.out.println();

        // 범위 검색 80<=
        NavigableSet<Integer> rangeSet = scores.tailSet(80, true);
        for(Integer s : rangeSet) System.out.print(s+" ");
        System.out.println();
        
        // 범위 검색 80<= s < 90
        rangeSet = scores.subSet(80, true, 90, false);
        for(Integer s : rangeSet) System.out.print(s+" ");
        System.out.println();
    }
}

// TreeMap
// 이진트리 기반 Map 컬렉션, 키와 값이 저장된 Entry를 저장
// 키값을 비교 낮은것은 왼쪽, 높은것은 오른쪽 자식 노드에 Entry 객체 저장
class TreeMapExample{
    public static void main(String[] args) {
        // TreeMap 컬렉션 생성
        TreeMap<String,Integer> treeMap = new TreeMap<>();

        // 엔트리 저장
        treeMap.put("apple",10);
        treeMap.put("forever",60);
        treeMap.put("description",40);
        treeMap.put("ever",50);
        treeMap.put("zoo",80);
        treeMap.put("base",20);
        treeMap.put("guess",70);
        treeMap.put("cherry",30);

        // 정렬된 엔트리를 하나씩 가져오기
        Set<Entry<String,Integer>> entryset = treeMap.entrySet(); 
        for(Entry<String,Integer> entry : entryset) System.out.println(entry.getKey()+"-"+entry.getValue());
        System.out.println();

        // 특정 키에 대한 값 가져오기
        Entry<String,Integer> entry = null;
        entry = treeMap.firstEntry();
        System.out.println("제일 앞 단어: "+entry.getKey()+"-"+entry.getValue());
        entry = treeMap.lastEntry();
        System.out.println("제일 뒤 단어: "+entry.getKey()+"-"+entry.getValue());
        entry = treeMap.lowerEntry("ever");
        System.out.println("ever 앞 단어: "+entry.getKey()+"-"+entry.getValue()+"\n");

        // 내림차순으로 정렬하기
        NavigableMap<String,Integer> descendingMap = treeMap.descendingMap();
        Set<Entry<String,Integer>> decendingEntrySet = descendingMap.entrySet();
        for(Entry<String,Integer> e: decendingEntrySet) System.out.println(e.getKey()+"-"+e.getValue());
        System.out.println();

        // 범위 검색
        System.out.println("[c~h 사이 단어 검색]");
        NavigableMap<String,Integer> rangeMap = treeMap.subMap("c", true, "h", false);
        for(Entry<String,Integer> e: rangeMap.entrySet()) System.out.println(e.getKey()+"-"+e.getValue());
    }
}

// Comparable 과 Comparator
// Comparable 인터페이스에서는 compareTo() 메소드가 정의되어 있음 -> 이 메소드를 재정의 해서 비교결과를 정수값으로 리턴
class Person implements Comparable<Person>{
    public String name;
    public int age;
    
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o){
        if(age<o.age) return -1;
        else if(age==o.age) return 0;
        else return 1;
    }
}
class ComparableExample{
    public static void main(String[] args) {
        // TreeSet 컬렉션 생성
        TreeSet<Person> treeSet = new TreeSet<>();

        // 객체저장
        treeSet.add(new Person("홍길동", 45));
        treeSet.add(new Person("김자바", 25));
        treeSet.add(new Person("박지원", 31));
        treeSet.add(new Person("박지투", 32));


        // 객체를 하나씩 가져오기
        for(Person person : treeSet) System.out.println(person.name+" : "+person.age);
    }
}

// 비교기능이 없는 Comparable 비구현 객체를 저장하고 싶다면 TreeSet과 TreeMap을 생성할때 비교자를 제공
class Fruit{
    public String name;
    public int price;

    public Fruit(String name, int price){
        this.name = name;
        this.price = price;
    }
}
class FruitComparator implements Comparator<Fruit>{
    @Override
    public int compare(Fruit o1, Fruit o2){
        if(o1.price < o2.price) return -1;
        else if(o1.price == o2.price) return 0;
        else return 1;
    }
}
class ComparableExample2{
    public static void main(String[] args) {
        // 비교자를 제공한 TreeSet 컬렉션 생성
        TreeSet<Fruit> treeSet = new TreeSet<>(new FruitComparator());

        // 객체 저장
        treeSet.add(new Fruit("포도", 3000));
        treeSet.add(new Fruit("수박", 10000));
        treeSet.add(new Fruit("딸기", 6000));

        // 객체를 하나씩 가져오기
        for(Fruit fruit : treeSet) System.out.println(fruit.name+" : "+fruit.price);
    }
}
*/

/* // LIFO와 FIFO 컬렉션 678p~
// LIFO : Last In First Out >> Stack
class Coin{
    private int value;

    public Coin(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
class StackExample{
    public static void main(String[] args) {
        // Stack 컬렉션 생성
        Stack<Coin> coinBox = new Stack<>();
        
        // 동전 넣기
        coinBox.push(new Coin(100));
        coinBox.push(new Coin(50));
        coinBox.push(new Coin(500));
        coinBox.push(new Coin(10));

        // 동전을 하나씩 꺼내기
        while (!coinBox.isEmpty()) {
            Coin coin = coinBox.pop(); // 스택의 맨 객체를 빼낸다
            System.out.println("꺼낸 동전 : "+coin.getValue()+"원"); // 10 500 50 100
        }
    }
}

// FIFO : First In First Out >> Queue -> LinkedList로 구현
class Message{
    public String commad;
    public String to;

    public Message(String command, String to){
        this.commad = command;
        this.to = to;
    }
}
class QueueExample{
    public static void main(String[] args) {
        // Queue 컬렉션 생성
        Queue<Message> messageQueue = new LinkedList<>();

        // 메세지 넣기
        messageQueue.offer(new Message("sendMail", "홍길동"));      // 1
        messageQueue.offer(new Message("sendSMS", "신용권"));       // 2
        messageQueue.offer(new Message("sendKakaotalk", "김자바")); // 3

        // 메세지를 하나씩 넣어서 처리
        while (!messageQueue.isEmpty()) {
            Message message = messageQueue.poll(); // 큐에서 객체를 빼낸다
            switch (message.commad) {
                case "sendMail":
                    System.out.println(message.to+"님께 메세지를 보냅니다.");
                    break;
            
                case "sendSMS":
                    System.out.println(message.to+"님께 SMS를 보냅니다.");
                    break;
                
                case "sendKakaotalk":
                    System.out.println(message.to+"님께 카카오톡을 보냅니다.");
                    break;
            }
        }
    }
}
*/

/* // 동기화된 컬렉션 683p~
// ArrayList 와 HashSet, HashMap은 동기화 된 메소드로 구성X -> 멀티스레드 환경에서 불안전
// 비동기화된 메소드를 래핑하는 synchronizedXXX() 메소드를 제공
class SynchronizedMapExample{
    public static void main(String[] args) {
        // Map 객체 생성
        Map<Integer, String> map = Collections.synchronizedMap(new HashMap<>());
        // Map<Integer, String> map = new HashMap<>(); // 다른 값 출력

        // 작업 스레드 객체 생성
        Thread threadA = new Thread() {
            @Override
            public void run(){
                // 엔트리 1000개 추가
                for(int i=1; i<=1000; i++){
                    map.put(i,"내용"+i);
                }
            }
        };

        // 작업 스레드 객체 생성
        Thread threadB = new Thread() {
            @Override
            public void run(){
                // 엔트리 1000개 추가
                for(int i=1001; i<=2000; i++){
                    map.put(i,"내용"+i);
                }
            }
        };

        // 작업 스레드 실행
        threadA.start();
        threadB.start();

        // 작업스레드들이 모두 종료될 때 까지 메인스레드 대기
        try{
            threadA.join();
            threadB.join();
        }catch(Exception e){}

        // 저장된 총 엔트리 수 얻기
        int size = map.size();
        System.out.println("총 엔트리 수: "+size); // 2000
        System.out.println();
    }
}
*/ 

/* // 수정 불가 컬렉션 686p~
// 컬렉션 생성시 저장된 요소를 변경하고 싶지 않을 때 유용
// 1. List Set Map 인터페이스의 정적메소드 of() 활용 -> List.of()
// 2. List Set Map 인터페이스의 정적메소드 copyOf() 활용하여 복사하여 수정불가 컬렉션 생상 -> List.copyOf()
class ImmutableExample{
    public static void main(String[] args) {
        // List 불변 컬렉션 생성
        List<String> immutableList1 = List.of("A","B","C");
        // immutableList1.add("D"); // 불가, Error 발생

        // Set 불변 컬렉션 생성
        Set<String> immutableSet1 = Set.of("A","B","C");
        // immutableSet1.remove("A"); // 불가, Error 발생

        // Map 불변 컬렉션 생성
        Map<Integer,String> immutableMap1 = Map.of(1,"A",2,"B",3,"C");
        // immutableMap1.add(4,"D"); // 불가, Error 발생

        // List 컬렉션을 불변 컬렉션으로 복사
        List<String>list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        List<String> immutableList2 = List.copyOf(list);

        // 배열로 부터 List 불변 컬렉션 생성
        String[] arr = {"A","B","C"};
        List<String> immutableList3 = Arrays.asList(arr);
    }
}
*/

// 확인문제 689p~
/* // 7번
class Board{
    private String title;
    private String content;

    public Board(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTitle() {return title;}
    public String getContent() {return content;}
}
class ListExample{
    public static void main(String[] args) {
        BoardDao dao = new BoardDao();
        List<Board> list = dao.getBoardList();
        for(Board board : list) System.out.println(board.getTitle()+"-"+board.getContent());
    }
}
class BoardDao{
    List<Board> boards = new ArrayList<>();

    public BoardDao(){
        boards.add(new Board("제목1","내용1"));
        boards.add(new Board("제목2","내용2"));
        boards.add(new Board("제목3","내용3"));
    }

    public List<Board> getBoardList(){
        return boards;
    }
    // public List<Board> getBoardList() {
    //     List<Board> list = new ArrayList<Board>();
    //     list.add(new Board("제목1", "내용1"));
    //     list.add(new Board("제목2", "내용2"));
    //     list.add(new Board("제목3", "내용3"));
    //     return list;
    // }

}
*/

/* // 8번
class Student{
    public int studentNum;
    public String name;

    public Student (int studentNum, String name){
        this.studentNum = studentNum;
        this.name = name;
    }

    @Override
    public int hashCode(){
        return studentNum;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Student student){
            return student.studentNum == studentNum;
        }
        else return false;
    }

}
class HashSetExample{
    public static void main(String[] args) {
        Set<Student> set = new HashSet<>();

        set.add(new Student(1, "홍길동"));
        set.add(new Student(2, "신용권"));
        set.add(new Student(1, "조민우"));

        System.out.println("저장된 객체 수: "+set.size());
        for(Student s: set) System.out.println(s.studentNum + ":"+s.name);
    }
}
*/

/* // 9번
class MapExample{
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("blue", 96);
        map.put("hong", 86);
        map.put("white", 92);

        String name = null; // 최고 점수 아이디 저장 변수
        int maxScore = 0;
        int totalScore = 0;

        Set<Entry<String,Integer>> entrySet = map.entrySet();

        Iterator<Entry<String,Integer>> entryIterator = entrySet.iterator();
        while (entryIterator.hasNext()) {
            Entry<String,Integer> entry = entryIterator.next();
            if(maxScore<=entry.getValue()) {
                name = entry.getKey();
                maxScore = entry.getValue();
            }
            totalScore += entry.getValue();
        }
        // 이렇게도 가능
        // for(Map.Entry<String,Integer> entry : entrySet) {
        //     if(entry.getValue()>maxScore) {
        //         name = entry.getKey();
        //         maxScore = entry.getValue();
        //     }
        //     totalScore += entry.getValue();
        // }
        
        
        System.out.println("평균 점수: "+totalScore/entrySet.size());
        System.out.println("최고 점수: "+maxScore);
        System.out.println("최고 점수를 받은 아이디: "+name);
    }
}
*/

/* // 10번
class Student implements Comparable<Student>{
    public String id;
    public int score;

    public Student(String id, int score){
        this.id = id;
        this.score = score;
    }

    @Override
    public int compareTo(Student s){
        if(score < s.score) return -1;
        else if(score == s.score) return 0;
        else return 1;
    }
}
class TreeSetExample{
    public static void main(String[] args) {
        TreeSet<Student> treeSet = new TreeSet<>();
        treeSet.add(new Student("blue", 96));
        treeSet.add(new Student("hone", 86));
        treeSet.add(new Student("white", 92));

        Student student = treeSet.last();
        System.out.println("최고 점수: "+student.score);
        System.out.println("최고 점수를 받은 아이디: "+student.id);
    }
}
*/

