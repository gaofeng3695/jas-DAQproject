// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/dijit/RasterList","dojo/_base/declare dijit/_WidgetBase dijit/_TemplatedMixin dijit/_WidgetsInTemplateMixin dojo/_base/array dgrid/OnDemandList dgrid/extensions/DijitRegistry dgrid/Selection dojo/_base/lang dojo/dom-class dojo/dom-construct".split(" "),function(d,n,p,q,r,s,t,u,w,e,a){var v=d([s,t,u],{selectionMode:"single",selection:!0,maxRowsPerPage:2,minRowsPerPage:2,farOffRemoval:500});return d([n,p,q],{templateString:'\x3cdiv style\x3d"height: 300px; width: 250px; background-color: white;"\x3e \x3cdiv data-dojo-attach-point \x3d "listDiv" style\x3d"height: 100%; width: 100%;" class\x3d"obliqueRasterList"\x3e\x3c/div\x3e\x3c/div\x3e',
showThumbnail:!1,setData:function(a){this.rasterList.set("store",a)},clearSelection:function(){this.rasterList.clearSelection()},startup:function(){this.inherited(arguments);var c=this;this.rasterList=new v({store:this.store,minRowsPerPage:5,maxRowsPerPage:6,bufferRows:3,selectionMode:"single",renderRow:function(b){b=b.attributes||b;var d,f=a.create("div");c.showThumbnail?e.add(f,"esriRasterListThumbnailRow"):e.add(f,"esriRasterListNoThumbnailRow");var g=a.create("div");e.add(g,"esriRasterListInfoTag");
r.forEach(c.fields,function(a){b[a.name]&&a.display&&(d=b[a.name].toFixed&&0<b[a.name].toString().indexOf(".")?b[a.name].toFixed(2):b[a.name],g.innerHTML+="\x3cstrong\x3e"+a.alias+": \x3c/strong\x3e"+d+"\x3cbr/\x3e")});var k=a.create("table"),l=a.create("tr");e.add(k,"esriRasterListRowTable");if(c.showThumbnail){var h=a.create("img",{className:"esriRasterListThumbnail",src:b.thumbnailUrl}),m=a.create("td");a.place(h,m);a.place(m,l)}h=a.create("td");a.place(g,h);a.place(h,l);a.place(l,k);a.place(k,
f);b.selected&&c.rasterList.select(c.rasterList.row(b));return f}},this.listDiv);this.rasterList.startup();this.own(this.rasterList.on(".dgrid-row:click",function(a){a=c.rasterList.row(a).data;c.emit("raster-select",a)}),this.rasterList.on(".dgrid-row:mouseover",function(a){a=c.rasterList.row(a).data;c.emit("raster-mouseover",a)}),this.rasterList.on(".dgrid-row:mouseout",function(a){c.emit("raster-mouseout")}))}})});