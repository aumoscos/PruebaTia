-- Crear la base de datos (si no existe)
CREATE DATABASE tia;

-- Usar la base de datos
\c tia;

--Categorias para productos
CREATE TABLE categorias (
    categoria_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla de Tiendas
CREATE TABLE tiendas (
    tienda_id SERIAL PRIMARY KEY,           -- ID autoincremental de la tienda
    nombre VARCHAR(100) NOT NULL,           -- Nombre de la tienda
    ubicacion TEXT NOT NULL,                -- Ubicación o dirección de la tienda
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Fecha de creación
);

-- Tabla de Productos
CREATE TABLE productos (
    producto_id SERIAL PRIMARY KEY,         -- ID único del producto
    nombre VARCHAR(100) NOT NULL,           -- Nombre del producto
    descripcion TEXT DEFAULT '',            -- Descripción opcional
    precio DECIMAL(10,2) NOT NULL CHECK (precio >= 0), -- Precio >= 0
    estado BOOLEAN NOT NULL DEFAULT TRUE,   -- Si el producto está activo o no
    categoria_id INT REFERENCES categorias(categoria_id) ON DELETE SET NULL, -- Relación con categoría
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla intermedia: Stock y Ventas por Tienda
CREATE TABLE stock_tienda (
    stock_id SERIAL PRIMARY KEY,            -- ID único para el stock
    tienda_id INT NOT NULL REFERENCES tiendas(tienda_id) ON DELETE CASCADE, -- FK a Tiendas
    producto_id INT NOT NULL REFERENCES productos(producto_id) ON DELETE CASCADE, -- FK a Productos
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),  -- Stock disponible en la tienda
    ventas INT NOT NULL DEFAULT 0 CHECK (ventas >= 0),-- Ventas acumuladas
    UNIQUE (tienda_id, producto_id)          -- Garantiza que un producto no se duplique en una tienda
);

CREATE TABLE ventas (
    venta_id SERIAL PRIMARY KEY,
    tienda_id INT NOT NULL REFERENCES tiendas(tienda_id) ON DELETE CASCADE,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL CHECK (total >= 0),
    metodo_pago VARCHAR(50) NOT NULL CHECK (metodo_pago IN ('efectivo', 'tarjeta', 'transferencia'))
);

-- Insertar tiendas
INSERT INTO tiendas (nombre, ubicacion) VALUES 
('Tienda Centro', 'Av. Principal 123'),
('Tienda Norte', 'Calle Secundaria 456');

-- Insertar productos
INSERT INTO productos (nombre, descripcion, precio) VALUES 
('Laptop Dell', 'Laptop 15 pulgadas', 799.99),
('Sofá 3 plazas', 'Sofá de cuero negro', 599.99),
('Camiseta Nike', 'Talla M, color azul', 25.99);

-- Asignar stock a las tiendas
INSERT INTO stock_tienda (tienda_id, producto_id, stock, ventas) VALUES
(1, 1, 10, 2),  -- 10 laptops en Tienda Centro, 2 vendidas
(1, 2, 5, 1),   -- 5 sofás en Tienda Centro, 1 vendido
(2, 1, 8, 3),   -- 8 laptops en Tienda Norte, 3 vendidas
(2, 3, 20, 5);  -- 20 camisetas en Tienda Norte, 5 vendidas
