package altice_labs.labseq.services;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabSeqService {

    private List<BigInteger> cache;

    /**
     * Assignment of the first values in the sequence
     */
    @PostConstruct
    public void init() {
        cache = new ArrayList<>();
        cache.add(0, BigInteger.ZERO);
        cache.add(1, BigInteger.ONE);
        cache.add(2, BigInteger.ZERO);
        cache.add(3, BigInteger.ONE);
    }

    /**
     * This method is the only entry to the service
     * Receives the index from the controller
     * @param index - index received to calculate the value of sequence
     * @return the value calculated from the sequence
     */
    public BigInteger getValueFromSequence(int index) {
        this.validateIndex(index);

        return calculateValue(index);
    }

    /**
     * This method is used to validate the index received
     * If index is negative, is thrown an exception
     * Otherwise, the process to calculate the value of sequence continues
     * @param index - index received to calculate the value of sequence
     */
    private void validateIndex(int index) {
        if (index < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Index should be a non-negative integer number.");
    }

    /**
     * This method calculate the value from the sequence
     * @param index - index received to calculate the value of sequence
     * @return string that has represented the number in scientific notation
     */
    private synchronized BigInteger calculateValue(int index) {
        if (index >= 0 && index < cache.size())
            return cache.get(index);

        BigInteger value;
        int size = cache.size();
        for (int i = size; i <= index; i++) {
            value = cache.get(i - 4).add(cache.get(i - 3));
            cache.add(i, value);
        }

        return cache.get(index);
    }

}