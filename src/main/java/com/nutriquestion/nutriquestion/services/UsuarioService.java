//package com.nutriquestion.nutriquestion.services;
//
//import java.util.Optional;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.nutriquestion.nutriquestion.dtos.UsuarioDTO;
//import com.nutriquestion.nutriquestion.dtos.NutricionistaInsertDTO;
//import com.nutriquestion.nutriquestion.entities.Usuario;
//import com.nutriquestion.nutriquestion.repositories.UsuarioRepository;
//import com.nutriquestion.nutriquestion.services.exceptions.ResourceNotFoundException;
//
//@Service
//public class UsuarioService implements UserDetailsService {
//
//	private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);
//
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//
//	@Autowired
//	private UsuarioRepository repository;
//
//	@Transactional
//	public UsuarioDTO insert(NutricionistaInsertDTO dto) {
//		Usuario entity = new Usuario();
//		copyDTOToEntity(dto, entity);
//		entity.setSenha(passwordEncoder.encode(dto.getSenha()));
//		entity = repository.save(entity);
//		return new UsuarioDTO(entity);
//	}
//
//	@Transactional(readOnly = true)
//	public UsuarioDTO findById(Long id) {
//		Optional<Usuario> obj = repository.findById(id);
//		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
//		return new UsuarioDTO(entity);
//	}
//
//	private void copyDTOToEntity(UsuarioDTO dto, Usuario entity) {
//		entity.setNome(dto.getNome());
//		entity.setEmail(dto.getEmail());
//		entity.setCpf(dto.getCpf());
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Usuario usuario = repository.findByEmail(username);
//		if (usuario == null) {
//			logger.error("User not found: " + username);
//			throw new UsernameNotFoundException("Email not found");
//		}
//		logger.info("User found: " + username);
//		return (UserDetails) usuario;
//	}
//}
