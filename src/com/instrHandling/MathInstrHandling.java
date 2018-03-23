package com.instrHandling;

import com.ClockTimer;
import com.Main;

public final class MathInstrHandling {

    static int dstRegNum, srcRegNum;

    public static void addc10(byte operand0) {
        dstRegNum = InstrParser.selectReg(operand0, 1);
        srcRegNum = InstrParser.selectReg(operand0, 2);

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(srcRegNum);

        Main.adder.inputA = Main.regOutMuxA.output;
        Main.adder.inputB = Main.regOutMuxB.output;

        Main.adder.operate();
        setFlags(Main.aluMux.inputs[0], Main.adder.inputA, Main.adder.inputB,"add");
        Main.aluMux.selectInput(0);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();

    }

    static void setFlags(byte result, byte operand0, byte operand1, String operation) {

        byte valueToFlags = (byte) 0x0;

        if (result == (byte) 0x0) {
            valueToFlags |= 0x08;
        }
        if (result <= (byte) 0xFF) {
            valueToFlags |= 0x02;
        }
        if (operation.equals("add")) {

            operand0 = (byte) (operand0 & 0x80);
            operand1 = (byte) (operand1 & 0x80);
            result = (byte) (result & 0x80);

            if (operand0 == (byte) 0x80 || operand1 == (byte) 0x80) {

                if (result == (byte) 0x00) {
                    valueToFlags |= 0x01;
                }
            }

        } else if (operation.equals("sub")) {

            operand0 = (byte) (operand0 & 0x80);
            operand1 = (byte) (operand1 & 0x80);
            result = (byte) (result & 0x80);

            if (operand0 == (byte) 0x00 || operand1 == (byte) 0x00) {

                if (result == (byte) 0x80) {
                    valueToFlags |= 0x01;
                }
            }

        }

        Main.FLAGS.setInput(valueToFlags);
        ClockTimer.waitForTick();

    }
}
