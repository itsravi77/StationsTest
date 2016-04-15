package com.hsbc.compliance.stations;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by lbajor on 2016-04-15.
 */
public class TrainStationsShould {
    @Test
    public void returnDartfordAndDartmouthWhenLookingForDART() {
        expect().
                body("name", equalTo("DARTFORD")).
        when().
                get("/stattion/search/DART");
    }
}
