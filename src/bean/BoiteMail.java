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
public class BoiteMail implements Serializable{

    private Long id;
    private String objet;
    private String message;
    private Date dateEnvoi;
    private boolean lu;
    private Salarie sender;
    private Salarie reciever;

    public BoiteMail(Long id) {
        this.id = id;
    }

    public BoiteMail(Long id, String objet, String message, Date dateEnvoi, boolean lu) {
        this.id = id;
        this.objet = objet;
        this.message = message;
        this.dateEnvoi = dateEnvoi;
        this.lu = lu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BoiteMail() {
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public boolean isLu() {
        return lu;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    public Salarie getSender() {
        return sender;
    }

    public void setSender(Salarie sender) {
        this.sender = sender;
    }

    public Salarie getReciever() {
        return reciever;
    }

    public void setReciever(Salarie reciever) {
        this.reciever = reciever;
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
        if (!(object instanceof BoiteMail)) {
            return false;
        }
        BoiteMail other = (BoiteMail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BoiteMail{" + "id=" + id + ", objet=" + objet + ", message=" + message + ", dateEnvoi=" + dateEnvoi + ", lu=" + lu + '}';
    }

}
