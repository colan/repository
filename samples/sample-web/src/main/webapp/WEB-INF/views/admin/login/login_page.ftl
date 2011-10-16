<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript" src="${admin.jsPath}/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="${admin.jsPath}/jquery.form.js"></script>
		<title>SGCMS-Login</title>
		<script type="text/javascript">
			
			function formSubmit() {
				$('form').ajaxSubmit({
					url: '${contextPath}/admin/login/ajaxJsonLogin.do',
					type: 'post',
					dataType: 'json',
					success: function(data) {
						$('#msg').text(data.msg);
						if(data.flag) {
							document.location.href = '${contextPath}/admin/login/main.do';
						} else { 
						}
					}
				});
			}
			
		</script>
	</head>
	<body >
		<form id="form1" method="post">
			<ul>
					<li>
						<span>用户名：</span>
			  						<input type="text" name="student.username" id="username" class="text"  />
					</li>
					<li>
						<span>密&nbsp;&nbsp;&nbsp;码：</span>
			  						<input type="password" name="student.passwordMd5" id="passwordMd5"   />
					</li>
					<li >
						<span style="color:red;" id="msg"></span>
					</li>
					<input type="button" value="登录" onclick="formSubmit()" />
			</ul>
		</form>
	</body>
</html>
