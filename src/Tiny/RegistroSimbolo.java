package Tiny;

public class RegistroSimbolo {
	private String identificador;
	private int NumLinea,lineaFinal;
	private int DireccionMemoria;
	
	public RegistroSimbolo(String identificador, int numLinea,
			int direccionMemoria) {
		super();
		this.identificador = identificador;
		NumLinea = numLinea;
		DireccionMemoria = direccionMemoria;
	}

	public String getIdentificador() {
		return identificador;
	}

	public int getNumLinea() {
		return NumLinea;
	}

        public void setNumLinea(int linea){
            this.NumLinea=linea;
        }
        
	public int getDireccionMemoria() {
		return DireccionMemoria;
	}
        public void setFinal(int linea){this.lineaFinal=linea;}
        public int getFinal(){return this.lineaFinal;}
	public void setDireccionMemoria(int direccionMemoria) {
		DireccionMemoria = direccionMemoria;
	}
}
