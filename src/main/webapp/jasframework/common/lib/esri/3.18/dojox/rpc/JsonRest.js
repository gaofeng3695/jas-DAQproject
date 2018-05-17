//>>built
define("dojox/rpc/JsonRest",["dojo","dojox","dojox/json/ref","dojox/rpc/Rest"],function(p,h){function t(a,c,b,d){(c=c.ioArgs&&c.ioArgs.xhr&&c.ioArgs.xhr.getResponseHeader("Last-Modified"))&&q._timeStamps&&(q._timeStamps[d]=c);var f=a._schema&&a._schema.hrefProperty;f&&(h.json.ref.refAttribute=f);b=b&&h.json.ref.resolveJson(b,{defaultId:d,index:q._index,timeStamps:c&&q._timeStamps,time:c,idPrefix:a._store.allowNoTrailingSlash?a.servicePath+"/":a.servicePath.replace(/[^\/]*$/,""),idAttribute:l.getIdAttribute(a),
schemas:l.schemas,loader:l._loader,idAsRef:a.idAsRef,assignAbsoluteIds:!0});h.json.ref.refAttribute="$ref";return b}var m=[],q=h.rpc.Rest,l;l=h.rpc.JsonRest={serviceClass:h.rpc.Rest,conflictDateHeader:"If-Unmodified-Since",commit:function(a){a=a||{};for(var c=[],b={},d=[],f=0;f<m.length;f++){var e=m[f],g=e.object,h=e.old;if((!a.service||!g&&!h||!(g||h).__id.indexOf(a.service.servicePath))&&e.save){delete g.__isDirty;if(g)if(h){var k;if(k=g.__id.match(/(.*)#.*/))g=q._index[k[1]];if(!(g.__id in b)){b[g.__id]=
g;if(a.incrementalUpdates&&!k)var n=("function"==typeof a.incrementalUpdates?a.incrementalUpdates:function(){n={};for(var a in g)if(g.hasOwnProperty(a))g[a]!==h[a]&&(n[a]=g[a]);else if(h.hasOwnProperty(a))return null;return n})(g,h);n?c.push({method:"post",target:g,content:n}):c.push({method:"put",target:g,content:g})}}else k=l.getServiceAndId(g.__id).service,l.getIdAttribute(k)in g&&!a.alwaysPostNewItems?c.push({method:"put",target:g,content:g}):c.push({method:"post",target:{__id:k.servicePath},
content:g});else h&&c.push({method:"delete",target:h});d.push(e);m.splice(f--,1)}}p.connect(a,"onError",function(){if(!1!==a.revertOnError){var b=m;m=d;l.revert();m=b}else p.forEach(d,function(a){l.changing(a.object,!a.object)})});l.sendToServer(c,a);return c},sendToServer:function(a,c){var b=p.xhr,d=a.length,f,e,g,m=this.conflictDateHeader;p.xhr=function(c,d){d.headers=d.headers||{};d.headers.Transaction=a.length-1==f?"commit":"open";m&&g&&(d.headers[m]=g);e&&(d.headers["Content-ID"]="\x3c"+e+"\x3e");
return b.apply(p,arguments)};for(f=0;f<a.length;f++){var k=a[f];h.rpc.JsonRest._contentId=k.content&&k.content.__id;var n="post"==k.method;(g="put"==k.method&&q._timeStamps[k.content.__id])&&(q._timeStamps[k.content.__id]=new Date+"");e=n&&h.rpc.JsonRest._contentId;var r=l.getServiceAndId(k.target.__id),n=r.service,r=k.deferred=n[k.method](r.id.replace(/#/,""),h.json.ref.toJson(k.content,!1,n.servicePath,!0));(function(b,f,g){f.addCallback(function(h){try{var e=f.ioArgs.xhr&&f.ioArgs.xhr.getResponseHeader("Location");
if(e){var k=e.match(/(^\w+:\/\/)/)&&e.indexOf(g.servicePath),e=0<k?e.substring(k):(g.servicePath+e).replace(/^(.*\/)?(\w+:\/\/)|[^\/\.]+\/\.\.\/|^.*\/(\/)/,"$2$3");b.__id=e;q._index[e]=b}h=t(g,f,h,b&&b.__id)}catch(l){}--d||c.onComplete&&c.onComplete.call(c.scope,a);return h})})(k.content,r,n);r.addErrback(function(a){d=-1;c.onError.call(c.scope,a)})}p.xhr=b},getDirtyObjects:function(){return m},revert:function(a){for(var c=m.length;0<c;){c--;var b=m[c],d=b.object,b=b.old,f=h.data._getStoreForItem(d||
b);if(!a||!d&&!b||!(d||b).__id.indexOf(a.servicePath)){if(d&&b){for(var e in b)if(b.hasOwnProperty(e)&&d[e]!==b[e]){if(f)f.onSet(d,e,d[e],b[e]);d[e]=b[e]}for(e in d)if(!b.hasOwnProperty(e)){if(f)f.onSet(d,e,d[e]);delete d[e]}}else if(b){if(f)f.onNew(b)}else if(f)f.onDelete(d);delete (d||b).__isDirty;m.splice(c,1)}}},changing:function(a,c){if(a.__id){a.__isDirty=!0;for(var b=0;b<m.length;b++){var d=m[b];if(a==d.object){c&&(d.object=!1,this._saveNotNeeded||(d.save=!0));return}}d=a instanceof Array?
[]:{};for(b in a)a.hasOwnProperty(b)&&(d[b]=a[b]);m.push({object:!c&&a,old:d,save:!this._saveNotNeeded})}},deleteObject:function(a){this.changing(a,!0)},getConstructor:function(a,c){if("string"==typeof a){var b=a;a=new h.rpc.Rest(a,!0);this.registerService(a,b,c)}if(a._constructor)return a._constructor;a._constructor=function(b){function c(a){if(a){c(a["extends"]);s=a.properties;for(var b in s){var d=s[b];d&&("object"==typeof d&&"default"in d)&&(e[b]=d["default"])}}a&&(a.prototype&&a.prototype.initialize)&&
(k=!0,a.prototype.initialize.apply(e,g))}var e=this,g=arguments,s,k;c(a._schema);!k&&(b&&"object"==typeof b)&&p.mixin(e,b);var n=l.getIdAttribute(a);q._index[this.__id=this.__clientId=a.servicePath+(this[n]||Math.random().toString(16).substring(2,14)+"@"+(h.rpc.Client&&h.rpc.Client.clientId||"client"))]=this;h.json.schema&&s&&h.json.schema.mustBeValid(h.json.schema.validate(this,a._schema));m.push({object:this,save:!0})};return p.mixin(a._constructor,a._schema,{load:a})},fetch:function(a){a=l.getServiceAndId(a);
return this.byId(a.service,a.id)},getIdAttribute:function(a){a=a._schema;var c;if(a&&!(c=a._idAttr))for(var b in a.properties)if(a.properties[b].identity||"self"==a.properties[b].link)a._idAttr=c=b;return c||"id"},getServiceAndId:function(a){var c="",b;for(b in l.services)a.substring(0,b.length)==b&&b.length>=c.length&&(c=b);if(c)return{service:l.services[c],id:a.substring(c.length)};a=a.match(/^(.*\/)([^\/]*)$/);return{service:new l.serviceClass(a[1],!0),id:a[2]}},services:{},schemas:{},registerService:function(a,
c,b){c=a.servicePath=c||a.servicePath;a._schema=l.schemas[c]=b||a._schema||{};l.services[c]=a},byId:function(a,c){var b,d=q._index[(a.servicePath||"")+c];return d&&!d._loadObject?(b=new p.Deferred,b.callback(d),b):this.query(a,c)},query:function(a,c,b){var d=a(c,b);d.addCallback(function(f){return f.nodeType&&f.cloneNode?f:t(a,d,f,"string"!=typeof c||b&&(b.start||b.count)?void 0:c)});return d},_loader:function(a){var c=l.getServiceAndId(this.__id),b=this;l.query(c.service,c.id).addBoth(function(c){c==
b?(delete c.$ref,delete c._loadObject):b._loadObject=function(a){a(c)};a(c)})},isDirty:function(a,c){return!a?c?p.some(m,function(a){return h.data._getStoreForItem(a.object||a.old)==c}):!!m.length:a.__isDirty}};return h.rpc.JsonRest});