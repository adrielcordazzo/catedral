
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="panel panel-default">
  <div class="panel-heading font-bold"><span rv-text="comodidade"> </span></div>
  <div class="panel-body">
    <form role="form" onsubmit="return false;">

      <div class="row">
        <div class="col-sm-5 form-group">
          <label>Campo:</label> 
          <input class="form-control " data-type="varchar" placeholder="Campo"  type="text" rv-value="campo" name="campo">
        </div>

        <div class="col-sm-3 form-group">
          <label>Variável:</label> 
          <input class="form-control " data-type="varchar" placeholder="Variável"  type="text" rv-value="variavel" name="variavel">
        </div>

         <div class="col-sm-2 form-group">
         <label>É Obrigatório:</label> <br>
         <label class="i-switch i-switch-lg bg-dark m-t-xs m-r">
          <input type="checkbox" name="obrigatorio" rv-checked="obrigatorio"  value="1">
          <i></i>
        </label>
      </div>

        <div class="col-sm-2 form-group">
         <label>É Texto:</label> <br>
         <label class="i-switch i-switch-lg bg-dark m-t-xs m-r">
          <input type="checkbox" name="tipocampo" rv-checked="tipocampo" checked="checked"  value="1">
          <i></i>
        </label>
      </div>
    </div>
    

  

  <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
  <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>

</form>
</div>
</div>
