import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

// DATE IMPORTS
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class main01 {
    public static Scanner scan = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        clrscr();

        // -- STATES -- //
        Queue<Civilian> queue = new LinkedList<>();
        int numberOfApprovedCivilians = 0;
        int civilianNumber = 1; 

        // -- CONSTANTS -- // 

        // NAME
        String[] firstNames = {"John", "Jane", "Alice", "Bob", "Michael", "Emily", "David", "Sarah"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson"};

        // CRIMINAL NAME
        String criminal_fname = firstNames[rand.nextInt(firstNames.length)];
        String criminal_lname = lastNames[rand.nextInt(lastNames.length)];
        
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
            "Burglar",
            "Assassin",
            "Drug Dealer",
            "Bank Robber",
            "Scammer",
            "Blackmailer",
            "Smuggler",
            "Hacker",
            "Arsonist",
            "Embezzler",         
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
            Civilian civilian = new Civilian(firstNames[rand.nextInt(firstNames.length)], lastNames[rand.nextInt(lastNames.length)], 
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

            // COMPARE IT WITH DECISION INPUT
            boolean isSuspiciousPassport = checkPassport(criminal_fname, criminal_lname, retrievedCivilan.fname, retrievedCivilan.lname, retrievedCivilan.age, retrievedCivilan.occupation, badOccupations, retrievedCivilan.expiration, formatCurrentDate);

            System.out.println("PROCEED OR REJECT");
            String userChoice = scan.nextLine();

            while (!userChoice.equalsIgnoreCase("PROCEED") && !userChoice.equalsIgnoreCase("REJECT")) {
                System.out.println("Invalid input. Please enter either 'PROCEED' or 'REJECT':");
                userChoice = scan.nextLine();
            }

            if (isSuspiciousPassport && userChoice.equalsIgnoreCase("PROCEED")){
                clrscr();
                addDialogEffect("You lost! You allowed a suspcious civilian to enter the border. The country is now in danger.");
                // player lost
                break;  
            }else if (!isSuspiciousPassport && userChoice.equalsIgnoreCase("PROCEED")){
                numberOfApprovedCivilians++;
                addDialogEffect("Approved Civilians: " + numberOfApprovedCivilians);
            }

            // win 
            if (numberOfApprovedCivilians >= 10) {
                addDialogEffect("You won! You Successfully allowed at least 10 civilians to the border without having a single suspcious civilian in");
            }            

            civilianNumber++;
        }

    }

    public static void displayIntro(String date, String criminal_fname, String criminal_lname){
        addDialogEffect("Today is " + date);
        addDialogEffect("NEWS: WANTED CRIMINAL: First Name: " + criminal_fname + " Last Name: " + criminal_lname);
        addDialogEffect("We decided to open the borders");
    }

    public static boolean checkPassport(String criminal_fname, String criminal_lname, String civilian_fname, String civilian_lname, 
                                        int civilian_age, String occupation, String[] badOccupation, String expdate, String formattedCurrentDate){
        
        // TO DO: (FORBIDDEN VALUES CHECKER) - RETURN TRUE IF SUS

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
        if (isDateLarger(formattedCurrentDate, expdate)){
            return true;
        }

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

    public static boolean isDateLarger(String dateString1, String dateString2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy"); // Adjust the format as needed
        LocalDate date1 = LocalDate.parse(dateString1, formatter);
        LocalDate date2 = LocalDate.parse(dateString2, formatter);
        return date1.isAfter(date2);
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
         scan.nextLine();
    }

    public static void displayInstructions() {
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║              Instructions             ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║ 1. Review each civilian's passport    ║");
        System.out.println("║    details carefully.                 ║");
        System.out.println("║                                       ║");
        System.out.println("║ 2. Type 'PROCEED' to allow a civilian ║");
        System.out.println("║    to pass if their passport seems    ║");
        System.out.println("║    legitimate.                        ║");
        System.out.println("║                                       ║");
        System.out.println("║ 3. Type 'REJECT' to deny entry to a   ║");
        System.out.println("║    civilian if you suspect they may   ║");
        System.out.println("║    pose a danger.                     ║");
        System.out.println("║                                       ║");
        System.out.println("║ 4. Your goal is to allow at least 10  ║");
        System.out.println("║    genuine civilians to pass without  ║");
        System.out.println("║    any security breaches.             ║");
        System.out.println("║                                       ║");
        System.out.println("║ 5. Be cautious - allowing a           ║");
        System.out.println("║    suspicious individual to proceed   ║");
        System.out.println("║    will be game over.                 ║");
        System.out.println("╚═══════════════════════════════════════╝");
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