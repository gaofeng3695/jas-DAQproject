var stationConfig = {
  pipePressureTest: {
    addUrl: "/jdbc/commonData/pipePressureTest/save.do",
    updateUrl: "/jdbc/commonData/pipePressureTest/update.do",
    deleteUrl: "/jdbc/commonData/pipePressureTest/delete.do",
    detailUrl: "/jdbc/commonData/pipePressureTest/getPage.do",
    getList: "/jdbc/commonData/pipePressureTest/getPage.do",
    searchFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid',
      'startMedianStakeOid',
      'endMedianStakeOid',
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'startMedianStakeCode',
      'startRelativeMileage',
      'endMedianStakeCode',
      'endRelativeMileage',
      'pressureTestLength',
      'pipeSpecification',
      'pressureTestMedium',
      'designPressure',
      'pressureTestDate',
      'processDescription',
      'pressureTestResult',
      'constructUnitName',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeStationOid",
      'constructUnit',
      'supervisionUnit',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
    ],
    addFields1: [ //
      'startMedianStakeOid',
      'startRelativeMileage',
      'endMedianStakeOid',
      'endRelativeMileage',
    ],
    addFields2: [ //
      'pressureTestLength',
      'pipeSpecification',
      'pressureTestMedium',
      'designPressure',
      'pressureTestDate',
      'processDescription',
      'pressureTestResult',
    ],
    addNames: ["基本信息", "位置信息", "试压信息"],
    detailFields0: [ //
      "projectName",
      "tendersName",
      "pipelineName",
      "pipeStationName",
      'constructUnitName',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
      'approveStatus'
    ],
    detailFields1: [ //
      'startMedianStakeCode',
      'startRelativeMileage',
      'endMedianStakeCode',
      'endRelativeMileage',
    ],
    detailFields2: [ //
      'pressureTestLength',
      'pipeSpecification',
      'pressureTestMedium',
      'designPressure',
      'pressureTestDate',
      'processDescription',
      'pressureTestResult',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['tendersOid'],
        childUrl: ['/daq/privilege/getTendersList.do'],
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      tendersOid: {
        type: 'select',
        name: '标段名称',
        childSelect: ['pipelineOid', 'supervisionUnit'],
        childUrl: ['/daq/privilege/getPipelineListByTendersOid.do', '/daq/privilege/getSupervisionUnitByTendersOid.do'],
        isRequired: true,
        isInit: true
      },
      pipelineOid: {
        type: 'select',
        name: '管线名称',
        childSelect: ['pipeStationOid', 'startMedianStakeOid', 'endMedianStakeOid'],
        childUrl: ['/daq/privilege/getPipeStationListByPipelineOid.do', '/daq/privilege/getMedianStakeList.do', '/daq/privilege/getMedianStakeList.do'],
        isRequired: true,
        isInit: true
      },
      tendersName: {
        name: '标段名称',
      },
      pipelineName: {
        name: '管线名称',
      },
      pipeStationOid: {
        type: 'select',
        name: '站场/阀室编号',
        isRequired: true,
        isInit: true
      },
      pipeStationName: {
        name: '站场/阀室编号',
      },
      constructUnit: {
        type: 'select',
        name: '施工单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '施工单位',
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }],
      },
      supervisionUnitName: {
        type: 'input',
        name: '监理单位',
        isRequired: true
      },
      supervisionEngineer: {
        type: 'input',
        name: '监理工程师',
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }],
      },
      collectionDate: {
        type: "date",
        name: "采集日期",
      },
      approveStatus: {
        type: 'input',
        name: '审核状态',
        formatter: function (a, b, value, c) {
          if (value == -1) return '驳回';
          if (value == 1) return '待审核';
          if (value == 2) return '通过';
          return '未上报';
        }
      },
      remarks: {
        name: "备注"
      },
      startMedianStakeOid: {
        type: 'select',
        name: '起始桩号',
        isRequired: true
      },
      startMedianStakeCode: {
        name: '起始桩号',
      },
      startRelativeMileage: {
        type: "number",
        name: "相对起始桩位置(m)",
        isRequired: true,
        max: 9999999.99,
        precision: 2
      },
      endMedianStakeOid: {
        type: 'select',
        name: '结束桩号',
        isRequired: true
      },
      endMedianStakeCode: {
        name: '结束桩号'
      },
      endRelativeMileage: {
        type: "number",
        name: "相对结束桩位置(m)",
        isRequired: true,
        max: 9999999.99,
        precision: 2
      },
      pressureTestLength: {
        type: "number",
        name: "试压段长度(m)",
        max: 99999999,
        precision: 0
      },
      pipeSpecification: {
        type: "input",
        name: "管道规格",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      },
      pressureTestMedium: {
        type: "select",
        name: "试压介质",
        options: [{
          key: 1,
          value: "水"
        }, {
          key: 2,
          value: "空气"
        }, {
          key: 3,
          value: "氮气"
        }],
        formatter: function (a, b, value, c) {
          if (value == 1) return "水";
          if (value == 2) return "空气";
          return "氮气"
        }
      },
      designPressure: {
        type: "number",
        name: "设计压力(Mpa)",
        max: 999999.9,
        precision: 1
      },
      pressureTestDate: {
        type: "date",
        name: "试压日期",
        isRequired: true,
      },
      processDescription: {
        type: "input",
        name: "试验过程描述",
        rules: [{
          min: "0",
          max: 100,
          message: "不能超过100个字"
        }]
      },
      pressureTestResult: {
        type: "select",
        name: "结论",
        options: [{
          key: 0,
          value: "管道强度、严密性试验经检查不合格"
        }, {
          key: 1,
          value: "管道强度、严密性试验经检查合格"
        }],
        formatter: function (a, b, value, c) {
          if (value == 0) return "管道强度、严密性试验经检查不合格";
          return "管道强度、严密性试验经检查合格"
        }
      }
    }
  },
  pipeSweeping: {
    addUrl: "/jdbc/commonData/daqStationPipeSweeping/save.do",
    updateUrl: "/jdbc/commonData/daqStationPipeSweeping/update.do",
    deleteUrl: "/jdbc/commonData/daqStationPipeSweeping/delete.do",
    detailUrl: "/jdbc/commonData/daqStationPipeSweeping/getPage.do",
    getList: "/jdbc/commonData/daqStationPipeSweeping/getPage.do",
    searchFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid',
      'startMedianStakeOid',
      'endMedianStakeOid',
    ],
    tableFields: [ //
      'approveStatus',
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'startMedianStakeCode',
      'startRelativeMileage',
      'endMedianStakeCode',
      'endRelativeMileage',
      'pipeLength',
      'pipeSpecification',
      'maximumHeightDifference',
      'maxHeightDistance',
      'sweepingMedium',
      'airCompressorModel',
      'runningTime',
      'processDescription',
      'pressureTestResult',
      'constructUnitName',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeStationOid",
      'constructUnit',
      'supervisionUnit',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
    ],
    addFields1: [ //
      'startMedianStakeOid',
      'startRelativeMileage',
      'endMedianStakeOid',
      'endRelativeMileage',
    ],
    addFields2: [ //
      'pipeLength',
      'pipeSpecification',
      'maximumHeightDifference',
      'maxHeightDistance',
      'sweepingMedium',
      'airCompressorModel',
      'runningTime',
      'processDescription',
      'pressureTestResult',
    ],
    addNames: ["基本信息", "位置信息", "扫水信息"],
    detailFields0: [ //
      "projectName",
      "tendersName",
      "pipelineName",
      "pipeStationName",
      'constructUnitName',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
      'approveStatus'
    ],
    detailFields1: [ //
      'startMedianStakeCode',
      'startRelativeMileage',
      'endMedianStakeCode',
      'endRelativeMileage',
    ],
    detailFields2: [ //
      'pipeLength',
      'pipeSpecification',
      'maximumHeightDifference',
      'maxHeightDistance',
      'sweepingMedium',
      'airCompressorModel',
      'runningTime',
      'processDescription',
      'pressureTestResult',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['tendersOid'],
        childUrl: ['/daq/privilege/getTendersList.do'],
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      tendersOid: {
        type: 'select',
        name: '标段名称',
        childSelect: ['pipelineOid', 'supervisionUnit'],
        childUrl: ['/daq/privilege/getPipelineListByTendersOid.do', '/daq/privilege/getSupervisionUnitByTendersOid.do'],
        isRequired: true,
        isInit: true
      },
      pipelineOid: {
        type: 'select',
        name: '管线名称',
        childSelect: ['pipeStationOid', 'startMedianStakeOid', 'endMedianStakeOid'],
        childUrl: ['/daq/privilege/getPipeStationListByPipelineOid.do', '/daq/privilege/getMedianStakeList.do', '/daq/privilege/getMedianStakeList.do'],
        isRequired: true,
        isInit: true
      },
      tendersName: {
        name: '标段名称',
      },
      pipelineName: {
        name: '管线名称',
      },
      pipeStationOid: {
        type: 'select',
        name: '站场/阀室编号',
        isRequired: true,
        isInit: true
      },
      pipeStationName: {
        name: '站场/阀室编号',
      },
      constructUnit: {
        type: 'select',
        name: '施工单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '施工单位',
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }]
      },
      supervisionUnitName: {
        type: 'input',
        name: '监理单位',
        isRequired: true
      },
      supervisionEngineer: {
        type: 'input',
        name: '监理工程师',
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      collectionDate: {
        type: "date",
        name: "采集日期"
      },
      approveStatus: {
        type: 'input',
        name: '审核状态',
        formatter: function (a, b, value, c) {
          if (value == -1) return '驳回';
          if (value == 1) return '待审核';
          if (value == 2) return '通过';
          return '未上报';
        }
      },
      remarks: {
        name: "备注"
      },
      startMedianStakeOid: {
        type: 'select',
        name: '起始桩号',
        isRequired: true
      },
      startMedianStakeCode: {
        name: '起始桩号',
      },
      startRelativeMileage: {
        type: "number",
        name: "相对起始桩位置(m)",
        isRequired: true,
        max: 9999999.99,
        precision: 2
      },
      endMedianStakeOid: {
        type: 'select',
        name: '结束桩号',
        isRequired: true
      },
      endMedianStakeCode: {
        name: '结束桩号'
      },
      endRelativeMileage: {
        type: "number",
        name: "相对结束桩位置(m)",
        isRequired: true,
        max: 9999999.99,
        precision: 2
      },
      pipeLength: {
        type: "number",
        name: "管道长度(km)",
        max: 99999.999,
        precision: 3
      },
      pipeSpecification: {
        type: "input",
        name: "管道规格",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      },
      maximumHeightDifference: {
        type: "number",
        name: "管段最大高差(m)",
        max: 99999.999,
        precision: 3
      },
      maxHeightDistance: {
        type: "number",
        name: "管段最高点距清管器发送端距离(km)",
        max: 99999.999,
        precision: 3
      },
      sweepingMedium: {
        type: "select",
        name: "吹扫介质",
        options: [{
          key: 1,
          value: "水"
        }, {
          key: 2,
          value: "空气"
        }, {
          key: 3,
          value: "氮气"
        }],
        formatter: function (a, b, value, c) {
          if (value == 1) return "水";
          if (value == 2) return "空气";
          return "氮气"
        }
      },

      airCompressorModel: {
        type: "input",
        name: "空气压缩机型号及规格",
        rules: [{
          min: "0",
          max: 100,
          message: "不能超过100个字"
        }]
      },
      runningTime: {
        type: "number",
        name: "运行时间(h)",
        max: 99999.9,
        precision: 1
      },
      processDescription: {
        type: "input",
        name: "扫水情况描述",
        rules: [{
          min: "0",
          max: 100,
          message: "不能超过100个字"
        }]
      },
      pressureTestResult: {
        type: "input",
        name: "结论",
        rules: [{
          min: "0",
          max: 100,
          message: "不能超过100个字"
        }]
      },
    }
  },
  entranceAndExit: { //进出站口
    addUrl: "/jdbc/commonData/entranceAndExit/save.do",
    updateUrl: "/jdbc/commonData/entranceAndExit/update.do",
    deleteUrl: "/jdbc/commonData/entranceAndExit/delete.do",
    detailUrl: "/jdbc/commonData/entranceAndExit/getPage.do",
    getList: "/jdbc/commonData/entranceAndExit/getPage.do",
    searchFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid'
    ],
    tableFields: [ //
      'approveStatus',
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'deviceCode',
      'deviceName',
      'deviceType',
      'weldCode',
      'connectionPipelineType',
      'connectionPipelineCode',
      'medianStakeCode',
      'relativeMileage',
      'pointx',
      'pointy',
      'pointz',
      'constructDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeStationOid",
      'constructDate',
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addFields1: [ //
      'medianStakeOid',
      'relativeMileage',
      'pointx',
      'pointy',
      'pointz',
    ],
    addFields2: [ //
      'deviceCode',
      'deviceName',
      'deviceType',
      'weldOid',
      'connectionPipelineType',
      'connectionPipelineCode',
    ],
    addNames: ["基本信息", "位置信息", "进出站口信息"],
    detailFields0: [ //
      "projectName",
      "tendersName",
      "pipelineName",
      "pipeStationName",
      'constructDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'medianStakeCode',
      'relativeMileage',
      'pointx',
      'pointy',
      'pointz',
    ],
    detailFields2: [ //
      'deviceCode',
      'deviceName',
      'deviceType',
      'weldCode',
      'connectionPipelineType',
      'connectionPipelineCode',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['tendersOid'],
        childUrl: ['/daq/privilege/getTendersList.do'],
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      tendersOid: {
        type: 'select',
        name: '标段名称',
        childSelect: ['pipelineOid', 'supervisionUnit'],
        childUrl: ['/daq/privilege/getPipelineListByTendersOid.do', '/daq/privilege/getSupervisionUnitByTendersOid.do'],
        isRequired: true,
        isInit: true
      },
      pipelineOid: {
        type: 'select',
        name: '管线名称',
        childSelect: ['pipeStationOid', 'medianStakeOid'],
        childUrl: ['/daq/privilege/getPipeStationListByPipelineOid.do', '/daq/privilege/getMedianStakeList.do'],
        isRequired: true,
        isInit: true
      },
      tendersName: {
        name: '标段名称',
      },
      pipelineName: {
        name: '管线名称',
      },
      pipeStationOid: {
        type: 'select',
        name: '站场/阀室编号',
        childSelect: ['weldOid'],
        childUrl: ['daq/processWeld/getProcessWeldList.do'],
        isRequired: true,
        isInit: true
      },
      pipeStationName: {
        name: '站场/阀室编号',
      },
      constructDate: {
        type: "date",
        isRequired: true,
        name: "施工日期",
      },
      constructUnit: {
        type: 'select',
        name: '施工单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '施工单位',
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }]
      },
      supervisionUnitName: {
        type: 'input',
        name: '监理单位',
        isRequired: true
      },
      supervisionEngineer: {
        type: 'input',
        name: '监理工程师',
        isRequired: true,
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      collectionDate: {
        type: "date",

        name: "采集日期"
      },
      approveStatus: {
        type: 'input',
        name: '审核状态',
        formatter: function (a, b, value, c) {
          if (value == -1) return '驳回';
          if (value == 1) return '待审核';
          if (value == 2) return '通过';
          return '未上报';
        }
      },
      remarks: {
        name: "备注"
      },
      medianStakeOid: {
        type: 'select',
        name: '桩号',

      },
      relativeMileage: {
        type: "number",
        name: "相对里程(m)",
        max: 9999999.99,
        precision: 2
      },
      medianStakeCode: {
        name: "桩号"
      },
      pointx: {
        type: "number",
        name: "x坐标",
        max: 999.999999999,
        precision: 9
      },
      pointy: {
        type: "number",
        name: "y坐标",
        max: 999.999999999,
        precision: 9
      },
      pointz: {
        type: "number",
        name: "高程",
        max: 9999.99,
        precision: 2
      },
      weldOid: {
        type: "select",
        name: "焊口编号",

      },
      weldCode: {
        name: "焊口编号"
      },
      deviceCode: {
        type: "input",
        name: "编号",
        isRequired: true,
        rules: [{
          min: "0",
          max: 40,
          message: "不能超过40个字"
        }]
      },
      deviceName: {
        type: "input",
        name: "名称",
        isRequired: true,
        rules: [{
          min: "0",
          max: 45,
          message: "不能超过45个字"
        }]
      },
      deviceType: {
        type: "select",
        name: "类型",
        isRequired: true,
        options: [{
          key: 1,
          value: "进站"
        }, {
          key: 2,
          value: "出站"
        }, {
          key: 3,
          value: "分输"
        }],
        formatter: function (a, b, value, c) {
          if (value == 1) return '进站';
          if (value == 2) return '出站';
          return '分输';
        }
      },
      connectionPipelineType: {
        type: "input",
        name: "连接管线类型",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      },
      connectionPipelineCode: {
        type: "input",
        name: "连接管线编码",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      },
    }
  },
  condensingTube: { //排凝管
    addUrl: "/jdbc/commonData/daqStationCondensingTube/save.do",
    updateUrl: "/jdbc/commonData/daqStationCondensingTube/update.do",
    deleteUrl: "/jdbc/commonData/daqStationCondensingTube/delete.do",
    detailUrl: "/jdbc/commonData/daqStationCondensingTube/getPage.do",
    getList: "/jdbc/commonData/daqStationCondensingTube/getPage.do",
    searchFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid'
    ],
    tableFields: [ //
      'approveStatus',
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'deviceCode',
      'deviceName',
      'emissionMedium',
      'condensingTubeDiameter',
      'condensingTubeMaterial',
      'pressureGrade',
      'medianStakeCode',
      'relativeMileage',
      'pointx',
      'pointy',
      'pointz',
      'executingAgency',
      'constructDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeStationOid",
      'constructUnit',
      'constructDate',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addFields1: [ //
      'medianStakeOid',
      'relativeMileage',
      'pointx',
      'pointy',
      'pointz',
    ],
    addFields2: [ //
      'deviceCode',
      'deviceName',
      'emissionMedium',
      'condensingTubeDiameter',
      'condensingTubeMaterial',
      'pressureGrade',
      'executingAgency',
    ],
    addNames: ["基本信息", "位置信息", "排凝管信息"],
    detailFields0: [ //
      "projectName",
      "tendersName",
      "pipelineName",
      "pipeStationName",
      'constructDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'medianStakeCode',
      'relativeMileage',
      'pointx',
      'pointy',
      'pointz',
    ],
    detailFields2: [ //
      'deviceCode',
      'deviceName',
      'emissionMedium',
      'condensingTubeDiameter',
      'condensingTubeMaterial',
      'pressureGrade',
      'executingAgency',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['tendersOid'],
        childUrl: ['/daq/privilege/getTendersList.do'],
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      tendersOid: {
        type: 'select',
        name: '标段名称',
        childSelect: ['pipelineOid', 'supervisionUnit'],
        childUrl: ['/daq/privilege/getPipelineListByTendersOid.do', '/daq/privilege/getSupervisionUnitByTendersOid.do'],
        isRequired: true,
        isInit: true
      },
      pipelineOid: {
        type: 'select',
        name: '管线名称',
        childSelect: ['pipeStationOid', 'medianStakeOid'],
        childUrl: ['/daq/privilege/getPipeStationListByPipelineOid.do', '/daq/privilege/getMedianStakeList.do'],
        isRequired: true,
        isInit: true
      },
      tendersName: {
        name: '标段名称',
      },
      pipelineName: {
        name: '管线名称',
      },
      pipeStationOid: {
        type: 'select',
        name: '站场/阀室编号',
        isRequired: true,
        isInit: true
      },
      pipeStationName: {
        name: '站场/阀室编号',
      },
      constructDate: {
        type: "date",
        isRequired: true,
        name: "施工日期"
      },
      constructUnit: {
        type: 'select',
        name: '施工单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '施工单位',
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }]
      },
      supervisionUnitName: {
        type: 'input',
        name: '监理单位',
        isRequired: true
      },
      supervisionEngineer: {
        type: 'input',
        name: '监理工程师',
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }],
        isRequired: true
      },
      collectionDate: {
        type: "date",
        name: "采集日期"
      },
      approveStatus: {
        type: 'input',
        name: '审核状态',
        formatter: function (a, b, value, c) {
          if (value == -1) return '驳回';
          if (value == 1) return '待审核';
          if (value == 2) return '通过';
          return '未上报';
        }
      },
      remarks: {
        name: "备注"
      },
      medianStakeOid: {
        type: 'select',
        name: '桩号'
      },
      medianStakeCode: {
        name: '桩号',
      },
      relativeMileage: {
        type: "number",
        name: "相对里程(m)",
        max: 9999999.99,
        precision: 2
      },
      pointx: {
        type: "number",
        name: "x坐标",
        max: 999.999999999,
        precision: 9
      },
      pointy: {
        type: "number",
        name: "y坐标",
        max: 999.999999999,
        precision: 9
      },
      pointz: {
        type: "number",
        name: "高程",
        max: 9999.99,
        precision: 2
      },
      deviceCode: {
        type: "input",
        name: "编号",
        isRequired: true,
        rules: [{
          min: "0",
          max: 40,
          message: "不能超过40个字"
        }]
      },
      deviceName: {
        type: "input",
        name: "名称",
        isRequired: true,
        rules: [{
          min: "0",
          max: 45,
          message: "不能超过45个字"
        }]
      },
      emissionMedium: {
        type: "input",
        name: "排放介质",
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }]
      },
      condensingTubeDiameter: {
        name: "排凝管管径(mm)",
        type: "number",
        precision: 0,
        max: 999999
      },
      condensingTubeMaterial: {
        type: "select",
        name: "排凝管材质",
        options: [{
          key: 1,
          value: "未知"
        }, {
          key: 2,
          value: "钢材"
        }, {
          key: 3,
          value: "塑料"
        }, {
          key: 4,
          value: "其他"
        }],
        formatter: function (a, b, value, c) {
          if (value == 1) return '未知';
          if (value == 2) return '钢材';
          if (value == 2) return '塑料';
          return '其他';
        }
      },
      pressureGrade: {
        name: "压力等级(Mpa)",
        type: "number",
        precision: 2,
        max: 999999.99
      },
      executingAgency: {
        type: "input",
        name: "执行机构",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      }
    }
  },
  reservedInterface: { //预留甩头
    addUrl: "/jdbc/commonData/reservedInterface/save.do",
    updateUrl: "/jdbc/commonData/reservedInterface/update.do",
    deleteUrl: "/jdbc/commonData/reservedInterface/delete.do",
    detailUrl: "/jdbc/commonData/reservedInterface/getPage.do",
    getList: "/jdbc/commonData/reservedInterface/getPage.do",
    searchFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid'
    ],
    tableFields: [ //
      'approveStatus',
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'deviceCode',
      'deviceName',
      'pipeDiameter',
      'interfaceMaterial',
      'wallThickness',
      'sealingType',
      'pointx',
      'pointy',
      'pointz',
      'address',
      'constructDate',
      'constructUnitName',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeStationOid",
      'constructDate',
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',

    ],
    addFields1: [ //
      'pointx',
      'pointy',
      'pointz',
    ],
    addFields2: [ //
      'deviceCode',
      'deviceName',
      'pipeDiameter',
      'interfaceMaterial',
      'wallThickness',
      'sealingType',
      'address'
    ],
    addNames: ["基本信息", "位置信息", "甩头信息"],
    detailFields0: [ //
      "projectName",
      "tendersName",
      "pipelineName",
      "pipeStationName",
      'constructDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'pointx',
      'pointy',
      'pointz',
    ],
    detailFields2: [ //
      'deviceCode',
      'deviceName',
      'pipeDiameter',
      'interfaceMaterial',
      'wallThickness',
      'sealingType',
      'address'
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['tendersOid'],
        childUrl: ['/daq/privilege/getTendersList.do'],
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      tendersOid: {
        type: 'select',
        name: '标段名称',
        childSelect: ['pipelineOid', 'supervisionUnit'],
        childUrl: ['/daq/privilege/getPipelineListByTendersOid.do', '/daq/privilege/getSupervisionUnitByTendersOid.do'],
        isRequired: true,
        isInit: true
      },
      pipelineOid: {
        type: 'select',
        name: '管线名称',
        childSelect: ['pipeStationOid'],
        childUrl: ['/daq/privilege/getPipeStationListByPipelineOid.do'],
        isRequired: true,
        isInit: true
      },
      tendersName: {
        name: '标段名称',
      },
      pipelineName: {
        name: '管线名称',
      },
      pipeStationOid: {
        type: 'select',
        name: '站场/阀室编号',
        isRequired: true,
        isInit: true
      },
      pipeStationName: {
        name: '站场/阀室编号',
      },
      constructDate: {
        type: "date",
        isRequired: true,
        name: "施工日期"
      },
      constructUnit: {
        type: 'select',
        name: '施工单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '施工单位',
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }]
      },
      supervisionUnitName: {
        type: 'input',
        name: '监理单位',
        isRequired: true
      },
      supervisionEngineer: {
        type: 'input',
        isRequired: true,
        name: '监理工程师',
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      collectionDate: {
        type: "date",
        name: "采集日期"
      },
      approveStatus: {
        type: 'input',
        name: '审核状态',
        formatter: function (a, b, value, c) {
          if (value == -1) return '驳回';
          if (value == 1) return '待审核';
          if (value == 2) return '通过';
          return '未上报';
        }
      },
      remarks: {
        name: "备注"
      },
      pointx: {
        type: "number",
        name: "x坐标",
        max: 999.999999999,
        precision: 9
      },
      pointy: {
        type: "number",
        name: "y坐标",
        max: 999.999999999,
        precision: 9
      },
      pointz: {
        type: "number",
        name: "高程",
        max: 9999.99,
        precision: 2
      },
      deviceCode: {
        type: "input",
        name: "编号",
        isRequired: true,
        rules: [{
          min: "0",
          max: 40,
          message: "不能超过40个字"
        }]
      },
      deviceName: {
        type: "input",
        name: "名称",
        isRequired: true,
        rules: [{
          min: "0",
          max: 45,
          message: "不能超过45个字"
        }]
      },
      pipeDiameter: {
        type: "number",
        name: "甩头管径(mm)",
        max: 999999.99,
        precision: 2
      },
      interfaceMaterial: {
        type: "select",
        name: "甩头材质",
        options: [{
          key: 1,
          value: "未知"
        }, {
          key: 2,
          value: "钢材"
        }, {
          key: 3,
          value: "塑料"
        }, {
          key: 4,
          value: "其他"
        }],
        formatter: function (a, b, value, c) {
          if (value == 1) return '未知';
          if (value == 2) return '钢材';
          if (value == 2) return '塑料';
          return '其他';
        }
      },
      wallThickness: {
        name: "甩头壁厚(mm)",
        type: "number",
        precision: 1,
        max: 999999.9
      },
      sealingType: {
        type: "input",
        name: "甩头封口形式",
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }]
      },
      address: {
        type: "input",
        name: "地址",
        rules: [{
          min: "0",
          max: 100,
          message: "不能超过100个字"
        }]
      }
    }
  },
  stationClosure: { //封堵物
    addUrl: "/jdbc/commonData/daqStationClosure/save.do",
    updateUrl: "/jdbc/commonData/daqStationClosure/update.do",
    deleteUrl: "/jdbc/commonData/daqStationClosure/delete.do",
    detailUrl: "/jdbc/commonData/daqStationClosure/getPage.do",
    getList: "/jdbc/commonData/daqStationClosure/getPage.do",
    searchFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid'
    ],
    tableFields: [ //
      'approveStatus',
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'deviceCode',
      'deviceName',
      'closureSize',
      'closureMoldName',
      'closureMaterial',
      'closureConnectionMethodsName',
      'pointx',
      'pointy',
      'pointz',
      'address',
      'tubeMakingMethodsName',
      'constructDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeStationOid",
      'constructDate',
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addFields1: [ //
      'pointx',
      'pointy',
      'pointz',
    ],
    addFields2: [ //
      'deviceCode',
      'deviceName',
      'closureSize',
      'closureMold',
      'closureMaterial',
      'closureConnectionMethods',
      'address',
      'tubeMakingMethods',
    ],
    addNames: ["基本信息", "位置信息", "封堵物信息"],
    detailFields0: [ //
      "projectName",
      "tendersName",
      "pipelineName",
      "pipeStationName",
      'constructDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'pointx',
      'pointy',
      'pointz',
    ],
    detailFields2: [ //
      'deviceCode',
      'deviceName',
      'closureSize',
      'closureMoldName',
      'closureMaterial',
      'closureConnectionMethodsName',
      'address',
      'tubeMakingMethodsName',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['tendersOid'],
        childUrl: ['/daq/privilege/getTendersList.do'],
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      tendersOid: {
        type: 'select',
        name: '标段名称',
        childSelect: ['pipelineOid', 'supervisionUnit'],
        childUrl: ['/daq/privilege/getPipelineListByTendersOid.do', '/daq/privilege/getSupervisionUnitByTendersOid.do'],
        isRequired: true,
        isInit: true
      },
      pipelineOid: {
        type: 'select',
        name: '管线名称',
        childSelect: ['pipeStationOid'],
        childUrl: ['/daq/privilege/getPipeStationListByPipelineOid.do'],
        isRequired: true,
        isInit: true
      },
      tendersName: {
        name: '标段名称',
      },
      pipelineName: {
        name: '管线名称',
      },
      pipeStationOid: {
        type: 'select',
        name: '站场/阀室编号',
        isRequired: true,
        isInit: true
      },
      pipeStationName: {
        name: '站场/阀室编号',
      },
      constructDate: {
        name: "施工日期",
        type: "date",
        isRequired: true,
      },
      constructUnit: {
        type: 'select',
        name: '施工单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '施工单位',
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }]
      },
      supervisionUnitName: {
        type: 'input',
        name: '监理单位',
        isRequired: true
      },
      supervisionEngineer: {
        type: 'input',
        name: '监理工程师',
        isRequired: true,
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      collectionDate: {
        type: "date",
        name: "采集日期"
      },
      approveStatus: {
        type: 'input',
        name: '审核状态',
        formatter: function (a, b, value, c) {
          if (value == -1) return '驳回';
          if (value == 1) return '待审核';
          if (value == 2) return '通过';
          return '未上报';
        }
      },
      remarks: {
        name: "备注"
      },
      pointx: {
        type: "number",
        name: "x坐标",
        max: 999.999999999,
        precision: 9
      },
      pointy: {
        type: "number",
        name: "y坐标",
        max: 999.999999999,
        precision: 9
      },
      pointz: {
        type: "number",
        name: "高程",
        max: 9999.99,
        precision: 2
      },
      deviceCode: {
        type: "input",
        isRequired: true,
        name: "编号",
        rules: [{
          min: "0",
          max: 40,
          message: "不能超过40个字"
        }]
      },
      deviceName: {
        type: "input",
        name: "名称",
        isRequired: true,
        rules: [{
          min: "0",
          max: 45,
          message: "不能超过45个字"
        }]
      },
      closureSize: {
        type: "input",
        name: "封堵物尺寸",
        rules: [{
          min: "0",
          max: 45,
          message: "不能超过45个字"
        }]
      },
      closureMold: {
        type: "select",
        name: "封堵物类型",
        domainName: "closure_mold_domain"
      },
      closureMoldName: {
        name: "封堵物类型"
      },
      closureMaterial: {
        type: "select",
        name: "封堵物材质",
        options: [{
          key: 1,
          value: "未知"
        }, {
          key: 2,
          value: "钢材"
        }, {
          key: 3,
          value: "塑料"
        }, {
          key: 4,
          value: "其他"
        }],
        formatter: function (a, b, value, c) {
          if (value == 1) return '未知';
          if (value == 2) return '钢材';
          if (value == 2) return '塑料';
          return '其他';
        }
      },
      closureConnectionMethods: {
        type: "select",
        name: "连接方式",
        domainName: "closure_connection_methods_domain"
      },
      closureConnectionMethodsName: {
        name: "连接方式"
      },
      tubeMakingMethods: {
        type: "select",
        name: "制管形式",
        domainName: "tube_making_methods_domain"
      },
      tubeMakingMethodsName: {
        name: "制管形式"
      },
      address: {
        type: "input",
        name: "具体地址",
        rules: [{
          min: "0",
          max: 100,
          message: "不能超过100个字"
        }]
      }
    }
  },
  ventStack: { //放空立管
    addUrl: "/jdbc/commonData/ventStack/save.do",
    updateUrl: "/jdbc/commonData/ventStack/update.do",
    deleteUrl: "/jdbc/commonData/ventStack/delete.do",
    detailUrl: "/jdbc/commonData/ventStack/getPage.do",
    getList: "/jdbc/commonData/ventStack/getPage.do",
    searchFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid'
    ],
    tableFields: [ //
      'approveStatus',
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'deviceCode',
      'materialDeviceName',
      'manufactureNumberName',
      'pointx',
      'pointy',
      'positionName',
      'distance',
      'constructDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeStationOid",
      'constructDate',
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addFields1: [ //
      'pointx',
      'pointy',
      'position',
    ],
    addFields2: [ //
      'deviceCode',
      'manufactureNumber',
      'distance',
    ],
    addNames: ["基本信息", "位置信息", "放空立管信息"],
    detailFields0: [ //
      "projectName",
      "tendersName",
      "pipelineName",
      "pipeStationName",
      'constructDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'pointx',
      'pointy',
      'positionName',
    ],
    detailFields2: [ //
      'deviceCode',
      'materialDeviceName',
      'manufactureNumberName',
      'distance',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['tendersOid', 'manufactureNumber'],
        childUrl: ['/daq/privilege/getTendersList.do', '/daq/staticalMaterial/getMaterialVentStackList.do'],
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      tendersOid: {
        type: 'select',
        name: '标段名称',
        childSelect: ['pipelineOid', 'supervisionUnit'],
        childUrl: ['/daq/privilege/getPipelineListByTendersOid.do', '/daq/privilege/getSupervisionUnitByTendersOid.do'],
        isRequired: true,
        isInit: true
      },
      pipelineOid: {
        type: 'select',
        name: '管线名称',
        childSelect: ['pipeStationOid'],
        childUrl: ['/daq/privilege/getPipeStationListByPipelineOid.do'],
        isRequired: true,
        isInit: true
      },
      tendersName: {
        name: '标段名称',
      },
      pipelineName: {
        name: '管线名称',
      },
      pipeStationOid: {
        type: 'select',
        name: '站场/阀室编号',
        isRequired: true,
        isInit: true
      },
      pipeStationName: {
        name: '站场/阀室编号',
      },
      constructDate: {
        type: "date",
        isRequired: true,
        name: "施工日期",
        lessDateScope: ['collectionDate'], //第一个数组表示 小于的日期，第二个数组表示大于的日期。默认是小于今天 today 默认为今天
        maxDateScope: [],
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        }
      },
      constructUnit: {
        type: 'select',
        name: '施工单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '施工单位',
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }]
      },
      supervisionUnitName: {
        type: 'input',
        name: '监理单位',
        isRequired: true
      },
      supervisionEngineer: {
        type: 'input',
        name: '监理工程师',
        isRequired: true,
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      collectionDate: {
        type: "date",
        name: "采集日期",
        lessDateScope: [], //表示小于的日期
        maxDateScope: ['constructDate'], //表示大于的日期
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        }
      },
      approveStatus: {
        type: 'input',
        name: '审核状态',
        formatter: function (a, b, value, c) {
          if (value == -1) return '驳回';
          if (value == 1) return '待审核';
          if (value == 2) return '通过';
          return '未上报';
        }
      },
      remarks: {
        name: "备注"
      },
      pointx: {
        type: "number",
        name: "x坐标",
        max:999.999999999,
        precision: 9
      },
      pointy: {
        type: "number",
        name: "y坐标",
        max: 999.999999999,
        precision: 9
      },
      position: {
        type: "select",
        name: "方位",
        domainName: "position_domain"
      },
      positionName: {
        name: "方位"
      },
      deviceCode: {
        type: "input",
        name: "设备编号",
        isRequired: true,
        rules: [{
          min: "0",
          max: 40,
          message: "不能超过40个字"
        }]
      },
      materialDeviceName: {
        type: "input",
        name: "物资名称",
        rules: [{
          min: "0",
          max: 45,
          message: "不能超过45个字"
        }]
      },
      manufactureNumber: {
        type: "select",
        name: "出厂编号",
        isRequired: true,
        // rules: [{
        //   min: "0",
        //   max: 70,
        //   message: "不能超过70个字"
        // }]
      },
      manufactureNumberName: {
        name: "出厂编号"
      },
      distance: {
        type: "number",
        name: "距离(m)",
        max: 9999999.99,
        precision: 2
      }
    }
  },
  auxiliaryAnodeBed: { //辅助阳极地床
    addUrl: "/jdbc/commonData/stationAuxiliaryAnodeBed/save.do",
    updateUrl: "/jdbc/commonData/stationAuxiliaryAnodeBed/update.do",
    deleteUrl: "/jdbc/commonData/stationAuxiliaryAnodeBed/delete.do",
    detailUrl: "/jdbc/commonData/stationAuxiliaryAnodeBed/getPage.do",
    getList: "/jdbc/commonData/stationAuxiliaryAnodeBed/getPage.do",
    searchFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid'
    ],
    tableFields: [ //
      'approveStatus',
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'deviceCode',
      'medianStakeCode',
      'relativeMileage',
      'buryingDepth',
      'isTemporary',
      'designLife',
      'backfillMaterialName',
      'auxiliaryAnodeNum',
      'burialWayName',
      'grossWeight',
      'pointx',
      'pointy',
      'pointz',
      'cableLength',
      'protectLength',
      'anodeMaterialName',
      'anodeSpecification',
      'anodeResistance',
      'hasWaterFloodingSys',
      'hasVentHole',
      'anodeBedConnectionPower',
      'embedmentDate',
      // 'constructDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'installLocationDes',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeStationOid",
      'embedmentDate',
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',

    ],
    addFields1: [ //
      'medianStakeOid',
      'relativeMileage',
      'pointx',
      'pointy',
      'pointz',
    ],
    addFields2: [ //
      'deviceCode',
      'installLocationDes',
      'buryingDepth',
      'isTemporary',
      'designLife',
      'backfillMaterial',
      'auxiliaryAnodeNum',
      'burialWay',
      'grossWeight',
      'cableLength',
      'protectLength',
      'anodeMaterial',
      'anodeSpecification',
      'anodeResistance',
      'hasWaterFloodingSys',
      'hasVentHole',
      'anodeBedConnectionPower',
    ],
    addNames: ["基本信息", "位置信息", "辅助阳极地床信息"],
    detailFields0: [ //
      "projectName",
      "tendersName",
      "pipelineName",
      "pipeStationName",
      "embedmentDate",
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'medianStakeCode',
      'relativeMileage',
      'pointx',
      'pointy',
      'pointz',
    ],
    detailFields2: [ //
      'deviceCode',
      'installLocationDes',
      'buryingDepth',
      'isTemporary',
      'designLife',
      'backfillMaterialName',
      'auxiliaryAnodeNum',
      'burialWayName',
      'grossWeight',
      'cableLength',
      'protectLength',
      'anodeMaterialName',
      'anodeSpecification',
      'anodeResistance',
      'hasWaterFloodingSys',
      'hasVentHole',
      'anodeBedConnectionPower',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['tendersOid'],
        childUrl: ['/daq/privilege/getTendersList.do'],
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      tendersOid: {
        type: 'select',
        name: '标段名称',
        childSelect: ['pipelineOid', 'supervisionUnit'],
        childUrl: ['/daq/privilege/getPipelineListByTendersOid.do', '/daq/privilege/getSupervisionUnitByTendersOid.do'],
        isRequired: true,
        isInit: true
      },
      pipelineOid: {
        type: 'select',
        name: '管线名称',
        childSelect: ['pipeStationOid', 'medianStakeOid'],
        childUrl: ['/daq/privilege/getPipeStationListByPipelineOid.do', '/daq/privilege/getMedianStakeList.do'],
        isRequired: true,
        isInit: true
      },
      tendersName: {
        name: '标段名称',
      },
      pipelineName: {
        name: '管线名称',
      },
      pipeStationOid: {
        type: 'select',
        name: '站场/阀室编号',
        // childSelect: ['weldOid'],
        // childUrl: ['daq/processWeld/getProcessWeldList.do'],
        isRequired: true,
        isInit: true
      },
      pipeStationName: {
        name: '站场/阀室编号',
      },
      constructDate: {
        type: "date",
        name: "施工日期",
        // isRequired: true,
        // lessDateScope: ['collectionDate'], //表示小于的日期
        // maxDateScope: [], //表示大于的日期
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        }
      },
      constructUnit: {
        type: 'select',
        name: '施工单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
        //        isInit: true
      },
      constructUnitName: {
        name: '施工单位',
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }]
      },
      supervisionUnitName: {
        type: 'input',
        name: '监理单位',
        isRequired: true
      },
      supervisionEngineer: {
        type: 'input',
        name: '监理工程师',
        isRequired: true,
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      collectionDate: {
        type: "date",
        // isRequired: true,
        name: "采集日期",
        // lessDateScope: [], //表示小于的日期
        // maxDateScope: ['constructDate'], //表示大于的日期
        // isLessToday: true, //默认是小于今天的
        // pickerOptions: { //用于设置默认是小于今天的
        //   disabledDate: function (time) {
        //     return time.getTime() > new Date().getTime()
        //   }
        // }
      },
      approveStatus: {
        type: 'input',
        name: '审核状态',
        formatter: function (a, b, value, c) {
          if (value == -1) return '驳回';
          if (value == 1) return '待审核';
          if (value == 2) return '通过';
          return '未上报';
        }
      },
      remarks: {
        name: "备注"
      },
      medianStakeOid: {
        type: "select",
        name: "桩号",
        isRequired: true,
      },
      medianStakeCode: {
        name: "桩号"
      },
      relativeMileage: {
        type: "number",
        name: "相对桩位置(m)",
        isRequired: true,
        max: 9999999.99,
        precision: 2
      },
      pointx: {
        type: "number",
        name: "x坐标",
        max: 999.999999999,
        precision: 9
      },
      pointy: {
        type: "number",
        name: "y坐标",
        max: 999.999999999,
        precision: 9
      },
      pointz: {
        type: "number",
        name: "高程",
        max: 9999.99,
        precision: 2
      },
      deviceCode: {
        type: "input",
        name: "地床编号",
        isRequired: true,
        rules: [{
          min: "0",
          max: 40,
          message: "不能超过40个字"
        }]
      },
      installLocationDes: {
        type: "input",
        name: "安装位置描述",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      },
      buryingDepth: {
        type: "number",
        name: "埋深(m)",
        max: 9999.99,
        precision: 2
      },
      isTemporary: {
        type: "select",
        name: "是否临时",
        options: [{
          key: 0,
          value: "否"
        }, {
          key: 1,
          value: "是"
        }],
        formatter: function (a, b, value, c) {
          console.log("哈哈哈哈哈"+value)
          if (value == 1) return '是';
          if (value == 0) return '否';
          return '';
        }
      },
      designLife: {
        type: "number",
        name: "设计寿命(年)",
        max: 99999,
        precision: 0
      },
      backfillMaterial: {
        name: "回填材料",
        type: "select",
        domainName: "backfill_material_domain"
      },
      backfillMaterialName: {
        name: "回填材料"
      },
      auxiliaryAnodeNum: {
        type: "number",
        name: "辅助阳极数量(个)",
        max: 999999,
        precision: 0
      },
      burialWay: {
        name: "埋设方式",
        type: "select",
        domainName: "burial_way_domain"
      },
      burialWayName: {
        name: "埋设方式"
      },
      grossWeight: {
        type: "number",
        name: "总重(kg)",
        max: 9999999.99,
        precision: 2
      },
      cableLength: {
        type: "number",
        name: "电缆长度(m)",
        max: 999999.99,
        precision: 2
      },
      protectLength: {
        type: "number",
        name: "保护长度(m)",
        max: 999999.99,
        precision: 2
      },
      anodeMaterial: {
        type: "select",
        name: "阳极材料类型",
        domainName: "anode_specification_domain"
      },
      anodeMaterialName: {
        name: "阳极材料类型"
      },
      anodeSpecification: {
        type: "input",
        name: "阳极材料规格",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      },
      anodeResistance: {
        type: "number",
        name: "阳极电阻(Ω)",
        max: 9999.99,
        precision: 2
      },
      hasWaterFloodingSys: {
        type: "select",
        name: "是否有注水系统",
        options: [{
          key: 0,
          value: "否"
        }, {
          key: 1,
          value: "是"
        }],
        formatter: function (a, b, value, c) {
          if (value == 1) return '是';
          if (value == 0) return '否';
          return '';
        }
      },
      hasVentHole: {
        type: "select",
        name: "是否有排气孔",
        options: [{
          key: 0,
          value: "否"
        }, {
          key: 1,
          value: "是"
        }],
        formatter: function (a, b, value, c) {
          if (value == 1) return '是';
          if (value == 0) return '否';
          return '';
        }
      },
      anodeBedConnectionPower: {
        type: "input",
        name: "地床连接阴保电源",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      },
      embedmentDate: {
        type: "date",
        name: "埋设日期",
        isRequired: true
      }
    }
  },

  corrosionDetectionSys: { //内腐蚀检测系统
    addUrl: "/jdbc/commonData/stationIcDetectionSys/save.do",
    updateUrl: "/jdbc/commonData/stationIcDetectionSys/update.do",
    deleteUrl: "/jdbc/commonData/stationIcDetectionSys/delete.do",
    detailUrl: "/jdbc/commonData/stationIcDetectionSys/getPage.do",
    getList: "/jdbc/commonData/stationIcDetectionSys/getPage.do",
    searchFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid'
    ],
    tableFields: [ //
      'approveStatus',
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'deviceCode',
      'deviceName',
      'manufactureNumber',
      'startMedianStakeCode',
      'startRelativeMileage',
      'endMedianStakeCode',
      'endRelativeMileage',
      'systemTypeName',
      'systemPrincipleName',
      'developmentUnit',
      'putIntoDate',
      // 'constructDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',

      'remarks'
    ],
    addFields0: [
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeStationOid",
      'constructUnit',
      'putIntoDate',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',

    ],
    addFields1: [ //
      'startMedianStakeOid',
      'startRelativeMileage',
      'endMedianStakeOid',
      'endRelativeMileage',
    ],
    addFields2: [ //
      'deviceCode',
      'deviceName',
      'manufactureNumber',
      'systemType',
      'systemPrinciple',
      'developmentUnit',
    ],
    addNames: ["基本信息", "位置信息", "内腐蚀检测信息"],
    detailFields0: [ //
      "projectName",
      "tendersName",
      "pipelineName",
      "pipeStationName",
      'putIntoDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'startMedianStakeCode',
      'startRelativeMileage',
      'endMedianStakeCode',
      'endRelativeMileage',
    ],
    detailFields2: [ //
      'deviceCode',
      'deviceName',
      'manufactureNumber',
      'systemTypeName',
      'systemPrincipleName',
      'developmentUnit',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['tendersOid'],
        childUrl: ['/daq/privilege/getTendersList.do'],
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      tendersOid: {
        type: 'select',
        name: '标段名称',
        childSelect: ['pipelineOid', 'supervisionUnit'],
        childUrl: ['/daq/privilege/getPipelineListByTendersOid.do', '/daq/privilege/getSupervisionUnitByTendersOid.do'],
        isRequired: true,
        isInit: true
      },
      pipelineOid: {
        type: 'select',
        name: '管线名称',
        childSelect: ['pipeStationOid', 'startMedianStakeOid', 'endMedianStakeOid'],
        childUrl: ['/daq/privilege/getPipeStationListByPipelineOid.do', '/daq/privilege/getMedianStakeList.do', '/daq/privilege/getMedianStakeList.do'],
        isRequired: true,
        isInit: true
      },
      tendersName: {
        name: '标段名称',
      },
      pipelineName: {
        name: '管线名称',
      },
      pipeStationOid: {
        type: 'select',
        name: '站场/阀室编号',
        // childSelect: ['weldOid'],
        // childUrl: ['daq/processWeld/getProcessWeldList.do'],
        isRequired: true,
        isInit: true
      },
      pipeStationName: {
        name: '站场/阀室编号',
      },
      constructUnit: {
        type: 'select',
        name: '施工单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
        //        isInit: true
      },
      constructUnitName: {
        name: '施工单位',
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }]
      },
      supervisionUnitName: {
        type: 'input',
        name: '监理单位',
        isRequired: true
      },
      supervisionEngineer: {
        type: 'input',
        name: '监理工程师',
        isRequired: true,
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      collectionDate: {
        type: "date",
        // isRequired: true,
        name: "采集日期",
        // lessDateScope: [], //表示小于的日期
        // maxDateScope: ['constructDate'], //表示大于的日期
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        }
      },
      // constructDate: {
      //   type: "date",
      //   isRequired: true,
      //   name: "施工日期",
      //   lessDateScope: ['collectionDate'], //表示小于的日期
      //   maxDateScope: [], //表示大于的日期
      //   isLessToday: true, //默认是小于今天的
      //   pickerOptions: { //用于设置默认是小于今天的
      //     disabledDate: function (time) {
      //       return time.getTime() > new Date().getTime()
      //     }
      //   }
      // },
      approveStatus: {
        type: 'input',
        name: '审核状态',
        formatter: function (a, b, value, c) {
          if (value == -1) return '驳回';
          if (value == 1) return '待审核';
          if (value == 2) return '通过';
          return '未上报';
        }
      },
      remarks: {
        name: "备注"
      },
      startMedianStakeOid: {
        type: "select",
        name: "起始桩号",
        isRequired: true,
      },
      startMedianStakeCode: {
        name: "起始桩号"
      },
      endMedianStakeCode: {
        name: "结束桩号"
      },
      startRelativeMileage: {
        type: "number",
        name: "相对起始桩里程(m)",
        isRequired: true,
        max: 9999999.99,
        precision: 2
      },
      endMedianStakeOid: {
        type: "select",
        name: "结束桩号",
        isRequired: true,
      },
      endRelativeMileage: {
        type: "number",
        name: "相对结束桩里程(m)",
        isRequired: true,
        max: 9999999.99,
        precision: 2
      },
      deviceCode: {
        type: "input",
        name: "设备编号",
        isRequired: true,
        rules: [{
          min: "0",
          max: 40,
          message: "不能超过40个字"
        }]
      },
      deviceName: {
        type: "input",
        name: "设备名称",
        rules: [{
          min: "0",
          max: 45,
          message: "不能超过45个字"
        }]
      },
      manufactureNumber: {
        type: "input",
        name: "出厂编号",
        isRequired: true,
        rules: [{
          min: "0",
          max: 70,
          message: "不能超过70个字"
        }]
      },
      systemType: {
        type: "select",
        name: "系统类型",
        domainName: "system_type_domain"
      },
      systemTypeName: {
        name: "系统类型",
      },
      systemPrincipleName: {
        name: "系统原理",
      },
      systemPrinciple: {
        type: "select",
        name: "系统原理",
        domainName: "system_principle_domain"
      },
      developmentUnit: {
        type: "input",
        name: "研发单位",
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      putIntoDate: {
        type: "date",
        name: "投用日期"
      }
    }
  },
  cpPowerSupply: { //阴保电源
    addUrl: "/jdbc/commonData/stationCpPowerSupply/save.do",
    updateUrl: "/jdbc/commonData/stationCpPowerSupply/update.do",
    deleteUrl: "/jdbc/commonData/stationCpPowerSupply/delete.do",
    detailUrl: "/jdbc/commonData/stationCpPowerSupply/getPage.do",
    getList: "/jdbc/commonData/stationCpPowerSupply/getPage.do",
    searchFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid'
    ],
    tableFields: [ //
      'approveStatus',
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'deviceCode',
      'deviceName',
      'manufactureNumber',
      'protectObjectName',
      'pointx',
      'pointy',
      'pointz',
      'ratedCurrent',
      'ratedVoltage',
      'outputCurrent',
      'outputVoltage',
      'bedNum',
      'anodeNum',
      'powerSupplyTypeName',
      'manufacturerName',
      'putIntoDate',
      'designLife',
      'electrifiedPoint',
      'givenVoltage',
      'referenceVoltage',
      // 'constructDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeStationOid",
      'putIntoDate',
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addFields1: [
      'pointx',
      'pointy',
      'pointz',
    ],
    addFields2: [ //
      'deviceCode',
      'deviceName',
      'manufactureNumber',
      'protectObject',
      'ratedCurrent',
      'ratedVoltage',
      'outputCurrent',
      'outputVoltage',
      'bedNum',
      'anodeNum',
      'powerSupplyType',
      'manufacturerName',
      'designLife',
      'electrifiedPoint',
      'givenVoltage',
      'referenceVoltage',
    ],
    addNames: ["基本信息", "位置信息", "阴保电源信息"],
    detailFields0: [ //
      "projectName",
      "tendersName",
      "pipelineName",
      "pipeStationName",
      "putIntoDate",
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'pointx',
      'pointy',
      'pointz',
    ],
    detailFields2: [ //
      'deviceCode',
      'deviceName',
      'manufactureNumber',
      'protectObjectName',
      'ratedCurrent',
      'ratedVoltage',
      'outputCurrent',
      'outputVoltage',
      'bedNum',
      'anodeNum',
      'powerSupplyTypeName',
      'manufacturerName',
      'designLife',
      'electrifiedPoint',
      'givenVoltage',
      'referenceVoltage',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['tendersOid'],
        childUrl: ['/daq/privilege/getTendersList.do'],
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      tendersOid: {
        type: 'select',
        name: '标段名称',
        childSelect: ['pipelineOid', 'supervisionUnit'],
        childUrl: ['/daq/privilege/getPipelineListByTendersOid.do', '/daq/privilege/getSupervisionUnitByTendersOid.do'],
        isRequired: true,
        isInit: true
      },
      pipelineOid: {
        type: 'select',
        name: '管线名称',
        childSelect: ['pipeStationOid'],
        childUrl: ['/daq/privilege/getPipeStationListByPipelineOid.do'],
        isRequired: true,
        isInit: true
      },
      tendersName: {
        name: '标段名称',
      },
      pipelineName: {
        name: '管线名称',
      },
      pipeStationOid: {
        type: 'select',
        name: '站场/阀室编号',
        // childSelect: ['weldOid'],
        // childUrl: ['daq/processWeld/getProcessWeldList.do'],
        isRequired: true,
        isInit: true
      },
      pipeStationName: {
        name: '站场/阀室编号',
      },
      // constructDate: {
      //   type: "date",
      //   name: "施工日期",
      //   isRequired: true,
      //   lessDateScope: ['collectionDate'], //表示小于的日期
      //   maxDateScope: [], //表示大于的日期
      //   isLessToday: true, //默认是小于今天的
      //   pickerOptions: { //用于设置默认是小于今天的
      //     disabledDate: function (time) {
      //       return time.getTime() > new Date().getTime()
      //     }
      //   }
      // },
      constructUnit: {
        type: 'select',
        name: '施工单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
        //        isInit: true
      },
      constructUnitName: {
        name: '施工单位',
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }]
      },
      supervisionUnitName: {
        type: 'input',
        name: '监理单位',
        isRequired: true
      },
      supervisionEngineer: {
        type: 'input',
        name: '监理工程师',
        isRequired: true,
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      collectionDate: {
        type: "date",
        // isRequired: true,
        name: "采集日期",
        // lessDateScope: [], //表示小于的日期
        // maxDateScope: ['constructDate'], //表示大于的日期
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        }
      },
      approveStatus: {
        type: 'input',
        name: '审核状态',
        formatter: function (a, b, value, c) {
          if (value == -1) return '驳回';
          if (value == 1) return '待审核';
          if (value == 2) return '通过';
          return '未上报';
        }
      },
      remarks: {
        name: "备注"
      },
      pointx: {
        type: "number",
        name: "x坐标",
        max: 999.999999999,
        precision: 9
      },
      pointy: {
        type: "number",
        name: "y坐标",
        max: 999.999999999,
        precision: 9
      },
      pointz: {
        type: "number",
        name: "高程",
        max: 9999.99,
        precision: 2
      },
      deviceCode: {
        type: "input",
        name: "设备编号",
        isRequired: true,
        rules: [{
          min: "0",
          max: 40,
          message: "不能超过40个字"
        }]
      },
      deviceName: {
        type: "input",
        name: "设备名称",
        isRequired: true,
        rules: [{
          min: "0",
          max: 45,
          message: "不能超过45个字"
        }]
      },
      manufactureNumber: {
        type: "input",
        name: "出厂编号",
        isRequired: true,
        rules: [{
          min: "0",
          max: 70,
          message: "不能超过70个字"
        }]
      },
      protectObject: {
        type: "select",
        name: "保护对象",
        domainName: "protect_object_domain"
      },
      protectObjectName: {
        name: "保护对象",
      },
      powerSupplyTypeName: {
        name: "电源类型"
      },
      ratedCurrent: {
        type: "number",
        name: "额定电流(A)",
        max: 99999.99,
        precision: 2
      },
      ratedVoltage: {
        type: "number",
        name: "额定电压(V)",
        max: 99999.99,
        precision: 2
      },
      outputCurrent: {
        type: "number",
        name: "输出电流(A)",
        max: 99999.99,
        precision: 2
      },
      outputVoltage: {
        type: "number",
        name: "输出电压(V)",
        max: 99999.99,
        precision: 2
      },
      bedNum: {
        type: "number",
        name: "地床数量",
        max: 999999,
        precision: 0
      },
      anodeNum: {
        type: "number",
        name: "阳极数量",
        max: 999999,
        precision: 0
      },
      powerSupplyType: {
        type: "select",
        name: "电源类型",
        domainName: "power_supply_type_domain"
      },
      manufacturerName: {
        type: "input",
        name: "制造商",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      },
      putIntoDate: {
        type: "date",
        name: "投用日期"
      },
      designLife: {
        type: "number",
        name: "设计寿命(年)",
        max: 99999,
        precision: 0
      },
      electrifiedPoint: {
        type: "input",
        name: "通电点描述",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      },
      givenVoltage: {
        type: "number",
        name: "给定(预置)电位(V)",
        max: 99999.99,
        precision: 2
      },
      referenceVoltage: {
        type: "number",
        name: "参比电位(V)",
        max: 99999.99,
        precision: 2
      },
    }
  },

  methods: {
    formatFields: function (fields, fieldsConfig, templateCode) {
      return fields.map(function (item) {
        return {
          name: fieldsConfig[item].name,
          field: item,
          formatter: fieldsConfig[item].formatter,
        };
      });
    },
    formatForm: function (fields) {
      var obj = {};
      fields.forEach(function (item) {
        obj[item] = '';
      });
      obj.remarks = '';
      return obj;
    },

  }
};