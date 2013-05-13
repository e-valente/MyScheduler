package br.usp.icmc.so.simulador;

public class Process {

    public int pid;
    public float requiredExecutionTime; // tamanho medido em unidades de tempo
    public float totalExecutionTime; //tempo que o processo leva pra executar desde que ele entra na fila
    public int requiredMemory; //quantidade de memoria necessaria para alocar o processo
    public int memoryPointer;

    public Process(int pid, int requiredExecutionTime, int requiredMemory) {
        this.pid = pid;
        this.requiredExecutionTime = requiredExecutionTime;
        this.requiredMemory = requiredMemory;

        this.totalExecutionTime = requiredExecutionTime;
        this.memoryPointer = -1;

    }

    public int getMemoryPointer() {
        return memoryPointer;
    }

    public void setMemoryPointer(int memoryPointer) {
        this.memoryPointer = memoryPointer;
    }

    public int getPid() {
        return pid;
    }

    public float getRequiredExecutionTime() {
        return requiredExecutionTime;
    }

    public void setRequiredExecutionTime(float requiredExecutionTime) {
        this.requiredExecutionTime = requiredExecutionTime;
    }

    public float getTotalExecutionTime() {
        return totalExecutionTime;
    }

    public void setTotalExecutionTime(float totalExecutionTime) {
        this.totalExecutionTime = totalExecutionTime;
    }

    public int getRequiredMemory() {
        return requiredMemory;
    }

    public void setRequiredMemory(int requiredMemory) {
        this.requiredMemory = requiredMemory;
    }
}
