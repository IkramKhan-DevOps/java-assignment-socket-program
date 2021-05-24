package appclientserver;


public class AdvancedApplication {
    
    public static void main(String[] args) {
        
        ChatServer chatServer =  new ChatServer();   
        ChatClient chatClient =  new ChatClient();
        
        int index = 1;
        while(true){
            if (index == 1){
                chatServer.sent();
                index = 0;
            }else{
                chatClient.sent();
                index =1;
            }
        }
    }
    
}
