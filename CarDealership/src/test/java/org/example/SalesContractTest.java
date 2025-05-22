package org.example;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.unit.;

import static org.junit.jupiter.api.Assertions.*;

class SalesContractTest {
    @Test
    public void isSold_isFinaced(){
        Vehicle vehicle=new Vehicle(42451,2001,"tesla","X",VehicleType.valueOf("sedan")
                ,"Orange",45938,300);
        SalesContract salesContract=new SalesContract("2020/20/2","Will","will@aol.com  ",vehicle,true);
                double sales=salesContract.getMonthlyPayment();
                assertEquals(31.15,sales);  //close enough
    }
}