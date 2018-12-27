<%--
  Created by IntelliJ IDEA.
  User: smile
  Date: 2018/9/5
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Hello React!</title>
    <script src="https://cdn.bootcss.com/react/16.4.0/umd/react.development.js"></script>
    <script src="https://cdn.bootcss.com/react-dom/16.4.0/umd/react-dom.development.js"></script>
    <script src="https://cdn.bootcss.com/babel-standalone/6.26.0/babel.min.js"></script>
</head>
<body>
    <div id="example"></div>

<script type="text/babel">
    React.render(
        <div>
            <h1>Hello</h1>
        </div>,
        document.getElementById("example")
    );
</script>
</body>
</html>
