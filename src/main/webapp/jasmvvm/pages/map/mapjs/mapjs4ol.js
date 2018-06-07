/**
 * Created by kc on 2018/1/19.
 * @version
 * @description
 * @version jasopengis-v-1.0.0
 * @lastUpdate 2018/06/04
 **/

var Constants = {
    "events": {
        "ErrorEvent": "ErrorEvent",//
        "MapLoadedEvent": "MapLoadedEvent",
        "BaseMapLayersLoaded": "BaseMapLayersLoaded",
        "OptionalLayersLoaded": "OptionalLayersLoaded",
        "ModulesLoadedEvent": "ModulesLoaded",
        "ModuleStartupEvent": "ModuleStartupEvent",
        "ModuleInitEvent": "ModuleInitEvent",
        "OptionalLayerAddedEvent": "OptionalLayerAddedEvent",
        "ModuleStateChanged": "ModuleStateChanged",
        "ConfigLoadedEvent": "ConfigLoaded"
    },
    "strings": {
        "apiLoading": "api初始化",
        "mapLoading": "地图初始化",
        "modulesLoading":"加载模块",
        "configLoading": "加载配置文件",
        "dependenceLoading": "加载相关依赖",
        "appNameConfigError": "appName配置错误",
        "parseConfigError": "地图配置解析失败,请检查配置数据格式是否正确！",
        "configUrlError": "js脚本标签没有配置data-config属性",
        "moduleClassNotFoundError": "该地图控件类没有定义",
        "moduleConfigError": "地图控件配置出错",
        "moduleReferError": "地图控件依赖文件modules.js没有加载",
        "moduleCreateError": "地图控件创建出错",
        "moduleNotFound": "地图控件没有找到",
        "moduleLoaded": "地图控件加载成功",
        "createLayerError": "图层创建失败，请检查配置！",
        "layerIdRepeatError": "图层创建失败，id已经存在！",
        "hasNoLayerTypeError": "无法创建的图层类型",
        "layerNotLoaded": "该图层未加载",
        "layerUrlNotNull": "该类型图层url不能为空",
        "layerLoadError": "图层加载出现错误，请检查网络！",
        "eventNotRegister": "事件没有注册，回调函数无法执行",
        "graphicCreateError": "图形创建出错 ，请检查数据结构",
        "getDistanceError": "计算距离出错！",
        "getAreaError": "计算面积出错！",
        "invalidFlashData": "无效的闪烁规则",
        "featureNotFound": "没有查询到目标",
        "queryError": "查询出错",
        "layerSetNotFound": "没有找到对应的layerSet",
        "repeatIdError": "重复ID",
        "hasNoIdError": "ID不存在",
        "hasNoProj4js": "自定义投影需要引入proj4.js",
        "hasNoConfigDataError":"配置不存在"
    }
};
/**
 *
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
        String.prototype.trim = function ()
        {
            return this.replace(/(^\s*)|(\s*$)/g, "");
        }
    }
    //功能拓展
    ///<<---浏览器兼容处理---
    ///---定义地图框架--->>
    JasMap = M = function(options){
        var _this = this;
        var basePath = "" ;
        var apiDefaults = {
            appScriptId:"mapApi",
            appConfigPath:"config.json",
            appName:"",
            duration:500,
            drawLayerId:"overLayer",
            drawLayerIndex:1000,
            defaultZoomLevel:10,
            defaultZoomScale:50000,
            defaultHighlightColor:[ 255,0,255,1],
            onConfigLoaded:function(e){

            },
            onMapLoaded:function(e){
                _this.addVectorLayer(apiDefaults.drawLayerId ,{ index: apiDefaults.drawLayerIndex});//创建默认的标绘图层
            },
            onError:function(){

            },
            onModuleStartup:function(e){
                console.info(e);
            }
        };
        var commonUtil = _this.commonUtil = new CommonUtil();
        apiDefaults = commonUtil.extend(apiDefaults,options);

        _this.Events = (Constants && Constants.events) ? Constants.events : {};
        _this.Strings = (Constants && Constants.strings) ? Constants.strings : {};

        var eventManager = new EventManager();//事件管理
        var layerManager = new LayerManager();//图层管理
        var mapManager = new MapManager();//地图管理
        var moduleManager = new ModuleManager();//模块管理
        var configManager = new ConfigManager(apiDefaults);//配置管理

        var apiInit = function(){
            eventManager.registerEvent( _this.Events .ConfigLoadedEvent ,apiDefaults.onConfigLoaded);
            eventManager.registerEvent( _this.Events .MapLoadedEvent ,apiDefaults.onMapLoaded);
            eventManager.registerEvent( _this.Events .ErrorEvent ,apiDefaults.onError);
            eventManager.registerEvent( _this.Events .ModuleStartupEvent ,apiDefaults.onModuleStartup);
            eventManager.startup();
            mapManager.startup();
            moduleManager.startup();
            layerManager.startup();
            configManager.startup();//这里配置加载最后启动
        };//
        _this.map = null;
        _this.apiConfig = null;
        _this.mapConfig = null;
        /*******导航类*******/
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
            //需要设置导航状态

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
        _this.zoomHome = function(){
            var args = [].concat(_this.mapConfig.mapOptions.center) ;
            var level = _this.mapConfig.mapOptions.level ;
            args.push(level);
            _this.zoomAt.apply(_this,args);
        };
        _this.zoomIn = function(){
            // 需要设置导航状态
            _this.levelUp();
        };
        _this.zoomOut = function(){
            // 需要设置导航状态
            _this.levelDown();
        };
        _this.zoomAt = function(x, y , level){
            _this.centerAt(x, y);
            _this.setLevel(level===undefined ? apiDefaults.defaultZoomLevel :level);
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
            //
        };
        /********信息获取类*******/
        _this.getLayerById = function(layerId){
            var collection = _this.map.getLayers();
            for(var i = 0 ; i < collection.getLength(); i++){
                var layer = collection.item(i);
                var id = layer.get("id");
                if(id === layerId)
                    return layer;
            }
        };
        _this.getMapLevel = function(){
            return _this.map.getView().getZoom();
        };
        _this.getMapMinLevel= function(){
            return _this.map.getView().getMinZoom();
        };
        _this.getMapMaxLevel= function(){
            return _this.map.getView().getMaxZoom();
        };
        _this.getMapCenter= function(){
            return _this.map.getView().getCenter();
        };
        _this.getMapExtent= function(){
            return _this.map.getView().get("extent");
        };
        _this.getFeatures = function(featureId ,layerId ,attributes){
            var layer = _this.getLayerById(layerId ? layerId : apiDefaults.drawLayerId);
            var source = layer && layer.getSource();
            var result = [];
            if(source){
                if(featureId){
                    result.push(source.getFeatureById(featureId));
                }else{
                    var features = source.getFeatures();
                    for(var i = 0 ; i < features.length ; i++){
                        var feature = features[i];
                        var flg = true ;
                        for(var key in attributes){
                            if(feature.get(key) != attributes[key])
                                flg = false;
                        }
                        flg && result.push(feature);
                    }
                }
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
        /********图层操作类********/
        _this.addLayer = function(options ){
            var layer = layerManager.createLayer(options);
            _this.map.addLayer(layer);
            return layer;
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
                "zIndex":null
            };
            var params = commonUtil.extend(defaults,options);
            var layer = _this.getLayerById(layerId);
            if(layer){
                eventManager.publishError( _this.Strings.layerIdRepeatError + ",id=" + layerId );
                return ;
            }
            return _this.addLayer(params);
        };   //new
        _this.clearAllGraphics = function(layerId){
            if(layerId){
                _this.clearGraphicsLayer(layerId);
            }else{
                _this.clearMapGraphics();
            }
            mapManager.clearAllOverlays();
            //
        };
        _this.addGraphics = function(layerId, geoJSON, style){
            var layer = _this.getLayerById(layerId);
            var vectorSource = layer && layer.getSource();
            if(layer && vectorSource){
                var features = new ol.format.GeoJSON().readFeatures(geoJSON);
                vectorSource.addFeatures(features);
            }
        };
        _this.addPictureGraphic  = function(x, y ,options){
            var defaults = {
                url:"classpath:images/location.png",
                width:24,
                height:24,
                offset:[0,0],
                anchor:[0.5,1],
                attributes:null,
                layerId:apiDefaults.drawLayerId
            };
            var params = commonUtil.extend(defaults,options);
            var layer = _this.getLayerById(params.layerId);
            var vectorSource = layer && layer.getSource();
            if(!layer || !vectorSource) return ;
            if(isNaN(x) || isNaN(y)) return;
            var feature = new ol.Feature({
                geometry : new ol.geom.Point([x ,y]),
                properties : params.attributes,
                name :  ''
            });
            if( params.attributes.id ){
                feature.setId( params.attributes.id );
            }
            var style = new ol.style.Style({
                image : new ol.style.Icon({
                    anchor: params.anchor,
                    offset: params.offset,
                    src:commonUtil.getApiRootPath(params.url)
                })
            });
            feature.setStyle(style);
            vectorSource.addFeature(feature);
        };
        _this.addPointGraphic = function(x,y,options){
            var defaults = {
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
            var layer = _this.getLayerById(params.layerId);
            var vectorSource = layer && layer.getSource();
            if(layer && vectorSource && isNaN(x) && isNaN(y)){
                var feature = new ol.Feature({
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
                });
                vectorSource.addFeature(feature);
            }
        };
        _this.addLineGraphic = function(path,options){
            var defaults = {
                color:[186,218,37,1],
                width:1,
                attributes:null,
                layerId:apiDefaults.drawLayerId
            };
            var params = commonUtil.extend(defaults,options);
            var layer = _this.getLayerById(params.layerId);
            var vectorSource = layer && layer.getSource();
            if(layer && vectorSource && path){
                var feature = new ol.Feature({
                    geometry : new ol.geom.LineString(path),
                    properties : params.attributes,
                    name : '',
                    style:new ol.style.Style({
                        stroke: new ol.style.Stroke({
                            color: params.color,
                            width: params.width
                        })
                    })
                });
                vectorSource.addFeature(feature);
            }
        };
        _this.addPolygonGraphic = function(ring,options){
            var defaults = {
                fillColor:[42,42,42,1],
                borderColor:[186,218,37,1],
                width:1,
                attributes:null,
                layerId:apiDefaults.drawLayerId
            };
            var params = commonUtil.extend(defaults,options);
            var layer = _this.getLayerById(params.layerId);
            var vectorSource = layer && layer.getSource();
            if(layer && vectorSource && ring){
                var feature = new ol.Feature({
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
                });
                vectorSource.addFeature(feature);
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
            _this.clearGraphicsLayer(apiDefaults.drawLayerId);
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
        _this.setLayerTips = function(){

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
            _this.addMapEvent("postrender",func);
        };
        _this.addZoomEventListener = function(func){// ?
            _this.addMapEvent("moveend",func);
        };
        _this.addExtentEventListener = function(func){//?
            _this.addMapEvent("moveend",func);
        };
        _this.addMouseMoveEventListener = function(func){//?
            _this.addMapEvent("moveend",func);
        };
        _this.addLayerClickEventListener = function(layerId , func){
            var layer = _this.getLayerById(layerId);
            if(layer && typeof func === "function"){
                var interaction = new ol.interaction.Select({
                    condition: ol.events.condition.click
                });
                interaction.on("select",func);
                _this.map.addInteraction(interaction);
                return interaction;
            }
        };
        _this.addLayerVisibilityChangeEventListener = function(layerId , func){
            var layer = _this.getLayerById(layerId);
            if(layer){
                return layer.on("change:visible",func);
            }
        };
        //-----new-----
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
        //----new end---
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
                "fieldName":"id",
                "center":true,
                "scale":20000 // 与map lods参数或底图的比例尺设置相关
            };
            var params = commonUtil.extend(defaults,options);
            var features = [];
            if(typeof target === "string")
                features = _this.getFeatures(target ,layerId);
            else if(typeof target === "object")
                features = _this.getFeatures(null,layerId ,target);
            var getBoldStyle = function(s){
                var style = s.clone();
                if(style.getFill()){
                    var fill = style.getFill();
                    fill.setColor(apiDefaults.defaultHighlightColor);
                }
                if(style.getStroke()){
                    var stroke = style.getStroke();
                    var width = stroke.getWidth() + 2;
                    stroke.setWidth(width);
                }
                if(style.getImage()){
                    var image = style.getImage();
                    var scale = image.getScale() * 1.25;
                    image.setScale(scale);
                }
                return style;
            };
            var prepareFlashStyle = function(){
                if(features && features.length > 0){
                    for(var i = 0 ; i < features .length ; i++) {
                        var f1 = features[i];
                        var fStyle1 =  f1.getStyle();
                        if(fStyle1){
                            f1.set("preSymbol",fStyle1);
                            f1.set("boldSymbol",getBoldStyle(fStyle1));
                        }
                    }
                    return true;
                }
                return false;
            };
            var featureStyleFlash = function(flg){
                for(var i = 0 ; i < features .length ; i++){
                    var f = features[i];
                    var fStyle =  f.getStyle();
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
                if(!feature0) return false ;
                var extent = feature0.getGeometry().getExtent();
                for(var i = 1 ; i < features.length ; i++){
                    var featureI = features[i];
                    featureI && ( extent = ol.extent.extend(extent,featureI.getGeometry().getExtent()));
                }
                _this.zoomExtent.apply(_this,extent);
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
            prepareFlashStyle() && doPosition() && doFlash();
        };
        _this.switchBaseMap = function(layerId){
            var layer = _this.getLayerById(layerId);
            if(!layer){
                eventManager.publishError( _this.Strings.layerNotLoaded + ",id=" + layerId);
                return
            }
            for(var i = 0 ; i < layerManager.baseMapLayers.length ; i++){
                var baseLayer = layerManager.baseMapLayers[i];
                var baseLayerId = baseLayer.get("id");
                baseLayer.setVisible(baseLayerId === layerId );
            }
        };
        _this.layerSetVisibleSwitch = function(layerSetId){
            //开发中...

        };
        /********标绘********/
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
            var defaultStyleModel = {
                radius:"5",
                picture:null,
                height:16,
                width:16,
                offset_x:8,
                offset_y:16,
                border_width:2,
                border_color:"#000000",
                border_opacity:1,
                fill_color:"#ff0000",
                fill_opacity:1
            };
            var defaults = {
                "drawLayerId" : apiDefaults.drawLayerId,
                "attributes" : null,
                "onDrawEnd" : null,
                "onDrawStart" : null,
                "style":defaultStyleModel
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
                "onDrawEnd":params.onDrawEnd
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
                "onDrawEnd": params.onDrawEnd
            });
        };
        /*******事件方法******/
        _this.publish = function(eventType , data){
            eventManager.publishEvent.apply(this,arguments);
        };
        _this.subscribe = function(eventType , onListener){
            eventManager.registerEvent.apply(this,arguments);
        };
        _this.getModuleById = function( moduleId ){
            // for(var i = 0 ; i < moduleManager.modules.length ; i++){
            //     var m = moduleManager.modules[i];
            //     if(moduleId === m.id ){
            //         return m;
            //     }
            // }
            return moduleManager.getModuleById(moduleId);
        };
        //
        function MapManager(){
            MapManager.NAVIGATOR = "navigator" ;
            MapManager.DRAW = "draw" ;
            MapManager.EDITOR = "editor" ;
            var _class = this;
            var createMap = function(){
                var divId = _this.mapConfig.id || "jasMap";
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
                setTimeout(function(){
                    eventManager.publishEvent( _this.Events .MapLoadedEvent);
                },10);
            };
            var onConfigLoaded = function(e){
                _this.apiConfig = e.data;
                _this.mapConfig = e.data.map;
                createMap();
            };

            var measureValueOverlay = null;
            var measureTipOverlay = null;
            var mouseMoveListener = null;
            var mouseOutListener = null;
            var geometryChangeListener = null;
            var currentDrawFeature = null;
            var onDrawEnd = null;
            var onDrawStart = null;
            var measureParams = null;

            var addEventHandler = function(){
                mouseMoveListener = _this.map.on('pointermove', function(evt) {
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
                mouseOutListener = _this.map.getViewport().addEventListener('mouseout', function(evt){
                    measureTipOverlay.getElement().classList.add('hidden');
                });
            };
            var addMeasureTipOverlay= function(){
                var helpTooltipElement;
                if(measureTipOverlay){
                    helpTooltipElement = measureTipOverlay.getElement();
                    helpTooltipElement.parentNode && helpTooltipElement.parentNode.removeChild(helpTooltipElement);
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
                // if(measureValueOverlay ){
                //     measureValueOverlay.set("undeletable",false);
                // }
                var measureValueElement = document.createElement('div');
                measureValueElement.className = 'tooltip tooltip-measure';
                measureValueOverlay = new ol.Overlay({
                    element: measureValueElement,
                    offset: [0, -15],
                    positioning: 'bottom-center'
                });
                //measureValueOverlay.set("undeletable",true);
                _this.map.addOverlay(measureValueOverlay);

            };
            var clearMeasureState = function(){
                measureParams && (measureParams=null);
                onDrawEnd && _this.removeEventListener(onDrawEnd);
                onDrawStart && _this.removeEventListener(onDrawStart);
                mouseMoveListener && _this.removeEventListener(mouseMoveListener);
                geometryChangeListener && _this.removeEventListener(geometryChangeListener);
                mouseOutListener && _this.removeEventListener(mouseOutListener);
                //measureValueOverlay && _this.map.removeOverlay(measureValueOverlay);
                mouseOutListener && _this.map.removeOverlay(mouseOutListener);
                currentDrawFeature = null;
            };
            var parseDrawStyle = function(styleParam){
                if( !styleParam ) return ;
                var style = {
                    fill: new ol.style.Fill({
                        color: styleParam.fill_color
                    }),
                    stroke: new ol.style.Stroke({
                        color: styleParam.border_color,
                        lineDash: [10, 10],
                        width: styleParam.border_width
                    })
                } ;
                if(styleParam.picture){
                    style.image = new ol.style.Icon({
                        anchor: [0.5, 1],
                        offset:[styleParam.offset_x,styleParam.offset_y],
                        size:[ styleParam.height , styleParam.width],
                        anchorXUnits: 'fraction',
                        anchorYUnits: 'pixels',
                        src: styleParam.picture
                    });
                }else{
                    style.image = new ol.style.Circle({
                        radius: styleParam.radius,
                        stroke: new ol.style.Stroke({
                            color: styleParam.border_color
                        }),
                        fill: new ol.style.Fill({
                            color: styleParam.fill_color
                        })
                    })
                }
                return new ol.style.Style(style)
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
            _class.measureInit = function(callback,options){
                clearMeasureState();
                measureParams = options;
                addMeasureTipOverlay();
                addEventHandler();
                onDrawEnd = measureParams.onDrawEnd = function(evt){
                    currentDrawFeature = null;
                    var measureTooltipElement = measureValueOverlay.getElement();
                    measureTooltipElement.className = 'tooltip tooltip-static';
                    measureValueOverlay.setOffset([0, -7]);
                    //measureTooltipElement = null;
                    ol.Observable.unByKey(geometryChangeListener);
                    if(callback && typeof callback === "function"){
                        callback(measureParams.labelStyleFunc(length));
                    }
                } ;
                onDrawStart = measureParams.onDrawStart = function(evt) {
                    addMeasureValueOverlay();
                    currentDrawFeature = evt.feature;
                    var tooltipCoord = evt.coordinate;
                    geometryChangeListener = currentDrawFeature.getGeometry().on('change', function(evt) {
                        var geom = evt.target;
                        var output;
                        var coordinates;
                        var wgs84Sphere= new ol.Sphere(6378137);
                        if (geom instanceof ol.geom.Polygon) {
                            coordinates = geom.getLinearRing(0).getCoordinates();
                            var area = Math.abs(wgs84Sphere.geodesicArea(coordinates));
                            output = measureParams.labelStyleFunc(area);
                            tooltipCoord = geom.getInteriorPoint().getCoordinates();
                        } else if (geom instanceof ol.geom.LineString) {
                            var length = 0.0 ;
                            coordinates = geom.getCoordinates();
                            var prePoint = coordinates[0];
                            for(var i = 1 ; i < coordinates.length ; i++){
                                var curPoint = coordinates[i];
                                length += wgs84Sphere.haversineDistance(prePoint,curPoint);
                                prePoint = curPoint;
                            }
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
            _class.startup = function(){
                eventManager.registerEvent( _this.Events .ConfigLoadedEvent ,onConfigLoaded);
            };
            _class.active = function(state,vectorLayer,param ){
                _this.removeEventListener(_class.drawInteracting);
                _this.removeEventListener(_class.editInteracting);
                switch(state){
                    case MapManager.DRAW:
                        var geometryFunction = null;
                        var styleFunction = null;
                        var drawType = param.drawType ;
                        var style = parseDrawStyle(param.style);
                        var attributes = param.style.attributes;//
                        var onDrawEnd = param.onDrawEnd;
                        var onDrawStart = param.onDrawStart;
                        if( drawType && vectorLayer ) {
                            if(style){///////
                                styleFunction = function(feature) {
                                    var geometry = feature.getGeometry();
                                    var styles = [];
                                    if(geometry)
                                        style.setGeometry(geometry);
                                    styles.push(style);
                                    return styles;
                                };
                            }
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
                                style: styleFunction ? styleFunction :style
                            });
                            if(onDrawEnd && typeof onDrawEnd==="function"){
                                _class.drawInteracting.on("drawend",onDrawEnd);
                            }
                            if(onDrawStart && typeof onDrawStart==="function"){
                                _class.drawInteracting.on("drawstart",onDrawStart);
                            }
                            _this.map.addInteraction(_class.drawInteracting);
                        }
                        break;
                    case MapManager.EDITOR://编辑不能指定图层？
                        var select = new ol.interaction.Select({
                            wrapX: false
                        });
                        var modify = new ol.interaction.Modify({
                            features: select.getFeatures()
                        });
                        _class.editInteracting = new ol.Collection([ select, modify ]);
                        _this.addInteractions( _class.editInteracting);
                        break;
                    case MapManager.NAVIGATOR:
                        clearMeasureState();
                    default:
                }
            };

        }
        function LayerManager(){
            var _class = this;
            var _mapConfig = null;
            var _optionalLayerConfig = null;
            var _baseMapLayerConfig = null;
            var parseLayerConfig = function(config){
                var sourceConfig = {
                    url:"",
                    format:"",
                    strategy:"",
                    serverType:"",
                    params:null
                };
                var layerConfig = commonUtil.extend({
                    opacity:1,
                    visible:true
                },{//配置必选
                    id:config.id,
                    type:config.type,
                    source:config.source,
                    opacity:config.opacity,
                    visible:config.visible,
                    zIndex: config.index
                });//
                if(config.url){
                    sourceConfig.url = config.url;
                }
                if(config.format){
                    if(config.format === "GeoJSON" ){
                        sourceConfig.format = new ol.format.GeoJSON();
                    }else if(config.format === "WKT"){
                        sourceConfig.format = new ol.format.WKT();
                    }
                }
                if(config.strategy){
                    if(config.strategy === "all"){
                        sourceConfig.strategy = ol.loadingstrategy.all;
                    }else if(config.strategy === "bbox"){
                        sourceConfig.strategy = ol.loadingstrategy.bbox;
                    }
                }else{
                    sourceConfig.strategy = ol.loadingstrategy.all;
                }
                if(config.serverType){
                    sourceConfig.serverType = config.serverType;
                }else{
                    sourceConfig.serverType = "geoserver";
                }
                if(config.params){
                    sourceConfig.params = config.params;
                }
                return {
                    layerConfig:layerConfig,
                    sourceConfig:sourceConfig
                };
            };
            var createBaseMapLayers = function(){
                if(_baseMapLayerConfig) {
                    var baseMapLayers = _baseMapLayerConfig.baseMapLayers;
                    if (baseMapLayers && baseMapLayers.length > 0) {
                        for (var i = 0; i < baseMapLayers.length; i++) {
                            var baseLayerConfig = baseMapLayers[i];
                            baseLayerConfig.icon && (baseLayerConfig.icon = commonUtil.getApiRootPath(baseLayerConfig.icon));
                            var layer = _class.createLayer(baseLayerConfig);
                            _this.map.addLayer(layer);
                            _class.baseMapLayers.push(layer);
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
                        var layer = _class.createLayer(layersConfig[j]);
                        _this.map.addLayer(layer);
                        _class.optionalLayers.push(layer);
                    }
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
            //
            _class.optionalLayers = [];
            _class.baseMapLayers = [];
            _class.startup = function(){
                eventManager.registerEvent( _this.Events .ConfigLoadedEvent ,onConfigLoaded);
                eventManager.registerEvent( _this.Events .ModulesLoadedEvent ,onModulesLoaded);
            };
            /**
             *
             * @param layerParam 包含layerConfig 和 sourceConfig 配置
             * @returns {*}
             */
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
                            //开发中...

                        }else if("VectorTile" === source){
                            //开发中...

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
                        }
                        layerConfig.source = layerSource;
                        layer = new ol.layer.Vector(layerConfig);
                        break;
                    default:
                        eventManager.publishInfo( _this.Strings.hasNoLayerTypeError + ",layerId=" + layerConfig.id  );
                }
                return layer;
            };
        }
        function EventManager(){
            var _class = this;
            //事件机制这里用了自定义事件
            var _eventObject = {
                _listeners: {},
                // 添加
                addEvent: function(type, fn) {
                    if (typeof this._listeners[type] === "undefined") {
                        this._listeners[type] = [];
                    }
                    if (typeof fn === "function") {
                        this._listeners[type].push(fn);
                    }
                    return this;
                },
                // 触发
                fireEvent: function(type,e) {
                    var arrayEvent = this._listeners[type];
                    if (arrayEvent instanceof Array) {
                        for (var i=0, length=arrayEvent.length; i<length; i+=1) {
                            if (typeof arrayEvent[i] === "function") {
                                arrayEvent[i]({ type: type ,data:e });
                            }
                        }
                    }
                    return this;
                },
                // 删除
                removeEvent: function( fn ,type ) {
                    if(fn && type){
                        var arrayEvent = this._listeners[type];
                        if (typeof type === "string" && arrayEvent instanceof Array) {
                            if (typeof fn === "function") {
                                //清除当前type类型事件下对应fn方法
                                for (var i=0, length=arrayEvent.length; i<length; i+=1){
                                    if (arrayEvent[i] === fn){
                                        this._listeners[type].splice(i, 1);
                                        break;
                                    }
                                }
                            } else {
                                //如果仅仅参数type,则所有type类型事件清除
                                delete this._listeners[type];
                            }
                        }
                    }else if(fn && typeof  fn === "function"){
                        for(var key in  this._listeners){
                            var listenerArr = this._listeners[key];
                            for(var i = 0 ; i < listenerArr.length ; i++){
                                if(listenerArr[i] === fn){
                                    this._listeners[type].splice(i, 1);
                                    break;
                                }
                            }
                        }
                    }
                    return this;
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
            _class.publishError = function(msg ,e){
                if(e){
                    console.error(e);
                }
                if(msg){
                    console.error(msg);
                }
                _class.publishEvent( _this.Events .ErrorEvent , { message:msg , type:"error"});
            };
            /**
             * @param eventType String
             * @param parameter Object
             */
            _class.publishEvent = function(eventType,target){
                _eventObject.fireEvent(eventType,target);
            };
            /**
             * @param eventType String
             * @param listener Function
             */
            _class.registerEvent = function(eventType,onListenerFunc){
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
                    if( _class.modules.length === 0) return ;
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
                module.startup();
            };
            var setModuleContainer = function(container){
                if(container==="map"){
                    return _this.map.getTargetElement();
                }else if(container === "html"){
                    return document.body;
                }else if(container.indexOf("#") >=0 ){
                    var domId = container.substring(1);
                    return document.getElementById(domId);
                }
                return null;
            };
            _class.loadModule = function(conf){
                //var deferred = new Deferred();
                var module = null;
                var ModuleClass = null;
                if(conf.class){
                    ModuleClass = eval(conf.class);
                    if(typeof ModuleClass !== "function"){
                        throw( _this.Strings.moduleClassNotFoundError);
                    }
                    try{
                        ModuleClass.prototype = new BaseMapModule();//
                        module = new ModuleClass(conf.options);
                        delete conf.options;
                        module.id = conf.id;
                        conf.label && ( module.label = conf.label );
                        conf.baseClass && ( module.baseClass = conf.baseClass );
                        conf.icon && ( module.icon = commonUtil.getApiRootPath( conf.icon ));

                        conf.template && ( module.template = conf.template);

                        conf.style && ( module.style = conf.style );
                        ( conf.moduleSet && conf.moduleSet.length > 0 ) && ( module.moduleSet = conf.moduleSet );
                        module.container = setModuleContainer( conf.container );
                        module.mapApi = _this;
                        if( module && conf.enable!==false ){
                            module.domCreate();
                            _class.loadedModules.push(module);
                        }
                    }catch(e){
                        module.destroy && typeof module.destroy === "function" && module.destroy();
                        eventManager.publishError( _this.Strings.moduleCreateError +" ,moduleId=" + module.id ,e);
                    }
                }else if(conf.url){
                    //开发中 ...
                }
                //return deferred.promise();
            };
            _class.startup = function(){
                eventManager.registerEvent( _this.Events .ConfigLoadedEvent ,onConfigLoaded);
                eventManager.registerEvent( _this.Events .MapLoadedEvent ,onMapLoaded);
                eventManager.registerEvent( _this.Events .ModuleInitEvent ,onModuleLoaded);
            };
            _class.getModuleById = function(id){
                for(var i = 0 ; i < _class.loadedModules.length ; i++){
                    if( _class.loadedModules[i].id === id){
                        return _class.loadedModules[i];
                    }
                }
                return null;
            };
        }
        function ConfigManager(apiOptions){
            var _class = this;
            var apiScript = null;
            _class.startup = function(){
                apiScript = document.getElementById(apiOptions.appScriptId);
                //var apiOpts = getMapOptions();//读取data-options
                basePath = getBasePath();
                var configPath = getMapConfigPath();//读取data-config
                if(configPath) {
                    //加载配置
                    loadConfig(configPath,function(conf){
                        //加载依赖
                        if (conf.resources && conf.resources.length > 0) {
                            eventManager.time( _this.Strings.dependenceLoading);
                            loadResources(conf.resources, function(type){
                                if (type==="dojo" && conf.dojoConfig) { // loadResources之前定义
                                    global.dojoConfig = conf.dojoConfig;
                                }
                            }, null, function () {
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
                simpleAjaxLoader(url, function () {
                    if(arguments[0] && arguments[0].target){
                        xmlHttp = arguments[0].target;
                    }else{//ie 8
                        xmlHttp = arguments.caller;
                    }
                    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
                        eventManager.timeEnd(  _this.Strings.configLoading);
                        var conf = {};
                        try {
                            var result = JSON.parse(xmlHttp.responseText);
                            if(apiOptions && apiOptions.appName)
                                conf = result[apiOptions.appName];
                            else
                                conf = result;

                            if (conf.dojoConfig) { // loadResources之前定义
                                global.dojoConfig = conf.dojoConfig;
                            }
                            if(onSuccess && typeof onSuccess === "function"){
                                onSuccess(conf);
                            }
                        } catch (e) {
                            eventManager.publishError( _this.Strings.parseConfigError,e);
                        }
                    }
                })
            }
            function loadResources( ress, onOneBeginLoad, onOneLoad, onLoad){
                var loaded = [];
                function _onOneLoad(url){
                    if(loaded.indexOf(url) > -1){
                        return;
                    }
                    loaded.push(url);
                    if(onOneLoad){
                        onOneLoad(url, loaded.length);
                    }
                    if(loaded.length === ress.length){
                        if(onLoad){
                            onLoad();
                        }
                    }
                }
                for(var i = 0; i < ress.length; i ++){
                    if(ress[i].url){
                        loadResource(ress[i].type, ress[i].url, onOneBeginLoad, _onOneLoad);
                    }
                }
            }
            function loadResource(type, url, onBeginLoad, onLoad){
                if(onBeginLoad){
                    onBeginLoad(type);
                }
                if(type === 'css'){
                    loadCss(url);
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
                        onLoad(url);
                    }
                }
                function elementReadyStateChanged(url){
                    if (this.readyState === 'loaded' || this.readyState === 'complete') {
                        elementLoaded(url);
                    }
                }
            }
            function simpleAjaxLoader(url,cFunc){
                var xmlHttp = null;
                if (window.XMLHttpRequest) {// IE7+, Firefox, Chrome, Opera, Safari 代码
                    xmlHttp=new XMLHttpRequest();
                }
                else{// IE6, IE5 代码
                    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlHttp.onreadystatechange = cFunc;
                xmlHttp.open("GET",url,true);
                xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlHttp.send();
            }
            function getMapConfigPath(){
                if(apiScript){
                    var path = apiScript.getAttribute("dataaccess-config");
                    if(path)
                        return path;
                }
                return "config.json";
            }
            function getBasePath(){
                if(apiScript){
                    var path = apiScript.getAttribute("src");
                    var index = path.indexOf("mapjs4ol.js");
                    if(index >= 0){
                        return path.substring(0,index);
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
        function CommonUtil (){
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
            _class.extend = function(destination, source) {
                var isObjFunc = function(name) {
                    var toString = Object.prototype.toString;
                    return function() {
                        return toString.call(arguments[0]) === '[object ' + name + ']'
                    }
                };
                var isObject = isObjFunc('Object'),
                    isArray = isObjFunc('Array');

                var obj,copy,i;
                for(i = arguments.length - 1;i>0;i--) {
                    destination = arguments[i - 1];
                    source = arguments[i];
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
                if(url.indexOf("classpath:") === 0){
                    result = basePath + url.replace("classpath:","");
                }
                return result.trim();
            };
        }
        apiInit();
    };
})(window);
