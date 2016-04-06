package br.com.eurekasoftwares.tablet.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import br.com.eurekasoftwares.tablet.map.MapasSimplesActivity;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.titulo.SolicitaTitulosListAsyncTask;
import br.com.eurekasoftwares.tablet.vo.ClienteDiaPedidoVO;
import br.com.eurekasoftwares.tablet.vo.CustomerVO;

public class ClienteAdapter extends BaseAdapter implements Filterable{
	
	private List<CustomerVO> items;
	private List<CustomerVO> removidos;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	private Filter myFilter;
	private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
	private Activity context;
	private CustomerVO customerVO;
	private CustomerVO p;
	private final Object lock = new Object();
	
	static class ViewHolder{
		private TextView nomeFantasia;
		private TextView contatoCliente;
		private TextView cidadeCliente;
		private TextView bairroCliente;
		private TextView ruaCliente;
		private ImageButton mapaImageButton;
	}
	public ClienteAdapter(Activity activity,List<CustomerVO> items)
	{
		this.mInflater = LayoutInflater.from(activity.getApplicationContext());
		this.removidos = new ArrayList<CustomerVO>();
		this.myFilter = new MyFilter();
		this.items = items;
    	this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)activity.getApplication();
    	this.context = activity;
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
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.clientes_list_item, null);
			holder = new ViewHolder();
 
			holder.nomeFantasia = (TextView) convertView.findViewById(R.id.nome_fantasia);
			holder.contatoCliente = (TextView) convertView.findViewById(R.id.contato_cliente);
			holder.cidadeCliente = (TextView) convertView.findViewById(R.id.cidade_cliente);
			holder.bairroCliente = (TextView) convertView.findViewById(R.id.bairro_cliente);
			holder.ruaCliente = (TextView) convertView.findViewById(R.id.rua_cliente);
			holder.mapaImageButton = (ImageButton) convertView.findViewById(R.id.mapa_image_button);
 
			convertView.setTag(holder);
 
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		 p = items.get(position);
 
		holder.nomeFantasia.setText(p.getNomeFantasia());
		holder.contatoCliente.setText(p.getContato());
		holder.cidadeCliente.setText(p.getCidade());
		holder.bairroCliente.setText(p.getBairro());
		holder.ruaCliente.setText(p.getRua());
		holder.mapaImageButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ModelSingleton.getInstance().setClienteMapa(p);
				Intent i = new Intent(v.getContext().getApplicationContext(), MapasSimplesActivity.class);
		    	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				v.getContext().getApplicationContext().startActivity(i);
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
                    //Toast.makeText(rowView,"Item in position " + position + " clicked", Toast.LENGTH_LONG).show();
    				TextView nomeFantasia = (TextView)v.findViewById(R.id.nome_fantasia);
    				ClienteAdapter.this.customerVO = casaDoQueijoLibraryApp.getDataManager().getCustomerVO(nomeFantasia.getText().toString());
    				//customerVO.setNomeFantasia(nomeFantasia.getText().toString());
    				if(!verificaClienteBloqueado(customerVO))
    				{
    					if(methodValidaClientePedido(customerVO.getNomeFantasia()))
    					{
    						ModelSingleton.getInstance().getPedidoVO().setCustomerCod(ClienteAdapter.this.customerVO.getNomeFantasia());
    						
    						new SolicitaTitulosListAsyncTask(ClienteAdapter.this.context,ModelSingleton.getInstance().getTitulos()).execute(ModelSingleton.getInstance().getPedidoVO().getCustomerCod());
    						
    					}
    					else
    					{
    						AlertDialog.Builder dialog = new AlertDialog.Builder(context);
    						dialog.setTitle("Atenção.");
    						dialog.setMessage("Já existe um pedido para este cliente lançado hoje.");
    						dialog.setPositiveButton("Continuar", new DialogInterface.OnClickListener() 
    						{
        						@Override
        						public void onClick(DialogInterface dialog, int which) {
        							ModelSingleton.getInstance().getPedidoVO().setCustomerCod(ClienteAdapter.this.customerVO.getNomeFantasia());
        							
        							new SolicitaTitulosListAsyncTask(ClienteAdapter.this.context,ModelSingleton.getInstance().getTitulos()).execute(ModelSingleton.getInstance().getPedidoVO().getCustomerCod());
            	    				
        						}
        					});
        					dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() 
        					{
        						@Override
        						public void onClick(DialogInterface dialog, int which) {
        							// TODO Auto-generated method stub
        				            dialog.cancel();
        						}
        					});
	    					dialog.create().show();
    					}
    				}
    				else
    				{
    					Dialog dialog = new AlertDialog.Builder(context)
    						.setTitle("Falha")
    						.setMessage("Cliente bloqueado.")
    						.setPositiveButton("ok", null).create();
    					dialog.show();
    				}
    				items.addAll(removidos);
    				Collections.sort(items);
            		removidos.clear();
                }
            });
        return rowView;
	}
	public void add(CustomerVO customerVO)
	{
		int index = Collections.binarySearch(this.items, customerVO);
		if (index < 0) {
			this.items.add(-index-1, customerVO);
		}
		else
		{
			this.items.add(index-1,customerVO);
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
	
	public void setContents(List<CustomerVO> strs) {
		Collections.sort(strs);
		items.clear();
		items.addAll(strs);
	}
	
	private final class MyFilter extends Filter {
   		@Override
   		protected FilterResults performFiltering(CharSequence constraint) 
   		{
   			synchronized(lock)
   			{
               // Search through your original list 
       			Filter.FilterResults results = new Filter.FilterResults();
       			
       			items.addAll(removidos);
        		removidos.clear();
        		
            	int size = items.size();
            	for (int i = 0;i < size;i++) 
            	{
            		CustomerVO CustomerVO = items.get(i);
            		if (!matches(CustomerVO, constraint)) 
            		{
            			items.remove(i);
            			size--;i--;
            			removidos.add(CustomerVO);
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
    		   ClienteAdapter.this.notifyDataSetChanged();
   			}
       }

       public boolean matches(CustomerVO customer, CharSequence value) {
          /// implement this part
    	   String lowerCaseConstrait = value.toString().toLowerCase();
    	   if(customer.getNomeFantasia().toLowerCase().startsWith(lowerCaseConstrait))
    	   {
    		   return true;
           }
    	   return false;
        }
    }
	private boolean verificaClienteBloqueado(CustomerVO customerVO)
    {
        if(customerVO.getBloqueado().equalsIgnoreCase("S"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
	private boolean methodValidaClientePedido(String codCliente)
    {
        ClienteDiaPedidoVO clienteDiaVOs = casaDoQueijoLibraryApp.getDataManager().getClienteDiaPedidoVO(codCliente); 
        if(clienteDiaVOs == null)
        {
            return true;
        }
        else
        {
            
            return false;
        }
    }
}
