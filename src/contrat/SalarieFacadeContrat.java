/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrat;

import bean.Departement;
import bean.Role;
import bean.Salarie;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author HamzaPC
 */
public interface SalarieFacadeContrat extends Remote {
    
    public String echo(String input) throws RemoteException;
    public Salarie getSalarieById(Long ID) throws RemoteException;
    public int login(String login, String password) throws RemoteException ;
    public Salarie getSalarieByLogin(String login) throws RemoteException;
    public Role findRoleById(int id) throws RemoteException ;
    public Departement findDepartementById(String id) throws  RemoteException ;
    public Salarie getResponsables() throws RemoteException ;
    public int update(String nom, String prenom, String telephone, String password, Long id) throws RemoteException ;
    
}
