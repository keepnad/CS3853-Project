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

    public static void and51(byte operand0, byte operand1) {
        dstRegNum = InstrParser.selectReg(operand0, 1);

        Main.instrLineLow = operand1;

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(6);

        Main.AND.inputA = Main.regOutMuxA.output;
        Main.AND.inputB = Main.regOutMuxB.output;

        Main.AND.operate();
        Main.aluMux.selectInput(1);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();


    }

    public static void and52() {

    }

    public static void and53() {

    }

    public static void or60(byte operand0) {
        dstRegNum = InstrParser.selectReg(operand0, 1);
        srcRegNum = InstrParser.selectReg(operand0, 2);

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(srcRegNum);

        Main.OR.inputA = Main.regOutMuxA.output;
        Main.OR.inputB = Main.regOutMuxB.output;

        Main.OR.operate();
        Main.aluMux.selectInput(2);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();

    }

    public static void or61(byte operand0, byte operand1) {
        dstRegNum = InstrParser.selectReg(operand0, 1);

        Main.instrLineLow = operand1;

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(6);

        Main.OR.inputA = Main.regOutMuxA.output;
        Main.OR.inputB = Main.regOutMuxB.output;

        Main.OR.operate();
        Main.aluMux.selectInput(2);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();

    }

    public static void or62() {

    }

    public static void or63() {

    }

    public static void xor70(byte operand0) {
        dstRegNum = InstrParser.selectReg(operand0, 1);
        srcRegNum = InstrParser.selectReg(operand0, 2);

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(srcRegNum);

        Main.XOR.inputA = Main.regOutMuxA.output;
        Main.XOR.inputB = Main.regOutMuxB.output;

        Main.XOR.operate();
        Main.aluMux.selectInput(3);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();

    }

    public static void xor71(byte operand0, byte operand1) {
        dstRegNum = InstrParser.selectReg(operand0, 1);

        Main.instrLineLow = operand1;

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(6);

        Main.XOR.inputA = Main.regOutMuxA.output;
        Main.XOR.inputB = Main.regOutMuxB.output;

        Main.XOR.operate();
        Main.aluMux.selectInput(3);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();

    }

    public static void xor72() {

    }

    public static void xor73() {

    }
}
