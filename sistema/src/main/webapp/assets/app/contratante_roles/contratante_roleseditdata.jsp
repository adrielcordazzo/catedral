
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="panel panel-default">
  <div class="panel-heading font-bold"><span rv-text=""> </span></div>
  <div class="panel-body">
    <form role="form" onsubmit="return false;">
      
      
    		<div class="row">
			    <div class="col-sm-12 form-group">
			      <label class="control-label">Role: </label>
			      <select name="role"  id="role" class="form-control" 
			      data-select="role/listAll" 
			      data-value="id" 
			      data-desc="role.id"
			      placeholder="Role"
			      rv-value="role.id">
			    </select>
			  </div>
			 </div>  <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
      <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>
      
    </form>
  </div>
</div>
