import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainServer {
    private static final int PORT = 8080;

    private ServerSocket socket;
    private Socket client;
    private Scanner fromClient;
    PrintWriter toClient;
    private List<String> diretorio = new ArrayList();
    private File raiz = new File("C:\\aepProg");

    public static void main(String[] args) {
        MainServer servidor = new MainServer();
        servidor.start();
    }

    private void start() {
        try {
            socket = new ServerSocket(PORT);
            client = socket.accept();
            fromClient = new Scanner(client.getInputStream());
            toClient = new PrintWriter(client.getOutputStream());
            String comandoDoClient = "";
            do {
                comandoDoClient = fromClient.nextLine();
                Integer result = Integer.valueOf(comandoDoClient);
                switch (result) {
                    case 1:
                        List<String> arquivos = new ArrayList();
                        arquivos = listarArquivos(raiz, "");
                        toClient.println(arquivos);
                        toClient.flush();
                        break;
                    case 2:
                        String nomeDir = "";
                        nomeDir = fromClient.nextLine();
                        File criarArquivo = new File("D:\\aepProg" + nomeDir);
                        criarArquivo.mkdirs();
                        break;
                    case 3:
                        String nomeArq = "";
                        nomeArq = fromClient.nextLine();
                        File excluirArquivo = new File("D:\\aepProg" + nomeArq);
                        excluirArquivo.delete();
                        break;
                    case 5:
                        toClient.println("Servidor desconectado!");
                        toClient.flush();
                        //socket.close();
                    default:
                        break;
                }
            } while (!comandoDoClient.equals("5"));
        } catch (Exception e) {

        }

    }

    private List<String> listarArquivos(File entrada, String identacao) throws IOException {
        diretorio.add((identacao + entrada.getAbsolutePath() + "\n"));
        if (entrada.isDirectory()) {
            for(File arquivo : entrada.listFiles()) {
                listarArquivos(arquivo, identacao + " ");
            }
        }
        return diretorio;
    }
}
