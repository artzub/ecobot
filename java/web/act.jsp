<%@ page import="bot.Controller" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %>
<%
    LinkedList<String> log1 = Controller.getInstance().getLog();
    List<String> log = Controller.getInstance().getLog().subList(0, log1.size() < 10 ? log1.size() : 10);
    for (int i = 0; i < log.size(); i++) {
%>
<%=log.get(i)%>
<%
    }
%>
200