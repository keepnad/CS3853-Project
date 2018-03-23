package com;

public class AddSub {

    public byte inputA, inputB;
    public byte carryIn;
    int ID;

    public AddSub(int U_number) {
        this.ID = U_number;
    }

    public void operate() {
        if (carryIn == (byte) 0x01) {

            if (Main.flagValues[2]) {
                Main.aluMux.inputs[0] = (byte) (inputA - (inputB + 1));
            } else {
                Main.aluMux.inputs[0] = (byte) (inputA - inputB);
            }
        } else if (carryIn == (byte) 0x00) {

            if (Main.flagValues[2]) {
                Main.aluMux.inputs[0] = (byte) (inputA + inputB + 1);
            } else {
                Main.aluMux.inputs[0] = (byte) (inputA + inputB);
            }
        } else {
            System.err.println("Error in AddSub, bad carryIn value");
        }
    }
}
