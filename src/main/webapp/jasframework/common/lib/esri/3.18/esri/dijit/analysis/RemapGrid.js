// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
require({cache:{"url:esri/dijit/analysis/templates/RemapGrid.html":'\x3cdiv\x3e\r\n  \x3cdiv data-dojo-attach-point\x3d"_gridDiv" class\x3d"grid" style\x3d"padding:1px;width:100%;height:100px"\x3e\x3c/div\x3e\r\n  \x3cdiv style\x3d"clear:both;"\x3e\x3c/div\x3e\r\n  \x3cdiv data-dojo-type\x3d"dijit.layout.ContentPane" style\x3d"padding:1px;width:100%"\x3e\r\n    \x3ctable style\x3d"width:100%;"\x3e\r\n      \x3ctbody\x3e\r\n        \x3ctr\x3e\r\n          \x3ctd class\x3d"esriFloatTrailing"\x3e\r\n            \x3cdiv class\x3d"esriLeadingMargin025"\x3e\r\n              \x3cdiv data-dojo-type\x3d"dijit/form/Button" class\x3d"esriActionButton" data-dojo-props\x3d"label:\'${i18n.add}\',iconClass:\'esriAnalysisAddIcon\',showLabel: false, disabled:false" data-dojo-attach-point\x3d"_aBtn" data-dojo-attach-event\x3d"onClick:_handleAButtonClick"\x3e\x3c/div\x3e\r\n              \x3cdiv data-dojo-type\x3d"dijit/form/Button" class\x3d"esriActionButton" data-dojo-props\x3d"label:\'${i18n.remove}\',iconClass:\'esriAnalysisDeleteIcon\',showLabel: false, disabled:false" data-dojo-attach-point\x3d"_rBtn" data-dojo-attach-event\x3d"onClick:_handleRButtonClick"\x3e\x3c/div\x3e\r\n            \x3c/div\x3e\r\n          \x3c/td\x3e\r\n        \x3c/tr\x3e\r\n      \x3c/tbody\x3e\r\n    \x3c/table\x3e\r\n  \x3c/div\x3e\r\n\x3c/div\x3e'}});
define("esri/dijit/analysis/RemapGrid","dojo/_base/declare dojo/_base/lang dojo/_base/event dojo/_base/array dojo/has dojo/dom-construct dojo/Evented dijit/_WidgetBase dijit/_TemplatedMixin dijit/_WidgetsInTemplateMixin dijit/_OnDijitClickMixin dijit/_FocusMixin dgrid/OnDemandGrid dgrid/Selection dgrid/selector dgrid/extensions/DijitRegistry dgrid/extensions/ColumnResizer dojo/store/Memory dojo/store/Observable dgrid/editor ../../kernel dojo/i18n!../../nls/jsapi dojo/text!./templates/RemapGrid.html".split(" "),
function(d,b,h,l,m,C,n,p,q,r,s,t,u,v,k,w,x,y,D,f,z,g,A){var B=d([u,v,k,x,w]);d=d([p,q,r,s,t,n],{declaredClass:"esri.dijit.analysis.RemapGrid",templateString:A,widgetsInTemplate:!0,_selectedIds:[],constructor:function(a){a.containerNode&&(this.container=a.containerNode)},destroy:function(){this.inherited(arguments)},postMixInProperties:function(){this.inherited(arguments);this.i18n={};b.mixin(this.i18n,g.common);b.mixin(this.i18n,g.analysisTools);b.mixin(this.i18n,g.remapValuesTool)},postCreate:function(){this.inherited(arguments);
this.idx=2;this.getColumns();this.remapStore=new y({data:{identifier:"id",items:[]}});this.remapGrid=new B({store:this.remapStore,columns:this.columns,selectionMode:"none",showHeader:!0,allowSelectAll:!0,allowSelect:function(a){return!0}},this._gridDiv);this.remapGrid.on("dgrid-select",b.hitch(this,this._handleRemapGridSelect));this.remapGrid.on("dgrid-deselect",b.hitch(this,this._handleRemapGridSelect));this.remapGrid.on("dgrid-editor-hide",b.hitch(this,function(a){this.handleLabelChange(a.cell.row.data,
a.editor.name,a.editor.value)}));this.remapGrid.keepScrollPosition=!0;this._rBtn.set("disabled",!0)},_handleRemapGridSelect:function(a){this._selectedObj=a.grid.selection;this._selectedIds=[];for(var c in this._selectedObj)this._selectedObj.hasOwnProperty(c)&&(c=parseInt(c,10),!0===this._selectedObj[c]&&0!==c&&this._selectedIds.push(c));this._rBtn.set("disabled",1>this._selectedIds.length)},_handleAButtonClick:function(a){this.remapStore.put({minValue:0,maxValue:0,output:0,id:this.idx++});this.remapGrid.resize();
this.remapGrid.refresh();this.remapGrid.set("store",this.remapStore);a&&h.stop(a)},_handleRButtonClick:function(a){h.stop(a);if(this._selectedIds&&0===this._selectedIds.length)return!1;l.forEach(this._selectedIds,function(a){this.remapStore.remove(a)},this);this._clear();this._rBtn.set("disabled",!0)},handleLabelChange:function(a,c,b){a[c]=b;this.remapStore.put({output:a.output,minValue:a.minValue,maxValue:a.maxValue,id:a.id})},_clear:function(){this._selectedIds=[];this.remapGrid.clearSelection();
this.remapGrid.refresh({keepScrollPosition:!0})},_getRangesAttr:function(){var a=[],c=[],b=[],d;this.remapStore.query().forEach(function(e){"NoData"===e.output?(b.push(e.minValue),b.push(e.maxValue)):(a.push(e.minValue),a.push(e.maxValue),c.push(e.output));d={InputRanges:a,OutputValues:c,NoDataRanges:b}});console.log(d);return d},getColumns:function(){this.columns={};this.columns.check=k({selector:"checkbox",label:""});this.columns.minValue=f({className:"labelCell",field:"minValue",label:"Minimum",
sortable:!0,canEdit:function(a){return!0}},"text","click");this.columns.maxValue=f({className:"labelCell",field:"maxValue",label:"Maximum",sortable:!0,canEdit:function(a){return!0}},"text","click");this.columns.output=f({className:"labelCell",field:"output",label:"Output",sortable:!0,canEdit:function(a){return!0}},"text","click")}});m("extend-esri")&&b.setObject("dijit.analysis.RemapGrid",d,z);return d});