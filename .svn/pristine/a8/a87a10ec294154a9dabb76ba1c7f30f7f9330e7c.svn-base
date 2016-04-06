package br.com.eurekasoftwares.tablet.cliente;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.adapter.ClienteAdapter;
import br.com.eurekasoftwares.tablet.constantes.Constantes;
import br.com.eurekasoftwares.tablet.vo.CustomerVO;

public class SolicitaClientesListAsyncTask extends
		AsyncTask<String, Void, List<CustomerVO>> {

	public SolicitaClientesListAsyncTask(Activity activity,ClienteAdapter adapter)
	{
		this.activity = activity;
		this.adapter = adapter;
	}
	
    private ProgressDialog dialog;
    private Activity activity;
    private ClienteAdapter adapter;
    private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
    @Override
    protected void onPreExecute() {
    	Application app = activity.getApplication();
    	this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)app;
    	this.dialog = ProgressDialog.show(activity, "Aguarde", "Carregando...",true,false);    	
    }

    
	@Override
	protected List<CustomerVO> doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		List<CustomerVO> clientes=new ArrayList<CustomerVO>();
		try
		{
			clientes = this.casaDoQueijoLibraryApp.getDataManager().getCustomerList();
		}
		catch(Exception e)
		{
			Log.w(Constantes.EUREKA_SOFTWARES, "SolicitaClientesListAsyncTask");
			Log.w(Constantes.EUREKA_SOFTWARES, e);
		}
		return clientes;
	}
	
	@Override
    protected void onPostExecute(List<CustomerVO> clientes) {
		this.adapter.clear();
		for(CustomerVO customerVO:clientes)
		{
			this.adapter.add(customerVO);
		}	
		
		this.adapter.notifyDataSetChanged();
		
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
	}
	
}
