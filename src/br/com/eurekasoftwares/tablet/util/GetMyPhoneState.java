package br.com.eurekasoftwares.tablet.util;

import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;

public class GetMyPhoneState {
	private int sLevel = 100;
	private String serviceStateString = "UNKNOWN";
	private String connectionState = "Unknown";
	private String directionString = "none";
	private PhoneStateListener phoneStateListener;
	public GetMyPhoneState()
	{
		phoneStateListener = new PhoneStateListener() 
		{
		    @Override
		    public void onCallForwardingIndicatorChanged(boolean cfi) {

		        super.onCallForwardingIndicatorChanged(cfi);
		    }

		    @Override
		    public void onCallStateChanged(int state, String incomingNumber) {
		        //checkInternetConnection();
		        String callState = "UNKNOWN";
		        switch (state) {
		        case TelephonyManager.CALL_STATE_IDLE:
		            callState = "IDLE";
		            break;
		        case TelephonyManager.CALL_STATE_RINGING:
		            callState = "Ringing (" + incomingNumber + ")";
		            break;
		        case TelephonyManager.CALL_STATE_OFFHOOK:
		            callState = "Offhook";
		            break;
		        }

		        Log.i("Phone Stats", "onCallStateChanged " + callState);

		        super.onCallStateChanged(state, incomingNumber);
		    }

		    @Override
		    public void onCellLocationChanged(CellLocation location) {
		        String cellLocationString = location.toString();

		        super.onCellLocationChanged(location);



		    }

		    @Override
		    public void onDataActivity(int direction) {
		        switch (direction) {
		        case TelephonyManager.DATA_ACTIVITY_IN:
		            directionString = "IN";
		            break;
		        case TelephonyManager.DATA_ACTIVITY_OUT:
		            directionString = "OUT";
		            break;
		        case TelephonyManager.DATA_ACTIVITY_INOUT:
		            directionString = "INOUT";
		            break;
		        case TelephonyManager.DATA_ACTIVITY_NONE:
		            directionString = "NONE";
		            break;
		        default:
		            directionString = "UNKNOWN: " + direction;
		            break;
		        }

		        Log.i("Phone Stats", "onDataActivity " + directionString);

		        super.onDataActivity(direction);
		    }

		    @Override
		    public void onDataConnectionStateChanged(int state,int networktype) {
		        

		        switch (state ) {

		        case TelephonyManager.DATA_CONNECTED :
		            connectionState = "Connected";
		            break;
		        case TelephonyManager.DATA_CONNECTING:
		            connectionState = "Connecting";
		            break;
		        case TelephonyManager.DATA_DISCONNECTED:
		            connectionState = "Disconnected";
		            break;
		        case TelephonyManager.DATA_SUSPENDED:
		            connectionState = "Suspended";
		            break;
		        default:
		            connectionState = "Unknown: " + state;
		            break;
		        }

		        super.onDataConnectionStateChanged(state);


		        Log.i("Phone Stats", "onDataConnectionStateChanged "
		                + connectionState);


		    }

		    @Override
		    public void onMessageWaitingIndicatorChanged(boolean mwi) {

		        super.onMessageWaitingIndicatorChanged(mwi);
		    }

		    @Override
		    public void onServiceStateChanged(ServiceState serviceState) {
		        switch (serviceState.getState()) {
		        case ServiceState.STATE_IN_SERVICE:
		            serviceStateString = "IN SERVICE";
		            break;
		        case ServiceState.STATE_EMERGENCY_ONLY:
		            serviceStateString = "EMERGENCY ONLY";
		            break;
		        case ServiceState.STATE_OUT_OF_SERVICE:
		            serviceStateString = "OUT OF SERVICE";
		            break;
		        case ServiceState.STATE_POWER_OFF:
		            serviceStateString = "POWER OFF";
		            break;

		        default:
		            serviceStateString = "UNKNOWN";
		            break;
		        }

		        Log.i("Phone Stats", "onServiceStateChanged " + serviceStateString);

		        super.onServiceStateChanged(serviceState);
		    }

		    @Override
		    public void onSignalStrengthChanged(int asu) {

		        Log.i("Phone Stats", "onSignalStrengthChanged " + asu);
		        setSignalLevel( asu);
		        super.onSignalStrengthChanged(asu);
		    }
		    private void setSignalLevel(int level) {
		        sLevel = (int) ((level / 31.0) * 100);


		        Log.i("signalLevel ", "" + sLevel);
		    }

		};
	}
	public int getsLevel() {
		return sLevel;
	}
	public void setsLevel(int sLevel) {
		this.sLevel = sLevel;
	}
	public String getServiceStateString() {
		return serviceStateString;
	}
	public void setServiceStateString(String serviceStateString) {
		this.serviceStateString = serviceStateString;
	}
	public String getConnectionState() {
		return connectionState;
	}
	public void setConnectionState(String connectionState) {
		this.connectionState = connectionState;
	}
	public String getDirectionString() {
		return directionString;
	}
	public void setDirectionString(String directionString) {
		this.directionString = directionString;
	}
	public PhoneStateListener getPhoneStateListener() {
		return phoneStateListener;
	}
	public void setPhoneStateListener(PhoneStateListener phoneStateListener) {
		this.phoneStateListener = phoneStateListener;
	}
	
}
