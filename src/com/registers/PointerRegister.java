package com.registers;

import com.Main;

public class PointerRegister extends Register {

    public byte inputHigh, inputLow;
    public byte outputHigh, outputLow;

    public PointerRegister(int bits, int U_number) {

        super(bits, U_number);

    }

    public void setInput(byte val){

    }

    public void setInput(byte valHigh, byte valLow) {
        this.inputHigh = valHigh;
        this.inputLow = valLow;

        switch (this.ID) {
            case 14:

                break;
            case 15:
                this.inputHigh = Main.ipMux.outputHigh;
                this.inputLow = Main.ipMux.output;

                break;
            default:
                System.err.println("Something went wrong in com.registers.PointerRegister.java...");
                System.exit(1);
        }
    }

    public byte getOutput() {
        return (byte) 0x0;
    }

    public byte getHighOutput(){
        return outputHigh;
    }

    public byte getLowOutput(){
        return outputLow;
    }

    public void clockIn() {
        this.outputHigh = this.inputHigh;
        this.outputLow = this.inputLow;
    }
}
