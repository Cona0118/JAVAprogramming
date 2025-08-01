package July.Week2;

import java.util.Arrays;
import java.util.Scanner;

public class J0709 {
    public static void main(String[] args) {
        // 다차원 배열 178p~
        int[][] scores = { 
            {100,100,100},
            {20,20,20},
            {30,30,30},
            {40,40,40},
            {50,50,50}
        };

        System.out.println("번호\t국어\t영어\t수학\t총점\t평균");
        int sum_kr = 0;
        int sum_en = 0;
        int sum_ma = 0;
        float sum_av = 0;
        for(int i=0; i<scores.length; i++){
            System.out.printf("%3d\t",i+1);
            int sum_pr = 0;
            for(int j=0;j<scores[i].length;j++){
                System.out.printf("%3d\t",scores[i][j]);
                sum_pr += scores[i][j];
                switch (j) {
                    case 0:
                        sum_kr += scores[i][j];
                        break;
                    case 1:
                        sum_en += scores[i][j];
                        break;
                    case 2:
                        sum_ma += scores[i][j];
                        break;

                    default:
                        break;
                }
            }
            System.out.printf("%4d\t%6.2f\n",sum_pr,(float)sum_pr/scores[i].length);
            sum_av += (float)sum_pr/scores[i].length;
        }   
        System.out.println("-----------------------------------------------");
        System.out.printf("총점\t%3d\t%3d\t%3d\t %3d\t%6.2f\n",sum_kr,sum_en,sum_ma,sum_kr+sum_en+sum_ma,sum_av/scores.length);
        System.out.println();

        // 문자열 대체 161p
        String oldString = "자바 프로그래밍";
        String newString = oldString.replace("자바", "JAVA");
        System.out.println(newString);
        System.out.println();

        // 문자열 잘라내기 162p
        String ssn = "880815-1234567";
        String FNum = ssn.substring(0,6); // 인덱스 0 ~ 5 까지
        String SNum = ssn.substring(7); // 인덱스 7부터 끝까지
        System.out.println(FNum);
        System.out.println(SNum);
        System.out.println();

        // 문자열 찾기 164p
        String subject = "자바 프로그래밍"; // "프로그래밍" 은 3번 인덱스부터 시작
        int idx = subject.indexOf("프로그래밍"); // 3
        System.out.println(idx); // 찾는 내용이 없으면 -1 출력
        System.out.println();

        // 문자열 분리 166p
        String board = "번호,제목,내용,성명";
        String[] arr = board.split(","); // , 을 기준으로 분리하여 배열에 저장
        System.out.println(Arrays.toString(arr));
        System.out.println(board.split(",")[0]); // 번호
        System.out.println();
  
        // 예제 : 호텔 예약 시스템
        Scanner scan1 = new Scanner(System.in);
        boolean[] roomArr = new boolean[12]; // 빈방 == false
        String[] nameArr = new String[12]; // 예약자 명 저장 배열
        String[] passwordArr = new String[12]; // 비밀번호 저장 배열

        A: while (true) {
            System.out.println();
            System.out.println("=================메뉴=================");
            System.out.println("1.예약하기 2.예약취소 3.호실보기 99.종료");
            System.out.print("Select> ");
            String op = scan1.nextLine();
            switch (op) {
                case "1":
                    System.out.print("몇번 방을 예약하시겠습니까? ");
                    int roomNum = Integer.parseInt(scan1.nextLine());
                    if (!roomArr[roomNum-1]){ // if (!false) : 빈방이면
                        System.out.print("이름을 입력해주세요: ");
                        String name = scan1.nextLine();
                        nameArr[roomNum-1] = name.substring(0,1)+"*".repeat(name.length()-2)+name.substring(name.length()-1); // 앞글자 + *이름자릿수-2 만큼 반복 + 뒷글자
                        System.out.print("비밀번호를 입력해주세요: ");
                        passwordArr[roomNum-1] = scan1.nextLine();
                        roomArr[roomNum-1] = true; // false(빈방) -> true(예약중)
                        System.out.println("예약이 완료되었습니다.");
                        break;
                    }
                    else{
                        System.out.printf("%d번 방은 예약이 불가능합니다.",roomNum);
                        break;
                    }
                case "2":
                    System.out.print("몇번 방을 예약하셨습니까? ");
                    int myroomNum = Integer.parseInt(scan1.nextLine());
                    if (roomArr[myroomNum-1]){ // if(true) : 예약중이면
                        System.out.print("비밀번호를 입력해주세요: ");
                        if(passwordArr[myroomNum-1].equals(scan1.nextLine())){
                            roomArr[myroomNum-1] = false; // true(예약중) -> false(빈방)
                            System.out.println("예약이 취소되었습니다.");
                            break;
                        }
                        else{
                            System.out.println("비밀번호가 올바르지 않습니다.");
                            break;
                        }
                        
                    }
                    else{
                        System.out.printf("%d번 방은 예약되지 않았습니다.\n",myroomNum);
                        break;
                    }

                case "3":
                    System.out.println();
                    for(int i=0;i<12;i++){
                        if (roomArr[i]){
                            System.out.printf("%2d호실 : 예약중    예약자 : %s \n",i+1,nameArr[i]);
                        }
                        else{
                            System.out.printf("%2d호실 : 예약가능 \n",i+1);
                        }
                    }
                    break;

                case "99":
                    System.out.println("시스템을 종료합니다.");
                    break A; // while 문 종료

                default:
                    System.out.println("올바른 번호를 선택해주세요.");
                    break;
            }
        }System.out.println();

        // 예제 : 로또 번호 생성 
        Scanner scan2 = new Scanner(System.in);
        System.out.print("로또 번호 세트 개수 입력 ");
        int set = Integer.parseInt(scan2.nextLine());
        System.out.println();
        for(int i=1;i<=set;i++){ // 총 과정을 로또 세트 수 만큼 반복
            int[] lotto = new int[6]; // 로또 1개의 배열 생성
            for(int j=0; j<6; j++) { // 로또 번호를 모두 채울 때 까지 반복
                boolean isDupli = true;
                while (isDupli){
                    isDupli = false;
                    int ranNum = (int)(Math.random()*45)+1; // 난수 생성
                    lotto[j] = ranNum; // J번째 인덱스에 저장
                    for(int k=0;k<j;k++){ // J-1 번 인덱스까지 중복 검사
                        if(ranNum == lotto[k]) isDupli = true; // 중복 발견되면 난수 생성부터 다시   
                    }
                }
            }
            Arrays.sort(lotto); // 오름차순 정렬
            System.out.printf("세트%3d: %2d %2d %2d %2d %2d %2d \n",i,lotto[0],lotto[1],lotto[2],lotto[3],lotto[4],lotto[5]);
        }
        
        
    }
}
