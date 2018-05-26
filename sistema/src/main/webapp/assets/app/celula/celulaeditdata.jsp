
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
          <label>Dia:</label> 
          <input class="form-control " placeholder="Dia"  type="text" rv-value="dia" name="dia">
        </div>
        <div class="col-sm-6 form-group">
          <label>Hora:</label> 
          <input class="form-control " placeholder="Hora"  type="text" rv-value="hora" name="hora">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Descrição:</label> 
          <input class="form-control " placeholder="Descrição"  type="text" rv-value="descricao" name="descricao">
        </div>
      </div>

      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Endereço:</label> 
          <input class="form-control " placeholder="Endereço"  type="text" rv-value="endereco" name="endereco">
        </div>
      </div>

      <div class="row">
        <div class="col-sm-6 form-group">
          <label>Latitude:</label> 
          <input class="form-control " placeholder="Latitude"  type="text" rv-value="latitude" name="latitude">
        </div>
        <div class="col-sm-6 form-group">
          <label>Longitude:</label> 
          <input class="form-control " placeholder="Longitude"  type="text" rv-value="longitude" name="longitude">
        </div>
      </div>
      
      <hr />
      <div class="clearfix"></div>
      <h3>Imagens</h3>
      <div id="anexocontroller"></div>
       
      
      <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
      <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>
      
    </form>
  </div>
</div>
