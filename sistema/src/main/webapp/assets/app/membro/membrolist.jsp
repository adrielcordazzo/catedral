
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="hbox hbox-auto-xs hbox-auto-sm" >
  <div class="col">
    <div class="bg-light lter b-b wrapper">
      <div class="row">
        <div class="col-sm-12 ">
          <h1 class="m-n font-thin h3 text-black pull-left">Membro</h1>
          <a data-action="inserir" class="btn btn-primary pull-right">Inserir</a>
          <!--a data-action="uploadMembros" class="btn btn-success pull-right">Importar Planilha</a-->

        </div>
      </div>
    </div>
    <div class="wrapper-md">
      <div id="formulario"></div>
    <div id="lista" class="panel panel-default">
      <div class="panel-heading">
        <div class="input-group m-b">
          <span class="input-group-addon"><i class="fa fa-search"></i></span> 
          <input class="form-control input-lg busca" type="text" placeholder="Busca"> 
        </div>
        <div class="row">
          <div class="col-sm-2 form-group">
            <label class="control-label">Aniversário: </label>
            <select name="aniversario"  id="aniversario" class="form-control selectfixo" rv-value="aniversario">
                <option value="">Selecione</option>
                <option value="1">Janeiro</option>
                <option value="2">Fevereiro</option>
                <option value="3">Março</option>
                <option value="4">Abril</option>
                <option value="5">Maio</option>
                <option value="6">Junho</option>
                <option value="7">Julho</option>
                <option value="8">Agosto</option>
                <option value="9">Setembro</option>
                <option value="10">Outubro</option>
                <option value="11">Novembro</option>
                <option value="12">Dezembro</option>
            </select>
          </div>
          <div class="col-sm-2 form-group">
            <label class="control-label">Cargo: </label>
            <select name="cargo"  id="cargo" class="form-control" 
              data-select="cargo/listAll" 
              data-value="id" 
              data-desc="cargo"
              selecione="true"
              rv-value="cargo"
              placeholder="cargo">
            </select>
          </div>
          <div class="col-sm-2 form-group">
            <label class="control-label">Foto: </label>
            <select name="foto"  id="foto" class="form-control selectfixo" rv-value="foto" >
                <option value="">Selecione</option>
                <option value="1">Com Foto</option>
                <option value="2">Sem Foto</option>
            </select>
          </div>
          <div class="col-sm-2 form-group">
            <label class="control-label">Ficha: </label>
            <select name="ficha"  id="ficha" class="form-control selectfixo" rv-value="ficha">
                <option value="">Selecione</option>
                <option value="1">Com Ficha</option>
                <option value="2">Sem Ficha</option>
            </select>
          </div>
          <div class="col-sm-2 form-group">
            <label class="control-label">Ativo: </label>
            <select name="ativo"  id="ativo" class="form-control selectfixo" rv-value="ativo">
                <option value="1">Ativo</option>
                <option value="0">Inativo</option>
            </select>
          </div>
          <div class="col-sm-2 form-group">
            <h4 class="m0">Total Registros <span class="totalregistros"></span></h4>
          </div>
        </div>
      </div>
      <div class="table-responsive">
        <table class="table table-striped b-t b-light">
          <thead>
            <tr>
              
              <th>Foto</th>
              <th>Ficha</th>
              <th>Nome</th>
              <th>E-mail</th> 
              <th>Telefone</th>
              <th>Data Nascimento</th>  
              <th>Cargos</th>  
              <th style="width: 120px;"></th>
            </tr>
          </thead>
          <tbody>
            <tr class="model">
              <td class="m-foto" style="width: 150px;"></td>
              <td class="m-numeroficha">{numeroficha}</td>
              <td class="m-nome">{nome}</td>
              <td class="m-nome">{email}</td>
              <td class="m-nome">{telefone}</td>
              <td class="m-nome">{datanascimento}</td>
              <td class="m-cargos">xxxx</td>
              <td>
                <a data-action="edit" class="btn btn-primary"><i class="fa fa-edit"></i></a>
                <a data-action="delete" class="btn btn-danger"><i class="fa fa-trash"></i></a>
              </td>
            </tr>           
          </tbody>
        </table>
      </div>
      <footer class="panel-footer">
        <div class="row">
            <div class="col-sm-4 text-right text-center-xs pull-right">
              <ul id="pagination" class="pagination pagination-sm m-t-none m-b-none">
              </ul>
            </div>
          </div>
      </footer>
    </div>
  </div>
  </div>
</div>