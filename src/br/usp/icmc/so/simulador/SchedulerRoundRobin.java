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

/**
 *
 * @author emanuel
 */
public class SchedulerRoundRobin extends Scheduler {

    private double quantum;
    public int n;

    public SchedulerRoundRobin(int mmemtype, int mmemarg1, MainMemory mem, double _quantum) throws IOException {
        processes = new ArrayList<>();
        this.mem = mem;
        this.quantum = _quantum;
        
        //particao dinamica
        if(mmemtype == 1)
            mmd = new MemoryManagerDynamicPartition(mmemarg1, mem);

        mystatistics = new Statistics();

        //limpa o arquivo
        FileWriter fstream = new FileWriter("ReportRR.txt");
        BufferedWriter out = new BufferedWriter(fstream);
        out.write("Estatística do Escalonador Round Robin:\n");
        out.write("----------------------------------------------\n\n");
        out.close();

    }

    @Override
    public void addProcess(Process p) { //aloca o processo com a politica FIFO
        int pointer = mem.allocMemory(p.requiredMemory);
        p.setMemoryPointer(pointer);
        processes.add(p);
        mmd.manageDynamicPartition(p, mem);
    }

    @Override
    public void execute() { //executa o primeiro da fila

        Iterator<Process> iterator = processes.iterator();
        Process p;
        
        System.out.println("\nExecutando escalonador Round Robin...\n");

        mystatistics.start_execution_time = getTime();

        while (!processes.isEmpty()) {
            while (iterator.hasNext()) {
                p = iterator.next();

                if (quantum > p.totalExecutionTime) {
                    incrementTime(p.totalExecutionTime);
                } else {
                    incrementTime((float) quantum);
                }



                p.setTotalExecutionTime(p.totalExecutionTime - (float) quantum);

                if (p.totalExecutionTime <= 0) {
                    writeReport(p);
                    System.out.println("Processo " + p.getPid() + " -> tempo de retorno:  " + getTime() + ".");
                    mem.freeMemory(p.getMemoryPointer(), p.getRequiredMemory());
                    iterator.remove();
                }



            }

            iterator = processes.iterator();
        }

        writeFootPrintReport();

        System.out.println("\nRelatório completo em ReportRR.txt (no diretório raíz do projeto)\n");
                
    }

    public void writeReport(Process p) {


        mystatistics.total_processes++;
        mystatistics.total_return_time += getTime();


        try {
            // true -> concatena
            FileWriter fstream = new FileWriter("ReportRR.txt", true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("Processo " + p.getPid() + ":\nTempo de retorno: " + getTime() + ".\n\n");
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
            FileWriter fstream = new FileWriter("ReportRR.txt", true);
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
