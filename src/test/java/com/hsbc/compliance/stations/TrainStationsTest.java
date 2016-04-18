package com.hsbc.compliance.stations;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TrainStationsTest {

    private static Server server;
    private static int port = 8080;

    @BeforeClass
    public static void setup() throws Exception {
        server = new Server(port);

        WebAppContext context = new WebAppContext();
        context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase("src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);

        // Start Server
        server.start();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void returnDartfordAndDartmouthWhenLookingForDART() {
        expect()
                .statusCode(200)
                .content(equalTo("{\"stations\":[{\"name\":\"DARTFORD\"},{\"name\":\"DARTMOUTH\"}]}")).
        when().
                get("/station/search/DART");
    }

    @Test
    public void returnLiverpoolAndLiverpoolStreetWhenLookingForLiverpool() {
        expect()
                .statusCode(200)
                .content(equalTo("{\"stations\":[{\"name\":\"LIVERPOOL\"},{\"name\":\"LIVERPOOL LIME STREET\"}]}")).
        when()
                .get("/station/search/Liverpool");
    }

    @Test
    public void returnJustOneDerbyForDerby() {
        expect()
                .statusCode(200)
                .content(equalTo("{\"stations\":[{\"name\":\"DERBY\"}]}")).
        when()
                .get("/station/search/Derby");
    }

    @Test
    public void returnEmptyForNonExistentStation() {
        expect()
                .statusCode(200)
                .content(equalTo("{\"stations\":[]}")).
        when()
                .get("/station/search/Kings Cross");
    }

    @Test
    public void returnAllStationsForSearchAllRequest() {
        expect()
                .statusCode(200)
                .content(equalTo("{\"stations\":[{\"name\":\"DARTFORD\"},{\"name\":\"DARTMOUTH\"},{\"name\":\"TOWER HILL\"},{\"name\":\"DERBY\"},{\"name\":\"LIVERPOOL\"},{\"name\":\"LIVERPOOL LIME STREET\"},{\"name\":\"PADDINGTON\"},{\"name\":\"EUSTON\"},{\"name\":\"LONDON BRIDGE\"},{\"name\":\"VICTORIA\"}]}")).
        when()
                .get("/station/searchAll");
    }

    @Test
    public void returnServiceIsUpForHealthCheck() {
        expect()
                .statusCode(200)
                .content(equalTo("Service is up.")).
        when().
                get("/station/health");
    }

    @Test
    public void returnErrorCode404ForNonExistenceService() {
        given().port(8080).then().statusCode(503).
        expect()
                .statusCode(404).
        when()
                .get("/station/searchAllThatDoesNotExist");
    }

}
