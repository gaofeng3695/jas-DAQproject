package cn.jasgroup.jasdoc.essearch.config;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.jasgroup.framework.data.exception.BusinessException;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * description: Elasticsearch Config
 *
 * @author xiefayang
 * 2018/12/19 18:57
 */
@Configuration
public class ElasticsearchConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(ElasticsearchConfig.class);

    /** 集群host */
    @Value("${jasdoc.elasticsearch.host}")
    private String clusterNodes;
    
    /** 集群transport */
    @Value("${jasdoc.elasticsearch.port}")
    private int clusterPort;

    /** 集群名称 */
    @Value("${jasdoc.elasticsearch.cluster-name}")
    private String clusterName;
    

    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        TransportAddress node = new TransportAddress(InetAddress.getByName(clusterNodes), clusterPort);

        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
//                .put("client.transport.sniff", true)
                .build();

        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        setJasDocMapping(client);
        return client;
    }
    
    private void setJasDocMapping(TransportClient client){
		String index = "jas-doc";
		String type="doc";
        if(isExists(client,index)){
            logger.info(index+"索引已经存在！");
            return;
        }
        try {
			CreateIndexRequestBuilder cib=client.admin()  
	                .indices().prepareCreate(index);
			XContentBuilder mapping = XContentFactory.jsonBuilder()
					.startObject()
					.field("dynamic",false)
					.startObject("properties")
					
					.startObject("eventid")
					.field("type","keyword")
					.endObject()
					
					.startObject("folderid")
					.field("type","keyword")
					.endObject()
					
					.startObject("fileno")
					.field("type","keyword")
					.endObject()
					
					.startObject("filename")
					.field("type","text")
					.field("analyzer","ik_max_word")
					.field("search_analyzer","ik_max_word")
					.startObject("fields").startObject("keyword").field("type","keyword").field("ignore_above",256).endObject().endObject()
					.endObject()

					.startObject("filetype")
					.field("type","keyword")
					.field("index",false)
					.endObject()
					
					.startObject("filelocation")
					.field("type","keyword")
					.endObject()
					
					.startObject("keyword")
					.field("type","text")
					.field("analyzer","ik_max_word")
					.field("search_analyzer","ik_max_word")
//					.field("fields", fieldsMap)
					.startObject("fields").startObject("keyword").field("type","keyword").field("ignore_above",256).endObject().endObject()
					.endObject()
					
					.startObject("summary")
					.field("type","text")
					.field("analyzer","ik_max_word")
					.field("search_analyzer","ik_max_word")
//					.field("fields", fieldsMap)
					.startObject("fields").startObject("keyword").field("type","keyword").field("ignore_above",256).endObject().endObject()
					.endObject()
					
					.startObject("filesize")
					.field("type","long")
					.field("index",false)
					.endObject()
					
					.startObject("uploadtime")
					.field("type","date")
					.field("format","yyyy-MM-dd HH:mm:ss||strict_date_optional_time||epoch_millis")
					.endObject()

					.startObject("remark")
					.field("type","text")
					.field("analyzer","ik_max_word")
					.field("search_analyzer","ik_max_word")
//					.field("fields", fieldsMap)
					.startObject("fields").startObject("keyword").field("type","keyword").field("ignore_above",256).endObject().endObject()
					.endObject()
					
					.startObject("contents")
					.field("type","text")
					.field("analyzer","ik_max_word")
					.field("search_analyzer","ik_max_word")
					.endObject()
					
					.startObject("description")
					.field("type","text")
					.field("analyzer","ik_max_word")
					.field("search_analyzer","ik_max_word")
//					.field("fields", fieldsMap)
					.startObject("fields").startObject("keyword").field("type","keyword").field("ignore_above",256).endObject().endObject()
					.endObject()
					
					.startObject("createuser")
					.field("type","keyword")
					.endObject()
					
					.startObject("createusername")
					.field("type","keyword")
					.endObject()
					
					.startObject("createtime")
					.field("type","date")
					.field("format","yyyy-MM-dd HH:mm:ss||strict_date_optional_time||epoch_millis")
					.endObject()
					
					.startObject("updateuser")
					.field("type","keyword")
					.endObject()
					
					.startObject("updateusername")
					.field("type","keyword")
					.endObject()
					
					.startObject("updatetime")
					.field("type","date")
					.field("format","yyyy-MM-dd HH:mm:ss||strict_date_optional_time||epoch_millis")
					.endObject()
					
					.startObject("deleteflag")
					.field("type","keyword")
					.endObject()
					
					.startObject("auditstate")
					.field("type","integer")
					.endObject()
					
					.endObject()
					.endObject();
			cib.addMapping(type, mapping);  
	        cib.execute().actionGet();  
	        logger.info("创建ElasticSearch Mapping完成");
		} catch (Exception e) {
			logger.error("ElasticSearch Mapping失败");
			e.printStackTrace();
		}
    
    }
    
    private boolean isExists(TransportClient client,String index){ 
        IndicesAdminClient adminClient = client.admin().indices();    
        IndicesExistsRequest request = new IndicesExistsRequest(index);  
        IndicesExistsResponse response = adminClient.exists(request).actionGet();  
        if (response.isExists()) {  
          return true;
        }
        return false;
        
    } 
}
