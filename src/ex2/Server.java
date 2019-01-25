package ex2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void socketServer(){
        String answer = "";
        Runtime runtime = Runtime.getRuntime();
        answer += "<html><body>";
        answer += "\n<h3>Max memory - "+ runtime.maxMemory()+"</h3>";
        answer += "\n<h3>Total memory - "+ runtime.totalMemory()+"</h3>";
        answer += "\n<h3>Free memory - "+ runtime.freeMemory()+"</h3>";
        answer += "\n<h3>Available processors - "+ runtime.availableProcessors()+"</h3>";
        answer += "\n<h2>Operation System - "+ System.getProperties().getProperty("os.name")+"</h3>";

        int number = 0;
        try (ServerSocket serverSocket = new ServerSocket(8080)){
            while (true) {
                Socket socket = serverSocket.accept();
                number++;
                Client client = new Client(socket, answer, number);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
