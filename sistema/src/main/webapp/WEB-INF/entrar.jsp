<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="utf-8" />
<title>Catedral | Login</title>
<meta name="description"
	content="Angularjs, Html5, Music, Landing, 4 in 1 ui kits package" />
<meta name="keywords"
	content="AngularJS, angular, bootstrap, admin, dashboard, panel, app, charts, components,flat, responsive, layout, kit, ui, route, web, app, widgets" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />

<link rel="stylesheet"
	href="assets/components/bootstrap/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="assets/components/animate.css/animate.css"
	type="text/css" />
<link rel="stylesheet"
	href="assets/components/font-awesome/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet" href="assets/css/font.css" type="text/css" />
<link rel="stylesheet" href="assets/css/app.css" type="text/css" />

</head>
<body>
	<div class="app app-header-fixed  ">


		<div class="container w-xxl w-auto-xs">
			<a href class="navbar-brand block m-t">Catedral</a>
			<div class="m-b-lg">
				<div class="wrapper text-center">
					<strong>Faça login para acessar o sistema</strong>
				</div>
				<form name="form" action="login" method="POST">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="list-group list-group-sm">
						<div class="list-group-item">
							<input type="email" placeholder="Email"
								class="form-control no-border" name="usuario" required>
						</div>
						<div class="list-group-item">
							<input type="password" placeholder="Senha"
								class="form-control no-border" name="senha" required>
						</div>
					</div>
					<button type="submit" class="btn btn-lg btn-primary btn-block">Entrar</button>

					<div class="line line-dashed"></div>
				</form>
			</div>
			<div class="text-center">
				<p>
					<small class="text-muted">xLabi<br>&copy; 2017</small>
				</p>
			</div>
		</div>


	</div>

	<script src="assets/components/jquery/jquery.min.js"></script>
	<script src="assets/components/bootstrap/js/bootstrap.js"></script>
	<script src="assets/js/ui-load.js"></script>
	<script src="assets/js/ui-jp.config.js"></script>
	<script src="assets/js/ui-jp.js"></script>
	<script src="assets/js/ui-nav.js"></script>
	<script src="assets/js/ui-toggle.js"></script>

</body>
</html>
