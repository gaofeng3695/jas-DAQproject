// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/layers/vectorTiles/views/2d/engine/StageGL","require exports ../../../core/tsSupport/extendsHelper ./Container ../../webgl/RenderingContext ../../webgl/webgl-utils".split(" "),function(l,m,f,g,h,k){return function(d){function c(){d.apply(this,arguments)}f(c,d);c.prototype.createElement=function(){this.el||(this.el=document.createElement("canvas"),this.el.setAttribute("class","esri-display-object"));return this.el};c.prototype.attach=function(a){this._resizeCanvas(a);var b=k.setupWebGL(this.el,
{alpha:!0,antialias:!1,depth:!0,stencil:!0});this._renderingContext=new h(b[0]);this.attached=!0;return d.prototype.attach.call(this,a)};c.prototype.detach=function(a){d.prototype.detach.call(this,a);this._renderingContext=null};c.prototype.render=function(a){this._resizeCanvas(a);d.prototype.render.call(this,a)};c.prototype.prepareChildrenRenderParameters=function(a){if(!this.attached||!this._renderingContext)return null;var b=this._renderingContext.gl;this._renderingContext.setClearColor(0,0,0,
0);this._renderingContext.setClearDepth(1);this._renderingContext.setClearStencil(0);this._renderingContext.clear(b.COLOR_BUFFER_BIT|b.DEPTH_BUFFER_BIT|b.STENCIL_BUFFER_BIT);this._renderingContext.setViewport(0,0,this.el.width,this.el.height);a.context=this._renderingContext;return a};c.prototype.attachChild=function(a,b){return a.attach(b)};c.prototype.detachChild=function(a,b){return a.detach(b)};c.prototype.renderChild=function(a,b){return a.render(b)};c.prototype._resizeCanvas=function(a){var b=
this.el,c=b.style,d=a.state,e=a.devicePixelRatio;a=d.width*e;e*=d.height;if(b.width!==a||b.height!==e)b.width=a,b.height=e,c.width=d.width+"px",c.height=d.height+"px"};return c}(g)});