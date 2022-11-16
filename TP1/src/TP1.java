import java.io.*;
import java.util.LinkedList;
public class TP1 {
    //"D:\Documentos\Code\imports\SO\trab1\Metodos\arquivos\FCFS-SJF-6.txt"


    public static void main(String[] args) throws IOException  {
        
        System.out.println("TP1 - Escalonamento de Processos!");
     
        Process process = new Process();
  
        LinkedList<Tarefa>inputTarefas = new LinkedList<Tarefa>();
     
        String dir = "D:\\Documentos\\Code\\imports\\SO\\trab1\\Metodos\\arquivos\\FCFS-SJF-6.txt";
           

        FileInputStream stream   = new FileInputStream(dir);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader lerArq    = new BufferedReader(reader);

        String linha = lerArq.readLine(); // lê a primeira linha
            
        while (linha != null) {
            String s[] = linha.split(" ");
             
            inputTarefas.addLast(new Tarefa(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]), Integer.parseInt(s[4]) ) );
              
            linha = lerArq.readLine(); // lê da segunda até a última linha
                
        }
            
    
        lerArq.close();

       process.printList(inputTarefas);
    }

  
  
    
}
