package ait.student.nutrition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ait.student.nutrition.controllers.NutritionController;
import ait.student.nutrition.nutrition.dto.supplier;
import ait.student.nutrition.rep.NutritionRepository;

class NutritionControllerTest {

    @Mock
    private NutritionRepository nutritionRepository;

    @InjectMocks
    private NutritionController nutritionController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(nutritionController).build();
    }

    @Test
    void testGetSuppliers() throws Exception {
        // Setup mock data
        List<supplier> suppliers = new ArrayList<supplier>();
        suppliers.add(new supplier(1L,"Supplier 1", "Item 1", "IC1", "10.0", "15.0", "5.0"));
        suppliers.add(new supplier(2L,"Supplier 2", "Item 2", "IC2", "20.0", "25.0", "5.0"));
        suppliers.add(new supplier(3L,"Supplier 3", "Item 3", "IC3", "30.0", "35.0", "5.0"));

        // Setup mock repository response
        when(nutritionRepository.findAll(Sort.by("item").descending())).thenReturn(suppliers);

        // Perform the request
        mockMvc.perform(get("/getsuppliers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].supplier_name").value("Supplier 1"))
                .andExpect(jsonPath("$[0].item").value("Item 1"))
                .andExpect(jsonPath("$[0].item_code").value("IC1"))
                .andExpect(jsonPath("$[0].manufacting_cost").value(10.0))
                .andExpect(jsonPath("$[0].selling_cost").value(15.0))
                .andExpect(jsonPath("$[0].profit_gained").value(5.0))
                .andExpect(jsonPath("$[1].supplier_name").value("Supplier 2"))
                .andExpect(jsonPath("$[1].item").value("Item 2"))
                .andExpect(jsonPath("$[1].item_code").value("IC2"))
                .andExpect(jsonPath("$[1].manufacting_cost").value(20.0))
                .andExpect(jsonPath("$[1].selling_cost").value(25.0))
                .andExpect(jsonPath("$[1].profit_gained").value(5.0))
                .andExpect(jsonPath("$[2].supplier_name").value("Supplier 3"))
                .andExpect(jsonPath("$[2].item").value("Item 3"))
                .andExpect(jsonPath("$[2].item_code").value("IC3"))
                .andExpect(jsonPath("$[2].manufacting_cost").value(30.0))
                .andExpect(jsonPath("$[2].selling_cost").value(35.0))
                .andExpect(jsonPath("$[2].profit_gained").value(5.0));
    }
    @Test
    void testGetApplication() {
        Long id = 1L;
        supplier mockSupplier = new supplier(2L, "item", "i1","1", "2", "3", "4");
        mockSupplier.setId(id);
        mockSupplier.setSupplier_name("Mock Supplier");
        mockSupplier.setItem("Mock Item");
        mockSupplier.setItem_code("MCK01");
 
        // Mock the repository method call
        Mockito.when(nutritionRepository.findById(id)).thenReturn(Optional.of(mockSupplier));
 
        // Call the controller method
        Optional<supplier> supplier = nutritionController.getApplication(id);
 
        // Verify the response
        assertThat(supplier.isPresent()).isTrue();
        assertThat(supplier.get().getId()).isEqualTo(id);
        assertThat(supplier.get().getSupplier_name()).isEqualTo("Mock Supplier");
        assertThat(supplier.get().getItem()).isEqualTo("Mock Item");
        assertThat(supplier.get().getItem_code()).isEqualTo("MCK01");
    }
	
	@Test public void testCreateSupplier() throws Exception { supplier
	 mockSupplier = new supplier(1L, "Supplier1", "Item1", "IC1", "10.00",
	  "20.00", "10.00");
	  when(nutritionRepository.save(any(supplier.class))).thenReturn(mockSupplier);
	  supplier result = nutritionController.create(mockSupplier);
	  verify(nutritionRepository, times(1)).save(mockSupplier); 
	 assertEquals(mockSupplier,result); }

	private boolean assertEquals(supplier mockSupplier, supplier result) {
		if(mockSupplier.equals(result))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	 

   

}
