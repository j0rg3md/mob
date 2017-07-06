/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlgenerator;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class XMLGenerator {

    public void generate(String name, String nombre,String accion, String fecha) throws Exception{
        
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, name, null);
            document.setXmlVersion("1.0");

            //Main Node
            Element raiz = document.getDocumentElement();
            //Por cada key creamos un item que contendrá la key y el value
           
                //Item Node
                Element objetoNode = document.createElement("OBJETO"); 
                //Nombre Node
                Element nombreNode = document.createElement("NOMBRE"); 
                Text nodeNombreValue = document.createTextNode(nombre);
                nombreNode.appendChild(nodeNombreValue);      
                //Accion Node
                Element accionNode = document.createElement("ACCION"); 
                Text nodeAccionValue = document.createTextNode(accion);                
                accionNode.appendChild(nodeAccionValue);
                //Fecha Node
                Element fechaNode = document.createElement("FECHA"); 
                Text nodeFechaValue = document.createTextNode(fecha);
                fechaNode.appendChild(nodeFechaValue);
                //append keyNode and valueNode to itemNode
                objetoNode.appendChild(nombreNode);
                objetoNode.appendChild(accionNode);
                objetoNode.appendChild(fechaNode);
                //append itemNode to raiz
                raiz.appendChild(objetoNode); //pegamos el elemento a la raiz "Documento"
                           
            //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File(name+".xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            System.out.println("FICHERO CREADO");
        }

    public static void agregar(String objetos, String nombre, String accion, String fecha) {
        Document document;
       
        try {
        //Obtenemos el document del fichero XML existente
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        document = db.parse(new File("objetos.xml"));
        Element raiz = document.getDocumentElement();
        //Main Node
            //Añadimos la información a la casa ya existente
        
            //Por cada key creamos un item que contendrá la key y el value
           
                //Item Node
                Element objetoNode = document.createElement("OBJETO"); 
                //Nombre Node
                Element nombreNode = document.createElement("NOMBRE"); 
                Text nodeNombreValue = document.createTextNode(nombre);
                nombreNode.appendChild(nodeNombreValue);      
                //Accion Node
                Element accionNode = document.createElement("ACCION"); 
                Text nodeAccionValue = document.createTextNode(accion);                
                accionNode.appendChild(nodeAccionValue);
                //Fecha Node
                Element fechaNode = document.createElement("FECHA"); 
                Text nodeFechaValue = document.createTextNode(fecha);
                fechaNode.appendChild(nodeFechaValue);
                //append keyNode and valueNode to itemNode
                objetoNode.appendChild(nombreNode);
                objetoNode.appendChild(accionNode);
                objetoNode.appendChild(fechaNode);
                //append itemNode to raiz
        String tamaño = raiz.getNodeValue();
        System.out.println(tamaño+" ESTE ES EL"
                + "");
        //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File(objetos+".xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            System.out.println("FICHERO ACTUALIZADO");
    } catch (ParserConfigurationException e) {
        e.printStackTrace();
    } catch (SAXException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }   catch (TransformerConfigurationException ex) {
            Logger.getLogger(XMLGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XMLGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
         

    }
    

}