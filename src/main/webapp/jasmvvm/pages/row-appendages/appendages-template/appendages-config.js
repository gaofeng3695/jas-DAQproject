var crossConfig = {
  appendagesMarkStake: {
    groupNames: ['基本信息', '标志桩信息'],
    searchFields: [ //
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeSegmentOrCrossOid',
      'medianStakeOid',
      'markStakeCode',
    ],
    tableFields: [ //


      "approveStatus", //: 0,
      // "projectOid",//: "a5667277-c862-4aca-9a23-284d08d5484a",
      "projectName", //: "西气东输工程",
      // "tendersOid",//: "162b8bf9-2b89-4eb1-8f34-bc382b91c64c",
      "tendersName", //: "标段2",
      // "pipelineOid",//: "aa4aaaa7-4697-40c3-a019-1ba16f85b9d3",
      "pipelineName", //: "长输管线A",
      // "pipeSegmentOrCrossOid",//: "da506339-8b7e-4e63-83b0-9bde3bec79bc",
      "pipeSegmentOrCrossName", //: "穿跨越A",


      "markStakeCode", //: "test-1",
      // "medianStakeOid", //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      "medianStakeCode", //: "median_stake_004",
      "relativeMileage", //: -0.5,
      "pointx", //: null,
      "pointy", //: null,
      // "stakeStructure",//: "mark_stake_structure_code_001",
      "stakeStructureName", //: null,
      "burialDate", //: "2017-08-11",
      // "stakeFunction",//: "mark_stake_function_code_001",
      "stakeFunctionName", //: "钢结构",
      "burialDepth", //: 1.22,

      // "constructUnit", //: "94257eab-9536-4cb1-a65b-4d0a8988ad58",
      'constructUnitName',
      // "supervisionUnit", //: "22f8b075-5921-4bd3-a681-ffe3e8bac82a",
      'supervisionUnitName',
      "supervisionEngineer", //: "zyfan",
      "collectionPerson", //: "zyfan",
      "collectionDate", //: "2017-08-12",
    ],
    addFields1: [ //
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeSegmentOrCrossOid",
      'constructUnit',
      // 'constructUnitName',
      'supervisionUnit',
      // 'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
    ],
    addFields2: [ //
      "markStakeCode", //: "test-1",
      "medianStakeOid", //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      // "medianStakeCode", //: "median_stake_004",
      "relativeMileage", //: -0.5,
      "pointx", //: null,
      "pointy", //: null,
      "stakeStructure", //: "mark_stake_structure_code_001",
      // "stakeStructureName", //: null,
      "burialDate", //: "2017-08-11",
      "stakeFunction", //: "mark_stake_function_code_001",
      // "stakeFunctionName", //: "钢结构",
      "burialDepth", //: 1.22,
    ],
    detailFields1: [ //
      "projectName", //: "西气东输工程",
      // "tendersOid",//: "162b8bf9-2b89-4eb1-8f34-bc382b91c64c",
      "tendersName", //: "标段2",
      // "pipelineOid",//: "aa4aaaa7-4697-40c3-a019-1ba16f85b9d3",
      "pipelineName", //: "长输管线A",
      // "pipeSegmentOrCrossOid",//: "da506339-8b7e-4e63-83b0-9bde3bec79bc",
      "pipeSegmentOrCrossName", //: "穿跨越A",
      // 'constructUnit',
      'constructUnitName',
      // 'supervisionUnit',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
      'approveStatus'
    ],
    detailFields2: [ //
      "markStakeCode", //: "test-1",
      // "medianStakeOid",//: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      "medianStakeCode", //: "median_stake_004",
      "relativeMileage", //: -0.5,
      "pointx", //: null,
      "pointy", //: null,
      // "stakeStructure",//: "mark_stake_structure_code_001",
      "stakeStructureName", //: null,
      "burialDate", //: "2017-08-11",
      // "stakeFunction",//: "mark_stake_function_code_001",
      "stakeFunctionName", //: "钢结构",
      "burialDepth", //: 1.22,
    ],
  },
  appendagesElectronicLabel: {
    groupNames: ['基本信息', '电子标签信息'],
    searchFields: [ //
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeSegmentOrCrossOid',
      'medianStakeOid',
      "electronicLabelCode",
      "productNum",
    ],
    tableFields: [ //

      "approveStatus", //: 0,
      // "projectOid",//: "a5667277-c862-4aca-9a23-284d08d5484a",
      "projectName", //: "西气东输工程",
      // "tendersOid",//: "162b8bf9-2b89-4eb1-8f34-bc382b91c64c",
      "tendersName", //: "标段2",
      // "pipelineOid",//: "aa4aaaa7-4697-40c3-a019-1ba16f85b9d3",
      "pipelineName", //: "长输管线A",
      // "pipeSegmentOrCrossOid",//: "da506339-8b7e-4e63-83b0-9bde3bec79bc",
      "pipeSegmentOrCrossName", //: "穿跨越A",


      "electronicLabelCode", //: "ss-1",
      "productNum", //: "ss-1",
      // "medianStakeOid", //: "1a9a3bc9-e499-4fe9-a4d8-a801090eec4c",
      "medianStakeCode", //: "median_stake_001",
      "pointx", //: 115.343,
      "pointy", //: 32.552,
      "pointz", //: 12.15,
      "burialDepth", //: 1,
      // "featurePointType", //: "feature_point_type_code_001",
      "featurePointTypeName", //: "热弯弧顶",
      "burialDate", //: "2017-08-11",

      // "constructUnit", //: "94257eab-9536-4cb1-a65b-4d0a8988ad58",
      'constructUnitName',
      // "supervisionUnit", //: "22f8b075-5921-4bd3-a681-ffe3e8bac82a",
      'supervisionUnitName',
      "supervisionEngineer", //: "zyfan",
      "collectionPerson", //: "zyfan",
      "collectionDate", //: "2017-08-12",
    ],
    addFields1: [ //
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeSegmentOrCrossOid",
      'constructUnit',
      // 'constructUnitName',
      'supervisionUnit',
      // 'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
    ],
    addFields2: [ //
      "electronicLabelCode", //: "test-1",
      "productNum", //: "ss-1",

      "medianStakeOid", //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      // "medianStakeCode", //: "median_stake_004",
      "pointx", //: null,
      "pointy", //: null,
      "pointz", //: 12.15,
      "burialDate", //: "2017-08-11",
      "featurePointType", //: "feature_point_type_code_001",
      // "featurePointTypeName", //: "热弯弧顶",
      "burialDepth", //: 1.22,
    ],
    detailFields1: [ //
      "projectName", //: "西气东输工程",
      // "tendersOid",//: "162b8bf9-2b89-4eb1-8f34-bc382b91c64c",
      "tendersName", //: "标段2",
      // "pipelineOid",//: "aa4aaaa7-4697-40c3-a019-1ba16f85b9d3",
      "pipelineName", //: "长输管线A",
      // "pipeSegmentOrCrossOid",//: "da506339-8b7e-4e63-83b0-9bde3bec79bc",
      "pipeSegmentOrCrossName", //: "穿跨越A",
      // 'constructUnit',
      'constructUnitName',
      // 'supervisionUnit',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
      'approveStatus'
    ],
    detailFields2: [ //
      "electronicLabelCode", //: "test-1",
      "productNum", //: "ss-1",

      // "medianStakeOid", //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      "medianStakeCode", //: "median_stake_004",
      "pointx", //: null,
      "pointy", //: null,
      "pointz", //: 12.15,
      "burialDate", //: "2017-08-11",
      // "featurePointType", //: "feature_point_type_code_001",
      "featurePointTypeName", //: "热弯弧顶",
      "burialDepth", //: 1.22,
    ],
  },
  appendagesHandHole: {
    groupNames: ['基本信息', '手孔信息'],
    searchFields: [ //
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeSegmentOrCrossOid',
      'medianStakeOid',
      "handHoleCode",
      "handHoleName",
      "handHoleType",
    ],
    tableFields: [ //

      "approveStatus", //: 0,
      // "projectOid",//: "a5667277-c862-4aca-9a23-284d08d5484a",
      "projectName", //: "西气东输工程",
      // "tendersOid",//: "162b8bf9-2b89-4eb1-8f34-bc382b91c64c",
      "tendersName", //: "标段2",
      // "pipelineOid",//: "aa4aaaa7-4697-40c3-a019-1ba16f85b9d3",
      "pipelineName", //: "长输管线A",
      // "pipeSegmentOrCrossOid",//: "da506339-8b7e-4e63-83b0-9bde3bec79bc",
      "pipeSegmentOrCrossName", //: "穿跨越A",


      "handHoleCode", //: "SK-median_stake_004-2",
      // "handHoleName", //: "hand_hole_name_code_001",
      "handHoleNameCodeName", //: "接续手孔",
      // "handHoleType", //: "hand_hole_type_code_002",
      "handHoleTypeName", //: "分歧手孔",
      // "medianStakeOid", //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      "medianStakeCode", //: "median_stake_004",
      "relativeMileage", //: 1,
      "pointx", //: 115.005,
      "pointy", //: 38.152,
      "pointz", //: 12.15,
      "handHoleSpecifications", //: "12*24",
      "baseInstallSituation", //: "良好",
      "circleInstallSituation", //: "良好",
      "materialType", //: "混凝土",
      "stayLong", //: 1,
      "isElectronicMark", //: 0,
      "acceptanceResults", //: 0,

      // "constructUnit", //: "94257eab-9536-4cb1-a65b-4d0a8988ad58",
      'constructUnitName',
      // "supervisionUnit", //: "22f8b075-5921-4bd3-a681-ffe3e8bac82a",
      'supervisionUnitName',
      "supervisionEngineer", //: "zyfan",
      "collectionPerson", //: "zyfan",
      "collectionDate", //: "2017-08-12",
    ],
    addFields1: [ //
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeSegmentOrCrossOid",
      'constructUnit',
      // 'constructUnitName',
      'supervisionUnit',
      // 'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
    ],
    addFields2: [ //
      "handHoleCode", //: "SK-median_stake_004-2",
      "handHoleName", //: "hand_hole_name_code_001",
      // "handHoleNameCodeName", //: "接续手孔",
      "handHoleType", //: "hand_hole_type_code_002",
      // "handHoleTypeName", //: "分歧手孔",
      "medianStakeOid", //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      // "medianStakeCode", //: "median_stake_004",
      "relativeMileage", //: 1,
      "pointx", //: 115.005,
      "pointy", //: 38.152,
      "pointz", //: 12.15,
      "handHoleSpecifications", //: "12*24",
      "baseInstallSituation", //: "良好",
      "circleInstallSituation", //: "良好",
      "materialType", //: "混凝土",
      "stayLong", //: 1,
      "isElectronicMark", //: 0,
      "acceptanceResults", //: 0,
    ],
    detailFields1: [ //
      "projectName", //: "西气东输工程",
      // "tendersOid",//: "162b8bf9-2b89-4eb1-8f34-bc382b91c64c",
      "tendersName", //: "标段2",
      // "pipelineOid",//: "aa4aaaa7-4697-40c3-a019-1ba16f85b9d3",
      "pipelineName", //: "长输管线A",
      // "pipeSegmentOrCrossOid",//: "da506339-8b7e-4e63-83b0-9bde3bec79bc",
      "pipeSegmentOrCrossName", //: "穿跨越A",
      // 'constructUnit',
      'constructUnitName',
      // 'supervisionUnit',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
      'approveStatus'
    ],
    detailFields2: [ //
      "handHoleCode", //: "SK-median_stake_004-2",
      // "handHoleName", //: "hand_hole_name_code_001",
      "handHoleNameCodeName", //: "接续手孔",
      // "handHoleType", //: "hand_hole_type_code_002",
      "handHoleTypeName", //: "分歧手孔",
      // "medianStakeOid", //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      "medianStakeCode", //: "median_stake_004",
      "relativeMileage", //: 1,
      "pointx", //: 115.005,
      "pointy", //: 38.152,
      "pointz", //: 12.15,
      "handHoleSpecifications", //: "12*24",
      "baseInstallSituation", //: "良好",
      "circleInstallSituation", //: "良好",
      "materialType", //: "混凝土",
      "stayLong", //: 1,
      "isElectronicMark", //: 0,
      "acceptanceResults", //: 0,
    ],
  },
  appendagesObstacle: {
    groupNames: ['基本信息', '地下障碍物信息'],
    searchFields: [ //
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeSegmentOrCrossOid',
      'medianStakeOid',
      "obstacleCode", //: "ss031",
      "obstacleName", //: "test",
      "obstacleType", //: "obstacle_type_code_001",
    ],
    tableFields: [ //

      "approveStatus", //: 0,
      // "projectOid",//: "a5667277-c862-4aca-9a23-284d08d5484a",
      "projectName", //: "西气东输工程",
      // "tendersOid",//: "162b8bf9-2b89-4eb1-8f34-bc382b91c64c",
      "tendersName", //: "标段2",
      // "pipelineOid",//: "aa4aaaa7-4697-40c3-a019-1ba16f85b9d3",
      "pipelineName", //: "长输管线A",
      // "pipeSegmentOrCrossOid",//: "da506339-8b7e-4e63-83b0-9bde3bec79bc",
      "pipeSegmentOrCrossName", //: "穿跨越A",


      "obstacleCode", //: "ss031",
      "obstacleName", //: "test",
      // "obstacleType", //: "obstacle_type_code_001",
      "obstacleTypeName", //: "输灰管道",
      // "medianStakeOid", //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      "medianStakeCode", //: "median_stake_004",
      "relativeMileage", //: 0.5,
      "pointx", //: 115.005,
      "pointy", //: 38.152,
      "pointz", //: 12.15,
      "subordinateUnit", //: "zz",
      "address", //: null,
      "contacts", //: null,
      "telephone", //: null,
      "minDistance", //: 1,
      "isThroughPipeline", //: 0,

      // "constructUnit", //: "94257eab-9536-4cb1-a65b-4d0a8988ad58",
      'constructUnitName',
      // "supervisionUnit", //: "22f8b075-5921-4bd3-a681-ffe3e8bac82a",
      'supervisionUnitName',
      "supervisionEngineer", //: "zyfan",
      "collectionPerson", //: "zyfan",
      "collectionDate", //: "2017-08-12",
    ],
    addFields1: [ //
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeSegmentOrCrossOid",
      'constructUnit',
      // 'constructUnitName',
      'supervisionUnit',
      // 'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
    ],
    addFields2: [ //
      "obstacleCode", //: "ss031",
      "obstacleName", //: "test",
      "obstacleType", //: "obstacle_type_code_001",
      // "obstacleTypeName", //: "输灰管道",
      "medianStakeOid", //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      // "medianStakeCode", //: "median_stake_004",
      "relativeMileage", //: 0.5,
      "pointx", //: 115.005,
      "pointy", //: 38.152,
      "pointz", //: 12.15,
      "subordinateUnit", //: "zz",
      "address", //: null,
      "contacts", //: null,
      "telephone", //: null,
      "minDistance", //: 1,
      "isThroughPipeline", //: 0,
    ],
    detailFields1: [ //
      "projectName", //: "西气东输工程",
      // "tendersOid",//: "162b8bf9-2b89-4eb1-8f34-bc382b91c64c",
      "tendersName", //: "标段2",
      // "pipelineOid",//: "aa4aaaa7-4697-40c3-a019-1ba16f85b9d3",
      "pipelineName", //: "长输管线A",
      // "pipeSegmentOrCrossOid",//: "da506339-8b7e-4e63-83b0-9bde3bec79bc",
      "pipeSegmentOrCrossName", //: "穿跨越A",
      // 'constructUnit',
      'constructUnitName',
      // 'supervisionUnit',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
      'approveStatus'
    ],
    detailFields2: [ //
      "obstacleCode", //: "ss031",
      "obstacleName", //: "test",
      // "obstacleType", //: "obstacle_type_code_001",
      "obstacleTypeName", //: "输灰管道",
      // "medianStakeOid", //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      "medianStakeCode", //: "median_stake_004",
      "relativeMileage", //: 0.5,
      "pointx", //: 115.005,
      "pointy", //: 38.152,
      "pointz", //: 12.15,
      "subordinateUnit", //: "zz",
      "address", //: null,
      "contacts", //: null,
      "telephone", //: null,
      "minDistance", //: 1,
      "isThroughPipeline", //: 0,
    ],
  },
  appendagesHydraulicProtection: {
    groupNames: ['基本信息', '水工保护信息'],
    searchFields: [ //
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeSegmentOrCrossOid',
      'medianStakeOid',
      "hydraulicProtectionCode",
      "hydraulicProtectionName",
      "hydraulicProtectionType",
    ],
    tableFields: [ //

      "approveStatus", //: 0,
      // "projectOid",//: "a5667277-c862-4aca-9a23-284d08d5484a",
      "projectName", //: "西气东输工程",
      // "tendersOid",//: "162b8bf9-2b89-4eb1-8f34-bc382b91c64c",
      "tendersName", //: "标段2",
      // "pipelineOid",//: "aa4aaaa7-4697-40c3-a019-1ba16f85b9d3",
      "pipelineName", //: "长输管线A",
      // "pipeSegmentOrCrossOid",//: "da506339-8b7e-4e63-83b0-9bde3bec79bc",
      "pipeSegmentOrCrossName", //: "穿跨越A",


      "hydraulicProtectionCode", //: "ss",
      // "hydraulicProtectionType",//: "挡土墙",
      "hydraulicProtectionTypeName", //: null,
      "hydraulicProtectionName", //: "test",
      // "medianStakeOid",//: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      "medianStakeCode", //: "median_stake_004",
      "relativeMileage", //: null,
      "startPointx", //: 115.005,
      "startPointy", //: 38.152,
      "endPointx", //: 115.005,
      "endPointy", //: 38.152,
      "structureSize", //: "12.15",
      "engineerQuatity", //: 12.15,
      // "hydraulicProtectionMaterial",//: "hydraulic_protection_material_code_001",
      "hydraulicProtectionMaterialName", //: null,
      "acceptDate", //: "2017-08-12",
      "inspectionFindings", //: 0,
      // "workUnitOid", //: "4a62dc52-5663-499a-9868-50b86a994c97",
      "workUnitName", //: "机组A",

      // "constructUnit", //: "94257eab-9536-4cb1-a65b-4d0a8988ad58",
      'constructUnitName',
      // "supervisionUnit", //: "22f8b075-5921-4bd3-a681-ffe3e8bac82a",
      'supervisionUnitName',
      "supervisionEngineer", //: "zyfan",
      "collectionPerson", //: "zyfan",
      "collectionDate", //: "2017-08-12",
    ],
    addFields1: [ //
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeSegmentOrCrossOid",
      'constructUnit',
      // 'constructUnitName',
      'supervisionUnit',
      // 'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
    ],
    addFields2: [ //
      "hydraulicProtectionCode", //: "ss",
      "hydraulicProtectionType", //: "挡土墙",
      // "hydraulicProtectionTypeName",//: null,
      "hydraulicProtectionName", //: "test",
      "medianStakeOid", //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      // "medianStakeCode",//: "median_stake_004",
      "relativeMileage", //: null,
      "startPointx", //: 115.005,
      "startPointy", //: 38.152,
      "endPointx", //: 115.005,
      "endPointy", //: 38.152,
      "structureSize", //: "12.15",
      "engineerQuatity", //: 12.15,
      "hydraulicProtectionMaterial", //: "hydraulic_protection_material_code_001",
      // "hydraulicProtectionMaterialName",//: null,
      "acceptDate", //: "2017-08-12",
      "inspectionFindings", //: 0,
      "workUnitOid", //: "4a62dc52-5663-499a-9868-50b86a994c97",
      // "workUnitName", //: "机组A",
    ],
    detailFields1: [ //
      "projectName", //: "西气东输工程",
      // "tendersOid",//: "162b8bf9-2b89-4eb1-8f34-bc382b91c64c",
      "tendersName", //: "标段2",
      // "pipelineOid",//: "aa4aaaa7-4697-40c3-a019-1ba16f85b9d3",
      "pipelineName", //: "长输管线A",
      // "pipeSegmentOrCrossOid",//: "da506339-8b7e-4e63-83b0-9bde3bec79bc",
      "pipeSegmentOrCrossName", //: "穿跨越A",
      // 'constructUnit',
      'constructUnitName',
      // 'supervisionUnit',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
      'approveStatus'
    ],
    detailFields2: [ //
      "hydraulicProtectionCode", //: "ss",
      // "hydraulicProtectionType",//: "挡土墙",
      "hydraulicProtectionTypeName", //: null,
      "hydraulicProtectionName", //: "test",
      // "medianStakeOid",//: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      "medianStakeCode", //: "median_stake_004",
      "relativeMileage", //: null,
      "startPointx", //: 115.005,
      "startPointy", //: 38.152,
      "endPointx", //: 115.005,
      "endPointy", //: 38.152,
      "structureSize", //: "12.15",
      "engineerQuatity", //: 12.15,
      // "hydraulicProtectionMaterial",//: "hydraulic_protection_material_code_001",
      "hydraulicProtectionMaterialName", //: null,
      "acceptDate", //: "2017-08-12",
      "inspectionFindings", //: 0,
      // "workUnitOid", //: "4a62dc52-5663-499a-9868-50b86a994c97",
      "workUnitName", //: "机组A",
    ],
  },
  appendagesCasingPipe: {
    groupNames: ['基本信息', '套管信息'],
    searchFields: [ //
      'projectOid',
      'tendersOid',
      'pipelineOid',
      'pipeSegmentOrCrossOid',
      "startMedianStakeOid",
      "endMedianStakeOid",
    ],
    tableFields: [ //

      "approveStatus", //: 0,
      // "projectOid",//: "a5667277-c862-4aca-9a23-284d08d5484a",
      "projectName", //: "西气东输工程",
      // "tendersOid",//: "162b8bf9-2b89-4eb1-8f34-bc382b91c64c",
      "tendersName", //: "标段2",
      // "pipelineOid",//: "aa4aaaa7-4697-40c3-a019-1ba16f85b9d3",
      "pipelineName", //: "长输管线A",
      // "pipeSegmentOrCrossOid",//: "da506339-8b7e-4e63-83b0-9bde3bec79bc",
      "pipeSegmentOrCrossName", //: "穿跨越A",


      // "startMedianStakeOid",//: "1a9a3bc9-e499-4fe9-a4d8-a801090eec4c",
      "startMedianStakeCode", //: "median_stake_001",
      "startRelativeMileage", //: 1,
      // "endMedianStakeOid",//: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      "endMedianStakeCode", //: "median_stake_001",
      "endRelativeMileage", //: -1,
      "startPointx", //: null,
      "startPointy", //: null,
      "endPointx", //: null,
      "endPointy", //: null,
      // "casingPipeType",//: "casing_pipe_type_code_001",
      "casingPipeTypeName", //: "钢套管",
      "casingPipeLength", //: 12.15,
      "casingPipeSpecifications", //: "11",
      "constructDate", //: "2017-08-12",

      // "constructUnit", //: "94257eab-9536-4cb1-a65b-4d0a8988ad58",
      'constructUnitName',
      // "supervisionUnit", //: "22f8b075-5921-4bd3-a681-ffe3e8bac82a",
      'supervisionUnitName',
      "supervisionEngineer", //: "zyfan",
      "collectionPerson", //: "zyfan",
      "collectionDate", //: "2017-08-12",
    ],
    addFields1: [ //
      "projectOid",
      "tendersOid",
      "pipelineOid",
      "pipeSegmentOrCrossOid",
      'constructUnit',
      // 'constructUnitName',
      'supervisionUnit',
      // 'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
    ],
    addFields2: [ //
      "startMedianStakeOid", //: "1a9a3bc9-e499-4fe9-a4d8-a801090eec4c",
      // "startMedianStakeCode",//: "median_stake_001",
      "startRelativeMileage", //: 1,
      "endMedianStakeOid", //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      // "endMedianStakeCode",//: "median_stake_001",
      "endRelativeMileage", //: -1,
      "startPointx", //: null,
      "startPointy", //: null,
      "endPointx", //: null,
      "endPointy", //: null,
      "casingPipeType", //: "casing_pipe_type_code_001",
      // "casingPipeTypeName",//: "钢套管",
      "casingPipeLength", //: 12.15,
      "casingPipeSpecifications", //: "11",
      "constructDate", //: "2017-08-12",
    ],
    detailFields1: [ //
      "projectName", //: "西气东输工程",
      // "tendersOid",//: "162b8bf9-2b89-4eb1-8f34-bc382b91c64c",
      "tendersName", //: "标段2",
      // "pipelineOid",//: "aa4aaaa7-4697-40c3-a019-1ba16f85b9d3",
      "pipelineName", //: "长输管线A",
      // "pipeSegmentOrCrossOid",//: "da506339-8b7e-4e63-83b0-9bde3bec79bc",
      "pipeSegmentOrCrossName", //: "穿跨越A",
      // 'constructUnit',
      'constructUnitName',
      // 'supervisionUnit',
      'supervisionUnitName',
      'supervisionEngineer',
      'collectionPerson',
      'collectionDate',
      'approveStatus'
    ],
    detailFields2: [ //
      // "startMedianStakeOid",//: "1a9a3bc9-e499-4fe9-a4d8-a801090eec4c",
      "startMedianStakeCode", //: "median_stake_001",
      "startRelativeMileage", //: 1,
      // "endMedianStakeOid",//: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
      "endMedianStakeCode", //: "median_stake_001",
      "endRelativeMileage", //: -1,
      "startPointx", //: null,
      "startPointy", //: null,
      "endPointx", //: null,
      "endPointy", //: null,
      // "casingPipeType",//: "casing_pipe_type_code_001",
      "casingPipeTypeName", //: "钢套管",
      "casingPipeLength", //: 12.15,
      "casingPipeSpecifications", //: "11",
      "constructDate", //: "2017-08-12",
    ],
  },
  fieldsConfig: {
    projectName: {
      name: '项目名称',
    },
    projectOid: {
      type: 'select',
      name: '项目名称',
      optionUrl: '/daq/privilege/getProjectList.do',
      childSelect: ['tendersOid', 'workUnitOid'],
      childUrl: ['/daq/privilege/getTendersList.do', '/daq/workUnit/getWorkUnitList.do'],
      isRequired: true,
      isInit:true
    },
    tendersName: {
      name: '标段名称',
    },
    tendersOid: {
      type: 'select',
      name: '标段名称',
      childSelect: ['pipelineOid', 'supervisionUnit'],
      childUrl: ['/daq/privilege/getPipelineListByTendersOid.do', '/daq/privilege/getSupervisionUnitByTendersOid.do'],
      isRequired: true,
      isInit:true
    },
    pipelineName: {
      name: '管线名称',
    },
    pipelineOid: {
      type: 'select',
      name: '管线名称',
      childSelect: ['pipeSegmentOrCrossOid'],
      childUrl: ['/daq/privilege/getPipeSegmentOrCrossList.do'],
      isRequired: true,
      isInit:true
    },
    pipeSegmentOrCrossName: {
      name: '线路段/穿跨越',
    },
    pipeSegmentOrCrossOid: {
      type: 'select',
      name: '线路段/穿跨越',
      childSelect: ['medianStakeOid', 'startMedianStakeOid', 'endMedianStakeOid'],
      childUrl: ['/daq/privilege/getMedianStakeList.do', '/daq/privilege/getMedianStakeList.do', '/daq/privilege/getMedianStakeList.do'],
      isRequired: true,
      isInit:true
    },
    constructUnit: {
      type: 'select',
      name: '施工单位',
      isRequired: true,
      disabled:true
    },
    constructUnitName: {
      type: 'input',
      name: '施工单位',
      rules: [{
        min: '0',
        max: 36,
        message: "不能超过36个字"
      }]
    },
    supervisionUnit: {
      type: 'select',
      name: '监理单位',
      isRequired: true
    },
    supervisionUnitName: {
      type: 'input',
      name: '监理单位',
      isRequired: true,
      rules: [{
        min: '0',
        max: 38,
        message: "不能超过38个字"
      }]
    },
    supervisionEngineer: {
      type: 'input',
      name: '监理工程师',
      rules: [{
        min: '0',
        max: 50,
        message: "不能超过50个字"
      }]
    },
    collectionPerson: {
      type: 'input',
      name: '采集人员',
      rules: [{
        min: '0',
        max: 30,
        message: "不能超过30个字"
      }]
    },
    collectionDate: {
      type: 'date',
      name: '采集日期',
      isRequired: true
    },

    // 1111
    markStakeCode: {
      name: '标志桩编号',
      type: 'input',
      isRequired: true,
      rules: [{
        min: '0',
        max: 36,
        message: "不能超过36个字"
      }]
    }, //: "test-1",

    medianStakeOid: {
      type: 'select',
      name: '桩号',
      isRequired: true
    }, //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
    medianStakeCode: {
      name: '桩号',
      isRequired: true
    }, //: "median_stake_004",

    relativeMileage: {
      name: '相对桩位置(m)',
      type: 'number',
      max: 999999.999,
      precision: 3,
      isRequired: true
    }, //: -0.5,
    pointx: {
      name: 'X坐标',
      type: 'number',
      max: 999.999999999,
      precision: 9,
      nameConfig: {
        appendagesMarkStake: 'X坐标'
      }
    }, //: null,
    pointy: {
      name: 'Y坐标',
      type: 'number',
      max: 999.999999999,
      precision: 9,
      nameConfig: {
        appendagesMarkStake: 'Y坐标'
      }
    }, //: null,
    stakeStructure: {
      type: 'select',
      name: '桩体结构',
      domainName: 'mark_stake_structure_domain'
    }, //: "mark_stake_structure_code_001",
    stakeStructureName: {
      name: '桩体结构',
    }, //: null,
    burialDate: {
      type: 'date',
      name: '埋设日期',
      isRequired: true
    }, //: "2017-08-11",
    stakeFunction: {
      type: 'multiSelect',
      name: '桩功能',
      domainName: 'mark_stake_function_domain',
      isRequired: true
    }, //: "mark_stake_function_code_001",
    stakeFunctionName: {
      name: '桩功能',
      isRequired: true
    }, //: "钢结构",
    burialDepth: {
      name: '埋深(m)',
      type: 'number',
      max: 99999.99,
      precision: 2
    },
    //2222222
    electronicLabelCode: {
      name: '电子标签编号',
      type: 'input',
      isRequired: true,
      rules: [{
        min: '0',
        max: 50,
        message: "不能超过50个字"
      }]
    }, //: "test-1",
    productNum: {
      name: '产品编号',
      type: 'input',
     // isRequired: true,
      rules: [{
        min: '0',
        max: 60,
        message: "不能超过60个字"
      }]
    }, //: "test-1",
    pointz: {
      name: '高程(m)',
      type: 'number',
      max: 9999.99,
      precision: 2,
      nameConfig: {
        appendagesHandHole: '地表高程(m)'
      }
    }, //: null,
    featurePointType: {
      type: 'select',
      name: '特征点类型',
      domainName: 'feature_point_type_domain',
      isRequired: true
    }, //: feature_point_type_code_001,
    featurePointTypeName: {
      name: '特征点类型',
    }, //: 热弯弧顶,

    //3333333
    "handHoleCode": {
      name: '孔编号',
      type: 'input',
      isRequired: true,
      rules: [{
        min: '0',
        max: 50,
        message: "不能超过50个字"
      }]
    }, //: "SK-median_stake_004-2",
    "handHoleName": {
      type: 'select',
      name: '孔名称',
      domainName: 'hand_hole_name_domain',
      isRequired: true
    }, //: "hand_hole_name_code_001",
    "handHoleNameCodeName": {
      name: '孔名称',
    }, //: "接续手孔",
    "handHoleType": {
      name: '孔类型',
      type: 'select',
      domainName: 'hand_hole_type_domain',
     // isRequired: true
    }, //: "hand_hole_type_code_002",
    "handHoleTypeName": {
      name: '孔类型',
    }, //: "分歧手孔",
    "handHoleSpecifications": {
      name: '规格型号',
      type: 'input',
      rules: [{
        min: '0',
        max: 50,
        message: "不能超过50个字"
      }]
    }, //: "12*24",
    "baseInstallSituation": {
      name: '基础制造及安装情况',
      type: 'input',
      rules: [{
        min: '0',
        max: 60,
        message: "不能超过60个字"
      }]
    }, //: "良好",
    "circleInstallSituation": {
      name: '口圈及安装情况',
      type: 'input',
      rules: [{
        min: '0',
        max: 60,
        message: "不能超过60个字"
      }]
    }, //: "良好",
    "materialType": {
      name: '材料类型',
      type: 'input',
      rules: [{
        min: '0',
        max: 40,
        message: "不能超过40个字"
      }]
    }, //: "混凝土",
    "stayLong": {
      name: '光缆盘留长度(m)',
      type: 'number',
      max: 99999.99,
      precision: 2,
    },
    "isElectronicMark": {
      type: 'select',
      name: '是否放置电子标识',
      options: [{
        key: 1,
        value: '是'
      }, {
        key: 0,
        value: '否'
      }],
      formatter: function (a, b, value) {
        if (value == 1) return '是';
        if (value == 0) return '否';
      }
    },
    "acceptanceResults": {
      type: 'select',
      name: '检查验收结果',
      options: [{
        key: 1,
        value: '合格'
      }, {
        key: 0,
        value: '不合格'
      }],
      formatter: function (a, b, value) {
        if (value == 1) return '合格';
        if (value == 0) return '不合格';
      }
    },
    // 4444444444444
    "obstacleCode": {
      name: '障碍物编号',
      type: 'input',
      isRequired: true,
      rules: [{
        min: '0',
        max: 45,
        message: "不能超过45个字"
      }]
    }, //: "ss031",
    "obstacleName": {
      name: '障碍物名称',
      type: 'input',
      isRequired: true,
      rules: [{
        min: '0',
        max: 45,
        message: "不能超过45个字"
      }]
    }, //: "test",
    "obstacleType": {
      type: 'select',
      name: '障碍物类型',
      domainName: 'obstacle_type_domain',
      //isRequired: true
    }, //: "obstacle_type_code_001",
    "obstacleTypeName": {
      name: '障碍物类型',
    }, //: "输灰管道",
    "subordinateUnit": {
      name: '所属单位',
      type: 'input',
       rules: [{
        min: '0',
        max:50,
        message: "不能超过50个字"
      }]
    }, //: "zz",
    "address": {
      name: '地址',
      type: 'input',
      rules: [{
        min: '0',
        max: 75,
        message: "不能超过75个字"
      }]
    }, //: null,
    "contacts": {
      name: '联系人',
      type: 'input',
      rules: [{
        min: '0',
        max: 20,
        message: "不能超过20个字"
      }]
    }, //: null,
    "telephone": {
      name: '电话',
      type: 'input',
      rules: [{
        min: '0',
        max: 15,
        message: "不能超过15个字"
      }]
    }, //: null,
    "minDistance": {
      name: '最小间距(m)',
      type: 'number',
      max: 9999.999,
      precision: 3,
    }, //: 1,
    "isThroughPipeline": {
      type: 'select',
      name: '是否与管道交叉',
      options: [{
        key: 1,
        value: '是'
      }, {
        key: 0,
        value: '否'
      }],
      formatter: function (a, b, value) {
        if (value == 1) return '是';
        if (value == 0) return '否';
      }
    }, //: 0,
    //555555555555
    "hydraulicProtectionCode": {
      name: '水工保护编号',
      type: 'input',
      isRequired: true,
      rules: [{
        min: '0',
        max: 45,
        message: "不能超过45个字"
      }]
    }, //: "ss",
    "hydraulicProtectionType": {
      type: 'select',
      name: '水工保护类型',
      domainName: 'hydraulic_protection_type_domain',
     // isRequired: true
    },
    "hydraulicProtectionTypeName": {
      name: '水工保护类型',
    }, //: null,
    "hydraulicProtectionName": {
      name: '水工保护名称',
      type: 'input',
      isRequired: true,
      rules: [{
        min: '0',
        max: 45,
        message: "不能超过45个字"
      }]
    }, //: "test",

    "startPointx": {
      name: '起点X坐标',
      type: 'number',
      max: 999.999999999,
      precision: 9,
    }, //: 115.005,
    "startPointy": {
      name: '起点Y坐标',
      type: 'number',
      max: 999.999999999,
      precision: 9,
    }, //: 38.153,
    "endPointx": {
      name: '终点X坐标',
      type: 'number',
      max: 999.999999999,
      precision: 9,
    }, //: 115.005,
    "endPointy": {
      name: '终点Y坐标',
      type: 'number',
      max: 999.999999999,
      precision: 9,
    }, //: 38.152,
    "structureSize": {
      name: '结构尺寸',
      type: 'input',
      rules: [{
        min: '0',
        max: 45,
        message: "不能超过45个字"
      }]
    }, //: "12.15",
    "engineerQuatity": {
      name: '工程量(m³)',
      type: 'number',
      max: 99999.999,
      precision: 3,
    }, //: 12.15,
    "hydraulicProtectionMaterial": {
      type: 'select',
      name: '水工保护材料',
      domainName: 'hydraulic_protection_material_domain',
    }, //: "hydraulic_protection_material_code_001",
    "hydraulicProtectionMaterialName": {
      name: '水工保护材料',
    }, //: null,
    "acceptDate": {
      type: 'date',
      name: '检查验收日期'
    }, //: "2017-08-12",
    "inspectionFindings": {
      type: 'select',
      name: '检查结论',
      options: [{
        key: 1,
        value: '合格'
      }, {
        key: 0,
        value: '不合格'
      }],
      formatter: function (a, b, value) {
        if (value == 1) return '合格';
        if (value == 0) return '不合格';
      }
    }, //: 0,
    "workUnitOid": {
      type: 'select',
      name: '施工机组',
      requestParams: {
        types: 'work_unit_type_code_014,work_unit_type_code_015'
      },
      isRequired: true
    }, //: "4a62dc52-5663-499a-9868-50b86a994c97",
    "workUnitName": {
      name: '施工机组',
      isRequired: true
    }, //: "机组A",

    // 666666

    "startMedianStakeOid": {
      type: 'select',
      name: '起始桩号',
      isRequired: true
    }, //: "1a9a3bc9-e499-4fe9-a4d8-a801090eec4c",
    "startMedianStakeCode": {
      name: '起始桩号',
    }, //: "median_stake_001",
    "startRelativeMileage": {
      name: '相对起始桩位置(m)',
      type: 'number',
      max: 999999.999,
      precision: 3,
      isRequired: true
    }, //: 1,
    "endMedianStakeOid": {
      type: 'select',
      name: '结束桩号',
      isRequired: true
    }, //: "5b295ca5-fcf1-42c7-bc87-0be4fd98c65c",
    "endMedianStakeCode": {
      name: '结束桩号',
    }, //: "median_stake_001",
    "endRelativeMileage": {
      name: '相对结束桩位置(m)',
      type: 'number',
      max: 999999.999,
      precision: 3,
      isRequired: true
    }, //: 1, //: -1,

    "casingPipeType": {
      type: 'select',
      name: '套管类型',
      domainName: 'casing_pipe_type_domain',
    }, //: "casing_pipe_type_code_001",
    "casingPipeTypeName": {
      name: '套管类型',
    }, //: "钢套管",
    "casingPipeLength": {
      name: '套管长度(m)',
      type: 'number',
      max: 9999999.99,
      precision: 2,
    }, //: 12.15,
    "casingPipeSpecifications": {
      name: '套管规格',
      type: 'input',
      rules:[{
        min:"0",
        max:40,
        message:"不能超过40个字"
      }]
    }, //: "11",
    "constructDate": {
      type: 'date',
      name: '施工日期',
      isRequired: true
    }, //: "2017-08-12",



    approveStatus: {
      type: 'input',
      name: '审核状态',
      formatter: function (a, b, value, c) {
        if (value == -1) return '驳回';
        if (value == 0) return '未上报';
        if (value == 1) return '待审核';
        if (value == 2) return '通过';
        return value;
      }
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
    '/jasmvvm/pages/row-appendages/appendages-template/appendages-template.html?privilegeCode=P-daq-hq-001007001&templateCode=appendagesMarkStake&isApprove=1className=cn.jasgroup.jasframework.acquisitiondata.material.appendages.markstake.dao.entity.DaqAppendagesMarkStake&classNameQuery=cn.jasgroup.jasframework.acquisitiondata.material.appendages.markstake.query.DaqAppendagesMarkStakeQuery',
    '/jasmvvm/pages/row-appendages/appendages-template/appendages-template.html?privilegeCode=P-daq-hq-001007002&templateCode=appendagesElectronicLabel&isApprove=1className=cn.jasgroup.jasframework.acquisitiondata.material.appendages.electroniclabel.dao.entity.DaqAppendagesElectronicLabel&classNameQuery=cn.jasgroup.jasframework.acquisitiondata.material.appendages.electroniclabel.query.DaqAppendagesElectronicLabelQuery',
    '/jasmvvm/pages/row-appendages/appendages-template/appendages-template.html?privilegeCode=P-daq-hq-001007003&templateCode=appendagesHandHole&isApprove=1className=cn.jasgroup.jasframework.acquisitiondata.material.appendages.handhole.dao.entity.DaqAppendagesHandHole&classNameQuery=cn.jasgroup.jasframework.acquisitiondata.material.appendages.handhole.query.DaqAppendagesHandHoleQuery',
    '/jasmvvm/pages/row-appendages/appendages-template/appendages-template.html?privilegeCode=P-daq-hq-001007004&templateCode=appendagesObstacle&isApprove=1className=cn.jasgroup.jasframework.acquisitiondata.material.appendages.obstacle.dao.entity.DaqAppendagesObstacle&classNameQuery=cn.jasgroup.jasframework.acquisitiondata.material.appendages.obstacle.query.DaqAppendagesObstacleQuery',
    '/jasmvvm/pages/row-appendages/appendages-template/appendages-template.html?privilegeCode=P-daq-hq-001007005&templateCode=appendagesHydraulicProtection&isApprove=1className=cn.jasgroup.jasframework.acquisitiondata.material.appendages.hydraulicprotection.dao.entity.DaqAppendagesHydraulicProtection&classNameQuery=cn.jasgroup.jasframework.acquisitiondata.material.appendages.hydraulicprotection.query.DaqAppendagesHydraulicProtectionQuery',
    '/jasmvvm/pages/row-appendages/appendages-template/appendages-template.html?privilegeCode=P-daq-hq-001007006&templateCode=appendagesCasingPipe&isApprove=1className=cn.jasgroup.jasframework.acquisitiondata.material.appendages.casingpipe.dao.entity.DaqAppendagesCasingPipe&classNameQuery=cn.jasgroup.jasframework.acquisitiondata.material.appendages.casingpipe.query.DaqAppendagesCasingPipeQuery',
  ]
};