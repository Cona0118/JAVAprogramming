package July.Week4;

import java.util.Scanner;

// 이것이 자바다 Chapter6 278p~
/* // 확인문제 18
class Q18{
    public static void main(String[] args) {
        ShopService obj1 = ShopService.getInstance();
        ShopService obj2 = ShopService.getInstance();

        if(obj1==obj2) System.out.println("같은 객체입니다.");
        else System.out.println("다른 객체입니다.");
    }
}

final class ShopService{ // 자식 클래스 생성 불가 (final 을 통해 표시)
    private static ShopService SS = new ShopService();
    private ShopService(){}; // 생성자에 접근 불가

    public static ShopService getInstance(){
        return SS;
    }
}
*/

/* // 확인문제 19
class Q19{
    public static void main(String[] args) {
        Account account = new Account();

        account.setBalance(10000);
        System.out.println("현재 잔고 "+account.getBalance());

        account.setBalance(-100);
        System.out.println("현재 잔고 "+account.getBalance());

        account.setBalance(2000000000);
        System.out.println("현재 잔고 "+account.getBalance());

        account.setBalance(300000);
        System.out.println("현재 잔고 "+account.getBalance());
    }
}

class Account{
    private int balance;
    public final int MAX_BALANCE = 1000000;
    public final int MIN_BALANCE = 0;

    public void setBalance(int balance){
        if(balance >= MIN_BALANCE && balance <= MAX_BALANCE){
            this.balance = balance;
        }
    }

    public int getBalance(){
        return balance;
    }
}
*/

/* // 확인문제 20
class Q20{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String op;
        while (true) {
            System.out.println("-----------------------------------------");
            System.out.println("1.계좌생성|2.계좌목록|3.예금|4.출금|5.종료");
            System.out.println("-----------------------------------------");
            System.out.print("선택> ");
            op = scan.nextLine();
            System.out.println();

            if(op.equals("1")){
                System.out.println("--------");
                System.out.println("계좌생성");
                System.out.println("--------");
                
                System.out.print("계좌번호: ");
                String no = scan.nextLine();
                System.out.print("계좌주: ");
                String name = scan.nextLine();
                System.out.print("초기입금금액: ");
                int balance = Integer.parseInt(scan.nextLine());
                System.out.println();

                BankApplication.createAcount(no,name,balance);
                System.out.println("결과: 계좌가 생성되었습니다.");
            }
            else if(op.equals("2")){
                System.out.println("--------");
                System.out.println("계좌목록");
                System.out.println("--------");
                BankApplication.accountList();
            }
            else if(op.equals("3")){
                System.out.println("--------");
                System.out.println("  예금  ");
                System.out.println("--------");

                System.out.print("계좌번호: ");
                String no = scan.nextLine();
                System.out.print("예금액: ");
                int money = Integer.parseInt(scan.nextLine());

                BankApplication.deposit(no,money);
            }
            else if(op.equals("4")){
                System.out.println("--------");
                System.out.println("  출금  ");
                System.out.println("--------");

                System.out.print("계좌번호: ");
                String no = scan.nextLine();
                System.out.print("출금액: ");
                int money = Integer.parseInt(scan.nextLine());

                BankApplication.withdrawal(no,money);
            }
            else if(op.equals("5")){
                System.out.println("프로그램 종료");
                break;
            }
        }
    }
}

class Account{
    String accountNo;
    String name;
    int balane;
}

class BankApplication{
    static Account[] accountArr = new Account[100];
    static int i;

    static void createAcount(String No, String n, int b){
        accountArr[i] = new Account();
        accountArr[i].accountNo = No;
        accountArr[i].name = n;
        accountArr[i].balane = b;
        i++;
    }

    static void accountList(){
        for(int j=0; j<i; j++){
            System.out.printf("%15s\t%5s\t%d\n",accountArr[j].accountNo,accountArr[j].name,accountArr[j].balane);
        }
    }

    static void deposit(String no, int money){
        int n=0;
        for(int j=0; j<i; j++){
            if(accountArr[j].accountNo == no){
                n = j;
                break;
            }
        }

        if(money >= 0){
            accountArr[n].balane += money;
        }
    }

    static void withdrawal(String no, int money){
        int n=0;
        for(int j=0; j<i; j++){
            if(accountArr[j].accountNo == no){
                n = j;
                break;
            }
        }

        if(money >= 0 && accountArr[n].balane > money){
            accountArr[n].balane -= money;
            System.out.println("출금이 성공되었습니다.");
        }
        else{
            System.out.println("잔고가 부족합니다.");
        }
    }
}
*/

