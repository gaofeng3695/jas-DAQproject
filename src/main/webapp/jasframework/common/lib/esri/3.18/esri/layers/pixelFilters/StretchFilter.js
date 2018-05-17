// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/layers/pixelFilters/StretchFilter",["dojo/_base/declare","dojo/_base/lang","../PixelBlock"],function(w,x,y){var e;return w(null,{stretchType:0,min:0,max:255,numberOfStandardDeviations:2,statistics:null,histograms:null,dra:!1,minPercent:0.25,maxPercent:0.5,useGamma:!1,gamma:null,raster:null,outputPixelType:"U8",constructor:function(b){this.stretchType=b.stretchType||b.StretchType||this.stretchType;this.min=b.min||b.Min||this.min;this.max=b.max||b.Max||this.max;this.numberOfStandardDeviations=
b.numberOfStandardDeviations||b.NumberOfStandardDeviations||this.numberOfStandardDeviations;this.statistics=b.statistics||b.Statistics||this.statistics;this.dra=b.dra||b.DRA||b.dRA||this.dra;this.minPercent=b.minPercent||b.MinPercent||this.minPercent;this.maxPercent=b.maxPercent||b.MaxPercent||this.maxPercent;this.useGamma=b.useGamma||b.UseGamma||this.useGamma;this.gamma=b.gamma||b.Gamma||this.gamma;this.raster=b.raster||b.Raster||this.raster;this.outputPixelType=b.outputPixelType||b.OutputPixelType||
this.outputPixelType;this.raster=b.raster||b.Raster||this.raster;e=this},filter:function(b){if(!(null===b||null===b.pixelBlock||null===b.pixelBlock.pixels)){var d=b.pixelBlock,f=d.pixels,k=d.width*d.height,r=f.length,g;(g=6===e.stretchType||3===e.stretchType&&e.dra)&&e._calculateStatisticsHistograms(b);if(e.dra)if(g)e._statistics=d.statistics,e._histograms=d.histograms;else{g=d.statistics;e._statistics=[];for(d=0;d<r;d++)e._statistics[d]=[g[d].minValue,g[d].maxValue,0,0]}else e._statistics=e.statistics,
e._histograms=e.histograms;e._createLUT(b);if(void 0===e.LUT||null===e.LUT)return e._filterNoLUT(b);for(var l=e.LUT,a=e.LUTOffset,c,h,d=0;d<r;d++){h=l[d];for(g=0;g<k;g++)c=f[d][g],f[d][g]=h[c-a]}b.pixelBlock.pixelType="U8";return b}},_calculateStatisticsHistograms:function(b){b=b.pixelBlock;var d=b.pixelType,e=b.pixels,k=b.mask,r=e.length,g,l,a,c,h=function(a){this.min=-0.5;this.max=255.5;this.size=256;x.mixin(this,a);this.counts=this.counts||new Uint32Array(this.size)},u=[],m,s,n,p,t,q;for(a=0;a<
r;a++){m=new h;s=m.counts;p=e[a];if("U8"===d)if(k)for(c=0;c<b.width*b.height;c++)k[c]&&s[p[c]]++;else for(c=0;c<b.width*b.height;c++)s[p[c]]++;else{g=b.statistics[a].minValue;l=b.statistics[a].maxValue;m.min=g;m.max=l;l=(l-g)/256;n=new Uint32Array(257);if(k)for(c=0;c<b.width*b.height;c++)k[c]&&n[Math.floor((p[c]-g)/l)]++;else for(c=0;c<b.width*b.height;c++)n[Math.floor((p[c]-g)/l)]++;for(c=0;255>c;c++)s[c]=n[c];s[255]=n[255]+n[256]}u.push(m);p=[];g=b.statistics[a].minValue;l=b.statistics[a].maxValue;
for(c=q=t=n=0;c<m.size;c++)n+=s[c],t+=c*s[c];t/=n;for(c=0;c<m.size;c++)q+=s[c]*Math.pow(c-t,2);s=Math.sqrt(q/(n-1));c=(t+0.5)*(m.max-m.min)/m.size+m.min;m=s*(m.max-m.min)/m.size;p.push(g);p.push(l);p.push(c);p.push(m);b.statistics[a]=p}b.histograms=u},_getCutOffPoints:function(b){b=b.pixelBlock.pixels.length;var d=[],f=[],k,r,g,l,a,c;switch(e.stretchType){case 5:for(a=0;a<b;a++)d[a]=e._statistics[a][0],f[a]=e._statistics[a][1];break;case 3:for(a=0;a<b;a++)d[a]=e._statistics[a][2]-e.numberOfStandardDeviations*
e._statistics[a][3],f[a]=e._statistics[a][2]+e.numberOfStandardDeviations*e._statistics[a][3],d[a]<e._statistics[a][0]&&(d[a]=e._statistics[a][0]),f[a]>e._statistics[a][1]&&(f[a]=e._statistics[a][1]);break;case 6:for(a=0;a<b;a++){k=e._histograms[a];l=new Uint32Array(k.size);g=k.counts;for(c=r=0;c<k.size;c++)r+=g[c],l[c]=r;g=e.minPercent*r/100;for(c=1;c<k.size;c++)if(l[c]>g){d[a]=k.min+(k.max-k.min)/k.size*(c-0.5);break}g=(1-e.maxPercent/100)*r;for(c=k.size-2;0<=c;c--)if(l[c]<g){f[a]=k.min+(k.max-
k.min)/k.size*(c+0.5);break}}break;default:for(a=0;a<b;a++)d[a]=0,f[a]=255}return{minCutOff:d,maxCutOff:f}},_createLUT:function(b){var d=b.pixelBlock,f=d.pixelType;if("U8"===f||"U16"===f||"S8"===f||"S16"===f){if(e._LUTSignature&&(f=e._computeLutSignature(),f.length===e._LUTSignature.length&&!f.some(function(a,b){return a!==e._LUTSignature[b]})))return;var f=d.pixels.length,k=[],r=[],g=[],l,a,c,h=e.max,u=e.min,m=e.gamma,s=h-u,n=e._getCutOffPoints(b);b=n.minCutOff;var n=n.maxCutOff,p=0,t=256,q;"S8"===
d.pixelType?p=-127:"S16"===d.pixelType&&(p=-32767);if("U16"===d.pixelType||"S16"===d.pixelType)t=65536;for(d=0;d<f;d++)k[d]=n[d]-b[d];if(e.useGamma&&null!==m&&m.length===f)for(d=0;d<f;d++)g[d]=1<m[d]?2<m[d]?6.5+Math.pow(m[d]-2,2.5):6.5+100*Math.pow(2-m[d],4):1;if(e.useGamma)for(var v,d=0;d<f;d++){q=[];for(l=0;l<t;l++)a=l+p,v=(a-b[d])/k[d],c=1,1<m[d]&&(c-=Math.pow(1/s,v*g[d])),a<n[d]&&a>b[d]?q[l]=Math.floor(c*s*Math.pow(v,1/m[d]))+u:a>n[d]?q[l]=h:a<b[d]&&(q[l]=u);r[d]=q}else for(d=0;d<f;d++){q=[];
for(l=0;l<t;l++)a=l+p,q[l]=a<b[d]?u:a>n[d]?h:Math.floor((a-b[d])/k[d]*s)+u;r[d]=q}e.LUT=r;e.LUTOffset=p;e._LUTSignature=e._computeLutSignature()}},_computeLutSignature:function(){var b=[],d,f;b.push(e.stretchType);b.push(e.min);b.push(e.max);b.push(e.numberOfStandardDeviations);if(e._statistics)for(d=0;d<e._statistics.length;d++)for(f=0;f<e._statistics[d].length;f++)b.push(e._statistics[d][f]);b.push(e.dra?1:0);b.push(e.minPercent);b.push(e.maxPercent);if(e.gamma)for(d=0;d<e._statistics.length;d++)b.push(e.gamma[d]);
b.push(e.useGamma?1:0);return b},_filterNoLUT:function(b){if(!(null===b||null===b.pixelBlock||null===b.pixelBlock.pixels)){var d=b.pixelBlock,f=d.pixels,k=d.mask,d=d.width*d.height,r=f.length,g=[],l=[],a,c,h,u,m,s=e.max,n=e.min,p=e.gamma,t=s-n;a=e._getCutOffPoints(b);var q=a.minCutOff,v=a.maxCutOff;for(a=0;a<r;a++)g[a]=v[a]-q[a];if(e.useGamma&&null!==p&&p.length===r)for(a=0;a<r;a++)l[a]=1<p[a]?2<p[a]?6.5+Math.pow(p[a]-2,2.5):6.5+100*Math.pow(2-p[a],4):1;if(e.useGamma)if(void 0!==k&&null!==k)for(c=
0;c<d;c++){if(k[c])for(a=0;a<r;a++)h=f[a][c],m=(h-q[a])/g[a],u=1,1<p[a]&&(u-=Math.pow(1/t,m*l[a])),h<v[a]&&h>q[a]?f[a][c]=Math.floor(u*t*Math.pow(m,1/p[a]))+n:h>v[a]?f[a][c]=s:h<q[a]&&(f[a][c]=n)}else for(c=0;c<d;c++)for(a=0;a<r;a++)h=f[a][c],m=(h-q[a])/g[a],u=1,1<p[a]&&(u-=Math.pow(1/t,m*l[a])),h<v[a]&&h>q[a]?f[a][c]=Math.floor(u*t*Math.pow(m,1/p[a]))+n:h>v[a]?f[a][c]=s:h<q[a]&&(f[a][c]=n);else if(void 0!==k&&null!==k)for(c=0;c<d;c++){if(k[c])for(a=0;a<r;a++)h=f[a][c],h<v[a]&&h>q[a]?f[a][c]=Math.floor((h-
q[a])/g[a]*t)+n:h>v[a]?f[a][c]=s:h<q[a]&&(f[a][c]=n)}else for(c=0;c<d;c++)for(a=0;a<r;a++)h=f[a][c],h<v[a]&&h>q[a]?f[a][c]=Math.floor((h-q[a])/g[a]*t)+n:h>v[a]?f[a][c]=s:h<q[a]&&(f[a][c]=n);b.pixelBlock.pixelType="U8";return b}}})});