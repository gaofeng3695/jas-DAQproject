// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
require({cache:{"url:esri/layers/vectorTiles/views/vectorTiles/renderers/shaders/sdfShader.vs.glsl":"attribute vec2 a_pos;\r\nattribute vec2 a_vertexOffset;\r\nattribute vec4 a_tex;\r\nattribute vec4 a_levelInfo;\r\n\r\n// attribute bool a_visible; // --\x3e a one bit controlling the visibility of the vertex\r\n\r\n// the relative transformation of a vertex given in tile coordinates to a relative normalized coordinate\r\n// relative to the tile's upper left corner\r\n// the extrusion vector.\r\nuniform highp mat4 u_transformMatrix;\r\n// the extrude matrix which is responsible for the 'anti-zoom' as well as the rotation\r\nuniform highp mat4 u_extrudeMatrix;\r\n// u_normalized_origin is the tile's upper left corner given in normalized coordinates\r\nuniform highp vec2 u_normalized_origin;\r\n// the size of the mosaic given in pixels\r\nuniform vec2 u_mosaicSize;\r\n// the z of the layer. Given by the order of the layers in the style\r\nuniform mediump float u_depth;\r\n// the map's rotation from the north\r\nuniform mediump float u_mapRotation;\r\nuniform mediump float u_level;\r\n// indicate whether the current set of iconst should be kept upright when the map is rotated\r\nuniform lowp float u_keepUpright;\r\n// the rate of the change in the opacity (fade) of the icons\r\nuniform mediump float u_fadeSpeed;\r\n// the low level we transition (to/from)\r\nuniform mediump float u_minfadeLevel;\r\n// the high level we transition (to/from)\r\nuniform mediump float u_maxfadeLevel;\r\n// the amount of fade given teh current time past the last recorded level\r\nuniform mediump float u_fadeChange;\r\n// the opacity of the layer given by the painter\r\nuniform mediump float u_opacity;\r\n// the interpolated texture coordinate value to be used by the fragment shader in order to sample the sprite texture\r\nvarying lowp vec2 v_tex;\r\n// the calculated transparency to be applied by the fragment shader. It is incorporating both the fade as well as the\r\n// opacity of the layer given by the painter\r\nvarying lowp float v_transparency;\r\n\r\n// the vertex offsets are given in integers, therefore in order to maintain a reasonable precission we multiply the values\r\n// by 16 and then at the shader devide by the same number\r\nconst float offsetPrecision \x3d 1.0 / 32.0;\r\n\r\nvoid main()\r\n{\r\n  mediump float a_labelMinLevel \x3d a_levelInfo[0];\r\n  mediump float a_angle        \x3d a_levelInfo[1];\r\n  mediump float a_minLevel    \x3d a_levelInfo[2];\r\n  mediump float a_maxLevel    \x3d a_levelInfo[3];\r\n\r\n  // if the given vertex should not be visible simply clip it by adding it a value that will push it outside the clipping plane\r\n  mediump float delta_z \x3d 0.0;\r\n\r\n  // TODO: force clipping the vertex in case that the vertex isn't visible\r\n  //delta_z +\x3d a_visible ? 0.0 : 1.0;\r\n\r\n  // If the label rotates with the map, and if the rotated label is upside down, hide it\r\n  mediump float rotated \x3d mod(a_angle - u_mapRotation, 256.0);\r\n  delta_z +\x3d (1.0 - step(u_keepUpright, 0.0)) * step(65.0, rotated) * (1.0 - step(193.0, rotated)); //ie. z +\x3d (flip \x3e 0) \x26\x26 (65 \x3c\x3d rotated) \x26\x26 (rotated \x3c 193)\r\n\r\n  // u_level is the current service level adjusted for the change in font size\r\n  delta_z +\x3d 1.0 - step(a_minLevel, u_level); // Test if (level \x3c minLevel)\r\n  delta_z +\x3d step(a_maxLevel, u_level); // Test if (maxLevel \x3c\x3d level)\r\n\r\n  // calculate the alpha given the change in the fade and the fade-speed\r\n  lowp float alpha \x3d clamp((u_fadeChange - a_labelMinLevel) / u_fadeSpeed, 0.0, 1.0);\r\n\r\n  // if the speed is positive we are zooming in and therefore we need to 'fade-in'. Else we need to 'fade-out'\r\n  v_transparency \x3d (u_fadeSpeed \x3e\x3d 0.0 ? alpha : 1.0 - alpha);\r\n\r\n  // now deal with the min/max fade-levels. If we exceeded the level we simply snap to 0 or 1\r\n  if (u_maxfadeLevel \x3c a_labelMinLevel)\r\n  {\r\n      v_transparency \x3d 0.0;\r\n  }\r\n  if (u_minfadeLevel \x3e\x3d a_labelMinLevel)\r\n  {\r\n      v_transparency \x3d 1.0;\r\n  }\r\n\r\n  // if label has been faded out, clip it\r\n  delta_z +\x3d step(v_transparency, 0.0);\r\n\r\n  gl_Position \x3d vec4(u_normalized_origin, u_depth, 0.0) + u_transformMatrix * vec4(a_pos, 0.0, 1.0) + u_extrudeMatrix * vec4(offsetPrecision * a_vertexOffset, delta_z, 0.0);\r\n  v_tex \x3d a_tex.xy / u_mosaicSize;\r\n\r\n  v_transparency *\x3d u_opacity;\r\n}\r\n",
"url:esri/layers/vectorTiles/views/vectorTiles/renderers/shaders/sdfShader.fs.glsl":"uniform lowp sampler2D u_texture;\r\nuniform lowp vec4 u_color;\r\nuniform mediump float u_edgeDistance;\r\nuniform mediump float u_edgeWidth;\r\n\r\nvarying lowp vec2 v_tex;\r\nvarying lowp float v_transparency;\r\n\r\n// this is taken from http://www.valvesoftware.com/publications/2007/SIGGRAPH2007_AlphaTestedMagnification.pdf\r\n// and https://www.mapbox.com/blog/text-signed-distance-fields/\r\n// http://metalbyexample.com/rendering-text-in-metal-with-signed-distance-fields/\r\n\r\nvoid main()\r\n{\r\n  // read the distance from the SDF texture\r\n  lowp float dist \x3d texture2D(u_texture, v_tex).a;\r\n  // use a smooth-step in order to calculate the geometry of the shape given by the distance field\r\n  mediump float alpha \x3d smoothstep(u_edgeDistance - u_edgeWidth, u_edgeDistance + u_edgeWidth, dist) * v_transparency;\r\n\r\n  gl_FragColor \x3d vec4(u_color.rgb, alpha* u_color.a);\r\n\r\n// YF: this code allow having both a fill and an outline colors combined in a single pass\r\n//  lowp float geometryAlpha \x3d smoothstep(0.75 - 0.21, 0.75 + 0.21, dist) * v_transparency;\r\n//  lowp vec4 geometryColor \x3d vec4(u_color.rgb, geometryAlpha * u_color.a);\r\n//\r\n//  if (true) {\r\n//    lowp float haloAlpha \x3d smoothstep(0.0 - 0.1, 0.0 + 0.1, dist) * v_transparency;\r\n//    lowp vec4 haloColor \x3d vec4(1.0, 1.0, 1.0, 1.0);\r\n//    haloColor \x3d vec4(haloColor.rgb, haloAlpha);\r\n//\r\n//    // calculate the composite color\r\n//    lowp float compositeAlpha \x3d geometryColor.a + haloColor.a * (1.0 - geometryColor.a);\r\n//    lowp vec3 compositeColor \x3d vec3(geometryColor) * geometryColor.a + vec3(haloColor) * haloColor.a * (1.0 - geometryColor.a);\r\n//    compositeColor /\x3d compositeAlpha;\r\n//    gl_FragColor \x3d vec4(compositeColor, compositeAlpha);\r\n//  }\r\n//  else {\r\n//    gl_FragColor \x3d geometryColor;\r\n//  }\r\n}\r\n"}});
define("esri/layers/vectorTiles/views/vectorTiles/renderers/SDFRenderer","require exports ../../../core/libs/gl-matrix/mat4 ../../../core/libs/gl-matrix/vec4 ../../../core/libs/gl-matrix/vec3 dojo/text!./shaders/sdfShader.vs.glsl dojo/text!./shaders/sdfShader.fs.glsl ../../webgl/Program ../../webgl/VertexArrayObject ../GeometryUtils".split(" "),function(y,z,m,n,q,t,u,v,w,x){return function(){function d(){this._attributeLocations={a_pos:0,a_vertexOffset:1,a_tex:2,a_levelInfo:3};this._initialized=!1;
this._extrudeMat=m.create();this._haloColor=n.create();this._sdfColor=n.create();this._scaleVec=q.create()}d.prototype.render=function(b,e,a,r,c,d,f,k,n,q,p){this._initialized||this._initialize(b);var g=f.getLayoutValue("text-size",a),h=g/24;r=x.degToByte(r);var l=f.getLayoutValue("text-rotation-alignment",a);1===l&&1===f.getLayoutValue("symbol-placement",a)&&!f.hasLayoutProperty("text-rotation-alignment")&&(l=0);var s=0===l,l=f.getLayoutValue("text-keep-upright",a)&&s;p=0.1*24/g/p;g=f.getPaintValue("text-opacity",
a);this._glyphTextureSize||(this._glyphTextureSize=new Float32Array([k.width/4,k.height/4]));s?m.copy(this._extrudeMat,n):m.copy(this._extrudeMat,q);this._scaleVec[0]=h;this._scaleVec[1]=h;this._scaleVec[2]=1;m.scale(this._extrudeMat,this._extrudeMat,this._scaleVec);k.bind(b,9729,0);b.bindProgram(this._sdfProgram);if(k=this._getSDFVAO(b,d))b.bindVAO(k),this._sdfProgram.setUniformMatrix4fv("u_transformMatrix",d.tileTransform.transform),this._sdfProgram.setUniformMatrix4fv("u_extrudeMatrix",this._extrudeMat),
this._sdfProgram.setUniform2fv("u_normalized_origin",d.tileTransform.displayCoord),this._sdfProgram.setUniform1f("u_depth",f.z),this._sdfProgram.setUniform2fv("u_mosaicSize",this._glyphTextureSize),this._sdfProgram.setUniform1f("u_mapRotation",r),this._sdfProgram.setUniform1f("u_keepUpright",l?1:0),this._sdfProgram.setUniform1f("u_level",10*a),this._sdfProgram.setUniform1f("u_fadeSpeed",10*c.fadeSpeed),this._sdfProgram.setUniform1f("u_minfadeLevel",10*c.minfadeLevel),this._sdfProgram.setUniform1f("u_maxfadeLevel",
10*c.maxfadeLevel),this._sdfProgram.setUniform1f("u_fadeChange",10*(a+c.fadeChange)),this._sdfProgram.setUniform1f("u_opacity",g),this._sdfProgram.setUniform1i("u_texture",0),c=f.getPaintValue("text-halo-color",a),0<c[3]&&(this._haloColor[0]=c[0],this._haloColor[1]=c[1],this._haloColor[2]=c[2],this._haloColor[3]=c[3]*g,c=p+f.getPaintValue("text-halo-blur",a)/h/8,h=0.75-f.getPaintValue("text-halo-width",a)/h/8,this._sdfProgram.setUniform4fv("u_color",this._haloColor),this._sdfProgram.setUniform1f("u_edgeDistance",
h),this._sdfProgram.setUniform1f("u_edgeWidth",c),b.drawElements(4,e.textElementCount,5125,12*e.textElementStart)),a=f.getPaintValue("text-color",a),0<a[3]&&(this._sdfColor[0]=a[0],this._sdfColor[1]=a[1],this._sdfColor[2]=a[2],this._sdfColor[3]=a[3]*g,this._sdfProgram.setUniform4fv("u_color",this._sdfColor),this._sdfProgram.setUniform1f("u_edgeDistance",0.75),this._sdfProgram.setUniform1f("u_edgeWidth",p),b.drawElements(4,e.textElementCount,5125,12*e.textElementStart),b.bindVAO())};d.prototype._initialize=
function(b){if(this._initialized)return!0;this._sdfProgram=new v(b,t,u,this._attributeLocations);this._sdfVertexAttributes={geometry:[{name:"a_pos",count:2,type:5122,offset:0,stride:16,normalized:!1,divisor:0},{name:"a_vertexOffset",count:2,type:5122,offset:4,stride:16,normalized:!1,divisor:0},{name:"a_tex",count:4,type:5121,offset:8,stride:16,normalized:!1,divisor:0},{name:"a_levelInfo",count:4,type:5121,offset:12,stride:16,normalized:!1,divisor:0}]};return this._initialized=!0};d.prototype._getSDFVAO=
function(b,e){if(e.textVertexArrayObject)return e.textVertexArrayObject;var a=e.textVertexBuffer,d=e.textIndexBuffer;if(!a||!d)return null;e.textVertexArrayObject=new w(b,this._attributeLocations,this._sdfVertexAttributes,{geometry:a},d);return e.textVertexArrayObject};return d}()});