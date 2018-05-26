
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



      
      <div class="row">
        <div class="col-sm-11 form-group">
          <label>Membro:</label> 
          <select name="membro"  id="membro" class="form-control" 
            data-select="membro/list"
            autocomplete="true" 
            data-value="id" 
            data-desc="nome"
            placeholder="Membro">
          </select>
        </div>
        <div class="col-sm-1 pull-right">
    		<a data-action="remove" class="btn btn-danger" style="margin-top: 20px;"><i class="fa fa-trash"></i></a>
  		</div>
      </div>
      

