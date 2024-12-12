package ex01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 10000); // 로컬호스트와 포트 필요

        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        pw.println("Hello World");

//        BufferedWriter bw = new BufferedWriter(
//                new OutputStreamWriter(socket.getOutputStream())
//        );
//
//        // 프로토콜 통신 약속: \n 붙임.없으면 터진다
//        // bw여러개면 마지막에만 \n 붙이기
//        bw.write("Hello World\n"); // 글작성
//        bw.flush(); // 전송
    }
}
