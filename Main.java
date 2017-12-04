
public class Main {

    public static void main(String[] args){

        int[] day1 = Challenges.day1();
        System.out.println("The sum for the first part is: "+day1[0]+"\nThe sum for the second part is: "+day1[1]);

        int[] day2 = Challenges.day2();
        System.out.println("Checksum is: "+day2[0]+" and sum of evenly divisible numbers is: "+day2[1]);

        int[] day3 = Challenges.day3();
        System.out.println("Steps: "+day3[0]+" and the next bigger value is: "+day3[1]);
    }

}
