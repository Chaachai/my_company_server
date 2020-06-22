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
public class Departement implements Serializable{

    private String id;
    private String nom;
    private Salarie responsable;

    public Departement() {
    }

    public Departement(String id) {
        this.id = id;
    }

    public Departement(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Salarie getResponsable() {
        return responsable;
    }

    public void setResponsable(Salarie responsable) {
        this.responsable = responsable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departement)) {
            return false;
        }
        Departement other = (Departement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }

}
