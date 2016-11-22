<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />

<script type="text/javascript" src="/spider/js/jquery.js"></script>
<script type="text/javascript">

  function start(){
    $.ajax({
      type:'POST',
      url:'startSpider',
      data: "github",
      success:function( a ){
        alert( a );
      }

    });
  }

  var websocket = null;

  //判断当前浏览器是否支持websocket
  if( 'WebSocket' in window ){
    websocket = new WebSocket( "ws://lumpanda.com/spider/websocket" );
  }
  else {
    alert("No support websocket");
  }

  function showMessage( message ){
    document.getElementById("offerMessage").innerHTML += message + '<br/>';
  }

  //连接成功
  websocket.onopen = function(){
    showMessage("open");
  }
  //连接关闭
  websocket.onclose = function(){
    showMessage("close");
  }
  //发生错误
  websocket.onerror = function(){
    showMessage("error");
  }
  //收到消息
  websocket.onmessage = function(event){
    showMessage( event.data );
  }
  //窗口关闭时，断开连接
  window.onbeforeunload = function(){
    websocket.close();
  }

  //发送消息
  function sendMessage(){
    var message = document.getElementById("inputMessage").value;
    websocket.send( message );
  }

  //关闭websocket
  function closeWebSocket(){
    websocket.close();
  }

</script>

<head>
  <title>StartSpider</title>
</head>

<body>

<div class="startButton">
  <button class="button" onclick="start();">开始爬虫</button>
</div>

<div class="control">
  <input id="inputMessage" type="text" />
  <button onclick="sendMessage();">Send</button>
  <button onclick="closeWebSocket();">Close</button>
</div>
<div id="offerMessage" class="offerMessage">

</div>

</body>
</html>
