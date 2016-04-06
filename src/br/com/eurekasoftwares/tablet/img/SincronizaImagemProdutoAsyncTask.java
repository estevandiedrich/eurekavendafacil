package br.com.eurekasoftwares.tablet.img;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import br.com.eurekasoftwares.tablet.constantes.Constantes;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.vo.LoginVO;

public class SincronizaImagemProdutoAsyncTask extends
	AsyncTask<String, Void, byte[]> {
		private Activity activity;
		private Dialog dialog;
		
		public SincronizaImagemProdutoAsyncTask(Activity activity)
		{
			this.activity = activity;
		}
		@Override
	    protected void onPreExecute() {
			Log.w(Constantes.EUREKA_SOFTWARES, "SincronizaImagemProdutoAsyncTask.onPreExecute()");
			this.dialog = ProgressDialog.show(activity, "Aguarde", "Carregando...",true,false);
			
		}
		@Override
		protected byte[] doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Log.w(Constantes.EUREKA_SOFTWARES, "SincronizaImagemProdutoAsyncTask.doInBackground");
			byte[] png = null;
			try {
				if(ModelSingleton.getInstance().getLogon().equalsIgnoreCase(""))
				{
					LoginVO loginVO = ModelSingleton.getInstance().getLoginVO();
					//this.casaDoQueijoLibraryApp.getDataManager().getLoginVO();
					String logon = ModelSingleton.getInstance().getWebToMobileClient().doLogin(loginVO.getUser(), loginVO.getPass());
					ModelSingleton.getInstance().setLogon(logon);
				}
				String serverURL = "";
				if(ModelSingleton.getInstance().getConfigVO().getUrl() != null && !ModelSingleton.getInstance().getConfigVO().getUrl().equalsIgnoreCase(""))
				{
					serverURL = ModelSingleton.getInstance().getConfigVO().getUrl()+"/CasaDoQueijoServer/ImagemProdutoServlet?codProduto="+arg0[0]+"&logon="+ModelSingleton.getInstance().getLogon()+"";
				}
				else
				{
					serverURL = "http://10.0.2.2:80/CasaDoQueijoServer/ImagemProdutoServlet?codProduto="+arg0[0]+"&logon="+ModelSingleton.getInstance().getLogon()+"";
				}
				URL urlObj = new URL(serverURL);
				HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
				connection.setDoInput(true);  
		    	connection.setDoOutput(true);  
		    	connection.setUseCaches(false);
		    	connection.setRequestMethod("POST"); 
		        connection.setRequestProperty("Content-Type", "image/png");
		        connection.setRequestProperty("Accept", "image/png");
		        connection.setRequestProperty("Connection", "close");
		        DataInputStream input = new DataInputStream(connection.getInputStream());
		        png = IOUtils.toByteArray(input);
		        input.close();
		        
		        File image = null;
				File cacheDir = null;
				String sdState = android.os.Environment.getExternalStorageState();
		        if (sdState.equals(android.os.Environment.MEDIA_MOUNTED)) 
		        {
		          File sdDir = android.os.Environment.getExternalStorageDirectory();    
		          cacheDir = new File(sdDir,"/eureka/");
		          if(!cacheDir.exists())
		          {
		        	  cacheDir.mkdirs();
		          }
		          image = new File(cacheDir.getPath()+"/"+arg0[0]);
		          if(image.exists())
		          {
		        	  image.delete();
		          }
	        	  image.createNewFile();
		        }
		        FileOutputStream out = null;
		        try {
		          out = new FileOutputStream(image);
		          out.write(png);
		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		        finally { 
		          try { if (out != null ) out.close(); }
		          catch(Exception ex) {} 
		        }

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return png;
		}
		@Override
	    protected void onPostExecute(byte[] png) {
			
			if (dialog.isShowing()) {
				dialog.dismiss();
			}

			BitmapFactory.Options options = new BitmapFactory.Options();
//			options.inPreferredConfig = Bitmap.Config.RGB_565;
			options.inPreferredConfig = Bitmap.Config.ALPHA_8;
//			options.inPreferredConfig = Bitmap.Config.ARGB_4444;
//			options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			if(png != null)
			{
				Bitmap bitmap = BitmapFactory.decodeByteArray(png, 0, png.length,options);
				ModelSingleton.getInstance().setImagemProduto(bitmap);
			}
			Intent i = new Intent(activity.getApplicationContext(), ImagemActivity.class);
	    	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			activity.startActivity(i);
			
		}
}
