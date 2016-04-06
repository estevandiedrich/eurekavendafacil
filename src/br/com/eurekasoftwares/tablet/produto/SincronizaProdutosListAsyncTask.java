package br.com.eurekasoftwares.tablet.produto;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.adapter.ProdutoAdapter;
import br.com.eurekasoftwares.tablet.gzip.GZipUtils;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.vo.LoginVO;
import br.com.eurekasoftwares.tablet.vo.ProductVO;
import br.com.eurekasoftwares.tablet.xml.XMLUtils;

public class SincronizaProdutosListAsyncTask extends
		AsyncTask<String, Void, byte[]> {
			
	public SincronizaProdutosListAsyncTask(Activity activity,ProdutoAdapter adapter)
	{
		this.activity = activity;
		this.adapter = adapter;
	}
	private ProgressDialog dialog;
	private Activity activity;
    private ProdutoAdapter adapter;
    private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
    
    @Override
    protected void onPreExecute() {
    	Application app = activity.getApplication();
    	this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)app;
    	this.dialog = ProgressDialog.show(activity, "Aguarde", "Carregando...",true,false);
    	List<ProductVO> produtos = this.casaDoQueijoLibraryApp.getDataManager().getProductList();
    	for(ProductVO product:produtos)
    	{
    		this.casaDoQueijoLibraryApp.getDataManager().deleteProduct(product.getCod());
    	}
    }
	@Override
	protected byte[] doInBackground(String... params) {
		// TODO Auto-generated method stub
		byte[] produtosCompressed=null;
		try {
			if(ModelSingleton.getInstance().getLogon().equalsIgnoreCase(""))
			{
				LoginVO loginVO = ModelSingleton.getInstance().getLoginVO();
				//this.casaDoQueijoLibraryApp.getDataManager().getLoginVO();
				String logon = ModelSingleton.getInstance().getWebToMobileClient().doLogin(loginVO.getUser(), loginVO.getPass());
				ModelSingleton.getInstance().setLogon(logon);
			}
			produtosCompressed = ModelSingleton.getInstance().getWebToMobileClient().getProductsList(ModelSingleton.getInstance().getLogon());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return produtosCompressed;
	}
	@Override
    protected void onPostExecute(byte[] produtosCompressed) {
		String produtosXML = new String(GZipUtils.decompress(produtosCompressed));
		produtosCompressed = null;
		InputStream in = new ByteArrayInputStream(produtosXML.getBytes());
		produtosXML = null;
		ProductVO[] productList = null;
		try {
			productList = XMLUtils.parserProductsVO(in);
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.adapter.clear();
		
		for(int i = 0;productList != null && i < productList.length;i++)
		{
			ProductVO productVO = productList[i];
			casaDoQueijoLibraryApp.getDataManager().saveProduct(productVO);
			this.adapter.add(productVO);
		}	
		productList = null;
		
		this.adapter.notifyDataSetChanged();
		
		if (dialog.isShowing()) {
            dialog.dismiss();
        }
	}
}
