
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="panel panel-default">
  <div class="panel-heading font-bold"><span rv-text="tipo"> </span></div>
  <div class="panel-body">
    <form role="form" onsubmit="return false;">
      
      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Tipo:</label> 
          <input class="form-control " data-type="varchar" placeholder="Tipo"  type="text" rv-value="tipo" name="tipo">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Página:</label> 
          <input class="form-control " data-type="varchar" placeholder="Página"  type="text" rv-value="pagina" name="pagina">
        </div>
      </div>
      
       <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
      <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>
      
    </form>
  </div>
</div>
