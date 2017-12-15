import java.util.ArrayList;
import java.util.Scanner;

public class Challenges4 {

    private static Scanner scanner = new Scanner(System.in);

    // day 12
    //******************************************************************************************************************
    public static int[] day12(){
        int[] programs = new int[] {0,0};
        ArrayList<String> inputPipes = scanInput();
        ArrayList<String> directPipes = new ArrayList<>();
        directPipes.add("0");

        for ( String pipeNumber : inputPipes){
            String[] pipes = pipeNumber.split("\\s");
            if ( !directPipes.contains(pipes[0]) || pipes[0].equals("0")) {
                directPipes = findPipes(inputPipes, directPipes, stringToInt(pipes[0]));
                // part 2
                programs[1]++;
            }
            // part 1
            if( pipes[0].equals("0"))
                programs[0] = directPipes.size();
        }

        return programs;
    }

    // method for finding all pipes that lead to pipe 0
    private static ArrayList<String> findPipes(ArrayList<String> inputPipes, ArrayList<String> directPipes, int pipeNum){
        String[] inputPipeLine = inputPipes.get(pipeNum).split("\\s");

        for (int i = 2; i < inputPipeLine.length; i++) {
            if( inputPipeLine[i].contains(",") )
                inputPipeLine[i] = inputPipeLine[i].substring(0,inputPipeLine[i].length()-1);
            if( !directPipes.contains(inputPipeLine[i]) ){
                directPipes.add(inputPipeLine[i]);
                findPipes(inputPipes,directPipes,stringToInt(inputPipeLine[i]));
            }
        }

        return directPipes;
    }

    // method for converting string to int value
    private static int stringToInt(String pipeName){
        int pipeNum = 0;

        for (char number : pipeName.toCharArray()){
            pipeNum *= 10;
            pipeNum += (number-48);
        }

        return pipeNum;
    }

    // method for scanning input
    private static ArrayList<String> scanInput(){
        ArrayList<String> input = new ArrayList<>();

        System.out.println("Enter you input: ");
        while (scanner.hasNextLine())
            input.add(scanner.nextLine());

        return input;
    }

    // day 13
    //******************************************************************************************************************
    public static int[] day13(){
        int[] danger = new int[]{0,0};
        ArrayList<String> scannedInput = scanInput();
        scannedInput = removeColon(scannedInput);
        String[] finalPos = scannedInput.get(scannedInput.size()-1).split("\\s");
        int scannedInputIterator = 0;
        int rangeIteration = 0;
        boolean blockedPath = true;
        boolean skip = false;

        // part 1
        for( int i = 0; i <= stringToInt(finalPos[0]); i++ ){
            String[] inputLineSplit = scannedInput.get(scannedInputIterator).split("\\s");
            if( i == stringToInt(inputLineSplit[0]) ){
                rangeIteration = (i / ((stringToInt(inputLineSplit[1])*2)-1) );
                if( (i%((stringToInt(inputLineSplit[1])*2)-1)+rangeIteration == 0) || (i%((stringToInt(inputLineSplit[1])*2)-1)+rangeIteration == (stringToInt(inputLineSplit[1])*2)-2 ) ){
                    danger[0] += (stringToInt(inputLineSplit[0])*stringToInt(inputLineSplit[1]));
                }
                scannedInputIterator++;
            }
        }

        // part 2
        for( int j = 0; blockedPath; j++ ){
            skip = false;
            scannedInputIterator = 0;
            for( int i = 0; i <= stringToInt(finalPos[0]) && !skip; i++ ){
                String[] inputLineSplit = scannedInput.get(scannedInputIterator).split("\\s");
                if( i == stringToInt(inputLineSplit[0]) ){
                    rangeIteration = ((i+j) / ((stringToInt(inputLineSplit[1])*2)-1) );
                    if( ((i+j)%((stringToInt(inputLineSplit[1])*2)-1)+rangeIteration == 0) || ((((j+i)%((stringToInt(inputLineSplit[1])*2)-1))+rangeIteration) % ((stringToInt(inputLineSplit[1])*2)-2) == 0 ) ){
                        skip = true;
                        continue;
                    }
                    scannedInputIterator++;
                }
            }
            if ( !skip ) {
                blockedPath = false;
                danger[1] = j;
            }
        }

        return danger;
    }

    // method for removing colon from input
    private static ArrayList<String> removeColon(ArrayList<String> input){
        String tmp;

        for(String inputLine : input){
            if ( inputLine.contains(":") ){
                tmp = inputLine.replace(":", "");
                input.set(input.indexOf(inputLine),tmp);
            }
        }

        return input;
    }

    // day 14
    //******************************************************************************************************************
    // not enough time today


    // day 15
    //******************************************************************************************************************
    public static int[] day15(){
        int[] matches = new int[]{0,0};
        long genA = 883;
        final int genAFactor = 16807;
        long genB = 879;
        final int genBFactor = 48271;

        // part 1
        for( long i = 0; i < 40000000L; i++){
            genA = nextValue(genA,genAFactor);
            genB = nextValue(genB, genBFactor);
            if ( cmpBinGens(toBinary(genA), toBinary(genB)) )
                matches[0]++;
        }

        // part 2
        genA = 883;
        genB = 879;
        for(long i = 0; i < 5000000; i++){
            do {
                genA = nextValue(genA, genAFactor);
            } while( genA % 4 != 0);
            do {
                genB = nextValue(genB, genBFactor);
            } while ( genB % 8 != 0);
            if ( cmpBinGens(toBinary(genA),toBinary(genB)) )
                matches[1]++;
        }

        return matches;
    }

    // method for calculatin next value that will be generated
    private static long nextValue(long gen, int genFactor){
        final long modulo = 2147483647;
        gen = (gen*genFactor)%modulo;
        return gen;
    }

    // method for converting generated values to binary, binary will be stored as 0 and 1 in Integer ArrayList
    private static ArrayList<Integer> toBinary(long gen){
        ArrayList<Integer> genBinary = new ArrayList<>();
        int bin;
        while(gen > 0){
            bin = (int) gen%2;
            genBinary.add(0,bin);
            gen = gen / 2;
        }
        for ( int i = genBinary.size(); i < 33; i++){
            genBinary.add(0,0);
        }
        return genBinary;
    }

    // method for comparing two ArrayList of Integers containing binary values
    private static boolean cmpBinGens(ArrayList<Integer> genABin, ArrayList<Integer> genBBing){
        for (int i = genABin.size()-1; i >= genABin.size()-16; i--) {
            if ( genABin.get(i) != genBBing.get(i) )
                return false;
        }
        return true;
    }
}
