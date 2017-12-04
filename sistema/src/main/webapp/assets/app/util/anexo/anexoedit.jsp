
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



    <form role="form" onsubmit="return false;">
    	<div class="row">
        <div class="col-sm-8 form-group">
        	<label>Legenda:</label>
          	<input class="form-control " data-type="varchar" 
          	       placeholder="Legenda"  type="text" rv-value="legenda" name="legenda " id="legenda">
        </div>
        <div class="col-sm-4" >
        	<label>Alinhamento Máscara</label>
        	<select name="mascaraalign" rv-value="mascaraalign" id="mascaraalign">
        		<option value="c">Alinhado no centro</option>
        		<option value="td">Alinhado no canto superior direito</option>
        		<option value="te">Alinhado no canto superior esquerdo</option>
        		<option value="id">Alinhado no canto inferior direito</option>
        		<option value="ie">Alinhado no canto inferior esquerdo</option>
        	</select>
        </div>
    </div>
      
      <div class="row">
        <div class="col-sm-8 form-group">
          <img id="image-crop" src="picture.jpg">
        </div>
        

        <div class="col-sm-4 form-group">
        	<h4>Ferramentas</h4>
        	<div class="btn-group">
          <button type="button" class="btn btn-primary btn-block crop"  title="Crop">
            <span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="cropper.reset()">
              <span class="fa fa-crop">Crop</span>
            </span>
          </button>
          
          <button type="button" class="btn btn-primary btn-block removecrop" data-method="destroy" title="Destroy">
            <span class="docs-tooltip" data-toggle="tooltip" title="" data-original-title="cropper.destroy()">
              <span class="fa fa-power-off">Remove Crop</span>
            </span>
          </button>
        </div>

        </div>
      </div>
      <a  data-action="save" class="btn btn-lg btn-primary">Salvar</a>
      <a  data-action="cancelar" class="btn btn-lg btn-default">Cancelar</a>
      
    </form>

