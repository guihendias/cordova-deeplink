/* global cordova:false */
/* globals window */

var exec = cordova.require("cordova/exec");
var PLUGIN_NAME = 'Deeplink';

var Deeplink = {
    payment: (phrase, cb) => exec(cb, (data) => alert(data), PLUGIN_NAME, "payment", [phrase]),
    preAuthorization: (phrase, cb) => exec(cb, (data) => alert(data), PLUGIN_NAME, "pre-authorization", [phrase]),
    refund: (phrase, cb) => exec(cb, (data) => alert(data), PLUGIN_NAME, "refund", [phrase]),
    reprint: (phrase, cb) => exec(cb, (data) => alert(data), PLUGIN_NAME, "reprint", [phrase]),
    getInfos: (phrase, cb) => exec(cb, (data) => alert(data), PLUGIN_NAME, "getinfos", [phrase])
};

module.exports = Deeplink;
