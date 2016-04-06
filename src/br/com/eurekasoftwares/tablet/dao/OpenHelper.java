package br.com.eurekasoftwares.tablet.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.eurekasoftwares.tablet.dao.tabledefinition.ClienteDiaPedidoVOTableDefinition;
import br.com.eurekasoftwares.tablet.dao.tabledefinition.ClienteProdutoQuantidadeVOTableDefinition;
import br.com.eurekasoftwares.tablet.dao.tabledefinition.ConfigVOTableDefinition;
import br.com.eurekasoftwares.tablet.dao.tabledefinition.CustomerVOTableDefinition;
import br.com.eurekasoftwares.tablet.dao.tabledefinition.ErroVOTableDefinition;
import br.com.eurekasoftwares.tablet.dao.tabledefinition.LoginVOTableDefinition;
import br.com.eurekasoftwares.tablet.dao.tabledefinition.PedidoVOTableDefinition;
import br.com.eurekasoftwares.tablet.dao.tabledefinition.ProductFromPedidoVOTableDefinition;
import br.com.eurekasoftwares.tablet.dao.tabledefinition.ProductVOTableDefinition;
import br.com.eurekasoftwares.tablet.dao.tabledefinition.TituloVOTableDefinition;

public class OpenHelper extends SQLiteOpenHelper {

	public OpenHelper(Context context, String name, CursorFactory factory,
	int version) {
		super(context, name, factory, version);		
	}

	@SuppressWarnings("static-access")
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try{
			ErroVOTableDefinition erroVOTableDefinition = new ErroVOTableDefinition();
			erroVOTableDefinition.onCreate(db);
			CustomerVOTableDefinition customerVOTableDefinition = new CustomerVOTableDefinition();
			customerVOTableDefinition.onCreate(db);
			ProductVOTableDefinition productVOTableDefinition = new ProductVOTableDefinition();
			productVOTableDefinition.onCreate(db);
			ConfigVOTableDefinition configVOTableDefinition = new ConfigVOTableDefinition();
			configVOTableDefinition.onCreate(db);
			ProductFromPedidoVOTableDefinition productFromPedidoVOTableDefinition = new ProductFromPedidoVOTableDefinition();
			productFromPedidoVOTableDefinition.onCreate(db);
			PedidoVOTableDefinition pedidoVOTableDefinition = new PedidoVOTableDefinition();
			pedidoVOTableDefinition.onCreate(db);
			ClienteDiaPedidoVOTableDefinition clienteDiaPedidoVOTableDefinition = new ClienteDiaPedidoVOTableDefinition();
			clienteDiaPedidoVOTableDefinition.onCreate(db);
			ClienteProdutoQuantidadeVOTableDefinition clienteProdutoQuantidadeVOTableDefinition = new ClienteProdutoQuantidadeVOTableDefinition();
			clienteProdutoQuantidadeVOTableDefinition.onCreate(db);
			LoginVOTableDefinition loginVOTableDefinition = new LoginVOTableDefinition();
			loginVOTableDefinition.onCreate(db);
			TituloVOTableDefinition tituloVOTableDefinition = new TituloVOTableDefinition();
			tituloVOTableDefinition.onCreate(db);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SuppressWarnings("static-access")
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		try{
			ErroVOTableDefinition erroVOTableDefinition = new ErroVOTableDefinition();
			erroVOTableDefinition.onUpgrade(db, oldVersion, newVersion);
			CustomerVOTableDefinition customerVOTableDefinition = new CustomerVOTableDefinition();
			customerVOTableDefinition.onUpgrade(db, oldVersion, newVersion);
			ProductVOTableDefinition productVOTableDefinition = new ProductVOTableDefinition();
			productVOTableDefinition.onUpgrade(db, oldVersion, newVersion);
			ConfigVOTableDefinition configVOTableDefinition = new ConfigVOTableDefinition();
			configVOTableDefinition.onUpgrade(db, oldVersion, newVersion);
			ProductFromPedidoVOTableDefinition productFromPedidoVOTableDefinition = new ProductFromPedidoVOTableDefinition();
			productFromPedidoVOTableDefinition.onUpgrade(db, oldVersion, newVersion);
			PedidoVOTableDefinition pedidoVOTableDefinition = new PedidoVOTableDefinition();
			pedidoVOTableDefinition.onUpgrade(db, oldVersion, newVersion);
			ClienteDiaPedidoVOTableDefinition clienteDiaPedidoVOTableDefinition = new ClienteDiaPedidoVOTableDefinition();
			clienteDiaPedidoVOTableDefinition.onUpgrade(db, oldVersion, newVersion);
			ClienteProdutoQuantidadeVOTableDefinition clienteProdutoQuantidadeVOTableDefinition = new ClienteProdutoQuantidadeVOTableDefinition();
			clienteProdutoQuantidadeVOTableDefinition.onUpgrade(db, oldVersion, newVersion);
			LoginVOTableDefinition loginVOTableDefinition = new LoginVOTableDefinition();
			loginVOTableDefinition.onUpgrade(db, oldVersion, newVersion);
			TituloVOTableDefinition tituloVOTableDefinition = new TituloVOTableDefinition();
			tituloVOTableDefinition.onUpgrade(db, oldVersion, newVersion);
		}catch(Exception e){
			e.printStackTrace();
		}				
	}

}
