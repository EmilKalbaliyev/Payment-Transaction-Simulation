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
public class AccJson {
    private String acc_id;
    private int amount;
    private long PAN;
    private String name;
    private String surname;
    private String expdate;
    private String dd;

    /**
     * @return the acc_id
     */
    public String getAcc_id() {
        return acc_id;
    }

    /**
     * @param acc_id the acc_id to set
     */
    public void setAcc_id(String acc_id) {
        this.acc_id = acc_id;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the PAN
     */
    public long getPAN() {
        return PAN;
    }

    /**
     * @param PAN the PAN to set
     */
    public void setPAN(long PAN) {
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
     * @return the expdate
     */
    public String getExpdate() {
        return expdate;
    }

    /**
     * @param expdate the expdate to set
     */
    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    /**
     * @return the dd
     */
    public String getDd() {
        return dd;
    }

    /**
     * @param dd the dd to set
     */
    public void setDd(String dd) {
        this.dd = dd;
    }
}
