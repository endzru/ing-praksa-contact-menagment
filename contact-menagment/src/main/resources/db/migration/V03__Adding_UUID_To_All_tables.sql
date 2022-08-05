alter table contact_type add column uid uuid not null unique default (gen_random_uuid());
alter table roles add column uid uuid not null unique default (gen_random_uuid());
alter table contacts add column uid uuid not null unique default (gen_random_uuid());