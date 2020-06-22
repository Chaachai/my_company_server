/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import contrat.SalarieFacadeContrat;
import bean.Departement;
import bean.Role;
import bean.Salarie;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HamzaPC
 */
public class SalarieFacade extends UnicastRemoteObject implements SalarieFacadeContrat{
    
    /**
     *
     * @throws RemoteException
     */
    public SalarieFacade() throws RemoteException{
        super();
        
    }
   
    
    public String echo(String input) throws RemoteException{
        
        echo e = new echo();
        return e.echo(input);
    }
    private Config c = new Config();

    public int login(String login, String password) {
        ResultSet rs = c.loadData("SELECT * FROM salarie WHERE login = '" + login + "' AND password = '" + password + "' ");
        if (rs != null) {
            try {
                while (rs.next()) {
                    return 1;
                }
                return -1;
            } catch (Exception e) {
                System.out.println(e);
                return -3;
            }
        }
        return -2;
    }

    public Salarie getSalarieByLogin(String login) throws RemoteException{
        ResultSet rs = c.loadData("SELECT * FROM salarie WHERE login = '" + login + "' ");
        Salarie sal = new Salarie();
        if (rs != null) {
            try {
                while (rs.next()) {
                    sal.setId(rs.getLong(1));
                    sal.setNom(rs.getString(2));
                    sal.setPrenom(rs.getString(3));
                    sal.setLogin(rs.getString(4));
                    sal.setPassword(rs.getString(5));
                    sal.setTelephone(rs.getString(6));
                    sal.setSalaire(rs.getDouble(7));
                    Role role = findRoleById(rs.getInt(8));
                    Departement dep = findDepartementById(rs.getString(9));
                    sal.setRole(role);
                    sal.setDepartement(dep);
                }
                return sal;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } else {
            return null;
        }

    }

    public Salarie getSalarieById(Long ID) throws RemoteException {
        ResultSet rs = c.loadData("SELECT * FROM salarie WHERE ID = '" + ID + "' ");
        Salarie sal = new Salarie();
        try {
            while (rs.next()) {
                Role role = findRoleById(rs.getInt(8));
                Departement dep = findDepartementById(rs.getString(9));
                sal.setId(rs.getLong(1));
                sal.setNom(rs.getString(2));
                sal.setPrenom(rs.getString(3));
                sal.setLogin(rs.getString(4));
                sal.setPassword(rs.getString(5));
                sal.setTelephone(rs.getString(6));
                sal.setSalaire(rs.getDouble(7));
                sal.setRole(role);
                sal.setDepartement(dep);
            }
            return sal;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Role findRoleById(int id) throws RemoteException{
        ResultSet rs = c.loadData("SELECT * FROM role WHERE id = " + id);
        Role role = new Role();
        if (rs != null) {
            try {
                while (rs.next()) {
                    role.setId(rs.getInt(1));
                    role.setLibelle(rs.getString(2));
                }
                return role;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } else {
            return null;
        }
    }
     public Salarie getResponsables() throws RemoteException{
        ResultSet rs = c.loadData("SELECT * FROM salarie WHERE role_id = 2 ");
        Salarie sal = new Salarie();
        try {
            while (rs.next()) {
                Role role = findRoleById(rs.getInt(8));
                Departement dep = findDepartementById(rs.getString(9));
                sal.setId(rs.getLong(1));
                sal.setNom(rs.getString(2));
                sal.setPrenom(rs.getString(3));
                sal.setLogin(rs.getString(4));
                sal.setPassword(rs.getString(5));
                sal.setTelephone(rs.getString(6));
                sal.setSalaire(rs.getDouble(7));
                sal.setRole(role);
                sal.setDepartement(dep);
            }
            return sal;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Departement findDepartementById(String id) throws RemoteException {
        ResultSet rs = c.loadData("SELECT * FROM departement WHERE id = '" + id + "' ");
        Departement dep = new Departement();
        if (rs != null) {
            try {
                while (rs.next()) {
                    dep.setId(rs.getString(1));
                    dep.setNom(rs.getString(2));
                }
                return dep;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } else {
            return null;
        }
    }
    public void ajouterEmploye(String nom, String prenom, String login, String motPasse, String telephone, float salaire, int role, int IDdep) throws RemoteException{
        try {
            //        Salarie sal = ((Salarie) Session.getAttribut("connectedUser"));
            Connection con = c.connect();
            CallableStatement cs;
            cs = con.prepareCall("{ call ajouterEmp(?,?,?,?,?,?,?,?,?)}");
            cs.setLong(1, c.generateId("salarie", "ID"));
            cs.setString(2, nom);
            cs.setString(3, prenom);
            cs.setString(4, login);
            cs.setString(5, motPasse);
            cs.setString(6, telephone);
            cs.setFloat(7, salaire);
            cs.setInt(8, role);
            cs.setInt(9, IDdep);

            cs.execute();
            System.out.println("insert succes");

        } catch (SQLException ex) {
            System.out.println("exeption");
            Logger.getLogger(SalarieFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int update(String nom, String prenom, String telephone, String password, Long id) throws RemoteException {
        int res = c.execQuery("UPDATE Salarie SET nom = '" + nom + "', prenom = '" + prenom + "', telephone = '" + telephone + "', password = '" + password + "' WHERE id = " + id);
        if (res == 1) {
            return 1;
        } else {
            return -1;
        }
    }
    
    
}
