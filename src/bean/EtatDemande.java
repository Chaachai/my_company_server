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
public class EtatDemande implements Serializable{
    private int id;
    private String libelle;

    public EtatDemande() {
    }

    public EtatDemande(int id) {
        this.id = id;
    }

    public EtatDemande(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
    
    
}
