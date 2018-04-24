package com.instrHandling;

import com.ClockTimer;
import com.Main;

public final class JumpInstrHandling {
    public static void jmpB8(byte operand0) {
        int[] registers = InstrParser.decode16(operand0);
        //int srcRegHigh = InstrParser.selectReg(operand0, 1);
        //int srcRegLow = InstrParser.selectReg(operand0, 2);
        int srcRegHigh = registers[2];
        int srcRegLow = registers[3];

        Main.regOutMuxA.selectInput(srcRegLow);
        Main.regOutMuxB.selectInput(srcRegHigh);

        Main.ipMux.selectInput(0);
        Main.IP.inputLow = Main.ipMux.output;
        Main.IP.inputHigh = Main.ipMux.outputHigh;
        ClockTimer.waitForTick();

    }

    public static void jmpB9(byte operand0, byte operand1) {
        Main.instrLineHigh = operand0;
        Main.instrLineLow = operand1;
        Main.ipMux.selectInput(1);
        Main.IP.inputLow = Main.ipMux.output;
        Main.IP.inputHigh = Main.ipMux.outputHigh;
        ClockTimer.waitForTick();

    }

    public static void jloD6(byte operand0, byte operand1) {
        if (Main.flagValues[2]) {

            ifJump(operand0, operand1);

        } else {

            ifNoJump();

        }

    }

    public static void jhsD7(byte operand0, byte operand1) {
        if (!Main.flagValues[2]) {

            ifJump(operand0, operand1);

        } else {

            ifNoJump();

        }

    }

    public static void jeqD8(byte operand0, byte operand1) {
        if (Main.flagValues[0]) {
            ifJump(operand0, operand1);

        } else {

            ifNoJump();

        }

    }

    public static void jneD9(byte operand0, byte operand1) {
        if (!Main.flagValues[0]) {

            ifJump(operand0, operand1);

        } else {

            ifNoJump();

        }

    }

    public static void jmiDA(byte operand0, byte operand1) {
        if (Main.flagValues[1]) {
            ifJump(operand0, operand1);

        } else {
            ifNoJump();

        }

    }

    public static void jplDB(byte operand0, byte operand1) {
        if (!Main.flagValues[1]) {
            ifJump(operand0, operand1);

        } else {
            ifNoJump();

        }

    }

    private static void ifJump(byte operand0, byte operand1) {
        Main.relOffset = (operand0 << 8) | operand1;

        //System.out.println(Main.relOffset);

        Main.incInstr.operate();
        Main.relJump.operate();
        Main.ipMux.selectInput(3);

        Main.IP.inputLow = Main.ipMux.output;
        Main.IP.inputHigh = Main.ipMux.outputHigh;
        ClockTimer.waitForTick();

    }

    private static void ifNoJump() {
        Main.relOffset = 0;
        Main.incInstr.operate();
        Main.relJump.operate();
        Main.ipMux.selectInput(3);

        Main.IP.inputLow = Main.ipMux.output;
        Main.IP.inputHigh = Main.ipMux.outputHigh;
        ClockTimer.waitForTick();
    }
}
