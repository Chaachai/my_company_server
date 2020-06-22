/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrat;

import bean.DemandeAugmentation;
import bean.DemandeAvance;
import bean.DemandeConge;
import bean.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hamza
 */
public interface DemandeFacadeContrat extends Remote{
     
    public String echo(String input) throws RemoteException;
    public List<DemandeAvance> getAllDemandesAvance(Salarie sal) throws RemoteException ;
    public List<DemandeConge> getAllDemandesConges(Salarie sal) throws RemoteException;
    public List<DemandeAugmentation> getAllDemandesAugmentations(Salarie sal) throws RemoteException;
    public int ajouterDemandeAugmentation(Salarie sal ,int pourcentage, String comment) throws RemoteException;
    public int ajouterDemandeAvance(Salarie sal ,int pourcentage, int mois, String comment) throws RemoteException;
    public int ajouterDemandeConge(Salarie sal ,Date date_debut, Date date_fin, String comment) throws RemoteException ;
    public List<DemandeConge> getPendingConge() throws RemoteException;
    public List<DemandeAvance> getPendingAvance() throws RemoteException;
    public List<DemandeAugmentation> getPendingAugmentation() throws RemoteException;
    public void updateDemande(String demandeName, int etat, Long id) throws RemoteException;
}
