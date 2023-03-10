package tests.demoqa;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class PracticeFormTests extends TestBase {

// gradle clean practice_form_test -Dremote_url="https://user1:1234@selenoid.autotests.cloud/wd/hub" -D....
    @Test
    @Tag("practice_form")
    void practiceFormTest() {

        String firstName = "anna";
        String lastName = "Petrova";
        String email = "anna@gmail.com";
        String mobile = "9166789010";
        String subject1 = "Chemistry";
        String subject2 = "Maths";
        String currentAddress = "Moscow";
        String submitText = "Thanks for submitting the form";

        step("Open registration form", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");
        });
        step("Fill form", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $("#genterWrapper").$(byText("Female")).click();
            $("#userNumber").setValue(mobile);

            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("December");
            $(".react-datepicker__year-select").selectOption("1991");
            $(".react-datepicker__day--005:not(.react-datepicker__day--outside-month)").click();

            $("#subjectsInput").setValue(subject1).pressEnter().setValue(subject2).pressEnter();

            $("#hobbiesWrapper").$(byText("Sports")).click();
            $("#hobbiesWrapper").$(byText("Music")).click();

            $("#uploadPicture").uploadFromClasspath("searchElements.jpg");

            $("#currentAddress").setValue(currentAddress);
            $("#state").click();
            $("#stateCity-wrapper").$(byText("NCR")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Gurgaon")).click();

            $("#submit").click();
        });
        step("Check form results", () -> {
            $(".modal-dialog").should(appear);
            $(".modal-header").shouldHave(text(submitText));

            $(".modal-body").shouldHave(text(firstName), text(lastName), text(email), text("Female"), text(mobile),
                text("05 December,1991"), text(subject1), text(subject2), text("Sports, Music"), text("searchElements.jpg"), text(currentAddress), text("NCR Gurgaon"));
            $(".modal-body").shouldNotHave(text("Reading"));
        });

    }
}
