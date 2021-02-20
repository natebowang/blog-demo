CREATE TABLE public.blog
(
    id            SERIAL primary key,

    title         varchar(2000) NOT NULL,
    content       varchar(5000) NOT NULL,

    record_status smallint     NOT NULL DEFAULT 0,
    mtime         timestamptz  NOT NULL DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON COLUMN blog.record_status IS '0. Normal; 1. Deleted';
