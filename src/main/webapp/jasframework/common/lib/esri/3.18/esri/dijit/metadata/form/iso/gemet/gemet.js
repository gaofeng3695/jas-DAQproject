// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/dijit/metadata/form/iso/gemet/gemet","dojo/_base/lang dojo/_base/array dojo/query dojo/has dijit/registry dojo/i18n!../../../nls/i18nIso ../../../../../kernel".split(" "),function(m,n,p,q,r,s,t){var e=null,k=null,b={bul:"bg",cze:"cs",dan:"da",dut:"nl",eng:"en",est:"et",fin:"fi",fre:"fr",ger:"de",gre:"el",hun:"hu",gle:"ga",ita:"it",lav:"lv",lit:"lt",mlt:"mt",pol:"pl",por:"pt",rum:"ro",slo:"sk",slv:"sl",spa:"es",swe:"sv"},g={getSelectedLanguage:function(f){var b=f.selectedIndex;return-1!==
b?f.options[b].value:"en"},getSessionConceptQuery:function(){return e},populateLanguages:function(f,e){var a,d=null,c=a=null;a=k;if("undefined"!==typeof a&&null!==a)d=a;else if((a=p("[data-gxe-path\x3d'/gmd:MD_Metadata/gmd:language/gmd:LanguageCode/@codeListValue']",f.rootDescriptor.domNode))&&1===a.length)if((a=r.byNode(a[0]))&&a.inputWidget)if(a=a.inputWidget.getInputValue(),a in b)d=b[a];else for(c in b)if(b.hasOwnProperty(c)&&b[c]===a){d=a;break}if("undefined"===typeof d||null===d)d="en";var g=
e.options;a=[];var l,h=s.gemet.languages;for(c in h)h.hasOwnProperty(c)&&(l=c===d,a.push(new Option(h[c],c,!1,l)));a.sort(function(a,b){return a.label===b.label?0:a.label>b.label?1:-1});n.forEach(a,function(a){g.add(a)})},saveSessionConceptQuery:function(b){e=b},saveSessionLanguage:function(b){k=b}};q("extend-esri")&&m.setObject("dijit.metadata.form.iso.gemet.gemet",g,t);return g});