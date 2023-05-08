INSERT INTO currency(symbol)
VALUES
('JPY=X'),
('GBPUSD=X'),
('AUDUSD=X'),
('NZDUSD=X'),
('CNY=X'),
('HKD=X'),
('SGD=X'),
('INR=X'),
('MXN=X'),
('PHP=X'),
('IDR=X'),
('THB=X'),
('MYR=X'),
('ZAR=X')
ON CONFLICT DO NOTHING;