package concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentParam {

	ConcurrentMap<String, String> map;
	
	private volatile static ConcurrentParam instance;
	public static ConcurrentParam getInstance(){
		
		if(instance == null){
			synchronized(ConcurrentParam.class){
				if(instance == null){
					instance = new ConcurrentParam();
				}
			}
		}
		return instance;
		
	}
	
	private ConcurrentParam(){
		map = new ConcurrentHashMap<String, String>();
	}

	public ConcurrentMap<String, String> getMap() {
		return map;
	}

	public void setMap(ConcurrentMap<String, String> map) {
		this.map = map;
	}
	
	
}
