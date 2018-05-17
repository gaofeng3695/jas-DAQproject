// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
require({cache:{"esri/layers/StreamTrackManager":function(){define("dojo/_base/declare dojo/_base/lang dojo/_base/array dojo/has ../kernel ../graphic ../geometry/Polyline ./TrackManager".split(" "),function(l,v,d,u,r,x,y,z){l=l([z],{declaredClass:"esri.layers._StreamTrackManager",constructor:function(d){this.inherited(arguments)},initialize:function(d){this.inherited(arguments)},addFeatures:function(h,m){function n(e,g){var d,a,b,c;p[e]||(p[e]=[]);d=p[e];0<q&&(g.length>q&&g.splice(0,g.length-q),b=
g.length+d.length,b>q&&(a=d.splice(0,b-q)));b=g.length;for(c=0;c<b;c+=1)d.push(g[c]);return{deletes:a,adds:g}}var p,e,w,q,f={},g={},s;if(m)return this.inherited(arguments),f;p=this.trackMap;e=this.layer;w=e._trackIdField;q=e.maximumTrackPoints||0;d.forEach(h,function(e){var d=e.attributes[w];e.visible&&(g[d]||(g[d]=[]),g[d].push(e))});for(s in g)g.hasOwnProperty(s)&&(e=n(s,g[s]),f[s]=e);return f},removeFeatures:function(h){var m=[],n=this.layer.objectIdField,p=this.layer._trackIdField;h&&(d.forEach(h,
function(e){var h,q,f,g;q=e.attributes[p];h=e.attributes[n];if(f=this.trackMap[q])for(e=0;e<f.length;e+=1)if(g=f[e],g.attributes[n]===h){this.trackMap[q].splice(e,1);-1===d.indexOf(q)&&m.push(q);break}},this),0<h.length&&this.refreshTracks(m))},drawTracks:function(h){function m(g){var d=e[g],f=d&&1<d.length,h,m,a;if((a=n.trackLineMap[g])&&!f)p.remove(a),delete n.trackLineMap[g],a=null;if(!f)return!1;f=[];for(h=d.length-1;0<=h;h-=1)(m=d[h].geometry)&&f.push([m.x,m.y]);d={};d[q]=g;1<f.length&&(a?(g=
a.geometry,g.removePath(0),g.addPath(f),a.setGeometry(g)):(a=new x(new y({paths:[f],spatialReference:l}),null,d),p.add(a),n.trackLineMap[g]=a))}var n=this,p=this.container,e,l,q,f;if(p)if(e=this.trackMap,l=this.map.spatialReference,q=this.layer._trackIdField,h)d.forEach(h,function(d){m(d)});else for(f in e)e.hasOwnProperty(f)&&m(f)},refreshTracks:function(h){function m(d){var e,f;d=n[d]||[];e=d.length;for(f=0;f<e;f++)p._repaint(d[f],null,!0)}var n=this.trackMap,p=this.layer;p._getRenderer();var e;
this.drawTracks(h);if(h)d.forEach(h,function(d){m(d)});else for(e in n)n.hasOwnProperty(e)&&m(e)},getLatestObservations:function(){var d,m,n=this.trackMap,p=[];for(d in n)n.hasOwnProperty(d)&&(m=n[d],p.push(m[m.length-1]));return p},destroy:function(){this.inherited(arguments);this.trackLineMap=null}});u("extend-esri")&&v.setObject("layers._StreamTrackManager",l,r);return l})},"esri/layers/PurgeOptions":function(){define(["dojo/_base/declare","dojo/_base/lang","dojo/Stateful","dojo/has","../kernel"],
function(l,v,d,u,r){l=l([d],{declaredClass:"esri.layers.PurgeOptions",constructor:function(d,l){this.parent=d;for(var r in l)this[r]=l[r]},_displayCountSetter:function(d){this.displayCount=d;this.parent.refresh()}});u("extend-esri")&&v.setObject("layers.PurgeOptions",l,r);return l})},"*noref":1}});
define("esri/layers/StreamLayer","dojo/_base/declare dojo/_base/connect dojo/_base/array dojo/_base/Color dojo/_base/lang dojo/has dojo/io-query dojo/on dojo/aspect ../kernel ../request ../graphic ./FeatureLayer ./StreamMode ./StreamTrackManager ../geometry/jsonUtils ../symbols/SimpleFillSymbol ../symbols/SimpleLineSymbol ../symbols/SimpleMarkerSymbol ../renderers/SimpleRenderer ./PurgeOptions".split(" "),function(l,v,d,u,r,x,y,z,h,m,n,p,e,w,q,f,g,s,A,C,B){l=l([e],{declaredClass:"esri.layers.StreamLayer",
_preventInit:!0,constructor:function(a,b){b=b||{};if(!b.mode||b.mode===e.MODE_STREAM)this._isStream=!0,this.currentMode=this.mode=e.MODE_STREAM,this._mode=new w(this);this.purgeOptions=new B(this,b.purgeOptions||{});this.purgeInterval=b.purgeInterval||0;this._reconnectAttempts=0;this.maxReconnectAttempts=10;this.socket=this._reconnectTimeoutId=null;this._keepLatestQueried=!1;this._keepLatestUrl=null;this._relatedQueried=!1;this._joinField=this._relatedUrl=null;this._refreshing=!1;this._attemptReconnect=
r.hitch(this,this._attemptReconnect);this._purge=r.hitch(this,this._purge);this._processServiceJson=r.hitch(this,this._processServiceJson);if(r.isObject(a)&&a.layerDefinition)this._initFeatureLayer(a,b);else{var c=this;n({url:a,content:r.mixin({f:"json"}),callbackParamName:"callback"}).then(function(a){c._processServiceJson(a,b)},function(a){c._errorHandler(a)})}},_processServiceJson:function(a,b){var c=this;a.relatedFeatures&&a.relatedFeatures.featuresUrl&&a.relatedFeatures.joinField?(this._relatedUrl=
a.relatedFeatures.featuresUrl,this.objectIdField=this._joinField=a.relatedFeatures.joinField,n({url:this._relatedUrl,content:{f:"json"},callbackParamName:"callback"}).then(function(k){k=k.fields||[];var t=d.map(a.fields,function(a){return a.name});d.forEach(k,function(c){-1===d.indexOf(t,c.name)&&a.fields.push(c)});b.resourceInfo=a;c._initFeatureLayer(c._url,b)},function(a){c.onError({error:a})})):(b.resourceInfo=a,this._initFeatureLayer(this._url,b))},_initLayer:function(a,b){this.inherited(arguments);
if(a){var c;if(a.layerDefinition)this.purgeOptions=new B(this,this._params.purgeOptions||{}),this.socketUrl=this._params.socketUrl||a.layerDefinition.socketUrl||void 0;else{if(this._params.socketUrl)this.socketUrl=this._params.socketUrl;else{var k=this._getWebsocketConnectionInfo(a),t=k.urls;t&&t.length?(this._socketUrls=t,this.socketUrl=t[0],this.socketDirection="broadcast"===this._params.socketDirection?"broadcast":"subscribe",this.socketUrl+="/"+this.socketDirection,this._websocketToken=k.token,
t.length>this.maxReconnectAttempts&&(this.maxReconnectAttempts=t.length)):(this.socketUrl=void 0,k="No web socket urls were retrieved from the Stream Service. Layer will not attempt to connect.","https:"===location.protocol&&(k+=" An insecure web socket connection cannot be made from a secure web page."),this.onError(Error(k)))}if(this._params.filter)try{this._filter=c=this._makeFilter(this._params.filter)}catch(d){this.onError(Error("Error trying to create filter object: "+d+". Layer will not have filter applied")),
this._filter=null}if(this._params.geometryDefinition||this._outFields||this._defnExpr){c=c||{};c.geometry=this._params.geometryDefinition;c.outFields=this._outFields;c.where=this._defnExpr;try{this._filter=c=this._makeFilter(c)}catch(e){this.onError(Error("Error trying to create filter object: "+e+". Layer will not have filter applied")),this._filter=null}}}this.maximumTrackPoints=this._params.maximumTrackPoints||0===this._params.maximumTrackPoints?this._params.maximumTrackPoints:1;(this._params.refreshRate||
0===this._params.refreshRate)&&this._mode&&this._mode._setRefreshRate&&this._mode._setRefreshRate(this._params.refreshRate);this._keepLatestUrl=a.keepLatestArchive?a.keepLatestArchive.featuresUrl:null;a.relatedFeatures&&(a.relatedFeatures.featuresUrl&&a.relatedFeatures.joinField)&&(this._relatedUrl=a.relatedFeatures.featuresUrl,this.objectIdField=this._joinField=a.relatedFeatures.joinField);this.objectIdField||this._makeObjectIdField();this._map&&(this.socketUrl&&!this._connected)&&this.connect()}},
_setMap:function(a){var b=this.inherited(arguments),c=this._getRenderer();if(this.timeInfo&&(this._trackIdField||c&&(c.latestObservationRenderer||c.trackRenderer)))this._trackManager=new q(this),this._trackManager.initialize(a);this.socketUrl&&(!this._connected&&this._map)&&this.connect();return b},_unsetMap:function(a,b){d.forEach(this._connects,v.disconnect);(this._connected||this._reconnecting||this.socket)&&this.disconnect();this._togglePurgeT();this.inherited(arguments);this._map=null},_suspend:function(){this._togglePurgeT();
this.inherited(arguments)},_resume:function(){this.inherited(arguments);this._togglePurgeT(!0)},clear:function(){try{this._mode&&this._mode._clearDrawBuffer&&this._mode._clearDrawBuffer(),this._mode&&this._mode._clearTimeBin&&this._mode._clearTimeBin(),this._mode&&this._mode._clearFeatureMap&&this._mode._clearFeatureMap(),this._trackManager&&this._trackManager.clearTracks()}catch(a){}this.inherited(arguments)},redraw:function(){this._mode&&this._mode._flushDrawBuffer&&this._mode._flushDrawBuffer();
this.inherited(arguments)},setDefinitionExpression:function(a){this.setFilter({where:a})},getDefinitionExpression:function(){var a;this._filter&&(a=this._filter.where);return a},destroy:function(){this.disconnect();this.inherited(arguments)},onResume:function(a){this.inherited(arguments)},setGeometryDefinition:function(a){this.setFilter({geometry:a})},getGeometryDefinition:function(){var a;this._filter&&(a=this._filter.geometry);return a},connect:function(a){var b=this,c={},k=this._filter,t,d,e=this.socketUrl,
g;try{if(!this._connected){if(this._map){var f;if(this._relatedUrl&&!this._relatedQueried&&this._mode._fetchArchive)return f=this.on("update-end",function(a){b._relatedQueried=!0;f.remove();f=null;a&&a.error?console.log("Not connecting. Error fetching related features"):b.connect()}),this._mode._fetchArchive(this._relatedUrl),!1;if(this._keepLatestUrl&&!this._keepLatestQueried&&this._mode._fetchArchive)return f=this.on("update-end",function(a){b._keepLatestQueried=!0;f.remove();f=null;a&&a.error?
console.log("Not connecting. Error fetching archived features"):b.connect()}),this._mode._fetchArchive(this._keepLatestUrl),!1}this._websocketToken&&(c.token=this._websocketToken);this._map&&(this._map.spatialReference&&this.spatialReference)&&this._map.spatialReference.wkid!==this.spatialReference.wkid&&(c.outSR=this._map.spatialReference.wkid);if(k)for(d in k)null!==k[d]&&(t="geometry"===d?JSON.stringify(k[d]):k[d],c[d]=t);e+="?"+y.objectToQuery(c);return g=this._createConnection(e,a)}}catch(h){console.log("Error connecting to data stream: ",
h),a&&a(h,!1),this.onConnectionError({error:h})}},disconnect:function(a){var b=this._refreshing?"Disconnecting as part of layer refresh cycle":"Connection closed from client",c=this._refreshing?!0:!1;this._reconnectTimeoutId&&clearTimeout(this._reconnectTimeoutId);this._refreshing=this._reconnecting=this._connected=!1;this.socket&&this.socket.close();this.onDisconnect({willReconnect:c,message:b});a&&a(null,!0)},setFilter:function(a){var b,c;if(this._collection)return this.onError("Filter can only be set when the source of the layer is a Stream Service"),
!1;try{if(void 0!==a.outFields)return c=Error("Outfields property cannot be changed after layer is created"),this.onFilterChange({filter:this.getFilter(),error:c}),!1;b=this._makeFilter(a)}catch(k){return c=Error(k),this.onFilterChange({filter:this.getFilter(),error:c}),!1}if(this.socket)a={filter:b},this.socket.send(JSON.stringify(a));else z.once(this,"connect",function(a){this.setFilter(b)});return!0},getFilter:function(){var a=this._filter,b={},c=["geometry","outFields","where"];a&&d.forEach(c,
function(c){var d=a[c];d&&("geometry"===c?d=f.fromJson(d):"outFields"===c&&(d=d.split(",")),b[c]=d)});return b},setMaximumTrackPoints:function(a){if(!a&&0!==a)return!1;this.maximumTrackPoints=a;this._mode.propertyChangeHandler(3)},getUniqueValues:function(a){var b,c={},k=[];b=this._getField(a,!0);if(!b)return k;a=b.name;d.forEach(this.graphics||[],function(b){b=(b.attributes||{})[a];void 0!==b&&!c[b]&&(c[b]=1,k.push(b))});k.sort(function(a,c){return a<c?-1:a>c?1:0});return k},getLatestObservations:function(){var a=
[];return a=this._trackManager?this._trackManager.getLatestObservations():this.graphics},setPurgeInterval:function(a){var b=this.purgeInterval;this.purgeInterval=a;this._togglePurgeT();a&&this._togglePurgeT(!0);if(b!==a)this.onPurgeIntervalChange();return this},_togglePurgeT:function(a){if(a&&this.purgeInterval){var b=this;clearTimeout(this._purgeT);this._mode&&this._mode._flushDrawBuffer&&(this._purgeT=setTimeout(function(){!b.updating&&!b.suspended&&(b._mode._flushDrawBuffer(),b._togglePurgeT(!0))},
6E4*this.purgeInterval))}else this._purgeT&&(clearTimeout(this._purgeT),this._refreshT=null)},onMessage:function(){},onConnect:function(){},onDisconnect:function(){},onFilterChange:function(){},onAttemptReconnect:function(){},onConnectionError:function(){},onPurgeIntervalChange:function(){},_createConnection:function(a,b){var c=this,d=new WebSocket(a);d.onopen=function(){c.socket=d;c._connected=!0;c._reconnecting=!1;c._reconnectAttempts=0;c._bind();c.onConnect();b&&b(null,!0)};d.onclose=function(a){var b,
d=!0,k=c._connected,e=null;if(c._connected||c._reconnecting){if(a.code)if(b="Connection failed: ",1001===a.code)b+=a.reason||"Service is going away.",d=!1;else if(4400===a.code)b+=a.reason||"Invalid url parameters. Check filter properties.",d=!1;else if(4404===a.code)b+="Service not found",d=!1;else if(4401===a.code||4403===a.code)b+="Not authorized",d=!1;d&&(c._reconnectAttempts+=1,c._reconnectAttempts>c.maxReconnectAttempts&&(b="Maximum reconnect attempts exceeded",d=!1,k=!0));c._connected=!1;k&&
(b&&(e=Error(b)),c.onDisconnect({error:e,willReconnect:d}));d?c._attemptReconnect():c.socket=null}else c.socket||(e=Error("Could not make connection to service"),c.onConnectionError({error:e})),c.socket=null,c._connected=!1};d.onerror=function(a){console.log("Socket error")};return d},_purge:function(){var a,b=[],c;if(this.purgeOptions.displayCount&&this.graphics.length>this.purgeOptions.displayCount)for(a=0;a<this.graphics.length-this.purgeOptions.displayCount;a++)c=this.graphics[a],b.push(c);0<
b.length&&(this._mode._removeFeatures(b),this._trackManager&&this._trackManager.removeFeatures(b))},_bind:function(){var a=this;this.socket.onmessage=function(b){a._onMessage(JSON.parse(b.data))}},_onMessage:function(a){var b=this;this.onMessage(a);var c={create:function(a){b._create(a)},update:function(a){b._update(a)},"delete":function(a){b._delete(a)}};if(a.type)c[a.type](a.feature);else a.hasOwnProperty("filter")?b._handleFilterMessage(a):this._create(a)},_create:function(a){function b(a){if(!c._featureHasOID(a,
d)){if(!a.geometry)return!1;a.attributes=a.attributes||{};a.attributes[d]=c._nextId++}a=a.declaredClass?a:new p(a);c._mode.drawFeature(a)}var c=this,d=c.objectIdField;a.length?a.forEach(function(a){a&&b(a)}):a&&b(a)},_delete:function(a){var b=this,c=a[b.objectIdField]||a.attributes[b.objectIdField],d=!1;this.graphics.forEach(function(a){a.attributes[b.objectIdField]==c&&(d=a)});d&&this.remove(d)},_update:function(a){var b=this,c=!1;this.graphics.forEach(function(d){d.attributes[b.objectIdField]==
a.attributes[b.objectIdField]&&(c=d)});c&&(a.attributes&&c.setAttributes(a.attributes),a.geometry&&c.setGeometry(f.fromJson(a.geometry)))},_makeFilter:function(a){var b,c=null;a=a||{};if(void 0!==a.geometry)if(c=c||{},null===a.geometry)c.geometry=null;else{b="string"===typeof a.geometry?f.fromJson(JSON.parse(a.geometry)):a.geometry.declaredClass?a.geometry:f.fromJson(a.geometry);if(!b||"point"===b.type)throw"Query object contains invalid geometry";"extent"!==b.type&&(b=b.getExtent());if(!b||0===b.getHeight()&&
0===b.getWidth())throw"Invalid filter geometry: Extent cannot have a height and width of 0";c.spatialRel="esriSpatialRelIntersects";c.geometryType="esriGeometryEnvelope";c.geometry=b.toJson()}void 0!==a.where&&(c=c||{},c.where=a.where);if(void 0!==a.outFields&&(c=c||{},a="string"===typeof a.outFields?"*"===a.outFields?null:a.outFields.replace(/,\s+/g,",").split(","):null===a.outFields?null:a.outFields,a=this._makeOutFields(a))){if(a.errors&&0<a.errors.length)throw"Invalid filter outFields. "+a.errors.join(",");
c.outFields=a.fields?a.fields.join(","):null}return c},_makeOutFields:function(a){var b=this,c=[],e=[],f={fields:null};if(!a||0===a.length)return f;d.forEach(a,function(a){if("*"===a)return f;var d=b._getField(a,!0);d?c.push(d.name):e.push("Field named "+a+" not found in schema.")});a=b._getOutFields();d.forEach(a,function(a){b._getField(a)&&-1===d.indexOf(c,a)&&c.push(a)});f.fields=c;f.errors=e;return f},_handleFilterMessage:function(a){a.error?(a=Error(a.error.join(",")),this.onFilterChange({filter:this.getFilter(),
error:a})):(a=a.filter,a.geometry&&"string"===typeof a.geometry&&(a.geometry=JSON.parse(a.geometry)),this._filter=a,this.onFilterChange({filter:this.getFilter()}))},_getWebsocketConnectionInfo:function(a){var b={urls:[]},c,e=[],f=[],g,h,l;a.streamUrls&&d.forEach(a.streamUrls,function(a){"ws"===a.transport&&(c=a.urls,b.token=a.token)});if(!c)return b;d.forEach(c,function(a){0===a.lastIndexOf("wss",0)?f.push(a):e.push(a)});a="https:"===location.protocol||0===this.url.lastIndexOf("https:",0)?f:0===e.length?
f:e;if(1<a.length)for(g=0;g<a.length-1;g++)h=g+Math.floor(Math.random()*(a.length-g)),l=a[h],a[h]=a[g],a[g]=l;b.urls=a;return b},_attemptReconnect:function(){var a=this,b;this._reconnectTimeoutId=null;a._connected=!1;if(!a._socketUrls)return!1;if(!a._collection&&!a._reconnecting&&a.socket&&a.credential)return a._reconnecting=!0,a._getServiceConnectionMetadata(a._attemptReconnect),!1;a._reconnecting=!0;a.socket=null;if(a._reconnectAttempts>a.maxReconnectAttempts)return a._reconnecting=!1;a.socketUrl=
a._socketUrls[a._reconnectAttempts>a._socketUrls.length-1?a._reconnectAttempts%a._socketUrls.length:a._reconnectAttempts];a.socketUrl+="/"+a.socketDirection;b=a._randomIntFromInterval(0,1E3);this._reconnectTimeoutId=setTimeout(function(){a.onAttemptReconnect({count:a._reconnectAttempts,url:a.socketUrl});a.connect()},1E3*a._reconnectAttempts+b)},_getServiceConnectionMetadata:function(a){var b=this,c=b._url.path;a="function"===typeof a?a:null;n({url:c,content:r.mixin({f:"json"},this._url.query),callbackParamName:"callback"}).then(function(c){c=
b._getWebsocketConnectionInfo(c);b._websocketToken=c.token;a&&a()},function(a){b.onError(Error(a))})},_setDefaultRenderer:function(){var a=this.geometryType,b=new u([5,112,176,0.8]),c=new u([255,255,255]),c=new s(s.STYLE_SOLID,c,1),d;if("esriGeometryPoint"===a||"esriGeometryMultipoint"===a)d=new A(A.STYLE_CIRCLE,10,c,b);else if("esriGeometryPolyline"===a)d=new s(s.STYLE_SOLID,b,2);else if("esriGeometryPolygon"===a||"esriGeometryEnvelope"===a)b=new u([5,112,176,0.2]),c=new u([5,112,176,0.8]),c=new s(s.STYLE_SOLID,
c,1),d=new g(g.STYLE_SOLID,c,b);d&&this.setRenderer(new C(d))},_makeObjectIdField:function(){var a=1,b,c,e=[];if(!this.objectIdField){b=this.fields.length;for(c=0;c<b;c++)e.push(this.fields[c].name.toLowerCase());for(;-1!==d.indexOf(e,"objectid_"+a);)a+=1;this.objectIdField="objectid_"+a;this.fields.push({name:"objectid_"+a,type:"esriFieldTypeOID",alias:"objectid_"+a,nullable:!1})}},_featureHasOID:function(a,b){return a.attributes&&(a.attributes[b]||0===a.attributes[b])},_randomIntFromInterval:function(a,
b){return Math.floor(Math.random()*(b-a+1)+a)}});x("extend-esri")&&r.setObject("layers.StreamLayer",l,m);return l});