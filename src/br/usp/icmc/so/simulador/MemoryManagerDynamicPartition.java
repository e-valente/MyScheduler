/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.icmc.so.simulador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author emanuel
 */
public class MemoryManagerDynamicPartition extends MemoryManager {

    private List<Integer> processPartitions;
    private List<Integer> freePartitions;
    private List<Integer> orderOfAllocations;
    
    private int algorithm;
    
    public MemoryManagerDynamicPartition(int alg)
    {
        algorithm = alg;
        
    }

    @Override
    public void manageDynamicPartition(Process p, MainMemory m) {
        processPartitions = new ArrayList<>();
        freePartitions = new ArrayList<>();
        orderOfAllocations = new ArrayList<>();
        
        totalmem = m.getSize();
        createProcessPartitions(p);
        createFreePartitions(p);
        
        //First Fit
        if(algorithm == 1)
        {
            System.out.println("executando First Fit...");
        }
        
        //Next Fit
        if(algorithm == 2)
        {
            System.out.println("executando Next Fit...");
        }

        
        //Best Fit
        if(algorithm == 3)
        {
            System.out.println("executando Best Fit...");
        }
        
        //Worst Fit
        if(algorithm == 4)
        {
            System.out.println("executando Worst Fit...");
        }




    }

    private void createProcessPartitions(Process p) {
        Integer sizeofpartition;
        int totalprocessmem = p.requiredMemory;
        Random randomGenerator = new Random();
        int i;

        //cada particao terá em media de 3 a 7% do total
        //de memoria requerido para o processo   
        i = 0;
        while (totalprocessmem > 7) {
            sizeofpartition = new Integer(randomGenerator.nextInt(5) + 3);
            processPartitions.add(i, sizeofpartition);
            totalprocessmem = totalprocessmem - sizeofpartition;
            i++;
        }

        //ultima particao é a que faltou
        System.out.println("Ainda faltou " + totalprocessmem + " megas ");
        processPartitions.add(i, totalprocessmem);
        //System.out.println("Imprimindo a lista:" + processPartitions);

    }

    private void createFreePartitions(Process p) {
        Integer sizeofpartition;
        int totalprocessmem = p.requiredMemory;
        Random randomGenerator = new Random();
        int i, remainingmem;
        
        remainingmem = totalmem - p.requiredMemory;

        //cada particao terá em media de 3 a 7% do total
        //de memoria requerido para o processo   
        i = 0;
        while (remainingmem > 10) {
            sizeofpartition = new Integer(randomGenerator.nextInt(11));
            freePartitions.add(i, sizeofpartition);
            remainingmem = remainingmem - sizeofpartition;
            i++;
        }

        //ultima particao é a que faltou
       // System.out.println("espacoes livres: Ainda faltou " + remainingmem + " megas ");
        freePartitions.add(i, remainingmem);
        //System.out.println("Imprimindo a lista de espacoes livres:" + freePartitions);

    }
}
