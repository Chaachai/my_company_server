/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;
import java.io.Serializable;

/**
 *
 * @author CHAACHAI Youssef
 */
public class Mois implements Serializable{

    private int id;
    private String mois;

    public Mois() {
    }

    public Mois(int id) {
        this.id = id;
        switch (id) {
            case 1:
                this.mois = "Janvier";
                break;
            case 2:
                this.mois = "Fevrier";
                break;
            case 3:
                this.mois = "Mars";
                break;
            case 4:
                this.mois = "Avril";
                break;
            case 5:
                this.mois = "Mai";
                break;
            case 6:
                this.mois = "Juin";
                break;
            case 7:
                this.mois = "Juillet";
                break;
            case 8:
                this.mois = "Aôut";
                break;
            case 9:
                this.mois = "Septembre";
                break;
            case 10:
                this.mois = "Octobre";
                break;
            case 11:
                this.mois = "Novembre";
                break;
            case 12:
                this.mois = "Décembre";
                break;
            default:
                break;
        }
    }

    public Mois(int id, String libelle) {
        this.id = id;
        this.mois = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    @Override
    public String toString() {
        return mois;
    }

}
