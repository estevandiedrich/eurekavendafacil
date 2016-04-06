package br.com.eurekasoftwares.tablet.datamanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.eurekasoftwares.tablet.dao.OpenHelper;
import br.com.eurekasoftwares.tablet.dao.impl.ClienteDiaPedidoDAO;
import br.com.eurekasoftwares.tablet.dao.impl.ClienteProdutoQuantidadeDAO;
import br.com.eurekasoftwares.tablet.dao.impl.ConfigDAO;
import br.com.eurekasoftwares.tablet.dao.impl.CustomerDAO;
import br.com.eurekasoftwares.tablet.dao.impl.ErroDAO;
import br.com.eurekasoftwares.tablet.dao.impl.LoginDAO;
import br.com.eurekasoftwares.tablet.dao.impl.PedidoDAO;
import br.com.eurekasoftwares.tablet.dao.impl.ProductDAO;
import br.com.eurekasoftwares.tablet.dao.impl.ProductFromPedidoDAO;
import br.com.eurekasoftwares.tablet.dao.impl.TituloDAO;
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
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.vo.ClienteDiaPedidoVO;
import br.com.eurekasoftwares.tablet.vo.ClienteProdutoQuantidadeVO;
import br.com.eurekasoftwares.tablet.vo.ConfigVO;
import br.com.eurekasoftwares.tablet.vo.CustomerVO;
import br.com.eurekasoftwares.tablet.vo.ErroVO;
import br.com.eurekasoftwares.tablet.vo.LoginVO;
import br.com.eurekasoftwares.tablet.vo.PedidoBaseVO;
import br.com.eurekasoftwares.tablet.vo.PedidoVO;
import br.com.eurekasoftwares.tablet.vo.ProductFromPedidoVO;
import br.com.eurekasoftwares.tablet.vo.ProductVO;
import br.com.eurekasoftwares.tablet.vo.TituloVO;

public class DataManager {
	private Context context;
	private SQLiteDatabase database;
	private CustomerDAO customerDao;
	private ErroDAO erroDao;
	private ProductDAO productDao;
	private ConfigDAO configDao;
	private ProductFromPedidoDAO productFromPedidoDAO;
	private PedidoDAO pedidoDao;
	private ClienteDiaPedidoDAO clienteDiaPedidoDAO;
	private ClienteProdutoQuantidadeDAO clienteProdutoQuantidadeDAO;
	private LoginDAO loginDAO;
	private TituloDAO tituloDAO;
	
	public DataManager(Context context){
		setContext(context);
		SQLiteOpenHelper openHelper = new OpenHelper(context, "casadoqueijodatabase.db", null, 56);
		setDatabase(openHelper.getWritableDatabase());
		this.customerDao = new CustomerDAO(new CustomerVOTableDefinition(), database);
		this.erroDao = new ErroDAO(new ErroVOTableDefinition(), database);
		this.productDao = new ProductDAO(new ProductVOTableDefinition(),database);
		this.configDao = new ConfigDAO(new ConfigVOTableDefinition(), database);
		this.productFromPedidoDAO = new ProductFromPedidoDAO(new ProductFromPedidoVOTableDefinition(), database);
		this.pedidoDao = new PedidoDAO(new PedidoVOTableDefinition(),database);
		this.clienteDiaPedidoDAO = new ClienteDiaPedidoDAO(new ClienteDiaPedidoVOTableDefinition(), database);
		this.clienteProdutoQuantidadeDAO = new ClienteProdutoQuantidadeDAO(new ClienteProdutoQuantidadeVOTableDefinition(), database);
		this.loginDAO = new LoginDAO(new LoginVOTableDefinition(), database);
		this.tituloDAO = new TituloDAO(new TituloVOTableDefinition(),database);
	}
	public LoginVO getLoginVO(String user,String pass)
	{
		return getLoginDAO().getByClause(" USER = '"+user+"' AND PASS = '"+pass+"' ", null);
	}
//	public LoginVO getLoginVO()
//	{
//		return getLoginDAO().getAll().get(0);
//	}
	public ClienteProdutoQuantidadeVO findClienteProdutoQuantidadeVOPorCodProdutoECodCliente(String codProduto,String codCliente)
	{
		return getClienteProdutoQuantidadeDAO().getByClause(" CODCLIENTE = '"+codCliente+"' AND CODPRODUTO = '"+codProduto+"' ",null);
	}
	public List<PedidoBaseVO> getPedidosPorCliente(String codCliente,String dataEmissao)
	{
		return getPedidoDao().getAllbyClause(" CUSTOMERCOD = '"+codCliente+"' AND DATAEMISSAO < '"+dataEmissao+"'", null, null, null, null);
	}
	public List<ClienteDiaPedidoVO> getClienteDiaPedidoVOPorCliente(String codCliente)
	{
		return getClienteDiaPedidoDAO().getAllbyClause(" CODCLIENTE = '"+codCliente+"' AND DIA < DATE('NOW') ", null, null, null, null);
	}
	public ClienteDiaPedidoVO getClienteDiaPedidoVO(String codCliente)
	{
		return getClienteDiaPedidoDAO().getByClause(" CODCLIENTE = '"+codCliente+"' AND strftime('%Y-%m-%d',DIA) = '"+ModelSingleton.getInstance().getSimpleDateFormat().format(new Date())+"' ",null);
	}

	public CustomerVO getCustomerVO(String nomeFantasia){
		return getCustomerDao().get(nomeFantasia);
	}
	
	public ErroVO getErroVO(Long id){
		return getErroDao().get(id);
	}
	public ProductVO getProductVO(String cod)
	{
		return getProductDao().get(cod);
	}
	public ConfigVO getConfigVO(Long id)
	{
		return getConfigDao().get(id);
	}
	public List<ProductFromPedidoVO> getProductsFromPedido(long codigoPedido){
		return getProductFromPedidoDAO().getAllbyClause(" CODIGOPEDIDO = "+codigoPedido,null,null,null,null);
	}
	public List<PedidoVO> getPedidosNaoSincronizados()
	{
		List<PedidoVO> pedidoVOList = new ArrayList<PedidoVO>();
		for(PedidoBaseVO pedidoBaseVO:getPedidoDao().getAllbyClause(" sincronizado = 0 ", null,  null, null, null))
		{
			PedidoVO pedidoVO = new PedidoVO(pedidoBaseVO);
			for(ProductFromPedidoVO productFromPedidoVO:this.getProductsFromPedido(pedidoBaseVO.getCodigoPedido()))
			{
				pedidoVO.addProduct(productFromPedidoVO);
			}
			pedidoVOList.add(pedidoVO);
		}
		return pedidoVOList;
	}
	public PedidoVO getPedidoVO(Long id)
	{
		PedidoBaseVO pedidoBaseVO = getPedidoDao().get(id);
		PedidoVO pedidoVO = null;
		if(pedidoBaseVO!=null)
		{
			pedidoVO = new PedidoVO(pedidoBaseVO);
			for(ProductFromPedidoVO productFromPedidoVO:this.getProductsFromPedido(pedidoVO.getCodigoPedido()))
			{
				pedidoVO.addProduct(productFromPedidoVO);
			}
		}
		return pedidoVO;
	}
	public TituloVO getTituloVO(String id)
	{
		return getTituloDAO().get(id);
	}
	public List<TituloVO> getTitulosPorCliente(String codCliente){
		return getTituloDAO().getAllbyClause(" CLIENTE = '"+codCliente+"' AND COBRADO = 'N'",null,null,null,null);
	}
	public List<TituloVO> getTitulosCobrados()
	{
		return getTituloDAO().getAllbyClause(" COBRADO = 'S' AND SINCRONIZADO = 'N' ",null,null,null,null);
	}
	public List<TituloVO> getTituloList()
	{
		return getTituloDAO().getAll();
	}
	public List<ClienteDiaPedidoVO> getClienteDiaPedidoList()
	{
		return getClienteDiaPedidoDAO().getAll();
	}
	public List<CustomerVO> getCustomerList(){
		return getCustomerDao().getAll();
	}
	public List<ErroVO> getErroList(){
		return getErroDao().getAll();
	}
	public List<ProductVO> getProductList()
	{
		return getProductDao().getAll();
	}
	public List<ConfigVO> getConfigList(){
		return getConfigDao().getAll();
	}
	public List<ProductFromPedidoVO> getProductFromPedidoList()
	{
		return getProductFromPedidoDAO().getAll();
	}
	public List<PedidoVO> getPedidoList()
	{
		List<PedidoBaseVO> pedidoBaseVOs = getPedidoDao().getAll();
		List<PedidoVO> pedidosVOs = new ArrayList<PedidoVO>();
		for(PedidoBaseVO pedidoBaseVO:pedidoBaseVOs)
		{
			PedidoVO pedidoVO = new PedidoVO(pedidoBaseVO);
			pedidoVO.setProducts(this.getProductsFromPedido(pedidoVO.getCodigoPedido()));
			pedidosVOs.add(pedidoVO);
		}
		return pedidosVOs;
	}
	public boolean deleteTitulo(String numeroTitulo)
	{
		boolean result = false;
		getDatabase().beginTransaction();
		result = getTituloDAO().delete(numeroTitulo);
		getDatabase().setTransactionSuccessful();
		getDatabase().endTransaction();
		return result;
	}
	public boolean deleteLoginVO(Long id)
	{
		boolean result = false;
		getDatabase().beginTransaction();
		result = getLoginDAO().delete(id.longValue());
		getDatabase().setTransactionSuccessful();
		getDatabase().endTransaction();
		return result;
	}
	public boolean deleteClienteProdutoQuantidade(Long chave)
	{
		boolean result = false;
		getDatabase().beginTransaction();
		result = getClienteDiaPedidoDAO().delete(chave.longValue());
		getDatabase().setTransactionSuccessful();
		getDatabase().endTransaction();
		return result;
	}
	public boolean deleteClienteDiaPedido(Long id)
	{
		boolean result = false;
		getDatabase().beginTransaction();
		result = getClienteDiaPedidoDAO().delete(id.longValue());
		getDatabase().setTransactionSuccessful();
		getDatabase().endTransaction();
		return result;
	}
	public boolean deleteErro(Long id){
		boolean result = false;
		getDatabase().beginTransaction();
		result = getErroDao().delete(id.longValue());
		getDatabase().setTransactionSuccessful();
		getDatabase().endTransaction();
		return result;
	}
	public boolean deleteProduct(String cod)
	{
		boolean result = false;
		getDatabase().beginTransaction();
		result = getProductDao().delete(cod);
		getDatabase().setTransactionSuccessful();
		getDatabase().endTransaction();
		return result;
		
	}	
	public boolean deleteCustomer(String nomeFantasia){
		boolean result = false;
		getDatabase().beginTransaction();
		result = getCustomerDao().delete(nomeFantasia);
		getDatabase().setTransactionSuccessful();
		getDatabase().endTransaction();
		return result;
	}
	public boolean deleteConfig(Long id){
		boolean result = false;
		getDatabase().beginTransaction();
		result = getConfigDao().delete(id);
		getDatabase().setTransactionSuccessful();
		getDatabase().endTransaction();
		return result;
	}
	public boolean deleteProductFromPedidoVO(Long id)
	{
		boolean result = false;
		getDatabase().beginTransaction();
		result = getProductFromPedidoDAO().delete(id);
		getDatabase().setTransactionSuccessful();
		getDatabase().endTransaction();
		return result;
	}
	public boolean deletePedidoVO(Long id)
	{
		boolean result = false;
		getDatabase().beginTransaction();
		result = getPedidoDao().delete(id);
		getDatabase().setTransactionSuccessful();
		getDatabase().endTransaction();
		return result;
	}
	public long saveLoginVO(LoginVO loginVO)
	{
		long result = 0;
		try {
			getDatabase().beginTransaction();
			result = getLoginDAO().save(loginVO);
			getDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public long saveClienteProdutoQuantidade(ClienteProdutoQuantidadeVO clienteProdutoQuantidadeVO)
	{
		long result = 0;
		try {
			getDatabase().beginTransaction();
			result = getClienteProdutoQuantidadeDAO().save(clienteProdutoQuantidadeVO);
			getDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public long saveClienteDiaPedido(ClienteDiaPedidoVO clienteDiaPedidoVO)
	{
		long result = 0;
		try {
			getDatabase().beginTransaction();
			result = getClienteDiaPedidoDAO().save(clienteDiaPedidoVO);
			getDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public long saveTitulo(TituloVO tituloVO)
	{
		long result = 0;
		try {
			getDatabase().beginTransaction();
			result = getTituloDAO().save(tituloVO);
			getDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public long saveCustomer(CustomerVO customer){
		long result = 0;
		try {
			getDatabase().beginTransaction();
			result = getCustomerDao().save(customer);
			getDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public long saveErro(ErroVO erro){
		long result = 0;
		try {
			getDatabase().beginTransaction();
			result = getErroDao().save(erro);
			getDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public long saveProduct(ProductVO productVO)
	{
		long result=0;
		try {
			getDatabase().beginTransaction();
			result = getProductDao().save(productVO);
			getDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public long saveConfig(ConfigVO configVO)
	{
		long result=0;
		try {
			getDatabase().beginTransaction();
			result = getConfigDao().save(configVO);
			getDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public long saveProductFromPedido(ProductFromPedidoVO productFromPedidoVO)
	{
		long result=0;
		try {
			getDatabase().beginTransaction();
			result = getProductFromPedidoDAO().save(productFromPedidoVO);
			getDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public long savePedido(PedidoVO pedidoVO)
	{
		PedidoBaseVO pedidoBaseVO = new PedidoBaseVO(pedidoVO);
		long result=0;
		try {
			getDatabase().beginTransaction();
			result = getPedidoDao().save(pedidoBaseVO);
			getDatabase().setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		for(ProductFromPedidoVO productFromPedidoVO:pedidoVO.getAllProducts())
		{
			this.saveProductFromPedido(productFromPedidoVO);
		}
		return result;
	}
	public boolean updateClienteProdutoQuantidade(ClienteProdutoQuantidadeVO clienteProdutoQuantidadeVO)
	{
		boolean result = false;
		try {
			getDatabase().beginTransaction();
			getClienteProdutoQuantidadeDAO().update(clienteProdutoQuantidadeVO, clienteProdutoQuantidadeVO.getChave());
			getDatabase().setTransactionSuccessful();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public boolean updateClienteDiaPedido(ClienteDiaPedidoVO clienteDiaPedidoVO)
	{
		boolean result = false;
		try {
			getDatabase().beginTransaction();
			getClienteDiaPedidoDAO().update(clienteDiaPedidoVO, clienteDiaPedidoVO.getChave());
			getDatabase().setTransactionSuccessful();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public boolean updateTitulo(TituloVO tituloVO)
	{
		boolean result = false;
		try {
			getDatabase().beginTransaction();
			getTituloDAO().update(tituloVO, tituloVO.getNumeroTitulo());
			getDatabase().setTransactionSuccessful();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public boolean updateErro(ErroVO erro){
		boolean result = false;
		try {
			getDatabase().beginTransaction();
			getErroDao().update(erro, erro.getId());
			getDatabase().setTransactionSuccessful();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public boolean updateCustomer(CustomerVO customer){
		boolean result = false;
		try {
			getDatabase().beginTransaction();
			getCustomerDao().update(customer, customer.getNomeFantasia());
			getDatabase().setTransactionSuccessful();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public boolean updateProduct(ProductVO productVO)
	{
		boolean result = false;
		try {
			getDatabase().beginTransaction();
			getProductDao().update(productVO, productVO.getCod());
			getDatabase().setTransactionSuccessful();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public boolean updateConfig(ConfigVO configVO)
	{
		boolean result = false;
		try {
			getDatabase().beginTransaction();
			getConfigDao().update(configVO, configVO.getId());
			getDatabase().setTransactionSuccessful();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public boolean updateProductFromPedido(ProductFromPedidoVO productFromPedidoVO)
	{
		boolean result = false;
		try {
			getDatabase().beginTransaction();
			getProductFromPedidoDAO().update(productFromPedidoVO, productFromPedidoVO.getId());
			getDatabase().setTransactionSuccessful();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public boolean updateProdutosPedido(PedidoVO pedidoVO)
	{
		boolean ret = this.updatePedido(pedidoVO);
		List<ProductFromPedidoVO> produtosAntigos = getProductsFromPedido(pedidoVO.getCodigoPedido());
		for(ProductFromPedidoVO produtoAntigo:produtosAntigos)
		{
			ret = this.deleteProductFromPedidoVO(produtoAntigo.getId());
		}
		for(ProductFromPedidoVO productFromPedidoVO:pedidoVO.getAllProducts())
		{
			this.saveProductFromPedido(productFromPedidoVO);
		}
		return ret;
	}
	public boolean updatePedido(PedidoVO pedidoVO)
	{
		boolean result = false;
		PedidoBaseVO pedidoBaseVO = new PedidoBaseVO(pedidoVO);
		try {
			getDatabase().beginTransaction();
			getPedidoDao().update(pedidoBaseVO, pedidoBaseVO.getCodigoPedido());
			getDatabase().setTransactionSuccessful();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDatabase().endTransaction();
		return result;
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	public SQLiteDatabase getDatabase() {
		return database;
	}
	public void setDatabase(SQLiteDatabase database) {
		this.database = database;
	}
	
	public CustomerDAO getCustomerDao() {
		return customerDao;
	}
	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}
	public ErroDAO getErroDao() {
		return erroDao;
	}
	public void setErroDao(ErroDAO erroDao) {
		this.erroDao = erroDao;
	}
	public ProductDAO getProductDao()
	{
		return this.productDao;
	}
	public void setProductDao(ProductDAO productDao)
	{
		this.productDao = productDao;
	}

	public ConfigDAO getConfigDao() {
		return configDao;
	}

	public void setConfigDao(ConfigDAO configDao) {
		this.configDao = configDao;
	}

	public ProductFromPedidoDAO getProductFromPedidoDAO() {
		return productFromPedidoDAO;
	}

	public void setProductFromPedidoDAO(ProductFromPedidoDAO productFromPedidoDAO) {
		this.productFromPedidoDAO = productFromPedidoDAO;
	}

	public PedidoDAO getPedidoDao() {
		return pedidoDao;
	}

	public void setPedidoDao(PedidoDAO pedidoDao) {
		this.pedidoDao = pedidoDao;
	}

	public ClienteDiaPedidoDAO getClienteDiaPedidoDAO() {
		return clienteDiaPedidoDAO;
	}

	public void setClienteDiaPedidoDAO(ClienteDiaPedidoDAO clienteDiaPedidoDAO) {
		this.clienteDiaPedidoDAO = clienteDiaPedidoDAO;
	}
	public ClienteProdutoQuantidadeDAO getClienteProdutoQuantidadeDAO() {
		return clienteProdutoQuantidadeDAO;
	}
	public void setClienteProdutoQuantidadeDAO(
			ClienteProdutoQuantidadeDAO clienteProdutoQuantidadeDAO) {
		this.clienteProdutoQuantidadeDAO = clienteProdutoQuantidadeDAO;
	}
	public LoginDAO getLoginDAO() {
		return loginDAO;
	}
	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	public TituloDAO getTituloDAO() {
		return tituloDAO;
	}
	public void setTituloDAO(TituloDAO tituloDAO) {
		this.tituloDAO = tituloDAO;
	}
	
}
