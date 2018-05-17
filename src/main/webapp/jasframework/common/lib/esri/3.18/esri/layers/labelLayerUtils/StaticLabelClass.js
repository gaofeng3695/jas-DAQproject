// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/layers/labelLayerUtils/StaticLabelClass","dojo/_base/declare dojo/_base/lang dojo/has ../../kernel ../../geometry/Extent ../../geometry/Point ../../geometry/Polygon".split(" "),function(B,D,E,F,z,C,A){return B(null,{declaredClass:"esri.layers.labelLayerUtils.StaticLabel",constructor:function(){this._preparedLabels=[];this._placedLabels=[];this._extent=null;this._ymax=this._ymin=this._xmax=this._xmin=0;this._scale=1;this._LINE_STEP_CONST=1.5;this._POLYGON_X_STEP_CONST=1;this._POLYGON_Y_STEP_CONST=
0.75;this._OVERRUN=2},setMap:function(b,d){this._labelLayer=d;this._map=b;this._xmin=b.extent.xmin;this._xmax=b.extent.xmax;this._ymin=b.extent.ymin;this._ymax=b.extent.ymax;this._scale=(this._xmax-this._xmin)/b.width},_process:function(b){var d,a,k,c,g,l,e,f,n;this._preparedLabels=b;this._placedLabels=[];for(b=this._preparedLabels.length-1;0<=b;b--){d=this._preparedLabels[b];g=d.labelWidth;l=d.labelHeight;f=(e=d.options)&&e.lineLabelPlacement?e.lineLabelPlacement:"PlaceAtCenter";n=e&&e.lineLabelPosition?
e.lineLabelPosition:"Above";a=e&&e.labelRotation?e.labelRotation:!0;k=d.angle*(Math.PI/180);c=e&&e.howManyLabels?e.howManyLabels:"OneLabel";var p=[];if("point"===d.geometry.type)this._generatePointPositions(d.geometry.x,d.geometry.y,d.text,k,g,l,d.symbolWidth,d.symbolHeight,e,p);else if("multipoint"===d.geometry.type)for(a=0;a<d.geometry.points.length;a++)this._generatePointPositions(d.geometry.points[a][0],d.geometry.points[a][1],d.text,k,g,l,d.symbolWidth,d.symbolHeight,e,p);else if("polyline"===
d.geometry.type)if("PlaceAtStart"===f)this._generateLinePositionsPlaceAtStart(d.geometry,!0,d.text,g,l,2*d.symbolHeight+l,f,n,a,p);else if("PlaceAtEnd"===f)this._generateLinePositionsPlaceAtEnd(d.geometry,!0,d.text,g,l,2*d.symbolHeight+l,f,n,a,p);else{e=[];var h=d.geometry.getExtent(),q=this._map.extent;if(h.getWidth()<g*this._scale/this._OVERRUN&&h.getHeight()<g*this._scale/this._OVERRUN)continue;else 0.5*h.getWidth()<q.getWidth()&&0.5*h.getHeight()<q.getHeight()?(h=0.1*Math.min(this._map.width,
this._map.height)*this._scale,this._generateLinePositionsPlaceAtCenter(d.geometry,!1,h,d.text,g,l,2*d.symbolHeight+l,f,n,a,e)):(h=0.2*Math.min(this._map.width,this._map.height)*this._scale,this._generateLinePositionsPlaceAtCenter(d.geometry,!0,h,d.text,g,l,2*d.symbolHeight+l,f,n,a,e));this._postSorting(q,e,p)}else if("polygon"===d.geometry.type){f=[];for(a=0;a<d.geometry.rings.length;a++)n=d.geometry.rings[a],A.prototype.isClockwise(n)&&(e=this._calcRingExtent(n),e.xmax-e.xmin<4*g*this._scale/this._OVERRUN&&
e.ymax-e.ymin<4*l*this._scale/this._OVERRUN||f.push(n));f.sort(function(a,b){return b.length-a.length});for(a=0;a<f.length;a++)this._generatePolygonPositionsForManyLabels(f[a],d.geometry.spatialReference,d.text,k,g,l,p)}for(a=0;a<p.length&&!(f=p[a].x,n=p[a].y,void 0!==p[a].angle&&(k=p[a].angle),e=this._findPlace(d,d.text,f,n,k,g,l),"OneLabel"===c&&e&&this._labelLayer._isWithinScreenArea(new C(f,n,d.geometry.spatialReference)));a++);}return this._placedLabels},_generatePointPositions:function(b,d,
a,k,c,g,l,e,f,n){a=f&&f.pointPriorities?f.pointPriorities:"AboveRight";c=(l+c)*this._scale;g=(e+g)*this._scale;switch(a.toLowerCase()){case "aboveleft":b-=c;d+=g;break;case "abovecenter":d+=g;break;case "aboveright":b+=c;d+=g;break;case "centerleft":b-=c;break;case "centercenter":break;case "centerright":b+=c;break;case "belowleft":b-=c;d-=g;break;case "belowcenter":d-=g;break;case "belowright":b+=c;d-=g;break;default:return}n.push({x:b,y:d})},_generateLinePositionsPlaceAtStart:function(b,d,a,k,c,
g,l,e,f,n){l=k*this._scale;var p=this._LINE_STEP_CONST*Math.min(this._map.width,this._map.height)*this._scale,h,q,s,r,u,v,m,w;for(h=0;h<b.paths.length;h++){var t=b.paths[h],x=l,y=0;for(q=0;q<t.length-1;q++)s=t[q][0],r=t[q][1],u=t[q+1][0],v=t[q+1][1],m=u-s,w=v-r,m=Math.sqrt(m*m+w*w),y+m>x?(y=this._generatePositionsOnLine(b.spatialReference,d,x,p,y,s,r,u,v,a,k,c,g,e,f,n),x=p):y+=m}},_generateLinePositionsPlaceAtEnd:function(b,d,a,k,c,g,l,e,f,n){l=k*this._scale;var p=this._LINE_STEP_CONST*Math.min(this._map.width,
this._map.height)*this._scale,h,q,s,r,u,v,m,w;for(h=0;h<b.paths.length;h++){var t=b.paths[h],x=l,y=0;for(q=t.length-2;0<=q;q--)s=t[q+1][0],r=t[q+1][1],u=t[q][0],v=t[q][1],m=u-s,w=v-r,m=Math.sqrt(m*m+w*w),y+m>x?(y=this._generatePositionsOnLine(b.spatialReference,d,x,p,y,s,r,u,v,a,k,c,g,e,f,n),x=p):y+=m}},_generateLinePositionsPlaceAtCenter:function(b,d,a,k,c,g,l,e,f,n,p){var h,q,s,r,u,v,m,w;for(e=0;e<b.paths.length;e++){var t=b.paths[e];if(!(2>t.length)){if(2==t.length){r=t[0];h=t[1];s=r[0];r=r[1];
t=h[0];v=h[1];u=(t-s)*(t-s)+(v-r)*(v-r);m=Math.atan2(v-r,t-s);v=Math.cos(m);m=Math.sin(m);t=[];w=s;for(var x=r;(w-s)*(w-s)+(x-r)*(x-r)<u;)t.push([w,x]),w+=a/2*v,x+=a/2*m;t.push(h)}var y=0;for(h=0;h<t.length-1;h++)s=t[h][0],r=t[h][1],u=t[h+1][0],v=t[h+1][1],m=u-s,w=v-r,y+=Math.sqrt(m*m+w*w);for(h=x=0;h<t.length-1;h++){s=t[h][0];r=t[h][1];u=t[h+1][0];v=t[h+1][1];m=u-s;w=v-r;m=Math.sqrt(m*m+w*w);if(x+m>y/2)break;x+=m}h==t.length-1&&h--;s=t[h][0];r=t[h][1];u=t[h+1][0];v=t[h+1][1];m=u-s;w=v-r;x=y/2-x;
m=Math.atan2(w,m);w=s+x*Math.cos(m);m=r+x*Math.sin(m);s=this._angleAndShifts(s,r,u,v,l,f,n);p.push({x:w+s.shiftX,y:m+s.shiftY,angle:s.angle});var y=w,z=m,x=0;for(q=h;q<t.length-1;q++)q==h?(s=y,r=z):(s=t[q][0],r=t[q][1]),u=t[q+1][0],v=t[q+1][1],m=u-s,w=v-r,m=Math.sqrt(m*m+w*w),x=x+m>a?this._generatePositionsOnLine(b.spatialReference,d,a,a,x,s,r,u,v,k,c,g,l,f,n,p):x+m;x=0;for(q=h;0<=q;q--)q==h?(s=y,r=z):(s=t[q+1][0],r=t[q+1][1]),u=t[q][0],v=t[q][1],m=u-s,w=v-r,m=Math.sqrt(m*m+w*w),x=x+m>a?this._generatePositionsOnLine(b.spatialReference,
d,a,a,x,s,r,u,v,k,c,g,l,f,n,p):x+m}}},_generatePositionsOnLine:function(b,d,a,k,c,g,l,e,f,n,p,h,q,s,r,u){n=Math.atan2(f-l,e-g);p=g;h=l;var v=p,m=h;do if(c=a-c,p+=c*Math.cos(n),h+=c*Math.sin(n),this._belongs(p,h,g,l,e,f))c=this._angleAndShifts(g,l,e,f,q,s,r),a=p+c.shiftX,m=h+c.shiftY,d?this._labelLayer._isWithinScreenArea(new z(a,m,a,m,b))&&u.push({x:a,y:m,angle:c.angle}):u.push({x:a,y:m,angle:c.angle}),v=p,m=h,c=0,a=k;else return b=e-v,f-=m,Math.sqrt(b*b+f*f);while(1)},_postSorting:function(b,d,a){if(b&&
0<d.length){var k=0.5*(b.xmin+b.xmax);b=0.5*(b.ymin+b.ymax);for(var c=d[0].x,g=d[0].y,l=Math.sqrt((c-k)*(c-k)+(g-b)*(g-b)),e=d[0].angle,f=0;f<d.length;f++){var n=d[f].x,p=d[f].y,h=Math.sqrt((n-k)*(n-k)+(p-b)*(p-b));h<l&&(c=n,g=p,l=h,e=d[f].angle)}a.push({x:c,y:g,angle:e})}},_belongs:function(b,d,a,k,c,g){if(c==a&&g==k)return!1;if(c>a){if(b>c||b<a)return!1}else if(b<c||b>a)return!1;if(g>k){if(d>g||d<k)return!1}else if(d<g||d>k)return!1;return!0},_angleAndShifts:function(b,d,a,k,c,g,l){for(b=Math.atan2(k-
d,a-b);b>Math.PI/2;)b-=Math.PI;for(;b<-(Math.PI/2);)b+=Math.PI;k=Math.sin(b);var e=Math.cos(b);a=d=0;"Above"==g&&(d=c*k*this._scale,a=c*e*this._scale);"Below"==g&&(d=-c*k*this._scale,a=-c*e*this._scale);c=[];c.angle=l?-b:0;c.shiftX=-d;c.shiftY=a;return c},_generatePolygonPositionsForManyLabels:function(b,d,a,k,c,g,l){c=this._findCentroidForRing(b);k=c[0];var e=c[1],f=this._calcRingExtent(b);c=f.xmin;g=f.ymin;var n=f.xmax,f=f.ymax,p=(f-g)/(this._map.height*this._scale);if(!(10<(n-c)/(this._map.width*
this._scale)&&10<p)){var h=!0;if(n-c>this._map.width*this._scale||f-g>this._map.height*this._scale)h=!1;var p=this._map.width*this._scale*(h?0.1875:0.5),h=this._map.height*this._scale*(h?0.1875:0.5),q=!0,s=!0,r=0;do{e+=(r%2?-1:1)*r*h;if(this._scanRingByX(a,d,b,k,e,c,n,p,l))break;e<g&&(q=!1);e>f&&(s=!1);r++}while(q||s)}},_scanRingByX:function(b,d,a,k,c,g,l,e,f){var n=!0,p=!0,h=0,q=1E3;do{k+=(h%2?-1:1)*h*e;var s=this._movePointInsideRing(a,k,c),r=this._labelLayer._isWithinScreenArea(new z(s,c,s,c,d)),
u=this._isPointWithinRing(b,a,s,c);if(r&&u)return f.push({x:s,y:c}),!0;k<g&&(n=!1);k>l&&(p=!1);h++;q--;if(0>=q)return!0}while(n||p);return!1},_movePointInsideRing:function(b,d,a){for(var k=[],c=b.length-1,g=b[0][1]>=b[c][1]?1:-1,l=0;l<=c;l++){var e=l,f=l+1;l==c&&(f=0);var n=b[e][0],e=b[e][1],p=b[f][0],f=b[f][1],h=f>=e?1:-1;if(e<=a&&a<=f||f<=a&&a<=e)a!=e&&a!=f?(k.push((a-e)*(p-n)/(f-e)+n),g=h):a==e&&a!=f?(g!=h&&k.push(n),g=h):a!=e&&a==f?(k.push(p),g=h):a==e&&a==f&&(1==g&&k.push(n),k.push(p),g=h)}k.sort(function(a,
b){return a-b});b=k.length;if(0<b){for(l=a=d=0;l<b-1;l+=2)c=Math.abs(k[l+1]-k[l]),c>d&&(d=c,a=l);d=(k[a]+k[a+1])/2}return d},_calcRingExtent:function(b){var d,a;a=new z;for(d=0;d<b.length-1;d++){var k=b[d][0],c=b[d][1];if(void 0===a.xmin||k<a.xmin)a.xmin=k;if(void 0===a.ymin||c<a.ymin)a.ymin=c;if(void 0===a.xmax||k>a.xmax)a.xmax=k;if(void 0===a.ymax||c>a.ymax)a.ymax=c}return a},_isPointWithinPolygon:function(b,d,a,k){var c;for(c=0;c<d.rings.length;c++)if(this._isPointWithinRing(b,d.rings[c],a,k))return!0;
return!1},_isPointWithinRing:function(b,d,a,k){var c,g,l,e,f=[],n=d.length;for(b=0;b<n-1;b++)if(c=d[b][0],g=d[b][1],l=d[b+1][0],e=d[b+1][1],!(c==l&&g==e)){if(g==e)if(k==g)f.push(c);else continue;c==l?(g<e&&(k>=g&&k<e)&&f.push(c),g>e&&(k<=g&&k>e)&&f.push(c)):(g=(l-c)/(e-g)*(k-g)+c,c<l&&(g>=c&&g<l)&&f.push(g),c>l&&(g<=c&&g>l)&&f.push(g))}f.sort(function(a,b){return a-b});for(b=0;b<f.length-1;b++)if(c=f[b],l=f[b+1],a>=c&&a<l)if(b%2)break;else return!0;return!1},_findCentroidForRing:function(b){for(var d=
b.length,a=[0,0],k=0,c=b[0][0],g=b[0][1],l=1;l<d-1;l++){var e=b[l][0],f=b[l][1],n=b[l+1][0],p=b[l+1][1],h=(e-c)*(p-g)-(n-c)*(f-g);a[0]+=h*(c+e+n);a[1]+=h*(g+f+p);k+=h}a[0]/=3*k;a[1]/=3*k;return a},_findCentroidForFeature:function(b){for(var d=0,a=[0,0],k=0;k<b.rings.length;k++)for(var c=b.rings[k],g=c.length,l=c[0][0],e=c[0][1],f=1;f<g-1;f++){var n=c[f][0],p=c[f][1],h=c[f+1][0],q=c[f+1][1],s=(n-l)*(q-e)-(h-l)*(p-e);a[0]+=s*(l+n+h);a[1]+=s*(e+p+q);d+=s}a[0]/=3*d;a[1]/=3*d;return a},_findPlace:function(b,
d,a,k,c,g,l){if(isNaN(a)||isNaN(k))return!1;for(var e=0;e<this._placedLabels.length;e++){var f=this._placedLabels[e].angle,n=this._placedLabels[e].width*this._scale,p=this._placedLabels[e].height*this._scale,h=this._placedLabels[e].x-a,q=this._placedLabels[e].y-k;if(0===c&&0===f){if(this._findPlace2(-g*this._scale,-l*this._scale,g*this._scale,l*this._scale,h-n,q-p,h+n,q+p))return!1}else{var s=new z(-g*this._scale,-l*this._scale,g*this._scale,l*this._scale,null),r=0,u=1;0!==c&&(r=Math.sin(c),u=Math.cos(c));
var v=h*u-q*r,h=h*r+q*u,f=f-c,r=Math.sin(f),u=Math.cos(f),m=-n*u- -p*r,q=-n*r+-p*u,f=+n*u- -p*r,w=+n*r+-p*u,n=v+m,p=h-q,r=v+f,u=h-w,m=v-m,q=h+q,v=v-f,h=h+w,f=new A;f.addRing([[n,p],[r,u],[m,q],[v,h],[n,p]]);if(s.intersects(f))return!1}}for(;c>Math.PI/2;)c-=Math.PI;for(;c<-(Math.PI/2);)c+=Math.PI;e={};e.layer=b;e.text=d;e.angle=c;e.x=a;e.y=k;e.width=g;e.height=l;this._placedLabels.push(e);return!0},_findPlace2:function(b,d,a,k,c,g,l,e){return(b>=c&&b<=l||a>=c&&a<=l||b<=c&&a>=l)&&(d>=g&&d<=e||k>=g&&
k<=e||d<=g&&k>=e)?!0:!1}})});