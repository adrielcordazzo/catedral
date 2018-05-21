
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="panel panel-default">
  <div class="panel-heading font-bold"><span rv-text="titulo"> </span></div>
  <div class="panel-body">
    <form role="form" onsubmit="return false;">
      
      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Titulo:</label> 
          <input class="form-control " data-type="varchar" placeholder="Titulo"  type="text" rv-value="titulo" name="titulo">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Texto 1:</label> 
          <input class="form-control " data-type="varchar" placeholder="Texto 1"  type="text" rv-value="texto1" name="texto1">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Texto 2:</label> 
          <input class="form-control " data-type="varchar" placeholder="Texto 2"  type="text" rv-value="texto2" name="texto2">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-8 form-group">
          <label>Link:</label> 
          <input class="form-control " data-type="varchar" placeholder="Link"  type="text" rv-value="link" name="link">
        </div>
        <div class="col-sm-4 form-group">
          <label>Lado Texto:</label> 
          <select name="ladotexto"  id="ladotexto" class="form-control" 
            data-type="simples"  rv-value="ladotexto">
            <option value="C">Centro</option>
            <option value="E">Esquerdo</option>
            <option value="D">Direito</option>
          </select>
        </div>
      </div>
      
          <hr />
          <div class="clearfix"></div>
          <h3>Imagens</h3>
          <div class="pastasController"></div> 

      <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
      <a  data-action="saveedit" class="btn btn-lg btn-success">Salvar e Continuar editando</a>
      <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>
      
    </form>
  </div>
</div>
