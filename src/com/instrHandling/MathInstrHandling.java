package com.instrHandling;

import com.ClockTimer;
import com.Main;
import com.MemoryHandling;

public final class MathInstrHandling {

    static int dstRegNum, srcRegNum;

    public static void addc10(byte operand0) {
        dstRegNum = InstrParser.selectReg(operand0, 1);
        srcRegNum = InstrParser.selectReg(operand0, 2);

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(srcRegNum);

        Main.adder.inputA = Main.regOutMuxA.output;
        Main.adder.inputB = Main.regOutMuxB.output;

        Main.adder.carryIn = (byte) 0x00;
        Main.adder.operate();
        setFlags(Main.aluMux.inputs[0], Main.adder.inputA, Main.adder.inputB,"add");
        Main.aluMux.selectInput(0);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();

    }

    public static void addc11(byte operand0, byte operand1){
        dstRegNum = InstrParser.selectReg(operand0, 1);

        Main.instrLineLow = operand1;

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(6);

        Main.adder.inputA = Main.regOutMuxA.output;
        Main.adder.inputB = Main.regOutMuxB.output;

        Main.adder.carryIn = (byte) 0x00;
        Main.adder.operate();
        setFlags(Main.aluMux.inputs[0], Main.adder.inputA, Main.adder.inputB,"add");
        Main.aluMux.selectInput(0);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();
    }

    public static void addc12(byte operand0, byte operand1, byte operand2){
        dstRegNum = InstrParser.selectReg(operand0, 1);

        MemoryHandling.readFromMem(operand1, operand2);

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(4);

        Main.adder.inputA = Main.regOutMuxA.output;
        Main.adder.inputB = Main.regOutMuxB.output;

        Main.adder.carryIn = (byte) 0x00;
        Main.adder.operate();
        setFlags(Main.aluMux.inputs[0], Main.adder.inputA, Main.adder.inputB,"add");
        Main.aluMux.selectInput(0);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();

    }

    public static void addc13(byte operand0, byte operand1, byte operand2){
        srcRegNum = InstrParser.selectReg(operand2, 2);

        MemoryHandling.readFromMem(operand0, operand1);

        Main.regOutMuxA.selectInput(srcRegNum);
        Main.regOutMuxB.selectInput(4);

        Main.adder.inputA = Main.regOutMuxA.output;
        Main.adder.inputB = Main.regOutMuxB.output;

        Main.adder.carryIn = (byte) 0x00;
        Main.adder.operate();
        setFlags(Main.aluMux.inputs[0], Main.adder.inputA, Main.adder.inputB,"add");
        Main.aluMux.selectInput(0);
        Main.dataBusMux.selectInput(2);

        MemoryHandling.writeToMem(operand0, operand1, Main.dataBusMux.output);
        ClockTimer.waitForTick();

    }

    public static void subb20(byte operand0){
        dstRegNum = InstrParser.selectReg(operand0, 1);
        srcRegNum = InstrParser.selectReg(operand0, 2);

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(srcRegNum);

        Main.adder.inputA = Main.regOutMuxA.output;
        Main.adder.inputB = Main.regOutMuxB.output;

        Main.adder.carryIn = (byte) 0x01;
        Main.adder.operate();
        setFlags(Main.aluMux.inputs[0], Main.adder.inputA, Main.adder.inputB,"sub");
        Main.aluMux.selectInput(0);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();
    }

    public static void subb21(byte operand0, byte operand1){
        dstRegNum = InstrParser.selectReg(operand0, 1);

        Main.instrLineLow = operand1;

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(6);

        Main.adder.inputA = Main.regOutMuxA.output;
        Main.adder.inputB = Main.regOutMuxB.output;

        Main.adder.carryIn = (byte) 0x01;
        Main.adder.operate();
        setFlags(Main.aluMux.inputs[0], Main.adder.inputA, Main.adder.inputB,"sub");
        Main.aluMux.selectInput(0);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();
    }

    public static void subb22(byte operand0, byte operand1, byte operand2){
        dstRegNum = InstrParser.selectReg(operand0, 1);

        MemoryHandling.readFromMem(operand1, operand2);

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(4);

        Main.adder.inputA = Main.regOutMuxA.output;
        Main.adder.inputB = Main.regOutMuxB.output;

        Main.adder.carryIn = (byte) 0x01;
        Main.adder.operate();
        setFlags(Main.aluMux.inputs[0], Main.adder.inputA, Main.adder.inputB,"sub");
        Main.aluMux.selectInput(0);
        Main.regInMuxA.selectInput(3);
        Main.regDeMux.selectInput('A');
        Main.regDeMux.selectReg(dstRegNum);
        ClockTimer.waitForTick();
    }

    public static void subb23(byte operand0, byte operand1, byte operand2){
        srcRegNum = InstrParser.selectReg(operand2, 2);

        MemoryHandling.readFromMem(operand0, operand1);

        Main.regOutMuxA.selectInput(srcRegNum);
        Main.regOutMuxB.selectInput(4);

        Main.adder.inputA = Main.regOutMuxA.output;
        Main.adder.inputB = Main.regOutMuxB.output;

        Main.adder.carryIn = (byte) 0x01;
        Main.adder.operate();
        setFlags(Main.aluMux.inputs[0], Main.adder.inputA, Main.adder.inputB,"sub");
        Main.aluMux.selectInput(0);
        Main.dataBusMux.selectInput(2);

        MemoryHandling.writeToMem(operand0, operand1, Main.dataBusMux.output);
        ClockTimer.waitForTick();
    }

    public static void cmp30(byte operand0){
        dstRegNum = InstrParser.selectReg(operand0, 1);
        srcRegNum = InstrParser.selectReg(operand0, 2);

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(srcRegNum);

        Main.adder.inputA = Main.regOutMuxA.output;
        Main.adder.inputB = Main.regOutMuxB.output;

        Main.adder.carryIn = (byte) 0x01;
        Main.adder.operate();
        setFlags(Main.aluMux.inputs[0], Main.adder.inputA, Main.adder.inputB,"sub");
        ClockTimer.waitForTick();
    }

    public static void cmp31(byte operand0, byte operand1){
        dstRegNum = InstrParser.selectReg(operand0, 1);

        Main.instrLineLow = operand1;

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(6);

        Main.adder.inputA = Main.regOutMuxA.output;
        Main.adder.inputB = Main.regOutMuxB.output;

        Main.adder.carryIn = (byte) 0x01;
        Main.adder.operate();
        setFlags(Main.aluMux.inputs[0], Main.adder.inputA, Main.adder.inputB,"sub");
        ClockTimer.waitForTick();
    }

    public static void cmp32(byte operand0, byte operand1, byte operand2){
        dstRegNum = InstrParser.selectReg(operand0, 1);

        MemoryHandling.readFromMem(operand1, operand2);

        Main.regOutMuxA.selectInput(dstRegNum);
        Main.regOutMuxB.selectInput(4);

        Main.adder.inputA = Main.regOutMuxA.output;
        Main.adder.inputB = Main.regOutMuxB.output;

        Main.adder.carryIn = (byte) 0x01;
        Main.adder.operate();
        setFlags(Main.aluMux.inputs[0], Main.adder.inputA, Main.adder.inputB,"sub");
        ClockTimer.waitForTick();
    }

    public static void cmp33(byte operand0, byte operand1, byte operand2){
        srcRegNum = InstrParser.selectReg(operand2, 2);

        MemoryHandling.readFromMem(operand0, operand1);

        Main.regOutMuxA.selectInput(srcRegNum);
        Main.regOutMuxB.selectInput(4);

        Main.adder.inputA = Main.regOutMuxA.output;
        Main.adder.inputB = Main.regOutMuxB.output;

        Main.adder.carryIn = (byte) 0x01;
        Main.adder.operate();
        setFlags(Main.aluMux.inputs[0], Main.adder.inputA, Main.adder.inputB,"sub");
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
