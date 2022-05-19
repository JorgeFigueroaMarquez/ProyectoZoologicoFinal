/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Implementaciones.ConexionBD;
import Implementaciones.HabitatDAO;
import Interfaces.IConexionBD;
import Interfaces.IHabitatDAO;
import Interfaces.INegocios;
import entidades.Habitat;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class HabitatControl implements INegocios<Habitat>{
    
    private final IHabitatDAO habitatsDAO;
    
    /**
     * Constructor que inicializa HabitatDAO
     */
    public HabitatControl(){
        this.habitatsDAO = new HabitatDAO();
    }
    
    /**
     * Metodo que recupera datos de habitat
     * @return 
     */
    @Override
    public List<Habitat> recuperarDatos() {
        try{
            return this.habitatsDAO.consultarTodos();
        }catch(Exception ex){
            System.out.println("No se pudieron recuperar los datos");
            return null;
        }
    }

    /**
     * Metodo que verifica habitat
     * @param habitat
     * @return 
     */
    @Override
    public boolean verificar(Habitat habitat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que guarda habitat
     * @param habitat
     * @return 
     */
    @Override
    public boolean guarda(Habitat habitat) {
        try{
            return this.habitatsDAO.agregar(habitat);
        }catch(Exception ex){
            System.out.println("No se pudo guardar el habitat");
            return false;
        }
    }

    /**
     * Metodo que actualiza habitat
     * @param entidad
     * @return 
     */
    @Override
    public boolean actualiza(Habitat entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina
     * @param entidad
     * @return 
     */
    @Override
    public boolean elimina(Habitat entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta habitat mediante id
     * @param idHabitat
     * @return 
     */
    @Override
    public Habitat consulta(ObjectId idHabitat) {
        try{
            return this.habitatsDAO.consultar(idHabitat);
        }catch(Exception ex){
            System.out.println("No se pudo guardar la itinerario");
            return null;
        }
    }
    
}
