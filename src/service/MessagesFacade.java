/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.BoiteMail;
import bean.Salarie;
import contrat.MessagesFacadeContrat;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Calendar.DATE;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Session;

public class MessagesFacade extends UnicastRemoteObject implements MessagesFacadeContrat {

    Config c = new Config();
    SalarieFacade salarieFacade;

    public MessagesFacade() throws RemoteException {
        this.salarieFacade = new SalarieFacade();
    }

    public void supprimerMessage(Long ID_message) throws RemoteException{
        try {
            Connection con = c.connect();
            CallableStatement cs;
            cs = con.prepareCall("{ call deleteMessage(?)}");
            cs.setLong(1, ID_message);
            cs.execute();

        } catch (SQLException ex) {
            Logger.getLogger(MessagesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int envoyerMessage(Salarie sal,String objet, String message, String login) throws RemoteException {
        try {
//            Salarie sal = ((Salarie) Session.getAttribut("connectedUser"));
            Salarie s = salarieFacade.getSalarieByLogin(login);
            if (sal.getId() != null && s.getId() != null) {
                Connection con = c.connect();
                CallableStatement cs;
                cs = con.prepareCall("{ call ajouterMessage(?,?,?,?,?)}");
                cs.setLong(1, c.generateId("boitemail", "ID"));
                cs.setString(2, objet);
                cs.setString(3, message);
                cs.setLong(4, sal.getId());
                cs.setLong(5, s.getId());
                cs.execute();
                System.out.println("insert succes");
                return 1;
            } else {
                return -1;
            }

        } catch (SQLException ex) {
            System.out.println("exeption");
            Logger.getLogger(SalarieFacade.class.getName()).log(Level.SEVERE, null, ex);
            return -2;
        }
    }

    public List<BoiteMail> getMessagesByRecieverID(Salarie sal) throws RemoteException {
        try {
//            Salarie sal = ((Salarie) Session.getAttribut("connectedUser"));
            List<BoiteMail> list = new ArrayList();
            ResultSet rs = c.loadData("SELECT * FROM boitemail WHERE RECIEVER_ID = '" + sal.getId() + "' ");

            while (rs.next()) {
                BoiteMail bm = new BoiteMail(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getBoolean(5));
                Salarie s1 = salarieFacade.getSalarieById(rs.getLong("SENDER_ID"));
                Salarie s2 = salarieFacade.getSalarieById(rs.getLong("RECIEVER_ID"));
                bm.setSender(s1);
                bm.setReciever(s2);
                list.add(bm);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(MessagesFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<BoiteMail> getMessagesBySenderID(Salarie sal) throws RemoteException {
        try {
//            Salarie sal = ((Salarie) Session.getAttribut("connectedUser"));
            List<BoiteMail> list = new ArrayList();
            ResultSet rs = c.loadData("SELECT * FROM boitemail WHERE SENDER_ID = '" + sal.getId() + "' ");

            while (rs.next()) {
                //System.out.println(rs.getLong(1));
                BoiteMail bm = new BoiteMail(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getBoolean(5));
                Salarie s1 = salarieFacade.getSalarieById(rs.getLong("SENDER_ID"));
                Salarie s2 = salarieFacade.getSalarieById(rs.getLong("RECIEVER_ID"));
                bm.setSender(s1);
                bm.setReciever(s2);
                list.add(bm);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(MessagesFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
