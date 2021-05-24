package applibrarymanagement;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Book {
    
    public int id;
    public String title;
    public int edition;
    public float price;
    public int noOfPages;
    
    public static int totalBooks;
    public static int availableBooks;
    public static int borrowedBooks;
    
    
    public void add(){
        Scanner sc =  new Scanner(System.in);
        
        //USER_INPUTS --------------------------------------------------------
        System.out.println();
        
        System.out.print("Enter Book ID: ");
        String bookId = sc.next();
        
        System.out.print("Enter Book Name: ");
        String bookName = sc.next();
        
        System.out.print("Enter Book Edition: ");
        String bookEdition = sc.next();
        
        System.out.print("Enter Book Price: ");
        String bookPrice = sc.next();
        
        System.out.print("Enter Book Total Page: ");
        String bookPages = sc.next();
        
        //WRITING_TO_FILE ----------------------------------------------------
        try(FileWriter fileWriter = new FileWriter("book.txt", true)){
            try (BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
                
                bufferWriter.write(
                        bookId+","+bookName+","+bookEdition+","+
                        bookPrice+","+bookPages+","+String.valueOf(false)
                );
                bufferWriter.newLine();
                System.out.println("Book Inormation has been stored Successfully.");
                
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
            File file = new File("book.txt");
            Scanner scanner = new Scanner(file);
        
            //USER_INPUTS ----------------------------------------------------
            System.out.println();
        
            System.out.print("Enter Book Name To Search: ");
            String bookName = sc.next();
            
            //USER_SEARCH ----------------------------------------------------
            boolean flag = false;
            
            while (scanner.hasNextLine()) {
                
                String line = scanner.nextLine();
                String[] lineArray =  line.split(",");
                
                if(lineArray[1] == null ? bookName == null : lineArray[1].equals(bookName)){
                    flag = true;
                    break;
                }
                
            }
            
            if(flag==true){
                System.out.println("Book available in stock with Name: "+bookName);
            }else{
                System.out.println("Book doesn't available in stock with Name: "+bookName);
            }
            
            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File Doesn't exists with name book.txt");
        }
    }
    
    public void showAvailable(){
        System.out.println("Avaialble Books :"+ Book.availableBooks);
    }
    
    public void showBorrowed(){
        System.out.println("Borrowed Books :"+ Book.borrowedBooks);
    }
    
}
