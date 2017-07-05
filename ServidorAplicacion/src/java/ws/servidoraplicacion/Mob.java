/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.servidoraplicacion;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import ws.servidoraplicacion.Objeto;
/**
 *
 * @author jorgelinux
 */
@Stateless
@Path("objeto")
public class Mob {
    
    @GET
    public String Crear(@QueryParam("nombre") String nombre,@QueryParam("accion") 
            String accion, @QueryParam("fecha") String fecha) {
        try {   
            Objeto objeto = new Objeto (nombre,accion,fecha);
            
            
        } catch (Exception ex) {
            Logger.getLogger(Mob.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            String respuesta = "Objeto creado: "+nombre;
            return respuesta;     
        }
    }

    
    
}
