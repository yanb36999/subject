<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>商品管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style type="text/css">
body {
	font-family: "微软雅黑";
	background-color: #EDEDED;
}

h2 {
	text-align: center;
}

table {
	margin: 0 auto;
	text-align: center;
	border-collapse: collapse;
	font-size: 16px
}

td, th {
	padding: 7px;
}

th {
	background-color: #DCDCDC;
	width: 120px;
}

th.desc {
	width: 500px;
}

hr {
	margin-bottom: 20px;
	border: 1px solid #aaa;
}

a {
	text-decoration: none;
}
</style>

<!--引入jquery的js库-->
<script src="../js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<h2>商品管理</h2>
	<hr />
	<h1 id="h" align="center"></h1>
	<table border="1">
		<tr>
			<th>商品ID</th>
			<th>商品名称</th>
			<th>商品种类</th>
			<th>商品单价</th>
			<th>库存数量</th>
			<th class="desc">描述信息</th>
			<th>操 作</th>
		</tr>
		<c:forEach items="${prods }" var="prod">
		<tr>
			<td>${prod.id }</td>
			<td>${prod.name }</td>
			<td>${prod.category }</td>
			<td>${prod.price }</td>
			<td>${prod.pnum }</td>
			<td>${prod.description }</td>
			<td><a id="delProd"
				href="${contextPath}/delete.prod?id=${prod.id }"
				onclick="return confirm('确定删除商品 ${prod.name } 吗? ')">删除</a> |
				<a id="updProd"
				href="${contextPath}/prod_upd.jsp?id=${prod.id }&name=${prod.name }&category=${prod.category }&price=${prod.price }&pnum=${prod.pnum }&description=${prod.description }">修改</a>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="7" align="center"><a
				href="${contextPath}/truncate.prod"
				onclick="return confirm('确认清空所有数据吗 ')">清空所有数据</a></td>
		</tr>
	</table>
</body>
</html>



