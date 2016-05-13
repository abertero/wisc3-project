package cl.wisc3.web.services;

import cl.wisc3.beans.AgeDetails;
import cl.wisc3.calculators.AgeCalculator;
import org.springframework.stereotype.Service;

@Service
public class AgeCalculatorService {

    public AgeDetails calculateAge(AgeDetails testDateAgeDetail, AgeDetails birthDateAgeDetail) {
        return AgeCalculator.INSTANCE.calculate(testDateAgeDetail, birthDateAgeDetail);
    }
}
