package domain;

public class CardPay implements Payment{
    @Override
    public void pay() {
        System.out.println("신용/체크카드 결제");
    }

    public String toString(){
        return "신용/체크카드 결제";
    }
}
