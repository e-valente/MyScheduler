package br.usp.icmc.so.simulador;

import java.io.IOException;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {

        //exemplo RR (memoria, tamanho_quantum)
        /*
        SchedulerRoundRobin s = new SchedulerRoundRobin(new MainMemory(100), 4);
        s.addProcess(new Process(0, 10, 8));
        s.addProcess(new Process(1, 11, 8));
        s.addProcess(new Process(2, 5, 8));
        s.addProcess(new Process(3, 15, 8));
        s.addProcess(new Process(4, 10, 8));
        s.addProcess(new Process(5, 12, 8));
        s.execute();
        * */
        
        //exemplo FIFO
        
        
        SchedulerFifo s = new SchedulerFifo(new MainMemory(100));
        s.addProcess(new Process(0, 10, 8));
        s.addProcess(new Process(1, 11, 8));
        s.addProcess(new Process(2, 5, 8));
        s.addProcess(new Process(3, 15, 8));
        s.addProcess(new Process(4, 10, 8));
        s.addProcess(new Process(5, 12, 8));
        s.execute();
        

    }
}
