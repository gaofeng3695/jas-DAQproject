// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/opsdashboard/featureActionProxy",["dojo/_base/declare","dojo/_base/lang","../tasks/FeatureSet","./core/ExtensionBase"],function(b,d,e,f){return new (b([f],{_messageReceived:function(a){"execute"===a.functionName.toLowerCase()&&this.getDataSourceProxy(a.args.dataSourceId).then(d.hitch(this,function(c){this._execute(c,new e(a.args.featureSet),a.args.configuration||a.args.config)}))},_execute:function(a,c,b){this.emit("execute",{dataSourceProxy:a,featureSet:c,config:b})}}))});