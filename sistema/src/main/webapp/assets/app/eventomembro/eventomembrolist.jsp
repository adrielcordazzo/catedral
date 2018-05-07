
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="table-responsive">
  <table class="table table-striped b-t b-light">
    <thead>
      <tr>
        
        
        <th>Membro</th> 
        <th style="width: 120px;"></th>
      </tr>
    </thead>
    <tbody>
      <tr class="model">
        
        
        <td class="m-contatotipo">{membro.nome}</td>
        <td>
          <a data-action="edit" class="btn btn-primary"><i class="fa fa-edit"></i></a>
          <a data-action="delete" class="btn btn-danger"><i class="fa fa-trash"></i></a>
        </td>
      </tr>           
    </tbody>
  </table>
</div>
