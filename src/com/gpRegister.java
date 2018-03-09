package com;

public class gpRegister extends register {

    gpRegister(int bits, int U_number) {

        super(bits, U_number);

    }

    void setInput(byte val){
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
                System.err.println("Something went wrong in com.gpRegister.java...");
                System.exit(1);

        }

    }
}
