package com.czerwo.reworktracking.ftrot.controllers;

import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageDto;
import com.czerwo.reworktracking.ftrot.services.TechnicalProjectManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class TechnicalProjectManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TechnicalProjectManagerService technicalProjectManagerService;


    @Test
    void shouldGetAllWorkPackages() throws Exception {
        // given
        WorkPackageDto workPackageDto = new WorkPackageDto();
        List<WorkPackageDto> workPackageDtos = Arrays.asList(workPackageDto);

        // when
        given(technicalProjectManagerService.findAllWorkPackagesByOwnerUsername(anyString())).willReturn(workPackageDtos);

        MvcResult mvcResult = mockMvc.perform(get("/api/technical-project-manager/all"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


    }



    //shouldEditWorkPackage

    //shouldDeleteWorkPackage



}