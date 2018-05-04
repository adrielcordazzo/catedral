
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="hbox hbox-auto-xs hbox-auto-sm" >
  <div class="col">
    <div class="bg-light lter b-b wrapper">
      <div class="row">
        <div class="col-sm-12 ">
          <h1 class="m-n font-thin h3 text-black pull-left">Conteúdo</h1>
          <a data-action="inserir" class="btn btn-primary pull-right">Inserir</a>
          <a data-action="upload" class="btn btn-primary pull-right">Upload</a>
        </div>
      </div> 
    </div>  
    <div class="wrapper-md">
      <div id="formulario"></div>
    <div id="lista" class="panel panel-default">
      <div class="panel-heading">
        <div class="row">
              <!-- <div class="col-sm-12 form-group">
                <label class="control-label">Conteúdo Tipo: </label>
                <select name="conteudotipofilter"  id="conteudotipofilter" class="form-control" 
                data-select="conteudotipo/listAll" 
                data-value="id" 
                data-desc="tipo"
                json="true"
                placeholder="Conteúdo Tipo"
                rv-value="conteudotipo">
                </select>
              </div>-->
            </div> 
        <div class="input-group m-b">
          <span class="input-group-addon"><i class="fa fa-search"></i></span> 
          <input class="form-control input-lg busca" type="text" placeholder="Busca"> 
        </div>
      </div>
      <div class="table-responsive">
        <table class="table table-striped b-t b-light">
          <thead>
            <tr>
              <th style="width: 20px;"></th>  
              <th>Tipo</th>
              <th>Título</th>
              <th>Url</th> 
              <th style="width: 120px;"></th>
            </tr>
          </thead>
          <tbody>
            <tr class="model">
              <td class="m-id">
                <label class="i-checks m-b-none"> 
                <input type="checkbox" id="checkboxDefault" class="ckbox"> <i></i>
                </label>
              </td>
              <td class="m-tipo">{conteudotipo.tipo}</td>
              <td class="m-titulo">{titulo}</td>
              <td class="m-url">{url}</td>
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