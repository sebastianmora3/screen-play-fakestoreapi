package co.com.calidad.practica.automatizacion.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/get_all_product.feature",
                "src/test/resources/features/delete_product.feature",
                "src/test/resources/features/user_login.feature",
                "src/test/resources/features/update_cart.feature"
        },
        glue = "co.com.calidad.practica.automatizacion.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class Runner {}