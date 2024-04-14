import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Date;
import java.util.Random;

public class main01 {
    // To do: 
    // Store instances to queue, when we queue.offer or remove an item to array, we need to create another instance of civilian again (so it loops)
    // Create a main loop, whereas it breaks if the player won or lose
    // Create a detector for forbidden datas (this always run in main loop) - probably iterate all the data in the instances? then compare it to another array if that forbidden element exist
    // Under this, we also need to create a a random date, the a comparator for checking the expiry date
    // For current date we can get system time, if the civilian passport date is greater than our system time, then it is a expired passport
    // Forbidden name, and occupation concept is the same


    public static Scanner scanner = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        // -- SETUP -- //
        Queue<Civilian> queue = new LinkedList<>();
        int numberOfApprovedCivilians = 0;
        int civilianNumber = 0;
        

        // -- CONSTANTS -- // 
        String[] firstNames = {"John", "Jane", "Alice", "Bob", "Michael", "Emily", "David", "Sarah"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson"};
        String[] goodOccupations = {
            "Teacher",
            "Doctor",
            "Engineer",
            "Software Developer",
            "Nurse",
            "Accountant",
            "Lawyer",
            "Chef",
            "Artist",
            "Writer",
            "Musician",
            "Police Officer",
            "Firefighter",
            "Architect",
            "Electrician",
            "Plumber",
            "Mechanic",
            "Real Estate Agent",
            "Entrepreneur",
            "Scientist"
        };

        String[] badOccupations = {
            "Kidnapper",
            "Thief",
            "Burglar",
            "Assassin",
            "Drug Dealer",
            "Bank Robber",
            "Scammer",
            "Blackmailer",
            "Counterfeiter",
            "Smuggler",
            "Hacker",
            "Fraudster",
            "Pirate",
            "Arsonist",
            "Embezzler",
            "Extortionist",
            "Hitman",
            "Con Artist",
            "Fence",
            "Money Launderer"
        };
        
        // COMBINE THE TWO ARRAY
        String[] occupations = new String[goodOccupations.length + badOccupations.length];
        
        // Copy elements from goodOccupations
        for (int i = 0; i < goodOccupations.length; i++) {
            occupations[i] = goodOccupations[i];
        }
        
        // Copy elements from badOccupations
        for (int i = 0; i < badOccupations.length; i++) {
            occupations[goodOccupations.length + i] = badOccupations[i];
        }
        
        // shuffle just to make sure it is random
        occupations = shuffle(occupations);


        // create random criminal name
        int randNamePicker01 = rand.nextInt(firstNames.length);
        int randNamePicker02 = rand.nextInt(lastNames.length);
        String criminalName = firstNames[randNamePicker01] + " " + lastNames[randNamePicker02];
        
        // ADDING INSTANCES IN QUEUE (INITIAL CIVILIAN) - WE NEED TO RANDOMIZE THIS TOO
        Civilian civilian01 = new Civilian("Bob", "Carpenter", 23, "04-12-2024", "Hello Officer");
        queue.offer(civilian01);

        // -- MAIN LOOP -- // 
        while (true){

            // REMOVING & RETRIEVING INSTANCES IN QUEUE
            Civilian retrievedCivilan = queue.poll();
    
            addDialogEffect(retrievedCivilan.dialog);
            clrscr();

            addDialogEffect("Here's my passport: ");
            clrscr();
            

            // MAYBE WE CAN CREATE SIMPLE BORDER FOR THIS ??
            System.out.println(" ------ PASSPORT DETAILS ---------");
            System.out.println("Civilian " + civilianNumber + ":");
            System.out.println("Name: " + retrievedCivilan.name);
            System.out.println("Age: " + retrievedCivilan.age);
            System.out.println("Occupation: " + retrievedCivilan.occupation);
            System.out.println("Expiration Date: " + retrievedCivilan.expiration);
            
            // CHECK THE INSTANCES VALUE (MAKE A FUNCTION FOR IT) - IN THAT FUNCTION, ALL FORBIDDEN VALUES ARE THERE, THIS WILL ONLY RETURN TRUE OR FALSE

            // IF IT RETURNED FALSE, BREAK THE MAIN LOOP
            break; // break for now (debugging purposes)




            // KEEP CREATING INSTANCES AS WE APPROVE A CIVILIAN
            // RANDOMIZE NAME, AGE, OCCUPATION, AND EXPIRATION
        


        }

        
        
    }


    public static boolean checkPassport(String criminalName, String name, int age, String occupation, String expdate){
        
        // TO DO: (FORBIDDEN VALUES CHECKER) 
        // compare the date today, to the date of passport for checking if the passport is expired
        // create one criminal
        // age validator
        // occupation validator

        return false;
    }
    
    //method for shuffling 
    public static String[] shuffle(String[] arr)
    {
        //fisher-yates shuffle
        int index;
        String temp;
        
        for (int i = arr.length - 1; i > 0; i--)
        {
            index = rand.nextInt(i + 1);
            temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    
    // -- DISPLAY UTILS -- //

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
    String expiration;
    int age;
    String dialog;
    
    Civilian(String name, String occupation, int age, String expiration, String dialog){
        this.name = name;
        this.occupation = occupation;
        this.age = age;
        this.expiration = expiration;
        this.dialog = dialog;
    }

}





