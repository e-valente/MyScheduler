/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.icmc.so.simulador;

import static br.usp.icmc.so.simulador.GlobalTime.getTime;
import static br.usp.icmc.so.simulador.GlobalTime.incrementTime;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author emanuel
 */
public class SchedulerRRPriority extends Scheduler {

    private List<Process> processesP5;
    private List<Process> processesP4;
    private List<Process> processesP3;
    private List<Process> processesP2;
    private List<Process> processesP1;
    private double quantum;

    public SchedulerRRPriority(MainMemory mem, double _quantum) throws IOException {
        processesP5 = new ArrayList<Process>();
        processesP4 = new ArrayList<Process>();
        processesP3 = new ArrayList<Process>();
        processesP2 = new ArrayList<Process>();
        processesP1 = new ArrayList<Process>();

        this.quantum = _quantum;
        this.mem = mem;

        mystatistics = new Statistics();

        //limpa o arquivo e escreve cabeçalho
        FileWriter fstream = new FileWriter("ReportRRPriority.txt");
        BufferedWriter out = new BufferedWriter(fstream);
        out.write("Estatística do Escalonador Round Robin com prioridade:\n");
        out.write("----------------------------------------------\n\n");
        out.close();


    }

    public void addProcess(Process p, int priority) {
        int pointer = mem.allocMemory(p.requiredMemory);
        p.setMemoryPointer(pointer);

        if (priority == 5) {
            processesP5.add(p);
        }
        if (priority == 4) {
            processesP4.add(p);
        }
        if (priority == 3) {
            processesP3.add(p);
        }
        if (priority == 2) {
            processesP2.add(p);
        }
        if (priority == 1) {
            processesP1.add(p);
        }

        if (priority > 5 && priority < 1) {
            System.out.println("\nEscalonador Round Robin com Prioridade: "
                    + "prioridade" + priority + "não existe!\n");
            System.exit(1);
        }

    }

    @Override
    public void addProcess(Process p) {
        System.out.println("\nEscalonador Round Robin com Prioridade: "
                + "processos devem ter prioridade!");
        System.exit(1);
    }

    @Override
    public void execute() {

        mystatistics.start_execution_time = getTime();

        Process p;
        System.out.println("\nExecutando escalonador Round Robin com prioridade...\n");
        //se a fila de processos 
        //com prioridade 5 nao está vazia, executamos!
        if (!processesP5.isEmpty()) {
            System.out.println("\nExecutando processos com prioridade 5...\n");
            Iterator<Process> iterator = processesP5.iterator();

            while (iterator.hasNext()) {
                p = iterator.next();

                if (quantum > p.totalExecutionTime) {
                    incrementTime(p.totalExecutionTime);
                } else {
                    incrementTime((float) quantum);
                }

                p.setTotalExecutionTime(p.totalExecutionTime - (float) quantum);

                if (p.totalExecutionTime <= 0) {
                    writeReport(p, 5);
                    System.out.println("Processo " + p.getPid() + " -> tempo de retorno:  " + getTime() + ".");
                    mem.freeMemory(p.getMemoryPointer(), p.getRequiredMemory());
                    iterator.remove();
                }


            }
        }

        //coloca o que restou da lista de prioridade 5 
        //na lista de prioridade 4
        if (!processesP5.isEmpty()) {
            Iterator<Process> iterator = processesP5.iterator();
            while (iterator.hasNext()) {
                p = iterator.next();
                processesP4.add(p);
            }
        }


        //executa RRPrioridade pra prioridade 4

        if (!processesP4.isEmpty()) {
            System.out.println("\nExecutando processos com prioridade 4...\n");
            Iterator<Process> iterator = processesP4.iterator();

            while (iterator.hasNext()) {
                p = iterator.next();

                if (quantum > p.totalExecutionTime) {
                    incrementTime(p.totalExecutionTime);
                } else {
                    incrementTime((float) quantum);
                }

                p.setTotalExecutionTime(p.totalExecutionTime - (float) quantum);

                if (p.totalExecutionTime <= 0) {
                    writeReport(p, 4);
                    System.out.println("Processo " + p.getPid() + " -> tempo de retorno:  " + getTime() + ".");
                    mem.freeMemory(p.getMemoryPointer(), p.getRequiredMemory());
                    iterator.remove();
                }


            }
        }


        //coloca o que restou da lista de prioridade 4
        //na lista de prioridade 3
        if (!processesP4.isEmpty()) {
            Iterator<Process> iterator = processesP4.iterator();
            while (iterator.hasNext()) {
                p = iterator.next();
                processesP3.add(p);
            }
        }


        //executa RRPrioridade pra prioridade 3
        if (!processesP3.isEmpty()) {
            System.out.println("\nExecutando processos com prioridade 3...\n");
            Iterator<Process> iterator = processesP4.iterator();

            while (iterator.hasNext()) {
                p = iterator.next();

                if (quantum > p.totalExecutionTime) {
                    incrementTime(p.totalExecutionTime);
                } else {
                    incrementTime((float) quantum);
                }

                p.setTotalExecutionTime(p.totalExecutionTime - (float) quantum);

                if (p.totalExecutionTime <= 0) {
                    writeReport(p, 3);
                    System.out.println("Processo " + p.getPid() + " -> tempo de retorno:  " + getTime() + ".");
                    mem.freeMemory(p.getMemoryPointer(), p.getRequiredMemory());
                    iterator.remove();
                }


            }
        }



        //coloca o que restou da lista de prioridade 3
        //na lista de prioridade 2
        if (!processesP3.isEmpty()) {
            Iterator<Process> iterator = processesP3.iterator();
            while (iterator.hasNext()) {
                p = iterator.next();
                processesP2.add(p);
            }
        }


        //executa RRPrioridade pra prioridade 2
        if (!processesP2.isEmpty()) {
            System.out.println("\nExecutando processos com prioridade 2...\n");
            Iterator<Process> iterator = processesP4.iterator();

            while (iterator.hasNext()) {
                p = iterator.next();

                if (quantum > p.totalExecutionTime) {
                    incrementTime(p.totalExecutionTime);
                } else {
                    incrementTime((float) quantum);
                }

                p.setTotalExecutionTime(p.totalExecutionTime - (float) quantum);

                if (p.totalExecutionTime <= 0) {
                    writeReport(p, 2);
                    System.out.println("Processo " + p.getPid() + " -> tempo de retorno:  " + getTime() + ".");
                    mem.freeMemory(p.getMemoryPointer(), p.getRequiredMemory());
                    iterator.remove();
                }


            }
        }


        //coloca o que restou da lista de prioridade 2
        //na lista de prioridade 1
        if (!processesP2.isEmpty()) {
            Iterator<Process> iterator = processesP3.iterator();
            while (iterator.hasNext()) {
                p = iterator.next();
                processesP1.add(p);
            }
        }



        //Prioridade 1: RR até terminar todos
        System.out.println("\nExecutando processos com prioridade 1...\n");
        Iterator<Process> iterator = processesP1.iterator();
        while (!processesP1.isEmpty()) {
            while (iterator.hasNext()) {
                p = iterator.next();

                if (quantum > p.totalExecutionTime) {
                    incrementTime(p.totalExecutionTime);
                } else {
                    incrementTime((float) quantum);
                }

                p.setTotalExecutionTime(p.totalExecutionTime - (float) quantum);

                if (p.totalExecutionTime <= 0) {
                    writeReport(p, 1);
                    System.out.println("Processo " + p.getPid() + " -> tempo de retorno:  " + getTime() + ".");
                    mem.freeMemory(p.getMemoryPointer(), p.getRequiredMemory());
                    iterator.remove();
                }



            }

            iterator = processesP1.iterator();
        }

        writeFootPrintReport();
       System.out.println("\nRelatório completo em ReportRRPriority.txt (no diretório raíz do projeto)\n");


    }

    public void writeReport(Process p, int priority) {


        mystatistics.total_processes++;
        mystatistics.total_return_time += getTime();


        try {
            // true -> concatena
            FileWriter fstream = new FileWriter("ReportRRPriority.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("Processo " + p.getPid() + ":\nTempo de retorno: " + getTime() + ".\n");
            out.write("Terminou na Fila de Prioridade:" + priority + " \n\n");
            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }


    }

    public void writeFootPrintReport() {

        mystatistics.end_execution_time = getTime();

        try {
            // true -> concatena
            FileWriter fstream = new FileWriter("ReportRRPriority.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("\n\nTotal de processos: " + mystatistics.total_processes);
            out.write("\nTempo Médio de retorno: " + mystatistics.averageReturn());
            out.write("\nThroughput: " + mystatistics.throughput());
            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }


    }
}
