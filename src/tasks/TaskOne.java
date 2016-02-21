package tasks;

import abspak.ConfigurableThread;

public class TaskOne extends ConfigurableThread {

	private int i = 0;
	private int id = 0;

	public TaskOne(int id) {
		super();
		this.id = id;
	}

	@Override
	public void doRun() {
		System.out.println("线程 : " + id + " | 计算:" + ++i + " | 第 : " + tryCount + " 次计算。");
	}

}
