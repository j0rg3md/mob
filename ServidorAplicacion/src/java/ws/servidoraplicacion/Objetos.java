/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.servidoraplicacion;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jorgelinux
 */
@XmlRootElement(name = "objetos")
public class Objetos {
    private ArrayList<Objeto> objetos = new ArrayList();

    public Objetos() {
    }
    
    @XmlElement(name = "objeto")
    public ArrayList<Objeto> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<Objeto> objetos) {
        this.objetos = objetos;
    }
    
    public int buscarNombre(String nombre){
        int indexOf = -1;
        int cont = 0;
         for(Objeto o: objetos){
             if (o.getNombre().equals(nombre)){
                 indexOf = cont;
                    System.out.println("    INDEXoF: "+indexOf);
                   System.out.println("        Nombre: "+o.getNombre());
                   
             }     
             cont++;
           }
        return indexOf;
    }
    
    public void eliminar(int indexOf){
        objetos.remove(indexOf);
    }
    
    
    
}
