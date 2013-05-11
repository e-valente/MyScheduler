package br.usp.icmc.so.simulador;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scheduler s = new Scheduler(new MainMemory(100));
		
		for (int i = 0; i < 10; i++) { // criação de processos
			s.addProcess(new Process(i, 8, 8));
		}
		
		s.execute();
		
	}

}
