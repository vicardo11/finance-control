package it.sosinski.financecontrol.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.sosinski.financecontrol.service.ExpenseCategoryService;
import it.sosinski.financecontrol.web.dto.NewExpenseCategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static it.sosinski.financecontrol.web.controller.ControllerConstants.EXPENSE_CATEGORIES_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ExpenseCategoryController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@ActiveProfiles(value = "test")
class ExpenseCategoryControllerTest {

    public static final String CATEGORY_NAME_HOME = "Home";
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExpenseCategoryService expenseCategoryService;

    @Test
    void whenGetThenStatusIsOk() throws Exception {
        //Given

        //When
        mvc.perform(get(EXPENSE_CATEGORIES_URL))
                .andExpect(status().isOk());

        //Then
    }

    @Test
    void whenPostThenStatusIsOk() throws Exception {
        //Given
        var categoryDto = new NewExpenseCategoryDto(CATEGORY_NAME_HOME);
        var categoryAsString = objectMapper.writeValueAsString(categoryDto);

        //When
        mvc.perform(post(EXPENSE_CATEGORIES_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryAsString))
                .andExpect(status().isOk());

        //Then
    }

}
