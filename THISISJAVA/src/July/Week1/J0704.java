package July.Week1;

import java.util.Scanner;

public class J0704 {
    public static void main(String[] args) {
        /* // 자동 타입 변환 55p
        byte byteValue = 10;
        int intValue =byteValue;
        System.out.println("intValue: "+intValue); // 10

        char charValue = '가';
        intValue = charValue;
        System.out.println("가의 유니코드: "+intValue); // 44032

        intValue = 50;
        long longValue = intValue;
        System.out.println("longValue: "+intValue); // 50

        longValue = 100;
        float floatValue = longValue;
        System.out.println("floatValue: "+floatValue); // 100.0

        floatValue = 100.5F;
        double doubleValue = floatValue;
        System.out.println("doubleValue "+doubleValue); // 100.5

        // 강제 타입 변환 57p
        intValue = 10;
        byteValue = (byte) intValue; 
        System.out.println(byteValue); // 10

        intValue = 1000;
        byteValue = (byte) intValue; 
        System.out.println(byteValue); // -24

        int var1 = 10;
        byte var2 = (byte) var1;
        System.out.println(var2); // 10

        long var3 = 300;
        int var4 = (int) var3; 
        System.out.println(var4); // 300

        int var5 = 65;
        char var6 = (char) var5;
        System.out.println(var6); // A

        double var7 = 3.14;
        int var8 = (int) var7;
        System.out.println(var8); // 3

        // 연산식에서 자동 타입 변환 60p
        byte x = 10;
        byte y = 20;
     // byte result = x+y; // 컴파일 에러
        int result1 = x + y;
        System.out.println(result1); // 30

        double result2 = 1.2f + 3.4; // float + double -> double로 저장
        double result3 = 10 + 5.5; // int + double -> double로 저장

        int x1 = 1;
        int y1 = 2;
        double result4 = x1 / y1; // 정수 연산의 값은 항상 정수
        System.out.println(result4); // 0.0 

        double result5 = (double)x1 / y1;
        System.out.println(result5); // 0.5

        double result6 = x1 / (double)y1;
        System.out.println(result6); // 0.5

        double result7 = (double)x1 / (double)y1;
        System.out.println(result7); // 0.5

        double result8 = (double) (x/y);
        System.out.println(result8); // 0.0
        
        byte r1 = 10 + 20; // 컴파일 단계에서 연산
        System.out.println("r1: "+r1); // 30

        byte v1 = 10;
        byte v2 = 20;
        int r2 = v1 + v2; // int 타입 변환 후 연산
        System.out.println("r2: "+r2); // 30

        byte v3 = 10;
        int v4 = 100;
        long v5 = 1000L;
        long r3 = v3 + v4 + v5; // long 타입 변환 후 연산
        System.out.println("r3: "+r3); // 1110

        char v6 = 'A'; // 유니코드 65
        char v7 = 1;
        int r4 = v6 + v7;
        System.out.println("r4: "+r4); // 66
        System.out.println("r4: "+(char)r4); // B 

        int v8 = 10;
        int r5 = v8 / 4; // 정수 연산의 결과는 정수
        System.out.println("r5: "+r5); // 2.0

        int v9 = 10;
        double r6 = v9 / 4.0; // double 타입 변환 후 연산
        System.out.println("r6: "+r6); // 2.5
        */

        /* // 연산자 80p
        byte b = 100;
      //byte result = -b; // 컴파일에러 : 부호 변경도 연산이므로 int로 변환
        int result = -b;

        int x = 1;
        int y = 1;
        int r1 = ++x +10; // x 1 증가 후 연산
        System.out.println("r1: "+r1); // 12
        int r2 = y++ +10; // 연산 후 y 1 증가
        System.out.println("r2: "+r2); // 11
        System.out.printf("x: %d, y: %d \n",x,y); // 2 2

        x = 10;
        y = 10;
        y = x++; // y에 x 대입 후 x 1 증가
        System.out.printf("x: %d, y: %d \n",x,y); // 11 10

        x = 10;
        y = 10;
        y = ++x; // x 1 증가 후 y에 x 대입 
        System.out.printf("x: %d, y: %d \n",x,y); // 11 11

        x = 10;
        y = 10;
        int z = 10;
        z = ++x + y++; // x 1증가 후 y와 합계를 z에 대입 후 y 1 증가
        System.out.printf("x: %d, y: %d, z: %d \n",x,y,z); // 11 11 21


        // 산술 연산자 83p
        Scanner scan1 = new Scanner(System.in);
        System.out.print("숫자1을 입력해주세요: ");
        int num1 = scan1.nextInt();

        System.out.print("숫자2을 입력해주세요: ");
        int num2 = scan1.nextInt();

        System.out.printf("%d + %d = %d \n",num1,num2,num1+num2);
        System.out.printf("%d - %d = %d \n",num1,num2,num1-num2);
        System.out.printf("%d * %d = %d \n",num1,num2,num1*num2);
        System.out.printf("%d / %d = %.2f \n",num1,num2,(float)num1/num2);
        System.out.printf("%d %% %d = %d \n",num1,num2,num1%num2);
        
        System.out.print("숫자를 입력해주세요: ");
        int num3 = scan1.nextInt();

        System.out.println("백의 자리 숫자 = "+num3/100%10);
        System.out.println("십의 자리 숫자 = "+num3/10%10);
        System.out.println("일의 자리 숫자 = "+num3%10);
        
        // 비교 연산자 90p
        int n1 = 1;
        double n2 = 1.0;
        boolean resultA = (n1 == n2);
        System.out.println("1 == 1.0 : "+resultA); // true

        n2 = 0.1;
        float n3 = 0.1f;
        boolean resultB = (n2 == n3);
        System.out.println("0.1 == 0.1f : "+resultB); // false

        String str1 = "자바";
        String str2 = "java";
        boolean resultC = (str1 == str2);
        boolean resultD = (str1.equals(str2));
        System.out.println(resultC); // false
        System.out.println(resultD); // false

        // 삼항 연산자 105p
        int score = 85;
        char grade = (score>90) ? 'A' : ((score>80) ? 'B' : 'C');
        System.out.printf("%d점은 %s등급입니다. \n",score,grade); // 85점은 B등급입니다.
        
        */

        /*// 조건문 112p
        int score = 93;
        if(score>90) System.out.println("등급은 A입니다.");

        Scanner scan1 = new Scanner(System.in);
        System.out.print("숫자1을 입력해주세요: ");
        int num1 = scan1.nextInt();

        System.out.print("연산자를 입력해주세요: ");
        String op = scan1.next();

        System.out.print("숫자2을 입력해주세요: ");
        int num2 = scan1.nextInt();

        if (op.equals("+")) System.out.printf("%d + %d = %d \n",num1,num2,num1+num2);
        else if (op.equals("-")) System.out.printf("%d - %d = %d \n",num1,num2,num1-num2);
        else if (op.equals("*")) System.out.printf("%d * %d = %d \n",num1,num2,num1*num2);
        else if (op.equals("/")) System.out.printf("%d / %d = %.2f \n",num1,num2,(float)num1/num2);
        else if (op.equals("%")) System.out.printf("%d %% %d = %d \n",num1,num2,num1%num2);
        else System.out.println("올바른 연산자를 입력해주세요");

        System.err.print("점수를 입력하세요: ");
        Scanner scan2 = new Scanner(System.in);
        int score1 = scan2.nextInt();

        if (score1>=90){
            if (score1>=98) {
                System.out.println("당신의 학점은 A+입니다.");                
            }
            else System.out.println("당신의 학점은 A입니다.");
        }
        else if (score1>=80){
            if (score1>=88) {
                System.out.println("당신의 학점은 B+입니다.");                
            }
            else System.out.println("당신의 학점은 B입니다.");
        }
        else if (score1>=70){
            if (score1>=78) {
                System.out.println("당신의 학점은 C+입니다.");                
            }
            else System.out.println("당신의 학점은 C입니다.");
        }
        else System.out.println("당신의 학점은 F입니다.");
        

        String Grade;
        if (score1>=90) Grade = "A";
        else if (score1>=80) Grade = "B";
        else if (score1>=70) Grade = "C";
        else Grade = "F";

        if (score1 == 100 || (score1 >=70 && score1%10 >=8)) Grade += "+";
        System.out.printf("당신의 학점은 %s입니다.\n",Grade);
*/

        // switch문 120p
        int num = (int)(Math.random()*5) + 1; // 난수 생성 1~5
        switch (num) {
            case 1:
                System.out.println("오늘의 점심은 라멘입니다.");
                break;
            case 2:
                System.out.println("오늘의 점심은 짜장면입니다.");
                break;
            case 3:
                System.out.println("오늘의 점심은 제육입니다.");
                break;
            case 4:
                System.out.println("오늘의 점심은 카레입니다.");
                break;
            default:
                System.out.println("오늘의 점심은 학식입니다.");
        }

        Scanner scan1 = new Scanner(System.in);
        System.out.print("주민번호를 입력해주세요: ");
        String Rnum = scan1.nextLine();
        
        char Rnum_Gender = Rnum.charAt(7);
        switch (Rnum_Gender) {
            case '1','3':
                System.out.println("남자입니다.");
                break;
            case '2','4':
                System.out.println("여자입니다.");
                break;
            default:
                System.err.println("주민번호가 올바르지 않습니다.");
        }
    }

}
