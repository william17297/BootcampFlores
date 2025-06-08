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
	VIN VARCHAR(50) PRIMARY KEY,
    VehicleName VARCHAR(50), 
    VehicleMake VARCHAR(50),
    VehicleType VARCHAR(50),
    VehicleYear int,
    Price decimal(18 , 2),
    Sold bit
);

CREATE TABLE Inventory (
	DealershipID INT,
	VIN VARCHAR(50),
	FOREIGN KEY (DealershipID) REFERENCES Dealerships(DealershipID),
	FOREIGN KEY (VIN) REFERENCES Vehicles(VIN)
);

CREATE TABLE SalesContract (
SalesID int AUTO_INCREMENT PRIMARY KEY,
	CustomerName VARCHAR(50),
    CustomerEmailAdress VARCHAR(50),
    CustomerAdress VARCHAR(50),
    CustomerPhone VARCHAR(50),
    Date VARCHAR(12),
    TaxAmount decimal(18 , 2),
    RecordingFee decimal(18 , 2),
    ProcessingFee decimal(18 , 2),
    Financed bit,
    MonthlyPayment decimal(18 , 2),
    TotalPrice decimal(18 , 2),
    VIN VARCHAR(50),
    FOREIGN KEY (VIN) REFERENCES Vehicles(VIN)
);

CREATE TABLE LeaseContract (
SalesID int AUTO_INCREMENT PRIMARY KEY,
	CustomerName VARCHAR(50),
    CustomerEmailAdress VARCHAR(50),
    CustomerAdress VARCHAR(50),
    CustomerPhone VARCHAR(50),
    Date VARCHAR(12),
    TaxAmount decimal(18 , 2),
    LeaseFee decimal(18 , 2),
    EndingValue decimal(18 , 2),
    MonthlyPayment decimal(18 , 2),
    TotalPrice decimal(18 , 2),	
    VIN VARCHAR(50),
    FOREIGN KEY (VIN) REFERENCES Vehicles(VIN)
);

INSERT INTO Dealerships (Name, Address, Phone) VALUES
('Auto Galaxy', '123 Main St, Houston, TX', '7135551234'),
('Fast Lane Motors', '456 Elm St, Dallas, TX', '2145555678'),
('Sunset Autos', '789 Oak Ave, Austin, TX', '5125557890'),
('DriveTime Hub', '321 Pine Rd, San Antonio, TX', '2105552345'),
('Elite Wheels', '654 Cedar Blvd, Fort Worth, TX', '8175553456');

INSERT INTO Vehicles (VIN, VehicleName, VehicleMake, VehicleType, VehicleYear, Price, Sold) VALUES
('JH4DA9450NS000826', 'Civic EX', 'Honda', 'Sedan', 2022, 22000.00, 0),
('1HGCM82633A004352', 'Accord LX', 'Honda', 'Sedan', 2021, 25000.00, 1),
('1FAFP4041WF152734', 'Mustang GT', 'Ford', 'Coupe', 2020, 28000.00, 0),
('WDBJF65J3XA456789', 'E320', 'Mercedes', 'Sedan', 2023, 46000.00, 0),
('2GCEK19T8Y1234567', 'Silverado', 'Chevrolet', 'Truck', 2022, 35000.00, 1),
('3VWFE21C04M000123', 'Jetta', 'Volkswagen', 'Sedan', 2019, 18000.00, 0),
('1N4AL11D75C109876', 'Altima', 'Nissan', 'Sedan', 2021, 23000.00, 1),
('1GCHK23D36F345678', 'Sierra', 'GMC', 'Truck', 2023, 39000.00, 0),
('5YJSA1AG9DFP12345', 'Model S', 'Tesla', 'Sedan', 2022, 70000.00, 0),
('1FTSW21R08EB12345', 'F-150', 'Ford', 'Truck', 2020, 34000.00, 1),
('WA1LFAFP9FA098765', 'Q5', 'Audi', 'SUV', 2021, 42000.00, 1),
('KMHDH4AE1CU123456', 'Elantra', 'Hyundai', 'Sedan', 2019, 17000.00, 0),
('2T1BURHE4GC123456', 'Corolla', 'Toyota', 'Sedan', 2022, 20000.00, 0),
('1G1ZT51826F123456', 'Malibu', 'Chevrolet', 'Sedan', 2020, 19500.00, 1),
('JTDKN3DU6A1234567', 'Prius', 'Toyota', 'Hatchback', 2021, 24000.00, 0),
('SALTY12485A123456', 'Discovery', 'Land Rover', 'SUV', 2023, 52000.00, 0),
('3FAHP0HA7AR123456', 'Fusion', 'Ford', 'Sedan', 2021, 21500.00, 1),
('1NXBU4EE9AZ123456', 'Matrix', 'Toyota', 'Wagon', 2020, 18500.00, 0),
('1C4RJFBG8EC123456', 'Grand Cherokee', 'Jeep', 'SUV', 2023, 44000.00, 1),
('WBA8D9G56JNU12345', '320i', 'BMW', 'Sedan', 2022, 39000.00, 0);

INSERT INTO Inventory (DealershipID, VIN) VALUES
(1, 'JH4DA9450NS000826'),
(1, '1HGCM82633A004352'),
(1, '1FAFP4041WF152734'),
(2, 'WDBJF65J3XA456789'),
(2, '2GCEK19T8Y1234567'),
(2, '3VWFE21C04M000123'),
(3, '1N4AL11D75C109876'),
(3, '1GCHK23D36F345678'),
(3, '5YJSA1AG9DFP12345'),
(4, '1FTSW21R08EB12345'),
(4, 'WA1LFAFP9FA098765'),
(4, 'KMHDH4AE1CU123456'),
(5, '2T1BURHE4GC123456'),
(5, '1G1ZT51826F123456'),
(5, 'JTDKN3DU6A1234567'),
(1, 'SALTY12485A123456'),
(2, '3FAHP0HA7AR123456'),
(3, '1NXBU4EE9AZ123456'),
(4, '1C4RJFBG8EC123456'),
(5, 'WBA8D9G56JNU12345');

INSERT INTO SalesContract (CustomerName, CustomerEmailAdress, CustomerAdress, CustomerPhone, Date, TaxAmount, RecordingFee, ProcessingFee, Financed, MonthlyPayment, TotalPrice, VIN) VALUES
('John Doe', 'john@example.com', '101 Birch St', '8325550101', '2025-01-10', 1500.00, 200.00, 300.00, 1, 300.00, 25000.00, '1HGCM82633A004352'),
('Jane Smith', 'jane@example.com', '202 Maple Rd', '2145550202', '2025-02-14', 1300.00, 150.00, 250.00, 0, 0.00, 24000.00, '1N4AL11D75C109876'),
('Alice Cooper', 'alice@example.com', '303 Spruce Ln', '5125550303', '2025-03-18', 1600.00, 180.00, 300.00, 1, 360.00, 27000.00, '2GCEK19T8Y1234567'),
('Bob Johnson', 'bob@example.com', '404 Fir Dr', '2105550404', '2025-04-22', 2000.00, 220.00, 310.00, 0, 0.00, 29000.00, '1FTSW21R08EB12345'),
('Mia Lee', 'mia@example.com', '505 Aspen Pl', '8175550505', '2025-05-26', 1800.00, 190.00, 290.00, 1, 350.00, 26000.00, '1G1ZT51826F123456'),
('Rick Ross', 'rick@example.com', '606 Palm Way', '7135550606', '2025-06-01', 3000.00, 250.00, 350.00, 0, 0.00, 34000.00, '1C4RJFBG8EC123456'),
('Nina Patel', 'nina@example.com', '707 Willow Ct', '2145550707', '2025-06-05', 1900.00, 200.00, 300.00, 1, 325.00, 25000.00, 'WA1LFAFP9FA098765'),
('Omar Lopez', 'omar@example.com', '808 Magnolia Blvd', '5125550808', '2025-06-06', 2700.00, 230.00, 320.00, 0, 0.00, 39000.00, '3FAHP0HA7AR123456'),
('Sally Kim', 'sally@example.com', '909 Redwood Cir', '2105550909', '2025-06-07', 2500.00, 220.00, 310.00, 1, 520.00, 45000.00, 'WBA8D9G56JNU12345'),
('Leo Chang', 'leo@example.com', '111 Juniper Ter', '8175551010', '2025-06-07', 3100.00, 260.00, 340.00, 0, 0.00, 41000.00, '1C4RJFBG8EC123456');

INSERT INTO LeaseContract (CustomerName, CustomerEmailAdress, CustomerAdress, CustomerPhone, Date, TaxAmount, LeaseFee, EndingValue, MonthlyPayment, TotalPrice, VIN) VALUES
('Tina Wells', 'tina@example.com', '121 River Rd', '8325551111', '2025-03-01', 900.00, 1000.00, 15000.00, 300.00, 25000.00, '5YJSA1AG9DFP12345'),
('Mark Yu', 'mark@example.com', '131 Forest Pkwy', '2145551212', '2025-04-02', 850.00, 1100.00, 12000.00, 270.00, 23000.00, 'KMHDH4AE1CU123456'),
('Ava Nguyen', 'ava@example.com', '141 Ocean Ave', '5125551313', '2025-05-05', 1000.00, 1200.00, 13000.00, 290.00, 24000.00, '2T1BURHE4GC123456'),
('Derek Lin', 'derek@example.com', '151 Hill St', '2105551414', '2025-06-06', 1100.00, 1300.00, 14000.00, 310.00, 25000.00, 'JTDKN3DU6A1234567'),
('Sophia Brown', 'sophia@example.com', '161 Lake Blvd', '8175551515', '2025-06-07', 1200.00, 1250.00, 13500.00, 320.00, 25500.00, 'SALTY12485A123456');
