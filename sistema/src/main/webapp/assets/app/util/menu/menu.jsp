
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<ul class="nav  hidden-sm">

	<li class="line dk"></li>


	<li>
		<a href="#/dashboard">
			<i class="fa fa-dashboard"></i>
			<span>Dashboard</span> 
		</a>
	</li>

	<sec:authorize access="hasAnyAuthority('MEMBROS','ADMINXLABI')">
	<li>
		<a href="#/membro">
			<i class="fa fa-users"></i>
			<span>Membros</span> 
		</a>
	</li>
	</sec:authorize>

	<sec:authorize access="hasAnyAuthority('CARGOS','ADMINXLABI')">
	<li>
		<a href="#/cargo">
			<i class="fa fa-book"></i>
			<span>Cargos</span> 
		</a>
	</li>
	</sec:authorize>

	<sec:authorize access="hasAnyAuthority('CONTEUDO','ADMINXLABI')">
	<li>
		<a href="#/conteudo">
			<i class="fa fa-folder"></i>
			<span>Conteúdo</span> 
		</a>
	</li>
	</sec:authorize>

	<sec:authorize access="hasAnyAuthority('COMENTARIOS','ADMINXLABI')">
	<li>
		<a href="#/comentario">
			<i class="fa fa-comment"></i>
			<span>Comentários</span> 
		</a>
	</li>
	</sec:authorize>

	<sec:authorize access="hasAnyAuthority('CELULAS','ADMINXLABI')">
	<li>
		<a href="#/celula">
			<i class="fa fa-sitemap"></i>
			<span>Células</span> 
		</a>
	</li>
	</sec:authorize>
	
	<sec:authorize access="hasAnyAuthority('BANNERS','ADMINXLABI')">
	<li>
		<a href="#/banner">
			<i class="fa fa-users"></i>
			<span>Banners</span> 
		</a>
	</li>
	</sec:authorize>

	<sec:authorize access="hasAnyAuthority('CONTATO','ADMINXLABI')">
	<li>
		<a href="#/contato">
			<i class="fa fa-folder"></i>
			<span>Contatos</span> 
		</a>
	</li>
	</sec:authorize>

	<sec:authorize access="hasAnyAuthority('EVENTO','ADMINXLABI')">
	<li>
		<a href="#/evento">
			<i class="fa fa-calendar"></i>
			<span>Eventos</span> 
		</a>
	</li>
	</sec:authorize>

	
	
	<sec:authorize access="hasAnyAuthority('ADMINXLABI')">
	<li>
		<a href="" class="auto">      
			<span class="pull-right text-muted">
				<i class="fa fa-fw fa-angle-right text"></i>
				<i class="fa fa-fw fa-angle-down text-active"></i>
			</span>
			<i class="glyphicon glyphicon-th"></i>
			<span>Config Conteúdo</span>
		</a>
		<ul class="nav nav-sub dk">
			
			<li>
				<a href="#/categoria">
					<i class="fa fa-tag"></i>
					<span>Categoria</span> 
				</a>
			</li>



			<li>
				<a href="#/conteudotipo">
					<i class="fa fa-tag"></i>
					<span>Conteúdo Tipo</span>
				</a>
			</li>
		</ul>
	</li>
	

	<li>
		<a href="" class="auto">      
			<span class="pull-right text-muted">
				<i class="fa fa-fw fa-angle-right text"></i>
				<i class="fa fa-fw fa-angle-down text-active"></i>
			</span>
			<i class="glyphicon glyphicon-th"></i>
			<span>Cadastros Gerais</span>
		</a>
		<ul class="nav nav-sub dk">

			<li>
				<a href="#/configuracaocampo/edit/alterar">
					<span>Configurações</span> 
				</a>
			</li>

		

			<li>
				<a href="#/estado">
					<span>Estado</span> 
				</a>
			</li>

			<li>
				<a href="#/cidade">
					<span>Cidade</span> 
				</a>
			</li>

			<li>
				<a href="#/bairro">
					<span>Bairro</span> 
				</a>
			</li>

		</ul>
	</li>

	<li>
		<a href="" class="auto">      
			<span class="pull-right text-muted">
				<i class="fa fa-fw fa-angle-right text"></i>
				<i class="fa fa-fw fa-angle-down text-active"></i>
			</span>
			<i class="fa fa-gear"></i>
			<span>Administração</span>
		</a>
		<ul class="nav nav-sub dk">

			<li>
				<a href="#/usuario">
					<span>Usuários</span> 
				</a>
			</li>

			<li>
				<a href="#/role">
					<span>Permissões</span> 
				</a>
			</li>

			

			<li>
				<a href="#/contratante">
					<span>Contratantes</span> 
				</a>
			</li>
			<li>
				<a href="#/campoconfiguracao">

					<span>Campos Configuração</span> 
				</a>
			</li>
			

		</ul>
	</li>	
	</sec:authorize>


	
</ul>