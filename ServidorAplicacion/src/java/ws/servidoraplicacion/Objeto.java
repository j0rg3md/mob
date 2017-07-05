/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.servidoraplicacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlRootElement;
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


/**
 *
 * @author jorgelinux
 */
@XmlRootElement
public class Objeto {
    private String nombre;
    private String accion;
    private Date fecha;

    public Objeto(String nombre, String accion,String strFecha)  {
        this.nombre = nombre;
        this.accion = accion;
        //Conveertir string a date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        
            try {
                this.fecha = dateFormat.parse(strFecha);
            } catch (ParseException ex) {
                Logger.getLogger(Objeto.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        System.out.println("Objeto creado exitosamente");
        System.out.println("Nombre: "+nombre);
        System.out.println("Accion: "+accion);
        System.out.println("Fecha: "+strFecha);
        
    }
}
