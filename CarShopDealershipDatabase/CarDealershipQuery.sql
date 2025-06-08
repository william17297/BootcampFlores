-- 1. Get all dealerships

SELECT * FROM dealerships;

-- 2. Find all vehicles for a specific dealership
SELECT *
FROM vehicles
JOIN inventory i on vehicles.VIN = i.VIN
WHERE DealershipID = 4;

-- 3. Find a car by VIN
SELECT *
FROM vehicles
WHERE VIN = 'KMHDH4AE1CU123456';

-- 4. Find the dealership where a certain car is located, by VIN
SELECT *
FROM dealerships d
JOIN inventory i  ON d.DealershipID = i.DealershipID
WHERE VIN = 'KMHDH4AE1CU123456';

-- 5. Find all Dealerships that have a certain car type (i.e. Red Ford Mustang)
SELECT *
FROM dealerships d
JOIN inventory i  ON d.DealershipID = i.DealershipID
JOIN vehicles v ON i.VIN = v.VIN
WHERE vehicleType = 'SUV';

-- 6. Get all sales information for a specific dealer for a specific date range
SELECT *
FROM salescontract s 
JOIN vehicles v ON s.VIN = v.VIN
JOIN inventory i ON v.Vin = i.VIN
JOIN dealerships d ON i.DealershipID = i.DealershipID
WHERE d.DealershipID = 1 AND 
STR_TO_DATE(s.Date, '%Y-%m-%d') BETWEEN '2025-02-01' AND '2025-04-01';  
