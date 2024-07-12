package altice_labs.labseq.controllers;

import altice_labs.labseq.services.LabSeqService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabSeqController {

    private final LabSeqService labSeqService;

    /**
     * Constructor to assign service to the instance received
     * @param labSeqService - receives an instance of the service
     */
    public LabSeqController(LabSeqService labSeqService) {
        this.labSeqService = labSeqService;
    }

    /**
     * This method handle HTTP GET request to retrieve the value calculated from the sequence
     * @param index - index of the sequence
     * @return response entity with status code 200 with the value calculated from the sequence
     */
    @GetMapping("/labseq/{n}")
    public ResponseEntity<?> getValueFromSequence(@PathVariable("n") int index) {
        return ResponseEntity.ok(labSeqService.getValueFromSequence(index));
    }

}