package br.edu.utfpr.pb.labquimica.backend.viewmodels;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.ParticipacaoProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateDeserializer;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(of = "id")
public class DadosPessoaViewModel {
	// dados pessoais

	private Long pessoaId;

	private TipoPessoa tipoPessoa;

	private String nome;

	private String documento;

	private String cep;

	private String endereco;

	private String bairro;

	private String numero;

	private String complemento;

	private String telefone;

	private String celular;

	private String email;

	private String inscricaoEstadual;

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dataCriacao;

	private boolean ehAtivo;

	private Cidade cidade;

	private Usuario usuario;

	private List<PessoaInstituicao> pessoaInstituicoes;

	public DadosPessoaViewModel() {
	}

	public DadosPessoaViewModel(Long pessoaId, TipoPessoa tipoPessoa, String nome, String documento, String cep,
			String endereco, String bairro, String numero, String complemento, String telefone, String celular,
			String email, String inscricaoEstadual, LocalDate dataCriacao, boolean ehAtivo, Cidade cidade,
			Usuario usuario, List<PessoaInstituicao> pessoaInstituicoes) {
		this.pessoaId = pessoaId;
		this.tipoPessoa = tipoPessoa;
		this.nome = nome;
		this.documento = documento;
		this.cep = cep;
		this.endereco = endereco;
		this.bairro = bairro;
		this.numero = numero;
		this.complemento = complemento;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.inscricaoEstadual = inscricaoEstadual;
		this.dataCriacao = dataCriacao;
		this.ehAtivo = ehAtivo;
		this.cidade = cidade;
		this.usuario = usuario;
		this.pessoaInstituicoes = pessoaInstituicoes;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public boolean isEhAtivo() {
		return ehAtivo;
	}

	public void setEhAtivo(boolean ehAtivo) {
		this.ehAtivo = ehAtivo;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PessoaInstituicao> getPessoaInstituicoes() {
		return pessoaInstituicoes;
	}

	public void setPessoaInstituicoes(List<PessoaInstituicao> pessoaInstituicoes) {
		this.pessoaInstituicoes = pessoaInstituicoes;
	}

}
