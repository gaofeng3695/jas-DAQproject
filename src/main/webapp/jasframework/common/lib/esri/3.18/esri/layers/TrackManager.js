// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/layers/TrackManager","dojo/_base/declare dojo/_base/lang dojo/_base/array dojo/has ../kernel ../graphic ../geometry/Polyline ./GraphicsLayer".split(" "),function(l,m,d,n,p,q,r,s){l=l(null,{declaredClass:"esri.layers._TrackManager",constructor:function(b){this.layer=b;this.trackMap={};this.trackLineMap={}},initialize:function(b){this.map=b;var a=this.layer,c=a._getRenderer(),c=c&&c.trackRenderer;if("esriGeometryPoint"===a.geometryType){var e=this.container=new s._GraphicsLayer({id:a.id+
"_tracks",_child:!0,visible:a.visible,minScale:a.minScale,maxScale:a.maxScale});e.loaded=!0;e.onLoad(e);e._setMap(b,a._div);e.setRenderer(c);a.on("visibility-change",m.hitch(this,function(a){this.container.setVisibility(a.visible);this.container.evaluateSuspension()}));a.on("scale-range-change",m.hitch(this,function(){this.container.setScaleRange(this.layer.minScale,this.layer.maxScale)}))}},addFeatures:function(b){var a=this.trackMap,c=this.layer,e=c._trackIdField,f=[];d.forEach(b,function(b){var c=
b.attributes[e];(a[c]=a[c]||[]).push(b);-1===d.indexOf(f,c)&&f.push(c)});var g=c._startTimeField,k=c.objectIdField,h=function(a,c){var b=a.attributes[g],f=c.attributes[g];return b===f?a.attributes[k]<c.attributes[k]?-1:1:b<f?-1:1};d.forEach(f,function(c){a[c].sort(h)})},trimTracks:function(b){function a(a){for(a=c[a]||[];a.length>e;)f.push(a.shift())}var c=this.trackMap,e=this.layer.maximumTrackPoints||0,f=[],g;if(!e)return f;if(b)d.forEach(b,function(c){a(c)});else for(g in c)c.hasOwnProperty(g)&&
a(g);return f},drawTracks:function(b){function a(a){var b=f[a],h,d,l;d=c.trackLineMap[a];e.remove(d);delete c.trackLineMap[a];if(!b||2>b.length)return!1;d=[];for(h=b.length-1;0<=h;h--)(l=b[h].geometry)&&d.push([l.x,l.y]);b={};b[k]=a;1<d.length&&(d=new q(new r({paths:[d],spatialReference:g}),null,b),e.add(d),c.trackLineMap[a]=d)}var c=this,e=this.container,f,g,k,h;if(e)if(f=this.trackMap,g=this.map.spatialReference,k=this.layer._trackIdField,b)d.forEach(b,function(b){a(b)});else for(h in f)f.hasOwnProperty(h)&&
a(h)},refreshTracks:function(b){function a(a){var b,d;c.drawTracks([a]);if(g&&g.latestObservationRenderer){a=e[a]||[];b=a.length;for(d=0;d<b;d++)f._repaint(a[d],null,!0)}}var c=this,e=this.trackMap,f=this.layer,g=f._getRenderer(),k;if(b)d.forEach(b,function(b){a(b)});else for(k in e)e.hasOwnProperty(k)&&a(k);this.moveLatestToFront()},moveLatestToFront:function(b){d.forEach(this.getLatestObservations(b),function(a){var b=a._shape;b&&b._moveToFront();this._repaint(a,null,!0)},this.layer)},getLatestObservations:function(b){function a(a){a=
f[a];return a[a.length-1]}var c=[],e=this.layer._getRenderer(),f=this.trackMap,g;if(!e.latestObservationRenderer)return c;if(b)d.forEach(b,function(b){c.push(a(b))});else for(g in f)f.hasOwnProperty(g)&&c.push(a(g));return c},clearTracks:function(b){var a=this.getLatestObservations(b),c=this.container,e;b?d.forEach(b,function(a){delete this.trackMap[a];c&&(e=this.trackLineMap[a],c.remove(e),delete this.trackLineMap[a])},this):(this.trackMap={},this.trackLineMap={},c&&c.clear());d.forEach(a,function(a){this._repaint(a,
null,!0)},this.layer)},isLatestObservation:function(b){var a=this.trackMap[b.attributes[this.layer._trackIdField]];return a?a[a.length-1]===b:!1},destroy:function(){var b=this.container;b&&(b.clear(),b._unsetMap(this.map,this.layer._div));this.map=this.layer=this.trackMap=this.container=null}});n("extend-esri")&&m.setObject("layers._TrackManager",l,p);return l});