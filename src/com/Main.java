package com;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static register R0, R1, R2, R3, SP, IP, FLAGS, SRC, DST;
    static mux ipMux, addyMux, regInMuxA, regOutMuxA, regInMuxB, regOutMuxB;
    static mux aluMux, flagsMux, spMux, dataBusMux;
    static demux2to4 regDeMux;
    static byte dataOutA, dataOutB;
    static byte instrLineHigh, instrLineLow;
    static byte[][] RAM;
    static byte[] EPROM, I_O;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String hexInstr = "";
        StringBuilder binStrOperand0, binStrOperand1, binStrOpcode;
        int srcRegNum, dstRegNum;
        long instruction = 0;
        byte opcode = 0;
        byte operand0 = 0;
        byte operand1 = 0;
        RAM = new byte[14][4096];
        EPROM = new byte[4096];
        I_O = new byte[4096];

        dataOutA = (byte) 0x0;
        dataOutB = (byte) 0x0;
        instrLineHigh = (byte) 0x0;
        instrLineLow = (byte) 0x0;


        R0 = new gpRegister(8, 10);
        R1 = new gpRegister(8, 11);
        R2 = new gpRegister(8, 12);
        R3 = new gpRegister(8, 13);

        SP = new pointerRegister(16, 14);
        IP = new pointerRegister(16, 15);

        FLAGS = new flagsRegister(4, 110);
        DST = SRC = null;

        ipMux = new mux4to1(4, 115);
        addyMux = new mux4to1(4, 116);

        regInMuxA = new mux8to1(8, 118, 'A');
        regInMuxB = new mux8to1(8, 118, 'B');
        regOutMuxA = new mux8to1(7, 112);
        regOutMuxB = new mux8to1(7, 113);

        aluMux = new mux8to1(5, 111);
        flagsMux = new mux2to1(2, 120);
        spMux = new mux2to1(2, 117);
        dataBusMux = new mux4to1(4, 220);

        regDeMux = new demux2to4(114);


        while (true) {
            System.out.print("Input hex instruction: ");

            try {
                instruction = input.nextLong(16);
                hexInstr = Long.toHexString(instruction);
            } catch (InputMismatchException e) {
                System.err.println("Bad input, exiting...");
                System.exit(1);
            }
            if (hexInstr.length() == 4) {
                opcode = (byte) ((instruction >> 8) & 0xFF);
                operand0 = (byte) (instruction & 0xFF);
            } else if (hexInstr.length() == 6) {
                opcode = (byte) ((instruction >> 16) & 0xFF);
                operand0 = (byte) ((instruction >> 8) & 0xFF);
                operand1 = (byte) (instruction & 0xFF);
            }

            if (opcode == (byte) 0x80) {
                binStrOperand0 = new StringBuilder(Integer.toBinaryString(operand0));

                while (binStrOperand0.length() < 8) {
                    binStrOperand0.insert(0, '0');
                }

                while (binStrOperand0.length() > 8) {
                    binStrOperand0.deleteCharAt(0);
                }

                switch (binStrOperand0.substring(0, 2)) {
                    case "00":
                        DST = R0;
                        dstRegNum = 0;
                        break;
                    case "01":
                        DST = R1;
                        dstRegNum = 1;
                        break;
                    case "10":
                        DST = R2;
                        dstRegNum = 2;
                        break;
                    case "11":
                        DST = R3;
                        dstRegNum = 3;
                        break;
                    default:
                        System.err.println("Invalid value in operand: " + binStrOperand0.substring(0, 2));
                        continue;
                }

                switch (binStrOperand0.substring(4, 6)) {
                    case "00":
                        SRC = R0;
                        srcRegNum = 0;
                        break;
                    case "01":
                        SRC = R1;
                        srcRegNum = 1;
                        break;
                    case "10":
                        SRC = R2;
                        srcRegNum = 2;
                        break;
                    case "11":
                        SRC = R3;
                        srcRegNum = 3;
                        break;
                    default:
                        System.err.println("Invalid value in operand: " + binStrOperand0.substring(4, 6));
                        continue;
                }

                SRC.setValue(SRC.getValue());
                //System.err.println("value using SRC pointer: " + SRC.getValue());
                regOutMuxA.selectInput(srcRegNum);
                //System.err.println("value from regOutMuxA: " + regOutMuxA.output);
                //System.err.println("value from dataOutA: " + dataOutA);
                regInMuxA.selectInput(0);
                //System.err.println("value from regInMuxA: " + regInMuxA.output);
                regDeMux.selectInput('A');
                //System.err.println("destination reg number: " + dstRegNum);
                regDeMux.selectReg(dstRegNum);


                //DST.setValue(SRC.getValue());

            }

            if (opcode == (byte) 0x81) {

                binStrOperand0 = new StringBuilder(Integer.toBinaryString(operand0));

                while (binStrOperand0.length() < 8) {
                    binStrOperand0.insert(0, '0');
                }
                while (binStrOperand0.length() > 8) {
                    binStrOperand0.deleteCharAt(0);
                }

                switch (binStrOperand0.substring(0, 2)) {
                    case "00":
                        DST = R0;
                        dstRegNum = 0;
                        break;
                    case "01":
                        DST = R1;
                        dstRegNum = 1;
                        break;
                    case "10":
                        DST = R2;
                        dstRegNum = 2;
                        break;
                    case "11":
                        DST = R3;
                        dstRegNum = 3;
                        break;
                    default:
                        System.err.println("Invalid value in operand: " + binStrOperand0.substring(0, 2));
                        continue;
                }

                instrLineLow = operand1;
                regInMuxA.selectInput(2);
                regDeMux.selectInput('A');
                regDeMux.selectReg(dstRegNum);


                //  DST.setValue(operand1);
            }

            System.out.printf("Register 0: 0x%02X\n", R0.getValue());
            System.out.printf("Register 1: 0x%02X\n", R1.getValue());
            System.out.printf("Register 2: 0x%02X\n", R2.getValue());
            System.out.printf("Register 3: 0x%02X\n", R3.getValue());
        }
    }
}
