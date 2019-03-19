var stationConfig = {
  stationRay: {
    searchFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid',
      'weldOid'
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'weldCode',
      'detectionReportNum',
      'detectionDate',
      'detectionTypeName',
      'evaluationGradeName',
      'evaluationResult',
      'negativeNum',
      'detectionUnitName',
      'detectionPerson',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionDate',
      'remarks'
    ],
    addName: ['射线检测基本信息', '缺陷信息'],
    addFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid',
      'weldOid',
      'detectionReportNum',
      'detectionDate',
      'detectionType',
      'evaluationGrade',
      'evaluationResult',
      'negativeNum',
      'detectionUnit',
      'detectionPerson',
      'supervisionUnit',
      'supervisionEngineer',
      'collectionDate'
    ],
    subFields: [
      'defectPosition',
      'defectProperties',
      'defectSize'
    ],
    sonFormDefault: {
      'defectPosition': "",
      'defectProperties': "",
      'defectSize': ""
    },
    detail: [
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'weldCode',
      'detectionReportNum',
      'detectionDate',
      'detectionTypeName',
      'evaluationGradeName',
      'evaluationResult',
      'negativeNum',
      'detectionUnitName',
      'detectionPerson',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionDate'
    ],
    detailSub: [
      'defectPosition',
      'defectPropertiesName',
      'defectSize'
    ],
    addUrl: "/jdbc/commonData/stationDetectionRay/save.do",
    updateUrl: "/jdbc/commonData/stationDetectionRay/update.do",
    deleteUrl: "/jdbc/commonData/stationDetectionRay/delete.do",
    detailUrl: "/jdbc/commonData/stationDetectionRay/getPage.do",
    getList: "/jdbc/commonData/stationDetectionRay/getPage.do",
  },
  stationUltrasonic: {
    searchFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid',
      'weldOid'
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'weldCode',
      'detectionReportNum',
      'detectionDate',
      'detectionTypeName',
      'evaluationGradeName',
      'evaluationResult',
      'detectionUnitName',
      'detectionPerson',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionDate',
      'remarks'
    ],
    addName: ['超声波检测基本信息', '缺陷信息'],
    addFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid',
      'weldOid',
      'detectionReportNum',
      'detectionDate',
      'detectionType',
      'evaluationGrade',
      'evaluationResult',
      'detectionUnit',
      'detectionPerson',
      'supervisionUnit',
      'supervisionEngineer',
      'collectionDate'
    ],
    subFields: [
      'defectPosition',
      'defectProperties',
      'defectLength',
      'defectDepth'
    ],
    sonFormDefault: {
      'defectPosition': "",
      'defectProperties': "",
      'defectLength': "",
      'defectDepth': ""
    },
    detail: [
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'weldCode',
      'detectionReportNum',
      'detectionDate',
      'detectionTypeName',
      'evaluationGradeName',
      'evaluationResult',
      'detectionUnitName',
      'detectionPerson',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionDate'
    ],
    detailSub: [
      'defectPosition',
      'defectPropertiesName',
      'defectLength',
      'defectDepth'
    ],
    addUrl: "/jdbc/commonData/stationDetectionUltrasonic/save.do",
    updateUrl: "/jdbc/commonData/stationDetectionUltrasonic/update.do",
    deleteUrl: "/jdbc/commonData/stationDetectionUltrasonic/delete.do",
    detailUrl: "/jdbc/commonData/stationDetectionUltrasonic/getPage.do",
    getList: "/jdbc/commonData/stationDetectionUltrasonic/getPage.do",
  },
  stationInfiltration: {
    searchFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid',
      'weldOid'
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'weldCode',
      'detectionReportNum',
      'detectionDate',
      'detectionLength',
      'detectionTypeName',
      'evaluationGradeName',
      'evaluationResult',
      'detectionUnitName',
      'detectionPerson',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionDate',
      'remarks'
    ],
    addName: ['渗透检测基本信息', '缺陷信息'],
    addFields: [
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeStationOid',
      'weldOid',
      'detectionReportNum',
      'detectionDate',
      'detectionLength',
      'detectionType',
      'evaluationGrade',
      'evaluationResult',
      'detectionUnit',
      'detectionPerson',
      'supervisionUnit',
      'supervisionEngineer',
      'collectionDate'
    ],
    subFields: [
      'defectPosition',
      'defectProperties',
      'defectLength'
    ],
    sonFormDefault: {
      'defectPosition': "",
      'defectProperties': "",
      'defectLength': ""
    },
    detail: [
      'projectName',
      'tendersName',
      'pipelineName',
      'pipeStationName',
      'weldCode',
      'detectionReportNum',
      'detectionDate',
      'detectionLength',
      'detectionTypeName',
      'evaluationGradeName',
      'evaluationResult',
      'detectionUnitName',
      'detectionPerson',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionDate'
    ],
    detailSub: [
      'defectPosition',
      'defectPropertiesName',
      'defectLength'
    ],
    addUrl: "/jdbc/commonData/daqStationDetectionInfiltration/save.do",
    updateUrl: "/jdbc/commonData/daqStationDetectionInfiltration/update.do",
    deleteUrl: "/jdbc/commonData/daqStationDetectionInfiltration/delete.do",
    detailUrl: "/jdbc/commonData/daqStationDetectionInfiltration/getPage.do",
    getList: "/jdbc/commonData/daqStationDetectionInfiltration/getPage.do",
  },
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
      childSelect: ['weldOid'],
      childUrl: ['daq/processWeld/getProcessWeldList.do'],
      isRequired: true,
      isInit: true
    },
    pipeStationName: {
      name: '站场/阀室编号',
    },
    weldOid: {
      type: 'select',
      name: '焊口编号',
      isRequired: true,
      isInit: true
    },
    weldCode: {
      name: '焊口编号'
    },
    weldName: {
      name: '焊口编号',
    },
    detectionReportNum: {
      type: 'input',
      name: '检测报告编号',
      isRequired: true,
      rules: [{
        min: "0",
        max: 60,
        message: "不能超过60个字"
      }]
    },
    detectionDate: {
      type: "date",
      isRequired: true,
      name: "检测日期"
    },
    detectionType: {
      type: 'select',
      name: '检测类型',
      isRequired: true,
      domainName: 'detection_type_domain'
    },
    detectionTypeName: {
      name: '检测类型',
    },
    evaluationGrade: {
      type: 'select',
      name: '评定等级',
      isRequired: true,
      domainName: 'evaluation_grade_domain'
    },
    evaluationGradeName: {
      name: '评定等级'
    },
    evaluationResult: {
      type: 'select',
      name: "评定结果",
      options: [{
        value: "不合格",
        key: 0
      }, {
        value: "合格",
        key: 1
      }],
      formatter: function (a, b, value, c) {
        if (value == 1) return '合格';
        return '不合格';
      }
    },
    negativeNum: {
      type: 'input',
      name: '底片编号',
      isRequired: true,
      rules: [{
        min: "0",
        max: 30,
        message: "不能超过30个字"
      }]
    },
    detectionUnit: {
      type: 'select',
      name: '检测单位',
      optionUrl: '/daq/privilege/getCurrentUnitId.do',
      isRequired: true,
      disabled: true,
//      isInit: true
    },
    detectionUnitName: {
      name: '检测单位',
    },
    supervisionUnit: {
      type: 'select',
      name: '监理单位',
      isRequired: true
    },
    detectionPerson: {
      type: 'input',
      name: '检测人员',
      rules: [{
        min: "0",
        max: 25,
        message: "不能超过25个字"
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
      isRequired: true,
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
    defectPosition: {
      type: 'input',
      name: '缺陷位置',
      rules: [{
        min: "0",
        max: 60,
        message: "不能超过60个字"
      }]
    },
    defectProperties: {
      type: 'select',
      name: '缺陷性质',
      domainName: 'defect_properties_domain'
    },
    defectPropertiesName: {
      name: '缺陷性质',
    },
    defectSize: {
      type: "number",
      name: '缺陷尺寸',
      max: 999999.999,
      precision: 3
    },
    defectLength: {
      type: "number",
      name: '缺陷长度',
      max: 999999.999,
      precision: 3
    },
    defectDepth: {
      type: "number",
      name: '缺陷深度',
      max: 999999.999,
      precision: 3
    },
    detectionLength: {
      type: "number",
      isRequired: true,
      name: '检测长度',
      max: 999999.999,
      precision: 3
    },
    remarks: {
      name: "备注",
      type: "textarea"
    }
  },
  methods: {
    formatForm: function (fields, subName) {
      var obj = {};
      fields.forEach(function (item) {
        obj[item] = '';
      });
      obj.remarks = '';
      obj[subName] = [];
      return obj;
    },
    formatFields: function (fields, fieldsConfig, templateCode) {
      return fields.map(function (item) {
        return {
          name: fieldsConfig[item].name,
          field: item,
          formatter: fieldsConfig[item].formatter,
        };
      });
    },
  }
}