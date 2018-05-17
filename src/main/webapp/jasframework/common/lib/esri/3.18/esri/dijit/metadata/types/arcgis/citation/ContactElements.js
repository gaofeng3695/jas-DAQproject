// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
require({cache:{"url:esri/dijit/metadata/types/arcgis/citation/templates/ContactElements.html":'\x3cdiv data-dojo-attach-point\x3d"containerNode"\x3e\r\n  \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/Tabs"\x3e\r\n    \r\n    \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/Section"\r\n      data-dojo-props\x3d"showHeader:false,label:\'${i18nArcGIS.contact.section.name}\'"\x3e\r\n      \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/types/arcgis/form/ContactNameElement"\r\n        data-dojo-props\x3d"target:\'rpIndName\',minOccurs:0,preferOpen:true,label:\'${i18nArcGIS.contact.rpIndName}\'"\x3e\r\n        \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/types/arcgis/form/InputContactName"\x3e\x3c/div\x3e\r\n      \x3c/div\x3e  \r\n      \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/types/arcgis/form/ContactNameElement"\r\n        data-dojo-props\x3d"target:\'rpOrgName\',minOccurs:0,preferOpen:true,label:\'${i18nArcGIS.contact.rpOrgName}\'"\x3e\r\n        \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/types/arcgis/form/InputContactName"\x3e\x3c/div\x3e\r\n      \x3c/div\x3e  \r\n      \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/types/arcgis/form/ContactNameElement"\r\n        data-dojo-props\x3d"target:\'rpPosName\',minOccurs:0,preferOpen:true,label:\'${i18nArcGIS.contact.rpPosName}\'"\x3e\r\n        \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/types/arcgis/form/InputContactName"\x3e\x3c/div\x3e\r\n      \x3c/div\x3e  \r\n      \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/OpenElement"\r\n        data-dojo-props\x3d"target:\'role\',minOccurs:1,label:\'${i18nArcGIS.codelist.CI_RoleCode}\'"\x3e\r\n        \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/Element"\r\n          data-dojo-props\x3d"target:\'RoleCd\',minOccurs:1,showHeader:false"\x3e\r\n          \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/Attribute"\r\n            data-dojo-props\x3d"target:\'value\',minOccurs:1,showHeader:false"\x3e\r\n            \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/types/arcgis/form/InputSelectCode"\r\n              data-dojo-props\x3d"codelistType:\'CI_RoleCode\'"\x3e\r\n            \x3c/div\x3e            \r\n          \x3c/div\x3e\r\n        \x3c/div\x3e\r\n      \x3c/div\x3e\r\n    \x3c/div\x3e\r\n    \r\n    \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/Section"\r\n      data-dojo-props\x3d"showHeader:false,label:\'${i18nArcGIS.contact.section.info}\'"\x3e\r\n      \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/types/arcgis/citation/ContactInfo"\x3e\x3c/div\x3e\r\n    \x3c/div\x3e\r\n     \r\n  \x3c/div\x3e\r\n\x3c/div\x3e'}});
define("esri/dijit/metadata/types/arcgis/citation/ContactElements","dojo/_base/declare dojo/_base/lang dojo/has ../../../../../kernel ../../../base/Descriptor dojo/text!./templates/ContactElements.html ../form/ContactNameElement ../form/InputContactName ./ContactInfo".split(" "),function(a,b,c,d,e,f){a=a(e,{templateString:f});c("extend-esri")&&b.setObject("dijit.metadata.types.arcgis.citation.ContactElements",a,d);return a});