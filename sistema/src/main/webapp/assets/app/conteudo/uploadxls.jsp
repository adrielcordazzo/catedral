
  
  <%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <div class="panel panel-default">
  <div class="panel-heading font-bold"><span rv-text="titulo"> </span></div>
  <div class="panel-body">
  <form class="form-horizontal" id="basicForm" onsubmit="return false;">
    <div class="portlet light ">
      <div class="portlet-title">
        <div class="caption caption-md">
          <i class="icon-bar-chart theme-font hide"></i>
          <span class="caption-subject theme-font bold uppercase">Ordens Load</span>
        </div>
       
      </div>
      <div class="portlet-body">
        
<div class="form-group">
  <input type="text"  class="form-control" name="url" id="url" value="imovel/upload">
      <label class="col-sm-3 control-label">Selecione o arquivo: <span class="asterisk">*</span></label>
      <div class="col-sm-9">
        <input type="file" placeholder="file" class="form-control" name="file" id="file">
      </div>
    </div>
 

     </div>
     <div class="portlet-footer">
      <div class="row">
        <div class="col-sm-9 col-sm-offset-3">
          <button class="btn btn-primary"  data-action="save">Carregar</button>
        </div>
      </div>
    </div>
  </div>
</form>
</div>
</div>


