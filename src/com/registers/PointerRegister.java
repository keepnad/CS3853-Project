package com.registers;

public class PointerRegister extends Register {

    byte inputHigh, inputLow;
    byte outputHigh, outputLow;

    public PointerRegister(int bits, int U_number) {

        super(bits, U_number);

    }

    public void setInput(byte val){

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
                System.err.println("Something went wrong in com.registers.PointerRegister.java...");
                System.exit(1);
        }
    }

    public byte getOutput() {
        return (byte) 0x0;
    }

    byte getHighOutput(){
        return outputHigh;
    }

    byte getLowOutput(){
        return outputLow;
    }

    public void clockIn() {
        this.outputHigh = this.inputHigh;
        this.outputLow = this.inputLow;
    }
}
