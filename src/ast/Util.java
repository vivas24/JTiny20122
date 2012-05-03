package ast;

public class Util {
	
	static int sangria = 0;
	
	//Imprimo en modo texto con sangrias el AST
	public static void imprimirAST(NodoBase raiz){
		  sangria+=2;
		  while (raiz != null) {
		    printSpaces();
		    if (raiz instanceof  NodoIf)
		    	System.out.println("If");
		    else if (raiz instanceof  NodoRepeat)
		    	System.out.println("Repeat");
		    
		    else if (raiz instanceof  NodoAsignacion)
		    	System.out.println("Asignacion a: "+((NodoAsignacion)raiz).getIdentificador());

		    else if (raiz instanceof  NodoLeer)  
		    	System.out.println("Lectura: "+((NodoLeer)raiz).getIdentificador());

		    else if (raiz instanceof  NodoEscribir)
		    	System.out.println("Escribir");
		    
		    else if (raiz instanceof NodoOperacion
		    		|| raiz instanceof NodoValor
		    		|| raiz instanceof NodoIdentificador 
                                || raiz instanceof NodoDeclaracion)
		    	imprimirNodo(raiz);
                    else if(raiz instanceof NodoWhile)
		    	System.out.println("While");
                    else if(raiz instanceof NodoFor)
		    	System.out.println("For");
		    else System.out.println("Tipo de nodo desconocido");;
		    
		    /* Hago el recorrido recursivo */
		    if (raiz instanceof  NodoIf){
		    	printSpaces();
		    	System.out.println("**Prueba IF**");
		    	imprimirAST(((NodoIf)raiz).getPrueba());
		    	printSpaces();
		    	System.out.println("**Then IF**");
		    	imprimirAST(((NodoIf)raiz).getParteThen());
		    	if(((NodoIf)raiz).getParteElse()!=null){
		    		printSpaces();
		    		System.out.println("**Else IF**");
		    		imprimirAST(((NodoIf)raiz).getParteElse());
		    	}
		    }
		    else if (raiz instanceof  NodoRepeat){
		    	printSpaces();
		    	System.out.println("**Cuerpo REPEAT**");
		    	imprimirAST(((NodoRepeat)raiz).getCuerpo());
		    	printSpaces();
		    	System.out.println("**Prueba REPEAT**");
		    	imprimirAST(((NodoRepeat)raiz).getPrueba());
		    }
		    else if (raiz instanceof  NodoAsignacion)
		    	imprimirAST(((NodoAsignacion)raiz).getExpresion());
		    else if (raiz instanceof  NodoEscribir)
		    	imprimirAST(((NodoEscribir)raiz).getExpresion());
		    else if (raiz instanceof NodoOperacion){
		    	printSpaces();
		    	System.out.println("**Expr Izquierda Operacion**");
		    	imprimirAST(((NodoOperacion)raiz).getOpIzquierdo());
		    	printSpaces();
		    	System.out.println("**Expr Derecha Operacion**");		    	
		    	imprimirAST(((NodoOperacion)raiz).getOpDerecho());
                    }else if(raiz instanceof NodoWhile){
		    	printSpaces();
		    	System.out.println("**Prueba WHILE**");
		    	imprimirAST(((NodoWhile)raiz).getPrueba());
		    	printSpaces();
		    	System.out.println("**Cuerpo WHILE**");
		    	imprimirAST(((NodoWhile)raiz).getCuerpo());
		    	printSpaces();
		    	System.out.println("**FIN WHILE**");
                    }else if(raiz instanceof NodoFor){
		    	printSpaces();
		    	System.out.println("**Asignacion FOR**");
		    	imprimirAST(((NodoFor)raiz).getAsigancion());
		    	printSpaces();
		    	System.out.println("**Prueba FOR**");
		    	imprimirAST(((NodoFor)raiz).getPrueba());
		    	printSpaces();
		    	System.out.println("**Expresion FOR**");
		    	imprimirAST(((NodoFor)raiz).getExpresion());
		    	printSpaces();
		    	System.out.println("**Cuerpo FOR**");
		    	imprimirAST(((NodoFor)raiz).getCuerpo());
		    	printSpaces();
		    	System.out.println("**FIN FOR**");
                    }
		    raiz = raiz.getHermanoDerecha();
		  }
		  sangria-=2;
		}

/* Imprime espacios con sangria */
static void printSpaces()
{ int i;
  for (i=0;i<sangria;i++)
	  System.out.print(" ");
}

/* Imprime informacion de los nodos */
static void imprimirNodo( NodoBase raiz )
{
	if(	raiz instanceof NodoRepeat
		||	raiz instanceof NodoLeer
		||	raiz instanceof NodoEscribir  ){
		System.out.println("palabra reservada: "+ raiz.getClass().getName());
	}
	
	if(	raiz instanceof NodoAsignacion )
		System.out.println(":=");
	
	if(	raiz instanceof NodoOperacion ){
		tipoOp sel=((NodoOperacion) raiz).getOperacion();
		if(sel==tipoOp.menor)
			System.out.println("<"); 
		if(sel==tipoOp.igual)
			System.out.println("=");
		if(sel==tipoOp.mas)
			System.out.println("+");
		if(sel==tipoOp.menos)
			System.out.println("-");
		if(sel==tipoOp.por)
			System.out.println("*");
		if(sel==tipoOp.entre)
			System.out.println("/");
		if(sel==tipoOp.mayor)
			System.out.println(">"); 
		if(sel==tipoOp.menorIgual)
			System.out.println("<="); 
		if(sel==tipoOp.mayorIgual)
			System.out.println(">="); 
		if(sel==tipoOp.diferente)
			System.out.println("!="); 
		if(sel==tipoOp.ylogico)
			System.out.println("&&"); 
		if(sel==tipoOp.ologico)
			System.out.println("||"); 
	}

	if(	raiz instanceof NodoValor ){
		System.out.println("NUM, val= "+ ((NodoValor)raiz).getValor());
	}

	if(	raiz instanceof NodoIdentificador ){
		System.out.println("ID, nombre= "+ ((NodoIdentificador)raiz).getNombre());
	}
	if(	raiz instanceof NodoDeclaracion ){
                NodoDeclaracion nodo = (NodoDeclaracion)raiz;
                if(nodo.esVector())
                    System.out.println("VECTOR: "+nodo.getIdentificador());
                else
                    System.out.println("VARIABLE: "+nodo.getIdentificador());
	}
        

}


}
