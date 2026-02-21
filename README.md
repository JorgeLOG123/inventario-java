# Inventario Java (POO)

Sistema de inventario por consola para practicar POO y lógica de negocio.

## Funcionalidades (MVP)
- Alta de productos (SKU único)
- Ingreso de stock (stock in)
- Egreso de stock (stock out, no permite stock negativo)
- Ajuste de stock
- Consulta de stock por SKU
- Listado de productos con stock bajo (<= mínimo)
- Registro de movimientos (auditoría)

## Reglas
- SKU no puede repetirse
- Cantidades deben ser > 0 (excepto ajuste directo)
- No se permite stock negativo
- Si SKU no existe, se informa error sin cerrar el programa

## Cómo ejecutar
1. Abrir el proyecto en IntelliJ
2. Ejecutar `Main`

## Ejemplos
- Crear producto: SKU=ABC123, name=Teclado, price=1000
- Stock in: ABC123 +10
- Stock out: ABC123 -3