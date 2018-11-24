JasMap.require(function () {

  var styleManager = this; //上下文环境为地图应用对象的样式管理器StyleManager对象
  var jasMap = styleManager.api; //地图应用对象
  var mapStyleTemplates = {}; //声明样式模版对象
  var getBasePath = jasMap.commonUtil.getApiRootPath; //工具方法

  //-------------------自定义样式开始---------------->>>
  // /**
  //  * 定义线路段渲染样式pipesegment_renderer_by_pipename，名称不能和其他模版里的样式名称重复
  //  *（其值可以是函数，但是函数返回值必须是Style对象）
  //  * 作用：根据要素的pipesegmentname属性字段值渲染线段
  //  * @param feature 样式如果是函数类型，该参数是要渲染的要素对象，包含pipesegmentname属性值
  //  * @returns {*}  函数返回值为样式对象
  //  */
//  mapStyleTemplates.pipesegment_renderer_by_pipename = function() {
//      if(!styleManager.pipesegment_renderer_by_pipename_style){
//          styleManager.pipesegment_renderer_by_pipename_style = {};
//      }
//      var name = feature.get('pipesegmentname');
//      if(!styleManager.pipesegment_renderer_by_pipename_style[name]){
//          var color = styleManager.randomColor();//随机颜色
//          //创建openlayers样式
//          var style = new ol.style.Style({
//              stroke: new ol.style.Stroke({
//                  color: color ,
//                  width: 6
//              })
//          });
//          //保存已经有的属性字段样式
//          styleManager.pipesegment_renderer_by_pipename_style[name] = style;
//      }
//      return styleManager.pipesegment_renderer_by_pipename_style[name]
//  };
//穿跨越
  mapStyleTemplates.crossAcross=function(feature){
	  if(!styleManager.crossAcrossStyle){
		  styleManager.crossAcrossStyle={};  
	  }
	  var name = feature.get('pipesegmentname');
	  if(!styleManager.crossAcrossStyle[name]){
		  var style = new ol.style.Style({
            stroke: new ol.style.Stroke({
                color: '#fec880' ,
                width:5
            })
        });
		  styleManager.crossAcrossStyle[name]=style;
	  }
	  return  styleManager.crossAcrossStyle[name];
  }
//钻爆隧道穿越
  mapStyleTemplates.crossDrillingBlasting=function(feature){
	  if(!styleManager.crossDrillingBlastingStyle){
		  styleManager.crossDrillingBlastingStyle={};  
	  }
	  var name = feature.get('pipesegmentname');
	  if(!styleManager.crossDrillingBlastingStyle[name]){
		  var style = new ol.style.Style({
            stroke: new ol.style.Stroke({
                color: '#f9ff00' ,
                width:5
            })
        });
		  styleManager.crossDrillingBlastingStyle[name]=style;
	  }
	  return  styleManager.crossAcrossStyle[name];
  }
//盾构隧道穿越
  mapStyleTemplates.crossShield=function(feature){
	  if(!styleManager.crossShieldStyle){
		  styleManager.crossShieldStyle={};  
	  }
	  var name = feature.get('pipesegmentname');
	  if(!styleManager.crossShieldStyle[name]){
		  var style = new ol.style.Style({
            stroke: new ol.style.Stroke({
                color: '#ccf068' ,
                width: 5
            })
        });
		  styleManager.crossShieldStyle[name]=style;
	  }
	  return  styleManager.crossShieldStyle[name];
  }//定向钻穿越
  mapStyleTemplates.crossDrilling=function(feature){
	  if(!styleManager.crossDrillingStyle){
		  styleManager.crossDrillingStyle={};  
	  }
	  var name = feature.get('pipesegmentname');
	  if(!styleManager.crossDrillingStyle[name]){
		  var style = new ol.style.Style({
            stroke: new ol.style.Stroke({
                color: '#00e0fc' ,
                width:5
            })
        });
		  styleManager.crossDrillingStyle[name]=style;
	  }
	  return  styleManager.crossDrillingStyle[name];
  }
  //箱涵穿越
  mapStyleTemplates.crossBoxCulvert=function(feature){
	  if(!styleManager.crossBoxCulvertStyle){
		  styleManager.crossBoxCulvertStyle={};  
	  }
	  var name = feature.get('pipesegmentname');
	  if(!styleManager.crossBoxCulvertStyle[name]){
		  var style = new ol.style.Style({
            stroke: new ol.style.Stroke({
                color: '#ae20ff' ,
                width:5
            })
        });
		  styleManager.crossBoxCulvertStyle[name]=style;
	  }
	  return  styleManager.crossBoxCulvertStyle[name];
  }
  //顶管穿越
  mapStyleTemplates.crossPipeJacking=function(feature){
	  if(!styleManager.crossPipeJackingStyle){
		  styleManager.crossPipeJackingStyle={};  
	  }
	  var name = feature.get('pipesegmentname');
	  if(!styleManager.crossPipeJackingStyle[name]){
		  var style = new ol.style.Style({
            stroke: new ol.style.Stroke({
                color: '#fd22d8' ,
                width: 5
            })
        });
		  styleManager.crossPipeJackingStyle[name]=style;
	  }
	  return  styleManager.crossPipeJackingStyle[name];
  }
   /**
     *管道焊口信息
     * @type {ol.style.Style}
     */
  mapStyleTemplates.constructionWeld = new ol.style.Style({
    image: new ol.style.RegularShape({
      fill: new ol.style.Fill({
        color: '#ff8845'
      }),
      stroke: new ol.style.Stroke({
        color: '#409EFF',
        width: 1
      }),
      points: 4,
      radius: 5,
      angle: Math.PI / 4
    })
  });
  //焊口返修
  mapStyleTemplates.weldrework = new ol.style.Style({
	    image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#ae01e7'
	        }),
	        stroke: new ol.style.Stroke({
	          color: '#409EFF',
	          width: 1
	        }),
	        points: 4,
	        radius: 5,
	        angle: Math.PI / 4
	      })
 });
  //管道焊接补口
  mapStyleTemplates.weldmeasured = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#82ff46'
	        }),
	        stroke: new ol.style.Stroke({
	          color: '#409EFF',
	          width: 1
	        }),
	        points: 4,
	        radius: 5,
	        angle: Math.PI / 4
	      })
  });
//管道阴保
  //绝缘件
  mapStyleTemplates.cathodicIsolatingPiece = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#fe7774'
	        }),
	        points: 4,
	        radius: 5,
	        angle: Math.PI / 2
	      })
 });
  //阴保电缆
  mapStyleTemplates.cathodicCableProtection = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#fcc982'
	        }),
	        points: 4,
	        radius: 5,
	        angle: Math.PI / 2
	      })
 });
  //牺牲阳极
  mapStyleTemplates.cathodicSacrificeAnode = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#feff7f'
	        }),
	        points: 4,
	        radius: 5,
	        angle: Math.PI / 2
	      })
 });
  //  绝缘接头保护器
  mapStyleTemplates.cathodicInsulatedJoint = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#fd22d8'
	        }),
	        points: 4,
	        radius: 5,
	        angle: Math.PI / 2
	      })
 });
  //固态去耦合器
  mapStyleTemplates.cathodicSolidDecoupler = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#caee68'
	        }),
	        points: 4,
	        radius: 5,
	        angle: Math.PI / 2
	      })
 });
  //测试桩
 mapStyleTemplates.cathodicTestStake = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#02defe'
	        }),
	        points: 4,
	        radius: 5,
	        angle: Math.PI / 2
	      })
});
 //极性排流器
 mapStyleTemplates.cathodicPolarityDrainage = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#80bbff'
	        }),
	        points: 4,
	        radius: 5,
	        angle: Math.PI / 2
	      })
});
 //辅助阳极地床
 mapStyleTemplates.cathodicAnodeBed = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#ae20ff'
	        }),
	        points: 4,
	        radius: 5,
	        angle: Math.PI / 2
	      })
});
 /**附属物**/
 //标志桩
 mapStyleTemplates.appendagesMarkStake =new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#ae20ff'
	        }),
	        radius1: 8,
    radius2: 4,
    points: 3,
	      })
});
 //电子标签
 mapStyleTemplates.appendagesElectronicLabel =new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#80bbff'
	        }),
	        radius1: 8,
      radius2: 4,
      points: 3,
	      })
});
 //手孔
 mapStyleTemplates.appendagesHandHole = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#02defe'
	        }),
	        radius1: 8,
        radius2: 4,
        points: 3,
	      })
});
 //地下障碍物
 mapStyleTemplates.appendagesObstacle =new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#caee68'
	        }),
	        radius1: 8,
          radius2: 4,
          points: 3,
	      })
});
 //水工保护
 mapStyleTemplates.appendagesHydraulicProtection =function(feature){
	  if(!styleManager.appendagesHydraulicProtectionStyle){
		  styleManager.appendagesHydraulicProtectionStyle={};  
	  }
	  var name = feature.get('pipesegmentname');
	  if(!styleManager.appendagesHydraulicProtectionStyle[name]){
		  var style = new ol.style.Style({
            stroke: new ol.style.Stroke({
                color: '#feff7f' ,
                width: 5
            })
        });
		  styleManager.appendagesHydraulicProtectionStyle[name]=style;
	  }
	  return  styleManager.appendagesHydraulicProtectionStyle[name];
  }
 //套管
 mapStyleTemplates.appendagesCasingPipe =function(feature){
	  if(!styleManager.appendagesCasingPipeStyle){
		  styleManager.appendagesCasingPipeStyle={};  
	  }
	  var name = feature.get('pipesegmentname');
	  if(!styleManager.appendagesCasingPipeStyle[name]){
		  var style = new ol.style.Style({
            stroke: new ol.style.Stroke({
                color: '#fd22d8' ,
                width: 5
            })
        });
		  styleManager.appendagesCasingPipeStyle[name]=style;
	  }
	  return  styleManager.appendagesCasingPipeStyle[name];
  }
 /**管道敷设  三角形**/
 //测量放线
 mapStyleTemplates.laySurveying = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#ae20ff'
	        }),
	        radius1: 8,
            radius2: 4,
            points: 3,
	      })
});
 //管沟开挖
 mapStyleTemplates.layPipeTrenchExcavation = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#fcc982'
	        }),
	        radius1: 8,
           radius2: 4,
           points: 3,
	      })
});
 //管沟开回填
 mapStyleTemplates.layPipeTrenchBackfill = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#80bbff'
	        }),
	        radius1: 8,
           radius2: 4,
           points: 3,
	      })
});
 //地貌恢复
 mapStyleTemplates.layLandRestoration = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#feff7f'
	        }),
	        radius1: 8,
           radius2: 4,
           points: 3,
	      })
});
 //保温
 mapStyleTemplates.layThermalInsulation = new ol.style.Style({
	  image: new ol.style.RegularShape({
	        fill: new ol.style.Fill({
	          color: '#82ff46'
	        }),
	        radius1: 8,
           radius2: 4,
           points: 3,
	      })
});
 //中线桩连线   
 mapStyleTemplates.medianStakePolyline=function(feature){
	 console.log(feature);
	 var colorArr = ['#CC0000','#00FF00','#CC33FF','#CCFF00','#FF66FF','#FFFF00','#00FFFF','#00FFCC'];
	  if(!styleManager.medianStakePolylineStyle){
		  styleManager.medianStakePolylineStyle={};  
	  }
	  var name = feature.get('pipeline_oid');
	  console.log(name);
	  if(!styleManager.medianStakePolylineStyle[name]){
		  var style = new ol.style.Style({
           stroke: new ol.style.Stroke({
               color: colorArr[random(0,8)] ,
               width:5
           })
       });
		  styleManager.medianStakePolylineStyle[name]=style;
	  }
	  return  styleManager.medianStakePolylineStyle[name];
 }
 

  //<<<-------------------自定义样式结束----------------ss
  return mapStyleTemplates; //返回样式模版对象
});


 function random (min, max) {
	var Range = max - min;
	var Rand = Math.random();
	var num = min + Math.round(Rand * Range); //四舍五入
	// console.log(num);
	return num;
}