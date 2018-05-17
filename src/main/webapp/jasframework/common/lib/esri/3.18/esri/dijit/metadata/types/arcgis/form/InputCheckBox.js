// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
require({cache:{"url:esri/dijit/metadata/types/arcgis/form/templates/InputCheckBox.html":'\x3cdiv class\x3d"gxeInput gxeInputCheckBox"\x3e\r\n  \x3cinput id\x3d"${id}_cbx" type\x3d"checkbox" ${checkedAttr} \r\n    data-dojo-attach-point\x3d"focusNode,checkBoxNode" \r\n    data-dojo-attach-event\x3d"onclick: _onClick"/\x3e\r\n  \x3clabel for\x3d"${id}_cbx"  data-dojo-attach-point\x3d"labelNode"\x3e${label}\x3c/label\x3e\r\n\x3c/div\x3e'}});
define("esri/dijit/metadata/types/arcgis/form/InputCheckBox","dojo/_base/declare dojo/_base/lang dojo/dom-attr dojo/has ../../../../../kernel ../../../base/InputBase dojo/text!./templates/InputCheckBox.html dojo/i18n!../../../nls/i18nArcGIS".split(" "),function(b,e,d,f,g,h,k,l){b=b([h],{templateString:k,allInline:!0,checkedAttr:"",serializeIfFalse:!1,falseValue:"False",trueValue:"True",label:"",postCreate:function(){this.inherited(arguments)},connectXNode:function(a,c){this.inherited(arguments)},
getInputValue:function(){return this.checkBoxNode&&this.checkBoxNode.checked?this.trueValue:this.falseValue},getXmlValue:function(){var a=this.inherited(arguments);return null!==a&&!this.serializeIfFalse&&a===this.falseValue?null:a},importValue:function(a,c){if("undefined"!==typeof c&&null!==c&&c.toLowerCase){var b=c.toLowerCase();if("true"===b||"1"===b)c=this.trueValue;else if("false"===b||"0"===b)c=this.falseValue}this.setInputValue(c)},_onClick:function(a){this.emitInteractionOccurred()},setInputValue:function(a){"undefined"===
typeof a&&(a=null);null!==a&&a===this.trueValue?d.set(this.checkBoxNode,"checked",!0):d.set(this.checkBoxNode,"checked",!1);this.focusNode.value=a;this.emitInteractionOccurred();this.applyViewOnly()}});f("extend-esri")&&e.setObject("dijit.metadata.types.arcgis.form.InputCheckBox",b,g);return b});