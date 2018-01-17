import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Challenges6 {

    enum states {A, B, C, D, E, F};
    enum facingStates {up, left, down, right};

    public static int[] day25(){
        int[] occurance = new int[]{0,0};
        ArrayList<Integer> tape = new ArrayList<>();
        states tapeStates = states.A;
        int index = 0;

        for (int i = 1; i < 12481998L; i++) {
            switch (tapeStates)  {
                case A:
                    if( tape.size() <= index) {
                        tape.add(index,0);
                    }
                    if ( tape.get(index) == 0){
                        tape.set(index,1);
                        tapeStates = states.B;
                        index++;
                    }
                    else {
                        tape.set(index,0);
                        index--;
                        tapeStates = states.C;
                    }
                    if( index == 0) {
                        tape = shiftTape(tape);
                        index = 1;
                    }
                    break;
                case B:
                    if( tape.size() <= index) {
                        tape.add(index,0);
                    }
                    if ( tape.get(index) == 0){
                    tape.set(index,1);
                    tapeStates = states.A;
                    index--;
                    }
                    else {
                        tapeStates = states.D;
                        index++;
                      }
                    if( index == 0) {
                        tape = shiftTape(tape);
                        index = 1;
                    }
                    break;
                case C:
                    if( tape.size() <= index) {
                        tape.add(index,0);
                    }
                    if( tape.get(index) == 0){
                        index--;
                        tapeStates = states.B;
                    }
                    else {
                        tape.set(index,0);
                        index--;
                        tapeStates = states.E;
                    }
                    if( index == 0) {
                        tape = shiftTape(tape);
                        index = 1;
                    }
                    break;
                case D:
                    if( tape.size() <= index) {
                        tape.add(index,0);
                    }
                    if( tape.get(index) == 0){
                        tape.set(index,1);
                        index++;
                        tapeStates = states.A;
                    }
                    else {
                        tape.set(index,0);
                        index++;
                        tapeStates = states.B;
                    }
                    if( index == 0) {
                        tape = shiftTape(tape);
                        index = 1;
                    }
                    break;
                case E:
                    if( tape.size() <= index) {
                        tape.add(index,0);
                    }
                    if( tape.get(index) == 0){
                        tape.set(index,1);
                        index--;
                        tapeStates = states.F;
                    }
                    else {
                        index--;
                        tapeStates = states.C;
                    }
                    if( index == 0) {
                        tape = shiftTape(tape);
                        index = 1;
                    }
                    break;
                case F:
                    if( tape.size() <= index) {
                        tape.add(index,0);
                    }
                    if( tape.get(index) == 0){
                        tape.set(index,1);
                        index++;
                        tapeStates = states.D;
                    }
                    else {
                        index++;
                        tapeStates = states.A;
                    }
                    if( index == 0) {
                        tape = shiftTape(tape);
                        index = 1;
                    }
                    break;
            }
        }
        for(int number: tape){
            if(number == 1)
                occurance[0]++;
        }

        return occurance;
    }

    // method for shifting elements in ArrayList to right
    private static ArrayList<Integer> shiftTape(ArrayList<Integer> tape){
        tape.add(tape.size(),tape.get(tape.size()-1));
        for (int i = tape.size()-1; i > 0; i--) {
            tape.set(i,tape.get(i-1));
        }
        tape.set(0,0);

        return tape;
    }

    // day 23
    //******************************************************************************************************************
    public static int[] day23(){
        int[] value = new int[]{0,0};
        ArrayList<String> input = Challenges5.scanInput18("day23");
        ArrayList<Register> registers = Challenges5.initRegisters(input);
        long regChng = 0;
        Register register;
        Challenges5.findRegister(registers,"1").value = 1;

        // part 1
        for (int j = 0; j < input.size(); j++){
            String[] inputLineSplit = input.get(j).split("\\s");
            if ( inputLineSplit.length > 2 )
                regChng = Challenges5.checkValue(registers,inputLineSplit[2]);
            register = Challenges5.findRegister(registers,inputLineSplit[1]);
            switch (inputLineSplit[0]){
                case "set" :
                    register.value = regChng;
                    break;
                case "mul" :
                    register.value *= regChng;
                    value[0]++;
                    break;
                case "sub" :
                    register.value -= regChng;
                    break;
                case "jnz" :
                    if ( register.value != 0 ){
                        j += regChng-1;
                    }
                    break;
            }
        }

        // part 2
        long b = 99 * 100 - (-100000);
        long c = b - (-17000);
        long step = - (-17);
        for(int j = (int) b; j <= c; j+=step){
            if( !prime(j) )
                value[1]++;
        }

        return value;
    }

    // method for finding if the number is prime or not
    public static boolean prime(int number){
        if(number <=3 )
            return false;

        for( int i = 2; i <= Math.sqrt(number); i++){
            if ( number % i == 0)
                return false;
        }

        return true;
    }

    // day 22
    //******************************************************************************************************************
    public static int[] day22(){
        int[] infection = new int[]{0,0};
        ArrayList<String> grid = scanInput22();
        int x_pos = grid.size()/2 ;
        int y_pos = grid.get(x_pos).length()/2;
        facingStates faceState = facingStates.up;
        int x_chng = -1;
        int y_chng = 0;

        for(int i = 0; i < 10000000L; i++){
            switch (grid.get(x_pos).charAt(y_pos)){
                case '.' :
                    grid.set(x_pos,grid.get(x_pos).substring(0,y_pos)+grid.get(x_pos).substring(y_pos,y_pos+1).replace('.','W')+grid.get(x_pos).substring(y_pos+1,grid.get(x_pos).length()));
                    variables tmp = turnLeft(x_chng, y_chng, faceState);
                    x_chng = tmp.x_chng;
                    y_chng = tmp.y_chnt;
                    faceState = tmp.faceState;
                    x_pos += x_chng;
                    y_pos += y_chng;
                    infection[0]++;
                    if ( (x_pos == 0) || (x_pos == grid.size()-1) || (y_pos == 0) || (y_pos == grid.get(0).length()-1) ) {
                        grid = addRowsAndCols(grid);
                        x_pos++;
                        y_pos++;
                    }
                    break;
                case '#' :
                    grid.set(x_pos,grid.get(x_pos).substring(0,y_pos)+grid.get(x_pos).substring(y_pos,y_pos+1).replace('#','F')+grid.get(x_pos).substring(y_pos+1,grid.get(x_pos).length()));
                    tmp = turnRight(x_chng, y_chng, faceState);
                    x_chng = tmp.x_chng;
                    y_chng = tmp.y_chnt;
                    faceState = tmp.faceState;
                    x_pos += x_chng;
                    y_pos += y_chng;
                    if ( (x_pos == 0) || (x_pos == grid.size()-1) || (y_pos == 0) || (y_pos == grid.get(0).length()-1) ) {
                        grid = addRowsAndCols(grid);
                        x_pos++;
                        y_pos++;
                    }
                    break;
                case 'F' :
                    grid.set(x_pos,grid.get(x_pos).substring(0,y_pos)+grid.get(x_pos).substring(y_pos,y_pos+1).replace('F','.')+grid.get(x_pos).substring(y_pos+1,grid.get(x_pos).length()));
                    tmp = turnBack(x_chng, y_chng, faceState);
                    x_chng = tmp.x_chng;
                    y_chng = tmp.y_chnt;
                    faceState = tmp.faceState;
                    x_pos += x_chng;
                    y_pos += y_chng;
                    if ( (x_pos == 0) || (x_pos == grid.size()-1) || (y_pos == 0) || (y_pos == grid.get(0).length()-1) ) {
                        grid = addRowsAndCols(grid);
                        x_pos++;
                        y_pos++;
                    }
                    break;
                case 'W' :
                    grid.set(x_pos,grid.get(x_pos).substring(0,y_pos)+grid.get(x_pos).substring(y_pos,y_pos+1).replace('W','#')+grid.get(x_pos).substring(y_pos+1,grid.get(x_pos).length()));
                    x_pos += x_chng;
                    y_pos += y_chng;
                    infection[1]++;
                    if ( (x_pos == 0) || (x_pos == grid.size()-1) || (y_pos == 0) || (y_pos == grid.get(0).length()-1) ) {
                        grid = addRowsAndCols(grid);
                        x_pos++;
                        y_pos++;
                    }
                    break;
                default: break;
            }
        }

        return infection;
    }

    // class for storing temporary variables
    private static class variables{
        private int x_chng = 0;
        private int y_chnt = 0;
        private facingStates faceState = facingStates.up;
    }

    // method for turning backwards
    private static variables turnBack(int x_chng, int y_chng, facingStates faceStates){
        variables variables = new variables();
        switch (faceStates){
            case up:
                variables.faceState = facingStates.down;
                variables.x_chng = -x_chng;
                variables.y_chnt = -y_chng;
                break;
            case left:
                variables.faceState = facingStates.right;
                variables.x_chng = -x_chng;
                variables.y_chnt = -y_chng;
                break;
            case down:
                variables.faceState = facingStates.up;
                variables.x_chng = -x_chng;
                variables.y_chnt = -y_chng;
                break;
            case right:
                variables.faceState = facingStates.left;
                variables.x_chng = -x_chng;
                variables.y_chnt = -y_chng;
                break;
        }
        return variables;
    }

    // method for turning left
    private static variables turnLeft(int x_chng, int y_chng, facingStates faceStates){
        variables variables = new variables();
        switch (faceStates){
            case up:
                variables.faceState = facingStates.left;
                variables.x_chng = y_chng;
                variables.y_chnt = x_chng;
                break;
            case left:
                variables.faceState = facingStates.down;
                variables.x_chng = -y_chng;
                variables.y_chnt = -x_chng;
                break;
            case down:
                variables.faceState = facingStates.right;
                variables.x_chng = y_chng;
                variables.y_chnt = x_chng;
                break;
            case right:
                variables.faceState = facingStates.up;
                variables.x_chng = -y_chng;
                variables.y_chnt = -x_chng;
                break;
        }

        return variables;
    }

    // method for turning right
    private static variables turnRight(int x_chng, int y_chng, facingStates faceStates){
        variables variables = new variables();
        switch (faceStates){
            case up:
                variables.faceState = facingStates.right;
                variables.x_chng = -y_chng;
                variables.y_chnt = -x_chng;
                break;
            case left:
                variables.faceState = facingStates.up;
                variables.x_chng = y_chng;
                variables.y_chnt = x_chng;
                break;
            case down:
                variables.faceState = facingStates.left;
                variables.x_chng = -y_chng;
                variables.y_chnt = -x_chng;
                break;
            case right:
                variables.faceState = facingStates.down;
                variables.x_chng = y_chng;
                variables.y_chnt = x_chng;
                break;
        }
        return variables;
    }

    // method for adding columns and rows to the grid
    private static ArrayList<String> addRowsAndCols(ArrayList<String> grid){
        int i = 0;
        for ( String string: grid){
            string = '.'+string+'.';
            grid.set(i,string);
            i++;
        }
        String string = new String();
        for (int j = 0; j < grid.get(0).length(); j++) {
            string = string.concat(".");
        }
        grid.add(0,string);
        grid.add(grid.size(),string);

        return grid;
    }

    // method for scanning input for day22
    private static ArrayList<String> scanInput22(){
        ArrayList<String> input = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File("day22"));

            while(scanner.hasNextLine()){
                input.add(scanner.nextLine());
            }
        }
        catch (FileNotFoundException ex){System.out.println("Unable to find file");}

        return input;
    }
}
