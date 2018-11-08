/* global cordova:false */
/* globals window */

var exec = cordova.require("cordova/exec");
var PLUGIN_NAME = 'Deeplink';

var Deeplink = {
    payment: (args, cb) => exec(cb, (data) => alert(data), PLUGIN_NAME, "payment", args),
    preAuthorization: (args, cb) => exec(cb, (data) => alert(data), PLUGIN_NAME, "pre-authorization", args),
    refund: (args, cb) => exec(cb, (data) => alert(data), PLUGIN_NAME, "refund", args),
    reprint: (args, cb) => exec(cb, (data) => alert(data), PLUGIN_NAME, "reprint", args),
    getInfos: (args, cb) => exec(cb, (data) => alert(data), PLUGIN_NAME, "getinfos", args)
};

module.exports = Deeplink;
