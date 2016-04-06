package br.com.eurekasoftwares.tablet.pedido;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import br.com.eurekasoftwares.tablet.R;
import br.com.eurekasoftwares.tablet.adapter.PedidoAdapter;
import br.com.eurekasoftwares.tablet.cliente.ClientePedidosTitulosViewActivity;
import br.com.eurekasoftwares.tablet.login.LoginActivity;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.vo.PedidoVO;

public class PedidosListViewActivity extends Activity {
	private EditText edsearch;
	private PedidoAdapter adapter;
	private ListView lv;
	private TextWatcher filterTextWatcher;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.pedidos_list_view);
	  
	  lv = (ListView)findViewById(R.id.pedidos_list);
	  edsearch = (EditText)findViewById(R.id.pedido_filtro);
	  adapter = new PedidoAdapter(this, ModelSingleton.getInstance().getPedidosListDataProvider());
	  
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
	  edsearch.addTextChangedListener(filterTextWatcher);
	  lv.setAdapter(adapter);	 

	  lv.setTextFilterEnabled(true);
	  
	  lv.setOnItemClickListener(new ListView.OnItemClickListener() 
      {
		  @Override
		public void onItemClick(AdapterView<?> a, View v, int i1, long l) 
		  {

        	  	PedidoVO p = (PedidoVO)adapter.getItem(i1);
  	    		ModelSingleton.getInstance().setPedidoVO(p);
//  	    	notifyDataSetChanged();
  	    		Intent i = new Intent(v.getContext().getApplicationContext(), ClientePedidosTitulosViewActivity.class);
		    	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				v.getContext().getApplicationContext().startActivity(i);
          }
      });
	  ImageButton sincronizarPedidos = (ImageButton)findViewById(R.id.sincroniza_pedidos);
	  sincronizarPedidos.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				new SolicitaPedidosListAsyncTask(PedidosListViewActivity.this, adapter).execute(ModelSingleton.getInstance().getLogon());
			}
	    });
	  Button voltar = (Button)findViewById(R.id.voltar_pedido);
	  voltar.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(v.getContext().getApplicationContext(), ClientePedidosTitulosViewActivity.class);
		    	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				v.getContext().getApplicationContext().startActivity(i);
			}
	    });
	  if(ModelSingleton.getInstance().getPedidosListDataProvider().size() == 0)
	  {
		  new SolicitaPedidosListAsyncTask(this, adapter).execute(ModelSingleton.getInstance().getLogon());
	  }

	}
	@Override
	public void onAttachedToWindow() {
	    // TODO Auto-generated method stub
	    this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
	    super.onAttachedToWindow();
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
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	if (item.getTitle().equals("Sair")) {
    		Intent i = new Intent(this.getApplicationContext(), LoginActivity.class);
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
	}
	@Override
	public void onStop()
	{
		super.onStop();
		this.edsearch.setText("");
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
}
