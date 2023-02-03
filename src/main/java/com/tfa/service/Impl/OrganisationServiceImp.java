package com.tfa.service.Impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tfa.dto.OrganisationDto;
import com.tfa.entite.Organisation;
import com.tfa.repository.OrganisationRepository;
import com.tfa.service.OrganisationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrganisationServiceImp implements OrganisationService {

	private final OrganisationRepository repo;
	private final ModelMapper mapper;
	
	@Override
	public OrganisationDto creationOrganisation(OrganisationDto dto) {
		log.info("Creation d'une organisation...");
		Organisation org = mapper.map(dto, Organisation.class);
		
		Organisation orgSaved = repo.save(org);
		
		return mapper.map(orgSaved, OrganisationDto.class);
	}

	@Override
	public OrganisationDto misajourOrganisationParCode(OrganisationDto dto, String code) {
		log.info("Mise à jour d'une organisation...");
		
		Organisation orgFromDb = repo.findByCode(code);
		if(orgFromDb == null) {
			return null;
		}
		orgFromDb.setCode(StringUtils.isBlank(dto.getCode()) ? orgFromDb.getCode() : dto.getCode());
		orgFromDb.setDescription(StringUtils.isBlank(dto.getDescription()) ? orgFromDb.getDescription() : dto.getDescription());
		orgFromDb.setName(StringUtils.isBlank(dto.getName()) ? orgFromDb.getName() : dto.getName());
		Organisation orgSaved = repo.saveAndFlush(orgFromDb);
		
		return mapper.map(orgSaved, OrganisationDto.class);
	}

	@Override
	public OrganisationDto obtenirOrganisationParCode(String code) {
		
		Organisation orgFromDb = repo.findByCode(code);
		if(orgFromDb == null) {
			return null;
		}
		return mapper.map(orgFromDb, OrganisationDto.class);
	}

	@Override
	public List<OrganisationDto> obtenirOrganisationParNom(String nom) {
		List<Organisation> organisations = repo.findByName(nom);
		
		if(CollectionUtils.isEmpty(organisations)) {
			return Collections.emptyList();
		}
		
		return organisations.stream().map(p -> mapper.map(p, OrganisationDto.class))
				.toList();
	}

	@Override
	public List<OrganisationDto> obtenirOrganisations() {
		List<Organisation> organisations = repo.findAll();
		
		if(CollectionUtils.isEmpty(organisations)) {
			return Collections.emptyList();
		}
		
		return organisations.stream().map(p -> mapper.map(p, OrganisationDto.class))
				.toList();
	}

	@Override
	public boolean supprimerOrganisationParCode(String code) {
		Organisation orgFromDb = repo.findByCode(code);
		if(orgFromDb == null) {
			return false;
		}
		repo.delete(orgFromDb);
		return true;
	}

}
