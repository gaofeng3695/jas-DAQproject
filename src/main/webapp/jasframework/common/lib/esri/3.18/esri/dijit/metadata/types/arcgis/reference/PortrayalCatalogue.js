// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
require({cache:{"url:esri/dijit/metadata/types/arcgis/reference/templates/PortrayalCatalogue.html":'\x3cdiv data-dojo-attach-point\x3d"containerNode"\x3e\r\n\r\n  \x3c!-- Portrayal Catalogue --\x3e\r\n  \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/Element"\r\n    data-dojo-props\x3d"target:\'porCatInfo\',minOccurs:0,maxOccurs:\'unbounded\',label:\'${i18nArcGIS.porCatInfo.caption}\'"\x3e\r\n    \r\n    \x3c!-- citation --\x3e\r\n    \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/Element"\r\n      data-dojo-props\x3d"target:\'portCatCit\',showHeader:false"\x3e\r\n      \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/types/arcgis/citation/CitationElements"\x3e\x3c/div\x3e\r\n    \x3c/div\x3e\r\n    \r\n  \x3c/div\x3e\r\n\x3c/div\x3e'}});
define("esri/dijit/metadata/types/arcgis/reference/PortrayalCatalogue","dojo/_base/declare dojo/_base/lang dojo/has ../../../../../kernel ../../../base/Descriptor dojo/text!./templates/PortrayalCatalogue.html ../citation/CitationElements".split(" "),function(a,b,c,d,e,f){a=a(e,{templateString:f});c("extend-esri")&&b.setObject("dijit.metadata.types.arcgis.reference.PortrayalCatalogue",a,d);return a});