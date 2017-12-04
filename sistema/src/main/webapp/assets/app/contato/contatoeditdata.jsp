
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="panel panel-default">
  <div class="panel-heading font-bold"><span rv-text="nome"> </span></div>
  <div class="panel-body">
    <form role="form" onsubmit="return false;">

      <div id="imovel"></div>

      <div class="row">
        <div class="col-sm-6 form-group">
          <label>Nome:</label> 
          <input class="form-control " data-type="varchar" placeholder="Nome"  type="text" rv-value="nome" name="nome">
        </div>

        <div class="col-sm-6 form-group">
          <label class="control-label">Tipo de Contato: </label>
          <select name="contatotipo"  id="contatotipo" class="form-control" 
          data-select="contatotipo/listAll" 
          data-value="id" 
          data-desc="contatotipo"
          placeholder="Contatotipo"
          rv-value="contatotipo">
        </select>
      </div>
    </div> 
    <div class="row">
      <div class="col-sm-6 form-group">
        <label>Email:</label> 
        <input class="form-control " data-type="varchar" placeholder="Email"  type="text" rv-value="email" name="email">
      </div>
      
      <div class="col-sm-6 form-group">
        <label>Telefone:</label> 
        <input class="form-control " data-type="varchar" placeholder="Telefone"  type="text" rv-value="telefone" name="telefone">
      </div>
    </div>
    <div class="row">
      <div class="col-sm-12 form-group">
        <label>Texto:</label> 
        <textarea class="form-control " data-type="varchar" placeholder="Texto"  type="text" rv-value="texto" name="texto" rows="10"></textarea>
      </div>
    </div>


    <hr />
    <div class="clearfix"></div>
    <h3>Imagens</h3>
    <div class="pastasController"></div> <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
    <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>

  </form>
</div>
</div>
