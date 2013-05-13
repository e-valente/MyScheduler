/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.icmc.so.simulador;

import java.util.ArrayList;

/**
 *
 * @author emanuel
 */
public class SchedulerFifo extends Scheduler {

    public SchedulerFifo(MainMemory mem) {
        processes = new ArrayList<Process>();
        this.mem = mem;
    }

    @Override
    public void addProcess(Process p) { //aloca o processo com a politica FIFO
        int pointer = mem.allocMemory(p.requiredMemory);
        p.setMemoryPointer(pointer);
        processes.add(p);
    }

    @Override
    public void execute() { //executa o primeiro da fila
        for (Process p : processes) {
            System.out.println("Now is: " + getTime() + " executando " + p.pid);
            incrementTime(p.requiredExecutionTime);
            p.setTotalExecutionTime(getTime());
            mem.freeMemory(p.getMemoryPointer(), p.getRequiredMemory());
            System.out.println("Process " + p.getPid() + " leaving at " + getTime() + ".");
        }
    }
}
