package br.com.eurekasoftwares.tablet.xml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;

import br.com.eurekasoftwares.tablet.vo.CustomerVO;
import br.com.eurekasoftwares.tablet.vo.ProductVO;

public class XMLUtils {
	public static synchronized CustomerVO[] parserCustomersVO(InputStream in) throws Exception
    {
        Vector customers = new Vector();
        KXmlParser parser = new KXmlParser();
        parser.setInput(new InputStreamReader(in));
        parser.nextTag();
        //Posiciona na tag <customers>
        parser.require(XmlPullParser.START_TAG, null, "customers");
        //Enquanto é diferente de END_TAG
        while (parser.nextTag () != XmlPullParser.END_TAG)
        {
            //Posiciona na tag <customer>
            parser.require(XmlPullParser.START_TAG, null, "customer");
            CustomerVO customerVO = parserCustomerVO(parser);
            customers.addElement(customerVO);
            parser.require(XmlPullParser.END_TAG, null, "customer");
        }
        parser.require(XmlPullParser.END_TAG, null, "customers");
        parser.next();
        parser.require(XmlPullParser.END_DOCUMENT, null, null);
        CustomerVO[] customersArray = new CustomerVO[customers.size()];
        for(int i=0;i<customers.size();i++)
        {
            customersArray[i] = (CustomerVO)customers.elementAt(i);
        }
        return customersArray;
    }
    public static ProductVO[] parserProductsVO(InputStream in) throws Exception 
    {
        Vector products = new Vector();
        //Inicia o XMLParser
        KXmlParser parser = new KXmlParser();
        parser.setInput(new InputStreamReader(in));
        parser.nextTag();
        //Posiciona na tag <products>
        parser.require(XmlPullParser.START_TAG, null, "products");
        //Enquanto é diferente de END_TAG
        while (parser.nextTag () != XmlPullParser.END_TAG)
        {
            //Posiciona na tag <product>
            parser.require(XmlPullParser.START_TAG, null, "product");
            ProductVO productVO = parserProductVO(parser);
            products.addElement(productVO);
            parser.require(XmlPullParser.END_TAG, null, "product");
        }
        parser.require(XmlPullParser.END_TAG, null, "products");
        parser.next();
        parser.require(XmlPullParser.END_DOCUMENT, null, null);
        ProductVO[] productsArray = new ProductVO[products.size()];
        for(int i=0;i<products.size();i++)
        {
            productsArray[i] = (ProductVO)products.elementAt(i);
        }
        return productsArray;
    }
    private static CustomerVO parserCustomerVO(KXmlParser parser) throws Exception
    {
        CustomerVO customerVO = new CustomerVO();
        //Enquanto é diferente de </customer>
        while (parser.nextTag() != XmlPullParser.END_TAG) 
        {              
              parser.require(XmlPullParser.START_TAG, null, null);
              String name = parser.getName();
              String text = parser.nextText();
              if(name.equalsIgnoreCase("nomeFantasia"))
              {
                  customerVO.setNomeFantasia(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("cidade"))
              {
                  customerVO.setCidade(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("bairro"))
              {
                  customerVO.setBairro(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("rua"))
              {
                  customerVO.setRua(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("numero"))
              {
                  customerVO.setNumero(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("cep"))
              {
                  customerVO.setCep(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);              
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("contato"))
              {
                  customerVO.setContato(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("cgc"))
              {
                  customerVO.setCgc(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("ie"))
              {
                  customerVO.setIe(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("bloqueado"))
              {
                  customerVO.setBloqueado(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
        }
        return customerVO;
    }
    private static ProductVO parserProductVO(KXmlParser parser) throws Exception 
    {    
        ProductVO productVO = new ProductVO();
        //Enquanto é diferente de </product>
        while (parser.nextTag() != XmlPullParser.END_TAG) 
        {
              //Posiciona em uma tag "START". Ex: <nome> ou <fone>
              parser.require(XmlPullParser.START_TAG, null, null);
              String name = parser.getName();
              String text = parser.nextText();
              if(name.equalsIgnoreCase("cod"))
              {
                  productVO.setCod(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //**************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("descricao"))
              {
                  productVO.setDescricao(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);              
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("unidade"))
              {
                  productVO.setUnidade(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("icms"))
              {
                  productVO.setIcms(Integer.parseInt(text));
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("desctoMax"))
              {
                  productVO.setDesctoMax(Float.parseFloat(text));
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("preco"))
              {
                  productVO.setPreco(Float.parseFloat(text));
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("quantidade"))
              {
                  productVO.setQuantidade(Float.parseFloat(text));
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);
              //**************************************************//
              parser.nextTag();
              //******************************************************//
              parser.require(XmlPullParser.START_TAG, null, null);
              name = parser.getName();
              text = parser.nextText();
              if(name.equalsIgnoreCase("urgente"))
              {
                  productVO.setUrgente(text);
              }
              //Posiciona no fim da tag </nome> ou </fone>
              parser.require(XmlPullParser.END_TAG, null, name);              
        }
        return productVO;
    }
}
