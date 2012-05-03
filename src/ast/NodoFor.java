package ast;
public class NodoFor extends NodoBase{
    private NodoBase asignacion;
    private NodoBase prueba;
    private NodoBase expresion;
    private NodoBase cuerpo;
    public NodoFor(NodoBase asignacion,NodoBase prueba,NodoBase expresion,NodoBase cuerpo){
        this.asignacion=asignacion;
        this.prueba=prueba;
        this.expresion=expresion;
        this.cuerpo=cuerpo;
    }
    public NodoBase getAsigancion(){return this.asignacion;}
    public NodoBase getPrueba(){return this.prueba;}
    public NodoBase getExpresion(){return this.expresion;}
    public NodoBase getCuerpo(){return this.cuerpo;}
}
