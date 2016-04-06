package br.com.eurekasoftwares.tablet.pedido;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import br.com.eurekasoftwares.tablet.R;
import br.com.eurekasoftwares.tablet.cliente.ClientePedidosTitulosViewActivity;
import br.com.eurekasoftwares.tablet.login.LoginActivity;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;

public class OpcoesPedidoViewActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opcoes_pedido);
        
//        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
//        KeyguardManager.KeyguardLock lock = keyguardManager
//                .newKeyguardLock(KEYGUARD_SERVICE);
//        lock.disableKeyguard();
        
        if(ModelSingleton.getInstance().getPedidoVO().getUrgente().equalsIgnoreCase(""))
        {
        	ModelSingleton.getInstance().getPedidoVO().setUrgente("S");
        }
        if(ModelSingleton.getInstance().getPedidoVO().getTipoPagamento().equalsIgnoreCase(""))
        {
        	ModelSingleton.getInstance().getPedidoVO().setTipoPagamento("COB");
        }
        if(ModelSingleton.getInstance().getPedidoVO().getPrazo().equalsIgnoreCase(""))
        {
        	ModelSingleton.getInstance().getPedidoVO().setPrazo("0");
        }
        
        Button finalizarOpcoesPedido = (Button)findViewById(R.id.finalizar_opcoes_pedido);
        finalizarOpcoesPedido.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(ModelSingleton.getInstance().getPedidoVO().getTipoPagamento().equalsIgnoreCase("COB"))
				{
					if(ModelSingleton.getInstance().getPedidoVO().getUrgente().equalsIgnoreCase("S"))
					{
						if(!ModelSingleton.getInstance().getPedidoVO().getPrazo().equalsIgnoreCase("0"))
						{
							Intent i = new Intent(getApplicationContext(), ClientePedidosTitulosViewActivity.class);
							i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(i);
						}
						else
						{
							Dialog pedidoInvalidoDialog = new AlertDialog.Builder(OpcoesPedidoViewActivity.this)
								.setTitle("Pedido invalido.")
								.setMessage("Cobrança não pode ter prazo 0.")
								.setPositiveButton("ok", null).create();
							pedidoInvalidoDialog.show();
						}
					}
					else
					{
						Dialog pedidoInvalidoDialog = new AlertDialog.Builder(OpcoesPedidoViewActivity.this)
							.setTitle("Pedido invalido.")
							.setMessage("Cobrança precisa ser urgente.")
							.setPositiveButton("ok", null).create();
						pedidoInvalidoDialog.show();
					}
				}
				else
				{
					Intent i = new Intent(getApplicationContext(), ClientePedidosTitulosViewActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
//				Intent i = new Intent(getApplicationContext(), PedidoActivity.class);
//				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(i);
			}
        });
        RadioGroup radioGroupPedidoUrgente = (RadioGroup)findViewById(R.id.pedido_urgente);
		radioGroupPedidoUrgente.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId == R.id.pedido_urgente_radio_button)
				{
					ModelSingleton.getInstance().getPedidoVO().setUrgente("S");
				}
				else if(checkedId == R.id.pedido_nao_urgente_radio_button)
				{
					ModelSingleton.getInstance().getPedidoVO().setUrgente("N");
				}
			}
        });
        RadioGroup radioGroupTipoPagamento = (RadioGroup)findViewById(R.id.tipo_pagamento);
        radioGroupTipoPagamento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId == R.id.cobranca_radio_button)
				{
					ModelSingleton.getInstance().getPedidoVO().setTipoPagamento("COB");
				}
				else if(checkedId == R.id.debito_radio_button)
				{
					ModelSingleton.getInstance().getPedidoVO().setTipoPagamento("DEB");
				}
				else
				{
					ModelSingleton.getInstance().getPedidoVO().setTipoPagamento("CHQ");
				}
			}
        });
        Spinner spinnerPrazo = (Spinner)findViewById(R.id.prazo);
        spinnerPrazo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                ModelSingleton.getInstance().getPedidoVO().setPrazo(String.valueOf(parent.getItemAtPosition(pos)));
            }
            @Override
			public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
            AlertDialog.Builder sair = new AlertDialog.Builder(OpcoesPedidoViewActivity.this);
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
					Intent i = new Intent(OpcoesPedidoViewActivity.this.getApplicationContext(), LoginActivity.class);
		        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        	OpcoesPedidoViewActivity.this.getApplicationContext().startActivity(i);
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
	public void onBackPressed() {
	}

	@Override
	public void onStart()
	{
		super.onStart();

    	RadioButton pedidoUrgentePedidoUrgente = (RadioButton)findViewById(R.id.pedido_urgente_radio_button);
    	RadioButton pedidoNaoUrgentePedidoUrgente = (RadioButton)findViewById(R.id.pedido_nao_urgente_radio_button);
    	if(ModelSingleton.getInstance().getPedidoVO().getUrgente().equalsIgnoreCase("S"))
    	{
    		pedidoUrgentePedidoUrgente.setChecked(true);
    	}
    	else
    	{
    		pedidoNaoUrgentePedidoUrgente.setChecked(true);
    	}

    	RadioButton cobrancaRadioButton = (RadioButton)findViewById(R.id.cobranca_radio_button);
    	RadioButton debitoRadioButton = (RadioButton)findViewById(R.id.debito_radio_button);
    	RadioButton chequeRadioButton = (RadioButton)findViewById(R.id.cheque_radio_button);
    	if(ModelSingleton.getInstance().getPedidoVO().getTipoPagamento().equalsIgnoreCase("COB"))
    	{
    		cobrancaRadioButton.setChecked(true);
    	}
    	else if(ModelSingleton.getInstance().getPedidoVO().getTipoPagamento().equalsIgnoreCase("DEB"))
    	{
    		debitoRadioButton.setChecked(true);
    	}
    	else if(ModelSingleton.getInstance().getPedidoVO().getTipoPagamento().equalsIgnoreCase("CHQ"))
    	{
    		chequeRadioButton.setChecked(true);
    	}
    	Spinner spinnerPrazo = (Spinner)findViewById(R.id.prazo);
    	if(ModelSingleton.getInstance().getPedidoVO().getPrazo().equalsIgnoreCase("0"))
    	{
    		spinnerPrazo.setSelection(0);
    	}
    	else if(ModelSingleton.getInstance().getPedidoVO().getPrazo().equalsIgnoreCase("7"))
    	{
    		spinnerPrazo.setSelection(1);
    	}
    	else if(ModelSingleton.getInstance().getPedidoVO().getPrazo().equalsIgnoreCase("14"))
    	{
    		spinnerPrazo.setSelection(2);
    	}
    	else if(ModelSingleton.getInstance().getPedidoVO().getPrazo().equalsIgnoreCase("21"))
    	{
    		spinnerPrazo.setSelection(3);
    	}
    	else if(ModelSingleton.getInstance().getPedidoVO().getPrazo().equalsIgnoreCase("28"))
    	{
    		spinnerPrazo.setSelection(4);
    	}
	}
	
	@Override
	public void onAttachedToWindow() {
	    // TODO Auto-generated method stub
	    this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
	    super.onAttachedToWindow();
	}
}
