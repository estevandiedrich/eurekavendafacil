//GEN-BEGIN:Client
/**
 * This file is generated. Please do not change
 */
package br.com.eurekasoftwares.tablet.servlet;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

import android.util.Log;
import br.com.eurekasoftwares.tablet.constantes.Constantes;
/**
 *
 */
public class WebToMobileClient {
    
    /** The URL of the servlet gateway */
    private String serverURL;
    
    /** The session cookie of this client */
    private String sessionCookie;
    
    /**
     * Empty array used for no-argument calls, and to represent the value "void"
     */
    
    private final static Object[] _ = new Object[0];
    
    /**
     * Constructs a new WebToMobileClient
     * and initializes the URL to the servlet gateway from a hard-coded value.
     */
    public WebToMobileClient() {
        this("http://localhost:80/CasaDoQueijoServer/servlet/br.com.diedrich.web.WebToMobileServlet");
    }
    
    /**
     * Constructs a new WebToMobileClient
     * and initializes the URL to the servlet gateway from the given value
     *
     * @param serverURL URL of the deployed servlet
     */
    public WebToMobileClient(String serverURL) {
        this.serverURL = serverURL;
    }
    
    public byte[] getProductsList(String logon) throws IOException {
        Object params[] = new Object[] {
            logon
        };
        int paramIDs[] = new int[] {
            10
        };
        return (byte[])invokeServer(5, params, paramIDs);
    }
    
    public byte[] buscaListaTitulo(String logon,String cliente) throws IOException {
        Object params[] = new Object[] {
            logon,
            cliente
        };
        int paramIDs[] = new int[] {
            10,
            10
        };
        return (byte[])invokeServer(7, params, paramIDs);
    }
    
    public String doLogin(String user, String pass) throws IOException {
        Object params[] = new Object[] {
            user,
            pass
        };
        int paramIDs[] = new int[] {
            10,
            10
        };
        return (String)invokeServer(1, params, paramIDs);
    }
    
    public byte[] getImagemProduto(String logon, String produto) throws IOException {
        Object params[] = new Object[] {
            logon,
            produto
        };
        int paramIDs[] = new int[] {
            10,
            10
        };
        return (byte[])invokeServer(3, params, paramIDs);
    }
    
    public String[] enviarTitulosCobradosParaServidor(byte[] xmlTitulos, String logon) throws IOException {
        Object params[] = new Object[] {
            xmlTitulos,
            logon
        };
        int paramIDs[] = new int[] {
            1,
            10
        };
        return (String[])invokeServer(6, params, paramIDs);
    }
    
    public byte[] getCustomerList(String logon) throws IOException {
        Object params[] = new Object[] {
            logon
        };
        int paramIDs[] = new int[] {
            10
        };
        return (byte[])invokeServer(4, params, paramIDs);
    }
    
    public void setErros(String logon, String errosXML) throws IOException {
        Object params[] = new Object[] {
            logon,
            errosXML
        };
        int paramIDs[] = new int[] {
            10,
            10
        };
        invokeServer(8, params, paramIDs);
    }
    
    public String[] sincronizaPedidos(byte[] compressed, String logon) throws IOException {
        Object params[] = new Object[] {
            compressed,
            logon
        };
        int paramIDs[] = new int[] {
            1,
            10
        };
        return (String[])invokeServer(2, params, paramIDs);
    }
    
    public String[] sincronizaPedidosMD5(byte[] compressed, String logon, String md5) throws IOException {
        Object params[] = new Object[] {
            compressed,
            logon,
            md5
        };
        int paramIDs[] = new int[] {
            1,
            10,
            10
        };
        return (String[])invokeServer(9, params, paramIDs);
    }
    
    
    /**
     *  This method performes a dynamic invocation on the server. It is generic in
     *  order to reduce the code size.
     *
     *@param  requestID        The id of the server service (method) we wish to
     *      invoke.
     *@param  parameters       The parameters that should be passed to the server
     *      (type safety is not checked by this method!)
     *@param  returnType       Is used to indicate the return type we should read
     *      from the server
     *@return                  The return value from the invoked service
     *@exception  IOException  When a communication error or a remove exception
     * occurs
     */
    private Object invokeServer(int requestID, Object[] parameters, int[] paramIDs ) throws IOException {
    	URL urlObj = new URL(serverURL);
    	HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
    	connection.setDoInput(true);  
    	connection.setDoOutput(true);  
    	connection.setUseCaches(false);
    	connection.setRequestMethod("POST"); 
        connection.setRequestProperty("Content-Type", "application/octet-stream");
        connection.setRequestProperty("Accept", "application/octet-stream");
        connection.setRequestProperty("Connection", "close");
        
        if (sessionCookie == null) {
            // if this is the first time this client contatcs the server,
            // verify that the version matches
            connection.setRequestProperty("version", "???");
        } else {
            connection.setRequestProperty("cookie", sessionCookie);
        }
        
        DataOutputStream output = new DataOutputStream(connection.getOutputStream());        
        
        // Write invocation code
        output.writeShort( 1 );
        
        /* Write the byte signifying that only one call
         * is being made.
         */
        // TODO This is not reflected on server now
        //output.writeShort(1 /* one call to be made to the server */);
        
        output.writeInt(requestID);
        for (int i = 0; i < parameters.length; i++ ) {
            writeObject(output, parameters[i], paramIDs[i]);
        }
        
        output.close();
        
        int response;
        try {
            response = connection.getResponseCode();
        } catch (IOException e) {
        	Log.w(Constantes.EUREKA_SOFTWARES,"No response from " + serverURL + e);
            throw new IOException("No response from " + serverURL);
        }
        if (response != 200) {
        	Log.w(Constantes.EUREKA_SOFTWARES, response + " " + connection.getResponseMessage());
            throw new IOException(response + " " + connection.getResponseMessage());
        }
        DataInputStream input = new DataInputStream(connection.getInputStream());
        String sc = connection.getHeaderField("set-cookie");
        if (sc != null) {
            sessionCookie = sc;
        }
        short errorCode = input.readShort();
        if (errorCode != 1) {
            // there was a remote exception
        	Log.w(Constantes.EUREKA_SOFTWARES, input.readUTF());
            throw new IOException(input.readUTF());            
        }
        Object returnValue = null;
    	returnValue = readObject(input);
        
        input.close();
//        connection.close();
        return returnValue;
    }
    
    /**
     * Serializes object to the stream with given type id
     *
     * @param out
     * @param o object to be serialized to the stream
     * @param id idetification code of the serialized object
     */
    private void writeObject( DataOutputStream out, Object o, int id ) throws IOException {
        if( o == null ) {
            // write null type to the stream
            out.writeShort( -1 );
            return;
        }
        switch( id ) {
            case 1:
                // byte[]
                out.writeShort(1);
                byte[] a_result_1 = (byte[]) o;
                out.writeInt( a_result_1.length );
                for( int i  = 0; i < a_result_1.length; i++ ) {
                    writeObject( out, new Byte(a_result_1[i]) , 2 );
                }
                
                break;
            case 2:
                // byte
                out.writeShort(2);
                out.writeByte(((Byte)o).byteValue());
                break;
            case 10:
                // String
                out.writeShort(10);
                out.writeUTF((String)o);
                break;
            default:
                // default if a data type is not supported
                throw new IllegalArgumentException("Unsupported parameter type: " + o.getClass());
        }
    }
    
    private static Object readObject(DataInput in) throws IOException {
        int type = in.readShort();
        Object result;
        switch (type) {
            case 1:
                // byte[]
                int a_size_1 = in.readInt();
                byte[] a_result_1 = new byte[ a_size_1 ];
                for( int a_i_1 = 0; a_i_1 < a_size_1; a_i_1++ ) {
                    a_result_1[a_i_1] = ((Byte)readObject( in )).byteValue();
                }
                result = a_result_1;
                
                return result;
            case 2:
                // byte
                return new Byte(in.readByte());
            case 4:
                // Hashtable
                int g_4_length = in.readInt();
                Hashtable g_4_result = new Hashtable();
                for( int i = 0; i < g_4_length; i++ ) {
                    g_4_result.put(readObject( in ), readObject( in ));
                }
                result = g_4_result;
                return result;
            case 8:
                // void
                return _;
            case 9:
                // Hashtable[]
                int a_size_9 = in.readInt();
                Hashtable[] a_result_9 = new Hashtable[ a_size_9 ];
                for( int a_i_9 = 0; a_i_9 < a_size_9; a_i_9++ ) {
                    a_result_9[a_i_9] = (Hashtable)readObject( in );
                }
                result = a_result_9;
                
                return result;
            case 10:
                // String
                result = in.readUTF();
                return result;
            case 11:
                // String[]
                int a_size_11 = in.readInt();
                String[] a_result_11 = new String[ a_size_11 ];
                for( int a_i_11 = 0; a_i_11 < a_size_11; a_i_11++ ) {
                    a_result_11[a_i_11] = (String)readObject( in );
                }
                result = a_result_11;
                
                return result;
            case -1: /* NULL */
                return null;
        }
        throw new IllegalArgumentException("Unsupported return type (" + type + ")");
    }
}
//GEN-END:Client
