package com;

public abstract class mux {

    int numInputs;
    int ID;
    byte inputs[];
    byte output;
    char aOrB = '\0';

    mux(int n, int U_number) {
        this.numInputs = n;
        this.ID = U_number;
        inputs = new byte[numInputs];
    }

    mux(int n, int U_number, char aOrB) {
        this.numInputs = n;
        this.ID = U_number;
        inputs = new byte[numInputs];
        this.aOrB = aOrB;
    }

    void selectInput(int n) {
        switch (aOrB) {
            case 0:
                break;
            case 'A':
                this.inputs[0] = Main.dataOutA;
                this.inputs[2] = Main.instrLineLow;
                break;
            case 'B':
                this.inputs[0] = Main.dataOutB;
                this.inputs[2] = Main.instrLineHigh;
                break;
        }

        this.output = this.inputs[n];

        switch (this.ID) {
            case 112:
                Main.dataOutA = output;
                break;
            case 113:
                Main.dataOutB = output;
                break;
            case 118:
                switch (this.aOrB) {
                    case 'A':
                        Main.regDeMux.inputs[0] = this.output;
                        break;
                    case 'B':
                        Main.regDeMux.inputs[1] = this.output;
                        break;
                    default:
                        System.out.println(this.getClass().getSimpleName());
                        System.err.println("Something went wrong in com.mux.java a or b switch...");
                        System.exit(1);
                }
                break;
            default:
                System.err.println("Something went wrong in com.mux.java... ID switch");
                System.exit(1);
        }


    }
}
