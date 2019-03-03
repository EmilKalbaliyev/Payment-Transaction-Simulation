/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.mvc.model;

/**
 *
 * @author Mardan Safarov & Emil Kalbaliyev
 */
public class Card {

    private int id;
    private String PAN;
    private String name;
    private String surname;
    private String EXP_DATE;
    private String discreationaryData;
    private Account account;

    /**
     * @return the id
     */

    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the PAN
     */
    public String getPAN() {
        return PAN;
    }

    /**
     * @param PAN the PAN to set
     */
    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the EXP_DATE
     */
    public String getEXP_DATE() {
        return EXP_DATE;
    }

    /**
     * @param EXP_DATE the EXP_DATE to set
     */
    public void setEXP_DATE(String EXP_DATE) {
        this.EXP_DATE = EXP_DATE;
    }



    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
