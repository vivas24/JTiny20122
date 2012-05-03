package Tiny;

import ast.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Generador {
    /* Ilustracion de la disposicion de la memoria en
     * este ambiente de ejecucion para el lenguaje Tiny
     *
     * |t1	|<- mp (Maxima posicion de memoria de la TM
     * |t1	|<- desplazamientoTmp (tope actual)
     * |free|
     * |free|
     * |...	|
     * |x	|
     * |y	|<- gp
     * 
     * */

    /* desplazamientoTmp es una variable inicializada en 0
     * y empleada como el desplazamiento de la siguiente localidad
     * temporal disponible desde la parte superior o tope de la memoria
     * (la que apunta el registro MP).
     * 
     * - Se decrementa (desplazamientoTmp--) despues de cada almacenamiento y
     * 
     * - Se incrementa (desplazamientoTmp++) despues de cada eliminacion/carga en 
     *   otra variable de un valor de la pila.
     * 
     * Pudiendose ver como el apuntador hacia el tope de la pila temporal
     * y las llamadas a la funcion emitirRM corresponden a una inserccion 
     * y extraccion de esta pila
     */
    private static int desplazamientoTmp = 0;
    private static TablaSimbolos tablaSimbolos = null;
    public static NodoBase raizTmp = null;

    public static void setTablaSimbolos(TablaSimbolos tabla) {
        tablaSimbolos = tabla;
    }

    public static void generarCodigoObjeto(NodoBase raiz) {
        System.out.println();
        System.out.println();
        System.out.println("------ CODIGO OBJETO DEL LENGUAJE TINY GENERADO PARA LA TM ------");
        System.out.println();
        System.out.println();
        generarPreludioEstandar();
        generar(raiz);
        /*Genero el codigo de finalizacion de ejecucion del codigo*/
        UtGen.emitirComentario("Fin de la ejecucion.");
        UtGen.emitirRO("HALT", 0, 0, 0, "");
        System.out.println();
        System.out.println();
        System.out.println("------ FIN DEL CODIGO OBJETO DEL LENGUAJE TINY GENERADO PARA LA TM ------");
    }

    //Funcion principal de generacion de codigo
    //prerequisito: Fijar la tabla de simbolos antes de generar el codigo objeto 
    private static void generar(NodoBase nodo) {
        if (tablaSimbolos != null) {
            
            if (nodo instanceof NodoIf) {
                generarIf(nodo);
            } else if (nodo instanceof NodoRepeat) {
                generarRepeat(nodo);
            } else if (nodo instanceof NodoAsignacion) {
                if(!((NodoAsignacion)nodo).esVector())
                    generarAsignacion(nodo);
                else
                    generarAsignacionVector(nodo);
            } else if (nodo instanceof NodoLeer) {
                generarLeer(nodo);
            } else if (nodo instanceof NodoEscribir) {
                generarEscribir(nodo);
            } else if (nodo instanceof NodoValor) {
                generarValor(nodo);
            } else if (nodo instanceof NodoIdentificador) {
                generarIdentificador(nodo);
            } else if (nodo instanceof NodoOperacion) {
                generarOperacion(nodo);
            } else if (nodo instanceof NodoDeclaracion) {
                System.out.println("********NODO DECLARACION => NO HACE NADA");
            } else if (nodo instanceof NodoWhile) {
                generarWhile(nodo);
            } else if (nodo instanceof NodoFor) {
                generarFor(nodo);
            } else if (nodo instanceof NodoFuncion) {
                //generarFuncion(nodo);
            } else if (nodo instanceof NodoCallFuncion) {
                generarCallFuncion(nodo);
            } else {
                System.out.println("BUG: Tipo de nodo a generar desconocido");
            }
            /*Si el hijo de extrema izquierda tiene hermano a la derecha lo genero tambien*/
            if (nodo.TieneHermano()) {
                generar(nodo.getHermanoDerecha());
            }
        } else {
            System.out.println("���ERROR: por favor fije la tabla de simbolos a usar antes de generar codigo objeto!!!");
        }
    }
    
    private static void generarCallFuncion(NodoBase nodo){
        NodoCallFuncion nc = (NodoCallFuncion)nodo;
        int linea,localidadActual;
        NodoBase aux = raizTmp;
        System.out.println("****LLAMADA A: "+nc.getNombre());
        linea = tablaSimbolos.BuscarSimbolo(nc.getNombre()).getNumLinea();
        if(linea!=-1) {
            UtGen.emitirRM_Abs("LDA", UtGen.PC, linea, "ejecutar funcion");
            return;
        }
        //lineaLlamadaFuncion = UtGen.emitirSalto(0)+1;
        //System.out.println("****LLAMAR A FUNCION EN: "+lineaLlamadaFuncion);
        localidadActual = UtGen.emitirSalto(0);
        UtGen.emitirRM_Abs("LDA", UtGen.PC, localidadActual+2, "ejecutar funcion");
        while(aux!=null){
            if(aux instanceof NodoFuncion){
                if(((NodoFuncion)aux).getNombre().equals(nc.getNombre())){
                    System.out.println("*****generar funcion: "+nc.getNombre());
                    generarFuncion(aux);
                }
            }
            aux = aux.getHermanoDerecha();
                    
        }
        linea = tablaSimbolos.BuscarSimbolo(nc.getNombre()).getNumLinea();
        
    }
    
    private static void generarFuncion(NodoBase nodo){
        NodoFuncion nf = (NodoFuncion)nodo;
        int localidadActual;
        int localidadSaltoSkip=UtGen.emitirSalto(1);
        System.out.println("******LOCALIDAD SALTO SKIP: "+localidadSaltoSkip);
        int localidadInicioFuncion=UtGen.emitirSalto(0),direccion;
        System.out.println("******FUNCION******");
        System.out.println("******POSICION INICIAL DE LA FUNCION: "+localidadInicioFuncion);
        tablaSimbolos.BuscarSimbolo(nf.getNombre()).setNumLinea(localidadInicioFuncion);
/*        System.out.println("******CARGAR EN MEMORIA LA POSICION DE LA LINEA INICIAL DE LA FUNCION");
        UtGen.emitirRM("LDC", UtGen.AC, localidadInicioFuncion, 0, "cargar en el registro AC el numero de linea de "+nf.getNombre());
        direccion = tablaSimbolos.getDireccion(nf.getNombre());
        UtGen.emitirRM("ST", UtGen.AC, direccion, UtGen.GP, "guardo en memoria el numero de linea de: "+nf.getNombre());
        UtGen.emitirRM("LD", UtGen.AC, direccion, UtGen.GP, "cargar desde memoria el numero de linea de : " + nf.getNombre());
        UtGen.emitirRO("OUT", UtGen.AC, 0, 0, "linea donde esta la funcion");
         *
*/
        generar(nf.getCuerpo());
        localidadActual=UtGen.emitirSalto(0);
        System.out.println("******FIN DE LA FUNCION******");
        UtGen.cargarRespaldo(localidadSaltoSkip);
        UtGen.emitirRM_Abs("LDA", UtGen.PC, localidadActual+1, "skip funcion...");
        UtGen.restaurarRespaldo();
    }
            
    private static void generarFor(NodoBase nodo){
        NodoFor nf = (NodoFor)nodo;
        int localidadInicio,localidadActual;
        System.out.println("*****INICIO FOR*****");
        
        /*generar asignacion inicial*/
        generar(nf.getAsigancion());
        
        /*lugar del salto al inicio*/
        localidadInicio=UtGen.emitirSalto(0);
        System.out.println("******POSICION INICIO: "+localidadInicio);

        /*generar cuerpo del ciclo*/
        System.out.println("****CUERPO DEL FOR****");
        generar(nf.getCuerpo());
        System.out.println("****FIN CUERPO DEL FOR****");
        
        /*generar la expresion que aumentara la variable*/
        System.out.println("****EXPRESION DEL FOR****");
        generar(nf.getExpresion());
        System.out.println("****FIN EXPRESION FOR****");
        
        /*generar prueba*/
        System.out.println("****PRUEBA DEL FOR*****");
        generar(nf.getPrueba());
        System.out.println("****FIN PRUEBA DEL FOR****");
        
        
        /*saltar al inicio en caso de verdadero*/
        UtGen.emitirRM_Abs("JNE", UtGen.AC, localidadInicio, "saltar al inicio del ciclo...");
        
        System.out.println("****FIN DEL CICLO FOR****");
    }
    private static void generarWhile(NodoBase nodo){
        NodoWhile nw = (NodoWhile)nodo;
        int localidadSaltoFuera,localidadActual,localidadInicio;
        System.out.println("******INICIO WHILE*****");
        
        
        /*linea donde comienza el while*/
        localidadInicio=UtGen.emitirSalto(0);
        System.out.println("******POSICION INICIO: "+localidadInicio);

        /*generar prueba*/
        generar(nw.getPrueba());

        /*generar lugar numero de linea donde se encuentra la condicion del salto en caso de falso*/
        localidadSaltoFuera = UtGen.emitirSalto(1);

        /*generar cuerpo del while*/
        System.out.println("******POSICION LINEA SALTO FUERA: "+localidadSaltoFuera);
        generar(nw.getCuerpo());


        /*aqui va el salto incondicional al inicio del ciclo*/
        UtGen.emitirRM_Abs("LDA", UtGen.PC, localidadInicio, "salto incondicional al inicio del while");

        
        /*hasta esta linea es donde se tiene que saltar en caso de falso*/
        localidadActual=UtGen.emitirSalto(0);
        System.out.println("******LOCALIDAD ACTUAL: "+localidadActual);
        
        /*aqui genero el salto hacia fuera del ciclo en caso de falso*/
        UtGen.cargarRespaldo(localidadSaltoFuera);
        UtGen.emitirRM_Abs("JEQ", UtGen.AC, localidadActual+1, "saltar fuera del ciclo...");
        /*restauro el numero de linea todo al estado anterior*/
        UtGen.restaurarRespaldo();
        
        System.out.println("*****FIN WHILE*****");
    }
    
    private static void generarIf(NodoBase nodo) {
        NodoIf n = (NodoIf) nodo;
        int localidadSaltoElse, localidadSaltoEnd, localidadActual;
        if (UtGen.debug) {
            UtGen.emitirComentario("-> if");
        }
        /*Genero el codigo para la parte de prueba del IF*/
        generar(n.getPrueba());
        localidadSaltoElse = UtGen.emitirSalto(1);
        UtGen.emitirComentario("If: el salto hacia el else debe estar aqui");
        /*Genero la parte THEN*/
        generar(n.getParteThen());
        localidadSaltoEnd = UtGen.emitirSalto(1);
        UtGen.emitirComentario("If: el salto hacia el final debe estar aqui");
        localidadActual = UtGen.emitirSalto(0);
        UtGen.cargarRespaldo(localidadSaltoElse);
        UtGen.emitirRM_Abs("JEQ", UtGen.AC, localidadActual, "if: jmp hacia else");
        UtGen.restaurarRespaldo();
        /*Genero la parte ELSE*/
        if (n.getParteElse() != null) {
            generar(n.getParteElse());
            localidadActual = UtGen.emitirSalto(0);
            UtGen.cargarRespaldo(localidadSaltoEnd);
            UtGen.emitirRM_Abs("LDA", UtGen.PC, localidadActual, "if: jmp hacia el final");
            UtGen.restaurarRespaldo();
        }else{
            /*ESTA SECCION DE CODIGO HA SIDO AGREGADA PARA CORREGIR UNA FALLA CON EL IF*/
            UtGen.cargarRespaldo(localidadSaltoEnd);
            UtGen.emitirRM_Abs("LDA", UtGen.PC, localidadActual, "if: end del if");
            UtGen.restaurarRespaldo();
        }
        if (UtGen.debug) {
            UtGen.emitirComentario("<- if");
        }
    }

    private static void generarRepeat(NodoBase nodo) {
        NodoRepeat n = (NodoRepeat) nodo;
        int localidadSaltoInicio;
        if (UtGen.debug) {
            UtGen.emitirComentario("-> repeat");
        }
        localidadSaltoInicio = UtGen.emitirSalto(0);
        UtGen.emitirComentario("repeat: el salto hacia el final (luego del cuerpo) del repeat debe estar aqui");
        /* Genero el cuerpo del repeat */
        generar(n.getCuerpo());
        /* Genero el codigo de la prueba del repeat */
        generar(n.getPrueba());
        UtGen.emitirRM_Abs("JEQ", UtGen.AC, localidadSaltoInicio, "repeat: jmp hacia el inicio del cuerpo");
        if (UtGen.debug) {
            UtGen.emitirComentario("<- repeat");
        }
    }

    private static void generarAsignacion(NodoBase nodo) {
        NodoAsignacion n = (NodoAsignacion) nodo;
        int direccion;
        if (UtGen.debug) {
            UtGen.emitirComentario("-> asignacion");
        }
        /* Genero el codigo para la expresion a la derecha de la asignacion */
        generar(n.getExpresion());
        /* Ahora almaceno el valor resultante */
        direccion = tablaSimbolos.getDireccion(n.getIdentificador());
        UtGen.emitirRM("ST", UtGen.AC, direccion, UtGen.GP, "asignacion: almaceno el valor para el id " + n.getIdentificador());
        if (UtGen.debug) {
            UtGen.emitirComentario("<- asignacion");
        }
    }
    
    private static void generarAsignacionVector(NodoBase nodo) {
        NodoAsignacion n = (NodoAsignacion) nodo;
        int direccion;
        if (UtGen.debug) {
            UtGen.emitirComentario("-> asignacion");
        }
        /*-------------------------------------------------------------------------*/
        direccion = tablaSimbolos.getDireccion(n.getIdentificador());
        generar(n.getIndice());
        UtGen.emitirRM("ST", UtGen.AC, UtGen.MP, UtGen.GP, "");
        UtGen.emitirRM("LD", UtGen.AC1, UtGen.MP, UtGen.GP, "");
        generar(n.getExpresion());
        UtGen.emitirRM("ST", UtGen.AC, direccion, UtGen.AC1, "");
        /*-------------------------------------------------------------------------*/
//        UtGen.emitirRM("LD", UtGen.AC1, direccion, UtGen.AC1, "");
//        UtGen.emitirRO("OUT", UtGen.AC1, 0, 0, "salida");
        if (UtGen.debug) {
            UtGen.emitirComentario("<- asignacion");
        }
    }

    private static void generarLeer(NodoBase nodo) {
        NodoLeer n = (NodoLeer) nodo;
        int direccion;
        if (UtGen.debug) {
            UtGen.emitirComentario("-> leer");
        }
        UtGen.emitirRO("IN", UtGen.AC, 0, 0, "leer: lee un valor entero ");
        direccion = tablaSimbolos.getDireccion(n.getIdentificador());
        UtGen.emitirRM("ST", UtGen.AC, direccion, UtGen.GP, "leer: almaceno el valor entero leido en el id " + n.getIdentificador());
        if (UtGen.debug) {
            UtGen.emitirComentario("<- leer");
        }
    }

    private static void generarEscribir(NodoBase nodo) {
        NodoEscribir n = (NodoEscribir) nodo;
        if (UtGen.debug) {
            UtGen.emitirComentario("-> escribir");
        }
        /* Genero el codigo de la expresion que va a ser escrita en pantalla */
        generar(n.getExpresion());
        /* Ahora genero la salida */
        UtGen.emitirRO("OUT", UtGen.AC, 0, 0, "escribir: genero la salida de la expresion");
        if (UtGen.debug) {
            UtGen.emitirComentario("<- escribir");
        }
    }

    private static void generarValor(NodoBase nodo) {
        NodoValor n = (NodoValor) nodo;
        if (UtGen.debug) {
            UtGen.emitirComentario("-> constante");
        }
        UtGen.emitirRM("LDC", UtGen.AC, n.getValor(), 0, "cargar constante: " + n.getValor());
        if (UtGen.debug) {
            UtGen.emitirComentario("<- constante");
        }
    }

    private static void generarIdentificador(NodoBase nodo) {
        NodoIdentificador n = (NodoIdentificador) nodo;
        int direccion;
        if (UtGen.debug) {
            UtGen.emitirComentario("-> identificador");
        }
        direccion = tablaSimbolos.getDireccion(n.getNombre());
        UtGen.emitirRM("LD", UtGen.AC, direccion, UtGen.GP, "cargar valor de identificador: " + n.getNombre());
        if (UtGen.debug) {
            UtGen.emitirComentario("-> identificador");
        }
    }

    private static void generarOperacion(NodoBase nodo) {
        NodoOperacion n = (NodoOperacion) nodo;
        if (UtGen.debug) {
            UtGen.emitirComentario("-> Operacion: " + n.getOperacion());
        }
        /* Genero la expresion izquierda de la operacion */
        generar(n.getOpIzquierdo());
        /* Almaceno en la pseudo pila de valor temporales el valor de la operacion izquierda */
        UtGen.emitirRM("ST", UtGen.AC, desplazamientoTmp--, UtGen.MP, "op: push en la pila tmp el resultado expresion izquierda");
        /* Genero la expresion derecha de la operacion */
        generar(n.getOpDerecho());
        /* Ahora cargo/saco de la pila el valor izquierdo */
        UtGen.emitirRM("LD", UtGen.AC1, ++desplazamientoTmp, UtGen.MP, "op: pop o cargo de la pila el valor izquierdo en AC1");
        switch (n.getOperacion()) {
            case mas:
                UtGen.emitirRO("ADD", UtGen.AC, UtGen.AC1, UtGen.AC, "op: +");
                break;
            case menos:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: -");
                break;
            case por:
                UtGen.emitirRO("MUL", UtGen.AC, UtGen.AC1, UtGen.AC, "op: *");
                break;
            case entre:
                UtGen.emitirRO("DIV", UtGen.AC, UtGen.AC1, UtGen.AC, "op: /");
                break;
            case menor:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: <");
                UtGen.emitirRM("JLT", UtGen.AC, 2, UtGen.PC, "voy dos instrucciones mas alla if verdadero (AC<0)");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de falso (AC=0)");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es falso evito colocarlo verdadero)");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
                break;
            case mayor:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: >");
                UtGen.emitirRM("JGT", UtGen.AC, 2, UtGen.PC, "voy dos instrucciones mas alla if verdadero (AC>0)");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de falso (AC=0)");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es falso evito colocarlo verdadero)");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
                break;
            case menorIgual:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: <=");
                UtGen.emitirRM("JLE", UtGen.AC, 2, UtGen.PC, "voy dos instrucciones mas alla if verdadero (AC<=0)");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de falso (AC=0)");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es falso evito colocarlo verdadero)");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
                break;
            case mayorIgual:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: >=");
                UtGen.emitirRM("JGE", UtGen.AC, 2, UtGen.PC, "voy dos instrucciones mas alla if verdadero (AC>=0)");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de falso (AC=0)");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es falso evito colocarlo verdadero)");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
                break;
            case diferente:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: !=");
                UtGen.emitirRM("JNE", UtGen.AC, 2, UtGen.PC, "voy dos instrucciones mas alla if verdadero (AC!=0)");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de falso (AC=0)");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es falso evito colocarlo verdadero)");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
                break;
            case igual:
                UtGen.emitirRO("SUB", UtGen.AC, UtGen.AC1, UtGen.AC, "op: ==");
                UtGen.emitirRM("JEQ", UtGen.AC, 2, UtGen.PC, "voy dos instrucciones mas alla if verdadero (AC==0)");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de falso (AC=0)");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es falso evito colocarlo verdadero)");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
            case ylogico:
                UtGen.emitirComentario("+++++++++++++++  Y LOGICO  +++++++++++++++");
                UtGen.emitirRM("JEQ", UtGen.AC, 3, UtGen.PC, "voy tres instrucciones mas alla: operador derecho AND falso (AC==0)");
                UtGen.emitirRM("JEQ", UtGen.AC1, 2, UtGen.PC, "voy dos instrucciones mas alla: operador izquierdo AND falso (AC==0)");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es verdadero, evito colocarlo falso)");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de false (AC=0)");
                UtGen.emitirComentario("+++++++++++++  FIN Y LOGICO  +++++++++++++");
                break;
            case ologico:
                UtGen.emitirComentario("+++++++++++++++  O LOGICO  +++++++++++++++");
                UtGen.emitirRM("JNE", UtGen.AC, 1, UtGen.PC, "voy una instrucciones mas alla: operador derecho OR verdadero (AC!=0)");
                UtGen.emitirRM("JEQ", UtGen.AC1, 2, UtGen.PC, "voy dos instrucciones mas alla: operador izquierdo OR falso (AC==0)");
                UtGen.emitirRM("LDC", UtGen.AC, 1, UtGen.AC, "caso de verdadero (AC=1)");
                UtGen.emitirRM("LDA", UtGen.PC, 1, UtGen.PC, "Salto incodicional a direccion: PC+1 (es verdadero, evito colocarlo falso)");
                UtGen.emitirRM("LDC", UtGen.AC, 0, UtGen.AC, "caso de false (AC=0)");
                UtGen.emitirComentario("+++++++++++++  FIN O LOGICO  +++++++++++++");
                break;
            default:
                UtGen.emitirComentario("BUG: tipo de operacion desconocida");
        }
        if (UtGen.debug) {
            UtGen.emitirComentario("<- Operacion: " + n.getOperacion());
        }
    }

    //TODO: enviar preludio a archivo de salida, obtener antes su nombre
    private static void generarPreludioEstandar() {
        UtGen.emitirComentario("Compilacion TINY para el codigo objeto TM");
        UtGen.emitirComentario("Archivo: " + "NOMBRE_ARREGLAR");
        /*Genero inicializaciones del preludio estandar*/
        /*Todos los registros en tiny comienzan en cero*/
        UtGen.emitirComentario("Preludio estandar:");
        UtGen.emitirRM("LD", UtGen.MP, 0, UtGen.AC, "cargar la maxima direccion desde la localidad 0");
        UtGen.emitirRM("ST", UtGen.AC, 0, UtGen.AC, "limpio el registro de la localidad 0");
    }
}
