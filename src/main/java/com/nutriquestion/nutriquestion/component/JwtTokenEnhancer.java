package com.nutriquestion.nutriquestion.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.nutriquestion.nutriquestion.entities.Nutricionista;
import com.nutriquestion.nutriquestion.repositories.NutricionistaRepository;

@Component
public class JwtTokenEnhancer implements TokenEnhancer{

	@Autowired
	private NutricionistaRepository nutricionistaRepository;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Nutricionista usuario = nutricionistaRepository.findByEmail(authentication.getName());
		
		Map<String, Object> map = new HashMap<>();
//		map.put("usuarioNome", usuario.getNome());
		map.put("usuarioId", usuario.getId());
		
		DefaultOAuth2AccessToken token =(DefaultOAuth2AccessToken) accessToken;
		token.setAdditionalInformation(map);
		return token;
		
	}

}
