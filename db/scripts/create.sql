
CREATE TABLE accident_types (
    id serial primary key,
    name text
);

CREATE TABLE accidents (
    id serial primary key,
    name text,
    description text,
    address text,
    accidentType_id int references accident_types(id)
);

CREATE TABLE rules (
    id serial primary key,
    rule_name text
);

CREATE TABLE accident_rules (
    id serial primary key,
    accident_id integer REFERENCES accidents(id),
    rule_id integer REFERENCES rules(id)
);



