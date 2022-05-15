package domain;

public class KakaoPay implements Payment{
    @Override
    public void pay() {
        System.out.println("카카오 페이 결제");
    }

    public String toString(){
        return "카카오 페이";
    }
}
