package com.example.thancv.notepaid.model;

/**
 * Created by ThanCV on 8/22/2017.
 */

public class PaidModel {
    private String id;
    private String titleName;
    private String moneyPaid;

    public PaidModel() {
    }

    public PaidModel(String titleName, String moneyPaid) {
        this.titleName = titleName;
        this.moneyPaid = moneyPaid;
    }

    public PaidModel(String id, String titleName, String moneyPaid) {
        this.id = id;
        this.titleName = titleName;
        this.moneyPaid = moneyPaid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(String moneyPaid) {
        this.moneyPaid = moneyPaid;
    }
}
