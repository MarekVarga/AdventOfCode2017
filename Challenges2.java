import java.util.*;

public class Challenges2 {

    private static Scanner scan = new Scanner(System.in);

    public static String[] day7(){
        String[] towName = new String[2];
        String[] towers = scanInput7();
        String[] firstLine = towers[0].split("\\s");

        // part 1
        towName[0] = findTowerBelow(firstLine[0], towers);

        // part 2
        // to be continued...

        return towName;
    }

    // method for scanning input for day 7 and day 8
    private static String[] scanInput7(){
        String[] inputs = new String[0];
        String[] tmp = new String[1];
        String data;

        System.out.println("Enter your input; ");
        for(int i = 0; scan.hasNextLine(); i++) {
            inputs = new String[i+1];
            for(int j = 0; j < i; j++){
                inputs[j] = tmp[j];
            }
            data = scan.nextLine();
            inputs[i] = data;
            tmp = new String[i+2];
            for(int j = 0; j <= i; j++)
                tmp[j]= inputs[j];
        }

        return inputs;
    }

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
        String[] inputRegisters = scanInput7();
        ArrayList<Register> registers = new ArrayList<Register>(inputRegisters.length);

        // creating registers
        for(int i = 0; i < inputRegisters.length; i++){
            String[] lineOfInputRegisters = inputRegisters[i].split("\\s");
            registers.add(i, new Register(lineOfInputRegisters[0], lineOfInputRegisters[1], lineOfInputRegisters[2], lineOfInputRegisters[4], lineOfInputRegisters[5], lineOfInputRegisters[6]));
        }
        // changing register values
        for(Register register: registers){
            switch (register.conditionSign){
                case "<" :
                    register = caseLess(register, registers);
                    value[1] = newHighestValue(register, value[1]);
                    registers = setNewValue(registers, register.name, register.value);
                    break;
                case "<=" :
                    register = caseLessOrEqual(register, registers);
                    value[1] = newHighestValue(register, value[1]);
                    registers = setNewValue(registers, register.name, register.value);
                    break;
                case ">" :
                    register = caseGreater(register,registers);
                    value[1] = newHighestValue(register, value[1]);
                    registers = setNewValue(registers, register.name, register.value);
                    break;
                case ">=" :
                    register = caseGreaterOrEqual(register, registers);
                    value[1] = newHighestValue(register, value[1]);
                    registers = setNewValue(registers, register.name, register.value);
                    break;
                case "==" :
                    register = caseEqual(register, registers);
                    value[1] = newHighestValue(register, value[1]);
                    registers = setNewValue(registers, register.name, register.value);
                    break;
                default :
                    register = caseNotEqual(register, registers);
                    value[1] = newHighestValue(register, value[1]);
                    registers = setNewValue(registers, register.name, register.value);
                    break;
            }
        }
        // finding register with the greatest value
        for(Register register: registers){
            if( register.value > value[0] )
                value[0] = register.value;
        }

        return value;
    }

    // method for getting value of the dependency register
    private static int getDependencyRegisterValue(ArrayList<Register> registers, String dependencyRegister){
        for(Register register: registers)
            if( register.name.equals(dependencyRegister) )
                return register.value;
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
            return register.value;

        return previousHighestValue;
    }

    private static Register caseLess(Register register, ArrayList<Register> registers){
        if( getDependencyRegisterValue(registers, register.dependencyRegister) < register.dependencyRegisterValue){
            switch ( register.operation ){
                case "inc" :
                    register.value += register.valueChange;
                    break;
                default:
                    register.value -= register.valueChange;
            }
        }

        return register;
    }

    private static Register caseLessOrEqual(Register register, ArrayList<Register> registers){
        if( getDependencyRegisterValue(registers, register.dependencyRegister) <= register.dependencyRegisterValue){
            switch ( register.operation ){
                case "inc" :
                    register.value += register.valueChange;
                    break;
                default:
                    register.value -= register.valueChange;
            }
        }

        return register;
    }

    private static Register caseGreater(Register register, ArrayList<Register> registers){
        if( getDependencyRegisterValue(registers, register.dependencyRegister) > register.dependencyRegisterValue){
            switch ( register.operation ){
                case "inc" :
                    register.value += register.valueChange;
                    break;
                default:
                    register.value -= register.valueChange;
            }
        }

        return register;
    }

    private static Register caseGreaterOrEqual(Register register, ArrayList<Register> registers){
        if( getDependencyRegisterValue(registers, register.dependencyRegister) >= register.dependencyRegisterValue){
            switch ( register.operation ){
                case "inc" :
                    register.value += register.valueChange;
                    break;
                default:
                    register.value -= register.valueChange;
            }
        }

        return register;
    }

    private static Register caseEqual(Register register, ArrayList<Register> registers){
        if( getDependencyRegisterValue(registers, register.dependencyRegister) == register.dependencyRegisterValue){
            switch ( register.operation ){
                case "inc" :
                    register.value += register.valueChange;
                    break;
                default:
                    register.value -= register.valueChange;
            }
        }

        return register;
    }

    private static Register caseNotEqual(Register register, ArrayList<Register> registers){
        if( getDependencyRegisterValue(registers, register.dependencyRegister) != register.dependencyRegisterValue){
            switch ( register.operation ){
                case "inc" :
                    register.value += register.valueChange;
                    break;
                default:
                    register.value -= register.valueChange;
            }
        }

        return register;
    }
}
