package br.usp.icmc.so.simulador;

public class GlobalTime { //tempo global de simulacao

    private static float time = 0;

    protected static float getTime() {
        return time;
    }

    protected static void incrementTime(float time) {
        GlobalTime.time += time;
    }

    protected static void incTime() {
        GlobalTime.time += 1;
    }
}
