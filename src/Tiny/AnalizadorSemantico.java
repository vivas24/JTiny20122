package Tiny;

import ast.NodoBase;
import ast.NodoCallFuncion;
import ast.NodoFuncion;
import ast.NodoIf;
import ast.NodoOperacion;

public class AnalizadorSemantico {
    private NodoBase root;
    public AnalizadorSemantico(NodoBase root){
        this.root=root;
    }
    public boolean AnalizarIf(){
        NodoBase aux = this.root;
        NodoBase aux2;
        NodoOperacion auxOp;
        while(aux!=null){
            if(aux instanceof NodoIf){
                aux2 = ((NodoIf)aux).getPrueba();
                if(aux2 instanceof NodoOperacion){
                    auxOp = (NodoOperacion)aux2;
                    switch(auxOp.getOperacion()){
                        case diferente:
                        case igual:
                        case mayor:
                        case menor:
                        case mayorIgual:
                        case menorIgual:
                        case ylogico:
                        case ologico:
                             break;
                        default: return false;
                    }
                }else
                    return false;
            }
            aux = aux.getHermanoDerecha();
        }
        return true;
    }
    
    public boolean AnalizarFunciones(){
        boolean retorno=true,encontrada;
        NodoBase aux = this.root;
        NodoBase aux2;
        while(aux!=null){
            if(aux instanceof NodoCallFuncion){
                    aux2 = this.root;
                    encontrada =false;
                    while(aux2!=null){
                        if(aux2 instanceof NodoFuncion){
                            System.out.println("ANALISIS SEMANTICO PARA: "+((NodoFuncion)aux2).getNombre()); 
                            if(((NodoFuncion)aux2).getNombre().equals(((NodoCallFuncion)aux).getNombre()))
                            {    
                                encontrada=true;
                            }
                        }
                        aux2 = aux2.getHermanoDerecha();
                    }
                    if(!encontrada){
                        retorno=false;
                    }
            }
            aux = aux.getHermanoDerecha();
        }
        return retorno;
    }
    
    
}
