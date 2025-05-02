package com.tenpo.demo.application.usecase;

import com.tenpo.demo.domain.Calculate;
import com.tenpo.demo.domain.port.IPercentageClientService;
import org.springframework.stereotype.Component;

@Component
public class CalculateUseCase implements  ICalculateUseCase {

    private final IPercentageClientService percentageClientService;

    public CalculateUseCase(IPercentageClientService percentageClientService) {
        this.percentageClientService = percentageClientService;
    }


    /**
     * This method calculates the percentage of number1 with respect to number2.
     *
     * @param calculate the first number
     * @return the percentage of number1 with respect to number2
     * @throws IllegalArgumentException if any of the numbers is null
     * @throws ArithmeticException if division by zero is attempted
     */
    @Override
    public Double calculatePercentage(Calculate calculate) {
        return (calculate.getNumber1() + calculate.getNumber2() ) * percentageClientService.fetchPercentageFromExternalService();
    }
}
