
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.rakuten.bean.response.*" %>
<html>
<head>
<title>食べたいものチャット</title>
<meta name="description" content="食べたい">
<meta name="keywords" content="食べたい">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<script type="text/javascript" src="js/jquery-2.2.3.min.js?ver=20171004"></script>
<script type="text/javascript" src="js_food/food_net.js?ver=20171004"></script>
</head>

<body bgcolor="lightcyan" >
<br/>
<div id = "foodNetSearch">
   <input type="hidden" id="currentpage" value="1" />
   <input type="hidden" id="processFlg" value="false" />
   <% String pageCount = (String)request.getAttribute("pageCount");
      String checkResult = (String)request.getAttribute("checkResult");
      String foodName = (String)request.getAttribute("foodName");
      String lowprice = (String)request.getAttribute("lowprice");
      String highprice = (String)request.getAttribute("highprice");
      if( "failure".equals(checkResult) ){
   %>
      <font color="red"> ※　検索に失敗しました。</font>
   <% }else if(pageCount == null){%>
      <font color="red"> ※　検索結果が0件です</font>
      <input type="hidden" id="pageCount" value="0" />
   <% }else{  
      String responseHtml = (String)request.getAttribute("responseHtml"); %>
      <input type="hidden" id="pageCount" value="<%=pageCount %>" />
      <input type="hidden" id="foodName" value="<%=foodName %>" />
      <input type="hidden" id="lowprice" value="<%=lowprice %>" />
      <input type="hidden" id="highprice" value="<%=highprice %>" />
       <table id = "foodNetResult"  border="1">
          <tr><td>商品名</td><td>商品画像</td><td>ショップ名</td><td>価格</td><td>商品説明</td></tr>
          <%=responseHtml %>
       </table>
   <% }%>
</div>
</body>
</html>