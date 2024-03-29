import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class _02_ZippoTestExtract {

    @Test
    public void extractingJsonpath() {

        String countryName =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")
                        .then()
                        .extract().path("country")// PATH i country olan degeri EXTRACT yap

                ;

        System.out.println("country =" + countryName);
        Assert.assertEquals(countryName, "United States");// alinan deger buna esit mi
    }


    // Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
    // place dizisinin ilk elemanının state değerinin  "California"
    // olduğunu testNG Assertion ile doğrulayınız

    @Test
    public void extractingJsonpath2() {

        String stateName =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")
                        .then()
                        .extract().path("places[0].state")// PATH i country olan degeri EXTRACT yap

                ;

        System.out.println("stateNAme =" + stateName);
        Assert.assertEquals(stateName, "California");// alinan deger buna esit mi
    }


    // Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
    // place dizisinin ilk elemanının place name değerinin  "Beverly Hills"
    // olduğunu testNG Assertion ile doğrulayınız


    @Test
    public void extractingJsonpath3() {

        String stateName =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")
                        .then()
                        .extract().path("places[0].'place name'")// PATH i country olan degeri EXTRACT yap

                ;

        System.out.println("placeName =" + stateName);
        Assert.assertEquals(stateName, "Beverly Hills");// alinan deger buna esit mi
    }

    // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
    // limit bilgisinin 10 olduğunu testNG ile doğrulayınız.

    @Test
    public void extractingJsonPath4() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // limit bilgisinin 10 olduğunu testNG ile doğrulayınız.

        int limit =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("meta.pagination.limit");
        ;

        System.out.println("limit = " + limit);
        Assert.assertTrue(limit == 10);
    }

    @Test
    public void extractingJsonPath5() {

        List<Integer> idler =
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("data.id");
        System.out.println("idler = " + idler);
    }

    //yukaridaki testten sonra ayni endpointin sonunda donen butun name leri yazdiriniz

    @Test
    public void extractingJsonPath6() {

        List<String> name =
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("data.name");
        System.out.println("name= " + name);
    }
    @Test
    public void extractingJsonPathResponsAll(){

        Response donenData=
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().response()
                ;

        List<Integer> idler= donenData.path("data.id");
        List<String> isimler= donenData.path("data.name");
        int limit = donenData.path("meta.pagination.limit");

        System.out.println("idler = " + idler);
        System.out.println("isimler = " + isimler);
        System.out.println("limit = " + limit);

        Assert.assertTrue(isimler.contains("Mahesh Menon"));
        Assert.assertTrue(idler.contains(5599126));
        Assert.assertTrue(limit==10);

    }
}

