package br.com.eurekasoftwares.tablet.login;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.cliente.ClientePedidosTitulosViewActivity;
import br.com.eurekasoftwares.tablet.constantes.Constantes;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.vo.LoginVO;

public class LoginAsyncTask extends AsyncTask<String, Void, String> {
	
	private Activity activity;
	private Dialog dialog;
	private String user;
	private String pass;
	private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
	
	public LoginAsyncTask(Activity activity)
	{
		this.activity = activity;
	}
	@Override
    protected void onPreExecute() {
		Log.w(Constantes.EUREKA_SOFTWARES, "LoginAsyncTask.onPreExecute()");
    	this.dialog = ProgressDialog.show(activity, "Aguarde", "Carregando...",true,false);
    	this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)activity.getApplication();
	}
	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		Log.w(Constantes.EUREKA_SOFTWARES, "LoginAsyncTask.doInBackground("+arg0[0]+","+arg0[1]+")");
		user = arg0[0];
		pass = arg0[1];
		String logon = "";
		try {
			logon =  ModelSingleton.getInstance().getWebToMobileClient().doLogin(arg0[0], arg0[1]);
			Log.w(Constantes.EUREKA_SOFTWARES, logon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Log.w(Constantes.EUREKA_SOFTWARES, "Exception no login "+e);
		}
		return logon;
	}
	@Override
    protected void onPostExecute(String logon) {
		ModelSingleton.getInstance().setLogon(logon);
		if (dialog.isShowing()) {
            dialog.dismiss();
        }
		if(!ModelSingleton.getInstance().getLogon().equalsIgnoreCase("")){
			LoginVO loginVO = new LoginVO();
			loginVO.setUser(user);
			loginVO.setPass(pass);
			this.casaDoQueijoLibraryApp.getDataManager().saveLoginVO(loginVO);
			ModelSingleton.getInstance().setLoginVO(loginVO);
			Intent i = new Intent(activity.getApplicationContext(), ClientePedidosTitulosViewActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			activity.startActivity(i);
		}
		else
		{
			this.dialog = new AlertDialog.Builder(activity)
				.setTitle("Falha ao logar.")
				.setMessage("Verifique os dados de acesso.")
				.setPositiveButton("ok", null).create();
			this.dialog.show();
		}
	}
}
