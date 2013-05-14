/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.icmc.so.simulador;

import java.util.Comparator;

/**
 *
 * @author emanuel
 */
public class TimeProcessComparator implements Comparator<Process> {

    @Override
    public int compare(Process p1, Process p2) {
        
        //se p1 exigir menos tempo que p2 retorna 1
        //p2 é menor -> retorna 1
        if(p1.requiredExecutionTime > p2.requiredExecutionTime) return 1;
        else return -1;
        
    }
    
}
