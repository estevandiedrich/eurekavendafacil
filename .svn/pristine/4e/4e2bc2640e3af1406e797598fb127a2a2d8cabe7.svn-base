package br.com.eurekasoftwares.tablet.img;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.output.ByteArrayOutputStream;

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

public class SolicitaImagemProdutoAsyncTask extends
		AsyncTask<String, Void, byte[]> {
	
	private Activity activity;
	private Dialog dialog;
	
	public SolicitaImagemProdutoAsyncTask(Activity activity)
	{
		this.activity = activity;
	}
	@Override
    protected void onPreExecute() {
		Log.w(Constantes.EUREKA_SOFTWARES, "SolicitaImagemProdutoAsyncTask.onPreExecute()");
		this.dialog = ProgressDialog.show(activity, "Aguarde", "Carregando...",true,false);
		
	}
	@Override
	protected byte[] doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		Log.w(Constantes.EUREKA_SOFTWARES, "SolicitaImagemProdutoAsyncTask.doInBackground");
		byte[] png = null;
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
        }
        if(image != null && image.exists())
        {
        	final int BUFFER = 2048;
        	int count = 0;
        	byte data[] = new byte[BUFFER];
        	FileInputStream fis = null;
        	ByteArrayOutputStream baos = null;
        	BufferedOutputStream dest = null;
			try {
				baos = new ByteArrayOutputStream();
				dest = new BufferedOutputStream(baos);
				fis = new FileInputStream(image);
				while ((count = fis.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
				fis.close();
				png = baos.toByteArray();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Log.e(Constantes.EUREKA_SOFTWARES,e.getMessage(),e);
			} catch(IOException e)
			{
				Log.e(Constantes.EUREKA_SOFTWARES,e.getMessage(),e);
			}
        }
        
		return png;
	}
	@Override
    protected void onPostExecute(byte[] png) {
		
		if (dialog.isShowing()) {
			dialog.dismiss();
		}

		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inPreferredConfig = Bitmap.Config.RGB_565;
		options.inPreferredConfig = Bitmap.Config.ALPHA_8;
//		options.inPreferredConfig = Bitmap.Config.ARGB_4444;
//		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
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
