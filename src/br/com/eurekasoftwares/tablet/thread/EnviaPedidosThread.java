package br.com.eurekasoftwares.tablet.thread;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import android.app.Activity;
import android.util.Log;
import br.com.eurekasoftwares.tablet.EurekaVendaFacilLibraryApp;
import br.com.eurekasoftwares.tablet.gzip.GZipUtils;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.util.MD5Util;
import br.com.eurekasoftwares.tablet.vo.ClienteDiaPedidoVO;
import br.com.eurekasoftwares.tablet.vo.CustomerVO;
import br.com.eurekasoftwares.tablet.vo.LoginVO;
import br.com.eurekasoftwares.tablet.vo.PedidoBaseVO;
import br.com.eurekasoftwares.tablet.vo.PedidoVO;
import br.com.eurekasoftwares.tablet.vo.TituloVO;
import br.com.eurekasoftwares.tablet.vo.TitulosVO;

public class EnviaPedidosThread extends ThreadBase{
	private EurekaVendaFacilLibraryApp casaDoQueijoLibraryApp;
	public EnviaPedidosThread(Activity activity)
	{
		this.casaDoQueijoLibraryApp = (EurekaVendaFacilLibraryApp)activity.getApplication();
	}
	@Override
	public void run()
	{
		XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
		while ( super.isRunning() ) 
		{
			try
			{
				if(ModelSingleton.getInstance().getLogon().equalsIgnoreCase(""))
				{
					LoginVO loginVO = ModelSingleton.getInstance().getLoginVO();
					//this.casaDoQueijoLibraryApp.getDataManager().getLoginVO();
					String logon = ModelSingleton.getInstance().getWebToMobileClient().doLogin(loginVO.getUser(), loginVO.getPass());
					ModelSingleton.getInstance().setLogon(logon);
				}
				
//		        PedidosVO pedidos = new PedidosVO();
//		        pedidos.setPedidos(casaDoQueijoLibraryApp.getDataManager().getPedidosNaoSincronizados());
//		        if(pedidos.getPedidos() != null && pedidos.getPedidos().size()>0)
//		        {
//			        String xml2 = xStream.toXML(pedidos);
//			        byte[] compressed = GZipUtils.compress(xml2.replace("<x>", "").replace("</x>", ""));
//	                String [] x = ModelSingleton.getInstance().getWebToMobileClient().sincronizaPedidos(compressed, ModelSingleton.getInstance().getLogon());
//	                
//	                for(int i = 0;i < x.length;i++)
//	                {
//	                	PedidoVO pedido = casaDoQueijoLibraryApp.getDataManager().getPedidoVO(Long.valueOf(x[i]));
//	                    pedido.setSincronizado("1");
//	                    casaDoQueijoLibraryApp.getDataManager().updatePedido(pedido);
//	                }
//		        }
		        TitulosVO titulos = new TitulosVO();
		        titulos.setTitulos(casaDoQueijoLibraryApp.getDataManager().getTitulosCobrados());
		        if(titulos.getTitulos() != null && titulos.getTitulos().size()>0)
		        {
		        	String xml2 = xStream.toXML(titulos);
		        	byte[] compressed = GZipUtils.compress(xml2);
		        	String[] x = ModelSingleton.getInstance().getWebToMobileClient().enviarTitulosCobradosParaServidor(compressed, ModelSingleton.getInstance().getLogon());
		        	for(int i = 0;i < x.length;i++)
		        	{
		        		TituloVO titulo = casaDoQueijoLibraryApp.getDataManager().getTituloVO(x[i]);
		        		titulo.setSincronizado("S");
		        		casaDoQueijoLibraryApp.getDataManager().updateTitulo(titulo);
		        	}
		        }
				String pedidosAEnviar = new String();
				pedidosAEnviar = "<pedidos>";
				for(PedidoVO pedidoVO:casaDoQueijoLibraryApp.getDataManager().getPedidosNaoSincronizados())
				{
                    pedidosAEnviar += pedidoVO.getXML();
				}
				pedidosAEnviar += "</pedidos>";
				pedidosAEnviar = reduzTags(pedidosAEnviar);
				if(!pedidosAEnviar.equalsIgnoreCase("<a></a>"))
                {
					String md5 = MD5Util.md5(pedidosAEnviar);
                    byte[] compressed = GZipUtils.compress(pedidosAEnviar);
                    String [] x = ModelSingleton.getInstance().getWebToMobileClient().sincronizaPedidosMD5(compressed, ModelSingleton.getInstance().getLogon(),md5);
                    
                    for(int i = 0;i < x.length;i++)
                    {
                    	PedidoVO pedido = casaDoQueijoLibraryApp.getDataManager().getPedidoVO(Long.valueOf(x[i]));
                        pedido.setSincronizado("1");
                        casaDoQueijoLibraryApp.getDataManager().updatePedido(pedido);
                    }
                }
				List<CustomerVO> customersList = casaDoQueijoLibraryApp.getDataManager().getCustomerList();
				for(CustomerVO customerVO:customersList)
				{
					try
					{
						List<ClienteDiaPedidoVO> clienteDiaPedidoVOList = casaDoQueijoLibraryApp.getDataManager().getClienteDiaPedidoVOPorCliente(customerVO.getNomeFantasia());
		                for(ClienteDiaPedidoVO clienteDiaPedidoVO:clienteDiaPedidoVOList)
		                {
		                	casaDoQueijoLibraryApp.getDataManager().deleteClienteDiaPedido(clienteDiaPedidoVO.getChave());
		                }
					}
					catch(Exception e)
					{
						Log.w("Problemas em EnviNFeThread", e);
					}
	                try
	                {
						Calendar calendar = Calendar.getInstance();
						calendar.add(Calendar.DAY_OF_MONTH, -15);
						Date dataEmissaoMesAnterior = calendar.getTime();
						List<PedidoBaseVO> pedidosList = casaDoQueijoLibraryApp.getDataManager().getPedidosPorCliente(customerVO.getNomeFantasia(),ModelSingleton.getInstance().getSimpleDateFormat().format(dataEmissaoMesAnterior));
						for(PedidoBaseVO pedidoBaseVO:pedidosList)
						{
							casaDoQueijoLibraryApp.getDataManager().deletePedidoVO(pedidoBaseVO.getCodigoPedido());
						}
	                }
	                catch(Exception e)
	                {
	                	Log.w("Problemas em EnviNFeThread", e);
	                }
				}
			}
			catch(Throwable t)
			{
				Log.w("Erro não capturado", t);
			}
			try 
			{
				Thread.sleep( 5000 );
			}
			catch ( InterruptedException e ) 
			{
				Log.w("Problemas ao interromper a Thread EnviNFeThread", e);
			}
			finally
			{
				
			}
		}
	}
	private String reduzTags(String xml)
    {
        String xmlReduzido = "";
        xmlReduzido = xml.replace("<pedidos>", "<a>");
        xmlReduzido = xmlReduzido.replace("</pedidos>", "</a>");
        xmlReduzido = xmlReduzido.replace("<PedidoVO>","<b>");
        xmlReduzido = xmlReduzido.replace("</PedidoVO>","</b>");
        xmlReduzido = xmlReduzido.replace("<codigoPedido>","<c>");
        xmlReduzido = xmlReduzido.replace("</codigoPedido>","</c>");
        xmlReduzido = xmlReduzido.replace("<customerCod>","<d>");
        xmlReduzido = xmlReduzido.replace("</customerCod>","</d>");
        xmlReduzido = xmlReduzido.replace("<dataEmissao>","<e>");
        xmlReduzido = xmlReduzido.replace("</dataEmissao>","</e>");
        xmlReduzido = xmlReduzido.replace("<prazo>","<f>");
        xmlReduzido = xmlReduzido.replace("</prazo>","</f>");
        xmlReduzido = xmlReduzido.replace("<tipoPagamento>","<g>");
        xmlReduzido = xmlReduzido.replace("</tipoPagamento>","</g>");
        xmlReduzido = xmlReduzido.replace("<urgente>","<h>");
        xmlReduzido = xmlReduzido.replace("</urgente>","</h>");
        xmlReduzido = xmlReduzido.replace("<produtos>","<i>");
        xmlReduzido = xmlReduzido.replace("</produtos>","</i>");
        xmlReduzido = xmlReduzido.replace("<produto>","<j>");
        xmlReduzido = xmlReduzido.replace("</produto>","</j>");
        xmlReduzido = xmlReduzido.replace("<cod>","<k>");
        xmlReduzido = xmlReduzido.replace("</cod>","</k>");
        xmlReduzido = xmlReduzido.replace("<urgente>","<l>");
        xmlReduzido = xmlReduzido.replace("</urgente>","</l>");
        xmlReduzido = xmlReduzido.replace("<descontoConcedido>","<m>");
        xmlReduzido = xmlReduzido.replace("</descontoConcedido>","</m>");
        xmlReduzido = xmlReduzido.replace("<quantidadeVenda>","<n>");
        xmlReduzido = xmlReduzido.replace("</quantidadeVenda>","</n>");
        xmlReduzido = xmlReduzido.replace("<quantidadeTroca>","<o>");
        xmlReduzido = xmlReduzido.replace("</quantidadeTroca>","</o>");
        xmlReduzido = xmlReduzido.replace("<preco>","<p>");
        xmlReduzido = xmlReduzido.replace("</preco>","</p>");
        
        return xmlReduzido;
    }
}
