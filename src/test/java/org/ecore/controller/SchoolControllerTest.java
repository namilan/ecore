package org.ecore.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.Arrays;
import java.util.Collection;

import org.ecore.model.School;
import org.ecore.notFoundException.SchoolNotFoundException;
import org.ecore.repository.SchoolRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class SchoolControllerTest {
	
	@InjectMocks
	private SchoolController underTest;
	
	@Mock
	private School school;
	Long schoolId;
	
	@Mock
	private School anotherSchool;
	
	@Mock
	private SchoolRepository schoolRepo;
	
	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddASingleSchoolToModel() throws SchoolNotFoundException { 
		long arbitrarySchoolId = 1;
		when(schoolRepo.findById(arbitrarySchoolId)).thenReturn(Optional.of(school));
	
		underTest.findOneSchool(arbitrarySchoolId, model);
		verify(model).addAttribute("schools", school);
	}
	
	@Test
	public void shouldAddMultipleSchoolsToModel() {
		Collection<School> allSchools = Arrays.asList(school, anotherSchool);
		when(schoolRepo.findAll()).thenReturn(allSchools);
		
		underTest.findAllSchools(model);
		verify(model).addAttribute("schools", allSchools);
	}
	
}