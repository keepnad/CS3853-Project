package com.registers;

public class FlagsRegister extends Register {

    byte input, output;

    public FlagsRegister(int bits, int U_number) {

        super(bits, U_number);

    }

    public void setInput(byte val){

    }

    public byte getOutput() {
        return this.output;
    }

    public void clockIn(){
        this.output = this.input;
    }
}
