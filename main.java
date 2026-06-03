import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class main {

    public static void main(String[] args) throws Exception {

        for (int i = 1; i <= 10; i++) {

            String endereco;
            String enderecoArquivoCriado;

            if (i == 10) {
                endereco = "D:\\TESTE-" + i + ".txt";
                enderecoArquivoCriado = "D:\\TESTE-" + i + "-RESULTADO.txt";
            } else {
                endereco = "D:\\TESTE-0" + i + ".txt";
                enderecoArquivoCriado = "D:\\TESTE-0" + i + "-RESULTADO.txt";
            }

            Path caminho = Path.of(endereco);

            // verifica se arquivo existe
            if (!Files.exists(caminho)) {
                System.out.println("Arquivo não encontrado: " + endereco);
                continue;
            }

            // lê linhas
            List<String> linhas = Files.readAllLines(caminho);

            // conta instruções
            int quantidadeDeInstrucoes = 0;

            for (String linha : linhas) {
                quantidadeDeInstrucoes++;
            }

            // transforma em vetores
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

            // percorre instruções
            for (int j = 0; j < linhasEmVetor.size(); j++) {

                String linhaDoMomento[] = new String[5];

                linhaDoMomento = linhasEmVetor.get(j);

                // esse if pode até ser removido
                if (j >= linhasEmVetor.size()) {
                    break;
                }

                else if (j == linhasEmVetor.size() - 1) {

                    // última linha não possui próxima
                    continue;
                }

                else {

                    String linhaProx[] = null;
                    String linhaPosProx[] = null;

                    // evita erro no j+1
                    if (j + 1 < linhasEmVetor.size()) {
                        linhaProx = linhasEmVetor.get(j + 1);
                    }

                    // evita erro no j+2
                    if (j + 2 < linhasEmVetor.size()) {
                        linhaPosProx = linhasEmVetor.get(j + 2);
                    }
                    if (linhaDoMomento[0].equals("lw")) {

                        // j
                        if ((linhaProx != null && linhaProx[0].equals("j"))||(linhaPosProx != null && linhaPosProx[0].equals("j"))) {
                            quantidadeDeInstrucoes = quantidadeDeInstrucoes + 0;
                        }

                        // sw sb sh
                        else if ((linhaProx != null &&(linhaProx[0].equals("sw")|| linhaProx[0].equals("sb")|| linhaProx[0].equals("sh")))||(linhaPosProx != null &&(linhaPosProx[0].equals("sw")|| linhaPosProx[0].equals("sb")|| linhaPosProx[0].equals("sh")))) {
                            if ((linhaProx != null &&(linhaDoMomento[1].equals(linhaProx[3])|| linhaDoMomento[1].equals(linhaProx[1])))||(linhaPosProx != null &&(linhaDoMomento[1].equals(linhaPosProx[3])|| linhaDoMomento[1].equals(linhaPosProx[1])))) {
                                quantidadeDeInstrucoes++;
                            }
                        }

                        // beq bne
                        else if ((linhaProx != null && (linhaProx[0].equals("beq")|| linhaProx[0].equals("bne")))||(linhaPosProx != null &&(linhaPosProx[0].equals("beq")|| linhaPosProx[0].equals("bne")))) {
                            if ((linhaProx != null &&(linhaDoMomento[1].equals(linhaProx[1])|| linhaDoMomento[1].equals(linhaProx[2])))||(linhaPosProx != null &&(linhaDoMomento[1].equals(linhaPosProx[1])|| linhaDoMomento[1].equals(linhaPosProx[2])))) {
                                quantidadeDeInstrucoes++;
                            }
                        }

                        // jr bgtz blez
                        else if ((linhaProx != null &&(linhaProx[0].equals("jr")|| linhaProx[0].equals("bgtz")|| linhaProx[0].equals("blez")))|| (linhaPosProx != null &&(linhaPosProx[0].equals("jr")|| linhaPosProx[0].equals("bgtz")|| linhaPosProx[0].equals("blez")))) {
                            if ((linhaProx != null &&linhaDoMomento[1].equals(linhaProx[1]))||(linhaPosProx != null && linhaDoMomento[1].equals(linhaPosProx[1]))) {
                                quantidadeDeInstrucoes++;
                            }
                        }
                        // add sub and or xor
                        else if ((linhaProx != null && (linhaProx[0].equals("add")|| linhaProx[0].equals("sub")|| linhaProx[0].equals("and")|| linhaProx[0].equals("or")|| linhaProx[0].equals("xor")))||(linhaPosProx != null &&(linhaPosProx[0].equals("add")|| linhaPosProx[0].equals("sub")|| linhaPosProx[0].equals("and")|| linhaPosProx[0].equals("or")|| linhaPosProx[0].equals("xor")))) {
                            if ((linhaProx != null && (linhaDoMomento[1].equals(linhaProx[2]) || linhaDoMomento[1].equals(linhaProx[3])))|| (linhaPosProx != null && (linhaDoMomento[1].equals(linhaPosProx[2]) || linhaDoMomento[1].equals(linhaPosProx[3])))) {
                                quantidadeDeInstrucoes++;
                            }
                        }

                        // addi andi ori ...
                        else if ((linhaProx != null && (linhaProx[0].equals("addi") || linhaProx[0].equals("andi") || linhaProx[0].equals("subi") || linhaProx[0].equals("ori") || linhaProx[0].equals("xori") || linhaProx[0].equals("liu") || linhaProx[0].equals("sll") || linhaProx[0].equals("srl"))) || (linhaPosProx != null && (linhaPosProx[0].equals("addi") || linhaPosProx[0].equals("andi") || linhaPosProx[0].equals("subi") || linhaPosProx[0].equals("ori") || linhaPosProx[0].equals("xori") || linhaPosProx[0].equals("liu") || linhaPosProx[0].equals("sll") || linhaPosProx[0].equals("srl")))) {

                            if ((linhaProx != null &&linhaDoMomento[1].equals(linhaProx[2]))||(linhaPosProx != null &&linhaDoMomento[1].equals(linhaPosProx[2]))) {
                                quantidadeDeInstrucoes++;
                            }
                        }
                        // lb lh lw
                        else if ((linhaProx != null && (linhaProx[0].equals("lb") || linhaProx[0].equals("lh") || linhaProx[0].equals("lw"))) ||(linhaPosProx != null && (linhaPosProx[0].equals("lb") || linhaPosProx[0].equals("lh") || linhaPosProx[0].equals("lw")))) {
                            if ((linhaProx != null && linhaDoMomento[1].equals(linhaProx[3])) || (linhaPosProx != null && linhaDoMomento[1].equals(linhaPosProx[3]))) {
                                quantidadeDeInstrucoes++;
                            }
                        }

                        else {
                                System.out.println("Instrução não identificada");
                        }
                    }
                }
            }

            int quantidadeCiclos;

            quantidadeCiclos = quantidadeDeInstrucoes + 4;

            // cria arquivo resultado
            try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(enderecoArquivoCriado))
            ) {

                bw.write(String.valueOf(quantidadeCiclos));
                bw.newLine();
            } catch (IOException e) {
                System.out.println("Erro na escrita do arquivo.");
            }

            System.out.println("Arquivo processado: " + endereco);
        }
    }
}
