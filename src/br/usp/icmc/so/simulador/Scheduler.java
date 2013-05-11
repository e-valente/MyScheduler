package br.usp.icmc.so.simulador;

import java.util.ArrayList;
import java.util.List;

public class Scheduler extends GlobalTime {
	
	private List<Process> processes;
	private MainMemory mem;
	
	public Scheduler(MainMemory mem) {
		processes = new ArrayList<Process>();
		this.mem = mem;
	}
	
	public void addProcess(Process p) { //aloca o processo com a política FIFO
		int pointer = mem.allocMemory(p.requiredMemory);
		p.setMemoryPointer(pointer);
		processes.add(p);
	}

	public void execute() { //executa o primeiro da fila
		for (Process p : processes) {
			System.out.println("Now is: " + getTime());
			incrementTime(p.requiredExecutionTime);
			p.setTotalExecutionTime(getTime());
			mem.freeMemory(p.getMemoryPointer(), p.getRequiredMemory());
			System.out.println("Process " + p.getPid() + " leaving at " + getTime() + ".");
		}
	}
	
	//recomenda-se criar outras políticas como: RR, SJF, etc

}
