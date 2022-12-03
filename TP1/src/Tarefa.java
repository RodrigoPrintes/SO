public class Tarefa {

    /**
     * Classe do objeto tarefa, objeto ao qual usa o processador e cria processos.
     */
    private String PID;

    /**
     * @return PID nome do processo
     */
    public String getPID() {
        return PID;
    }
    /**
     * @param pID
     */
    public void setPID(String pID) {
        PID = pID;
    }
   
    private int t_Ingresso;
    
    public int getT_Ingresso() {
        return t_Ingresso;
    }
    /**
     * @param t_Ingresso
     */
    public void setT_Ingresso(int t_Ingresso) {
        this.t_Ingresso = t_Ingresso;
    }
    
    private int t_Execucao;
    
    public int getT_Execucao() {
        return t_Execucao;
    }
    /**
     * @param t_Execucao
     */
    public void setT_Execucao(int t_Execucao) {
        this.t_Execucao = t_Execucao;
    }
    
    private int prioridade;
    
    public int getPrioridade() {
        return prioridade;
    }
    /**
     * @param prioridade
     */
    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    
    private int type;
   
    public int getType() {
        return type;
    }
    /**
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Contrutor do objeto tarefa.
     * @param pID
     * @param t_Ingresso
     * @param t_Execucao
     * @param prioridade
     * @param type
     */
    public Tarefa(String pID, int t_Ingresso, int t_Execucao, int prioridade, int type) {
        PID = pID;
        this.t_Ingresso = t_Ingresso;
        this.t_Execucao = t_Execucao;
        this.prioridade = prioridade;
        this.type = type;
    }
}
