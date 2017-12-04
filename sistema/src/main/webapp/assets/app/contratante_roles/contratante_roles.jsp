
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<aside id="aside" class="app-aside hidden-xs">
  <div id="contexto" class="aside-wrap" >
    <jsp:include page="contratante_rolesmenucontexto.jsp"/>
  </div>
</aside>
<div id="content" class="app-content" role="main">
  <div class="app-content-body ">
    <jsp:include page="contratante_roleslist.jsp"/>
  </div>
</div>