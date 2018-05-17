// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/layers/Raster","require dojo/_base/declare dojo/_base/lang dojo/_base/Deferred dojo/_base/array dojo/_base/config dojo/json dojo/sniff ../kernel ../Evented ../request ../geometry/Extent ../SpatialReference ../deferredUtils ./PixelBlock ./rasterFormats/LercCodec".split(" "),function(B,n,m,t,x,J,C,u,D,E,F,G,H,y,l,I){var v,w,p,z,A,q;n=n(E,{declaredClass:"esri.layers.Raster",imageServiceUrl:null,validPixelTypes:"U1 U2 U4 U8 U16 U32 S8 S16 S32 F32".split(" "),validFormats:"lerc jpeg jpg jpgpng png png8 png24 png32 bip bsq tiff".split(" "),
_eventMap:{"raster-read-complete":["pixelData","params"]},constructor:function(a){this.imageServiceUrl=a;this.registerConnectEvents();this._loadRasterFormatModules()},read:function(a,b,c){var d=this,e=new t(y._dfdCanceller);if(10>u("ie"))throw"This browser is not supported.";if(!a.imageServiceParameters)throw"Insufficient parameters to read data";var f=m.clone(a.imageServiceParameters),r=a.pixelType;x.some(this.validPixelTypes,function(a){return a===r})||(f.pixelType="F32");x.some(this.validFormats,
function(a){return a.toLowerCase()===f.format.toLowerCase()})||(f.format="lerc");var g=a.decodeFunc,h;this._prepareGetImageParameters(f);e._pendingDfd=F({url:this.imageServiceUrl+"/exportImage",handleAs:"arraybuffer",content:m.mixin(f,{f:"image"}),load:function(a){d.decode(a,{width:f.width,height:f.height,planes:null,pixelType:r,noDataValue:f.noData,format:f.format,decodeFunc:g}).then(function(a){h={pixelBlock:a,extent:f.extent};d._resolve([h,f],"onRasterReadComplete",b,e)},function(a){d._resolve([a],
null,c,e,!0)})},error:function(a){d._resolve([a],null,c,e,!0)}});return e.promise},decode:function(a,b){if(void 0===b||null===b)throw"missing decode options";var c,d;b.format&&(c=b.format.toUpperCase());"BSQ"!==c&&"BIP"!==c&&(c=this._getFormat(a));d=b.decodeFunc;if(void 0===d||null===d)d=this._getFormatDecoderDfd(c);return d(a,b)},onRasterReadComplete:function(){},_prepareGetImageParameters:function(a){if(a.size&&a.bbox){var b=a.size.split(",");a.width=parseFloat(b[0]);a.height=parseFloat(b[1]);a.extent||
(b=a.bbox.split(","),a.extent=new G(parseFloat(b[0]),parseFloat(b[1]),parseFloat(b[2]),parseFloat(b[3]),new H(a.bboxSR)))}else{if(!a.width||Math.floor(a.width)!==a.width||!a.height||Math.floor(a.height)!==a.height)throw"Incorrect Image Dimensions";if(!a.extent||"esri.geometry.Extent"!==a.extent.declaredClass)throw"Incorrect extent";var b=a.extent,c=b.spatialReference.wkid||C.toJson(b.spatialReference.toJson());delete a._ts;m.mixin(a,{bbox:b.xmin+","+b.ymin+","+b.xmax+","+b.ymax,imageSR:c,bboxSR:c,
size:a.width+","+a.height},a.disableClientCaching?{_ts:(new Date).getTime()}:{})}},_adjustExtent:function(a,b,c){var d=a.ymax-a.ymin,e=a.xmax-a.xmin;c>=b?a.ymax=a.ymin+e*b/c:(e=d*c/b,a.xmax=a.xmin+e);return a},_resolve:function(a,b,c,d,e){b&&this[b].apply(this,a);c&&c.apply(null,a);d&&y._resDfd(d,a,e)},_getFormatDecoderDfd:function(a){var b=null;switch(a){case "LERC":b=this._decodeLerc;break;case "JPEG":b=this._decodeJpeg;break;case "PNG":b=this._decodePng;break;case "BSQ":b=this._decodeBsq;break;
case "BIP":b=this._decodeBip;break;case "TIFF":b=this._decodeTiff;break;default:b=function(a,b){throw"The raster format is not supported";}}b=m.hitch(this,b);return function(c,d){var e=new t;try{var f;"LERC"===a||!0===A?(f=b(c,d),e.resolve(f)):q.then(function(){f=b(c,d);e.resolve(f)})}catch(g){e.reject(g)}return e}},_getFormat:function(a){a=new Uint8Array(a,0,10);var b="";if(255===a[0]&&216===a[1])b="JPEG";else if(137===a[0]&&80===a[1]&&78===a[2]&&71===a[3])b="PNG";else if(67===a[0]&&110===a[1]&&
116===a[2]&&90===a[3]&&73===a[4]&&109===a[5]&&97===a[6]&&103===a[7]&&101===a[8]&&32===a[9])b="LERC";else if(-1<String.fromCharCode.apply(null,a).toLowerCase().indexOf("error"))b="ERROR";else if(73===a[0]&&73===a[1]&&42===a[2]&&0===a[3]||77===a[0]&&77===a[1]&&0===a[2]&&42===a[3])b="TIFF";return b},_validateDecodeParams:function(a){if(!a.height||Math.floor(a.height)!==a.height)throw"Height not provided.";if(!a.width||Math.floor(a.width)!==a.width)throw"Width not provided.";},_decodeJpeg:function(a,
b){if(!v)throw"The jpeg decoder module is not loaded.";this._validateDecodeParams(b);var c=(new v).decode(a);if(c.height!==b.height||c.width!==b.width)throw"The decoded image dimensions are incorrect.";var d=[],e,f;for(e=0;e<c.pixels.length;e++)f=c.pixels[e],d.push(this._calculateBandStatistics(f));return new l({width:c.width,height:c.height,pixels:c.pixels,pixelType:"U8",mask:c.mask,statistics:d})},_decodePng:function(a,b){if(!w)throw"The png decoder module is not loaded.";this._validateDecodeParams(b);
var c=new Uint8Array(a),d=new w(c),c=new Uint8Array(4*b.width*b.height);d.copyToImageData(c,d.decodePixels());for(var e=d=0,f,e=new Uint8Array(b.width*b.height),d=0;d<b.width*b.height;d++)e[d]=c[4*d+3];for(var g=new l({width:b.width,height:b.height,pixels:[],pixelType:"U8",mask:e,statistics:[]}),d=0;3>d;d++){f=new Uint8Array(b.width*b.height);for(e=0;e<b.width*b.height;e++)f[e]=c[4*e+d];g.addData({pixels:f,statistics:this._calculateBandStatistics(f)})}return g},_decodeBsq:function(a,b){if(!p)throw"The bsq decoder module is not loaded.";
this._validateDecodeParams(b);g=b.noDataValue;b.pixelType=s(b.pixelType);var c=p.decodeBSQ(a,{bandCount:b.planes,width:b.width,height:b.height,pixelType:h,noDataValue:g}),d=[],e,f=null;for(e=0;e<c.pixels.length;e++)f=c.pixels[e],d.push(this._calculateBandStatistics(f));return new l({width:b.width,height:b.height,pixels:c.pixels,pixelType:b.pixelType,mask:c.maskData,statistics:d})},_decodeBip:function(a,b){this._validateDecodeParams(b);g=b.noDataValue;b.pixelType=s(b.pixelType);var c=p.decodeBIP(a,
{bandCount:b.planes,width:b.width,height:b.height,pixelType:h,noDataValue:g}),d=[],e,f=null;for(e=0;e<c.pixels.length;e++)f=c.pixels[e],d.push(this._calculateBandStatistics(f));return new l({width:b.width,height:b.height,pixels:c.pixels,pixelType:b.pixelType,mask:c.maskData,statistics:d})},_decodeTiff:function(a,b){this._validateDecodeParams(b);g=b.noDataValue;b.pixelType=s(b.pixelType);var c=z.decode(a),d=[],e,f=null;for(e=0;e<c.pixels.length;e++)f=c.pixels[e],d.push(this._calculateBandStatistics(f,
c.maskData));return new l({width:c.width,height:c.height,pixels:c.pixels,pixelType:c.pixelType,mask:c.maskData,statistics:d})},_decodeLerc:function(a,b){this._validateDecodeParams(b);g=b.noDataValue;b.pixelType=s(b.pixelType);for(var c=0,d,e=0,f,r=a.byteLength-10;e<r;){var k=I.decode(a,{inputOffset:e,encodedMaskData:d,returnMask:0===c?!0:!1,returnEncodedMask:0===c?!0:!1,returnFileInfo:!0,pixelType:h,noDataValue:g}),e=k.fileInfo.eofOffset;0===c&&(d=k.encodedMaskData,f=new l({width:b.width,height:b.height,
pixels:[],pixelType:b.pixelType,mask:k.maskData,statistics:[]}));c++;if(k.height!==b.height||k.width!==b.width)throw"The decoded image dimensions are incorrect";f.addData({pixels:k.pixelData,statistics:{minValue:k.minValue,maxValue:k.maxValue,noDataValue:k.noDataValue}})}return f},_calculateBandStatistics:function(a,b){var c=Infinity,d=-Infinity,e=a.length,f,g=0;if(b)for(f=0;f<e;f++)b[f]&&(g=a[f],c=g<c?g:c,d=g>d?g:d);else for(f=0;f<e;f++)g=a[f],c=g<c?g:c,d=g>d?g:d;return{minValue:c,maxValue:d}},_loadRasterFormatModules:function(){q=
new t;10>u("ie")?q.reject("unsupported browser version"):B(["./rasterFormats/JpgPlus","./rasterFormats/Png","./rasterFormats/Raw","./rasterFormats/TiffDecoder","./rasterFormats/Zlib"],function(a,b,c,d){v=a;w=b;p=c;z=d;A=!0;q.resolve(!0)})}});var h=null,g=null,s=function(a){"U1"===a||"U2"===a||"U4"===a||"U8"===a?(a="U8",g=Math.pow(2,8)-1,h=Uint8Array):"U16"===a?(g=g||Math.pow(2,16)-1,h=Uint16Array):"U32"===a?(g=g||Math.pow(2,32)-1,h=Uint32Array):"S8"===a?(g=g||0-Math.pow(2,7),h=Int8Array):"S16"===
a?(g=g||0-Math.pow(2,15),h=Int16Array):"S32"===a?(g=g||0-Math.pow(2,31),h=Int32Array):h=Float32Array;return a};u("extend-esri")&&m.setObject("layers.Raster",n,D);return n});