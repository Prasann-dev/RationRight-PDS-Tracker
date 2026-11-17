# CP-077 RationRight: Entitlement-Based Public Distribution Tracker

## How to run
javac *.java
java RationRight

## Features that work
1. Register family cards with card ID, head of family name, card type (AAY / PHH / NPHH) and number of members, rejecting duplicate IDs and empty inputs
2. Calculate monthly entitlement for rice, wheat and sugar based on card type and family size
3. Record monthly ration distribution with YYYY-MM validation and block duplicate collection for any previously claimed month
4. Track shop inventory for rice, wheat and sugar, and refuse distribution if available stock is less than required
5. Generate monthly status reports showing families yet to collect and remaining shop stock

## Sample run
```text
========================================
   RATIONRIGHT: PDS DISTRIBUTION TRACKER 
========================================
1. Register a New Family Card
2. Calculate & View Monthly Entitlements
3. Record Distribution & Block Duplicates
4. Check Shop Stock & Inventory
5. Generate Reports (Pending & Stock)
6. Exit
Enter your choice (1-6): 1

--- Register New Family Card ---
Enter Card ID (e.g., R001): R001
Enter Head of Family Name: Rahul
Enter Card Type (AAY / PHH / NPHH): PHH
Enter Number of Members: 5
Family successfully registered!

========================================
   RATIONRIGHT: PDS DISTRIBUTION TRACKER 
========================================
1. Register a New Family Card
2. Calculate & View Monthly Entitlements
3. Record Distribution & Block Duplicates
4. Check Shop Stock & Inventory
5. Generate Reports (Pending & Stock)
6. Exit
Enter your choice (1-6): 2

--- Monthly Entitlement Calculator ---
Enter Card ID to check entitlement: R001

Entitlement Details for Rahul (PHH):
----------------------------------------
Rice (5 members * 4.0kg): 20.0 kg
Wheat (5 members * 2.0kg): 10.0 kg
Sugar (Fixed quota): 1.0 kg

========================================
   RATIONRIGHT: PDS DISTRIBUTION TRACKER 
========================================
1. Register a New Family Card
2. Calculate & View Monthly Entitlements
3. Record Distribution & Block Duplicates
4. Check Shop Stock & Inventory
5. Generate Reports (Pending & Stock)
6. Exit
Enter your choice (1-6): 3

--- Record Ration Distribution ---
Enter Card ID: R001
Enter Current Collection Month (YYYY-MM, e.g., 2026-11): 2026-11
Distribution successful! Rations issued and stock updated for Rahul
Issued -> Rice: 20.0 kg, Wheat: 10.0 kg, Sugar: 1.0 kg

========================================
   RATIONRIGHT: PDS DISTRIBUTION TRACKER 
========================================
1. Register a New Family Card
2. Calculate & View Monthly Entitlements
3. Record Distribution & Block Duplicates
4. Check Shop Stock & Inventory
5. Generate Reports (Pending & Stock)
6. Exit
Enter your choice (1-6): 3

--- Record Ration Distribution ---
Enter Card ID: R001
Enter Current Collection Month (YYYY-MM, e.g., 2026-11): 2026-11
ACCESS DENIED: This family has already collected their quota for 2026-11!

========================================
   RATIONRIGHT: PDS DISTRIBUTION TRACKER 
========================================
1. Register a New Family Card
2. Calculate & View Monthly Entitlements
3. Record Distribution & Block Duplicates
4. Check Shop Stock & Inventory
5. Generate Reports (Pending & Stock)
6. Exit
Enter your choice (1-6): 5

--- PDS Status & Collection Report ---
Enter reporting month to check (YYYY-MM, e.g., 2026-11): 2026-11

[1] Families Yet to Collect for 2026-11:
 All registered families have collected their rations for this month.

[2] Remaining Stock Summary:

--- Current Shop Stock ---
Rice: 80.0 kg
Wheat: 90.0 kg
Sugar: 49.0 kg

========================================
   RATIONRIGHT: PDS DISTRIBUTION TRACKER 
========================================
1. Register a New Family Card
2. Calculate & View Monthly Entitlements
3. Record Distribution & Block Duplicates
4. Check Shop Stock & Inventory
5. Generate Reports (Pending & Stock)
6. Exit
Enter your choice (1-6): 6
Exiting RationRight. Program terminated.