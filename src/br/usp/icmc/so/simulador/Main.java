package br.usp.icmc.so.simulador;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        SchedulerRoundRobin s = new SchedulerRoundRobin(new MainMemory(100), 4);
        /*
         for (int i = 0; i < 2; i++) {
         if(i == 1) s.addProcess(new Process(i, 20, 8));
         else s.addProcess(new Process(i, 50, 8));
         }
         */
        s.addProcess(new Process(0, 10, 8));
        s.addProcess(new Process(1, 11, 8));
        s.addProcess(new Process(2, 5, 8));
        s.addProcess(new Process(3, 15, 8));
        s.addProcess(new Process(4, 10, 8));
        s.addProcess(new Process(5, 12, 8));
        s.execute();

    }
}
