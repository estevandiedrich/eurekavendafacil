package br.com.eurekasoftwares.tablet.adapter;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.R;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.vo.TituloVO;

public class TituloAdapter extends BaseAdapter{
	private List<TituloVO> items;
	private LayoutInflater mInflater;
	private ViewHolder holder;
	private TituloVO t;
	private Activity activity;
	private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
	
	static class ViewHolder{
		private TextView numeroTitulo;
		private TextView dataEmissao;
		private TextView dataVencimento;
		private TextView valor;
		private Button cobrarTitulo;
	}
	public TituloAdapter(Activity activity,List<TituloVO> items)
	{
		this.mInflater = LayoutInflater.from(activity.getApplicationContext());
		this.items = items;
		this.activity = activity;
		Application app = this.activity.getApplication();
		this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)app;
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
			convertView = mInflater.inflate(R.layout.titulos_list_item, null);
			holder = new ViewHolder();
 
			holder.numeroTitulo = (TextView) convertView.findViewById(R.id.numero_titulo);
			holder.dataEmissao = (TextView) convertView.findViewById(R.id.data_emissao);
			holder.dataVencimento = (TextView) convertView.findViewById(R.id.data_vencimento);
			holder.valor = (TextView) convertView.findViewById(R.id.valor);
			holder.cobrarTitulo = (Button) convertView.findViewById(R.id.cobrar_titulo);
			convertView.setTag(holder);
			 
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		t = items.get(position);
		holder.cobrarTitulo.setId(position);
		holder.numeroTitulo.setText(String.valueOf(t.getNumeroTitulo()));
		holder.dataEmissao.setText(ModelSingleton.getInstance().getSimpleDateFormatBR().format(t.getDataEmissao()));
		holder.dataVencimento.setText(ModelSingleton.getInstance().getSimpleDateFormatBR().format(t.getDataVencimento()));
		holder.valor.setText(Float.toString(t.getValor()));
		holder.cobrarTitulo.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				final int position = v.getId();
				AlertDialog.Builder builder = new AlertDialog.Builder(TituloAdapter.this.activity);
				builder.setMessage("Titulo cobrado ?");
				builder.setCancelable(false);
				builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						TituloVO titulo = items.remove(position);
						titulo.setCobrado("S");
						casaDoQueijoLibraryApp.getDataManager().updateTitulo(titulo);
						
			     	    notifyDataSetInvalidated();
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
		View rowView = convertView;
		return rowView;
	}
	
	public void add(TituloVO tituloVO)
	{
		int index = Collections.binarySearch(this.items, tituloVO);
		if (index < 0) {
			this.items.add(-index-1, tituloVO);
		}
		else if(index == 0)
		{
			this.items.add(0, tituloVO);
		}
		else
		{
			this.items.add(index-1,tituloVO);
		}
	}
	public void clear()
	{
		this.items.clear();
	}
}
