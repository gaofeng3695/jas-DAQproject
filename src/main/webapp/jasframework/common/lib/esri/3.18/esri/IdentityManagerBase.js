// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/IdentityManagerBase","dojo/_base/declare dojo/_base/config dojo/_base/lang dojo/_base/array dojo/_base/Deferred dojo/_base/json dojo/_base/url dojo/sniff dojo/cookie dojo/io-query dojo/regexp ./kernel ./config ./lang ./ServerInfo ./urlUtils ./deferredUtils ./request ./Evented ./OAuthCredential ./arcgis/OAuthInfo".split(" "),function(J,w,n,g,B,C,x,D,K,Q,R,f,E,v,F,q,L,y,M,N,O){var z={},G=function(a){var b=(new x(a.owningSystemUrl)).host;a=(new x(a.server)).host;var c=/.+\.arcgis\.com$/i;
return c.test(b)&&c.test(a)},H=function(a,b){return!(!G(a)||!b||!g.some(b,function(b){return b.test(a.server)}))},s,I=J(M,{declaredClass:"esri.IdentityManagerBase",constructor:function(){this._portalConfig=n.getObject("esriGeowConfig");this.serverInfos=[];this.oAuthInfos=[];this.credentials=[];this._soReqs=[];this._xoReqs=[];this._portals=[];this._getOAuthHash()},_defaultOAuthInfo:null,defaultTokenValidity:60,tokenValidity:null,signInPage:null,useSignInPage:!0,normalizeWebTierAuth:!1,_busy:null,_oAuthHash:null,
_gwTokenUrl:"/sharing/generateToken",_agsRest:"/rest/services",_agsPortal:/\/sharing(\/|$)/i,_agsAdmin:/https?:\/\/[^\/]+\/[^\/]+\/admin\/?(\/.*)?$/i,_adminSvcs:/\/admin\/services(\/|$)/i,_agolSuffix:".arcgis.com",_gwDomains:[{regex:/https?:\/\/www\.arcgis\.com/i,tokenServiceUrl:"https://www.arcgis.com/sharing/generateToken"},{regex:/https?:\/\/dev\.arcgis\.com/i,tokenServiceUrl:"https://dev.arcgis.com/sharing/generateToken"},{regex:/https?:\/\/.*dev[^.]*\.arcgis\.com/i,tokenServiceUrl:"https://devext.arcgis.com/sharing/generateToken"},
{regex:/https?:\/\/.*qa[^.]*\.arcgis\.com/i,tokenServiceUrl:"https://qaext.arcgis.com/sharing/generateToken"},{regex:/https?:\/\/.*.arcgis\.com/i,tokenServiceUrl:"https://www.arcgis.com/sharing/generateToken"}],_legacyFed:[],_regexSDirUrl:/http.+\/rest\/services\/?/ig,_regexServerType:/(\/(MapServer|GeocodeServer|GPServer|GeometryServer|ImageServer|NAServer|FeatureServer|GeoDataServer|GlobeServer|MobileServer|GeoenrichmentServer|VectorTileServer)).*/ig,_gwUser:/http.+\/users\/([^\/]+)\/?.*/i,_gwItem:/http.+\/items\/([^\/]+)\/?.*/i,
_gwGroup:/http.+\/groups\/([^\/]+)\/?.*/i,_errorCodes:[499,498,403,401],_rePortalTokenSvc:/\/sharing(\/rest)?\/generatetoken/i,_publicUrls:[/\/arcgis\/tokens/i,/\/sharing(\/rest)?\/generatetoken/i,/\/rest\/info/i],_createDefaultOAuthInfo:!1,_hasTestedIfAppIsOnPortal:!1,registerServers:function(a){var b=this.serverInfos;b?(a=g.filter(a,function(a){return!this.findServerInfo(a.server)},this),this.serverInfos=b.concat(a)):this.serverInfos=a;g.forEach(a,function(a){a.owningSystemUrl&&this._portals.push(a.owningSystemUrl);
if(a.hasPortal){this._portals.push(a.server);var b=E.defaults.io.corsEnabledServers,e=this._getOrigin(a.tokenServiceUrl);q.canUseXhr(a.server)||b.push(a.server.replace(/^https?:\/\//i,""));q.canUseXhr(e)||b.push(e.replace(/^https?:\/\//i,""))}},this)},registerOAuthInfos:function(a){var b=this.oAuthInfos;b?(a=g.filter(a,function(a){return!this.findOAuthInfo(a.portalUrl)},this),this.oAuthInfos=b.concat(a)):this.oAuthInfos=a},registerToken:function(a){var b=this._sanitizeUrl(a.server),c=this.findServerInfo(b),
d=!0,e;c||(c=new F,c.server=this._getServerInstanceRoot(b),c.tokenServiceUrl=this._getTokenSvcUrl(b),c.hasPortal=!0,this.registerServers([c]));(e=this.findCredential(b,a.userId))?(n.mixin(e,a),d=!1):(e=new s({userId:a.userId,server:c.server,token:a.token,expires:a.expires,ssl:a.ssl,scope:this._isServerRsrc(b)?"server":"portal"}),e.resources=[b],this.credentials.push(e));e.onTokenChange(!1);d||e.refreshServerTokens()},toJson:function(){return v.fixJson({serverInfos:g.map(this.serverInfos,function(a){return a.toJson()}),
oAuthInfos:g.map(this.oAuthInfos,function(a){return a.toJson()}),credentials:g.map(this.credentials,function(a){return a.toJson()})})},initialize:function(a){if(a){n.isString(a)&&(a=C.fromJson(a));var b=a.serverInfos,c=a.oAuthInfos;a=a.credentials;if(b){var d=[];g.forEach(b,function(a){a.server&&a.tokenServiceUrl&&d.push(a.declaredClass?a:new F(a))});d.length&&this.registerServers(d)}if(c){var e=[];g.forEach(c,function(a){a.appId&&e.push(a.declaredClass?a:new O(a))});e.length&&this.registerOAuthInfos(e)}a&&
g.forEach(a,function(a){a.userId&&(a.server&&a.token&&a.expires&&a.expires>(new Date).getTime())&&(a=a.declaredClass?a:new s(a),a.onTokenChange(),this.credentials.push(a))},this)}},findServerInfo:function(a){var b;a=this._sanitizeUrl(a);g.some(this.serverInfos,function(c){this._hasSameServerInstance(c.server,a)&&(b=c);return!!b},this);return b},findOAuthInfo:function(a){var b;a=this._sanitizeUrl(a);g.some(this.oAuthInfos,function(c){this._hasSameServerInstance(c.portalUrl,a)&&(b=c);return!!b},this);
return b},findCredential:function(a,b){var c,d;a=this._sanitizeUrl(a);d=this._isServerRsrc(a)?"server":"portal";b?g.some(this.credentials,function(e){this._hasSameServerInstance(e.server,a)&&(b===e.userId&&e.scope===d)&&(c=e);return!!c},this):g.some(this.credentials,function(b){this._hasSameServerInstance(b.server,a)&&(-1!==this._getIdenticalSvcIdx(a,b)&&b.scope===d)&&(c=b);return!!c},this);return c},getCredential:function(a,b){var c,d,e=!0;v.isDefined(b)&&(n.isObject(b)?(c=!!b.token,d=b.error,e=
!1!==b.prompt):c=b);a=this._sanitizeUrl(a);var g=new B(L._dfdCanceller),k=this._isAdminResource(a),m=c&&this._doPortalSignIn(a)?this._getEsriAuthCookie():null;c=c?this.findCredential(a):null;if(m||c)return e=Error("You are currently signed in as: '"+(m&&m.email||c&&c.userId)+"'. You do not have access to this resource: "+a),e.code="IdentityManagerBase.1",e.httpCode=d&&d.httpCode,e.messageCode=d?d.messageCode:null,e.subcode=d?d.subcode:null,e.details=d?d.details:null,e.log=w.isDebug,g.errback(e),g;
if(d=this._findCredential(a,b))return g.callback(d),g;if(d=this.findServerInfo(a))!d.hasServer&&this._isServerRsrc(a)&&(d._restInfoDfd=this._getTokenSvcUrl(a,!0),d.hasServer=!0);else{m=this._getTokenSvcUrl(a);if(!m)return e=Error("Unknown resource - could not find token service endpoint."),e.code="IdentityManagerBase.2",e.log=w.isDebug,g.errback(e),g;d=new F;d.server=this._getServerInstanceRoot(a);n.isString(m)?(d.tokenServiceUrl=m,d.hasPortal=!0):(d._restInfoDfd=m,d.hasServer=!0);this.registerServers([d])}e&&
(d.hasPortal&&void 0===d._selfReq&&!this._findOAuthInfo(a))&&(d._selfReq={owningTenant:b&&b.owningTenant,selfDfd:this._getPortalSelf(d.tokenServiceUrl.replace(this._rePortalTokenSvc,"/sharing/rest/portals/self"),a)});return this._enqueue(a,d,b,g,k)},getResourceName:function(a){return this._isRESTService(a)?a.replace(this._regexSDirUrl,"").replace(this._regexServerType,"")||"":this._gwUser.test(a)&&a.replace(this._gwUser,"$1")||this._gwItem.test(a)&&a.replace(this._gwItem,"$1")||this._gwGroup.test(a)&&
a.replace(this._gwGroup,"$1")||""},generateToken:function(a,b,c){var d,e,g,k,m,P,p=this._rePortalTokenSvc.test(a.tokenServiceUrl),r=new x(window.location.href.toLowerCase()),u=this._getEsriAuthCookie(),A,l=!b;k=a.shortLivedTokenValidity;var h;b&&(h=f.id.tokenValidity||k||f.id.defaultTokenValidity,h>k&&(h=k));c&&(d=c.isAdmin,e=c.serverUrl,g=c.token,P=c.ssl,a.customParameters=c.customParameters);if(d)k=a.adminTokenServiceUrl;else{k=a.tokenServiceUrl;m=new x(k.toLowerCase());u&&(A=(A=u.auth_tier)&&A.toLowerCase());
if(("web"===A||a.webTierAuth)&&c&&c.serverUrl&&!P&&"http"===r.scheme&&(q.hasSameOrigin(r.uri,k,!0)||"https"===m.scheme&&r.host===m.host&&"7080"===r.port&&"7443"===m.port))k=k.replace(/^https:/i,"http:").replace(/:7443/i,":7080");l&&p&&(k=k.replace(/\/rest/i,""))}d=n.mixin({url:k,content:n.mixin({request:"getToken",username:b&&b.username,password:b&&b.password,serverUrl:e,token:g,expiration:h,referer:d||p?window.location.host:null,client:d?"referer":null,f:"json"},a.customParameters),handleAs:"json",
callbackParamName:l?"callback":void 0},c&&c.ioArgs);c={usePost:!l,disableIdentityLookup:!0,useProxy:this._useProxy(a,c)};p||(d.withCredentials=!1);p=y(d,c);p.addCallback(function(c){if(!c||!c.token)return c=Error("Unable to generate token"),c.code="IdentityManagerBase.3",c.log=w.isDebug,c;var d=a.server;z[d]||(z[d]={});b&&(z[d][b.username]=b.password);c.validity=h;return c});p.addErrback(function(a){});return p},isBusy:function(){return!!this._busy},checkSignInStatus:function(a){return this.getCredential(a,
{prompt:!1})},setRedirectionHandler:function(a){this._redirectFunc=a},setProtocolErrorHandler:function(a){this._protocolFunc=a},signIn:function(){},oAuthSignIn:function(){},onCredentialCreate:function(){},onCredentialsDestroy:function(){},destroyCredentials:function(){if(this.credentials){var a=this.credentials.slice();g.forEach(a,function(a){a.destroy()})}this.onCredentialsDestroy()},_getOAuthHash:function(){var a=window.location.hash;if(a){"#"===a.charAt(0)&&(a=a.substring(1));var a=Q.queryToObject(a),
b=!1;a.access_token&&a.expires_in&&a.state&&a.hasOwnProperty("username")?(a.state=C.fromJson(a.state),this._oAuthHash=a,b=!0):a.error&&a.error_description&&(console.log("IdentityManager OAuth Error: ",a.error," - ",a.error_description),"access_denied"===a.error&&(b=!0));if(b&&(!D("ie")||8<D("ie")))window.location.hash=""}},_findCredential:function(a,b){var c=-1,d,e,f,k,m=b&&b.token;d=b&&b.resource;var n=this._isServerRsrc(a)?"server":"portal",p=g.filter(this.credentials,function(b){return this._hasSameServerInstance(b.server,
a)&&b.scope===n},this);a=d||a;if(p.length)if(1===p.length)if(d=p[0],f=(e=(k=this.findServerInfo(d.server))&&k.owningSystemUrl)&&this.findCredential(e,d.userId),c=this._getIdenticalSvcIdx(a,d),m)-1!==c&&(d.resources.splice(c,1),this._removeResource(a,f));else return-1===c&&d.resources.push(a),this._addResource(a,f),d;else{var r,u;g.some(p,function(b){u=this._getIdenticalSvcIdx(a,b);return-1!==u?(r=b,f=(e=(k=this.findServerInfo(r.server))&&k.owningSystemUrl)&&this.findCredential(e,r.userId),c=u,!0):
!1},this);if(m)r&&(r.resources.splice(c,1),this._removeResource(a,f));else if(r)return this._addResource(a,f),r}},_findOAuthInfo:function(a){var b=this.findOAuthInfo(a);b||g.some(this.oAuthInfos,function(c){this._isIdProvider(c.portalUrl,a)&&(b=c);return!!b},this);return b},_addResource:function(a,b){b&&-1===this._getIdenticalSvcIdx(a,b)&&b.resources.push(a)},_removeResource:function(a,b){var c=-1;b&&(c=this._getIdenticalSvcIdx(a,b),-1<c&&b.resources.splice(c,1))},_useProxy:function(a,b){return b&&
b.isAdmin&&!q.hasSameOrigin(a.adminTokenServiceUrl,window.location.href)||!this._isPortalDomain(a.tokenServiceUrl)&&10.1==a.currentVersion&&!q.hasSameOrigin(a.tokenServiceUrl,window.location.href)},_getOrigin:function(a){a=new x(a);return a.scheme+"://"+a.host+(v.isDefined(a.port)?":"+a.port:"")},_getServerInstanceRoot:function(a){var b=a.toLowerCase(),c=b.indexOf(this._agsRest);-1===c&&this._isAdminResource(a)&&(c=b.indexOf("/admin"));-1===c&&(c=b.indexOf("/sharing"));-1===c&&"/"===b.substr(-1)&&
(c=b.length-1);return-1<c?a.substring(0,c):a},_hasSameServerInstance:function(a,b){a=a.toLowerCase();b=this._getServerInstanceRoot(b).toLowerCase();a=a.substr(a.indexOf(":"));b=b.substr(b.indexOf(":"));return a===b},_sanitizeUrl:function(a){a=q.fixUrl(n.trim(a));var b=(E.defaults.io.proxyUrl||"").toLowerCase(),c=b?a.toLowerCase().indexOf(b+"?"):-1;-1!==c&&(a=a.substring(c+b.length+1));return q.urlToObject(a).path},_isRESTService:function(a){return-1<a.indexOf(this._agsRest)},_isAdminResource:function(a){return this._agsAdmin.test(a)||
this._adminSvcs.test(a)},_isServerRsrc:function(a){return this._isRESTService(a)||this._isAdminResource(a)},_isIdenticalService:function(a,b){var c;if(this._isRESTService(a)&&this._isRESTService(b)){var d=this._getSuffix(a).toLowerCase(),e=this._getSuffix(b).toLowerCase();c=d===e;c||(c=/(.*)\/(MapServer|FeatureServer).*/ig,c=d.replace(c,"$1")===e.replace(c,"$1"))}else this._isAdminResource(a)&&this._isAdminResource(b)?c=!0:!this._isServerRsrc(a)&&(!this._isServerRsrc(b)&&this._isPortalDomain(a))&&
(c=!0);return c},_isPortalDomain:function(a){a=a.toLowerCase();var b=(new x(a)).authority,c=this._portalConfig,d=-1!==b.indexOf(this._agolSuffix);!d&&c&&(d=this._hasSameServerInstance(this._getServerInstanceRoot(c.restBaseUrl),a));if(!d){if(!this._arcgisUrl&&(c=n.getObject("esri.arcgis.utils.arcgisUrl")))this._arcgisUrl=(new x(c)).authority;this._arcgisUrl&&(d=this._arcgisUrl.toLowerCase()===b)}d||(d=g.some(this._portals,function(b){return this._hasSameServerInstance(b,a)},this));return d=d||this._agsPortal.test(a)},
_isIdProvider:function(a,b){var c=-1,d=-1;g.forEach(this._gwDomains,function(e,g){-1===c&&e.regex.test(a)&&(c=g);-1===d&&e.regex.test(b)&&(d=g)});var e=!1;if(-1<c&&-1<d)if(0===c||4===c){if(0===d||4===d)e=!0}else if(1===c){if(1===d||2===d)e=!0}else 2===c?2===d&&(e=!0):3===c&&3===d&&(e=!0);if(!e){var f=this.findServerInfo(b),k=f&&f.owningSystemUrl;k&&(G(f)&&this._isPortalDomain(k)&&this._isIdProvider(a,k))&&(e=!0)}return e},_isPublic:function(a){a=this._sanitizeUrl(a);return g.some(this._publicUrls,
function(b){return b.test(a)})},_getIdenticalSvcIdx:function(a,b){var c=-1;g.some(b.resources,function(b,e){return this._isIdenticalService(a,b)?(c=e,!0):!1},this);return c},_getSuffix:function(a){return a.replace(this._regexSDirUrl,"").replace(this._regexServerType,"$1")},_getTokenSvcUrl:function(a){var b,c;if((b=this._isRESTService(a))||this._isAdminResource(a))return c=a.toLowerCase().indexOf(b?this._agsRest:"/admin/"),b=a.substring(0,c)+"/admin/generateToken",a=a.substring(0,c)+"/rest/info",c=
y({url:a,content:{f:"json"},handleAs:"json",callbackParamName:"callback"}),c.adminUrl_=b,c;if(this._isPortalDomain(a)){var d="";g.some(this._gwDomains,function(b){b.regex.test(a)&&(d=b.tokenServiceUrl);return!!d});d||g.some(this._portals,function(b){this._hasSameServerInstance(b,a)&&(d=b+this._gwTokenUrl);return!!d},this);d||(c=a.toLowerCase().indexOf("/sharing"),-1!==c&&(d=a.substring(0,c)+this._gwTokenUrl));d||(d=this._getOrigin(a)+this._gwTokenUrl);d&&(b=(new x(a)).port,/^http:\/\//i.test(a)&&
"7080"===b&&(d=d.replace(/:7080/i,":7443")),d=d.replace(/http:/i,"https:"));return d}if(-1!==a.toLowerCase().indexOf("premium.arcgisonline.com"))return"https://premium.arcgisonline.com/server/tokens"},_getPortalSelf:function(a,b){"https:"===window.location.protocol?a=a.replace(/^http:/i,"https:").replace(/:7080/i,":7443"):/^http:/i.test(b)&&(a=a.replace(/^https:/i,"http:").replace(/:7443/i,":7080"));return y({url:a,content:{f:"json"},handleAs:"json",callbackParamName:"callback"},{crossOrigin:!1,disableIdentityLookup:!0})},
_hasPortalSession:function(){return!!this._getEsriAuthCookie()},_getEsriAuthCookie:function(){var a;if(K.isSupported()){var b=this._getAllCookies("esri_auth"),c;for(c=0;c<b.length;c++){var d=C.fromJson(b[c]);if(d.portalApp){a=d;break}}}return a},_getAllCookies:function(a){var b=[],c=document.cookie.match(RegExp("(?:^|; )"+R.escapeString(a)+"\x3d([^;]*)","g"));if(c)for(a=0;a<c.length;a++){var d=c[a],e=d.indexOf("\x3d");-1<e&&(d=d.substring(e+1),b.push(decodeURIComponent(d)))}return b},_doPortalSignIn:function(a){if(K.isSupported()){var b=
this._getEsriAuthCookie(),c=this._portalConfig,d=window.location.href,e=this.findServerInfo(a);if(this.useSignInPage&&(c||this._isPortalDomain(d)||b)&&(e?e.hasPortal||e.owningSystemUrl&&this._isPortalDomain(e.owningSystemUrl):this._isPortalDomain(a))&&(this._isIdProvider(d,a)||c&&(this._hasSameServerInstance(this._getServerInstanceRoot(c.restBaseUrl),a)||this._isIdProvider(c.restBaseUrl,a))||q.hasSameOrigin(d,a,!0)))return!0}return!1},_checkProtocol:function(a,b,c,d){var e=!0;d=d?b.adminTokenServiceUrl:
b.tokenServiceUrl;if(0===n.trim(d).toLowerCase().indexOf("https:")&&0!==window.location.href.toLowerCase().indexOf("https:")&&(!E.defaults.io.useCors||!q.canUseXhr(d)&&!q.canUseXhr(q.getProxyUrl(!0).path)))e=this._protocolFunc?!!this._protocolFunc({resourceUrl:a,serverInfo:b}):!1,e||(a=Error("Aborted the Sign-In process to avoid sending password over insecure connection."),a.code="IdentityManagerBase.4",a.log=w.isDebug,console.log(a.message),c(a));return e},_enqueue:function(a,b,c,d,e,g){d||(d=new B(L._dfdCanceller));
d.resUrl_=a;d.sinfo_=b;d.options_=c;d.admin_=e;d.refresh_=g;this._busy?this._hasSameServerInstance(this._getServerInstanceRoot(a),this._busy.resUrl_)?(this._oAuthDfd&&this._oAuthDfd.oAuthWin_&&this._oAuthDfd.oAuthWin_.focus(),this._soReqs.push(d)):this._xoReqs.push(d):this._doSignIn(d);return d},_doSignIn:function(a){this._busy=a;var b=this,c=function(c){var d=a.options_&&a.options_.resource,e=a.resUrl_,f=a.refresh_,l=!1;-1===g.indexOf(b.credentials,c)&&(f&&-1!==g.indexOf(b.credentials,f)?(f.userId=
c.userId,f.token=c.token,f.expires=c.expires,f.validity=c.validity,f.ssl=c.ssl,f.creationTime=c.creationTime,l=!0,c=f):b.credentials.push(c));c.resources||(c.resources=[]);c.resources.push(d||e);c.scope=b._isServerRsrc(e)?"server":"portal";c.onTokenChange();var d=b._soReqs,h={};b._soReqs=[];g.forEach(d,function(a){if(!this._isIdenticalService(e,a.resUrl_)){var b=this._getSuffix(a.resUrl_);h[b]||(h[b]=!0,c.resources.push(a.resUrl_))}},b);a.callback(c);g.forEach(d,function(a){a.callback(c)});b._busy=
a.resUrl_=a.sinfo_=a.refresh_=null;if(!l)b.onCredentialCreate({credential:c});b._soReqs.length&&b._doSignIn(b._soReqs.shift());b._xoReqs.length&&b._doSignIn(b._xoReqs.shift())},d=function(c){a.errback(c);b._busy=a.resUrl_=a.sinfo_=a.refresh_=null;b._soReqs.length&&b._doSignIn(b._soReqs.shift());b._xoReqs.length&&b._doSignIn(b._xoReqs.shift())},e=function(e,g,f,k){var l=a.sinfo_,h=!a.options_||!1!==a.options_.prompt,t=l.hasPortal&&b._findOAuthInfo(a.resUrl_);b._doPortalSignIn(a.resUrl_)?(e=b._getEsriAuthCookie(),
t=b._portalConfig,e?c(new s({userId:e.email,server:l.server,token:e.token,expires:null})):h?(h="",e=window.location.href,h=b.signInPage?b.signInPage:t?t.baseUrl+t.signin:b._isIdProvider(e,a.resUrl_)?b._getOrigin(e)+"/home/signin.html":l.tokenServiceUrl.replace(b._rePortalTokenSvc,"")+"/home/signin.html",h=h.replace(/http:/i,"https:"),t&&!1===t.useSSL&&(h=h.replace(/https:/i,"http:")),0===e.toLowerCase().replace("https","http").indexOf(h.toLowerCase().replace("https","http"))?(l=Error("Cannot redirect to Sign-In page from within Sign-In page. URL of the resource that triggered this workflow: "+
a.resUrl_),l.code="IdentityManagerBase.5",l.log=w.isDebug,d(l)):b._redirectFunc?b._redirectFunc({signInPage:h,returnUrlParamName:"returnUrl",returnUrl:e,resourceUrl:a.resUrl_,serverInfo:l}):window.location=h+"?returnUrl\x3d"+window.escape(e)):(l=Error("User is not signed in."),l.code="IdentityManagerBase.6",l.log=w.isDebug,d(l))):e?c(new s({userId:e,server:l.server,token:f,expires:v.isDefined(k)?Number(k):null,ssl:!!g})):t?(e=t._oAuthCred,e||(g=new N(t,window.localStorage),f=new N(t,window.sessionStorage),
g.isValid()&&f.isValid()?g.expires>f.expires?(e=g,f.destroy()):(e=f,g.destroy()):e=g.isValid()?g:f,t._oAuthCred=e),e.isValid()?c(new s({userId:e.userId,server:l.server,token:e.token,expires:e.expires,ssl:e.ssl,_oAuthCred:e})):b._oAuthHash&&b._oAuthHash.state.portalUrl===t.portalUrl?(h=b._oAuthHash,l=new s({userId:h.username,server:l.server,token:h.access_token,expires:(new Date).getTime()+1E3*Number(h.expires_in),ssl:"true"===h.ssl,oAuthState:h.state,_oAuthCred:e}),e.storage=h.persist?window.localStorage:
window.sessionStorage,e.token=l.token,e.expires=l.expires,e.userId=l.userId,e.ssl=l.ssl,e.save(),b._oAuthHash=null,c(l)):h?a._pendingDfd=b.oAuthSignIn(a.resUrl_,l,t,a.options_).addCallbacks(c,d):(l=Error("User is not signed in."),l.code="IdentityManagerBase.6",l.log=w.isDebug,d(l))):h?b._checkProtocol(a.resUrl_,l,d,a.admin_)&&(h=a.options_,a.admin_&&(h=h||{},h.isAdmin=!0),a._pendingDfd=b.signIn(a.resUrl_,l,h).addCallbacks(c,d)):(l=Error("User is not signed in."),l.code="IdentityManagerBase.6",l.log=
w.isDebug,d(l))},f=function(){var e=a.sinfo_,f=e.owningSystemUrl,k=a.options_,m,l,h;k&&(m=k.token,l=k.error);h=b._findCredential(f,{token:m,resource:a.resUrl_});!h&&G(e)&&g.some(b.credentials,function(a){this._isIdProvider(f,a.server)&&(h=a);return!!h},b);h?(k=b.findCredential(a.resUrl_,h.userId))?c(k):H(e,b._legacyFed)?(k=h.toJson(),k.server=e.server,k.resources=null,c(new s(k))):(a._pendingDfd=b.generateToken(b.findServerInfo(h.server),null,{serverUrl:a.resUrl_,token:h.token,ssl:h.ssl})).addCallbacks(function(b){c(new s({userId:h.userId,
server:e.server,token:b.token,expires:v.isDefined(b.expires)?Number(b.expires):null,ssl:!!b.ssl,isAdmin:a.admin_,validity:b.validity}))},d):(b._busy=null,m&&(a.options_.token=null),(a._pendingDfd=b.getCredential(f.replace(/\/?$/,"/sharing"),{resource:a.resUrl_,owningTenant:e.owningTenant,token:m,error:l})).addCallbacks(function(c){b._enqueue(a.resUrl_,a.sinfo_,a.options_,a,a.admin_)},function(a){d(a)}))},k=a.sinfo_.owningSystemUrl,m=this._isServerRsrc(a.resUrl_),q=a.sinfo_._restInfoDfd;q?q.addCallbacks(function(c){var d=
a.sinfo_;d.adminTokenServiceUrl=d._restInfoDfd.adminUrl_;d._restInfoDfd=null;d.tokenServiceUrl=n.getObject("authInfo.tokenServicesUrl",!1,c)||n.getObject("authInfo.tokenServiceUrl",!1,c)||n.getObject("tokenServiceUrl",!1,c);d.shortLivedTokenValidity=n.getObject("authInfo.shortLivedTokenValidity",!1,c);d.currentVersion=c.currentVersion;d.owningTenant=c.owningTenant;(c=d.owningSystemUrl=c.owningSystemUrl)&&b._portals.push(c);m&&c?f():e()},function(){a.sinfo_._restInfoDfd=null;var b=Error("Unknown resource - could not find token service endpoint.");
b.code="IdentityManagerBase.2";b.log=w.isDebug;d(b)}):m&&k?f():a.sinfo_._selfReq?a.sinfo_._selfReq.selfDfd.then(function(c){var d={},e,f,g,h;c&&(e=c.user&&c.user.username,d.username=e,d.allSSL=c.allSSL,f=c.supportsOAuth,g=c.currentVersion||"4.3","multitenant"===c.portalMode&&(h=c.customBaseUrl));a.sinfo_.webTierAuth=!!e;return e&&b.normalizeWebTierAuth?b.generateToken(a.sinfo_,null,{ssl:d.allSSL}).always(function(a){d.portalToken=a&&a.token;d.tokenExpiration=a&&a.expires;return d}):!e&&f&&4.3<=parseFloat(g)&&
!b._doPortalSignIn(a.resUrl_)?b._generateOAuthInfo({portalUrl:a.sinfo_.server,customBaseUrl:h,owningTenant:a.sinfo_._selfReq.owningTenant}).always(function(){return d}):d}).always(function(b){a.sinfo_._selfReq=null;b?e(b.username,b.allSSL,b.portalToken,b.tokenExpiration):e()}):e()},_generateOAuthInfo:function(a){var b=this,c,d=a.portalUrl;if(a=!this._defaultOAuthInfo&&this._createDefaultOAuthInfo&&!this._hasTestedIfAppIsOnPortal){c=window.location.href;var e=c.indexOf("?");-1<e&&(c=c.slice(0,e));
e=c.search(/\/(apps|home)\//);c=-1<e?c.slice(0,e):null}a&&c?(this._hasTestedIfAppIsOnPortal=!0,c=y({url:c+"/sharing/rest",content:{f:"json"},handleAs:"json"}).then(function(){b._defaultOAuthInfo=new O({appId:"arcgisonline",popup:!0,popupCallbackUrl:"/home/oauth-callback.html"})})):(c=new B,c.resolve(),c=c.promise);return c.always(function(){if(b._defaultOAuthInfo){var a=b._defaultOAuthInfo.clone();a.portalUrl=d;b.oAuthInfos.push(a)}})}});s=J(M,{declaredClass:"esri.Credential",tokenRefreshBuffer:2,
constructor:function(a){n.mixin(this,a);this.resources=this.resources||[];v.isDefined(this.creationTime)||(this.creationTime=(new Date).getTime())},_oAuthCred:null,refreshToken:function(){var a=this,b=this.resources&&this.resources[0],c=f.id.findServerInfo(this.server),d=c&&c.owningSystemUrl,e=!!d&&"server"===this.scope,n=e&&H(c,f.id._legacyFed),k=e&&f.id.findServerInfo(d),m,q=(m=c.webTierAuth)&&f.id.normalizeWebTierAuth,p=z[this.server],p=p&&p[this.userId],r={username:this.userId,password:p},u;if(!m||
q)if(e&&!k&&g.some(f.id.serverInfos,function(a){f.id._isIdProvider(d,a.server)&&(k=a);return!!k}),m=k&&f.id.findCredential(k.server,this.userId),!e||m)if(n)m.refreshToken();else{if(e)u={serverUrl:b,token:m&&m.token,ssl:m&&m.ssl};else if(q)r=null,u={ssl:this.ssl};else if(p)this.isAdmin&&(u={isAdmin:!0});else{var s;b&&(b=f.id._sanitizeUrl(b),this._enqueued=1,s=f.id._enqueue(b,c,null,null,this.isAdmin,this),s.addCallback(function(){a._enqueued=0;a.refreshServerTokens()}).addErrback(function(){a._enqueued=
0}));return s}return f.id.generateToken(e?k:c,e?null:r,u).addCallback(function(b){a.token=b.token;a.expires=v.isDefined(b.expires)?Number(b.expires):null;a.creationTime=(new Date).getTime();a.validity=b.validity;a.onTokenChange();a.refreshServerTokens()}).addErrback(function(){})}},refreshServerTokens:function(){"portal"===this.scope&&g.forEach(f.id.credentials,function(a){var b=f.id.findServerInfo(a.server),c=b&&b.owningSystemUrl;if(a!==this&&a.userId===this.userId&&c&&"server"===a.scope&&(f.id._hasSameServerInstance(this.server,
c)||f.id._isIdProvider(c,this.server)))H(b,f.id._legacyFed)?(a.token=this.token,a.expires=this.expires,a.creationTime=this.creationTime,a.validity=this.validity,a.onTokenChange()):a.refreshToken()},this)},onTokenChange:function(a){clearTimeout(this._refreshTimer);var b=this.server&&f.id.findServerInfo(this.server),c=(b=b&&b.owningSystemUrl)&&f.id.findServerInfo(b);!1!==a&&((!b||"portal"===this.scope||c&&c.webTierAuth&&!f.id.normalizeWebTierAuth)&&(v.isDefined(this.expires)||v.isDefined(this.validity)))&&
this._startRefreshTimer()},onDestroy:function(){},destroy:function(){this.userId=this.server=this.token=this.expires=this.validity=this.resources=this.creationTime=null;this._oAuthCred&&(this._oAuthCred.destroy(),this._oAuthCred=null);var a=g.indexOf(f.id.credentials,this);-1<a&&f.id.credentials.splice(a,1);this.onTokenChange();this.onDestroy()},toJson:function(){return this._toJson()},_toJson:function(){var a=v.fixJson({userId:this.userId,server:this.server,token:this.token,expires:this.expires,
validity:this.validity,ssl:this.ssl,isAdmin:this.isAdmin,creationTime:this.creationTime,scope:this.scope}),b=this.resources;b&&0<b.length&&(a.resources=b);return a},_startRefreshTimer:function(){clearTimeout(this._refreshTimer);var a=6E4*this.tokenRefreshBuffer,b=(this.validity?this.creationTime+6E4*this.validity:this.expires)-(new Date).getTime();0>b&&(b=0);this._refreshTimer=setTimeout(n.hitch(this,this.refreshToken),b>a?b-a:b)}});I.Credential=s;D("extend-esri")&&(f.IdentityManagerBase=I);return I});