package com;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * MemoryHandling class:
 * * Non-instanced, static methods for use with RAM
 */
public final class MemoryHandling {

    public static void writeToMem(byte addyHigh, byte addyLow, byte value) {

        int chipNumber = addyHigh >>> 4;
        chipNumber = chipNumber & 0xF;
        int offset = (((addyHigh & 0xFF) * 0x100) + (addyLow & 0xFF)) % 0x1000;
        offset = offset & 0xFFF;

        if (chipNumber > 13 || offset > 4095) {
            System.err.println("Bad memory value in writeToMem");
            return;
        }

        Main.RAM[chipNumber][offset] = value;

        //System.out.printf("\nwritten to ram: %02X", Main.RAM[chipNumber][offset]);

    }

    public static void readFromMem(byte addyHigh, byte addyLow) {

        int chipNumber = addyHigh >>> 4;
        chipNumber = chipNumber & 0xF;
        int offset = (((addyHigh & 0xFF) * 0x100) + (addyLow & 0xFF)) % 0x1000;
        offset = offset & 0xFFF;

        if (chipNumber > 13 || offset > 4095) {
            System.err.println("Bad memory offset in readFromMem");
            return;
        }

        Main.memRead = Main.RAM[chipNumber][offset];

    }

    public static void memDump() {
        PrintWriter writer;

        try {
            writer = new PrintWriter("ramDump.txt");
        } catch (IOException e1) {
            System.out.println("\nError creating dump file.\n");
            return;
        }

        writer.println("Address\t\tVal");

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 4096; j++) {
                writer.printf("0x%X%03X\t\t%04X\n", i, j, Main.RAM[i][j]);
            }
        }

        writer.flush();
        writer.close();
    }
}
