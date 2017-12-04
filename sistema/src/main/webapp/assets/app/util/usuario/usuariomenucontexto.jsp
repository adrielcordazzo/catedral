
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col w-md bg-white-only b-l bg-auto no-border-xs contexto">
  <div class="nav-tabs-alt">
    <ul class="nav nav-tabs" role="tablist">
      <li id="tabAcoes" class="active"><a href="#acoes" data-toggle="tab"><i class="fa fa-ellipsis-v text-md text-muted wrapper-sm"></i></a></li>
      <li id="tabPesquisa"><a href="#pesquisa" data-toggle="tab"><i class="glyphicon glyphicon-search text-md text-muted wrapper-sm"></i></a></li>
      <li id="tabEdicao" class="hidden"><a href="#edicao" data-toggle="tab"><i class="fa fa-edit text-md text-muted wrapper-sm"></i></a></li>
    </ul>
  </div>
  <div class="tab-content">
    <div class="tab-pane active" id="acoes">
      <div class="">
        <ul class="list-group no-bg no-borders pull-in wrapper-md">
          <li class="list-group-item">
            <a data-action="inserir" class="btn btn-primary btn-block">Inserir</a>
          </li>
        </ul>

        <ul class="nav">
          <li>
            <a data-action="deletarSelecionados">Excluir selecionados</a>
          </li>
        </ul>
      </div>
    </div>
    <div class="tab-pane" id="pesquisa">
      <div class="wrapper-md">

      </div>
    </div>

    <div class="tab-pane" id="edicao">
     <ul class="nav">
          <li>
            <a data-action="deletarSelecionados">Excluir selecionados</a>
          </li>
        </ul>
    </div>

  </div>
  <div class="padder-md">      

  </div>

</div>