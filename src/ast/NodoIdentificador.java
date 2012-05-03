package ast;

public class NodoIdentificador extends NodoBase {
	private String nombre;
        private NodoBase indice;
        private boolean isVector;

	public NodoIdentificador(String nombre) {
		super();
		this.nombre = nombre;
                this.indice=null;
                this.isVector=false;
	}
	public NodoIdentificador(String nombre,NodoBase indice) {
		super();
		this.nombre = nombre;
                this.indice=indice;
                this.isVector=true;
	}
       
	public NodoIdentificador() {
		super();
	}

	public String getNombre() {
		return nombre;
	}
        public boolean esVector(){return isVector;}
        public NodoBase getIndice(){return this.indice;}
}
