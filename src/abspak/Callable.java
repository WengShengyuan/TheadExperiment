package abspak;

public interface Callable {
	
	void onError(Exception e);
	void onSuccess();
	void onTimeout();
	void onExeout();

}
