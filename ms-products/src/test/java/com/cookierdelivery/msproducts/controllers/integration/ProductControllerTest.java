package com.cookierdelivery.msproducts.controllers.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

import com.cookierdelivery.msproducts.models.Product;
import com.cookierdelivery.msproducts.repositories.ProductRepository;
import com.cookierdelivery.msproducts.util.AbstractIntegrationTest;
import java.text.MessageFormat;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@Slf4j
@Sql(
    scripts = "/db/controller/integration/product/data.sql",
    config = @SqlConfig(transactionMode = ISOLATED))
@Sql(
    scripts = "/db/controller/integration/product/clear-data.sql",
    config = @SqlConfig(transactionMode = ISOLATED),
    executionPhase = AFTER_TEST_METHOD)
public class ProductControllerTest extends AbstractIntegrationTest {

  private static final String RESOURCE = "products";

  @Autowired private TestRestTemplate restTemplate;
  @Autowired private ProductRepository repository;

  @Test
  public void shouldReturnProductListWhenGetAllProducts() {
    var response = restTemplate.exchange(buildUrl(), HttpMethod.GET, HttpEntity.EMPTY, List.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().size()).isEqualTo(4);
  }

  @Test
  public void shouldReturnProductWhenGetProductById() {
    Long id = 1L;
    String name = "Notebook";
    String model = "Acer";
    String code = "0001";
    Double price = 2020.0;

    var response =
        restTemplate.exchange(buildUrl(id), HttpMethod.GET, HttpEntity.EMPTY, Product.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getId()).isEqualTo(id);
    assertThat(response.getBody().getName()).isEqualTo(name);
    assertThat(response.getBody().getModel()).isEqualTo(model);
    assertThat(response.getBody().getCode()).isEqualTo(code);
    assertThat(response.getBody().getPrice()).isEqualTo(price);
  }

  @Test
  public void shouldReturnStatusNotFoundWhenProductIdNonExisting() {
    Long id = 999L;

    var response =
        restTemplate.exchange(buildUrl(id), HttpMethod.GET, HttpEntity.EMPTY, Product.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  public void shouldReturnProductWhenGetProductByCode() {
    Long id = 1L;
    String name = "Notebook";
    String model = "Acer";
    String code = "0001";
    Double price = 2020.0;

    var response =
        restTemplate.exchange(
            buildUrl("by", "code", code), HttpMethod.GET, HttpEntity.EMPTY, Product.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getId()).isEqualTo(id);
    assertThat(response.getBody().getName()).isEqualTo(name);
    assertThat(response.getBody().getModel()).isEqualTo(model);
    assertThat(response.getBody().getCode()).isEqualTo(code);
    assertThat(response.getBody().getPrice()).isEqualTo(price);
  }

  @Test
  public void shouldReturnStatusNotFoundWhenCodeNonExisting() {
    String code = "9999";

    var response =
        restTemplate.exchange(
            buildUrl("by", "code", code), HttpMethod.GET, HttpEntity.EMPTY, Product.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  public void shouldAddAProductWhenPostProduct() {
    String name = "Novo Notebook";
    String model = "iMac";
    String code = "0005";
    Double price = 99.0;
    var product = createProduct(name, model, code, price);

    var response =
        restTemplate.exchange(buildUrl(), HttpMethod.POST, buildRequest(product), Product.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody().getId()).isNotNull();
    assertThat(response.getBody().getName()).isEqualTo(name);
    assertThat(response.getBody().getModel()).isEqualTo(model);
    assertThat(response.getBody().getCode()).isEqualTo(code);
    assertThat(response.getBody().getPrice()).isEqualTo(price);
  }

  @Test
  public void shouldDeleteAProductWhenDeleteProductById() {
    Long id = 2L;

    var response =
        restTemplate.exchange(buildUrl(id), HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    assertThat(repository.existsById(id)).isFalse();
  }

  @Test
  public void shouldUpdateAProductWhenPutProductById() {
    Long id = 3L;
    String name = "Novo Notebook";
    String model = "iMac";
    String code = "0005";
    Double price = 99.0;
    var product = createProduct(name, model, code, price);

    var response =
        restTemplate.exchange(buildUrl(id), HttpMethod.PUT, buildRequest(product), Product.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getName()).isEqualTo(name);
    assertThat(response.getBody().getModel()).isEqualTo(model);
    assertThat(response.getBody().getCode()).isEqualTo(code);
    assertThat(response.getBody().getPrice()).isEqualTo(price);
  }

  @Test
  public void shouldReturnNotFoundWhenUpdateAProductNonExisting() {
    Long id = 999L;

    var response =
        restTemplate.exchange(
            buildUrl(id), HttpMethod.PUT, buildRequest(new Product()), Product.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  private Product createProduct(String name, String model, String code, Double price) {
    var product = new Product();
    product.setName(name);
    product.setModel(model);
    product.setCode(code);
    product.setPrice(price);

    return product;
  }

  private HttpEntity<Object> buildRequest(Object object) {
    return new HttpEntity<>(object);
  }

  private String buildUrl(String path, String param, String value) {
    return MessageFormat.format("/{0}/{1}?{2}={3}", buildUrl(), path, param, value);
  }

  private String buildUrl(Long id) {
    return MessageFormat.format("{0}/{1}", buildUrl(), id);
  }

  private String buildUrl() {
    return MessageFormat.format("/{0}", RESOURCE);
  }
}
