package com.tfa.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfa.dto.OrganisationDto;
import com.tfa.service.OrganisationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/org")
@RequiredArgsConstructor
public class OrganisationController {

	private final OrganisationService service;
	
	@PostMapping("creation")
	public ResponseEntity<OrganisationDto> creationOrganisation(@RequestBody @Valid OrganisationDto dto) {
		
		OrganisationDto organisationDto = service.creationOrganisation(dto);
		
		return new ResponseEntity<>(organisationDto, HttpStatus.CREATED);
	}
	
	@PatchMapping("update/{code}")
	public ResponseEntity<OrganisationDto> misajourOrganisationParCode(@RequestBody OrganisationDto dto, @PathVariable("code") String code) {
		
		OrganisationDto organisationDto = service.misajourOrganisationParCode(dto,code);
		
		return ResponseEntity.ok(organisationDto);
	}
	
	@GetMapping("code/{code}")
	public ResponseEntity<OrganisationDto>  obtenirOrganisationParCode(@PathVariable String code) {
		OrganisationDto dto = service.obtenirOrganisationParCode(code);
		
		if(Objects.isNull(dto))
			throw new RuntimeException(String.format("Il n'existe pas une organisation avec le code %s", code));
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("{nom}")
	public ResponseEntity<List<OrganisationDto>> obtenirOrganisationParNom(@PathVariable String nom) {
		List<OrganisationDto> dtos = service.obtenirOrganisationParNom(nom);
		
		if (CollectionUtils.isEmpty(dtos)) 
			throw new RuntimeException(String.format("Il n'existe pas une organisation avec le nom %s", nom));
		
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping
	public ResponseEntity<List<OrganisationDto>> obtenirOrganisations() {
		List<OrganisationDto> dtos = service.obtenirOrganisations();
		
		if (CollectionUtils.isEmpty(dtos)) 
			throw new RuntimeException("Il n'existe pas d'organisation");
		
		return ResponseEntity.ok(dtos);
	}
	
	@DeleteMapping("/delete/{code}")
	public ResponseEntity<String> supprimerOrganisationParCode(@PathVariable String code) {
		boolean isSupprimé = service.supprimerOrganisationParCode(code);
		
		if(!isSupprimé) 
			throw new RuntimeException(String.format("Il n'existe pas une organisation avec le code %s", code));
		
		return ResponseEntity.ok(String.format("Suppression de l'organisation %s avec succès!", code));
		
	}
}
