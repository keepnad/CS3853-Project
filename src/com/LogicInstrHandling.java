package com;

public final class LogicInstrHandling {

    static StringBuilder binStrOperand0;
    static int dstRegNum, srcRegNum;

    public static void not40(byte operand0) {

        dstRegNum = InstrParser.selectReg(operand0, 1);

        Main.regOutMuxB.selectInput(dstRegNum);
        Main.NOT.inputB = Main.regOutMuxB.output;
        Main.NOT.operate();
        Main.aluMux.selectInput(4);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();
    }

    public static void not43(byte operand0, byte operand1) {

        MemoryHandling.readFromMem(operand0, operand1);

        Main.regOutMuxB.selectInput(4);
        Main.NOT.inputB = Main.regOutMuxB.output;
        Main.NOT.operate();
        Main.aluMux.selectInput(4);
        Main.dataBusMux.selectInput(2);

        MemoryHandling.writeToMem(operand0, operand1, Main.dataBusMux.output);
        ClockTimer.waitForTick();

    }

    public static void and50(byte operand0) {
        dstRegNum = InstrParser.selectReg(operand0, 1);
        srcRegNum = InstrParser.selectReg(operand0, 2);

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(srcRegNum);

        Main.AND.inputA = Main.regOutMuxA.output;
        Main.AND.inputB = Main.regOutMuxB.output;

        Main.AND.operate();
        Main.aluMux.selectInput(1);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();

    }

    public static void and51() {

    }

    public static void and52() {

    }

    public static void and53() {

    }

    public static void or60() {

    }

    public static void or61() {

    }

    public static void or62() {

    }

    public static void or63() {

    }

    public static void xor70() {

    }

    public static void xor71() {

    }

    public static void xor72() {

    }

    public static void xor73() {

    }
}
