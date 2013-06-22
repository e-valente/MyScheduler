/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.icmc.so.simulador;

/**
 *
 * @author emanuel
 */
abstract class MemoryManager {
    
    
    protected int totalmem;
   
    public abstract void manageDynamicPartition(Process p, MainMemory m);
    
}
