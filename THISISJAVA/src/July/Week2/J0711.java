package July.Week2;

import java.util.Calendar;
import java.util.Scanner;

public class J0711 {
/*  // 메서드 
    public static int sum(int x, int y){
        // int result = x + y;
        // return result;
        return x+y;
    }
    public static int sub(int x, int y){
        int result = x - y;
        return result;
    }
    public static int mul(int x, int y){
        int result = x * y;
        return result;
    }
    public static float div(int x, int y){
        float result = (float)x / y;
        return result;
    }

    public static void main(String[] args) {
        int x = 3;
        int y = 9;
        System.out.println(sum(x, y));
        System.out.println(sub(x, y));
        System.out.println(mul(x, y));
        System.out.println(div(x, y));
        System.out.println();

    }
*/
    
    // 열거 타입 Enum 196p
    public enum Week{
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday,
        Sunday
    }

    public static void main(String[] args) {
    /*  // 열거 타입
        Week today = null;
        Calendar cal = Calendar.getInstance();
        int week = cal.get(Calendar.DAY_OF_WEEK);

        switch (week) {
            case 1: today = Week.Sunday; break;
            case 2: today = Week.Monday; break;
            case 3: today = Week.Tuesday; break;
            case 4: today = Week.Wednesday; break;
            case 5: today = Week.Thursday; break;
            case 6: today = Week.Friday; break;
            case 7: today = Week.Saturday; break;
            default: break;
        }

        if(today == Week.Sunday){
            System.out.println("일요일");
        }
        else{
            System.out.println("그외");
        }
    */

    /*  // 배열 항목 반복을 위한 for 문 191p
        int[] scores = {10,20,30,40,50};
        for(int score : scores) System.out.print(score+" ");
        System.out.println();

        // 확인문제 201p 7번
        int[] array7 = {1,5,4,8,2};
        int maxV = -1;
        for(int i=0;i<array7.length;i++){
            if(maxV<array7[i]) maxV = array7[i];
        }
        System.out.println("최댓값: "+maxV);

        // 8번
        int[][] array8 = {
            {95,86},
            {83,92,96},
            {78,83,93,87,88}
        };
        float sumV = 0.0f;
        int cnt = 0;
        for(int i=0;i<array8.length;i++){
            for(int j=0;j<array8[i].length;j++){
                sumV += array8[i][j];
                cnt++;
            }
        }
        System.out.printf("평균: %.3f",sumV/cnt);

        // 9번
        int student = 0;
        Scanner scan9 = new Scanner(System.in);
        int[] scores = new int[0];
        while (true) {
            
            System.out.println();
            System.out.println("----------------------------------------------------");
            System.out.println("1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
            System.out.println("----------------------------------------------------");
            System.out.print("선택> ");
            String op = scan9.nextLine();
            
            if(op.equals("1")){
                System.out.print("학생수> ");
                student = Integer.parseInt(scan9.nextLine());
                scores = new int[student];
            }
            else if(op.equals("2")){
                for(int i=0; i<student; i++){
                    System.out.printf("scores[%d]:",i);
                    scores[i] = Integer.parseInt(scan9.nextLine());
                }
            }
            else if(op.equals("3")){
                for(int i=0; i<student; i++){
                    System.out.printf("scores[%d]: %d \n",i,scores[i]);
                }
            }
            else if(op.equals("4")){
                int maxScore = 0;
                int sumScore = 0;
                for(int i=0;i<student;i++){
                    if(maxScore<scores[i]) maxScore = scores[i];
                    sumScore += scores[i];
                }
                System.out.printf("최고 점수: %d\n",maxScore);
                System.out.printf("평균 점수: %.1f\n",(float)sumScore/student);
            }
            else if(op.equals("5")){
                System.out.println("프로그램 종료");
                //System.exit(0); // 프로그램 종료
                break;
            }
            else System.out.println("올바른 값을 입력해주세요");
        }
    */   

        // 빙고게임
        
        Scanner scan1 = new Scanner(System.in);

        int[] bingoNum = new int[25]; // 25칸 int 배열 생성
        for(int i=0; i<bingoNum.length;i++) bingoNum[i]=i+1; // 배열에 1~25 숫자 넣기
        for(int i=0; i<bingoNum.length;i++){
            int tmp = bingoNum[i]; // tmp에 현재 인덱스의 배열의 값 저장
            int randomNum = (int)(Math.random()*bingoNum.length); // 난수 생성(0~24)
            bingoNum[i] = bingoNum[randomNum]; // 현재 인덱스의 배열의 값을 생성된 난수와 동일한 인덱스의 배열의 값으로 교체
            bingoNum[randomNum] = tmp; // 교체한 배열의 값은 tmp에 저장해둔 원래 값으로 교체
        }
        
        int[][] bingoBoard = new int[5][5]; // 5X5 2차원 int 배열 생성
        int CNT = 0;
        for(int i=0; i<bingoBoard.length;i++){  // 세로 5번 반복
            for(int j=0; j<bingoBoard[i].length; j++){ // 가로 5번 반복
                bingoBoard[i][j] = bingoNum[CNT];
                CNT++;
            }
        }

        int postBingoCNT = 0; // 이전 빙고 수를 기록
        while (true) {
            System.out.println();
            System.out.println("===빙고게임===");
            for(int i=0; i<bingoBoard.length;i++){
                for(int j=0; j<bingoBoard[i].length; j++){
                    if(bingoBoard[i][j]==0) System.out.print("XX "); // 값이 0일경우 XX로 표시
                    else System.out.printf("%02d ",bingoBoard[i][j]); 
                }
                System.out.println();
            }
            System.out.println("==============");
            System.out.print("번호를 선택해주세요(나가기 = 0): ");
            int opNum = Integer.parseInt(scan1.nextLine());

            if(opNum == 0){
                System.out.println("빙고게임을 종료합니다.");
                System.exit(0);
            }

            boolean effectNum = false; // 입력된 숫자가 유효한 값인지 검사
            A: for(int i=0; i<bingoBoard.length;i++){ // 세로 5번 만큼 반복
                for(int j=0; j<bingoBoard[i].length; j++){ // 가로 5번 만큼 반복
                    if(opNum == bingoBoard[i][j]) { // 입력된 숫자와 일치하는 값이 있을 경우
                        bingoBoard[i][j] = 0; // 해당 값을 0으로 변경
                        effectNum = true; // bool 값을 true로 변경하여 이후 메세지가 출력 안되도록 함
                        break A;
                    }
                }
            }
            if(!effectNum){ // 입력한 숫자가 보드에 없을경우 effectNum은 그대로 false 이므로
                System.out.println("다른 번호를 선택해주세요"); // 해당 메세지 출력
            }


            int bingoCNT = 0; 
            boolean isBingo3 = true;
            boolean isBingo4 = true;
            for(int i=0; i<5;i++){
                boolean isBingo = true;
                boolean isBingo2 = true;
                for(int j=0; j<5;j++){
                    if(bingoBoard[i][j] != 0) isBingo = false; // 가로열 빙고 수 체크
                    if(bingoBoard[j][i] != 0) isBingo2 = false; // 세로열 빙고 수 체크
                }
                if(isBingo) bingoCNT++;
                if(isBingo2) bingoCNT++;

                if(bingoBoard[i][i] != 0) isBingo3 = false; // 우하향 대각선 빙고 체크
                if(bingoBoard[4-i][i] != 0) isBingo4 = false; // 우상향 대각선 빙고 체크

            }
            if(isBingo3) bingoCNT++;
            if(isBingo4) bingoCNT++;
            

            if(bingoCNT!=postBingoCNT){ // 이전보다 빙고 수가 추가되었을 경우
                System.out.println();
                System.out.println(bingoCNT+" BINGO!"); // 빙고 메세지 출력
                postBingoCNT = bingoCNT; // 빙고 수 업데이트
            }

        }
        

        
            
            
        

    

    }
}
