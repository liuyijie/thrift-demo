

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;

import tutorial.Calculator;

public class Agent extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public static CalculatorHandler handler;

	public static Calculator.Processor processor;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		
		TTransport inTransport = null;  
        TTransport outTransport = null;  
        TProtocolFactory inProtocolFactory = null;  
        TProtocolFactory outProtocolFactory = null;  
        String protocolType = request.getParameter("type");  
  
        try {  
            if ("json".equals(protocolType)) {//支持json协议  
                inProtocolFactory = new TJSONProtocol.Factory();  
                outProtocolFactory = new TJSONProtocol.Factory();  
                response.setContentType("application/json");  
            } else {  
                inProtocolFactory = new TCompactProtocol.Factory();  
                outProtocolFactory = new TCompactProtocol.Factory();  
                response.setContentType("application/x-thrift");  
            }  
            System.out.println("-------------------------------------------");
              
            InputStream in = request.getInputStream();  
            OutputStream out = response.getOutputStream();  
  
            TTransport transport = new TIOStreamTransport(in, out);  
            inTransport = transport;  
            outTransport = transport;  
  
            TProtocol inProtocol = inProtocolFactory.getProtocol(inTransport);  
            TProtocol outProtocol = outProtocolFactory  
                    .getProtocol(outTransport);  
  
            processor.process(inProtocol, outProtocol);  
            out.flush();  
        } catch (TException te) {  
            throw new ServletException(te);  
        }  
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		System.out.println(this.getClass().getSimpleName() + "init");
		super.init(config);
		try {
			handler = new CalculatorHandler();
			processor = new Calculator.Processor(handler);
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
	
	@Override
	public void destroy(){
		System.out.println(this.getClass().getSimpleName() + "destroy");
		super.destroy();
	}
}
