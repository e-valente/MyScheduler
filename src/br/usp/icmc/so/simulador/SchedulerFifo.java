/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.icmc.so.simulador;

import static br.usp.icmc.so.simulador.GlobalTime.getTime;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author emanuel
 */
public class SchedulerFifo extends Scheduler {

    public SchedulerFifo(int mmemtype, int mmemarg1, MainMemory mem) throws IOException {
        processes = new ArrayList<Process>();
        this.mem = mem;
        
         //particao dinamica
        if(mmemtype == 1)
            mmd = new MemoryManagerDynamicPartition(mmemarg1, mem);


        mystatistics = new Statistics();

        //limpa o arquivo
        FileWriter fstream = new FileWriter("ReportFIFO.txt");
        BufferedWriter out = new BufferedWriter(fstream);
        out.write("Estatística do Escalonador FIFO:\n");
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

        System.out.println("\nExecutando escalonador em FIFO ...\n");

        for (Process p : processes) {

            incrementTime(p.requiredExecutionTime);
            p.setTotalExecutionTime(getTime());
            writeReport(p);
            mem.freeMemory(p.getMemoryPointer(), p.getRequiredMemory());
            System.out.println("Processo " + p.getPid() + " -> tempo de retorno:  " + getTime() + ".");
        }

        writeFootPrintReport();
        System.out.println("\nRelatório completo em ReportFIFO.txt (no diretório raíz do projeto)\n");

    }

    public void writeReport(Process p) {


        mystatistics.total_processes++;
        mystatistics.total_return_time += getTime();


        try {
            // true -> concatena
            FileWriter fstream = new FileWriter("ReportFIFO.txt", true);
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
            FileWriter fstream = new FileWriter("ReportFIFO.txt", true);
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
