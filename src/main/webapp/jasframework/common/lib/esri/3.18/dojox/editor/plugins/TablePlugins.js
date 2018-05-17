//>>built
require({cache:{"url:dojox/editor/plugins/resources/insertTable.html":'\x3cdiv class\x3d"dijitDialog" tabindex\x3d"-1" role\x3d"dialog" aria-labelledby\x3d"${id}_title"\x3e\r\n\t\x3cdiv dojoAttachPoint\x3d"titleBar" class\x3d"dijitDialogTitleBar"\x3e\r\n\t\x3cspan dojoAttachPoint\x3d"titleNode" class\x3d"dijitDialogTitle" id\x3d"${id}_title"\x3e${insertTableTitle}\x3c/span\x3e\r\n\t\x3cspan dojoAttachPoint\x3d"closeButtonNode" class\x3d"dijitDialogCloseIcon" dojoAttachEvent\x3d"onclick: onCancel" title\x3d"${buttonCancel}"\x3e\r\n\t\t\x3cspan dojoAttachPoint\x3d"closeText" class\x3d"closeText" title\x3d"${buttonCancel}"\x3ex\x3c/span\x3e\r\n\t\x3c/span\x3e\r\n\t\x3c/div\x3e\r\n    \x3cdiv dojoAttachPoint\x3d"containerNode" class\x3d"dijitDialogPaneContent"\x3e\r\n        \x3ctable class\x3d"etdTable"\x3e\x3ctr\x3e\r\n            \x3ctd\x3e\r\n                \x3clabel\x3e${rows}\x3c/label\x3e\r\n\t\t\t\x3c/td\x3e\x3ctd\x3e\r\n                \x3cspan dojoAttachPoint\x3d"selectRow" dojoType\x3d"dijit.form.TextBox" value\x3d"2"\x3e\x3c/span\x3e\r\n            \x3c/td\x3e\x3ctd\x3e\x3ctable\x3e\x3ctr\x3e\x3ctd class\x3d"inner"\x3e\r\n                \x3clabel\x3e${columns}\x3c/label\x3e\r\n\t\t\t\x3c/td\x3e\x3ctd class\x3d"inner"\x3e\r\n                \x3cspan dojoAttachPoint\x3d"selectCol" dojoType\x3d"dijit.form.TextBox" value\x3d"2"\x3e\x3c/span\x3e\r\n            \x3c/td\x3e\x3c/tr\x3e\x3c/table\x3e\x3c/td\x3e\x3c/tr\x3e\t\t\r\n\t\t\t\x3ctr\x3e\x3ctd\x3e\r\n                \x3clabel\x3e${tableWidth}\x3c/label\x3e\r\n            \x3c/td\x3e\x3ctd\x3e\r\n                \x3cspan dojoAttachPoint\x3d"selectWidth" dojoType\x3d"dijit.form.TextBox" value\x3d"100"\x3e\x3c/span\x3e\r\n\t\t\t\x3c/td\x3e\x3ctd\x3e\r\n                \x3cselect dojoAttachPoint\x3d"selectWidthType" hasDownArrow\x3d"true" dojoType\x3d"dijit.form.FilteringSelect"\x3e\r\n                  \x3coption value\x3d"percent"\x3e${percent}\x3c/option\x3e\r\n                  \x3coption value\x3d"pixels"\x3e${pixels}\x3c/option\x3e\r\n                \x3c/select\x3e\x3c/td\x3e\x3c/tr\x3e\t\r\n            \x3ctr\x3e\x3ctd\x3e\r\n                \x3clabel\x3e${borderThickness}\x3c/label\x3e\r\n            \x3c/td\x3e\x3ctd\x3e\r\n                \x3cspan dojoAttachPoint\x3d"selectBorder" dojoType\x3d"dijit.form.TextBox" value\x3d"1"\x3e\x3c/span\x3e\r\n            \x3c/td\x3e\x3ctd\x3e\r\n                ${pixels}\r\n            \x3c/td\x3e\x3c/tr\x3e\x3ctr\x3e\x3ctd\x3e\r\n                \x3clabel\x3e${cellPadding}\x3c/label\x3e\r\n            \x3c/td\x3e\x3ctd\x3e\r\n                \x3cspan dojoAttachPoint\x3d"selectPad" dojoType\x3d"dijit.form.TextBox" value\x3d"0"\x3e\x3c/span\x3e\r\n            \x3c/td\x3e\x3ctd class\x3d"cellpad"\x3e\x3c/td\x3e\x3c/tr\x3e\x3ctr\x3e\x3ctd\x3e\r\n                \x3clabel\x3e${cellSpacing}\x3c/label\x3e\r\n            \x3c/td\x3e\x3ctd\x3e\r\n                \x3cspan dojoAttachPoint\x3d"selectSpace" dojoType\x3d"dijit.form.TextBox" value\x3d"0"\x3e\x3c/span\x3e\r\n            \x3c/td\x3e\x3ctd class\x3d"cellspace"\x3e\x3c/td\x3e\x3c/tr\x3e\x3c/table\x3e\r\n        \x3cdiv class\x3d"dialogButtonContainer"\x3e\r\n            \x3cdiv dojoType\x3d"dijit.form.Button" dojoAttachEvent\x3d"onClick: onInsert"\x3e${buttonInsert}\x3c/div\x3e\r\n            \x3cdiv dojoType\x3d"dijit.form.Button" dojoAttachEvent\x3d"onClick: onCancel"\x3e${buttonCancel}\x3c/div\x3e\r\n        \x3c/div\x3e\r\n\t\x3c/div\x3e\r\n\x3c/div\x3e\r\n',
"url:dojox/editor/plugins/resources/modifyTable.html":'\x3cdiv class\x3d"dijitDialog" tabindex\x3d"-1" role\x3d"dialog" aria-labelledby\x3d"${id}_title"\x3e\r\n\t\x3cdiv dojoAttachPoint\x3d"titleBar" class\x3d"dijitDialogTitleBar"\x3e\r\n\t\x3cspan dojoAttachPoint\x3d"titleNode" class\x3d"dijitDialogTitle" id\x3d"${id}_title"\x3e${modifyTableTitle}\x3c/span\x3e\r\n\t\x3cspan dojoAttachPoint\x3d"closeButtonNode" class\x3d"dijitDialogCloseIcon" dojoAttachEvent\x3d"onclick: onCancel" title\x3d"${buttonCancel}"\x3e\r\n\t\t\x3cspan dojoAttachPoint\x3d"closeText" class\x3d"closeText" title\x3d"${buttonCancel}"\x3ex\x3c/span\x3e\r\n\t\x3c/span\x3e\r\n\t\x3c/div\x3e\r\n    \x3cdiv dojoAttachPoint\x3d"containerNode" class\x3d"dijitDialogPaneContent"\x3e\r\n        \x3ctable class\x3d"etdTable"\x3e\r\n          \x3ctr\x3e\x3ctd\x3e\r\n                \x3clabel\x3e${backgroundColor}\x3c/label\x3e\r\n            \x3c/td\x3e\x3ctd colspan\x3d"2"\x3e\r\n                \x3cspan class\x3d"colorSwatchBtn" dojoAttachPoint\x3d"backgroundCol"\x3e\x3c/span\x3e\r\n            \x3c/td\x3e\x3c/tr\x3e\x3ctr\x3e\x3ctd\x3e\r\n                \x3clabel\x3e${borderColor}\x3c/label\x3e\r\n            \x3c/td\x3e\x3ctd colspan\x3d"2"\x3e\r\n                \x3cspan class\x3d"colorSwatchBtn" dojoAttachPoint\x3d"borderCol"\x3e\x3c/span\x3e\r\n            \x3c/td\x3e\x3c/tr\x3e\x3ctr\x3e\x3ctd\x3e\r\n                \x3clabel\x3e${align}\x3c/label\x3e\r\n            \x3c/td\x3e\x3ctd colspan\x3d"2"\x3e\t\r\n                \x3cselect dojoAttachPoint\x3d"selectAlign" dojoType\x3d"dijit.form.FilteringSelect"\x3e\r\n                  \x3coption value\x3d"default"\x3e${default}\x3c/option\x3e\r\n                  \x3coption value\x3d"left"\x3e${left}\x3c/option\x3e\r\n                  \x3coption value\x3d"center"\x3e${center}\x3c/option\x3e\r\n                  \x3coption value\x3d"right"\x3e${right}\x3c/option\x3e\r\n                \x3c/select\x3e\r\n            \x3c/td\x3e\x3c/tr\x3e\r\n            \x3ctr\x3e\x3ctd\x3e\r\n                \x3clabel\x3e${tableWidth}\x3c/label\x3e\r\n            \x3c/td\x3e\x3ctd\x3e\r\n                \x3cspan dojoAttachPoint\x3d"selectWidth" dojoType\x3d"dijit.form.TextBox" value\x3d"100"\x3e\x3c/span\x3e\r\n            \x3c/td\x3e\x3ctd\x3e\r\n                \x3cselect dojoAttachPoint\x3d"selectWidthType" hasDownArrow\x3d"true" dojoType\x3d"dijit.form.FilteringSelect"\x3e\r\n                  \x3coption value\x3d"percent"\x3e${percent}\x3c/option\x3e\r\n                  \x3coption value\x3d"pixels"\x3e${pixels}\x3c/option\x3e\r\n                \x3c/select\x3e\x3c/td\x3e\x3c/tr\x3e\t\r\n            \x3ctr\x3e\x3ctd\x3e\r\n                \x3clabel\x3e${borderThickness}\x3c/label\x3e\r\n            \x3c/td\x3e\x3ctd\x3e\r\n                \x3cspan dojoAttachPoint\x3d"selectBorder" dojoType\x3d"dijit.form.TextBox" value\x3d"1"\x3e\x3c/span\x3e\r\n            \x3c/td\x3e\x3ctd\x3e\r\n                ${pixels}\r\n            \x3c/td\x3e\x3c/tr\x3e\x3ctr\x3e\x3ctd\x3e\r\n                \x3clabel\x3e${cellPadding}\x3c/label\x3e\r\n            \x3c/td\x3e\x3ctd\x3e\r\n                \x3cspan dojoAttachPoint\x3d"selectPad" dojoType\x3d"dijit.form.TextBox" value\x3d"0"\x3e\x3c/span\x3e\r\n            \x3c/td\x3e\x3ctd class\x3d"cellpad"\x3e\x3c/td\x3e\x3c/tr\x3e\x3ctr\x3e\x3ctd\x3e\r\n                \x3clabel\x3e${cellSpacing}\x3c/label\x3e\r\n            \x3c/td\x3e\x3ctd\x3e\r\n                \x3cspan dojoAttachPoint\x3d"selectSpace" dojoType\x3d"dijit.form.TextBox" value\x3d"0"\x3e\x3c/span\x3e\r\n            \x3c/td\x3e\x3ctd class\x3d"cellspace"\x3e\x3c/td\x3e\x3c/tr\x3e\r\n        \x3c/table\x3e\r\n        \x3cdiv class\x3d"dialogButtonContainer"\x3e\r\n            \x3cdiv dojoType\x3d"dijit.form.Button" dojoAttachEvent\x3d"onClick: onSet"\x3e${buttonSet}\x3c/div\x3e\r\n            \x3cdiv dojoType\x3d"dijit.form.Button" dojoAttachEvent\x3d"onClick: onCancel"\x3e${buttonCancel}\x3c/div\x3e\r\n        \x3c/div\x3e\r\n\t\x3c/div\x3e\r\n\x3c/div\x3e\r\n'}});
define("dojox/editor/plugins/TablePlugins","dojo/_base/declare dojo/_base/array dojo/_base/Color dojo/aspect dojo/dom-attr dojo/dom-style dijit/_editor/_Plugin dijit/_WidgetBase dijit/_TemplatedMixin dijit/_WidgetsInTemplateMixin dijit/Dialog dijit/Menu dijit/MenuItem dijit/MenuSeparator dijit/ColorPalette dojox/widget/ColorPicker dojo/text!./resources/insertTable.html dojo/text!./resources/modifyTable.html dojo/i18n!./nls/TableDialog dijit/_base/popup dijit/popup dojo/_base/connect dijit/TooltipDialog dijit/form/Button dijit/form/DropDownButton dijit/form/TextBox dijit/form/FilteringSelect".split(" "),
function(k,w,x,y,f,l,d,z,q,r,s,A,m,t,B,u,C,D,h){function n(a){return new p(a)}dojo.experimental("dojox.editor.plugins.TablePlugins");var E=k(d,{tablesConnected:!1,currentlyAvailable:!1,alwaysAvailable:!1,availableCurrentlySet:!1,initialized:!1,tableData:null,shiftKeyDown:!1,editorDomNode:null,undoEnabled:!0,refCount:0,doMixins:function(){dojo.mixin(this.editor,{getAncestorElement:function(a){return this._sCall("getAncestorElement",[a])},hasAncestorElement:function(a){return this._sCall("hasAncestorElement",
[a])},selectElement:function(a){this._sCall("selectElement",[a])},byId:function(a){return dojo.byId(a,this.document)},query:function(a,b,c){a=dojo.query(a,b||this.document);return c?a[0]:a}})},initialize:function(a){this.refCount++;a.customUndo=!0;this.initialized||(this.initialized=!0,this.editor=a,this.editor._tablePluginHandler=this,a.onLoadDeferred.addCallback(dojo.hitch(this,function(){this.editorDomNode=this.editor.editNode||this.editor.iframe.document.body.firstChild;this._myListeners=[dojo.connect(this.editorDomNode,
"mouseup",this.editor,"onClick"),dojo.connect(this.editor,"onDisplayChanged",this,"checkAvailable"),dojo.connect(this.editor,"onBlur",this,"checkAvailable"),dojo.connect(this.editor,"_saveSelection",this,function(){this._savedTableInfo=this.getTableInfo()}),dojo.connect(this.editor,"_restoreSelection",this,function(){delete this._savedTableInfo})];this.doMixins();this.connectDraggable()})))},getTableInfo:function(a){if(this._savedTableInfo)return this._savedTableInfo;a&&this._tempStoreTableData(!1);
if(this.tableData)return this.tableData;var b,c,g,e,d,f,v;if(c=this.editor.getAncestorElement("td"))b=c.parentNode;(e=this.editor.getAncestorElement("table"))?(g=dojo.query("td",e),g.forEach(function(a,b){c==a&&(f=b)}),a=dojo.query("tr",e),a.forEach(function(a,c){b==a&&(v=c)}),d=g.length/a.length,a={tbl:e,td:c,tr:b,trs:a,tds:g,rows:a.length,cols:d,tdIndex:f,trIndex:v,colIndex:f%d}):a={};this.tableData=a;this._tempStoreTableData(500);return this.tableData},connectDraggable:function(){dojo.isIE&&(this.editorDomNode.ondragstart=
dojo.hitch(this,"onDragStart"),this.editorDomNode.ondragend=dojo.hitch(this,"onDragEnd"))},onDragStart:function(){var a=window.event;a.srcElement.id||(a.srcElement.id="tbl_"+(new Date).getTime())},onDragEnd:function(){var a=window.event.srcElement,b=a.id,c=this.editor.document;"table"==a.tagName.toLowerCase()&&setTimeout(function(){var a=dojo.byId(b,c);dojo.removeAttr(a,"align")},100)},checkAvailable:function(){if(this.availableCurrentlySet)return this.currentlyAvailable;if(!this.editor)return!1;
if(this.alwaysAvailable)return!0;(this.currentlyAvailable=this.editor.focused&&(this._savedTableInfo?this._savedTableInfo.tbl:this.editor.hasAncestorElement("table")))?this.connectTableKeys():this.disconnectTableKeys();this._tempAvailability(500);dojo.publish(this.editor.id+"_tablePlugins",[this.currentlyAvailable]);return this.currentlyAvailable},_prepareTable:function(a){var b=this.editor.query("td",a);console.log("prep:",b,a);b[0].id||b.forEach(function(a,b){a.id||(a.id="tdid"+b+this.getTimeStamp())},
this);return b},getTimeStamp:function(){return(new Date).getTime()},_tempStoreTableData:function(a){!0!==a&&(!1===a?this.tableData=null:void 0===a?console.warn("_tempStoreTableData must be passed an argument"):setTimeout(dojo.hitch(this,function(){this.tableData=null}),a))},_tempAvailability:function(a){!0===a?this.availableCurrentlySet=!0:!1===a?this.availableCurrentlySet=!1:void 0===a?console.warn("_tempAvailability must be passed an argument"):(this.availableCurrentlySet=!0,setTimeout(dojo.hitch(this,
function(){this.availableCurrentlySet=!1}),a))},connectTableKeys:function(){if(!this.tablesConnected){this.tablesConnected=!0;var a=this.editor.iframe?this.editor.document:this.editor.editNode;this.cnKeyDn=dojo.connect(a,"onkeydown",this,"onKeyDown");this.cnKeyUp=dojo.connect(a,"onkeyup",this,"onKeyUp");this._myListeners.push(dojo.connect(a,"onkeypress",this,"onKeyUp"))}},disconnectTableKeys:function(){dojo.disconnect(this.cnKeyDn);dojo.disconnect(this.cnKeyUp);this.tablesConnected=!1},onKeyDown:function(a){var b=
a.keyCode;16==b&&(this.shiftKeyDown=!0);9==b&&(b=this.getTableInfo(),b.tdIndex=this.shiftKeyDown?b.tdIndex-1:tabTo=b.tdIndex+1,0<=b.tdIndex&&b.tdIndex<b.tds.length?(this.editor.selectElement(b.tds[b.tdIndex]),this.currentlyAvailable=!0,this._tempAvailability(!0),this._tempStoreTableData(!0),this.stopEvent=!0):(this.stopEvent=!1,this.onDisplayChanged()),this.stopEvent&&dojo.stopEvent(a))},onKeyUp:function(a){var b=a.keyCode;16==b&&(this.shiftKeyDown=!1);if(37==b||38==b||39==b||40==b)this.onDisplayChanged();
9==b&&this.stopEvent&&dojo.stopEvent(a)},onDisplayChanged:function(){this.currentlyAvailable=!1;this._tempStoreTableData(!1);this._tempAvailability(!1);this.checkAvailable()},uninitialize:function(a){this.editor==a&&(this.refCount--,!this.refCount&&this.initialized&&(this.tablesConnected&&this.disconnectTableKeys(),this.initialized=!1,dojo.forEach(this._myListeners,function(a){dojo.disconnect(a)}),delete this._myListeners,delete this.editor._tablePluginHandler,delete this.editor),this.inherited(arguments))}}),
p=k("dojox.editor.plugins.TablePlugins",d,{iconClassPrefix:"editorIcon",useDefaultCommand:!1,buttonClass:dijit.form.Button,commandName:"",label:"",alwaysAvailable:!1,undoEnabled:!0,onDisplayChanged:function(a){this.alwaysAvailable||(this.available=a,this.button.set("disabled",!this.available))},setEditor:function(a){this.editor=a;this.editor.customUndo=!0;this.inherited(arguments);this._availableTopic=dojo.subscribe(this.editor.id+"_tablePlugins",this,"onDisplayChanged");this.onEditorLoaded()},onEditorLoaded:function(){this.editor._tablePluginHandler?
this.editor._tablePluginHandler.initialize(this.editor):(new E).initialize(this.editor)},selectTable:function(){var a=this.getTableInfo();a&&a.tbl&&this.editor._sCall("selectElement",[a.tbl])},_initButton:function(){this.command=this.name;this.label=this.editor.commands[this.command]=this._makeTitle(this.command);this.inherited(arguments);delete this.command;this.connect(this.button,"onClick","modTable");this.onDisplayChanged(!1)},modTable:function(a,b){dojo.isIE&&this.editor.focus();this.begEdit();
var c=this.getTableInfo(),g,e,d,f=!1;switch(dojo.isString(a)?a:this.name){case "insertTableRowBefore":g=c.tbl.insertRow(c.trIndex);for(d=0;d<c.cols;d++)e=g.insertCell(-1),e.innerHTML="\x26nbsp;";break;case "insertTableRowAfter":g=c.tbl.insertRow(c.trIndex+1);for(d=0;d<c.cols;d++)e=g.insertCell(-1),e.innerHTML="\x26nbsp;";break;case "insertTableColumnBefore":c.trs.forEach(function(a){e=a.insertCell(c.colIndex);e.innerHTML="\x26nbsp;"});f=!0;break;case "insertTableColumnAfter":c.trs.forEach(function(a){e=
a.insertCell(c.colIndex+1);e.innerHTML="\x26nbsp;"});f=!0;break;case "deleteTableRow":c.tbl.deleteRow(c.trIndex);console.log("TableInfo:",this.getTableInfo());break;case "deleteTableColumn":c.trs.forEach(function(a){a.deleteCell(c.colIndex)}),f=!0}f&&this.makeColumnsEven();this.endEdit()},begEdit:function(){this.editor._tablePluginHandler.undoEnabled&&(this.editor.customUndo?this.editor.beginEditing():this.valBeforeUndo=this.editor.getValue())},endEdit:function(){if(this.editor._tablePluginHandler.undoEnabled){if(this.editor.customUndo)this.editor.endEditing();
else{var a=this.editor.getValue();this.editor.setValue(this.valBeforeUndo);this.editor.replaceValue(a)}this.editor.onDisplayChanged()}},makeColumnsEven:function(){setTimeout(dojo.hitch(this,function(){var a=this.getTableInfo(!0),b=Math.floor(100/a.cols);a.tds.forEach(function(a){dojo.attr(a,"width",b+"%")})}),10)},getTableInfo:function(a){return this.editor._tablePluginHandler.getTableInfo(a)},_makeTitle:function(a){this._strings=dojo.i18n.getLocalization("dojox.editor.plugins","TableDialog");return this._strings[a+
"Title"]||this._strings[a+"Label"]||a},getSelectedCells:function(){var a=[],b=this.getTableInfo().tbl;this.editor._tablePluginHandler._prepareTable(b);var c=this.editor,b=c._sCall("getSelectedHtml",[null]).match(/id="*\w*"*/g);dojo.forEach(b,function(b){b=b.substring(3,b.length);'"'==b.charAt(0)&&'"'==b.charAt(b.length-1)&&(b=b.substring(1,b.length-1));(b=c.byId(b))&&"td"==b.tagName.toLowerCase()&&a.push(b)},this);if(!a.length&&(b=dijit.range.getSelection(c.window),b.rangeCount))for(b=b.getRangeAt(0).startContainer;b&&
b!=c.editNode&&b!=c.document;){if(1===b.nodeType&&"td"===(b.tagName?b.tagName.toLowerCase():""))return[b];b=b.parentNode}return a},updateState:function(){this.button&&((this.available||this.alwaysAvailable)&&!this.get("disabled")?this.button.set("disabled",!1):this.button.set("disabled",!0))},destroy:function(){this.inherited(arguments);dojo.unsubscribe(this._availableTopic);this.editor._tablePluginHandler.uninitialize(this.editor)}}),F=k(p,{constructor:function(){this.connect(this,"setEditor",function(a){a.onLoadDeferred.addCallback(dojo.hitch(this,
function(){this._createContextMenu()}));this.button.domNode.style.display="none"})},destroy:function(){this.menu&&(this.menu.destroyRecursive(),delete this.menu);this.inherited(arguments)},_initButton:function(){this.inherited(arguments);"tableContextMenu"===this.name&&(this.button.domNode.display="none")},_createContextMenu:function(){var a=new A({targetNodeIds:[this.editor.iframe]});a.addChild(new m({label:h.selectTableLabel,onClick:dojo.hitch(this,"selectTable")}));a.addChild(new t);a.addChild(new m({label:h.insertTableRowBeforeLabel,
onClick:dojo.hitch(this,"modTable","insertTableRowBefore")}));a.addChild(new m({label:h.insertTableRowAfterLabel,onClick:dojo.hitch(this,"modTable","insertTableRowAfter")}));a.addChild(new m({label:h.insertTableColumnBeforeLabel,onClick:dojo.hitch(this,"modTable","insertTableColumnBefore")}));a.addChild(new m({label:h.insertTableColumnAfterLabel,onClick:dojo.hitch(this,"modTable","insertTableColumnAfter")}));a.addChild(new t);a.addChild(new m({label:h.deleteTableRowLabel,onClick:dojo.hitch(this,"modTable",
"deleteTableRow")}));a.addChild(new m({label:h.deleteTableColumnLabel,onClick:dojo.hitch(this,"modTable","deleteTableColumn")}));this.menu=a}}),G=k("dojox.editor.plugins.EditorTableDialog",[s,q,r],{baseClass:"EditorTableDialog",templateString:C,postMixInProperties:function(){dojo.mixin(this,h);this.inherited(arguments)},postCreate:function(){dojo.addClass(this.domNode,this.baseClass);this.inherited(arguments)},onInsert:function(){console.log("insert");for(var a=this.selectRow.get("value")||1,b=this.selectCol.get("value")||
1,c=this.selectWidth.get("value"),g=this.selectWidthType.get("value"),e=this.selectBorder.get("value"),d=this.selectPad.get("value"),f=this.selectSpace.get("value"),h="tbl_"+(new Date).getTime(),c='\x3ctable id\x3d"'+h+'"width\x3d"'+c+("percent"==g?"%":"")+'" border\x3d"'+e+'" cellspacing\x3d"'+f+'" cellpadding\x3d"'+d+'"\x3e\n',g=0;g<a;g++){c+="\t\x3ctr\x3e\n";for(e=0;e<b;e++)c+='\t\t\x3ctd width\x3d"'+Math.floor(100/b)+'%"\x3e\x26nbsp;\x3c/td\x3e\n';c+="\t\x3c/tr\x3e\n"}var c=c+"\x3c/table\x3e\x3cbr /\x3e",
k=dojo.connect(this,"onHide",function(){dojo.disconnect(k);var a=this;setTimeout(function(){a.destroyRecursive()},10)});this.hide();this.onBuildTable({htmlText:c,id:h})},onCancel:function(){var a=dojo.connect(this,"onHide",function(){dojo.disconnect(a);var b=this;setTimeout(function(){b.destroyRecursive()},10)})},onBuildTable:function(a){}}),H=k("dojox.editor.plugins.InsertTable",p,{alwaysAvailable:!0,modTable:function(){var a=new G({});a.show();var b=dojo.connect(a,"onBuildTable",this,function(a){dojo.disconnect(b);
this.editor.focus();this.editor.execCommand("inserthtml",a.htmlText)})}}),I=k([s,q,r],{baseClass:"EditorTableDialog",table:null,tableAtts:{},templateString:D,postMixInProperties:function(){dojo.mixin(this,h);this.inherited(arguments)},postCreate:function(){dojo.addClass(this.domNode,this.baseClass);this.inherited(arguments);var a=new this.colorPicker({params:this.params});this.connect(a,"onChange",function(b){this._started&&(dijit.popup.close(a),this.setBrdColor(b))});this.connect(a,"onBlur",function(){dijit.popup.close(a)});
this.connect(this.borderCol,"click",function(){a.set("value",this.brdColor,!1);dijit.popup.open({popup:a,around:this.borderCol});a.focus()});var b=new this.colorPicker({params:this.params});this.connect(b,"onChange",function(a){this._started&&(dijit.popup.close(b),this.setBkColor(a))});this.connect(b,"onBlur",function(){dijit.popup.close(b)});this.connect(this.backgroundCol,"click",function(){b.set("value",this.bkColor,!1);dijit.popup.open({popup:b,around:this.backgroundCol});b.focus()});this.own(a,
b);this.pickers=[a,b];this.setBrdColor(l.get(this.table,"borderColor"));this.setBkColor(l.get(this.table,"backgroundColor"));var c=f.get(this.table,"width");c||(c=this.table.style.width);var g="pixels";dojo.isString(c)&&-1<c.indexOf("%")&&(g="percent",c=c.replace(/%/,""));c?(this.selectWidth.set("value",c),this.selectWidthType.set("value",g)):(this.selectWidth.set("value",""),this.selectWidthType.set("value","percent"));this.selectBorder.set("value",f.get(this.table,"border"));this.selectPad.set("value",
f.get(this.table,"cellPadding"));this.selectSpace.set("value",f.get(this.table,"cellSpacing"));this.selectAlign.set("value",f.get(this.table,"align"))},startup:function(){w.forEach(this.pickers,function(a){a.startup()});this.inherited(arguments)},setBrdColor:function(a){this.brdColor=a;l.set(this.borderCol,"backgroundColor",a)},setBkColor:function(a){this.bkColor=a;l.set(this.backgroundCol,"backgroundColor",a)},onSet:function(){l.set(this.table,"borderColor",this.brdColor);l.set(this.table,"backgroundColor",
this.bkColor);this.selectWidth.get("value")&&(l.set(this.table,"width",""),f.set(this.table,"width",this.selectWidth.get("value")+("pixels"==this.selectWidthType.get("value")?"":"%")));f.set(this.table,"border",this.selectBorder.get("value"));f.set(this.table,"cellPadding",this.selectPad.get("value"));f.set(this.table,"cellSpacing",this.selectSpace.get("value"));f.set(this.table,"align",this.selectAlign.get("value"));var a=dojo.connect(this,"onHide",function(){dojo.disconnect(a);var b=this;setTimeout(function(){b.destroyRecursive()},
10)});this.hide()},onCancel:function(){var a=dojo.connect(this,"onHide",function(){dojo.disconnect(a);var b=this;setTimeout(function(){b.destroyRecursive()},10)})},onSetTable:function(a){}}),J=k("dojox.editor.plugins.ModifyTable",p,{colorPicker:B,modTable:function(){if(this.editor._tablePluginHandler.checkAvailable()){var a=this.getTableInfo(),a=new I({table:a.tbl,colorPicker:"string"===typeof this.colorPicker?require(this.colorPicker):this.colorPicker,params:this.params});a.show();this.connect(a,
"onSetTable",function(a){var c=this.getTableInfo();l.set(c.td,"backgroundColor",a)})}}}),K=k([z,q,r],{colorPicker:u,templateString:"\x3cdiv style\x3d'display: none; position: absolute; top: -10000; z-index: -10000'\x3e\x3cdiv dojoType\x3d'dijit.TooltipDialog' dojoAttachPoint\x3d'dialog' class\x3d'dojoxEditorColorPicker'\x3e\x3cdiv dojoAttachPoint\x3d'_colorPicker'\x3e\x3c/div\x3e\x3cdiv style\x3d'margin: 0.5em 0em 0em 0em'\x3e\x3cbutton dojoType\x3d'dijit.form.Button' type\x3d'submit' dojoAttachPoint\x3d'_setButton'\x3e${buttonSet}\x3c/button\x3e\x26nbsp;\x3cbutton dojoType\x3d'dijit.form.Button' type\x3d'button' dojoAttachPoint\x3d'_cancelButton'\x3e${buttonCancel}\x3c/button\x3e\x3c/div\x3e\x3c/div\x3e\x3c/div\x3e",
widgetsInTemplate:!0,constructor:function(){dojo.mixin(this,h)},postCreate:function(){this._colorPicker=new ("string"==typeof this.colorPicker?require(this.colorPicker):this.colorPicker)({params:this.params},this._colorPicker)},startup:function(){this._started||(this.inherited(arguments),this.connect(this.dialog,"execute",function(){this.onChange(this.get("value"))}),this.connect(this._cancelButton,"onClick",function(){dijit.popup.close(this.dialog)}),this.connect(this.dialog,"onCancel","onCancel"),
dojo.style(this.domNode,"display","block"))},_setValueAttr:function(a,b){this._colorPicker.set("value",a,b)},_getValueAttr:function(){return this._colorPicker.get("value")},onChange:function(a){},onCancel:function(){}}),L=k("dojox.editor.plugins.ColorTableCell",p,{colorPicker:u,constructor:function(){this.closable=!0;this.buttonClass=dijit.form.DropDownButton;var a=this,b,c={colorPicker:this.colorPicker,params:this.params};this.dropDown?(b=this.dropDown,b.set(c)):(b=new K(c),b.startup(),this.dropDown=
b.dialog);this.connect(b,"onChange",function(a){this.editor.focus();this.modTable(null,a)});this.connect(b,"onCancel",function(){this.editor.focus()});y.before(this.dropDown,"onOpen",function(){var c=a.getTableInfo();if((c=a.getSelectedCells(c.tbl))&&0<c.length){for(var c=c[0]===a.lastObject?c[0]:c[c.length-1],e;c&&c!==a.editor.document&&("transparent"===(e=dojo.style(c,"backgroundColor"))||0===e.indexOf("rgba"));)c=c.parentNode;"transparent"!==e&&0!==e.indexOf("rgba")&&b.set("value",x.fromString(e).toHex())}});
this.connect(this,"setEditor",function(a){a.onLoadDeferred.addCallback(dojo.hitch(this,function(){this.connect(this.editor.editNode,"onmouseup",function(a){this.lastObject=a.target})}))})},_initButton:function(){this.command=this.name;this.label=this.editor.commands[this.command]=this._makeTitle(this.command);this.inherited(arguments);delete this.command;this.onDisplayChanged(!1)},modTable:function(a,b){this.begEdit();var c=this.getTableInfo(),c=this.getSelectedCells(c.tbl);dojo.forEach(c,function(a){dojo.style(a,
"backgroundColor",b)});this.endEdit()}});d.registry.insertTableRowBefore=n;d.registry.insertTableRowAfter=n;d.registry.insertTableColumnBefore=n;d.registry.insertTableColumnAfter=n;d.registry.deleteTableRow=n;d.registry.deleteTableColumn=n;d.registry.colorTableCell=function(a){return new L(a)};d.registry.modifyTable=function(a){return new J(a)};d.registry.insertTable=function(a){return new H(a)};d.registry.tableContextMenu=function(a){return new F(a)};return p});