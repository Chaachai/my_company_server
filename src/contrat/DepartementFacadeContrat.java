/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrat;

import bean.Departement;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hamza
 */
public interface DepartementFacadeContrat extends Remote{
    public List<Departement> ggetAllDepartements() throws RemoteException ;
    public int updateDep(String numero, String nom, Long respo_id) throws RemoteException;
    public void supprimerDepartement(String ID_departement) throws RemoteException ;
    public void ajouterDepartement(String ID_dep, String nom_Dep, Long ID_respo) throws RemoteException ;
    
}
