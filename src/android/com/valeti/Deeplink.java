
package org.apache.cordova.test;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.util.Base64;

/**
* This class exposes methods in Cordova that can be called from JavaScript.
*/
public class Deeplink extends CordovaPlugin {

    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("echo")) {
            callbackContext.success(args.getString(0));
        } else {
            callbackContext.error("error");
            return false;
        }
        return true;
    }
}
