package br.com.eurekasoftwares.tablet.titulo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.cliente.ClientePedidosTitulosViewActivity;
import br.com.eurekasoftwares.tablet.constantes.Constantes;
import br.com.eurekasoftwares.tablet.vo.TituloVO;

public class SolicitaTitulosListAsyncTask extends
AsyncTask<String, Void, List<TituloVO>> {
	
	private ProgressDialog dialog;
    private Activity activity;
    private List<TituloVO> list;
    private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
    
	public SolicitaTitulosListAsyncTask(Activity activity,List<TituloVO> list)
	{
		this.activity = activity;
		this.list = list;
	}
	
	@Override
    protected void onPreExecute() {
    	Application app = activity.getApplication();
    	this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)app;
    	this.dialog = ProgressDialog.show(activity, "Aguarde", "Carregando...",true,false);    	
    }

    
	@Override
	protected List<TituloVO> doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		List<TituloVO> titulos=new ArrayList<TituloVO>();
		try
		{
			titulos = this.casaDoQueijoLibraryApp.getDataManager().getTitulosPorCliente(arg0[0]);
		}
		catch(Exception e)
		{
			Log.w(Constantes.EUREKA_SOFTWARES, "SolicitaTitulosListAsyncTask");
			Log.w(Constantes.EUREKA_SOFTWARES, e);
		}
		return titulos;
	}
	
	@Override
    protected void onPostExecute(List<TituloVO> titulos) {
		this.list.clear();
		for(TituloVO tituloVO:titulos)
		{
			this.list.add(tituloVO);
		}	
		
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        
        Intent i = new Intent(this.activity.getApplicationContext(), ClientePedidosTitulosViewActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		this.activity.getApplicationContext().startActivity(i);
	}
}
