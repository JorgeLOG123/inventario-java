# Inventory Java (OOP)

Console-based inventory system built in Java to practice Object-Oriented Programming (OOP) and basic business logic.

## Features
- Add products (unique SKU)
- Stock in (increase quantity)
- Stock out (decrease quantity, no negative stock)
- Get stock by SKU
- List low stock items (quantity <= minStock)
- Input validation (handles invalid numeric input in the menu)

## Project Structure
- `domain/`
    - `Product` (sku, name, price)
    - `InventoryItem` (product, quantity, minStock + increase/decrease/isLowStock)
- `service/`
    - `InventoryService` (Map<sku, InventoryItem> + business rules)
- `app/`
    - `Main` (CLI menu using Scanner)

## How to Run
1. Open the project in IntelliJ IDEA
2. Run: `Main`  
   Path: `src/com/jorgelog123/inventario/app/Main.java`

## Menu Options
1. Add product
2. Stock in
3. Stock out
4. List low stock
0. Exit

> Note: `Name` is read with `Scanner.next()`, so it should not contain spaces.

## Example Session
Add a product:
- SKU: `TEC_012`
- Name: `Keyboard`
- Price: `12000`
- Initial quantity: `2`
- Min stock: `5`

Then choose:
- `4) List low stock` → should list `TEC_012` as low stock.

## Error Handling
- Duplicate SKU → `IllegalArgumentException`
- Missing SKU → `IllegalArgumentException`
- Invalid amounts (<= 0) → `IllegalArgumentException`
- Stock out greater than available stock → `IllegalArgumentException`
- Invalid numeric input in menu → handled with `InputMismatchException`

## Git Workflow (quick)
```bash
git status
git add -A
git commit -m "message"
git push