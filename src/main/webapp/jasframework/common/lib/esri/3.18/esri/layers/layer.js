// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/layers/layer","dojo/_base/declare dojo/_base/config dojo/_base/connect dojo/_base/lang dojo/_base/Deferred dojo/_base/json dojo/has ../Evented ../kernel ../lang ../request ../deferredUtils ../urlUtils ../SpatialReference ../geometry/Extent".split(" "),function(q,r,g,h,s,t,n,u,l,k,v,w,x,p,y){var m=q([u],{declaredClass:"esri.layers.Layer",_eventMap:{error:["error"],load:["layer"],"opacity-change":["opacity"],"update-end":["error"],"visibility-change":["visible"]},constructor:function(a,
b){this._attrs={};if(a&&h.isString(a))this._url=x.urlToObject(this.url=a);else if(this.url=this._url=null,(b=b||a)&&(b.layerDefinition||b.query))b=null;this.spatialReference=new p(4326);this.initialExtent=new y(-180,-90,180,90,new p(4326));this._map=this._div=null;this.normalization=!0;b&&(b.id&&(this.id=b.id),!1===b.visible&&(this.visible=!1),k.isDefined(b.opacity)&&(this.opacity=b.opacity),k.isDefined(b.minScale)&&this.setMinScale(b.minScale),k.isDefined(b.maxScale)&&this.setMaxScale(b.maxScale),
this.attributionDataUrl=b.attributionDataUrl||"",this.hasAttributionData=!!this.attributionDataUrl,k.isDefined(b.showAttribution)&&(this.showAttribution=b.showAttribution),this.className=b.className,this.refreshInterval=b.refreshInterval||0);this._errorHandler=h.hitch(this,this._errorHandler);this.refresh=h.hitch(this,this.refresh);if(this.managedSuspension){var c=this._setMap;this._setMap=function(a){var b=c.apply(this,arguments);this.evaluateSuspension();if(this.suspended&&!a.loaded)var f=g.connect(a,
"onLoad",this,function(){g.disconnect(f);f=null;this.evaluateSuspension()});return b}}this.registerConnectEvents()},id:null,visible:!0,opacity:1,loaded:!1,loadError:null,minScale:0,maxScale:0,visibleAtMapScale:!1,suspended:!0,attributionDataUrl:"",hasAttributionData:!1,showAttribution:!0,refreshInterval:0,_errorHandler:function(a){this.loaded||(this.loadError=a);this.onError(a)},_setMap:function(a,b,c,d){this._map=a;this._lyrZEHandle=g.connect(a,"onZoomEnd",this,this._processMapScale);if(a.loaded)this.visibleAtMapScale=
this._isMapAtVisibleScale();else var e=g.connect(a,"onLoad",this,function(){g.disconnect(e);e=null;this._processMapScale()})},_unsetMap:function(a,b){g.disconnect(this._lyrZEHandle);this._toggleRT();this._map=this._lyrZEHandle=null;this._resumedOnce=void 0;this.suspended=!0},_cleanUp:function(){this._map=this._div=null},_fireUpdateStart:function(){this.updating||(this.updating=!0,this.attr("data-updating",""),this._toggleRT(),this.onUpdateStart(),this._map&&this._map._incr())},_fireUpdateEnd:function(a,
b){this.updating&&(this.updating=!1,this.attr("data-updating"),this._toggleRT(!0),this.onUpdateEnd(a,b),this._map&&this._map._decr())},_getToken:function(){var a=this._url,b=this.credential;return a&&a.query&&a.query.token||b&&b.token||void 0},_findCredential:function(){this.credential=l.id&&this._url&&l.id.findCredential(this._url.path)},_useSSL:function(){var a=this._url,b=/^http:/i;this.url&&(this.url=this.url.replace(b,"https:"));a&&a.path&&(a.path=a.path.replace(b,"https:"))},refresh:function(){},
show:function(){this.setVisibility(!0)},hide:function(){this.setVisibility(!1)},setMinScale:function(a){this.setScaleRange(a)},setMaxScale:function(a){this.setScaleRange(null,a)},setScaleRange:function(a,b){var c=k.isDefined(a),d=k.isDefined(b);this.loaded||(this._hasMin=this._hasMin||c,this._hasMax=this._hasMax||d);var e=this.minScale,f=this.maxScale;this.minScale=(c?a:this.minScale)||0;this.maxScale=(d?b:this.maxScale)||0;if(e!==this.minScale||f!==this.maxScale)this.onScaleRangeChange(),this._processMapScale()},
suspend:function(){this._suspended=!0;this.evaluateSuspension()},resume:function(){this._suspended=!1;this.evaluateSuspension()},canResume:function(){return this.loaded&&this._map&&this._map.loaded&&this.visible&&this.visibleAtMapScale&&!this._suspended},evaluateSuspension:function(){this.canResume()?this.suspended&&this._resume():this.suspended||this._suspend()},_suspend:function(){this.suspended=!0;this.attr("data-suspended","");this._toggleRT();this.onSuspend();if(this._map)this._map.onLayerSuspend(this)},
_resume:function(){this.suspended=!1;this.attr("data-suspended");var a=void 0===this._resumedOnce,b=this.className,c=this._attrs,d=this.getNode(),e;if(a){this._resumedOnce=!0;if(b&&d){var f=d.getAttribute("class")||"";RegExp("(^|\\s)"+b+"(\\s|$)","i").test(f)||d.setAttribute("class",f+((f?" ":"")+b))}if(c&&d)for(e in c)c.hasOwnProperty(e)&&d.setAttribute(e,c[e])}this._toggleRT(!0);this.onResume({firstOccurrence:a});if(this._map)this._map.onLayerResume(this)},_processMapScale:function(){var a=this.visibleAtMapScale;
this.visibleAtMapScale=this._isMapAtVisibleScale();a!==this.visibleAtMapScale&&(this.onScaleVisibilityChange(),this.evaluateSuspension())},isVisibleAtScale:function(a){return a?m.prototype._isMapAtVisibleScale.apply(this,arguments):!1},_isMapAtVisibleScale:function(a,b){if(!a&&(!this._map||!this._map.loaded))return!1;var c=this._map;a=a||c.getScale();var d=this.minScale,e=this.maxScale,f=!d,g=!e,h;b&&(h=c.width>c.height?c.width:c.height);f||(a<=d?f=!0:b&&(f=Math.abs(d-a)/d<1/h));g||(a>=e?g=!0:b&&
(g=Math.abs(e-a)/e<1/h));return f&&g},getAttributionData:function(){var a=this.attributionDataUrl,b=new s(w._dfdCanceller);this.hasAttributionData&&a?(b._pendingDfd=v({url:a,content:{f:"json"},handleAs:"json",callbackParamName:"callback"}),b._pendingDfd.then(function(a){b.callback(a)},function(a){b.errback(a)})):(a=Error("Layer does not have attribution data"),a.log=r.isDebug,b.errback(a));return b},getResourceInfo:function(){var a=this.resourceInfo;return h.isString(a)?t.fromJson(a):h.clone(a)},
getMap:function(){return this._map},getNode:function(){return this._div},attr:function(a,b){var c=this.getNode();if("data-reference"===a&&11>n("ie"))return this;c&&(null==b?c.removeAttribute(a):c.setAttribute(a,b));this._attrs&&(null==b?delete this._attrs[a]:this._attrs[a]=b);return this},setRefreshInterval:function(a){var b=this.refreshInterval;this.refreshInterval=a;this._toggleRT();a&&!this.updating&&!this.suspended&&this._toggleRT(!0);if(b!==a)this.onRefreshIntervalChange();return this},_toggleRT:function(a){a&&
this.refreshInterval?(clearTimeout(this._refreshT),this._refreshT=setTimeout(this.refresh,6E4*this.refreshInterval)):this._refreshT&&(clearTimeout(this._refreshT),this._refreshT=null)},setNormalization:function(a){this.normalization=a},setVisibility:function(a){this.visible!==a&&(this.visible=a,this.onVisibilityChange(this.visible),this.evaluateSuspension());this.attr("data-hidden",a?null:"")},onLoad:function(){},onVisibilityChange:function(){},onScaleRangeChange:function(){},onScaleVisibilityChange:function(){},
onSuspend:function(){},onResume:function(){},onUpdate:function(){},onUpdateStart:function(){},onUpdateEnd:function(){},onRefreshIntervalChange:function(){},onError:function(){}});n("extend-esri")&&h.setObject("layers.Layer",m,l);return m});