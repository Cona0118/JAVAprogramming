package July.Week1;

import java.util.Scanner;

public class J0703 {
    public static void main(String[] args) {
        /*  //변수값 교환 방법
        int num1 = 10;
        int num2 = 20;
        int tmp;

        System.out.println("X:"+ num1 + " y:"+ num2);

        tmp = num1;
        num1 = num2;
        num2 = tmp;

        System.out.println("X:"+ num1 + " y:"+ num2);
        */
        
        /* // Scanner + print 종류
        Scanner scan1 = new Scanner(System.in);
        System.out.print("숫자를 입력하세요: ");
        int num1 = scan1.nextInt();

        System.out.println("입력내용="+num1);
        System.out.printf("입력내용=%d \n",num1);
        System.out.print("입력내용="+num1);
        System.out.print("입력내용="+num1);
        */

        /* // 더하기 계산기
        Scanner scan1 = new Scanner(System.in);
        System.out.print("숫자1을 입력하세요: ");
        int num1 = scan1.nextInt();

        System.out.print("숫자2를 입력하세요: ");
        int num2 = scan1.nextInt();

        int sum1 = num1 + num2;

        System.out.printf("%d + %d = %d",num1,num2,sum1);
        */

        /* // next, nextLine
        Scanner scan1 = new Scanner(System.in);
        System.out.print("이름을 입력하세요: ");
        // String name = scan1.next(); // 스페이스 까지 입력
        String name = scan1.nextLine(); // 엔터 까지 입력

        System.out.print("취미를 입력하세요: ");
        String hb = scan1.nextLine();

        System.out.println(name + hb);
        System.out.println("name= "+name+" hb= "+hb);
        */

        // 형변환
        Scanner scan1 = new Scanner(System.in);
        
        System.out.print("이름을 입력해주세요: ");
        String name = scan1.nextLine();

        System.out.print("나이를 입력해주세요: ");
        //String age = scan1.nextLine();
        //int int_age = Integer.parseInt(age);
        int int_age = Integer.parseInt(scan1.nextLine());

        System.out.print("학과를 입력해주세요: ");
        String major = scan1.nextLine();

        System.out.printf("제 이름은 %s 이고, 나이는 %d살 이며, 학과는 %s 입니다. \n",name,int_age,major);
        System.out.println("제 이름은 "+name+" 이고, 나이는 "+int_age+"살 이며, 학과는 "+major+" 입니다.");
        
    }

}
