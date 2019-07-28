package com.retail.model.customer;

public class Affiliate extends User {

    String userId;
    String userName;
    boolean isActive;

    public Affiliate() { super();}

    public Affiliate(String userId,String userName,boolean isActive) {
        super(userId,userName,isActive);
    }

}
