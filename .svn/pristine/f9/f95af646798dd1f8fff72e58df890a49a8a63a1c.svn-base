/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.eurekasoftwares.tablet.vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author germano
 */
@XStreamAlias("b")
public class PedidoVO extends PedidoBaseVO implements Comparable<PedidoVO>{
	@XStreamAlias("i")
    private List<ProductFromPedidoVO> products;
	
    public PedidoVO()
    {
        super();
        this.products = new ArrayList<ProductFromPedidoVO>();
    }
    public PedidoVO(PedidoBaseVO pedidoBaseVO)
    {
    	this();
    	this.setCodigoPedido(pedidoBaseVO.getCodigoPedido());
    	this.setCustomerCod(pedidoBaseVO.getCustomerCod());
    	this.setDataEmissao(pedidoBaseVO.getDataEmissao());
    	this.setPrazo(pedidoBaseVO.getPrazo());
    	this.setSincronizado(pedidoBaseVO.getSincronizado());
    	this.setTipoPagamento(pedidoBaseVO.getTipoPagamento());
    	this.setUrgente(pedidoBaseVO.getUrgente());
    }
    public String getXML()
    {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S z");  
        StringBuffer xml = new StringBuffer();
        xml.append("<PedidoVO>");
        xml.append("<codigoPedido>");
        xml.append(this.getCodigoPedido());
        xml.append("</codigoPedido>");
        xml.append("<customerCod>");
        xml.append(this.getCustomerCod());
        xml.append("</customerCod>");
        xml.append("<dataEmissao>");
        xml.append(format.format(this.getDataEmissao()));
        xml.append("</dataEmissao>");
        xml.append("<prazo>");
        xml.append(this.getPrazo());
        xml.append("</prazo>");
        xml.append("<tipoPagamento>");
        xml.append(this.getTipoPagamento());
        xml.append("</tipoPagamento>");
        xml.append("<urgente>");
        xml.append(this.getUrgente());
        xml.append("</urgente>");
        xml.append("<produtos>");
        List<ProductFromPedidoVO> produtos = this.getAllProducts();
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        for(int i = 0;i < produtos.size();i++)
        {
            ProductFromPedidoVO produto = produtos.get(i);
            xml.append(produto.getXML());
            
//            String xml2 = xStream.toXML(produto);
//            System.out.println(xml2);
        }
        xml.append("</produtos>");
        xml.append("</PedidoVO>");
        return xml.toString();
    }
    @Override
	public int compareTo(PedidoVO rhs) {
		// TODO Auto-generated method stub
		Date date1 = this.getDataEmissao();
		Date date2 = rhs.getDataEmissao();
		return date1.compareTo(date2);
	}
    
    public void addProduct(ProductFromPedidoVO product)
    {
    	product.setCodigoPedido(super.getCodigoPedido());
        this.getAllProducts().add(product);
    }

    public ProductFromPedidoVO getProductAt(int index) throws ArrayIndexOutOfBoundsException
    {
        return this.getAllProducts().get(index);
    }
    
    public List<ProductFromPedidoVO> getAllProducts()
    {
        return this.products;
    }
    
    public ProductFromPedidoVO getProductDetails(String productCod)
    {
        for(int i = 0;i < this.getAllProducts().size();i++)
        {
            ProductFromPedidoVO productFromPedidoVO = this.getProductAt(i);
            if(productFromPedidoVO.getCod().equalsIgnoreCase(productCod))
            {
                return productFromPedidoVO;
            }
        }
        return null;
    }

	public List<ProductFromPedidoVO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductFromPedidoVO> products) {
		this.products = products;
	}
}
