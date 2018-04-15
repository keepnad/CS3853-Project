package com.instrHandling;

import com.ClockTimer;
import com.Main;
import com.MemoryHandling;
import com.instrHandling.InstrParser;

public final class MovInstrHandling {

    static StringBuilder binStrOperand0, binStrOperand1, binStrOpcode;
    static int srcRegNum, srcRegNumHigh, dstRegNum, dstRegNumHigh;

    public static void mov80(byte operand0) {

        dstRegNum = InstrParser.selectReg(operand0, 1);
        srcRegNum = InstrParser.selectReg(operand0, 2);

        Main.regOutMuxA.selectInput(srcRegNum);
        Main.regInMuxA.selectInput(0);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();
    }


    public static void mov81(byte operand0, byte operand1) {

        dstRegNum = InstrParser.selectReg(operand0,1);

        Main.instrLineLow = operand1;
        Main.regInMuxA.selectInput(2);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();
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
        srcRegNum = InstrParser.selectReg(operand2, 2);

        Main.regOutMuxA.selectInput(srcRegNum);
        Main.dataBusMux.selectInput(0);
        MemoryHandling.writeToMem(operand0, operand1, Main.dataBusMux.output);
        ClockTimer.waitForTick();

    }

    public static void mov88(byte operand0, byte operand1){
        dstRegNum = InstrParser.selectReg(operand0, 1);
        dstRegNumHigh = InstrParser.selectReg(operand0, 2);
        srcRegNum = InstrParser.selectReg(operand1, 1);
        srcRegNumHigh = InstrParser.selectReg(operand1, 2);

        //System.out.println("src = " + srcRegNum + " " + srcRegNumHigh);
        //System.out.println("dst = " + dstRegNum + " " + dstRegNumHigh);
        Main.regOutMuxA.selectInput(srcRegNum);
        Main.regOutMuxB.selectInput(srcRegNumHigh);
        Main.regInMuxA.selectInput(0);
        Main.regInMuxB.selectInput(0);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        Main.regDeMux.selectInput('B');
        Main.regDeMux.selectReg(dstRegNumHigh);

        ClockTimer.waitForTick();
    }

    public static void nopE0() {
        ClockTimer.waitForTick();

    }
}
