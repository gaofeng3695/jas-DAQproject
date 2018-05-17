// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/dijit/geoenrichment/DataBrowser/_DerivativeVariables",["dojo/_base/declare","dojo/_base/lang","dojo/_base/array","dojo/i18n!../../../nls/jsapi"],function(h,k,e,f){f=f.geoenrichment.dijit.VariableStore;var d=function(){function b(a,b){var c=f[a];return void 0!==c?c:b}var a=b("number","#"),c=b("percent","%"),d=b("average","Avg"),g=b("index","Index"),e=b("reliability","Reliability");return{_N_P:{labels:[a,c],names:["_N","_P"]},_N_A:{labels:[a,d],names:["_N","_A"]},_N_I:{labels:[a,g],names:["_N",
"_I"]},_N_R:{labels:[a,e],names:["_N","_R"]},_N_P_I:{labels:[a,c,g],names:["_N","_P","_I"]},_N_A_I:{labels:[a,d,g],names:["_N","_A","_I"]},_N_P_R:{labels:[a,c,e],names:["_N","_P","_R"]}}},d=d();return h(null,{_statesHash:null,_getDataCollections:function(b){var a=this.variableFields;a&&(a=a.slice(),a.push("derivative"));this._statesHash={};return this._getGeoenrichmentTask().getDataCollections(b,null,a,["all"])},_processDataCollections:function(b){this.inherited(arguments);e.forEach(this._data,this._processDerivativeVariable,
this)},_processDerivativeVariable:function(b){b.derivative=!!b.derivative;var a=this._getDerivingState(b);if(a){var c=this._getBaseVariable(b,a);c&&(c._base=!0,c[a]=!0,b.derivative=a,this._registerTogether(b,c))}},_getDerivingState:function(b){var a=b.id;return b.derivative?a.substr(a.length-2):"US"==this.getCurrentCountryID()&&"REL"==a.substr(0,3)?"_R":null},_getBaseVariable:function(b,a){var c=b.id,c="_R"===a?"ACS"+c.substr(3):c.substr(0,c.length-2);return this.get(this._composeIdentity(b,c))},
_registerTogether:function(b,a){var c=this._composeIdentity(a,b.id);this._variables[c]=b},_composeIdentity:function(b,a){var c=b[this.idProperty],c=c.substr(0,c.length-b.id.length-1);return c+"."+a},queryFilter:function(b){return"string"!=typeof b.derivative},getStates:function(b){(b=this.get(b))&&"string"==typeof b.derivative&&(b=this._getBaseVariable(b,b.derivative));if(!b||!b._base)return null;var a=this._statesHash[b[this.idProperty]];void 0===a&&(a=this._createStates(b),this._statesHash[b[this.idProperty]]=
a);return a},_createStates:function(b){var a="_N";b._P&&(a+="_P");b._A&&(a+="_A");b._I&&(a+="_I");b._R&&(a+="_R");if(a=d[a])a=k.mixin({},a),a.ids=e.map(a.names,function(a){return this._getRelatedVariable(b,a)[this.idProperty]},this);return a||null},_getRelatedVariable:function(b,a){if("_N"==a)return b;var c=b.id,c="_R"===a?"REL"+c.substr(3):c+a;return this.get(this._composeIdentity(b,c))}})});