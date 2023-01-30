import java.util.LinkedList;

public class LRU {

   
    private int PageFaults;
    private int nQuadros;
    private LinkedList<String>quadros;
  
    public LRU(int nQuadros) {
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

        int process = quadros.indexOf(pageNuString);	


        if (process == -1) {
        	
           //Checa se a pagina esta em memoria
            if (quadros.size() < getnQuadros()) {
                quadros.add(pageNuString);
            } 
            //se nao tiver remover o 1
            else {

            	this.quadros.remove(0);
            	this.quadros.add(pageNuString);
            }
            this.PageFaults++;

        } 
      //pagina ja esta na memoria
        else {
        	this.quadros.remove(process);
        	this.quadros.add(pageNuString);
        }
    }
}
