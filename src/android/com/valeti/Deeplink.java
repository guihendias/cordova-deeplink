package com.valeti;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * This class exposes methods in Cordova that can be called from JavaScript.
 */
public class Deeplink extends CordovaPlugin {

    private CallbackContext PUBLIC_CALLBACKS = null;
    private final int REQUEST_CODE = 1001;
    private final String ARG_RESULT = "result";
    private final String ARG_RESULT_DETAILS = "resultDetails";
    private final String ARG_AMOUNT = "amount";
    private final String ARG_TYPE = "type";
    private final String ARG_INPUT_TYPE = "inputType";
    private final String ARG_INSTALLMENTS = "installments";
    private final String ARG_NSU = "nsu";
    private final String ARG_BRAND = "brand";

    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext)
            throws JSONException {
        if (action.equals("payment")) {
            Bundle bundle = new Bundle();
            bundle.putString("amount", "000000001000");
            bundle.putString("currencyPosition", "CURRENCY_AFTER_AMOUNT");
            bundle.putString("currencyCode", "986");

            Intent intent = new Intent("getnet://pagamento/v1/payment");

            intent.putExtras(bundle);
            cordova.startActivityForResult((CordovaPlugin) this, intent, REQUEST_CODE);
            // callbackContext.success(args.getString(0));
        }

        PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true);

        return true;
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == cordova.getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();// Get data sent by the Intent
            String result = extras.getString(ARG_RESULT); // data parameter will be send from the other activity.
            String resultDetails = extras.getString(ARG_RESULT_DETAILS);
            String amount = extras.getString(ARG_AMOUNT);
            String type = extras.getString(ARG_TYPE);
            String inputType = extras.getString(ARG_INPUT_TYPE);
            String installments = extras.getString(ARG_INSTALLMENTS);
            String nsu = extras.getString(ARG_NSU);
            String brand = extras.getString(ARG_BRAND);

            PluginResult resultado = new PluginResult(PluginResult.Status.OK, "this value will be sent to cordova");
            resultado.setKeepCallback(true);
            PUBLIC_CALLBACKS.sendPluginResult(resultado);

            return;
        } else if (resultCode == cordova.getActivity().RESULT_CANCELED) {
            PluginResult resultado = new PluginResult(PluginResult.Status.OK,
                    "canceled action, process this in javascript");
            resultado.setKeepCallback(true);
            PUBLIC_CALLBACKS.sendPluginResult(resultado);
            return;
        }
        // Handle other results if exists.
        super.onActivityResult(requestCode, resultCode, data);
    }
}
