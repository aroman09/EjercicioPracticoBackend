Feature: Pruebas del endpoint de Cuentas

  Background:
    * url 'http://localhost:8084/api/cuentas'

  Scenario: Obtener todos los movimientos
    When method GET
    Then status 200
    And match response == { '#[]' }

  Scenario: Crear un nuevo movimiento
    Given request { "numeroCuenta": "123456784", "tipoCuenta": "CORRIENTE", "saldoInicial": 100, "estado": true, "idCliente":"1"}
    When method POST
    Then status 201
    And match response.tipoCuenta == 'CORRIENTE'
    And match response.numeroCuenta == '123456784'