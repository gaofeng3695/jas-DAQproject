//>>built
(function(h,b){"object"===typeof exports&&"undefined"!==typeof module&&"function"===typeof require?b(require("../moment")):"function"===typeof define&&define.amd?define("moment/locale/lt",["../moment"],b):b(h.moment)})(this,function(h){function b(a,b,c,f){return b?d(c)[0]:f?d(c)[1]:d(c)[2]}function d(a){return k[a].split("_")}function e(a,e,c,f){var g=a+" ";return 1===a?g+b(a,e,c[0],f):e?g+(0===a%10||10<a&&20>a?d(c)[1]:d(c)[0]):f?g+d(c)[1]:g+(0===a%10||10<a&&20>a?d(c)[1]:d(c)[2])}var k={m:"minut\u0117_minut\u0117s_minut\u0119",
mm:"minut\u0117s_minu\u010di\u0173_minutes",h:"valanda_valandos_valand\u0105",hh:"valandos_valand\u0173_valandas",d:"diena_dienos_dien\u0105",dd:"dienos_dien\u0173_dienas",M:"m\u0117nuo_m\u0117nesio_m\u0117nes\u012f",MM:"m\u0117nesiai_m\u0117nesi\u0173_m\u0117nesius",y:"metai_met\u0173_metus",yy:"metai_met\u0173_metus"};return h.defineLocale("lt",{months:{format:"sausio vasario kovo baland\u017eio gegu\u017e\u0117s bir\u017eelio liepos rugpj\u016b\u010dio rugs\u0117jo spalio lapkri\u010dio gruod\u017eio".split(" "),
standalone:"sausis vasaris kovas balandis gegu\u017e\u0117 bir\u017eelis liepa rugpj\u016btis rugs\u0117jis spalis lapkritis gruodis".split(" "),isFormat:/D[oD]?(\[[^\[\]]*\]|\s+)+MMMM?|MMMM?(\[[^\[\]]*\]|\s+)+D[oD]?/},monthsShort:"sau vas kov bal geg bir lie rgp rgs spa lap grd".split(" "),weekdays:{format:"sekmadien\u012f pirmadien\u012f antradien\u012f tre\u010diadien\u012f ketvirtadien\u012f penktadien\u012f \u0161e\u0161tadien\u012f".split(" "),standalone:"sekmadienis pirmadienis antradienis tre\u010diadienis ketvirtadienis penktadienis \u0161e\u0161tadienis".split(" "),
isFormat:/dddd HH:mm/},weekdaysShort:"Sek Pir Ant Tre Ket Pen \u0160e\u0161".split(" "),weekdaysMin:"S P A T K Pn \u0160".split(" "),weekdaysParseExact:!0,longDateFormat:{LT:"HH:mm",LTS:"HH:mm:ss",L:"YYYY-MM-DD",LL:"YYYY [m.] MMMM D [d.]",LLL:"YYYY [m.] MMMM D [d.], HH:mm [val.]",LLLL:"YYYY [m.] MMMM D [d.], dddd, HH:mm [val.]",l:"YYYY-MM-DD",ll:"YYYY [m.] MMMM D [d.]",lll:"YYYY [m.] MMMM D [d.], HH:mm [val.]",llll:"YYYY [m.] MMMM D [d.], ddd, HH:mm [val.]"},calendar:{sameDay:"[\u0160iandien] LT",
nextDay:"[Rytoj] LT",nextWeek:"dddd LT",lastDay:"[Vakar] LT",lastWeek:"[Pra\u0117jus\u012f] dddd LT",sameElse:"L"},relativeTime:{future:"po %s",past:"prie\u0161 %s",s:function(a,b,c,d){return b?"kelios sekund\u0117s":d?"keli\u0173 sekund\u017ei\u0173":"kelias sekundes"},m:b,mm:e,h:b,hh:e,d:b,dd:e,M:b,MM:e,y:b,yy:e},ordinalParse:/\d{1,2}-oji/,ordinal:function(a){return a+"-oji"},week:{dow:1,doy:4}})});