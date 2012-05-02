package ast;

public class NodoWhile extends NodoBase {

	private NodoBase cuerpo;
	private NodoBase prueba;
	
	public NodoWhile(NodoBase prueba, NodoBase cuerpo) {
		super();
		this.cuerpo = cuerpo;
		this.prueba = prueba;
	}
	
	public NodoBase getCuerpo() {
		return cuerpo;
	}

	public NodoBase getPrueba() {
		return prueba;
	}

	
	
	
	
}
