package common;

import java.io.Serializable;

public interface GUI<D extends Data> extends Serializable {
	public void init(D data);
	public void show();
	//public Data getData() throws RemoteException;
}