package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("products")
                .then().statusCode(200);
    }
    //21. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The value of the limit is : "+limit);
        System.out.println("------------------End of Test---------------------------");
    }
    //22. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The value of the total is : "+total);
        System.out.println("------------------End of Test---------------------------");
    }
    //23. Extract the name of 5th product
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The name of the 5th product is : "+name);
        System.out.println("------------------End of Test---------------------------");
    }
    //24. Extract the names of all the products
    @Test
    public void test004() {
        List<Object> productNames = response.extract().path("data.name");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The names of all products are : " + productNames);
        System.out.println("------------------End of Test---------------------------");
    }
    //25. Extract the productId of all the products
    @Test
    public void test005() {
        List<Object> productIDs = response.extract().path("data.id");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The the productId of all the products are : " + productIDs);
        System.out.println("------------------End of Test---------------------------");
    }
    //26. Print the size of the data list
    @Test
    public void test006() {
        List<Object> dataSize = response.extract().path("data.");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The size of the data list is : " + dataSize.size());
        System.out.println("------------------End of Test---------------------------");
    }
    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test007() {
        List<HashMap<String,Object>> values =response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The values for product name 'Energizer - MAX Batteries AA (4-Pack)' are: "+values);
        System.out.println("------------------End of Test---------------------------");
    }
    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test008() {
        List<HashMap<String,?>> modelName =response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The model for product name 'Energizer - N Cell E90 Batteries (2-  Pack)' are: "+ modelName);
        System.out.println("------------------End of Test---------------------------");
    }
    //29. Get all the categories of 8th products
    @Test
    public void test009() {
        List<HashMap<String,Object>> categoriesOfProducts =response.extract().path("data[7].categories");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The categories of 8th products are: "+categoriesOfProducts);
        System.out.println("------------------End of Test---------------------------");
    }
    //30. Get categories of the store where product id = 150115
    @Test
    public void test010() {
        List<HashMap<String,Object>> categoriesOfProduct =response.extract().path("data.findAll{it.id == 150115}.categories");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The categories of the store where id is 150115 are: "+categoriesOfProduct);
        System.out.println("------------------End of Test---------------------------");
    }
    //31. Get all the descriptions of all the products
    @Test
    public void test011() {
        List<HashMap<String,Object>> description =response.extract().path("data.description");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The all the descriptions of all the products are: "+description);
        System.out.println("------------------End of Test---------------------------");
    }
    //32. Get id of all the all categories of all the products
    @Test
    public void test012() {
        List<HashMap<String,Object>> idsCategories =response.extract().path("data.categories.id");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The id of all the all categories of all the products are: "+idsCategories);
        System.out.println("------------------End of Test---------------------------");
    }
    //33. Find the product names Where type = HardGood
    @Test
    public void test013() {
        List<String> names =response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The product names where the type is HardGood are: "+names);
        System.out.println("------------------End of Test---------------------------");
    }
    //34. Find the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014() {
        int totalCategories =response.extract().path("data.find{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories.size");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack) is : "+totalCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test015() {
        List<String> values =response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The createdAt for all products whose price < 5.49 are: "+values);
        System.out.println("------------------End of Test---------------------------");
    }
    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test016() {
        List<String> categoriesName =response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The name of all categories where product name is “Energizer - MAX Batteries AA (4-Pack)” are : "+categoriesName);
        System.out.println("------------------End of Test---------------------------");
    }
    //37. Find the manufacturer of all the products
    @Test
    public void test017() {
        List<Object> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The manufacturer of all the products are : " + manufacturer);
        System.out.println("------------------End of Test---------------------------");
    }
    //38. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test018() {
        List<String> imageProduct =response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The image of products whose manufacturer is = Energizer : "+imageProduct);
        System.out.println("------------------End of Test---------------------------");
    }
    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019() {
        List<String> createdAt =response.extract().path("data.findAll{it.price < 5.99}.categories.createdAt");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The createdAt for all products whose price < 5.49 are: "+createdAt);
        System.out.println("------------------End of Test---------------------------");
    }
    //40. Find the uri of all the products
    @Test
    public void test020() {
        List<String> url =response.extract().path("data.url");
        System.out.println("------------------Starting Test---------------------------");
        System.out.println("The url of all the products are: "+url);
        System.out.println("------------------End of Test---------------------------");
    }

}
