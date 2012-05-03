package ast;

public class NodoAsignacion extends NodoBase {
	private String identificador;
	private NodoBase expresion;
	private NodoBase indice;
        private boolean isVector;
	public NodoAsignacion(String identificador) {
		super();
		this.identificador = identificador;
		this.expresion = null;
                this.indice =null;
                this.isVector=false;
	}
	
	public NodoAsignacion(String identificador, NodoBase expresion) {
		super();
		this.identificador = identificador;
		this.expresion = expresion;
                this.indice =null;
                this.isVector=false;
	}
        /*Constructor para un nodo asignacion de un vector*/
        public NodoAsignacion(String identificador, NodoBase indice, NodoBase expresion) {
		super();
		this.identificador = identificador;
		this.expresion = expresion;
                this.indice = indice;
                this.isVector=true;
	}
        public NodoBase getIndice(){return this.indice;}
	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public NodoBase getExpresion() {
		return expresion;
	}

	public void setExpresion(NodoBase expresion) {
		this.expresion = expresion;
	}
	public boolean esVector(){return this.isVector;}
	
	
}
