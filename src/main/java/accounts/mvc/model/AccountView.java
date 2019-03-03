/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.mvc.model;

import java.util.Date;

/**
 *
 * @author Mardan Safarov & Emil Kalbaliyev
 */
public class AccountView {

    //person
    private String name;
    private String surname;
    private String patronymic;
    private String pin;
    //account
    private int id;
    private int amount;
    //card
    private String pan;
    private String ownerName;
    private String ownerSurname;
    private String expDate;
    private String discreationaryData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the discreationaryData
     */
    public String getDiscreationaryData() {
        return discreationaryData;
    }

    /**
     * @param discreationaryData the discreationaryData to set
     */
    public void setDiscreationaryData(String discreationaryData) {
        this.discreationaryData = discreationaryData;
    }
}
