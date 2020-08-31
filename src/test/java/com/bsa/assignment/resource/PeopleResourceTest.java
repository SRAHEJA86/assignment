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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(value = PeopleResource.class)
@Import(PeopleResource.class)
class PeopleResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PeopleService peopleService;

    List<People> mockPeople = new ArrayList<People>();

    @BeforeEach
    void setUp(){
        mockPeople.add(new People(123,"Sadhana",
                Arrays.asList(new Skills(456,"Java", SkillLevel.WORKING),
                new Skills(457,"C++", SkillLevel.AWARENESS))));
        mockPeople.add(new People(124,"Ayaansh",
                Arrays.asList(new Skills(458,"J2EE", SkillLevel.EXPERT),
                        new Skills(459,"Python", SkillLevel.PRACTITIONER))));
    }

    @Test
    void getAllPeople() throws Exception {
        String url = "/people";
        Mockito.when(peopleService.getAllPeople()).thenReturn(mockPeople);
        String expected = "[{\"personId\":123,\"personName\":\"Sadhana\",\"skills\":[{\"skillId\":456," +
                "\"skillName\":\"Java\",\"skillLevel\":\"WORKING\"}," +
                "{\"skillId\":457,\"skillName\":\"C++\",\"skillLevel\":" +
                "\"AWARENESS\"}]},{\"personId\":124,\"personName\":\"Ayaansh\"," +
                "\"skills\":[{\"skillId\":458,\"skillName\":\"J2EE\",\"skillLevel\":" +
                "\"EXPERT\"},{\"skillId\":459,\"skillName\":\"Python\",\"skillLevel\":\"PRACTITIONER\"}]}]";
        MvcResult result = mockMvc.perform(get(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expected))
                .andDo(print())
                .andReturn();
        Assertions.assertEquals(expected,result.getResponse().getContentAsString());
    }

    @Test
    void updatePeople() {
        Mockito.when(peopleService.addPeople(Mockito.any(People.class))).
                thenReturn(mockPeople.get(0));
    }

    @Test
    void addPeople() throws Exception {
        String url = "/people";
        Mockito.when(peopleService.addPeople(Mockito.any(People.class)))
                .thenReturn(mockPeople.get(0));
        MvcResult result = mockMvc.perform(post(url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void deletePeople() {
    }
}