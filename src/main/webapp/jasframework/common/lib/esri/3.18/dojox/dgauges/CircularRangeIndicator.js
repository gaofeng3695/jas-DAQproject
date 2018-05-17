//>>built
define("dojox/dgauges/CircularRangeIndicator",["dojo/_base/declare","./ScaleIndicatorBase","./_circularUtils","dojo/_base/event"],function(r,s,q,u){return r("dojox.dgauges.CircularRangeIndicator",s,{start:0,radius:NaN,startThickness:6,endThickness:6,fill:null,stroke:null,constructor:function(){this.indicatorShapeFunc=null;this.fill=[255,120,0];this.stroke={color:"black",width:0.2};this.interactionMode="none";this.addInvalidatingProperties("start radius startThickness endThickness fill stroke".split(" "))},
_interpolateColor:function(d,b,e){return((1-e)*(d>>16&255)+e*(b>>16&255)&255)<<16|((1-e)*(d>>8&255)+e*(b>>8&255)&255)<<8|(1-e)*(d&255)+e*(b&255)&255},_colorsInterpolation:function(d,b,e){for(var c=[],a=0,f=0;f<d.length-1;f++)a=(b[f+1]-b[f])*e,a=Math.round(a),c=c.concat(_colorInterpolation(d[f],d[f+1],a));return c},_alphasInterpolation:function(d,b,e){for(var c=[],a=0,f=0;f<d.length-1;f++)a=(b[f+1]-b[f])*e,a=Math.round(a),c=c.concat(_alphaInterpolation(d[f],d[f+1],a));return c},_alphaInterpolation:function(d,
b,e){b=(b-d)/(e-1);for(var c=[],a=0;a<e;a++)c.push(d+a*b);return c},_colorInterpolation:function(d,b,e){for(var c=[],a=0;a<e;a++)c.push(_interpolateColor(d,b,a/(e-1)));return c},_getEntriesFor:function(d,b){for(var e=[],c,a=0;a<d.length;a++)c=d[a],c=null==c[b]||isNaN(c[b])?a/(d.length-1):c[b],e.push(c);return e},_drawColorTrack:function(d,b,e,c,a,f,g,k,h,r,s,l){var m=0.05;g=6.28318530718-q.computeAngle(f,g,a);isNaN(l)||(l=q.computeAngle(f,l,a),h*=l/g,g=l);l=Math.max(2,Math.floor(g/m));var m=g/l,t=
0;g=-k;var t=(k-h)/l,n;"clockwise"==a&&(m=-m);var p=[];a=b+Math.cos(f)*(c+g);k=e-Math.sin(f)*(c+g);p.push(a,k);for(h=0;h<l;h++)n=f+h*m,a=b+Math.cos(n+m)*(c+g+h*t),k=e-Math.sin(n+m)*(c+g+h*t),p.push(a,k);isNaN(n)&&(n=f);a=b+Math.cos(n+m)*(c+0+0*(l-1));k=e-Math.sin(n+m)*(c+0+0*(l-1));p.push(a,k);for(h=l-1;0<=h;h--)n=f+h*m,a=b+Math.cos(n+m)*(c+0+0*h),k=e-Math.sin(n+m)*(c+0+0*h),p.push(a,k);a=b+Math.cos(f)*(c+0);k=e-Math.sin(f)*(c+0);p.push(a,k);a=b+Math.cos(f)*(c+g);k=e-Math.sin(f)*(c+g);p.push(a,k);
d.createPolyline(p).setFill(r).setStroke(s)},refreshRendering:function(){this.inherited(arguments);var d=this._gfxGroup;d.clear();var b=this.scale.originX,e=this.scale.originY,c=isNaN(this.radius)?this.scale.radius:this.radius,a=this.scale.orientation,f=q.toRadians(360-this.scale.positionForValue(this.start)),g=isNaN(this._transitionValue)?this.value:this._transitionValue,g=q.toRadians(360-this.scale.positionForValue(g));this._drawColorTrack(d,b,e,c,a,f,g,this.startThickness,this.endThickness,this.fill,
this.stroke,NaN)},_onMouseDown:function(d){this.inherited(arguments);var b=this.scale._gauge._gaugeToPage(this.scale.originX,this.scale.originY),b=180*Math.atan2(d.pageY-b.y,d.pageX-b.x)/Math.PI;this.set("value",this.scale.valueForPosition(b));u.stop(d)},_onMouseMove:function(d){this.inherited(arguments);var b=this.scale._gauge._gaugeToPage(this.scale.originX,this.scale.originY),b=180*Math.atan2(d.pageY-b.y,d.pageX-b.x)/Math.PI;this.set("value",this.scale.valueForPosition(b))}})});