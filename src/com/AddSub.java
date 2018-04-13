package com;

public class AddSub {

    public byte inputA, inputB;
    public byte carryIn;
    int ID;

    public AddSub(int U_number) {
        this.ID = U_number;
    }

    public void operate() {
        switch (this.ID) {
            case 100:
            if (carryIn == (byte) 0x01) {

                if (Main.flagValues[2]) {
                    Main.aluMux.inputs[0] = (byte) (inputA - (inputB + 1));
                } else {
                    Main.aluMux.inputs[0] = (byte) (inputA - inputB);
                }
            } else if (carryIn == (byte) 0x00) {

                if (Main.flagValues[2]) {
                    Main.aluMux.inputs[0] = (byte) (inputA + inputB + 1);
                } else {
                    Main.aluMux.inputs[0] = (byte) (inputA + inputB);
                }
            } else {
                System.err.println("Error in AddSub, bad carryIn value");
            }
            break;

            case 105:
                int fullAddy = Main.IP.outputHigh * 0x100 + Main.IP.outputLow;
                fullAddy += Main.instrLength;

                Main.ipMux.inputs[2] = (byte) (fullAddy & 0xFF);
                Main.ipMux.inputHigh[2] = (byte) ((fullAddy >>> 8) & 0xFF);
                break;
            case 106:
                fullAddy = Main.IP.outputHigh * 0x100 + Main.IP.outputLow;
                fullAddy += Main.instrLength;
                fullAddy += Main.relOffset;

                Main.ipMux.inputs[3] = (byte) (fullAddy & 0xFF);
                Main.ipMux.inputHigh[3] = (byte) ((fullAddy >>> 8) & 0xFF);
                break;
        }
    }
}
