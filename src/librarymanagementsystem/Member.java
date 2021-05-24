package librarymanagementsystem;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Member {
    
    public int id;
    public String name;
    public String fatherName;
    public String address;
    public String contactNo;

    public void add(){
        Scanner sc =  new Scanner(System.in);
        
        //USER_INPUTS --------------------------------------------------------
        System.out.println();
        
        System.out.print("Enter member ID: ");
        String memberId = sc.next();
        
        System.out.print("Enter member Name: ");
        String memberName = sc.next();
        
        System.out.print("Enter member Fathername: ");
        String memberFathername = sc.next();
        
        System.out.print("Enter member Address: ");
        String memberAddress = sc.next();
        
        System.out.print("Enter member Contact: ");
        String memberContact = sc.next();
        
        //WRITING_TO_FILE ----------------------------------------------------
        try(FileWriter fileWriter = new FileWriter("member.txt", true)){
            try (BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
                
                bufferWriter.write(
                        memberId+","+memberName+","+memberFathername+","+
                        memberAddress+","+memberAddress+","+memberContact
                );
                bufferWriter.newLine();
                System.out.println("Member Information has been stored Successfully.");
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void search(){
        try {
            
            Scanner sc =  new Scanner(System.in);
            File file = new File("member.txt");
            Scanner scanner = new Scanner(file);
        
            //USER_INPUTS ----------------------------------------------------
            System.out.println();
        
            System.out.print("Enter Member Name To Search: ");
            String memberName = sc.next();
            
            //USER_SEARCH ----------------------------------------------------
            boolean flag = false;
            
            while (scanner.hasNextLine()) {
                
                String line = scanner.nextLine();
                String[] lineArray =  line.split(",");
                
                if(lineArray[1] == null ? memberName == null : lineArray[1].equals(memberName)){
                    flag = true;
                    break;
                }
                
            }
            
            if(flag==true){
                System.out.println("Member available with Name: "+memberName);
            }else{
                System.out.println("Book doesn't available with Name: "+memberName);
            }
            
            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File Doesn't exists with name member.txt");
        }
    }
    
}
