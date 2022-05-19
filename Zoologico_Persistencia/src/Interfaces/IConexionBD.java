/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import com.mongodb.client.MongoDatabase;

/**
 *
 * @author A515-52
 */
public interface IConexionBD {

    /**
     * Interfaz que implementa el metodo crear conexion
     * @return 
     */
    MongoDatabase crearConexion();
}
