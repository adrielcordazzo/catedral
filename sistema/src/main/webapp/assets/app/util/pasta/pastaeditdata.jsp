
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="panel panel-default">
  <div class="panel-heading font-bold">Pasta - <span rv-text="criado"> </span></div>
  <div class="panel-body">
    <form role="form">
      
      <div class="row">
        <div class="col-sm-6 form-group">
          <label>Criado:</label> 
          <input class="form-control data" placeholder="Criado" type="text" rv-value="criado">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-6 form-group">
          <label>Atualizado:</label> 
          <input class="form-control data" placeholder="Atualizado" type="text" rv-value="atualizado">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-6 form-group">
          <label>Inativo:</label> 
          <input class="form-control data" placeholder="Inativo" type="text" rv-value="inativo">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-6 form-group">
          <label>Excluido:</label> 
          <input class="form-control data" placeholder="Excluido" type="text" rv-value="excluido">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-6 form-group">
          <label>Idexterno:</label> 
          <input class="form-control data" placeholder="Idexterno" type="text" rv-value="idexterno">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-6 form-group">
          <label>Pasta:</label> 
          <input class="form-control data" placeholder="Pasta" type="text" rv-value="pasta">
        </div>
      </div>
      <div class="row">
    <div class="col-sm-12 form-group">
      <label class="control-label">Contratante: </label>
      <select name="contratante"  id="contratante" class="form-control" 
      data-select="contratante/listAll" 
      data-value="id" 
      data-desc="contratante_id"
      json="true"
      placeholder="Contratante"
      rv-value="contratante_id">
    </select>
  </div>
  
</div> <div class="row">
    <div class="col-sm-12 form-group">
      <label class="control-label">Usuario: </label>
      <select name="usuario"  id="usuario" class="form-control" 
      data-select="usuario/listAll" 
      data-value="id" 
      data-desc="usuario_id"
      json="true"
      placeholder="Usuario"
      rv-value="usuario_id">
    </select>
  </div>
  
</div>  
      
      <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
      <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>
      
    </form>
  </div>
</div>
