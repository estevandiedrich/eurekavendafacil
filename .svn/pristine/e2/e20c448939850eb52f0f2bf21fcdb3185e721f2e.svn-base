package br.com.eurekasoftwares.tablet.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import br.com.eurekasoftwares.tablet.R;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.vo.PedidoVO;

public class PedidoAdapter extends BaseAdapter implements Filterable{
	private List<PedidoVO> items;
	private List<PedidoVO> removidos;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	private Filter myFilter;
	private PedidoVO p;
	private final Object lock = new Object();
	
	static class ViewHolder{
		private TextView codPedido;
		private TextView codCliente;
		private TextView dataEmissao;
		private TextView urgente;
		private TextView tipoPagamento;
		private TextView prazo;
		private TextView sincronizado;
	}
	
	public PedidoAdapter(Activity activity,List<PedidoVO> items)
	{
		this.mInflater = LayoutInflater.from(activity.getApplicationContext());
		this.removidos = new ArrayList<PedidoVO>();
		this.myFilter = new MyFilter();
		this.items = items;
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
			convertView = mInflater.inflate(R.layout.pedidos_list_item, null);
			holder = new ViewHolder();
 
			holder.codPedido = (TextView) convertView.findViewById(R.id.cod_pedido);
			holder.codCliente = (TextView) convertView.findViewById(R.id.cod_cliente);
			holder.dataEmissao = (TextView) convertView.findViewById(R.id.data_emissao);
			holder.urgente = (TextView) convertView.findViewById(R.id.urgente);
			holder.tipoPagamento = (TextView) convertView.findViewById(R.id.pagamento);
			holder.prazo = (TextView) convertView.findViewById(R.id.prazo);
			holder.sincronizado = (TextView) convertView.findViewById(R.id.sincronizado);
 
			convertView.setTag(holder);
 
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		p = items.get(position);
 
		holder.codPedido.setText(String.valueOf(p.getCodigoPedido()));
		holder.codCliente.setText(p.getCustomerCod());
		holder.dataEmissao.setText(ModelSingleton.getInstance().getSimpleDateFormatBR().format(p.getDataEmissao()));
		holder.urgente.setText(p.getUrgente());
		holder.tipoPagamento.setText(p.getTipoPagamento());
		holder.prazo.setText(p.getPrazo());
		holder.sincronizado.setText(p.getSincronizado().equalsIgnoreCase("1")?"Sim":"Não");
		
		View rowView = convertView;
        //all your inflation and setting values goes here and at the end
        //add listener to the button also and also to the row view
		
//        rowView.setOnClickListener( new View.OnClickListener()
//        {
//            public void onClick(View v)
//            {
//    	    	ModelSingleton.getInstance().setPedidoVO(p);
//    	    	Intent i = new Intent(v.getContext().getApplicationContext(), PedidoActivity.class);
//		    	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				v.getContext().getApplicationContext().startActivity(i);
//            }
//        });
        return rowView;
	}
	
	public void add(PedidoVO PedidoVO)
	{
		int index = Collections.binarySearch(this.items, PedidoVO);
		if (index < 0) {
			this.items.add(-index-1, PedidoVO);
		}
		else
		{
			this.items.add(index-1,PedidoVO);
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
	
	public void setContents(List<PedidoVO> strs) {
		Collections.sort(strs);
		items.clear();
		items.addAll(strs);
	}
	
	private final class MyFilter extends Filter {
		private List<PedidoVO> filtrados = new ArrayList<PedidoVO>();
		private List<PedidoVO> items = new ArrayList<PedidoVO>();
   		@Override
   		protected FilterResults performFiltering(CharSequence constraint) 
   		{
   			synchronized(lock)
   			{
               // Search through your original list 
       			filtrados.clear();
       			items.clear();
       			Filter.FilterResults results = new Filter.FilterResults();
       			items.addAll(PedidoAdapter.this.items);
       			
   				items.addAll(removidos);
        		removidos.clear();
            	results.count = items.size();
            	results.values = items;

            	int size = items.size();
            	for (int i = 0;i < size;i++) 
            	{
            		PedidoVO PedidoVO = items.get(i);
            		if (matches(PedidoVO, constraint)) 
            		{
            			items.remove(i);
            			size--;i--;
            			filtrados.add(PedidoVO);
            		}
            	}
        		removidos.addAll(items);
            	results.count = filtrados.size();
            	results.values = filtrados;
	            return results;
       		}
   		}

       @SuppressWarnings("unchecked")
       @Override
       protected void publishResults(CharSequence constraint, FilterResults results)
       {
    	    synchronized(lock)
   			{
    		   setContents(new ArrayList<PedidoVO>((List<PedidoVO>)results.values));
    		   PedidoAdapter.this.notifyDataSetChanged();
   			}
       }

       public boolean matches(PedidoVO pedido, CharSequence value) {
          /// implement this part
    	   String lowerCaseConstrait = value.toString().toLowerCase();
    	   String dataEmissao = ModelSingleton.getInstance().getSimpleDateFormatBR().format(pedido.getDataEmissao());
    	   if(pedido.getCustomerCod().toLowerCase().contains(lowerCaseConstrait) || dataEmissao.contains(lowerCaseConstrait))
    	   {
    		   return true;
           }
    	   return false;
        }
    }
}
