import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) {
        int port = 1234;
        ServerSocket serverSocket;
        Socket socket;
        System.out.println("It's time to gamer");

        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for connections");
                socket = serverSocket.accept();

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Client connected");

                String name = in.readLine();
                if (name.equals("Shutdown")) {
                    out.println("SERVER: Oh no, you terminated me...");
                    in.close();
                    out.close();
                    socket.close();
                    System.out.println("server shutting down...");
                    System.exit(0);
                }

                System.out.println("Client name is \"" + name + "\"");
                System.out.println("Sending feedback");
                out.println("SERVER: Welcome " + name + "! Keep up the good work");

                in.close();
                out.close();
                socket.close();

                System.out.println("Closing down " + name);
            }
        } catch (Exception e) {
            System.out.println("Server fail");
        }
    }
}
