/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.DemandeAugmentation;
import bean.DemandeAvance;
import bean.DemandeConge;
import bean.EtatDemande;
import bean.Mois;
import bean.Salarie;
import contrat.DemandeFacadeContrat;
import contrat.DepartementFacadeContrat;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Session;

/**
 *
 * @author CHAACHAI Youssef
 */
public class DemandeFacade extends UnicastRemoteObject implements DemandeFacadeContrat {

    Config c = new Config();
    
    SalarieFacade salarieFacade;
    EtatDemandeFacade etatDemandeFacade = new EtatDemandeFacade();

    public DemandeFacade() throws RemoteException {
        this.salarieFacade = new SalarieFacade();
        
    }
    
    @Override
    public String echo(String input) throws RemoteException{
        
        echo e = new echo();
        return e.echo(input);
    }

    @Override
    public List<DemandeAvance> getAllDemandesAvance(Salarie sal) throws RemoteException {
        //Salarie sal = ((Salarie) Session.getAttribut("connectedUser"));
        ResultSet rs = c.loadData("SELECT * FROM demandeavance WHERE id_salarie = " + sal.getId() + " ORDER BY datedemande DESC");
        List<DemandeAvance> demandes = new ArrayList();
        if (rs != null) {
            try {
                while (rs.next()) {
                    DemandeAvance da = new DemandeAvance(rs.getLong(1), rs.getString(2), rs.getDate(3), rs.getInt(6));
                    EtatDemande etat = etatDemandeFacade.findEtatById(rs.getInt(4));
                    da.setEtat(etat);
                    da.setMois(new Mois(rs.getInt(7)));
                    System.out.println(da.getDate());
                    demandes.add(da);
                }
                return demandes;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } else {
            return null;
        }
    }

    public List<DemandeConge> getAllDemandesConges(Salarie sal) throws RemoteException{
//        Salarie sal = ((Salarie) Session.getAttribut("connectedUser"));
        ResultSet rs = c.loadData("SELECT * FROM demandeConge WHERE id_salarie = " + sal.getId() + " ORDER BY datedemande DESC");
        List<DemandeConge> demandes = new ArrayList();
        if (rs != null) {
            try {
                while (rs.next()) {
                    // System.out.println(rs.getInt(1) + " " + rs.getString(2));
                    DemandeConge dc = new DemandeConge(rs.getLong(1), rs.getString(2), rs.getDate(7), rs.getDate(5), rs.getDate(6));
                    EtatDemande etat = etatDemandeFacade.findEtatById(rs.getInt(3));
                    dc.setEtat(etat);
                    demandes.add(dc);
                }
                return demandes;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<DemandeAugmentation> getAllDemandesAugmentations(Salarie sal) throws RemoteException{
//        Salarie sal = ((Salarie) Session.getAttribut("connectedUser"));
        ResultSet rs = c.loadData("SELECT * FROM demandeaugmentation WHERE id_salarie = " + sal.getId() + " ORDER BY datedemande DESC");
        List<DemandeAugmentation> demandes = new ArrayList();
        if (rs != null) {
            try {
                while (rs.next()) {
                    DemandeAugmentation da = new DemandeAugmentation(rs.getLong(1), rs.getString(2), rs.getDate(3), rs.getInt(6));
                    EtatDemande etat = etatDemandeFacade.findEtatById(rs.getInt(4));
                    da.setEtat(etat);
                    demandes.add(da);
                }
                return demandes;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public int ajouterDemandeConge(Salarie sal,Date date_debut, Date date_fin, String comment) throws RemoteException{
//        Salarie sal = ((Salarie) Session.getAttribut("connectedUser"));
        Connection con = c.connect();
        Date nowDate = new Date();
        java.sql.Date sqlDateDebut = new java.sql.Date(date_debut.getTime());
        java.sql.Date sqlDateFin = new java.sql.Date(date_fin.getTime());
        java.sql.Date sqlNowDate = new java.sql.Date(nowDate.getTime());
        String query = "INSERT INTO demandeconge VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setLong(1, c.generateId("demande", "id"));
            ps.setString(2, comment);
            ps.setInt(3, 0);
            ps.setLong(4, sal.getId());
            ps.setDate(5, sqlDateDebut);
            ps.setDate(6, sqlDateFin);
            ps.setDate(7, sqlNowDate);
            ps.execute();
            ps.close();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DemandeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int ajouterDemandeAugmentation(Salarie sal ,int pourcentage, String comment) throws RemoteException{
//        Salarie sal = ((Salarie) Session.getAttribut("connectedUser"));
        Connection con = c.connect();
        Date nowDate = new Date();
        java.sql.Date sqlNowDate = new java.sql.Date(nowDate.getTime());
        String query = "INSERT INTO demandeaugmentation VALUES (?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setLong(1, c.generateId("demande", "id"));
            ps.setString(2, comment);
            ps.setDate(3, sqlNowDate);
            ps.setInt(4, 0);
            ps.setLong(5, sal.getId());
            ps.setInt(6, pourcentage);
            ps.execute();
            ps.close();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DemandeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public int ajouterDemandeAvance(Salarie sal ,int pourcentage, int mois, String comment) throws RemoteException{
//        Salarie sal = ((Salarie) Session.getAttribut("connectedUser"));
        Connection con = c.connect();
        Date nowDate = new Date();
        java.sql.Date sqlNowDate = new java.sql.Date(nowDate.getTime());
        String query = "INSERT INTO demandeavance VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ps.setLong(1, c.generateId("demande", "id"));
            ps.setString(2, comment);
            ps.setDate(3, sqlNowDate);
            ps.setInt(4, 0);
            ps.setLong(5, sal.getId());
            ps.setInt(6, pourcentage);
            ps.setInt(7, mois);
            ps.execute();
            ps.close();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DemandeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public List<DemandeConge> getPendingConge() throws RemoteException{
        ResultSet rs = c.loadData("SELECT * FROM demandeConge WHERE etat = 0 ORDER BY datedemande DESC");
        List<DemandeConge> demandes = new ArrayList();
        if (rs != null) {
            try {
                while (rs.next()) {
                    DemandeConge dc = new DemandeConge(rs.getLong(1), rs.getString(2), rs.getDate(7), rs.getDate(5), rs.getDate(6));
                    EtatDemande etat = etatDemandeFacade.findEtatById(rs.getInt(3));
                    Salarie sal = salarieFacade.getSalarieById(rs.getLong(4));
                    dc.setEtat(etat);
                    dc.setSalarie(sal);
                    demandes.add(dc);
                }
                return demandes;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<DemandeAvance> getPendingAvance() throws RemoteException{
        ResultSet rs = c.loadData("SELECT * FROM demandeAvance WHERE etat = 0 ORDER BY datedemande DESC");
        List<DemandeAvance> demandes = new ArrayList();
        if (rs != null) {
            try {
                while (rs.next()) {
                    DemandeAvance da = new DemandeAvance(rs.getLong(1), rs.getString(2), rs.getDate(3), rs.getInt(6));
                    EtatDemande etat = etatDemandeFacade.findEtatById(rs.getInt(4));
                    Salarie sal = salarieFacade.getSalarieById(rs.getLong(5));
                    da.setEtat(etat);
                    da.setSalarie(sal);
                    da.setMois(new Mois(rs.getInt(7)));
                    demandes.add(da);
                }
                return demandes;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<DemandeAugmentation> getPendingAugmentation() throws RemoteException{
        ResultSet rs = c.loadData("SELECT * FROM demandeAugmentation WHERE etat = 0 ORDER BY datedemande DESC");
        List<DemandeAugmentation> demandes = new ArrayList();
        if (rs != null) {
            try {
                while (rs.next()) {
                    DemandeAugmentation da = new DemandeAugmentation(rs.getLong(1), rs.getString(2), rs.getDate(3), rs.getInt(6));
                    EtatDemande etat = etatDemandeFacade.findEtatById(rs.getInt(4));
                    Salarie sal = salarieFacade.getSalarieById(rs.getLong(5));
                    da.setEtat(etat);
                    da.setSalarie(sal);
                    demandes.add(da);
                }
                return demandes;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void updateDemande(String demandeName, int etat, Long id) throws RemoteException{
        c.execQuery("UPDATE " + demandeName + " SET etat = " + etat + " WHERE id = " + id);
    }
}
