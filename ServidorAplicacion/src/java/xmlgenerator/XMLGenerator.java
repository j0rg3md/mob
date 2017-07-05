/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlgenerator;

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
import org.w3c.dom.Text;

public class XMLGenerator {

    public static void generate(String name, String nombre,String accion, String fecha) throws Exception{
        
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
    

}