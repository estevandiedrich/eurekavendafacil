package br.com.eurekasoftwares.tablet.titulo;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.gzip.GZipUtils;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.vo.LoginVO;
import br.com.eurekasoftwares.tablet.vo.TituloVO;

import com.thoughtworks.xstream.XStream;

public class EnviarTitulosCobradosParaServidorAsyncTask extends
	AsyncTask<TituloVO, Void, String[]>{
	private ProgressDialog dialog;
    private Activity activity;
    private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
    
	public EnviarTitulosCobradosParaServidorAsyncTask(Activity activity)
	{
		this.activity = activity;
		Application app = activity.getApplication();
    	this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)app;
	}
	@Override
    protected void onPreExecute() {
		this.dialog = ProgressDialog.show(activity, "Aguarde", "Carregando...",true,false);
	}
	@Override
	protected String[] doInBackground(TituloVO... arg0) {
		String[] titulosEnviados=null;
		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		try {
			if(ModelSingleton.getInstance().getLogon().equalsIgnoreCase(""))
			{
				LoginVO loginVO = ModelSingleton.getInstance().getLoginVO();
				//this.casaDoQueijoLibraryApp.getDataManager().getLoginVO();
				String logon = ModelSingleton.getInstance().getWebToMobileClient().doLogin(loginVO.getUser(), loginVO.getPass());
				ModelSingleton.getInstance().setLogon(logon);
			}
			titulosEnviados = ModelSingleton.getInstance().getWebToMobileClient().enviarTitulosCobradosParaServidor(GZipUtils.compress(xStream.toXML(arg0[0])), ModelSingleton.getInstance().getLogon());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return titulosEnviados;
	}
	@Override
    protected void onPostExecute(String[] titulosCompressed) {
		
		for(int i = 0;titulosCompressed != null && i < titulosCompressed.length;i++)
		{
			TituloVO tituloVO = casaDoQueijoLibraryApp.getDataManager().getTituloVO(titulosCompressed[i]);
			tituloVO.setSincronizado("S");
			casaDoQueijoLibraryApp.getDataManager().updateTitulo(tituloVO);
		}	
		titulosCompressed = null;
		
    	if (dialog.isShowing()) {
            dialog.dismiss();
        }
	}
}
