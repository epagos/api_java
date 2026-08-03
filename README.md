# ePagos Java SDK

SDK Java que consume la API REST publicada por ePagos.

## Requisitos

- Java 17+
- Maven 3.9+

## Instalacion local

```powershell
mvn -f java-sdk/pom.xml clean package
```

Para usarlo desde otro proyecto Maven local:

```powershell
mvn -f java-sdk/pom.xml install
```

```xml
<dependency>
  <groupId>com.epagos</groupId>
  <artifactId>epagos-sdk</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Ambientes

- Sandbox: `https://sandbox.epagos.com/v1.0`
- Produccion: `https://api.epagos.com/v1.0`

```java
EpagosClient sandbox = EpagosClient.sandbox();
EpagosClient production = EpagosClient.production();
```

## Metodos implementados

- `obtenerToken(Credenciales)`
- `obtenerPagos(Credenciales, FiltroObtenerPagos)`
- `obtenerDevoluciones(Credenciales, FiltroObtenerPagos)`
- `obtenerContracargos(Credenciales, FiltroObtenerContracargos)`
- `obtenerPagosAdicionales(Credenciales, FiltroPagosAdicionales)`
- `obtenerRendiciones(Credenciales, FiltroObtenerRendiciones)`
- `solicitudPagos(Credenciales, Operacion, List<FormaPago>, int convenio)`
- `solicitudPagosLote(Credenciales, List<SolicitudLoteItem>)`
- `pagoLote(Credenciales, PagoLote)`
- `obtenerEntidadesPago(Credenciales, List<Integer>)`
- `generarOrdenQr(Credenciales, OrdenQr)`
- `obtenerCajasQr(Credenciales)`

Los metodos de negocio obtienen primero un token con `/token` y luego envian el request con `id_organismo` y `token`.

## Ejemplo basico

```java
Credenciales credenciales = new Credenciales(
    "<hash>",
    "<password>",
    58,
    1
);

EpagosClient client = EpagosClient.sandbox();
FiltroObtenerPagos filtro = new FiltroObtenerPagos();
filtro.fechaNovedadAcreditacionDesde = "2026-06-01";
filtro.fechaNovedadAcreditacionHasta = "2026-06-25";

EpagosResult<ObtenerPagosRequest, ObtenerPagosResponse> result =
    client.obtenerPagos(credenciales, filtro);

```

## Ejemplos

Los ejemplos estan en `src/main/java/com/epagos/sdk/examples` y compilan junto con el SDK.

Los ejemplos leen primero variables de entorno reales del proceso y, si no existen, buscan un archivo `.env` desde el directorio actual hacia arriba. Si queres indicar una ruta puntual, usa `EPAGOS_ENV_FILE`.

Formato de `.env`:

```dotenv
EPAGOS_ENVIRONMENT=sandbox
EPAGOS_HASH=<hash>
EPAGOS_PASSWORD=<password>
EPAGOS_ID_ORGANISMO=<organismo>
EPAGOS_ID_USUARIO=<usuario>
EPAGOS_CONVENIO=<convenio>
```

Variables usadas:

- `EPAGOS_ENVIRONMENT`: `sandbox` por defecto, o `production` para produccion.
- `EPAGOS_HASH`
- `EPAGOS_PASSWORD`
- `EPAGOS_ID_ORGANISMO`
- `EPAGOS_ID_USUARIO`
- `EPAGOS_CONVENIO`

Ejecutar:

```powershell
mvn -f java-sdk/pom.xml exec:java -Dexec.mainClass=com.epagos.sdk.examples.ObtenerTokenExample
mvn -f java-sdk/pom.xml exec:java -Dexec.mainClass=com.epagos.sdk.examples.ObtenerPagosExample
mvn -f java-sdk/pom.xml exec:java -Dexec.mainClass=com.epagos.sdk.examples.ObtenerContracargosExample
mvn -f java-sdk/pom.xml exec:java -Dexec.mainClass=com.epagos.sdk.examples.ObtenerPagosAdicionalesExample
mvn -f java-sdk/pom.xml exec:java -Dexec.mainClass=com.epagos.sdk.examples.ObtenerRendicionesExample
mvn -f java-sdk/pom.xml exec:java -Dexec.mainClass=com.epagos.sdk.examples.SolicitudPagoExample
mvn -f java-sdk/pom.xml exec:java -Dexec.mainClass=com.epagos.sdk.examples.SolicitudPagoLoteExample
mvn -f java-sdk/pom.xml exec:java -Dexec.mainClass=com.epagos.sdk.examples.PagoLoteExample
mvn -f java-sdk/pom.xml exec:java -Dexec.mainClass=com.epagos.sdk.examples.ObtenerEntidadesPagoExample
mvn -f java-sdk/pom.xml exec:java -Dexec.mainClass=com.epagos.sdk.examples.GenerarOrdenQrExample
mvn -f java-sdk/pom.xml exec:java -Dexec.mainClass=com.epagos.sdk.examples.ObtenerCajasQrExample
```
