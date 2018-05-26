
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="panel panel-default">
  <div class="panel-heading font-bold"><span rv-text="nome"> </span></div>
  <div class="panel-body">
    <form role="form" onclick="return false;">
      
      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Nome:</label> 
          <input class="form-control " placeholder="Nome"  type="text" rv-value="nome" name="nome">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-6 form-group">
          <label>Email:</label> 
          <input class="form-control " placeholder="Email"  type="text" rv-value="email" name="email">
        </div>
        <div class="col-sm-6 form-group">
          <label>Conteudo:</label> 
          <select name="conteudo"  id="conteudo" class="form-control" 
          data-select="conteudo/list" 
          autocomplete="true"
          data-value="id" 
          data-desc="titulo"
          placeholder="Conteúdo"
          rv-value="conteudo">
        </select>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Mensagem:</label> 
          <input class="form-control " placeholder="Mensagem"  type="text" rv-value="mensagem" name="mensagem">
        </div>
      </div>

       
      
      <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
      <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>
      
    </form>
  </div>
</div>
