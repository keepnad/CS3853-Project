package com;

public class mux8to1 extends mux {

    mux8to1(int n, int U_number) {
        super(n, U_number);
    }

    mux8to1(int n, int U_number, char aOrB) {
        super(n, U_number, aOrB);
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
                        System.err.println("Something went wrong in com.mux8to1.java a or b switch...");
                        System.exit(1);
                }
                break;
            default:
                System.err.println("Something went wrong in com.mux8to1.java... ID switch");
                System.exit(1);
        }
    }
}
