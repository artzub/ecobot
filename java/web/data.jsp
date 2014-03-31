<%@ page import="run.Statistics" %>
<%
    /*Object io = request.getSession().getAttribute("io");
    Object jo = request.getSession().getAttribute("jo");
    int i = -1, j = -1;
    if (io != null)
        i = Integer.parseInt(io.toString());
    if (jo != null)
       j = Integer.parseInt(jo.toString());*/
%>
<%=Statistics.getInstance().getLastFromTo(-1, -1)%>
