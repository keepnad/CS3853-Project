package com;

public final class InstrParser {
    static long instruction = 0;
    static byte opcode = 0;
    static byte operand0 = 0;
    static byte operand1 = 0;
    static byte operand2 = 0;

    public static boolean parse(String hexInstr) {
        try {
            instruction = Long.parseLong(hexInstr, 16);
        } catch (NumberFormatException e) {
            System.out.println("\nBad input, try again.\n");
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
        switch (opcode) {
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
            default:
                System.out.println("\nUnrecognized instruction, try again.\n");
                break;
        }
    }
}
