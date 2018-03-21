package com.registers;

public abstract class Register {

    int size;
    int ID;

    Register(int bits, int U_number) {
        this.size = bits;
        this.ID = U_number;
    }

    public abstract byte getOutput();

    public abstract void clockIn();

    public abstract void setInput(byte val);
}
