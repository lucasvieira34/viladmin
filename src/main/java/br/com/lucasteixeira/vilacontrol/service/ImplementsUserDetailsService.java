package br.com.lucasteixeira.vilacontrol.service;

import br.com.lucasteixeira.vilacontrol.models.Usuario;
import br.com.lucasteixeira.vilacontrol.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByLogin(login);

        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }

        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());

    }

}
