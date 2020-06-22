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
public class DemandeAvance implements Serializable{

    private Long id;
    private String commentaire;
    private Date date;
    private EtatDemande etat;
    private Salarie salarie;
    private int pourcentage;
    private Mois mois;

    public DemandeAvance() {
    }

    public DemandeAvance(Long id) {
        this.id = id;
    }

    public DemandeAvance(Long id, String commentaire, Date date, int pourcentage) {
        this.id = id;
        this.commentaire = commentaire;
        this.date = date;
        this.pourcentage = pourcentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Mois getMois() {
        return mois;
    }

    public void setMois(Mois mois) {
        this.mois = mois;
    }

    @Override
    public String toString() {
        return "DemandeAvance{" + "id=" + id + ", commentaire=" + commentaire + ", date=" + date + ", pourcentage=" + pourcentage + ", mois=" + mois + '}';
    }

}
