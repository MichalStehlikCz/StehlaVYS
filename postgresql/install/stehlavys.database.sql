CREATE DATABASE stehlavys
    WITH 
    OWNER = owner
    TEMPLATE = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = stehlavys
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE stehlavys
    IS 'Database that stores all StehlaVys configuration and data objects';
