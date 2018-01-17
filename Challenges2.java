import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

public class Challenges2 {

    private static Scanner scan = new Scanner(System.in);

    public static String[] day7(){
        String[] towName = new String[2];
        String[] towers = Challenges.scanInput4("day7");
        String[] firstLine = towers[0].split("\\s");

        // part 1
        towName[0] = findTowerBelow(firstLine[0], towers);

        // part 2
        // to be continued...

        return towName;
    }

    // recursive method for finding the bottom tower
    private static String findTowerBelow(String towerNameAbove, String[] towerList){

        for( int i = 0; i < towerList.length; i++){
            String[] towerListLine = towerList[i].split("\\s");
            for( int j = 0; j < towerListLine.length; j++){
                if( towerListLine[j].contains(towerNameAbove) && (j != 0 )){
                    return findTowerBelow(towerListLine[0], towerList);
                }
            }
        }

        return towerNameAbove;
    }

    // day 8
    //******************************************************************************************************************
    public static int[] day8(){
        int[] value = new int[] { 0, 0};
        String[] inputRegisters = Challenges.scanInput4("day8");
        ArrayList<Register> registers = new ArrayList<Register>(inputRegisters.length);

        // creating registers
        for(int i = 0; i < inputRegisters.length; i++){
            String[] lineOfInputRegisters = inputRegisters[i].split("\\s");
            registers.add(i, new Register(lineOfInputRegisters[0], lineOfInputRegisters[1], lineOfInputRegisters[2], lineOfInputRegisters[4], lineOfInputRegisters[5], lineOfInputRegisters[6]));
        }
        // changing register values
        for(Register register: registers){
            register = changeRegValue(register, registers);
            // part 2
            value[1] = newHighestValue(register, value[1]);
            registers = setNewValue(registers, register.name, (int) register.value);
        }
        // part 1
        // finding register with the greatest value
        for(Register register: registers){
            if( register.value > value[0] )
                value[0] = (int) register.value;
        }

        return value;
    }

    // method for getting value of the dependency register
    private static int getDependencyRegisterValue(ArrayList<Register> registers, String dependencyRegister){
        for(Register register: registers)
            if( register.name.equals(dependencyRegister) )
                return (int) register.value;
        return 0;
    }

    // method for setting new value in registers with the same name
    private static ArrayList<Register> setNewValue(ArrayList<Register> registers, String regName, int newRegValue){
        for (Register register: registers) {
            if( register.name.equals(regName)){
                register.value = newRegValue;
            }
        }
        return registers;
    }

    // method for setting new highest value
    private static int newHighestValue(Register register, int previousHighestValue){

        if( register.value > previousHighestValue )
            return (int) register.value;

        return previousHighestValue;
    }

    // method for setting new register value
    private static Register setNewRegisterValue(Register register){
        if( register.operation.equals("inc"))
            register.value += register.valueChange;
        else
            register.value -= register.valueChange;

        return register;
    }

    private static boolean caseLess(Register register, ArrayList<Register> registers){
        return ( getDependencyRegisterValue(registers, register.dependencyRegister) < register.dependencyRegisterValue);
    }

    private static boolean caseLessOrEqual(Register register, ArrayList<Register> registers){
        return ( getDependencyRegisterValue(registers, register.dependencyRegister) <= register.dependencyRegisterValue);
    }

    private static boolean caseGreater(Register register, ArrayList<Register> registers){
        return ( getDependencyRegisterValue(registers, register.dependencyRegister) > register.dependencyRegisterValue);
    }

    private static boolean caseGreaterOrEqual(Register register, ArrayList<Register> registers){
        return ( getDependencyRegisterValue(registers, register.dependencyRegister) >= register.dependencyRegisterValue);
    }

    private static boolean caseEqual(Register register, ArrayList<Register> registers){
        return ( getDependencyRegisterValue(registers, register.dependencyRegister) == register.dependencyRegisterValue);
    }

    private static boolean caseNotEqual(Register register, ArrayList<Register> registers){
        return ( getDependencyRegisterValue(registers, register.dependencyRegister) != register.dependencyRegisterValue);
    }

    // method for changing register value if dependency condition is met
    private static Register changeRegValue(Register register, ArrayList<Register> registers){
        switch (register.conditionSign){
            case "<" :
                if( caseLess(register, registers) )
                    return setNewRegisterValue(register);
                break;
            case "<=" :
                if( caseLessOrEqual(register, registers) )
                    return setNewRegisterValue(register);
                break;
            case ">" :
                if( caseGreater(register,registers) )
                    return setNewRegisterValue(register);
                break;
            case ">=" :
                if( caseGreaterOrEqual(register, registers) )
                    return setNewRegisterValue(register);
                break;
            case "==" :
                if( caseEqual(register, registers) )
                    return setNewRegisterValue(register);
                break;
            default :
                if( caseNotEqual(register, registers) )
                    return setNewRegisterValue(register);
                break;
        }
        return register;
    }
}
