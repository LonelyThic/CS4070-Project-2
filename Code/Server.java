import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 12345;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server running on port " + port);

            Socket clientSocket = serverSocket.accept();
            String clientIP = clientSocket.getInetAddress().getHostAddress();

            System.out.println("Connected to client: " + clientIP);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            // Initial messages
            out.println("server: got connection from client " + clientIP);
            out.println("Server is ready...");
            out.println("Available commands:");
            out.println("UPPERCASE");
            out.println("REVERSE"); 

            String command;

            while ((command = in.readLine()) != null) {
                System.out.println(clientIP + " sends " + command);

                if (command.equals("UPPERCASE")) {
                    out.println("200 OK");
                    handleUppercase(in, out);

                } else if (command.equals("REVERSE")) {
                    out.println("200 OK");
                    String msg = in.readLine();
                    String reversed = new StringBuilder(msg).reverse().toString();
                    out.println(reversed);

                } else {
                    out.println("400: Not a valid command!");
                }
            }

            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleUppercase(BufferedReader in, PrintWriter out) throws IOException {
        String line;
        while (!(line = in.readLine()).equals(".")) {
            out.println(line.toUpperCase());
        }
    }
}