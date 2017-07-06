/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlgenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author jorgelinux
 */
public class GeneradorDom {
   private Document document;
   
   public GeneradorDom() throws ParserConfigurationException{
       DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder = factoria.newDocumentBuilder();
       document = builder.newDocument();
   }
   
   public void generarDocument(String nombre, String accion, String fecha){
       Element objetos = document.createElement("objetos");
       document.appendChild(objetos);
       
       Element objeto = document.createElement("objeto");
       objetos.appendChild(objeto);
       
       Element nombreNode = document.createElement("nombre"); 
                Text nodeNombreValue = document.createTextNode(nombre);
                nombreNode.appendChild(nodeNombreValue);      
                //Accion Node
                Element accionNode = document.createElement("accion"); 
                Text nodeAccionValue = document.createTextNode(accion);                
                accionNode.appendChild(nodeAccionValue);
                //Fecha Node
                Element fechaNode = document.createElement("fecha"); 
                Text nodeFechaValue = document.createTextNode(fecha);
                fechaNode.appendChild(nodeFechaValue);
         objeto.appendChild(nombreNode);
         objeto.appendChild(accionNode);
         objeto.appendChild(fechaNode);
                
   }
   
   public void generarXml() throws TransformerConfigurationException, IOException, TransformerException{
       TransformerFactory factoria = TransformerFactory.newInstance();
       Transformer transformer = factoria.newTransformer();
       
       Source source = new DOMSource(document);
       File file = new File ("objetos.xml");
       FileWriter fw = new FileWriter(file);
       PrintWriter pw = new PrintWriter(file);
       Result result = new StreamResult(pw);
       
       transformer.transform(source,result);
   }
}
