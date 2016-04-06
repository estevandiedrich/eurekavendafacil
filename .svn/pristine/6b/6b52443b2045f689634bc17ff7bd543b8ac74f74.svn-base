package br.com.eurekasoftwares.tablet.cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.R;
import br.com.eurekasoftwares.tablet.adapter.ProdutoPedidoAdapter;
import br.com.eurekasoftwares.tablet.adapter.TituloAdapter;
import br.com.eurekasoftwares.tablet.constantes.Constantes;
import br.com.eurekasoftwares.tablet.login.LoginActivity;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.pedido.OpcoesPedidoViewActivity;
import br.com.eurekasoftwares.tablet.pedido.PedidosListViewActivity;
import br.com.eurekasoftwares.tablet.produto.ProdutosListViewActivity;
import br.com.eurekasoftwares.tablet.thread.EnviaPedidosThread;
import br.com.eurekasoftwares.tablet.titulo.SincronizaTitulosListAsyncTask;
import br.com.eurekasoftwares.tablet.vo.ClienteDiaPedidoVO;
import br.com.eurekasoftwares.tablet.vo.ClienteProdutoQuantidadeVO;
import br.com.eurekasoftwares.tablet.vo.ErroVO;
import br.com.eurekasoftwares.tablet.vo.PedidoVO;
import br.com.eurekasoftwares.tablet.vo.ProductFromPedidoVO;

public class ClientePedidosTitulosViewActivity extends Activity{
	private ProdutoPedidoAdapter adapter;
	private TituloAdapter tituloAdapter;
	private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
	private EditText editTextCliente;
	private AlertDialog.Builder builder;
	private ListView lv;
	private ListView titulosLv;
	private ViewFlipper flipper;
	private long result = 0l;
	private List<String> listPedidoInvalido = new ArrayList<String>();
	private TelephonyManager tm;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_pedidos_titulos);
        Log.w(Constantes.EUREKA_SOFTWARES, "ClientePedidosTitulosViewActivity");
        
        tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
  	  	tm.listen(ModelSingleton.getInstance().getMyPhoneState().getPhoneStateListener(), PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
  	  	
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        
        if(ModelSingleton.getInstance().getEnviaPedidosThread() == null)
		{
			ModelSingleton.getInstance().setEnviaPedidosThread(new EnviaPedidosThread(this));
			ModelSingleton.getInstance().getEnviaPedidosThread().start();
		}
        
        this.builder = new AlertDialog.Builder(this);
		this.builder.setMessage("Finalizar pedido ?");
		this.builder.setCancelable(false);
        
        Application app = this.getApplication();
    	this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)app;
    	
        adapter = new ProdutoPedidoAdapter(this, ModelSingleton.getInstance().getPedidoVO().getProducts());
        tituloAdapter = new TituloAdapter(this, ModelSingleton.getInstance().getTitulos());
        
        editTextCliente = (EditText) findViewById(R.id.cliente_pedido);
        editTextCliente.setText(ModelSingleton.getInstance().getPedidoVO().getCustomerCod());
        editTextCliente.setOnClickListener(new EditText.OnClickListener() {
        	
        	@Override
			public void onClick(View v) {
        		Intent i = new Intent(getApplicationContext(), ClientesListViewActivity.class);
        		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        		startActivity(i);
        	}
        });
        
        ImageButton sincronizaTitulos = (ImageButton)findViewById(R.id.sincroniza_titulos);
        sincronizaTitulos.setOnClickListener(new Button.OnClickListener() {
    		
    		@Override
			public void onClick(View v) {
    			AlertDialog.Builder sincronizar = new AlertDialog.Builder(ClientePedidosTitulosViewActivity.this);
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
							new SincronizaTitulosListAsyncTask(ClientePedidosTitulosViewActivity.this, tituloAdapter).execute(ModelSingleton.getInstance().getLogon());
						}
						else
						{
							AlertDialog.Builder sinal = new AlertDialog.Builder(ClientePedidosTitulosViewActivity.this);
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
        
        Button adionaProdutoPedido = (Button)findViewById(R.id.adiciona_produto_pedido);
        adionaProdutoPedido.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean pedidoValido = true;
				for(ProductFromPedidoVO produtoSelecionado:ModelSingleton.getInstance().getPedidoVO().getAllProducts())
				{
			        if(produtoSelecionado.getQuantidadeTroca() == 0.0f)
			        {
			            if(produtoSelecionado.getQuantidadeVenda() == 0)
			            {
			                Dialog pedidoInvalidoDialog = new AlertDialog.Builder(ClientePedidosTitulosViewActivity.this)
								.setTitle("Pedido invalido.")
								.setMessage("A Quantidade Venda e Quantidade Troca nao podem ser ambas 0")
								.setPositiveButton("ok", null).create();
							pedidoInvalidoDialog.show();
							pedidoValido = false;
			                break;
			            }
			        }
				}
				if(pedidoValido)
				{
					Intent i = new Intent(getApplicationContext(), ProdutosListViewActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			}
        });
        final Button opcoesPedido = (Button)findViewById(R.id.opcoes_pedido);
	      opcoesPedido.setOnClickListener(new Button.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(), OpcoesPedidoViewActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
	        });
	    
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.produtos_titulos);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId == R.id.produtos_radio_button)
				{
					//opcoesPedido.setVisibility(View.VISIBLE);
					flipper.setInAnimation(inFromRightAnimation());
					flipper.setOutAnimation(outToLeftAnimation());
					flipper.showNext();
				}
				else
				{
					if(ModelSingleton.getInstance().getTitulos().size()==0)
					{
						Dialog dialog = new AlertDialog.Builder(ClientePedidosTitulosViewActivity.this)
						.setTitle("Nenhum titulo.")
						.setMessage("Nenhum titulo localizado para este cliente.")
						.setPositiveButton("ok", null).create();
						dialog.show();
					}
					//opcoesPedido.setVisibility(View.INVISIBLE);
					flipper.setInAnimation(inFromLeftAnimation());
					flipper.setOutAnimation(outToRightAnimation());
					flipper.showPrevious();  
				}
			}
        });
        
        RadioButton pedidos = (RadioButton)this.findViewById(R.id.produtos_radio_button);
        RadioButton titulos = (RadioButton)this.findViewById(R.id.titulos_radio_button);
        
        if(ModelSingleton.getInstance().getPedidoVO().getCustomerCod().equalsIgnoreCase(""))
	    {
	    	opcoesPedido.setEnabled(false);
	    	pedidos.setEnabled(false);
	    	titulos.setEnabled(false);
	    }
        
        titulosLv = (ListView)findViewById(R.id.titulos);
        titulosLv.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        titulosLv.setStackFromBottom(true);
        titulosLv.setTextFilterEnabled(true);
        titulosLv.setAdapter(tituloAdapter);
        
        lv = (ListView)findViewById(R.id.produtos_pedido);
        lv.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        lv.setStackFromBottom(true);
        lv.setTextFilterEnabled(true);
        lv.setAdapter(adapter);
        Button finalizarPedido = (Button)findViewById(R.id.finalizar_pedido);
        finalizarPedido.setOnClickListener(new Button.OnClickListener(){
		@Override
		public void onClick(View v)
		{
  			builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					ModelSingleton.getInstance().getPedidoVO().setDataEmissao(new Date());
					if(methodValidaPedido())
					{
						PedidoVO pedidoVO = casaDoQueijoLibraryApp.getDataManager().getPedidoVO(ModelSingleton.getInstance().getPedidoVO().getCodigoPedido());
						if(pedidoVO == null)
						{
							result = casaDoQueijoLibraryApp.getDataManager().savePedido(ModelSingleton.getInstance().getPedidoVO());
							ModelSingleton.getInstance().getPedidoVO().setSincronizado("0");
							casaDoQueijoLibraryApp.getDataManager().updatePedido(ModelSingleton.getInstance().getPedidoVO());
						}
						else
						{
							AlertDialog.Builder salvarAlteracoes = new AlertDialog.Builder(ClientePedidosTitulosViewActivity.this);
							salvarAlteracoes.setMessage("Salvar alterações ?");
							salvarAlteracoes.setNegativeButton("Não", new DialogInterface.OnClickListener() {
								
			  					@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
					            	dialog.cancel();
					            	ModelSingleton.getInstance().getPedidoVO().getAllProducts().clear();
						  			ModelSingleton.getInstance().setPedidoVO(new PedidoVO());
						  			finish();
						  			startActivity(getIntent()); 
								}
			  				});
							salvarAlteracoes.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
										ModelSingleton.getInstance().getPedidoVO().setSincronizado("0");
										casaDoQueijoLibraryApp.getDataManager().updateProdutosPedido(ModelSingleton.getInstance().getPedidoVO());
										result = 1;
										salvarAlteracoes(result);
								}
							});
							salvarAlteracoes.create().show();
						}
						salvarAlteracoes(result);
					}
					else
					{
						String msg = "";
						for(String msgPart:listPedidoInvalido)
						{
							msg += "\n"+msgPart;
						}
						Dialog pedidoInvalidoDialog = new AlertDialog.Builder(ClientePedidosTitulosViewActivity.this)
							.setTitle("Pedido invalido.")
							.setMessage(msg)
							.setPositiveButton("ok", null).create();
						pedidoInvalidoDialog.show();
					}
				}
			});
  			builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
				
  					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
		            	dialog.cancel();
					}
  				});
  				builder.create().show();
			}
  	  	});
		if(ModelSingleton.getInstance().getPedidoVO().getCustomerCod().equalsIgnoreCase(""))
		{
			adionaProdutoPedido.setEnabled(false);
			finalizarPedido.setEnabled(false);
		}
		EditText totalPedido = (EditText)findViewById(R.id.total_pedido);
		ModelSingleton.getInstance().setTotalPedido(R.id.total_pedido);
		totalPedido.setText(String.valueOf(ModelSingleton.getInstance().getPedidoVO().getTotal()));
	}
	public void salvarAlteracoes(long result)
	{
		if(result != 0)
		{
			ClienteDiaPedidoVO clienteDiaPedidoVO = new ClienteDiaPedidoVO();
	        clienteDiaPedidoVO.setCodCliente(ModelSingleton.getInstance().getPedidoVO().getCustomerCod());
	        clienteDiaPedidoVO.setDia(ModelSingleton.getInstance().getPedidoVO().getDataEmissao());
	        clienteDiaPedidoVO.setCodPedido(ModelSingleton.getInstance().getPedidoVO().getCodigoPedido());					        
	        result = casaDoQueijoLibraryApp.getDataManager().saveClienteDiaPedido(clienteDiaPedidoVO);
	        if(result != 0)
	        {	
	        	for(ProductFromPedidoVO fromPedidoVO:ModelSingleton.getInstance().getPedidoVO().getAllProducts())
	        	{
	        		ClienteProdutoQuantidadeVO clienteProdutoQuantidadeVO = new ClienteProdutoQuantidadeVO();
	        		clienteProdutoQuantidadeVO.setCodCliente(ModelSingleton.getInstance().getPedidoVO().getCustomerCod());
	        		clienteProdutoQuantidadeVO.setCodProduto(fromPedidoVO.getCod());
	        		clienteProdutoQuantidadeVO.setQuantidade(fromPedidoVO.getQuantidadeVenda());
	        		result = casaDoQueijoLibraryApp.getDataManager().saveClienteProdutoQuantidade(clienteProdutoQuantidadeVO);
	        		if(result == 0)
	        		{
	        			ErroVO erroVO = new ErroVO();
	        			erroVO.setMensagem("Falha ao salvar historico de produtos por cliente");
	        			casaDoQueijoLibraryApp.getDataManager().saveErro(erroVO);
	        		}
	        	}
	        	ModelSingleton.getInstance().getPedidoVO().getAllProducts().clear();
	  			ModelSingleton.getInstance().setPedidoVO(new PedidoVO());
	  			finish();
	  			startActivity(getIntent()); 
//	  			adapter.notifyDataSetInvalidated();
//	  			editTextCliente.setText("");
	        }
		}
	}
	private boolean methodValidaPedido()
    {
        this.getListPedidoInvalido().clear();
        if(ModelSingleton.getInstance().getPedidoVO().getAllProducts().size() > 0)
        {
            if(!ModelSingleton.getInstance().getPedidoVO().getCustomerCod().equalsIgnoreCase(""))
            {
                if(!ModelSingleton.getInstance().getPedidoVO().getTipoPagamento().equalsIgnoreCase(""))
                {
                    if(!ModelSingleton.getInstance().getPedidoVO().getUrgente().equalsIgnoreCase(""))
                    {
                        if(!ModelSingleton.getInstance().getPedidoVO().getPrazo().equalsIgnoreCase(""))
                        {
                        	for(ProductFromPedidoVO produtoSelecionado:ModelSingleton.getInstance().getPedidoVO().getAllProducts())
            				{
                        		if(ModelSingleton.getInstance().getPedidoVO().getTipoPagamento().equalsIgnoreCase("COB") || ModelSingleton.getInstance().getPedidoVO().getUrgente().equalsIgnoreCase("S"))
                                {
                        			produtoSelecionado.setUrgente("S");
                                }
            			        if(produtoSelecionado.getQuantidadeTroca() == 0.0f)
            			        {
            			            if(produtoSelecionado.getQuantidadeVenda() == 0)
            			            {
            							this.getListPedidoInvalido().add("A Quantidade Venda e Quantidade Troca nao podem ser ambas 0");
            			                return false;
            			            }
            			        }
            				}
                        	return true;
                        }
                        else
                        {
                            this.getListPedidoInvalido().add("Prazo nao Inform.");
                        }
                    }
                    else
                    {
                        this.getListPedidoInvalido().add("Pedido Urgente ?");
                    }
                }
                else
                {
                    this.getListPedidoInvalido().add("Tipo Pagam. Invalido");
                }
            }
            else
            {
                this.getListPedidoInvalido().add("Nenhum Cliente");
            }
        }
        else
        {
            this.getListPedidoInvalido().add("Nenhum Produto");
        }
        return false;
    }
	@Override
	public void onResume()
	{
		super.onResume();
		lv.post(new Runnable() {
            @Override
			public void run() {
            	int i = lv.getAdapter().getCount()-1;
            	if(i >= 0)
            	{
            		lv.smoothScrollToPosition(i);
//            		lv.setSelection(i);
//            		View v = lv.getChildAt(i);
//            		if(v != null)
//            		{
//            			v.requestFocus();
//            		}
            	}
            }
        });
	}
	@Override
	public void onAttachedToWindow() {
	    // TODO Auto-generated method stub
	    this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
	    super.onAttachedToWindow();
	}
	
	public void onRadioButtonClicked(View v) {
	    // Perform action on clicks
	    RadioButton rb = (RadioButton) v;
	    Toast.makeText(this, rb.getText(), Toast.LENGTH_SHORT).show();
	}
	
	public void onRadioButtonProdutosTitulosClicked(View v)
	{
		RadioButton rb = (RadioButton) v;
	    Toast.makeText(this, rb.getText(), Toast.LENGTH_SHORT).show();
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
    		
    		AlertDialog.Builder sair = new AlertDialog.Builder(ClientePedidosTitulosViewActivity.this);
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
					Intent i = new Intent(ClientePedidosTitulosViewActivity.this.getApplicationContext(), LoginActivity.class);
		        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        	ClientePedidosTitulosViewActivity.this.getApplicationContext().startActivity(i);
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
    
    public List<String> getListPedidoInvalido()
    {
    	return this.listPedidoInvalido;
    }
    
    private Animation inFromRightAnimation() {

    	Animation inFromRight = new TranslateAnimation(
    	Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
    	Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
    	);
    	inFromRight.setDuration(500);
    	inFromRight.setInterpolator(new AccelerateInterpolator());
    	return inFromRight;
	}
	private Animation outToLeftAnimation() {
    	Animation outtoLeft = new TranslateAnimation(
    	 Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  -1.0f,
    	 Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
    	);
    	outtoLeft.setDuration(500);
    	outtoLeft.setInterpolator(new AccelerateInterpolator());
    	return outtoLeft;
	}
	private Animation inFromLeftAnimation() {
		Animation inFromLeft = new TranslateAnimation(
		Animation.RELATIVE_TO_PARENT,  -1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
		Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
		);
		inFromLeft.setDuration(500);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}
	private Animation outToRightAnimation() {
		Animation outtoRight = new TranslateAnimation(
		 Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  +1.0f,
		 Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
		);
		outtoRight.setDuration(500);
		outtoRight.setInterpolator(new AccelerateInterpolator());
		return outtoRight;
	}
}
