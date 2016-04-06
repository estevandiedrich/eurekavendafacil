package br.com.eurekasoftwares.tablet.adapter;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.R;
import br.com.eurekasoftwares.tablet.constantes.Constantes;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.vo.ProductFromPedidoVO;
import br.com.eurekasoftwares.tablet.vo.ProductVO;

public class ProdutoPedidoAdapter extends ArrayAdapter<ProductFromPedidoVO> implements Filterable{
	
	private List<ProductFromPedidoVO> dataProvider;
	private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	private AlertDialog.Builder builder;
	private Activity context;
	
	static class ViewHolder{
		private TextView codDescricao;
		private RadioGroup radioGroup;
		private ImageButton lockUnlock;
		private EditText quantidadeTroca;
		private EditText quantidadeVenda;
		private EditText desconto;
		private RadioButton produtoUrgente;
		private RadioButton produtoNaoUrgente;
		private Button removerProdutoPedido;
		private EditText precoUnitario;
		private EditText totalItemPedido;
	}
	
	public ProdutoPedidoAdapter(Activity context,List<ProductFromPedidoVO> dataProvider)
	{
		super(context,R.layout.produtos_list_item,dataProvider);
		this.mInflater = LayoutInflater.from(context.getApplicationContext());
		this.dataProvider = dataProvider;
		this.builder = new AlertDialog.Builder(context);
		this.builder.setMessage("Deseja remover este produto do pedido ?");
		this.builder.setCancelable(false);
		this.context = context;
		Application app = this.context.getApplication();
		this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)app;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataProvider.size();
	}

	@Override
	public ProductFromPedidoVO getItem(int arg0) {
		// TODO Auto-generated method stub
		return dataProvider.get(arg0);
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
			convertView = mInflater.inflate(R.layout.produtos_pedido_list_item, null);
			holder = new ViewHolder();
			
			holder.codDescricao = (TextView) convertView.findViewById(R.id.cod_descricao_produto_pedido);
			holder.radioGroup = (RadioGroup)convertView.findViewById(R.id.produto_urgente);
			holder.lockUnlock = (ImageButton)convertView.findViewById(R.id.lock_unlock_button);
			holder.quantidadeTroca = (EditText)convertView.findViewById(R.id.quantidade_troca);
			holder.quantidadeVenda = (EditText)convertView.findViewById(R.id.quantidade_venda);
			holder.produtoUrgente = (RadioButton)convertView.findViewById(R.id.produto_urgente_radio_button);
			holder.produtoNaoUrgente = (RadioButton)convertView.findViewById(R.id.produto_nao_urgente_radio_button);
			holder.desconto = (EditText)convertView.findViewById(R.id.desconto);
			holder.removerProdutoPedido = (Button)convertView.findViewById(R.id.remover_produto_pedido);
			holder.precoUnitario = (EditText)convertView.findViewById(R.id.preco_unitario);
			holder.totalItemPedido = (EditText)convertView.findViewById(R.id.total_item_pedido);
			
			convertView.setTag(holder);
			 
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		ProductFromPedidoVO p = dataProvider.get(position);
		holder.codDescricao.setText(p.getCod()+"/"+p.getDescricao());
		holder.precoUnitario.setText(p.getPreco());
		holder.totalItemPedido.setText(String.valueOf(p.getTotalItem()));
		holder.lockUnlock.setId(position);
		holder.quantidadeVenda.setId(position);
		holder.quantidadeTroca.setId(position);
		holder.desconto.setId(position);
		holder.removerProdutoPedido.setId(position);
		holder.radioGroup.setId(position);
		if(p.getUrgente().equalsIgnoreCase("S"))
		{
			holder.radioGroup.check(R.id.produto_urgente_radio_button);
		}
		else
		{
			holder.radioGroup.check(R.id.produto_nao_urgente_radio_button);
		}
		if(p.getQuantidadeTroca() != 0.0F)
		{
			holder.quantidadeTroca.setText(String.valueOf(p.getQuantidadeTroca()));
		}
		else
		{
			holder.quantidadeTroca.setText("");
		}
		if(p.getQuantidadeVenda() != 0)
		{
			holder.quantidadeVenda.setText(String.valueOf(p.getQuantidadeVenda()));
		}
		else
		{
			holder.quantidadeVenda.setText("");
		}
		if(p.getDescontoConcedido() != 0)
		{
			holder.desconto.setText(String.valueOf(p.getDescontoConcedido()));
		}
		else
		{
			holder.desconto.setText("");
		}
		if(p.getFechado().equalsIgnoreCase("S"))
		{
			holder.lockUnlock.setImageResource(R.drawable.lock);
			holder.quantidadeTroca.setEnabled(false);
			holder.quantidadeVenda.setEnabled(false);
			holder.desconto.setEnabled(false);
			holder.produtoUrgente.setEnabled(false);
			holder.produtoNaoUrgente.setEnabled(false);
			holder.removerProdutoPedido.setEnabled(false);
		}
		
		
		holder.radioGroup.setOnCheckedChangeListener(new ProdutoUrgenteRadioGroup());
		holder.removerProdutoPedido.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				final int position = v.getId();
				builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dataProvider.remove(position);
			     	    notifyDataSetInvalidated();
					}
				});
				builder.create().show();
			}
		});
		holder.lockUnlock.setOnClickListener(new LockUnlockButtonClick(holder));
		holder.quantidadeTroca.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus){
					final int position = v.getId();
					final EditText quantidadeTroca = (EditText) v;
					try
					{
						if(!quantidadeTroca.getText().toString().equalsIgnoreCase(""))
						{
							dataProvider.get(position).setQuantidadeTroca(Float.parseFloat(quantidadeTroca.getText().toString()));
						}
						else
						{
							dataProvider.get(position).setQuantidadeTroca(0.0F);
						}
					}
					catch(IndexOutOfBoundsException boundsException)
					{
						Log.w("Faiooo",boundsException);
					}
				}
			}
		});
		holder.quantidadeVenda.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus){
					final int position = v.getId();
					final EditText quantidadeVenda = (EditText) v;
					try
					{
						if(!quantidadeVenda.getText().toString().equalsIgnoreCase(""))
						{
							dataProvider.get(position).setQuantidadeVenda(Integer.parseInt(quantidadeVenda.getText().toString()));
						}
						else
						{
							dataProvider.get(position).setQuantidadeVenda(0);
						}
					}
					catch(IndexOutOfBoundsException boundsException)
					{
						Log.w("Faioooo", boundsException);
					}
				}
			}
		});
		holder.desconto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus)
				{
					final int position = v.getId();
					final EditText desconto = (EditText)v;
					try
					{
						if(!desconto.getText().toString().equalsIgnoreCase(""))
						{
							dataProvider.get(position).setDescontoConcedido(Integer.parseInt(desconto.getText().toString()));
						}
						else
						{
							dataProvider.get(position).setDescontoConcedido(0);
						}
					}
					catch(IndexOutOfBoundsException boundsException)
					{
						Log.w("Faiooooo",boundsException);
					}
				}
			}
		});
		
		this.builder.setNegativeButton("Não", new CancelaRemoverProdutoPedidoDialog());
		return convertView;
	}
	private class LockUnlockButtonClick implements Button.OnClickListener
	{
		private ViewHolder holder;
		public LockUnlockButtonClick(ViewHolder holder)
		{
			this.holder = holder;					
		}
		@Override
		public void onClick(View v) {
			final int position = v.getId();
			ProductFromPedidoVO productFromPedidoVO = dataProvider.get(position);
			ProductVO produto = casaDoQueijoLibraryApp.getDataManager().getProductVO(productFromPedidoVO.getCod());
			if(!holder.desconto.getText().toString().equalsIgnoreCase(""))
			{
				productFromPedidoVO.setDescontoConcedido(Integer.valueOf(holder.desconto.getText().toString()));
			}
			else
			{
				productFromPedidoVO.setDescontoConcedido(0);
			}
			float totalItemPedido = 0.0f;
			if(productFromPedidoVO.getFechado().equalsIgnoreCase("N"))
			{
				if(productFromPedidoVO.getDescontoConcedido() <= produto.getDesctoMax())
				{
					if(productFromPedidoVO.getQuantidadeTroca() <= productFromPedidoVO.getQuantidadeVenda())
					{
						
						holder.lockUnlock.setImageResource(R.drawable.lock);
						holder.quantidadeTroca.setEnabled(false);
						holder.quantidadeVenda.setEnabled(false);
						holder.desconto.setEnabled(false);
						holder.produtoUrgente.setEnabled(false);
						holder.produtoNaoUrgente.setEnabled(false);
						holder.removerProdutoPedido.setEnabled(false);
						productFromPedidoVO.setFechado("S");
						
						totalItemPedido = calculaTotalItemPedido(holder);
						
						holder.totalItemPedido.setText(String.valueOf(totalItemPedido));
						
						ModelSingleton.getInstance().getPedidoVO().setTotal(ModelSingleton.getInstance().getPedidoVO().getTotal()+totalItemPedido);
						EditText totalPedido = (EditText)ProdutoPedidoAdapter.this.context.findViewById(ModelSingleton.getInstance().getTotalPedido());
						totalPedido.setText(ModelSingleton.getInstance().getDecimalFormat().format(ModelSingleton.getInstance().getPedidoVO().getTotal()));
					}
					else
					{
						Dialog pedidoInvalidoDialog = new AlertDialog.Builder(ProdutoPedidoAdapter.this.context)
						.setTitle("Traca invalida.")
						.setMessage("Troca não pode ser maior doque venda.")
						.setPositiveButton("ok", null).create();
						pedidoInvalidoDialog.show();
					}
				}
				else
				{
					Dialog pedidoInvalidoDialog = new AlertDialog.Builder(ProdutoPedidoAdapter.this.context)
						.setTitle("Desconto invalido.")
						.setMessage("Desconto maximo para este produto é : " + produto.getDesctoMax())
						.setPositiveButton("ok", null).create();
					pedidoInvalidoDialog.show();
				}
			}
			else
			{
				holder.lockUnlock.setImageResource(R.drawable.unlock);
				holder.quantidadeTroca.setEnabled(true);
				holder.quantidadeVenda.setEnabled(true);
				holder.desconto.setEnabled(true);
				holder.produtoUrgente.setEnabled(true);
				holder.produtoNaoUrgente.setEnabled(true);
				holder.removerProdutoPedido.setEnabled(true);
				productFromPedidoVO.setFechado("N");
				
				totalItemPedido = calculaTotalItemPedido(holder);
				
				ModelSingleton.getInstance().getPedidoVO().setTotal(ModelSingleton.getInstance().getPedidoVO().getTotal()-totalItemPedido);
				EditText totalPedido = (EditText)ProdutoPedidoAdapter.this.context.findViewById(ModelSingleton.getInstance().getTotalPedido());
				totalPedido.setText(ModelSingleton.getInstance().getDecimalFormat().format(ModelSingleton.getInstance().getPedidoVO().getTotal()));
			}
			productFromPedidoVO.setTotalItem(totalItemPedido);
		}
	}
	private class ProdutoUrgenteRadioGroup implements RadioGroup.OnCheckedChangeListener
	{
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			final int position = group.getId();
			if(checkedId == R.id.produto_urgente_radio_button)
			{
				dataProvider.get(position).setUrgente("S");
			}
			else
			{
				dataProvider.get(position).setUrgente("N");
			}
		}
	}
	private class CancelaRemoverProdutoPedidoDialog implements DialogInterface.OnClickListener
	{
		@Override
		public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
       }
	}
	
	public void setContents(List<ProductFromPedidoVO> strs) {
		Collections.sort(strs);
		dataProvider.clear();
		dataProvider.addAll(strs);
	}
	private float calculaTotalItemPedido(ViewHolder holder)
	{
		int quantidadeVenda = 0;
		try
		{
			quantidadeVenda = Integer.valueOf(holder.quantidadeVenda.getText().toString());
		}
		catch(Exception exception)
		{
			Log.w(Constantes.EUREKA_SOFTWARES, "ProdutoPedidoAdapter");
			Log.w(Constantes.EUREKA_SOFTWARES, exception);
		}
		int desconto = 0;
		try
		{
			desconto = Integer.valueOf(holder.desconto.getText().toString());
		}
		catch(Exception e)
		{
			Log.w(Constantes.EUREKA_SOFTWARES, "ProdutoPedidoAdapter");
			Log.w(Constantes.EUREKA_SOFTWARES, e);
		}
		float preco = 0.0f;
		float totalItemPedido = 0.0f;
		try
		{
			preco = Float.valueOf(holder.precoUnitario.getText().toString());
			if(desconto>0)
			{
				float quantidadeVendaPreco = (quantidadeVenda*preco);
				float descontoPercentual = (desconto/100.0f);
				float valorDescontoItem = descontoPercentual*quantidadeVendaPreco;
				totalItemPedido = quantidadeVendaPreco-valorDescontoItem;
			}
			else
			{
				totalItemPedido = (quantidadeVenda*preco);
			}
		}
		catch(Exception e)
		{
			Log.w(Constantes.EUREKA_SOFTWARES, "ProdutoPedidoAdapter");
			Log.w(Constantes.EUREKA_SOFTWARES, e);
		}
		return totalItemPedido;
	}
}
