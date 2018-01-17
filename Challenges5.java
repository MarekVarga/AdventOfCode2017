import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.sql.Array;
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
        try{ scanner = new Scanner(new File("day16"));}
        catch (FileNotFoundException ex){System.out.println("Unable to find file");}

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

    // day 18
    //******************************************************************************************************************
    public static int[] day18(){
        int[] value = new int[]{0,0};
        ArrayList<String> input = scanInput18("day18");
        ArrayList<Register> registers = initRegisters(input);
        long freq = 0;
        long regChng = 0;
        Register register;

        // part 1
        for (int j = 0; j < input.size(); j++){
            String[] inputLineSplit = input.get(j).split("\\s");
            if ( inputLineSplit.length > 2 )
                regChng = checkValue(registers,inputLineSplit[2]);
                register = findRegister(registers,inputLineSplit[1]);
            switch (inputLineSplit[0]){
                case "set" :
                    register.value = regChng;
                    break;
                case "add" :
                    register.value += regChng;
                    break;
                case "mul" :
                    register.value *= regChng;
                    break;
                case "mod" :
                    register.value = register.value % regChng;
                    break;
                case "snd" :
                    freq = register.value;
                    break;
                case "rcv" :
                    if( register.value != 0 ) {
                        value[0] = (int) freq;
                        j = input.size();
                    }
                    else
                        break;
                case "jgz" :
                    if (register.value > 0 ){
                        j += regChng-1;
                    }
                    else
                        break;
            }
        }

        return value;
    }

    // method for checking value of register
    public static long checkValue(ArrayList<Register> registers, String registerValue){
        if ( registerValue.charAt(0) < 'a' )
            return Challenges4.stringToInt(registerValue);
        else
            return findRegister(registers,registerValue).value;
    }

    // method for initializing registers
    public static ArrayList<Register> initRegisters(ArrayList<String> input){
        ArrayList<Register> registers = new ArrayList<>();
        int i =0;

        for (String inputLine : input){
            String[] inputLineSplit = inputLine.split("\\s");
            if( !containsRegister(registers, inputLineSplit[1]) ){
                registers.add(new Register(inputLineSplit[1]));
            }
        }

        return registers;
    }

    // method for locating a register in ArrayList of Registers
    public static Register findRegister(ArrayList<Register> registers, String regName){
        for( Register register : registers)
            if ( register.name.equals(regName) )
                return register;

        return null;
    }

    // method for determining if register appears in ArrayList of Registers
    public static boolean containsRegister(ArrayList<Register> registers, String regName){
        for(Register register : registers){
            if ( register.name.equals(regName) )
                return true;
        }
        return false;
    }

    // method for scanning input for day 18 and day 23
    public static ArrayList<String> scanInput18(String fileName){
        ArrayList<String> input = new ArrayList<>();
        try{ scanner = new Scanner(new File(fileName));}
        catch (FileNotFoundException ex){System.out.println("Unable to find file");}

        while ( scanner.hasNextLine() ){
            input.add(scanner.nextLine());
        }
        return input;
    }

    // day 24
    //******************************************************************************************************************
    private static class Component{
        int a = 0;
        int b = 0;
        boolean used = false;
    }
    private static ArrayList<Component> components = scanInput24();
    public static int[] day24(){
        int[] strongestBridge = new int[]{0,0,0};

        return findStrongestBridge(0,0,0, strongestBridge);
    }

    // method for handling input
    private static ArrayList<Component> scanInput24(){
        String fileName = "day24";
        String line;
        ArrayList<Component> components = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ( (line = bufferedReader.readLine()) != null ){
                Component component = new Component();
                String[] tmp2 = line.split("/");
                component.a = Challenges4.stringToInt(tmp2[0]);
                component.b = Challenges4.stringToInt(tmp2[1]);
                components.add(components.size(),component);
            }

            bufferedReader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("unable to find "+fileName);
        }
        catch (IOException e){
            System.out.println("unable to read from "+fileName);
        }

        return components;
    }

    // method for finding strongest bridge
    private static int[] findStrongestBridge(int port, int length, int strength, int[] Bridge){
        Bridge[0] = Math.max(strength, Bridge[0]);
         Bridge[2] = Math.max(length, Bridge[2]);

        if (length == Bridge[2])
            Bridge[1] = Math.max(strength,Bridge[1]);

        for(Component component: components){
            if(!component.used && (component.a == port || component.b == port)){
                component.used = true;
                findStrongestBridge( (component.a == port) ? component.b : component.a, length+1, strength+component.a+component.b,Bridge);
                component.used = false;
            }
        }

        return Bridge;
    }
}
