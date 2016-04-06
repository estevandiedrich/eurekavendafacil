package br.com.eurekasoftwares.tablet.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.location.Location;
import br.com.eurekasoftwares.tablet.map.GetMyGPSLocation;
import br.com.eurekasoftwares.tablet.servlet.WebToMobileClient;
import br.com.eurekasoftwares.tablet.thread.EnviaPedidosThread;
import br.com.eurekasoftwares.tablet.util.GetMyPhoneState;
import br.com.eurekasoftwares.tablet.vo.ConfigVO;
import br.com.eurekasoftwares.tablet.vo.CustomerVO;
import br.com.eurekasoftwares.tablet.vo.LoginVO;
import br.com.eurekasoftwares.tablet.vo.PedidoVO;
import br.com.eurekasoftwares.tablet.vo.ProductVO;
import br.com.eurekasoftwares.tablet.vo.TituloVO;

public class ModelSingleton {

	private static ModelSingleton instance ;
	private String logon;
	private WebToMobileClient webToMobileClient;
	private PedidoVO pedidoVO;
	private LoginVO loginVO;
	private CustomerVO clienteMapa;
	private ConfigVO configVO;
	private List<ProductVO> productListDataProvider;
	private List<CustomerVO> customerListDataProvider;
	private List<TituloVO> titulos;
	private List<PedidoVO> pedidosListDataProvider;
	private EnviaPedidosThread enviaPedidosThread;
	private Location location;
	private GetMyGPSLocation myGPSLocation; 
	private GetMyPhoneState myPhoneState;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat simpleDateFormatBR = new SimpleDateFormat("dd/MM/yyyy");
	private DecimalFormat decimalFormat = new java.text.DecimalFormat("#,##0.00");
	private int totalPedido = 0;
	private Bitmap imagemProduto = null;
	private String codProdutoSelecionado = "";

	private ModelSingleton() {
		logon = "";
		productListDataProvider = new ArrayList<ProductVO>();
		customerListDataProvider = new ArrayList<CustomerVO>();
		pedidosListDataProvider = new ArrayList<PedidoVO>();
		titulos = new ArrayList<TituloVO>();
		pedidoVO = new PedidoVO();
	}

	public static synchronized ModelSingleton getInstance () {
		if ( instance == null ) {
			instance = new ModelSingleton();
		}
		return instance;
	}
	public String getLogon() {
		return logon;
	}

	public void setLogon(String s) {
		this.logon = s;
	}

	public WebToMobileClient getWebToMobileClient() {
		return webToMobileClient;
	}

	public void setWebToMobileClient(WebToMobileClient webToMobileClient) {
		this.webToMobileClient = webToMobileClient;
	}

	public ConfigVO getConfigVO() {
		return configVO;
	}

	public void setConfigVO(ConfigVO configVO) {
		this.configVO = configVO;
	}

	public PedidoVO getPedidoVO() {
		return pedidoVO;
	}

	public void setPedidoVO(PedidoVO pedidoVO) {
		this.pedidoVO = pedidoVO;
	}

	public List<ProductVO> getProductListDataProvider() {
		return productListDataProvider;
	}

	public void setProductListDataProvider(List<ProductVO> productListDataProvider) {
		this.productListDataProvider = productListDataProvider;
	}

	public List<CustomerVO> getCustomerListDataProvider() {
		return customerListDataProvider;
	}

	public void setCustomerListDataProvider(
			List<CustomerVO> customerListDataProvider) {
		this.customerListDataProvider = customerListDataProvider;
	}

	public SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
	}

	public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
		this.simpleDateFormat = simpleDateFormat;
	}

	public EnviaPedidosThread getEnviaPedidosThread() {
		return enviaPedidosThread;
	}

	public void setEnviaPedidosThread(EnviaPedidosThread enviaPedidosThread) {
		this.enviaPedidosThread = enviaPedidosThread;
	}

	public DecimalFormat getDecimalFormat() {
		return decimalFormat;
	}

	public void setDecimalFormat(DecimalFormat decimalFormat) {
		this.decimalFormat = decimalFormat;
	}

	public List<PedidoVO> getPedidosListDataProvider() {
		return pedidosListDataProvider;
	}

	public void setPedidosListDataProvider(List<PedidoVO> pedidosListDataProvider) {
		this.pedidosListDataProvider = pedidosListDataProvider;
	}

	public SimpleDateFormat getSimpleDateFormatBR() {
		return simpleDateFormatBR;
	}

	public void setSimpleDateFormatBR(SimpleDateFormat simpleDateFormatBR) {
		this.simpleDateFormatBR = simpleDateFormatBR;
	}

	public CustomerVO getClienteMapa() {
		return clienteMapa;
	}

	public void setClienteMapa(CustomerVO clienteMapa) {
		this.clienteMapa = clienteMapa;
	}

	public int getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(int totalPedido) {
		this.totalPedido = totalPedido;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public GetMyGPSLocation getMyGPSLocation() {
		return myGPSLocation;
	}

	public void setMyGPSLocation(GetMyGPSLocation myGPSLocation) {
		this.myGPSLocation = myGPSLocation;
	}

	public Bitmap getImagemProduto() {
		return imagemProduto;
	}

	public void setImagemProduto(Bitmap imagemProduto) {
		this.imagemProduto = imagemProduto;
	}

	public LoginVO getLoginVO() {
		return loginVO;
	}

	public void setLoginVO(LoginVO loginVO) {
		this.loginVO = loginVO;
	}

	public List<TituloVO> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<TituloVO> titulos) {
		this.titulos = titulos;
	}

	public GetMyPhoneState getMyPhoneState() {
		return myPhoneState;
	}

	public void setMyPhoneState(GetMyPhoneState myPhoneState) {
		this.myPhoneState = myPhoneState;
	}

	public String getCodProdutoSelecionado() {
		return codProdutoSelecionado;
	}

	public void setCodProdutoSelecionado(String codProdutoSelecionado) {
		this.codProdutoSelecionado = codProdutoSelecionado;
	}
}

