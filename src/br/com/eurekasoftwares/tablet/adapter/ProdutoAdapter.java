package br.com.eurekasoftwares.tablet.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.R;
import br.com.eurekasoftwares.tablet.cliente.ClientePedidosTitulosViewActivity;
import br.com.eurekasoftwares.tablet.img.SolicitaImagemProdutoAsyncTask;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.vo.ClienteProdutoQuantidadeVO;
import br.com.eurekasoftwares.tablet.vo.ProductFromPedidoVO;
import br.com.eurekasoftwares.tablet.vo.ProductVO;

public class ProdutoAdapter extends BaseAdapter implements Filterable{

	private List<ProductVO> items;
	private List<ProductVO> removidos;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	private Filter myFilter;
	private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
	private Activity context;
	private ProductFromPedidoVO produtoSelecionado;
	private View v;
	private ProductVO p;
	private final Object lock = new Object();
	
	static class ViewHolder{
		private TextView codDescricao;
		private TextView precoProduto;
		private TextView descMax;
		private TextView urgente;
		private TextView unidade;
		private TextView estoque;
		private ImageButton produtoImageButton;
	}
	
	public ProdutoAdapter(Activity activity,List<ProductVO> items)
	{
		this.mInflater = LayoutInflater.from(activity.getApplicationContext());
		this.removidos = new ArrayList<ProductVO>();
		this.myFilter = new MyFilter();
		this.items = items;
		this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)activity.getApplication();
		this.context = activity;
		//Collections.copy(this.items, items);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return items.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.produtos_list_item, null);
			holder = new ViewHolder();
 
			holder.codDescricao = (TextView) convertView.findViewById(R.id.cod_descricao);
			holder.precoProduto = (TextView) convertView.findViewById(R.id.preco_produto);
			holder.descMax = (TextView) convertView.findViewById(R.id.desc_max);
			holder.urgente = (TextView) convertView.findViewById(R.id.urgente);
			holder.unidade = (TextView) convertView.findViewById(R.id.unidade);
			holder.estoque = (TextView) convertView.findViewById(R.id.estoque);
			holder.produtoImageButton = (ImageButton) convertView.findViewById(R.id.imagem_produto);
 
			convertView.setTag(holder);
 
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		p = items.get(position);
 
		holder.codDescricao.setText(p.getCod()+"/"+p.getDescricao());
		holder.precoProduto.setText(String.valueOf(p.getPreco()));
		holder.descMax.setText(ModelSingleton.getInstance().getDecimalFormat().format(p.getDesctoMax()));
		holder.urgente.setText(p.getUrgente());
		holder.unidade.setText(p.getUnidade());
		holder.estoque.setText(String.valueOf(p.getQuantidade()));
		holder.produtoImageButton.setId(position);
		holder.produtoImageButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				ProductVO prod = items.get(v.getId());
				ModelSingleton.getInstance().setCodProdutoSelecionado(prod.getCod());
				
				items.addAll(removidos);
    	    	Collections.sort(items);
        		removidos.clear();
        		
				new SolicitaImagemProdutoAsyncTask(ProdutoAdapter.this.context).execute(prod.getCod());
			}
        });
		View rowView = convertView;
        //all your inflation and setting values goes here and at the end
        //add listener to the button also and also to the row view
		
        rowView.setOnClickListener( new View.OnClickListener()
        {
            @Override
			public void onClick(View v)
            {
            	ProdutoAdapter.this.v = v;
                //Toast.makeText(rowView,"Item in position " + position + " clicked", Toast.LENGTH_LONG).show();
            	produtoSelecionado = new ProductFromPedidoVO();
    	    	TextView codDescricao = (TextView)v.findViewById(R.id.cod_descricao);
    	    	produtoSelecionado.setCod(codDescricao.getText().toString().substring(0, codDescricao.getText().toString().indexOf("/")));
    	    	produtoSelecionado.setDescricao(codDescricao.getText().toString().substring(codDescricao.getText().toString().indexOf("/")+1));
    	    	TextView preco = (TextView)v.findViewById(R.id.preco_produto);
    	    	produtoSelecionado.setPreco(preco.getText().toString());
    	    	produtoSelecionado.setQuantidadeTroca(0.0F);
    	    	produtoSelecionado.setQuantidadeVenda(0);
    	    	TextView urgente = (TextView)v.findViewById(R.id.urgente);
    	    	produtoSelecionado.setUrgente(urgente.getText().toString());
    	    	produtoSelecionado.setIdx(ModelSingleton.getInstance().getPedidoVO().getProducts().size()+1);
    	    	
    	    	if(methodValidaProdutoPedido(produtoSelecionado))
    	    	{
//    	    		if(verificarFrequenciaConsumoProdutoCliente(produtoSelecionado.getCod()))
//                	{
        	    		adicionaProdutoPedido(produtoSelecionado,ProdutoAdapter.this.v);
//                	}
//        	    	else
//        	    	{
//        	    		AlertDialog.Builder dialog = new AlertDialog.Builder(ProdutoAdapter.this.context);
//    	    			dialog.setTitle("Atenção.");
//    					dialog.setMessage("O produto "+produtoSelecionado.getCod()+" nunca foi vendido ao cliente "+ModelSingleton.getInstance().getPedidoVO().getCustomerCod()+" deseja prosseguir?");
//    					dialog.setCancelable(false);
//    					dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//    						
//    						@Override
//    						public void onClick(DialogInterface dialog, int which) {
//    							adicionaProdutoPedido(produtoSelecionado,ProdutoAdapter.this.v);
//    						}
//    					});
//    					dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
//    						
//    						@Override
//    						public void onClick(DialogInterface dialog, int which) {
//    							// TODO Auto-generated method stub
//    				            dialog.cancel();
//    						}
//    					});
//    	    			dialog.create().show();
//        	    	}
    	    	}
    	    	items.addAll(removidos);
    	    	Collections.sort(items);
        		removidos.clear();
            }
        });
        return rowView;
		//return convertView;
	}
	private void adicionaProdutoPedido(ProductFromPedidoVO produtoSelecionado,View v)
	{
		ModelSingleton.getInstance().getPedidoVO().addProduct(produtoSelecionado);
    	Collections.sort(ModelSingleton.getInstance().getPedidoVO().getProducts());
    	notifyDataSetChanged();
    	
    	Intent i = new Intent(v.getContext().getApplicationContext(), ClientePedidosTitulosViewActivity.class);
    	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		v.getContext().getApplicationContext().startActivity(i);
	}
	public void add(ProductVO productVO)
	{
		int index = Collections.binarySearch(this.items, productVO);
		if (index < 0) {
			this.items.add(-index-1, productVO);
		}
		else
		{
			this.items.add(index-1,productVO);
		}
	}
	public void clear()
	{
		this.items.clear();
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return this.myFilter;
	}
	
	private final class MyFilter extends Filter {
   		@Override
   		protected FilterResults performFiltering(CharSequence constraint) 
   		{
   			synchronized(lock)
   			{
       			Filter.FilterResults results = new Filter.FilterResults();
       			
   				items.addAll(removidos);
        		removidos.clear();

            	int size = items.size();
            	for (int i = 0;i < size;i++) 
            	{
            		ProductVO productVO = items.get(i);
            		if (!matches(productVO, constraint)) 
            		{
            			items.remove(i);
            			size--;i--;
            			removidos.add(productVO);
            		}
            	}
            	results.count = items.size();
            	results.values = items;
	            return results;
       		}
   		}

       @Override
       protected void publishResults(CharSequence constraint, FilterResults results)
       {
    	    synchronized(lock)
   			{
	    		Collections.sort(items);
	    		ProdutoAdapter.this.notifyDataSetChanged();
   			}
       }

       public boolean matches(ProductVO product, CharSequence value) {
          /// implement this part
    	   String lowerCaseConstrait = value.toString().toLowerCase();
    	   String cod = "";
    	   for(int i = 0;i < product.getCod().length();i++)
    	   {
    		   if(product.getCod().charAt(i) != '0')
    		   {
    			   cod = product.getCod().substring(i);
    			   break;
    		   }
    	   }
    	   if(product.getDescricao().toLowerCase().startsWith(lowerCaseConstrait) || cod.equalsIgnoreCase(lowerCaseConstrait))
    	   {
    		   return true;
           }
    	   return false;
        }
    }
	private boolean verificarFrequenciaConsumoProdutoCliente(String codigoProduto)
    {
        if(ModelSingleton.getInstance().getPedidoVO().getCustomerCod() != null && !ModelSingleton.getInstance().getPedidoVO().getCustomerCod().equalsIgnoreCase(""))
        {
        	ClienteProdutoQuantidadeVO clienteProdutoQuantidadeVO = casaDoQueijoLibraryApp.getDataManager().findClienteProdutoQuantidadeVOPorCodProdutoECodCliente(codigoProduto,ModelSingleton.getInstance().getPedidoVO().getCustomerCod());
            if(clienteProdutoQuantidadeVO != null)
            {
                return true;
            }
            else
            {
        		return false;
            }
        }
        else
        {
            return true;
        }
    }
	private boolean methodValidaProdutoPedido(ProductFromPedidoVO produtoSelecionado)
    {

        for(int i = 0;i < ModelSingleton.getInstance().getPedidoVO().getAllProducts().size();i++)
        {
            ProductFromPedidoVO productFromPedidoVO = ModelSingleton.getInstance().getPedidoVO().getProductAt(i);
            if(produtoSelecionado.getCod().equalsIgnoreCase(productFromPedidoVO.getCod()))
            {
                Dialog pedidoInvalidoDialog = new AlertDialog.Builder(this.context)
					.setTitle("Pedido invalido.")
					.setMessage("Produto já consta no pedido.")
					.setPositiveButton("ok", null).create();						
				pedidoInvalidoDialog.show();
                return false;
            }
        }
        return true;
    }

	public List<ProductVO> getItems() {
		return items;
	}

	public void setItems(List<ProductVO> items) {
		this.items = items;
	}

	public List<ProductVO> getRemovidos() {
		return removidos;
	}

	public void setRemovidos(List<ProductVO> removidos) {
		this.removidos = removidos;
	}
}
