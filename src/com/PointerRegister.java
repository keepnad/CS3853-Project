package com;

public class PointerRegister extends Register {

    byte inputHigh, inputLow;
    byte outputHigh, outputLow;

    PointerRegister(int bits, int U_number) {

        super(bits, U_number);

    }

    void setInput(byte val){

    }

    void setInput(byte valHigh, byte valLow) {
        this.inputHigh = valHigh;
        this.inputLow = valLow;

        switch (this.ID) {
            case 14:

                break;
            case 15:

                break;
            default:
                System.err.println("Something went wrong in com.PointerRegister.java...");
                System.exit(1);
        }
    }

    byte getOutput() {
        return (byte) 0x0;
    }

    byte getHighOutput(){
        return outputHigh;
    }

    byte getLowOutput(){
        return outputLow;
    }

    void clockIn() {
        this.outputHigh = this.inputHigh;
        this.outputLow = this.inputLow;
    }
}
