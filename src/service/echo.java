/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.rmi.RemoteException;

/**
 *
 * @author hamza
 */
public class echo {
    public String echo(String input){
        return "From server ehooo :" + input;
    }
}
