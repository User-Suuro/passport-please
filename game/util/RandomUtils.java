package game.util;
import java.time.LocalDate;
import java.util.Random;

public class RandomUtils {
    private static Random rand = new Random();

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
}
