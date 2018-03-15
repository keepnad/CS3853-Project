package com;

public class LogicGate {

    byte inputA, inputB;
    String type;
    int ID;

    LogicGate(String type, int U_number) {
        this.ID = U_number;
        this.type = type;
    }

    public void operate() {
        switch (this.type) {
            case "AND":
                Main.aluMux.inputs[1] = (byte) (this.inputA & this.inputB);
                break;
            case "OR":
                Main.aluMux.inputs[2] = (byte) (this.inputA | this.inputB);
                break;
            case "XOR":
                Main.aluMux.inputs[3] = (byte) (this.inputA ^ this.inputB);
                break;
            case "NOT":
                Main.aluMux.inputs[4] = (byte) (~this.inputB);
                break;
            default:
                System.out.println("Error selecting type of logic gate.");
                break;
        }
    }
}
