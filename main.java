import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class main {
    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 10; i++) {
            String endereco;
            if (i == 10) {
                endereco = "D:\\TESTE-" + i + ".txt";
            } else {
                endereco = "D:\\TESTE-0" + i + ".txt";
            }
            // Lê cada linha de instrução e coloca em uma lista
            List<String> linhas = Files.readAllLines(Path.of(endereco));

            // Contar quantas instruções temos
            int quantidadeDeInstrucoes = 0;
            for (String linha : linhas) {
                quantidadeDeInstrucoes++;
            }

            // Dividir cada linha em vetores e joga em uma lista de vetores
            List<String[]> linhasEmVetor = new ArrayList<>();
            for (String linha : linhas) {
                StringTokenizer separador = new StringTokenizer(linha, " , ()");
                int indice = 0;
                String comandos[] = new String[5];
                while (separador.hasMoreTokens()) {
                    String token = separador.nextToken();
                    comandos[indice] = token;
                    System.out.println(token);
                    indice++;
                }
                linhasEmVetor.add(comandos);
            }

            
        }
    }
}