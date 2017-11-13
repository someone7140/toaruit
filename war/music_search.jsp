
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.music.bean.*" %>
<html>
<head>
<title>音楽検索</title>
<meta name="description" content="音楽検索">
<meta name="keywords" content="音楽検索">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>

</head>

<body bgcolor="lightcyan" >
<font size=5>■曲の雰囲気やテンポがわかる音楽検索</font>
<br/><br/>
<% String searchArtist = (String)request.getAttribute("searchArtist");
   String searchTrack = (String)request.getAttribute("searchTrack");
   String searchNonDispFlg = (String)request.getAttribute("searchNonDispFlg");
   if( searchNonDispFlg == null || !"true".equals(searchNonDispFlg) ){
%>
<form method="post" action="/musicsearch">
&nbsp;&nbsp;アーティスト名入力&nbsp;&nbsp;&nbsp;&nbsp;
<% if(searchArtist != null){ %>
<input type="text" id="searchArtist" name="searchArtist" maxlength=30 size=30 value = "<%=searchArtist %>"/>
<% }else{ %>
<input type="text" id="searchArtist" name="searchArtist" maxlength=30 size=30 />
<% } %><br/>
&nbsp;&nbsp;曲名入力&nbsp;&nbsp;&nbsp;&nbsp;
<% if(searchTrack != null){ %>
<input type="text" id="searchTrack" name="searchTrack" maxlength=30 size=30 value = "<%=searchTrack %>"/>
<% }else{ %>
<input type="text" id="searchTrack" name="searchTrack" maxlength=30 size=30 />
<% } %><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="検索" id = "submitbutton" />
 </form>
 <% } %>
 <br/><br/>
  <% String checkResult = (String)request.getAttribute("checkResult");
     if(checkResult != null){
        if(checkResult.equals("false")){ %>
          <font color="red"> ※　アーティスト名・曲名のどちらか、もしくは双方入力して検索してください。</font>
     <% }else if(checkResult.equals("failure")){  %>
          <font color="red"> ※　曲検索に失敗しました。</font>
     <% }else{  %>
          <font color="red"> ※　該当の条件にヒットする曲がありませんでした。</font>
  <% }  } %>
 <% ResultMusicBean musicBean = (ResultMusicBean)request.getAttribute("resultMusicBean"); 
   if(musicBean != null){
 %>
      <table border="1">
        <tr>
           <td colspan = "2">アーティスト</td>
           <td>曲名</td>
           <td>雰囲気</td>
           <td>テンポ</td>
        </tr>
        <tr>
           <td><%=musicBean.getArtistName() %></td>
           <td><img src ="<%=musicBean.getArtistImage() %>"  width = "100" height = "100" /></td>
           <td><%=musicBean.getTitle() %></td>
           <td><% List<String> moodList = musicBean.getMoodList();
                  for (String moodText : moodList) { %>
                      <%=moodText %> <br/>
               <%  } %>
           </td>
           <td><% List<String> tempoList = musicBean.getTempoList();
                  for (String tempoText : tempoList) { %>
                      <%=tempoText %> <br/>
               <%  } %>
           </td>
   
 <%  }  %>

</body>
</html>