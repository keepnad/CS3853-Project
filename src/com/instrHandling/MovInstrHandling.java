package com.instrHandling;

import com.ClockTimer;
import com.Main;
import com.MemoryHandling;
import com.instrHandling.InstrParser;

public final class MovInstrHandling {

    static StringBuilder binStrOperand0, binStrOperand1, binStrOpcode;
    static int srcRegNum, dstRegNum;

    public static void mov80(byte operand0) {

        dstRegNum = InstrParser.selectReg(operand0, 1);

        srcRegNum = InstrParser.selectReg(operand0, 2);

        //Main.SRC.setInput(Main.SRC.getOutput());
        //System.err.println("value using SRC pointer: " + SRC.getOutput());
        Main.regOutMuxA.selectInput(srcRegNum);
        //System.err.println("value from regOutMuxA: " + regOutMuxA.output);
        //System.err.println("value from dataOutA: " + dataOutA);
        Main.regInMuxA.selectInput(0);
        //System.err.println("value from regInMuxA: " + regInMuxA.output);
        Main.regDeMux.selectInput('A');
        //System.err.println("destination reg number: " + dstRegNum);
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();


        //DST.setValue(SRC.getOutput());

    }


    public static void mov81(byte operand0, byte operand1) {

        dstRegNum = InstrParser.selectReg(operand0,1);

        Main.instrLineLow = operand1;
        Main.regInMuxA.selectInput(2);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();


        //  DST.setValue(operand1);

    }

    public static void mov82(byte operand0, byte operand1, byte operand2) {
        dstRegNum = InstrParser.selectReg(operand0, 1);

        MemoryHandling.readFromMem(operand1, operand2);
        Main.regInMuxA.selectInput(5);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();

    }

    public static void mov83(byte operand0, byte operand1, byte operand2) {
        srcRegNum = InstrParser.selectReg(operand2, 1);

        Main.regOutMuxA.selectInput(srcRegNum);
        Main.dataBusMux.selectInput(0);
        MemoryHandling.writeToMem(operand0, operand1, Main.dataBusMux.output);
        ClockTimer.waitForTick();

    }

    public static void nopE0() {
        ClockTimer.waitForTick();

    }
}
