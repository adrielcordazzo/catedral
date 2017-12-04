
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="panel panel-default">
  <div class="panel-heading font-bold">Users_roles - <span rv-text="role_id"> </span></div>
  <div class="panel-body">
    <form role="form">
      
      <div class="row">
        <div class="col-sm-6 form-group">
          <label>Id:</label> 
          <input class="form-control data" placeholder="Id" type="text" rv-value="id">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-6 form-group">
          <label>Role_id:</label> 
          <input class="form-control data" placeholder="Role_id" type="text" rv-value="role_id">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-6 form-group">
          <label>Usuario_id:</label> 
          <input class="form-control data" placeholder="Usuario_id" type="text" rv-value="usuario_id">
        </div>
      </div>
       
      
      <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
      <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>
      
    </form>
  </div>
</div>
