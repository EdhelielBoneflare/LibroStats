CREATE TABLE user_acc
(
    id          VARCHAR(36)                 NOT NULL,
    username    VARCHAR(32)                 NOT NULL,
    password    VARCHAR(128)                NOT NULL,
    provider_id BIGINT,
    is_enabled  BOOLEAN                     NOT NULL,
    role        VARCHAR(16)                 NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_user_acc PRIMARY KEY (id)
);

ALTER TABLE user_acc
    ADD CONSTRAINT uc_user_acc_username UNIQUE (username);