/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;
import java.io.Serializable;


/**
 *
 * @author CHAACHAI Youssef
 */
public class Demande implements Serializable{

    private Long id;
    private String commentaire;
    private Date date;
    private EtatDemande etat;
    private Salarie salarie;
    private int type;

    public Demande() {
    }

    public Demande(Long id) {
        this.id = id;
    }

    public Demande(Long id, String message, Date date, int id_etat, Long id_salarie, int type) {
        this.id = id;
        this.commentaire = message;
        this.date = date;
        etat = new EtatDemande(id_etat);
        salarie = new Salarie(id_salarie);
        this.type = type;
    }

    public String getMessage() {
        return commentaire;
    }

    public void setMessage(String message) {
        this.commentaire = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EtatDemande getEtat() {
        return etat;
    }

    public void setEtat(EtatDemande etat) {
        this.etat = etat;
    }

    public Salarie getSalarie() {
        return salarie;
    }

    public void setSalarie(Salarie salarie) {
        this.salarie = salarie;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", commentaire=" + commentaire + ", date=" + date + ", type=" + type + '}';
    }

}
