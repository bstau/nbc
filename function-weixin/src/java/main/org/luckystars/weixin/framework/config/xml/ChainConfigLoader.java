package org.luckystars.weixin.framework.config.xml;

import static org.luckystars.weixin.framework.config.xml.XmlConfigUtils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.AppContext; 
import org.luckystars.weixin.framework.config.ChainMapping;
import org.luckystars.weixin.framework.config.HandlerChainConfig;
import org.luckystars.weixin.framework.config.HandlerMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 读取handler chain内容
 * @author xuechong
 */
class ChainConfigLoader extends AbstractXmlContextLoader{
	
	private static final Logger logger = Logger.getLogger(ChainConfigLoader.class);

	private final String XML_TAG = "handlerChain";
	
	public ChainConfigLoader(){}
	
	ChainConfigLoader (Document document){
		this.doc = document;
	}
	
	@Override
	public void doLoadIntoContext(AppContext ctx) {
		
		NodeList handlerChains = 
			doc.getDocumentElement().getElementsByTagName(XML_TAG);
		
		valiExists(handlerChains);
		
		for (int i = 0;i<handlerChains.getLength();i++) {
			HandlerChainConfig config = buildConfig(handlerChains.item(i));
			ctx.put(config.getName(), config);
		}
		
	}
	
	
	private HandlerChainConfig buildConfig(Node handlerChain) {
		HandlerChainConfig config = new HandlerChainConfig();
		
		String chainName = 
			handlerChain.getAttributes().getNamedItem("id").getNodeValue();
		config.setName(chainName);
		
		Map<String, ChainMapping> chains = buildChainMappings(handlerChain);
		config.setAllChains(chains);
		
		return config;
	}
	

	private Map<String, ChainMapping> buildChainMappings(Node handlerChain) {
		Map<String, ChainMapping> result = new HashMap<String, ChainMapping>();
		NodeList chainsList = handlerChain.getChildNodes();
		
		for (int i =0,end = chainsList.getLength();i<end;i++) {
			Node chainNode = chainsList.item(i);
			if(isChainNode(chainNode)){
				List<HandlerMapping> handlers = buildHandlers(chainNode);
				
				String chainId = 
					chainNode.getAttributes().getNamedItem("id").getNodeValue();
				Node facNode = 
					chainNode.getAttributes().getNamedItem("handlerFactoryClass");
				
				ChainMapping chain = null;
				if(facNode==null){
					chain = new ChainMapping(chainId,handlers);
				}else{
					chain = new ChainMapping(chainId,facNode.getNodeValue(),handlers);
				}
				
				result.put(chainId, chain);
				if(logger.isInfoEnabled()){
					logger.info("handlerChain:" + chainId + " is loaded \n");
				}
			}
		}
		return result;
	}

	private List<HandlerMapping> buildHandlers(Node chainNode) {
		List<HandlerMapping> handlerList = 
			new ArrayList<HandlerMapping>(chainNode.getChildNodes().getLength());
		NodeList handlerNodeList = chainNode.getChildNodes();
		
		for (int i=0,end=handlerNodeList.getLength();i<end;i++) {
			Node handlerNode = handlerNodeList.item(i);
			if(isHandlerNode(handlerNode)){
				String scope = trimNodeAttr("scope",handlerNode);
				String handlerClass = handlerNode.getTextContent();
				if(handlerClass==null||handlerClass.isEmpty()){
					throw new NullPointerException("没有配置handler class");
				}
				HandlerMapping handler = new HandlerMapping(handlerClass,scope);
				handlerList.add(handler);
			}
		}
		
		return handlerList;
	}

	private boolean isHandlerNode(Node handlerNode) {
		return handlerNode.getNodeType()==Node.ELEMENT_NODE
			&& handlerNode.getNodeName().equals("handler");
	}

	private boolean isChainNode(Node chainNode) {
		return chainNode.getNodeType()==Node.ELEMENT_NODE
			&& chainNode.getNodeName().equals("chain");
	}

	private void valiExists(NodeList nodes) {
		if(nodes==null||nodes.getLength()<=0){
			throw new NullPointerException("没有配置handlerChain");
		}
	}
	
	
}
