// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/dijit/PopupRenderer","require dojo/_base/declare dojo/_base/connect dojo/_base/lang dojo/_base/array dojo/_base/kernel dojo/sniff dojo/query dojo/dom dojo/dom-attr dojo/dom-class dojo/dom-construct dojo/dom-style dojox/html/entities dijit/_Widget dijit/_Templated ../kernel ../urlUtils ./_EventedWidget dojo/i18n!../nls/jsapi dojo/NodeList-dom".split(" "),function(t,n,q,m,r,k,u,E,v,h,e,p,s,w,x,y,z,A,B,C){var D=0;n=n([B,x,y],{declaredClass:"esri.dijit._PopupRenderer",constructor:function(){this._nls=
m.mixin({},C.widgets.popup)},templateString:"\x3cdiv class\x3d'esriViewPopup'\x3e\x3cdiv class\x3d'mainSection'\x3e\x3cdiv class\x3d'header' dojoAttachPoint\x3d'_title'\x3e\x3c/div\x3e\x3cdiv class\x3d'hzLine'\x3e\x3c/div\x3e\x3cdiv dojoAttachPoint\x3d'_description'\x3e\x3c/div\x3e\x3cdiv class\x3d'break'\x3e\x3c/div\x3e\x3c/div\x3e\x3cdiv class\x3d'attachmentsSection hidden'\x3e\x3cdiv\x3e${_nls.NLS_attach}:\x3c/div\x3e\x3cul dojoAttachPoint\x3d'_attachmentsList'\x3e\x3c/ul\x3e\x3cdiv class\x3d'break'\x3e\x3c/div\x3e\x3c/div\x3e\x3cdiv class\x3d'mediaSection hidden'\x3e\x3cdiv class\x3d'header' dojoAttachPoint\x3d'_mediaTitle'\x3e\x3c/div\x3e\x3cdiv class\x3d'hzLine'\x3e\x3c/div\x3e\x3cdiv class\x3d'caption' dojoAttachPoint\x3d'_mediaCaption'\x3e\x3c/div\x3e\x3cdiv class\x3d'gallery' dojoAttachPoint\x3d'_gallery'\x3e\x3cdiv class\x3d'mediaHandle prev' dojoAttachPoint\x3d'_prevMedia' dojoAttachEvent\x3d'onclick: _goToPrevMedia'\x3e\x3c/div\x3e\x3cdiv class\x3d'mediaHandle next' dojoAttachPoint\x3d'_nextMedia' dojoAttachEvent\x3d'onclick: _goToNextMedia'\x3e\x3c/div\x3e\x3cul class\x3d'summary'\x3e\x3cli class\x3d'image mediaCount hidden' dojoAttachPoint\x3d'_imageCount'\x3e0\x3c/li\x3e\x3cli class\x3d'image mediaIcon hidden'\x3e\x3c/li\x3e\x3cli class\x3d'chart mediaCount hidden' dojoAttachPoint\x3d'_chartCount'\x3e0\x3c/li\x3e\x3cli class\x3d'chart mediaIcon hidden'\x3e\x3c/li\x3e\x3c/ul\x3e\x3cdiv class\x3d'frame' dojoAttachPoint\x3d'_mediaFrame'\x3e\x3c/div\x3e\x3c/div\x3e\x3c/div\x3e\x3cdiv class\x3d'editSummarySection hidden' dojoAttachPoint\x3d'_editSummarySection'\x3e\x3cdiv class\x3d'break'\x3e\x3c/div\x3e\x3cdiv class\x3d'break hidden' dojoAttachPoint\x3d'_mediaBreak'\x3e\x3c/div\x3e\x3cdiv class\x3d'editSummary' dojoAttachPoint\x3d'_editSummary'\x3e\x3c/div\x3e\x3c/div\x3e\x3c/div\x3e",
showTitle:!0,startup:function(){this.inherited(arguments);this.template.getComponents(this.graphic).then(m.hitch(this,this._handleComponentsSuccess),m.hitch(this,this._handleComponentsError))},destroy:function(){this._dfd&&this._dfd.cancel();this._destroyFrame();this.template=this.graphic=this._nls=this._mediaInfos=this._mediaPtr=this._dfd=null;this.inherited(arguments)},_goToPrevMedia:function(){0>this._mediaPtr-1||(this._mediaPtr--,this._updateUI(),this._displayMedia())},_goToNextMedia:function(){this._mediaPtr+
1!==this._mediaInfos.length&&(this._mediaPtr++,this._updateUI(),this._displayMedia())},_updateUI:function(){var b=this._mediaInfos,c=b.length,a=this.domNode,f=this._prevMedia,d=this._nextMedia;if(1<c){var g=0,l=0;r.forEach(b,function(a){"image"===a.type?g++:-1!==a.type.indexOf("chart")&&l++});g&&(h.set(this._imageCount,"innerHTML",g),k.query(".summary .image",a).removeClass("hidden"));l&&(h.set(this._chartCount,"innerHTML",l),k.query(".summary .chart",a).removeClass("hidden"))}else k.query(".summary",
a).addClass("hidden"),e.add(f,"hidden"),e.add(d,"hidden");b=this._mediaPtr;0===b?e.add(f,"hidden"):e.remove(f,"hidden");b===c-1?e.add(d,"hidden"):e.remove(d,"hidden");this._destroyFrame()},_displayMedia:function(){var b=this._mediaInfos[this._mediaPtr],c=b.title,a=b.caption,f=k.query(".mediaSection .hzLine",this.domNode)[0];h.set(this._mediaTitle,"innerHTML",c);e[c?"remove":"add"](this._mediaTitle,"hidden");h.set(this._mediaCaption,"innerHTML",a);e[a?"remove":"add"](this._mediaCaption,"hidden");e[c&&
a?"remove":"add"](f,"hidden");this._rid=null;if("image"===b.type)this._showImage(b.value);else{var d=this,c=["dojox/charting/Chart2D","dojox/charting/action2d/Tooltip"],a=b.value.theme||this.chartTheme;m.isString(a)&&(a=a.replace(/\./gi,"/"),-1===a.indexOf("/")&&(a="dojox/charting/themes/"+a));a||(a="./Rainbow");c.push(a);try{var g=this._rid=D++;t(c,function(a,c,f){g===d._rid&&(d._rid=null,d._showChart(b.type,b.value,a,c,f))})}catch(l){console.log("PopupRenderer: error loading modules")}}},_preventNewTab:function(b){return(b=
b&&m.trim(b).toLowerCase())&&(0===b.indexOf("mailto:")||0===b.indexOf("tel:"))},_showImage:function(b){e.add(this._mediaFrame,"image");var c=s.get(this._gallery,"height"),a;b.linkURL&&(a=p.create("a",{href:b.linkURL,target:this._preventNewTab(b.linkURL)?"":"_blank"},this._mediaFrame));p.create("img",{className:"esriPopupMediaImage",src:b.sourceURL},a||this._mediaFrame);var f=k.query(".esriPopupMediaImage",this._mediaFrame)[0],d=this,g;g=q.connect(f,"onload",function(){q.disconnect(g);g=null;d._imageLoaded(f,
c)})},_showChart:function(b,c,a,f,d){e.remove(this._mediaFrame,"image");a=this._chart=new a(p.create("div",{"class":"chart"},this._mediaFrame),{margins:{l:4,t:4,r:4,b:4}});d&&a.setTheme(d);switch(b){case "piechart":a.addPlot("default",{type:"Pie",labels:!1});a.addSeries("Series A",c.fields);break;case "linechart":a.addPlot("default",{type:"Markers"});a.addAxis("x",{min:0,majorTicks:!1,minorTicks:!1,majorLabels:!1,minorLabels:!1});a.addAxis("y",{includeZero:!0,vertical:!0,fixUpper:"minor"});r.forEach(c.fields,
function(a,b){a.x=b+1});a.addSeries("Series A",c.fields);break;case "columnchart":a.addPlot("default",{type:"Columns",gap:3});a.addAxis("y",{includeZero:!0,vertical:!0,fixUpper:"minor"});a.addSeries("Series A",c.fields);break;case "barchart":a.addPlot("default",{type:"Bars",gap:3}),a.addAxis("x",{includeZero:!0,fixUpper:"minor",minorLabels:!1}),a.addAxis("y",{vertical:!0,majorTicks:!1,minorTicks:!1,majorLabels:!1,minorLabels:!1}),a.addSeries("Series A",c.fields)}this._action=new f(a);a.render()},
_destroyFrame:function(){this._rid=null;this._chart&&(this._chart.destroy(),this._chart=null);this._action&&(this._action.destroy(),this._action=null);h.set(this._mediaFrame,"innerHTML","")},_imageLoaded:function(b,c){var a=b.height;a<c&&(a=Math.round((c-a)/2),s.set(b,"marginTop",a+"px"))},_attListHandler:function(b,c){if(b===this._dfd){this._dfd=null;var a="";!(c instanceof Error)&&(c&&c.length)&&r.forEach(c,function(b){a+="\x3cli\x3e";a+="\x3ca href\x3d'"+A.addProxy(b.url)+"' target\x3d'_blank'\x3e"+
(b.name||"[No name]")+"\x3c/a\x3e";a+="\x3c/li\x3e"});h.set(this._attachmentsList,"innerHTML",a||"\x3cli\x3e"+this._nls.NLS_noAttach+"\x3c/li\x3e")}},_handleComponentsSuccess:function(b){if(b){var c=this.showTitle?b.title:"",a=b.description,f=b.fields,d=b.mediaInfos,g=this.domNode,l=this._nls,n=this,p=this.template,q=this.graphic;this._prevMedia.title=l.NLS_prevMedia;this._nextMedia.title=l.NLS_nextMedia;h.set(this._title,"innerHTML",c);c||e.add(this._title,"hidden");!b.hasDescription&&f&&(a="",r.forEach(f,
function(b){a+="\x3ctr valign\x3d'top'\x3e";a+="\x3ctd class\x3d'attrName'\x3e"+w.encode(b[0])+"\x3c/td\x3e";a+="\x3ctd class\x3d'attrValue'\x3e"+b[1].replace(/^\s*(https?:\/\/[^\s]+)\s*$/i,"\x3ca target\x3d'_blank' href\x3d'$1' title\x3d'$1'\x3e"+l.NLS_moreInfo+"\x3c/a\x3e")+"\x3c/td\x3e";a+="\x3c/tr\x3e"}),a&&(a="\x3ctable class\x3d'attrTable' cellpadding\x3d'0px' cellspacing\x3d'0px'\x3e"+a+"\x3c/table\x3e"));h.set(this._description,"innerHTML",a);a||e.add(this._description,"hidden");k.query("a",
this._description).forEach(function(a){n._preventNewTab(a.href)?"_blank"===a.target&&h.remove(a,"target"):h.set(a,"target","_blank")});c&&a?k.query(".mainSection .hzLine",g).removeClass("hidden"):c||a?k.query(".mainSection .hzLine",g).addClass("hidden"):k.query(".mainSection",g).addClass("hidden");if(c=this._dfd=p.getAttachments(q))c.addBoth(m.hitch(this,this._attListHandler,c)),h.set(this._attachmentsList,"innerHTML","\x3cli\x3e"+l.NLS_searching+"...\x3c/li\x3e"),k.query(".attachmentsSection",g).removeClass("hidden");
d&&d.length&&(k.query(".mediaSection",g).removeClass("hidden"),v.setSelectable(this._mediaFrame,!1),this._mediaInfos=d,this._mediaPtr=0,this._updateUI(),this._displayMedia());b.editSummary&&(h.set(this._editSummary,"innerHTML",b.editSummary),d&&d.length&&e.remove(this._mediaBreak,"hidden"),e.remove(this._editSummarySection,"hidden"))}},_handleComponentsError:function(b){console.log("PopupRenderer: error loading template",b)}});u("extend-esri")&&m.setObject("dijit._PopupRenderer",n,z);return n});