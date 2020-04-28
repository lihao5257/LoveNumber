package com.number.lover.rabbitmq.consumer.model;

public class TelPhoneNumber {

    /*
     * phoneNumber 11bit
     * e.g. 13812345678
     */
    private String phoneNumber;
    
    /*
     * loverNumber 8bit
     * from right side count 8bit
     * e.g. phoneNumber 13812345678 ， loverNumber 12345678
     */
    private String loverNumber;
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getLoverNumber() {
        return loverNumber;
    }
    public void setLoverNumber(String loverNumber) {
        this.loverNumber = loverNumber;
    }
}
