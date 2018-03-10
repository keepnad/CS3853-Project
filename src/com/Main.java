package com;

import java.util.Scanner;

public class Main {

    static Register R0, R1, R2, R3, SP, IP, FLAGS, SRC, DST;
    static Mux ipMux, addyMux, regInMuxA, regOutMuxA, regInMuxB, regOutMuxB;
    static Mux aluMux, flagsMux, spMux, dataBusMux;
    static Demux2to4 regDeMux;
    static byte dataOutA, dataOutB;
    static byte instrLineHigh, instrLineLow;
    static byte[][] RAM;
    static byte[] EPROM, I_O;
    static boolean CLK;
    static Thread clockThread;

    public static void main(String[] args) {

        GlobalSetup.initializeAll();

        Scanner input = new Scanner(System.in);
        String hexInstr;
        boolean parseSuccess;

        while (true) {
            System.out.print("Input hex instruction (or q to quit): ");
            hexInstr = input.nextLine();
            if (hexInstr.length() > 0 && (hexInstr.charAt(0) == 'q' || hexInstr.charAt(0) == 'Q')) {
                System.out.println("\nQuit.");
                System.exit(0);
            }
            hexInstr = hexInstr.replaceAll("\\s", "");

            parseSuccess = InstrParser.parse(hexInstr);
            if (parseSuccess) {
                InstrParser.runInstruction();

                System.out.printf("Register 0: 0x%02X\n", R0.getOutput());
                System.out.printf("Register 1: 0x%02X\n", R1.getOutput());
                System.out.printf("Register 2: 0x%02X\n", R2.getOutput());
                System.out.printf("Register 3: 0x%02X\n", R3.getOutput());
            }
        }
    }
}
