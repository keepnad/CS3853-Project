package com;

public class pointerRegister extends register {

    pointerRegister(int bits, int U_number) {

        super(bits, U_number);

    }

    void setInput(byte val){
        this.input = val;

        switch(this.ID){
            case 14:

                break;
            case 15:

                break;
            default:System.err.println("Something went wrong in com.pointerRegister.java...");
                System.exit(1);
        }
    }
}
