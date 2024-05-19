package game;
// IMPORTED DEP-MODULES
import java.util.Random;
import java.time.LocalDate;
import java.util.Scanner;
// IMPORTED MODULES 
import game.util.DisplayUtils;
import game.util.RandomUtils;
import game.util.DateUtils;
import game.util.Validate;
import game.model.Civilian;
import game.model.Constants;
import game.model.States;

public class Game {
    private static Scanner scan = new Scanner(System.in);
    private static Random rand = new Random();

    private static DisplayUtils display = new DisplayUtils();
    private static RandomUtils random = new RandomUtils();
    private static DateUtils date = new DateUtils();
    private static Constants constant = new Constants();
    private static States state = new States();
    private static Validate validate = new Validate();

    public static void main(String[] args) {
        
        display.clrscr();

        display.displayInstructions();

        display.pressAnyKeyToContinue();

        display.clrscr();

        display.criminalNewspaper(constant.criminal_fname, constant.criminal_lname, constant.formatCurrentDate);

        display.pressAnyKeyToContinue();

        // -- MAIN LOOP -- // 
        while (true){
            display.clrscr();

            // KEEP CREATING INSTANCES
            LocalDate randomDate = random.generateRandomDate(constant.startDate, constant.endDate);
            Civilian civilian = new Civilian(constant.firstNames[rand.nextInt(constant.firstNames.length)], constant.lastNames[rand.nextInt(constant.lastNames.length)], 
            constant.occupations[rand.nextInt(constant.occupations.length)], random.generateRandomAge(15, 150), date.formatDate(randomDate), constant.greetingDialogs[rand.nextInt(constant.greetingDialogs.length)]);
            constant.queue.offer(civilian);

            // TODO: CREATE AN ODDS ALGORITHM FOR HAVING BAD OR GOOD PASSPORT

            // REMOVING & RETRIEVING INSTANCES IN QUEUE
            Civilian retrievedCivilan = constant.queue.poll();
            display.addDialogEffect("Civilian " + state.civilianNumber + ":");
            display.addDialogEffect(retrievedCivilan.dialog);
            display.addDialogEffect("Here's my passport: ");
            display.clrscr();

            display.addAnimationEffect("╔══════════ PASSPORT DETAILS ═══════════╗", 5);
            display.addAnimationEffect("    First Name: " + retrievedCivilan.fname, 5);
            display.addAnimationEffect("    Last Name: " + retrievedCivilan.lname, 5);
            display.addAnimationEffect("    Age: " + retrievedCivilan.age, 5);
            display.addAnimationEffect("    Occupation: " + retrievedCivilan.occupation, 5);
            display.addAnimationEffect("    Expiration Date: " + retrievedCivilan.expiration, 5);
            display.addAnimationEffect("╚═══════════════════════════════════════╝ ", 5);

            // COMPARE IT WITH DECISION INPUT
            boolean isSuspiciousPassport = validate.checkPassport(constant.criminal_fname, constant.criminal_lname, retrievedCivilan.fname, retrievedCivilan.lname, retrievedCivilan.age, retrievedCivilan.occupation, constant.badOccupations, retrievedCivilan.expiration, constant.formatCurrentDate);
            display.addAnimationEffect("PROCEED OR REJECT", 5);
            
            String userChoice = scan.nextLine();

            while (!userChoice.equalsIgnoreCase("PROCEED") && !userChoice.equalsIgnoreCase("REJECT")) {
                display.addAnimationEffect("Invalid input. Please enter either 'PROCEED' or 'REJECT': ", 5);
                userChoice = scan.nextLine();
            }

            if (isSuspiciousPassport && userChoice.equalsIgnoreCase("PROCEED")){
                display.addDialogEffect("Warning! You allowed " + validate.returnSuspicousDetail(constant.criminal_fname, constant.criminal_lname, retrievedCivilan.fname, retrievedCivilan.lname, retrievedCivilan.age, retrievedCivilan.occupation, constant.badOccupations, retrievedCivilan.expiration, constant.formatCurrentDate));
                state.numberOfApprovedBadCivilians++;
                display.addDialogEffect("Approved Civlians that has bad passport:" + state.numberOfApprovedBadCivilians);
             }else if (!isSuspiciousPassport && userChoice.equalsIgnoreCase("PROCEED")){
                state.numberOfApprovedCivilians++;
                display.addDialogEffect("Approved Civilians: " + state.numberOfApprovedCivilians);
            }else if (isSuspiciousPassport && userChoice.equalsIgnoreCase("REJECT")){ 
                display.addDialogEffect("You rejected " + validate.returnSuspicousDetail(constant.criminal_fname, constant.criminal_lname, retrievedCivilan.fname, retrievedCivilan.lname, retrievedCivilan.age, retrievedCivilan.occupation, constant.badOccupations, retrievedCivilan.expiration, constant.formatCurrentDate));
            }else if (!isSuspiciousPassport && userChoice.equalsIgnoreCase("REJECT")){
                display.addDialogEffect("Warning!, You Rejected a civilian that has valid passport");
                state.numberOfRejectedGoodCivilians++;
                display.addDialogEffect("Rejected Civilians that has good passport: " + state.numberOfRejectedGoodCivilians);
            }

            // player lost
            if (state.numberOfRejectedGoodCivilians == 3){
                display.clrscr();
                display.addDialogEffect("You rejected 3 civilians that has good passport. The president is angered with your actions. You are now fired!");
                break;
            }

            if (state.numberOfApprovedBadCivilians == 3){
                display.clrscr();
                display.addDialogEffectNoContinue("You allowed 3 civilians that has bad passport. The country is now in danger!");
                break;
            }

            // win 
            if (state.numberOfApprovedCivilians == 10) {
                display.clrscr();
                display.addDialogEffectNoContinue("You won! You Successfully allowed at least 10 civilians to the border");
                break;  
            }            

            state.civilianNumber++;
        }
    }
}