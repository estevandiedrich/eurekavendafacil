package br.com.eurekasoftwares.tablet.pedido;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.adapter.PedidoAdapter;
import br.com.eurekasoftwares.tablet.constantes.Constantes;
import br.com.eurekasoftwares.tablet.vo.PedidoVO;

public class SolicitaPedidosListAsyncTask extends
	AsyncTask<String, Void, List<PedidoVO>> {
	
	private ProgressDialog dialog;
    private Activity activity;
    private PedidoAdapter adapter;
    private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
    
	public SolicitaPedidosListAsyncTask(Activity activity,PedidoAdapter adapter)
	{
		this.activity = activity;
		this.adapter = adapter;
	}
	@Override
    protected void onPreExecute() {
    	Application app = activity.getApplication();
    	this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)app;
    	this.dialog = ProgressDialog.show(activity, "Aguarde", "Carregando...",true,false);    	
    }

    
	@Override
	protected List<PedidoVO> doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		List<PedidoVO> pedidos=new ArrayList<PedidoVO>();
		try
		{
			pedidos = this.casaDoQueijoLibraryApp.getDataManager().getPedidoList();
		}
		catch(Exception e)
		{
			Log.w(Constantes.EUREKA_SOFTWARES, "SolicitaPedidosListAsyncTask");
			Log.w(Constantes.EUREKA_SOFTWARES, e);
		}
		return pedidos;
	}
	
	@Override
    protected void onPostExecute(List<PedidoVO> pedidos) {
		this.adapter.clear();
		for(PedidoVO pedidoVO:pedidos)
		{
			this.adapter.add(pedidoVO);
		}	
		
		this.adapter.notifyDataSetChanged();
		
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
	}
}
