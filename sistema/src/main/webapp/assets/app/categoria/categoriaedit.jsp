<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<aside id="aside" class="app-aside hidden-xs">
  <div id="contexto" class="aside-wrap" >
    <jsp:include page="categoriamenucontexto.jsp"/>
  </div>
</aside>

<div id="content" class="app-content" role="main">
  <div class="app-content-body ">

    <div class="hbox hbox-auto-xs hbox-auto-sm" >
      <div class="col">
        <div class="bg-light lter b-b wrapper">
          <div class="row">
            <div class="col-sm-6 col-xs-12">
              <h1 class="m-n font-thin h3 text-black">Categoria</h1>
            </div>
          </div>
        </div>
        <div class="wrapper-md">
          <div id="formulario">
           <jsp:include page="categoriaeditdata.jsp"/>
         </div>
       </div>
     </div>
   </div>
   
 </div>
</div>