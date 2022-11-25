import java.security.Timestamp;
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
        CloneProcesso(task);
        LinkedList<Tarefa> FilaExecucao = new LinkedList<Tarefa>();
        LinkedList<Processo> Times = new LinkedList<Processo>();
        double tt=0,tw=0;
        sjfSort(FilaProntos,0);
      
        int time = 0;

       //Ordenar no tempo

       // t 0 --> quem chegou -->> ordena e execcutta
       // t 1 --> chegou alguém ?? -->> ordena e execcutta

       while(!FilaProntos.isEmpty()){
            Tarefa t = FilaProntos.getFirst();
           
            while(FilaProntos.size() >=1 && time >= t.getT_Ingresso()){
                /*
                 * Pegar todos que chegaram ao mesmo tempo
                */
                FilaExecucao.add(t);
                FilaProntos.removeFirst();
                if( !FilaProntos.isEmpty())
                    t = FilaProntos.getFirst();

            }

            /**
             * Organizar pelo tempo de execução
             */
            sjfSort(FilaExecucao, 1);
            System.out.println();

            while(!FilaExecucao.isEmpty()){

                Tarefa nexTarefa = FilaExecucao.getFirst();
                if( FilaProntos.size() >=1 && t.getT_Ingresso() <= time){
                    //Checa se exite e chegou algum processo no tempo de execução
                    break;
                }else{
                    Times.add(new Processo(time+ nexTarefa.getT_Execucao(), time, nexTarefa.getT_Ingresso(), nexTarefa.getT_Execucao(),nexTarefa.getPID()));
                    time+= nexTarefa.getT_Execucao();
                    FilaExecucao.removeFirst();
                }
                    
            }    
        
       }

       System.out.println("Ordem de execução (PID)");   
       for (Processo p : Times){
           System.out.println("PID : " + p.getPID());
           tt+=p.getTt() - p.getTi();
           tw+=p.getTw() - p.getTi();
        }

       System.out.println("Tempo médio execução"); 
       System.out.println("Tt = "+(tt/Times.size())+"\n"+
                          "Tw = "+(tw/Times.size())+"\n");     
       
      
        
    }
   
    private void sjfSort(LinkedList<Tarefa> fList, int type){
        /* type = 0 - sort by tempo ingresso
         * type = 1 - sort by tempo execucao
         * 
        */
        Collections.sort(fList, new Comparator<Tarefa>() {

            @Override
            public int compare(Tarefa t1, Tarefa t2) {
                if(type == 0){
                    if (t1.getT_Ingresso() > t2.getT_Ingresso()) 
                        return 1;
                    else if (t1.getT_Ingresso() == t2.getT_Ingresso()) 
                        return 0;
                    else 
                        return -1;
        
                }else{
                    if (t1.getT_Execucao() > t2.getT_Execucao()) 
                        return 1;
                    else if (t1.getT_Execucao() == t2.getT_Execucao()) 
                        return 0;
                    else  
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
