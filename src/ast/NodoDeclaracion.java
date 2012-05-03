package ast;

public class NodoDeclaracion extends NodoBase{
    private String id;
    private int tamanho;
    public NodoDeclaracion(int tamanho, String id){
        this.id=id;
        this.tamanho=tamanho;
    }
    public int getTamanho(){return this.tamanho;}
    public String getIdentificador(){
        return this.id;
    }
    public boolean esVector(){return this.tamanho>0;}
    
}
