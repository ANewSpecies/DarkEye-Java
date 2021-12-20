package org.spray.darkeye.managers;

public abstract class MoveManager implements IManager {
	
	private String path;
	
	public MoveManager(String path) {
		this.path = path;
	}

	@Override
	public void run() {
		
	}

	@Override
	public abstract String getPath();

}
