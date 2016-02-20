package abspak;

import java.util.Date;

public class ConfigurableThread extends Thread {

	private boolean firstExe = false;
	private boolean enableTimeLimit = false;
	private boolean enableExeLimit = false;
	private Date firstExeTime;
	private Long timeInterval = 1000L;

	private Integer exeLimit = 0;
	private Long timeLimit = 0L;
	
	private Integer tryCount = 0;
	protected boolean successFlag = false; 
	
	@Override
	public void run(){
		if(!firstExe){
			this.firstExe = true;
			this.firstExeTime = new Date();
		}
		
		try {
			
			while(execuble()){
//				System.out.println("第 " + this.tryCount + " 次尝试");
				doRun();
				tryCount ++;
				Thread.currentThread().sleep(this.timeInterval);
			}
			
			System.out.println("循环结束，退出");
			return;
			
		} catch (Exception e) {
			System.out.println("doRun 出现异常 : "+ e);
		}
	}
	
	public void doRun() throws Exception{
		throw new Exception("还未声明实现方法");
	}
	
	private boolean execuble(){
		if(enableTimeLimit && 
				(new Date().getTime() - this.firstExeTime.getTime()) > this.timeLimit){
			System.out.println("执行时间超时，线程结束");
			return false;
		}
		
		if(enableExeLimit && 
				this.tryCount > this.exeLimit){
			System.out.println("尝试次数超出，线程结束");
			return false;
		}
		
		if(successFlag){
			System.out.println("任务执行成功，线程结束");
			return false;
		}
		
		return true;
	}
	
	public final ConfigurableThread enableTimeLimit() {
		this.enableTimeLimit = true;
		return this;
	}
	
	public final ConfigurableThread disableTimeLimit() {
		this.enableTimeLimit = false;
		return this;
	}
	
	public final ConfigurableThread enableExeLimit() {
		this.enableExeLimit = true;
		return this;
	}
	
	public final ConfigurableThread disableExeLimit() {
		this.enableExeLimit = false;
		return this;
	}

	public final ConfigurableThread setExeLimit(Integer exeLimit) {
		this.exeLimit = exeLimit;
		return this;
	}


	public final ConfigurableThread setTimeLimit(Long timeLimit) {
		this.timeLimit = timeLimit;
		return this;
	}
	
	public final ConfigurableThread setInterval(Long interval) {
		this.timeInterval = interval;
		return this;
	}

}