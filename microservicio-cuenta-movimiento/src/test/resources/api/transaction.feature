Feature: Pruebas del endpoint de Movimietos

  Background:
    * url 'http://localhost:8084/api/movimientos'

  Scenario: Obtener todos los movimientos
    When method GET
    Then status 200
    And match response == { movimientos: '#[]' }

  Scenario: Crear un nuevo movimiento
    Given request { "id": 0,"fecha": "2024-07-02", "tipoMovimiento": "DEPOSITO", "valor": 5,"numeroCuenta":"123456789" }
    When method POST
    Then status 201
    And match response.tipoMovimiento == 'DEPOSITO'
    And match response.valor == 5
