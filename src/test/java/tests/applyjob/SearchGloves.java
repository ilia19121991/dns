package tests.applyjob;

import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SearchGloves {
    @DisplayName("Проверка корректной работы поиска через поисковую строку")
    @Test
    void searchBySearchStringTest() {

        step("Открываем сайт главную страницу DNS", () -> {
            open("https://www.dns-shop.ru/");
        });
        step("Вводим в поисковую строку слово Перчатки", () -> {
            $("[name=q]").setValue("Перчатки").pressEnter();
        });
        step("Проверяем, что вывелись результаты именно по запросу 'перчатки'", () -> {
            $(".products-list__group-title").shouldHave(text("Перчатки"));
        });

    }

    @DisplayName("Проверка корректной работы поиска по категориям")
    @Test
    void searchByCategoryTest() {

        step("Открываем сайт главную страницу DNS", () -> {
            open("https://www.dns-shop.ru/");
        });
        step("Выбираем пункт 'Смартфоны и Фототехника'", () -> {
            $(".menu-desktop").find(byText("Смартфоны и фототехника")).click();
        });
        step("Проверяем, что открылся нужный раздел ", () -> {
            $(".subcategory__page-title").shouldHave(text("Смартфоны и фототехника"));
        });

    }

    @DisplayName("Проверка корректной работы поиска магазинов")
    @Test
    void searchShopsTest() {
        step("Открываем сайт главную страницу DNS", () -> {
            open("https://www.dns-shop.ru/");
        });
        step("Выбираем пункт 'Магазины'", () -> {
            $(".header-top-menu").find(byText("Магазины")).click();
        });
        step("Выбиваем с строку поиска по магазинам 'белая дача'", () -> {
            $("[data-role=shop-search-field]").setValue("белая дача");
        });
        step("Проверяем, что в выдаче есть магазин DNS в Мега Белая Дача", () -> {
            $(".shop-list-item:not(.hidden)").shouldHave(text("белая дача"));
        });
    }

    @DisplayName("Проверка корректной работы поиска магазинов")
    @Test
    void seacrhShopsTest() {
        step("Открываем сайт главную страницу DNS", () -> {
            open("https://www.dns-shop.ru/");
        });
        step("Выбираем пункт 'Магазины'", () -> {
            $(".header-top-menu").find(byText("Магазины")).click();
        });
        step("Выбиваем с строку поиска по магазинам 'белая дача'", () -> {
            $("[data-role=shop-search-field]").setValue("белая дача");
        });
        step("Проверяем, что в выдаче есть магазин DNS в Мега Белая Дача", () -> {
            $(".shop-list-item:not(.hidden)").shouldHave(text("белая дача"));
        });

    }

}
