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
    private final String ARG_EC = "ec";
    private final String ARG_NUM_SERIE = "numserie";
    private final String ARG_NUM_LOGIC = "numlogic";
    private final String ARG_VERSION = "version";
    private final String ARG_CNPJ = "cnpjEC";
    private final String ARG_NAME = "nomeEC";

    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext)
            throws JSONException {
        PUBLIC_CALLBACKS = callbackContext;

        Bundle bundle = new Bundle();
        bundle.putString("currencyPosition", "CURRENCY_AFTER_AMOUNT");
        bundle.putString("currencyCode", "986");

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("getnet://pagamento/v1/" + action));

        if(action.equals("payment")){
            bundle.putString("paymentType", args.getString(0));
            bundle.putString("amount", args.getString(1));

        } else if (action.equals("refund")) {
         //TODO
        }

        intent.putExtras(bundle);
        cordova.startActivityForResult((CordovaPlugin) this, intent, REQUEST_CODE);

        PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true);

        return true;
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == cordova.getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();// Get data sent by the Intent
            JSONObject json = new JSONObject();

            try {
                json.put("result", extras.getString(ARG_RESULT));
                json.put("resultDetails", extras.getString(ARG_RESULT_DETAILS));
                json.put("amount", extras.getString(ARG_AMOUNT));
                json.put("type", extras.getString(ARG_TYPE));
                json.put("inputType", extras.getString(ARG_INPUT_TYPE));
                json.put("installments", extras.getString(ARG_INSTALLMENTS));
                json.put("nsu", extras.getString(ARG_NSU));
                json.put("brand", extras.getString(ARG_BRAND));
                json.put("ec", extras.getString(ARG_EC));
                json.put("numSerie", extras.getString(ARG_NUM_SERIE));
                json.put("numLogic", extras.getString(ARG_NUM_LOGIC));
                json.put("apiVersion", extras.getString(ARG_VERSION));
                json.put("cnpj", extras.getString(ARG_CNPJ));
                json.put("name", extras.getString(ARG_NAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            PluginResult resultado = new PluginResult(PluginResult.Status.OK, json);
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
