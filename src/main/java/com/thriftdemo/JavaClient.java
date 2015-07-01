package com.thriftdemo;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import tutorial.Calculator;
import tutorial.InvalidOperation;
import tutorial.Operation;
import tutorial.Work;

public class JavaClient {
	public static void main(String [] args) {
	    try {
	      TTransport transport = new TSocket("localhost", 9190);
	      transport.open();
	      
	      TProtocol protocol = new  TBinaryProtocol(transport);
	      Calculator.Client client = new Calculator.Client(protocol);
	
	      perform(client);
	
	      transport.close();
	    } catch (TException x) {
	      x.printStackTrace();
	    } 
	}

	private static void perform(Calculator.Client client) throws TException{
	    client.ping();
	    System.out.println("ping()");
	
	    int sum = client.add(1,1);
	    System.out.println("1+1=" + sum);
	
	    Work work = new Work();
	
	    work.op = Operation.DIVIDE;
	    work.num1 = 100;
	    work.num2 = 1000;
	    try {
	    	int quotient = client.calculate(1, work);
	    	System.out.println("Whoa we can divide by 0= "+quotient);
	    } catch (InvalidOperation io) {
	    	System.out.println("Invalid operation: " + io.why);
	    }
	
	    work.op = Operation.MULTIPLY;
	    work.num1 = 157;
	    work.num2 = 11;
	    try {
	    	int diff = client.calculate(1, work);
	    	System.out.println("15-10=" + diff);
	    } catch (InvalidOperation io) {
	    	System.out.println("Invalid operation: " + io.why);
	    }
	
	}
}
