package com.util;

import android.app.Activity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class VolleyUtils {
    private static int mResultCode;

    public VolleyUtils() {
    }

    public static void GET_METHOD(final Activity mContext, final ApiResultCallback apiResultCallback, String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        apiResultCallback.getResult_Callback(response, mResultCode);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        apiResultCallback.getResult_Callback(error.toString(), mResultCode);
                    }
                }) {

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                mResultCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Access the RequestQueue through singleton class.
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }


}
