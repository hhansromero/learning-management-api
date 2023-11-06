-- student table
CREATE TABLE IF NOT EXISTS student (
    id serial NOT NULL,
    ssn_number varchar(100) NOT NULL,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    birth_date DATE NOT NULL DEFAULT CURRENT_DATE,
    address varchar(100) NOT NULL,
    email_address varchar(100) NOT NULL,
    phone_number varchar(100),
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    CONSTRAINT student_pk PRIMARY KEY (id)
);
COMMENT ON TABLE student IS 'This is for saving basic information about Students.';
COMMENT ON COLUMN student.ssn_number IS 'Student SSN number.';
COMMENT ON COLUMN student.first_name IS 'Student first name.';
COMMENT ON COLUMN student.last_name IS 'Student last name.';
COMMENT ON COLUMN student.birth_date IS 'Student date of birth.';
COMMENT ON COLUMN student.address IS 'Student address.';
COMMENT ON COLUMN student.email_address IS 'Student email address.';
COMMENT ON COLUMN student.phone_number IS 'Student phone number.';
COMMENT ON COLUMN student.created_at IS 'Audit field for creating time.';
COMMENT ON COLUMN student.updated_at IS 'Audit field for updating time.';

-- course table
CREATE TABLE IF NOT EXISTS course (
    id serial NOT NULL,
    name varchar(100) NOT NULL,
    start_date DATE NOT NULL DEFAULT CURRENT_DATE,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NULL,
    CONSTRAINT course_pk PRIMARY KEY (id)
);

-- category table
CREATE TABLE IF NOT EXISTS category (
    id serial NOT NULL,
    name varchar(50) NOT NULL,
    CONSTRAINT category_pk PRIMARY KEY (id)
);

-- student_assignment table
CREATE TABLE IF NOT EXISTS student_assignment (
    id serial NOT NULL,
    student_id integer NOT NULL,
    course_id integer NOT NULL,
    assignment_date DATE NOT NULL DEFAULT CURRENT_DATE,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    CONSTRAINT student_assignment_pk PRIMARY KEY (id),
    CONSTRAINT student_assignment_fk_student FOREIGN KEY (student_id) REFERENCES student(id),
    CONSTRAINT student_assignment_fk_course FOREIGN KEY (course_id) REFERENCES course(id)
);

-- task table
CREATE TABLE IF NOT EXISTS task (
    id serial NOT NULL,
    task_date DATE NOT NULL DEFAULT CURRENT_DATE,
    hours varchar(10) NOT NULL,
    category_id integer NOT NULL,
    description text NOT NULL,
    student_assignment_id integer NOT NULL,
    created_at timestamptz NOT NULL,
    updated_at timestamptz NOT NULL,
    CONSTRAINT task_pk PRIMARY KEY (id),
    CONSTRAINT task_fk_category FOREIGN KEY (category_id) REFERENCES category(id),
    CONSTRAINT task_fk_student_assignment FOREIGN KEY (student_assignment_id) REFERENCES student_assignment(id)
);
