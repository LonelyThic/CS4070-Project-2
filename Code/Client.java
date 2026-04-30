import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try {
            Socket socket = new Socket(host, port);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            // Initial messages
            for (int i = 0; i < 7; i++) {
                    System.out.println(in.readLine());
            }

            while (true) {
                System.out.print("Enter command: ");
                String command = scanner.nextLine();
                out.println(command);

                String response = in.readLine();
                System.out.println("s: " + response);

                if (command.equals("UPPERCASE")) {
                    String line;
                    while (true) {
                        System.out.print("Enter text (end with .): ");
                        line = scanner.nextLine();
                        out.println(line);
                        if (line.equals(".")) break;
                    }

                    // Read results
                    while (true) {
                        socket.setSoTimeout(500);
                        try {
                            String result = in.readLine();
                            if (result == null) break;
                            System.out.println("s: " + result);
                        } catch (IOException e) {
                            break;
                        }
                    }
                    socket.setSoTimeout(0);
                }

                else if (command.equals("REVERSE")) {
                    System.out.print("Enter text: ");
                    String text = scanner.nextLine();
                    out.println(text);
                    System.out.println("s: " + in.readLine());
                }

                else if(command.equals("LOWERCASE")){
                    String line;
                    while(true){
                        System.out.print("Enter text (end with .): ");
                        line = scanner.nextLine();
                        out.println(line);
                        if (line.equals(".")) break;
                    }

                    while (true) {
                        socket.setSoTimeout(500);
                        try {
                            String result = in.readLine();
                            if (result == null) break;
                            System.out.println("s: " + result);

                        }catch(IOException e){
                            break;
                        }






                    }

                    socket.setSoTimeout(0);




                }
                else if (command.equals("EXIT")){
                    break;
                }



            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
