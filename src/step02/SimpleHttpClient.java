package step02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SimpleHttpClient {

  public static void main(String[] args) throws Exception {
    Socket socket = new Socket("www.github.com/jinioh88",80);
    BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintStream out = new PrintStream(socket.getOutputStream());
    
    out.println("GET / HTTP/1.1");
    
    out.println("Host:www.github.com/jinioh88");
    out.println(); // 공백라인
    
    String line = null;
    while((line=in.readLine())!= null) {
      System.out.println(line);
    }
    in.close();
    out.close();
    socket.close();

  }

}
