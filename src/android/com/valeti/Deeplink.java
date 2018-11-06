
package org.apache.cordova.test;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.util.Base64;

/**
* This class exposes methods in Cordova that can be called from JavaScript.
*/
public class Deeplink extends CordovaPlugin {


     public void initialize(CordovaInterface cordova, CordovaWebView webView) {
         super.initialize(cordova, webView);
     }

    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("echo")) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, args.getString(0)));
        } else if(action.equals("echoAsync")) {
            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    callbackContext.sendPluginResult( new PluginResult(PluginResult.Status.OK, args.optString(0)));
                }
            });
        } else {
            return false;
        }
        return true;
    }
}
