package br.com.eurekasoftwares.tablet.pedido;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.Toast;
import br.com.eurekasoftwares.tablet.R;
import br.com.eurekasoftwares.tablet.constantes.Constantes;
import br.com.eurekasoftwares.tablet.login.LoginActivity;

public class PedidoActivity extends Activity {
//	private ProdutoPedidoAdapter adapter;
//	private CasaDoQueijoLibraryApp casaDoQueijoLibraryApp;
//	private EditText editTextCliente;
//	private AlertDialog.Builder builder;
//	private ListView lv;
//	private long result = 0l;
//	private List<String> listPedidoInvalido = new ArrayList<String>();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido);
        Log.w(Constantes.EUREKA_SOFTWARES, "PedidoActivity");
        
//        if(ModelSingleton.getInstance().getEnviaPedidosThread() == null)
//		{
//			ModelSingleton.getInstance().setEnviaPedidosThread(new EnviaPedidosThread(this));
//			ModelSingleton.getInstance().getEnviaPedidosThread().start();
//		}
        
//        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
//        KeyguardManager.KeyguardLock lock = keyguardManager
//                .newKeyguardLock(KEYGUARD_SERVICE);
//        lock.disableKeyguard();
        
        
//        this.builder = new AlertDialog.Builder(this);
//		this.builder.setMessage("Finalizar pedido ?");
//		this.builder.setCancelable(false);
//        
//        Application app = this.getApplication();
//    	this.casaDoQueijoLibraryApp = (CasaDoQueijoLibraryApp)app;
//    	
//        adapter = new ProdutoPedidoAdapter(this, ModelSingleton.getInstance().getPedidoVO().getProducts());
        
//        editTextCliente = (EditText) findViewById(R.id.cliente_pedido);
//        editTextCliente.setText(ModelSingleton.getInstance().getPedidoVO().getCustomerCod());
//        editTextCliente.setOnClickListener(new EditText.OnClickListener() {
//        	
//        	public void onClick(View v) {
//        		Intent i = new Intent(getApplicationContext(), ClientesListViewActivity.class);
//        		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        		startActivity(i);
//        	}
//        });
        
//        Button adionaProdutoPedido = (Button)findViewById(R.id.adiciona_produto_pedido);
//        adionaProdutoPedido.setOnClickListener(new Button.OnClickListener() {
//			
//			public void onClick(View v) {
//				boolean pedidoValido = true;
//				for(ProductFromPedidoVO produtoSelecionado:ModelSingleton.getInstance().getPedidoVO().getAllProducts())
//				{
//			        if(produtoSelecionado.getQuantidadeTroca() == 0.0f)
//			        {
//			            if(produtoSelecionado.getQuantidadeVenda() == 0)
//			            {
//			                Dialog pedidoInvalidoDialog = new AlertDialog.Builder(PedidoActivity.this)
//								.setTitle("Pedido invalido.")
//								.setMessage("A Quantidade Venda e Quantidade Troca nao podem ser ambas 0")
//								.setPositiveButton("ok", null).create();
//							pedidoInvalidoDialog.show();
//							pedidoValido = false;
//			                break;
//			            }
//			        }
//				}
//				if(pedidoValido)
//				{
//					Intent i = new Intent(getApplicationContext(), ProdutosListViewActivity.class);
//					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(i);
//				}
//			}
//        });
//        final Button opcoesPedido = (Button)findViewById(R.id.opcoes_pedido);
//	      opcoesPedido.setOnClickListener(new Button.OnClickListener() {
//				
//				public void onClick(View v) {
//					Intent i = new Intent(getApplicationContext(), OpcoesPedidoViewActivity.class);
//					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(i);
//				}
//	        });
//	    if(ModelSingleton.getInstance().getPedidoVO().getCustomerCod().equalsIgnoreCase(""))
//	    {
//	    	opcoesPedido.setEnabled(false);
//	    }
//        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.produtos_titulos);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
//        {
//			@Override
//			public void onCheckedChanged(RadioGroup group, int checkedId) {
//				// TODO Auto-generated method stub
//				if(checkedId == R.id.produtos_radio_button)
//				{
//					opcoesPedido.setVisibility(View.VISIBLE);
//				}
//				else
//				{
//					opcoesPedido.setVisibility(View.INVISIBLE);
//				}
//			}
//        });
        
        // Listening to register new account link
//        lv = (ListView)findViewById(R.id.produtos_pedido);
//        lv.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
//        lv.setStackFromBottom(true);
//        lv.setTextFilterEnabled(true);
//        lv.setOnItemClickListener(new OnItemClickListener() {
//  	    public void onItemClick(AdapterView<?> parent, View view,
//    		int position, long id) {
//  	    	// When clicked, show a toast with the TextView text
//  	    	TextView codDescricao = (TextView)view.findViewById(R.id.cod_descricao_produto_pedido);
//  	    	Toast.makeText(getApplicationContext(), codDescricao.getText().toString(),
//  	    			Toast.LENGTH_SHORT).show();
//  	    	}
//        });
//        lv.setAdapter(adapter);
//        Button finalizarPedido = (Button)findViewById(R.id.finalizar_pedido);
//        finalizarPedido.setOnClickListener(new Button.OnClickListener(){
//		public void onClick(View v)
//		{
//  			builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					// TODO Auto-generated method stub
//					ModelSingleton.getInstance().getPedidoVO().setDataEmissao(new Date());
//					if(methodValidaPedido())
//					{
//						PedidoVO pedidoVO = casaDoQueijoLibraryApp.getDataManager().getPedidoVO(ModelSingleton.getInstance().getPedidoVO().getCodigoPedido());
//						if(pedidoVO == null)
//						{
//							result = casaDoQueijoLibraryApp.getDataManager().savePedido(ModelSingleton.getInstance().getPedidoVO());
//						}
//						else
//						{
//							AlertDialog.Builder salvarAlteracoes = new AlertDialog.Builder(PedidoActivity.this);
//							salvarAlteracoes.setMessage("Salvar alterações ?");
//							salvarAlteracoes.setNegativeButton("Não", new DialogInterface.OnClickListener() {
//								
//			  					@Override
//								public void onClick(DialogInterface dialog, int which) {
//									// TODO Auto-generated method stub
//					            	dialog.cancel();
//					            	ModelSingleton.getInstance().getPedidoVO().getAllProducts().clear();
//						  			ModelSingleton.getInstance().setPedidoVO(new PedidoVO());
//						  			finish();
//						  			startActivity(getIntent()); 
//								}
//			  				});
//							salvarAlteracoes.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//								
//								@Override
//								public void onClick(DialogInterface dialog, int which) {
//									// TODO Auto-generated method stub
//										ModelSingleton.getInstance().getPedidoVO().setSincronizado("0");
//										casaDoQueijoLibraryApp.getDataManager().updateProdutosPedido(ModelSingleton.getInstance().getPedidoVO());
//										result = 1;
//										salvarAlteracoes(result);
//								}
//							});
//							salvarAlteracoes.create().show();
//						}
//						salvarAlteracoes(result);
//					}
//					else
//					{
//						String msg = "";
//						for(String msgPart:listPedidoInvalido)
//						{
//							msg += "\n"+msgPart;
//						}
//						Dialog pedidoInvalidoDialog = new AlertDialog.Builder(PedidoActivity.this)
//							.setTitle("Pedido invalido.")
//							.setMessage(msg)
//							.setPositiveButton("ok", null).create();
//						pedidoInvalidoDialog.show();
//					}
//				}
//			});
//  			builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
//				
//  					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//		            	dialog.cancel();
//					}
//  				});
//  				builder.create().show();
//			}
//  	  	});
//		if(ModelSingleton.getInstance().getPedidoVO().getCustomerCod().equalsIgnoreCase(""))
//		{
//			adionaProdutoPedido.setEnabled(false);
//			finalizarPedido.setEnabled(false);
//		}
//		EditText totalPedido = (EditText)findViewById(R.id.total_pedido);
//		ModelSingleton.getInstance().setTotalPedido(R.id.total_pedido);
//		totalPedido.setText(String.valueOf(ModelSingleton.getInstance().getPedidoVO().getTotal()));
	}
	public void makeUseOfNewLocation(Location location)
	{
		
	}
//	public void salvarAlteracoes(long result)
//	{
//		if(result != 0)
//		{
//			ClienteDiaPedidoVO clienteDiaPedidoVO = new ClienteDiaPedidoVO();
//	        clienteDiaPedidoVO.setCodCliente(ModelSingleton.getInstance().getPedidoVO().getCustomerCod());
//	        clienteDiaPedidoVO.setDia(ModelSingleton.getInstance().getPedidoVO().getDataEmissao());
//	        clienteDiaPedidoVO.setCodPedido(ModelSingleton.getInstance().getPedidoVO().getCodigoPedido());					        
//	        result = casaDoQueijoLibraryApp.getDataManager().saveClienteDiaPedido(clienteDiaPedidoVO);
//	        if(result != 0)
//	        {	
//	        	for(ProductFromPedidoVO fromPedidoVO:ModelSingleton.getInstance().getPedidoVO().getAllProducts())
//	        	{
//	        		ClienteProdutoQuantidadeVO clienteProdutoQuantidadeVO = new ClienteProdutoQuantidadeVO();
//	        		clienteProdutoQuantidadeVO.setCodCliente(ModelSingleton.getInstance().getPedidoVO().getCustomerCod());
//	        		clienteProdutoQuantidadeVO.setCodProduto(fromPedidoVO.getCod());
//	        		clienteProdutoQuantidadeVO.setQuantidade(fromPedidoVO.getQuantidadeVenda());
//	        		result = casaDoQueijoLibraryApp.getDataManager().saveClienteProdutoQuantidade(clienteProdutoQuantidadeVO);
//	        		if(result == 0)
//	        		{
//	        			ErroVO erroVO = new ErroVO();
//	        			erroVO.setMensagem("Falha ao salvar historico de produtos por cliente");
//	        			casaDoQueijoLibraryApp.getDataManager().saveErro(erroVO);
//	        		}
//	        	}
//	        	ModelSingleton.getInstance().getPedidoVO().getAllProducts().clear();
//	  			ModelSingleton.getInstance().setPedidoVO(new PedidoVO());
//	  			finish();
//	  			startActivity(getIntent()); 
////	  			adapter.notifyDataSetInvalidated();
////	  			editTextCliente.setText("");
//	        }
//		}
//	}
	@Override
	public void onAttachedToWindow() {
	    // TODO Auto-generated method stub
	    this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
	    super.onAttachedToWindow();
	}

//	private boolean methodValidaPedido()
//    {
//        this.getListPedidoInvalido().clear();
//        if(ModelSingleton.getInstance().getPedidoVO().getAllProducts().size() > 0)
//        {
//            if(!ModelSingleton.getInstance().getPedidoVO().getCustomerCod().equalsIgnoreCase(""))
//            {
//                if(!ModelSingleton.getInstance().getPedidoVO().getTipoPagamento().equalsIgnoreCase(""))
//                {
//                    if(!ModelSingleton.getInstance().getPedidoVO().getUrgente().equalsIgnoreCase(""))
//                    {
//                        if(!ModelSingleton.getInstance().getPedidoVO().getPrazo().equalsIgnoreCase(""))
//                        {
//                        	for(ProductFromPedidoVO produtoSelecionado:ModelSingleton.getInstance().getPedidoVO().getAllProducts())
//            				{
//                        		if(ModelSingleton.getInstance().getPedidoVO().getTipoPagamento().equalsIgnoreCase("COB") || ModelSingleton.getInstance().getPedidoVO().getUrgente().equalsIgnoreCase("S"))
//                                {
//                        			produtoSelecionado.setUrgente("S");
//                                }
//            			        if(produtoSelecionado.getQuantidadeTroca() == 0.0f)
//            			        {
//            			            if(produtoSelecionado.getQuantidadeVenda() == 0)
//            			            {
//            							this.getListPedidoInvalido().add("A Quantidade Venda e Quantidade Troca nao podem ser ambas 0");
//            			                return false;
//            			            }
//            			        }
//            				}
//                        	return true;
//                        }
//                        else
//                        {
//                            this.getListPedidoInvalido().add("Prazo nao Inform.");
//                        }
//                    }
//                    else
//                    {
//                        this.getListPedidoInvalido().add("Pedido Urgente ?");
//                    }
//                }
//                else
//                {
//                    this.getListPedidoInvalido().add("Tipo Pagam. Invalido");
//                }
//            }
//            else
//            {
//                this.getListPedidoInvalido().add("Nenhum Cliente");
//            }
//        }
//        else
//        {
//            this.getListPedidoInvalido().add("Nenhum Produto");
//        }
//        return false;
//    }
	@Override
	public void onResume()
	{
		super.onResume();
//		lv.post(new Runnable() {
//            public void run() {
//            	int i = lv.getAdapter().getCount()-1;
//            	if(i >= 0)
//            	{
//            		lv.smoothScrollToPosition(i);
//            		lv.setSelection(i);
//            		View v = lv.getChildAt(i);
//            		if(v != null)
//            		{
//            			v.requestFocus();
//            		}
//            	}
//            }
//        });
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
//            finish();
//            System.runFinalizersOnExit(true);
//            System.exit(0);
//    		android.os.Process.killProcess(android.os.Process.myPid());
    		Intent i = new Intent(this.getApplicationContext(), LoginActivity.class);
        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		this.getApplicationContext().startActivity(i);
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

//    public List<String> getListPedidoInvalido()
//    {
////    	return this.listPedidoInvalido;
//    }
}
