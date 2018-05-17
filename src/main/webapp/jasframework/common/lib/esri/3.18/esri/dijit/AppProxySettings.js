// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/dijit/AppProxySettings","require dojo/_base/array dojo/_base/declare dojo/_base/lang ../kernel dojo/uacss dijit/_WidgetBase ../arcgis/AppProxyManager ../arcgis/utils dojo/i18n!../nls/jsapi dojo/Deferred dojo/on dojo/promise/all dojo/query dojo/string dojo/dom-attr dojo/dom-construct dojo/store/Memory dgrid/OnDemandGrid dgrid/Selection dgrid/editor dojo/domReady!".split(" "),function(s,e,m,b,t,u,f,v,w,n,h,k,p,x,q,r,y,z,A,B,C){f=m([f],{declaredClass:"esri.dijit.AppProxySettings",_creditDomains:[/demographics\w*\.arcgis\.com/i,
/geoenrich\w*\.arcgis\.com/i,/route\w*\.arcgis\.com/i,/logistics\w*\.arcgis\.com/i,/analysis\w*\.arcgis\.com/i,/elevation\w*\.arcgis\.com/i],_premiumDomains:[/traffic\w*\.arcgis\.com/i,/landsat\w*\.arcgis\.com/i,/elevation\w*\.arcgis\.com/i,/geoenrich\w*\.arcgis\.com/i,/hydro\w*\.arcgis\.com/i,/naip\w*\.arcgis\.com/i,/livefeeds\w*\.arcgis\.com/i,/demographics\w*\.arcgis\.com/i,/landscape\w*\.arcgis\.com/i,/historical\w*\.arcgis\.com/i,/earthobs\w*\.arcgis\.com/i],defaults:{webmaps:[],proxyManagerOptions:{appid:""},
proxies:[]},constructor:function(a){this.css={container:"esriAppProxySettings"};a=b.mixin({},this.defaults,a);this.set(a);this.appProxyManager=new v(this.proxyManagerOptions);if(this.appProxyManager.loaded)this._init();else k.once(this.appProxyManager,"load",b.hitch(this,function(){this._init()}))},startup:function(){this.inherited(arguments);if(this.loaded)this._createTable();else k.once(this,"load",b.hitch(this,function(){this._createTable()}))},_queryForSecureServices:function(a){return w.getItem(a).then(b.hitch(this,
this._parseMap)).then(b.hitch(this,function(a){var d=this._mapsProxies;e.forEach(a,b.hitch(this,function(a){d.push(a)}));return a}))},_loaded:function(){this.set("loaded",!0);this.emit("load")},_itemInApp:function(a,c){return e.some(this._mapsProxies,function(d){if(d[a]===c.url)return d.title=c.title,!0})},_parseUrl:function(a){var c=document.createElement("a");c.href=a;return c},_consumesCredits:function(a){return e.some(this._creditDomains,function(c){return-1<a.search(c)})},_isPremium:function(a){return e.some(this._premiumDomains,
function(c){return-1<a.search(c)})},_checkItem:function(a){var c=new h,d=this._parseUrl(a.url);this._isPremium(d.host)?c.resolve({sourceUrl:a.url,id:a.id,title:a.title,proxyId:null,proxied:!1}):c.resolve();return c.promise},_parseMap:function(a){if(a&&a.itemData){var c=[];e.forEach(a.itemData.operationalLayers,b.hitch(this,function(a){this._itemInApp("sourceUrl",a)||c.push(this._checkItem(a))}));return p(c).then(function(a){var c=[];e.forEach(a,function(a){a&&c.push(a)});return c})}a=new h;a.resolve();
return a.promise},_getWebmapsProxies:function(){this._mapsProxies=this.proxies;for(var a=[],c=0,d=this.webmaps.length;c<d;c++)a.push(this._queryForSecureServices(this.webmaps[c]));return p(a)},_webmapsChanged:function(){var a=new h;this.webmaps&&this.webmaps.length?this._getWebmapsProxies().then(b.hitch(this,function(){this.set("proxies",this._mapsProxies);a.resolve()})):a.resolve();return a.promise},_init:function(){var a=this.appProxyManager.proxies;e.forEach(a,b.hitch(this,function(a,d){if(!a.hasOwnProperty("title")){var b=
this._parseUrl(a.sourceUrl);b&&b.host&&(a.title=q.substitute(n.widgets.appProxySettings.untitled,{url:b.host}))}a.hasOwnProperty("proxied")||(a.proxied=!0,a.id="AppProxy"+d)}));this.proxies=a;this.webmaps&&this.webmaps.length?this._webmapsChanged().then(b.hitch(this,function(){this._loaded()})):this._loaded()},_createTable:function(){this._memoryStore=new z({data:this.proxies});var a=y.create("div",{className:this.css.container},this.domNode);this._grid=new (m([A,B]))({store:this._memoryStore,selectionMode:"single",
columns:{proxied:C({label:"",field:"proxied",editor:"checkbox"}),title:{label:n.widgets.appProxySettings.premiumContent,get:b.hitch(this,function(a){var b=a.title;this._consumesCredits(a.sourceUrl)&&(b+=" ${url}");return b}),formatter:function(a){return q.substitute(a,{url:'\x3cimg width\x3d"16" height\x3d"16" src\x3d"'+s.toUrl("../css/images/item_type_icons/premiumcredits16.png")+'" /\x3e'})}}}},a);this._grid.startup();this.own(k(this._grid,"dgrid-datachange",b.hitch(this,function(a){var d=a.cell,
e,l=x("input",d.element);l&&l.length&&(e=l[0]);e&&r.set(e,"disabled",!0);var g=d.row.data,f=a.value;f?this.appProxyManager.createProxies([g]).then(b.hitch(this,function(a){this._updateProxy(g,f,e,a);this.emit("create-proxy",g)})):this.appProxyManager.deleteProxies([g]).then(b.hitch(this,function(){this._updateProxy(g,f,e);this.emit("delete-proxy",g)}))})))},_updateProxy:function(a,c,d,f){e.some(f,b.hitch(this,function(d){if(d.sourceUrl===a.sourceUrl)return d.proxied=c,this._memoryStore&&(d=b.mixin(a,
{proxyId:d.proxyId}),this._memoryStore.put(d,{overwrite:!0})),!0}));d&&r.set(d,"disabled",!1)},_setWebmapsAttr:function(a){a=a.slice();this._set("webmaps",a);this._created&&this._memoryStore&&this._webmapsChanged()},_setProxiesAttr:function(a){a=a.slice();this._set("proxies",a);this._created&&this._memoryStore&&(this._memoryStore.setData(this.proxies),this._grid.set("store",this._memoryStore))}});u("extend-esri")&&b.setObject("dijit.AppProxySettings",f,t);return f});