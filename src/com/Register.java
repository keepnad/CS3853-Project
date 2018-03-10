package com;

public abstract class Register {

    int size;
    int ID;

    Register(int bits, int U_number) {
        this.size = bits;
        this.ID = U_number;
    }

    abstract byte getOutput();

    abstract void clockIn();

    abstract void setInput(byte val);
}
