package com.retail.model.customer;

public abstract class User {

    String userId;
    String userName;
    boolean isActive;
    boolean isLoyal;

    public User() {}

    public User(String userId,String userName,boolean isActive) {
        this.userId = userId;
        this.userName = userName;
        this.isActive = isActive;
    }

    public String getUserId(String userId){
        return this.userId;
    }

    public String getUserName(String userName){
        return this.userName;
    }

    public boolean isLoyal() {
        return this.isLoyal;
    }
}
