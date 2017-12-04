
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="panel panel-default">
  <div class="panel-heading font-bold"><span>{nome}</span></div>
  <div class="panel-body">
    <form role="form" onsubmit="return false">

      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Nome:</label> 
          <input class="form-control" placeholder="Nome" type="text" rv-value="nome" name="nome">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-6 form-group">
          <label>E-mail:</label> 
          <input class="form-control" placeholder="E-mail" type="text" rv-value="email" name="email"> 
        </div>

        <div class="col-sm-6 form-group">
          <label>Senha: <small class="senhaAviso"></small></label> 
          <input class="form-control" placeholder="Senha" type="password" rv-value="novasenha" name="novasenha">
        </div>
      </div>


      <div class="row">
        <div class="col-sm-4 form-group">
          <label>Assinatura:</label>
          <input class="form-control" placeholder="Assinatura" type="text" rv-value="assinatura" name="assinatura">
        </div>

        <div class="col-sm-4 form-group">
          <label>Código do Corretor</label> 
          <input class="form-control" placeholder="Código do Corretor" type="text" rv-value="codigocorretor" name="codigocorretor"> 
        </div>
        
        <div class="col-sm-4 form-group">
          <label>Telefone</label> 
          <input class="form-control" placeholder="Telefone" type="text" rv-value="telefone" name="telefone"> 
        </div>

       

        </div>

    
       <div class="row">
        <div class="col-sm-12 form-group">
          <label class="control-label">Permissões:</label>
          <select rv-value="roles" multiple="multiple" class="form-control" name="roles" id="roles"
          data-value="id" 
          data-desc="role">
        </select>
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

