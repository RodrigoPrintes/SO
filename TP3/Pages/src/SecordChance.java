import java.util.LinkedList;

public class SecordChance {
    private LinkedList<Integer> bits;
    private int PageFaults;
    private int posInicial = 0;
    public void setPageFaults(int pageFaults) {
        PageFaults = pageFaults;
    }

    private int nQuadros;
    private LinkedList<String>quadros;
    
    public SecordChance(int nQuadros) {
        if( nQuadros < 0)
            throw new IllegalArgumentException();
        
        setnQuadros(nQuadros);
        this.quadros = new LinkedList<String>();
        this.bits = new LinkedList<Integer>();
    }

    public int getPageFaults() {
        return PageFaults;
    }
    
    public int getnQuadros() {
        return nQuadros;
    }

    public void setnQuadros(int nQuadros) {
        this.nQuadros = nQuadros;
    }

    public void addPage(String pageNuString){

        int process = quadros.indexOf(pageNuString);

        
        if (process == -1) {

            if (quadros.size() < getnQuadros()) {
                quadros.add(pageNuString);	//adiciona
                bits.add(0);
            } 
            
            //processo/pagina nao esta na memoria, e tbm nao tem espaco na memorial. vai procurar um processo/pagina q contem o bit 0(menos usado)
            //caso o bit seja igual a 1, vai colocar o bit para 0(pq ja teve a sua segunda chance)
            else {
                while (bits.get(this.posInicial).equals(1)) {
                    bits.set(this.posInicial, 0);
                    this.posInicial++;
                    // ponteiro voltando ao inicio
                    if (this.posInicial == getnQuadros()) {
                        this.posInicial = 0;
                    }
                }
                //ao achar um processo/pagina com bit igual a 0 ele remove e adiciona o novo processo/pagina no lugar dele
                quadros.remove(this.posInicial);
                bits.remove(this.posInicial);
                quadros.add(this.posInicial, pageNuString);
                bits.add(this.posInicial, 0);

                this.posInicial++;
                // ponteiro voltando ao inicio
                if (this.posInicial == getnQuadros()) {
                	this.posInicial = 0;
                }
            }
            PageFaults++;
        } 
        else { // se a pagina ja esta na memoria eu so atualizo o bit para 1
            bits.set(process, 1);

        }
    }

}
