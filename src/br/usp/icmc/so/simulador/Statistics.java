/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.icmc.so.simulador;

import java.util.List;

/**
 *
 * @author emanuel
 */
public class Statistics {
    
     public List<Process> processes;
     public int total_processes;
     public float start_execution_time;
     public float end_execution_time;
     public float total_return_time;
     
     public Statistics (){
         total_processes = 0;
         start_execution_time = 0;
         end_execution_time = 0;
         total_return_time = 0;
         
    
    }
     
    public float averageReturn()
    {
        return total_return_time / total_processes;
        
    }
    
    public float throughput()
    {
        return total_processes / (end_execution_time -
                start_execution_time);
        
    }
}
