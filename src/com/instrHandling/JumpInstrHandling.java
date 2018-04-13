package com.instrHandling;

import com.ClockTimer;
import com.Main;

import java.time.Clock;

public final class JumpInstrHandling {
    public static void jmpB8(byte operand0){
        int srcRegHigh = InstrParser.selectReg(operand0, 1);
        int srcRegLow = InstrParser.selectReg(operand0, 2);

        Main.regOutMuxA.selectInput(srcRegLow);
        Main.regOutMuxB.selectInput(srcRegHigh);

        Main.ipMux.selectInput(0);
        Main.IP.inputLow = Main.ipMux.output;
        Main.IP.inputHigh = Main.ipMux.outputHigh;
        ClockTimer.waitForTick();

    }

    public static void jmpB9(byte operand0, byte operand1){
        Main.instrLineHigh = operand0;
        Main.instrLineLow = operand1;
        Main.ipMux.selectInput(1);
        Main.IP.inputLow = Main.ipMux.output;
        Main.IP.inputHigh = Main.ipMux.outputHigh;
        ClockTimer.waitForTick();

    }

    public static void jloD6(byte operand0, byte operand1){

    }

    public static void jhsD7(byte operand0, byte operand1){

    }

    public static void jeqD8(byte operand0, byte operand1){

    }

    public static void jneD9(byte operand0, byte operand1){

    }

    public static void jmiDA(byte operand0, byte operand1){

    }

    public static void jplDB(byte operand0, byte operand1){

    }
}
