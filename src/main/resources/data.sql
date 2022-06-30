INSERT INTO expense_categories (name)
	VALUES
		('Garden'),
		('Home'),
		('Car'),
		('Food'),
		('Bills');

INSERT INTO accounts (email, password)
	VALUES
		('user@gmail.com', '$2a$10$sXL0IVREAPeoL5bb1aydN.iUFS9eRG/URWToRkpZNnL.7tc9kIwxC'),
		('admin@gmail.com', '$2a$10$sXL0IVREAPeoL5bb1aydN.iUFS9eRG/URWToRkpZNnL.7tc9kIwxC');


INSERT INTO expenses (date, price, expense_category_id, account_id)
	VALUES
		('2012-09-17', 20.40, 1, 1),
		('2012-09-18', 22.53, 1, 1),
		('2020-04-17', 33.40, 2, 1),
		('2020-04-12', 3.40, 3, 2),
		('2020-06-12', 13.40, 3, 2),
		('2020-06-13', 53.40, 4, 2),
		('2020-06-14', 1.40, 4, 1);

INSERT INTO roles (name)
	VALUES
		('ADMIN'),
		('USER');

INSERT INTO privileges (name)
	VALUES
		('Delete expense'),
		('Add expense'),
		('Edit expense');

INSERT INTO accounts_roles (role_id, account_id)
	VALUES
		(1, 2),
		(2, 1);

INSERT INTO roles_privileges (role_id, privilege_id)
	VALUES
		(1, 1),
		(1, 2),
		(1, 3),
		(2, 2);

INSERT INTO accounts_expenses (account_id, expense_id)
	VALUES
		(1, 1),
		(2, 2),
		(1, 3),
		(2, 4);
