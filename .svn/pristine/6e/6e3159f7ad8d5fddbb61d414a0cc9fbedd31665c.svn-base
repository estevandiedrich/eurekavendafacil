package br.com.eurekasoftwares.tablet;

import android.app.Application;
import br.com.eurekasoftwares.tablet.datamanager.DataManager;

public class EurekaVendaFacilLibraryApp extends Application {
	
	private DataManager dataManager;
	
	@Override
	public void onCreate() {
		super.onCreate();
		setDataManager(new DataManager(this));
	}
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	
	public DataManager getDataManager() {
		return dataManager;
	}
	
	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}
}
