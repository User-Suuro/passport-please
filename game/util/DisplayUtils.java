package game.util;
import java.util.Scanner;

public class DisplayUtils {
    public static Scanner scan = new Scanner(System.in);
    // Clear Screen method
    public void clrscr() {
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
    public void sleep(long ms){
        try{
            Thread.sleep(ms);
        } catch (InterruptedException e){};
    }

    // dialog effect 
    public void addDialogEffect(String line){
         // Convert the String to a char array
         char[] charArray = line.toCharArray();
        
         // Print each character in the char array
         for (char c : charArray) {
             System.out.print(c);
             sleep(50);
         }

        System.out.println();
        pressAnyKeyToContinue();
    }

    public void addDialogEffectNoContinue(String line){
        // Convert the String to a char array
        char[] charArray = line.toCharArray();
       
        // Print each character in the char array
        for (char c : charArray) {
            System.out.print(c);
            sleep(50);
        }
   }

   public void addAnimationEffect(String line, long ms){
        char[] charArray = line.toCharArray();
        
        // Print each character in the char array
        for (char c : charArray) {
            System.out.print(c);
            sleep(ms);
        }

        System.out.println();
   }

 public void criminalNewspaper(String criminal_fname, String criminal_lname,String date) {
        String redColor = "\u001B[31m"; 
        String midGreyColor = "\u001B[38;5;240m"; 
        String resetColor = "\u001B[0m";
        int speed = 5;

        addAnimationEffect("╔═══════════════════════════════════════╗",speed);
        addAnimationEffect("║            "+ midGreyColor +"BREAKING NEWS!!!!!"+ resetColor +"         ╠╗",speed);
        addAnimationEffect("╠═══════════════════════════════════════╣║", speed);
        addAnimationEffect("║                           "+ midGreyColor + date + resetColor +"  ║║", speed);
        addAnimationEffect("║                                       ║║", speed);
        addAnimationEffect("║         "+ redColor +"WANTED CRIMINAL MISSING"+ resetColor +"       ║║", speed);
        addAnimationEffect("║                                       ║║", speed);
        addAnimationEffect("║ "+ midGreyColor +"Criminal Identification:"+ resetColor +"              ║║", speed);
        addAnimationEffect("║                                       ╚╩═╗", speed);
        addAnimationEffect("║ "+ midGreyColor +"First Name: " + criminal_fname + resetColor +"           ", speed);
        addAnimationEffect("║ "+ midGreyColor +"Last Name: " + criminal_lname + resetColor + "           ", speed);
        addAnimationEffect("║                                       ╔╦═╝", speed);
        addAnimationEffect("║    "+ midGreyColor +"The criminal was last seen in a"+ resetColor +"    ║║", speed);
        addAnimationEffect("║    "+ midGreyColor +"prison nearby the border."+resetColor+"          ║║", speed);
        addAnimationEffect("║                                       ║║", speed);
        addAnimationEffect("╚═╦═════════════════════════════════════╝║", speed);
        addAnimationEffect("  ╚══════════════════════════════════════╝\n", speed);
}

    public void displayInstructions() {
        int speed = 5;

        addAnimationEffect("╔═══════════════════════════════════════╗", speed);
        addAnimationEffect("║              Instructions             ║", speed);
        addAnimationEffect("╠═══════════════════════════════════════╣", speed);
        addAnimationEffect("║ 1. Review each civilian's passport    ║", speed);
        addAnimationEffect("║    details carefully.                 ║", speed);
        addAnimationEffect("║                                       ║", speed);
        addAnimationEffect("║ 2. Type 'PROCEED' to allow a civilian ║", speed);
        addAnimationEffect("║    to pass if their passport seems    ║", speed);
        addAnimationEffect("║    legitimate.                        ║", speed);
        addAnimationEffect("║                                       ║", speed);
        addAnimationEffect("║ 3. Type 'REJECT' to deny entry to a   ║", speed);
        addAnimationEffect("║    civilian if you suspect they may   ║", speed);
        addAnimationEffect("║    pose a danger.                     ║", speed);
        addAnimationEffect("║                                       ║", speed);
        addAnimationEffect("║ 4. Your goal is to allow at least 10  ║", speed);
        addAnimationEffect("║    genuine civilians to pass without  ║", speed);
        addAnimationEffect("║    any security breaches.             ║", speed);
        addAnimationEffect("║                                       ║", speed);
        addAnimationEffect("║ 5. Be cautious - allowing a           ║", speed);
        addAnimationEffect("║    3 suspicious individual to proceed ║", speed);
        addAnimationEffect("║    will be game over.                 ║", speed);
        addAnimationEffect("╚═══════════════════════════════════════╝", speed);
    }

    public void pressAnyKeyToContinue(){
        System.out.println("Press any key to continue");
        scan.nextLine();
    }
}
