package br.com.eurekasoftwares.tablet.produto;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.adapter.ProdutoAdapter;
import br.com.eurekasoftwares.tablet.constantes.Constantes;
import br.com.eurekasoftwares.tablet.vo.ProductVO;

public class SolicitaProdutosListAsyncTask extends
		AsyncTask<String, Void, List<ProductVO>> {
	
	public SolicitaProdutosListAsyncTask(Activity activity,ProdutoAdapter adapter)
	{
		this.activity = activity;
		this.adapter = adapter;
	}
	private ProgressDialog dialog;
	private Activity activity;
    private ProdutoAdapter adapter;
    private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
    @Override
    protected void onPreExecute() {
    	Application app = activity.getApplication();
    	this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)app;
    	this.dialog = ProgressDialog.show(activity, "Aguarde", "Carregando...",true,false);
    }
	@Override
	protected List<ProductVO> doInBackground(String... params) {
		// TODO Auto-generated method stub
		List<ProductVO> produtos=new ArrayList<ProductVO>();
		try
		{
			produtos = this.casaDoQueijoLibraryApp.getDataManager().getProductList();
		}
		catch(Exception e)
		{
			Log.w(Constantes.EUREKA_SOFTWARES, "SolicitaProdutosListAsyncTask");
			Log.w(Constantes.EUREKA_SOFTWARES, e);
		}
		return produtos;
	}
	@Override
    protected void onPostExecute(List<ProductVO> produtos) {
		this.adapter.clear();
		for(ProductVO productVO:produtos)
		{
			this.adapter.add(productVO);
		}	
		
		this.adapter.notifyDataSetChanged();
		
		if (dialog.isShowing()) {
            dialog.dismiss();
        }
	}
	
}
