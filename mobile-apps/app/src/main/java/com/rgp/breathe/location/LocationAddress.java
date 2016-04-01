package com.rgp.breathe.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationAddress {
    private static final String TAG = "LocationAddress";

    public static String getAddressFromLatLang(double latitude, double longitude, Context context) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        String result = "";
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = (Address) addressList.get(0);
                StringBuilder sb = new StringBuilder();
                int addressLines = address.getMaxAddressLineIndex();
                for (int i = 0; i < addressLines; i++) {
                    sb.append(address.getAddressLine(i)).append(", ");
                }
                sb.append("++");
                sb.append(address.getLocality());
                return sb.toString().replace("null", "");
            } else {
                //Toast.makeText(context,"Location not found for latitude: " + latitude + " , lonitude: "+ longitude, Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Log.e(TAG, "Unable connect to Geocoder", e);
        }
        return result;
    }
}
