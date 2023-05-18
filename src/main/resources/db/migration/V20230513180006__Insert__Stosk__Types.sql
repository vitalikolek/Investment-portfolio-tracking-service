INSERT INTO stock_type(type)
VALUES
('cryptocurrency'),
('currency'),
('share')
ON CONFLICT DO NOTHING;