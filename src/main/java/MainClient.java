import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("|----------Bem vindo----------|");
        System.out.println("|1 - Conectar-se              |");
        System.out.println("|2 - Sair                     |");
        System.out.println("|-----------------------------|");
        System.out.print(">> ");
        Scanner menu = new Scanner(System.in);
        do {
            if(menu.nextLine().equals("1")) {
                MainClient client = new MainClient();
                client.start();
            }

        } while (!menu.nextLine().equals("2"));
        System.out.println("Finish");
    }

    private void start() {
        try {
            Scanner console = new Scanner(System.in);
            Socket server = new Socket("localhost", 8080);
            PrintWriter toServer = new PrintWriter(server.getOutputStream());
            Scanner fromServer = new Scanner(server.getInputStream());
            System.out.println("\n Conectado ao servidor! \n");
            System.out.println(menu());
            do {
                System.out.print("Escolha uma opção >> ");
                String comando = console.nextLine();
                Integer result = Integer.valueOf(comando);
                switch (result) {
                    case 1:
                        toServer.println("1");
                        toServer.flush();
                        String resposta = fromServer.nextLine();
                        System.out.println("Resposta do server: " + resposta);
                        break;
                    case 2:
                        System.out.print("Nome do diretorio>> ");
                        comando = console.nextLine();
                        toServer.println("2");
                        toServer.flush();
                        toServer.println(comando);
                        toServer.flush();
                        resposta = fromServer.nextLine();
                        System.out.println("Resposta do server: " + resposta);
                    case 3:
                        System.out.print("Nome do arquivo a ser excluido >> ");
                        comando = console.nextLine();
                        toServer.println("3");
                        toServer.flush();
                        toServer.println(comando);
                        toServer.flush();
                        resposta = fromServer.nextLine();
                        System.out.println("Resposta do server: " + resposta);
                    case 5:
                        comando = console.nextLine();
                        toServer.println("5");
                        toServer.flush();
                        resposta = fromServer.nextLine();
                        System.out.println("Resposta do server: " + resposta);
                    default:
                        break;
                }
                toServer.println(comando);
                toServer.flush();
                String resposta = fromServer.nextLine();
                System.out.println("Resposta do server: " + resposta);
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String menu() {
        String Menu;
        Menu  = "|-----Escolha uma opcao-----|\n";
        Menu +=	"| 1 - Listar arquivos       |\n";
        Menu +=	"| 2 - Criar diretorio       |\n";
        Menu +=	"| 3 - Remover diretorio     |\n";
        Menu +=	"| 4 - Enviar arquivo        |\n";
        Menu +=	"| 5 - Desconectar           |\n";
        Menu +=	"|---------------------------|\n";

        return Menu;
    }
}
