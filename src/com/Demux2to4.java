package com;

public class Demux2to4 {

    byte[] inputs;
    byte[] outputs;
    int ID;
    byte valueToReg;

    Demux2to4(int U_number) {
        inputs = new byte[2];
        outputs = new byte[4];
        this.ID = U_number;
    }

    void selectInput(char OE) {
        switch (OE) {
            case 'a':
            case 'A':
                valueToReg = inputs[0];
                break;
            case 'b':
            case 'B':
                valueToReg = inputs[1];
                break;
            default:
                System.err.println("Something went wrong in com.Demux2to4.java");
                System.exit(1);
        }
    }

    void selectReg(int regNum) {
        switch (regNum) {
            case 0:
                Main.R0.setInput(valueToReg);
                break;
            case 1:
                Main.R1.setInput(valueToReg);
                break;
            case 2:
                Main.R2.setInput(valueToReg);
                break;
            case 3:
                Main.R3.setInput(valueToReg);
                break;
            default:
                System.err.println("Something went wrong in com.Demux2to4.java");
                System.exit(1);
        }
    }

}
