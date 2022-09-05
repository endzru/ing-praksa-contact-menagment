CREATE TABLE IF NOT EXISTS "users"
(
    "id"           BIGSERIAL PRIMARY KEY,
    "email"        varchar UNIQUE not null,
    "first_name"   varchar not null,
    "last_name"    varchar not null,
    "password"     varchar not null,
    "role_id"      bigint  not null,
    "time_created" TIMESTAMP not null,
    "time_updated" TIMESTAMP not null
);

CREATE TABLE IF NOT EXISTS "roles"
(
    "id"        BIGSERIAL PRIMARY KEY,
    "role_name" varchar not null
);

ALTER TABLE "users"
    ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("id");

CREATE TABLE "contact_type"
(
    "id"                BIGSERIAL PRIMARY KEY,
    "contact_type_name" varchar,
    "time_created"      TIMESTAMP not null,
    "time_updated"      TIMESTAMP not null
);

CREATE TABLE "contacts"
(
    "id"                  BIGSERIAL PRIMARY KEY,
    "contact_type_id"      bigint  not null,
    "user_id"             bigint  not null,
    "contact_first_name"  varchar not null,
    "contact_last_name"   varchar not null,
    "contact_email"       varchar,
    "contact_phonenumber" varchar not null,
    "time_created" TIMESTAMP not null,
    "time_updated" TIMESTAMP not null
);

ALTER TABLE "contacts"
    ADD FOREIGN KEY ("contact_type_id") REFERENCES "contact_type" ("id");
ALTER TABLE "contacts"
    ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

