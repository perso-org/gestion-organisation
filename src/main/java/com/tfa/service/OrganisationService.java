package com.tfa.service;

import java.util.List;

import com.tfa.dto.ApiResponseDto;
import com.tfa.dto.OrganisationDto;


public interface OrganisationService {

	OrganisationDto creationOrganisation(OrganisationDto dto);
	OrganisationDto misajourOrganisationParCode(OrganisationDto dto,String code);
	OrganisationDto obtenirOrganisationParCode(String code);
	List<OrganisationDto> obtenirOrganisationParNom(String nom);
	List<OrganisationDto> obtenirOrganisations();
	boolean supprimerOrganisationParCode(String code);
}
