package com.kiran.apiautomation;

import com.kiran.utils.EndPoints;
import com.kiran.utils.Utils;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static io.restassured.RestAssured.given;

public class ApiTest {

    private Utils utils;

    @BeforeClass(alwaysRun = true)
    public void init() {
        utils = new Utils();
    }

    @DataProvider(name="ValidData")
    public Iterator<Object[]> allValid() {
        return utils.readFromCsv("./input/movies.csv");
    }

    @Test(description = "Positive test for get books api")
    public void testGetBooks() {

        try {
            RestAssured.baseURI = utils.getProperty("baseURI");
            Response getBooksResponse =
                    given().
                            log().all().
                    when().
                            get(EndPoints.GET_BOOKS).

                    then().
                            assertThat().statusCode(200).log().all().extract().response();

            JSONParser parser = new JSONParser();
            JSONObject jObject = (JSONObject) parser.parse(getBooksResponse.asString());
            JSONArray jArray = (JSONArray) jObject.get("docs");
            Assert.assertEquals(jArray.size()+"","3");
            Assert.assertEquals(jObject.get("total")+"",jArray.size()+"","Mismatch in book count");

        } catch (Exception e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test(description = "Negative test for get movies api")
    public void negativeTestGetMovies() {

        try {
                RestAssured.baseURI = utils.getProperty("baseURI");

//              Passing invalid bearer token in the header to validate negative scenario
                Header header = new Header("Authorization",utils.getProperty("bearerToken"));

                Response getMoviesResponse =
                        given().
                                header(header).log().all().
                        when().
                                get(EndPoints.GET_MOVIES).
                        then().
                                assertThat().statusCode(401).log().all().extract().response();

                JSONParser jParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jParser.parse(getMoviesResponse.asString());
                Assert.assertEquals(jsonObject.get("success"),false);
                Assert.assertEquals(jsonObject.get("message"), "Unauthorized.");

        }
        catch (Exception e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test(description = "Positive test for get movies api")
    public void positiveTestGetMovies() {
        try {
            RestAssured.baseURI = utils.getProperty("baseURI");

            Header header = new Header("Authorization","Bearer 4qAaXynbVomwqHwO6MXW");

            Response getMoviesResponse =
                    given().
                            header(header).log().all().
                    when().
                            get(EndPoints.GET_MOVIES).
                    then().
                            assertThat().statusCode(200).log().all().extract().response();

            JSONParser jParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jParser.parse(getMoviesResponse.asString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("docs");

            Assert.assertEquals(jsonObject.get("total")+"",jsonArray.size()+"","Mismatch in movie count");
        }
        catch (Exception e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test(description = "Positive test for get quote api",
            dataProvider = "ValidData")
    public void getMovieQuote(String movieName, String quoteCount) {
        try {
            String movieId = null;
            RestAssured.baseURI = utils.getProperty("baseURI");

            Header header = new Header("Authorization","Bearer 4qAaXynbVomwqHwO6MXW");

            Response getMoviesResponse =
                    given().
                            header(header).log().all().
                    when().
                            get(EndPoints.GET_MOVIES).
                    then().
                            assertThat().statusCode(200).log().all().extract().response();

            JSONParser jParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jParser.parse(getMoviesResponse.asString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("docs");

            for(int i=0; i<jsonArray.size(); i++) {
                JSONObject movie = (JSONObject)jsonArray.get(i);
                if(movie.get("name").toString().equalsIgnoreCase(movieName)) {
                    movieId = movie.get("_id").toString();
                }
            }

            Response getQuoteResponse =
                    given().
                            header(header).log().all().
                    when().
                            get("/v2/movie/"+movieId+"/quote").
                    then().
                            assertThat().statusCode(200).log().all().extract().response();


            JSONParser jParser1 = new JSONParser();
            JSONObject jsonObject1 = (JSONObject) jParser.parse(getQuoteResponse.asString());
            Assert.assertEquals(jsonObject1.get("total")+"",quoteCount,"Quote count mismatch");
            ;
        }
        catch(Exception e) {
            Assert.fail();
            e.printStackTrace();
        }
    }
}
