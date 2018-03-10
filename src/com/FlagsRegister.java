package com;

public class FlagsRegister extends Register {

    byte input, output;

    FlagsRegister(int bits, int U_number) {

        super(bits, U_number);

    }

    void setInput(byte val){

    }

    byte getOutput() {
        return this.output;
    }

    void clockIn(){
        this.output = this.input;
    }
}
