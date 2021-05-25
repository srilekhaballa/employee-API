CREATE TABLE public.department(
    dept_id int4 NOT NULL,
    d_name varchar NULL,
    description text NULL,
    created_by varchar(255) NULL,
    created_on varchar(255) NULL,
    PRIMARY KEY(dept_id)
);

CREATE TABLE public.employee(
	emp_id int4 NOT NULL,
	dept_id int4 NOT NULL,
	created_by varchar(255) NULL,
	created_on varchar(255) NULL,
	email varchar(255) NULL,
	name varchar(255) NULL,
	phone varchar(255) NULL,
	updated_by varchar(255) NULL,
	updated_on varchar(255) NULL,
        date_of_joining varchar(255) NULL,
	CONSTRAINT employee_pkey PRIMARY KEY (emp_id),
	CONSTRAINT fk_department FOREIGN KEY (dept_id) REFERENCES public.department(dept_id)
);



 