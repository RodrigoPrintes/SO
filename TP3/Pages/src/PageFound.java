import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PageFound {
    public static void main(String[] args) throws Exception {
        System.out.println("Para Testar o arquivo adicione na pasta /data");
        /*
         * Carredando os arquivos de teste;
        */
        Path path = Paths.get("");
		String dir = path.toAbsolutePath().toString();
		dir +="/src/data/";

        String[] input_Test = PageFound.getTeste(dir);
         /*
         * Arquivo carregado e em memória;
        */
        
        if( input_Test != null){

            /*
                * Carregando os algoritmos em memória;
            */
            Fifo fifo = new Fifo(8000);
            LRU lru = new LRU(8000);
            SecordChance secordChance = new SecordChance(8000);

            for(int i = 0 ; i < input_Test.length-1; i++)
                fifo.addPage(input_Test[i]);
            /*
             * Rodando os algorimos no arquivo selecionado
             * Os algorimos são rodados separadamente para evitar problemas de interferencia
            */

            for(int i = 0 ; i < input_Test.length-1; i++)
                fifo.addPage(input_Test[i]);

            for(int i = 0 ; i < input_Test.length-1; i++)
                lru.addPage(input_Test[i]);
           
            for(int i = 0 ; i < input_Test.length-1; i++)
                secordChance.addPage(input_Test[i]);

            /*
             * Exibição dos Resultados
             */
            System.out.println("Page Foulds");
            System.out.println("FIFO : "+fifo.getPageFaults());
            System.out.println("LRU  : "+lru.getPageFaults());
            System.out.println("Segunda Chance : "+secordChance.getPageFaults());


        }else{
            System.out.println("Arquivo inválido!!!");
        }
       
    }
    /**
     * @param dir
     * @return Retonra um vetor de Strings contento a entrada do arquivo selecionado para teste
     */
    private static String[] getTeste(String dir){
        try{
              /*
                * leitura do arquivo
                */
            
            File fileDir = new File(dir);
            File[] arquivos = fileDir.listFiles();
            
            int k = 1;
            for (File f : arquivos) {
                System.out.printf("	%d - ",k++);
                System.out.println(f.getName());
            }
           
            Scanner s = new Scanner(System.in);
            System.out.println("-> Selecione o arquivo de teste:");
            int r = s.nextInt(); s.close();

            /*
                * Transformando o aquivo em um vetor de Strings 
                */
            FileReader arquivo = new FileReader(arquivos[r-1]);
                @SuppressWarnings("resource")
                Scanner arquivoLido = new Scanner(arquivo);
                String Linha = arquivoLido.nextLine();
        
                String[] input_Test = Linha.split(";");
            
          return input_Test;
            

        }catch(Exception e){
            e.getStackTrace();
            return null;
        }
    }
   
}
