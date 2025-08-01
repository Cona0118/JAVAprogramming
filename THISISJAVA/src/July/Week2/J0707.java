package July.Week2;

import java.util.Scanner;

public class J0707 {
    public static void main(String[] args) {
        /* // for문 126p
        // for(초기화;조건식;증감식) 조건식이 False 경우 for문 종료
        for (int i=1; i<=10; i++) System.out.print(i+" ");
        System.out.println();

        for (int i=2; i<=10; i+=2) System.out.print(i+" ");
        System.out.println();

        for (int i=1; i<=10; i++){
            if (i%2 == 0) System.out.print(i+" ");
        }
        System.out.println();

        int sum1 = 0;
        for (int i=1; i<=10; i++){
            sum1 += i;
            // System.out.printf("1부터 %02d까지의 합은 %02d입니다 \n",i,sum1);
        } 
        System.out.printf("1부터 10까지의 합은 %d입니다 \n",sum1);

        int sum2 = 0;
        int tot = 0;
        for (int i=1; i<=10 ; i++){
            sum2 += i;
            tot += sum2;
        }
        System.out.println(tot);

        tot = 0;
        for (int i=1; i<=10 ; i++){
            for (int j=1; j<=i; j++){
                tot += j;
            }
        }
        System.out.println(tot);

        int i;
        int sum3 = 0;
        for (i=1;sum3<100;i++){
            if (i%2 == 0) i *= -1;
            // System.out.printf("%d+%d \n",sum3,i);
            sum3 += i;
            if (i<0) i *= -1;  
        }
        System.out.println(i-1); // 200-1
        // System.out.println(sum3); // 100

        sum3 = 0;
        for (i=1;true;i++){
            if (i%2 == 0) i *= -1;
            // System.out.printf("%d+%d \n",sum3,i);
            sum3 += i;
            if (i<0) i *= -1;  
            if (sum3 >= 100) break;
        }
        System.out.println(i); // 199
        // System.out.println(sum3); // 100

        i=0; sum3=0; 
        int num;
        for(;;){
            i++;
            if(i%2==0) num = -i;
            else num = i;
            sum3 += num;
            if (sum3 >= 100) {
                break;
            }
        }
        System.out.println(i);

        for(i=1; i<=5; i++){
            for(int j=1; j<=5; j++){
                System.out.print("*");
            }
            System.out.println();
        }

        Scanner scan = new Scanner(System.in);
        System.out.print("숫자를 입력하세요: ");
        int ea = scan.nextInt();
        // int ea = Integer.parseInt(scan.nextLine());

        for(i=1; i<=ea; i++){
            for(int j=1; j<=i; j++){
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();

        System.out.print("숫자를 입력하세요: ");
        ea = scan.nextInt();
        for(i=1; i<=ea; i++){
            for(int j=0; j<=ea-i; j++){
                System.out.print("*");
            }
            System.out.println();
        }

        for(i=2; i<=9; i++){
            int cnt = 0;
            System.out.printf("===%s단===\n",i);
            for(int j=1; j<=9; j++){
                System.out.printf("%d * %d = %d\n",i,j,i*j);
                cnt++;
                if(cnt==3){
                    System.out.println();
                    cnt = 0;
                }
            }
        }

        String str = "1234";
        int sum = 0;
        for(int j=0; j<str.length();j++){
            sum += str.charAt(j)-'0';
        }
        System.out.println(sum);
        
        for(int n=1; n<=3; n++){
            for(int m=1; m<=3; m++){
                for(int k=1; k<=3; k++){
                    // System.out.printf("%d%d%d\n",n,m,k);
                    // System.out.println(String.valueOf(n)+m+k);
                    // System.out.println(100*n+10*m+k);
                    System.out.println(""+n+m+k);
                }
            }
        }

        for(int n=1; n<=5; n++){
            for(int m=1; m<n; m++){
                System.out.print("     ");
            }
            System.out.printf("[%d,%d]\n",n,n);
        }

        for(int n=1; n<=5; n++){
            for(int m=1; m<=5; m++){
                if(n==m) System.out.printf("[%d,%d]",n,m);
                else System.out.print("     ");
            }
            System.out.println();
        }
*/

        /*// while 문 131p
        // while(조건식) 조건식이 false가 되면 while문 종료
        int n = 0;
        int m = 0;
        while (n<5){
            m=0;
            while (m<=n) {
                System.out.print("*");
                m++;
            }
            System.out.println("");
            n++;
        }
        System.out.println();

        Scanner scan2 = new Scanner(System.in);
        String ip ="";
        int speed = 0;
        while (!(ip.equals("3"))) {
            System.out.println("-------------------");
            System.out.println("1.증속 2.감속 3.중지");
            System.out.println("-------------------");
            System.out.print("선택: ");
            ip = scan2.nextLine();

            if(ip.equals("1")){
                speed++;
                System.out.println("현재속도: "+speed);
            }
            else if (ip.equals("2")){
                speed--;
                System.out.println("현재속도: "+speed);
            }
            else if (ip.equals("3")){
                System.out.println("프로그램 종료");
            }
            else System.out.println("올바른 값을 입력해주세요");
        }
        System.out.println();
    
        String inputString;
        Scanner scanner = new Scanner(System.in);
        do{
            System.err.print(">");
            inputString = scanner.nextLine();
            System.out.println(inputString);
        } while(!inputString.equals("q"));

        System.out.println("시스템 종료");
        System.out.println();

        int i = 0;
        while (i<=10) {
            i++;
            if(i%2==1) continue; // 다시 반복 
            System.out.println(i);
        }
        System.out.println();
    
        Scanner scan3 = new Scanner(System.in);
        int UDnum = (int)(Math.random()*100) + 1;
        System.out.println(UDnum);
        int tryNum = 0;
        System.out.print("1에서 100 사이의 숫자를 입력하세요: ");
        while (true) {
            int answer = scan3.nextInt();
            if(UDnum == answer){
                System.out.println("정답입니다. 오답횟수:"+tryNum);
                break;
            }
            tryNum++;

            if(UDnum<answer){
                System.out.print("더 작은 수를 입력하세요: ");
            }
            else{
                System.out.print("더 큰 수를 입력하세요: ");
            }
        }

        int answer = 0;
        tryNum = 0;
        do{
            System.out.print("1에서 100 사이의 숫자를 입력하세요: ");
            answer = scan3.nextInt();
            if(UDnum<answer){
                System.out.println("더 작은 수를 입력하세요");
                tryNum++;
            }
            else if(UDnum>answer){
                System.out.println("더 큰 수를 입력하세요");
                tryNum++;
            }
        } while(UDnum!=answer);
        System.out.println("정답입니다. 오답횟수:"+tryNum);
    */
    
        /*// 구구단
        for(int i=1;i<=9;i++){
            System.out.printf("---------------%d단----------------\n",i);
            for(int j=1;j<=9;j++){
                System.out.printf("%d X %d = %2d  ",i,j,i*j);
                if(j%3==0) System.out.println();
            }
            System.out.println("----------------------------------");
        }
        
        for(int n=1; n<=9; n+=3){
            for(int i=1; i<=9; i++){
                for(int j=n; j<n+3; j++){
                    System.out.printf("%d X %d = %2d  ",j,i,i*j);
                }
                System.out.println();
            }
            System.out.println();
        }
    */
    
        // 369게임
        Scanner scan369 = new Scanner(System.in);
        int number = 30;
        while(true){
            number++;

            int number_check = number;
            int cnt = 0;
            boolean check_369 = false;
            while (number_check!=0) {
                if(number_check%10 == 3 || number_check%10 == 6 || number_check%10 == 9){
                    check_369 = true;
                    cnt++;
                }
                number_check /= 10;
            }

            if (!check_369) System.out.println("컴퓨터: "+number);
            else {
                System.out.print("컴퓨터: "+"짝".repeat(cnt));
                System.out.println();
            }

            number++;
            
            number_check = number;
            check_369 = false;
            cnt = 0;
            while (number_check!=0) {
                if(number_check%10 == 3 || number_check%10 == 6 || number_check%10 == 9){
                    check_369 = true;
                    cnt++;
                }
                number_check /= 10;
            }

            String correctAnswer = "";
            if (check_369){
                correctAnswer = "짝".repeat(cnt);
            }
            else correctAnswer = String.valueOf(number);

            System.err.print("나: ");
            String inputNumber = scan369.nextLine();
            if(inputNumber.equals(correctAnswer)) continue;
            else break;
            
        }System.out.println("컴퓨터의 승리입니다!");


    }
    
}

