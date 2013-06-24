/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.icmc.so.simulador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author emanuel
 */
public class MemoryManagerDynamicPartition extends MemoryManager {

    private List<Integer> processPartitions;
    private List<Integer> processPartitionsbk;
    private List<Integer> freePartitions;
    private List<Integer> freePartitionsbk;
    private List<Integer> orderOfAllocations;
    private int algorithm, totalallocs;

    public MemoryManagerDynamicPartition(int alg, MainMemory m) {
        algorithm = alg;
        totalmemkb = m.getSize() * 1024;

    }

    @Override
    public void manageDynamicPartition(Process p, MainMemory m) {
        processPartitions = new ArrayList<>();
        freePartitions = new ArrayList<>();
        orderOfAllocations = new ArrayList<>();

        totalmemkb = m.getSize() * 1024;
        createProcessPartitions(p);
        createFreePartitions(p);

        //First Fit
        if (algorithm == 1) {
            Integer mypartition, myfreepartition;
            boolean match, continue_partitions;

            System.out.println("\n\nEstatísticas do Processo " + p.pid);
            System.out.println("Algoritmo: First Fit");
            System.out.println("Total de memória requerido:" + p.requiredMemory + " MB");

            Iterator<Integer> itpp = processPartitions.iterator();
            Iterator<Integer> itfp = freePartitions.iterator();
            totalallocs = 0;



            continue_partitions = true;

            while (itpp.hasNext() && continue_partitions) {
                mypartition = itpp.next();
                match = false;

                while (itfp.hasNext() && !match && !freePartitions.isEmpty()) {
                    myfreepartition = itfp.next();
                    // System.out.println("=>coparando atual " + mypartition+ " com livre " + myfreepartition);
                    if (myfreepartition >= mypartition) {
                        match = true;

                        itpp.remove();
                        itfp.remove();
                        itfp = freePartitions.iterator();
                        //comeco da lista
                        //itfp = freePartitions.iterator();
                        System.out.println("=>Alocando particao de espaço " + mypartition + "KB com particao livre de "
                                + myfreepartition + "KB");
                        totalallocs++;
                        //System.out.println("Imprimindo particoes restantes: " + processPartitions);

                    } else {
                        if (itfp.hasNext()) {
                            myfreepartition = itfp.next();
                        }
                    }

                }

                if (!match) {
                    System.out.println("AVISO: Não foi possível alocar partições para este processo: "
                            + "faltaram partições livres!");
                    continue_partitions = false;


                }

            }
            System.out.println("=>Total de alocações: " + totalallocs);
            processPartitions.addAll(processPartitionsbk);
            freePartitions.addAll(freePartitionsbk);

        }

        algorithm = 2;

        //Worst Fit
        if (algorithm == 2) {
            Collections.sort(freePartitions);
            Collections.reverse(freePartitions);

            Integer mypartition, myfreepartition;
            boolean match, continue_partitions;

            System.out.println("\n\nEstatísticas do Processo " + p.pid);
            System.out.println("Algoritmo: Worst Fit");
            System.out.println("Total de memória requerido:" + p.requiredMemory + " MB");

            Iterator<Integer> itpp = processPartitions.iterator();
            Iterator<Integer> itfp = freePartitions.iterator();


            totalallocs = 0;

            continue_partitions = true;

            while (itpp.hasNext() && continue_partitions) {
                mypartition = itpp.next();
                match = false;

                while (itfp.hasNext() && !match && !freePartitions.isEmpty()) {
                    myfreepartition = itfp.next();
                    // System.out.println("=>coparando atual " + mypartition+ " com livre " + myfreepartition);
                    if (myfreepartition >= mypartition) {
                        match = true;

                        itpp.remove();
                         itfp.remove();
                        System.out.println("=>Alocando particao de espaço " + mypartition + "KB com particao livre de "
                                + myfreepartition + "KB");
                        totalallocs++;
                        //System.out.println("Imprimindo particoes restantes: " + processPartitions);

                    } else {
                        if (itfp.hasNext()) {
                            myfreepartition = itfp.next();
                        }
                    }

                }

                if (!match) {
                    System.out.println("AVISO: Não foi possível alocar partições para este processo: "
                            + "faltaram partições livres!");
                    continue_partitions = false;


                }

            }

            System.out.println("=>Total de alocações: " + totalallocs);
            processPartitions.addAll(processPartitionsbk);
            freePartitions.addAll(freePartitionsbk);




        }

        algorithm = 3;

        //Best Fit
        if (algorithm == 3) {
            //ordena as duas listas
            Collections.sort(freePartitions);
            Collections.sort(processPartitions);

            Integer mypartition, myfreepartition;
            boolean match, continue_partitions;

            System.out.println("\n\nEstatísticas do Processo " + p.pid);
            System.out.println("Algoritmo: Best Fit");
            System.out.println("Total de memória requerido:" + p.requiredMemory + " MB");

            Iterator<Integer> itpp = processPartitions.iterator();
            Iterator<Integer> itfp = freePartitions.iterator();


            totalallocs = 0;

            continue_partitions = true;

            while (itpp.hasNext() && continue_partitions) {
                mypartition = itpp.next();
                match = false;

                while (itfp.hasNext() && !match && !freePartitions.isEmpty()) {
                    myfreepartition = itfp.next();
                    // System.out.println("=>coparando atual " + mypartition+ " com livre " + myfreepartition);
                    if (myfreepartition >= mypartition) {
                        match = true;

                        itpp.remove();
                         itfp.remove();
                        System.out.println("=>Alocando particao de espaço " + mypartition + "KB com particao livre de "
                                + myfreepartition + "KB");
                        totalallocs++;
                        //System.out.println("Imprimindo particoes restantes: " + processPartitions);

                    } else {
                        if (itfp.hasNext()) {
                            myfreepartition = itfp.next();
                        }
                    }

                }

                if (!match) {
                    System.out.println("AVISO: Não foi possível alocar partições para este processo: "
                            + "faltaram partições livres!");
                    continue_partitions = false;


                }

            }

            System.out.println("=>Total de alocações: " + totalallocs);
            processPartitions.addAll(processPartitionsbk);
            freePartitions.addAll(freePartitionsbk);

        }

        algorithm = 4;

        //Next Fit
        if (algorithm == 4) {
            Integer mypartition, myfreepartition;
            boolean match, continue_partitions;

            System.out.println("\n\nEstatísticas do Processo " + p.pid);
            System.out.println("Algoritmo: Next Fit");
            System.out.println("Total de memória requerido:" + p.requiredMemory + " MB");

            Iterator<Integer> itpp = processPartitions.iterator();
            Iterator<Integer> itfp = freePartitions.iterator();
            totalallocs = 0;



            continue_partitions = true;

            while (itpp.hasNext() && continue_partitions) {
                mypartition = itpp.next();
                match = false;

                while (itfp.hasNext() && !match && !freePartitions.isEmpty()) {
                    myfreepartition = itfp.next();
                    // System.out.println("=>coparando atual " + mypartition+ " com livre " + myfreepartition);
                    if (myfreepartition >= mypartition) {
                        match = true;

                        itpp.remove();
                         itfp.remove();
                        System.out.println("=>Alocando particao de espaço " + mypartition + "KB com particao livre de "
                                + myfreepartition + "KB");
                        totalallocs++;
                        //System.out.println("Imprimindo particoes restantes: " + processPartitions);

                    } else {
                        if (itfp.hasNext()) {
                            myfreepartition = itfp.next();
                        }
                    }

                }

                if (!match) {
                    System.out.println("AVISO: Não foi possível alocar partições para este processo: "
                            + "faltaram partições livres!");
                    continue_partitions = false;


                }

            }
            System.out.println("=>Total de alocações: " + totalallocs);
            processPartitions.addAll(processPartitionsbk);
            freePartitions.addAll(freePartitionsbk);

        }




    }

    private void createProcessPartitions(Process p) {
        Integer sizeofpartition;
        int totalprocessmem = p.requiredMemory * 1024;
        Random randomGenerator = new Random();
        int i;

        i = 0;
        while (totalprocessmem > 1024) {
            sizeofpartition = new Integer(randomGenerator.nextInt(1024));
            processPartitions.add(i, sizeofpartition);
            totalprocessmem = totalprocessmem - sizeofpartition;
            i++;
        }

        //ultima particao é a que faltou
        // System.out.println("Ainda faltou " + totalprocessmem + " KB ");
        processPartitions.add(i, totalprocessmem);
        //  System.out.println("Imprimindo a lista:" + processPartitions);


        processPartitionsbk = new ArrayList<>(processPartitions);
    }

    private void createFreePartitions(Process p) {
        Integer sizeofpartition;
        int totalprocessmemkb = p.requiredMemory * 1024;
        Random randomGenerator = new Random();
        int i, remainingmemkb;

        remainingmemkb = totalmemkb - totalprocessmemkb;


       
        i = 0;
        while (remainingmemkb > 1536) {
            sizeofpartition = new Integer(randomGenerator.nextInt(1536));
            freePartitions.add(i, sizeofpartition);
            remainingmemkb = remainingmemkb - sizeofpartition;
            i++;
        }

        //ultima particao é a que faltou
        // System.out.println("espacoes livres: Ainda faltou " + remainingmem + " megas ");
        freePartitions.add(i, remainingmemkb);
        //System.out.println("Imprimindo a lista de espacoes livres:" + freePartitions);
        freePartitionsbk = new ArrayList<>(freePartitions);

    }
}
