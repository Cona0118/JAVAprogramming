package July.Week2;

import java.util.Scanner;

public class J0710 {
    public static void main(String[] args) {
        // 자판기
        Scanner scan = new Scanner(System.in);
        System.out.println("자판기가 켜졌습니다.");
        String[] menu = {"콜라","사이다","커피"};
        int[] price = {2000,1500,1800}; 
        int[] stock = {10,1,0};
        int money = 0;
        int cnt = 0;
        String password = "0000";

        while (true) {
            System.out.println();
            System.out.println("====================[메뉴]====================");
            for(int i=0; i<menu.length;i++){
                if(stock[i]>0) System.out.printf("%d.%s(%4d원) ",i+1,menu[i],price[i]);
                else System.out.printf("%d.%s(품절) ",i+1,menu[i]);
                cnt ++;
                if(cnt%3==0) System.out.println();
                
            }
            if(cnt%3!=0) System.out.println();
            cnt=0;
            System.out.println("7.동전투입     8.거스름돈반환   9.관리자모드");
            System.out.printf("현재 투입 금액: %5d           0.자판기종료\n",money);
            System.out.println("==============================================");
            System.out.print("버튼을 눌러주세요> ");
            String op = scan.nextLine();

            if (Integer.parseInt(op)>=1 && Integer.parseInt(op)<=menu.length){
                if(money>=price[Integer.parseInt(op)-1] && stock[Integer.parseInt(op)-1]>0){
                    money -= price[Integer.parseInt(op)-1];
                    stock[Integer.parseInt(op)-1] -= 1;
                    System.out.printf("%s가 나왔습니다. 남은 금액: %d\n",menu[Integer.parseInt(op)-1],money);
                }
                else if (stock[Integer.parseInt(op)-1]<=0) System.out.println("재고가 부족합니다.");
                else System.out.println("투입 금액이 부족합니다.");
            }
            else if(op.equals("7")){
                System.out.print("얼마를 투입하시겠습니까? ");
                int InputMoney = Integer.parseInt(scan.nextLine());
                money += InputMoney;
                System.out.printf("%s원이 추가되었습니다. 현재 금액:%s\n",InputMoney,money);
            }
            else if(op.equals("8")){
                if(money>0){
                    System.out.printf("거스름돈을 드리겠습니다. %d원이 반환됩니다.\n",money);
                    money = 0;
                }
                else System.out.println("투입 금액이 없습니다.");
                
            }
            else if(op.equals("9")){
                System.out.print("비밀번호를 입력해주세요: ");
                if(password.equals(scan.nextLine())){
                    while (true) {
                        System.out.println();
                        System.out.println("=============[관리자 모드]============");
                        System.out.println("91.재고변경   92.금액변경   93.메뉴변경");
                        System.out.println("94.메뉴추가   95.메뉴제거   96.모드종료");
                        System.out.println("=====================================");
                        System.out.print("버튼을 눌러주세요> ");
                        String ManagerOP = scan.nextLine();

                        if(ManagerOP.equals("91")){
                            System.out.println();
                            System.out.println("=====================[재고 변경]====================");
                            for(int i=0; i<menu.length;i++){
                                System.out.printf("%d.%s(%3d개) ",i+1,menu[i],stock[i]);
                                cnt ++;
                                if(cnt%3==0) System.out.println();
                            }
                            if(cnt%3!=0) System.out.println();
                            cnt=0;
                           
                            System.out.println("=====================================================");
                            System.out.print("재고를 변경할 메뉴를 선택해주세요> ");
                            int stockOP = Integer.parseInt(scan.nextLine());
                            System.out.print("변경 수량을 입력해주세요> ");
                            int amount = Integer.parseInt(scan.nextLine());
                            if(amount>=0){
                                stock[stockOP-1] = amount;
                                System.out.printf("재고 변경이 완료되었습니다. %s의 현재 재고 : %d개\n",menu[stockOP-1],stock[stockOP-1]);
                            }
                            else System.out.println("올바른 값을 입력해주세요");
                        }
                        else if(ManagerOP.equals("92")){
                            System.out.println();
                            System.out.println("====================[금액 변경]====================");
                            for(int i=0; i<menu.length;i++){
                                System.out.printf("%d.%s(%4d원) ",i+1,menu[i],price[i]);
                                cnt ++;
                                if(cnt%3==0) System.out.println();
                            }
                            if(cnt%3!=0) System.out.println();
                            cnt=0;
                            System.out.println("==================================================");
                            System.out.print("금액을 변경할 메뉴를 선택해주세요> ");
                            int priceOP = Integer.parseInt(scan.nextLine());
                            System.out.print("변경 금액을 입력해주세요> ");
                            int changePrice = Integer.parseInt(scan.nextLine());
                            if (changePrice>=0) {
                                price[priceOP-1] = changePrice;
                                System.out.printf("금액 변경이 완료되었습니다. %s의 현재 금액 : %d원\n",menu[priceOP-1],price[priceOP-1]);
                            }
                            else System.out.println("가격은 0보다 낮아질 수 없습니다.");
                            
                        }
                        else if(ManagerOP.equals("93")){
                            System.out.println();
                            System.out.println("=================[메뉴 변경]=================");
                            for(int i=0; i<menu.length;i++){
                                System.out.printf("%d.%s(%4d원) ",i+1,menu[i],price[i]);
                                cnt ++;
                                if(cnt%3==0) System.out.println();
                            }
                            if(cnt%3!=0) System.out.println();
                            cnt=0;
                            System.out.println("============================================");
                            System.out.print("변경할 메뉴를 선택해주세요> ");
                            int menuOP = Integer.parseInt(scan.nextLine());
                            String oldMenu = menu[menuOP-1];
                            System.out.print("변경 메뉴를 입력해주세요> ");
                            menu[menuOP-1] = scan.nextLine();
                            System.out.printf("메뉴가 변경되었습니다. 기존메뉴: %s -> 신규메뉴: %s\n",oldMenu,menu[menuOP-1]);

                        }
                        else if(ManagerOP.equals("94")){
                            if(menu.length<6){
                                System.out.print("추가할 메뉴를 입력해주세요> ");
                                String newMenu = scan.nextLine();
                                System.out.print("추가할 메뉴의 가격을 입력해주세요> ");
                                int newMenuPrice = Integer.parseInt(scan.nextLine());
                                String[] newMenuArr = new String[menu.length+1];
                                int[] newPriceArr = new int[price.length+1];
                                int[] newStockArr = new int[stock.length+1];
                                for(int i=0; i<menu.length;i++) {
                                    newMenuArr[i] = menu[i];
                                    newPriceArr[i] = price[i];
                                    newStockArr[i] = stock[i];
                                }
                                newMenuArr[menu.length] = newMenu;
                                newPriceArr[price.length] = newMenuPrice;
                                newStockArr[stock.length] = 0;
                                menu = newMenuArr;
                                price = newPriceArr;
                                stock = newStockArr;
                                System.out.printf("%s 메뉴 추가가 완료되었습니다.\n",newMenu);

                            }
                            else System.out.println("더 이상 메뉴를 추가할 수 없습니다.");
                        }
                        else if(ManagerOP.equals("95")){
                            if(menu.length>0){
                                System.out.println();
                                System.out.println("=================[메뉴 제거]=================");
                                for(int i=0; i<menu.length;i++){
                                    System.out.printf("%d.%s(%4d원) ",i+1,menu[i],price[i]);
                                    cnt ++;
                                    if(cnt%3==0) System.out.println();
                                }
                                if(cnt%3!=0) System.out.println();
                                cnt=0;
                                System.out.println("============================================");
                                System.out.print("제거할 메뉴를 선택해주세요> ");
                                int delMenu = Integer.parseInt(scan.nextLine());
                                String delMenuName = menu[delMenu-1];
                                String[] delMenuArr = new String[menu.length-1];
                                int[] delPriceArr = new int[price.length-1];
                                int[] delStockArr = new int[stock.length-1];
                                for(int i=0; i<delMenu-1; i++){
                                    delMenuArr[i] = menu[i];
                                    delPriceArr[i] = price[i];
                                    delStockArr[i] = stock[i];
                                }
                                for(int i=delMenu; i<menu.length; i++){
                                    delMenuArr[i-1] = menu[i];
                                    delPriceArr[i-1] = price[i];
                                    delStockArr[i-1] = stock[i];
                                }
                                menu = delMenuArr;
                                price = delPriceArr;
                                stock = delStockArr;
                                System.out.printf("%s 메뉴가 제거되었습니다.\n",delMenuName);
                            }
                            else System.out.println("더 이상 메뉴를 제거할 수 없습니다.");

                        }
                        else if(ManagerOP.equals("96")){
                            System.out.println("관리자 모드를 종료합니다.");
                            break;
                        }
                        else System.out.println("올바른 메뉴를 선택해주세요");
                    }
                }
                else System.out.println("비밀번호가 일치하지 않습니다.");
            }
            else if(op.equals("0")) {
                if(money>0){
                    System.out.printf("투입 금액이 남아있습니다. %d원이 반환됩니다.\n",money);
                    money = 0;
                    System.out.println("자판기가 종료되었습니다.");
                    break;
                }
                else{
                    System.out.println("자판기가 종료되었습니다.");
                    break;
                }
            }
            else System.out.println("다른 메뉴를 선택해주세요.");
        }


    }

}
