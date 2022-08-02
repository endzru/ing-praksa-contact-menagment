CREATE TABLE IF NOT EXISTS "users"
(
    "id"           SERIAL PRIMARY KEY,
    "email"        varchar UNIQUE,
    "first_name"   varchar not null,
    "last_name"    varchar not null,
    "password"     varchar not null,
    "id_role"      bigint  not null,
    "time_created" TIMESTAMP,
    "time_updated" TIMESTAMP
);

CREATE TABLE IF NOT EXISTS "roles"
(
    "id"        SERIAL PRIMARY KEY,
    "role_name" varchar not null
);

ALTER TABLE "users"
    ADD FOREIGN KEY ("id_role") REFERENCES "roles" ("id");

CREATE TABLE "contact_type"
(
    "id"                SERIAL PRIMARY KEY,
    "contact_type_name" varchar,
    "time_created"      TIMESTAMP,
    "time_updated"      TIMESTAMP
);

CREATE TABLE "contacts"
(
    "id"                  SERIAL PRIMARY KEY,
    "id_contactType"      bigint  not null,
    "id_user"             bigint  not null,
    "contact_first_name"  varchar not null,
    "contact_last_name"   varchar not null,
    "contact_email"       varchar not null,
    "contact_phonenumber" varchar not null
);

ALTER TABLE "contacts"
    ADD FOREIGN KEY ("id_contactType") REFERENCES "contact_type" ("id");
ALTER TABLE "contacts"
    ADD FOREIGN KEY ("id_user") REFERENCES "users" ("id");





