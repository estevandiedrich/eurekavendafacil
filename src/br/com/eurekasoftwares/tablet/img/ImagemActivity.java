package br.com.eurekasoftwares.tablet.img;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import br.com.eurekasoftwares.tablet.R;
import br.com.eurekasoftwares.tablet.constantes.Constantes;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.produto.ProdutosListViewActivity;

public class ImagemActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagem_produto);
        Log.w(Constantes.EUREKA_SOFTWARES, "PedidoActivity");
        
        ImageView imagemProduto = (ImageView)findViewById(R.id.imagem_produto);
        imagemProduto.setImageBitmap(ModelSingleton.getInstance().getImagemProduto());
        
        Button voltarProdutoButton = (Button)findViewById(R.id.voltar_produto);
        voltarProdutoButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ModelSingleton.getInstance().setImagemProduto(null);
				Intent i = new Intent(v.getContext().getApplicationContext(), ProdutosListViewActivity.class);
			    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				v.getContext().getApplicationContext().startActivity(i);
			}
		});
        ImageButton sincronizaImagemImageButton = (ImageButton)findViewById(R.id.sincroniza_imagem);
        sincronizaImagemImageButton.setOnClickListener(new Button.OnClickListener() {
        	
        	@Override
			public void onClick(View v) {
        		new SincronizaImagemProdutoAsyncTask(ImagemActivity.this).execute(ModelSingleton.getInstance().getCodProdutoSelecionado());
        	}
        });
	}
}
