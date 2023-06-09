create table if not exists Counter
(
    count int
);

create table Education
(
    id   int primary key generated by default as identity,
    name varchar
);

create table Gender
(
    id   int primary key generated by default as identity,
    name varchar
);

create table Marital
(
    id   int primary key generated by default as identity,
    name varchar
);

create table City
(
    id   int primary key generated by default as identity,
    name varchar
);

create table Activity
(
    id   int primary key generated by default as identity,
    name varchar
);

create table Person
(
    id            int primary key generated by default as identity,
    username      varchar(100) not null,
    password      varchar      not null,
    role          varchar(100) not null,
    surname       varchar(100) not null,
    firstname     varchar(100) not null,
    photo         varchar,
    date_of_birth date,
    gender_id     int          references Gender (id) on delete set null,
    city_id       int          references City (id) on delete set null,
    education_id  int          references Education (id) on delete set null,
    activity_id   int          references Activity (id) on delete set null,
    marital_id    int          references Marital (id) on delete set null
);

-- create table Person_Info
-- (
--     id             int primary key generated by default as identity,
--     user_id        int          not null references Person (id) on delete cascade,
--     surname        varchar(100) not null,
--     firstname      varchar(100) not null,
--     photo          varchar,
--     date_of_birth  date,
--     gender         varchar,
--     city_id        int          references City (id) on delete set null,
--     education      varchar,
--     activity_id    int          references City (id) on delete set null,
--     marital_status varchar
-- );
