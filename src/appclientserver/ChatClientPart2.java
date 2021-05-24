package appclientserver;


import java.io.*;
import java.net.Socket;


public class ChatClientPart2 {

    static Socket s;
    static int port;
    static String name;

    public static void main(String[] args) {

        Receivemsg1 receive = new Receivemsg1();
        Sendmsg1 send = new Sendmsg1();

        Thread receiver = new Thread(receive);
        Thread sender = new Thread(send);

        try{
            port = 6666;
            s = new Socket("localhost", port);
            
            System.out.println("_____________________________________________________");
            System.out.println("Connected to: " + s.getRemoteSocketAddress());
            System.out.println("** Note: Type 'bye' and press Enter to disconnect **\n");
            System.out.println("--------------CLIENT APP IS READY TO USE ------------");
            System.out.println("_____________________________________________________");
            System.out.println();

            receiver.start();
            sender.start();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

class Sendmsg1 implements Runnable {
    @Override
    public void run() {
        String input;
        PrintWriter out = null;
        try {
            out = new PrintWriter(ChatClientPart2.s.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (!(input = br.readLine()).equals("bye")){
                out.println(input);
            }
            out.println("Client disconnected");
            ChatClientPart2.s.close();
        } catch (IOException e) {
            System.out.println("Disconnected");
        }
    }
}

class Receivemsg1 implements Runnable {
    @Override
    public void run() {
        String line;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(ChatClientPart2.s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while((line = in.readLine()) != null) {
                if(line.equals("Server disconnected")){
                    System.out.println("> Server: bye");
                    System.out.println(line);
                    break;
                }
                String message = "> Server: " + line;
                System.out.println(message);
                
                FileWriter fileWriter = new FileWriter("text.txt", true);
                try (BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
                    bufferWriter.write(message);
                    bufferWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("SErver has been Disconnected");
        }
    }
}
