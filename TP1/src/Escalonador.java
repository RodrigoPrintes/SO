import java.security.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
 
public class Escalonador {

    /**
     *
     */
    private LinkedList<Tarefa>FilaProntos;  
    private int quantum;
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
        this.quantum = 0;
    }
    
    private void CloneProcesso(LinkedList<Tarefa> task){
        this.FilaProntos = new LinkedList<Tarefa>();
         
        for(Tarefa t : task)
            this.FilaProntos.addLast(new Tarefa(t.getPID(), t.getT_Ingresso(), t.getT_Execucao(), t.getPrioridade(), t.getType()));
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
        sortL(FilaProntos,0);
      
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
            sortL(FilaExecucao, 1);
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
   
    private void sortL(LinkedList<Tarefa> fList, int type){
        /* type = 0 - sort by tempo ingresso
         * type = 1 - sort by tempo execucao
         * type = 2 - sort by prioridade
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
        
                }else if(type == 2){
                    if (t1.getPrioridade() > t2.getPrioridade()) 
                        return 1;
                    else if (t1.getPrioridade() == t2.getPrioridade()) 
                        return 0;
                    else 
                        return -1;
                }else{// if(type == 1)
                    if (t1.getT_Execucao() > t2.getT_Execucao()) 
                        return 1;
                    else if (t1.getT_Execucao() == t2.getT_Execucao()) 
                        return 0;
                    else  
                        return -1;
                }
                     
            } });
    }

    /**
     * @param task
     */
    public void RR(LinkedList<Tarefa> task){
        //FCFS com preempção !

        System.out.println("Quantum: ");
        quantum = new Scanner(System.in).nextInt();
        
        CloneProcesso(task);
        sortL(FilaProntos,0);

        LinkedList<Tarefa> FilaExecucao = new LinkedList<Tarefa>();
        LinkedList<Processo> Times = new LinkedList<Processo>();
        LinkedList<Tarefa> FilaSuspensos = new LinkedList<Tarefa>();
       
        double tt0=0, tw0 = 0;  
        double[] tw = new double[task.size()];
        double[] tt = new double[task.size()];
      
        int time = 0;
      
        while(!FilaProntos.isEmpty()){
            Tarefa t = FilaProntos.getFirst();
            
            while(FilaProntos.size() >=1 && time >= t.getT_Ingresso()){
                /*
                * Pegar todos que chegaram ao mesmo tempo
                */
                FilaExecucao.addLast(t);
                FilaProntos.removeFirst();
                if(!FilaSuspensos.isEmpty()){
                    for(Tarefa t0 : FilaSuspensos)
                        FilaExecucao.addLast(t0);
                    
                    FilaSuspensos.clear();

                }
                if( !FilaProntos.isEmpty())
                    t = FilaProntos.getFirst();

            }
            
            while(!FilaExecucao.isEmpty()){
                Tarefa t1= FilaExecucao.getFirst();
                
                if(FilaExecucao.size() >= 1 && t1.getT_Execucao() <= quantum ){
                    Times.add(new Processo(t1.getT_Execucao()+time, time, t1.getT_Ingresso(), t1.getT_Execucao(),t1.getPID()));
                    time+= t1.getT_Execucao();

                    FilaExecucao.removeFirst();

                    if( !FilaExecucao.isEmpty())
                        t1 = FilaExecucao.getFirst();
                }else{
                    Times.add(new Processo(quantum+time, time, t1.getT_Ingresso(), t1.getT_Execucao(),t1.getPID()));
                    t1.setT_Execucao(t1.getT_Execucao() - quantum);
                    t1.setT_Ingresso(time+quantum);
                    FilaSuspensos.addLast(t1);
                    FilaExecucao.removeFirst();
                    if( !FilaExecucao.isEmpty())
                        t1 = FilaExecucao.getFirst();

                    time+= quantum;    

                }
                
               

            }
          
        }
       
        int i = 0;
       
        System.out.println();
        for(Tarefa p : task){
            double tempo_aux = 0;
         
            for(Processo t2 : Times){
                
                if(p.getPID().equals(t2.getPID())){
                   tw[i]+= t2.getTw()- t2.getTi();
                   
                    tt[i] = t2.getTt() - p.getT_Ingresso();
                }

            }

            i++;
        }
       
        System.out.println("\nOrdem de execução (PID)");   
        for (Processo p : Times)
            System.out.println("PID : " + p.getPID());
        for(double d : tw )
            tw0 += d;
        for(double d : tt)
            tt0+=d;

        System.out.println("Tempo médio execução");    
        System.out.println("Tt = "+(tt0/task.size())+"\n"+
                           "Tw = "+(tw0/task.size())+"\n"); 
       
    }
    private Processo searchProcesso_byPID(LinkedList<Processo>times, String PID){
        for(Processo p : times)
            if(p.getPID().equals(PID))
                return p;

        return null;
    }
    /**
     * @param task
     */
    public void Prioridade(LinkedList<Tarefa> task){
        
      
    }

    public void printList(LinkedList<Tarefa> filaProntos){
        for (Tarefa t : filaProntos){
            System.out.println("PID  : " + t.getPID()+
                               "\nT_in : " + t.getT_Ingresso()+
                               "\nT_out: " + t.getT_Execucao()+
                               "\nPrio : " + t.getPrioridade()+
                               "\ntype : " + t.getType()+"\n");
        }
    }
    
    public void printList2(LinkedList<Tarefa> filaProntos){
        for (Tarefa t : filaProntos){
            System.out.println("PID  : " + t.getPID()+
                               " T_in : " + t.getT_Ingresso()+
                               " T_out: " + t.getT_Execucao()+
                               " Prio : " + t.getPrioridade()+
                               " type : " + t.getType()+"\n");
        }
    }
    
    public void printListPID(LinkedList<Tarefa> filaProntos){
        for (Tarefa t : filaProntos)
            System.out.println("PID  : " + t.getPID());
        
    }
   
    public void printTimes(LinkedList<Processo>times){

        for(Processo p : times)
                System.out.println(p.getPID()+" "+
                                    p.getTf()+" "+
                                    p.getTi()+" "+
                                    p.getTw()+" "+
                                    p.getTt());

    }
}