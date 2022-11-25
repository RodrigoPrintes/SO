public class Processo {
    private int tt;
    private int tw;
    private int ti;
    private int tf;
    private String PID;
 


  
    public Processo(int tt, int tw, int ti, int tf, String pID) {
        this.tt = tt;
        this.tw = tw;
        this.ti = ti;
        this.tf = tf;
        PID = pID;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String pID) {
        PID = pID;
    }

    public Processo(int tt, int tw, int tempoIngress, int tempoFinal) {
        this.tt = tt;
        this.tw = tw;
        this.ti = tempoIngress;
        this.tf = tempoFinal;
    }

    public int getTw() {
        return tw;
    }

    public void setTw(int tw) {
        this.tw = tw;
    }

    public int getTt() {
        return tt;
    }

    public void setTt(int tt) {
        this.tt = tt;
    }

    public int getTi() {
        return ti;
    }

    public void setTi(int tempoIngress) {
        this.ti = tempoIngress;
    }

    public int getTf() {
        return tf;
    }

    public void setTf(int tempoFinal) {
        this.tf = tempoFinal;
    }
}
