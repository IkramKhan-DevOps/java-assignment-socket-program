package appclientserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable{

    static int port;
    static ServerSocket ss;
    static Socket s;


    @Override
    public void run() {


        try{
            port = 6666;
            ss = new ServerSocket(port);
            s = ss.accept();
            System.out.println("_____________________________________________________");
            System.out.println("Server Started with port: " + ss.getLocalPort());
            System.out.println("Connection Established with client: " + s.getRemoteSocketAddress());
            System.out.println("** Note: Type 'bye' and press Enter to disconnect **\n");
            System.out.println("--------------SERVER APP IS READY TO USE ------------");
            System.out.println("_____________________________________________________\n");
            
            System.out.println("Establishing connection .............................");
            System.out.println("Incase of failur press any key to reconnect .........\n");
            
            System.out.println();

        }catch(IOException e){
            e.printStackTrace();
        }
    
    }
    
    void sent() {
        String input;
        PrintWriter out = null;
        try {
            out = new PrintWriter(ChatServer.s.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Server: ");
            input = br.readLine();
            out.println(input);
            
            FileWriter fileWriter = new FileWriter("text.txt", true);
            try (BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
                bufferWriter.write("SEVCER: "+input);
                bufferWriter.newLine();
            }
            
            if(input.equalsIgnoreCase("bye")){
                System.out.println();
                System.out.println("----------------------------------------------------");
                System.out.println("Terminating Client-Server Chat application by Server");
                System.out.println("---------------------THANKS-------------------------");
                System.exit(0);
            }
            
        } catch (IOException e) {
            System.out.println("Disconnected");
        }
    }
    
}

