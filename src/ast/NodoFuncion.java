package ast;

public class NodoFuncion extends NodoBase{
    private String nombre;
    private NodoBase cuerpo;
    private int lineaFinal;
    public NodoFuncion(String nombre,NodoBase cuerpo){
        this.cuerpo=cuerpo;
        this.nombre=nombre;
    }
    public String getNombre(){return this.nombre;}
    public NodoBase getCuerpo(){return this.cuerpo;}
}
