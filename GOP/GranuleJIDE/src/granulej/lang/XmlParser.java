package granulej.lang;

import org.w3c.dom.*;

import java.util.*;
import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import granulej.util.FingerprintUtil;
import granulej.util.xmlUtil;

public class XmlParser {

	private Document doc = null;

	private Document similar_doc = null;

	protected static HashMap<String, ArrayList<String>> c_shadows;

	protected static HashMap<String, ArrayList<String>> m_granules;

	private static HashMap<String, ArrayList<String>> m_shadows;

	private static ArrayList<String> connect_shadows = new ArrayList<String>();

	public static char granuleSeprator = '_';

	private static XmlParser xml_parser = null;

	private static String tarPath;
	
	private static String con_seq="";
	
	private int number;
	
	private String m_map;
	
	
	// 用于收集子树节点的粒
	private static ArrayList<String> sub_tree_items = new ArrayList<String>();

	public static XmlParser getInstance() {
		if (xml_parser == null)
			xml_parser = new XmlParser();
		return xml_parser;
	}
	
	private XmlParser()
	{
	 addChangeListener(TaskExecutor.getInstance());	
	}
	
	public String readXML(String xmlFile) {
		String md5 = null;
		try {
			c_shadows = new HashMap<String, ArrayList<String>>();
			m_shadows = new HashMap<String, ArrayList<String>>();
			doc = xmlUtil.load(xmlFile);
			doc.normalize();
			Element root = doc.getDocumentElement();
			md5 = getMd5Code(root);
			readXmlNode(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5;
	}

	public static String getMd5Code(Node node) {
		String md5 = null;
		String name = node.getNodeName();
		if (name.equals("g-tree")) {
			NamedNodeMap nodeMap = node.getAttributes();
			md5 = nodeMap.getNamedItem("md5").getNodeValue();
		}
		return md5;
	}

	public void readXmlNode(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			String name = node.getNodeName();
			if (name.equals("granule")) {
				NamedNodeMap nodeMap = node.getAttributes();
				String gName = nodeMap.getNamedItem("name").getNodeValue();
				if (!gName.equals("g0")) {
					int num = 0;
					int length = nodeMap.getLength();
					for (int i = 0; i < length; i++) {
						String nodeName = nodeMap.item(i).getNodeName();
						if (nodeName.startsWith("shadow_class"))
							num++;
					}
					for (int j = 0; j < num; j++) {
						String attr = "shadow_class" + j;
						String shadowname = nodeMap.getNamedItem(attr).getNodeValue();
						String className = getClassName(shadowname).trim();
						ArrayList<String> list;
						if (!c_shadows.containsKey(className))
							list = new ArrayList<String>();
						else
							list = (ArrayList<String>) c_shadows.get(className);
						if (!list.contains(shadowname))
							list.add(shadowname);
						c_shadows.put(className, list);
					}
				}
			}
	    //读方法集
			else if (name.equals("method")) {
				NamedNodeMap nodeMap = node.getAttributes();
				String mName = nodeMap.getNamedItem("name").getNodeValue();
				//System.out.println("method signature is :"+mName);
				ArrayList<String> list = new ArrayList<String>();
				int num = 0;
				int length = nodeMap.getLength();
				String nodeName = "";
				for (int i = 0; i < length; i++) {
					nodeName = nodeMap.item(i).getNodeName();
					if (nodeName.startsWith("shadow"))
						num++;
				}
				String attr = "";
				String shadowname = "";
				for (int j = 0; j < num; j++) {
					attr = "shadow" + j;
					shadowname = nodeMap.getNamedItem(attr).getNodeValue();
					if (!list.contains(shadowname))
						list.add(shadowname);
				}
				m_shadows.put(mName, list);
			}
		}
		
		for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
			readXmlNode(child);
		}
	}

	public static String getClassName(String s_name) {
		if (s_name != null && !s_name.equals("")) {
			int index = s_name.lastIndexOf(granuleSeprator);
			s_name = s_name.substring(index > 0 ? index+1 : 0);
			return s_name;
		}
		return null;
	}

	public static ArrayList<String> colInterceptMethods() {
		ArrayList<String> intercept_m = new ArrayList<String>();
		Map map = m_shadows;
		for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
			String mSig = (String) iter.next();
			//System.out.println("method signature is in java :"+mSig);
			if (!intercept_m.contains(mSig))
				intercept_m.add(mSig);
		}
		return intercept_m;
	}

	public static ArrayList<String> interceptMethods() {
		return colInterceptMethods();
	}

	public static ArrayList<String> lookupMethods(String cname, String methodSig) {
		ArrayList<String> list = new ArrayList<String>();
		if (cname != null && methodSig != null) {
			if (cname.contains("_"))
				cname = cname.substring(cname.indexOf("_") + 1);
			String methodIndex = methodSig + "#" + cname;
			if (m_shadows.containsKey(methodIndex))
				list = m_shadows.get(methodIndex);
		}
		return list;
	}

	public static ArrayList<String> getFitnessGranuleSet(String cname, String methodSig) {
		ArrayList<String> new_list = new ArrayList<String>();
		ArrayList<String> temp = lookupMethods(cname, methodSig);
		Iterator iter = temp.iterator();
		while (iter.hasNext()) {
			String sname = (String) iter.next();
			String gname=sname.substring(0,sname.lastIndexOf(granuleSeprator));
			if (!new_list.contains(gname))
				new_list.add(gname);
		}
		return new_list;
	}

	public static ArrayList<String> getCShadowsSet(String cname) {
		ArrayList<String> list = new ArrayList<String>();
		if (c_shadows.containsKey(cname))
			list = c_shadows.get(cname);
		return list;
	}

	public static boolean isConnectPoint(String cname) {
		for (String s : connect_shadows) {
			if (s.endsWith("_" + cname))
				return true;
		}
		return false;
	}

	public static String findConnectPoint(String cname) {
		for (String s : connect_shadows) {
			if (s.endsWith("_" + cname))
				return s;
		}
		return null;
	}

	// 获取各自路径上对应的文件MethodMapShadowClass.xml
	public static String getCorrespondsToOriginalFile(String file) {
		String fname = "";
		if (file != null && !file.equals("")) {
			fname = IndividualInfo.getInstance().getWorkDirectory() + "MethodMapShadowClass"+ XmlParser.getInstance().getNumber() +".xml";
		}
		return fname;
	}
	
	// 粒树演化--粒树替换
	public static void replaceGranuleTree(String gname, String similar_file, String similar_gname) throws Exception {
		try {			
			XmlParser xpar = XmlParser.getInstance();

			String file = IndividualInfo.getInstance().getConfigfile();

			if (xpar.doc == null) {
				xpar.doc = xmlUtil.load(file);
			}
			Node gnode = lookupXmlNode(xpar.doc, gname);

			// 相似文件和相似粒节点		
			xpar.similar_doc = ReceivePacket.getInstance().getSimilarFileDom();
			if (xpar.similar_doc == null) {
				xpar.similar_doc = xmlUtil.load(similar_file);
			}
			tarPath = similar_file;

			Node simi_node = ReceivePacket.getInstance().getSimilarGranuleNode();
			if (simi_node == null) {
				simi_node = lookupXmlNode(xpar.similar_doc, similar_gname);
			}
			
			connect_shadows = new ArrayList<String>();		

			// 创建相似粒节点
			if (gnode != null && simi_node != null) {
				Element el = xpar.doc.createElement("granule");
				if (simi_node.hasAttributes()) {
					NamedNodeMap attrs = simi_node.getAttributes();
					int length = attrs.getLength();
					String attrs_name = ""; 
					String attrs_value = "";
					for (int i = 0; i < length; i++) {
						attrs_name = attrs.item(i).getNodeName();
						attrs_value = attrs.item(i).getNodeValue();
						el.setAttribute(attrs_name, attrs_value);
						if (attrs_name.equals("name")) {
							if (!sub_tree_items.contains(attrs_value))
								sub_tree_items.add(attrs_value);
						}
					}
				}
				
				GranuleTree.getInstance().Iterator();
				//在相似粒节点之前插入一个新的节点
				gnode.getParentNode().insertBefore(el, gnode);
				removeSubTree(gnode);  

				// 添加子树
				updateGranuleSubTree(gname, simi_node);
				//GranuleTree.getInstance().IteratorParents(similar_gname);
				//IteratorHashMap();
				
				addSimilarGranuleToList(simi_node);
//				System.out.println("simi_node: "+simi_node.getNodeName());
				addXmlNode(simi_node, el, simi_node);
				removeFileNamesFromSubIterms();
				xmlUtil.copyByteFiles(getCurrentDirectory(), getTargetGranulePath(similar_file), sub_tree_items);
				
				//IteratorMethodHashMap();				
				//更新方法映射表
			    //updateMethodMapShadowes(file, similar_file);
				//System.out.println("update is starting is :");
  			    updateMethodMapShadowes(file,similar_file);
			    //IteratorMethodHashMap();
			    
			    //设置个体的MD5号
			    IndividualInfo.getInstance().setMd5(getNewIndividualMd5Code(xpar.doc.getDocumentElement()));		    
		
				//演化的程序版本号
			    setEnvolutionVersion();	
			    
			    if(!con_seq.equals("")){
			    //liyu 
			    //与线程阻塞无关
			    	new Thread(new ContextRepUpdate(con_seq)).start();
			/*    	Thread t = new Thread(new ContextRepUpdate(con_seq));
			    	t.start();
			    	t.stop();*/
				
			    }
			    
			    /*
			     * 问题2：注释掉  程序正常结束
			     * 这是重点
			     */
			    	
			    xpar.register(xpar);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 private List<ChangeListener> list = new ArrayList<ChangeListener>();
	 public void addChangeListener(ChangeListener l) {
		    if(l==null)
		    return ;
		    synchronized (l){
		    	list.add(l);
		    }
	 } 
	 
	 public void removeChangeListener(ChangeListener l){
		   if(l==null)
			 return ;
		   synchronized(list){
			      if(list.contains(l)){
					 list.remove(l);
				  }
			   }
	 }
	
	 public void register(XmlParser xpar) {
		/*
		 * 问题3：
		 * ChangeListener实现的接口存在线程池
		 * 需要关闭线程
		 */
		
	     for (ChangeListener l : list) {
	          l.actionChange(new ChangeEvent(xpar.doc,xpar.getNumber()));
	       }
	 }	
	 
	 public void dispose(){
		 synchronized(list){
			 list.clear();
		 }
	 } 
	 
	
	public static String getCorrespondsToTargetFile(String similar_file)
	{
		String fname="";
		if(similar_file!=null && !similar_file.equals("")){
		String path=getTargetGranulePath(similar_file);
	    Document s_doc=XmlParser.getInstance().getSimilarFileDoc();
		if (s_doc == null) {
			s_doc = xmlUtil.load(similar_file);
		}
		fname=path+s_doc.getDocumentElement().getAttribute("mmap");	
		}
		return fname;		
	}	
    //获取配置文件上的编号
	public static String extractNumFromConfigFile(String file,String suffix){
	    String count="";
	    if(file!=null && !file.equals("")){
	    	if (file.endsWith(".xml")) {
				int index = file.lastIndexOf(suffix);
				int len = suffix.length();
	            count=file.substring(index+len,file.indexOf(".xml"));
	    }
	    }
	    return count;
	}
	
	public static Node lookupMethodItemXmlNode(NodeList nodeList, String methodItem) {
		int length = nodeList.getLength();
		for (int i = 0; i < length; i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (node.getNodeName().equals("method")) {
					if (node.getAttributes().getNamedItem("name").getNodeValue().equals(methodItem))
						return node;
				}
			}
			Node child = node.getFirstChild();
			while (child != null) {
				if (child.getNodeType() == Node.ELEMENT_NODE) {
					if (child.getNodeName().equals("method")) {
						if (child.getAttributes().getNamedItem("name").getNodeValue().equals(methodItem))
							return child;
					}
				}
				child = child.getNextSibling();
			}
		}
	return null;
   }	
	public static void updateMethodMapShadowesXml(Document doc, String methodItem, ArrayList<String> new_list) {
		try {
			NodeList node_list=doc.getDocumentElement().getElementsByTagName("ShadowClasses");
			NodeList gmethodlist=null;
			if(node_list.getLength()>0)
			{
			   gmethodlist=node_list.item(0).getChildNodes();
			}
		    if(gmethodlist==null)
		    return ;
			Node node = lookupMethodItemXmlNode(gmethodlist, methodItem);
			if (node != null) {
				if (new_list.size() == 0)
					node.getParentNode().removeChild(node);
				else {
					Element new_node = doc.createElement("method");
					new_node.setAttribute("name", methodItem);
					int size = new_list.size();
					for (int i = 0; i < size; i++)
						new_node.setAttribute("shadow" + i, (String) new_list.get(i));
					node.getParentNode().replaceChild(new_node, node);
				   }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void updateMethodMapShadowes(String file,String similar_file) {
		try {
			XmlParser xpar = XmlParser.getInstance();
			if (xpar.doc == null) {
				xpar.doc = xmlUtil.load(file);
			}
			xpar.doc.normalize();			
			
			xpar.similar_doc = ReceivePacket.getInstance().getSimilarFileDom();
			if (xpar.similar_doc == null) {
				xpar.similar_doc = xmlUtil.load(similar_file);
			}
			xpar.similar_doc.normalize();			
			Element root = xpar.similar_doc.getDocumentElement();
			int len=root.getElementsByTagName("ShadowClasses").getLength();
		    if(len>0){
		    NodeList nodeList=root.getElementsByTagName("ShadowClasses").item(0).getChildNodes();
			int length = nodeList.getLength();
			for (int i = 0; i < length; i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					if (node.getNodeName().equals("method")) {
						String methodItem = node.getAttributes().getNamedItem("name").getNodeValue();
						//System.out.println("method item is :"+methodItem);
						String cname = methodItem.substring(methodItem.indexOf("#") + 1).trim();
						//System.out.println("class name is :"+cname);
						String connect_s = findConnectPoint(cname);
						//System.out.println("connect point name is :"+ connect_s);
						if (connect_s != null) {
							ArrayList<String> cs_list = getCShadowsSet(cname);
							int index = cs_list.indexOf(connect_s);
							// 获取从对接点后在c-shadow后的子列表
							List<String> sub_list = (List<String>) cs_list.subList(index, cs_list.size());

							// 遍历该节点所有的影子类
							NamedNodeMap nodeMap = node.getAttributes();
							ArrayList<String> new_list;
							int num = 0;
							for (int n = 0; n < nodeMap.getLength(); n++) {
								String nodeName = nodeMap.item(n).getNodeName();
								if (nodeName.startsWith("shadow"))
									num++;
							}

							// 原方法的映射表中是否含有这个方法表项
							//System.out.println("connect methoditem: " +methodItem);
							if (m_shadows.containsKey(methodItem)) {
								new_list = m_shadows.get(methodItem);

								if (index == 0) {
									new_list = new ArrayList<String>();
								}
								boolean flag = false;
								for (int m = index - 1; m >= 0; m--) {
									String item = (String) cs_list.get(m);
									//System.out.println("item : " + item);
									if (new_list.contains(item)) {
										new_list=new ArrayList<String>(new_list.subList(0, new_list.indexOf(item)+1));
										flag = true;
										break;
									}

								}
								if (!flag) {
									new_list = new ArrayList<String>();
								}
                               
		              			boolean found = false;
								String attr = null;
								String shadowname = null;
								for (int j = 0; j < num; j++) {
									attr = "shadow" + j;
									shadowname = nodeMap.getNamedItem(attr).getNodeValue();
									if (!found && sub_list.contains(shadowname))
										found = true;
									if (found) {
										if (!new_list.contains(shadowname))
											new_list.add(shadowname);
									}
								}
								if (new_list.size() > 0)
									m_shadows.put(methodItem, new_list);
								else
									m_shadows.remove(methodItem);
								updateMethodMapShadowesXml(xpar.doc, methodItem, new_list);
							} else {
								new_list = new ArrayList<String>();
								boolean found = false;
								String attr = null;
								String shadowname = null;
								for (int j = 0; j < num; j++) {
									attr = "shadow" + j;
									shadowname = nodeMap.getNamedItem(attr).getNodeValue();
									if (!found && sub_list.contains(shadowname))
										found = true;
									if (found) {
										if (!new_list.contains(shadowname))
											new_list.add(shadowname);
									}
								}
								if (new_list.size() > 0)
									m_shadows.put(methodItem, new_list);
								else
									m_shadows.remove(methodItem);
								updateMethodMapShadowesXml(xpar.doc, methodItem, new_list);
							}
						}
					}
				}
		     }	    	
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList LevelOrder(Node node) {
		ArrayList<Node> list = new ArrayList<Node>();
		Queue<Node> queue = new LinkedList<Node>();
		Node current = node;
		while ((current != null) || (!queue.isEmpty())) {
			if (current != null) {
				if (!list.contains(current))
					list.add(current);
				for (Node child = current.getFirstChild(); current != null; current = current.getPreviousSibling())
					queue.add(child);
				current = queue.poll();
			} else {
				current = queue.poll();
			}
		}
		return list;
	}

	public static void removeSubTree(Node node) {
		ArrayList<Node> list = LevelOrder(node);
		for (int i = list.size() - 1; i >= 0; i--) {
			Node delnode = list.get(i);			
			Node parent = delnode.getParentNode();

			String ssPrefix = delnode.getAttributes().getNamedItem("name").getNodeValue() + "_";
			List delList0 = new ArrayList<String>();
			Iterator iter = c_shadows.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry0 = (Map.Entry) iter.next();
				Object val0 = entry0.getValue();
				ArrayList<String> nlist0 = (ArrayList<String>) val0;
				for (String s : nlist0) {
					if (s.startsWith(ssPrefix))
						delList0.add(s);
				}
				nlist0.removeAll(delList0);
			}

			parent.removeChild(delnode);
		}
	}

	// 处理相似粒节点
	public static void addSimilarGranuleToList(Node simi_node) {
		if (simi_node.getNodeType() == Node.ELEMENT_NODE) {
			if (simi_node.getNodeName().equals("granule")) {
				if (simi_node.hasAttributes()) {
					NamedNodeMap attrs = simi_node.getAttributes();
					int num = 0;
					int length = attrs.getLength();
					for (int i = 0; i < length; i++) {
						String nodeName = attrs.item(i).getNodeName();
						if (nodeName.startsWith("shadow_class"))
							num++;
					}
					for (int j = 0; j < num; j++) {
						String sn = attrs.getNamedItem("shadow_class" + j).getNodeValue();
						String className = getClassName(sn);
						ArrayList<String> list;
						if (c_shadows.containsKey(className)) {
							list = c_shadows.get(className);
							if (!list.contains(sn))
								list.add(sn);
						} else {
							list = new ArrayList<String>();
							list.add(sn);
						}
						c_shadows.put(className, list);
						connect_shadows.add(sn);
						ByteCodeModify bcm = ByteCodeModify.getInstance();
						bcm.setTargetDir(getTargetGranulePath(tarPath));
						int n = list.indexOf(sn);
						if (n - 1 >= 0) {
							String supername = (String) list.get(n - 1);
							try {
								ByteCodeModify.modifySuperClass(sn, supername);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							try {
								ByteCodeModify.modifySuperClass(sn, className);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	// 获取除去对接点的影子类外，其他的粒和影子类均被收集
	public static ArrayList<String> getSubTreeIterms() {
		return sub_tree_items;
	}

	// 从XML节点获取粒、影子类的文件名称
	public static void getFileNamesFromXmlNode(Node node) {
		String attrs_name;
		String attrs_value;
		if (node.hasAttributes()) {
			NamedNodeMap attrs = node.getAttributes();
			int length = attrs.getLength();
			for (int i = 0; i < length; i++) {
				attrs_name = attrs.item(i).getNodeName();
				attrs_value = attrs.item(i).getNodeValue();
				if (attrs_name.equals("name") || attrs_name.startsWith("shadow_class")) {
					if (!sub_tree_items.contains(attrs_value))
						sub_tree_items.add(attrs_value);
				}
			}
		}
	}

	// 从xml节点中滤除掉对接影子类
	public static void removeFileNamesFromSubIterms() {
		ArrayList<String> temp = connect_shadows;
		String sname = null;
		for (Iterator iter = temp.iterator(); iter.hasNext();) {
			sname = (String) iter.next();
			if (sub_tree_items.contains(sname))
				sub_tree_items.remove(sname);
		}
	}

	public static void addXmlNode(Node node, Node parent, Node simi_node) {
		if (node != null && parent != null && simi_node != null) {
			String simi_name = simi_node.getAttributes().getNamedItem("name").getNodeValue();
			String node_name = null;
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (node.getNodeName().equals("granule"))
					node_name = node.getAttributes().getNamedItem("name").getNodeValue();
			}
			if (simi_name != null && node_name != null && !node_name.equals(simi_name)) {
				Element ell = parent.getOwnerDocument().createElement("granule");
				if (node.hasAttributes()) {
					NamedNodeMap attrs = node.getAttributes();
					int length = attrs.getLength();
					for (int i = 0; i < length; i++) {
						String attrs_name = attrs.item(i).getNodeName();
						String attrs_value = attrs.item(i).getNodeValue();
						ell.setAttribute(attrs_name, attrs_value);
						if (attrs_name.equals("name") || attrs_name.startsWith("shadow_class")) {
							if (!sub_tree_items.contains(attrs_value))
								sub_tree_items.add(attrs_value);
						}
					}
					int num = 0;
					for (int i = 0; i < length; i++) {
						String nodeName = attrs.item(i).getNodeName();
						if (nodeName.startsWith("shadow_class"))
							num++;
					}
					for (int j = 0; j < num; j++) {
						String sn = attrs.getNamedItem("shadow_class" + j).getNodeValue();
						String className = getClassName(sn).trim();
						boolean found = false;
						for (String s : connect_shadows) {
							if (s.endsWith("_" + className)) {
								found = true;
								break;
							}
						}

						ArrayList<String> list;
						if (c_shadows.containsKey(className)) {
							list = c_shadows.get(className);
							if (!list.contains(sn))
								list.add(sn);
						} else {
							list = new ArrayList<String>();
							list.add(sn);
						}
						c_shadows.put(className, list);

						if (!found) {
							connect_shadows.add(sn);
							ByteCodeModify bcm = ByteCodeModify.getInstance();
							bcm.setTargetDir(getTargetGranulePath(tarPath));
							int n = list.indexOf(sn);
							if (n - 1 >= 0) {
								String supername = (String) list.get(n - 1);
								try {
									ByteCodeModify.modifySuperClass(sn, supername);
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else {
								try {
									ByteCodeModify.modifySuperClass(sn, className);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
				parent.appendChild(ell);
				parent = ell;
			}
		}
		for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
			addXmlNode(child, parent, simi_node);
		}
	}

	public static ArrayList<String> lookupShadowSet(String cname) {
		ArrayList<String> new_list = new ArrayList<String>();
		if (c_shadows.containsKey(cname))
			new_list = (ArrayList<String>) c_shadows.get(cname);
		return new_list;
	}

	public static void iteratorShadowSet(String cname) {
		if (c_shadows.containsKey(cname)) {
			ArrayList<String> list = c_shadows.get(cname);
			for (String s : list)
				System.out.println("shadow class name is :" + s);
		} else
			System.out.println("c_shadows table is null");
	}	
	//获取最新的影子类
    public static String getLatestShadowClass(String cname)
    {
    	Map map = c_shadows;    	
    	String index=getClassName(cname);
		if(!c_shadows.containsKey(index))
		return null;
		else{		
	     ArrayList<String> list=c_shadows.get(index);	   
	     return (String)list.get(list.size()-1);			
		}    	
    }  
    //判断粒中是否含有当前类的影子类
    public static boolean haveShadowClass(String cname, String gname)
    {
      Map map=c_shadows;
      String index=getClassName(cname);
      String curSname=getShadowClass(index,gname);
      if(!c_shadows.containsKey(index)){
       return false;	  
      }
      else 
      {
    	 return c_shadows.get(index).contains(curSname);  
      }    	
    }   

     public static String getShadowClass(String base,String gname)
     {
    	return gname +granuleSeprator + base;    	 
     }   
    
    public static void setEnvolutionVersion()
    {
       int num=IndividualInfo.getInstance().getVersion();
       num+=1;
       XmlParser.getInstance().setNumber(num);
       IndividualInfo.getInstance().setVersion(num);
    }  
     
	//得到当前运行个体演化版本号	
	public static void setEnvolutionVersion(String filename, String suffix)
	{
		String num=extractNumFromConfigFile(filename,"TestGranuleTree");
		int numb=0;
		if("".equals(num)){
		numb=1;
		}
		else {
		numb=Integer.parseInt(num)+1;
		}
		//String temp=String.valueOf(numb);
		XmlParser.getInstance().setNumber(numb);
		IndividualInfo.getInstance().setVersion(numb);
	}

	// 获取要替换的粒所在的目录
	public static String getTargetGranulePath(String similar_file) {
		String path = null;
		if (similar_file != null && !similar_file.equals("")) {
			similar_file = similar_file.replace('/', File.separatorChar);
			int index = similar_file.lastIndexOf(File.separatorChar);
			path = similar_file.substring(0, index > 0 ? index + 1 : 0);
		}
		return path;
	}

	// 获取当前不适合的粒所在的目录
	public static String getOriginalGranulePath(String file) {
		String path = null;
		if (file != null && !file.equals("")) {
			file = file.replace('/', File.separatorChar);
			int index = file.lastIndexOf(File.separatorChar);
			path = file.substring(0, index > 0 ? index + 1 : 0);
		}
		return path;
	}

	// 补齐当前不适合粒的配置文件的目录
	public static String getOriginalGranuleFullPath(String file) {
		if (file != null && !file.equals("")) {
			file = IndividualInfo.getInstance().getWorkDirectory() + file;
		}
		return file;
	}
    
	public static String getCurrentDirectory()
	{
		String path=IndividualInfo.getInstance().getWorkDirectory();
		return path.substring(0,path.length()-1);
	}
	
	public static void updateGranuleSubTree(String gname, Node simi_node){		
		if (gname != null) {
			GranuleNode parent = GranuleTree.getInstance().getDelParentNode(gname);
			updateGranuleTreeNode(simi_node, parent); 
		}
	}

	public static void updateGranuleTreeNode(Node simi_node, GranuleNode parent) {
		GranuleNode gn = null;
		String node_name = "";
		String root_name = "";
		if (simi_node.getNodeType() == Node.ELEMENT_NODE) {
			if (simi_node.getNodeName().equals("granule")) {
				if (simi_node.hasAttributes()) {
					node_name = simi_node.getAttributes().getNamedItem("name").getNodeValue();
					root_name = simi_node.getAttributes().getNamedItem("class").getNodeValue();
					gn = new GranuleNode(node_name, root_name,parent);
					GranuleTree.getInstance().addGranuleNode(gn);
					String context_name=simi_node.getAttributes().getNamedItem("context").getNodeValue();
					if(!GopContext.getContexts().containsKey(context_name)){
					if(!con_seq.equals(""))
					con_seq=con_seq+";"+context_name;
					else 
					con_seq=context_name; 
					}
				}
			}
		}
		for (Node child = simi_node.getFirstChild(); child != null; child = child.getNextSibling()) {
			parent = gn;
			updateGranuleTreeNode(child, parent);
		}
	}

	public static Node lookupXmlNode(Document doc, String gname) {
		Element root = doc.getDocumentElement();
		NodeList nodeList = root.getFirstChild().getChildNodes();
		if (null != root) {
			int length = nodeList.getLength();
			Node node = null;
			Node child = null;
			for (int i = 0; i < length; i++) {
				node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					if (node.getNodeName().equals("granule")) {
						if (node.getAttributes().getNamedItem("name").getNodeValue().equals(gname))
							return node;
					}
				}
				child = node.getFirstChild();
				while (child != null) {
					if (child.getNodeType() == Node.ELEMENT_NODE) {
						if (child.getNodeName().equals("granule")) {
							if (child.getAttributes().getNamedItem("name").getNodeValue().equals(gname))
								return child;
						}
					}
					child = child.getNextSibling();
				}
			}
		}
		return null;
	}

	public Document getCurrentGranuleFileDom() {
		return doc;
	}

	public void setCurrentGranuleFileDom(Document dom) {
		doc = dom;
	}

	public Document getSimilarGranuleFileDom() {
		return similar_doc;
	}

	public void setSimilarGranuleFileDom(Document dom) {
		similar_doc = dom;
	}

	public static HashMap<String, ArrayList<String>> getClassToShadowMap() {
		return c_shadows;
	}

	// 获取对接点的列表
	public static ArrayList<String> getConnectedPointMap() {
		return connect_shadows;
	}

	//从内存中xml得到新的MD5	
	public static String getNewIndividualMd5Code(Element root) {
		Element sonNode = (Element) root.getFirstChild();
		String mdToString = FingerprintUtil.StringFingerprintWithSHA512(NodetoString(sonNode));
		root.setAttribute("md5", mdToString);
		return mdToString;
	}
	
	public static String NodetoString(Element node) {
		Transformer transformer = null;
		String result = null;
		if (node == null)
			throw new IllegalArgumentException();
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		if (transformer != null) {
			try {
				StringWriter sw = new StringWriter();
				transformer.transform(new DOMSource(node), new StreamResult(sw));
				result = sw.toString();
				return result;
			} catch (TransformerException te) {
				throw new RuntimeException(te.getMessage());
			}
		}
		return result;
	}	
	
	public static void IteratorHashMap() {
		Map map = c_shadows;
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			//System.out.println("da hua hua ti shi first :" + key);
			ArrayList<String> value = (ArrayList<String>) (entry.getValue());
			int v_size = value.size();
			for (int i = 0; i < v_size; i++) {
				//System.out.println("da hua hua ti shi second : " + value.get(i));
			}
			// Object val = entry.getValue();
		}
	}

	public static void IteratorMethodHashMap() {
		Map map = m_shadows;
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			System.out.println(" method map :" + key);
			ArrayList<String> value = (ArrayList<String>) (entry.getValue());
			int v_size = value.size();
			for (int i = 0; i < v_size; i++) {
				System.out.println("method map : " + value.get(i));
			}
		}
	}

	public static void main(String[] args) {
		try {
			XmlParser.getInstance().readXML("E:/bdGranuleJ/example/Log/bin/TestGranuleTree.xml");
			//XmlParser.getInstance().readMethodMapShadows("D:/test/src/Test/bin/MethodMapShadowClass.xml");
			
		   /* long startTime=System.currentTimeMillis();  
		    for(int i=0;i<1000000;i++){
		    	XmlParser.getInstance().readXML("D:/test/src/Test/bin/TestGranuleTree.xml");
		    }
		    	long endTime=System.currentTimeMillis(); //获取结束时间  
		    	System.out.println("程序运行时间： "+(endTime-startTime)+"ms");*/ 

			XmlParser.replaceGranuleTree("gPlainText","E:/bdGranuleJ/example/Log2/bin/TestGranuleTree.xml","gEncrypt");
			
			//XmlParser.replaceGranuleTree("TestGranuleTree.xml", "g", "D:/test/Test10/Test/bin/TestGranuleTree.xml", "gEncrypt");
			// getOriginalGranuleFullPath("TestGranuleTree.xml");
			// getCorrespondsToOriginalFile("TestGranuleTree.xml");
			/*
			 * ByteCodeModify bcm=ByteCodeModify.getInstance();
			 * bcm.setTargetDir("D:/test/Test10/Test/bin/");
			 * ByteCodeModify.modifySuperClass("gEncrypt","B");
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getMethodMap() {
		return m_map;
	}

	public void setMethodMap(String m_map) {
		this.m_map = m_map;
	}

	public Document getSimilarFileDoc() {
		return similar_doc;
	}

	public void setSimilarFileDoc(Document similar_doc) {
		this.similar_doc = similar_doc;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
