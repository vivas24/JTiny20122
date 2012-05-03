package ast;

public class NodoCallFuncion extends NodoBase{
    private String nombre;
    public NodoCallFuncion(String nombre){
        this.nombre=nombre;
    }
    public String getNombre(){return this.nombre;}
}
