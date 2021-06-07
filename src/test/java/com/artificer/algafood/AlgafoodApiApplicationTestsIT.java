/*
 * package com.artificer.algafood;
 * 
 * import static io.restassured.RestAssured.given; import static
 * org.hamcrest.CoreMatchers.equalTo; import static
 * org.hamcrest.CoreMatchers.hasItem; import static
 * org.hamcrest.Matchers.hasSize;
 * 
 * import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
 * import org.junit.runner.RunWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.boot.web.server.LocalServerPort; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.test.context.TestPropertySource; import
 * org.springframework.test.context.junit4.SpringRunner;
 * 
 * import com.artificer.algafood.domain.model.Cozinha; import
 * com.artificer.algafood.domain.repository.CozinhaRepository; import
 * com.artificer.algafood.domain.service.CadastroCozinhaService; import
 * com.artificer.algafood.util.DataBaseCleaner; import
 * com.artificer.algafood.util.ResourceUtils;
 * 
 * import io.restassured.RestAssured; import io.restassured.http.ContentType;
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 * 
 * @TestPropertySource("/application-test.properties") class
 * AlgafoodApiApplicationTestsIT {
 * 
 * private static final int COZINHA_INEXISTENTE = 9999;
 * 
 * @LocalServerPort private Integer port;
 * 
 * private Integer cozinhas;
 * 
 * @Autowired private CadastroCozinhaService cadastroCozinha;
 * 
 * @Autowired private CozinhaRepository cozinhaRepository;
 * 
 * private Cozinha Argentina;
 * 
 * private String payload;
 * 
 * @Autowired private DataBaseCleaner dataBaseCleaner;
 * 
 * @BeforeEach public void setUp() {
 * RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
 * RestAssured.port = port; RestAssured.basePath = "/cozinhas";
 * 
 * payload = ResourceUtils.getContentFromResource("/json/cozinha.json");
 * 
 * dataBaseCleaner.clearTables();
 * 
 * insertData(); }
 * 
 * private void insertData() { Cozinha cozinha = new Cozinha();
 * cozinha.setNome("Alemã"); cozinhaRepository.save(cozinha);
 * 
 * Argentina = new Cozinha(); Argentina.setNome("Argentina");
 * cozinhaRepository.save(Argentina); cozinhas = (int)
 * cozinhaRepository.count(); }
 * 
 * @Test public void testarConsultaCozinhaSuccess() {
 * given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK
 * .value()); }
 * 
 * @Test public void testarConsultaCozinhaPorIdSuccess() {
 * given().pathParam("cozinhaId",
 * Argentina.getId()).accept(ContentType.JSON).when().get("/{cozinhaId}").then()
 * .statusCode(HttpStatus.OK.value()).body("nome",
 * equalTo(Argentina.getNome())); }
 * 
 * @Test public void testarConsultaCozinhaPorIdErros() {
 * given().pathParam("cozinhaId",
 * COZINHA_INEXISTENTE).accept(ContentType.JSON).when().get("/{cozinhaId}").then
 * () .statusCode(HttpStatus.NOT_FOUND.value()); }
 * 
 * @Test public void testarConsultaCozinhasSuccess() {
 * given().accept(ContentType.JSON).when().get().then().body("",
 * hasSize(cozinhas)).body("nome", hasItem("Argentina"));
 * 
 * }
 * 
 * @Test public void testarCadastrarCozinhaSuccess() {
 * given().body(payload).contentType(ContentType.JSON).accept(ContentType.JSON).
 * when().post().then() .statusCode(HttpStatus.CREATED.value()); }
 * 
 * // // @Test // public void testarCadastroCozinhaSuccess() { // // // Scenario
 * // Cozinha novaCozinha = new Cozinha(); // novaCozinha.setNome("Alemã"); //
 * // // action // novaCozinha = cadastroCozinha.salvar(novaCozinha); // // //
 * validation // assertThat(novaCozinha).isNotNull(); //
 * assertThat(novaCozinha.getId()).isNotNull(); // } // // @Test // public void
 * testarCadastroCozinhaSemNome() { // // Scenario // Cozinha novaCozinha = new
 * Cozinha(); // novaCozinha.setNome(null); // // // action //
 * ConstraintViolationException erroEsperado =
 * Assertions.assertThrows(ConstraintViolationException.class, () -> { //
 * cadastroCozinha.salvar(novaCozinha); // }); // // // validation //
 * assertThat(erroEsperado).isNotNull(); // } // // // @Test // public void
 * testarExclusaoDeCozinhaEmUso() { // // Scenario // Long cozinhaId = 1L; // //
 * // action // EntidadeEmUsoException erroEsperado =
 * Assertions.assertThrows(EntidadeEmUsoException.class, () -> { //
 * cadastroCozinha.excluir(cozinhaId);; // }); // // // validation //
 * assertThat(erroEsperado).isNotNull(); // } // // // @Test // public void
 * testarExclusaoDeCozinhaEmInexistente() { // // Scenario // Long cozinhaId =
 * 10L; // // // action // CozinhaNaoEncontradaException erroEsperado =
 * Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> { //
 * cadastroCozinha.excluir(cozinhaId);; // }); // // // validation //
 * assertThat(erroEsperado).isNotNull(); // } // }
 */