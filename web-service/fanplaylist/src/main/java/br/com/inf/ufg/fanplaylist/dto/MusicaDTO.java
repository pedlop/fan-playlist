package br.com.inf.ufg.fanplaylist.dto;

import br.com.inf.ufg.fanplaylist.dominio.Musica;
import br.com.inf.ufg.fanplaylist.excecao.ExcecaoNegocio;

public class MusicaDTO extends ExcecaoNegocio implements PadraoRetorno<Musica> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

	public MusicaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MusicaDTO(ExcecaoNegocio e, String controllerP) {
		super(e, controllerP);
		// TODO Auto-generated constructor stub
	}

	static public MusicaDTO converteDominioDto(Musica dominio) {
		MusicaDTO dto = new MusicaDTO();
			dto.id = dominio.getId();
			dto.nome = dominio.getNome();
		return dto;
	}

	@Override
	public PadraoRetorno<Musica> criaDTO() {
		return new MusicaDTO();
	}

	@Override
	public PadraoRetorno<Musica> converteExcecaoNegocio(ExcecaoNegocio excecao,
			String controllerP) {
		return new MusicaDTO(excecao, controllerP);
	}

}
