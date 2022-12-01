import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
public class TP1 {
    // PARA CADOS DE COMPATIBILIDADE COLOQUE O DIRETÓRIO DO ARQUIVO A SER EXECUTADO
    //DIR ATUAL = "D:\Documentos\Code\imports\SO\trab1\Metodos\arquivos\FCFS-SJF-6.txt"
    
    static LinkedList<Tarefa>TASKs;
    static Escalonador escalonador= new Escalonador();
    
    public static void main(String[] args) throws IOException  {
        
        System.out.println("    TP1 - Escalonamento de Processos!\n");
     
        TP1.menu();
      
    }

    public static void menu()throws IOException {
       String opcao[]={"FCFS","SJF","RR","	Prioridade"};
       int  inpuT = -1;

       while(inpuT != 44){
            System.out.println("\n==========  Escolha o numero da opção  ==========");
            System.out.printf("0 - FCFS\n "+
                              "1 - SJF\n"+
                              "2 - RR\n"+
                              "3 - Prioridade \n"+
                              "44 - sair \n"+
                              "Opcao : ");
                                
            inpuT = new Scanner(System.in).nextInt();

            if(testInput(inpuT)){
                System.out.println("Algotimo "+ opcao[inpuT]);
                    if((inpuT == 0 )){
                        TASKs = TP1.input("D:\\Documentos\\Code\\JAVA\\SO\\TP1\\src\\FCFS-SJF-6.txt");
                       
                        TP1.escalonador.FCFC(TASKs);
                    }else if(inpuT == 1){
                        TASKs = TP1.input("D:\\Documentos\\Code\\JAVA\\SO\\TP1\\src\\FCFS-SJF-6.txt");
                        TP1.escalonador.SJR(TASKs);
                    }else if(inpuT == 2){
                        TASKs = TP1.input("D:\\Documentos\\Code\\JAVA\\SO\\TP1\\src\\RR-5.txt");
                        
                        TP1.escalonador.RR(TASKs);
                    }else {
                        TASKs = TP1.input("D:\\Documentos\\Code\\JAVA\\SO\\TP1\\src\\Prio-6.txt");
                        TP1.escalonador.Prioridade(TASKs);
                    }   
            }else
                if(inpuT != 44)
                    System.out.println("Entrada inválida");
                else{
                    System.out.println("FIM");
                    break;
                }
        }
    }
   
    private static boolean testInput(int input){
       return (input >=0 && input <= 3);
    }

    public static LinkedList<Tarefa> input(String dir)  throws IOException{
        //*** */
        FileInputStream stream   = new FileInputStream(dir);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader lerArq    = new BufferedReader(reader);

        LinkedList<Tarefa>inputTarefas = new LinkedList<Tarefa>();

        String linha = lerArq.readLine(); // lê a primeira linha
            
        while (linha != null) {
            String s[] = linha.split(" ");
             
            inputTarefas.addLast(new Tarefa(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]) ) );
              
            linha = lerArq.readLine(); // lê da segunda até a última linha
                
        }
            
        lerArq.close();

        return inputTarefas;
      
    }
  
}
