import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

// DATE IMPORTS
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class main01 {
    public static Scanner scanner = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        clrscr();

        // -- STATES -- //
        Queue<Civilian> queue = new LinkedList<>();
        int numberOfApprovedCivilians = 0;
        int civilianNumber = 0; 

        // -- CONSTANTS -- // 

        // NAME
        String[] firstNames = {"John", "Jane", "Alice", "Bob", "Michael", "Emily", "David", "Sarah"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson"};

        // CRIMINAL NAME
        String criminal_fname = firstNames[rand.nextInt(firstNames.length)];
        String criminal_lname = lastNames[rand.nextInt(firstNames.length)];

        // DIALOG
        String[] greetingDialogs = {
            "Hello!",
            "Hi there!",
            "Good morning!",
            "Good afternoon!",
            "Good evening!",
            "Hey!",
            "Greetings!",
            "Howdy!",
            "Salutations!",
            "What's up?",
            "Yo!",
            "Hi, how are you?",
            "Nice to meet you!",
            "Hey, what's going on?",
            "How's it going?",
            "Hi, nice to see you!",
            "Long time no see!",
            "Hey, how have you been?",
            "Hello, how's everything?",
            "Good to see you!",
        };


        // OCCUPATIONS
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

        // DATE
        LocalDate getDate = LocalDate.now();
        String formatCurrentDate = formatDate(getDate);

        // ADDING INSTANCES IN QUEUE (INITIAL CIVILIAN) 
        LocalDate startDate = getDate.minusMonths(1); // Start date is one month behind the current date
        LocalDate endDate = getDate.plusMonths(1);    // End date is one month ahead of the current date
        
       
        displayIntro(formatCurrentDate, criminal_fname, criminal_lname);


        // -- MAIN LOOP -- // 
        while (true){
            clrscr();

            // KEEP CREATING INSTANCES (RANDOMIZE NAME, AGE, OCCUPATION, AND EXPIRATION)
            LocalDate randomDate = generateRandomDate(startDate, endDate);
            Civilian civilian = new Civilian(firstNames[rand.nextInt(firstNames.length)], firstNames[rand.nextInt(lastNames.length)], 
            occupations[rand.nextInt(occupations.length)], generateRandomAge(15, 150), formatDate(randomDate), greetingDialogs[rand.nextInt(greetingDialogs.length)]);
            queue.offer(civilian);

            // REMOVING & RETRIEVING INSTANCES IN QUEUE
            Civilian retrievedCivilan = queue.poll();
            System.out.println("Civilian " + civilianNumber + ":");
            addDialogEffect(retrievedCivilan.dialog);
            addDialogEffect("Here's my passport: ");
            clrscr();
            
            // MAYBE WE CAN CREATE SIMPLE BORDER FOR THIS ??
            System.out.println(" ------ PASSPORT DETAILS ---------");
            System.out.println("First Name: " + retrievedCivilan.fname);
            System.out.println("Last Name: " + retrievedCivilan.lname);
            System.out.println("Age: " + retrievedCivilan.age);
            System.out.println("Occupation: " + retrievedCivilan.occupation);
            System.out.println("Expiration Date: " + retrievedCivilan.expiration);
            
          
            // CHECK THE INSTANCES VALUE (MAKE A FUNCTION FOR IT) - IN THAT FUNCTION, ALL FORBIDDEN VALUES ARE THERE, THIS WILL ONLY RETURN TRUE OR FALSE
            
            // COMPARE IT WITH DECISION INPUT


            break; // break for now (debugging purposes)

          

        }

    }

    public static void displayIntro(String date, String criminal_fname, String criminal_lname){
        addDialogEffect("Today is " + date);
        addDialogEffect("NEWS: WANTED CRIMINAL: First Name: " + criminal_fname + " Last Name: " + criminal_lname);
        addDialogEffect("We decided to open the borders");
    }

    public static boolean checkPassport(String criminal_fname, String criminal_lname, String civilian_fname, String civilian_lname, 
                                        int civilian_age, String occupation, String expdate, String[] badOccupation){
        
        // TO DO: (FORBIDDEN VALUES CHECKER) 

        // CRIMINAL CHECKER - DONE
        if (criminal_fname.equalsIgnoreCase(civilian_fname) && criminal_lname.equalsIgnoreCase(civilian_lname)) {
            return true;
        }

        // AGE VALIDATOR - DONE
        if (civilian_age > 100){ // maybe over a hundered years old is suspicious enough?
            return true;
        }

        // OCCUPATION VALIDATOR - DONE
        if (contains1D(badOccupation, occupation)){
            return true;
        }

        // EXPDATE VALIDATOR




        // RETURN FALSE IF NOT SUSPICIOUS
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

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return date.format(formatter);
    }

    public static boolean contains1D(String[] arr, String target){
        for (String string : arr) {
            if (string.equalsIgnoreCase(target)){
                return true;
            }
        }
        return false;
    }


    // -- RANDOM GENERATOR UTILS -- //
    public static int[] generateRandomIntArray(int size, int min, int max) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt((max - min) + 1) + min;
        }
        return arr;
    }

    public static int generateRandomAge(int minAge, int maxAge) {
        Random rand = new Random();
        return rand.nextInt((maxAge - minAge) + 1) + minAge;
    }

    
    public static LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        Random random = new Random();
        long randomEpochDay = startEpochDay + random.nextInt((int) (endEpochDay - startEpochDay));
        return LocalDate.ofEpochDay(randomEpochDay);
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
    String fname;
    String lname;
    String occupation;
    String expiration;
    int age;
    String dialog;
    
    Civilian(String fname, String lname, String occupation, int age, String expiration, String dialog){
        this.fname = fname;
        this.lname = lname;
        this.occupation = occupation;
        this.age = age;
        this.expiration = expiration;
        this.dialog = dialog;
    }

}





