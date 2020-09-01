package com.bsa.assignment.resource;

import com.bsa.assignment.enums.SkillLevel;
import com.bsa.assignment.model.People;
import com.bsa.assignment.model.Skills;
import com.bsa.assignment.service.SkillsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = SkillsResource.class)
@Import(SkillsResource.class)
class SkillsResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SkillsService skillsService;

    List<Skills> mockSkillsList = new ArrayList<>();

    Skills mockSkills;

    @BeforeEach
    void setUp(){
        mockSkills = new Skills(123,"JAVA", SkillLevel.AWARENESS);
        mockSkillsList.add(mockSkills);
        mockSkillsList.add(new Skills(456,"J2EE",SkillLevel.WORKING));
    }

    @Test
    void getAllSkills() throws Exception {
        String url = "/skills";
        when(skillsService.getAllSkills()).thenReturn(mockSkillsList);
        String expected = "[{\"skillId\":123,\"skillName\":\"JAVA\",\"skillLevel\":\"AWARENESS\"}," +
                "{\"skillId\":456,\"skillName\":\"J2EE\",\"skillLevel\":\"WORKING\"}]";
        MvcResult result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expected))
                .andDo(print())
                .andReturn();
        Assertions.assertEquals(expected,result.getResponse().getContentAsString());
    }

    @Test
    void getSkillBySkillId() throws Exception {
        String url = "/skills/123";
        when(skillsService.getSkillBySkillId(any(Integer.class))).thenReturn(mockSkills);
        String expected = "{\"skillId\":123,\"skillName\":\"JAVA\",\"skillLevel\":\"AWARENESS\"}";
        MvcResult result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expected))
                .andDo(print())
                .andReturn();
        Assertions.assertEquals(expected,result.getResponse().getContentAsString());
    }

    @Test
    void addSkill() throws Exception {
        String url = "/skills";
        when(skillsService.addSkill(any(Skills.class))).thenReturn(mockSkills);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{skillId:456,skillName:\"JAVA\",\"skillLevel:\"EXPERT\"}"))
                .andDo(print());
        //.andExpect(status().isOk());
        // .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8))
        //.andExpect(header().string("Location", "/people"));
    }

    @Test
    void updateSkill() throws Exception {
        String url = "/skills/123";
        when(skillsService.updateSkill(any(Integer.class),any(Skills.class)))
                .thenReturn(mockSkills);
        String expected = "";
        MvcResult result = mockMvc.perform(put(url))
                // .andExpect(status().isOk())
                //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(MockMvcResultMatchers.content().json(expected))
                .andDo(print())
                .andReturn();
        Assertions.assertEquals(expected,result.getResponse().getContentAsString());
    }

    @Test
    void deleteSkill() throws Exception {
        String url = "/skills/123";
        mockMvc.perform(delete(url))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        verify(skillsService).deleteSkill(any(Integer.class));
    }
}