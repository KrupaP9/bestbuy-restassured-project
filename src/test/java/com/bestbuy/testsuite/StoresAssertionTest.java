package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest extends TestBase {
    static ValidatableResponse response;

    public StoresAssertionTest() {
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }


    //Assert
    //1. Verify the if the total is equal to 1561
    @Test
    public void verifyTotal() {
        response.body("total", equalTo(1561));
    }

    //2. Verify the if the stores of limit is equal to 10
    @Test
    public void verifyStoreLimit() {
        response.body("limit", equalTo(10));
    }

    //3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void verifyNameInInverGrove() {
        response.body("data[1]", hasKey("name"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void verifyNamesInRoseville() {
        response.body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"));
    }

    //5. Verify the storied=7 inside storeservices of the third store of second services
    @Test
    public void verifyStoreId() {
        response.body("data[2].services[1].storeservices.storeId", equalTo(7));
    }

    //6. Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    @Test
    public void verifyStateValue() {
        response.body("data.find{it.name=='Roseville'}.services.storeservices.find{it.createdAt}", hasKey("createdAt"));
    }

    //7. Verify the state = MN of forth store
    @Test
    public void verifyStateFourthStore() {
        response.body("data[3].state", equalTo("MN"));
    }

    //8. Verify the store name = Rochester of 9th store
    @Test
    public void verifyStoreName() {
        response.body("data[8].name", equalTo("Rochester"));
    }

    //9. Verify the storeId = 11 for the 6th store
    @Test
    public void verifyStoreIdFor() {
        response.body("data[5].id", equalTo(11));
    }

    //10. Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void verifyServiceId() {
        response.body("data[6].services[3].storeservices.serviceId", equalTo(4));
    }
    //Extraction Example
    //1. Extract the limit
    //2. Extract the total
    //3. Extract the name of 5th store
    //4. Extract the names of all the store
    //5. Extract the storeId of all the store
    //6. Print the size of the data list
    //7. Get all the value of the store where store name = St Cloud
    //8. Get the address of the store where store name = Rochester
    //9. Get all the services of 8th store
    //10. Get storeservices of the store where service name = Windows Store
    //11. Get all the storeId of all the store
    //12. Get id of all the store
    //13. Find the store names Where state = ND
    //14. Find the Total number of services for the store where store name = Rochester
    //15. Find the createdAt for all services whose name = “Windows Store”
    //16. Find the name of all services Where store name = “Fargo”
    //17. Find the zip of all the store
    //18. Find the zip of store name = Roseville
    //19. Find the storeservices details of the service name = Magnolia Home Theater
    //20. Find the lat of all the stores
}
