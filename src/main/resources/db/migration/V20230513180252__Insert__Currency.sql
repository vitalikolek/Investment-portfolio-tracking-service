INSERT INTO stock(symbol, type)
VALUES
('JPY=X', 2),
('GBPUSD=X', 2),
('AUDUSD=X', 2),
('NZDUSD=X', 2),
('CNY=X', 2),
('HKD=X', 2),
('SGD=X', 2),
('INR=X', 2),
('MXN=X', 2),
('PHP=X', 2),
('IDR=X', 2),
('THB=X', 2),
('MYR=X', 2),
('ZAR=X', 2)
ON CONFLICT DO NOTHING;