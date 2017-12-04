
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="panel panel-default">
  <div class="panel-heading font-bold"><span rv-text="titulo"> </span></div>
  <div class="panel-body">
    <form role="form" onsubmit="return false;">

      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Título:</label> 
          <input class="form-control " data-type="varchar" placeholder="Titulo"  type="text" rv-value="titulo" name="titulo">
        </div>
      </div>

      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Subtítulo:</label> 
          <input class="form-control " data-type="varchar" placeholder="Subtitulo"  type="text" rv-value="subtitulo" name="subtitulo">
        </div>
      </div>
      
      <div class="row">
        <div class="col-sm-6 form-group">
          <label>URL:</label> 
          <input class="form-control " data-type="varchar" placeholder="Url"  type="text" rv-value="url" name="url">
        </div>
        <div class="col-sm-6 form-group">
          <label class="control-label">Conteúdo Tipo: </label>
          <select name="conteudotipo"  id="conteudotipo" class="form-control" 
          data-select="conteudotipo/listAll" 
          data-value="id" 
          data-desc="tipo"
          placeholder="Conteúdo Tipo"
          rv-value="conteudotipo">
        </select>
      </div>
    </div>

    <div class="row">
      <div class="col-sm-12 form-group">
        <label class="control-label">Categorias:</label>
        <select rv-value="categorias" multiple="multiple" class="form-control" name="categorias"
        data-select="categoria/list"
        data-value="id" 
        data-desc="nome">
        </select>
      </div>
    </div>

    <div class="row">
      <div class="col-sm-12 form-group">
        <label>Conteúdo:</label> 
        <textarea name="conteudo" rv-value="conteudo" rows="20" id="conteudo" class="form-control"></textarea>
      </div>
    </div>


    <hr />
    <div class="clearfix"></div>
    <h3>Imagens</h3>
    <div id="anexocontroller"></div>

    <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
    <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>

  </form>
</div>
</div>
