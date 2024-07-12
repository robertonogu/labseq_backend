package altice_labs.labseq.controllers;

import altice_labs.labseq.services.LabSeqService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LabSeqControllerTest {

    @Autowired
    private LabSeqService labSeqService;

    @BeforeEach
    void setUp() {
        labSeqService.init();
    }

    @Test
    public void testGetValueFromSequence() {
        LabSeqController labSeqController = new LabSeqController(labSeqService);

        ResponseEntity<?> responseEntity = labSeqController.getValueFromSequence(5);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(BigInteger.ONE, responseEntity.getBody());
    }

    @Test
    public void testNegativeIndex() {
        LabSeqController labSeqController = new LabSeqController(new LabSeqService());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> labSeqController.getValueFromSequence(-1));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Index should be a non-negative integer number.", exception.getReason());
    }
}

