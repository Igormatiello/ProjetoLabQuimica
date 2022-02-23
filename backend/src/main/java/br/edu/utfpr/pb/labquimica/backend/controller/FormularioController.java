package br.edu.utfpr.pb.labquimica.backend.controller;

import br.edu.utfpr.pb.labquimica.backend.enumerators.StatusFormulario;
import br.edu.utfpr.pb.labquimica.backend.model.Formulario;
import br.edu.utfpr.pb.labquimica.backend.model.Instituicao;
import br.edu.utfpr.pb.labquimica.backend.model.PessoaInstituicao;
import br.edu.utfpr.pb.labquimica.backend.security.acessor.UserAccessor;
import br.edu.utfpr.pb.labquimica.backend.service.CrudService;
import br.edu.utfpr.pb.labquimica.backend.service.EquipamentoService;
import br.edu.utfpr.pb.labquimica.backend.service.FormularioService;
import br.edu.utfpr.pb.labquimica.backend.service.InstituicaoService;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.FormularioAnaliseViewModel;
import br.edu.utfpr.pb.labquimica.backend.viewmodels.ResultadoOperacaoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.Normalizer;
import java.util.List;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("formulario")
public class FormularioController extends CrudController<Formulario, Long> {

	private FormularioService formularioService;

	public FormularioController(FormularioService formularioService) {
		this.formularioService = formularioService;
	}

	private UserAccessor usuarioAccessor;

	public FormularioController(UserAccessor usuarioAccessor) {
		this.usuarioAccessor = usuarioAccessor;
	}

	@Override
	protected CrudService<Formulario, Long> getService() {
		return formularioService;
	}

	@GetMapping("busca-status/{status}")
	public List<Formulario> findByStatus(@PathVariable StatusFormulario status) {
		return formularioService.findByStatusOrderByDataSolicitacaoDesc(status);
	}

	@GetMapping("busca-por-pessoa")
	public List<Formulario> findFormulariosPessoa() {
		return formularioService.findFormulariosByPessoaId(usuarioAccessor.getUsuario().getPessoa().getId());
	}

	@GetMapping("buscaFormulariosPessoaJuridica")
	public List<Formulario> buscaFormulariosPessoaJuridica() {
		return formularioService.buscaFormulariosPessoaJuridica(usuarioAccessor.getUsuario().getPessoa().getId());
	}

	@PostMapping("{id}/muda-status")
	public ResultadoOperacaoViewModel mudaStatus(@PathVariable Long id,
			@RequestBody @Valid FormularioAnaliseViewModel analise) {
		return formularioService.analisaFormulario(analise.setFormularioId(id));
	}

	// UPLOAD
	@PostMapping("upload/{id}")
	public ResponseEntity<?> save(@PathVariable Long id, @RequestParam("arquivos") MultipartFile[] arquivos,
			HttpServletRequest request) {

		if (arquivos.length > 0 && !arquivos[0].getOriginalFilename().isEmpty()) {
			saveFile(id, arquivos, request);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	private void saveFile(Long id, MultipartFile anexo, HttpServletRequest request) {
		File dir = new File(request.getServletContext().getRealPath("/images/"));
		if (!dir.exists()) { // verifica se o diretório de armazenamento existe
			dir.mkdirs(); // não existindo, cria o diretório
		}

		String caminhoAnexo = request.getServletContext().getRealPath("/images/");
		String extensao = anexo.getOriginalFilename().substring(anexo.getOriginalFilename().lastIndexOf("."),
				anexo.getOriginalFilename().length());

		String nomeArquivo = id + extensao;

		try {
			FileOutputStream fileOut = new FileOutputStream(new File(caminhoAnexo + nomeArquivo));

			BufferedOutputStream stream = new BufferedOutputStream(fileOut);
			stream.write(anexo.getBytes());
			stream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveFile(Long id, MultipartFile[] arquivos, HttpServletRequest request) {
		File dir = new File(request.getServletContext().getRealPath("/images/"));
		if (!dir.exists()) { // verifica se o diretório de armazenamento existe
			dir.mkdirs(); // não existindo, cria o diretório
		}

		String caminhoAnexo = request.getServletContext().getRealPath("/images/");
		int i = 0;
		for (MultipartFile arquivo : arquivos) {
			i++;
			String extensao = arquivo.getOriginalFilename().substring(arquivo.getOriginalFilename().lastIndexOf("."),
					arquivo.getOriginalFilename().length());

			String nomeArquivo = id + "_" + i + extensao;

			try {
				FileOutputStream fileOut = new FileOutputStream(new File(caminhoAnexo + nomeArquivo));

				BufferedOutputStream stream = new BufferedOutputStream(fileOut);
				stream.write(arquivo.getBytes());
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
