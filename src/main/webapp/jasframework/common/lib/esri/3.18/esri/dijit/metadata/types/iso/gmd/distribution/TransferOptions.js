// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
require({cache:{"url:esri/dijit/metadata/types/iso/gmd/distribution/templates/TransferOptions.html":'\x3cdiv data-dojo-attach-point\x3d"containerNode"\x3e\r\n\r\n  \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/iso/ObjectReference"\r\n    data-dojo-props\x3d"target:\'gmd:transferOptions\',minOccurs:0,showHeader:false"\x3e\r\n    \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/iso/AbstractObject"\r\n      data-dojo-props\x3d"target:\'gmd:MD_DigitalTransferOptions\',minOccurs:0"\x3e\r\n      \r\n      \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/iso/ObjectReference"\r\n        data-dojo-props\x3d"target:\'gmd:onLine\',minOccurs:0,maxOccurs:\'unbounded\',\r\n          label:\'${i18nIso.MD_DigitalTransferOptions.onLine}\'"\x3e\r\n        \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/iso/AbstractObject"\r\n          data-dojo-props\x3d"target:\'gmd:CI_OnlineResource\',minOccurs:0"\x3e\r\n          \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/Element"\r\n            data-dojo-props\x3d"target:\'gmd:linkage\',\r\n              label:\'${i18nIso.CI_OnlineResource.linkage}\'"\x3e\r\n            \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/iso/GcoElement"\r\n              data-dojo-props\x3d"target:\'gmd:URL\'"\x3e\x3c/div\x3e\r\n          \x3c/div\x3e\r\n          \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/form/iso/CodeListReference"\r\n            data-dojo-props\x3d"target:\'gmd:function\',\r\n              label:\'${i18nIso.CI_OnlineResource.sFunction}\',minOccurs:0"\x3e\r\n            \x3cdiv data-dojo-type\x3d"esri/dijit/metadata/types/iso/gmd/citation/CI_OnlineFunctionCode"\x3e\x3c/div\x3e\r\n          \x3c/div\x3e                          \r\n        \x3c/div\x3e\r\n      \x3c/div\x3e\r\n      \r\n    \x3c/div\x3e\r\n  \x3c/div\x3e    \r\n  \r\n\x3c/div\x3e'}});
define("esri/dijit/metadata/types/iso/gmd/distribution/TransferOptions","dojo/_base/declare dojo/_base/lang dojo/has ../../../../base/Descriptor ../../../../form/Element ../../../../form/iso/AbstractObject ../../../../form/iso/CodeListReference ../../../../form/iso/GcoElement ../../../../form/iso/ObjectReference ../citation/CI_OnlineFunctionCode dojo/text!./templates/TransferOptions.html ../../../../../../kernel".split(" "),function(a,b,c,d,g,h,k,l,m,n,e,f){a=a(d,{templateString:e});c("extend-esri")&&
b.setObject("dijit.metadata.types.iso.gmd.distribution.TransferOptions",a,f);return a});