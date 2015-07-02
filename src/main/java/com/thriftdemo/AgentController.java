package com.thriftdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import tutorial.Calculator;


@Controller
public class AgentController {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Calculator.Processor processor = new Calculator.Processor(new CalculatorHandler());

    @RequestMapping(value = "/test")
    public void test(final ModelMap model, 
    		HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
}
