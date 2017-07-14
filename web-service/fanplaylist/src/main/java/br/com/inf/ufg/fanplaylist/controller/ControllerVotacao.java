package br.com.inf.ufg.fanplaylist.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.inf.ufg.fanplaylist.dominio.Musica;
import br.com.inf.ufg.fanplaylist.dominio.TipoUsuario;
import br.com.inf.ufg.fanplaylist.dominio.Usuario;
import br.com.inf.ufg.fanplaylist.dominio.Voto;
import br.com.inf.ufg.fanplaylist.dto.MusicaDTO;
import br.com.inf.ufg.fanplaylist.dto.UsuarioDTO;
import br.com.inf.ufg.fanplaylist.repositorio.IMusicaRepositorio;
import br.com.inf.ufg.fanplaylist.repositorio.IUsuarioRepositorio;
import br.com.inf.ufg.fanplaylist.repositorio.IVotoRepositorio;

@RestController
public class ControllerVotacao {
	
	@Autowired
	private IMusicaRepositorio iMusicaRepositorio;
	
	@Autowired
    private IUsuarioRepositorio iUsuarioRepositorio;
	
	@Autowired
	private IVotoRepositorio iVotoRepositorio;

    @Secured({
        TipoUsuario.ADMIN_S,
        TipoUsuario.APP_S
    })
    @RequestMapping(method = RequestMethod.POST,
    value = "/ControllerVotacao/votar")
	public UsuarioDTO votar(@RequestBody ParametroVotacao p) {
		Optional<Usuario> opUsuario = iUsuarioRepositorio.findById(p.usuarioDTO.getId());
		Optional<Musica> opMusica = iMusicaRepositorio.findById(p.musica.getId());
		
		if (opUsuario.isPresent() && opMusica.isPresent()) {
			Voto voto = new Voto(opUsuario.get(), opMusica.get());
			iVotoRepositorio.save(voto);
		}
		return p.usuarioDTO;
	}
	
	
    @RequestMapping(method = RequestMethod.GET,
            value = "/ControllerVotacao/todasMusicas")
	public Collection<MusicaDTO> buscaTodasMusicas() {
		return iMusicaRepositorio.findAll()
				                 .stream()
				                 .map(MusicaDTO::converteDominioDto)
				                 .collect(Collectors.toList());
	}
	
	static class ParametroVotacao {
		
		private UsuarioDTO usuarioDTO;
		
		private MusicaDTO musica;

		public UsuarioDTO getUsuarioDTO() {
			return usuarioDTO;
		}

		public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
			this.usuarioDTO = usuarioDTO;
		}

		public MusicaDTO getMusica() {
			return musica;
		}

		public void setMusica(MusicaDTO musica) {
			this.musica = musica;
		}
		
	}
}
