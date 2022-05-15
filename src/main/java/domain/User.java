package domain;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String id;
    private String pw;
    private String address;
    private Set<Payment> payments;
    private String phoneNum;

    public User(String id, String pw, String address, String phoneNum) {
        this.id = id;
        this.pw = pw;
        this.address = address;
        this.payments = new HashSet<>();
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public boolean addPayment(Payment payment){
        return payments.add(payment);
    }
    public String getId() {
        return id;
    }

    public String getPassword() {
        return pw;
    }

    public String getAddress() {
        return address;
    }
    public Set<Payment> getPayments() {
        return payments;
    }
}
