var crossConfig = {
  workUnit: {
    searchFields: [ //
      'projectOid', //	项目oid
      'workUnitName', //	机组名称
      'workUnitCode', //	机组编号
    ],
    tableFields: [ //
      // 'projectOid',	 //	项目oid
      'projectName',
      'workUnitName', //	机组名称
      'workUnitCode', //	机组编号
      // 'workUnitType',	 //	机组类型
      'workUnitTypeName', //	机组类型
      // 'constructUnit',	 //	施工单位
      'constructUnitName', //	施工单位
      'remarks', //	备注
    ],
    addFields1: [ //
      'projectOid', //	项目oid
      // 'projectName',
      'constructUnit', //	施工单位
      'workUnitName', //	机组名称
      'workUnitCode', //	机组编号
      'workUnitType', //	机组类型
      // 'workUnitTypeName',	 //	机组类型
      // 'constructUnitName',	 //	施工单位
    ],
    detailFields1: [ //
      'projectName',
      'workUnitName', //	机组名称
      'workUnitCode', //	机组编号
      // 'workUnitType',	 //	机组类型
      'workUnitTypeName', //	机组类型
      // 'constructUnit',	 //	施工单位
      'constructUnitName', //	施工单位
    ],
  },
  fieldsConfig: {
    projectOid: {
      type: 'select',
      name: '项目名称',
      optionUrl: '/daq/privilege/getProjectList.do',
      isInit: true,
      isRequired: true
    },
    projectName: {
      name: '项目名称',
    },
    constructUnit: {
      type: 'cascader',
      name: '施工单位',
      optionUrl: '/daq/workUnit/getUnitListById.do',
      requestParams: {
        "unitOid": "8432ae89-470e-4fb9-a351-f0f48eff5548"
      },
      props: {
        value: 'id',
        label: 'text',
      },
      isRequired: true
    },
    constructUnitName: {
      name: '施工单位',
    },
    workUnitName: {
      type: 'input',
      name: '机组名称',
      isRequired: true,
      rules: [{
        min: "0",
        max: 50,
        message: "不能超过50个字"
      }]
    },
    workUnitCode: {
      type: 'input',
      name: '机组编号',
      isRequired: true,
      rules: [{
        min: "0",
        max: 50,
        message: "不能超过50个字"
      }]
    },
    workUnitType: {
      type: 'select',
      name: '机组类型',
      domainName: 'work_unit_type_domain'
    },
    workUnitTypeName: {
      name: '机组类型',
    },
    remarks: {
      name: '备注',
    },
  },
  methods: {
    formatFields: function (fields, fieldsConfig, templateCode) {
      return fields.map(function (item) {
        if (!fieldsConfig[item]) return {};
        var nameConfig = fieldsConfig[item].nameConfig;
        return {
          name: (nameConfig && nameConfig[templateCode]) ? nameConfig[templateCode] : fieldsConfig[item].name,
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
  },
  url: [
    '/jasmvvm/pages/row-cross/cross-template/cross-template.html?privilegeCode=P-daq-hq-001006001&pageCode=crossExcavation&isApprove=1&className=cn.jasgroup.jasframework.acquisitiondata.material.cross.excavation.dao.entity.DaqCrossExcavation&classNameQuery=cn.jasgroup.jasframework.acquisitiondata.material.cross.excavation.query.DaqCrossExcavationQuery&templateCode=',
  ]
};