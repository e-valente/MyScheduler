package br.usp.icmc.so.simulador;

import java.util.ArrayList;
import java.util.List;

public abstract class Scheduler extends GlobalTime {

    protected List<Process> processes;
    protected MainMemory mem;

    public abstract void addProcess(Process p); //aloca o processo com a politica FIFO

    public abstract void execute();
    //recomenda-se criar outras politicas como: RR, SJF, etc
}
