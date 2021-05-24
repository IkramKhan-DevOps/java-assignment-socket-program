package appclientserver;




import java.io.*;
import java.net.Socket;


public class ChatClient {

    static Socket s;
    static int port;
    static String name;

    public ChatClient() {
        ChatServer charServer =  new ChatServer();
        Thread thread = new Thread(charServer);
        thread.start();

        try{
            port = 6666;
            s = new Socket("localhost", port);

        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    void sent() {
        String input;
        PrintWriter out = null;
        try {
            out = new PrintWriter(ChatClient.s.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Client: ");
            input = br.readLine();
            out.println(input);
            
            FileWriter fileWriter = new FileWriter("text.txt", true);
            try (BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
                bufferWriter.write("CLIENT: "+input);
                bufferWriter.newLine();
            }
            
            String output_message = "";
            if(input.contains("file:")){
                    String[] newString = input.split(":");
                    if (!newString[1].equals("")) {
                        String file_name = newString[1]+".txt";
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
            
            if(input.equalsIgnoreCase("bye")){
                System.out.println();
                System.out.println("----------------------------------------------------");
                System.out.println("Terminating Client-Server Chat application by Client");
                System.out.println("---------------------THANKS-------------------------");
                System.exit(0);
            }
            
            
        } catch (IOException e) {
            System.out.println("Disconnected");
        }
    }
    


}
