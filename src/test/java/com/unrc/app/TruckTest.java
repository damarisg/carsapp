package com.unrc.app;

import com.unrc.app.models.Vehicle;
import com.unrc.app.models.Truck;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;													


public class TruckTest{
    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_test", "root", "root");
                                                          
        Base.openTransaction();
    }

    @After
    public void after(){
        System.out.println("TruckTest tearDown");				
        Base.rollbackTransaction();
        Base.close();
    }

    @Test
    public void shouldValidateMandatoryFields(){
      Truck  truck= new Truck();

       // check errors
        the(truck).shouldNotBe("valid");
        the(truck.errors().get("id_vehicle")).shouldBeEqual("value is missing");
        the(truck.errors().get("count_belt")).shouldBeEqual("value is missing");
		the(truck.errors().get("id")).shouldBeEqual("value is missing");


        Vehicle vehicle= new Vehicle();
    	 vehicle.set("patent", "HDK526", "kind", "307", "mark", "Fiat", "description"," Combustible: Diesel, Color: Negro. Sin uso. Muy pocos kilómetros. Excelente estado. Alarma. Papeles al día. Listo para transferir. ", "status", "Sell", "price", 32.393, "user_id",1, "city_id", 1);      
		  vehicle.save();

		// Create Vehicle
    	 truck.set("id",1, "id_vehicle", vehicle.get("patent"), "count_belt", 1234);
         truck.save();

		System.out.println(truck);

        // Everything is good:
        the(truck).shouldBe("valid");

    }
}


 
