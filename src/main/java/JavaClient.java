/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

// Generated code
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import shared.SharedStruct;
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
