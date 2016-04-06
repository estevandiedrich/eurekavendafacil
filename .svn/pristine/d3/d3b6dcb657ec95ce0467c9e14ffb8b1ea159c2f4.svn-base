package br.com.eurekasoftwares.tablet.map;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import br.com.eurekasoftwares.tablet.R;
import br.com.eurekasoftwares.tablet.cliente.ClientesListViewActivity;
import br.com.eurekasoftwares.tablet.constantes.Constantes;
import br.com.eurekasoftwares.tablet.map.MapRoute.RouteListener;
import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import br.com.eurekasoftwares.tablet.vo.CustomerVO;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapasSimplesActivity extends MapActivity {
	private MapView mapView = null;
	private Geocoder geocoder = null;
	private List<Address> foundAdresses = null;
	private ProgressDialog pd = null;
	private CustomerVO p = null;
	private double lat = 0.0d;
	private double lon = 0.0d;
	private GeoPoint pointSrc = null;
	private GeoPoint pointDest = null;

	private List<Overlay> mapOverlays = null;
	private Drawable drawable = null;
	private EurekaItemizedOverlay itemizedoverlay = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_view);
        
        mapView = (MapView) findViewById(R.id.map_view);
        mapView.setBuiltInZoomControls(true);
        mapView.setClickable(true);
        mapOverlays = mapView.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.google_maps_marker);
        itemizedoverlay = new EurekaItemizedOverlay(drawable);
        geocoder = new Geocoder(this.getApplicationContext());
        
        try
        {
	        lat = ModelSingleton.getInstance().getLocation().getLatitude();
	        lon = ModelSingleton.getInstance().getLocation().getLongitude();
	        long latLong = Math.round(lat * 1000000);
	        String latString = Long.toString(latLong);
	        int latInt = Integer.parseInt(latString);
	        long lonLong = Math.round(lon * 1000000);
	        String lonString = Long.toString(lonLong);
	        int lonInt = Integer.parseInt(lonString);
	        pointSrc = new GeoPoint(latInt,lonInt);
	        
        }
        catch(Exception e)
        {
        	Log.w(Constantes.EUREKA_SOFTWARES, "MapasSimplesActivity");
        	Log.w(Constantes.EUREKA_SOFTWARES, e);
        }
        
        Button voltarCliente = (Button)findViewById(R.id.voltar_cliente);
        voltarCliente.setOnClickListener(new Button.OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent i = new Intent(v.getContext().getApplicationContext(), ClientesListViewActivity.class);
			    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				v.getContext().getApplicationContext().startActivity(i);
			}
		});
        p = ModelSingleton.getInstance().getClienteMapa();
        pd = ProgressDialog.show(MapasSimplesActivity.this, "Aguarde", "Procurando endereço", true, false);
        Thread searchAdress = new Thread() {
            @Override
			public void run(){
                String addressInput = "Cidade " + p.getCidade() + " Bairro " + p.getBairro() + " " + p.getRua(); // Get input text
                try {
                    foundAdresses = geocoder.getFromLocationName(addressInput, 5); // Search addresses
                    Thread.sleep(1500);
                } catch (Exception e) {
                    // @todo: Show error message
                	Log.w(Constantes.EUREKA_SOFTWARES, "MapasSimplesActivity");
                	Log.w(Constantes.EUREKA_SOFTWARES, e);
                }
                showAdressResults.sendEmptyMessage(0);                      
            }
        };
        searchAdress.start();
	}
	private Handler showAdressResults = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();

            if (foundAdresses == null || foundAdresses.size() == 0) { // if no address found,
                // display an error
                Dialog locationError = new AlertDialog.Builder(
                		MapasSimplesActivity.this).setIcon(0).setTitle("Erro")
                		.setPositiveButton("ok", null)
                        .setMessage("Endereço não localizado.")
                        .create();
                locationError.show();

            } else { // else display address on map
                for (int i = 0; i < foundAdresses.size(); ++i) {
                    // Save results as Longitude and Latitude
                    // @todo: if more than one result, then show a
                    // select-list
                    Address x = foundAdresses.get(i);
                    lat = x.getLatitude();
                    lon = x.getLongitude();
                    long latLong = Math.round(lat * 1000000);
                    String latString = Long.toString(latLong);
                    int latInt = Integer.parseInt(latString);
                    long lonLong = Math.round(lon * 1000000);
                    String lonString = Long.toString(lonLong);
                    int lonInt = Integer.parseInt(lonString);
                    pointDest = new GeoPoint(latInt,lonInt);

                    if(pointSrc.getLatitudeE6() == 0.0d && pointSrc.getLongitudeE6() == 0.0d)
                    {
                    	OverlayItem overlayitem = new OverlayItem(pointDest, p.getNomeFantasia(), p.getCidade() + " " + p.getBairro() + " " + p.getRua());
                    	itemizedoverlay.addOverlay(overlayitem);
                    	mapOverlays.add(itemizedoverlay);                    	
                    }
                    else
                    {
                    	doDrawPath(pointSrc,pointDest);
                    }
                }
                navigateToLocation((lat * 1000000), (lon * 1000000),mapView); // display the found address
            }
        }
    };  
    /**
     * Navigates a given MapView to the specified Longitude and Latitude
     * 
     * @param latitude
     * @param longitude
     * @param mv
     */
    public static void navigateToLocation(double latitude, double longitude, MapView mv) {
        GeoPoint p = new GeoPoint((int) latitude, (int) longitude); // new
        // GeoPoint
        mv.displayZoomControls(true); // display Zoom (seems that it doesn't
        // work yet)
        MapController mc = mv.getController();
        mc.animateTo(p); // move map to the given point
        int zoomlevel = mv.getMaxZoomLevel(); // detect maximum zoom level
        mc.setZoom(zoomlevel - 1); // zoom
        mv.setSatellite(false); // display only "normal" mapview
    }

    private void doDrawPath(GeoPoint gpSrc,GeoPoint gpDest) 
    {
        MapRoute oRoute = new MapRoute(gpSrc,gpDest);
        oRoute.getPoints(new RouteListener()
        {
            @Override
            public void onDetermined(ArrayList<GeoPoint> alPoint) 
            {
                GeoPoint oPointA = null;
                GeoPoint oPointB = null;

                mapView.getOverlays().clear();

                for(int i=1; i<alPoint.size()-1; i++)
                {
                    oPointA = alPoint.get(i-1);
                    oPointB =  alPoint.get(i);

                    mapView.getOverlays().add(new MapRouteOverlay(oPointA,oPointB,2,Color.BLUE));
                }


                OverlayItem overlayitem = new OverlayItem(alPoint.get(0), "Eu", "Eu");
                itemizedoverlay.addOverlay(overlayitem);
                mapOverlays.add(itemizedoverlay);
                
                OverlayItem overlayitem2 = new OverlayItem(alPoint.get(alPoint.size()-1), p.getNomeFantasia(), p.getCidade() + " " + p.getBairro() + " " + p.getRua());
                itemizedoverlay.addOverlay(overlayitem2);
                mapOverlays.add(itemizedoverlay);
//                mapView.getOverlays().add(new MapRoutePinOverlay(alPoint.get(0),drawable));
//                mapView.getOverlays().add(new MapRoutePinOverlay(alPoint.get(alPoint.size()-1),drawable));

                mapView.invalidate();
            }

            @Override
            public void onError() 
            {
            }           
        });
    }
    @Override
	public void onAttachedToWindow() {
	    // TODO Auto-generated method stub
	    this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
	    super.onAttachedToWindow();
	}
    @Override
    public void onBackPressed() {
    }
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
