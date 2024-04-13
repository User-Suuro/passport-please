
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class main01 {
    // To do: 
    // Store instances to queue, when we queue.offer or remove an item to array, we need to create another instance of civilian again (so it loops)
    // Create a main loop, whereas it breaks if the player won or lose
    // Create a detector for forbidden datas (this always run in main loop) - probably iterate all the data in the instances? then compare it to another array if that forbidden element exist
    // Under this, we also need to create a a random date, the a comparator for checking the expiry date
    // For current date we can get system time, if the civilian passport date is greater than our system time, then it is a expired passport
    // Forbidden name, and occupation concept is the same
        


    public static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        clrscr();
        addDialogEffect("Good Morning Officer");
        clrscr();
        addDialogEffect("Here's my passport: ");

      

        Queue<Civilian> queue = new LinkedList<>();
  
        // ADDING INSTANCES IN QUEUE
      
        Civilian civilian01 = new Civilian("Bob", "Carpenter", 23);
        queue.offer(civilian01);


        // REMOVING & RETRIEVING INSTANCES IN QUEUE
        Civilian retrievedCivilan = queue.poll();
        System.out.println("Civilian 01: ");
        System.out.println("Name is: " + retrievedCivilan.name);
        System.out.println("Age: " + retrievedCivilan.age);
        System.out.println("Occupation: " + retrievedCivilan.occupation);
        
    }

 
    // -- UTILS -- //

    // Clear Screen method
    public static void clrscr() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec(new String[]{"clear"});
        } catch (Exception ex) {
            System.out.println("Failed to clear the screen.");
        }
    }
    
    //method for pausing in command prompt
    public static void sleep(long ms){
        try{
            Thread.sleep(ms);
        } catch (InterruptedException e){};
    }

    // dialog effect 
    public static void addDialogEffect(String line){
         // Convert the String to a char array
         char[] charArray = line.toCharArray();
        
         // Print each character in the char array
         for (char c : charArray) {
             System.out.print(c);
             sleep(50);
         }

         System.out.println();
         System.out.println("Please press any key to continue");
         scanner.nextLine();
    }
}

class Civilian{
    String name;
    String occupation;
    int age;
   
    Civilian(String name, String occupation, int age){
        this.name = name;
        this.occupation = occupation;
        this.age = age;
    }

}





