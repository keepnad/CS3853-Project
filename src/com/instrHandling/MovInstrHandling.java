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

    public static void mov88(byte operand0){

        int [] registers;
        registers = InstrParser.decode16(operand0);
        //System.out.println("operand: " + operand0);
        //System.out.println("destH: " + registers[0] + " destL: " + registers[1]);
        //System.out.println("srcH: " + registers[2] + " srcL: " + registers[3]);
        Main.regOutMuxA.selectInput(registers[2]);
        Main.regOutMuxB.selectInput(registers[3]);
        Main.regInMuxA.selectInput(0);
        Main.regInMuxB.selectInput(0);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(registers[0]);
        Main.regDeMux.selectInput('B');
        Main.regDeMux.selectReg(registers[1]);

        ClockTimer.waitForTick();
    }

    public static void nopE0() {
        ClockTimer.waitForTick();

    }
}
