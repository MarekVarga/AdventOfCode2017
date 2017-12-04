import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.*;

public class Challenges {

    private static Scanner scan = new Scanner(System.in);

    // day 1 challenge
    public static int[] day1(){

        // part 1
        String input = scanInput1();
        char currentChar;
        int[] sum = {0, 0};
        int halfway = input.length() / 2;
        int matchingDigitIndex = 0;

        for (int i = 0; i < input.length(); i++){
            currentChar = input.charAt(i);
            if ( (i+1) == input.length() ){
                matchingDigitIndex = 0;
            }
            else{
                matchingDigitIndex = i+1;
            }
            if( currentChar == input.charAt(matchingDigitIndex) ){
                sum[0] += (currentChar - 48);
            }
        }

        // part 2
        for (int i = 0; i < input.length(); i++){
            currentChar = input.charAt(i);
            if( i < halfway ){
                matchingDigitIndex = i + halfway;
            }
            else {
                matchingDigitIndex = i - halfway;
            }
            if ( currentChar == input.charAt(matchingDigitIndex) ) {
                sum[1] += (currentChar - 48);
            }
        }

        return sum;
    }

    // input for day1 challenge
    private static String scanInput1(){
        System.out.println("Enter you input: ");
        return scan.next();
    }

    // day 2 challenge
    //*****************************************************************************************************************
    public static int[] day2(){

        // part 1
        int[][] input = {
        {       1236,	741,	557,	1029,	144,	101,	1968,	2159,	1399,	80,	    1139,	1167,	1695,	82,	    90,	    2236},
        {       2134,	106,	107,	1025,	584,	619,	191,	496,	80, 	352,	351,	2267,	1983,	1973,	97,	    1244},
        {       3227,	179,	691,	3177,	172,	1636,	3781,	2020,	3339,	2337,	189,	3516,	1500,	176,	159,	3279},
        {       201,	688,	364,	180,	586,	659,	623,	577,	188,	265,	403,	670,	195,	720,	115,	37},
        {       1892,	1664,	2737,	2676,	849,	2514,	923,	171,	311,	218,    255,	2787,	1271,	188,	1278,	2834},
        {       150,	3276,	204,	603,	3130,	587,    3363,	3306,	2890,	127,	176,	174,	383,	3309,	213,	1620},
        {       5903,	3686,	200,	230,	6040,	4675,	6266,	179,	5375,	1069,	283,	82, 	6210,	6626,	6398,	1954},
        {       942,	2324,	1901,	213,	125,	2518,	655,	189,	2499,	160,	2841,	2646,	198,	173,	1841,	200},
        {       232,	45, 	272,	280,	44,	    248,	50, 	266,	296,	297,	236,	254,	58, 	212,	276,	48},
        {       563,	768,	124,	267,	153,	622,	199,	591,	204,	125,	93, 	656,	198,	164,	797,    506},
        {       243,	4746,	1785,	204,	568,	4228,	2701,	4303,	188,	4148,	4831,	1557,	4692,	166,	4210,	3656},
        {       72,	    514,	1572,	172,	1197,	750,	1392,	1647,	1587,	183,    1484,	213,	1614,	718,	177,	622},
        {       1117,	97, 	2758,	2484,	941,	1854,	1074,	264,	2494,	83, 	1434,	96, 	2067,	2825,	2160,	92},
        {       2610,	1290,	204,	2265,	1374,	2581,	185,	852,	207,	175,	3308,	1500,	2898,	1120,	1892,	3074},
        {       2322,	1434,	301,	2156,	98, 	2194,	587,	1416,	1521,	94, 	1985,	424,	91, 	119,	1869,	1073},
        {       66,	    87, 	176,	107,	2791,	109,	21, 	92, 	3016,	2239,	1708,	3175,	3210,	2842,	446,    484}};
        int[] checkSum = {0,0};
        int rowMax = 0, rowMin = 0, rowDiv = 0;
        final int rowNum = 16, colNum = 16;

        for(int i = 0; i < rowNum; i++){
            rowMax = input[i][0];
            rowMin = input[i][0];
            for (int j = 0; j < colNum; j++){
                if( input[i][j] > rowMax)
                    rowMax = input[i][j];
                if( input[i][j] < rowMin)
                    rowMin = input[i][j];
            }
            checkSum[0] += (rowMax - rowMin);
        }

        // part 2
        for(int i = 0; i < rowNum; i++){
            rowDiv = 0;
            for(int j = 0; j < (colNum-1); j++){
                for(int k = (j+1); k < colNum; k++){
                    if( ((Math.max(input[i][j],input[i][k])) % (Math.min(input[i][k],input[i][j]))) == 0){
                        rowDiv = ((Math.max(input[i][j],input[i][k])) / (Math.min(input[i][k],input[i][j])));
                        j = colNum;
                        break;
                    }
                }
            }
            checkSum[1] += rowDiv;
        }

        return checkSum;
    }

    /**
     *
     * @// TODO: allow input value for day2 challenge to be read
     */
    /*public static ArrayList<int[]> scanInput2(){
        ArrayList<int[]> spreadsheet = new ArrayList<>();
        int i = 0, j = 0;

        System.out.println("Enter you input spreadsheet");

        while(scan.hasNextLine()){
            while(scan.hasNextInt()){
                spreadsheet.add(scan.nextInt());
                j++;
            }
            i++;
        }

        return spreadsheet;
        *//*List<String[]> lines = new ArrayList<>();
        //sc.nextLine(); //skip line 1
        while (sc.hasNextLine())
            lines.add(sc.nextLine().split(" "));

        lines.stream().map(Arrays::toString).forEach(System.out::println);*//*
        *//*String input = "";
        return Stream.of(input.split("\n")).map(line -> line.split(" ")).toArray(String[][]::new);*//*
    }*/

    // day 3
    //******************************************************************************************************************
    public static int[] day3(){
        // part 1
        int side =1;
        int value = scanInput3();
        int[] steps = new int[]{ 0, 0};

        // finding length of a side of square in which input number(value) is located
        while((side*side) <= value){
            side += 2;
        }
        int areaOfLesserSquare = (( side - 2 ) * (side - 2));
        int halfSide = ((side-1)/2);
        // checking if value belongs to the left part of a square
        if( (value > areaOfLesserSquare) && (value < ( areaOfLesserSquare + (side - 1)) )){
            // calculating offset of x-axis from the middle of the square, then adding distance from edge of square to the middle
            steps[0] += ( Math.abs(value-(areaOfLesserSquare + halfSide)) + halfSide);
        }
        //checking if value belongs to the top part of a square
        if( (value < ( areaOfLesserSquare + (2*(side - 1))) ) && (value >= ( areaOfLesserSquare + (side - 1)) )){
            steps[0] += ( Math.abs(value-(areaOfLesserSquare + halfSide + (side-1))) + halfSide);
        }
        //checking if value belongs to the right part of a square
        if( (value >= ( areaOfLesserSquare + (2*(side - 1))) ) && (value < ( areaOfLesserSquare + (3*(side - 1))) )){
            steps[0] += ( Math.abs(value-(areaOfLesserSquare + halfSide + (2*(side-1)))) + halfSide);
        }
        //checking if value belongs to the bottom part of a square
        if( (value >= ( areaOfLesserSquare + (3*(side - 1))) ) && (value <= ( areaOfLesserSquare + (4*(side - 1))) )){
            steps[0] += ( Math.abs(value-(areaOfLesserSquare + (3*(side - 1)) + halfSide)) + halfSide);
        }

        // part 2
        int[][] arr = new int[][]{ {1} };
        while (steps[1] < value) {
            // resizing array
            int[][] arr2 = new int[arr.length + 2][arr.length + 2];
            for (int l = 1; l <= arr.length; l++) {
                for (int k = 1; k <= arr.length; k++) {
                    arr2[l][k] = arr[l - 1][k - 1];
                }
            }
            // going up
            goingUp(value, arr2, steps);
            if(steps[1] > value)
                break;
            // top right value
            arr2[0][arr2.length-1] = arr2[1][arr2.length-1] + arr2[1][arr2.length-2];
            if(arr2[0][arr2.length-1] > value){
                steps[1] = arr2[0][arr2.length-1];
                break;
            }
            // going left
            goingLeft(value, arr2, steps);
            if(steps[1] > value)
                break;
            // top left value
            arr2[0][0] = arr2[0][1] + arr2[1][1];
            if(arr2[0][0] > value){
                steps[1] = arr2[0][0];
                break;
            }
            // going down
            goingDown(value, arr2, steps);
            if(steps[1] > value)
                break;
            // botton left value
            arr2[arr2.length-1][0] = arr2[arr2.length-2][0] + arr2[arr2.length-2][1];
            if(arr2[arr2.length-1][0] > value){
                steps[1] = arr2[arr2.length-1][0];
                break;
            }
            // going right
            goingRight(value,arr2,steps);
            if(steps[1] > value)
                break;
            // bottom right value
            arr2[arr2.length-1][arr2.length-1] = arr2[arr2.length-1][arr2.length-2] + arr2[arr2.length-2][arr2.length-2] + arr2[arr2.length-2][arr2.length-1];
            if(arr2[arr2.length-1][arr2.length-1] > value){
                steps[1] = arr2[arr2.length-1][arr2.length-1];
                break;
            }
            // copying array
            arr = new int[arr2.length][arr2.length];
            for (int l = 0; l < arr2.length; l++) {
                for (int k = 0; k < arr2.length; k++) {
                    arr[l][k] = arr2[l][k];
                }
            }
        }

        return steps;
    }

    // input for day3 challenge
    private static int scanInput3(){
        System.out.println("Enter you input: ");
        return scan.nextInt();
    }

    // method for resing an array
    private static int[][] resizeArray(){


    }

    // method for going right in array
    private static int[] goingRight(int value, int[][] arr2, int[] nextBigger){

        for(int j = 1; j < arr2.length-1; j++) {
            arr2[arr2.length-1][j] = arr2[arr2.length-1][j - 1] + arr2[arr2.length-2][j - 1] + arr2[arr2.length-2][j] + arr2[arr2.length-2][j + 1];
            if (arr2[arr2.length-1][j] > value) {
                nextBigger[1] = arr2[arr2.length-1][j];
                break;
            }
        }

        return nextBigger;
    }

    // method for going down in array
    private static int[] goingDown(int value, int[][] arr2, int[] nextBigger){

        for(int i = 1; i < arr2.length-1; i++) {
            arr2[i][0] = arr2[i-1][0] + arr2[i-1][1] + arr2[i][1] + arr2[i+1][1];
            if (arr2[i][0] > value) {
                nextBigger[1] = arr2[i][0];
                break;
            }
        }

        return nextBigger;
    }

    // method for going left in array
    private static int[] goingLeft(int value, int[][] arr2, int[] nextBigger){

        for(int j = (arr2.length-2); j > 0; j--){
            arr2[0][j] = arr2[0][j+1] + arr2[1][j+1] + arr2[1][j] + arr2[1][j-1];
            if (arr2[0][j] > value){
                nextBigger[1] = arr2[0][j];
                break;
            }
        }

        return nextBigger;
    }

    // method for going up in array
    private static int[] goingUp(int value, int[][] arr2, int[] nextBigger){

        for(int i = (arr2.length-2); i > 0; i--){
            arr2[i][arr2.length-1] = arr2[i+1][arr2.length-1] + arr2[i+1][arr2.length-2] + arr2[i][arr2.length-2] + arr2[i-1][arr2.length-2];
            if (arr2[i][arr2.length-1] > value){
                nextBigger[1] = arr2[i][arr2.length-1];
                break;
            }
        }

        return nextBigger;
    }
}
