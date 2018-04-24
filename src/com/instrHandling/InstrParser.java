package com.instrHandling;

import com.Main;

public final class InstrParser {
    static long instruction = 0;
    static byte opcode = 0;
    static byte operand0 = 0;
    static byte operand1 = 0;
    static byte operand2 = 0;
    static StringBuilder binStrOperand;
    static int regNum;

    public static boolean parse(String hexInstr) {
        try {
            instruction = Long.parseLong(hexInstr, 16);
        } catch (NumberFormatException e) {
            //System.out.println("\nBad input, try again. " +hexInstr + "\n");
            return false;
        }
        if (hexInstr.length() == 4) {
            opcode = (byte) ((instruction >> 8) & 0xFF);
            operand0 = (byte) (instruction & 0xFF);
        } else if (hexInstr.length() == 6) {
            opcode = (byte) ((instruction >> 16) & 0xFF);
            operand0 = (byte) ((instruction >> 8) & 0xFF);
            operand1 = (byte) (instruction & 0xFF);
        } else if (hexInstr.length() == 8) {
            opcode = (byte) ((instruction >> 24) & 0xFF);
            operand0 = (byte) ((instruction >> 16) & 0xFF);
            operand1 = (byte) ((instruction >> 8) & 0xFF);
            operand2 = (byte) (instruction & 0xFF);
        }
        return true;
    }

    public static void runInstruction() {

        if (opcode >= (byte) 0xD6 && opcode <= (byte) 0xDB) {
            Main.incInstr.operate();
            Main.relJump.operate();
            Main.ipMux.selectInput(3);
        } else {
            Main.incInstr.operate();
            Main.ipMux.selectInput(2);
        }
        Main.IP.inputLow = Main.ipMux.output;
        Main.IP.inputHigh = Main.ipMux.outputHigh;

        switch (opcode) {
            //ADD instructions
            case (byte) 0x10:
                MathInstrHandling.addc10(operand0);
                break;
            case (byte) 0x11:
                MathInstrHandling.addc11(operand0, operand1);
                break;
            case (byte) 0x12:
                MathInstrHandling.addc12(operand0, operand1, operand2);
                break;
            case (byte) 0x13:
                MathInstrHandling.addc13(operand0, operand1, operand2);
                break;

            //SUB instructions
            case (byte) 0x20:
                MathInstrHandling.subb20(operand0);
                break;
            case (byte) 0x21:
                MathInstrHandling.subb21(operand0, operand1);
                break;
            case (byte) 0x22:
                MathInstrHandling.subb22(operand0, operand1, operand2);
                break;
            case (byte) 0x23:
                MathInstrHandling.subb23(operand0, operand1, operand2);
                break;

            //CMP instructions
            case (byte) 0x30:
                MathInstrHandling.cmp30(operand0);
                break;
            case (byte) 0x31:
                MathInstrHandling.cmp31(operand0, operand1);
                break;
            case (byte) 0x32:
                MathInstrHandling.cmp32(operand0, operand1, operand2);
                break;
            case (byte) 0x33:
                MathInstrHandling.cmp33(operand0, operand1, operand2);
                break;

            //NOT instructions
            case (byte) 0x40:
                LogicInstrHandling.not40(operand0);
                break;
            case (byte) 0x43:
                LogicInstrHandling.not43(operand0, operand1);
                break;

            //AND instructions
            case (byte) 0x50:
                LogicInstrHandling.and50(operand0);
                break;
            case (byte) 0x51:
                LogicInstrHandling.and51(operand0, operand1);
                break;
            case (byte) 0x52:
                LogicInstrHandling.and52(operand0, operand1, operand2);
                break;
            case (byte) 0x53:
                LogicInstrHandling.and53(operand0, operand1, operand2);
                break;

            //OR instructions
            case (byte) 0x60:
                LogicInstrHandling.or60(operand0);
                break;
            case (byte) 0x61:
                LogicInstrHandling.or61(operand0, operand1);
                break;
            case (byte) 0x62:
                LogicInstrHandling.or62(operand0, operand1, operand2);
                break;
            case (byte) 0x63:
                LogicInstrHandling.or63(operand0, operand1, operand2);
                break;

            //XOR instructions
            case (byte) 0x70:
                LogicInstrHandling.xor70(operand0);
                break;
            case (byte) 0x71:
                LogicInstrHandling.xor71(operand0, operand1);
                break;
            case (byte) 0x72:
                LogicInstrHandling.xor72(operand0, operand1, operand2);
                break;
            case (byte) 0x73:
                LogicInstrHandling.xor73(operand0, operand1, operand2);
                break;

            //MOV instructions
            case (byte) 0x80:
                MovInstrHandling.mov80(operand0);
                break;
            case (byte) 0x81:
                MovInstrHandling.mov81(operand0, operand1);
                break;
            case (byte) 0x82:
                MovInstrHandling.mov82(operand0, operand1, operand2);
                break;
            case (byte) 0x83:
                MovInstrHandling.mov83(operand0, operand1, operand2);
                break;
            case (byte) 0x88:
                MovInstrHandling.mov88(operand0);

            //JMP instructions
            case (byte) 0xB8:
                JumpInstrHandling.jmpB8(operand0);
                break;
            case (byte) 0xB9:
                JumpInstrHandling.jmpB9(operand0, operand1);
                break;
            case (byte) 0xD6:
                JumpInstrHandling.jloD6(operand0, operand1);
                break;
            case (byte) 0xD7:
                JumpInstrHandling.jhsD7(operand0, operand1);
                break;
            case (byte) 0xD8:
                JumpInstrHandling.jeqD8(operand0, operand1);
                break;
            case (byte) 0xD9:
                JumpInstrHandling.jneD9(operand0, operand1);
                break;
            case (byte) 0xDA:
                JumpInstrHandling.jmiDA(operand0, operand1);
                break;
            case (byte) 0xDB:
                JumpInstrHandling.jplDB(operand0, operand1);
                break;

            //NOP instruction
            case (byte) 0xE0:
                MovInstrHandling.nopE0();
                break;

            default:
                System.out.println("\nUnrecognized instruction, try again.\n");
                break;
        }
    }

    public static int selectReg(byte operand, int nibToDecode) {
        binStrOperand = new StringBuilder(Integer.toBinaryString(operand));

        while (binStrOperand.length() < 8) {
            binStrOperand.insert(0, '0');
        }

        while (binStrOperand.length() > 8) {
            binStrOperand.deleteCharAt(0);
        }
        if (nibToDecode == 1) {

            switch (binStrOperand.substring(0, 2)) {
                case "00":
                    //Main.DST = Main.R0;
                    regNum = 0;
                    break;
                case "01":
                    //Main.DST = Main.R1;
                    regNum = 1;
                    break;
                case "10":
                    //Main.DST = Main.R2;
                    regNum = 2;
                    break;
                case "11":
                    //Main.DST = Main.R3;
                    regNum = 3;
                    break;
                default:
                    System.err.println("Invalid value in operand: " + binStrOperand.substring(0, 2));
            }
        } else if (nibToDecode == 2) {
            switch (binStrOperand.substring(4, 6)) {
                case "00":
                    //Main.SRC = Main.R0;
                    regNum = 0;
                    break;
                case "01":
                    //Main.SRC = Main.R1;
                    regNum = 1;
                    break;
                case "10":
                    //Main.SRC = Main.R2;
                    regNum = 2;
                    break;
                case "11":
                    //Main.SRC = Main.R3;
                    regNum = 3;
                    break;
                default:
                    System.err.println("Invalid value in operand: " + binStrOperand.substring(4, 6));
            }

        } else {
            System.out.println("Error in selectReg. Probably bad value for nibToDecode.");
            System.exit(1);
        }

        return regNum;
    }

    public static int[] decode16(byte operand){
        int [] registers = new int[4];
        StringBuilder binaryString = new StringBuilder(Integer.toBinaryString(operand));

        //System.out.println("Prefix: " + binaryString);
        while (binaryString.length() < 8) {
            binaryString.insert(0, '0');
        }
        //System.out.println("Postfix:" + binaryString);
        // Assign High Destination Register
        switch(binaryString.substring(0, 2)) {
            case "00":
                registers[0] = 0;
                break;
            case "01":
                registers[0] = 1;
                break;
            case "10":
                registers[0] = 2;
                break;
            case "11":
                registers[0] = 3;
        }

        // Assign Low Destination Register
        switch(binaryString.substring(2, 4)){
            case "00":
                registers[1] = 0;
                break;
            case "01":
                registers[1] = 1;
                break;
            case "10":
                registers[1] = 2;
                break;
            case "11":
                registers[1] = 3;
                break;
        }

        // Assign High Source Register
        switch(binaryString.substring(4, 6)){
            case "00":
                registers[2] = 0;
                break;
            case "01":
                registers[2] = 1;
                break;
            case "10":
                registers[2] = 2;
                break;
            case "11":
                registers[2] = 3;
                break;
        }

        // Assign Low Source Register
        switch(binaryString.substring(6)){
            case "00":
                registers[3] = 0;
                break;
            case "01":
                registers[3] = 1;
                break;
            case "10":
                registers[3] = 2;
                break;
            case "11":
                registers[3] = 3;
                break;
        }

        return registers;
    }


    public static int getInstrLength(byte opcode) {
        int instrLength = 0;

        switch (opcode) {
            case (byte) 0xE0:
                instrLength = 1;
                break;
            case (byte) 0x10:
            case (byte) 0x20:
            case (byte) 0x30:
            case (byte) 0x40:
            case (byte) 0x50:
            case (byte) 0x60:
            case (byte) 0x70:
            case (byte) 0x80:
            case (byte) 0xB8:
                instrLength = 2;
                break;
            case (byte) 0x11:
            case (byte) 0x21:
            case (byte) 0x31:
            case (byte) 0x43:
            case (byte) 0x51:
            case (byte) 0x61:
            case (byte) 0x71:
            case (byte) 0x81:
            case (byte) 0xB9:
            case (byte) 0xD6:
            case (byte) 0xD7:
            case (byte) 0xD8:
            case (byte) 0xD9:
            case (byte) 0xDA:
            case (byte) 0xDB:
                instrLength = 3;
                break;
            case (byte) 0x12:
            case (byte) 0x22:
            case (byte) 0x32:
            case (byte) 0x52:
            case (byte) 0x62:
            case (byte) 0x72:
            case (byte) 0x82:
            case (byte) 0x13:
            case (byte) 0x23:
            case (byte) 0x33:
            case (byte) 0x53:
            case (byte) 0x63:
            case (byte) 0x73:
            case (byte) 0x83:
                instrLength = 4;
                break;
        }

        return instrLength;
    }

    public static String makeHexInstrString(int length, int chipNum, int offset) {
        String hexInstr = "";

        for (int i = 0; i < length; i++) {
            hexInstr += String.format("%02X", Main.Memory[chipNum][offset]);
            offset++;
            if (offset == 4096) {
                offset = 0;
                chipNum++;
            }
            if (chipNum >= 16 || chipNum == 14) {
                System.err.println("Chipnum out of range 0 - 15, or is 14");
                System.exit(-1);
            }
        }

        return hexInstr;
    }
}
