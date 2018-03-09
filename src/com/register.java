package com;

public abstract class register {

    int size;
    int ID;
    byte value;

    register(int bits, int U_number) {
        this.size = bits;
        this.ID = U_number;
        value = (byte) 0x0;
    }

    byte getValue() {
        return this.value;
    }

    void setValue(byte val) {
        this.value = val;

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
            case 14:
            case 15:
            case 110:
            default:
                System.err.println("Something went wrong in com.register.java...");
                System.exit(1);

        }
    }
}
