package br.com.eurekasoftwares.tablet.cliente;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import br.com.eurekasoftwares.tablet.R;
import br.com.eurekasoftwares.tablet.adapter.ClienteAdapter;
import br.com.eurekasoftwares.tablet.login.LoginActivity;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.pedido.PedidosListViewActivity;

public class ClientesListViewActivity extends Activity {
	private ClienteAdapter adapter;
    private TextWatcher filterTextWatcher;
    private EditText edsearch;
    private ListView lv;
    private TelephonyManager tm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.clientes_list_view);
	  
	  tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
	  tm.listen(ModelSingleton.getInstance().getMyPhoneState().getPhoneStateListener(), PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
	  
//	  KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
//      KeyguardManager.KeyguardLock lock = keyguardManager
//              .newKeyguardLock(KEYGUARD_SERVICE);
//      lock.disableKeyguard();
	  
	  lv = (ListView)findViewById(R.id.clientes_list);
	  edsearch = (EditText)findViewById(R.id.cliente_filtro);
	  adapter = new ClienteAdapter(this, ModelSingleton.getInstance().getCustomerListDataProvider());
	  filterTextWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			adapter.getFilter().filter(s);
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
		}
	};
	ImageButton cadastrarCliente = (ImageButton)findViewById(R.id.cadastrar_cliente);
	cadastrarCliente.setOnClickListener(new Button.OnClickListener(){
		@Override
		public void onClick(View v) {
			Toast.makeText(v.getContext(), "refresh",
		  	          Toast.LENGTH_SHORT).show();
		}
	});
	ImageButton sincronizaClientes = (ImageButton)findViewById(R.id.sincroniza_clientes);
	sincronizaClientes.setOnClickListener(new Button.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			AlertDialog.Builder sincronizar = new AlertDialog.Builder(ClientesListViewActivity.this);
			sincronizar.setMessage("Deseja realmente sincronizar ?");
			sincronizar.setNegativeButton("Não", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
	            	dialog.cancel();
				}
			});
			sincronizar.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					if(ModelSingleton.getInstance().getMyPhoneState().getConnectionState().equalsIgnoreCase("Connected"))
					{
						new SincronizaClientesListAsyncTask(ClientesListViewActivity.this, adapter).execute(ModelSingleton.getInstance().getLogon());
					}
					else
					{
						AlertDialog.Builder sinal = new AlertDialog.Builder(ClientesListViewActivity.this);
						sinal.setTitle("Sem sinal.");
						sinal.setMessage("Impossivel sincronizar.");
						sinal.setPositiveButton("Ok", null).create();
						sinal.show();
					}
				}
			});
			sincronizar.create().show();			
		}
    });

	  edsearch.addTextChangedListener(filterTextWatcher);
	  lv.setAdapter(adapter);	 
	  lv.setTextFilterEnabled(true);
	}
	@Override
	public void onBackPressed() {
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }
	@Override
	public void onAttachedToWindow() {
	    // TODO Auto-generated method stub
	    this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
	    super.onAttachedToWindow();
	}
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	if (item.getTitle().equals("Sair")) {
    		AlertDialog.Builder sair = new AlertDialog.Builder(ClientesListViewActivity.this);
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
					Intent i = new Intent(ClientesListViewActivity.this.getApplicationContext(), LoginActivity.class);
		        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        	ClientesListViewActivity.this.getApplicationContext().startActivity(i);
				}
			});
    		sair.create().show();

        }
    	else if(item.getTitle().equals("Pedidos"))
    	{
    		Intent i = new Intent(this.getApplicationContext(), PedidosListViewActivity.class);
        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		this.getApplicationContext().startActivity(i);
    	}
    	return super.onOptionsItemSelected(item);
    }
	@Override
	public void onStart()
	{
		super.onStart();
		if(ModelSingleton.getInstance().getCustomerListDataProvider().size() == 0)
		{
			new SolicitaClientesListAsyncTask(this, adapter).execute(ModelSingleton.getInstance().getLogon());
		}
	}
	@Override
	public void onStop()
	{
		super.onStop();
		this.edsearch.setText("");
	}
	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    edsearch.removeTextChangedListener(filterTextWatcher);
	}

}