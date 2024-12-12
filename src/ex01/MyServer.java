package ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(10000); // 1. 서버포켓 생성

            Socket socket = serverSocket.accept(); // 2. 리스닝
            System.out.println("oh! connect?");

            BufferedReader br = new BufferedReader(
                    // 소켓으로 부터 input 읽음
                    new InputStreamReader(socket.getInputStream())
            );

            String line = br.readLine(); // 버퍼에 있는 메세지를 \n까지 읽는다
            System.out.println("read : " + line); // 읽고 끝. 쓰려면 밑에 bw 구현

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
