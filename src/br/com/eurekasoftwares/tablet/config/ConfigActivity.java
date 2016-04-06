package br.com.eurekasoftwares.tablet.config;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.R;
import br.com.eurekasoftwares.tablet.login.LoginActivity;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;

public class ConfigActivity extends Activity {
	private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
	private EditText configUrl;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.config);
	  
	  this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)getApplication();
	  configUrl = (EditText)findViewById(R.id.config_url);
	  configUrl.setText(ModelSingleton.getInstance().getConfigVO().getUrl());
	  
	  Button buttonSalvarConfig = (Button) findViewById(R.id.salvar_config);
	  buttonSalvarConfig.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url = configUrl.getText().toString();
				if(URLUtil.isHttpUrl(url))
				{
					if(ModelSingleton.getInstance().getConfigVO().getUrl() == null)
					{
						ModelSingleton.getInstance().getConfigVO().setUrl(url);
						casaDoQueijoLibraryApp.getDataManager().saveConfig(ModelSingleton.getInstance().getConfigVO());
					}
					else
					{
						ModelSingleton.getInstance().getConfigVO().setUrl(configUrl.getText().toString());
						casaDoQueijoLibraryApp.getDataManager().updateConfig(ModelSingleton.getInstance().getConfigVO());
					}
					Intent i = new Intent(getApplicationContext(), LoginActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
				else
				{
					Dialog dialog = new AlertDialog.Builder(ConfigActivity.this)
					.setTitle("Url invalida")
					.setMessage("Verifique a url digitada.")
					.setPositiveButton("ok", null).create();
					dialog.show();
				}
			}
	  });
	}
	@Override
	public void onBackPressed() {
	}

}
