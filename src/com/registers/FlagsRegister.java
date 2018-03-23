package com.registers;

import com.Main;

public class FlagsRegister extends Register {

    public byte input, output, mask = 0x0F;

    public boolean zeroFlag, signFlag, carryFlag;

    public FlagsRegister(int bits, int U_number) {

        super(bits, U_number);

    }

    public void setInput(byte val) {

        this.input = (byte) (val & mask);

        switch (this.input) {
            case (byte) 0x00:
                zeroFlag = signFlag = carryFlag = false;
                break;
            case (byte) 0x01:
                zeroFlag = signFlag = false;
                carryFlag = true;
                break;
            case (byte) 0x02:
                zeroFlag = carryFlag = false;
                signFlag = true;
                break;
            case (byte) 0x03:
                zeroFlag = false;
                signFlag = carryFlag = true;
                break;
            case (byte) 0x08:
                zeroFlag = true;
                signFlag = carryFlag = false;
                break;
            case (byte) 0x09:
                zeroFlag = carryFlag = true;
                signFlag = false;
                break;
            case (byte) 0x0A:
                zeroFlag = signFlag = true;
                carryFlag = false;
                break;
            case (byte) 0x0B:
                zeroFlag = signFlag = carryFlag = true;

        }

        this.getOutput();

    }

    public byte getOutput() {
        Main.flagValues[0] = this.zeroFlag;
        Main.flagValues[1] = this.signFlag;
        Main.flagValues[2] = this.carryFlag;
        return this.output;
    }

    public void clockIn() {
        this.output = (byte) (this.input & mask);
    }
}
