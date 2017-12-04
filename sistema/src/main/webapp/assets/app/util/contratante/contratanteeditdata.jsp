
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="panel panel-default">
  <div class="panel-heading font-bold"><span rv-text="nome"> </span></div>
  <div class="panel-body">
    <form role="form">
      
      
      <div class="row">
        <div class="col-sm-8 form-group">
          <label>Nome:</label> 
          <input class="form-control " placeholder="Nome"  type="text" rv-value="nome" name="nome">
        </div>
        <div class="col-sm-4 form-group">
          <label>Data Vencimento:</label> 
          <input class="form-control " placeholder="Datavendimento"  type="date" rv-value="datavendimento" name="datavendimento">
        </div>
      </div>
      
      <div class="row">
        <div class="col-sm-6 form-group">
          <label>Razão Social:</label> 
          <input class="form-control " placeholder="Razão Social"  type="text" rv-value="razaosocial" name="razaosocial">
        </div>

        <div class="col-sm-6 form-group">
          <label>Responsável:</label> 
          <input class="form-control " placeholder="Responsável"  type="text" rv-value="responsavel" name="responsavel">
        </div>
      </div>
      
      <div class="row">
        <div class="col-sm-4 form-group">
          <label>Fone:</label> 
          <input class="form-control " placeholder="Fone"  type="text" rv-value="fone" name="fone">
        </div>
      
        <div class="col-sm-4 form-group">
          <label>Cnpj:</label> 
          <input class="form-control " placeholder="Cnpj"  type="text" rv-value="cnpj" name="cnpj">
        </div>
      
        <div class="col-sm-4 form-group">
          <label>Email:</label> 
          <input class="form-control " placeholder="Email"  type="text" rv-value="email" name="email">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 form-group">
          <label class="control-label">Permissões:</label>
          <select rv-value="roles" multiple="multiple" class="form-control" name="roles"
          data-select="role/listAll"
          data-value="id" 
          data-desc="role">
        </select>
      </div>
    </div>
    <div class="row">
      <div class="col-sm-6 form-group">
        <label>URL Marca Dágua:</label> 
        <input class="form-control " placeholder="URL Marca Dágua"  type="text" rv-value="marcadagua" name="marcadagua">
      </div>
      <div class="col-sm-6 form-group">
        <label>URL Sem Foto:</label> 
        <input class="form-control " placeholder="URL Sem Foto"  type="text" rv-value="semfoto" name="semfoto">
      </div>
    
    </div>
    <div class="row">
      <div class="col-sm-12 form-group">
        <label>Observacao:</label> 
        <textarea class="form-control " placeholder="Observacao"  type="text" rv-value="observacao" name="observacao"></textarea>
      </div>
    </div>
       
      
      <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
      <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>
      
    </form>
  </div>
</div>
