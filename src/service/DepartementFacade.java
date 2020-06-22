/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Departement;
import bean.Salarie;
import contrat.DepartementFacadeContrat;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamza
 */
public class DepartementFacade extends UnicastRemoteObject implements DepartementFacadeContrat {

    SalarieFacade salarieFacade;
    Config c = new Config();

    public DepartementFacade() throws RemoteException{
        super();
        this.salarieFacade = new SalarieFacade();
    }

    public List<Departement> ggetAllDepartements() throws RemoteException{
        try {
            List<Departement> list = new ArrayList();
            ResultSet rs = c.loadData("SELECT * FROM Departement ");

            while (rs.next()) {
                Departement dp = new Departement();
                Salarie sal = salarieFacade.getSalarieById(rs.getLong(3));
                dp.setId(rs.getString(1));
                dp.setNom(rs.getString(2));
                dp.setResponsable(sal);
                list.add(dp);
            }
            return list;
        } catch (SQLException ex) {
                 System.out.println("getAllDepartement exeption");

//            Logger.getLogger(MessagesFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public int updateDep(String numero, String nom, Long respo_id) throws RemoteException{
        int res = c.execQuery("UPDATE Departement SET id = '" + numero + "', nom = '" + nom + "', respo_id = " + respo_id + " WHERE id = '" + numero + "' ");
        if (res == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    public void supprimerDepartement(String ID_departement) throws RemoteException{
        try {
            Connection con = c.connect();
            CallableStatement cs;
            cs = con.prepareCall("{ call deleteDepartement(?)}");
            cs.setString(1, ID_departement);
            cs.execute();

        } catch (SQLException ex) {
             System.out.println("supprimerDepartement exeption");

//            Logger.getLogger(MessagesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ajouterDepartement(String ID_dep, String nom_Dep, Long ID_respo) throws RemoteException{
        try {
            Connection con = c.connect();
            CallableStatement cs;
            cs = con.prepareCall("{ call ajouterDepartement(?,?,?)}");
            cs.setString(1, ID_dep);
            cs.setString(2, nom_Dep);
            cs.setLong(3, ID_respo);
            cs.execute();
            System.out.println("insert succes");

        } catch (SQLException ex) {
            System.out.println("exeption");
//            Logger.getLogger(SalarieFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
