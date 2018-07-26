/**
 * 样式模板，需要openlayer相关依赖
 */
JasMap.require(function(){
    var jasMap = this;
    var mapStyleTemplates = {};
    var getBasePath = jasMap.commonUtil.getApiRootPath;
    /**
     *
     * @type {ol.style.Style}
     */
    mapStyleTemplates.basestake = new ol.style.Style({
        image: new ol.style.RegularShape({
            fill: new ol.style.Fill({
                color: 'red'
            }),
            stroke:new ol.style.Stroke({
                color: 'black',
                width: 1
            }) ,
            points: 4,
            radius: 5,
            angle: Math.PI / 4
        })
    });
    /**
     *
     * @type {ol.style.Style}
     */
    mapStyleTemplates.centerlinestake = new ol.style.Style({
        image: new ol.style.Icon({
            src:getBasePath("basepath:images/renderer/centerlinestake.png"),
            anchor: [0.5, 0.5]
        })
    });
    /**
     *
     * @type {ol.style.Style}
     */
    mapStyleTemplates.valveroom = new ol.style.Style({
        image: new ol.style.Icon({
            anchor: [0.5, 0.5],
            src: getBasePath('basepath:images/renderer/valveroom.png')
        })
    });
    /**
     *
     * @type {ol.style.Style}
     */
    mapStyleTemplates.station = new ol.style.Style({
        image: new ol.style.Icon({
            anchor: [0.5, 0.5],
            src:getBasePath('basepath: images/renderer/station.png')
        })
    });
    /**
     *
     * @type {ol.style.Style}
     */
    mapStyleTemplates.stationarea = new ol.style.Style({
        fill: new ol.style.Fill({
            color: '#48ddff'
        }),
        stroke: new ol.style.Stroke({
            color: '#319FD3',
            width: 1
        })
    });
    /**
     *
     * @type {ol.style.Style}
     */
    mapStyleTemplates.pipesegment = new ol.style.Style({
        stroke: new ol.style.Stroke({
            color: '#ff0000',
            width: 6
        }),
        fill: new ol.style.Fill({
            color: 'rgba(225, 0, 255, 1)'
        })
    });
    /**
     *
     * @type {ol.style.Style}
     */
    mapStyleTemplates.country = new ol.style.Style({
        fill: new ol.style.Fill({
            color: 'rgba(255, 255, 255, 0.6)'
        }),
        stroke: new ol.style.Stroke({
            color: '#319FD3',
            width: 1
        })
    });
    /**
     *
     * @type {ol.style.Style}
     */
    mapStyleTemplates.firstLevelPointLabelStyle = new ol.style.Style({
        text: new ol.style.Text({
            font: '12px Calibri,sans-serif',
            offsetX:-20,
            offsetY:10,
            overflow: true,
            textAlign:"right",
            fill: new ol.style.Fill({
                color: '#000'
            }),
            stroke: new ol.style.Stroke({
                color: '#fff',
                width: 3
            })
        })
    });
    /**
     *
     * @type {ol.style.Style}
     */
    mapStyleTemplates.secondLevelPointLabelStyle = new ol.style.Style({
        text: new ol.style.Text({
            font: '10px Calibri,sans-serif',
            offsetX:15,
            offsetY:5,
            overflow: true,
            textAlign:"left",
            fill: new ol.style.Fill({
                color: '#000'
            }),
            stroke: new ol.style.Stroke({
                color: '#fff',
                width: 3
            })
        })
    });
    /**
     *
     * @type {ol.style.Style}
     */
    mapStyleTemplates.lineLabelStyle = new ol.style.Style({
        text: new ol.style.Text({
            font: '13px Calibri,sans-serif',
            placement:'line',
            overflow: true,
            textBaseline:"bottom",
            //offsetY:10,
            //offsetX:10,
            fill: new ol.style.Fill({
                color: '#fff'
            }),
            stroke: new ol.style.Stroke({
                color: '#580087',
                width: 2
            })
        })
    });
    /**
     *
     * @type {ol.style.Style}
     */
    mapStyleTemplates.polygonLabelStyle = new ol.style.Style({
        fill: new ol.style.Fill({
            color: 'rgba(255, 255, 255, 0.6)'
        }),
        stroke: new ol.style.Stroke({
            color: '#319FD3',
            width: 1
        }),
        geometry: function(feature) {
            var geometry = feature.getGeometry();
            if (geometry.getType() === 'MultiPolygon') {
                var polygons = geometry.getPolygons();
                var widest = 0;
                for (var i = 0, ii = polygons.length; i < ii; ++i) {
                    var polygon = polygons[i];
                    var width = ol.extent.getWidth(polygon.getExtent());
                    if (width > widest) {
                        widest = width;
                        geometry = polygon;
                    }
                }
            }
            return geometry;
        },
        text: new ol.style.Text({
            font: '12px Calibri,sans-serif',
            placement:'polygon',
            overflow: true,
            fill: new ol.style.Fill({
                color: '#000'
            }),
            stroke: new ol.style.Stroke({
                color: '#fff',
                width: 3
            })
        })
    });
    return mapStyleTemplates;
});




