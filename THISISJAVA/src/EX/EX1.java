package EX;

import java.util.Arrays;
import java.util.Scanner;

public class EX1 {
    public static void main(String[] args) {
    // 배열
    /*  // 문자열을 입력 받아 문자 하나하나를 배열에 넣고 검색할 문자가 문자열에 몇 개 들어가 있는지 개수와 몇 번째 인덱스에 위치하는지 인덱스를 출력하세요.         
        // ex.
        // 문자열 : application
        // 문자 : i
        // application에 i가 존재하는 위치(인덱스) : 4 8
        // i 개수 : 2

        Scanner scan1 = new Scanner(System.in);

        System.out.print("단어를 입력하세요: ");
        String str1 = scan1.nextLine();
        System.out.print("문자를 입력하세요: ");
        char char1 = scan1.nextLine().charAt(0);

        char[] arr1 = new char[str1.length()];
        for (int i=0; i<str1.length(); i++) arr1[i] = str1.charAt(i);

        int CNT = 0;
        System.out.printf("%s에 %c가 존재하는 위치(인덱스) : ",str1,char1);
        for (int i=0; i<arr1.length; i++){
            if(arr1[i] == char1) {
                System.out.print(i+" ");
                CNT++;
            }
        }
        System.out.printf("\n%c 개수 : %d",char1,CNT);
        scan1.close();
    */   

    /*  // 3이상인 홀수 자연수를 입력 받아 배열의 중간까지는 1부터 1씩 증가하여 오름차순으로 값을 넣고, 중간 이후부터 끝까지는 1씩 감소하여 내림차순으로 값을 넣어 출력하세요. 
        // 단, 입력한 정수가 홀수가 아니거나 3 미만일 경우 “다시 입력하세요”를 출력하고 다시 정수를 받도록 하세요.
        // ex. 정수 : 4
        // 다시 입력하세요.
        // 정수 : -6
        // 다시 입력하세요.
        // 정수 : 5
        // 1 2 3 2 1

        Scanner scan1 = new Scanner(System.in);
        while (true) {
            System.out.print("정수 : ");
            int num1 = scan1.nextInt();
            if (num1%2 == 0 || num1<3){
                System.out.println("다시 입력 하세요");
                continue;
            } 
            else{
                int[] arr1 = new int[2*num1-1];
                for(int i=0;i<arr1.length;i++){
                    if(i+1<=num1) arr1[i] = i+1;
                    else arr1[i] = 2*num1-1-i;
                }
                for(int i=0; i<arr1.length;i++) System.out.print(arr1[i]+" ");
                break;
            } 
        }
        scan1.close();
    */
        
    /*  // 10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고 1~10 사이의 난수를 발생시켜 배열에 초기화한 후 출력하세요.
        // ex.
        // 9 7 6 2 5 10 7 2 9 6

        int[] ranNum = new int[10];
        for(int i=0; i<ranNum.length; i++){
            ranNum[i] = (int)(Math.random()*10) + 1;
            System.out.print(ranNum[i]+" ");
        }
        System.out.println();

        // 10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고 1~10 사이의 난수를 발생시켜 배열에 초기화 후 배열 전체 값과 그 값 중에서 최대값과 최소값을 출력하세요.
        // ex. 5 3 2 7 4 8 6 10 9 10
        // 최대값 : 10
        // 최소값 : 2

        int maxNum = 0;
        int minNum = 10;
        for(int i=0; i<ranNum.length; i++){
            if(ranNum[i]>=maxNum) maxNum = ranNum[i];
            else if(ranNum[i]<minNum) minNum = ranNum[i];
        }
        System.out.printf("최댓값: %d\n",maxNum);
        System.out.printf("최솟값: %d\n",minNum);
    */
       
    /*  // 2차원 배열에서 합계 구하기: 2차원 배열에서 모든 값의 합을 구하는 프로그램을 작성하세요.
        // 입력 예시
        // int[][] matrix = {
        //     {1, 2, 3},
        //     {4, 5, 6},
        //     {7, 8, 9}
        // };
        // 출력 예시 배열의 모든 값의 합은: 45

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int sum1 = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                sum1 +=matrix[i][j];
            }
        }
        System.out.println("배열의 모든 값의 합은: "+sum1);
    */

    /*  // 판매 데이터 분석 : 가상의 회사에서 여러 지점에서 판매된 상품의 데이터를 수집합니다. 각 지점에서 판매한 상품의 판매량을 기록한 배열이 주어지며, 
        // 각 지점의 판매량은 주간 단위로 기록됩니다. 이제 주어진 데이터를 바탕으로 다음과 같은 분석을 해야 합니다.
        // 요구 사항
        // 1. 총 판매량을 계산합니다.
        // 2. 최고 판매량을 기록한 지점과 그 지점의 판매량을 출력합니다.
        // 3. 평균 판매량을 계산하고, 그 값이 평균 이상인 지점만 출력합니다.
        // 제약 사항
        // 1. 각 지점의 판매량은 1주일(7일) 동안 기록됩니다.
        // 2. 판매량을 기록한 배열은 2차원 배열로 주어집니다. (각 행은 각 지점의 7일 간의 판매량)
        // 3. 각 지점의 판매량 데이터는 정수입니다.
        // 4. 지점의 개수와 판매량의 수는 동적으로 결정됩니다.
        // 입력 예시 :
        // int[][] salesData = {
        //     {50, 60, 70, 80, 90, 100, 110},  // 지점 1의 판매량
        //     {40, 50, 60, 70, 80, 90, 100},   // 지점 2의 판매량
        //     {30, 40, 50, 60, 70, 80, 90}     // 지점 3의 판매량
        // };
        // 출력 예시: 
        // 총 판매량: 1470
        // 가장 많이 판매된 지점: 지점 1 (판매량: 560)
        // 평균 판매량: 70.000
        // 평균 이상인 지점:
        // - 지점 1 (평균 판매량: 80.000)
        // - 지점 2 (평균 판매량: 70.000)

        int[][] salesData = {
            {55, 66, 70, 80, 98, 100, 110},  // 지점 1의 판매량
            {40, 42, 60, 10, 86, 84, 100},   // 지점 2의 판매량
            {95, 40, 51, 60, 70, 82, 99},     // 지점 3의 판매량 
            {10, 27, 22, 40, 48, 67, 72}
        };
        int[] sumSale = new int[salesData.length];
        for(int i=0; i<salesData.length; i++){
            int saleAmount = 0;
            for(int j=0; j<salesData[i].length; j++){
                saleAmount += salesData[i][j];
            }
            sumSale[i] = saleAmount;
        }
        int sumAll = 0;
        int bestSaleIDX = 0;
        for(int i=0; i<sumSale.length; i++){
            sumAll+=sumSale[i];
            if(sumSale[bestSaleIDX]<sumSale[i]) bestSaleIDX = i;

        }

        System.out.println("총 판매량: "+sumAll);
        System.out.printf("가장 많이 판매된 지점: 지점 %d (판매량: %d)\n",bestSaleIDX+1,sumSale[bestSaleIDX]);
        System.out.printf("평균 판매량: %.3f\n",(float)sumAll/(salesData.length*7));
        System.out.println("평균 이상인 지점: ");
        for(int i=0; i<sumSale.length; i++){
            if((float)sumSale[i]/7 >= (float)sumAll/(salesData.length*7)){
                System.out.printf(" - 지점 %d (평균 판매량: %.3f)\n",i+1,(float)sumSale[i]/7);
            }
        }
    */
    
    /*  // 직원 근무 시간 관리 시스템
        // 어떤 회사에서 직원들의 근무 시간을 관리하는 시스템을 개발한다고 가정해 봅시다. 회사는 각 부서마다 여러 명의 직원들이 있으며, 각 직원은 주어진 주 동안 일일 근무 시간을 기록합니다.
        
        // 요구 사항
        // 각 직원의 주간 근무 총 시간을 계산합니다.
        // 주어진 주간 근무 시간이 40시간을 초과한 직원을 찾아 그 직원의 초과 근무 시간을 계산하여 출력합니다.
        // 각 직원의 평균 근무 시간을 계산하고, 평균 근무 시간이 8시간 미만인 직원을 찾아 출력합니다.
        // 전체 부서별 주간 근무 시간의 평균을 계산하고 출력합니다.

        // 제약 사항
        // 직원 수는 각 부서마다 다릅니다.
        // 근무 시간은 1주일(7일) 동안의 시간으로 주어집니다.
        // 각 부서별로 직원들이 몇 명이 있는지 알 수 없으며, 동적으로 관리됩니다.
        // 주어진 데이터는 2차원 배열로 각 부서에 대한 근무 시간이 기록됩니다. 각 부서마다 직원 수가 다르므로, 불규칙한 2차원 배열로 처리합니다.

        // 입력 예시
        // int[][][] workingHours = {
        //     {   // 부서 1
        //         {8, 9, 8, 8, 9, 8, 7},  // 직원 1의 근무 시간
        //         {8, 8, 8, 8, 8, 8, 8}   // 직원 2의 근무 시간
        //     },
        //     {   // 부서 2
        //         {10, 10, 9, 9, 8, 7, 6}, // 직원 1의 근무 시간
        //         {8, 7, 8, 8, 9, 8, 7},   // 직원 2의 근무 시간
        //         {6, 6, 6, 6, 6, 6, 6}    // 직원 3의 근무 시간
        //     }
        // };  

        // 출력 예시
        // 부서 1:
        // - 직원 1: 총 근무 시간: 57, 초과 근무 시간: 17
        // - 직원 2: 총 근무 시간: 56, 초과 근무 시간: 16
        // 부서 2:
        // - 직원 1: 총 근무 시간: 59, 초과 근무 시간: 19
        // - 직원 2: 총 근무 시간: 55, 초과 근무 시간: 15
        // - 직원 3: 총 근무 시간: 42, 초과 근무 시간: 2

        // 평균 근무 시간이 8시간 미만인 직원:
        // - 부서 2 직원 2 (평균 근무 시간: 7.86시간)
        // - 부서 2 직원 3 (평균 근무 시간: 6.00시간)

        // 부서별 평균 근무 시간:
        // - 부서 1 평균 근무 시간: 56.5시간
        // - 부서 2 평균 근무 시간: 52.0시간

        int[][][] workingHours = {
            {   // 부서 1
                {8, 9, 8, 8, 9, 8, 7},  // 직원 1의 근무 시간
                {8, 8, 8, 8, 8, 8, 8}   // 직원 2의 근무 시간
            },
            {   // 부서 2
                {10, 10, 9, 9, 8, 7, 6}, // 직원 4의 근무 시간
                {8, 7, 8, 8, 9, 8, 7},   // 직원 5의 근무 시간
                {6, 6, 6, 6, 6, 6, 6}    // 직원 6의 근무 시간
            }
        };
        int[][] sumWorkingHours = new int[workingHours.length][];

        for(int i=0; i<workingHours.length;i++){
            sumWorkingHours[i] = new int[workingHours[i].length];
            for(int j=0; j<workingHours[i].length; j++){
                int sumHours = 0;
                for(int k=0; k<workingHours[i][j].length;k++){
                    sumHours += workingHours[i][j][k];
                }
                sumWorkingHours[i][j] = sumHours;
            }
        }

        for(int i=0; i<sumWorkingHours.length; i++){
            System.out.printf("부서 %d:\n",i+1);
            for(int j=0; j<sumWorkingHours[i].length; j++){
                System.out.printf("- 직원 %d: 총 근무시간: %d, 초과 근무시간: %d\n",j+1,sumWorkingHours[i][j],sumWorkingHours[i][j]-40);
            }
        }
        System.out.println();
        System.out.println("평균 근무 시간이 8시간 미만인 직원:");
        for(int i=0; i<sumWorkingHours.length; i++){
            for(int j=0; j<sumWorkingHours[i].length; j++){
                if((float)sumWorkingHours[i][j]/7 < 8)
                System.out.printf("- 부서 %d 직원 %d (평균 근무 시간: %.2f시간)\n",i+1,j+1,(float)sumWorkingHours[i][j]/7);
            }
        }
        System.out.println();
        System.out.println("부서별 평균 근무 시간:");
        for(int i=0; i<sumWorkingHours.length; i++){
            float sumWorkTeam = 0.0f;
            for(int j=0; j<sumWorkingHours[i].length; j++){
                sumWorkTeam += sumWorkingHours[i][j];
            }
            System.out.printf("- 부서 %d 평균 근무 시간: %.1f시간\n",i+1,sumWorkTeam/workingHours[i].length);
        }
    */    

        //






    }
}
