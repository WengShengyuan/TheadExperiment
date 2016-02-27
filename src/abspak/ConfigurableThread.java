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
	
	protected Integer tryCount = 0;
	protected boolean successFlag = false;
	protected Callable callable;
	
	public ConfigurableThread(Callable callable){
		this.callable = callable;
	}
	
	@Override
	public void run(){
		if(!firstExe){
			this.firstExe = true;
			this.firstExeTime = new Date();
		}
		
		try {
			
			while(execuble()){
				doRun();
				tryCount ++;
				Thread.sleep(this.timeInterval);
			}
			return;
			
		} catch (Exception e) {
			callable.onError(e);
		}
	}
	
	protected void doRun() throws Exception{
		throw new Exception("还未声明实现方法");
	}
	
	private boolean execuble(){
		if(enableTimeLimit && 
				(new Date().getTime() - this.firstExeTime.getTime()) > this.timeLimit){
			callable.onTimeout();
			return false;
		}
		
		if(enableExeLimit && 
				this.tryCount > this.exeLimit){
			callable.onExeout();
			return false;
		}
		
		if(successFlag){
			callable.onSuccess();
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
