/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Implementaciones.ConexionBD;
import Implementaciones.CuidadoresDAO;
import Implementaciones.EspeciesDAO;
import Implementaciones.GuiaDAO;
import Implementaciones.HabitatDAO;
import Implementaciones.ZonaDAO;
import Interfaces.ICuidadoresDAO;
import Interfaces.IGuiaDAO;
import Interfaces.IHabitatDAO;
import Interfaces.IZonaDAO;
import entidades.Cuidador;
import entidades.Especie;
import entidades.Guia;
import entidades.Habitat;
import entidades.Zona;
import java.util.Arrays;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
   // IHabitatDAO habitatsDAO = new HabitatDAO(new ConexionBD()); //String nombre, String clima, String vegetacionPredominate, List<String> continente
    //Habitat habitat = new Habitat("Desierto", "Cálido", "Cactus", Arrays.asList("Ámerica", "África"));
  //  Direccion direccion = new Direccion("6 de abril", "150", "Centro");
 //   direccion.generarId();
    
 //   restaurante.setDireccion(direccion);
   // restaurante.addIdRepartidor(new ObjectId("62717ebdf7ac818a0d00a450"));
    //restaurante.addIdRepartidor(new ObjectId("62717ebdf7ac818a0d00a451"));
   // restaurantesDAO.agregar(restaurante);
   
//   Especie especie = new Especie();
//   especie.setNombreEspañol("Tigre");
//   especie.setNombreCientifico("Tigris");
//   especie.setDescripcionGeneral("Felino de gran pelaje");
//   
//   EspeciesDAO especiesDAO = new EspeciesDAO();
//   especiesDAO.agregar(especie);
        
//        IHabitatDAO habitatsDAO = new HabitatDAO();
//        System.out.println(habitatsDAO.consultarTodos());
        

//        ICuidadoresDAO cuidadoresDAO = new CuidadoresDAO();
//        
//        Cuidador cuidador = new Cuidador();
//        
//        cuidador.setNombre("Maik");
//        cuidador.setDireccion("Avenida Itesca 3377");
//        cuidador.setTelefono("64556112145");
//        
//        cuidadoresDAO.agregar(cuidador);


//        IZonaDAO zonasDAO = new ZonaDAO();
//        
//        Zona zona = new Zona();
//        zona.setNombre("Rosa");
//        zona.setExtension(150);
//        
//        zonasDAO.agregar(zona);
        

            Guia guia = new Guia();
            guia.setNombre("Miguel");
            guia.setDireccion("Villa Itesca");
            IGuiaDAO guiasDAO = new GuiaDAO();
            
            guiasDAO.agregar(guia);
            
            
    }
    
}
