import java.util.*;

public class Challenges3 {

    private static Scanner scanner = new Scanner(System.in);

    // day 9
    //******************************************************************************************************************
    public static int[] day9(){
        int[] score = new int[] {0,0};
        System.out.println("Enter you input:");
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
    // not working so far
    // correct aswer for part 1 should be 11413
    // all lists that are to be inverted have correct starting and finishing position
    // also practice input containing lengths 3 4 5 1 and list filled with values 0 1 2 3 4 is working
    public static int[] day10(){
        int[] multiple = new int[] {0, 0};
        int stepSize = 0;
        int startPosition = 0;
        ArrayList<Integer> inputLengths = scanInput10();
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList = fillList(intList);

        for(int length: inputLengths){
            System.out.println("\n"+length+"***************");
            //System.out.println(startPosition+" start pos");
            if( length != 1 && length != 0)
                intList = invertIntList(intList, startPosition, length);
            startPosition += ( (stepSize+length)%intList.size());
            if ( startPosition >= intList.size())
                startPosition -= intList.size();
            stepSize++;
        }

        multiple[0] = intList.get(0) * intList.get(1);

        return multiple;
    }

    // method for inverting values in the list
    private static ArrayList<Integer> invertIntList(ArrayList<Integer> intList, int startPosition, int intListInvertLengt){
        int tmp = 0;
        int tmpPos = startPosition;
        List<Integer> intSubList;
        ArrayList<Integer> intListCPY = (ArrayList<Integer>) intList.clone();

        if( intList.size()-startPosition < intListInvertLengt){
            intSubList = intListCPY.subList(startPosition,intListCPY.size());
            intSubList.addAll(intListCPY.subList(0,intListCPY.size()-intListInvertLengt+1));
        }
        else {
            intSubList = intListCPY.subList(startPosition,startPosition+intListInvertLengt);
            // intSubList = 0, 1 , 2
        }
        //System.out.println(intSubList.toString());
        int tmpFinPos;
        if ( startPosition+intSubList.size() < intList.size() )
            tmpFinPos = startPosition + intSubList.size();
        else
            tmpFinPos = intList.size()-intSubList.size()+1;
        //System.out.println(tmpFinPos+" fin pos ");
        for(int i = 0; i < intSubList.size()/2; i++){
            //System.out.println(intSubList.get(i));
            tmp = intSubList.get(i);
            //tmp = intSubList.get(tmpPos+1);
            intList.set(tmpPos,intSubList.get(intSubList.size()-i-1));
            //System.out.println(intList.toString());
            //System.out.print(i+" ");
            intList.set(calcFinPos(tmpFinPos,i,intList.size()),tmp);
            //System.out.println(intList.toString());
            if(tmpPos+1 == intList.size())
                tmpPos = 0;
            else
                tmpPos++;
        }

        return intList;
    }

    // method for calculating final position
    private static int calcFinPos(int FinPos, int iter, int offSet){
        if( (FinPos - iter) < 0)
            return FinPos-iter+offSet;
        else
            return FinPos-iter;
    }

    // method for fill list with values
    private static ArrayList<Integer> fillList(ArrayList<Integer> list){
        for(int i = 0; i < 256; i++)
            list.add(i);

        return list;
    }

    // method for scanning input for day 12
    private static ArrayList<Integer> scanInput10(){
        ArrayList<Integer>  inputInts = new ArrayList<>();

        System.out.println("Enter you lengths: ");
        while(scanner.hasNextInt()){
            inputInts.add(scanner.nextInt());
        }

        return inputInts;
    }

    // day 11
    //******************************************************************************************************************
    // missing so far, not enough time today
}
