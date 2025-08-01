package July.Week2;

import java.util.Arrays;
import java.util.Scanner;

public class J0708 {
    public static void main(String[] args) {
    /*  // 140~141p 문제
        // Expression (표현식) 140p 2
        String grade = "B";
        int score = switch (grade){
            case "A" -> 100;
            case "B" -> {
                int result = 100-20;
                yield result;
            }
            default -> 60;
        };
        System.out.println(score);
        System.out.println();

        // 주사위 140p 4
        while (true) {
            int num1 = (int)(Math.random()*6)+1;
            int num2 = (int)(Math.random()*6)+1;

            System.out.printf("(%d,%d)\n",num1,num2);
            if(num1+num2==5) break;
        } System.out.println();

        // 방정식 140p 5
        for(int x=1; x<=10; x++){
            for(int y=1; y<=10; y++){
                if(4*x+5*y==60) System.out.printf("(%d,%d)\n",x,y);
            }
        } System.out.println();

        // ATM 141p 7
        int money = 10000;
        Scanner scan1 = new Scanner(System.in);
        A: while (true) {
            System.out.println("--------------------------------");
            System.err.println("1.예금 : 2.출금 : 3.잔고 : 4.종료");
            System.out.println("--------------------------------");
            System.out.print("선택> ");
            String op = scan1.nextLine();

            switch(op){
                case "1" -> {
                    System.out.printf("예금액> ");
                    Scanner scan2 = new Scanner(System.in);
                    int out_money = scan2.nextInt();
                    money += out_money;
                }
                case "2" -> {
                    System.out.printf("출금액> ");
                    Scanner scan2 = new Scanner(System.in);
                    int out_money = scan2.nextInt();
                    money -= out_money;
                }
                case "3" -> System.out.println("잔고> "+money);
                case "4" -> {
                    System.out.println("\n프로그램 종료");
                    break A;
                }
                default -> System.out.println("입력이 올바르지 않습니다.");
            };

            // if(op.equals("1")) {
            //     System.out.printf("예금액> ");
            //     Scanner scan2 = new Scanner(System.in);
            //     int out_money = scan2.nextInt();
            //     money += out_money;
            // }
            // else if(op.equals("2")){
            //     System.out.printf("출금액> ");
            //     Scanner scan2 = new Scanner(System.in);
            //     int out_money = scan2.nextInt();
            //     money -= out_money;
            // }
            // else if(op.equals("3")) System.out.println("잔고> "+money);
            // else if(op.equals("4")) break;
            // else System.out.println("입력이 올바르지 않습니다.");
            

            System.out.println();
        }
 */       

      // 참조변수 150p~
        // 배열은 같은 타입의 값만 관리
        // 배열의 길이는 늘리거나 줄일 수 없음
        int[] arr1; // 배열 변수 선언
        int[] arr2;
        int[] arr3;
        arr1 = new int[] {1,2,3}; // 배열 {1,2,3} 생성하고 arr1 변수에 대입
        arr2 = new int[] {1,2,3}; 
        arr3 = arr2;
        System.out.println(arr1==arr2); // false
        System.out.println(arr2==arr3); // true

        String refVar1 = "자바";
        String refVar2 = null;
        int[] intArray = null;
     // intArray[0] = 10; //NullPointerException
        
        String[] stu = new String[5];
        stu[0] = "박창현";
        stu[1] = "홍길동";
        stu[2] = "김민수";
        stu[3] = "김태현";
        stu[4] = "이동수";
        System.out.println(stu[4]); 
        for(int i=0;i<=4;i++) System.out.println(stu[i]);
        System.out.println();
        for(int i=0;i<stu.length;i++) System.out.println(stu[i]);
        System.out.println();

        Scanner scan2 = new Scanner(System.in);
        System.out.print("학생 수를 입력해주세요: ");
        final int stuNum = scan2.nextInt(); // final <- 상수처리
        String[] stuName = new String[stuNum];
        int[] Score = new int[stuNum];
        for(int i=0; i<stuNum; i++){
            System.out.print("학생 이름과 점수를 입력해주세요: ");
            stuName[i] = scan2.next();
            Score[i] = scan2.nextInt();
        }
        int sum = 0;
        for(int i=0; i<stuNum; i++){
            System.out.printf("%d. 이름: %s, 점수: %d\n",i+1,stuName[i],Score[i]);
            sum += Score[i];
        }
        System.out.printf("학생들의 평균 점수는 %.2f점 입니다.\n",(float)sum/stuNum);
        System.out.println();
          
        // 배열 167p
        // 배열 길이 늘리기
        int[] arrA = new int[] {10,20,30,40,50};
        int[] arrB = new int[10];
        for (int i=0;i<arrA.length;i++) arrB[i] = arrA[i];
        arrA = arrB;
        for (int i=0;i<arrA.length;i++) System.out.print(arrA[i]+" ");
        System.out.println();
    
        // 1~100 배열, 3의 배수의 수와 합 구하기
        int[] arrC = new int[100];
        for(int i=0; i<arrC.length;i++) arrC[i] = i+1;
        int CNT = 0;
        int SUM1 = 0;
        for(int i=0; i<arrC.length;i++){
            if(arrC[i]%3==0){
                CNT++;
                SUM1 += i;
            }
        }System.out.printf("%d, %d\n",CNT,SUM1);
        System.out.println();
    
        // 값으로 인덱스 출력
        Scanner scan3 = new Scanner(System.in);
        int[] arrD = {1, 2, 5, 3, 4, 9, 7, 6, 8};
        System.out.print("숫자를 입력해주세요: ");
        String number = scan3.nextLine();
        int idx = -1;

        for(int i=0; i<arrD.length; i++){
            if(arrD[i]==Integer.parseInt(number)){
                idx = i+1;
                break;
            }
        }
        if(idx > 0) System.out.printf("%d번째 숫자입니다.\n",idx);
        else System.out.println("찾는 수가 없습니다.");
         
    
    }
}
