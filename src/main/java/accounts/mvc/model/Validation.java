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
public class Validation {
    
    private boolean isValid;
    private String msj;

    public Validation() {
    }

    public Validation(boolean isValid, String msj) {
        this.isValid = isValid;
        this.msj = msj;
    }

    /**
     * @return the isValid
     */
    public boolean isIsValid() {
        return isValid;
    }

    /**
     * @param isValid the isValid to set
     */
    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * @return the msj
     */
    public String getMsj() {
        return msj;
    }

    /**
     * @param msj the msj to set
     */
    public void setMsj(String msj) {
        this.msj = msj;
    }
}
