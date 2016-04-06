package br.com.eurekasoftwares.tablet.cliente;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.adapter.ClienteAdapter;
import br.com.eurekasoftwares.tablet.gzip.GZipUtils;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.vo.CustomerVO;
import br.com.eurekasoftwares.tablet.vo.LoginVO;
import br.com.eurekasoftwares.tablet.xml.XMLUtils;

public class SincronizaClientesListAsyncTask extends
		AsyncTask<String, Void, byte[]> {
			
	public SincronizaClientesListAsyncTask(Activity activity,ClienteAdapter adapter)
	{
		this.activity = activity;
		this.adapter = adapter;
	}
	private ProgressDialog dialog;
    private Activity activity;
    private ClienteAdapter adapter;
    private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
    
    @Override
    protected void onPreExecute() {
    	Application app = activity.getApplication();
    	this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)app;
    	this.dialog = ProgressDialog.show(activity, "Aguarde", "Carregando...",true,false);
    	List<CustomerVO> clientes = this.casaDoQueijoLibraryApp.getDataManager().getCustomerList();
    	for(CustomerVO customerVO:clientes)
    	{
    		this.casaDoQueijoLibraryApp.getDataManager().deleteCustomer(customerVO.getNomeFantasia());
    	}
    }
    @Override
	protected byte[] doInBackground(String... arg0) {
		// TODO Auto-generated method stub
    	byte[] clientesCompressed=null;
		try {
			if(ModelSingleton.getInstance().getLogon().equalsIgnoreCase(""))
			{
				LoginVO loginVO = ModelSingleton.getInstance().getLoginVO();
				//this.casaDoQueijoLibraryApp.getDataManager().getLoginVO();
				String logon = ModelSingleton.getInstance().getWebToMobileClient().doLogin(loginVO.getUser(), loginVO.getPass());
				ModelSingleton.getInstance().setLogon(logon);
			}
			clientesCompressed = ModelSingleton.getInstance().getWebToMobileClient().getCustomerList(ModelSingleton.getInstance().getLogon());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientesCompressed;
	}
    @Override
    protected void onPostExecute(byte[] clientesCompressed) {
    	String clientesXML = new String(GZipUtils.decompress(clientesCompressed));
		clientesCompressed = null;
		InputStream in = new ByteArrayInputStream(clientesXML.getBytes());
		clientesXML = null;
		CustomerVO[] customerList = null;
		try {
			customerList = XMLUtils.parserCustomersVO(in);
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.adapter.clear();
		
		for(int i = 0;customerList != null && i < customerList.length;i++)
		{
			CustomerVO customerVO = customerList[i];
			casaDoQueijoLibraryApp.getDataManager().saveCustomer(customerVO);
			this.adapter.add(customerVO);
			
		}	
		customerList = null;
		
		this.adapter.notifyDataSetChanged();

    	if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
