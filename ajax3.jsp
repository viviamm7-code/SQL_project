<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 2026-01-20
  Time: 오전 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
	<script type="text/javascript">
		window.onload = function() {
            document.getElementById( 'btn' ).onclick = function() {
                const request = new XMLHttpRequest();
                request.onreadystatechange = function() {
					if( request.readyState == 4 ) {
                        if( request.status == 200 ) {
                            // 비동기 처리의 모든 조작
                            console.log( request.responseText );
						} else {
                            alert( '잘못된 요청' );
						}
					}
                };
                request.open( 'GET', './data/csv.jsp', true );
                request.send();
            };
		};
	</script>
</head>
<body>
<button id="btn">요청하기</button>
</body>
</html>
