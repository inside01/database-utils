/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.seb.database.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Cette classe retourne une connexion d'une base de données mysql
 * elle implemente le pattern singleton
 * @author formation
 */
public class DatabaseConnection {
    
    /*variable destinée a stocker l'instance de la connexion
    * 
    */
    private static Connection instance;
    
    /**
     * constructeur privé pour eviter de pouvoir instancier
     * la classe depuis l'exterieur
     */

    private DatabaseConnection() {
        
    }//fin de constructeur
    
    /*
    retourne un objet de type Connection
    */
    
    public static Connection getInstance() throws SQLException {
        FileInputStream fis = null;
        try {
            //instanciation d'un objet properties qui contiendra la configuration
            Properties config = new Properties();
            
            //ouverture du fichier qui contient les infos
            fis = new FileInputStream("./config/app.properties");
            
            //chargement des donnees du fichiers dans l'objet properties
            config.load(fis);
            fis.close();
            
            //recuperation des informations de configuration dans les variables
            String dbHost = config.getProperty("db.host", "localhost");
            String dbName = config.getProperty("db.name", "bibliotheque");
            String dbUser = config.getProperty("db.user", "root");
            String dbPass = config.getProperty("db.pass", "");
            
            //si l'instance est nulle on instancie une nouvelle connexion
            if(instance == null) {
                instance = DriverManager.getConnection(
                        "jdbc:mysql://"+dbHost+"/"+ dbName, dbUser, dbPass);
            }   
        } catch (IOException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        return instance;
    }
    
    
}//fin de classe
