public class Register {

    int value = 0;                   // value of register
    String name;                     // name of register
    String operation;                // operation of the register
    int valueChange;                 // number by which the value needs to change
    String dependencyRegister;       // register on which condition depends
    String conditionSign;            // condition of the dependency register
    int dependencyRegisterValue;      // value of dependency register

    public Register(String regName, String regOperation, String regValueChange, String regDependencyRegister, String regConditionSign, String regDepRegVal){
        this.name = regName;
        this.operation = regOperation;
        this.valueChange = Integer.parseInt(regValueChange);
        this.dependencyRegister = regDependencyRegister;
        this.conditionSign = regConditionSign;
        this.dependencyRegisterValue = Integer.parseInt(regDepRegVal);
    }

}
