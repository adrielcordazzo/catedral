
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style type="text/css">

  input[type="file"] {
    display: none;
}
</style>

<div class="panel panel-default">
  <div class="panel-heading font-bold"><span rv-text="nome"> </span></div>
  <div class="panel-body">
    <form role="form" onsubmit="return false;">


      <div class="row">
        <div class="col-sm-12 form-group">
          <label>Nome:</label> 
          <input class="form-control " data-type="varchar" placeholder="Nome" rv-value="nome" name="nome" type="text">
        </div>
      </div>

      <h3>Contato</h3>

      <div class="row">
        <div class="col-sm-4 form-group">
          <label>E-mail:</label> 
          <input class="form-control " data-type="varchar" placeholder="E-mail" rv-value="email" name="email" type="text">
        </div>
        <div class="col-sm-4 form-group">
          <label>Telefone:</label> 
          <input class="form-control " data-type="varchar" placeholder="Telefone" rv-value="telefone" name="telefone" type="text">
        </div>
        <div class="col-sm-4 form-group">
          <label>CEP:</label> 
          <input class="form-control " data-type="varchar" placeholder="CEP" rv-value="cep" name="cep" type="text">
        </div>
        <div class="col-sm-10 form-group">
          <label>Endereço:</label> 
          <input class="form-control " data-type="varchar" placeholder="Endereço" rv-value="endereco" name="endereco" type="text">
        </div>
        <div class="col-sm-4 form-group">
          <label class="control-label">Estado: </label>
          <select name="estado"  id="estado" class="form-control" 
            data-select="estado/listAll" 
            data-value="id" 
            data-desc="nome"
            placeholder="estado"
            rv-value="estado">
          </select>
        </div>
        <div class="col-sm-4 form-group">
          <label class="control-label">Cidade: </label>
          <select name="cidade"  id="cidade" class="form-control" 
            data-select="cidade/listAll" 
            data-value="id" 
            data-desc="nome"
            placeholder="Cidade"
            rv-value="cidade">
          </select>
        </div>
        <div class="col-sm-4 form-group">
          <label class="control-label">Bairro: </label>
          <select name="bairro"  id="bairro" class="form-control" 
            data-select="bairro/listAll" 
            data-value="id" 
            data-desc="nome"
            placeholder="bairro"
            rv-value="bairro">
          </select>
        </div>
      </div>

      <h3>Dados pessoais</h3>

      <div class="row">
        <div class="col-sm-3 form-group">
          <label>Data de Nascimento:</label> 
          <input class="form-control data hasDatepicker" data-type="date" placeholder="Data de Nascimento" rv-value="datanascimento" name="datanascimento" id="dp1512348234405" maxlength="10" type="text">
        </div>
        <div class="col-sm-3 form-group">
          <label>CPF:</label> 
          <input class="form-control " data-type="varchar" placeholder="CPF" rv-value="cpf" name="cpf" type="text">
        </div>
        
        <div class="col-sm-3 form-group">
          <label>RG:</label> 
          <input class="form-control " data-type="varchar" placeholder="RG" rv-value="rg" name="rg" type="text">
        </div>

        <div class="col-sm-3 form-group">
          <label>Estado Civil:</label> 
          <select name="estadocivil"  id="estadocivil" class="form-control" 
            data-type="simples"  rv-value="estadocivil">
            <option value="1">Solteiro</option>
            <option value="2">Casado</option>
            <option value="3">Viúvo</option>
            <option value="4">Separado</option>
          </select>
        </div>

        <div class="col-sm-6 form-group">
          <label>Escolaridade:</label> 
          <input class="form-control " data-type="varchar" placeholder="Escolaridade" rv-value="escolaridade" name="escolaridade" type="text">
        </div>
        <div class="col-sm-6 form-group">
          <label>Profissão:</label> 
          <input class="form-control " data-type="varchar" placeholder="Profissão" rv-value="profissao" name="profissao" type="text">
        </div>

        <div class="col-sm-6 form-group">
          <label>Pai:</label> 
          <input class="form-control " data-type="varchar" placeholder="Pai" rv-value="pai" name="pai" type="text">
        </div>
        <div class="col-sm-6 form-group">
          <label>Mãe:</label> 
          <input class="form-control " data-type="varchar" placeholder="Mãe" rv-value="mae" name="mae" type="text">
        </div>
        <div class="col-sm-6 form-group">
          <label>Cônjuge:</label> 
          <input class="form-control " data-type="varchar" placeholder="Cônjuge" rv-value="conjuge" name="conjuge" type="text">
        </div>
        <div class="col-sm-6 form-group">
          <label>Filhos:</label> 
          <input class="form-control " data-type="varchar" placeholder="Filhos" rv-value="filhos" name="filhos" type="text">
        </div>

      </div>

      <h3>Dados ministeriais</h3>

      <div class="row">
        <div class="col-sm-3 form-group">
          <label>Data de Batismo:</label> 
          <input class="form-control " data-type="varchar data" placeholder="Data de Batismo" rv-value="databatismo" name="databatismo" type="text">
        </div>
        <div class="col-sm-3 form-group">
          <label>Nro da Ficha:</label> 
          <input class="form-control " data-type="varchar data" placeholder="Nro da Ficha" rv-value="numeroficha" name="numeroficha" type="text">
        </div>
        <div class="col-sm-3 form-group">
          <label>Recebido Por:</label> 
          <select name="recebidopor"  id="recebidopor" class="form-control" 
            data-type="simples"  rv-value="recebidopor">
            <option value="batismo">Batismo</option>
            <option value="transferencia">Transferência</option>
          </select>
        </div>
        <div class="col-sm-3 form-group">
          <label>Batizado Esp. Santo:</label> 
          <select name="batizadoespiritosanto"  id="batizadoespiritosanto" class="form-control" 
            data-type="simples"  rv-value="batizadoespiritosanto">
            <option value="sim">Sim</option>
            <option value="não">Não</option>
          </select>
        </div>
      </div>  
        
        

        

      <h3>Observação</h3>
      <div class="row">
        <div class="col-sm-12 form-group">
          <textarea class="form-control" data-type="varchar" placeholder="Obs" rv-value="obs" name="obs"></textarea>
        </div>
      </div>

      <h3>Cargos</h3>
      <div id="cargos"></div>

      <h3>Imagem</h3>
      <div class="row">
        <div class="col-sm-6 form-group">
          <label for="selectfoto" class="btn btn-success">
              Selecionar Foto
          </label>
          <input id="selectfoto" name="selectfoto" type="file">
          <a data-action="abrirCamera" class="btn btn-success">Abrir Câmera</a> 
          <a data-action="tirarFoto" class="btn btn-primary" style="display: none;">Tirar Foto</a>    <br>
          <div id="camera-container">
          </div>
          <div id="foto"></div>
        </div>
      </div>
    


  

  <a data-action="save" class="btn btn-lg btn-primary">Salvar</a>
  <a data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>

</form>
</div>
</div>
