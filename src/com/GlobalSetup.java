package com;

import com.muxes.Demux2to4;
import com.muxes.Mux2To1;
import com.muxes.Mux4To1;
import com.muxes.Mux8To1;
import com.registers.FlagsRegister;
import com.registers.GpRegister;
import com.registers.PointerRegister;

/**
 * GlobalSetup class:
 * Non-instanced
 */
public final class GlobalSetup {
    /**
     * Performs setup of all the global objects and variables
     */
    public static void initializeAll() {


        Main.CLK = false;

        Main.clockThread = new Thread(new ClockTimer());
        Main.clockThread.start();

        Main.RAM = new byte[14][4096];
        Main.EPROM = new byte[4096];
        Main.I_O = new byte[4096];

        Main.dataOutA = (byte) 0x0;
        Main.dataOutB = (byte) 0x0;
        Main.instrLineHigh = (byte) 0x0;
        Main.instrLineLow = (byte) 0x0;

        Main.AND = new LogicGate("AND", 101);
        Main.OR = new LogicGate("OR", 102);
        Main.XOR = new LogicGate("XOR", 103);
        Main.NOT = new LogicGate("NOT", 104);

        Main.R0 = new GpRegister(8, 10);
        Main.R1 = new GpRegister(8, 11);
        Main.R2 = new GpRegister(8, 12);
        Main.R3 = new GpRegister(8, 13);

        Main.SP = new PointerRegister(16, 14);
        Main.IP = new PointerRegister(16, 15);

        Main.FLAGS = new FlagsRegister(4, 110);
        Main.DST = Main.SRC = null;

        Main.ipMux = new Mux4To1(4, 115);
        Main.addyMux = new Mux4To1(4, 116);

        Main.regInMuxA = new Mux8To1(8, 118, 'A');
        Main.regInMuxB = new Mux8To1(8, 118, 'B');
        Main.regOutMuxA = new Mux8To1(7, 112);
        Main.regOutMuxB = new Mux8To1(7, 113);

        Main.aluMux = new Mux8To1(5, 111);
        Main.flagsMux = new Mux2To1(2, 120);
        Main.spMux = new Mux2To1(2, 117);
        Main.dataBusMux = new Mux4To1(4, 220);

        Main.regDeMux = new Demux2to4(114);
    }
}
