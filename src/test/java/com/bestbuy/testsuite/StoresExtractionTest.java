package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001(){
        int limit =response.extract().path("limit");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The value of limit is : "+limit);
        System.out.println("------------------End of Test---------------------------");
    }
    //2.Extract the total
    @Test
    public void test002(){
        int total =response.extract().path("total");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The value of the total is : "+total);
        System.out.println("------------------End of Test---------------------------");
    }
    //3.Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The name of 5th stores is : "+name);
        System.out.println("------------------End of Test---------------------------");
    }
    //4.Extract the names of all the store
    @Test
    public void test004() {
        List<Object> storeNames = response.extract().path("data.name");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The names of all the stores are : " + storeNames);
        System.out.println("------------------End of Test---------------------------");
    }
    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<Object> storeIDs = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The the storeId of all the stores are : " + storeIDs);
        System.out.println("------------------End of Test---------------------------");
    }
    //6. Print the size of the data list
    @Test
    public void test006() {
        List<Object> dataSize = response.extract().path("data.");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The size of the data list is : " + dataSize.size());
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String,Object>> values =response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The values for store name 'St Cloud' are: "+values);
        System.out.println("------------------End of Test---------------------------");
    }
    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<String> address =response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The address of the store where store name = Rochester is : "+ address);
        System.out.println("------------------End of Test---------------------------");
    }
    //9.Get all the services of 8th store
    @Test
    public void test009() {
        List<HashMap<String,Object>> services =response.extract().path("data[7].services");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The all the services of 8th products are : "+services);
        System.out.println("------------------End of Test---------------------------");
    }
    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        List<HashMap<String,Object>> storeServices =response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The storeservices of the store where service name = Windows Store are: "+storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {
        List<Object> storeIDs = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The the storeId of all the stores are : " + storeIDs);
        System.out.println("------------------End of Test---------------------------");
    }
    //12. Get id of all the store
    @Test
    public void test012() {
        List<Object> storeIDs = response.extract().path("data.id");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The storeId of all the stores are : " + storeIDs);
        System.out.println("------------------End of Test---------------------------");
    }
    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        List<String> state =response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The store names Where state = ND: "+state);
        System.out.println("------------------End of Test---------------------------");
    }
    //14.Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        int totalServices =response.extract().path("data.find{it.name == 'Rochester'}.services.size");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The Total number of services for the store where store name = Rochester is : "+totalServices);
        System.out.println("------------------End of Test---------------------------");
    }
    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test0115() {
        List<String> createdAt =response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.createdAt");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The createdAt for all services whose name = “Windows Store”: "+createdAt);
        System.out.println("------------------End of Test---------------------------");
    }
    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<String> name =response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The name of all services Where store name = “Fargo” are : "+name);
        System.out.println("------------------End of Test---------------------------");
    }
    //17. Find the zip of all the store
    @Test
    public void test017() {
        List<Object> zipCode = response.extract().path("data.zip");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The zip of all the store are : " + zipCode);
        System.out.println("------------------End of Test---------------------------");
    }
    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<String> zip =response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The zip of store name = Roseville is : "+zip);
        System.out.println("------------------End of Test---------------------------");
    }
    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<HashMap<String,Object>> storeServiceDetails =response.extract().path("data.services*.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The storeservices details of the service name = Magnolia Home Theater are : "+storeServiceDetails);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void test020() {
        List<Object> lat = response.extract().path("data.lat");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The zip of all the store are : " + lat);
        System.out.println("------------------End of Test---------------------------");
    }
}
