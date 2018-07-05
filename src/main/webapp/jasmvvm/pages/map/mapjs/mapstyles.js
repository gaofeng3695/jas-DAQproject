/**
 * 样式模板，需要openlayer相关依赖
 */
var mapStyleTemplates = {};
/**
 *
 * @type {ol.style.Style}
 */
mapStyleTemplates.centerlinestake = new ol.style.Style({
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
mapStyleTemplates.valveroom = new ol.style.Style({
    image: new ol.style.Icon({
        anchor: [0.5, 0.5],
        anchorXUnits: 'fraction',
        anchorYUnits: 'pixels',
        src: '../mapjs/images/renderer/valveroom.png'
    })
});
/**
 *
 * @type {ol.style.Style}
 */
mapStyleTemplates.pipesegment = new ol.style.Style({
    stroke: new ol.style.Stroke({
        color: 'red',
        width: 2
    }),
    fill: new ol.style.Fill({
        color: 'rgba(0, 0, 255, 1)'
    })
});






