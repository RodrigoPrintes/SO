import java.util.LinkedList;

public class Fifo {
    private int posInicial = 0;
    private int PageFaults;
    private int nQuadros;

    private LinkedList<String>quadros;
  
    public Fifo(int nQuadros) {
        if( nQuadros < 0)
            throw new IllegalArgumentException();
        
        setnQuadros(nQuadros);
        setPageFaults(0);
    
        this.quadros = new LinkedList<String>();
    }

    public int getPageFaults() {
        return PageFaults;
    }

    public void setPageFaults(int pageFaults) {
        PageFaults = pageFaults;
    }

    public int getnQuadros() {
        return nQuadros;
    }

    public void setnQuadros(int nQuadros) {
        this.nQuadros = nQuadros;
    }

   
    public void addPage(String pageNuString){
         //check a pagina na lista.
         if (!quadros.contains(pageNuString)) {

            
            if (quadros.size() < getnQuadros()) {
                quadros.add(pageNuString);
            } 
            //nao tem espaco na memoria, remover o mais velho e atualiza a PosInicio
            else {
                quadros.remove(this.posInicial);
                quadros.add(this.posInicial, pageNuString);
                this.posInicial++;
                //verifica se PosInicio esta no final, se estiver atualiza para o inicio
                if (this.posInicial == getnQuadros()) {
                	this.posInicial = 0;
                }
            }
            this.PageFaults++;
        }
    }
}
