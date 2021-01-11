//package com.example.jinlin.Test;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class LongLiveServer {
//    public static void main(String [] args) throws IOException {
//        ServerSocket serverSocket = new ServerSocket(1111);
//        Socket socket = serverSocket.accept();
//
//        while(true){
//            try{
//                Thread.sleep(3000);
//            }
//            catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            if(socket != null){
//                InputStream is = null;
//                OutputStream os = null;
//                try {
//                    String ip = socket.getInetAddress().toString().replace("/", "");
//                    System.out.println("====socket.getInetAddress() =====" + ip);
//                    socket.setKeepAlive(true);
//
//                }
//            }
//        }
//    }
//}
