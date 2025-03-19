package com.vijay.learning.useroperations;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserOperationsApplicationTests {

	@Test
	void contextLoads() {
	}


    /**
     * Test the main method of UserOperationsApplication
     * Verifies that the application starts successfully and logs the expected message
     */
    @Test
    @ExtendWith(OutputCaptureExtension.class)
    public void testMainMethodStartsApplicationSuccessfully(CapturedOutput output) {
        UserOperationsApplication.main(new String[]{});
        assertTrue(output.toString().contains("UserOperations Application Started successfully !!!"));
    }
}
