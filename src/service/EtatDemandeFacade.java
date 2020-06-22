/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.EtatDemande;
import java.sql.ResultSet;

/**
 *
 * @author CHAACHAI Youssef
 */
public class EtatDemandeFacade {
    Config c = new Config();
    
    public EtatDemande findEtatById(int id){
        ResultSet rs = c.loadData("SELECT * FROM etatDemande WHERE id = "+id);
        EtatDemande etat = new EtatDemande();
        
        if(rs != null){
            try {
                while (rs.next()) {
                    etat.setId(rs.getInt(1));
                    etat.setLibelle(rs.getString(2));
                }
                return etat;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        }else {
            return null;
        }
    }
    
}
