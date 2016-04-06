package br.com.eurekasoftwares.tablet.map;

import br.com.eurekasoftwares.tablet.model.ModelSingleton;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GetMyGPSLocation {
	public GetMyGPSLocation(Activity activity)
	{
		//*********************************************//
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            @Override
			public void onLocationChanged(Location location) {
              // Called when a new location is found by the network location provider.
              makeUseOfNewLocation(location);
            }

            @Override
			public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
			public void onProviderEnabled(String provider) {}

            @Override
			public void onProviderDisabled(String provider) {}
          };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        //*********************************************//
	}
	public void makeUseOfNewLocation(Location location)
	{
		ModelSingleton.getInstance().setLocation(location);
	}
}
