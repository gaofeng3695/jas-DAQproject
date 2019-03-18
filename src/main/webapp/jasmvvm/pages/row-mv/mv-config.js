var mvConfig = {
  mvPipeNode: {
    addUrl: "/jdbc/commonData/daqMvPipeNode/save.do",
    updateUrl: "/jdbc/commonData/daqMvPipeNode/update.do",
    deleteUrl: "/jdbc/commonData/daqMvPipeNode/delete.do",
    detailUrl: "/jdbc/commonData/daqMvPipeNode/getPage.do",
    getList: "/jdbc/commonData/daqMvPipeNode/getPage.do",
    searchFields: [
      'projectOid',
      'pipeNodeCode'
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'pipeNodeCode',
      'pipeNodeTypeName',
      'pipeNodeSpec',
      'manufacturer',
      'factoryNum',
      'pointx',
      'pointy',
      'pointz',
      'buriedDepth',
      'userBuilding',
      'isElectronicLabel',
      'electronicLabelTypeName',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addFields1: [ //
      'pipeNodeCode',
      'pipeNodeType',
      'pipeNodeSpec',
      'manufacturer',
      'factoryNum',
      'pointx',
      'pointy',
      'pointz',
      'buriedDepth',
      'userBuilding',
      'isElectronicLabel',
      'electronicLabelType'
    ],
    addNames: ["基本信息", "节点信息"],
    detailFields0: [ //
      "projectName",
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'pipeNodeCode',
      'pipeNodeTypeName',
      'pipeNodeSpec',
      'manufacturer',
      'factoryNum',
      'pointx',
      'pointy',
      'pointz',
      'buriedDepth',
      'userBuilding',
      'isElectronicLabel',
      'electronicLabelTypeName',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['supervisionUnit'],
        childUrl: ['/daq/privilege/getSupervisionUnitByProjectOid.do'],
        requestParams: {
          pipeNetworkTypeCode: "pipe_network_code_002,pipe_network_code_003"
        },
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      constructUnit: {
        type: 'select',
        name: '采集单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '采集单位',
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        isRequired: true,
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }],
      },
      collectionDate: {
        type: "date",
        isRequired: true,
        name: "采集日期",
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        },
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
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
      pipeNodeCode: {
        type: "input",
        name: "节点编号",
        isRequired: true,
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      pipeNodeType: {
        type: "select",
        name: "点类型",
        domainName: "pipe_node_type_domain"
      },
      pipeNodeTypeName: {
        name: "点类型",
      },
      pipeNodeSpec: {
        type: "input",
        name: "规格",
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      manufacturer: {
        type: "input",
        name: "生产厂家",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      },
      factoryNum: {
        type: "input",
        name: "出厂编号",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      },
      pointx: {
        type: 'number',
        name: 'X坐标',
        max: 999.999999999,
        min: 0,
        precision: 9,
        isRequired: true
      },
      pointy: {
        type: 'number',
        name: 'Y坐标',
        max: 999.999999999,
        min: 0,
        precision: 9,
        isRequired: true
      },
      pointz: {
        type: 'number',
        name: '管顶高程(m)',
        max: 9999.99,
        min: 0,
        precision: 2,
        isRequired: true
      },
      buriedDepth: {
        type: 'number',
        name: '埋深(m)',
        max: 99999.99,
        min: 0,
        precision: 2,
        isRequired: true
      },
      userBuilding: {
        type: "input",
        name: "用户楼宇",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      },
      isElectronicLabel: {
        type: "select",
        name: "是否设置电子标签",
        isRequired: true,
        options: [{
          key: 0,
          value: "否"
        }, {
          key: 1,
          value: "是"
        }],
        formatter: function (a, b, value, c) {
          if (value == 0) return "否";
          if (value == 1) return "是";
        }
      },
      electronicLabelType: {
        type: "select",
        name: "电子标签类型",
        domainName: "electronic_label_type_domain"
      },
      electronicLabelTypeName: {
        name: "电子标签类型"
      }
    }
  },
  mvPipeSection: { //管段信息表
    addUrl: "/jdbc/commonData/mvPipeSection/save.do",
    updateUrl: "/jdbc/commonData/mvPipeSection/update.do",
    deleteUrl: "/jdbc/commonData/mvPipeSection/delete.do",
    detailUrl: "/jdbc/commonData/mvPipeSection/getPage.do",
    getList: "/jdbc/commonData/mvPipeSection/getPage.do",
    searchFields: [
      'projectOid',
      'pipeSectionCode'
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'pipeSectionCode',
      'startPipeNodeCode',
      'endPipeNodeCode',
      'pipeSectionLength',
      'pipeDiameter',
      'wallThickness',
      'pipeSectionMaterialName',
      'pipeSectionSpecName',
      'designLife',
      'pipeOuterAnticorrosive',
      'outerAnticorrosiveGrade',
      'cathodicProtectionMethod',
      'burialMethod',
      'pipeSectionCategory',
      'startPointx',
      'startPointy',
      'startPointz',
      'endPointx',
      'endPointy',
      'endPointz',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      'pipeSectionCode',
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addFields1: [ //
      'startPipeNodeOid',
      'endPipeNodeOid',
      'pipeSectionLength',
      'pipeDiameter',
      'wallThickness',
      'pipeSectionMaterial',
      'pipeSectionSpec',
      'designLife',
      'pipeOuterAnticorrosive',
      'outerAnticorrosiveGrade',
      'cathodicProtectionMethod',
      'burialMethod',
      'pipeSectionCategory',
      'startPointx',
      'startPointy',
      'startPointz',
      'endPointx',
      'endPointy',
      'endPointz',
    ],
    addNames: ["基本信息", "管段信息"],
    detailFields0: [ //
      "projectName",
      "pipeSectionCode",
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'startPipeNodeCode',
      'endPipeNodeCode',
      'pipeSectionLength',
      'pipeDiameter',
      'wallThickness',
      'pipeSectionMaterialName',
      'pipeSectionSpecName',
      'designLife',
      'pipeOuterAnticorrosive',
      'outerAnticorrosiveGrade',
      'cathodicProtectionMethod',
      'burialMethod',
      'pipeSectionCategory',
      'startPointx',
      'startPointy',
      'startPointz',
      'endPointx',
      'endPointy',
      'endPointz',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['supervisionUnit', 'startPipeNodeOid', 'endPipeNodeOid'],
        childUrl: ['/daq/privilege/getSupervisionUnitByProjectOid.do', '/jdbc/commonData/daqMvPipeNode/getDaqMvPipeNodeList.do', '/jdbc/commonData/daqMvPipeNode/getDaqMvPipeNodeList.do'],
        requestParams: {
          pipeNetworkTypeCode: "pipe_network_code_002,pipe_network_code_003"
        },
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      constructUnit: {
        type: 'select',
        name: '采集单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '采集单位',
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        isRequired: true,
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }],
      },
      collectionDate: {
        type: "date",
        isRequired: true,
        name: "采集日期",
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        },
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
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
      pipeSectionCode: {
        type: "input",
        name: "管段编号",
        isRequired: true,
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      startPipeNodeCode: {
        name: "起始节点",
      },
      startPipeNodeOid: {
        type: "select",
        name: "起始节点",
        isRequired: true,
        subDefault: {
          startPointx: {
            value: "pointx",
          },
          startPointy: {
            value: "pointy",
          },
          startPointz: {
            value: "pointz",
          }
        },
      },
      endPipeNodeCode: {
        name: "终止节点",
      },
      endPipeNodeOid: {
        type: "select",
        name: "终止节点",
        isRequired: true,
        subDefault: {
          endPointx: {
            value: "pointx",
          },
          endPointy: {
            value: "pointy",
          },
          endPointz: {
            value: "pointz",
          }
        },

      },
      pipeSectionLength: {
        type: "number",
        name: "管段长度(m)",
        isRequired: true,
        precision: 3,
        max: 999999999.999
      },
      pipeDiameter: {
        type: "number",
        name: "管径(mm)",
        isRequired: true,
        precision: 3,
        max: 999999.999
      },
      wallThickness: {
        type: "number",
        name: "壁厚(mm)",
        isRequired: true,
        precision: 3,
        max: 999999.999
      },
      pipeSectionMaterial: {
        type: "select",
        name: "材质",
        isRequired: true,
        domainName: "pipe_section_material_domain",
      },
      pipeSectionMaterialName: {
        name: "材质",
      },
      pipeSectionSpec: {
        type: "select",
        name: "规格",
        isRequired: true,
        domainName: "pipe_section_spec_domain",
      },
      pipeSectionSpecName: {
        name: "规格"
      },
      designLife: {
        type: "select",
        name: "管道设计年限(年)",
        options: [{
          key: 1,
          value: "30"
        }, {
          key: 2,
          value: "50"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return "30";
          if (c == 2) return "50"
        }
      },
      pipeOuterAnticorrosive: {
        type: "select",
        name: "管道外防腐",
        options: [{
          key: 1,
          value: '3PE'
        }, {
          key: 2,
          value: "环氧粉末"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return '3PE';
          if (c == 2) return "环氧粉末"
        }
      },
      outerAnticorrosiveGrade: {
        type: "select",
        name: "防腐等级",
        options: [{
          key: 1,
          value: '普通级'
        }, {
          key: 2,
          value: "加强级"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return '普通级';
          if (c == 2) return "加强级"
        }
      },
      cathodicProtectionMethod: {
        type: "select",
        name: "阴极保护方式",
        options: [{
          key: 1,
          value: '牺牲阳极'
        }, {
          key: 2,
          value: "外加电流"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return '牺牲阳极';
          if (c == 2) return "外加电流"
        }
      },
      burialMethod: {
        type: "select",
        name: "埋设方式",
        isRequired: true,
        options: [{
          key: 1,
          value: '埋地管'
        }, {
          key: 2,
          value: "明管"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return '埋地管';
          if (c == 2) return "明管"
        }
      },
      pipeSectionCategory: {
        type: "select",
        name: "管段类别",
        isRequired: true,
        options: [{
          key: 1,
          value: '市政管'
        }, {
          key: 2,
          value: "庭院管"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return '市政管';
          if (c == 2) return "庭院管"
        }
      },
      startPointx: {
        type: 'number',
        name: '起始点X坐标',
        max: 999.999999999,
        min: 0,
        precision: 9,
        disabled: true
      },
      startPointy: {
        type: 'number',
        name: '起始点Y坐标',
        max: 999.999999999,
        min: 0,
        precision: 9,
        disabled: true
      },
      startPointz: {
        type: 'number',
        name: '起始点管顶高程(m)',
        max: 9999.99,
        min: 0,
        precision: 2,
        disabled: true
      },
      endPointx: {
        type: 'number',
        name: '终止点X坐标',
        max: 999.999999999,
        min: 0,
        precision: 7,
        disabled: true
      },
      endPointy: {
        type: 'number',
        name: '终止点Y坐标',
        max: 999.999999999,
        min: 0,
        precision: 9,
        disabled: true
      },
      endPointz: {
        type: 'number',
        name: '终止点管顶高程(m)',
        max: 9999.99,
        min: 0,
        precision: 2,
        disabled: true
      }
    }
  },
  mvAcrossInfo: { //穿越信息表
    addUrl: "/jdbc/commonData/daqMvAcrossInfo/save.do",
    updateUrl: "/jdbc/commonData/daqMvAcrossInfo/update.do",
    deleteUrl: "/jdbc/commonData/daqMvAcrossInfo/delete.do",
    detailUrl: "/jdbc/commonData/daqMvAcrossInfo/getPage.do",
    getList: "/jdbc/commonData/daqMvAcrossInfo/getPage.do",
    searchFields: [
      'projectOid',
      'pipeSectionCode'
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'pipeSectionCode',
      'startPipeNodeName',
      'endPipeNodeName',
      'pipeSectionLength',
      'acrossMethodName',
      'acrossObjectName',
      'burialMethod',
      'pipeSectionCategory',
      'pipeSectionMaterialName',
      'pipeSectionSpecName',
      'outerDiameter',
      'wallThickness',
      'designLife',
      'startPointx',
      'startPointy',
      'startPointz',
      'endPointx',
      'endPointy',
      'endPointz',
      'measureUnit',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      'pipeSectionCode',
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addFields1: [ //
      'startPipeNodeOid',
      'endPipeNodeOid',
      'pipeSectionLength',
      'acrossMethod',
      'acrossObject',
      'burialMethod',
      'pipeSectionCategory',
      'pipeSectionMaterial',
      'pipeSectionSpec',
      'outerDiameter',
      'wallThickness',
      'designLife',
      'measureUnit',
      'startPointx',
      'startPointy',
      'startPointz',
      'endPointx',
      'endPointy',
      'endPointz',
    ],
    addNames: ["基本信息", "穿越信息"],
    detailFields0: [ //
      "projectName",
      "pipeSectionCode",
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'startPipeNodeName',
      'endPipeNodeName',
      'pipeSectionLength',
      'acrossMethodName',
      'acrossObjectName',
      'burialMethod',
      'pipeSectionCategory',
      'pipeSectionMaterialName',
      'pipeSectionSpecName',
      'outerDiameter',
      'wallThickness',
      'designLife',
      'measureUnit',
      'startPointx',
      'startPointy',
      'startPointz',
      'endPointx',
      'endPointy',
      'endPointz',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['supervisionUnit', 'startPipeNodeOid', 'endPipeNodeOid'],
        childUrl: ['/daq/privilege/getSupervisionUnitByProjectOid.do', '/jdbc/commonData/daqMvPipeNode/getDaqMvPipeNodeList.do', '/jdbc/commonData/daqMvPipeNode/getDaqMvPipeNodeList.do'],
        requestParams: {
          pipeNetworkTypeCode: "pipe_network_code_002,pipe_network_code_003"
        },
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      constructUnit: {
        type: 'select',
        name: '采集单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '采集单位',
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        isRequired: true,
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }],
      },
      collectionDate: {
        type: "date",
        isRequired: true,
        name: "采集日期",
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        },
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
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
      pipeSectionCode: {
        type: "input",
        name: "管段编号",
        isRequired: true,
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      startPipeNodeOid: {
        type: "select",
        name: "起始节点",
        isRequired: true,
        subDefault: {
          startPointx: {
            value: "pointx",
          },
          startPointy: {
            value: "pointy",
          },
          startPointz: {
            value: "pointz",
          }
        }
      },
      startPipeNodeName: {
        name: "起始节点"
      },
      endPipeNodeName: {
        name: "终止节点",
      },
      endPipeNodeOid: {
        type: "select",
        name: "终止节点",
        isRequired: true,
        subDefault: {
          endPointx: {
            value: "pointx",
          },
          endPointy: {
            value: "pointy",
          },
          endPointz: {
            value: "pointz",
          }
        }
      },
      pipeSectionLength: {
        type: "number",
        name: "管段长度(m)",
        isRequired: true,
        precision: 3,
        max: 999999999.999
      },
      acrossMethod: {
        type: "select",
        name: "穿越方式",
        isRequired: true,
        domainName: "across_method_domain"
      },
      acrossMethodName: {
        name: "穿越方式"
      },
      acrossObjectName: {
        name: "穿越对象类型"
      },
      acrossObject: {
        type: "select",
        name: "穿越对象类型",
        isRequired: true,
        domainName: "across_object_domain"
      },
      burialMethod: {
        type: "select",
        name: "埋设方式",
        options: [{
          key: 1,
          value: '埋地管'
        }, {
          key: 2,
          value: "明管"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return '埋地管';
          if (c == 2) return "明管"
        }
      },
      pipeSectionCategory: {
        type: "select",
        name: "管段类别",
        options: [{
          key: 1,
          value: '市政管'
        }, {
          key: 2,
          value: "庭院管"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return '市政管';
          if (c == 2) return "庭院管"
        }
      },
      pipeSectionMaterial: {
        type: "select",
        name: "材质",
        isRequired: true,
        domainName: "pipe_section_material_domain",
      },
      pipeSectionMaterialName: {
        name: "材质",
      },
      pipeSectionSpec: {
        type: "select",
        name: "规格",
        isRequired: true,
        domainName: "pipe_section_spec_domain",
      },
      pipeSectionSpecName: {
        name: "规格"
      },
      outerDiameter: {
        type: "number",
        name: "外径(mm)",
        isRequired: true,
        precision: 3,
        max: 999999.999
      },
      wallThickness: {
        type: "number",
        name: "壁厚(mm)",
        isRequired: true,
        precision: 3,
        max: 999999.999
      },
      designLife: {
        type: "select",
        name: "管道设计年限(年)",
        options: [{
          key: 1,
          value: "30"
        }, {
          key: 2,
          value: "50"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return "30";
          if (c == 2) return "50"
        }
      },
      measureUnit: {
        type: "input",
        name: "陀螺仪测量单位",
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      startPointx: {
        type: 'number',
        name: '起始点X坐标',
        max: 999.999999999,
        min: 0,
        precision: 9,
        disabled: true
      },
      startPointy: {
        type: 'number',
        name: '起始点Y坐标',
        max: 999.999999999,
        min: 0,
        precision: 9,
        disabled: true
      },
      startPointz: {
        type: 'number',
        name: '起始点管顶高程(m)',
        max: 9999.99,
        min: 0,
        precision: 2,
        disabled: true
      },
      endPointx: {
        type: 'number',
        name: '终止点X坐标',
        max: 999.999999999,
        min: 0,
        precision: 9,
        disabled: true
      },
      endPointy: {
        type: 'number',
        name: '终止点Y坐标',
        max: 999.999999999,
        min: 0,
        precision: 9,
        disabled: true
      },
      endPointz: {
        type: 'number',
        name: '终止点管顶高程(m)',
        max: 9999.99,
        min: 0,
        precision: 2,
        disabled: true
      }
    }
  },
  //
  mvStrideAcrossInfo: { //跨越信息表
    addUrl: "/jdbc/commonData/daqMvStrideAcrossInfo/save.do",
    updateUrl: "/jdbc/commonData/daqMvStrideAcrossInfo/update.do",
    deleteUrl: "/jdbc/commonData/daqMvStrideAcrossInfo/delete.do",
    detailUrl: "/jdbc/commonData/daqMvStrideAcrossInfo/getPage.do",
    getList: "/jdbc/commonData/daqMvStrideAcrossInfo/getPage.do",
    searchFields: [
      'projectOid',
      'pipeSectionCode'
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'pipeSectionCode',
      'startPipeNodeName',
      'endPipeNodeName',
      'pipeSectionLength',
      'acrossMethodName',
      'acrossObjectName',
      'burialMethod',
      'pipeSectionCategory',
      'pipeSectionMaterialName',
      'pipeSectionSpecName',
      'outerDiameter',
      'wallThickness',
      'designLife',
      'startPointx',
      'startPointy',
      'startPointz',
      'endPointx',
      'endPointy',
      'endPointz',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      'pipeSectionCode',
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addFields1: [ //
      'startPipeNodeOid',
      'endPipeNodeOid',
      'pipeSectionLength',
      'acrossMethod',
      'acrossObject',
      'burialMethod',
      'pipeSectionCategory',
      'pipeSectionMaterial',
      'pipeSectionSpec',
      'outerDiameter',
      'wallThickness',
      'designLife',
      'startPointx',
      'startPointy',
      'startPointz',
      'endPointx',
      'endPointy',
      'endPointz',
    ],
    addNames: ["基本信息", "跨越信息"],
    detailFields0: [ //
      "projectName",
      "pipeSectionCode",
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'startPipeNodeName',
      'endPipeNodeName',
      'pipeSectionLength',
      'acrossMethodName',
      'acrossObjectName',
      'burialMethod',
      'pipeSectionCategory',
      'pipeSectionMaterialName',
      'pipeSectionSpecName',
      'outerDiameter',
      'wallThickness',
      'designLife',
      'startPointx',
      'startPointy',
      'startPointz',
      'endPointx',
      'endPointy',
      'endPointz',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['supervisionUnit', 'startPipeNodeOid', 'endPipeNodeOid'],
        childUrl: ['/daq/privilege/getSupervisionUnitByProjectOid.do', '/jdbc/commonData/daqMvPipeNode/getDaqMvPipeNodeList.do', '/jdbc/commonData/daqMvPipeNode/getDaqMvPipeNodeList.do'],
        requestParams: {
          pipeNetworkTypeCode: "pipe_network_code_002,pipe_network_code_003"
        },
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      constructUnit: {
        type: 'select',
        name: '采集单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '采集单位',
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        isRequired: true,
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }],
      },
      collectionDate: {
        type: "date",
        isRequired: true,
        name: "采集日期",
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        },
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
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
      pipeSectionCode: {
        type: "input",
        name: "管段编号",
        // isRequired: true,
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      startPipeNodeOid: {
        type: "select",
        name: "起始节点",
        isRequired: true,
        subDefault: {
          startPointx: {
            value: "pointx",
          },
          startPointy: {
            value: "pointy",
          },
          startPointz: {
            value: "pointz",
          }
        },
      },
      startPipeNodeName: {
        name: "起始节点"
      },
      endPipeNodeName: {
        name: "终止节点"
      },
      endPipeNodeOid: {
        type: "select",
        name: "终止节点",
        isRequired: true,
        subDefault: {
          endPointx: {
            value: "pointx",
          },
          endPointy: {
            value: "pointy",
          },
          endPointz: {
            value: "pointz",
          }
        },
      },
      pipeSectionLength: {
        type: "number",
        name: "管段长度(m)",
        isRequired: true,
        precision: 3,
        max: 999999999.999
      },
      acrossMethod: {
        type: "select",
        name: "穿越方式",
        isRequired: true,
        domainName: "across_method_domain"
      },
      acrossMethodName: {
        name: "穿越方式"
      },
      acrossObject: {
        type: "select",
        name: "穿越对象类型",
        isRequired: true,
        domainName: "across_object_domain"
      },
      acrossObjectName: {
        name: "穿越对象类型"
      },
      burialMethod: {
        type: "select",
        name: "埋设方式",
        options: [{
          key: 1,
          value: '埋地管'
        }, {
          key: 2,
          value: "明管"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return '埋地管';
          if (c == 2) return "明管"
        }
      },
      pipeSectionCategory: {
        type: "select",
        name: "管段类别",
        options: [{
          key: 1,
          value: '市政管'
        }, {
          key: 2,
          value: "庭院管"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return '市政管';
          if (c == 2) return "庭院管"
        }
      },
      pipeSectionMaterial: {
        type: "select",
        name: "材质",
        isRequired: true,
        domainName: "pipe_section_material_domain",
      },
      pipeSectionMaterialName: {
        name: "材质",
      },
      pipeSectionSpec: {
        type: "select",
        name: "规格",
        isRequired: true,
        domainName: "pipe_section_spec_domain",
      },
      pipeSectionSpecName: {
        name: "规格"
      },
      outerDiameter: {
        type: "number",
        name: "外径(mm)",
        isRequired: true,
        precision: 3,
        max: 999999.999
      },
      wallThickness: {
        type: "number",
        name: "壁厚(mm)",
        isRequired: true,
        precision: 3,
        max: 999999.999
      },
      designLife: {
        type: "select",
        name: "管道设计年限(年)",
        options: [{
          key: 1,
          value: "30"
        }, {
          key: 2,
          value: "50"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return "30";
          if (c == 2) return "50"
        }
      },
      startPointx: {
        type: 'number',
        name: '起始点X坐标',
        max: 999.999999999,
        min: 0,
        precision: 9,
        disabled: true
      },
      startPointy: {
        type: 'number',
        name: '起始点Y坐标',
        max: 999.999999999,
        min: 0,
        precision: 9,
        disabled: true
      },
      startPointz: {
        type: 'number',
        name: '起始点管顶高程(m)',
        max: 9999.99,
        min: 0,
        precision: 2,
        disabled: true
      },
      endPointx: {
        type: 'number',
        name: '终止点X坐标',
        max: 999.999999999,
        min: 0,
        precision: 9,
        disabled: true
      },
      endPointy: {
        type: 'number',
        name: '终止点Y坐标',
        max: 999.999999999,
        min: 0,
        precision: 9,
        disabled: true
      },
      endPointz: {
        type: 'number',
        name: '终止点管顶高程(m)',
        max: 9999.99,
        min: 0,
        precision: 2,
        disabled: true
      }
    }
  },
  mvPipeTrenchProtect: { //管沟信息表
    addUrl: "/jdbc/commonData/daqMvPipeTrenchProtect/save.do",
    updateUrl: "/jdbc/commonData/daqMvPipeTrenchProtect/update.do",
    deleteUrl: "/jdbc/commonData/daqMvPipeTrenchProtect/delete.do",
    detailUrl: "/jdbc/commonData/daqMvPipeTrenchProtect/getPage.do",
    getList: "/jdbc/commonData/daqMvPipeTrenchProtect/getPage.do",
    searchFields: [
      'projectOid'
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'pipeTrenchLength',
      'pipeTrenchWidth',
      'pipeTrenchHeight',
      'startPointx',
      'startPointy',
      'startPointz',
      'endPointx',
      'endPointy',
      'endPointz',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addFields1: [ //
      'pipeTrenchLength',
      'pipeTrenchWidth',
      'pipeTrenchHeight',
      'startPointx',
      'startPointy',
      'startPointz',
      'endPointx',
      'endPointy',
      'endPointz',
    ],
    addNames: ["基本信息", "管沟信息"],
    detailFields0: [ //
      "projectName",
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'pipeTrenchLength',
      'pipeTrenchWidth',
      'pipeTrenchHeight',
      'startPointx',
      'startPointy',
      'startPointz',
      'endPointx',
      'endPointy',
      'endPointz',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['supervisionUnit'],
        childUrl: ['/daq/privilege/getSupervisionUnitByProjectOid.do'],
        requestParams: {
          pipeNetworkTypeCode: "pipe_network_code_002,pipe_network_code_003"
        },
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      constructUnit: {
        type: 'select',
        name: '采集单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '采集单位',
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        isRequired: true,
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }],
      },
      collectionDate: {
        type: "date",
        isRequired: true,
        name: "采集日期",
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        },
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
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
      pipeTrenchLength: {
        type: 'number',
        name: '管沟长度(m)',
        isRequired: true,
        max: 9999999.99,
        min: 0,
        precision: 2
      },
      pipeTrenchWidth: {
        type: 'number',
        name: '管沟宽度(m)',
        //   isRequired: true,
        max: 999999.999,
        min: 0,
        precision: 3
      },
      pipeTrenchHeight: {
        type: 'number',
        name: '管沟高度(m)',
        //        isRequired: true,
        max: 99999.9999,
        min: 0,
        precision: 4
      },
      startPointx: {
        type: 'number',
        name: '起始点X坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      startPointy: {
        type: 'number',
        name: '起始点Y坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      startPointz: {
        type: 'number',
        name: '起始点管顶高程(m)',
        isRequired: true,
        max: 9999.99,
        min: 0,
        precision: 2
      },

      endPointx: {
        type: 'number',
        name: '终止点X坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      endPointy: {
        type: 'number',
        name: '终止点Y坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      endPointz: {
        type: 'number',
        name: '终止点管顶高程(m)',
        isRequired: true,
        max: 9999.99,
        min: 0,
        precision: 2
      },
    }
  },
  mvBushingInfo: { //套管信息表
    addUrl: "/jdbc/commonData/daqMvBushingInfo/save.do",
    updateUrl: "/jdbc/commonData/daqMvBushingInfo/update.do",
    deleteUrl: "/jdbc/commonData/daqMvBushingInfo/delete.do",
    detailUrl: "/jdbc/commonData/daqMvBushingInfo/getPage.do",
    getList: "/jdbc/commonData/daqMvBushingInfo/getPage.do",
    searchFields: [
      'projectOid'
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'bushingMaterialName',
      'bushingSpec',
      'startPointx',
      'startPointy',
      'startPointz',
      'startBuriedDepth',
      'endPointx',
      'endPointy',
      'endPointz',
      'endBuriedDepth',
      'hasCheckLeakDevice',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addFields1: [ //
      'bushingMaterial',
      'bushingSpec',
      'startPointx',
      'startPointy',
      'startPointz',
      'startBuriedDepth',
      'endPointx',
      'endPointy',
      'endPointz',
      'endBuriedDepth',
      'hasCheckLeakDevice',
    ],
    addNames: ["基本信息", "套管信息"],
    detailFields0: [ //
      "projectName",
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'bushingMaterialName',
      'bushingSpec',
      'startPointx',
      'startPointy',
      'startPointz',
      'startBuriedDepth',
      'endPointx',
      'endPointy',
      'endPointz',
      'endBuriedDepth',
      'hasCheckLeakDevice',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['supervisionUnit'],
        childUrl: ['/daq/privilege/getSupervisionUnitByProjectOid.do'],
        requestParams: {
          pipeNetworkTypeCode: "pipe_network_code_002,pipe_network_code_003"
        },
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      constructUnit: {
        type: 'select',
        name: '采集单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '采集单位',
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        isRequired: true,
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }],
      },
      collectionDate: {
        type: "date",
        isRequired: true,
        name: "采集日期",
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        },
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
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
      bushingMaterial: {
        type: 'select',
        name: '材质',
        domainName: "bushing_material_domain",
        isRequired: true
      },
      bushingMaterialName: {
        name: '材质'
      },
      bushingSpec: {
        type: 'input',
        name: '规格',
        rules: [{
          min: "0",
          max: 40,
          message: "不能超过40个字"
        }],
        isRequired: true
      },
      startPointx: {
        type: 'number',
        name: '起始点X坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      startPointy: {
        type: 'number',
        name: '起始点Y坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      startPointz: {
        type: 'number',
        name: '起始点管顶高程(m)',
        isRequired: true,
        max: 9999.99,
        min: 0,
        precision: 2
      },
      startBuriedDepth: {
        type: 'number',
        name: '起始点埋深(m)',
        isRequired: true,
        max: 99999.99,
        min: 0,
        precision: 2
      },
      endPointx: {
        type: 'number',
        name: '终止点X坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      endPointy: {
        type: 'number',
        name: '终止点Y坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      endPointz: {
        type: 'number',
        name: '终止点管顶高程(m)',
        isRequired: true,
        max: 9999.99,
        min: 0,
        precision: 2
      },
      endBuriedDepth: {
        type: 'number',
        name: '终止点埋深(m)',
        isRequired: true,
        max: 99999.99,
        min: 0,
        precision: 2
      },
      hasCheckLeakDevice: {
        type: "select",
        name: "是否设置捡漏装置",
        options: [{
          key: 0,
          value: "否"
        }, {
          key: 1,
          value: "是"
        }],
        formatter: function (a, b, c, d) {
          if (c == 0) return "否";
          if (c == 1) return "是";
        }
      }
    }
  },
  mvValveInfo: { //阀门信息表
    addUrl: "/jdbc/commonData/mvValveInfo/save.do",
    updateUrl: "/jdbc/commonData/mvValveInfo/update.do",
    deleteUrl: "/jdbc/commonData/mvValveInfo/delete.do",
    detailUrl: "/jdbc/commonData/mvValveInfo/getPage.do",
    getList: "/jdbc/commonData/mvValveInfo/getPage.do",
    searchFields: [
      'projectOid',
      'pipeNodeOid'
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'pipeNodeCode',
      'valveCode',
      'manufacturer',
      'valveSpec',
      'valveTypeName',
      'valveMaterial',
      'burialMethod',
      'nominalDiameter',
      'nominalPressure',
      'pointx',
      'pointy',
      'pointz',
      'buriedDepth',
      'isElectronicLabel',
      'electronicLabelTypeName',
      'valveConnectionMethodName',
      'isHeavyShaftCover',
      'wellCoverType',
      'wellCoverMaterialName',
      'dischargePipe',
      'valveDischargePipeInfo',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      "projectOid",
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addFields1: [ //
      'pipeNodeOid',
      'valveCode',
      'manufacturer',
      'valveSpec',
      'valveType',
      'valveMaterial',
      'burialMethod',
      'nominalDiameter',
      'nominalPressure',
      'pointx',
      'pointy',
      'pointz',
      'buriedDepth',
      'isElectronicLabel',
      'electronicLabelType',
      'valveConnectionMethod',
      'isHeavyShaftCover',
      'wellCoverType',
      'wellCoverMaterial',
      'dischargePipe',
      'valveDischargePipeInfo'
    ],
    addNames: ["基本信息", "阀门信息"],
    detailFields0: [ //
      "projectName",
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    detailFields1: [ //
      'pipeNodeCode',
      'valveCode',
      'manufacturer',
      'valveSpec',
      'valveTypeName',
      'valveMaterial',
      'burialMethod',
      'nominalDiameter',
      'nominalPressure',
      'pointx',
      'pointy',
      'pointz',
      'buriedDepth',
      'isElectronicLabel',
      'electronicLabelTypeName',
      'valveConnectionMethodName',
      'isHeavyShaftCover',
      'wellCoverType',
      'wellCoverMaterialName',
      'dischargePipe',
      'valveDischargePipeInfo',
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['supervisionUnit', 'pipeNodeOid'],
        childUrl: ['/daq/privilege/getSupervisionUnitByProjectOid.do', '/jdbc/commonData/daqMvPipeNode/getDaqMvPipeNodeList.do'],
        requestParams: {
          pipeNetworkTypeCode: "pipe_network_code_002,pipe_network_code_003"
        },
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      constructUnit: {
        type: 'select',
        name: '采集单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '采集单位',
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        isRequired: true,
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }],
      },
      collectionDate: {
        type: "date",
        isRequired: true,
        name: "采集日期",
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        },
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
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
      pipeNodeOid: {
        type: "select",
        name: "节点名称",
        isRequired: true,
        subDefault: {
          pointx: {
            value: "pointx",
          },
          pointy: {
            value: "pointy",
          },
          pointz: {
            value: "pointz",
          },
          buriedDepth: {
            value: "buried_depth"
          }
        },
      },
      pipeNodeCode: {
        name: "节点名称"
      },
      valveCode: {
        type: 'input',
        name: '阀门编号',
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }],
        isRequired: true
      },
      manufacturer: {
        type: 'input',
        name: '生产厂家',
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }],
        isRequired: true
      },
      valveSpec: {
        type: 'input',
        name: '阀门规格',
        isRequired: true,
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }],
      },
      valveType: {
        type: "select",
        name: "阀门类型",
        isRequired: true,
        domainName: "mv_valve_type_domain"
      },
      valveTypeName: {
        name: "阀门类型"
      },
      valveMaterial: {
        type: "select",
        name: "阀门材质",
        isRequired: true,
        options: [{
          key: 1,
          value: "刚"
        }, {
          key: 2,
          value: "PE"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return "刚";
          if (c == 2) return "PE";
        }
      },
      burialMethod: {
        type: "select",
        name: "埋设方式",
        isRequired: true,
        options: [{
          key: 1,
          value: "直埋"
        }, {
          key: 2,
          value: "阀门井"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return "直埋";
          if (c == 2) return "阀门井";
        }
      },
      nominalDiameter: {
        type: 'number',
        name: '阀门公称直径',
        max: 999999.999,
        min: 0,
        precision: 3
      },
      nominalPressure: {
        type: 'number',
        name: '阀门公称压力',
        max: 999999.999,
        min: 0,
        precision: 3
      },
      pointx: {
        type: 'number',
        name: 'X坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9,
        disabled: true
      },
      pointy: {
        type: 'number',
        name: 'Y坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9,
        disabled: true
      },
      pointz: {
        type: 'number',
        name: '管顶高程(m)',
        isRequired: true,
        max: 9999.99,
        min: 0,
        precision: 2,
        disabled: true
      },
      buriedDepth: {
        type: 'number',
        name: '埋深',
        isRequired: true,
        disabled: true,
        max: 99999.99,
        min: 0,
        precision: 2
      },
      isElectronicLabel: {
        type: 'select',
        name: "是否设置电子标签",
        options: [{
          key: 0,
          value: "否"
        }, {
          key: 1,
          value: "是"
        }],
        formatter: function (a, b, c, d) {
          if (c == 0) return "否";
          if (c == 1) return "是";
        }
      },
      electronicLabelType: {
        type: "select",
        name: "电子标签类型",
        domainName: "electronic_label_type_domain"
      },
      electronicLabelTypeName: {
        name: "电子标签类型"
      },
      valveConnectionMethod: {
        type: "select",
        name: "阀门连接方式",
        isRequired: true,
        domainName: "valve_connection_method_domain"
      },
      valveConnectionMethodName: {
        name: "阀门连接方式"
      },
      isHeavyShaftCover: {
        type: "select",
        name: "阀门是否重型井盖",
        options: [{
          key: 0,
          value: "否"
        }, {
          key: 1,
          value: "是"
        }],
        formatter: function (a, b, c, d) {
          if (c == 0) return "否";
          if (c == 1) return "是";
        }
      },
      wellCoverType: {
        type: "select",
        name: "井盖类型",
        options: [{
          key: 1,
          value: "矩形"
        }, {
          key: 2,
          value: "圆形"
        }],
        formatter: function (a, b, c, d) {
          if (c == 1) return "矩形";
          if (c == 2) return "圆形";
        }
      },
      wellCoverMaterial: {
        type: "select",
        name: "井盖材质",
        domainName: "well_cover_material_domain"
      },
      wellCoverMaterialName: {
        name: "井盖材质"
      },
      dischargePipe: {
        type: 'number',
        name: '放散管径',
        //isRequired: true,
        max: 999999.999,
        min: 0,
        precision: 3
      },
      valveDischargePipeInfo: {
        type: "input",
        name: "阀门放散管信息",
        rules: [{
          min: "0",
          max: 60,
          message: "不能超过60个字"
        }]
      }
    }
  },
  mvMarkStake: { //标志桩信息表
    addUrl: "/jdbc/commonData/mvMarkStake/save.do",
    updateUrl: "/jdbc/commonData/mvMarkStake/update.do",
    deleteUrl: "/jdbc/commonData/mvMarkStake/delete.do",
    detailUrl: "/jdbc/commonData/mvMarkStake/getPage.do",
    getList: "/jdbc/commonData/mvMarkStake/getPage.do",
    searchFields: [
      'projectOid',
      'markStakeCode'
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'markStakeCode',
      'markStakeTypeName',
      'markStakeMaterialName',
      'pointx',
      'pointy',
      'pointz',
      'burialDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      'projectOid',
      'markStakeCode',
      'markStakeType',
      'markStakeMaterial',
      'pointx',
      'pointy',
      'pointz',
      'burialDate',
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addNames: ["基本信息"],
    detailFields0: [ //
      'projectName',
      'markStakeCode',
      'markStakeTypeName',
      'markStakeMaterialName',
      'pointx',
      'pointy',
      'pointz',
      'burialDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['supervisionUnit'],
        childUrl: ['/daq/privilege/getSupervisionUnitByProjectOid.do'],
        requestParams: {
          pipeNetworkTypeCode: "pipe_network_code_002,pipe_network_code_003"
        },
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      constructUnit: {
        type: 'select',
        name: '采集单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '采集单位',
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        isRequired: true,
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }],
      },
      collectionDate: {
        type: "date",
        isRequired: true,
        name: "采集日期",
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        },
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
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
      markStakeCode: {
        type: 'input',
        name: '编号',
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }],
        isRequired: true
      },
      markStakeType: {
        type: 'select',
        name: '标志桩类型',
        domainName: "mark_stake_type_domain",
        isRequired: true
      },
      markStakeTypeName: {
        name: '标志桩类型',
      },
      markStakeMaterial: {
        type: "select",
        name: "标志桩材质",
        isRequired: true,
        domainName: "mark_stake_material_domain"
      },
      markStakeMaterialName: {
        name: "标志桩材质",
      },
      pointx: {
        type: 'number',
        name: 'X坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      pointy: {
        type: 'number',
        name: 'Y坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      pointz: {
        type: 'number',
        name: '地面高程',
        isRequired: true,
        max: 9999.99,
        min: 0,
        precision: 2
      },
      burialDate: {
        type: 'date',
        name: '埋设日期',
        isRequired: true,
      }
    }
  },
  mvElectronicLabel: { //电子标签信息表
    addUrl: "/jdbc/commonData/mvElectronicLabel/save.do",
    updateUrl: "/jdbc/commonData/mvElectronicLabel/update.do",
    deleteUrl: "/jdbc/commonData/mvElectronicLabel/delete.do",
    detailUrl: "/jdbc/commonData/mvElectronicLabel/getPage.do",
    getList: "/jdbc/commonData/mvElectronicLabel/getPage.do",
    searchFields: [
      'projectOid',
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'electronicLabelTypeName',
      'electronicLabelFeaturesName',
      'pointx',
      'pointy',
      'pointz',
      'burialDepth',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      'projectOid',
      'electronicLabelType',
      'electronicLabelFeatures',
      'pointx',
      'pointy',
      'pointz',
      'burialDepth',
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addNames: ["基本信息"],
    detailFields0: [ //
      'projectName',
      'electronicLabelTypeName',
      'electronicLabelFeaturesName',
      'pointx',
      'pointy',
      'pointz',
      'burialDepth',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['supervisionUnit', 'pipeNodeOid'],
        childUrl: ['/daq/privilege/getSupervisionUnitByProjectOid.do', '/jdbc/commonData/daqMvPipeNode/getDaqMvPipeNodeList.do'],
        requestParams: {
          pipeNetworkTypeCode: "pipe_network_code_002,pipe_network_code_003"
        },
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      constructUnit: {
        type: 'select',
        name: '采集单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '采集单位',
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        isRequired: true,
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }],
      },
      collectionDate: {
        type: "date",
        isRequired: true,
        name: "采集日期",
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        },
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
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
      electronicLabelType: {
        type: 'select',
        name: '电子标签类型',
        domainName: "electronic_label_type_domain",
        isRequired: true
      },
      electronicLabelTypeName: {
        name: '电子标签类型',
      },
      electronicLabelFeatures: {
        type: "select",
        isRequired: true,
        name: "电子标签特征点类型",
        domainName: "electronic_label_features_domain"
      },
      electronicLabelFeaturesName: {
        name: "电子标签特征点类型",
      },
      pointx: {
        type: 'number',
        name: 'X坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      pointy: {
        type: 'number',
        name: 'Y坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      pointz: {
        type: 'number',
        name: '管顶高程',
        //        isRequired: true,
        max: 9999.99,
        min: 0,
        precision: 2
      },
      burialDepth: {
        type: 'number',
        name: '埋深',
        precision: 2,
        max: 9999999.99,
        isRequired: true,
      }
    }
  },
  mvMonitorWell: { //监测井信息表
    addUrl: "/jdbc/commonData/mvMonitorWell/save.do",
    updateUrl: "/jdbc/commonData/mvMonitorWell/update.do",
    deleteUrl: "/jdbc/commonData/mvMonitorWell/delete.do",
    detailUrl: "/jdbc/commonData/mvMonitorWell/getPage.do",
    getList: "/jdbc/commonData/mvMonitorWell/getPage.do",
    searchFields: [
      'projectOid',
    ],
    tableFields: [
      'approveStatus',
      'projectName',
      'monitorWellCode',
      'monitorWellPurposeName',
      'pointx',
      'pointy',
      'pointz',
      'monitorWellMaterialName',
      'investmentDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'remarks'
    ],
    addFields0: [
      'projectOid',
      'monitorWellCode',
      'monitorWellPurpose',
      'pointx',
      'pointy',
      'pointz',
      'monitorWellMaterial',
      'investmentDate',
      'constructUnit',
      'collectionPerson',
      'collectionDate',
      'supervisionUnit',
      'supervisionEngineer',
    ],
    addNames: ["基本信息"],
    detailFields0: [ //
      'projectName',
      'monitorWellCode',
      'monitorWellPurposeName',
      'pointx',
      'pointy',
      'pointz',
      'monitorWellMaterialName',
      'investmentDate',
      'constructUnitName',
      'collectionPerson',
      'collectionDate',
      'supervisionUnitName',
      'supervisionEngineer',
      'approveStatus'
    ],
    fieldsConfig: {
      projectOid: {
        type: 'select',
        name: '项目名称',
        optionUrl: '/daq/privilege/getProjectList.do',
        childSelect: ['supervisionUnit'],
        childUrl: ['/daq/privilege/getSupervisionUnitByProjectOid.do'],
        requestParams: {
          pipeNetworkTypeCode: "pipe_network_code_002,pipe_network_code_003"
        },
        isRequired: true,
        isInit: true
      },
      projectName: {
        name: '项目名称',
      },
      constructUnit: {
        type: 'select',
        name: '采集单位',
        optionUrl: '/daq/privilege/getCurrentUnitId.do',
        isRequired: true,
        disabled: true,
      },
      constructUnitName: {
        name: '采集单位',
      },
      collectionPerson: {
        type: 'input',
        name: '采集人员',
        isRequired: true,
        rules: [{
          min: "0",
          max: 30,
          message: "不能超过30个字"
        }],
      },
      collectionDate: {
        type: "date",
        isRequired: true,
        name: "采集日期",
        isLessToday: true, //默认是小于今天的
        pickerOptions: { //用于设置默认是小于今天的
          disabledDate: function (time) {
            return time.getTime() > new Date().getTime()
          }
        },
      },
      supervisionUnit: {
        type: 'select',
        name: '监理单位',
        isRequired: true
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
      monitorWellPurpose: {
        type: 'select',
        name: '用途',
        domainName: "monitor_well_purpose_domain",
        isRequired: true
      },
      monitorWellPurposeName: {
        name: '用途',
      },
      monitorWellCode: {
        type: "input",
        name: "编号",
        isRequired: true,
        rules: [{
          min: "0",
          max: 50,
          message: "不能超过50个字"
        }]
      },
      pointx: {
        type: 'number',
        name: 'X坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      pointy: {
        type: 'number',
        name: 'Y坐标',
        isRequired: true,
        max: 999.999999999,
        min: 0,
        precision: 9
      },
      pointz: {
        type: 'number',
        name: '管顶高程',
        //        isRequired: true,
        max: 9999.99,
        min: 0,
        precision: 2
      },
      investmentDate: {
        type: 'date',
        name: '投用日期',
        //        isRequired: true,
      },
      monitorWellMaterial: {
        type: "select",
        name: "阴保监测井材料",
        domainName: "monitor_well_material_domain"
      },
      monitorWellMaterialName: {
        name: "阴保监测井材料",
      }
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

