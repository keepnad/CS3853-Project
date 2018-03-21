package com.muxes;

import com.Main;

public class Mux8To1 extends Mux {

    public Mux8To1(int n, int U_number) {
        super(n, U_number);
    }

    public Mux8To1(int n, int U_number, char aOrB) {
        super(n, U_number, aOrB);
    }

    public void selectInput(int n) {
        if(this.ID == 112 || this.ID == 113){
            this.inputs[4] = Main.memRead;
            this.inputs[6] = Main.instrLineLow;
        }

        switch (aOrB) {
            case 0:
                break;
            case 'A':
                this.inputs[0] = Main.dataOutA;
                this.inputs[2] = Main.instrLineLow;
                this.inputs[3] = Main.aluMux.output;
                this.inputs[5] = Main.memRead;
                break;
            case 'B':
                this.inputs[0] = Main.dataOutB;
                this.inputs[2] = Main.instrLineHigh;
                this.inputs[3] = Main.aluMux.output;
                this.inputs[5] = Main.memRead;
                break;
        }

        this.output = this.inputs[n];

        switch (this.ID) {
            case 111:
                break;
            case 112:
                Main.dataOutA = this.output;
                break;
            case 113:
                Main.dataOutB = this.output;
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
                        System.err.println("Something went wrong in com.muxes.Mux8To1.java a or b switch...");
                        System.exit(1);
                }
                break;
            default:
                System.err.println("Something went wrong in com.muxes.Mux8To1.java... ID switch");
                System.exit(1);
        }
    }
}
