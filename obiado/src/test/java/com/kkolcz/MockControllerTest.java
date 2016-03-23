package com.kkolcz;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.test.web.servlet.MockMvc;

/* import org.testng.annotations.Test; */

import static org.mockito.Mockito.*;
/* import static org.junit.matchers.JUnitMatchers.*; // for hasItem() */
/* import static org.junit.Assert.*; */
import org.junit.Test;
/* import org.junit.runner.RunWith; */

import org.springframework.web.servlet.view.InternalResourceView;

import com.kkolcz.controller.*;

public class MockControllerTest {

  /* z książki Spring In Action */
  /* @Test */
  public void testAppControllerRegisterPage() throws Exception {
    AppController controller = new AppController();
    MockMvc mockMvc = standaloneSetup(controller)
      .setSingleView(new InternalResourceView("/WEB-INF/views/register.jsp")) /* bez tego jest błąd circular reference */
      .build();

    mockMvc.perform(get("/register"))
      .andExpect(view().name("register"))
      .andExpect(model().attributeExists("userCommand"));
  }



}
