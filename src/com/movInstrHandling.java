package com;

public final class movInstrHandling {

    static StringBuilder binStrOperand0, binStrOperand1, binStrOpcode;
    static int srcRegNum, dstRegNum;

    public static void mov80(byte operand0) {

        binStrOperand0 = new StringBuilder(Integer.toBinaryString(operand0));

        while (binStrOperand0.length() < 8) {
            binStrOperand0.insert(0, '0');
        }

        while (binStrOperand0.length() > 8) {
            binStrOperand0.deleteCharAt(0);
        }

        switch (binStrOperand0.substring(0, 2)) {
            case "00":
                Main.DST = Main.R0;
                dstRegNum = 0;
                break;
            case "01":
                Main.DST = Main.R1;
                dstRegNum = 1;
                break;
            case "10":
                Main.DST = Main.R2;
                dstRegNum = 2;
                break;
            case "11":
                Main.DST = Main.R3;
                dstRegNum = 3;
                break;
            default:
                System.err.println("Invalid value in operand: " + binStrOperand0.substring(0, 2));
        }

        switch (binStrOperand0.substring(4, 6)) {
            case "00":
                Main.SRC = Main.R0;
                srcRegNum = 0;
                break;
            case "01":
                Main.SRC = Main.R1;
                srcRegNum = 1;
                break;
            case "10":
                Main.SRC = Main.R2;
                srcRegNum = 2;
                break;
            case "11":
                Main.SRC = Main.R3;
                srcRegNum = 3;
                break;
            default:
                System.err.println("Invalid value in operand: " + binStrOperand0.substring(4, 6));
        }

        Main.SRC.setValue(Main.SRC.getValue());
        //System.err.println("value using SRC pointer: " + SRC.getValue());
        Main.regOutMuxA.selectInput(srcRegNum);
        //System.err.println("value from regOutMuxA: " + regOutMuxA.output);
        //System.err.println("value from dataOutA: " + dataOutA);
        Main.regInMuxA.selectInput(0);
        //System.err.println("value from regInMuxA: " + regInMuxA.output);
        Main.regDeMux.selectInput('A');
        //System.err.println("destination reg number: " + dstRegNum);
        try {
            while (Main.CLK) {
                Thread.sleep(25);
            }
            while (!Main.CLK) {
                Thread.sleep(25);
            }
        } catch (InterruptedException e) {
        }
        Main.regDeMux.selectReg(dstRegNum);


        //DST.setValue(SRC.getValue());

    }


    public static void mov81(byte operand0, byte operand1) {
        binStrOperand0 = new StringBuilder(Integer.toBinaryString(operand0));

        while (binStrOperand0.length() < 8) {
            binStrOperand0.insert(0, '0');
        }
        while (binStrOperand0.length() > 8) {
            binStrOperand0.deleteCharAt(0);
        }

        switch (binStrOperand0.substring(0, 2)) {
            case "00":
                Main.DST = Main.R0;
                dstRegNum = 0;
                break;
            case "01":
                Main.DST = Main.R1;
                dstRegNum = 1;
                break;
            case "10":
                Main.DST = Main.R2;
                dstRegNum = 2;
                break;
            case "11":
                Main.DST = Main.R3;
                dstRegNum = 3;
                break;
            default:
                System.err.println("Invalid value in operand: " + binStrOperand0.substring(0, 2));
        }

        Main.instrLineLow = operand1;
        Main.regInMuxA.selectInput(2);
        Main.regDeMux.selectInput('A');
        try {
            while (Main.CLK) {
                Thread.sleep(25);
            }
            while (!Main.CLK) {
                Thread.sleep(25);
            }
        } catch (InterruptedException e) {
        }
        Main.regDeMux.selectReg(dstRegNum);


        //  DST.setValue(operand1);

    }

    public static void mov82() {

    }

    public static void mov83() {

    }
}
