// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/dijit/analysis/_AnalysisOptions","dojo/_base/declare dojo/_base/lang dojo/dom-attr dojo/dom-class dojo/dom-style dojo/has ../../kernel".split(" "),function(b,e,f,l,g,h,k){b=b([],{declaredClass:"esri.dijit.analysis._AnalysisOptions",showSelectFolder:!1,showChooseExtent:!0,showHelp:!0,showCredits:!0,returnFeatureCollection:!1,showCloseIcon:!0,showSelectAnalysisLayer:!0,map:null,showReadyToUseLayers:!0,showAnalysisBusyIndicator:!0,showGeoAnalyticsParams:!1,showBrowseLayers:!1,showAnalysisModeLabel:!1,
distanceDefaultUnits:"Miles",constructor:function(a){},_setShowSelectFolderAttr:function(a){!0===a&&a===this.get("returnFeatureCollection")&&(a=!a);this.showSelectFolder=a;this._webMapFolderSelect&&this._webMapFolderSelect.set("required",a)},_getShowSelectFolderAttr:function(){return this.showSelectFolder},_setShowChooseExtentAttr:function(a){this.showChooseExtent=a},_getShowChooseExtentAttr:function(){return this.showChooseExtent},_setMapAttr:function(a){this.map=a},_getMapAttr:function(){return this.map},
_setShowHelpAttr:function(a){this.showHelp=a},_getShowHelpAttr:function(){return this.showHelp},_setReturnFeatureCollectionAttr:function(a){(this.returnFeatureCollection=a)&&this.set("showSelectFolder",!a)},_getReturnFeatureCollectionAttr:function(){return this.returnFeatureCollection},_setShowCreditsAttr:function(a){this.showCredits=a},_getShowCreditsAttr:function(){return this.showCredits},_setShowCloseIconAttr:function(a){this.showCloseIcon=a},_getShowCloseIconAttr:function(){return this.showCloseIcon},
_setShowSelectAnalysisLayerAttr:function(a){this.showSelectAnalysisLayer=a;this._analysisSelect&&this._analysisSelect.set("required",a)},_getShowSelectAnalysisLayerAttr:function(){return this.showSelectAnalysisLayer},_setIsSingleTenantAttr:function(a){this.isSingleTenant=a},_getIsSingleTenantAttr:function(){return this.isSingleTenant},_setAllowChooseLabelAttr:function(a){this.allowChooseLabel=a},_getAllowChooseLabelAttr:function(){return this.allowChooseLabel},_setTitleAttr:function(a){this.title=
a;this._toolTitle&&f.set(this._toolTitle,"innerHTML",a)},_getTitleAttr:function(){return this.title=f.get(this._toolTitle,"innerHTML")},_setShowReadyToUseLayersAttr:function(a){this.showReadyToUseLayers=a},_getShowReadyToUseLayersAttr:function(a){return this.showReadyToUseLayers},_setFolderIdAttr:function(a){this.folderId=a},_getFolderIdAttr:function(){this._webMapFolderSelect&&this.folderStore&&(this.folderId=this._webMapFolderSelect.item?this.folderStore.getValue(this._webMapFolderSelect.item,"id"):
"");return this.folderId},_setFolderNameAttr:function(a){this.folderName=a},_getFolderNameAttr:function(){this._webMapFolderSelect&&(this.folderStore&&this._webMapFolderSelect.item)&&(this.folderName=this.folderStore.getValue(this._webMapFolderSelect.item,"name"));return this.folderName},_setHelperServicesAttr:function(a){this.helperServices=a},_getHelperServicesAttr:function(a){return this.helperServices},_getPortalSelfAttr:function(){return this.portalSelf},_setPortalSelfAttr:function(a){this.portalSelf=
a},_setShowAnalysisBusyIndicatorAttr:function(a){a&&this.own(this.on("start",e.hitch(this,function(){this.showLoadingIndicator()})),this.on("job-fail, job-result, job-cancel",e.hitch(this,function(){this.hideLoadingIndicator()})))},_setShowGeoAnalyticsParamsAttr:function(a){this.showGeoAnalyticsParams=a},_getShowGeoAnalyticsParamsAttr:function(){return this.showGeoAnalyticsParams},_setShowBrowseLayersAttr:function(a){this.showBrowseLayers=a},_getShowBrowseLayersAttr:function(){return this.showBrowseLayers},
showLoadingIndicator:function(){this.get("showAnalysisBusyIndicator")&&(this._saveBtn.set("iconClass","esriLoading"),this.set("disableRunAnalysis",!0))},hideLoadingIndicator:function(){this.get("showAnalysisBusyIndicator")&&(this._saveBtn.set("iconClass",""),this.set("disableRunAnalysis",!1))},_setDistanceDefaultUnitsAttr:function(a){this.distanceDefaultUnits=a},_getDistanceDefaultUnitsAttr:function(){return this.distanceDefaultUnits},_setAnalysisModeAttr:function(a){this.analysiMode=a},_setShowAnalysisModeLabelAttr:function(a){this.showAnalysisModeLabel=
a;this._titleLbl&&this._analysisModeLblNode&&(g.set(this._titleLbl,"display",this.showAnalysisModeLabel?"none":"block"),g.set(this._analysisModeLblNode,"display",this.showAnalysisModeLabel?"flex":"none"),this.showAnalysisModeLabel&&("standard"===this.analysiMode?this.analysisModeLabel=this.i18n.standard:"bigdata"===this.analysiMode||this.showGeoAnalyticsParams?this.analysisModeLabel=this.i18n.bigData:"raster"===this.analysisMode&&(this.analysisModeLabel=this.i18n.raster),f.set(this._analysisModeCrumb,
"innerHTML",this.analysisModeLabel)))},_getTimeReferenceAttr:function(){if(this._timeRefDay){var a,b,d="",c="";a=this._timeRefDay.get("value");b=this._timeRefTime.get("value");a&&(d=a.toDateString());b&&(c=b.toTimeString());a=c&&-1!==c.indexOf("GMT")?d+" "+c.substring(0,c.indexOf("GMT")+3):c?d+" "+c.split(" ")[0]+" GMT":d+" GMT";this.timeReference=(new Date(a)).getTime()}return this.timeReference},_handleModeCrumbClick:function(a){a.preventDefault();this._onClose(!1)}});h("extend-esri")&&e.setObject("dijit.analysis._AnalysisOptions",
b,k);return b});