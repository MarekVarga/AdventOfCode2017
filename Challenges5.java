import java.lang.reflect.Array;
import java.util.*;

public class Challenges5 {

    private static Scanner scanner = new Scanner(System.in);

    // day 16
    //******************************************************************************************************************
    public static String[] day16(){
        String[] order = new String[]{"",""};
        ArrayList<String> input = scanInput16();
        String programs = fillPrograms();

        for(long i = 0; i < (1000000000%60); i++) {
            for (String string :input) {
                switch (string.charAt(0)) {
                    case 's':
                        programs = spin(programs, string);
                        break;
                    case 'x':
                        programs = exchange(programs, string);
                        break;
                    default:
                        programs = partner(programs, string);
                        break;
                }
            }
            // part 1
            if( i == 0 )
                order[0] =programs;
        }

        // part 2
        order[1] = programs;

        return order;
    }

    // method for placing certain programs in front of the other programs
    private static String spin (String programs, String instruction){
        int spin = Challenges4.stringToInt(instruction.substring(1, instruction.length()) );
        StringBuilder tmp = new StringBuilder();
        tmp.append(programs.substring(programs.length()-spin,programs.length()));
        tmp.append(programs.substring(0, programs.length()-spin));
        return tmp.toString();
    }

    // method for swapping programs according to their index
    private static String exchange (String programs, String instruction){
        int globI = 1;
        StringBuilder tmp1 = new StringBuilder();
        StringBuilder tmp2 = new StringBuilder();

        for ( int i = 1; instruction.charAt(i) != '/' ; i++){
            tmp1.append(instruction.charAt(i));
            globI = i;
        }
        int val1 = Challenges4.stringToInt(tmp1.toString());
        for ( int i = globI+2; i < instruction.length(); i++){
            tmp2.append(instruction.charAt(i));
        }
        int val2 = Challenges4.stringToInt(tmp2.toString());

        char tmp = programs.charAt(val1);
        char tmp3 = programs.charAt(val2);
        programs = programs.replace(tmp3,'z');
        programs = programs.replace(tmp,tmp3);
        programs = programs.replace('z',tmp);

        return programs;
    }

    // method for swapping programs according to their name
    private static String partner(String programs, String instruction){
        char tmp = instruction.charAt(1);
        char tmp1 = instruction.charAt(3);
        programs = programs.replace(tmp1,'z');
        programs = programs.replace(tmp,tmp1);
        programs = programs.replace('z',tmp);

        return programs;
    }

    // method for filling program names
    private static String fillPrograms(){
        StringBuilder tmp = new StringBuilder();

        for(char character = 'a'; character <= 'p'; character++){
            tmp.append(character);
        }

        return tmp.toString();
    }

    private static ArrayList<String> scanInput16(){
        ArrayList<String> input = new ArrayList<>();
        int i = 0;
        String tmpStr = new String();
        StringBuilder tmp = new StringBuilder();

        System.out.println("Enter your input: ");
        while( scanner.hasNext() ){
            /*while( scanner.next().char )*/
            tmpStr = scanner.next();
        }
        for( char character: tmpStr.toCharArray()) {
            if ( character != ','){
                tmp.append(character);
            }
            else {
                input.add(i,tmp.toString());
                i++;
                tmp = new StringBuilder();
            }
        }
        input.add(i,tmp.toString());
        return input;
    }

    // day 17
    //******************************************************************************************************************
    public static int[] day17(){
        int[] value = new int[]{0,0};
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(0);
        int index = 1;
        int steps = 348;

        // part 1
        for(int i = 0; i <= 2017; i++){
            numbers.add(index,i+1);
            index = (index + steps) % numbers.size() + 1;
        }

        value[0] = numbers.get(numbers.indexOf(2017)+1);

        // part 2
       int numbersCount = 1;
       index = 0;
       for (int i = 1; i < 50000000; i++){
           index = (index + steps) % numbersCount + 1;
           numbersCount++;
           if( index == 1)
               value[1] = i;
       }

        return value;
    }
}
