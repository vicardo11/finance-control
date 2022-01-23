INSERT INTO expense_categories (name)
	VALUES
		('Garden'),
		('Home'),
		('Car'),
		('Food'),
		('Bills');

INSERT INTO accounts (email, password)
	VALUES
		('user@gmail.com', '{bcrypt}$2a$12$ysESXEFxwlgvnaiM7gPHhugBg2MP45YQ65UTvsGgK09cgf5ZOpsn2'),
		('admin@gmail.com', '{bcrypt}$2a$12$ysESXEFxwlgvnaiM7gPHhugBg2MP45YQ65UTvsGgK09cgf5ZOpsn2');


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
		('ROLE_ADMIN'),
		('ROLE_USER');

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
