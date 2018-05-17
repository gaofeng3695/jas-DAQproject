// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/layers/vectorTiles/core/promiseUtils",["require","exports","dojo/Deferred","./Error"],function(k,d,f,h){d.eachAlways=function(b){var a=new f,e=[],c=b.length;0===c&&a.resolve(e);b.forEach(function(b){var g={promise:b};e.push(g);b.then(function(a){g.value=a}).otherwise(function(a){g.error=a}).then(function(){--c;0===c&&a.resolve(e)})});return a.promise};d.create=function(b){var a=new f;b(function(b){void 0===b&&(b=null);return a.resolve(b)},a.reject);return a.promise};d.reject=function(b){var a=
new f;a.reject(b);return a.promise};d.resolve=function(b){void 0===b&&(b=null);var a=new f;a.resolve(b);return a.promise};d.after=function(b,a){void 0===a&&(a=null);var e=0,c=new f(function(){e&&(clearTimeout(e),e=0)}),e=setTimeout(function(){c.resolve(a)},b);return c.promise};d.timeout=function(b,a,e){var c=0,d=new f(b.cancel);b.then(function(a){d.isFulfilled()||(d.resolve(a),c&&(clearTimeout(c),c=0))});b.otherwise(function(a){d.isFulfilled()||(d.reject(a),c&&(clearTimeout(c),c=0))});c=setTimeout(function(){d.reject(e||
h("promiseUtils:timeout","The wrapped promise did not resolve within "+a+" ms"))},a);return d.promise}});