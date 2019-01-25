package ex2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
    private Socket socket;
    private String answer;
    private Thread thread;
    private int numberOfRequest;

    public Client(Socket socket, String answer, int numberOfRequest) {
        this.socket = socket;
        this.answer = answer;
        this.numberOfRequest = numberOfRequest;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try (InputStream is = socket.getInputStream();
             OutputStream os = socket .getOutputStream();
             PrintWriter pWriter = new PrintWriter(os)) {

            byte[] request = new byte[is.available()];
            is.read(request);
            String response = "HTTP/1.1 200 OK\r\n" + "Server: My_Server\r\n" + "Content-Type: text/html\r\n"
                    + "Content-Length: " + "\r\n" + "Connection: close\r\n\r\n";

            answer += "\n<h2>Your request number - "+ numberOfRequest +"</h2>";
            answer += "\n</body></html>";
            pWriter.print(response +""+ answer);
            pWriter.flush();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
