// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/tasks/ImageServiceProjectTask","dojo/_base/declare dojo/_base/lang dojo/has dojo/_base/Deferred dojo/_base/array ../kernel ../request ../deferredUtils ./Task ../urlUtils ../geometry/jsonUtils".split(" "),function(c,h,k,n,p,q,r,s,t,u,l){c=c(t,{constructor:function(){this._handler=h.hitch(this,this._handler)},execute:function(a,b,d){var e=this._getImageServiceUrl(a),f=new n(s._dfdCanceller);if(e){var e=u.urlToObject(e),v=this._encode(h.mixin({},e.query,{f:"json"},a.toJson())),g=this._handler,
m=this._errorHandler,c=l.getJsonType(a.geometries[0]),k=a.outSR;f._pendingDfd=r({url:e.path+"/project",content:v,callbackParamName:"callback",load:function(a){g(a,c,k,b,d,f)},error:function(a){m(a,d,f)}})}else f.reject("Input arguments do not contain image service URL.");return f},_handler:function(a,b,d,e,f,c){try{var g=this._decodeGeometries(a,b,d);this._successHandler([g],"onComplete",e,c)}catch(m){this._errorHandler(m,f,c)}},_encodeGeometries:function(a){var b=[],d,e=a.length;for(d=0;d<e;d++)b.push(a[d].toJson());
return{geometryType:l.getJsonType(a[0]),geometries:b}},_decodeGeometries:function(a,b,d){var e=l.getGeometryType(b),f=[],c={spatialReference:d},g=h.mixin;p.forEach(a.geometries,function(a,b){f[b]=new e(g(a,c))});return f},onComplete:function(){},_getImageServiceUrl:function(a){var b;if(a&&a.geometries&&a.geometries.length&&a.outSR)if(!a.geometries[0].spatialReference&&!a.geometries[0].spatialReference.url&&!a.outSR.url)this.onError(Error("Spatial reference doesn't contain Image Service URL"));else if(a.geometries[0].spatialReference.url&&
a.outSR.url&&a.geometries[0].spatialReference.url!==a.outSR.url)this.onError(Error("Input and output spatial reference must be from one image service"));else b=a.geometries[0].spatialReference&&a.geometries[0].spatialReference.url?a.geometries[0].spatialReference.url:a.outSR.url;return b}});k("extend-esri")&&h.setObject("tasks.ImageServiceProjectTask",c,q);return c});