import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
 
public class Escalonador {

    /**
     *
     */
    private LinkedList<Tarefa>FilaProntos;  

    public LinkedList<Tarefa> getFilaProntos() {
        return FilaProntos;
    }


    public void setFilaProntos(LinkedList<Tarefa> filaProntos) {
        FilaProntos = filaProntos;
    }

    public void addTarefa(Tarefa task){
        this.FilaProntos.addLast(task);
    }
    public Escalonador(){
        this.FilaProntos = new LinkedList<Tarefa>();
    }
    
    private void CloneProcesso(LinkedList<Tarefa> task){
        this.FilaProntos.clear();
       
        for(Tarefa t : task)
            this.FilaProntos.addLast(t);
    }
    public void FCFC(LinkedList<Tarefa> task){
        CloneProcesso(task);
        LinkedList<Processo> Times = new LinkedList<Processo>();
        int time = 0;
        double tt=0,tw=0;
      
        for(Tarefa t : FilaProntos){
                Times.addLast(new Processo(time+t.getT_Execucao(), time, t.getT_Ingresso(), t.getT_Execucao()));
                time+=t.getT_Execucao();

         }
        /*outs
        * Ordem de execução (PID)	
        * Tempo	médio e	execução
        * Tempo	médio de espera.
        */
        System.out.println("\nOrdem de execução (PID)");   
        for (Tarefa t : FilaProntos)
            System.out.println("PID : " + t.getPID());

        System.out.println("Tempo médio execução"); 
        for(Processo t : Times ){
            tt+=t.getTt() - t.getTi();
            tw+=t.getTw() - t.getTi();
        }
        System.out.println("Tt = "+(tt/FilaProntos.size())+"\n"+
                           "Tw = "+(tw/FilaProntos.size())+"\n");     
        
    }
    public void SJR(LinkedList<Tarefa> task){
        //Este algoritmo assume que a lista de entrada está ordenada por ordem de chegada.
        CloneProcesso(task);

       //Ordenar no tempo

       // t 0 --> quem chegou -->> ordena e execcutta
       // t 1 --> chegou alguém ?? -->> ordena e execcutta
       //
 

 
    }
   
    private void sjfSort(LinkedList<Tarefa> fList){

        Collections.sort(fList, new Comparator<Tarefa>() {

            @Override
            public int compare(Tarefa t1, Tarefa t2) {
              
                if (t1.getT_Execucao() > t2.getT_Execucao()) {
                    return 1;
                }
                else if (t1.getT_Execucao() == t2.getT_Execucao()) {
                    return 0;
                }
                else {
                    return -1;
                }
            } });
    }
    
    public void RR(){}
    public void Prioridade(){}

    public void printList(LinkedList<Tarefa> filaProntos){
        for (Tarefa t : filaProntos){
            System.out.println("PID  : " + t.getPID()+
                               "\nT_in : " + t.getT_Ingresso()+
                               "\nT_out: " + t.getT_Execucao()+
                               "\nPrio : " + t.getPrioridade()+
                               "\ntype : " + t.getType()+"\n");
        }
    }
    public void printListPID(LinkedList<Tarefa> filaProntos){
        for (Tarefa t : filaProntos)
            System.out.println("PID  : " + t.getPID());
        
    }



}
