
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.rakuten.bean.response.*" %>
<html>
<head>
<title>音楽検索</title>
<meta name="description" content="音楽検索">
<meta name="keywords" content="音楽検索">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<script type="text/javascript" src="js/jquery-2.2.3.min.js?ver=20170703"></script>
<script type="text/javascript" src="js_music/music_search_artist.js?ver=20170703"></script>
</head>

<body bgcolor="lightcyan" >
<font size=5>■曲の雰囲気やテンポがわかる音楽検索</font>
<br/><br/>
<a href="/musicsearch?searchTrack=from_music_search_artist" >検索画面へ戻る</a>
<br/>
<div id = "responseArtistSearch">
   <input type="hidden" id="currentpage" value="1" />
   <input type="hidden" id="processFlg" value="false" />
   <% String pageCount = (String)request.getAttribute("pageCount");
      String checkResult = (String)request.getAttribute("checkResult");
      if( checkResult == null || "failure".equals(checkResult) ){
   %>
      <font color="red"> ※　アーティスト検索に失敗しました。</font>
   <% }else if(pageCount == null){%>
      <font color="red"> ※　検索結果が0件です</font>
      <input type="hidden" id="pageCount" value="0" />
   <% }else{  
      String responseHtml = (String)request.getAttribute("responseHtml"); %>
      <input type="hidden" id="pageCount" value="<%=pageCount %>" />
       <table id = "artistResult"  border="1">
          <tr><td>CDタイトル</td><td>CD画像</td><td>プレイリスト</td></tr>
          <%=responseHtml %>
       </table>
   <% }%>
</div>
</body>
</html>