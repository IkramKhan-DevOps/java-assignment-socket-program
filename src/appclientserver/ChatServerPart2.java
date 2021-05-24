/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appclientserver;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerPart2 {

    static int port;
    static ServerSocket ss;
    static Socket s;

    public static void main(String[] args) {

        Receivemsg receive = new Receivemsg();
        Sendmsg send = new Sendmsg();

        Thread receiver = new Thread(receive);
        Thread sender = new Thread(send);

        try{
            port = 6666;
            ss = new ServerSocket(port);
            s = ss.accept();
            System.out.println("_____________________________________________________");
            System.out.println("Server Started with port: " + ss.getLocalPort());
            System.out.println("Connection Established with client: " + s.getRemoteSocketAddress());
            System.out.println("** Note: Type 'bye' and press Enter to disconnect **\n");
            System.out.println("--------------SERVER APP IS READY TO USE ------------");
            System.out.println("_____________________________________________________");
            System.out.println();

            receiver.start();
            sender.start();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}


class Sendmsg implements Runnable {
    @Override
    public void run() {
        String input;
        PrintWriter out = null;
        try {
            out = new PrintWriter(ChatServerPart2.s.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (!(input = br.readLine()).equals("bye")){
                out.println(input);
            }
            out.println("Server disconnected");
            ChatServerPart2.s.close();
        } catch (IOException e) {
            System.out.println("Disconnected");
        }
    }
}


class Receivemsg implements Runnable {
    @Override
    public void run() {
        String line;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(ChatServerPart2.s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while((line = in.readLine()) != null) {
                if(line.equals("Client disconnected")){
                    System.out.println("> Client: bye");
                    System.out.println(line);
                    break;
                }
                
                String message = "> Client: " + line;
                String output_message = "";
                
                System.out.println(message);
                
                if(message.contains("file:")){
                    String[] newString = message.split(":");
                    if (!newString[2].equals("")) {
                        String file_name = newString[2]+".txt";
                        File file = new File("serverfiles/"+file_name);
                        
                        if (file.exists()) {
                            
                            File dest= new File("clientfiles/"+file_name);
                            try {
                                InputStream is = new FileInputStream(file);
                                OutputStream os = new FileOutputStream(dest);
                                byte[] buffer = new byte[1024];
                                int length;
                                while ((length = is.read(buffer)) > 0) {
                                    os.write(buffer, 0, length);
                                }
                                output_message = "Requeted file transfered to client location.";
                            } catch (IOException e) {e.printStackTrace();}
                            
                        }else{
                            output_message = "Requested File: "+file_name+" doesn't exists";
                        }
                    }else{
                        output_message = "Something is wrong with file naming --> please don't add .txt at the end";
                    }
                }
                System.out.println(output_message);
                
                FileWriter fileWriter = new FileWriter("text.txt", true);
                try (BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
                    bufferWriter.write(message);
                    bufferWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Server has been Disconnected");
        }
    }
}
