CREATE TABLE IF NOT EXISTS TELEGRAM_USER
(
    ID       BIGSERIAL PRIMARY KEY,
    CHAT_ID  BIGINT       NOT NULL UNIQUE,
    USERNAME VARCHAR(255) NULL
);

CREATE TABLE IF NOT EXISTS TELEGRAM_MESSAGE
(
    ID       BIGSERIAL PRIMARY KEY,
    DATA     TEXT         NOT NULL,
    USERNAME VARCHAR(255) NULL
);

CREATE TABLE IF NOT EXISTS TELEGRAM_USER_TO_MESSAGE
(
    ID                  BIGSERIAL PRIMARY KEY,
    TELEGRAM_USER_ID    BIGINT REFERENCES TELEGRAM_USER (ID) ON DELETE SET NULL ON UPDATE RESTRICT    NULL,
    TELEGRAM_MESSAGE_ID BIGINT REFERENCES TELEGRAM_MESSAGE (ID) ON DELETE SET NULL ON UPDATE RESTRICT NULL
);