package com.muxes;

import com.Main;

public class Mux4To1 extends Mux {

    public Mux4To1(int n, int U_number) {
        super(n, U_number);
    }

    public void selectInput(int n) {
        switch (this.ID) {

            case 115:
                this.inputs[0] = Main.dataOutA;
                this.inputHigh[0] = Main.dataOutB;

                this.inputs[1] = Main.instrLineLow;
                this.inputHigh[1] = Main.instrLineHigh;

                //Main.incInstr.operate();

                //Main.relJump.operate();

                break;

            case 116:
                this.inputs[0] = Main.IP.outputLow;
                this.inputHigh[0] = Main.IP.outputHigh;

                this.inputs[2] = Main.instrLineLow;
                this.inputHigh[2] = Main.instrLineHigh;

                this.inputs[3] = Main.dataOutA;
                this.inputHigh[3] = Main.dataOutB;

                break;

            case 220:
                this.inputs[0] = Main.dataOutA;
                this.inputs[1] = Main.dataOutB;
                this.inputs[2] = Main.aluMux.output;
                this.inputs[3] = Main.instrLineLow;
        }
        this.output = this.inputs[n];
        if(this.ID == 115 || this.ID == 116) {
            this.outputHigh = this.inputHigh[n];
        }
    }
}
