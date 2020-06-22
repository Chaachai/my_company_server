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
public class DemandeConge implements Serializable{

    private Long id;
    private String commentaire;
    private Date date;
    private EtatDemande etat;
    private Salarie salarie;
    private Date dateDebut;
    private Date dateFin;

    public DemandeConge() {
    }

    public DemandeConge(Long id) {
        this.id = id;
    }

    public DemandeConge(Long id, String commentaire, Date date, Date dateDebut, Date dateFin) {
        this.id = id;
        this.commentaire = commentaire;
        this.date = date;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "DemandeConge{" + "id=" + id + ", commentaire=" + commentaire + ", date=" + date + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }

}
