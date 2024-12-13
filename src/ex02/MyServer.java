package ex02;

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

    // 메서드말고 전체적인 흐름 파악
    public static void main(String[] args) {
        try {
            // 1. 리스너 생성 및 대기
            ServerSocket serverSocket = new ServerSocket(20000);
            Socket socket = serverSocket.accept();

            // 2. 버퍼 달기(반이중 연결) - 읽고 쓴다
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); // 해당 부분 지우면 단방향

            // 3. 요청받고 응답하기
            while(true) { // 메인 스레드가 while을 반복
                String line = br.readLine();
                String response = parser(line);
                pw.println(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // parser
    // 메서드를 만들고 분리하기
    private static String parser(String line) {
        String header = line.split(":")[0];
        String body = line.split(":")[1];

        String response = "404";

        if(header.equals("buyer")){
            if(body.equals("1")) response = "사과";
            else if(body.equals("2")) response = "라면";
        }

        if(header.equals("seller")){
            if(body.equals("1")) response = "당근";
            else if(body.equals("2")) response = "우유";
        }

        return response;
    }
}