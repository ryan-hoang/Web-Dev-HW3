<!doctype html>
<%@ page import = "HW3.StudentBean"%>
<%@ page import = "HW3.StudentDAO"%>
<%@ page import = "java.util.ArrayList"%>

<head><title>SimpleAcknowledgement</title></head>
<body>
<%@ page language= "java" %>
<h3>Thank you for filling out the survey.</h3>
<h5>Mean:${dataBean.mean}</h5>
<h5>Standard Deviation:${dataBean.standardDeviation}</h5>

<%
StudentDAO dao = new StudentDAO();
ArrayList<StudentBean> beans = dao.pullAllBeans();
out.println("<ul>");
for(StudentBean sb : beans)
{
    out.println("<li><a href=\"./survey-servlet?id="+ sb.getStudentID() +"\">" + sb.getStudentID() + "</a></li>");
}

out.println("</ul>");
%>
</body>
</html>