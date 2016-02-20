package tasks;

import java.util.UUID;

import abspak.ConfigurableThread;

public class TaskOne extends ConfigurableThread{
	
	private int i = 0;
	private int id = 0;
	
	public TaskOne(int id){this.id = id;}
	
	@Override
	public void doRun(){
		System.out.println("线程 : " + this.id + " | 计算:"+ ++i);
		this.successFlag = true;
	}

}
