package ex03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
buyer:1 -> 사과
seller:1 -> 당근
buyer:2 -> 라면
seller:2 -> 우유

위 유형이 아니면 404 응답
 */
public class MyServer {

    public static void main(String[] args) {
        try {
            // main 스레드
            ServerSocket serverSocket = new ServerSocket(20000);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(socket.getInputStream())
                        );
                        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

                        String line = br.readLine();
                        String response = parser(line);
                        pw.println(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parser(String line) {
        String header = line.split(":")[0];
        String body = line.split(":")[1];

        String response = "404";

        if (header.equals("buyer")) {
            if (body.equals("1")) response = "사과";
            else if (body.equals("2")) response = "라면";
        }

        if (header.equals("seller")) {
            if (body.equals("1")) response = "당근";
            else if (body.equals("2")) response = "우유";
        }
        return response;
    }
}