
package br.com.xlabi.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xlabi.entity.Bairro;
import br.com.xlabi.entity.Cargo;
import br.com.xlabi.entity.Cidade;
import br.com.xlabi.entity.Estado;
import br.com.xlabi.entity.Membro;
import br.com.xlabi.entity.Membrocargo;
import br.com.xlabi.entity.geral.Pasta;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.geral.AbstractService;
import br.com.xlabi.service.geral.PastaService;

@Service
@Transactional
public class MembroService extends AbstractService<String, Membro> {
	
	@Autowired
	CidadeService cidadeServ;
	
	@Autowired
	BairroService bairroServ;
	
	@Autowired
	EstadoService estadoServ;
	
	@Autowired
	CargoService cargoServ;
	
	@Autowired
	PastaService pastaServ;

	public Membro save(Membro membro, SessaoUser sessao) {
		
		if (membro.getId() == null) {
			Pasta p = new Pasta();
			p.setPasta("Projeto");
			pastaServ.save(p, sessao);
			membro.setPasta(p);
		}

		setReferencias(membro, sessao);
		try {
			super.save(membro);
			
			return membro;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public Membro saveFicha(String id, String ficha, SessaoUser sessao) {
		
		Membro membro = this.get(id, sessao);
		if(membro != null) {
			if(ficha.equals(""))
				ficha = null;
			membro.setNumeroficha(ficha);
		}
		try {
			super.save(membro);
			
			return membro;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Membro membro = get(id, sessao);
			if (membro != null) {
				super.delete(membro);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Membro externalid(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("externalid", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Membro temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
	}

	public Integer count(SessaoUser sessao) {
		SimpleExpression restricao = null;
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		return super.count(restricao, ruser, rcontratante);
	}

	public Membro get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Membro temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		temp.setImagem(temp.getImagemmembro());
		return temp;
	}

	public Integer CountFk(String campo, String value, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq(campo, value);
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		return super.countFK(restricao, ruser, rcontratante);
	}

	public Result list(PaginateForm pages, SessaoUser sessao) {
		SimpleExpression restricao = null;
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		
		Criterion restricaoaniversario = null;
		Integer aniversario = pages.getCampos().indexOf("aniversario");
		if (aniversario > -1) {
			pages.getCampos().remove("aniversario");
			String mesaniversario = pages.getValues().get(aniversario);
			pages.getValues().remove(mesaniversario);
			if(mesaniversario.length() < 2) {
				mesaniversario = "0"+mesaniversario;
			}
			restricaoaniversario = Restrictions.sqlRestriction(" MONTH(datanascimento) = '"+mesaniversario+"' ");
		}
		
		SimpleExpression restricaocargo = null;
		Integer cargo = pages.getCampos().indexOf("cargo");
		if (cargo > -1) {
			pages.getCampos().remove("cargo");
			String cargoid = pages.getValues().get(cargo);
			pages.getValues().remove(cargoid);
			restricaocargo = Restrictions.eq("alias.cargo.id", cargoid);
			pages.setAlias("cargos");
		}
		
		Criterion restricaofoto = null;
		Integer indexfoto = pages.getCampos().indexOf("foto");
		if (indexfoto > -1) {
			pages.getCampos().remove("foto");
			String foto = pages.getValues().get(indexfoto);
			pages.getValues().remove(foto);
			if(foto.equals("1")) {
				restricaofoto = Restrictions.isNotNull("imagemmembro");
			}else {
				restricaofoto = Restrictions.isNull("imagemmembro");
			}
		}
		
		Criterion restricaoficha = null;
		Integer indexficha = pages.getCampos().indexOf("ficha");
		if (indexficha > -1) {
			pages.getCampos().remove("ficha");
			String ficha = pages.getValues().get(indexficha);
			pages.getValues().remove(ficha);
			if(ficha.equals("1")) {
				restricaoficha = Restrictions.isNotNull("numeroficha");
			}else {
				restricaoficha = Restrictions.isNull("numeroficha");
			}
		}
		
		Criterion restricaoativo = null;
		Integer indexativo = pages.getCampos().indexOf("ativo");
		if (indexativo > -1) {
			pages.getCampos().remove("ativo");
			String ativo = pages.getValues().get(indexativo);
			pages.getValues().remove(ativo);
			if(ativo.equals("1"))
				restricaoativo = Restrictions.eq("ativo",1);
			else
				restricaoativo = Restrictions.isNull("ativo");
		}
		
		Result result = this.listRestriction(pages, "nome", "nome", restricao, ruser, rcontratante, restricaoaniversario, restricaocargo, restricaofoto, restricaoficha, restricaoativo);
		inicializeList(result.getList());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Result listRestriction(PaginateForm pages, String order, String like, Criterion... restricoes) {

		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setMaxResults(pages.getMaxResult());
		if (pages.getOrder() == 0) {
			if (order != null)
				criteria.addOrder(Order.asc(order));
		} else {
			if (order != null)
				criteria.addOrder(Order.desc(order));
		}

		if (pages.getAlias() != null) {
			criteria.createAlias(pages.getAlias(), "alias");
		}

		criteria.setFirstResult((pages.getPagina() - 1) * pages.getMaxResult());

		if (pages.getSearch() != null) {
			Criterion nome = Restrictions.like("nome", "%" + pages.getSearch() + "%");
			Criterion ficha = Restrictions.like("numeroficha", "%" + pages.getSearch() + "%");

			criteria.add(Restrictions.or(nome, ficha));
		}

		if (restricoes != null) {
			for (Criterion r : restricoes) {
				if (r != null)
					criteria.add(r);
			}
		}

		if (pages.getCampos().size() > 0) {
			for (int i = 0; i <= pages.getCampos().size() - 1; i++) {
				try {
					String campo = pages.getCampos().get(i);
					String value = pages.getValues().get(i);
					if (value.equals("NULL") || value.equals("null")) {
						criteria.add(Restrictions.isNull(campo));
					} else if (value.equals("NOTNULL") || value.equals("notnull")) {
						criteria.add(Restrictions.isNotNull(campo));
					} else {
						criteria.add(Restrictions.eq(campo, value));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Result result = new Result();
		List<T> lista = null;
		try {
			lista = criteria.list();
			System.out.println("SQLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL "+ criteria.toString());
			Number qt = ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult());
			Integer totalResult = 0;
			if (qt != null)
				totalResult = qt.intValue();
			result.setList(lista);
			result.setCountResult(totalResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Membro> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Membro>) result.getList();
	}

	public Result listAll(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(Order.asc("nome"), rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Membro membro) {
		if (membro != null) {
			membro.getId();
			for(Membrocargo c : membro.getCargos()) {
				c.getId();
			}
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Membro) p);
			}
		}
	}

	public Membro synchronizeAndSave(Membro p, Membro vs) {

		super.save(p);
		return p;
	}
	
	public void deleteCargos(String id) {
		String hql = "delete from Membrocargo p " + " where p.membro.id=:id ";
		getSession().createQuery(hql).setString("id", id).executeUpdate();
	}

	public Membro setReferencias(Membro p, SessaoUser sessao) {
		p.setContratante(sessao.getContratante());
		p.setUsuario(sessao.getUsuario());
		
		if (p.getId() != null) {
			deleteCargos(p.getId());
		} 

		for (Membrocargo i : p.getCargos()) {
			i.setMembro(p);
		}
		
		if(p.getImagem() != null) {
			p.setImagemmembro(p.getImagem());
		}

		return p;
	}
	
	/**/
	
	public Result readXls(InputStream inputStream, String filename, SessaoUser sessao)
			throws IOException, InterruptedException {

		String excelFilePath = filename;
		Result result = new Result();

		Workbook workbook;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		List<Membro> membros = new ArrayList<Membro>();
		List<String> erros = new ArrayList<String>();
		List<String> logsucess = new ArrayList<String>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			Sheet sheet = workbook.getSheetAt(i);
			String planilhaName = sheet.getSheetName();
			List<Object> retorno = this.trataMembros(sheet, sessao, logsucess);

			membros.addAll((List<Membro>) retorno.get(0));
			erros.addAll((List<String>) retorno.get(1));
		}

		if (erros.size() > 0) {
			System.out.println("ERRRRRRORROOROROROR NA LISTA" + erros.size());
			for (String g : erros) {
				System.out.println(g);
				result.addError("Erro na linha", g);
			}
			return result;
		}

		for (Membro p : membros) {
			System.out.println(p.getNome() + " Insert" + p.getId());
			Thread.sleep(5);
			this.verificaMembro(p, sessao, logsucess);

		}
		result.addSuccessMessage("Importação realizada com sucesso");
		result.setList(logsucess);
		workbook.close();
		inputStream.close();
		return result;
	}
	
	public List<Object> trataMembros(Sheet sheet, SessaoUser sessao, List<String> log) {
		List<Object> retorno = new ArrayList<Object>();
		Iterator<Row> iterator = sheet.iterator();
		List<Membro> membros = new ArrayList<Membro>();
		List<String> erros = new ArrayList<String>();

		int linha = 0;
		while (iterator.hasNext()) {

			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			linha++;
			if (linha == 1) {
				continue;
			}
			int count = 0;
			List<String> itensMembro = new ArrayList<String>();
			Membro membro = new Membro();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				itensMembro.add(cell.toString());
				count++;
			}

			/*if (itensMembro.size() != 23) {

				erros.add("Quantidade de colunas inv�lida  Linha " + linha);

				break;
			}*/

			if (itensMembro.get(0).isEmpty() || itensMembro.get(1).isEmpty()) {
				continue;
			}

			membro.setAtivo(1);
			
			String nome = itensMembro.get(0);
			if(nome != null && !nome.equals(""))
				membro.setNome(nome); 
			System.out.println("NOME " + itensMembro.get(0));
			
			String email = itensMembro.get(1);
			if(email != null && !email.equals(""))
				membro.setEmail(email);
			System.out.println("EMAIL " + itensMembro.get(1));
			
			String telefone = itensMembro.get(2);
			if(telefone != null && !telefone.equals(""))
				membro.setTelefone(telefone);
			System.out.println("TELEFONE " + itensMembro.get(2));
			
			String endereco = itensMembro.get(3);
			if(endereco != null && !endereco.equals(""))
				membro.setEndereco(endereco);
			System.out.println("ENDEREÇO " + itensMembro.get(3));
			
			String cep = itensMembro.get(4);
			if(cep != null && !cep.equals(""))
				membro.setCep(cep);
			System.out.println("CEP " + itensMembro.get(4));
			
			Estado estado = estadoServ.getUf(itensMembro.get(5).trim(), sessao);
			if(estado != null) {
				membro.setEstado(estado);
				System.out.println("ESTADO " + estado.getUf());
			}
			//System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBUSCAR CIDADE "+itensMembro.get(6).trim());
			//Cidade cidade = cidadeServ.getNome(itensMembro.get(6).trim(), sessao);
			Cidade cidade = cidadeServ.get("402880925fc75ff9015fc7677f7f0004", sessao);
			if(cidade != null) {
				membro.setCidade(cidade);
				System.out.println("CIDADE " + cidade.getNome());
			}
			Bairro bairro = bairroServ.getNome(itensMembro.get(7).trim(), sessao);
			if(bairro != null) {
				membro.setBairro(bairro);
				System.out.println("BAIRRO " + bairro.getNome());
			}else {
				if(!itensMembro.get(7).trim().equals("")) {
					//cidade = cidadeServ.getNome("Bento Gonçalves", sessao);
					cidade = cidadeServ.get("402880925fc75ff9015fc7677f7f0004", sessao);
					bairro = new Bairro();
					bairro.setCidade(cidade);
					bairro.setNome(itensMembro.get(7).trim());
					bairro = bairroServ.save(bairro, sessao);
					membro.setBairro(bairro);
				}
			}
			
			try {
				DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
				df.setLenient (false); 
				Date datanascimento = df.parse (itensMembro.get(8).trim() + "");
				membro.setDatanascimento(datanascimento);
				System.out.println("DTNASCIMENTO " + datanascimento.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String estadocivil = "1";
			if(itensMembro.get(9).trim().equals("Casado")) {
				estadocivil = "2";
			}else if(itensMembro.get(9).trim().equals("Viúvo")) {
				estadocivil = "3";
			}else if(itensMembro.get(9).trim().equals("Separado")) {
				estadocivil = "4";
			}
			membro.setEstadocivil(estadocivil);
			System.out.println("ESTADOCIVIL " + estadocivil);
			
			String rg = itensMembro.get(10);
			if(rg != null && !rg.equals(""))
				membro.setRg(rg);
			
			String cpf = itensMembro.get(11);
			if(cpf != null && !cpf.equals(""))
				membro.setCpf(cpf);
			
			String ficha = itensMembro.get(12);
			if(ficha != null && !ficha.equals(""))
				membro.setNumeroficha(ficha);
			
			String dtbatismo = itensMembro.get(13);
			if(dtbatismo != null && !dtbatismo.equals(""))
				membro.setDatabatismo(dtbatismo);
			
			String dtentrada = itensMembro.get(14);
			if(dtentrada != null && !dtentrada.equals(""))
				membro.setDataentrada(dtentrada);
			
			String dtsaida = itensMembro.get(15);
			if(dtsaida != null && !dtsaida.equals(""))
				membro.setDatasaida(dtsaida);
			
			String recebido = itensMembro.get(16);
			if(recebido != null && !recebido.equals(""))
				membro.setRecebidopor(recebido);
			
			String bes = itensMembro.get(17);
			if(bes != null && !bes.equals(""))
				membro.setBatizadoespiritosanto(bes);
			
			String pai = itensMembro.get(18);
			if(pai != null && !pai.equals(""))
				membro.setPai(pai);
			
			String mae = itensMembro.get(19);
			if(mae != null && !mae.equals(""))
				membro.setMae(mae);
			
			String conj = itensMembro.get(20);
			if(conj != null && !conj.equals(""))
				membro.setConjuge(conj);
			
			String filhos = itensMembro.get(21);
			if(filhos != null && !filhos.equals(""))
				membro.setFilhos(filhos);
			
			String esc = itensMembro.get(22);
			if(esc != null && !esc.equals(""))
				membro.setEscolaridade(esc);
			System.out.println("ESCOLARIDADE " + itensMembro.get(22));
			
			String prof = itensMembro.get(23);
			if(prof != null && !prof.equals(""))
				membro.setProfissao(prof);
			System.out.println("PROFISSAO " + itensMembro.get(23));
			
			if(itensMembro.size() >= 24) {
				List<Membrocargo> cargos = new ArrayList<Membrocargo>();
				String car = itensMembro.get(24);
				if(car != null && !car.equals("")) {
					String[] cargosstring = itensMembro.get(24).split(",");
					for(String c : cargosstring) {
						Cargo cargo = cargoServ.getNome(c.trim(), sessao);
						if(cargo != null) {
							Membrocargo membrocargo = new Membrocargo();
							membrocargo.setCargo(cargo);
							cargos.add(membrocargo);
							System.out.println("CARGO " + cargo.getCargo());
						}
					}
					membro.setCargos(cargos);
				}
			}
			System.out.println(membro.getNome());
			membros.add(membro);
		}

		retorno.add(membros);
		retorno.add(erros);

		return retorno;
	}
	
	public String dataBrToBd(Date data) {
		java.sql.Date dataSql1 = new java.sql.Date(data.getTime());
		return dataSql1.toString();
	}
	
	public boolean verificaMembro(Membro membro, SessaoUser sessao, List<String> log) throws InterruptedException {
		Membro membroExisteExiste = this.getNome(membro.getNome(), sessao);

		if (membroExisteExiste == null) {
			System.out.println("membro não existe");

			this.save(membro, sessao);

			log.add("Membro Criado: " + membro.getNome());

		} else {
			System.out.println("membro  existe");
			
			Membro membro1 = new Membro();
			membro1 = membroExisteExiste;
			membro1.setId(membroExisteExiste.getId());
			membro1.setAtivo(membro.getAtivo());
			membro1.setBairro(membro.getBairro());
			membro1.setBatizadoespiritosanto(membro.getBatizadoespiritosanto());
			membro1.setCargos(membro.getCargos());
			membro1.setCep(membro.getCep());
			membro1.setCidade(membro.getCidade());
			membro1.setConjuge(membro.getConjuge());
			membro1.setCpf(membro.getCpf());
			membro1.setDatabatismo(membro.getDatabatismo());
			membro1.setDataentrada(membro.getDataentrada());
			membro1.setDatanascimento(membro.getDatanascimento());
			membro1.setDatasaida(membro.getDatasaida());
			membro1.setEmail(membro.getEmail());
			membro1.setEndereco(membro.getEndereco());
			membro1.setEscolaridade(membro.getEscolaridade());
			membro1.setEstado(membro.getEstado());
			membro1.setEstadocivil(membro.getEstadocivil());
			membro1.setFilhos(membro.getFilhos());
			//membro1.setImagem(membro.getImagem());
			membro1.setMae(membro.getMae());
			membro1.setNome(membro.getNome());
			membro1.setNumeroficha(membro.getNumeroficha());
			membro1.setObs(membro.getObs());
			membro1.setPai(membro.getPai());
			membro1.setProfissao(membro.getProfissao());
			membro1.setRecebidopor(membro.getRecebidopor());
			membro1.setRg(membro.getRg());
			membro1.setTelefone(membro.getTelefone());
			
			/*List<Membrocargo> cargos = new ArrayList<Membrocargo>();
			for(Membrocargo c : membro.getCargos()) {
				cargos.add(c);
			}
			membro1.setCargos(cargos);*/
			membro1.setCargos(membro.getCargos());
			
			System.out.println("MEMBROOO QUE VAI SALVAR: " + membro1.getNome() + " " + membro1.getId());
			this.save(membro1, sessao);
			log.add("Membro Atualizado: " + membro1.getNome());
		}

		return true;
	}
	
	public Membro getNome(String nome, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("nome", nome);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		return super.get(restricao, rcontratante);
	}

}
