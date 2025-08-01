package EX;

public class EX0 {
    public static void main(String[] args) {
        String[] lunchMenu = {
            "학식",
            "중찬미식",
            "맘스터치",
            "연신내(제육)",
            "이삭토스트",
            "돈컵치컵",
            "쿠니라멘",
            "힐링돈까스",
            "엄마돈국수"
        };

        int randomNum = (int)(Math.random()*lunchMenu.length);
        System.out.println(lunchMenu[randomNum]);


    }
}
