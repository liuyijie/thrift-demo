package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class test_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html lang=\"zh-cn\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1,minimum-scale=1, maximum-scale=1, user-scalable=no\" />\r\n");
      out.write("    <title> title here</title> \r\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/thrift.js\"></script>  \r\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/tutorial_types.js\"></script> \r\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/shared_types.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/SharedService.js\"></script>   \r\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/Calculator.js\"></script> \r\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/jquery.min.js\"></script> \r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tfunction calc() {\r\n");
      out.write("\t  var transport = new Thrift.Transport(\"/demo/test?type=json\");\r\n");
      out.write("\t  //var transport = new Thrift.Transport(\"http://localhost:9190\");\r\n");
      out.write("\t  var protocol  = new Thrift.Protocol(transport);\r\n");
      out.write("\t  var client    = new tutorial.CalculatorClient(protocol);\r\n");
      out.write("\r\n");
      out.write("\t  var work = new tutorial.Work()\r\n");
      out.write("\t  work.num1 = $(\"#num1\").val();\r\n");
      out.write("\t  work.num2 = $(\"#num2\").val();\r\n");
      out.write("\t  var op = parseInt($(\"#op\").val(), 10);\r\n");
      out.write("\r\n");
      out.write("\t  try {\r\n");
      out.write("\t    result = client.calculate(op, work);\r\n");
      out.write("\t    $('#result').val(result);\r\n");
      out.write("\t    $('#result').css('color', 'black');\r\n");
      out.write("\t  } catch(ouch){\r\n");
      out.write("\t    $('#result').val(ouch.why);\r\n");
      out.write("\t    $('#result').css('color', 'red');\r\n");
      out.write("\t  }\r\n");
      out.write("\t}\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t$(\"#run\").click(function(){\r\n");
      out.write("\t\t\tcalc();\r\n");
      out.write("\t\t})\r\n");
      out.write("\t})\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("num1: <input type=\"text\" id=\"num1\"/><br>\r\n");
      out.write("num2: <input type=\"text\" id=\"num2\"/><br>\r\n");
      out.write("操作: <select id=\"op\">\r\n");
      out.write("\t  \t<option value=\"1\">+</option>\r\n");
      out.write("\t  \t<option value=\"2\">-</option>\r\n");
      out.write("\t  \t<option value=\"3\">*</option>\r\n");
      out.write("\t  \t<option value=\"4\">/</option>\r\n");
      out.write("\t  </select><br>\r\n");
      out.write("结果: <input type=\"text\" id=\"result\"/><br>\r\n");
      out.write("运算：<input type=\"button\" id=\"run\" value=\"运算\"><br>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
