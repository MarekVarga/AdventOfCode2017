import com.sun.deploy.util.ArrayUtil;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

public class Challenges3 {

    private static Scanner scanner = new Scanner(System.in);

    // day 9
    //******************************************************************************************************************
    public static int[] day9(){
        int[] score = new int[] {0,0};
        try {scanner = new Scanner(new File( "day9")); }
        catch (FileNotFoundException ex) {System.out.println("unable to find file"); }
        String input = scanner.nextLine();
        int currentOpen = 0;
        boolean garbage = false;
        boolean ignoreChar = false;

        for(char character:input.toCharArray()){
            if( ignoreChar ) {
                ignoreChar = false;
                continue;
            }
            if( character != '!' ){
                if( character == '<'){
                    garbage = true;
                }
                else if(character == '>' && garbage) {
                    garbage = false;
                    score[1]--;
                }
                else if ( character == '{' && !garbage ){
                    currentOpen++;
                }
                else if( character == '}' && currentOpen != 0 && !garbage){
                    score[0] += currentOpen;
                    currentOpen--;
                }
                if( garbage ){
                    score[1]++;
                }
            }
            else {
                ignoreChar = true;
            }
        }

        return score;
    }

    // day 10
    //******************************************************************************************************************
    private static int stepSize = 0;
    private static int startPosition = 0;
    public static int[] day10(){
        int[] multiple = new int[] {0, 0};
        int[] intList = IntStream.range(0,256).toArray();

        // part 1
        intList = part1(scanInput10(),intList);
        multiple[0] = intList[0] * intList[1];

        // part 2
        System.out.println("Hash for part 2 is :"+part2(scanInput10b()));

        return multiple;
    }

    // method for hashing integers in int[]
    private static int[] part1(int[] inputLengths, int[] intList){
        for (int length : inputLengths) {
            int[] temp = new int[length];
            int cut;
            if (length <= intList.length - startPosition) {
                cut = length;
            } else {
                cut = (intList.length - startPosition);
            }
            int leftover = length - cut;
            System.arraycopy(intList, startPosition, temp, 0, cut);
            System.arraycopy(intList, 0, temp, cut, leftover);

            for(int i = 0; i < temp.length / 2; i++)
            {
                int tmp = temp[i];
                temp[i] = temp[temp.length - i - 1];
                temp[temp.length - i - 1] = tmp;
            }

            System.arraycopy(temp, 0, intList, startPosition, cut);
            System.arraycopy(temp, length - leftover, intList, 0, leftover);

            startPosition += length + stepSize;
            startPosition %= intList.length;
            stepSize++;
        }
        return intList;
    }

    // method for finding knot hash
    private static String part2(int[] inputLengths){
        final int[] suffixes = {17, 31, 73, 47, 23};
        stepSize = 0;
        startPosition = 0;
        int[] intList = IntStream.range(0,256).toArray();
        inputLengths = Arrays.copyOf(inputLengths, inputLengths.length + suffixes.length);
        System.arraycopy(suffixes, 0, inputLengths, inputLengths.length - suffixes.length, suffixes.length);
        for(int i = 0; i < 64; i++){
            intList = part1(inputLengths,intList);
        }
        int[] denseHash = new int[16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                denseHash[i] ^= intList[(i * 16) + j];
            }
        }
        StringBuilder knotHash = new StringBuilder();
        for (int element: denseHash) {
            knotHash.append(String.format("%2s",Integer.toHexString(element)).replace(" ","0"));
        }

        return knotHash.toString();
    }

    // method for scanning input for day 10 for part 1
    private static int[] scanInput10(){
        String input = "";
        try {scanner = new Scanner(new File( "day10")); }
        catch (FileNotFoundException ex) {System.out.println("unable to find file"); }
        while(scanner.hasNextLine()){
            input = scanner.nextLine();
        }
        String[] inputs = input.split(",");
        int[] lengths = new int[inputs.length];
        for(int i = 0; i < lengths.length; i++){
            lengths[i] = Challenges4.stringToInt(inputs[i]);
        }

        return lengths;
    }

    // method for scanning input for day 10 for part 2
    private static int[] scanInput10b(){
        String input = "";
        try {scanner = new Scanner(new File( "day10")); }
        catch (FileNotFoundException ex) {System.out.println("unable to find file"); }
        while(scanner.hasNextLine()){
            input = scanner.nextLine();
        }

        return input.chars().toArray();
    }

    // day 11
    //******************************************************************************************************************
    // missing so far, not enough time today
}
