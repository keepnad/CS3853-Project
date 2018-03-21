package com;

import com.instrHandling.InstrParser;
import com.muxes.Demux2to4;
import com.muxes.Mux;
import com.registers.Register;

import java.util.Scanner;

/**
 * Main class:
 * *Defines all global objects
 */
public class Main {

    public static Register R0, R1, R2, R3, SP, IP, FLAGS, SRC, DST;
    public static LogicGate AND, OR, XOR, NOT;
    public static Mux ipMux, addyMux, regInMuxA, regOutMuxA, regInMuxB, regOutMuxB;
    public static Mux aluMux, flagsMux, spMux, dataBusMux;
    public static Demux2to4 regDeMux;
    public static byte dataOutA, dataOutB;
    public static byte instrLineHigh, instrLineLow, memRead;
    public static byte[][] RAM;
    public static byte[] EPROM, I_O;
    public static boolean CLK;
    public static Thread clockThread;

    /**
     * main method: calls initializeAll to set up all the globals
     * takes input as hex string from console, with or without spaces
     * Exit program or call memDump
     * calls InstrParser.parse on the hex string,
     * then runs the instruction, if parsing succeeded
     * print contents of all registers
     */
    public static void main(String[] args) {

        GlobalSetup.initializeAll();

        Scanner input = new Scanner(System.in);
        String hexInstr;
        boolean parseSuccess;

        System.out.println("\nInput q to quit, input memdump to write RAM to file\n");
        while (true) {
            System.out.print("Input hex instruction: ");
            hexInstr = input.nextLine();
            if (hexInstr.length() > 0 && (hexInstr.equals("q") || hexInstr.equals("Q"))) {
                System.out.println("\nQuit.");
                System.exit(0);
            } else if (hexInstr.toLowerCase().equals("memdump")) {
                MemoryHandling.memDump();
                System.out.println("\nMemory dumped to ramDump.txt\n");
                continue;
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
