package kitipoom.clickinggame;

/**
 * Created by kitipoom on 30/5/2559.
 */
public class Money {
    private int cash;
    private static Money money;

    private Money() {
        cash = 0;
    }

    public static Money getInstance() {
        if (money == null) {
            money = new Money();
        }
        return money;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash += cash;
    }
//    public int getoutcome(int level){
//        return level*30;
//    }
}
