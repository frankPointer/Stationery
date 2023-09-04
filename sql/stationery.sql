-- 创建文具类别表
CREATE TABLE Category
(
    CategoryID   INT PRIMARY KEY,      -- 类别ID
    CategoryName VARCHAR(50) NOT NULL, -- 类别名称
    Description  VARCHAR(200)          -- 描述
);

-- 创建供应商表
CREATE TABLE Supplier
(
    SupplierID    INT PRIMARY KEY,       -- 供应商ID
    SupplierName  VARCHAR(50)  NOT NULL, -- 供应商名称
    ContactPerson VARCHAR(50)  NOT NULL, -- 联系人
    ContactNumber VARCHAR(20)  NOT NULL, -- 联系电话
    Address       VARCHAR(100) NOT NULL  -- 地址
);

-- 创建客户表
CREATE TABLE Customer
(
    CustomerID    INT PRIMARY KEY,       -- 客户ID
    CustomerName  VARCHAR(50)  NOT NULL, -- 客户名称
    ContactNumber VARCHAR(20)  NOT NULL, -- 联系电话
    Address       VARCHAR(100) NOT NULL  -- 地址
);

-- 创建订单表
CREATE TABLE Orders
(
    OrderID      INT PRIMARY KEY,         -- 订单ID
    CustomerID   INT            NOT NULL, -- 客户ID，关联到客户表中的客户ID
    OrderDate    DATE           NOT NULL, -- 订单日期
    OrderStatus  VARCHAR(20)    NOT NULL, -- 订单状态
    DeliveryDate DATE,                    -- 发货日期
    TotalAmount  DECIMAL(10, 2) NOT NULL, -- 总金额
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID)
);

-- 创建商品表
CREATE TABLE Product
(
    ProductID     INT PRIMARY KEY,         -- 商品ID
    ProductName   VARCHAR(50)    NOT NULL, -- 商品名称
    CategoryID    INT            NOT NULL, -- 类别ID，关联到文具类别表中的类别ID
    SupplierID    INT            NOT NULL, -- 供应商ID，关联到供应商表中的供应商ID
    Price         DECIMAL(10, 2) NOT NULL, -- 价格
    StockQuantity INT            NOT NULL, -- 库存数量
    Description   VARCHAR(200),            -- 描述
    FOREIGN KEY (CategoryID) REFERENCES category (CategoryID),
    FOREIGN KEY (SupplierID) REFERENCES Supplier (SupplierID)
);

-- 创建订单明细表
CREATE TABLE OrdersDetail
(
    OrderDetailID INT PRIMARY KEY,         -- 订单明细ID
    OrderID       INT            NOT NULL, -- 订单ID，关联到订单表中的订单ID
    ProductID     INT            NOT NULL, -- 商品ID，关联到文具商品表中的商品ID
    Quantity      INT            NOT NULL, -- 数量
    UnitPrice     DECIMAL(10, 2) NOT NULL, -- 单价
    TotalAmount   DECIMAL(10, 2) NOT NULL, -- 总金额
    FOREIGN KEY (OrderID) REFERENCES Orders (OrderID),
    FOREIGN KEY (ProductID) REFERENCES product (ProductID)
);
