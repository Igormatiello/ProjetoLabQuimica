package br.edu.utfpr.pb.labquimica.backend.viewmodels;

import br.edu.utfpr.pb.labquimica.backend.enumerators.TipoPessoa;
import br.edu.utfpr.pb.labquimica.backend.model.Cidade;
import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.model.ProgramaEnsino;
import br.edu.utfpr.pb.labquimica.backend.utils.ValidationMessages;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateDeserializer;
import br.edu.utfpr.pb.labquimica.backend.utils.serializers.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@EqualsAndHashCode(of = "id")
public class SolicitacaoCadastroViewModel {
    // dados iniciais
    @NotNull(message = ValidationMessages.IdNaoPodeSerVazio)
    private Long id;
    @NotNull(message = ValidationMessages.TipoPessoaNaoPodeSerVazio)
    private TipoPessoa tipoPessoa;

    @NotNull(message = ValidationMessages.NomeNaoPodeSerVazio)
    private String nome;
    @NotNull(message = ValidationMessages.CepNaoPodeSerVazio)
    private String cep;
    @NotNull(message = ValidationMessages.EnderecoNaoPodeSerVazio)
    private String endereco;
    @NotNull(message = ValidationMessages.BairroNaoPodeSerVazio)
    private String bairro;
    @NotNull(message = ValidationMessages.NumeroEnderecoNaoPodeSerVazio)
    private String numero;
    private String complemento;
    private String nomeprojeto;
    @NotNull(message = ValidationMessages.TelefoneNaoPodeSerVazio)
    private String telefone;
    @NotNull(message = ValidationMessages.CelularNaoPodeSerVazio)
    private String celular;
    @NotNull(message = ValidationMessages.EmailNaoPodeSerVazio)
    @Email(message = ValidationMessages.EmailNaoPodeSerInvalido)
    private String email;
    @NotNull(message = ValidationMessages.CidadeNaoPodeSerVazio)
    private Cidade cidade;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDate dataCriacao;

    // SE FOR PESSOA FISICA SERÁ O CPF, SENÃO O CNPJ
    @NotBlank(message = ValidationMessages.DocumentoNaoPodeSerVazio)
    private String documento;

    // INDICA SE É UM PROFESSOR
    private boolean ehProfessor;

    // SE FOR PRECISA TER INSTITUIÇÃO VINCULADA
    // E PROGRAMA DE ENSINO
    private String nomeInstituicao;
    private Instituicao instituicao;
    private ProgramaEnsino programaEnsino;
    private String nomeProgramaEnsino;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataTerminoPrograma;

    // SE FOR ALUNO DEVE TER ORIENTADOR
    private String cpfOrientador;
    private PessoaInstituicao orientador;

    //CNPJ
    private String inscricaoEstadual;

    public SolicitacaoCadastroViewModel() {
    }

    public SolicitacaoCadastroViewModel(Long id, TipoPessoa tipoPessoa, String nome, String cep, String endereco, String bairro, String numero, String complemento, String telefone, String celular, String email, Cidade cidade, LocalDate dataCriacao, String documento, boolean ehProfessor, String nomeInstituicao, String nomeProgramaEnsino, LocalDate dataTerminoPrograma, String cpfOrientador,String nomeProjeto) {
        this.id = id;
        this.tipoPessoa = tipoPessoa;
        this.nome = nome;
        this.cep = cep;
        this.endereco = endereco;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
        this.cidade = cidade;
        this.dataCriacao = dataCriacao;
        this.documento = documento;
        this.ehProfessor = ehProfessor;
        this.nomeInstituicao = nomeInstituicao;
        this.nomeProgramaEnsino = nomeProgramaEnsino;
        this.dataTerminoPrograma = dataTerminoPrograma;
        this.cpfOrientador = cpfOrientador;
        this.nomeprojeto = nomeProjeto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public boolean isEhProfessor() {
        return ehProfessor;
    }

    public void setEhProfessor(boolean ehProfessor) {
        this.ehProfessor = ehProfessor;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public ProgramaEnsino getProgramaEnsino() {
        return programaEnsino;
    }

    public void setProgramaEnsino(ProgramaEnsino programaEnsino) {
        this.programaEnsino = programaEnsino;
    }

    public String getNomeProgramaEnsino() {
        return nomeProgramaEnsino;
    }

    public void setNomeProgramaEnsino(String nomeProgramaEnsino) {
        this.nomeProgramaEnsino = nomeProgramaEnsino;
    }

    public LocalDate getDataTerminoPrograma() {
        return dataTerminoPrograma;
    }

    public void setDataTerminoPrograma(LocalDate dataTerminoPrograma) {
        this.dataTerminoPrograma = dataTerminoPrograma;
    }

    public String getCpfOrientador() {
        return cpfOrientador;
    }

    public void setCpfOrientador(String cpfOrientador) {
        this.cpfOrientador = cpfOrientador;
    }

    public PessoaInstituicao getOrientador() {
        return orientador;
    }

    public void setOrientador(PessoaInstituicao orientador) {
        this.orientador = orientador;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getNomeprojeto() {
        return nomeprojeto;
    }

    public void setNomeprojeto(String nomeprojeto) {
        this.nomeprojeto = nomeprojeto;
    }
}
