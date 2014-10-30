package com.unrc.app;

import com.unrc.app.models.City;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
import static org.junit.Assert.assertEquals;
 
public class CityTest{

    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/carsapp_test", "root", "root");
        System.out.println("CityTest setup");
        Base.openTransaction();
    }

    @After
    public void after(){
        System.out.println("CityTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }

    @Test
    public void shouldValidateMandatoryFields(){
        City city = new City();

        the(city).shouldNotBe("valid");
        the(city.errors().get("name")).shouldBeEqual("value is missing");
        the(city.errors().get("postal_code")).shouldBeEqual("value is missing");

        city.set("name", "General Deheza", "postal_code", 5923);
        city.save();
        // Everything is good:
        the(city).shouldBe("valid");
    }
}

