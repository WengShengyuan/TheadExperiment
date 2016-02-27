package abspak;

public class SysoutCallable implements Callable {

	@Override
	public void onError(Exception e) {
		System.out.println("Error:"+e);
	}

	@Override
	public void onSuccess() {
		System.out.println("success!");
	}

	@Override
	public void onTimeout() {
		System.out.println("timeout, thread exit!");
	}

	@Override
	public void onExeout() {
		System.out.println("exeout, thread exit!");

	}

}
