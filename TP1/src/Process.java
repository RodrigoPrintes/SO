import java.util.LinkedList;
 
public class Process {

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
    public Process(){
        this.FilaProntos = new LinkedList<Tarefa>();
    }
    
    
    public void FCFC(){}
    public void SJR(){}
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
}
