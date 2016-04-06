package br.com.eurekasoftwares.tablet.titulo;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.adapter.TituloAdapter;
import br.com.eurekasoftwares.tablet.gzip.GZipUtils;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.vo.LoginVO;
import br.com.eurekasoftwares.tablet.vo.TituloVO;
import br.com.eurekasoftwares.tablet.vo.TitulosVO;

import com.thoughtworks.xstream.XStream;

public class SincronizaTitulosListAsyncTask extends
	AsyncTask<String, Void, byte[]>{
	public SincronizaTitulosListAsyncTask(Activity activity,TituloAdapter adapter)
	{
		this.activity = activity;
		this.adapter = adapter;
	}
	private ProgressDialog dialog;
    private Activity activity;
    private TituloAdapter adapter;
    private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
    
    @Override
    protected void onPreExecute() {
    	Application app = activity.getApplication();
    	this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)app;
    	this.dialog = ProgressDialog.show(activity, "Aguarde", "Carregando...",true,false);
    	List<TituloVO> titulos = this.casaDoQueijoLibraryApp.getDataManager().getTitulosPorCliente(ModelSingleton.getInstance().getPedidoVO().getCustomerCod());
    	for(TituloVO tituloVO:titulos)
    	{
    		this.casaDoQueijoLibraryApp.getDataManager().deleteTitulo(tituloVO.getNumeroTitulo());
    	}
    }
    @Override
	protected byte[] doInBackground(String... arg0) {
		// TODO Auto-generated method stub
    	byte[] titulosCompressed=null;
		try {
			if(ModelSingleton.getInstance().getLogon().equalsIgnoreCase(""))
			{
				LoginVO loginVO = ModelSingleton.getInstance().getLoginVO();
				//this.casaDoQueijoLibraryApp.getDataManager().getLoginVO();
				String logon = ModelSingleton.getInstance().getWebToMobileClient().doLogin(loginVO.getUser(), loginVO.getPass());
				ModelSingleton.getInstance().setLogon(logon);
			}
			titulosCompressed = ModelSingleton.getInstance().getWebToMobileClient().buscaListaTitulo(ModelSingleton.getInstance().getLogon(),ModelSingleton.getInstance().getPedidoVO().getCustomerCod());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return titulosCompressed;
	}
    @Override
    protected void onPostExecute(byte[] titulosCompressed) {
    	String titulosXML = new String(GZipUtils.decompress(titulosCompressed));
    	titulosCompressed = null;
    	XStream xStream = new XStream();
    	xStream.autodetectAnnotations(true);
    	xStream.alias("x1", TitulosVO.class);
		TitulosVO titulos = (TitulosVO)xStream.fromXML(titulosXML);
		
		this.adapter.clear();
		
		
		for(int i = 0;titulos != null && i < titulos.getTitulos().size();i++)
		{
			TituloVO tituloVO = titulos.getTitulos().get(i);
			casaDoQueijoLibraryApp.getDataManager().saveTitulo(tituloVO);
			if(ModelSingleton.getInstance().getPedidoVO().getCustomerCod().equalsIgnoreCase(tituloVO.getCustomerCod()))
			{
				this.adapter.add(tituloVO);
			}
			
		}
		if(this.adapter.getCount() == 0)
		{
			Dialog dialog = new AlertDialog.Builder(activity)
			.setTitle("Nenhum titulo.")
			.setMessage("Nenhum titulo localizado para este cliente.")
			.setPositiveButton("ok", null).create();
			dialog.show();
		}
		titulos = null;
		
		this.adapter.notifyDataSetChanged();

    	if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
