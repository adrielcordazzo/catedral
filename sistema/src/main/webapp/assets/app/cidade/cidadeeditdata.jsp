
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="panel panel-default">
  <div class="panel-heading font-bold"><span rv-text="nome"> </span></div>
  <div class="panel-body">
    <form role="form" onsubmit="return false;">
      
      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Nome:</label> 
          <input class="form-control " data-type="varchar" placeholder="Nome"  type="text" rv-value="nome" name="nome">
        </div>
      </div>
      
    		<div class="row">
			    <div class="col-sm-12 form-group">
			      <label class="control-label">Estado: </label>
			      <select name="estado"  id="estado" class="form-control" 
			      data-select="estado/listAll" 
			      data-value="id" 
			      data-desc="nome"
			      placeholder="Estado"
			      rv-value="estado">
			    </select>
			  </div>
			 </div>  <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
      <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>
      
    </form>
  </div>
</div>
