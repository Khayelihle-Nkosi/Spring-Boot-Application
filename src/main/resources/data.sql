-- Insert Personal data
INSERT INTO PERSONAL (id_number, first_name, last_name, gender, dob)
VALUES  (123456789, 'John', 'Doe', 'Male', '1980-01-01'),
        (987654321, 'Sam', 'Jones', 'Male', '1953-03-19');

-- Insert Contact data
INSERT INTO CONTACT (email, phone_number)
VALUES ('john.doe@example.com', 1234567890),
       ('sam.jones@example.com', 98765443210);

-- Insert Address data
INSERT INTO ADDRESS (house_number, street, city, province, postal, country)
VALUES (44, 'Main Street', 'Johannesburg', 'Gauteng', 011, 'RSA'),
       (61, 'Second Street', 'Pretoria', 'Gauteng', 012, 'RSA');

-- Insert Investor data (assuming Personal, Contact and Address IDs already exist)
INSERT INTO INVESTOR (personal_id, contact_id, address_id)
VALUES (1, 1, 1),
       (2, 2, 2);

-- Insert Product data
INSERT INTO PRODUCT (product_type, name, balance, investor_id)
VALUES ('SAVINGS', 'Savings Account', 10000.00, 1),
       ('RETIREMENT', 'Retirement Account', 100000.00, 1),
       ('SAVINGS', 'My Savings Account', 20000.00, 2),
       ('RETIREMENT', 'My Retirement Account', 200000.00, 2);

--Insert Notice Data
INSERT INTO WITHDRAWAL_NOTICE (withdrawal_amount, timestamp, product_id)
VALUES
    (500.00, '2024-03-11 10:00:00', 1),
    (750.00, '2024-03-06 10:30:00', 2),
    (1000.00, '2024-03-01 11:00:00', 3),
    (600.00, '2024-03-21 11:30:00', 4),
    (800.00, '2024-03-05 12:00:00', 1),
    (900.00, '2024-03-09 12:30:00', 2),
    (1100.00, '2024-03-30 13:00:00', 3),
    (700.00, '2024-03-17 13:30:00', 4),
    (1200.00, '2024-03-11 14:00:00', 1),
    (850.00, '2024-03-03 14:30:00', 2),

    (5000.00, '2024-03-11 07:00:00', 4),
    (7500.00, '2024-03-06 16:30:00', 4),
    (10000.00, '2024-03-11 11:00:00', 4),
    (6000.00, '2024-03-08 11:30:00', 4),
    (8000.00, '2024-03-09 12:00:00', 4),
    (9000.00, '2024-03-09 12:30:00', 4),
    (11000.00, '2024-03-10 13:00:00', 4),
    (7000.00, '2024-03-17 13:30:00', 4),
    (12000.00, '2024-03-11 14:00:00', 4),
    (8500.00, '2024-03-03 14:30:00', 4);

