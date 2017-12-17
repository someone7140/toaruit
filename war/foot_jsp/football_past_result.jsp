
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.rakuten.bean.response.*" %>
<html>
<head>
<title>ヨーロッパサッカー順位表・チーム情報</title>
<meta name="description" content="サッカー">
<meta name="keywords" content="サッカー">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<script type="text/javascript" src="js/jquery-2.2.3.min.js?ver=20170703"></script>
<script type="text/javascript" src="js_foot/foot_team_js.js"></script>

</head>

<body bgcolor="lightcyan" >
<% String checkResult = (String)request.getAttribute("checkResult");
   if(  "failure".equals(checkResult) ){
%>
   <font color="red"> ※　データ取得時にエラーが発生しました。</font>
<% }else{ %>
  <%=(String)request.getAttribute("resultHtml") %><br/>
<% } %>

</body>
</html>