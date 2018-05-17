// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/dijit/FeatureLayerQueryStore","dojo/_base/declare dojo/_base/lang dojo/_base/array dojo/has dojo/promise/all dojo/Deferred ../request ../tasks/query ../tasks/RelationshipQuery ../dijit/FeatureLayerQueryResult dojo/i18n!../nls/jsapi".split(" "),function(f,h,g,t,q,u,k,v,w,r,x){var s=f(null,{layer:null,data:null,objectIds:null,idProperty:"id",totalCount:0,batchCount:25,where:null,orderByFields:null,getAttachments:!1,getRelatedRecords:!1,constructor:function(b){f.safeMixin(this,b);this.idProperty=
this.layer.objectIdField;this.data=[]},get:function(b){return this.data[b]},getIdentity:function(b){return b[this.idProperty]},query:function(b,c){var d=new u,e=new v,l=c.start||0,f=c.count||this.batchCount,k=this.layer.relationships,m=c.objectIds||this.objectIds,p,n=[];p=!(!this.layer.advancedQueryCapabilities||!this.layer.advancedQueryCapabilities.supportsOrderBy||!(this.orderByFields&&0<this.orderByFields.length));m&&m.length?e.objectIds=m.length>=l+this.batchCount?m.slice(l,l+f):m.slice(l):(e.start=
l,e.num=f,e.where=this.where);p&&(e.orderByFields=this.orderByFields);e.returnGeometry=!1;e.outFields=["*"];this.layer.queryFeatures(e).then(h.hitch(this,function(a){var b=a.objectIdFieldName,c={},f=[];b||g.some(a.fields,function(a,d){if("esriFieldTypeOID"===a.type)return b=a.name,!1});if(!a.features||!a.features.length)return new r(d);this.objectIds&&!p&&(g.forEach(a.features,function(a,d){c[a.attributes[b]]=a}),a.features=g.map(e.objectIds,function(a){return c[a]}));g.forEach(a.features,function(d,
c){a.features[c]=d.attributes;f.push(a.features[c][b]);this.data[a.features[c][b]]=d},this);a.total=this.totalCount;this.getAttachments&&this.getRelatedRecords?(n.push(this._queryAttachments(f)),g.forEach(k,function(a){n.push(this._queryRelatedRecords(f,a))},this),q(n).then(h.hitch(this,function(b){a.attachmentInfos=this._createAttachmentInfoLookup(b.shift());a.relatedRecordInfos=this._createRelatedRecordInfoLookup(b);d.resolve(a)})).otherwise(function(){a.attachmentInfos=null;a.relatedRecordInfos=
{};d.resolve(a)})):this.getRelatedRecords?(g.forEach(k,function(a){n.push(this._queryRelatedRecords(f,a))},this),q(n).then(h.hitch(this,function(b){a.relatedRecordInfos=this._createRelatedRecordInfoLookup(b);d.resolve(a)})).otherwise(function(){a.relatedRecordInfos=null;d.resolve(a)})):this.getAttachments?this._queryAttachments(f).then(h.hitch(this,function(b){a.attachmentInfos=this._createAttachmentInfoLookup(b);d.resolve(a)})).otherwise(function(){a.attachmentInfos=null;d.resolve(a)}):d.resolve(a)}));
return new r(d)},_queryRelatedRecords:function(b,c){var d=this.layer;if(d.advancedQueryCapabilities&&d.advancedQueryCapabilities.supportsAdvancedQueryRelated)return this._queryRelatedRecordCount(b,c);var e=new w;e.outFields=[this.idProperty];e.returnGeometry=!1;e.relationshipId=c.id;e.objectIds=b;return d.queryRelatedFeatures(e)},_queryRelatedRecordCount:function(b,c){return k({url:this.layer._url.path+"/queryRelatedRecords",content:{f:"json",objectIds:b.toString(),outFields:this.idProperty,returnGeometry:!1,
relationshipId:c.id,returnCountOnly:!0},handleAs:"json",callbackParamName:"callback"})},_createRelatedRecordInfoLookup:function(b){var c={};g.forEach(b,function(b,e){c[this.layer.relationships[e].id]=b},this);return c},_queryAttachments:function(b){return k({url:this.layer._url.path+"/queryAttachments",content:{f:"json",objectIds:b.toString()},handleAs:"json",callbackParamName:"callback"})},_createAttachmentInfoLookup:function(b){var c={};g.forEach(b.attachmentGroups,function(b){c[b.parentObjectId]=
{attachments:b.attachmentInfos}});return c}});t("extend-esri")&&h.setObject("dijit.FeatureLayerQueryStore",s,x);return s});