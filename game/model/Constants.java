package game.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import game.util.RandomUtils;
import game.util.DateUtils;


public class Constants {

    // -- PRIVATE CONSTANTS -- //

    private static DateUtils date = new DateUtils();

    private static RandomUtils random = new RandomUtils();

    private static Random rand = new Random();

    private static LocalDate getDate = LocalDate.now();

    // -- PUBLIC CONSTANTS -- //'
    
    public static Queue<Civilian> queue = new LinkedList<>();

    public static final String[] firstNames = {"John", "Jane", "Alice", "Bob", "Michael", "Emily", "David", "Sarah"};

    public static final String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson"};

    public static final String criminal_fname = firstNames[rand.nextInt(firstNames.length)];

    public static final String criminal_lname = lastNames[rand.nextInt(lastNames.length)];

    public static final String[] greetingDialogs = {
        "Hello!",
        "Hi there!",
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

    public static final String[] goodOccupations = {
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

    public static final String[] badOccupations = {
        "Kidnapper",
        "Burglar",
        "Drug Dealer",
        "Bank Robber",
        "Scammer",
        "Smuggler",
        "Hacker",
        "Embezzler",         
    };

    public final static String[] occupations = combineOccupations();
    
    public static final String formatCurrentDate = date.formatDate(getDate);

    public static final LocalDate startDate = getDate.minusMonths(1); // Start date is one month behind the current date

    public static final LocalDate endDate = getDate.plusMonths(1);    // End date is one month ahead of the current date

    public static final String redColor = "\u001B[31m";

    public static final String resetColor = "\u001B[0m";

    private static String[] combineOccupations(){
        String[] temp = new String[goodOccupations.length + badOccupations.length];
        
        for (int i = 0; i < goodOccupations.length; i++) {
            temp[i] = goodOccupations[i];
        }
    
        for (int i = 0; i < badOccupations.length; i++) {
            temp[goodOccupations.length + i] = badOccupations[i];
        }

        return random.shuffle(temp);
    }
}
