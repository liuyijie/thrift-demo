<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1, maximum-scale=1, user-scalable=no" />
    <title> title here</title> 
<script type="text/javascript" src="resources/js/thrift.js"></script>  
<script type="text/javascript" src="resources/js/tutorial_types.js"></script> 
<script type="text/javascript" src="resources/js/shared_types.js"></script>
<script type="text/javascript" src="resources/js/SharedService.js"></script>   
<script type="text/javascript" src="resources/js/Calculator.js"></script> 
<script type="text/javascript" src="resources/js/jquery.min.js"></script> 
<script type="text/javascript">
	function calc() {
	  //var transport = new Thrift.Transport("/test?type=json");
	  var transport = new Thrift.Transport("http://localhost:9190");
	  var protocol  = new Thrift.Protocol(transport);
	  var client    = new tutorial.CalculatorClient(protocol);

	  var work = new tutorial.Work()
	  work.num1 = $("#num1").val();
	  work.num2 = $("#num2").val();
	  var op = parseInt($("#op").val(), 10);

	  try {
	    result = client.calculate(op, work);
	    $('#result').val(result);
	    $('#result').css('color', 'black');
	  } catch(ouch){
	    $('#result').val(ouch.why);
	    $('#result').css('color', 'red');
	  }
	}
	$(function(){
		$("#run").click(function(){
			calc();
		})
	})
</script>
</head>
<body>
num1: <input type="text" id="num1"/><br>
num2: <input type="text" id="num2"/><br>
操作: <select id="op">
	  	<option value="1">+</option>
	  	<option value="2">-</option>
	  	<option value="3">*</option>
	  	<option value="4">/</option>
	  </select><br>
结果: <input type="text" id="result"/><br>
运算：<input type="button" id="run" value="运算"><br>
</body>
</html>
