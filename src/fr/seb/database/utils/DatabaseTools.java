/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.seb.database.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author formation
 */
public class DatabaseTools {
    
    /**
     * encode un texte
     * @param text
     * @return string
     */
    
    public static String sha1Encode(String text) {
        String encoded = "";  
        try {
            
            
            MessageDigest crypto = MessageDigest.getInstance("SHA-1");
            crypto.reset();
            crypto.update(text.getBytes("UTF-8"));
            encoded = byteToHex(crypto.digest());
            
           
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
         return encoded;
    }
    
    /*
    conversion d'un tableau de caractere en chaine hexadecimale
    
    */
    private static String byteToHex(byte[] byteArray) {
        String result = "";
        
        Formatter f = new Formatter();
        
        for(byte b : byteArray) {
            f.format("%02x", b);
        }
        
        result = f.toString();
        
        return result;
    }
    
}
