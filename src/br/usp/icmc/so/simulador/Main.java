package br.usp.icmc.so.simulador;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        Random randomGenerator = new Random();
        int simtype, totalmem, scheduleType, totalProcess;
        int memEachProcess, mmemtype, mmemarg1;




        System.out.println("===== Simulador de SO ===== \n");

        System.out.print("Tipos de simulações disponíveis:\n");
        System.out.print("1 - Padrão\n");
        System.out.print("2 - Personalizada\n");
        System.out.print("Escolha o tipo de simulacão:");
        simtype = in.nextInt();

        //simulacao padrao
        if (simtype == 1) {
            //em Mb
            totalmem = 256;
            mmemtype = 1;  //particao dinamica
            mmemarg1 = 1;


            SchedulerRoundRobin s = new SchedulerRoundRobin(mmemtype, mmemarg1, new MainMemory(totalmem), 4);
            totalProcess = 5;

            for (int i = 0; i < totalProcess; i++) {
                memEachProcess = randomGenerator.nextInt(totalmem / 10) + 1;
                System.out.println("Processo " + i + " requereu " + memEachProcess + " Mb");
                s.addProcess(new Process(i, (i+1) * 10, memEachProcess));
            }
            
            s.execute();


        }


        //simulacao personalizada
        if (simtype == 2) {

            System.out.print("Escolha o total de memória real(em MB) ex: 100 : ");
            totalmem = in.nextInt();

            System.out.println("Escalonadores disponíveis:\n");
            System.out.println("1 - FIFO ");
            System.out.println("2 - SJF (Short Job First)");
            System.out.println("3 - RR (Round Robin)");
            System.out.println("4 - RR com prioridade \n");
            System.out.print("Escolha o escalonador: ");

            scheduleType = in.nextInt();

            System.out.print("Escolha o total de processos: ");
            totalProcess = in.nextInt();

            mmemtype = 0;
            mmemarg1 = 0;
            //FIFO
            if (scheduleType == 1) {
                SchedulerFifo s = new SchedulerFifo(new MainMemory(totalmem));

                for (int i = 0; i < totalProcess; i++) {
                    memEachProcess = randomGenerator.nextInt(totalmem / 10);
                    System.out.println("Processo " + i + " requereu " + memEachProcess + " Mb");
                    s.addProcess(new Process(i, i * 10, memEachProcess));
                }


                s.execute();
            }


            //SJF
            if (scheduleType == 2) {
                SchedulerSJF s = new SchedulerSJF(new MainMemory(totalmem));

                for (int i = 0; i < totalProcess; i++) {
                    memEachProcess = randomGenerator.nextInt(totalmem / 10);
                    System.out.println("Processo " + i + " requereu " + memEachProcess + " Mb");
                    s.addProcess(new Process(i, i * 10, memEachProcess));
                }


                s.execute();
            }

            //RR
            if (scheduleType == 3) {
                SchedulerRoundRobin s = new SchedulerRoundRobin(mmemtype, mmemarg1,new MainMemory(totalmem), 4);

                for (int i = 0; i < totalProcess; i++) {
                    memEachProcess = randomGenerator.nextInt(totalmem / 10);
                    System.out.println("Processo " + i + " requereu " + memEachProcess + " Mb");
                    s.addProcess(new Process(i, i * 10, memEachProcess));
                }



                s.execute();
            }


            //RR com prioridade
            if (scheduleType == 4) {
                SchedulerRRPriority s = new SchedulerRRPriority(new MainMemory(totalmem), 4);

                for (int i = 0; i < totalProcess; i++) {
                    int priority = randomGenerator.nextInt(6);
                    memEachProcess = randomGenerator.nextInt(totalmem / 10);
                    System.out.println("Processo " + i + " requereu " + memEachProcess + " Mb");
                    s.addProcess(new Process(i, i * 10, memEachProcess), priority);
                }

                s.execute();
            }
        }

        if (simtype != 2 && simtype != 1) {
            System.out.println("\nOpcao inválida!");
        }



    }
}
