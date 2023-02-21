CREATE SCHEMA "feature_manager";

CREATE TABLE "feature_manager"."feature" (
    id bigserial primary key,
    external_id uuid not null,
    name varchar(255) not null,
    description varchar(255),
    enabled boolean not null default false,
    valid_after timestamp,
    valid_before timestamp,
    created timestamp not null default now(),
    last_update timestamp not null default now()
);

CREATE TABLE "feature_manager"."rule" (
    id bigserial primary key,
    field varchar(255) not null ,
    rule_type varchar(50) not null,
    reference_value jsonb,
    operation_type varchar(50) not null,
    rule_composition_id bigint,
    rule_composition_type varchar(50),
    foreign key (rule_composition_id) references "feature_manager"."rule" (id)
);

CREATE TABLE "feature_manager"."feature_rule" (
    id bigserial primary key,
    feature_id bigint not null,
    rule_id bigint not null,
    foreign key (feature_id) references "feature_manager"."feature" (id),
    foreign key (rule_id) references "feature_manager"."rule" (id)
);

CREATE INDEX idx_feature_external_id ON feature_manager.feature (external_id);
