package com;

public class clockTimer implements Runnable {

    public void run() {

        //long timer = 0;

        while (true) {

            try {
                Thread.sleep(250);
            } catch (InterruptedException e1) {
            }
            //timer++;
            //System.out.println("timer = " + timer);
            Main.CLK = !Main.CLK;
            //System.out.println(Main.CLK);

        }
    }
}
