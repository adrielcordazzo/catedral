
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="panel panel-default">
  <div class="panel-heading font-bold"><span rv-text="titulo"> </span></div>
  <div class="panel-body">
    <form role="form" onsubmit="return false;">
      
      <div class="row">
        <div class="col-sm-8 form-group">
          <label>Título:</label> 
          <input class="form-control " data-type="varchar" placeholder="Título"  type="text" rv-value="titulo" name="titulo">
        </div>
        <div class="col-sm-4 form-group">
          <label>Data:</label> 
          <input class="form-control " data-type="date" placeholder="Data"  type="text" rv-value="data" name="data">
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Descrição:</label> 
          <textarea name="descricao" rv-value="descricao" rows="4" id="descricao" class="form-control"></textarea>
        </div>
        <!-- div class="col-sm-12 form-group">
            <label class="control-label">Membro: </label>
            <select name="membro"  id="membro" class="form-control" 
            data-select="membro/list"
            autocomplete="true" 
            data-value="id" 
            data-desc="nome"
            placeholder="Membro">
          </select>
        </div>
      </div>
      <div class="table-responsive">
        <table class="table table-striped b-t b-light">
          <tbody>
            <tr class="model">
              <td class="m-nome"></td>
              <td width="40">
                <a data-action="delete" class="btn btn-danger"><i class="fa fa-trash"></i></a>
              </td>
            </tr>           
          </tbody>
        </table>
      </div-->
      
      <div id="membros"></div>


      <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
      <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>
      
    </form>
  </div>
</div>
