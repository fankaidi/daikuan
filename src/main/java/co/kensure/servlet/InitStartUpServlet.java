package co.kensure.servlet;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;



/**
 * 初始化servlet，初始化路径和ip
 *
 * 获取端口这个功能只有在tomcat下能用
 * @author fankd
 */

public class InitStartUpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = Logger.getLogger(InitStartUpServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

//	@Override
//	public void init(ServletConfig config) throws ServletException {
//
//		super.init(config);
//		try {
//			ServletContext context = config.getServletContext();
//			getIpAddressParam(context);
//			getRootFilePath(context);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//	private void getRootFilePath(ServletContext context) {
//		String filePath;
//		try {
//			filePath = context.getResource("/").getPath();
//		} catch (MalformedURLException e) {
//			throw new RuntimeException(e);
//		}
//		Const.DEFAULT_PATH = filePath;
//	}
//
//	private void getIpAddressParam(ServletContext context) throws UnknownHostException, SocketException {
//		String ip;
//		if (isWindowsOS()) {
//			ip = InetAddress.getLocalHost().getHostAddress();
//		} else {
//			ip = getLinuxLocalIp();
//		}
//
//		try {
//			String port = getHttpPort();
//			Const.DEFAULT_URL = "http://" + ip + ":" + port + context.getContextPath();
//		} catch (Exception e) {
//			LOGGER.error(e);
//		}
//	}
//
//	private boolean isWindowsOS() {
//		boolean isWindowsOS = false;
//		String osName = System.getProperty("os.name");
//		if (osName.toLowerCase().indexOf("windows") > -1) {
//			isWindowsOS = true;
//		}
//		return isWindowsOS;
//	}
//
//	String getHttpPort() {
//		String httpport = "";
//		try {
//			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
//			Set<ObjectName> objs = mbs.queryNames(new ObjectName("*:type=Connector,*"), Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
//			for (Iterator<ObjectName> i = objs.iterator(); i.hasNext();) {
//				ObjectName obj = i.next();
//				String scheme = mbs.getAttribute(obj, "scheme").toString();
//				String port = obj.getKeyProperty("port");
//				if ("http".equals(scheme)) {
//					httpport = port;
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error(e);
//		}
//		return httpport;
//	}
//
//	private String getLinuxLocalIp() throws SocketException {
//		String ip = "";
//		try {
//			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
//				NetworkInterface networkInterface = en.nextElement();
//				String name = networkInterface.getName();
//				if (!name.contains("docker") && !name.contains("lo")) {
//					for (Enumeration<InetAddress> enumIpAddr = networkInterface.getInetAddresses(); enumIpAddr.hasMoreElements();) {
//						InetAddress inetAddress = enumIpAddr.nextElement();
//						if (!inetAddress.isLoopbackAddress()) {
//							String ipaddress = inetAddress.getHostAddress().toString();
//							if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
//								ip = ipaddress;
//							}
//						}
//					}
//				}
//			}
//		} catch (SocketException ex) {
//			LOGGER.error("获取ip地址异常", ex);
//		}
//		return ip;
//	}
}
