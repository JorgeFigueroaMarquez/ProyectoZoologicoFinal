/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Implementaciones.ZonaDAO;
import Interfaces.INegocios;
import Interfaces.IZonaDAO;
import entidades.Zona;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class ZonaControl implements INegocios<Zona>{
    
    private final IZonaDAO zonasDAO;
    
    /**
     * Constructor que inicializa ZonaDAO
     */
    public ZonaControl(){
        this.zonasDAO = new ZonaDAO();
    }
    
    /**
     * Metodo que recupera datos de zona
     * @return 
     */
    @Override
    public List<Zona> recuperarDatos() {
        try{
            return this.zonasDAO.consultarTodos();
        }catch(Exception ex){
            System.out.println("No se pudieron recuperar los datos");
            return null;
        }
    }

    /**
     * Metodo que verifica Zona
     * @param entidad
     * @return 
     */
    @Override
    public boolean verificar(Zona entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que guarda zona
     * @param zona
     * @return 
     */
    @Override
    public boolean guarda(Zona zona) {
        try{
            return this.zonasDAO.agregar(zona);
        }catch(Exception ex){
            System.out.println("No se pudo guardar la zona");
            return false;
        }
    }

    /**
     * Metodo que actualiza zona
     * @param zona
     * @return 
     */
    @Override
    public boolean actualiza(Zona zona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina zona
     * @param zona
     * @return 
     */
    @Override
    public boolean elimina(Zona zona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que actualiza
     * @param idZona
     * @return 
     */
    @Override
    public Zona consulta(ObjectId idZona) {
        try{
            return this.zonasDAO.consultar(idZona);
        }catch(Exception ex){
            System.out.println("No se pudo consultar la zona");
            return null;
        }
    }
    
}
