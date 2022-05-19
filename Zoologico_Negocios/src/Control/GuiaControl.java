/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Implementaciones.GuiaDAO;
import Interfaces.IGuiaDAO;
import Interfaces.INegocios;
import entidades.Guia;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class GuiaControl implements INegocios<Guia>{
    
    private final IGuiaDAO guiasDAO;
    
    /**
     * Constructor que inicializa GuiaDAO
     */
    public GuiaControl(){
        this.guiasDAO = new GuiaDAO();
    }
    
    /**
     * Metodo que recupera datos de guia
     * @return 
     */
    @Override
    public List<Guia> recuperarDatos() {
        try{
            return this.guiasDAO.consultarTodos();
        }catch(Exception ex){
            System.out.println("No se pudieron recuperar los datos");
            return null;
        }
    }

    /**
     * Metodo que verifica guia
     * @param guia
     * @return 
     */
    @Override
    public boolean verificar(Guia guia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que guarda guia
     * @param guia
     * @return 
     */
    @Override
    public boolean guarda(Guia guia) {
        try{
            return this.guiasDAO.agregar(guia);
        }catch(Exception ex){
            System.out.println("No se pudo guardar la itinerario");
            return false;
        }
    }

    /**
     * Metodo que actualiza guia
     * @param guia
     * @return 
     */
    @Override
    public boolean actualiza(Guia guia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina guia
     * @param guia
     * @return 
     */
    @Override
    public boolean elimina(Guia guia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta guia mediante id
     * @param idGuia
     * @return 
     */
    @Override
    public Guia consulta(ObjectId idGuia) {
        try{
            return this.guiasDAO.consultar(idGuia);
        }catch(Exception ex){
            System.out.println("No se pudo consultar el guia");
            return null;
        }
    }
    
}
