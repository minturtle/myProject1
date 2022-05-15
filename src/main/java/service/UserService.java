package service;

import domain.CardPay;
import domain.KakaoPay;
import domain.Payment;
import domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static final Map<String, User> userRepository = new HashMap<>();

    public UserService() {
        userRepository.put("root11", new User("root1", "1111",
                "경상북도 구미시 금오공과대학교 푸름관 1동 100호", "010-1111-2222"));

        userRepository.put("root12", new User("root1", "1111",
                "경상북도 구미시 거양길 200-20 101호", "010-2222-3333"));

        userRepository.put("root13", new User("root1", "1111",
                "경상북도 구미시 신평1동 고양이아파트 105동 101호", "010-3333-4444"));

        userRepository.put("root14", new User("root1", "1111",
                "경상북도 구미시 산호대교 어딘가", "010-4444-5555"));
    }

    public User login(String id, String pw){
        User findUser = userRepository.get(id);
        if(findUser == null){return null;}
        if(findUser.getPassword().equals(pw)){
            return findUser;
        }
        return null;
    }

    public void addPayment(User user, Class classType){
        Payment payment;
        if(classType.isAssignableFrom(KakaoPay.class)){
            payment = new KakaoPay();
        }else{
            payment = new CardPay();
        }

        user.addPayment(payment);
    }

}
