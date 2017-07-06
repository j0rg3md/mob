/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.servidoraplicacion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import ws.servidoraplicacion.Objeto;
import xmlgenerator.*;
/**
 *
 * @author jorgelinux
 */
@Stateless
@Path("objeto")
public class Mob {
    
    @GET
    @Path("/eliminar")
    @Produces("plain/text")
    public String Eliminar (@QueryParam("nombre") String nombre) throws JAXBException, IOException{
         JAXBContext context = JAXBContext.newInstance(Objetos.class);
         Unmarshaller unmarshaller = context.createUnmarshaller();
         
         Objetos objetos = (Objetos) unmarshaller.unmarshal(new File("objetos.xml"));
         int indexOf = objetos.buscarNombre(nombre);
         objetos.eliminar(indexOf);
     
         JAXBContext contextW = JAXBContext.newInstance(Objetos.class);
         Marshaller marshaller = contextW.createMarshaller();
         
         marshaller.marshal(objetos, new FileWriter("objetos.xml"));
         
        return "Objeto "+nombre+" eliminado";
    }
    
    @GET
    @Path("/crear")
    @Produces("plain/json")
    public String Crear(@QueryParam("nombre") String nombre,@QueryParam("accion") 
            String accion, @QueryParam("fecha") String fecha) {
        try {   
            
            
            /*GeneradorDom generadorDom = new GeneradorDom();
            generadorDom.generarDocument(nombre,accion,fecha);
            generadorDom.generarXml();*/
            
            Objeto objeto = new Objeto (nombre,accion,fecha);
            
            //xml.generate("objetos", nombre, accion, fecha);
            JAXBContext context = JAXBContext.newInstance(Objetos.class);
            JAXBContext contextW = JAXBContext.newInstance(Objetos.class);
            Marshaller marshaller = contextW.createMarshaller();
            Unmarshaller unmarshaller = context.createUnmarshaller();
           
            Objetos objetos = (Objetos) unmarshaller.unmarshal(new File("objetos.xml"));
            ArrayList<Objeto> ListaObjetos= objetos.getObjetos();
            ListaObjetos.add(objeto);
            objetos.setObjetos(ListaObjetos);
            marshaller.marshal(objetos, new FileWriter("objetos.xml"));
            
                for(Objeto o: ListaObjetos){
                    System.out.println("    LISTA DE LIBROS");
                    System.out.println("        Nombre: "+o.getNombre());
                    System.out.println("        Accion: "+o.getAccion());
                    System.out.println("        Fecha: "+o.getFecha());
                }
                
        } catch (Exception ex) {
            Logger.getLogger(Mob.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            String respuesta = "Objeto creado: "+nombre;
            return respuesta;     
        }
    }
    
    
    
     
}
