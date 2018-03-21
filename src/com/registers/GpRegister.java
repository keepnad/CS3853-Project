package com.registers;

import com.Main;

public class GpRegister extends Register {

    byte input, output;

    public GpRegister(int bits, int U_number) {

        super(bits, U_number);

    }

    public void setInput(byte val){
        this.input = val;

        switch (this.ID) {
            case 10:
                Main.regOutMuxA.inputs[0] = Main.regOutMuxB.inputs[0] = val;
                break;
            case 11:
                Main.regOutMuxA.inputs[1] = Main.regOutMuxB.inputs[1] = val;
                break;
            case 12:
                Main.regOutMuxA.inputs[2] = Main.regOutMuxB.inputs[2] = val;
                break;
            case 13:
                Main.regOutMuxA.inputs[3] = Main.regOutMuxB.inputs[3] = val;
                break;
            default:
                System.err.println("Something went wrong in com.registers.GpRegister.java...");
                System.exit(1);

        }

    }

    public byte getOutput() {
        return this.output;
    }

    public void clockIn(){
        this.output = this.input;
    }
}
