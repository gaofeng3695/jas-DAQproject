// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.18/esri/copyright.txt for details.
//>>built
define("esri/dijit/metadata/nls/i18nArcGIS",{root:{documentTypes:{arcgis:{caption:"ArcGIS Metadata",editorCaption:"Metadata",description:""}},emptyOption:"Empty",conditionals:{ISO19139A1_ROW4:"If Metadata Hierarchy Level is Dataset, a Geographic Bounding Box or Geographic Description is required.",ISO19139A1_ROW6:"A Dataset Identifier or Dataset Name is required.",ISO19139A1_ROW7:"If Other Restrictions is chosen, Other Constraints is required.",ISO19139A1_ROW9:"If Scope is not Dataset or Series a Level Description is required.",
ISO19139A1_ROW10_11_12:"If Scope is Dataset or Series; one of Statement, Process Step, or Data Source is required.",ISO19139A1_ROW15:"If Check Point Availability is selected, Check Point Description is required.",ISO19139A1_ROW18:"If Distribution is documented a Format or Distributor/Format is required.",INSPIRE_AccessLimitation:" At least one legal access constraint code or security classification code is required. (INSPIRE)",INSPIRE_UseLimitation:" At least one use limitation is required. (INSPIRE)",
INSPIRE_ConformanceResult:"A Domain Consistency report requires a Conformance Result. (INSPIRE)",INSPIRE_DomainConsistency:"A Domain Consistency report is required. (INSPIRE)",INSPIRE_LineageStatement:"If Scope is Dataset or Series, a Lineage Statement is required. (INSPIRE).",FGDC_DescIfTemporal:"A Description is required for a Temporal Extent. (FGDC)",FGDC_Keywords:"A Topic, Tag or Theme Keyword is required. (FGDC)",FGDC_Reports:"Completeness Omission and Conceptual Consistency reports are required. (FGDC)",
FGDC_Temporal:"At least one Temporal Extent is required. (FGDC)",NAP_Contact:"An Address/Delivery Point, Phone/Voice number or Online Resource/URL is required. (NAP)",GEN_BoundingBox:"At least one Geographic Bounding Box is required.",GEN_ReportResult:"Either a Conformance or Quantitative result is required.",minLessThanMax:"The Minumum Value must be less than the Maximum Value."},hints:{integerGreaterThanZero:"(enter an integer \x3e 0)",integerGreaterThanOne:"(enter an integer \x3e 1)",integer0To100:"(enter an integer 0..100)",
maxScale:"(enter an integer \x3e 0, e.g. 50000)",minScale:"(enter an integer \x3e 0, e.g. 150000000)",number0To100:"(enter an number 0..100)",number0To360:"(enter an number 0..360)",number90To90:"(enter an number -90..90)",listOfDoubles:"(enter a list of numbers, use a space to separate)"},htmlEditor:{button:"Edit..."},sections:{overview:"Overview",esri:"Esri",resource:"Resource",reference:"Reference",content:"Content",distribution:"Distribution",quality:"Quality",eainfo:"Fields",representation:"Representation",
metadata:"Metadata"},metadataStyle:{caption:"ArcGIS Metadata Style",changeButton:"Change...",dialogTitle:"Choose a Metadata Style",updating:"Updating document...","Item Description":"Item Description","FGDC CSDGM Metadata":"FGDC CSDGM Metadata","ISO 19139 Metadata Implementation Specification":"ISO 19139 Metadata Implementation Specification","ISO 19139 Metadata Implementation Specification GML3.2":"ISO 19139 Metadata Implementation Specification GML3.2","INSPIRE Metadata Directive":"INSPIRE Metadata Directive",
"North American Profile of ISO19115 2003":"North American Profile of ISO19115 2003"},aggrInfo:{caption:"Aggregate Information",datasetHint:"A Dataset Identifier or Dataset Name is required.",aggrDSIdent:"Dataset Identifier",aggrDSName:"Dataset Name"},appSchInfo:{caption:"Application Schema",asName:"Schema Name",asSchLang:"Schema Language",asCstLang:"Constraint Language",asAscii:"ASCII",asGraFile:"Graphics File",asGraFile_src:"Graphics File Source",asSwDevFile:"Software Development File",asSwDevFile_src:"Software Development File Source",
asSwDevFiFt:"Software Development File Format"},citation:{caption:"Citation",section:{titlesAndDates:"Titles \x26 Dates",links:"URLs",identifiers:"Identifiers",presentation:"Form",other:"Other",edition:"Edition",series:"Series"},conditionalDate:{caption:"Citation Date",msg:"One of Creation Date, Publication Date or Revision Date is required.",msg_nap:"A citation date is required."},resTitle:"Title",resAltTitle:"Alternate Title",collTitle:"Collective Title",date:{createDate:"Creation Date",pubDate:"Publication Date",
reviseDate:"Revision Date",notavailDate:"Not Available Date",inforceDate:"In Force Date",adoptDate:"Adopted Date",deprecDate:"Deprecated Date",supersDate:"Superseded Date"},isbn:"ISBN",issn:"ISSN",citId:{caption:"Identifier",identCode:"Code",identAuth:"Authority Citation"},resEd:"Edition",resEdDate:"Edition Date",datasetSeries:{seriesName:"Name",issId:"Issue",artPage:"Page"},otherCitDet:"Other Details",contactCaption:"Citation Contact"},cntAddress:{caption:"Address",delPoint:"Delivery Point",city:"City",
adminArea:"Administrative Area",postCode:"Postal Code",country:"Country",eMailAdd:"Email",addressType:{caption:"Address Type",postal:"Postal",physical:"Physical",both:"Both"}},cntOnlineRes:{caption:"Online Resource",linkage:"URL",protocol:"Protocol",appProfile:"Application Profile",orName:"Name",orDesc:"Description"},cntPhone:{caption:"Phone",voiceNum:"Voice",faxNum:"Fax",tddtty:"TDD/TTY?"},codeRef:{caption:"Identifier",identCode:"Code",idCodeSpace:"Code Space",idVersion:"Version",identAuth:"Authority Citation"},
constraints:{caption:"Constraints",useLimit:"Use Limitation",general:{caption:"General"},legal:{caption:"Legal",accessConsts:"Access Constraints",useConsts:"Use Constraints",othConsts:"Other Constraints"},security:{caption:"Security",classSys:"Classification System",userNote:"User Note",handDesc:"Handling Description"}},contInfo:{caption:"Content Information",section:{CovDesc:"Coverage Description",ImgDesc:"Image Description",FetCatDesc:"Feature Catalogue"},attDesc:"Attribute Description",covDim:{caption:"Range or Band",
seqID:"Sequence Identifier",seqIDType:"Sequence Identifier Type",dimDescrp:"Descriptor"},RangeDim:{caption:"Range Dimension"},Band:{caption:"Band",minVal:"Minimum Value",maxVal:"Maximum Value",valUnit:"Value Units",pkResp:"Peak Response",bitsPerVal:"Bits per Value",toneGrad:"Tone Gradation",sclFac:"Scale Factor",offset:"Offset"},CovDesc:{caption:"Coverage Description",section:{description:"Description",rangesAndBands:"Ranges and Bands"}},ImgDesc:{caption:"Image Description",section:{description:"Description",
rangesAndBands:"Ranges and Bands"},illElevAng:"Illumination Elevation Angle",illAziAng:"Illumination Azimuth Angle",cloudCovPer:"Cloud Cover Percentage",cmpGenQuan:"Compression Quality",trianInd:"Triangulation Indicator?",radCalDatAv:"Radiometric Calibration Data Availability?",camCalInAv:"Camera Calibration Information Availability?",filmDistInAv:"Film Distoration Information Availability?",lensDistInAv:"Lens Distoration Information Availability?",imagQuCode:"Quality Code",prcTypCde:"Processing Level Code"},
FetCatDesc:{caption:"Feature Catalogue",section:{description:"Description",featureTypes:"Feature Types",citation:"Citation"},compCode:"Complies With ISO 19110?",incWithDS:"Included With Dataset?",catCitation:"Feature Catalogue Citation",catFetTyps:{caption:"Feature Type",genericName:"Name",codeSpace:"Code Space"}}},contact:{caption:"Contact",section:{name:"Contact Name",info:"Contact Information",hoursAndInstructions:"Hours \x26 Instructions"},conditionalName:{caption:"Contact Name",msg:"One of Individual Name, Organization Name or Position Name is required.",
msg_fgdc:"One of Individual Name or Organization Name is required."},rpIndName:"Individual Name",rpOrgName:"Organization Name",rpPosName:"Position Name",rpCntInfo:"Contact Information",cntHours:"Hours of Service",cntInstr:"Contact Instructions"},distInfo:{caption:"Distribution Information",section:{format:"Format",distributor:"Distributor",transfer:"Transfer Options"},distFormat:{caption:"Distribution Format",formatName:"Format Name",formatVer:"Format Version",formatAmdNum:"Amendment Number",formatSpec:"Specification",
fileDecmTech:"Decompression Technique",formatInfo:"Information Content"},distributor:{caption:"Distributor"},distTranOps:{caption:"Digital Transfer Options",section:{unitsAndSize:"Units"},unitsODist:"Units of Distribution",transSize:"Transfer Size",offLineMed:{caption:"Offline Medium",medDensity:"Density",medDenUnits:"Density Units",medVol:"Volumes",medNote:"Medium Note"}},distorOrdPrc:{caption:"Ordering Process",resFees:"Fees",planAvDtTm:"Available Date",planAvTmPd:{caption:"Available Date Period",
tmBegin:"Begin Date/Time",tmEnd:"End Date/Time"},ordInstr:"Ordering Instructions",ordTurn:"Turnaround"}},dqInfo:{caption:"Data Quality",section:{scope:"Scope",report:"Report",lineage:"Lineage"},dqScope:{section:{level:"Level",extent:"Extent"},scpLvl:"Scope Level",scpLvlDesc:"Level Description",scpExt:"Scope Extent"},report:{section:{measure:"Measure",evaluation:"Evaluation",result:"Result",conformance:"Conformance"},measDesc:"Measure Description",measName:"Measure Name",measDateTm:"Measure Date",
measId:"Measure Identifier",evalMethDesc:"Evaluation Method",evalProc:"Procedure Citation",ConResult:{caption:"Conformance Result",conExpl:"Explanation",conSpec:"Specification",conPass:{caption:"Degree",_1:"Conformant",_0:"Non Conformant"}},QuanResult:{caption:"Quantitative Result",quanVal:"Value",quanValType:"Value Type",quanValUnit:"Value Units",errStat:"Error Statistic"}},dataLineage:{section:{statement:"Statement",dataSource:"Data Source",prcStep:"Process Step"},statement:"Lineage Statement",
dataSource:{caption:"Data Source",section:{description:"Description",srcRefSys:"Reference System",srcExt:"Extent",srcCitatn:"Citation"},srcDesc:"Source Description",srcScale:{rfDenom:"Scale Denominator"},srcRefSys:"Source Reference System",srcExt:"Source Extent",srcCitatn:"Source Citation"},prcStep:{caption:"Process Step",section:{description:"Description",stepProc:"Processor",stepSrc:"Data Source"},stepDesc:"Process Description",stepRat:"Rationale",stepDateTm:"Process Step Date",stepProc:"Processor",
stepSrc:"Data Source"}}},eainfo:{caption:"Entity and Attribute Information",section:{detailed:"Details",overview:"Overview"},detailed:{caption:"Entity and Attribute Details",section:{enttyp:"Entity",attr:"Attributes"},enttyp:{caption:"Entity Type",enttypl:"Label",enttypt:"Object",enttypc:"Count",enttypd:"Definition",enttypds:"Definition Source"},attr:{caption:"Attribute",section:{description:"Description",value:"Value",domain:"Domain"},attrlabl:"Label",attalias:"Alias",attrdef:"Definition",attrdefs:"Definition Source",
attrtype:"Type",attwidth:"Width",atprecis:"Precision",attscale:"Scale",atindex:"Indexed",attrvai:{attrva:"Value Explanation",attrvae:"Value Accuracy"},attrmfrq:"Value Measurement Frequency",begdatea:"Beginning Date of Values",enddatea:"Ending Date of Values",attrdomv:{caption:"Domain",edom:{caption:"Enumerated",edomv:"Value",edomvd:"Definition",edomvds:"Definition Source"},rdom:{caption:"Range",rdommin:"Minimum Value",rdommax:"Maximum Value",rdommean:"Mean",rdomstdv:"Standard Deviation",attrunit:"Units",
attrmres:"Measurement Resolution"},codesetd:{caption:"Codeset",codesetn:"Name",codesets:"Source"},udom:{caption:"Unrepresentable"}}}},overview:{caption:"Overview",eaover:"Summary",eadetcit:"Citation"}},extent:{caption:"Extent",section:{description:"Description",geographic:"Geographic",temporal:"Temporal",vertical:"Vertical"},exDesc:"Extent Description",geoEle:{caption:"Geographic Extent",GeoBndBox:{caption:"Bounding Box",esriExtentType:"Extent is for search?",exTypeCode:"Extent contains the resource?",
westBL:"West Bounding Longitude",eastBL:"East Bounding Longitude",southBL:"South Bounding Latitude",northBL:"North Bounding Latitude"},GeoDesc:{caption:"Geographic Description",exTypeCode:"Description contains the resource?",identCode:"Code"}},tempEle:{caption:"Temporal Extent",TM_Period:"Time Period",TM_Instant:"Time Instant",tmPosition:"Date",tmBegin:"Begin Date",tmEnd:"End Date"},vertEle:{caption:"Vertical Extent",vertMinVal:"Minimum Value",vertMaxVal:"Maximum Value"}},graphOver:{caption:"Browse Graphic",
bgFileName:"Browse Graphic URL",bgFileDesc:"Browse Graphic Description",bgFileType:"Browse Graphic File Type"},keywords:{caption:"Keywords",section:{topicCategory:"Topic",searchKeys:"Tags",themeKeys:"Theme",placeKeys:"Place",tempKeys:"Temporal",discKeys:"Discipline",stratKeys:"Stratum",productKeys:"Product",subTopicCatKeys:"Subtopic",otherKeys:"Other"},delimited:"Keywords",searchKeys:"Tags",themeKeys:"Theme Keywords",placeKeys:"Place Keywords",tempKeys:"Temporal Keywords",discKeys:"Discipline Keywords",
stratKeys:"Stratum Keywords",productKeys:"Product Keywords",subTopicCatKeys:"Subtopic Keywords",otherKeys:"Other Keywords",thesaName:"Thesaurus Citation",thesaLang:"Thesaurus Language"},locales:{caption:"Locales",locale:"Locale",resTitle:"Title",idAbs:"Summary"},maintenance:{caption:"Maintenance",section:{frequency:"Frequency",scope:"Scope",note:"Note"},usrDefFreq:"Custom Frequency",dateNext:"Next Update",maintScp:"Update Scope",upScpDesc:{caption:"Scope Description",attribSet:"Attributes",attribIntSet:"Attribute Instances",
featSet:"Features",featIntSet:"Feature Instances",datasetSet:"Dataset",other:"Other Instances"},maintNote:"Maintenance Note",maintCont:"Maintenance Contact"},metadata:{section:{profile:"Profile",details:"Scope"},mdFileID:"File Identifier",mdParentID:"Parent Identifier",datasetURI:"Dataset URI",dataSetFn:"Dataset Function",mdDateSt:"Metadata Date",mdLang:"Metadata Language",mdChar:"Character Set",mdHrLv:"Hierarchy Level",mdHrLvName:"Hierarchy Level Name",mdContact:"Metadata Contact",mdMaint:"Metadata Maintenance",
mdConst:"Metadata Constraints"},porCatInfo:{caption:"Portrayal Citation"},refSysInfo:{caption:"Spatial Reference"},resource:{section:{citation:"Citation",details:"Details",description:"Description",keywords:"Keywords",status:"Status",resolution:"Resolution",representation:"Representation",browse:"Browse Graphic",format:"Format",usage:"Usage",aggregateInfo:"Aggregation",additional:"Additional"},idAbs:"Description (Abstract)",idPurp:"Summary (Purpose)",suppInfo:"Supplemental Information",idCredit:"Credits",
envirDesc:"Processing Environment",dataLang:"Resource Language",dataExt:"Resource Extent",idPoC:"Point of Contact",resMaint:"Resource Maintenance",resConst:"Resource Constraints",dsFormat:"Resource Format",dataScale:{caption:"Data Scale",equScale:"Scale Resolution",scaleDist:"Distance Resolution",scaleDist_value:"Distance"},idSpecUse:{caption:"Resource Usage",specUsage:"Specific Usage",usageDate:"Usage Date",usrDetLim:"Limitations",usrCntInfo:"Usage Contact"}},service:{caption:"Service",svType:"Service Type",
svType_Name:"Name",svAccProps:"Access Properties",svCouplRes:{caption:"Coupled Resource",svOpName:"Operation Name",svResCitId:"Resource Identifier"},svCoupleType:"Coupling Type"},scaleRange:{caption:"Scale Range",maxScale:"Maximum Scale",minScale:"Minimum Scale"},spatRepInfo:{caption:"Spatial Representation",section:{dimension:"Dimension",parameters:"Parameters"},numDims:"Number of Dimensions",tranParaAv:"Transformation Parameter Availability?",axisDimension:{caption:"Dimension",dimSize:"Size",dimResol:{caption:"Resolution",
_value:"Resolution Value",uom:"Resolution Units"}},VectSpatRep:{caption:"Vector",geometObjs:"Geometric Objects",geoObjCnt:"Object Count"},GridSpatRep:{caption:"Grid"},Georect:{caption:"Georectified",section:{centerPoint:"Center Point",cornerPts:"Corner Points"},chkPtAv:"Check Point Availability?",chkPtDesc:"Check Point Description",ptInPixel:"Point In Pixel",transDimDesc:"Transformation Dimension Description",transDimMap:"Transformation Dimension Mapping",cornerPts:{caption:"Corner Point",pos:"Position",
gmlDesc:"Description",gmlDescRef:"Reference",gmlIdent:"Identifier",codeSpace:"Identifier Codespace"}},Georef:{caption:"Georeferenceable",ctrlPtAv:"Control Point Availability?",orieParaAv:"Orientation Parameter Availability?",orieParaDs:"Orientation Parameter Description",georefPars:"Georeferenced Parameters",paraCit:"Parameter Citation"},Indref:{caption:"Indirect"}},booleanOptions:{_false:"No",_true:"Yes"},codelist:{CountryCode:"Country",LanguageCode:"Language",MonetaryUnits:"Monetary Units",MonetaryUnits_empty:"No universal currency",
PresentationForm:"FGDC Geospatial Data Presentation Form",CI_PresentationFormCode:"Presentation Form",CI_RoleCode:"Role",CI_OnLineFunctionCode:"Function",IMS_ContentType:"Content Type",DQ_ElementDimension:"Dimension",DQ_ElementType:"Report Type",DQ_EvaluationMethodTypeCode:"Evaluation Type",DS_AssociationTypeCode:"Association Type",DS_InitiativeTypeCode:"Initiative Type",LI_SourceType:"Source Type",MD_CellGeometryCode:"Cell Geometry",MD_CharacterSetCode:"Character Set",MD_ClassificationCode:"Classification",
MD_CoverageContentTypeCode:"Content Type",MD_DimensionNameTypeCode:"Dimension Type",MD_GeometricObjectTypeCode:"Geometric Object Type",MD_ImagingConditionCode:"Imaging Condition",MD_MaintenanceFrequenceCode:"Update Frequency",MD_MediumFormatCode:"Format Code",MD_MediumNameCode:"Medium Name",MD_PixelOrientationCode:"Pixel Orientation",MD_ProgressCode:"Status",MD_RestrictionCode:"Restriction Code",MD_ScopeCode:"Scope",MD_SpatialRepresentationTypeCode:"Spatial Representation Type",MD_TopicCategoryCode:"Topic Category",
MD_TopologyLevelCode:"Topology Level",RS_Dimension:"Dimension",SV_CouplingType:"Coupling Type",UCUM:"Units",UCUM_Length:"Distance Units"}},ar:1,cs:1,da:1,de:1,el:1,es:1,et:1,fi:1,fr:1,he:1,it:1,ja:1,ko:1,lv:1,lt:1,nl:1,nb:1,pl:1,"pt-br":1,"pt-pt":1,ro:1,ru:1,sv:1,th:1,tr:1,vi:1,"zh-cn":1,"zh-hk":1,"zh-tw":1});