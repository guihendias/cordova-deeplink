/* global cordova:false */
/* globals window */

var exec = cordova.require("cordova/exec");
var PLUGIN_NAME = "Deeplink";

var Deeplink = {
    echo: (phrase, cb) =>
        exec(data => alert(data), data => alert(data), PLUGIN_NAME, "echo", [
            phrase
        ])
};

module.exports = Deeplink;
