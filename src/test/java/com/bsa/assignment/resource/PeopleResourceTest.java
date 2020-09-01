package com.bsa.assignment.resource;

import com.bsa.assignment.enums.SkillLevel;
import com.bsa.assignment.model.People;
import com.bsa.assignment.model.Skills;
import com.bsa.assignment.service.PeopleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

import static com.bsa.assignment.enums.SkillLevel.AWARENESS;
import static com.bsa.assignment.enums.SkillLevel.EXPERT;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = PeopleResource.class)
@Import(PeopleResource.class)
class PeopleResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PeopleService peopleService;

    List<People> mockPeopleList = new ArrayList<>();

    People mockPeople;

    @BeforeEach
    void setUp(){
        mockPeople = new People(123,"Sadhana",
                Arrays.asList(new Skills(456,"Java", SkillLevel.WORKING),
                        new Skills(457,"C++", AWARENESS)));
        mockPeopleList.add(mockPeople);
        mockPeopleList.add(new People(124,"Ayaansh",
                Arrays.asList(new Skills(458,"J2EE", EXPERT),
                        new Skills(459,"Python", SkillLevel.PRACTITIONER))));
    }

    @Test
    void getAllPeople() throws Exception {
        String url = "/people";
        when(peopleService.getAllPeople()).thenReturn(mockPeopleList);
        String expected = "[{\"personId\":123,\"personName\":\"Sadhana\",\"skills\":[{\"skillId\":456," +
                "\"skillName\":\"Java\",\"skillLevel\":\"WORKING\"}," +
                "{\"skillId\":457,\"skillName\":\"C++\",\"skillLevel\":" +
                "\"AWARENESS\"}]},{\"personId\":124,\"personName\":\"Ayaansh\"," +
                "\"skills\":[{\"skillId\":458,\"skillName\":\"J2EE\",\"skillLevel\":" +
                "\"EXPERT\"},{\"skillId\":459,\"skillName\":\"Python\",\"skillLevel\":\"PRACTITIONER\"}]}]";
        MvcResult result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expected))
                .andDo(print())
                .andReturn();
        Assertions.assertEquals(expected,result.getResponse().getContentAsString());
    }

    @Test
    void updatePeople() throws Exception {
        String url = "/people/123";
        when(peopleService.updatePeople(any(Integer.class),any(People.class)))
                .thenReturn(mockPeople);
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
    void addPeople() throws Exception {
        String url = "/people";
        when(peopleService.addPeople(any(People.class))).thenReturn(mockPeople);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{personId : 123,personName:\"Sadhana\",skills: [{skillId:456,skillName:\"JAVA\"," +
                        "skillLevel:\"EXPERT\"},{skillId:890,skillName:\"J2EE\",skillLevel:\"AWARENESS\"} ]}"))
                .andDo(print());
                //.andExpect(status().isOk());
               // .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //.andExpect(header().string("Location", "/people"));
    }

    @Test
    void deletePeople() throws Exception {
        String url = "/people/123";
        mockMvc.perform(delete(url))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        verify(peopleService).deletePeople(any(Integer.class));

    }

    @Test
    void getPeopleById() throws Exception {
        String url = "/people/123";
        when(peopleService.getPeopleById(any(Integer.class))).thenReturn(mockPeople);
        String expected = "{\"personId\":123,\"personName\":\"Sadhana\",\"skills\":[{\"skillId\":456,\"skillName\":" +
                "\"Java\",\"skillLevel\":\"WORKING\"},{\"skillId\":457,\"skillName\":\"C++\"," +
                "\"skillLevel\":\"AWARENESS\"}]}";
        MvcResult result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expected))
                .andDo(print())
                .andReturn();
        Assertions.assertEquals(expected,result.getResponse().getContentAsString());

    }

    @Test
    void getSkillsForPeople() throws Exception {
        String url = "/people/123/skills";
        when(peopleService.getSkillsForPeople(any(Integer.class))).thenReturn(mockPeople.getSkills());
        String expected = "[{\"skillId\":456,\"skillName\":" +
                "\"Java\",\"skillLevel\":\"WORKING\"},{\"skillId\":457,\"skillName\":\"C++\"," +
                "\"skillLevel\":\"AWARENESS\"}]";
        MvcResult result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expected))
                .andDo(print())
                .andReturn();
        Assertions.assertEquals(expected,result.getResponse().getContentAsString());

    }

    @Test
    void getDetailsForSkills() throws Exception {
        String url = "/people/123/skills/456";
        when(peopleService.getDetailsForASkill(any(Integer.class),any(Integer.class)))
                .thenReturn(mockPeople.getSkills().get(0));
        String expected = "{\"skillId\":456,\"skillName\":\"Java\",\"skillLevel\":\"WORKING\"}";
        MvcResult result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expected))
                .andDo(print())
                .andReturn();
        Assertions.assertEquals(expected,result.getResponse().getContentAsString());
    }
}