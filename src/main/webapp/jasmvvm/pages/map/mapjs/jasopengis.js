/**
 * Created by kc on 2018/1/19.
 * @description jasopengis webgis 开发框架
 * @version jasopengis-v-1.0.0
 * @lastUpdate 2018/06/27
 */
var Constants = {
    "events": {
        "ErrorEvent": "ErrorEvent",//
        "MapLoadedEvent": "MapLoadedEvent",
        "StylesLoadedEvent": "StylesLoadedEvent",
        "MapResizedEvent": "MapResizedEvent",
        "BaseMapLayersLoaded": "BaseMapLayersLoaded",
        "OptionalLayersLoaded": "OptionalLayersLoaded",
        "ModulesLoadedEvent": "ModulesLoaded",
        "ModuleStartupEvent": "ModuleStartupEvent",
        "ModuleInitEvent": "ModuleInitEvent",
        "ModuleStateChanged": "ModuleStateChanged",
        "OptionalLayerAddedEvent": "OptionalLayerAddedEvent",
        "OptionalLayerRemovedEvent": "OptionalLayerRemovedEvent",
        "ConfigLoadedEvent": "ConfigLoaded"
    },
    "strings": {
        "apiLoading": "api初始化",
        "mapLoading": "地图初始化",
        "modulesLoading":"加载模块",
        "configLoading": "加载配置文件",
        "dependenceLoading": "加载相关依赖",
        "appNameConfigError": "appName配置错误",
        "mapDivIdNotExitsError": "地图div配置错误,id不存在！",
        "parseConfigError": "地图配置解析失败,请检查配置数据格式是否正确！",
        "parseLayerConfigError": "图层配置解析出错,请检查相关图层配置是否正确！",
        "configUrlError": "js脚本标签没有配置data-config属性",
        "moduleClassNotFoundError": "该地图控件类没有定义",
        "moduleConfigError": "地图控件配置出错",
        "moduleReferError": "地图控件依赖文件modules.js没有加载",
        "moduleCreateError": "地图控件创建出错",
        "moduleNotFound": "地图控件没有找到",
        "moduleLoaded": "地图控件加载成功",
        "createLayerError": "图层创建失败，请检查配置！",
        "layerIdRepeatError": "图层创建失败，id已经存在！",
        "mapStyleNameRepeatError": "地图样式模版名称已经存在！",
        "mapStyleConfigError": "地图样式配置错误！",
        "hasNoLayerTypeError": "无法创建的图层类型",
        "layerNotLoaded": "该图层未加载",
        "layerUrlNotNull": "该类型图层url不能为空",
        "layerLoadError": "图层加载出现错误，请检查网络！",
        "layerListenerConfigError": "图层监听配置出错！",
        "eventNotRegister": "事件没有注册，回调函数无法执行",
        "eventNameRepeatError": "事件名称已经存在！",
        "graphicCreateError": "图形创建出错 ，请检查数据结构！",
        "getDistanceError": "计算距离出错！",
        "getAreaError": "计算面积出错！",
        "invalidFlashData": "无效的闪烁规则！",
        "featureNotFound": "没有查询到目标要素，请检查查询条件！",
        "geometryNotFound": "没有查询到空间坐标数据！",
        "queryError": "查询出错！",
        "layerSetNotFound": "没有找到对应的layerSet",
        "repeatIdError": "重复ID",
        "hasNoIdError": "ID不存在",
        "hasNoLabelPropertyError": "图层没有包含标注所需要的属性字段，请检查图层的outFields配置",
        "hasNoStyleError": "样式不存在",
        "hasNoProj4js": "自定义投影需要引入proj4.js",
        "hasNoJqueryEasyUILib": "需要引入jquery easyUI依赖",
        "hasNoConfigDataError":"配置不存在",
        "callbackConfigNeeded":"需要配置callback参数",
        "exportResourceNeeded":"地图导出功能需要引入FileSaver.js"
    },
    "keys":{
        "token":"token",
        "defaultDragStyleName":"defaultDragStyle",
        "defaultCircleQueryStyleName":"defaultPolygonQueryStyle",
        "defaultPolygonQueryStyleName":"defaultPolygonQueryStyle",
        "defaultDrawStyleName":"defaultDrawStyle",
        "defaultMeasureStyleName":"defaultMeasureStyle"
    }
};
/**
 * 之前的版本使用M，推荐使用JasMap
 */
var JasMap = null ,M = null;
/**
 * 加载平台依赖的类库和配置文件、浏览器兼容问题处理
 */
(function(global){
    ///---浏览器兼容处理--->>
    //for ie8
    if (!Array.prototype.indexOf){
        Array.prototype.indexOf = function(elt /*, from*/){
            var len = this.length >>> 0;
            var from = Number(arguments[1]) || 0;
            from = (from < 0)
                ? Math.ceil(from)
                : Math.floor(from);
            if (from < 0)
                from += len;

            for (; from < len; from++){
                if (from in this && this[from] === elt)
                    return from;
            }
            return -1;
        };
    }
    //for ie8
    if(!String.prototype.trim){
        String.prototype.trim = function () {
            return this.replace(/(^\s*)|(\s*$)/g, "");
        }
    }
    //功能拓展
    ///<<---浏览器兼容处理---
    ///---定义地图框架--->>
    M = JasMap = function(options){
        var _this = this;
        var basePath = "" ;
        var apiDefaults = {
            appScriptId:"mapApi",
            appConfig:"../jasmvvm/pages/map/config.json",
            mapDivId:"jasMap",
            projectPathName:"",
            layersVisible:null,
            sphereRadius:6378137.0,//4490、4326、3857
            appName:"",
            mapStyle:"default",
            dpi:window.screen.deviceXDPI ? window.screen.deviceXDPI :96 ,
            duration:500,
            drawLayerId:"drawLayer",
            dragLayerId:"drawLayer_drag",
            drawLayerIndex:1000,
            defaultZoomLevel:10,
            defaultZoomScale:50000,
            defaultHighlightColor:'rgba(255,0,255,1)',
            onConfigLoaded:function(e){

            },
            onMapLoaded:function(e){

            },
            onLayerAdded:function(e){

            },
            onMapClicked:function(e){

            },
            onError:function(){

            },
            onModulesLoaded:function(e){

            },
            onModuleStartup:function(e){

            },
            onMapResized:function(e){

            }
        };
        var commonUtil = _this.commonUtil = new CommonUtil();
        var apiParams = commonUtil.extend(apiDefaults,options);
        apiDefaults = commonUtil.extend({},apiParams);
        //
        _this.Events = (Constants && Constants.events) ? Constants.events : {};
        _this.Strings = (Constants && Constants.strings) ? Constants.strings : {};
        _this.Keys = (Constants && Constants.keys) ? Constants.keys : {};
        //
        var eventManager = new EventManager();//事件管理
        var layerManager = new LayerManager();//图层管理
        var mapManager = new MapManager();//地图管理
        var moduleManager = new ModuleManager();//模块管理
        var configManager = new ConfigManager(apiDefaults);//配置管理
        var styleManager = new StyleManager();//图层样式管理器

        //
        var startup = function(){

            _this.subscribe( _this.Events .ConfigLoadedEvent ,apiDefaults.onConfigLoaded);
            _this.subscribe( _this.Events .MapLoadedEvent ,apiDefaults.onMapLoaded);
            _this.subscribe( _this.Events .ErrorEvent ,apiDefaults.onError);
            _this.subscribe( _this.Events .ModulesLoadedEvent ,apiDefaults.onModulesLoaded);
            _this.subscribe( _this.Events .ModuleStartupEvent ,apiDefaults.onModuleStartup);
            _this.subscribe( _this.Events .OptionalLayerAddedEvent ,apiDefaults.onLayerAdded);
            _this.subscribe( _this.Events .MapResizedEvent ,apiDefaults.onMapResized);

            eventManager.startup();
            styleManager.startup();
            mapManager.startup();
            moduleManager.startup();
            layerManager.startup();
            configManager.startup();//这里配置加载最后启动

        };//
        _this.map = null;
        _this.apiConfig = null;
        _this.mapConfig = null;
        _this.logMapInfo = function(){
            var view = _this.map.getView();
            var info = {};
            info.size = _this.map.getSize();
            info.center = view.getCenter();
            info.currentZoom = view.getZoom();
            console.log("MapInfo:" + JSON.stringify(info));
        };
        //------------导航类--------------//
        _this.centerAt = function(x, y ){
            //_this.map.getView().centerOn(x,y);
            if(!isNaN(x) && !isNaN(y) ){
                return _this.map.getView().animate({
                    center: [ x * 1.0,y * 1.0 ],
                    duration: apiDefaults.duration
                });
            }
        };
        _this.startPanMode = function(){
            mapManager.active(MapManager.NAVIGATOR);
        };
        _this.levelUp = function(){
            var level = _this.map.getView().getZoom();
            var maxLevel = _this.map.getView().getMaxZoom();
            if(level < maxLevel){
                //_this.map.getView().setZoom(++level);
                _this.map.getView().animate({
                    zoom:++level,
                    duration: 300
                });
            }
        };
        _this.levelDown = function(){
            var level = _this.map.getView().getZoom();
            var minLevel = _this.map.getView().getMinZoom();
            if(level > minLevel){
                //_this.map.getView().setZoom(--level);
                _this.map.getView().animate({
                    zoom:--level,
                    duration: 300
                });
            }
        };
        _this.setLevel = function(level){
            if(!isNaN(level) ){
                //_this.map.getView().setZoom(level * 1);
                _this.map.getView().animate({
                    zoom: level,
                    duration: apiDefaults.duration
                });
            }
        };
        _this.setResolution = function(re){
            if(!isNaN(re) ){
                _this.map.getView().animate({
                    resolution: re,
                    duration: apiDefaults.duration
                });
            }
        };
        _this.zoomHome = function(){
            var args = [].concat(_this.mapConfig.mapOptions.center) ;
            var level = _this.mapConfig.mapOptions.level ;
            args.push(level);
            _this.zoomAt.apply(_this,args);
        };
        _this.zoomIn = function(){
            mapManager.active(MapManager.DragZoomIn);
        };
        _this.zoomOut = function(){
            mapManager.active(MapManager.DragZoomOut);
        };
        _this.zoomAt = function(x, y , level){
            _this.centerAt(x, y);
            if(level=== undefined ){
                var re = commonUtil.scaleToResolution(apiDefaults.defaultZoomScale);
                _this.setResolution(re);
            }else {
                _this.setLevel(level);
            }
        };
        _this.zoomLayer = function(layerId){
            var layer = _this.getLayerById(layerId);
            var source =  layer.getSource();
            if(layer && source){
                if(typeof source.getExtent === "function"){
                    _this.zoomExtent.apply(_this,source.getExtent());
                }
            }
        };
        _this.zoomExtent = function(xmin,ymin,xmax,ymax){
            if(!isNaN(xmin) && !isNaN(ymin) && !isNaN(xmax) && !isNaN(ymax)){
                if(ymin === ymax && xmin === xmax)
                    _this.zoomAt(xmin ,ymin);
                else
                    _this.map.getView().fit([xmin , ymin , xmax ,ymax]);
            }
        };
        _this.hideZoomSlider = function(){
            //console.info("开发中...");
        };
        //------------信息获取类------------//
        _this.getLayerById = function(layerId){
            layerId = layerId.toLowerCase();
            var collection = _this.map.getLayers();
            for(var i = 0 ; i < collection.getLength(); i++){
                var layer = collection.item(i);
                var id = layer.get("id").toLowerCase();
                if(id === layerId)
                    return layer;
            }
        };
        _this.getLayerVisible = function(layerId){
            var layer = _this.getLayerById(layerId);
            if(layer){
                return layer.getVisible();
            }
            return false;
        };
        _this.getMapLevel = function(){
            return _this.map.getView().getZoom();
        };
        _this.getMapMinLevel = function(){
            return _this.map.getView().getMinZoom();
        };
        _this.getMapMaxLevel = function(){
            return _this.map.getView().getMaxZoom();
        };
        _this.getMapCenter = function(){
            return _this.map.getView().getCenter();
        };
        _this.getMapExtent = function(){
            return _this.map.getView().get("extent");
        };
        _this.getFeatures = function(featureId ,layerId ,attributes){
            var result = [];
            var layer = _this.getLayerById(layerId ? layerId : apiDefaults.drawLayerId);
            var source = layer && layer.getSource();
            if(!source) {
                return result ;
            }
            if(featureId){
                var ft = source.getFeatureById(featureId);
                if(ft){
                    result.push(ft);
                }
            }else{
                var features = source.getFeatures();
                for(var i = 0 ; i < features.length ; i++){
                    var feature = features[i];
                    var flg = true ;
                    for(var key in attributes){
                        if(feature.get(key) !== attributes[key])
                            flg = false;
                    }
                    flg && result.push(feature);
                }
            }
            return result;
        };
        _this.queryFeatures = function(featureId ,layerId ,attributes){
            var result = [];
            var layer = _this.getLayerById(layerId ? layerId : apiDefaults.drawLayerId);
            var source = layer && layer.getSource();
            if( source && source.getUrl()){//查询
                var url = source.getUrl();
                url = commonUtil.appendUrl(url,"featureId",featureId);
                commonUtil.simpleAjaxLoader({
                    async: false,
                    url:url ,
                    onSuccess:function(e){
                        var data = JSON.parse(e);
                        if(data){
                            result = new ol.format.GeoJSON().readFeatures(data);//先只处理GeoJSON格式数据
                        }
                    }
                });
            }
            return result;
        };
        _this.getSymbolByObject = function(obj){
            var defaults = {
                img:null,
                fill:null,
                stroke:null,
                renderer:null
            };
            var params = commonUtil.extend(defaults , obj);
            return new ol.style.Style(params);
        };
        //-----------弹出窗口----------//
        _this.showInfoWindow = function(x, y ,content ,title ,width ,height){
            mapManager.showInfoWindow.apply(this,arguments);
        };

        _this.dialog = function(options){
            if(!$ ){
                eventManager.publishError(_this.Strings.hasNoJqueryEasyUILib);
            }
            var defaults = {
                title : "信息窗口" ,
                width :  550 ,
                height : 500 ,
                left: null,
                top: null,
                modal :true ,
                draggable :true ,
                inline: false ,
                resizable :true ,
                collapsible :true ,//可折叠的
                constrain :true,
                href : "",
                $dom :null,
                container :"body", //inline :true 条件下可配置"map"|"body"|"#id"
                onClose: function () {//弹出层关闭事件
                    $(this).dialog('destroy');
                },
                onLoad: function () {//弹出层加载事件

                }
            };
            var container = options.container ;
            var $dom = options.$dom;
            container && delete options.container;
            $dom && delete options.$dom;

            var params = commonUtil.extend(defaults , options);

            if($dom){
                return $dom.dialog(params);
            }
            var inline = options.inline;
            var $dialog = null;
            if(container instanceof Node){
                $dialog =  $('<div id="map-dialog"/>',container);
            }else if(inline && container === "map"){
                $dialog =  $('<div id="map-dialog"/>',_this.map.getTargetElement());
            }else{
                $dialog = $('<div id="map-dialog"/>');
            }
            return $dialog.dialog(params);
        };
        _this.moduleDialog = function(options, $dom ,parent){
            options.resizable = true;
            options.modal = false;
            options.collapsible = true;
            options.constrain = true;
            options.inline = true;
            if(options.right){
                options.left = parent.offsetWidth - options.width - options.right;
            }
            if(options.bottom){
                options.top = parent.offsetHeight - options.height  - options.bottom;
            }
            options.container = parent;
            options.$dom = $dom;
            return _this.dialog(options );
        };
        //-------------图层操作类----------//
        _this.addLayer = function(layer ){
            _this.map.addLayer(layer);
            //layerAddedEvent
            eventManager.publishEvent(_this.Events.OptionalLayerAddedEvent,{ layerId:layer.get("id") });
        };
        _this.addGraphicsLayer = function(layerId,options){
            _this.addVectorLayer.apply(_this,arguments);
        };
        _this.addVectorLayer = function(layerId,options){
            var defaults = {
                "id":layerId,
                "visible":true,
                "type":"Vector",
                "source":"Vector",
                "format":"GeoJSON",
                "strategy":"all",
                "zIndex":null,
                "style": null
            };
            var params = commonUtil.extend(defaults,options);
            var layer = _this.getLayerById(layerId);
            if(layer){
                eventManager.publishError( _this.Strings.layerIdRepeatError + ",id=" + layerId );
                return ;
            }
            layer  = layerManager.createLayer(params);
            _this.addLayer(layer);
            return layer;
        };
        _this.clearAllGraphics = function(layerId){
            if(layerId){
                _this.clearGraphicsLayer(layerId);
            }else{
                _this.clearMapGraphics();
            }
            mapManager.clearAllOverlays();
            //
        };
        _this.addFeatures = function(layerId,features,center){
            var layer = _this.getLayerById(layerId);
            var vectorSource = layer && layer.getSource();
            if(layer && vectorSource){
                vectorSource.addFeatures(features);
            }
            if(center === true){
                _this.flashGraphic(features);
            }
        };
        _this.addGraphics = function(layerId, geoJSON, center){
            var features = new ol.format.GeoJSON().readFeatures(geoJSON);
            _this.addFeatures(layerId,features,center);
        };
        _this.addPictureGraphic  = function(x, y ,options){
            var defaults = {
                url: commonUtil.getApiRootPath("basepath:images/location.png"),
                width:24,
                height:24,
                center:false,
                offset:[0,0],
                anchor:[0.5,1],
                attributes:null,
                layerId:apiDefaults.drawLayerId
            };
            var params = commonUtil.extend(defaults,options);
            var features = [];
            if(isNaN(x) || isNaN(y)) return;
            var feature = new ol.Feature({
                geometry : new ol.geom.Point([x ,y]),
                properties : params.attributes,
                name :  ''
            });
            if( params.attributes.id ){
                feature.setId( params.layerId + "." + params.attributes.id );
            }
            var style = new ol.style.Style({
                image : new ol.style.Icon({
                    anchor: params.anchor,
                    offset: params.offset,
                    src:commonUtil.getApiRootPath(params.url)
                })
            });
            feature.setStyle(style);
            features.push(feature);
            _this.addFeatures(params.layerId,features,params.center);
        };
        _this.addPointGraphic = function(x,y,options){
            var defaults = {
                center:false,
                radius:10,
                offset:[0,0],
                fillColor:[42,42,42,1],
                borderColor:[186,218,37,1],
                borderWidth:1,
                anchor:[0.5,0.5],
                attributes:null,
                layerId:apiDefaults.drawLayerId
            };
            var params = commonUtil.extend(defaults,options);
            if( isNaN(x) && isNaN(y)){
                var features = [new ol.Feature({
                    geometry : new ol.geom.Point([ x ,y]),
                    properties : params.attributes,
                    name :  '',
                    style:new ol.style.Style({
                        image : new ol.style.Circle({
                            anchor: params.anchor,
                            offset: params.offset,
                            radius: params.radius,
                            fill: new ol.style.Fill({ color: params.fillColor }),
                            stroke: new ol.style.Stroke({ color: params.borderColor, width: params.borderWidth})
                        })
                    })
                })];
                _this.addFeatures(params.layerId,features,params.center);
            }
        };
        _this.addPolylineGraphic = function(path,options){
            var defaults = {
                center:false,
                color:[186,218,37,1],
                width:1,
                attributes:null,
                layerId:apiDefaults.drawLayerId
            };
            var params = commonUtil.extend(defaults,options);
            if( path){
                var features = [new ol.Feature({
                    geometry : new ol.geom.LineString(path),
                    properties : params.attributes,
                    name : '',
                    style:new ol.style.Style({
                        stroke: new ol.style.Stroke({
                            color: params.color,
                            width: params.width
                        })
                    })
                })];
                _this.addFeatures(params.layerId,features,params.center);
            }
        };
        _this.addPolygonGraphic = function(ring,options){
            var defaults = {
                center:false,
                fillColor:[42,42,42,1],
                borderColor:[186,218,37,1],
                width:1,
                attributes:null,
                layerId:apiDefaults.drawLayerId
            };
            var params = commonUtil.extend(defaults,options);
            if( ring){
                var features = [new ol.Feature({
                    geometry : new ol.geom.Polygon(ring),
                    properties : params.attributes,
                    name : '',
                    style:new ol.style.Style({
                        stroke: new ol.style.Stroke({
                            color: params.borderColor,
                            width: params.width
                        }),
                        fill:new ol.style.Fill({ color: params.fillColor })
                    })
                })];
                _this.addFeatures(params.layerId,features,params.center);
            }
        };
        _this.removeGraphics = function(layerId , featureIds){
            var layer = _this.getLayerById(layerId);
            var source = layer && layer.getSource();
            if(layer && source){
                for(var j = 0 ; j < featureIds.length ; j++){
                    var featureId = featureIds[j];
                    var feature = source.getFeatureById(featureId);
                    feature && source.removeFeature(feature);
                }
            }
            _this.refreshLayerById(layerId);
        };
        _this.removeLayerByIds = function(layerIds){
            for(var i = 0 ; i < layerIds.length ; i++){
                var layerId = layerIds[i];
                var layer = _this.getLayerById(layerId);
                layer && _this.map.removeLayer(layer);
            }
        };
        _this.refreshLayerById = function(layerId){
            var layer = _this.getLayerById(layerId);
            var source = layer && layer.getSource();
            source && source.refresh();
        };
        _this.removeAllLayers = function(){
            alert("不建议使用！")
        };
        _this.layerVisibleSwitch = function(layerId,visible){
            var layer = _this.getLayerById(layerId);
            layer && layer.setVisible((visible===undefined || visible===false) ? false : true);
        };
        _this.clearGraphicsLayer = function(layerId){
            var layer = _this.getLayerById(layerId);
            var source = layer && layer.getSource();
            source && typeof source.clear === "function" && source.clear();
        };
        _this.clearMapGraphics = function(){
            var layerId = apiDefaults.drawLayerId.toLowerCase();
            var collection = _this.map.getLayers();
            for(var i = 0 ; i < collection.getLength(); i++){
                var layer = collection.item(i);
                var id = layer.get("id").toLowerCase();
                if(id.indexOf(layerId) ===0 )
                    _this.clearGraphicsLayer(id);
            }
        };
        _this.setPointLayerRenderer = function(layerId,options){
            var defaults = {
                radius:7,
                fill:[ 0,0,0,1 ],
                snapToPixel: false ,
                strokeColor:[ 255,0,0,1 ],
                strokeWidth:2
            };
            var layer = _this.getLayerById(layerId);
            if(layer && typeof layer.setStyle === "function"){
                var params = commonUtil.extend(defaults,options);
                var style = new ol.style.Style({
                    image: new ol.style.Circle({
                        radius: params.radius,
                        snapToPixel: params.snapToPixel,
                        fill: new ol.style.Fill({ "color": params.fill }),
                        stroke: new ol.style.Stroke({
                            color: params.strokeColor, width: params.strokeWidth
                        })
                    })
                });
                layer.setStyle(style);
            }
        };
        _this.setPointLayerPictureRenderer = function(layerId,options){
            var defaults = {
                src:null,//图片url
                img:null,//
                imgSize:[16,16],
                opacity:1,
                scale:1,
                offset:[0,0]
            };
            var layer = _this.getLayerById(layerId);
            if(layer &&  typeof layer.setStyle === "function"){
                var params = commonUtil.extend(defaults,options);
                var icon = null;
                if(params.src){
                    icon = new ol.style.Icon({
                        anchor: params.anchor,
                        opacity: params.opacity,
                        scale: params.scale,
                        offset: params.offset,
                        src: 'https://openlayers.org/en/v4.6.4/examples/dataaccess/icon.png'
                    });
                }else if(params.img){
                    icon = new ol.style.Icon({
                        anchor: [0.5, 1],
                        img: params.img,
                        imgSize: params.imgSize,
                        opacity: params.opacity,
                        scale: params.scale,
                        offset: params.offset
                    });
                }
                var style = new ol.style.Style({
                    image: icon
                });
                layer.setStyle(style);
            }
        };
        _this.setLineLayerRenderer = function(layerId,options){
            var defaults = {
                lineDash:[],
                strokeColor:[ 255,0,0,1 ],
                strokeWidth:2
            };
            var layer = _this.getLayerById(layerId);//
            if(layer && typeof layer.setStyle === "function"){
                var params = commonUtil.extend(defaults,options);
                var style = new ol.style.Style({
                    stroke: new ol.style.Stroke({
                        color: params.strokeColor,
                        lineDash:[],
                        width: params.strokeWidth
                    })
                });
                layer.setStyle(style);
            }
        };
        _this.setPolygonLayerRenderer = function(layerId,options){
            var defaults = {
                lineDash:[],
                fill:[ 0,0,0,1 ],
                strokeColor:[ 255,0,0,1 ],
                strokeWidth:2
            };
            var layer = _this.getLayerById(layerId);
            if(layer && typeof layer.setStyle === "function"){
                var params = commonUtil.extend(defaults,options);
                var style = new ol.style.Style({
                    fill: new ol.style.Fill({ "color": params.fill }),
                    stroke: new ol.style.Stroke({
                        color: params.strokeColor,
                        width: params.strokeWidth
                    })
                });
                layer.setStyle(style);
            }
        };
        _this.setLayerOpacity = function(layerId,opacity){
            var layer = _this.getLayerById(layerId);
            layer && layer.setOpacity(opacity);
        };
        _this.setLayerTips = function(layerId,fieldName,options){
            //addOverlayer
        };
        _this.flashLayer = function(layerId,options){
            var defaults = {
                during:500,
                time:5
            };
            var layer  = _this.getLayerById(layerId);
            if(layer){
                var params = commonUtil.extend(defaults,options);
                var i = params.time;
                var interval = setInterval(function(){
                    if(i < 0){
                        clearInterval(interval);
                    }else{
                        layer.getVisible() ? layer.setVisible(false):(layer.setVisible(true) && i--);
                    }
                },params.during);
            }
        };
        _this.addMapEvent = function(type,func){
            if(type && typeof func === "function"){
                return _this.map.on(type,func);
            }
        };
        _this.addMapLoadedEventListener = function(func){
            return _this.addMapEvent("postrender",func);
        };
        _this.addZoomEventListener = function(func){// ?
            return _this.addMapEvent("moveend",func);
        };
        _this.addExtentEventListener = function(func){//?
            return _this.addMapEvent("moveend",func);
        };
        _this.addMouseMoveEventListener = function(func){
            return _this.addMapEvent("pointermove",func);
        };
        _this.addMouseClickEventListener = function(func){
            return _this.addMapEvent("singleclick",func);
        };
        _this.addLayerClickEventListener = function(layerId , func){
            var layer = _this.getLayerById(layerId);
            if(!layer){
                eventManager.publishError(_this.Strings.hasNoIdError + ",layerId=" + layerId);
                return ;
            }
            if(layer && typeof func === "function"){
                return mapManager.addClickLayer(layerId,layer,func);
            }
        };
        _this.addLayerVisibilityChangeEventListener = function(layerId , func){
            var layer = _this.getLayerById(layerId);
            if(layer){
                return layer.on("change:visible",func);
            }
        };
        _this.unMapEvent = function(handler){
            if(handler && handler.type && handler.listener){
                return _this.map.un(handler.type,handler.listener);
            }
        };
        _this.removeInteractions = function(interactions){
            if(interactions instanceof  ol.Collection){
                for(var i = 0 ;i < interactions.getArray().length;i++){
                    var interaction = interactions.getArray()[i];
                    _this.map.removeInteraction(interaction);
                }
            }else if(interactions instanceof  ol.interaction.Interaction){
                _this.map.removeInteraction(interactions);
            }
            interactions = null;
        };
        _this.addInteractions = function(interactions){
            if(interactions instanceof  ol.Collection){
                for(var i = 0 ;i < interactions.getArray().length;i++){
                    var interaction = interactions.getArray()[i];
                    _this.map.addInteraction(interaction);
                }
            }else if(interactions instanceof  ol.interaction.Interaction){
                _this.map.addInteraction(interactions);
            }
        };
        _this.removeEventListener = function(handler){
            if(handler){
                if(handler instanceof ol.interaction.Interaction){
                    _this.removeInteractions(handler);
                }else if( handler.type && handler.listener){
                    _this.unMapEvent(handler);
                }else{

                }
            }
        };
        _this.flashGraphic = function(target ,layerId ,options){
            var defaults = {
                "repeatCount":5,
                "delay":500,//ms
                "fieldName":"oid",
                "center":true,
                "scale":20000 // 与map lods参数或底图的比例尺设置相关
            };
            var params = commonUtil.extend(defaults,options);
            layerId = layerId ? layerId.trim() : apiDefaults.drawLayerId;
            var targetLayer = _this.getLayerById(layerId);
            var features = [];
            //
            if(targetLayer && !targetLayer.getVisible()){
                _this.layerVisibleSwitch(layerId,true);
            }
            var prepareFlashStyle = function(){
                if(features && features.length > 0){
                    var layerStyle = targetLayer.getStyle();
                    for(var i = 0 ; i < features .length ; i++) {
                        var f1 = features[i];
                        var fStyle1 =  f1.getStyle ? f1.getStyle():null;
                        var boldStyle = styleManager.getBoldStyle(fStyle1,layerStyle,f1);
                        f1.set("preSymbol",fStyle1);
                        f1.set("boldSymbol",boldStyle);
                    }
                    return true;
                }
                return false;
            };
            var featureStyleFlash = function(flg){
                for(var i = 0 ; i < features .length ; i++){
                    var f = features[i];
                    var fStyle = null;
                    if(flg){
                        fStyle = f.get("boldSymbol");
                    }else{
                        fStyle = f.get("preSymbol");
                    }
                    f.setStyle(fStyle);
                }
            };
            var doPosition = function(){
                //取得多个要素的分布范围
                var feature0 = features[0];
                if(!feature0 ){
                    eventManager.publishError(_this.Strings.featureNotFound);
                    return false ;
                }
                if( !feature0.getGeometry()){
                    eventManager.publishError(_this.Strings.geometryNotFound);
                    return false ;
                }
                var extent = feature0.getGeometry().getExtent();//要素的分布范围
                for(var i = 1 ; i < features.length ; i++){
                    var featureI = features[i];
                    featureI && ( extent = ol.extent.extend(extent,featureI.getGeometry().getExtent()));
                }
                //分布与图层显示范围判定？
                if(extent[0] === extent[2] && extent[1]===extent[3]){
                    var maxReso = targetLayer.getMaxResolution();
                    var minReso = targetLayer.getMinResolution();
                    var defaultReso = commonUtil.scaleToResolution(apiDefaults.defaultZoomScale);
                    if(maxReso < defaultReso){
                        defaultReso = maxReso;
                    }else if(maxReso === defaultReso){
                        defaultReso = maxReso / 2;
                    }else if(minReso === defaultReso){
                        defaultReso = minReso * 2;
                    }else if(minReso > defaultReso){
                        defaultReso = minReso;
                    }
                    _this.centerAt(extent[0],extent[1]);
                    _this.setResolution(defaultReso);
                }else{
                    _this.zoomExtent.apply(_this,extent);
                }
                return true ;
            };
            var doFlash = function(){
                var flash = true;
                var repeat = params.repeatCount;
                var interval = setInterval(function(e){
                    if(repeat < 0 )
                        clearInterval(interval);
                    else{
                        flash = !flash;
                        featureStyleFlash(flash);
                        flash || --repeat;
                    }
                },params.delay);
            };
            if(targetLayer && targetLayer.getSource() && targetLayer.getSource().getUrl()){
                if(typeof target === "string") {//主键查询
                    if (target.indexOf(".") < 0) {
                        target = layerId + "." + target;//geotools主键方案"表名.主键"
                    }
                    features = _this.queryFeatures(target, layerId);
                }else if(typeof target === "object"){
                    features = _this.queryFeatures(null,layerId ,target);
                }
                if(features.length > 0){
                    targetLayer.once("render",function(e){
                        if(typeof target === "string") {
                            if (target.indexOf(".") < 0) {
                                target = layerId + "." + target;
                            }
                            features = _this.getFeatures(target, layerId);
                        }else if(typeof target === "object"){
                            features = _this.getFeatures(null,layerId ,target);
                        }
                        prepareFlashStyle() && doFlash();
                    });
                    doPosition();
                }else{
                    eventManager.publishError(_this.Strings.featureNotFound);
                }
            }else{
                //如果是数组,认为是要素数组
                if(Array.isArray(target)){
                    features = target;
                }else if(typeof target === "string") {//如果是字符串，则按主键处理
                    if (target.indexOf(".") < 0) {
                        target = layerId + "." + target;//geotools
                    }
                    features = _this.getFeatures(target, layerId);
                }else if(typeof target === "object"){
                    features = _this.getFeatures(null,layerId ,target);
                }
                if(features.length > 0){
                    prepareFlashStyle() && doPosition() && doFlash();
                }else{
                    eventManager.publishError(_this.Strings.featureNotFound);
                }
            }
        };
        _this.switchBaseMap = function(layerId){
            for(var i = 0 ; i < layerManager.baseMapLayers.length ; i++){
                var baseLayer = layerManager.baseMapLayers[i];
                var baseLayerId = (baseLayer.get ? baseLayer.get("id"):baseLayer.id);
                var visible = ( baseLayerId === layerId );
                if(!baseLayer.layers){
                    baseLayer.setVisible(visible);
                }else{
                    for(var j = 0; j< baseLayer.layers.length ;j++ ){
                        var layer = baseLayer.layers[j];
                        layer.setVisible(visible);
                    }
                }
            }
        };
        _this.layerSetVisibleSwitch = function(layerSetId){
            //开发中...

        };
        _this.export = function(){
            _this.map.once('postcompose', function (event) {
                var canvas = event.context.canvas;
                var pngName = "map" + new Date().getTime() + ".png";
                if(!navigator.msSaveBlob && !saveAs){
                    eventManager.publishError(_this.Strings.exportResourceNeeded);
                }
                if (navigator.msSaveBlob) {
                    navigator.msSaveBlob(canvas.msToBlob(), pngName);
                } else {
                    canvas.toBlob(function (blob) {
                        saveAs(blob,pngName);
                    });
                }
            });
            _this.map.renderSync();
        };
        //---------------标绘--------------//
        _this.getXY = function(callback){
            if(typeof callback === "function"){
                _this.map.once("click",function(e){
                    e && e.coordinate && callback(e.coordinate );
                });
            }
        };
        _this.editGraphic = function(layerId){
            var layer = _this.getLayerById(layerId ? layerId : apiDefaults.drawLayerId);
            layer && mapManager.active(MapManager.EDITOR ,layer);
        };
        _this.drawGraphic = function(drawType,options){
            var defaults = {
                "drawLayerId" : apiDefaults.drawLayerId,
                "attributes" : null,
                "onDrawEnd" : null,
                "onDrawStart" : null,
                "style":null
            };
            var params = commonUtil.extend(defaults,options);
            var layer = _this.getLayerById(params.drawLayerId);
            params.drawType = drawType;
            layer && mapManager.active(MapManager.DRAW,layer,params);
        };
        _this.drawPoint = function(options){
            _this.drawGraphic("Point",options);
        };
        _this.drawCircle = function(options){
            _this.drawGraphic("Circle",options);
        };
        _this.drawLine = function(options){
            _this.drawGraphic("LineString",options);
        };
        _this.drawPolygon = function(options){
            _this.drawGraphic("Polygon",options);
        };
        _this.drawPolyline = function(options){
            _this.drawLine(options);
        };
        _this.drawLineAndGetLength = function(callback,options){
            //检查监听状态，如果正在绘制，清除相关事件
            var defaults = {
                "onDrawStart":null,
                "drawOptions":null,
                "continueMsg":"双击结束",
                "startMsg":"单击开始测量长度",
                "labelStyleFunc":function(length){
                    var label = "";
                    if(length <= 1000)
                        label = length.toFixed(1) + " m";
                    else
                        label = (length/1000).toFixed(3) + " km";
                    return label;
                }
            };
            var params = commonUtil.extend(defaults,options);
            mapManager.measureInit(callback,params);
            _this.drawLine({
                "onDrawStart":params.onDrawStart,
                "onDrawEnd":params.onDrawEnd,
                "style":{
                    radius:"5",
                    picture:null,
                    height:16,
                    width:16,
                    offset_x:8,
                    offset_y:16,
                    border_width:2,
                    border_color:"#ffcc33",
                    border_opacity:1,
                    fill_color:"rgb(225,205,51,0.6)"
                }
            });
        };
        _this.drawPolygonAndGetArea = function(callback,options){
            //检查监听状态，如果正在绘制，清除相关事件
            var defaults = {
                "onDrawStart":null,
                "drawOptions":null,
                "continueMsg":"双击结束",
                "startMsg":"单击开始测量面积",
                "labelStyleFunc":function(area){
                    var label = "";
                    if(area > 10000)
                        label = (Math.round(area/1000000 * 100) / 100) +' ' + 'km<sup>2</sup>';
                    else
                        label = (Math.round(area*100) / 100) + ' ' + 'm<sup>2</sup>';
                    return label;
                }
            };
            var params = commonUtil.extend(defaults,options);
            mapManager.measureInit(callback,params);
            _this.drawPolygon({
                "onDrawStart": params.onDrawStart,
                "onDrawEnd": params.onDrawEnd,
                "style":{
                    radius:"5",
                    picture:null,
                    height:16,
                    width:16,
                    offset_x:8,
                    offset_y:16,
                    border_width:2,
                    border_color:"#ffcc33",
                    border_opacity:1,
                    fill_color:"rgb(225,205,51,0.6)"
                }
            });
        };
        //---------------事件方法------------//
        _this.publish = function(eventType , data){
            eventManager.publishEvent.apply(this,arguments);
        };
        _this.subscribe = function(eventType , onListener){
            eventManager.registerEvent.apply(this,arguments);
        };
        _this.registerEventType = function(type,name){
            if(_this.Events[type]){
                eventManager.publishError(_this.Strings.eventNameRepeatError);
                return
            }
            _this.Events[type] = name;
        };
        _this.getModuleById = function( moduleId ){
            return moduleManager.getModuleById(moduleId);
        };
        _this.resizeMap = function(){
            _this.map.updateSize();
        };


        //-----------几个管理器------------//
        function MapManager(){
            MapManager.NAVIGATOR = "navigator" ;
            MapManager.DRAW = "draw" ;
            MapManager.EDITOR = "editor" ;
            MapManager.DragZoomIn = "dragZoomIn" ;
            MapManager.DragZoomOut = "dragZoomOut" ;
            var _class = this;
            var createMap = function(){
                var divId = _this.mapConfig.id ? _this.mapConfig.id : apiDefaults.mapDivId;
                var mapContainerDom = document.getElementById(divId);
                if(!mapContainerDom){
                    eventManager.publishError(_this.Strings.mapDivIdNotExitsError + " id=" + divId);
                    return;
                }
                var viewOptions = commonUtil.extend({
                    "center":[ 118.41, 28.82 ],//中国
                    "zoom":3
                },{
                    "center":_this.mapConfig.mapOptions.center,
                    "zoom":_this.mapConfig.mapOptions.level
                });
                viewOptions.projection = _class.defineProjection(_this.mapConfig.mapOptions.projection);
                _this.map = new ol.Map({
                    target:divId,
                    view:new ol.View(viewOptions),
                    logo:false
                });
                if(_this.mapConfig.attribution !== true){
                    _this.map.getControls().pop();//移除attribution
                }
                if(_this.mapConfig.scale === true){//有点问题！
                    var scaleLineControl = new ol.control.ScaleLine({
                        "units":"metric",
                        "topOutUnits":"千米",
                        "topInUnits":"米"
                    });
                    _this.map.getControls().push(scaleLineControl);
                }
                _this.map.on("change:size",function(e){
                    eventManager.publishEvent( _this.Events .MapResizedEvent );
                });
                eventManager.publishEvent( _this.Events .MapLoadedEvent ,null, 10);

            };
            var onConfigLoaded = function(e){
                _this.apiConfig = e.data;
                _this.mapConfig = e.data.map;
                createMap();
            };
            var onMapLoaded = function(e){
                //创建默认的标绘图层
                _this.addVectorLayer(apiDefaults.drawLayerId ,{
                    "index": apiDefaults.drawLayerIndex,
                    "style":{
                        radius:"5",
                        picture:null,
                        height:16,
                        width:16,
                        offset_x:8,
                        offset_y:16,
                        border_width:2,
                        border_color:"#ffcc33",
                        border_opacity:1,
                        fill_color:'rgba(255, 204, 51, 0.5)',
                        fill_opacity:1
                    }
                });

            };
            var onOptionLayersLoaded = function(e){
                var fLayers = e.data.flashLayers;
                var layers = e.data.layers;
                addLayerListeners(layers);
                addLayerFlashEffect(fLayers,layers);
                initDragAndDropInteracting();
                _class.active(MapManager.NAVIGATOR);
            };
            var onLayerAdded = function(e){
                var layerId = e.data.layerId;
                if(layerManager.initLayersVisible && layerManager.initLayersVisible[layerId] !== undefined){
                    _this.layerVisibleSwitch(layerId,layerManager.initLayersVisible[layerId]);
                }
            };
            //---量测模型---
            var currentDrawFeature = null;
            var measureParams = null;
            var measureValueOverlay = null;//测量值Overlay
            var measureTipOverlay = null;//测量提示Overlay
            var onMouseMove = null;//
            var onMouseOut = null;
            var onGeometryChange = null;
            var onMeasureDrawEnd = null;
            var onMeasureDrawStart = null;
            var addMouseEventHandler = function(){
                onMouseMove = _this.map.on('pointermove', function(evt) {
                    if (evt.dragging) return;
                    var helpMsg = measureParams.startMsg;
                    if (currentDrawFeature) {
                        var geom = (currentDrawFeature.getGeometry());
                        if (geom instanceof ol.geom.Polygon) {
                            helpMsg = measureParams.continueMsg;
                        } else if (geom instanceof ol.geom.LineString) {
                            helpMsg = measureParams.continueMsg;
                        }
                    }
                    measureTipOverlay.getElement().innerHTML = helpMsg;
                    measureTipOverlay.setPosition(evt.coordinate);
                    measureTipOverlay.getElement().classList.remove('hidden');
                });
                onMouseOut = _this.map.getViewport().addEventListener('mouseout', function(evt){
                    if(measureTipOverlay){
                        measureTipOverlay.getElement().classList.add('hidden');
                    }
                });
            };
            var addMeasureTipOverlay = function(){
                var helpTooltipElement;
                if(measureTipOverlay){
                    helpTooltipElement = measureTipOverlay.getElement();
                    //helpTooltipElement.parentNode && helpTooltipElement.parentNode.removeChild(helpTooltipElement);
                    helpTooltipElement.classList.remove('hidden');
                }else{
                    helpTooltipElement = document.createElement('div');
                    helpTooltipElement.className = 'tooltip hidden';
                    measureTipOverlay = new ol.Overlay({
                        element: helpTooltipElement,
                        offset: [15, 0],
                        positioning: 'center-left'
                    });
                    _this.map.addOverlay(measureTipOverlay);
                }
            };
            var addMeasureValueOverlay = function(){
                var measureValueElement = document.createElement('div');
                measureValueElement.className = 'tooltip tooltip-measure';
                measureValueOverlay = new ol.Overlay({
                    element: measureValueElement,
                    offset: [0, -15],
                    positioning: 'bottom-center'
                });
                _this.map.addOverlay(measureValueOverlay);

            };
            var clearMeasureState = function(){
                measureParams && (measureParams=null);
                onMeasureDrawEnd && _this.removeEventListener(onMeasureDrawEnd);
                onMeasureDrawStart && _this.removeEventListener(onMeasureDrawStart);
                onMouseMove && _this.removeEventListener(onMouseMove);
                onGeometryChange && _this.removeEventListener(onGeometryChange);
                onMouseOut && _this.removeEventListener(onMouseOut);
                measureTipOverlay && _this.map.removeOverlay(measureTipOverlay);
                measureTipOverlay = null;
                currentDrawFeature = null;
            };
            _class.measureInit = function(callback,options){
                clearMeasureState();
                addMeasureTipOverlay();
                addMouseEventHandler();
                measureParams = options;
                onMeasureDrawEnd = measureParams.onDrawEnd = function(evt){
                    currentDrawFeature = null;
                    var measureValueElement = measureValueOverlay.getElement();
                    measureValueElement.className = 'tooltip tooltip-static';
                    measureValueOverlay.setOffset([0, -7]);
                    ol.Observable.unByKey(onGeometryChange);//?
                    if(callback && typeof callback === "function"){
                        callback(measureParams.labelStyleFunc(length));
                    }
                    clearMeasureState();
                    _this.removeEventListener(_class.drawInteracting);
                } ;
                onMeasureDrawStart = measureParams.onDrawStart = function(evt) {
                    addMeasureValueOverlay();
                    currentDrawFeature = evt.feature;
                    var tooltipCoord = evt.coordinate;
                    onGeometryChange = currentDrawFeature.getGeometry().on('change', function(evt) {
                        var geom = evt.target;
                        var output;
                        var coordinates;
                        var sphereMetricOptions = {
                            projection:_this.map.getView().getProjection(),
                            radius:apiDefaults.sphereRadius
                        };
                        if (geom instanceof ol.geom.Polygon) {
                            //coordinates = geom.getLinearRing(0).getCoordinates();
                            //var area = Math.abs(wgs84Sphere.geodesicArea(coordinates));
                            var area = Math.abs(ol.sphere.getArea(geom ,sphereMetricOptions));
                            output = measureParams.labelStyleFunc(area);
                            tooltipCoord = geom.getInteriorPoint().getCoordinates();
                        } else if (geom instanceof ol.geom.LineString) {
                            var length = Math.abs(ol.sphere.getLength(geom,sphereMetricOptions));
                            output = measureParams.labelStyleFunc(length);
                            tooltipCoord = geom.getLastCoordinate();
                        }
                        measureValueOverlay.getElement().innerHTML = output;
                        measureValueOverlay.setPosition(tooltipCoord);
                    });
                };
            };
            _class.drawInteracting = null;
            _class.editInteracting = null;
            _class.snapInteracting = null;
            //------地图拖拽------
            _class.dragAndDropInteracting = null;
            var dragAndDropStyleFunction = function(feature, resolution) {
                var featureStyleFunction = feature.getStyleFunction();
                if (featureStyleFunction) {
                    return featureStyleFunction.call(feature, resolution);
                } else {
                    var styleName = _this.Keys.defaultDragStyleName + "_" + feature.getGeometry().getType();
                    return styleManager.parse(styleName);
                }
            };
            var initDragAndDropInteracting = function(){
                if(_class.dragAndDropInteracting){
                    _this.removeEventListener(_class.dragAndDropInteracting);
                }
                _class.dragAndDropInteracting = new ol.interaction.DragAndDrop({
                    formatConstructors: [
                        ol.format.WKT,
                        ol.format.EsriJSON,
                        ol.format.GPX,
                        ol.format.GeoJSON,
                        ol.format.IGC,
                        ol.format.KML,
                        ol.format.TopoJSON
                    ]
                });
                _class.dragAndDropInteracting.on('addfeatures', function(event) {
                    var layerId = apiDefaults.dragLayerId;
                    var layer = _this.getLayerById(layerId);
                    if(!layer){
                        layer = _this.addVectorLayer(layerId,{ "index": apiDefaults.drawLayerIndex});
                    }
                    var vectorSource = layer.getSource();
                    vectorSource.addFeatures(event.features);
                    layer.setStyle(dragAndDropStyleFunction);
                    var extent = vectorSource.getExtent();
                    _this.zoomExtent.apply(_this,extent);
                });
                _this.addInteractions(_class.dragAndDropInteracting);
            };
            //------图层高亮-------
            var clickListenedLayers = {};
            _class.flashSelectInteracting = null;//鼠标经过图层高亮
            var addLayerFlashEffect = function(flashLayers,layers){
                //添加交互对象
                _class.flashSelectInteracting = new ol.interaction.Select({
                    style:function(feature){
                        var layerId = feature._layerId ;
                        var style = feature.getStyle();
                        var lStyle = styleManager.layerStyles[layerId];
                        return styleManager.getBoldStyle(style,lStyle,feature);
                    },
                    condition: ol.events.condition.pointerMove,
                    layers:flashLayers
                });
                _class.flashSelectInteracting.on("select",function(e){
                    var features = e.selected;
                    for(var i = 0 ; i < features.length ;i++){
                        var feature = features[i];
                        var layerId = e.target.getLayer(feature).get("id");
                        feature._layerId = layerId;
                    }
                });
                _this.map.addInteraction(_class.flashSelectInteracting);
            };
            //------图层点击------
            _class.clickInteracting = null;//图层点击交互对象
            _class.addClickLayer = function(layerId ,layer ,callback){
                if(!clickListenedLayers[layerId]){
                    clickListenedLayers[layerId] = {
                        layer:layer,
                        callback:callback
                    };
                }
                var interaction = _class.clickInteracting;
                if(interaction){
                    _this.removeInteractions(interaction);
                    interaction = null;
                }
                interaction = new ol.interaction.Select({
                    condition: ol.events.condition.click,
                    layers:_class.getClickLayers()
                });
                interaction.on("select",function(e){
                    var features = e.selected;
                    var pixel = e.mapBrowserEvent.pixel;
                    var coordinate = e.mapBrowserEvent.coordinate;
                    for(var i = 0 ; i < features.length ;i++){
                        var feature = features[i];
                        //默认的样式设置 ？
                        var layerId = e.target.getLayer(feature).get("id");
                        layerManager.writeFeatureId(feature);
                        var ly = clickListenedLayers[layerId];
                        if(ly && ly.callback){
                            ly.callback({
                                layerId:layerId,
                                attributes:feature.getProperties() ,
                                feature:feature,
                                pixel:pixel ,
                                coordinate:coordinate
                            });
                        }
                    }
                });
                _this.map.addInteraction(interaction);
                return interaction;
            };
            _class.removeClickLayer = function(layerId){
                if(clickListenedLayers[layerId]){
                    delete clickListenedLayers[layerId];
                }
            };
            _class.getClickLayers = function(){
                var layers = [];
                for(var key in clickListenedLayers ){
                    var v = clickListenedLayers[key];
                    if(v && v.layer){
                        layers.push(v.layer);
                    }
                }
                return layers ;
            };
            //-------信息窗口------
            var infoWindowOverlay = null;
            var popupElement = null;
            var titleContainer = null;
            var titleElement = null;
            var contentContainer = null;
            var contentElement = null;
            var titleCloser = null;

            _class.showInfoWindow = function(x,y,content,title,width,height){
                if(!infoWindowOverlay){
                    popupElement = document.createElement("div");
                    popupElement.className = "map-popup";
                    popupElement.id = "popup";
                    //title
                    titleContainer = document.createElement("div");
                    titleContainer.id = "popup-title";
                    titleElement = document.createElement("p");
                    titleCloser = document.createElement("a");
                    titleCloser.href = "#";
                    titleCloser.id = "popup-closer";
                    titleCloser.className = "map-popup-closer";
                    titleContainer.appendChild(titleElement);
                    titleContainer.appendChild(titleCloser);
                    //
                    var href = document.createElement("hr");
                    //content
                    contentContainer = document.createElement("div");
                    contentContainer.id ="popup-content";
                    contentElement= document.createElement("div");
                    contentElement.className = "popup-content-body";
                    contentContainer.appendChild(contentElement);
                    //
                    popupElement.appendChild(titleContainer);
                    popupElement.appendChild(href);
                    popupElement.appendChild(contentContainer);

                    infoWindowOverlay = new ol.Overlay({
                        element: popupElement ,
                        autoPan: true,
                        autoPanAnimation: {
                            duration: 250
                        }
                    });
                    _this.map.addOverlay(infoWindowOverlay);
                    //
                    titleCloser.onclick = function() {
                        infoWindowOverlay.setPosition(undefined);
                        titleCloser.blur();
                        return false;
                    };
                }
                titleElement.innerHTML = title ? title : "信息窗口";
                if(width && height){
                    popupElement.style.width = width + "px" ;
                    popupElement.style.height = height + "px";
                }
                if(content instanceof Node){
                    contentElement.innerHTML="";
                    contentElement.appendChild(content)  ;
                }else  {
                    contentElement.innerHTML = content ? content :"无";
                }
                infoWindowOverlay.setPosition([ x, y ]);
            };

            _class.defineProjection = function(epsgString){
                if(epsgString === "EPSG:4326" || epsgString === "EPSG:3857"){
                    return epsgString;
                }
                if(!proj4){
                    eventManager.publishError( _this.Strings.hasNoProj4js);
                    return;
                }
                var projection = null;
                switch (epsgString){
                    case "EPSG:4490":
                        proj4.defs("EPSG:4490","+proj=longlat +ellps=GRS80 +no_defs");
                        projection = new ol.proj.Projection({
                            code: 'EPSG:4490',
                            units: 'degrees',
                            axisOrientation: 'neu'
                        });
                        projection.setExtent([-180,-90,180,90]);break;
                    default:
                }
                return projection;
            };
            _class.clearAllOverlays = function(){
                var collection = _this.map.getOverlays();
                var deleteCollection = new ol.Collection();
                collection.forEach(function(ele,i){
                    if(ele && ele.get("undeletable") !== true){
                        deleteCollection.push(ele);
                    }
                });
                collection.clear();
            };
            //-------图层交互-------
            var hoverLayers = [];
            var clickLayers = [];
            var dbClickLayers = [];
            var layerTipOverLay = null;
            var layerTipInteracting = null;//提示
            var layerClickInteracting = null;//单击
            var layerDbClickInteracting = null;//双击
            var addLayerTipOverLay = function(){
                var element = document.createElement('div');
                element.className = 'tooltip tooltip-layer';
                layerTipOverLay = new ol.Overlay({
                    element: element,
                    offset: [0, -15],
                    positioning: 'bottom-center'
                });
                _this.map.addOverlay(layerTipOverLay);
            };
            var addLayerListeners = function(layers){
                var listenerConfig = layerManager.onListeners;
                for(var i = 0 ; i< layers.length ; i++){
                    var layer = layers[i];
                    var layerId = layer.get("id");
                    var listener = listenerConfig[layerId];
                    if(listener) {
                        if(listener.onHover){
                            hoverLayers.push(layer);
                        }
                        if(listener.onClick){
                            clickLayers.push(layer);
                        }
                        if(listener.onDbClick){
                            dbClickLayers.push(layer);
                        }
                    }
                }
                if(hoverLayers.length > 0 )
                    addHoverListener(hoverLayers);
                if(clickLayers.length > 0 )
                    addClickListener(clickLayers);
                if(dbClickLayers.length > 0 )
                    addDbClickListener(dbClickLayers);
            };
            var addHoverListener = function(layers){
                var listenerConfig = layerManager.onListeners;
                layerTipInteracting = new ol.interaction.Select({
                    style:function(feature){
                        var layerId = feature._layerId ;
                        //var style = feature.getStyle();
                        var lStyle = styleManager.layerStyles[layerId];
                        //return styleManager.getBoldStyle(style,lStyle,feature);
                        return typeof lStyle === "function" ? lStyle(feature) : lStyle ;
                    },
                    condition: ol.events.condition.pointerMove,
                    layers:layers
                });
                layerTipInteracting.on("select",function(e){
                    var feature = e.selected[0];
                    if(feature){
                        var layer = e.target.getLayer(feature);
                        var layerId = layer.get("id");
                        var coors = e.mapBrowserEvent.coordinate;
                        var conf = listenerConfig[layerId];
                        feature._layerId = layerId;
                        parseListenerParams(layerId,feature,coors,conf.onHover);
                    }else{
                        layerTipOverLay && layerTipOverLay.setPosition();
                    }
                });
                _this.map.addInteraction(layerTipInteracting);
            };
            var addClickListener = function(layers){
                var listenerConfig = layerManager.onListeners;
                layerClickInteracting = new ol.interaction.Select({
                    condition: ol.events.condition.click,
                    layers:layers
                });
                layerClickInteracting.on("select",function(e){
                    var feature = e.selected[0];
                    if(feature){
                        var layer = e.target.getLayer(feature);
                        var layerId = layer.get("id");
                        var coors = e.mapBrowserEvent.coordinate;
                        var conf = listenerConfig[layerId];
                        parseListenerParams(layerId,feature,coors,conf.onClick);
                    }
                });
                _this.map.addInteraction(layerClickInteracting);
            };
            var addDbClickListener = function(layers){
                var listenerConfig = layerManager.onListeners;
                layerDbClickInteracting = new ol.interaction.Select({
                    condition: ol.events.condition.doubleClick,
                    layers:layers
                });
                layerDbClickInteracting.on("select",function(e){
                    var feature = e.selected[0];
                    if(feature){
                        var layer = e.target.getLayer(feature);
                        var layerId = layer.get("id");
                        var coors = e.mapBrowserEvent.coordinate;
                        var conf = listenerConfig[layerId];
                        parseListenerParams(layerId,feature,coors,conf.onDbClick);
                    }
                });
                _this.map.addInteraction(layerDbClickInteracting);
            };
            var parseAttrTemplate = function(fieldNames,template,attribute){
                var result = template;
                var fieldsArray =( typeof fieldNames === "string" ? fieldNames.split(","):fieldNames );
                for(var i = 0 ; i < fieldsArray.length;i++){
                    var name = fieldsArray[i];
                    var value = attribute[name];
                    result = result.replace(new RegExp("\\${" + name + "}","gm"),value ? value :"");
                }
                return result;
            };
            var parseListenerParams = function(layerId ,feature , coors ,param){
                var attr = feature.getProperties();
                var responseType = param.responseType;
                try{
                    if(responseType === "tip"){
                        if(layerTipOverLay === null){
                            addLayerTipOverLay();
                        }
                        if(feature) {
                            var text = parseAttrTemplate(param.fieldNames, param.template, attr);
                            var ele = layerTipOverLay.getElement();
                            ele.innerHTML = text;
                            layerTipOverLay.setPosition(coors);
                        }else{
                            layerTipOverLay.setPosition();
                        }
                    }
                    if(responseType.toLowerCase() === "infowindow"){
                        var title = param.title ? param.title :"";
                        var content = param.content ? param.content :"";
                        var fieldNames = content.match(/[a-zA-Z]+/g);
                        var text = parseAttrTemplate(fieldNames,content,attr);
                        var height = param.height ? param.height:150 ;
                        var width = param.width ? param.width:250 ;
                        _this.showInfoWindow(coors[0],coors[1],text,title,width,height);
                    }
                    if(responseType.toLowerCase() === "dialog"){
                        var title = param.title ? param.title :"";
                        var height = param.height ? param.height:450 ;
                        var width = param.width ? param.width:550 ;
                        var url = param.url ;
                        //
                        _this.dialog({
                            open:true,
                            title:title,
                            href:url ,
                            width : width,
                            height : height
                        });
                    }
                    if(responseType.toLowerCase()==="function"){
                        var callback = param.callback;
                        if(!callback)
                            throw (_this.Strings.callbackConfigNeeded);
                    }
                    //
                    var functionName = param.callback;
                    if(functionName){
                        var func = eval(functionName);
                        func(attr);
                    }
                }catch(e){
                    eventManager.publishWarn(_this.Strings.layerListenerConfigError,e);
                }
            };
            //---------draw-----------
            var deleteListener = null;
            var clearDrawState = function(){
                _this.removeEventListener(_class.drawInteracting);
                _this.removeEventListener(_class.editInteracting);
                _this.removeEventListener(_class.snapInteracting);
                _this.removeEventListener(deleteListener);
            };
            var initDrawState = function(state,vectorLayer,param){
                var geometryFunction = null;
                var drawType = param.drawType ;
                var onDrawEnd = param.onDrawEnd;
                var onDrawStart = param.onDrawStart;
                if("Delete" === drawType){
                    _this.removeEventListener(deleteListener);
                    deleteListener = _this.addLayerClickEventListener(apiDefaults.drawLayerId,function(e){
                        var fId = e.feature.getId();
                        _this.removeGraphics(e.layerId,[fId]);
                    });
                    return;
                }
                if( drawType && vectorLayer ) {
                    var style = styleManager.drawStyle(param.style);
                    if(drawType === "Box"){
                        drawType = 'Circle';
                        geometryFunction = ol.interaction.Draw.createBox();
                    }
                    if(drawType === "Picture"){
                        drawType = 'Point';
                    }
                    _class.drawInteracting = new ol.interaction.Draw({
                        source: vectorLayer.getSource(),
                        type: drawType,
                        geometryFunction: geometryFunction,
                        style: style
                    });
                    _class.drawInteracting.on("drawend",function(evt){
                        if(param.style){
                            var style = styleManager.parse(param.style);
                            //var geometry = evt.feature.getGeometry();
                            //style.setGeometry(geometry);
                            evt.feature.setStyle(style);
                        }
                        if(!evt.feature.getId()){
                            evt.feature.setId(new Date().getTime());
                        }
                        if(onDrawEnd && typeof onDrawEnd === "function"){
                            onDrawEnd.apply(this,arguments);
                        }
                    });
                    _class.drawInteracting.on("drawstart",function(evt){
                        if(onDrawStart && typeof onDrawStart==="function"){
                            onDrawStart.apply(this,arguments);
                        }
                    });
                    _class.editInteracting = new ol.interaction.Modify({
                        source: vectorLayer.getSource()
                    });
                    _class.snapInteracting = new ol.interaction.Snap({
                        source: vectorLayer.getSource()
                    });
                    var interactions = new ol.Collection([ _class.editInteracting, _class.snapInteracting  ,_class.drawInteracting]);
                    _this.addInteractions( interactions);
                }
            };
            var initEditState = function(){
                var select = new ol.interaction.Select({
                    wrapX: false
                });
                var modify = new ol.interaction.Modify({
                    features: select.getFeatures()
                });
                _class.editInteracting = new ol.Collection([ select, modify ]);
                _this.addInteractions( _class.editInteracting);
            };
            var initDragZoomState = function(outFlag){
                var dragZoomInteracting = null;
                var dragZoom = null;
                _this.map.interactions.forEach(function(ele,index,arr){
                    if(ele instanceof ol.interaction.DragZoom){
                        dragZoomInteracting = ele;
                        
                    }
                });
                if(dragZoomInteracting !== null){
                    _this.map.interactions.remove(dragZoomInteracting);
                }
                if(outFlag === undefined){
                    dragZoom = new ol.interaction.DragZoom({
                        condition:ol.events.condition.shiftKeyOnly
                    });
                }else{
                    dragZoom = new ol.interaction.DragZoom({
                        condition:ol.events.condition.mouseOnly,
                        out: outFlag === false ? false :true
                    });
                }
                _this.addInteractions( dragZoom);

            };
            _class.startup = function(){
                _this.subscribe( _this.Events .ConfigLoadedEvent ,onConfigLoaded);
                _this.subscribe( _this.Events .MapLoadedEvent ,onMapLoaded);
                _this.subscribe( _this.Events .OptionalLayersLoaded ,onOptionLayersLoaded);
                _this.subscribe( _this.Events .OptionalLayerAddedEvent , onLayerAdded);
            };
            _class.active = function(state,vectorLayer,param ){
                switch(state){
                    case MapManager.DRAW:
                        clearDrawState();
                        initDrawState(state,vectorLayer,param );
                        break;
                    case MapManager.EDITOR:
                        //clearDrawState();
                        initEditState();
                        break;
                    case MapManager.NAVIGATOR:
                        clearDrawState();
                        clearMeasureState();
                        initDragZoomState();
                        break;
                    case MapManager.DragZoomIn:
                        clearDrawState();
                        clearMeasureState();
                        initDragZoomState(false);
                        break;
                    case MapManager.DragZoomOut:
                        clearDrawState();
                        clearMeasureState();
                        initDragZoomState(true);
                        break;
                    default:
                }
            };
        }
        function StyleManager(){
            var _class = this;
            var defaultStyle = {
                radius:"5",
                picture:null,
                height:16,
                width:16,
                offset_x:8,
                offset_y:16,
                border_width:2,
                border_color:"#ffcc33",
                border_opacity:1,
                fill_color:"rgb(255,204,51,0.7)",
                fill_opacity:1
            };
            var olDefaultStyle = null;
            var onMapLoaded = function(e){
                if(typeof mapStyleTemplates !== "undefined"){
                    _class.mapStyleTemplates = mapStyleTemplates;
                }
                olDefaultStyle = new ol.style.Style({
                    fill : new ol.style.Fill({
                        color:"rgba(255,255,255,0.4)"
                    }),
                    stroke :new ol.style.Stroke({
                        color:"rgba(255,255,255,0.4)"
                    }),
                    image:new ol.style.Circle()
                });
                //
                var size = _this.map.getSize();
                _class.mapWidth = size[0];
                _class.mapHeight = size[1];

            };
            _class.api = _this;
            _class.mapHeight = 0;
            _class.mapWidth = 0;
            _class.mapStyleTemplates = {};
            _class.layerStyles = {};
            _class.drawStyle = function(param){
                var defaultStyleClone = commonUtil.extend({},defaultStyle);
                if( typeof param === "string"  ){
                    return _class.parse(param)
                }else if( param instanceof ol.style.Style){
                    return param;
                }else{
                    var styleParam = commonUtil.extend({},defaultStyleClone,param);
                    var fillColor = ol.color.asArray(styleParam.fill_color);
                    var borderColor = ol.color.asArray(styleParam.border_color);
                    var borderOpacity = parseFloat(styleParam.border_opacity);
                    var fillOpacity = parseFloat(styleParam.fill_opacity);
                    fillColor[3] = fillOpacity;
                    borderColor[3] = borderOpacity;
                    var style = {
                        fill: new ol.style.Fill({
                            color: fillColor
                        }),
                        stroke: new ol.style.Stroke({
                            color: borderColor,
                            lineDash: [2, 3],
                            width: parseFloat(styleParam.border_width)
                        })
                    } ;
                    if(styleParam.picture){
                        var offset_x = parseFloat(styleParam.offset_x);
                        var offset_y = parseFloat(styleParam.offset_y);
                        var height = parseFloat(styleParam.height);
                        var width = parseFloat(styleParam.width);
                        style.image = new ol.style.Icon({
                            anchor: [0.5, 0.5],
                            offset:[offset_x,offset_y],
                            size:[ width , height],
                            src: styleParam.picture
                        });
                    }else{
                        style.image = new ol.style.Circle({
                            radius: styleParam.radius,
                            stroke: new ol.style.Stroke({
                                color: borderColor
                            }),
                            fill: new ol.style.Fill({
                                color: fillColor
                            })
                        })
                    }
                    return new ol.style.Style(style);
                }
            };
            _class.parse = function(styleParam){
                if( !styleParam ) return ;
                if(typeof styleParam ==="string"){
                    var targetStyle =  _class.mapStyleTemplates[styleParam];
                    if( targetStyle){
                        if(targetStyle.image && targetStyle.image  ){
                            var src =  targetStyle.image.getSrc();
                            var url = commonUtil.getApiRootPath(src);
                            targetStyle.image.setSrc(url);
                        }
                        return targetStyle
                    }else{
                        eventManager.publishInfo(_this.Strings.hasNoStyleError + ",style=" + styleParam);
                    }
                    return null;
                }else if(typeof styleParam ==="object"){
                    // var style = {
                    //     fill: new ol.style.Fill({
                    //         color: styleParam.fill_color
                    //     }),
                    //     stroke: new ol.style.Stroke({
                    //         color: styleParam.border_color,
                    //         width: parseFloat(styleParam.border_width)
                    //     })
                    // } ;
                    // if(styleParam.picture){
                    //     style.image = new ol.style.Icon({
                    //         anchor: [0.5, 1],
                    //         offset:[parseFloat(styleParam.offset_x),parseFloat(styleParam.offset_y)],
                    //         size:[ parseFloat(styleParam.height) , parseFloat(styleParam.width)],
                    //         anchorXUnits: 'fraction',
                    //         anchorYUnits: 'pixels',
                    //         src: styleParam.picture
                    //     });
                    // }else{
                    //     style.image = new ol.style.Circle({
                    //         radius: styleParam.radius,
                    //         stroke: new ol.style.Stroke({
                    //             color: styleParam.border_color
                    //         }),
                    //         fill: new ol.style.Fill({
                    //             color: styleParam.fill_color
                    //         })
                    //     })
                    // }
                    //return new ol.style.Style(style);
                    return _class.drawStyle(styleParam);
                }
            };
            _class.startup =  function(){
                _this.subscribe( _this.Events .MapLoadedEvent ,onMapLoaded);
            };
            _class.getBoldStyle = function(s ,lStyle,feature){
                var style = s && s.clone();
                if(!style){
                    if( lStyle && typeof lStyle === "function"){
                        lStyle =  lStyle(feature);
                    }
                    if( lStyle){
                        if(lStyle instanceof Array)
                            style = lStyle[0].clone();
                        else
                            style = lStyle.clone();
                    }
                }
                if(style){
                    if(style.getFill()){
                        var fill = style.getFill();
                        fill.setColor(apiDefaults.defaultHighlightColor);
                    }
                    if(style.getStroke()){
                        var stroke = style.getStroke();
                        var width = stroke.getWidth() + 2;
                        stroke.setWidth(width);
                        stroke.setColor(apiDefaults.defaultHighlightColor);
                    }
                    if(style.getImage()){
                        var image = style.getImage();
                        var scale = image.getScale() * 1.5;
                        image.setScale(scale);
                    }
                }
                return style;
            };
            _class.addMapStyles = function(module,pre){
                for(var k in module){
                    var keyName = pre ? pre + "_" + k : k;
                    _class.addMapStyle(keyName,module[k]);
                }
            };
            _class.addMapStyle = function(name,style){
                if(style instanceof ol.style.Style === false &&  typeof  style !== "function"){
                    _class.addMapStyles(style,name);
                }else if(style instanceof  ol.style.Style || typeof style === "function"){
                    if(!_class.mapStyleTemplates[name]   ){
                        _class.mapStyleTemplates[name] = style;
                    }else{
                        eventManager.publishWarn(_this.Strings.mapStyleConfigError);
                    }
                }else{
                    eventManager.publishError(_this.Strings.mapStyleConfigError);
                }
            };
            _class.randomColor = function(){
                return '#' + Math.floor(Math.random() * 0xffffff).toString(16);
            };
            _class.parseLayerLabelStyle = function(labelStyleConfig ,fieldNameConfig ,outFieldsConfig ,style ,layerId){
                var labelStyleStr = labelStyleConfig;
                var fieldName = fieldNameConfig;
                var outFields = outFieldsConfig;
                var labelStyle = _class.parse(labelStyleStr);
                if(fieldName && outFields && !commonUtil.hasValue(fieldName,outFields.split(",")) ){
                    eventManager.publishError(_this.Strings.hasNoLabelPropertyError + ",layerId=" + layerId + ",缺少属性" + fieldName);
                }
                if(labelStyle){
                    var styleFunc = null;
                    var mergeStyle = null;
                    var styles = [];
                    var textStyle = labelStyle.getText();
                    if(typeof style === "function"){
                        styleFunc = style;
                    }else if(style instanceof ol.style.Style){
                        //style.setText(textStyle);
                        mergeStyle = style;
                    }
                    var styleFunction = function(feature){
                        if(typeof feature !== "undefined") {
                            var text = feature.get && feature.get(fieldName);
                            text && textStyle.setText(text);
                            if(styleFunc){
                                mergeStyle = styleFunc(feature);
                            }
                            if(mergeStyle){
                                mergeStyle.setText(textStyle);
                            }
                        }
                        return mergeStyle;
                    };
                    style = styleFunction;
                }
                return style;
            }
        }
        function LayerManager(){
            var _class = this;
            var _mapConfig = null;
            var _optionalLayerConfig = null;
            var _baseMapLayerConfig = null;
            var _flashLayers = [];
            var defaultGridSet = {
                "tileSize":[256,256],
                "origin":[-180.0,90.0],
                "resolutions":[],
                "matrixIds":[]
            };
            var defaultWmtsConfig = {
                "style":"",
                "format":"image/png",
                "version":"1.0.0"
            };
            var defaultVectorTileConfig = {
                'request': 'GetTile',
                'version': '1.0.0',
                'layer': '',
                'style': '',
                'tilematrix':  '',
                'tilematrixset': '',
                'service': 'WMTS',
                'format': 'application/x-protobuf;type=mapbox-vector',
                'tilecol': '{x}',
                'tilerow': '{y}'
            };
            var createDefaultLoader = function(){

            };
            var parseLayerConfig = function(config){
                try {
                    var minResolution =config.minResolution ? config.minResolution :(config.minScale ? commonUtil.scaleToResolution(config.minScale):undefined) ;
                    var maxResolution =config.maxResolution ? config.maxResolution :(config.maxScale ? commonUtil.scaleToResolution(config.maxScale):undefined ) ;
                    var sourceConfig = {
                        url:"",
                        format:"GeoJSON",
                        strategy:"",
                        serverType:"",
                        params:null
                    };
                    var layerConfig = commonUtil.extend({
                        opacity: 1,
                        visible: true,
                        minResolution: undefined,
                        maxResolution: undefined
                    },{
                        //配置必选
                        id: config.id,
                        type: config.type ? config.type :"Vector",
                        source: config.source ? config.source :"Vector",
                        opacity: config.opacity,
                        visible: config.visible,
                        zIndex: config.index,
                        minResolution: minResolution,
                        maxResolution: maxResolution
                        //style:config.style
                    });
                    if(config.url){
                        sourceConfig.url = config.url;
                    }
                    //format
                    if(config.format){
                        if(config.format === "GeoJSON" ){
                            sourceConfig.format = new ol.format.GeoJSON();
                        }else if(config.format === "WKT"){
                            sourceConfig.format = new ol.format.WKT();
                        }else if(config.format === "EsriJSON"){
                            sourceConfig.format = new ol.format.EsriJSON();
                        }
                    }else{
                        sourceConfig.format = new ol.format.GeoJSON();
                    }
                    //strategy
                    if(config.strategy){
                        if(config.strategy === "all"){
                            sourceConfig.strategy = ol.loadingstrategy.all;
                        }else if(config.strategy === "bbox"){
                            sourceConfig.strategy = ol.loadingstrategy.bbox;
                        }else if(config.strategy === "tile"){ // arcgis feature server
                            sourceConfig.strategy = ol.loadingstrategy.tile(ol.tilegrid.createXYZ({
                                tileSize:512
                            }));
                        }
                    }else{
                        sourceConfig.strategy = ol.loadingstrategy.all;
                    }
                    //serverType
                    if(config.serverType){
                        sourceConfig.serverType = config.serverType;
                    }else{
                        sourceConfig.serverType = "geoserver";
                    }
                    // ?
                    if(config.params){
                        sourceConfig.params = config.params;
                    }
                    // loader (要素服务回调函数）
                    var loader = null;
                    if(config.loader){
                        //通用查询参数
                        if(!sourceConfig.url ){
                            sourceConfig.url = commonUtil.getDefaultLayerQueryUrl(config.id);
                        }
                        if(config.loader === "arcgis"){
                            sourceConfig.url += "/query";
                            config.outFields = config.outFields ? config.outFields.toUpperCase() : "";
                        }
                        if(config.where){
                            var encodeUrl = config.where.replace("=", '%3D');
                            sourceConfig.url = commonUtil.appendUrl( sourceConfig.url ,"where", encodeUrl);
                        }
                        if(config.outFields){
                            sourceConfig.url = commonUtil.appendUrl( sourceConfig.url ,"outFields", config.outFields);
                        }
                        //jasopengis
                        if("jas" === config.loader){
                            var strategy = config.strategy;
                            //添加token参数
                            var token = localStorage.getItem(_this.Keys.token);
                            if(token ){
                                sourceConfig.url  = commonUtil.appendUrl(sourceConfig.url ,_this.Keys.token,token);
                            }
                            loader = function(extent, resolution, projection) {
                                var proj = projection.getCode();
                                var source = this;
                                var url = source.getUrl();
                                if(strategy === "bbox"){
                                    url = commonUtil.appendUrl(url,"geometry", extent.join(','));
                                }
                                if(proj.indexOf(":") >= 0){
                                    var inSR = proj.split(":")[1];
                                    url = commonUtil.appendUrl(url,"inSR", inSR);
                                    url = commonUtil.appendUrl(url,"outSR", inSR);
                                }
                                commonUtil.simpleAjaxLoader({
                                    url: url ,
                                    onSuccess:function(result){
                                        var obj = JSON.parse(result);
                                        source.addFeatures( source.getFormat().readFeatures(obj));
                                    },
                                    onError:function(err){
                                        eventManager.publishError(_this.Strings.layerLoadError,err);
                                        source.removeLoadedExtent(extent);
                                    }
                                });
                            };
                        }else if(config.loader === "arcgis"){ // arcserver loader
                            var url = sourceConfig .url;
                            url  = commonUtil.appendUrl(url,"f", "json");
                            url  = commonUtil.appendUrl(url,"geometryType", "esriGeometryEnvelope");
                            url  = commonUtil.appendUrl(url,"returnGeometry", true);
                            sourceConfig.url  = commonUtil.appendUrl(url,"spatialRel", "esriSpatialRelIntersects");
                            loader = function(extent, resolution, projection) {
                                var projCode = projection.getCode().split(":")[1];
                                var source = this;
                                var url = source.getUrl();
                                var geometry = encodeURIComponent('{"xmin":' + extent[0] + ',"ymin":' +
                                    extent[1] + ',"xmax":' + extent[2] + ',"ymax":' + extent[3] +
                                    ',"spatialReference":{"wkid":'+ projCode +'}}');
                                url  = commonUtil.appendUrl(url,"geometry", geometry);
                                commonUtil.simpleAjaxLoader({
                                    url: url ,
                                    onSuccess:function(result){
                                        var obj = JSON.parse(result);
                                        source.addFeatures( source.getFormat().readFeatures(obj));
                                    },
                                    onError:function(err){
                                        eventManager.publishError(_this.Strings.layerLoadError,err);
                                        source.removeLoadedExtent(extent);
                                    }
                                });
                            }
                        }
                        sourceConfig.loader = loader;
                    }
                    var renderer = config.renderer ?  config.renderer : {};
                    if(renderer.style){
                        layerConfig.style = styleManager.parse(renderer.style);
                    }
                    if(renderer.labelStyle && renderer.labelField){
                        var labelConfig = renderer.labelStyle;
                        var fieldConfig = renderer.labelField;
                        var outFieldsConfig = config.outFields;
                        var layerId = config.id;
                        layerConfig.style = styleManager.parseLayerLabelStyle(labelConfig,fieldConfig,outFieldsConfig,layerConfig.style,layerId);
                    }
                    if(layerConfig.source.toUpperCase() ==="WMTS"){
                        var tileGrid = commonUtil.extend({},defaultGridSet,config.tileGrid);
                        delete config.tileGrid;
                        config = commonUtil.extend({},defaultWmtsConfig,config);
                        var resolutions = tileGrid.resolutions;
                        if(!resolutions || resolutions.length === 0){
                            resolutions = _this.mapConfig.mapOptions.resolutions;
                        }
                        for(var i = 0; i < resolutions.length;i++){
                            var reso = resolutions[i];
                            tileGrid.resolutions.push(reso.resolution);
                            tileGrid.matrixIds.push(reso.matrixId);
                        }
                        sourceConfig.layer = config.layer;
                        sourceConfig.format = config.format;
                        sourceConfig.style = config.style ? config.style :"";
                        sourceConfig.matrixSet = config.tileMatrixSet;
                        sourceConfig.tileGrid = new ol.tilegrid.WMTS(tileGrid);
                    }
                    if(layerConfig.source.toUpperCase() ==="VECTORTILE"){
                        var layer = config.layer;
                        var tileMatrixSet = config.tileMatrixSet;
                        var style = config.style;
                        var params = commonUtil.extend({},defaultVectorTileConfig);
                        params.style = style ? style :"";
                        params.layer = layer;
                        params.tilematrixset = tileMatrixSet;
                        params.tilematrix = tileMatrixSet  + ':{z}';

                        var url = config.url + '?';
                        for (var param in params) {
                            url = url + param + '=' + params[param] + '&';
                        }
                        url = url.slice(0, -1);

                        var tileGrid = commonUtil.extend({},defaultGridSet,config.tileGrid);
                        var resolutions = tileGrid.resolutions;
                        if(!resolutions || resolutions.length === 0){
                            resolutions = _this.mapConfig.mapOptions.resolutions;
                        }
                        for(var i = 0; i < resolutions.length;i++){
                            var reso = resolutions[i];
                            tileGrid.resolutions.push(reso.resolution);
                            tileGrid.matrixIds.push(reso.matrixId);
                        }
                        sourceConfig = {
                            url:url,
                            format:  new ol.format.MVT(),
                            tileGrid:new ol.tilegrid.WMTS(tileGrid),
                            style:params.style,
                            wrapX: true
                        }
                    }
                    if(layerConfig.type.toUpperCase() ==="VECTOR"){
                        layerConfig.declutter = config.declutter === true ? true : false;
                    }
                    //后置处理
                    if(config.listener){
                        _class.onListeners[layerConfig.id] = config.listener;
                    }
                    //保存图层样式
                    if(layerConfig.style){
                        styleManager.layerStyles[layerConfig.id] = layerConfig.style;
                    }
                    return {
                        layerConfig:layerConfig,
                        sourceConfig:sourceConfig
                    };
                } catch (e) {
                    eventManager.publishError(_this.Strings.parseLayerConfigError + "layerId=" + config.id ,e);
                }
            };
            var createBaseMapLayers = function(){
                if(_baseMapLayerConfig) {
                    var layerShow = false ;
                    var baseMapLayers = _baseMapLayerConfig.baseMapLayers;
                    if (baseMapLayers && baseMapLayers.length > 0) {
                        for (var i = 0; i < baseMapLayers.length; i++) {
                            var baseLayerConfig = baseMapLayers[i];
                            var layerVisible = ( (layerShow === false && ( baseLayerConfig.visible===undefined || baseLayerConfig.visible === true ) )? true :false);
                            baseLayerConfig.icon && (baseLayerConfig.icon = commonUtil.getApiRootPath(baseLayerConfig.icon));
                            baseLayerConfig.visible = layerVisible;
                            layerVisible && ( layerShow = true);
                            var layer = null;
                            if(baseLayerConfig.layerSet && baseLayerConfig.layerSet.length > 0){
                                var subLayers = [];
                                for(var j = 0 ; j < baseLayerConfig.layerSet.length;j++ ){
                                    var subBaseLayer = baseLayerConfig.layerSet[j];
                                    if(!subBaseLayer.id){
                                        subBaseLayer.id = baseLayerConfig.id + "_" + j;
                                    }
                                    subBaseLayer.visible = baseLayerConfig.visible;
                                    layer = _class.createLayer(subBaseLayer);
                                    _this.addLayer(layer);
                                    subLayers.push(layer);
                                }
                                baseLayerConfig.layers = subLayers;
                                _class.baseMapLayers.push(baseLayerConfig);
                            }else{
                                layer = _class.createLayer(baseLayerConfig);
                                _this.addLayer(layer);
                                _class.baseMapLayers.push(layer);
                            }
                        }
                        if(!layerShow){
                            //没有显示的底图 ，默认显示第一个
                            var baseLayerConfig =  baseMapLayers[0];
                            _this.switchBaseMap(baseLayerConfig.id)
                        }
                    }
                }
            };
            var createOptionalLayers = function(){
                var layersConfig = [];
                if(_optionalLayerConfig ){
                    for(var i = 0 ; i < _optionalLayerConfig.length ; i++){
                        var item = _optionalLayerConfig[i];
                        commonUtil.mapLayerSetData(item,function(layer,layerSet){
                            if(!layer.layerSet){
                                layersConfig.push(layer);
                            }
                        });
                    }
                    for(var j = 0 ; j < layersConfig.length ;j++){
                        var layerConfig = layersConfig[j];
                        var layer = _class.createLayer(layerConfig);
                        if(layerConfig && layerConfig.flash === true){
                            _flashLayers.push(layer);
                        }
                        _class.optionalLayers.push(layer);
                        _this.addLayer(layer);
                    }
                    eventManager.publishEvent(_this.Events.OptionalLayersLoaded ,{
                        "flashLayers":_flashLayers,
                        "layers":_class.optionalLayers
                    })
                }
            };
            var parseLayerConfigs = function(){
                if( _baseMapLayerConfig.baseMapLayers && _baseMapLayerConfig.baseMapLayers.length > 0){
                    for(var i = 0 ; i < _baseMapLayerConfig.baseMapLayers.length ; i++ ){
                        var baseLayer = _baseMapLayerConfig.baseMapLayers[i];
                        baseLayer.icon = commonUtil.getApiRootPath(baseLayer.icon);
                    }
                }
                if(_optionalLayerConfig && _optionalLayerConfig.length > 0 ){
                    for(var j = 0 ; j < _optionalLayerConfig.length > 0 ; j++ ){
                        var opLayer = _optionalLayerConfig[j];
                        commonUtil.mapLayerSetData(opLayer,function(layer,layerSet){

                        });
                    }
                }
            };
            var onModulesLoaded = function(e){
                createBaseMapLayers();
                createOptionalLayers();
            };
            var onConfigLoaded = function(e){
                _mapConfig = e.data.map;
                _optionalLayerConfig = _mapConfig.optionallayers;
                _baseMapLayerConfig = _mapConfig.basemaps;
                parseLayerConfigs();
            };

            _class.optionalLayers = [];
            _class.baseMapLayers = [];
            _class.initLayersVisible = null;
            _class.onListeners = {};
            _class.startup = function(){
                if(apiDefaults.layersVisible){
                    _class.initLayersVisible = apiDefaults.layersVisible;
                }
                _this.subscribe( _this.Events .ConfigLoadedEvent ,onConfigLoaded);
                _this.subscribe( _this.Events .ModulesLoadedEvent ,onModulesLoaded);
            };
            _class.createLayer = function(params){
                var config = parseLayerConfig(params);
                var layerConfig = config.layerConfig;
                var sourceConfig = config.sourceConfig;
                var type = layerConfig.type ;
                var source = layerConfig.source;
                delete layerConfig.type;
                delete layerConfig.source;
                var url = sourceConfig.url;
                var format = sourceConfig.format ;
                var strategy = sourceConfig.strategy ;
                var serverType = sourceConfig.serverType ;
                var layerSource = null;
                var layer = null;
                switch(type){
                    case "Tile":
                        sourceConfig.crossOrigin = 'Anonymous';
                        if("TileArcGISRest" === source){
                            layerSource = new ol.source.TileArcGISRest({
                                url: url
                            });
                        }else if("OSM" === source){
                            layerSource = new ol.source.OSM();
                        }else if("TileWMS" === source){
                            layerSource = new ol.source.TileWMS({
                                url: url,
                                serverType: serverType ? serverType : 'geoserver',
                                params: sourceConfig.params
                            });
                        }else if("WMTS" === source){
                            delete sourceConfig.serverType;
                            delete sourceConfig.params;
                            delete sourceConfig.strategy;
                            layerSource = new ol.source.WMTS(sourceConfig);
                        }else if("tdt"=== source){
                            var layerType = params.layer + "_c";
                            layerSource = createTdtLayerSource(layerType);
                        }
                        layerConfig.source = layerSource;
                        layer = new ol.layer.Tile(layerConfig);
                        break;
                    case "Vector":
                        if( !source || "Vector" === source){
                            if(url && "" !== url){
                                layerSource = new ol.source.Vector({
                                    url:url,
                                    format:format,
                                    strategy:strategy
                                });
                            }else{
                                layerSource = new ol.source.Vector({
                                    format:format
                                });
                            }
                            if(sourceConfig.loader){
                                layerSource.setLoader(sourceConfig.loader);
                            }
                        }
                        layerConfig.source = layerSource;
                        layer = new ol.layer.Vector(layerConfig);
                        break;
                    case "VectorTile":
                        sourceConfig.crossOrigin = 'Anonymous';
                        if("VectorTile" === source){
                            layerSource = new ol.source.VectorTile(sourceConfig);
                        }
                        layerConfig.source = layerSource;
                        layer = new ol.layer.VectorTile(layerConfig);
                        break;

                    default:
                        eventManager.publishInfo( _this.Strings.hasNoLayerTypeError + ",layerId=" + layerConfig.id  );
                }
                return layer;
            };
            _class.writeFeatureId = function(feature){
                var featureId = feature.getId();
                var dotIndex = featureId.indexOf ? featureId.indexOf("."):-1;
                var id = "";
                if(dotIndex >= 0 ){
                    id = featureId.substring( dotIndex + 1);
                }               var attributes = feature.getProperties();
                attributes.id = id;
            };
            _class.getLayerIdByFeature = function(feature){
                var fId = feature.getId();
                var dotIndex = fId.indexOf ? fId.indexOf(".") : -1 ;
                return dotIndex >= 0 ? fId .substring(0,dotIndex) : "";
            };
            //创建天地图图层
            var createTdtLayerSource = function(type){
                //
                //var type = 'vec_c';
                var proj = _this.map.getView().getProjection();
                //
                var source =  new ol.source.XYZ({
                    url: 'http://t{0-7}.tianditu.com/DataServer?T='+type+'&x={x}&y={y}&l={z}',
                    projection: proj
                }) ;
                return source;
            };
        }
        function EventManager(){
            var _class = this;
            //事件机制这里用了自定义事件
            var _eventObject = {
                _listeners: {},
                // 添加
                addEvent: function(type, fn) {
                    if (typeof _eventObject._listeners[type] === "undefined") {
                        _eventObject._listeners[type] = [];
                    }
                    if (typeof fn === "function") {
                        _eventObject._listeners[type].push(fn);
                    }
                    return _eventObject;
                },
                // 触发
                fireEvent: function(type,e) {
                    var arrayEvent = _eventObject._listeners[type];
                    if (arrayEvent instanceof Array) {
                        for (var i=0, length=arrayEvent.length; i<length; i+=1) {
                            if (typeof arrayEvent[i] === "function") {
                                arrayEvent[i].apply(this,[{ type: type ,data:e }]);
                            }
                        }
                    }
                    return _eventObject;
                },
                // 删除
                removeEvent: function( fn ,type ) {
                    if(fn && type){
                        var arrayEvent = _eventObject._listeners[type];
                        if (typeof type === "string" && arrayEvent instanceof Array) {
                            if (typeof fn === "function") {
                                //清除当前type类型事件下对应fn方法
                                for (var i=0, length=arrayEvent.length; i<length; i+=1){
                                    if (arrayEvent[i] === fn){
                                        _eventObject._listeners[type].splice(i, 1);
                                        break;
                                    }
                                }
                            } else {
                                //如果仅仅参数type,则所有type类型事件清除
                                delete _eventObject._listeners[type];
                            }
                        }
                    }else if(fn && typeof  fn === "function"){
                        for(var key in  _eventObject._listeners){
                            var listenerArr = _eventObject._listeners[key];
                            for(var i = 0 ; i < listenerArr.length ; i++){
                                if(listenerArr[i] === fn){
                                    _eventObject._listeners[type].splice(i, 1);
                                    break;
                                }
                            }
                        }
                    }
                    return _eventObject;
                }
            };
            _class.startup = function(){
                //_eventObject = new ol.Object();

            };
            _class.log = function(msg){
                console.log(msg);
            };
            _class.time = function(msg){
                console.time(msg);
            };
            _class.timeEnd = function(msg){
                console.timeEnd(msg);
            };
            _class.publishInfo = function(msg){
                if(msg){
                    console.log(msg);
                }
                _class.publishEvent( _this.Events .ErrorEvent , { message:msg , type:"info"});
            };
            _class.publishWarn = function(msg ,e){
                if(msg){
                    console.warn(msg);
                }
                if(e){
                    console.warn(e);
                }
                _class.publishEvent( _this.Events .ErrorEvent , { message:msg , type:"warn"});
            };
            _class.publishError = function(msg ,e){
                if(msg){
                    console.error(msg);
                }
                if(e){
                    console.error(e);
                }
                _class.publishEvent( _this.Events .ErrorEvent , { message:msg , type:"error"});
            };
            /**
             *
             * @param eventType
             * @param target
             * @param defer
             */
            _class.publishEvent = function(eventType,target,defer){
                if(defer)
                    setTimeout(function(){
                        _eventObject.fireEvent.apply(_this,[eventType,target]);
                    },defer);
                else
                    _eventObject.fireEvent.apply(_this,[eventType,target]);
            };
            /**
             * @param eventType String
             * @param listener Function
             */
            _class.registerEvent = function(eventType,onListenerFunc){
                if(typeof onListenerFunc === "function")
                    _eventObject.addEvent(eventType,onListenerFunc);
            };
            _class.destroyEventHandler = function(handler){
                if(Array.isArray(handler)){
                    for(var i = 0 ; i < handler.length ; i++){
                        _eventObject.removeEvent(handler[i]);
                    }
                }
                else if(handler){
                    _eventObject.removeEvent(handler);
                }
            };
        }
        function ModuleManager(){
            var _class = this;
            _class.apiConfig = null;
            _class.modulesConfig = null;
            _class.modules = [];
            _class.moduleIds = [];
            _class.loadedModules = [];
            var getModuleConfigById = function(id){
                for(var i = 0 ; i < _class.modulesConfig.length ;i++){
                    if(id ===_class. modulesConfig[i].id){
                        return _class.modulesConfig[i];
                    }
                }
                return null;
            };
            var parseConfigs = function(){
                //解析相关配置
                var checkResult = checkModuleConfigs();
                try {
                    if(!checkResult){
                        throw(_this.Strings.moduleConfigError);
                    }
                    //解析module配置
                    for (var i = 0; i <  _class.modulesConfig.length; i++) {
                        var obj = _class.modulesConfig[i];
                        var conf = parseModuleConfig(obj);
                        _class.modules.push(conf);
                        // if(obj.controller == true){
                        //     controllerModulesConfig.push(conf);
                        // }else{
                        //     modulesConfig.push(conf);
                        // }
                    }
                    //解析controller里的moduleSet
                    // for (i = 0; i < controllerModulesConfig.length; i++) {
                    //     var module = controllerModulesConfig[i];
                    //     for(var j = 0 ; j < module.moduleSet.length ; j++){
                    //         var mConf = module.moduleSet[j];
                    //         if(mConf.type === "module"){
                    //             var m = getModuleConfigById(mConf.target);
                    //             mConf = lang.mixin(m,mConf);//!
                    //             module.moduleSet[j] = mConf;
                    //         }
                    //     }
                    // }
                }catch(e){
                    eventManager.publishError(_this.Strings.moduleConfigError,e);
                }
            };
            var checkModuleId = function(moduleId){
                for(var i = 0 ; i < _class.moduleIds.length ; i++){
                    var id = _class.moduleIds[i];
                    if(id === moduleId)
                        return false;
                }
                return true;
            };
            var checkModuleConfigs = function(){
                var controlModules = [];
                for (var i = 0; i < _class.modulesConfig.length; i++) {
                    var obj = _class.modulesConfig[i];
                    if( obj.id ){
                        var idExist = checkModuleId(obj.id);
                        if(!idExist){
                            eventManager.publishError(_this.Strings.repeatIdError + ", id=" + obj.id);
                            return false ;
                        }
                        obj.type || ( obj.type="module" );
                        if(obj.controller === true){
                            obj.moduleSet || ( obj.moduleSet=[] );
                            controlModules.push(obj);
                        }
                        obj.container || (obj.container = "map");
                        obj.icon && ( obj.icon = commonUtil.getApiRootPath(obj.icon) );
                        _class.moduleIds.push(obj.id );
                    }else{
                        eventManager.publishError(_this.Strings.moduleConfigError);
                        return false
                    }
                }
                for( i = 0 ; i < controlModules.length ; i++){
                    var controller = controlModules[i];
                    for(var j = 0 ; j < controller.moduleSet.length ; j++){
                        var m = controller.moduleSet[j];
                        idExist = checkModuleId(m);
                        if(!idExist){
                            eventManager.publishError(_this.Strings.hasNoIdError + ",id=" + m.target);
                            return false ;
                        }
                        m.icon && ( m.icon = commonUtil.getApiRootPath(m.icon) );
                    }
                }
                return true;
            };
            var parseModuleConfig = function(conf){
                var defaults = {
                    "id":null,
                    "label":null,
                    "module":null,//函数名称
                    "baseClass":"BaseMapModule",
                    //"url":"",//?
                    "icon":null,
                    "template":null,//string？#divId？url？
                    "container":"map",//html,#divId
                    "style":null,
                    "controller":false,//control module id
                    "moduleSet":[],
                    "options":null//模块自己的配置
                };
                return commonUtil.extend(defaults,conf);
            };
            var loadModules = function(){
                try{
                    if( _class.modules.length === 0) {
                        //
                        return ;
                    }
                    if( typeof BaseMapModule === "undefined"){
                        throw ( _this.Strings.moduleReferError);
                    }
                    //先加载普通module
                    for( var i = 0 ; i < _class.modules.length ; i++ ){
                        var m = _class.modules[i];
                        if(m.type==="module" && m.controller!==true){
                            _class.loadModule(_class.modules[i]);
                        }
                    }
                    //再加载controller
                    for( i = 0 ; i < _class.modules.length ; i++){
                        m = _class.modules[i];
                        if(m.type==="module" && m.controller===true){
                            _class.loadModule(_class.modules[i]);
                        }
                    }
                    for( i = 0 ; i < _class.loadedModules.length ; i++){
                        var mo = _class.loadedModules[i];
                        mo.startup();
                    }
                }catch(e){
                    eventManager.publishError( _this.Strings.moduleCreateError,e);
                }
            };
            var onConfigLoaded = function(e){
                _class.apiConfig = e.data;
                _class.modulesConfig = e.data.modules ? e.data.modules :[];
                parseConfigs();//解析module配置
            };
            var onMapLoaded = function(e){
                eventManager.time( _this.Strings.modulesLoading);
                loadModules();//初始化modules
                eventManager.timeEnd( _this.Strings.modulesLoading);
                eventManager.publishEvent( _this.Events .ModulesLoadedEvent,_class.modules);
            };
            var onModuleLoaded = function(e){
                var module = e.data.target;
                module.startup && module.startup();
            };
            _class.getContainerDom = function(container){
                if(container==="map"){
                    return _this.map.getTargetElement();
                }else if(container === "html" || container === "body" ){
                    return document.body;
                }else if(container.indexOf("#") >=0 ){
                    var domId = container.substring(1);
                    return document.getElementById(domId);
                }
                return null;
            };
            var require = function(){
                var length = arguments.length ;
                if(length !== 1 ){

                }
                if(typeof  arguments[0] === "function"){
                    var module = arguments[0].apply(styleManager);
                    //这先只处理样式模版
                    styleManager.addMapStyles(module);
                }
                return null;
            };
            _class.loadModule = function(conf){
                //var deferred = new Deferred();
                var module = null;
                var ModuleClass = null;
                if(conf.class){
                    try{
                        ModuleClass = eval(conf.class);
                        if(typeof ModuleClass !== "function"){
                            throw( _this.Strings.moduleClassNotFoundError);
                        }
                        ModuleClass.prototype = new BaseMapModule();//
                        module = new ModuleClass(conf.options);
                        delete conf.options;
                        module.mapApi = _this;
                        module.id = conf.id;
                        conf.label && ( module.label = conf.label );
                        conf.baseClass && ( module.baseClass = conf.baseClass );
                        conf.icon && ( module.icon = commonUtil.getApiRootPath( conf.icon ));
                        if(conf.template && conf.template.indexOf(".html") >= 0){
                            alert("开发中");
                        }
                        conf.style && ( module.style = conf.style );
                        ( conf.moduleSet && conf.moduleSet.length > 0 ) && ( module.moduleSet = conf.moduleSet );
                        module.container = _class.getContainerDom( conf.container );

                        if( module && conf.enable!==false ){
                            module.domCreate();
                            _class.loadedModules.push(module);
                        }
                    }catch(e){
                        module && module.destroy();
                        eventManager.publishError( _this.Strings.moduleCreateError +" ,moduleId=" + conf.id ,e);
                    }
                }
                //return deferred.promise();
            };
            _class.startup = function(){
                _this.subscribe( _this.Events .ConfigLoadedEvent ,onConfigLoaded);
                _this.subscribe( _this.Events .MapLoadedEvent ,onMapLoaded);
                _this.subscribe( _this.Events .ModuleInitEvent ,onModuleLoaded);
                JasMap.require = require;
            };
            _class.getModuleById = function(id){
                for(var i = 0 ; i < _class.loadedModules.length ; i++){
                    if( _class.loadedModules[i].id === id){
                        return _class.loadedModules[i];
                    }
                }
                eventManager.publishError(_this.Strings.moduleNotFound + ",id=" + id);
                return null;
            };
        }
        function ConfigManager(apiOptions){
            var _class = this;
            var apiScript = null;
            var _config = null;
            var parseConfig = function(){
                var appName = _config.appName ;
                if(appName){
                    document.title = appName;
                }
            };
            _class.startup = function(){
                apiScript = document.getElementById(apiOptions.appScriptId);
                basePath = getBasePath();
                var configPath = getMapConfigPath();//读取data-config
                if(configPath) {  //加载配置
                    loadConfig(configPath,function(conf){
                        var resources = conf.resources;
                        if ( resources && resources.length > 0) {
                            eventManager.time( _this.Strings.dependenceLoading);
                            //load map style.css
                            if(conf.mapStyle){
                                var styleUrl = "basepath:styles/"+ conf.mapStyle +"/style.css";
                                var styleCss = { "type":"css","url":styleUrl};
                                resources .push(styleCss);
                            }
                            loadResources(resources, function(type){

                            }, function(){
                                console.info("地图资源加载完成：",arguments[0]);
                            }, function () {
                                _config = conf;
                                parseConfig();
                                eventManager.timeEnd( _this.Strings.dependenceLoading);
                                eventManager.publishEvent( _this.Events .ConfigLoadedEvent,conf);
                            });
                        }else{
                            eventManager.publishEvent( _this.Events .ConfigLoadedEvent,conf);
                        }
                    });
                }else{
                    eventManager.publishError( _this.Strings.configUrlError);
                }
            };
            function loadConfig(url,onSuccess,onError){
                eventManager.time( _this.Strings.configLoading);
                commonUtil.simpleAjaxLoader({
                    url:url,
                    method:'get',
                    onSuccess:function (responseText) {
                        eventManager.timeEnd(  _this.Strings.configLoading);
                        var conf = {};
                        try {
                            var result = JSON.parse(responseText);
                            if(apiOptions && apiOptions.appName){
                                //多个app配置
                                conf = result[apiOptions.appName];
                            }else
                                conf = result;
                            //
                            if(apiOptions["mapStyle"] !== "default"){
                                conf["mapStyle"] = apiOptions["mapStyle"];
                            }else{
                                if( !conf["mapStyle"]){
                                    conf["mapStyle"] = apiOptions["mapStyle"];
                                }
                            }
                            if (conf.dojoConfig) { // loadResources之前定义
                                global.dojoConfig = conf.dojoConfig;
                            }
                            if(onSuccess && typeof onSuccess === "function"){
                                onSuccess(conf);
                            }
                        } catch (e) {
                            eventManager.timeEnd( _this.Strings.configLoading);
                            eventManager.publishError( _this.Strings.parseConfigError,e);
                        }
                    },
                    onError:function(err){
                        eventManager.timeEnd(  _this.Strings.configLoading);
                    }
                });
            }
            function loadResources( ress, onOneBeginLoad, onOneLoad, onLoad){
                var loaded = [];
                var relys = {};
                function _onOneLoad(url,id){
                    if(loaded.indexOf(url) > -1){
                        return;
                    }
                    loaded.push(url);
                    if(onOneLoad){
                        onOneLoad(url, loaded.length);
                    }
                    if(relys[id]){
                        var arrs = relys[id];
                        for(var i = 0 ; i < arrs.length; i++ ){
                            if(arrs[i].url){
                                loadResource(arrs[i].type, arrs[i].url, onOneBeginLoad, _onOneLoad,arrs[i].id);
                            }
                        }
                    }
                    if(loaded.length === ress.length){
                        if(onLoad){
                            onLoad();
                        }
                    }
                }
                for(var i = 0; i < ress.length; i ++){
                    var re = ress[i];
                    //如果配置了依赖，先加入数组，不加载
                    if(re.relyOn){
                        if(!relys[re.relyOn]){
                            relys[re.relyOn] = [];
                        }
                        relys[re.relyOn].push(re);
                    } else if(re.url){
                        //被依赖的必须要有Id
                        loadResource(re.type, re.url, onOneBeginLoad, _onOneLoad,re.id);
                    }
                }
            }
            function loadResource(type, url, onBeginLoad, onLoad,id){
                url = commonUtil.getApiRootPath(url);
                if(onBeginLoad){
                    onBeginLoad(type);
                }
                if(type === 'css'){
                    loadCss(url);
                    //}else if(type==="js"){
                }else{
                    loadJs(url);
                }
                function createElement(config) {
                    var e = document.createElement(config.element);
                    for (var i in config) {
                        if (i !== 'element' && i !== 'appendTo') {
                            e[i] = config[i];
                        }
                    }
                    var root = document.getElementsByTagName(config.appendTo)[0];
                    return (typeof root.appendChild(e) === 'object');
                }
                function loadCss(url) {
                    var result = createElement({
                        element: 'link',
                        rel: 'stylesheet',
                        type: 'text/css',
                        href: url,
                        onload: elementLoaded.bind(this, url),
                        appendTo: 'head'
                    });
                    var ti = setInterval(function() {
                        var styles = document.styleSheets;
                        for(var i = 0; i < styles.length; i ++){
                            if(styles[i].href && styles[i].href.substr(styles[i].href.indexOf(url), styles[i].href.length) === url){
                                clearInterval(ti);
                                elementLoaded(url);
                            }
                        }
                    }, 500);
                    return (result);
                }
                function loadJs(url) {
                    var result = createElement({
                        element: 'script',
                        type: 'text/javascript',
                        onload: elementLoaded.bind(this, url),
                        onreadystatechange: elementReadyStateChanged.bind(this, url),
                        src: url,
                        appendTo: 'body'
                    });
                    return (result);
                }
                function elementLoaded(url){
                    if(onLoad){
                        onLoad(url,id);
                    }
                }
                function elementReadyStateChanged(url){
                    if (this.readyState === 'loaded' || this.readyState === 'complete') {
                        elementLoaded(url);
                    }
                }
            }
            function getMapConfigPath(){
                // if(apiScript){
                //     var path = apiScript.getAttribute("dataaccess-config");
                //     if(path)
                //         return path;
                // }
                return commonUtil.getApiRootPath(apiDefaults.appConfig);
            }
            function getBasePath(){
                if(apiScript){
                    var path = apiScript.getAttribute("src");
                    var base = document.location.pathname;
                    var lastIndex = base.lastIndexOf("/");
                    base = base .substring(0,lastIndex) ;
                    path = commonUtil.subUrl(base,path);
                    var lastSpritIndex = path.lastIndexOf("/");
                    //var index = path.indexOf("mapjs4ol.js");
                    if(lastSpritIndex >= 0){
                        return path.substring(0, lastSpritIndex);
                    }
                    return path;
                }
            }
            function getMapOptions(){
                var options = null;
                if(apiScript) {
                    try {
                        options = JSON.parse(apiScript.getAttribute("dataaccess-options"));
                    }catch(e){
                        console.error(e);
                        console.error("解析data-options出错！");
                    }
                }
                return options;
            }
        }
        function CommonUtil(){
            var _class = this;
            _class.mapLayerSetData  = function(conf,mapFunc,parent){// 遍历
                if(mapFunc && typeof mapFunc==="function"){
                    mapFunc(conf,parent);
                }
                if(conf.layerSet && conf.layerSet.length > 0){
                    var layerSet = conf.layerSet ;
                    for(var i = 0 ; i < layerSet.length ; i++){
                        var c = layerSet[i];
                        _class.mapLayerSetData( c ,mapFunc ,conf);
                    }
                }
            };
            _class.extend = function() {
                //var destination = JSON.parse(JSON.stringify(target));
                var isObjFunc = function(name) {
                    var toString = Object.prototype.toString;
                    return function() {
                        return toString.call(arguments[0]) === '[object ' + name + ']'
                    }
                };
                var isObject = isObjFunc('Object'),
                    isArray = isObjFunc('Array');

                var obj,copy,i;
                for(i = arguments.length - 1;i > 0;i--) {
                    var destination = arguments[i - 1];
                    var source = arguments[i];
                    if(isObject(source) || isArray(source)) {
                        for(var property in source) {
                            obj = source[property];
                            if(  isObject(obj) || isArray(obj)  ) {
                                copy = isObject(obj) ? {} : [];
                                var extended = _class.extend(copy,obj);
                                destination[property] = extended;
                            }else if(source[property]!= null || source[property] != undefined){
                                destination[property] = source[property]
                            }
                        }
                    } else if(source) {
                        destination = source;
                    }
                }
                return destination

            };
            _class.getApiRootPath = function(url){
                var result = url;
                if(url.indexOf("basepath:") === 0){
                    url = url.replace("basepath:","").trim();
                    result = commonUtil.subUrl(basePath, url);
                }else if(url.indexOf("stylepath:") === 0 ){
                    var mapStyle = _this.apiConfig.mapStyle ;
                    url = url.replace("stylepath:","").trim();
                    result = commonUtil.subUrl(basePath + "/styles/" + mapStyle +"/" , url);
                }
                return result.trim();
            };
            _class.subUrl =function(path ,url){
                if(path.lastIndexOf("/")=== path.length - 1){
                    path = path.substring(0,path.lastIndexOf("/"));
                }
                while(url.indexOf("../") === 0){
                    url = url.substring(3);
                    path = path.substring(0,path.lastIndexOf("/"));
                }
                return path + "/" + url;
            };
            _class.simpleAjaxLoader = function(op){
                var options = {
                    url:"",
                    data:null,
                    method:"post",
                    async:true,
                    onSuccess:function(){

                    },
                    onError:function(){
                        eventManager.publishError(arguments[0]);
                    }
                };
                options = _class.extend(options,op);
                var xmlHttp = null;
                if (window.XMLHttpRequest) {// IE7+, Firefox, Chrome, Opera, Safari 代码
                    xmlHttp = new XMLHttpRequest();
                }else{// IE6, IE5 代码
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                var url = options.url;
                var formData = options.data;
                var method = options.method ;
                var onSuccess = options.onSuccess ;
                var onError = options.onError ;
                var async = options.async ;
                xmlHttp.onreadystatechange = function(){
                    if(arguments[0] && arguments[0].target){
                        xmlHttp = arguments[0].target;
                    }else{//ie 8
                        xmlHttp = arguments.caller;
                    }
                    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                        onSuccess(xmlHttp.responseText);
                    }
                    if(xmlHttp.readyState === 4 && xmlHttp.status !== 200){
                        onError(xmlHttp.responseText);
                    }
                };
                xmlHttp.open(method,url,async);
                //xmlHttp.setRequestHeader("Content-type","application/json");
                xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlHttp.send(formData ? formData : null);
            };
            _class.scaleToResolution = function(scale){
                var espg = _this.map.getView().getProjection().getCode();
                if(espg==="EPSG:4490" || espg==="EPSG:4326"){
                    return   ( scale * 360 * 0.0254 ) /( 96 * 2 * Math.PI * 6378137);
                }else {
                    //(espg==="EPSG:3857")
                    //1:scale = 1 : (96 * Resolution / 0.0254)
                    return  scale * 0.0254 / apiDefaults.dpi;
                }
            };
            _class.resolutionToScale = function(reso){
                var espg = _this.map.getView().getProjection().getCode();
                if(espg==="EPSG:4490" || espg==="EPSG:4326"){
                    return  reso * apiDefaults.dpi * 2 * Math.PI * 6378137/(360 * 0.0254);
                }else {
                    //(espg==="EPSG:3857")
                    //1:scale = 1 : (96 * Resolution / 0.0254)
                    return  reso * apiDefaults.dpi / 0.0254;
                }
            };
            _class.appendUrl = function(url, fieldName , fieldValue){
                if(url.indexOf("?")<0){
                    url += "?";
                }
                if(url.lastIndexOf("&")!== url.length-1 && url.substring(url.length-1) !== "?" ){
                    url += "&";
                }
                url += (fieldName + "=" + fieldValue);
                return url ;
            };
            _class.getDefaultLayerQueryUrl = function(layerId){
                var projectPath = apiDefaults.projectPathName;
                if(!projectPath){
                    var pathname = document.location.pathname;
                    if(pathname.lastIndexOf("/") === 0){
                        projectPath = "";//路径没有项目名称
                    }else{
                        pathname = pathname.substring(1);
                        projectPath = pathname.substring(0,pathname.indexOf("/"));
                    }
                }
                return  document.location.origin + "/" + projectPath + "/jasgis/" + layerId +"/query.do";
            };
            _class.getDefaultMapQueryUrl = function(){
                var projectPath = apiDefaults.projectPathName;
                if(!projectPath){
                    var pathname = document.location.pathname;
                    if(pathname.lastIndexOf("/") === 0){
                        projectPath = "";//路径没有项目名称
                    }else{
                        pathname = pathname.substring(1);
                        projectPath = pathname.substring(0,pathname.indexOf("/"));
                    }
                }
                return  document.location.origin + "/" + projectPath + "/jasgis/query.do";
            };
            _class.hasValue = function(target,obj){
                for(var key in obj){
                    var value = obj[key];
                    if(value === target){
                        return true;
                    }
                }
                return false;
            };
            _class.buildCircleGeometry = function(centerX ,centerY ,radius ){
                return "CIRCLE(" + centerX + "," + centerY + "," + radius + ")";
            };
            _class.dataURLtoBlob = function(dataurl) {
                var arr = dataurl.split(','),
                    mime = arr[0].match(/:(.*?);/)[1],
                    bstr = atob(arr[1]),
                    n = bstr.length, u8arr = new Uint8Array(n);
                while(n--){
                    u8arr[n] = bstr.charCodeAt(n);
                }
                return new Blob([u8arr], {type:mime});
            }

        }

        startup();
    };

})(window);
