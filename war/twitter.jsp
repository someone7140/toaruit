<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.twitter.search.WordTotal" %>
<%@ page import="com.twitter.search.DayTotal" %>
<%@ page import="com.twitter.search.DayTotalSummary" %>

<html>
<head>
<title>Twitter関連語句検索</title>
<meta name="description" content="twitter検索">
<meta name="keywords" content="twitter">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<link rel="stylesheet" href="js/jquery-ui.min.css" />
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
  <script type="text/javascript">
     $(function(){
        $("#searchform").on('submit', function(){
    	   $("#searchresult").empty();
	       $("#searchresult").append("<img src=\"image/loading.gif\"><br/><br/>");
	       $("#submitbutton").attr('disabled', 'disabled');
	    } );
	    var dates = $( '#jquery-ui-datepicker-from, #jquery-ui-datepicker-to' ).datepicker( {
        showAnim: 'clip',
        changeMonth: true,
        showCurrentAtPos: 1,
        dateFormat : 'yy-mm-dd',
        numberOfMonths: 3,
        minDate: '-1w',
        maxDate: '0',
        onSelect: function( selectedDate ) 
            {
            var instance = $( this ).data( 'datepicker' ),
            date = $.datepicker.parseDate(
                    instance.settings.dateFormat ||
                    $.datepicker._defaults.dateFormat,
                    selectedDate, instance.settings );
            if ( this.id == 'jquery-ui-datepicker-from') {
               date.setDate(date.getDate() + 1);
               $( '#jquery-ui-datepicker-to' ).datepicker( 'option', 'minDate' , date);
            
            }else{
               date.setDate(date.getDate() - 1);
               $( '#jquery-ui-datepicker-from' ).datepicker( 'option', 'maxDate' , date);
            
            }
            
            }

        } );
	 });
  </script>
</head>

<body bgcolor="lightcyan" >
<font size=5>■twitter関連語句解析</font>
<br/><br/>
<% String searchword = (String)request.getAttribute("searchword");
   String dateFrom = (String)request.getAttribute("dateFrom");
   String dateTo = (String)request.getAttribute("dateTo");
   String searchKind = (String)request.getAttribute("searchKind");
%>
<form method="post" action="/twittersearch"  id="searchform">
&nbsp;&nbsp;検索キーワード（最大集計件数1000ツイート）&nbsp;&nbsp;&nbsp;&nbsp;
<% if(searchword != null){ %>
<input type="text" id="searchword" name="searchword" maxlength=30 size=30 value = "<%=searchword %>"/>
<% }else{ %>
<input type="text" id="searchword" name="searchword" maxlength=30 size=30 />
<% } %><br/>
&nbsp;&nbsp;検索対象日付（7日前まで）&nbsp;&nbsp;
<% if(dateFrom != null){ %>
<input type="text" id="jquery-ui-datepicker-from" name="jquery-ui-datepicker-from" readonly="readonly"  value = "<%=dateFrom %>"/>
<% }else{ %>
<input type="text" id="jquery-ui-datepicker-from" name="jquery-ui-datepicker-from" readonly="readonly" />
<% } %>
<label for="jquery-ui-datepicker-from">から</label>
<% if(dateTo != null){ %>
<input type="text" id="jquery-ui-datepicker-to" name="jquery-ui-datepicker-to" readonly="readonly" value = "<%=dateTo %>"/>
<% }else{ %>
<input type="text" id="jquery-ui-datepicker-to" name="jquery-ui-datepicker-to" readonly="readonly"/>
<% } %>
<label for="jquery-ui-datepicker-to">まで</label><br/>

&nbsp;&nbsp;集計結果表示区分&nbsp;&nbsp;
<select id="searchKind" name="searchKind">
<% if(searchKind != null && searchKind.equals("word")){ %>
<option value="word" selected>ワード別</option>
<% }else{ %>
<option value="word">ワード別</option>
<% } %>
<% if(searchKind != null && searchKind.equals("day")){ %>
<option value="day" selected>日別・ワード別</option>
<% }else{ %>
<option value="day">日別・ワード別</option>
<% } %>
</select><br/>

&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="検索" id = "submitbutton" />
  <% String checkResult = (String)request.getAttribute("checkResult");
     if(checkResult != null){
        if(checkResult.equals("false")){ %>
          <font color="red"> ※　関連語句を検索したいワードおよび日付を入力してください。</font>
     <% }else if(checkResult.equals("failure")){  %>
          <font color="red"> ※　tweetの集計に失敗しました。</font>
     <% }else{  %>
          <font color="red"> ※　集計結果が0件でした。</font>
  <% }  } %>
<br/>
</form>
<br/>
<hr/>
<div id="searchresult">
  <% String tweetTargetCount = (String)request.getAttribute("tweetTargetCount");
     if(tweetTargetCount != null){ %>
  集計対象tweet数：<%=tweetTargetCount %><br/>
  <%}%>
  <% List<WordTotal> wordTotalList = (List<WordTotal>)request.getAttribute("wordTotalList");
     if(wordTotalList != null){ %>

	   <table border="1">
		 <tr bgcolor="olive">
			<td>関連キーワード</td>
			<td>件数</td>
		 </tr>
		 <% for (WordTotal wordTotal : wordTotalList) { %>
		    <tr>
			   <td><%=wordTotal.getWordText() %></td>
			   <td><%=wordTotal.getWordCount() %></td>
		    </tr>
		 <% } %>

	   </table>

  <%  } %>
  <% List<DayTotalSummary> dayTotalSummaryList = (List<DayTotalSummary>)request.getAttribute("dayTotalList");
     if(dayTotalSummaryList != null){ %>
        <table border="1">
           <tr bgcolor="olive">
           <% for (DayTotalSummary tempDayTotalSummary : dayTotalSummaryList){ %>
                 <td colspan = "2"><%=tempDayTotalSummary.getSummaryDay() %></td>
           <% } %>
           </tr>
           <tr bgcolor="olive">
           <% int tempWordCount = 0;
              int wordMaxCount =  0;
              for (DayTotalSummary tempDayTotalSummary : dayTotalSummaryList){
                 tempWordCount = tempDayTotalSummary.getDayCount();
                 if(tempWordCount > wordMaxCount){
                    wordMaxCount = tempWordCount;
                 }%>
			    <td>関連キーワード</td>
			    <td>件数</td>
            <%} %>
            <tr/>  
            
            <% List<DayTotal> dayTotalList = null;
               DayTotal dayTotal = null;
               String wordText = null;
               int wordCount = 0;
              
               for( int i =0 ; i < wordMaxCount ; i++){
            %>
              <tr>  
                 <%for(DayTotalSummary tempDayTotalSummary : dayTotalSummaryList){
                     dayTotalList = tempDayTotalSummary.getDayTotalArrayList();
                     try{
                         dayTotal = dayTotalList.get(i);
                         wordText = dayTotal.getWordText();
                         wordCount = dayTotal.getWordCount();
                     
                     }catch(Exception e){
                         wordText = "";
                         wordCount = 0;
                     }
                  %>
                  <td><%=wordText %></td>
			      <td><%=wordCount %></td>
                 <%} %>
              <tr/>
           <%} %>
	  </table>
  <% } %>
</div>
</body>
</html>