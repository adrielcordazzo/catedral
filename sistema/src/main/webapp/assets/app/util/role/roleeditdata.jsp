
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="panel panel-default">
  <div class="panel-heading font-bold"><span rv-text="role"> </span></div>
  <div class="panel-body">
    <form role="form">
      
      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Permissão:</label> 
          <input class="form-control data" placeholder="Permissão" type="text" rv-value="role">
        </div>
      </div>
       
      
      <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
      <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>
      
    </form>
  </div>
</div>
