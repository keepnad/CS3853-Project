package com.muxes;

import com.Main;

public class Mux4To1 extends Mux {

    public Mux4To1(int n, int U_number) {
        super(n, U_number);
    }

    public void selectInput(int n) {
        switch (this.ID) {
            case 220:
                this.inputs[0] = Main.dataOutA;
                this.inputs[1] = Main.dataOutB;
                this.inputs[2] = Main.aluMux.output;
                this.inputs[3] = Main.instrLineLow;

                this.output = this.inputs[n];
        }

    }
}
