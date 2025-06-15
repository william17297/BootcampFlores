DROP DATABASE IF EXISTS dealership;

CREATE DATABASE IF NOT EXISTS dealership;

USE dealership;

CREATE TABLE Dealerships(
	DealershipID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50), 
    Address VARCHAR(50),
    Phone VARCHAR(12)
);

CREATE TABLE Vehicles (
	VIN int PRIMARY KEY,
    VehicleName VARCHAR(50), 
    VehicleMake VARCHAR(50),
    VehicleType VARCHAR(50),
    VehicleColor VARCHAR(50),
    VehicleYear int,
    Price DOUBLE(18 , 2),
    Sold bit,
    Leased bit,
    Odometer int
);

CREATE TABLE Inventory (
	DealershipID INT,
	VIN int,
	FOREIGN KEY (DealershipID) REFERENCES Dealerships(DealershipID),
	FOREIGN KEY (VIN) REFERENCES Vehicles(VIN)
);

CREATE TABLE SalesContract (
SalesID int AUTO_INCREMENT PRIMARY KEY,
	CustomerName VARCHAR(50),
    CustomerEmailAddress VARCHAR(50),
    Date VARCHAR(12),
    TaxAmount decimal(18 , 2),
    RecordingFee decimal(18 , 2),
    ProcessingFee decimal(18 , 2),
    Financed bit,
    MonthlyPayment decimal(18 , 2),
    TotalPrice DOUBLE(18 , 2),
    VIN int,
    FOREIGN KEY (VIN) REFERENCES Vehicles(VIN)
);

CREATE TABLE LeaseContract (
SalesID int AUTO_INCREMENT PRIMARY KEY,
	CustomerName VARCHAR(50),
    CustomerEmailAddress VARCHAR(50),
    Date VARCHAR(12),
    LeaseFee decimal(18 , 2),
    EndingValue decimal(18 , 2),
    MonthlyPayment decimal(18 , 2),
    TotalPrice decimal(18 , 2),	
    VIN int,
    FOREIGN KEY (VIN) REFERENCES Vehicles(VIN)
);

INSERT INTO Dealerships (Name, Address, Phone) VALUES
('Auto Galaxy', '123 Main St, Houston, TX', '7135551234'),
('Fast Lane Motors', '456 Elm St, Dallas, TX', '2145555678'),
('Sunset Autos', '789 Oak Ave, Austin, TX', '5125557890'),
('DriveTime Hub', '321 Pine Rd, San Antonio, TX', '2105552345'),
('Elite Wheels', '654 Cedar Blvd, Fort Worth, TX', '8175553456');

INSERT INTO Vehicles (VIN, VehicleName, VehicleMake, VehicleType, VehicleColor, VehicleYear, Price, Sold , Leased , Odometer) VALUES
(2345678, 'Civic EX', 'Honda', 'sedan', 'Red', 2022, 22000.00, 0, 1, 15000),
(3456789, 'Accord LX', 'Honda', 'sedan', 'Black', 2021, 25000.00, 1, 0, 20000),
(4567890, 'Mustang GT', 'Ford', 'sedan', 'Yellow', 2020, 28000.00, 0, 1, 18000),
(5678901, 'E320', 'Mercedes', 'sedan', 'Silver', 2023, 46000.00, 0, 1, 5000),
(6789012, 'Silverado', 'Chevrolet', 'truck', 'Blue', 2022, 35000.00, 0, 1, 25000),
(7890123, 'Jetta', 'Volkswagen', 'sedan', 'White', 2019, 18000.00, 0, 1, 30000),
(8901234, 'Altima', 'Nissan', 'sedan', 'Gray', 2021, 23000.00, 1, 0, 22000),
(9012345, 'Sierra', 'GMC', 'truck', 'Black', 2023, 39000.00, 0, 0, 7000),
(1123456, 'Model S', 'Tesla', 'sedan', 'White', 2022, 70000.00, 1, 0, 12000),
(1223456, 'F-150', 'Ford', 'truck', 'Blue', 2020, 34000.00, 1, 0, 35000),
(1323456, 'Q5', 'Audi', 'SUV', 'Gray', 2021, 42000.00, 1 , 0, 15000),
(1423456, 'Elantra', 'Hyundai', 'sedan', 'Red', 2019, 17000.00, 0, 0, 40000),
(1523456, 'Corolla', 'Toyota', 'sedan', 'Silver', 2022, 20000.00, 0, 0, 18000),
(1623456, 'Malibu', 'Chevrolet', 'Impala', 'Blue', 2020, 19500.00, 1, 0, 22000),
(1723456, 'Prius', 'Toyota', 'sedan', 'Green', 2021, 24000.00, 1, 0, 10000),
(1823456, 'Discovery', 'Land Rover', 'SUV', 'White', 2023, 52000.00, 1, 0, 3000),
(1923456, 'Fusion', 'Ford', 'sedan', 'Black', 2021, 21500.00, 1, 0, 21000),
(2023456, 'Matrix', 'Toyota', 'wagon', 'Red', 2020, 18500.00, 1, 0, 28000),
(2123456, 'Grand Cherokee', 'Jeep', 'SUV', 'Silver', 2023, 44000.00, 0, 0, 6000),
(2234567, '320i', 'BMW', 'sedan', 'Blue', 2022, 39000.00, 0, 0, 12000);

INSERT INTO Inventory (DealershipID, VIN) VALUES
(1, 2345678), (1, 3456789), (1, 4567890), (2, 5678901), (2, 6789012),
(2, 7890123), (3, 8901234), (3, 9012345), (3, 1123456), (4, 1223456),
(4, 1323456), (4, 1423456), (5, 1523456), (5, 1623456), (5, 1723456),
(1, 1823456), (2, 1923456), (3, 2023456), (4, 2123456), (5, 2234567);


INSERT INTO SalesContract (CustomerName, CustomerEmailAddress, Date, TaxAmount, RecordingFee, ProcessingFee, Financed, MonthlyPayment, TotalPrice, VIN) VALUES
('John Doe', 'john@example.com', '2025-01-10', 1500.00, 200.00, 300.00, 1, 300.00, 25000.00, 3456789),
('Jane Smith', 'jane@example.com', '2025-02-14', 1300.00, 150.00, 250.00, 0, 0.00, 24000.00, 8901234),
('Alice Cooper', 'alice@example.com', '2025-03-18', 1600.00, 180.00, 300.00, 1, 360.00, 27000.00, 1123456),
('Bob Johnson', 'bob@example.com', '2025-04-22', 2000.00, 220.00, 310.00, 0, 0.00, 29000.00, 1223456),
('Mia Lee', 'mia@example.com', '2025-05-26', 1800.00, 190.00, 290.00, 1, 350.00, 26000.00, 1623456),
('Rick Ross', 'rick@example.com', '2025-06-01', 3000.00, 250.00, 350.00, 0, 0.00, 34000.00, 1723456),
('Nina Patel', 'nina@example.com', '2025-06-05', 1900.00, 200.00, 300.00, 1, 325.00, 25000.00, 1323456),
('Omar Lopez', 'omar@example.com', '2025-06-06', 2700.00, 230.00, 320.00, 0, 0.00, 39000.00, 9012345),
('Sally Kim', 'sally@example.com', '2025-06-07', 2500.00, 220.00, 310.00, 1, 520.00, 45000.00, 1823456),
('Leo Chang', 'leo@example.com', '2025-06-07', 3100.00, 260.00, 340.00, 0, 0.00, 41000.00, 2023456);


INSERT INTO LeaseContract (CustomerName, CustomerEmailAddress, Date, LeaseFee, EndingValue, MonthlyPayment, TotalPrice, VIN) VALUES
('Lara Grant', 'lara@example.com', '2025-06-08', 500.00, 10000.00, 400.00, 32000.00, 2345678),
('Kevin Wu', 'kevin@example.com', '2025-06-09', 600.00, 12000.00, 450.00, 35000.00, 4567890),
('Tina Hall', 'tina@example.com', '2025-06-10', 550.00, 11000.00, 420.00, 31000.00, 5678901),
('Derek Stone', 'derek@example.com', '2025-06-11', 620.00, 12500.00, 460.00, 37000.00, 6789012),
('Sophia Lin', 'sophia@example.com', '2025-06-12', 570.00, 10500.00, 430.00, 33000.00, 7890123);
