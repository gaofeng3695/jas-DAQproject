// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
require({cache:{"url:esri/dijit/metadata/types/arcgis/citation/templates/CodeRefElements.html":'\x3cdiv data-dojo-attach-point\x3d"containerNode"\x3e\r\n\r\n  \x3c!-- code --\x3e\r\n  \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/OpenElement"\r\n    data-dojo-props\x3d"target:\'identCode\',minOccurs:1,label:\'${i18nArcGIS.codeRef.identCode}\'"\x3e\r\n    \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/Attribute" \r\n      data-dojo-props\x3d"target:\'code\',minOccurs:1,showHeader:false"\x3e\r\n    \x3c/div\x3e\r\n  \x3c/div\x3e\r\n  \r\n  \x3c!-- code space --\x3e\r\n  \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/OpenElement"\r\n    data-dojo-props\x3d"target:\'idCodeSpace\',minOccurs:0,label:\'${i18nArcGIS.codeRef.idCodeSpace}\'"\x3e\r\n  \x3c/div\x3e\r\n  \r\n  \x3c!-- version --\x3e\r\n  \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/OpenElement"\r\n    data-dojo-props\x3d"target:\'idVersion\',minOccurs:0,label:\'${i18nArcGIS.codeRef.idVersion}\'"\x3e\r\n  \x3c/div\x3e\r\n  \r\n  \x3c!-- authority citation --\x3e\r\n  \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/Element"\r\n    data-dojo-props\x3d"target:\'identAuth\',minOccurs:0,label:\'${i18nArcGIS.codeRef.identAuth}\'"\x3e\r\n    \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/types/arcgis/citation/CitationElements"\x3e\x3c/div\x3e    \r\n  \x3c/div\x3e\r\n\r\n\x3c/div\x3e'}});
define("esri/dijit/metadata/types/arcgis/citation/CodeRefElements","dojo/_base/declare dojo/_base/lang dojo/has ../../../../../kernel ../../../base/Descriptor dojo/text!./templates/CodeRefElements.html ./CitationElements".split(" "),function(a,b,c,d,e,f){a=a(e,{templateString:f});c("extend-esri")&&b.setObject("dijit.metadata.types.arcgis.citation.CodeRefElements",a,d);return a});