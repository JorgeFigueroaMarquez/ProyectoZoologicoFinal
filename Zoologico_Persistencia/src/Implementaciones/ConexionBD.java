/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.IConexionBD;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author A515-52
 */
public class ConexionBD implements IConexionBD{

    private static final String HOST = "localhost";
    private static final int PUERTO = 27017;
    private static final String BASE_DATOS = "zoologicobd";
    
    /**
     * Metodo que crea la conexion 
     * @return 
     */
    public MongoDatabase crearConexion() {
       try{
            //CONFIGURACIÓN PARA QUE MONGODRIVE REALICE EL MAPEO DE POJOS AUMATICAMENTE
            CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
            
            CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
            
            ConnectionString cadenaConexion = new ConnectionString("mongodb://"+HOST+"/"+PUERTO);
            
            MongoClientSettings clientsSettings = MongoClientSettings.builder()
                .applyConnectionString(cadenaConexion)
                .codecRegistry(codecRegistry)
                .build();
            
            MongoClient clienteMongo = MongoClients.create(clientsSettings);
            
            MongoDatabase baseDatos = clienteMongo.getDatabase(BASE_DATOS);
            
            return baseDatos;           
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            return null;
        }
    }
           
}
