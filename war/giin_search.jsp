
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.giin.search.*" %>
<html>
<head>
<title>国会議員・都道府県知事一覧</title>
<meta name="description" content="国会議員">
<meta name="keywords" content="国会議員">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js_giin/giin_display.js"></script>

</head>

<body bgcolor="lightcyan" >
<font size=5>■国会議員・都道府県知事一覧(Wikipediaより取得)</font>
<br/><br/>
<table  rules="none">
<caption>表示したい都道府県のチェック</caption>
<% HashMap<Integer ,String> map = (HashMap<Integer ,String>)request.getAttribute("todouhukenList");
   int i =1;
   for(Map.Entry<Integer ,String> entry : map.entrySet()){

      if(i%6 ==1){%>
         <tr>
    <%}%>
      <td>
          <input type = "checkbox" class = "<%=entry.getKey()%>"/><%=entry.getValue()%>
      </td>
    <% if(i%6 ==0){%>
         </tr>
    <%}%>
 <%i++;
   }%>
</table>
<% String errorflg = (String)request.getAttribute("errorflg"); 
   if(GiinConstants.WIKI_ERROR_FLG.equals(errorflg)){
%>
   <font color="red"> ※　Wikipediaからデータ取得時にエラーが発生しました。</font>
<% }else{ %>
<hr/><br/>
<%      List<TodoufukenGiinBean> todoufukenGiinBeanList = (List<TodoufukenGiinBean>)request.getAttribute("todoufukenGiinList");
      for (TodoufukenGiinBean todoufukenGiinBean : todoufukenGiinBeanList) {
%>
         <div class ="<%=todoufukenGiinBean.getTodouhukenId()%>">
         <font size=3> 【<%=todoufukenGiinBean.getTodouhukenName() %>】 </font><br/>
         <table  border="0"><tr>
         <td valign="top">
         <% 
            List<SyuugiinBean> syuugiinBeanList = todoufukenGiinBean.getSyuugiinList();
            if(syuugiinBeanList != null && syuugiinBeanList.size() != 0){
         %>
               <table border="1"><tr bgcolor="olive"><td colspan="5">衆議院議員一覧</td></tr>
               <tr bgcolor="olive"><td>区分</td><td>選挙区</td><td>政党</td><td>議員名</td><td>任期満了日</td>
               <% for (SyuugiinBean syuugiinBean : syuugiinBeanList) { %>
                  <tr><td><% if( GiinConstants.HIREI_ID.equals(syuugiinBean.getSenkyoKubun()) ){ %>比例<%}else{%>小選挙区<%}%></td>
                      <td><%=syuugiinBean.getSenkyoku() %></td>
                      <td><%=syuugiinBean.getSeitou() %></td>
                      <td><%=syuugiinBean.getGiinName() %></td>
                      <td><%=syuugiinBean.getNinkiManryoubi() %></td>
                  </tr>

          <%   } %>
              </table>
         <% }%>
         </td>
         
         <td valign="top">
         <% 
            List<SangiinBean> sangiinBeanList = todoufukenGiinBean.getSangiinList();
            if(sangiinBeanList != null && sangiinBeanList.size() != 0){
         %>
               <table border="1"><tr bgcolor="olive"><td colspan="4">参議院議員一覧</td></tr>
               <tr bgcolor="olive"><td>区分</td><td>政党</td><td>議員名</td><td>任期満了日</td>
               <% for (SangiinBean  sangiinBean : sangiinBeanList) { %>
                  <tr><td><% if( GiinConstants.HIREI_ID.equals(sangiinBean.getSenkyoKubun()) ){ %>比例<%}else{%>選挙区<%}%></td>
                      <td><%=sangiinBean.getSeitou() %></td>
                      <td><%=sangiinBean.getGiinName() %></td>
                      <td><%=sangiinBean.getNinkiManryoubi() %></td>
                  </tr>
          <%   } %>
              </table>
         <% }%>
         </td>
         
         <td valign="top">
         <% 
            List<ChijiBean> chijiBeanList = todoufukenGiinBean.getChijiList();
            if(chijiBeanList != null && chijiBeanList.size() != 0){
         %>
               <table border="1"><tr bgcolor="olive"><td colspan="6">都道府県知事一覧</td></tr>
               <tr bgcolor="olive"><td>知事名</td><td>任期</td><td>任期満了日</td><td>出身高校</td><td>出身大学</td><td>前職</td>
               <% for (ChijiBean  chijiBean : chijiBeanList) { %>
                  <tr><td><%=chijiBean.getGiinName() %></td>
                      <td><%=chijiBean.getNinki() %></td>
                      <td><%=chijiBean.getNinkiManryoubi() %></td>
                      <td><%=chijiBean.getSyussinKoukou() %></td>
                      <td><%=chijiBean.getSyussinDaigaku() %></td>
                      <td><%=chijiBean.getZensyoku()%></td>
                  </tr>
          <%   } %>
              </table>
         <% }%>
         </td>
         
      </tr></table><br/></div>               
      <% } %>


<% } %>
</body>
</html>