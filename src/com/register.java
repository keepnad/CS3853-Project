package com;

public abstract class register {

    int size;
    int ID;
    //byte value;
    byte input, output;

    register(int bits, int U_number) {
        this.size = bits;
        this.ID = U_number;
        input = output = (byte) 0x0;
    }

    byte getOutput() {
        return this.output;
    }

    void clockIn(){
        this.output = this.input;
    }

    abstract void setInput(byte val);
}
