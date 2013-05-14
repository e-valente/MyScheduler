package br.usp.icmc.so.simulador;

import java.io.IOException;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {

        /* 1 - FIFO
         * 2 - SJF
         * 3 - RR
         * 4 - RR com Prioridade
         */

        int opcao = 1;


        //FIFO
        if (opcao == 1) {
            SchedulerFifo s = new SchedulerFifo(new MainMemory(100));
            s.addProcess(new Process(0, 10, 8));
            s.addProcess(new Process(1, 11, 8));
            s.addProcess(new Process(2, 5, 8));
            s.addProcess(new Process(3, 15, 8));
            s.addProcess(new Process(4, 10, 8));
            s.addProcess(new Process(5, 12, 8));
            s.execute();
        }


        //SJF
        if (opcao == 2) {
            SchedulerSJF s = new SchedulerSJF(new MainMemory(100));
            s.addProcess(new Process(0, 10, 8));
            s.addProcess(new Process(1, 11, 8));
            s.addProcess(new Process(2, 5, 8));
            s.addProcess(new Process(3, 15, 8));
            s.addProcess(new Process(4, 20, 8));
            s.addProcess(new Process(5, 12, 8));
            s.execute();
        }

        //RR
        if (opcao == 3) {
            SchedulerRoundRobin s = new SchedulerRoundRobin(new MainMemory(100), 4);
            s.addProcess(new Process(0, 10, 8));
            s.addProcess(new Process(1, 11, 8));
            s.addProcess(new Process(2, 5, 8));
            s.addProcess(new Process(3, 15, 8));
            s.addProcess(new Process(4, 10, 8));
            s.addProcess(new Process(5, 12, 8));
            s.execute();
        }


        //RR com prioridade
        if (opcao == 4) {
            SchedulerRRPriority s = new SchedulerRRPriority(new MainMemory(100), 4);
            s.addProcess(new Process(0, 100, 8), 5);
            s.addProcess(new Process(1, 11, 8), 4);
            s.addProcess(new Process(2, 5, 8), 5);
            s.addProcess(new Process(3, 15, 8), 4);
            s.addProcess(new Process(4, 10, 8), 4);
            s.addProcess(new Process(5, 12, 8), 3);
            s.addProcess(new Process(6, 15, 8), 3);
            s.addProcess(new Process(7, 50, 8), 2);
            s.addProcess(new Process(8, 20, 8), 1);
            s.execute();
        }


    }
}
