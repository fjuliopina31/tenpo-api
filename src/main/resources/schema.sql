CREATE TABLE IF NOT EXISTS call_history (
    id SERIAL PRIMARY KEY,
    timestamp TIMESTAMP,
    endpoint VARCHAR(255),
    parameters TEXT,
    response TEXT,
    error BOOLEAN
);
