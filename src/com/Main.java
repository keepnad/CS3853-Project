package com;

import com.instrHandling.InstrParser;
import com.muxes.Demux2to4;
import com.muxes.Mux;
import com.registers.Register;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
    public static AddSub adder;
    public static byte dataOutA, dataOutB;
    public static byte instrLineHigh, instrLineLow, memRead;
    public static byte[][] RAM;
    public static byte[] EPROM, I_O;
    public static boolean CLK, flagValues[];
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

        int i = 0, j = 0;
        Scanner input = new Scanner(System.in);
        Scanner programInput = null;
        boolean fileFound = false;
        String hexInstr;
        int instrLength;
        boolean parseSuccess;
        System.out.println("Input x to execute program in memory, input i to enter instructions manually");
        String execType = input.nextLine();

        switch (execType.toLowerCase()) {
            case "x":
                System.out.println("Enter name of program file: ");
                while(!fileFound) {
                    String inFilename = input.nextLine();
                    try {
                        programInput = new Scanner(new FileReader(inFilename));
                        fileFound = true;
                    } catch (FileNotFoundException e1) {
                        System.out.println("File not found, try again: ");
                    }
                }
                while(programInput.hasNext()){

                    RAM[i][j] = (byte) Integer.parseInt(programInput.next(), 16);

                    j++;
                    if(j == 4095){
                        j = 0;
                        i++;
                    }
                    if (i == 14){
                        MemoryHandling.memDump();
                        System.out.println("The file is that long? Huh...");
                        System.exit(0);
                    }
                }

                MemoryHandling.memDump();
                instrLength = InstrParser.getInstrLength(Main.RAM[0][0]);
                hexInstr = InstrParser.makeHexInstrString(instrLength, 0, 0);
                parseSuccess = InstrParser.parse(hexInstr);

                if(parseSuccess){
                    InstrParser.runInstruction();
                }
                else{
                    System.err.println("Bad instruction");
                    break;
                }

                System.out.printf("Register 0: 0x%02X\n", R0.getOutput());
                System.out.printf("Register 1: 0x%02X\n", R1.getOutput());
                System.out.printf("Register 2: 0x%02X\n", R2.getOutput());
                System.out.printf("Register 3: 0x%02X\n\n", R3.getOutput());
                System.out.println("Z: " + flagValues[0] + " N: " + flagValues[1] + " C: " + flagValues[2] + "\n");

                System.exit(0);
                //while(true){

                //}

            case "i":
                System.out.println("\nInput q to quit, input memdump to write RAM to file\n");
                while (true) {
                    System.out.print("Input hex instruction: ");
                    hexInstr = input.nextLine();
                    if (hexInstr.equals("q") || hexInstr.equals("Q")) {
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
                        System.out.printf("Register 3: 0x%02X\n\n", R3.getOutput());
                        System.out.println("Z: " + flagValues[0] + " N: " + flagValues[1] + " C: " + flagValues[2] + "\n");
                    }
                }
        }
    }
}
