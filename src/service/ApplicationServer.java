/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Salarie;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author HamzaPC
 */
public class ApplicationServer {
    
    public static void main(String[] args) throws RemoteException {
        
        
//        System.out.println(helloServant.echo("fin"));
//       Salarie salarie  = helloServant.getSalarieById(1l);
//        System.out.println(salarie.getLogin());
        
        Registry registry = LocateRegistry.createRegistry(5655);
        registry.rebind("salarieFacade", new SalarieFacade());
        registry.rebind("departementFacade", new DepartementFacade());
        registry.rebind("demandeFacade", new DemandeFacade());
        registry.rebind("messageFacade", new MessagesFacade());


        

        
            
    }
    
}
