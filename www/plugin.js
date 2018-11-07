/* global cordova:false */
/* globals window */

var exec = cordova.require("cordova/exec");
var PLUGIN_NAME = 'Deeplink';

var Deeplink = {
    payment: (phrase, cb) => exec(cb, (data) => alert(data), PLUGIN_NAME, "payment", [phrase])
};

module.exports = Deeplink;
