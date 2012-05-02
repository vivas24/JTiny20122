/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

/**
 *
 * @author gerardo
 */
public class NodoDeclaracion extends NodoBase{
    private String id;
    private int tamanho;
    public NodoDeclaracion(int tamanho, String id){
        System.out.println("RECIBI: "+id);
        this.id=id;
        this.tamanho=tamanho;
    }
    public int getTamanho(){return this.tamanho;}
    public String getIdentificador(){
        System.out.println("ID: "+id);
        return this.id;
    }
    public boolean esVector(){return this.tamanho>0;}
    
}
