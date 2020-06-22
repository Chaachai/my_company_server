/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrat;

import bean.BoiteMail;
import bean.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author hamza
 */
public interface MessagesFacadeContrat extends Remote{
    public void supprimerMessage(Long ID_message) throws RemoteException;
    public int envoyerMessage(Salarie sal,String objet, String message, String login) throws RemoteException ;
    public List<BoiteMail> getMessagesByRecieverID(Salarie sal) throws RemoteException;
    public List<BoiteMail> getMessagesBySenderID(Salarie sal) throws RemoteException;
    
    
}
