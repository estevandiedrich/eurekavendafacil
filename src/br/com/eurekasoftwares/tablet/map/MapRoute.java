package br.com.eurekasoftwares.tablet.map;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.Handler;
import android.util.Log;
import br.com.eurekasoftwares.tablet.constantes.Constantes;

import com.google.android.maps.GeoPoint;

public class MapRoute {
	private GeoPoint gpSrc = null;
	private GeoPoint gpDest = null;
	private ArrayList<GeoPoint> alRoute = new ArrayList<GeoPoint>();
	private Handler haRoute = new Handler();

	public interface RouteListener
	{
	    public void onDetermined(ArrayList<GeoPoint> alPoint);
	    public void onError();
	}

	private RouteListener oRoute = null;


	public MapRoute(GeoPoint gpSrc,GeoPoint gpDest)
	{
	    this.gpSrc = gpSrc;
	    this.gpDest = gpDest;
	}

	public void getPoints(RouteListener oRoute)
	{
	    this.oRoute = oRoute;
	    new Thread(ruFetch).start();
	}

	private Runnable ruFetchOk = new Runnable()
	{
	    @Override
		public void run()
	    {
	        oRoute.onDetermined(alRoute);
	    }
	};

	private Runnable ruFetchError = new Runnable()
	{
	    @Override
		public void run()
	    {
	        oRoute.onDetermined(alRoute);
	    }
	};

	private Runnable ruFetch = new Runnable()
	{
	    @Override
		public void run()
	    {
	        String szUrl = "http://maps.googleapis.com/maps/api/directions/xml";
	        szUrl += "?origin=" + (gpSrc.getLatitudeE6()/1e6) + "," + (gpSrc.getLongitudeE6()/1e6);
	        szUrl += "&destination=" + (gpDest.getLatitudeE6()/1e6) + "," + (gpDest.getLongitudeE6()/1e6);
	        szUrl += "&sensor=true";

	        HttpGet uri = new HttpGet(szUrl);    

	        DefaultHttpClient client = new DefaultHttpClient();
	        HttpResponse resp = null;
			try {
				resp = client.execute(uri);
			} catch (ClientProtocolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

	        StatusLine status = resp.getStatusLine();
	        if (status.getStatusCode() != 200) {
	            Log.w(Constantes.EUREKA_SOFTWARES, "HTTP error, invalid server status code: " + resp.getStatusLine());  
	        }
	        
	        String szXml = "";
			try {
				szXml = IOUtils.toString(resp.getEntity().getContent());
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	        try
	        {
	            XmlPullParserFactory xppfFactory = XmlPullParserFactory.newInstance();
	            xppfFactory.setNamespaceAware(true);
	            XmlPullParser xppParses = xppfFactory.newPullParser();

	            xppParses.setInput(new StringReader(szXml));
	            int iEventType = xppParses.getEventType();
	            String szTag = "";
	            String szText = "";
	            boolean bStep = false;
	            int iLat = 0;
	            int iLong = 0;

	            while(iEventType != XmlPullParser.END_DOCUMENT) 
	            {     
	                 iEventType = xppParses.next();

	                 if(iEventType == XmlPullParser.START_TAG) 
	                 {
	                     szTag = xppParses.getName();

	                     if(szTag.equals("step"))
	                         bStep = true;
	                 }
	                 else if(iEventType == XmlPullParser.TEXT)      
	                 {  
	                     if(szTag.equals("points"))
	                         szText = "";
	                     else
	                         szText = xppParses.getText().trim();
	                 }          
	                 else if(iEventType == XmlPullParser.END_TAG) 
	                 {
	                     if(xppParses.getName().equals("step"))
	                     {
	                         bStep = false;
	                     }
	                    else if(bStep && xppParses.getName().equals("start_location") || xppParses.getName().equals("end_location"))
	                     {
	                         GeoPoint gpPoint = new GeoPoint(iLat,iLong);
	                         alRoute.add(gpPoint);
	                     }
	                     else if(bStep && xppParses.getName().equals("lat"))
	                     {
	                         iLat = (int)(Double.parseDouble(szText) * 1e6);
	                     }
	                     else if(bStep && xppParses.getName().equals("lng"))
	                     {
	                         iLong = (int)(Double.parseDouble(szText) * 1e6);
	                     }
	                 }
	            }
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            haRoute.post(ruFetchError);
	        }


	        if(alRoute.size() == 0)
	            haRoute.post(ruFetchError);
	        else
	            haRoute.post(ruFetchOk);
	    }
	};
}
