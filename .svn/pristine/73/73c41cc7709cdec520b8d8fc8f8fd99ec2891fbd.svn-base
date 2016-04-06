package br.com.eurekasoftwares.tablet.login;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.R;
import br.com.eurekasoftwares.tablet.cliente.ClientePedidosTitulosViewActivity;
import br.com.eurekasoftwares.tablet.config.ConfigActivity;
import br.com.eurekasoftwares.tablet.constantes.Constantes;
import br.com.eurekasoftwares.tablet.map.GetMyGPSLocation;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.servlet.WebToMobileClient;
import br.com.eurekasoftwares.tablet.util.GetMyPhoneState;
import br.com.eurekasoftwares.tablet.vo.ConfigVO;
import br.com.eurekasoftwares.tablet.vo.LoginVO;

public class LoginActivity extends Activity {
	private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    	Log.w(Constantes.EUREKA_SOFTWARES, "Aplicação iniciada");
    	ModelSingleton.getInstance().setMyGPSLocation(new GetMyGPSLocation(this));
    	ModelSingleton.getInstance().setMyPhoneState(new GetMyPhoneState());
        this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)getApplication();
        if(ModelSingleton.getInstance().getEnviaPedidosThread() != null)
        	ModelSingleton.getInstance().getEnviaPedidosThread().stop();
        ModelSingleton.getInstance().setEnviaPedidosThread(null);
        ModelSingleton.getInstance().setLoginVO(null);
        ModelSingleton.getInstance().setLogon("");
        List<ConfigVO> configVO = casaDoQueijoLibraryApp.getDataManager().getConfigList();
        if(configVO != null && configVO.size() > 0)
        {
        	ModelSingleton.getInstance().setConfigVO(configVO.get(0));
        	WebToMobileClient webToMobileClient = new WebToMobileClient(ModelSingleton.getInstance().getConfigVO().getUrl()+"/CasaDoQueijoServer/servlet/br.com.diedrich.web.WebToMobileServlet");
        	ModelSingleton.getInstance().setWebToMobileClient(webToMobileClient);
        }
        else
        {
  	  		ModelSingleton.getInstance().setConfigVO(new ConfigVO(null));
  	  		WebToMobileClient webToMobileClient = new WebToMobileClient("http://10.0.2.2:8080/CasaDoQueijoServer/servlet/br.com.diedrich.web.WebToMobileServlet");
  	  		ModelSingleton.getInstance().setWebToMobileClient(webToMobileClient);
        }
        
        Button buttonLogin = (Button) findViewById(R.id.btnLogin);
        
        // Listening to register new account link
        buttonLogin.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Switching to Register screen
				Log.w(Constantes.EUREKA_SOFTWARES, "Botão login");
				EditText usuarioEditText = (EditText)findViewById(R.id.usuario);
				EditText senhaEditText = (EditText)findViewById(R.id.senha);
				try {
					String usuario = usuarioEditText.getText().toString();
					String senha = 	senhaEditText.getText().toString();
					LoginVO loginVO = casaDoQueijoLibraryApp.getDataManager().getLoginVO(usuario, senha);
					if(loginVO != null)
					{
						ModelSingleton.getInstance().setLoginVO(loginVO);
						Intent i = new Intent(LoginActivity.this.getApplicationContext(), ClientePedidosTitulosViewActivity.class);
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						LoginActivity.this.startActivity(i);
					}
					else
					{
						new LoginAsyncTask(LoginActivity.this).execute(usuario,senha);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        ImageButton buttonConfig = (ImageButton) findViewById(R.id.configuracao);
        buttonConfig.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.w(Constantes.EUREKA_SOFTWARES, "Botão configuração");
				Intent i = new Intent(getApplicationContext(), ConfigActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
        });
    }
    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.addSubMenu("Sair");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	if (item.getTitle().equals("Sair")) {
    		
    		AlertDialog.Builder sair = new AlertDialog.Builder(LoginActivity.this);
    		sair.setMessage("Deseja realmente sair ?");
    		sair.setNegativeButton("Não", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
	            	dialog.cancel();
				}
			});
    		sair.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					if(ModelSingleton.getInstance().getEnviaPedidosThread()!=null)
		    		{
		    			ModelSingleton.getInstance().getEnviaPedidosThread().stopThread();
		    		}
		    		finish();
		    		System.runFinalizersOnExit(true);
		    		System.exit(0);
		    		android.os.Process.killProcess(android.os.Process.myPid());
				}
			});
    		sair.create().show();
    	}
    	return super.onOptionsItemSelected(item);
    }
}