CREATE TABLE tb_raffle (
    id UUID PRIMARY KEY,
    title VARCHAR(50),
    image_url VARCHAR(200),
    price DOUBLE PRECISION,
    status VARCHAR(50),
    sold BOOLEAN,
    create_at TIMESTAMP,
    buyer_id uuid,
    FOREIGN KEY (buyer_id) REFERENCES tb_buyer(id)
);