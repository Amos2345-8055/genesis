<%@ page import="com.mongodb.client.*, org.bson.Document, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Online Exam Result</title>
<style>
  body { background:black; color:cyan; font-family:courier; text-align:center; }
</style>
</head>
<body>
<h2>ONLINE EXAM RESULT</h2><hr/>

<%
int mark = 0;
String[] ans = { request.getParameter("ans1"), request.getParameter("ans2"), request.getParameter("ans3") };

try (MongoClient mongo = MongoClients.create("mongodb://localhost:27017")) {
    List<Document> qlist = mongo.getDatabase("examDB").getCollection("examTab")
                               .find().into(new ArrayList<Document>());

    for (int i = 0; i < ans.length && i < qlist.size(); i++)
        if (ans[i] != null && ans[i].equalsIgnoreCase(qlist.get(i).getString("answer")))
            mark += 5;

    out.println("<h3>Your Mark: " + mark + "</h3>");
    if (mark >= 10)
        out.println("<h3>✅ Congratulations! Eligible for next round.</h3>");
    else
        out.println("<h3>❌ Sorry! Not eligible for next round.</h3>");
} catch (Exception e) {
    out.println("<p style='color:red'>Error: " + e.getMessage() + "</p>");
}
%>

<br><a href="ExamClient.html" style="color:lightblue;">Back</a>
</body>
</html>
