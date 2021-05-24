package librarymanagementsystem;


import java.util.Scanner;


public class LibararyManagementSystem {
    
    
    
    public void runLibrary(){
        Scanner input =  new Scanner(System.in);
        int choice = 0;
        Book book =  new Book();
        Member member =  new Member();
        
        greetingMessage();
        helpMessage();
        
        
        do{
            
            System.out.println();
            System.out.print("Please Enter your choice: ");
            choice = input.nextInt();
            
            switch(choice){
                case 1:
                    book.add();
                    break;
                    
                case 2:
                    book.search();
                    break;
                    
                case 3:
                    member.add(1, "Shiza", "Kabir", "Mansehra", "03419382222");
                    break;
                    
                case 4:
                    member.search();
                    break;
                    
                case 5:
                    book.showBorrowed();
                    break;
                    
                case 6:
                    book.showAvailable();
                    break;
                    
                    
                case 7:
                    System.out.println("System will exit");
                    
                    
                default:
                    helpMessage();
            }
            
        }while(choice!=7);
        
        goodByeMessage();
        
        
    }
    
    public void greetingMessage(){
        System.out.println("____________WELCOME_________________");
        System.out.println("__EXARTH LIBRARY MANAGEMENT SYSTEM__");
        System.out.println("____________________________________");
        System.err.println("\n");
    }
    
    public void goodByeMessage(){
        System.out.println("");
        System.out.println("__Thanks for Using Our Application__");
        System.out.println("____________________________________");
    }
    
    public void helpMessage(){
        System.out.println("Press 1: Add Book");
        System.out.println("Press 2: Search Book");
        System.out.println("Press 3: Add Member");
        System.out.println("Press 4: Search Member");
        System.out.println("Press 5: Show Borrowed Books");
        System.out.println("Press 6: Show available Books");
        System.out.println("Press 7: Exit");
    }
    
}
