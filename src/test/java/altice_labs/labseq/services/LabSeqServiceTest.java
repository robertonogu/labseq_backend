package altice_labs.labseq.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LabSeqServiceTest {

    @Autowired
    private LabSeqService labSeqService;

    @BeforeEach
    void setUp() {
        labSeqService.init();
    }

    @Test
    public void testGetValueFromSequence() {

        // Test cases for known values

        assertEquals(BigInteger.ZERO, labSeqService.getValueFromSequence(0));
        assertEquals(BigInteger.ONE, labSeqService.getValueFromSequence(1));
        assertEquals(BigInteger.ZERO, labSeqService.getValueFromSequence(2));
        assertEquals(BigInteger.ONE, labSeqService.getValueFromSequence(3));
        assertEquals(BigInteger.ONE, labSeqService.getValueFromSequence(4));
        assertEquals(BigInteger.ONE, labSeqService.getValueFromSequence(5));
        assertEquals(BigInteger.ONE, labSeqService.getValueFromSequence(6));
        assertEquals(BigInteger.TWO, labSeqService.getValueFromSequence(7));
        assertEquals(BigInteger.TWO, labSeqService.getValueFromSequence(8));
        assertEquals(BigInteger.TWO, labSeqService.getValueFromSequence(9));
        BigInteger three = BigInteger.ONE.add(BigInteger.TWO);
        assertEquals(three, labSeqService.getValueFromSequence(10));
    }

    @Test
    public void testNegativeIndex() {

        // Test case for negative input

        assertThrows(ResponseStatusException.class, () -> labSeqService.getValueFromSequence(-1));
    }
}