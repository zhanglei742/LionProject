package com.loginmodel.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginmodel.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ResponseBody;
@RestController
@RequestMapping("/Receive")
public class ChatWithApp  {

//	@RequestMapping("/SendCode")
//	@ResponseBody
//		//======注解方式 参数
//	public String SendCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		//HttpServletRequest req, HttpServletResponse rep
//		String webphone = request.getParameter("inputPhoneET");
//		String weblocation = request.getParameter("location");
//		//String webphone ="130";
//		//String weblocation ="1";
//		System.out.println("后端接受用户第一次传来的信息");
//
//		//======添加service层
//		InterfaceClass interfaceClass= new AchieveInterfaceClass();
//		if (interfaceClass.SendCode(webphone, weblocation)) {
//			//返回1表明验证码无误并成功
//			return "1";
//		} else
//			//返回1表明验证码输入错误
//			return "0";
//			
//	}
//	@RequestMapping("/CheckCode")
//	@ResponseBody
//	public String CheckCode(HttpServletRequest request, HttpServletResponse response)throws Exception
//	{
//		//Integer phone, Integer location, Integer code
////		String  phone1 ="130";
////		String location2 ="1";
////		String code3 = "4321";
//		String secondphone =request.getParameter("phonenumber");
//		String secondlocation =request.getParameter("location");
//		String secondcode =request.getParameter("code");
//		System.out.println("匹配验证码信息：用户第二次传来的手机号为：" + secondphone + "；功能位置：" + secondlocation+ "；验证码：" + secondcode);
//
//		InterfaceClass interfaceClass= new AchieveInterfaceClass();
//		if (interfaceClass.CheckCode(secondphone, secondlocation,secondcode)) {
//			return "传给前端：信息已经匹配";
//		} else
//			return "传给前端：信息不匹配";
//	}
	

	@RequestMapping("/IfPhoneExit")
	@ResponseBody
	public String IfPhoneExit(HttpServletRequest req, HttpServletResponse rep) throws Exception {
		long phone=0;
		if(req.getParameter("phone")!=null)
		{
			 phone =Long.parseLong( req.getParameter("phone"));
		}
		LoginDao op = new LoginDaoImpl();
		System.out.println(phone+" : 号码是否存在?");
		if (op.PhoneExist(phone)) {
			return phone+" : phoneExist";
		} else
			return phone+": phoneNoExist";
	}
	@RequestMapping("/AddUser")
	@ResponseBody
	public String AddUser(HttpServletRequest req, HttpServletResponse rep) throws Exception {
		long phone =Long.parseLong( req.getParameter("phone"));
		String pwd=req.getParameter("pwd");
		LoginDao op = new LoginDaoImpl();
		System.out.println(phone+" : 开始插入");
		if (op.AddUser(phone, pwd)) {
			return phone+" : 插入成功";
		} else
			return phone+": 插入失败";
	}
	@RequestMapping("/UpdateUserInfo")
	@ResponseBody
	public String UpdateUserInfo(HttpServletRequest req, HttpServletResponse rep) throws Exception {
		User user=new User();
		if(req.getParameter("phone")!=null)
		{
			user.setPhonenumber(Long.parseLong( req.getParameter("phone")));
		}
		else return "error：手机号为空";
		if(req.getParameter("name")!=null)
		{
			user.setName(req.getParameter("name"));
			
		}
		if(req.getParameter("pwd")!=null)
		{
			user.setPwd(req.getParameter("pwd"));
		}
		if(req.getParameter("email")!=null)
		{
			System.out.println("0email"+req.getParameter("email"));
			user.setEmail(req.getParameter("email"));
			System.out.println("1email"+user.getEmail());
			
		}
		LoginDao op = new LoginDaoImpl();
		//System.out.println(user.getPhonenumber()+" : 更改名字");
		if (op.UpdateUserInfo(user.getPhonenumber(), user)) {
			return "更新用户信心成功";
		} 
		else return "更新用户信息失败";
	}
	
	
/*	@RequestMapping("/test")
	public void test(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		//Map<String, String> map = new HashMap<String, String>();

		String requestUrl = request.getRequestURL().toString();//得到请求的URL地址
		String requestUri = request.getRequestURI();//得到请求的资源
		String queryString = request.getQueryString();//得到请求的URL地址中附带的参数
		String remoteAddr = request.getRemoteAddr();//得到来访者的IP地址
		String remoteHost = request.getRemoteHost();
		int remotePort = request.getRemotePort();
		String remoteUser = request.getRemoteUser();
		String method = request.getMethod();//得到请求URL地址时使用的方法
		String pathInfo = request.getPathInfo();
		String localAddr = request.getLocalAddr();//获取WEB服务器的IP地址
		String localName = request.getLocalName();//获取WEB服务器的主机名
		response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
		//通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write("获取到的客户机信息如下：");
		out.write("<hr/>");
		out.write("请求的URL地址："+requestUrl);
		out.write("<br/>");
		out.write("请求的资源："+requestUri);
		out.write("<br/>");
		out.write("请求的URL地址中附带的参数："+queryString);
		out.write("<br/>");
		out.write("来访者的IP地址："+remoteAddr);
		out.write("<br/>");
		out.write("来访者的主机名："+remoteHost);
		out.write("<br/>");
		out.write("使用的端口号："+remotePort);
		out.write("<br/>");
		out.write("remoteUser："+remoteUser);
		out.write("<br/>");
		out.write("请求使用的方法："+method);
		out.write("<br/>");
		out.write("pathInfo："+pathInfo);
		out.write("<br/>");
		out.write("localAddr："+localAddr);
		out.write("<br/>");
		out.write("localName："+localName);
		response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
		//通过设置响应头控制浏览器以UTF-8的编码显示数据
		response.setHeader("content-type", "text/html;charset=UTF-8");
		out = response.getWriter();
		Enumeration<String> reqHeadInfos = request.getHeaderNames();//获取所有的请求头
		out.write("获取到的客户端所有的请求头信息如下：");
		out.write("<hr/>");
		while (reqHeadInfos.hasMoreElements()) {
			String headName = (String) reqHeadInfos.nextElement();
			String headValue = request.getHeader(headName);//根据请求头的名字获取对应的请求头的值
			out.write(headName+":"+headValue);
			out.write("<br/>");
		}
		out.write("<br/>");
		out.write("获取到的客户端Accept-Encoding请求头的值：");
		out.write("<hr/>");
		String value = request.getHeader("Accept-Encoding");//获取Accept-Encoding请求头对应的值
		out.write(value);

		Enumeration<String> e = request.getHeaders("Accept-Encoding");
		while (e.hasMoreElements()) {
			String string = (String) e.nextElement();
			System.out.println(string);
		}
//		OP op = new Register();
//		if (op.MatchInfo("123", "123")) {
//			System.out.println("ƥ��ɹ�" );
//		}
//		else {
//			System.out.println("�򲻿����ݿ⣬ʧ��");
//		}
//		map.put("results", "login success");
//		return "1";
	}*/
}