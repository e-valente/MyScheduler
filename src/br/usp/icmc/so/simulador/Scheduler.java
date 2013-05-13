package br.usp.icmc.so.simulador;

import java.util.List;

public abstract class Scheduler extends GlobalTime {

    protected List<Process> processes;
    protected MainMemory mem;
    public Statistics mystatistics;
   

    public abstract void addProcess(Process p); 
    //public abstract void addProcess(Process p, int priority); 


    public abstract void execute();
    //recomenda-se criar outras politicas como: RR, SJF, etc
}
